package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParser.IdentifierExpressionContext
import chapi.ast.antlr.TypeScriptParser.ParenthesizedExpressionContext
import chapi.ast.antlr.TypeScriptParser.VariableStatementContext
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class TypeScriptFullIdentListener(val node: TSIdentify) : TypeScriptAstListener() {
    private var hasHtmlElement: Boolean = false
    private var filePath: String = node.filePath

    private var exitArrowCount: Int = 0
    private var isCallbackOrAnonymousFunction: Boolean = false
    private var hasAnnotation: Boolean = false
    private var hasEnterClass = false
    private var currentExprIdent: String = ""
    private var localVars = mutableMapOf<String, String>()

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer =
        CodeContainer(
            FullName = node.filePath,
            PackageName = node.resolvePackage(),
            Language = "typescript",
            Kind = ContainerKind.MODULE,
            ResolvedModulePath = node.resolvePackage()
        )

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunc = CodeFunction(IsConstructor = false)
    private var namespaceName: String = ""
    private var currentAnnotations = listOf<CodeAnnotation>()

    private var funcsStackForCount = Stack<CodeFunction>()

    private var classNodeStack = Stack<CodeDataStruct>()

    override fun enterNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ctx!!.namespaceName().text
        // Update namespace path for structured semantics
        codeContainer.NamespacePath = codeContainer.NamespacePath + this.namespaceName
    }

    override fun exitNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        // Pop from namespace path when exiting
        if (codeContainer.NamespacePath.isNotEmpty()) {
            codeContainer.NamespacePath = codeContainer.NamespacePath.dropLast(1)
        }
        this.namespaceName = ""
    }

    override fun enterVariableStatement(ctx: VariableStatementContext?) {
        if (ctx == null) return

        val isExport = hasExportPrefix(ctx, 5)
        val declList = ctx.variableDeclarationList() ?: return

        if (!isExport) {
            // Normal top-level vars: add to default node fields (so default node exists)
            val newFields = variableToFields(declList)
            // Keep fields that contain calls (e.g., yield call(...)) in front for legacy tests.
            val withCalls = newFields.filter { it.Calls.isNotEmpty() }
            val withoutCalls = newFields.filter { it.Calls.isEmpty() }
            defaultNode.Fields = withCalls + defaultNode.Fields + withoutCalls
            return
        }

        // Exported vars: record export + fields with modifier
        val modifier = ctx.varModifier()?.text ?: ""
        val modifiers = if (modifier.isNotBlank()) listOf(modifier) else listOf()
        val fields = variableToFields(declList, modifiers)
        if (fields.isNotEmpty()) {
            defaultNode.Exports += CodeExport(
                Name = fields[0].TypeKey,
                Type = DataStructType.Variable,
                SourceFile = codeContainer.FullName
            )

            defaultNode.Fields += fields
        }
    }

    private fun hasExportPrefix(ctx: ParserRuleContext, maxDepth: Int): Boolean {
        var current: ParserRuleContext? = ctx.parent as? ParserRuleContext
        for (i in 0 until maxDepth) {
            if (current != null && current.childCount > 0 && current.getChild(0).text == "export") {
                return true
            }
            current = current?.parent as? ParserRuleContext
        }
        return false
    }

    private fun variableToFields(
        varDecl: TypeScriptParser.VariableDeclarationListContext,
        modifiers: List<String> = listOf()
    ): List<CodeField> {
        return varDecl.variableDeclaration()
            .map { decl ->
                variableToField(decl, modifiers)
            }
    }

    private fun variableToField(
        it: TypeScriptParser.VariableDeclarationContext,
        modifiers: List<String>
    ): CodeField {
        val key = it.getChild(0).text
        val hasInitializer = it.Assign() != null
        val singleExpressions = it.singleExpression()

        // Only treat it as a value when it actually has an initializer
        val lastExpr = if (hasInitializer) singleExpressions.lastOrNull() else null
        val field = CodeField(TypeKey = key, TypeValue = lastExpr?.text ?: "", Modifiers = modifiers)

        when (lastExpr) {
            is TypeScriptParser.LiteralExpressionContext -> {
                if (lastExpr.literal().StringLiteral() != null) {
                    field.TypeValue = unQuote(lastExpr.text)
                    field.TypeType = "String"
                }
            }

            is IdentifierExpressionContext -> {
                singleExprToFieldCall(field, lastExpr.singleExpression(), lastExpr.identifierName().text)
            }

            is TypeScriptParser.YieldExpressionContext -> {
                if (lastExpr.yieldStatement().expressionSequence() != null) {
                    val singeExprs = lastExpr.yieldStatement().expressionSequence().singleExpression()
                    singeExprs.forEach { expr ->
                        singleExprToFieldCall(field, expr, expr.text)
                    }
                }
            }

            else -> {
                //                        println("variableToFields -> ${lastExpr.text} === ${lastExpr.javaClass.simpleName}")
            }
        }

        return field
    }

    private fun singleExprToFieldCall(
        field: CodeField,
        singleExpr: TypeScriptParser.SingleExpressionContext?,
        funcName: String
    ) {
        if (singleExpr != null) {
            when (singleExpr) {
                is TypeScriptParser.ParenthesizedExpressionContext -> {
                    val parameters = parseParenthesizedExpression(singleExpr)
                    field.Calls += CodeCall("", CallType.FIELD, "", funcName, parameters)
                }

                is TypeScriptParser.ArgumentsExpressionContext -> {
                    field.Calls += CodeCall(
                        Type = CallType.FIELD,
                        NodeName = "",
                        FunctionName = functionNameFromArguments(singleExpr),
                        Parameters = processArgumentList(singleExpr.arguments()?.argumentList())
                    )
                }

                is IdentifierExpressionContext -> {
                    // Handle `call(...)` parsed as IdentifierExpression + ParenthesizedExpression
                    val child = singleExpr.singleExpression()
                    if (child is TypeScriptParser.ParenthesizedExpressionContext) {
                        field.Calls += CodeCall(
                            Type = CallType.FIELD,
                            NodeName = "",
                            FunctionName = singleExpr.identifierName().text,
                            Parameters = parseParenthesizedExpression(child),
                        )
                    } else {
                        field.Calls += CodeCall("", CallType.FIELD, "", singleExpr.identifierName().text)
                    }
                }

                else -> {
//                    println("todo -> var -> decl call: $name")
                }
            }
        }
    }

    override fun enterDecoratorList(ctx: TypeScriptParser.DecoratorListContext?) {
        if (!hasEnterClass) {
            hasAnnotation = true

            ctx!!.decorator()
                .asSequence()
                .map { buildAnnotation(it) }
                .forEach { currentAnnotations += it }
        }
    }

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.identifierName().text

        hasEnterClass = true
        // In new grammar, decorators are part of classDeclaration, so ctx.start may point to '@'.
        // Use the 'class' keyword token as the start position to keep old behavior.
        val pos = buildPosition(ctx).also {
            ctx.Class()?.symbol?.let { classToken ->
                it.StartLine = classToken.line
                it.StartLinePosition = classToken.charPositionInLine
            }
        }
        currentNode = CodeDataStruct(
            NodeName = nodeName,
            Type = DataStructType.CLASS,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Position = pos
        )

        val heritageCtx = ctx.classHeritage()
        if (heritageCtx.classImplementsClause() != null) {
            val typeList = heritageCtx.classImplementsClause().classOrInterfaceTypeList()
            currentNode.Implements = buildImplements(typeList)
        }

        if (heritageCtx.classExtendsClause() != null) {
            val refCtx = heritageCtx.classExtendsClause().parameterizedTypeRef()
            currentNode.Extend = refCtx.typeName().text
        }

        // Handle decorators that are part of classDeclaration (new grammar style)
        if (ctx.decoratorList() != null) {
            ctx.decoratorList().decorator()
                .asSequence()
                .map { buildAnnotation(it) }
                .forEach { currentAnnotations += it }
        }

        // load annotations before class
        currentNode.Annotations = currentAnnotations
        currentAnnotations = listOf()
        hasAnnotation = false

        if (ctx.classBody()?.classMemberList() != null) {
            this.handleClassBodyElements(ctx.classBody())
        }

        classNodeStack.push(currentNode)
        nodeMap[nodeName] = currentNode
    }

    private fun handleClassBodyElements(classTailCtx: TypeScriptParser.ClassBodyContext) {
        for (clzElementCtx in classTailCtx.classMemberList().classMember()) {
            val childCtx = clzElementCtx.getChild(0) ?: continue
            when (childCtx) {
                is TypeScriptParser.ConstructorDeclarationContext -> {
                    val codeFunction = this.buildConstructorMethod(childCtx)
                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }

                is TypeScriptParser.PropertyDeclarationExpressionContext -> {
                    val codeField = CodeField(TypeKey = childCtx.propertyName().text)

                    val modifier = childCtx.propertyMemberBase().text
                    if (modifier != "") {
                        codeField.Modifiers += modifier
                    }
                    if (childCtx.typeAnnotation() != null) {
                        codeField.TypeType = this.buildTypeAnnotation(childCtx.typeAnnotation())!!
                        codeField.TypeRef = TypeScriptTypeRefBuilder.build(childCtx.typeAnnotation())
                    }

                    currentNode.Fields += codeField
                }

                is TypeScriptParser.MethodDeclarationExpressionContext -> {
                    val codeFunction = CodeFunction(
                        Name = childCtx.propertyName().text, Position = buildPosition(childCtx)
                    )
                    val callSignCtx = childCtx.callSignature()

                    if (callSignCtx.typeRef() != null) {
                        codeFunction.ReturnType = processRef(callSignCtx.typeRef()) ?: ""
                        codeFunction.ReturnTypeRef = TypeScriptTypeRefBuilder.buildFromTypeRef(callSignCtx.typeRef())
                    }

                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }

                is TypeScriptParser.GetterSetterDeclarationExpressionContext -> {
                    // Get the accessor type (get/set) as the function name
                    // This matches the old behavior where identifierName was null and fell back to "get"/"set"
                    val name = if (childCtx.getAccessor() != null) {
                        "get"
                    } else {
                        "set"
                    }

                    currentNode.Functions += CodeFunction(
                        Name = name, Position = buildPosition(childCtx)
                    )
                }

                is TypeScriptParser.AbstractMemberDeclarationContext -> {
                    childCtx.abstractDeclaration().let {
                        val name = it.identifierName()?.text ?: ""
                        val type = it.callSignature()?.typeRef()?.let { ref ->
                            processRef(ref) ?: "void"
                        } ?: "void"

                        currentNode.Functions += CodeFunction(
                            Name = name, Position = buildPosition(childCtx), ReturnType = type
                        )
                    }
                }

                else -> {
//                    println("handleClassBodyElements -> childElementType : ${childCtx.javaClass.simpleName}")
                }
            }
        }
    }

    private fun buildConstructorMethod(ctx: TypeScriptParser.ConstructorDeclarationContext): CodeFunction {
        val codeFunction = CodeFunction(
            Name = "constructor", Position = this.buildPosition(ctx)
        )

        if (ctx.accessibilityModifier() != null) {
            codeFunction.Modifiers += ctx.accessibilityModifier().text
        }

        if (ctx.formalParameterList() != null) {
            codeFunction.Parameters += buildParameters(ctx.formalParameterList())
        }

        return codeFunction
    }

    private fun buildImplements(typeList: TypeScriptParser.ClassOrInterfaceTypeListContext?): List<String> {
        return typeList?.parameterizedTypeRef()?.map { typeRefCtx ->
            typeRefCtx.typeName().text
        } ?: listOf()
    }

    override fun exitClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        hasEnterClass = false
        classNodeStack.pop()
    }

    override fun enterInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        val nodeName = ctx!!.identifierName().text
        val currentType = DataStructType.INTERFACE

        currentNode = CodeDataStruct(
            NodeName = nodeName,
            Type = currentType,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Position = buildPosition(ctx)
        )

        if (ctx.interfaceExtendsClause() != null) {
            val elements = buildImplements(ctx.interfaceExtendsClause().classOrInterfaceTypeList())
            currentNode.Extend = elements[0]
        }

        if (ctx.interfaceBody() != null) {
            this.buildInterfaceBody(ctx.interfaceBody().interfaceMemberList())
        }

        nodeMap[nodeName] = currentNode
    }

    override fun exitInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        currentNode = CodeDataStruct(Package = codeContainer.PackageName, FilePath = codeContainer.FullName)
    }

    private fun buildInterfaceBody(typeMemberList: TypeScriptParser.InterfaceMemberListContext) {
        typeMemberList.interfaceMember()?.forEach { memberContext ->
            when (val memberChild = memberContext.getChild(0)) {
                is TypeScriptParser.PropertySignatureContext -> {
                    buildInterfacePropertySignature(memberChild)
                }

                is TypeScriptParser.MethodSignatureContext -> {
                    val func = CodeFunction(
                        Name = memberChild.propertyName().text
                    )

                    fillMethodFromCallSignature(memberChild.callSignature(), func)

                    func.FilePath = filePath
                    currentNode.Functions += func
                }

                else -> {
                    //                    println("enterInterfaceDeclaration -> buildInterfaceBody")
                }
            }
        }
    }

    private fun buildInterfacePropertySignature(signCtx: TypeScriptParser.PropertySignatureContext) {
        val annotation = signCtx.typeAnnotation()
        val typeType = buildTypeAnnotation(annotation)
        val typeValue = signCtx.propertyName().text

        val codeField = CodeField(
            TypeType = typeType, 
            TypeValue = typeValue,
            TypeRef = TypeScriptTypeRefBuilder.build(annotation)
        )
        currentNode.Fields += codeField

//        val isArrowFunc = annotation.typeRef() != null
//        if (isArrowFunc) {
//            val codeFunction = CodeFunction(
//                Name = typeValue
//            )
//            val param = CodeProperty(
//                TypeValue = "any", TypeType = typeType
//            )
//
//            val returnType = CodeProperty(
//                TypeType = annotation.typeRef().text, TypeValue = ""
//            )
//
//            codeFunction.Parameters += param
//            codeFunction.MultipleReturns += returnType
//
//            codeFunction.FilePath = filePath
//            currentNode.Functions += codeFunction
//        } else {
//        }
    }

    override fun enterFromBlock(ctx: TypeScriptParser.FromBlockContext?) {
        val imp = unQuote(ctx!!.StringLiteral().text)
        val codeImport = CodeImport(
            Source = imp
        )
        val specifiers = mutableListOf<ImportSpecifier>()

        if (ctx.moduleItems() != null) {
            codeImport.Kind = ImportKind.NAMED
            for (nameContext in ctx.moduleItems().aliasName()) {
                if (nameContext.identifierName().isNotEmpty()) {
                    val originalName = nameContext.identifierName()[0].text
                    val localName = if (nameContext.As() != null) {
                        nameContext.identifierName()[1].text
                    } else {
                        originalName
                    }
                    
                    // Legacy fields
                    codeImport.UsageName += originalName
                    if (nameContext.As() != null) {
                        codeImport.AsName += localName
                    }
                    
                    // New structured field
                    specifiers += ImportSpecifier(OriginalName = originalName, LocalName = localName)
                }
            }
        }

        val importNamespace = ctx.importNamespace()
        if (importNamespace != null) {
            val isImportAll = importNamespace.Multiply() != null
            if (isImportAll) {
                codeImport.Kind = ImportKind.NAMESPACE
                codeImport.UsageName += "*"
                if (importNamespace.As() != null) {
                    val nsName = importNamespace.identifierName()[0].text
                    codeImport.AsName += nsName
                    codeImport.NamespaceName = nsName
                }
            } else {
                codeImport.Kind = ImportKind.DEFAULT
                val defaultName = importNamespace.identifierName()[0].text
                codeImport.UsageName += defaultName
                codeImport.DefaultName = defaultName
                if (importNamespace.As() != null) {
                    codeImport.AsName += importNamespace.identifierName()[1].text
                }
            }
        }

        if (ctx.Dollar() != null) {
            codeImport.UsageName += ctx.Dollar().text
        }

        if (ctx.Lodash() != null) {
            codeImport.UsageName += ctx.Lodash().text
        }

        codeImport.Specifiers = specifiers
        codeContainer.Imports += codeImport
    }

    // Handle new grammar import syntax
    override fun enterImportFromBlock(ctx: TypeScriptParser.ImportFromBlockContext?) {
        if (ctx == null) return

        // Get import source from importFrom or StringLiteral
        val source = ctx.importFrom()?.StringLiteral()?.text 
            ?: ctx.StringLiteral()?.text 
            ?: return
        val codeImport = CodeImport(Source = unQuote(source))
        val specifiers = mutableListOf<ImportSpecifier>()

        // Handle module items: import { A, B } from 'module'
        if (ctx.importModuleItems() != null) {
            codeImport.Kind = ImportKind.NAMED
            for (aliasName in ctx.importModuleItems().importAliasName()) {
                val exportName = aliasName.moduleExportName()
                if (exportName != null) {
                    val originalName = exportName.identifierName()?.text 
                        ?: exportName.StringLiteral()?.text?.let { unQuote(it) } 
                        ?: ""
                    val localName = if (aliasName.As() != null && aliasName.importedBinding() != null) {
                        aliasName.importedBinding().text
                    } else {
                        originalName
                    }
                    
                    // Legacy fields
                    codeImport.UsageName += originalName
                    if (aliasName.As() != null && aliasName.importedBinding() != null) {
                        codeImport.AsName += localName
                    }
                    
                    // New structured field
                    specifiers += ImportSpecifier(OriginalName = originalName, LocalName = localName)
                }
            }
        }

        // Handle namespace import: import * as name from 'module'
        val importNamespace = ctx.importNamespace()
        if (importNamespace != null) {
            val isImportAll = importNamespace.Multiply() != null
            if (isImportAll) {
                codeImport.Kind = ImportKind.NAMESPACE
                codeImport.UsageName += "*"
                if (importNamespace.As() != null && importNamespace.identifierName().isNotEmpty()) {
                    val nsName = importNamespace.identifierName()[0].text
                    codeImport.AsName += nsName
                    codeImport.NamespaceName = nsName
                }
            } else if (importNamespace.identifierName().isNotEmpty()) {
                codeImport.Kind = ImportKind.DEFAULT
                val defName = importNamespace.identifierName()[0].text
                codeImport.UsageName += defName
                codeImport.DefaultName = defName
                if (importNamespace.As() != null && importNamespace.identifierName().size > 1) {
                    codeImport.AsName += importNamespace.identifierName()[1].text
                }
            }
        }

        // Handle default import
        val importDefault = ctx.importDefault()
        if (importDefault != null && importDefault.aliasName() != null) {
            val defaultName = importDefault.aliasName().identifierName()
            if (defaultName.isNotEmpty()) {
                val defName = defaultName[0].text
                // Insert default import at the beginning
                codeImport.UsageName = listOf(defName) + codeImport.UsageName
                codeImport.DefaultName = defName
                // If we only have a default import, set kind to DEFAULT
                if (codeImport.Kind == ImportKind.UNKNOWN) {
                    codeImport.Kind = ImportKind.DEFAULT
                }
            }
        }

        codeImport.Specifiers = specifiers
        codeContainer.Imports += codeImport
    }

    private fun unQuote(text: String): String = text.replace("[\"']".toRegex(), "")

    override fun enterImportAliasDeclaration(ctx: TypeScriptParser.ImportAliasDeclarationContext?) {
        if (ctx == null) return
        
        // Handle: import zip = require("./ZipCodeValidator");
        if (ctx.Require() != null && ctx.StringLiteral() != null) {
            val codeImport = CodeImport(
                Source = unQuote(ctx.StringLiteral().text)
            )
            codeImport.UsageName += ctx.identifierName()?.text ?: ""
            codeContainer.Imports += codeImport
        }
        // Handle: import ns = namespace.path;
        else if (ctx.namespaceName() != null) {
            val codeImport = CodeImport(
                Source = ctx.namespaceName().text
            )
            codeImport.UsageName += ctx.identifierName()?.text ?: ""
            codeContainer.Imports += codeImport
        }
    }

    override fun enterImportAll(ctx: TypeScriptParser.ImportAllContext?) {
        codeContainer.Imports += CodeImport(
            Source = unQuote(ctx!!.StringLiteral().text),
            Kind = ImportKind.SIDE_EFFECT
        )
    }

    // see also in arrow function declaration
    override fun enterFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        if (ctx == null) return
        if (ctx.identifierName() == null) return

        val funcName = ctx.identifierName().text
        val func = CodeFunction(FilePath = filePath)
        func.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature(), func)
        func.Position = this.buildPosition(ctx)

        isCallbackOrAnonymousFunction = false
        processingNewArrowFunc(func)
    }

    // todo: align logic to arrow functions
    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        handleFuncDeclExit()
    }

    // see also in function declaration
    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        isCallbackOrAnonymousFunction = false

        // Try to find VariableDeclarationContext in parent chain (up to 5 levels)
        val varDecl = findParentOfType<TypeScriptParser.VariableDeclarationContext>(ctx, 5)
        val argCtx = findParentOfType<TypeScriptParser.ArgumentContext>(ctx, 5)
        val exprSeqCtx = findParentOfType<TypeScriptParser.ExpressionSequenceContext>(ctx, 3)


        when {
            // for: const blabla = () => { }
            varDecl != null -> {
                val func = CodeFunction(
                    FilePath = filePath,
                    Name = varDecl.identifierOrKeyWord()?.text ?: varDecl.getChild(0)?.text ?: "",
                    Parameters = this.buildArrowFunctionParameters(ctx?.arrowFunctionParameters()),
                    Position = this.buildPosition(ctx)
                )

                if (ctx?.typeAnnotation() != null) {
                    func.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }

                isCallbackOrAnonymousFunction = false
                processingNewArrowFunc(func)
            }

            argCtx != null -> {
                // todo: add arg ctx
                isCallbackOrAnonymousFunction = true
                currentFunc.FunctionCalls += CodeCall(FunctionName = currentExprIdent, Type = CallType.ARROW)
            }
            // such as: `(e) => e.stopPropagation()`
            exprSeqCtx != null && varDecl == null -> {
                val func = CodeFunction(FilePath = filePath, Name = "")

                isCallbackOrAnonymousFunction = true
                processingNewArrowFunc(func)
            }
            // Handle any other arrow functions (e.g., inside JSX attributes)
            // Mark as callback to prevent stack mismatch on exit
            else -> {
                isCallbackOrAnonymousFunction = true
            }
        }

        if (ctx?.arrowFunctionBody() == null) return

        // Handle expression body: => expression
        if (ctx.arrowFunctionBody().singleExpression() != null) {
            parseSingleExpression(ctx.arrowFunctionBody().singleExpression())
        }
    }

    override fun enterReturnStatement(ctx: TypeScriptParser.ReturnStatementContext?) {
        if (ctx?.expressionSequence() != null) {
            ctx.expressionSequence().singleExpression().forEach(::parseSingleExpression)
        }

        if (node.isJsxFile()) {
            currentFunc.IsReturnHtml = true
        }
    }

    private fun processingNewArrowFunc(func: CodeFunction) {
        if (isCallbackOrAnonymousFunction) {
            return
        }

        if (funcsStackForCount.count() != 0) {
            currentFunc.InnerFunctions += func
        }

        funcsStackForCount.push(func)
        currentFunc = func
    }

    override fun exitArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        handleFuncDeclExit()
    }

    private fun handleFuncDeclExit() {
        if (!isCallbackOrAnonymousFunction) {
            val pop = funcsStackForCount.pop()
            if (pop != null) {
                if (funcsStackForCount.count() != 0) {
                    currentFunc = funcsStackForCount.peek()!!
                }
            }

            exitArrowCount = funcsStackForCount.count()
        }

        isCallbackOrAnonymousFunction = false
        if (funcsStackForCount.count() == 0) {
            // Normalize: sometimes chained call parsing can emit an empty call name.
            // If we already have a chain name (contains "->"), reuse it for blank entries.
            val chainName = currentFunc.FunctionCalls.firstOrNull { it.FunctionName.contains("->") }?.FunctionName
            if (!chainName.isNullOrBlank()) {
                currentFunc.FunctionCalls = currentFunc.FunctionCalls.map { call ->
                    if (call.FunctionName.isBlank()) call.copy(FunctionName = chainName) else call
                }
            }

            defaultNode.Functions += currentFunc
            currentFunc = CodeFunction()
        }
    }

    private fun buildArrowFunctionParameters(arrowFuncCtx: TypeScriptParser.ArrowFunctionParametersContext?): List<CodeProperty> {
        if (arrowFuncCtx!!.formalParameterList() != null) {
            return this.buildParameters(arrowFuncCtx.formalParameterList())
        }

        var parameters: List<CodeProperty> = listOf()
        // Handle single parameter without parentheses: (x) => x or x => x
        if (arrowFuncCtx.propertyName() != null) {
            parameters += CodeProperty(
                TypeValue = arrowFuncCtx.propertyName().text, TypeType = "any"
            )
        }

        return parameters
    }

    override fun enterFunctionExpression(ctx: TypeScriptParser.FunctionExpressionContext?) {
        // Skip if this is an arrow function (handled by enterArrowFunctionDeclaration)
        val anonFunc = ctx?.anonymousFunction()
        if (anonFunc?.arrowFunctionDeclaration() != null) {
            return
        }

        // Try different parent levels to find VariableDeclarationContext
        val varDecl = findParentOfType<TypeScriptParser.VariableDeclarationContext>(ctx, 5)

        if (varDecl != null) {
            // Get name from variable declaration
            currentFunc.Name = varDecl.identifierOrKeyWord()?.text ?: varDecl.getChild(0)?.text ?: ""

            // Get parameters from anonymous function
            if (anonFunc?.formalParameterList() != null) {
                currentFunc.Parameters = this.buildParameters(anonFunc.formalParameterList())
            }

            if (anonFunc?.typeAnnotation() != null) {
                currentFunc.MultipleReturns += buildReturnTypeByType(anonFunc.typeAnnotation())
            }

            currentFunc.Position = this.buildPosition(ctx)
            currentFunc.FilePath = filePath
            defaultNode.Functions += currentFunc
        } else {
            when (val grad = ctx?.parent?.parent) {
                is IdentifierExpressionContext -> {
                    currentFunc.Position = this.buildPosition(ctx)
                    currentFunc.FilePath = filePath
                    defaultNode.Functions += currentFunc
                }
            }
        }
    }

    private inline fun <reified T> findParentOfType(ctx: org.antlr.v4.runtime.ParserRuleContext?, maxDepth: Int): T? {
        var current: org.antlr.v4.runtime.tree.ParseTree? = ctx?.parent
        for (i in 0 until maxDepth) {
            if (current is T) return current as T
            current = (current as? org.antlr.v4.runtime.ParserRuleContext)?.parent
        }
        return null
    }

    private fun parseStatement(context: TypeScriptParser.StatementContext) {
        when (val child = context.getChild(0)) {
            is TypeScriptParser.ReturnStatementContext -> {

                if (child.expressionSequence() != null) {
                    child.expressionSequence().singleExpression().forEach(::parseSingleExpression)
                }

                if (node.isJsxFile()) {
                    currentFunc.IsReturnHtml = true
                }
            }

            else -> {
//                println("parseStmt childType -> :${child.javaClass.name}")
            }
        }
    }

    private fun singleExpToText(ctx: TypeScriptParser.SingleExpressionContext): String {
        var text = ctx.text
        when (ctx) {
            is TypeScriptParser.LiteralExpressionContext -> {
                val singleStr = text.startsWith("'") && text.endsWith("'")
                val doubleStr = text.startsWith("\"") && text.endsWith("\"")
                val templateStr = text.startsWith("`") && text.endsWith("`")
                if (singleStr || doubleStr || templateStr) {
                    text = text.drop(1).dropLast(1)
                }
            }
        }

        return text
    }

    private fun parseSingleExpression(ctx: TypeScriptParser.SingleExpressionContext) {
        when (ctx) {
            is IdentifierExpressionContext -> {
                currentExprIdent = ctx.identifierName().text

                if (ctx.singleExpression() != null) {
                    parseSingleExpression(ctx.singleExpression())
                }
            }

            is TypeScriptParser.GenericTypesContext -> {
                if (ctx.expressionSequence() != null) {
                    parseExpressionSequence(ctx.expressionSequence())
                }
            }

            is TypeScriptParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(ctx)
            }

            is TypeScriptParser.GenericCallExpressionContext -> {
                // e.g. axios<Module[]>({ ... })
                val calleeText = ctx.identifierName().text
                currentExprIdent = calleeText
                val args = buildArguments(ctx.arguments())
                currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, args)
            }

            is TypeScriptParser.OptionalCallExpressionContext -> {
                // Optional call: foo?.()
                // We don't currently model it as a call, but we must traverse the target.
                parseSingleExpression(ctx.singleExpression())
            }

            is ParenthesizedExpressionContext -> {
                // If we have a preceding identifier (like Number(...) or foo(...)), treat as a function call
                // Otherwise, just parse the contents (like (someExpr) or (<div></div>))
                if (currentExprIdent.isNotBlank()) {
                    val parameters = parseParenthesizedExpression(ctx)
                    currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, parameters)
                } else {
                    // Recursively parse the expression inside parentheses without recording a call
                    ctx.expressionSequence()?.singleExpression()?.forEach(::parseSingleExpression)
                }
            }

            else -> {
//                println("todo -> need support type: ${ctx::class.java.simpleName} ==== ${ctx.text}")
            }
        }
    }

    private fun argumentsExpressionToCall(argument: TypeScriptParser.ArgumentsExpressionContext, varName: String = "") {
        val callee = argument.singleExpression()
        val args = buildArguments(argument.arguments())

        // For chained calls like axios(...).then(...).catch(...)
        if (callee is TypeScriptParser.MemberDotExpressionContext) {
            val rawFn = buildCallChain(callee).ifBlank { callee.text }
            val fn = normalizeMemberCallName(rawFn)
            currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", fn, args)
            return
        }

        val calleeText = callee.text
        currentExprIdent = if (varName.isNotBlank()) varName else calleeText
        currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, args)
    }

    private fun normalizeMemberCallName(name: String): String {
        // Keep legacy "axios.get" style for simple member calls,
        // but keep "axios->then"/"request->...->catch" style for promise chains.
        if (!name.contains("->")) return name

        // If it's a single hop like `axios->get`, prefer dot unless it's a promise-ish step.
        val parts = name.split("->")
        if (parts.size == 2) {
            val right = parts[1]
            val keepArrow = right == "then" || right == "catch" || right == "finally"
            return if (keepArrow) name else "${parts[0]}.$right"
        }

        return name
    }

    private fun buildCallChain(expr: TypeScriptParser.SingleExpressionContext?): String {
        if (expr == null) return ""
        return when (expr) {
            is TypeScriptParser.MemberDotExpressionContext -> {
                var left = buildCallChain(expr.singleExpression())
                if (left.isBlank()) {
                    // Best-effort: recover base identifier from raw text like `axios<Module[]>({..})`
                    val raw = expr.singleExpression()?.text ?: ""
                    left = Regex("^[A-Za-z_$][A-Za-z0-9_$]*").find(raw)?.value ?: ""
                }
                if (left.isBlank()) {
                    // Fallback: recover from the full member expression text
                    val rawAll = expr.text
                    left = Regex("^[A-Za-z_$][A-Za-z0-9_$]*").find(rawAll)?.value ?: ""
                }
                val right = expr.identifierName().text
                if (left.isBlank()) right else "$left->$right"
            }

            is TypeScriptParser.ArgumentsExpressionContext -> {
                buildCallChain(expr.singleExpression())
            }

            is TypeScriptParser.GenericCallExpressionContext -> {
                expr.identifierName().text
            }

            is IdentifierExpressionContext -> {
                expr.identifierName().text
            }

            // identifierName + typeArguments is typically represented via IdentifierExpressionContext + GenericTypes child,
            // so we can usually ignore this and let recursion find the identifier.
            is TypeScriptParser.GenericTypesContext -> {
                expr.expressionSequence()?.singleExpression()?.firstOrNull()?.let { buildCallChain(it) } ?: ""
            }

            else -> {
                // Fallback: best-effort take the prefix before '(' or '.'
                expr.text.substringBefore('(').substringBefore('.')
            }
        }
    }

    private fun parseArguments(argument: TypeScriptParser.ArgumentsExpressionContext): List<CodeProperty> {
        var params = listOf<CodeProperty>()

        // for: `axios<Module[]>({parameter}).then`
        // create then and update parameter
        argument.children.forEach {
            when (it) {
                is IdentifierExpressionContext -> {
                    currentExprIdent = it.identifierName().text
                }

                is TypeScriptParser.MemberDotExpressionContext -> {
                    val expr = it.singleExpression()
                    when (expr) {
                        is TypeScriptParser.ParenthesizedExpressionContext -> {
                            params += parseParenthesizedExpression(expr)
                        }

                        is TypeScriptParser.ArgumentsExpressionContext -> {
                            // request.get('/api/v1/xxx?id=1').then(function(response){console.log(response)}).catch()
                            parseArguments(expr)
                        }

                        is IdentifierExpressionContext -> {
                            currentExprIdent = expr.identifierName().text
                        }

                        else -> {
//                            println("MemberDotExpressionContext: -> ${expr.text}")
                        }
                    }

                    currentExprIdent += "->${it.identifierName().text}"
                }

                is ParenthesizedExpressionContext -> {
                    params += parseParenthesizedExpression(it)
                }

                else -> {
//                    println("singleExpression -> ${it.javaClass.simpleName} -> ${it.text}")
                }
            }
        }

        return params
    }

    private fun parseParenthesizedExpression(context: ParenthesizedExpressionContext): List<CodeProperty> {
        return context.expressionSequence().singleExpression().map { subSingle ->
            var parameter = CodeProperty(TypeValue = "", TypeType = "object")

            when (subSingle) {
                is TypeScriptParser.ObjectLiteralExpressionContext -> {
                    val objectLiteral = parseObjectLiteral(subSingle.objectLiteral())
                    parameter =
                        CodeProperty(TypeValue = subSingle.text, TypeType = "object", ObjectValue = objectLiteral)
                }

                is TypeScriptParser.ArgumentsExpressionContext -> {
                    parseArguments(subSingle)
                }

                is TypeScriptParser.IdentifierExpressionContext -> {
                    parameter = CodeProperty(TypeValue = subSingle.text, TypeType = "parameter")
                }

                is TypeScriptParser.ArrayLiteralExpressionContext -> {
                    parameter = CodeProperty(TypeValue = "[]", TypeType = "parameter")
                }

                is TypeScriptParser.LiteralExpressionContext -> {
                    parameter = CodeProperty(TypeValue = singleExpToText(subSingle), TypeType = "string")
                }

                else -> {
//                    println("todo -> ParenthesizedExpressionContext: ${subSingle.javaClass.simpleName}, text: ${subSingle.text}")
                }
            }

            parameter
        }
    }

    private fun parseObjectLiteral(objectLiteral: TypeScriptParser.ObjectLiteralContext): List<CodeProperty> {
        return objectLiteral.propertyAssignment().mapNotNull { property ->
            when (property) {
                is TypeScriptParser.PropertyExpressionAssignmentContext -> {
                    val text = if (property.singleExpression() != null) {
                        property.singleExpression().text
                    } else {
                        property.text
                    }

                    val value = CodeProperty(TypeType = "value", TypeValue = text)
                    val propText = property.propertyName().text

                    CodeProperty(TypeType = "key", TypeValue = propText, ObjectValue = listOf(value))
                }

                is TypeScriptParser.PropertyShorthandContext -> {
                    val short = property.text
                    val value = CodeProperty(TypeType = "value", TypeValue = short)

                    CodeProperty(TypeType = "key", TypeValue = short, ObjectValue = listOf(value))
                }

                else -> {
                    null
                }
            }
        }
    }

    private fun parseExpressionSequence(ctx: TypeScriptParser.ExpressionSequenceContext) {
        ctx.singleExpression().forEach { singleExpressionContext ->
            parseSingleExpression(singleExpressionContext)
        }
    }

    private fun fillMethodFromCallSignature(
        callSignCtx: TypeScriptParser.CallSignatureContext,
        currentFunc: CodeFunction
    ) {
        if (callSignCtx.parameterList() != null) {
            val parameters = buildMethodParameters(callSignCtx.parameterList())
            currentFunc.Parameters = parameters
        }

        if (callSignCtx.typeRef() != null) {
            val returnType = buildReturnTypeByTypeRef(callSignCtx.typeRef()!!)
            currentFunc.MultipleReturns += returnType
        }
    }

    private fun buildReturnTypeByType(typeAnnotationContext: TypeScriptParser.TypeAnnotationContext?): CodeProperty =
        CodeProperty(
            TypeType = buildTypeAnnotation(typeAnnotationContext) ?: "", 
            TypeValue = "",
            TypeRef = TypeScriptTypeRefBuilder.build(typeAnnotationContext)
        )

    private fun buildReturnTypeByTypeRef(typeRefContext: TypeScriptParser.TypeRefContext): CodeProperty =
        CodeProperty(
            TypeType = processRef(typeRefContext) ?: "", 
            TypeValue = "",
            TypeRef = TypeScriptTypeRefBuilder.buildFromTypeRef(typeRefContext)
        )

    override fun enterExpressionStatement(ctx: TypeScriptParser.ExpressionStatementContext?) {
        if (ctx?.expressionSequence() == null) return

        for (singleExprCtx in ctx.expressionSequence().singleExpression()) {
            when (singleExprCtx) {
                is TypeScriptParser.ArgumentsExpressionContext -> {
                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = processArgumentList(singleExprCtx.arguments()?.argumentList()),
                        FunctionName = buildFunctionName(singleExprCtx),
                        NodeName = wrapTargetType(singleExprCtx),
                        Position = buildPosition(ctx)
                    )
                }

                is TypeScriptParser.IdentifierExpressionContext -> {
                    val params = when (val child = singleExprCtx.singleExpression()) {
                        is ParenthesizedExpressionContext -> parseParenthesizedExpression(child)
                        else -> listOf()
                    }
                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = params,
                        FunctionName = singleExprCtx.identifierName().text,
                        NodeName = "",
                        Position = buildPosition(ctx)
                    )
                }

                else -> {
//                    println("enterExpressionStatement : ${singleExprCtx.javaClass.simpleName}")
                }
            }

        }
    }

    private fun buildFunctionName(argsCtx: TypeScriptParser.ArgumentsExpressionContext): String {
        val name = functionNameFromArguments(argsCtx)

        if (name.isEmpty()) {
            var text = argsCtx.singleExpression().text
            if (text.contains(".")) {
                val split = text.split(".")
                text = split[split.size - 1]
            }

            return text
        }

        return name
    }

    private fun functionNameFromArguments(argsCtx: TypeScriptParser.ArgumentsExpressionContext): String {
        return when (val singleExpr = argsCtx.singleExpression()) {
            is TypeScriptParser.ArgumentsExpressionContext -> {
                functionNameFromArguments(singleExpr)
            }

            is TypeScriptParser.MemberDotExpressionContext -> {
                when (val child = singleExpr.singleExpression()) {
                    is TypeScriptParser.ArgumentsExpressionContext -> {
                        functionNameFromArguments(child)
                    }

                    else -> {
                        ""
                    }
                }
            }

            is IdentifierExpressionContext -> {
                singleExpr.identifierName().text
            }

            else -> {
                ""
            }
        }
    }

    private fun wrapTargetType(argsCtx: TypeScriptParser.ArgumentsExpressionContext): String {
        var text = argsCtx.singleExpression().text
        if (text.contains(".")) {
            text = text.split(".")[0]
        }

        if (localVars[text] != null && localVars[text] != "") {
            text = localVars[text]
        }

        if (text == null) {
            text = ""
        }

        return text
    }

    /**
     *     create local var when new object
     */
    override fun enterVariableDeclaration(ctx: TypeScriptParser.VariableDeclarationContext?) {
        if (ctx == null) {
            return
        }
        if (ctx.children == null) {
            return
        }

        val varName = ctx.getChild(0).text
        val exprs = ctx.singleExpression()
        // Prefer initializer (expression after '=') when present
        val initExpr = if (ctx.Assign() != null) exprs.lastOrNull() else exprs.lastOrNull()
        when (initExpr) {
            is TypeScriptParser.NewExpressionContext -> {
                when (val newSingleExpr = initExpr.singleExpression()) {
                    // todo: legacy expression, remove
                    is IdentifierExpressionContext -> {
                        localVars[varName] = newSingleExpr.identifierName().text
                    }

                    is TypeScriptParser.ArgumentsExpressionContext -> {
                        localVars[varName] = newSingleExpr.singleExpression().text
                    }

                    else -> {
//                    println("enterVariableDeclaration : $singleCtxType === ${ctx.text}")
                    }
                }
            }

            is IdentifierExpressionContext -> {
                when (initExpr.identifierName().text) {
                    "await" -> {
                        parseSingleExpression(initExpr.singleExpression())
                    }

                    "Number" -> {
                        parseSingleExpression(initExpr.singleExpression())
                    }

                    else -> {
//                        println("IdentifierExpressionContext ->  ${initExpr.text}")
                    }
                }
            }

            is TypeScriptParser.AwaitExpressionContext -> {
                parseSingleExpression(initExpr.singleExpression())
            }

            is TypeScriptParser.ArrowFunctionExpressionLContext -> {
                // will recall by ArrowFunctionDeclaration
            }

            is TypeScriptParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(initExpr, varName)
            }

            is ParenthesizedExpressionContext -> {
                parseParenthesizedExpression(initExpr)
            }
        }
    }

    private fun buildArguments(arguments: TypeScriptParser.ArgumentsContext?): List<CodeProperty> {
        if (arguments?.getChild(1)?.text == ")") {
            return listOf()
        }

        val argumentList = arguments?.argumentList()
        return processArgumentList(argumentList)
    }

    private fun processArgumentList(argumentList: TypeScriptParser.ArgumentListContext?) =
        argumentList?.argument()?.map {
            parseSingleExpression(it.singleExpression())
            val expr = it.singleExpression()
            when (expr) {
                is TypeScriptParser.ObjectLiteralExpressionContext -> {
                    val objectLiteral = parseObjectLiteral(expr.objectLiteral())
                    return@map CodeProperty(
                        TypeValue = expr.text,
                        TypeType = "object",
                        ObjectValue = objectLiteral
                    )
                }
            }

            val typeValue: String = when (expr) {
                is TypeScriptParser.LiteralExpressionContext -> {
                    if (expr.literal().templateStringLiteral() != null) {
                        expr.literal().templateStringLiteral().text.drop(1).dropLast(1)
                    } else {
                        it.text
                    }
                }

                else -> {
                    it.text;
                }
            }

            CodeProperty(TypeValue = typeValue, TypeType = "")
        } ?: listOf()


    override fun enterExportElementDirectly(ctx: TypeScriptParser.ExportElementDirectlyContext?) {
        // Note: Variable exports are handled by enterVariableStatement which also records fields.
        // Here we only handle exports that enterVariableStatement doesn't cover (e.g., classes, functions, etc.)
        // VariableStatementContext is handled separately to avoid duplicate exports.
        when (val stmt = ctx?.declarationStatement()?.getChild(0)) {
            is VariableStatementContext -> {
                // Skip - handled by enterVariableStatement
            }
            // Handle other export types if needed in the future
        }
    }

    override fun enterExportDefaultDeclaration(ctx: TypeScriptParser.ExportDefaultDeclarationContext?) {
        // Get the exported expression/name
        val name = ctx?.singleExpression()?.text
        if (name != null) {
            val export = CodeExport(
                Name = name,
                Kind = ExportKind.DEFAULT
            )
            currentNode.Exports += export
            defaultNode.Exports += export
        }
    }

    override fun enterExportDeclaration(ctx: TypeScriptParser.ExportDeclarationContext?) {
        // Handle exports like: export { foo, bar } or export { foo } from 'module'
        val exportFromBlock = ctx?.exportFromBlock() ?: return
        val specifiers = mutableListOf<ExportSpecifier>()
        
        // Check if this is a re-export (has 'from' clause)
        val fromSource = exportFromBlock.importFrom()?.StringLiteral()?.text?.let { unQuote(it) } ?: ""
        val isReExport = fromSource.isNotBlank()
        
        // Handle export * from 'module' (via importNamespace with Multiply)
        val importNamespace = exportFromBlock.importNamespace()
        if (importNamespace?.Multiply() != null) {
            val export = CodeExport(
                Name = "*",
                Kind = ExportKind.RE_EXPORT_ALL,
                FromSource = fromSource
            )
            currentNode.Exports += export
            defaultNode.Exports += export
            return
        }
        
        exportFromBlock.exportModuleItems()?.exportAliasName()?.forEach {
            val localName = it.moduleExportName(0)?.text ?: ""
            val exportedName = if (it.moduleExportName().size > 1) {
                it.moduleExportName(1)?.text ?: localName
            } else {
                localName
            }
            
            if (localName.isNotBlank()) {
                specifiers += ExportSpecifier(LocalName = localName, ExportedName = exportedName)
                
                // Legacy: add individual CodeExport for each name
                val export = CodeExport(
                    Name = exportedName,
                    Kind = if (isReExport) ExportKind.RE_EXPORT_NAMED else ExportKind.NAMED,
                    FromSource = fromSource,
                    Specifiers = listOf(ExportSpecifier(LocalName = localName, ExportedName = exportedName))
                )
                currentNode.Exports += export
                defaultNode.Exports += export
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        for (entry in nodeMap) {
            codeContainer.DataStructures += entry.value
        }

        // for: `export const baseURL = '/api'`
        val fieldOnly = defaultNode.Fields.isNotEmpty()
        // for export default function
        val functionOnly = defaultNode.Functions.isNotEmpty()

        if (functionOnly) {
            defaultNode.NodeName = "default"
            defaultNode.FilePath = codeContainer.FullName
            defaultNode.Package = codeContainer.PackageName
            codeContainer.DataStructures += defaultNode
        } else if (fieldOnly) {
            defaultNode.NodeName = "default"
            defaultNode.FilePath = codeContainer.FullName
            defaultNode.Package = codeContainer.PackageName
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }

//    override fun enterEveryRule(ctx: ParserRuleContext?) {
//        println(ctx!!.javaClass.simpleName)
//    }
}
