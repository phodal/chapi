package chapi.ast.arkts

import chapi.ast.antlr.ArkTSParser
import chapi.ast.antlr.ArkTSParser.IdentifierExpressionContext
import chapi.ast.antlr.ArkTSParser.ParenthesizedExpressionContext
import chapi.ast.antlr.ArkTSParser.VariableStatementContext
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class ArkTSFullIdentListener(val node: ArkTSIdentify) : ArkTSAstListener() {
    private var hasHtmlElement: Boolean = false
    private var filePath: String = node.filePath

    private var exitArrowCount: Int = 0
    private var hasAnnotation: Boolean = false
    private var hasEnterClass = false
    private var currentExprIdent: String = ""
    private var localVars = mutableMapOf<String, String>()

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer =
        CodeContainer(
            FullName = node.filePath,
            PackageName = node.resolvePackage(),
            Language = "arkts",
            Kind = ContainerKind.MODULE,
            ResolvedModulePath = node.resolvePackage()
        )

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunc = CodeFunction(IsConstructor = false)
    private var namespaceName: String = ""
    private var currentAnnotations = listOf<CodeAnnotation>()

    private var funcsStackForCount = Stack<CodeFunction>()
    private var arrowFunctionScopeStack = Stack<Boolean>()

    private var classNodeStack = Stack<CodeDataStruct>()

    override fun enterNamespaceDeclaration(ctx: ArkTSParser.NamespaceDeclarationContext?) {
        this.namespaceName = ctx!!.namespaceName().text
        // Update namespace path for structured semantics
        codeContainer.NamespacePath = codeContainer.NamespacePath + this.namespaceName
    }

    override fun exitNamespaceDeclaration(ctx: ArkTSParser.NamespaceDeclarationContext?) {
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
        varDecl: ArkTSParser.VariableDeclarationListContext,
        modifiers: List<String> = listOf()
    ): List<CodeField> {
        return varDecl.variableDeclaration()
            .map { decl ->
                variableToField(decl, modifiers)
            }
    }

    private fun variableToField(
        it: ArkTSParser.VariableDeclarationContext,
        modifiers: List<String>
    ): CodeField {
        val key = it.getChild(0).text
        val hasInitializer = it.Assign() != null
        val singleExpressions = it.singleExpression()

        // Only treat it as a value when it actually has an initializer
        val lastExpr = if (hasInitializer) singleExpressions.lastOrNull() else null
        val field = CodeField(TypeKey = key, TypeValue = lastExpr?.text ?: "", Modifiers = modifiers)

        when (lastExpr) {
            is ArkTSParser.LiteralExpressionContext -> {
                if (lastExpr.literal().StringLiteral() != null) {
                    field.TypeValue = unQuote(lastExpr.text)
                    field.TypeType = "String"
                }
            }

            is IdentifierExpressionContext -> {
                singleExprToFieldCall(field, lastExpr.singleExpression(), lastExpr.identifierName().text)
            }

            is ArkTSParser.YieldExpressionContext -> {
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
        singleExpr: ArkTSParser.SingleExpressionContext?,
        funcName: String
    ) {
        if (singleExpr != null) {
            when (singleExpr) {
                is ArkTSParser.ParenthesizedExpressionContext -> {
                    val parameters = parseParenthesizedExpression(singleExpr)
                    field.Calls += CodeCall("", CallType.FIELD, "", funcName, parameters)
                }

                is ArkTSParser.ArgumentsExpressionContext -> {
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
                    if (child is ArkTSParser.ParenthesizedExpressionContext) {
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

    override fun enterDecoratorList(ctx: ArkTSParser.DecoratorListContext?) {
        if (!hasEnterClass) {
            hasAnnotation = true

            ctx!!.decorator()
                .asSequence()
                .map { buildAnnotation(it) }
                .forEach { currentAnnotations += it }
        }
    }

    override fun enterClassDeclaration(ctx: ArkTSParser.ClassDeclarationContext?) {
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

    private fun handleClassBodyElements(classTailCtx: ArkTSParser.ClassBodyContext) {
        for (clzElementCtx in classTailCtx.classMemberList().classMember()) {
            val childCtx = clzElementCtx.getChild(0) ?: continue
            when (childCtx) {
                is ArkTSParser.ConstructorDeclarationContext -> {
                    val codeFunction = this.buildConstructorMethod(childCtx)
                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }

                // Fields and methods are handled by their enter/exit callbacks so their
                // decorators, bodies, and calls are attached to the right struct/class.
                is ArkTSParser.PropertyDeclarationExpressionContext -> Unit
                is ArkTSParser.MethodDeclarationExpressionContext -> Unit

                is ArkTSParser.GetterSetterDeclarationExpressionContext -> {
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

                is ArkTSParser.AbstractMemberDeclarationContext -> {
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

    private fun buildConstructorMethod(ctx: ArkTSParser.ConstructorDeclarationContext): CodeFunction {
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

    private fun buildImplements(typeList: ArkTSParser.ClassOrInterfaceTypeListContext?): List<String> {
        return typeList?.parameterizedTypeRef()?.map { typeRefCtx ->
            typeRefCtx.typeName().text
        } ?: listOf()
    }

    override fun exitClassDeclaration(ctx: ArkTSParser.ClassDeclarationContext?) {
        hasEnterClass = false
        classNodeStack.pop()
    }

    override fun enterStructDeclaration(ctx: ArkTSParser.StructDeclarationContext?) {
        if (ctx == null) return
        val nodeName = ctx.identifierName().text
        hasEnterClass = true

        currentNode = CodeDataStruct(
            NodeName = nodeName,
            Type = DataStructType.STRUCT,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Position = buildPosition(ctx),
            Annotations = ctx.decoratorList()?.decorator()?.map(::buildAnnotation) ?: listOf()
        )

        ctx.classHeritage().classImplementsClause()?.let {
            currentNode.Implements = buildImplements(it.classOrInterfaceTypeList())
        }
        ctx.classHeritage().classExtendsClause()?.let {
            currentNode.Extend = it.parameterizedTypeRef().typeName().text
        }

        ctx.classBody()?.takeIf { it.classMemberList() != null }?.let(::handleClassBodyElements)
        classNodeStack.push(currentNode)
        nodeMap[nodeName] = currentNode
    }

    override fun exitStructDeclaration(ctx: ArkTSParser.StructDeclarationContext?) {
        hasEnterClass = false
        classNodeStack.pop()
        currentNode = classNodeStack.peek()
            ?: CodeDataStruct(Package = codeContainer.PackageName, FilePath = codeContainer.FullName)
    }

    override fun enterInterfaceDeclaration(ctx: ArkTSParser.InterfaceDeclarationContext?) {
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

    override fun exitInterfaceDeclaration(ctx: ArkTSParser.InterfaceDeclarationContext?) {
        currentNode = CodeDataStruct(Package = codeContainer.PackageName, FilePath = codeContainer.FullName)
    }

    override fun enterAnnotationDeclaration(ctx: ArkTSParser.AnnotationDeclarationContext?) {
        if (ctx == null) return
        val annotationType = CodeDataStruct(
            NodeName = ctx.identifierName().text,
            Type = DataStructType.INTERFACE,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Position = buildPosition(ctx),
            Annotations = listOf(CodeAnnotation(Name = "interface", Position = buildPosition(ctx)))
        )
        currentNode = annotationType
        ctx.annotationBody().annotationMember().forEach { member ->
            annotationType.Fields += CodeField(
                TypeKey = member.propertyName().text,
                TypeValue = member.initializer()?.singleExpression()?.text ?: "",
                TypeType = buildTypeAnnotation(member.typeAnnotation()),
                TypeRef = ArkTSTypeRefBuilder.build(member.typeAnnotation())
            )
        }
        nodeMap[annotationType.NodeName] = annotationType
    }

    override fun exitAnnotationDeclaration(ctx: ArkTSParser.AnnotationDeclarationContext?) {
        currentNode = classNodeStack.peek()
            ?: CodeDataStruct(Package = codeContainer.PackageName, FilePath = codeContainer.FullName)
    }

    override fun enterPropertyDeclarationExpression(ctx: ArkTSParser.PropertyDeclarationExpressionContext?) {
        if (ctx == null || classNodeStack.isEmpty()) return
        val owner = classNodeStack.peek() ?: return
        val member = ctx.parent as? ArkTSParser.ClassMemberContext
        val annotations = member?.decoratorList()?.decorator()?.map(::buildAnnotation) ?: listOf()
        val initializer = ctx.initializer()?.singleExpression()?.text ?: ""
        owner.Fields += CodeField(
            TypeKey = ctx.propertyName().text,
            TypeValue = initializer,
            TypeType = buildTypeAnnotation(ctx.typeAnnotation()),
            TypeRef = ArkTSTypeRefBuilder.build(ctx.typeAnnotation()),
            Modifiers = ctx.propertyMemberBase().children?.map { it.text } ?: listOf(),
            Annotations = annotations
        )
    }

    override fun enterMethodDeclarationExpression(ctx: ArkTSParser.MethodDeclarationExpressionContext?) {
        if (ctx == null || classNodeStack.isEmpty()) return
        val member = ctx.parent as? ArkTSParser.ClassMemberContext
        val func = CodeFunction(
            Name = ctx.propertyName().text,
            FilePath = filePath,
            Position = buildPosition(ctx),
            Annotations = member?.decoratorList()?.decorator()?.map(::buildAnnotation) ?: listOf(),
            Modifiers = ctx.propertyMemberBase().children?.map { it.text } ?: listOf()
        )
        fillMethodFromCallSignature(ctx.callSignature(), func)
        ctx.callSignature().typeRef()?.let {
            func.ReturnType = processRef(it) ?: ""
            func.ReturnTypeRef = ArkTSTypeRefBuilder.buildFromTypeRef(it)
        }
        processingNewArrowFunc(func)
    }

    override fun exitMethodDeclarationExpression(ctx: ArkTSParser.MethodDeclarationExpressionContext?) {
        finishCurrentFunction(classNodeStack.peek())
    }

    private fun buildInterfaceBody(typeMemberList: ArkTSParser.InterfaceMemberListContext) {
        typeMemberList.interfaceMember()?.forEach { memberContext ->
            when (val memberChild = memberContext.getChild(0)) {
                is ArkTSParser.PropertySignatureContext -> {
                    buildInterfacePropertySignature(memberChild)
                }

                is ArkTSParser.MethodSignatureContext -> {
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

    private fun buildInterfacePropertySignature(signCtx: ArkTSParser.PropertySignatureContext) {
        val annotation = signCtx.typeAnnotation()
        val typeType = buildTypeAnnotation(annotation)
        val typeValue = signCtx.propertyName().text

        val codeField = CodeField(
            TypeType = typeType,
            TypeValue = typeValue,
            TypeRef = ArkTSTypeRefBuilder.build(annotation)
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

    override fun enterFromBlock(ctx: ArkTSParser.FromBlockContext?) {
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

        if (ctx.moduleItems() == null && ctx.importNamespace() == null) {
            codeImport.Kind = ImportKind.SIDE_EFFECT
        }
        codeImport.Specifiers = specifiers
        applyArkTSImportQualifier(codeImport, ctx.parent as? ArkTSParser.ImportStatementContext)
        codeContainer.Imports += codeImport
    }

    // Handle new grammar import syntax
    override fun enterImportFromBlock(ctx: ArkTSParser.ImportFromBlockContext?) {
        if (ctx == null) return

        // Get import source from importFrom or StringLiteral
        val source = ctx.importFrom()?.StringLiteral()?.text
            ?: ctx.StringLiteral()?.text
            ?: return
        val codeImport = CodeImport(Source = unQuote(source))
        if (ctx.importFrom() == null && ctx.StringLiteral() != null) {
            codeImport.Kind = ImportKind.SIDE_EFFECT
        }
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
        applyArkTSImportQualifier(codeImport, ctx.parent as? ArkTSParser.ImportStatementContext)
        codeContainer.Imports += codeImport
    }

    private fun applyArkTSImportQualifier(
        codeImport: CodeImport,
        statement: ArkTSParser.ImportStatementContext?
    ) {
        when {
            statement?.TypeAlias() != null -> {
                codeImport.Kind = ImportKind.TYPE_ONLY
                codeImport.Specifiers.forEach { it.IsTypeOnly = true }
            }
            statement?.Lazy() != null -> codeImport.Scope = "lazy"
        }
    }

    private fun unQuote(text: String): String = text.replace("[\"']".toRegex(), "")

    override fun enterImportAliasDeclaration(ctx: ArkTSParser.ImportAliasDeclarationContext?) {
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

    override fun enterImportAll(ctx: ArkTSParser.ImportAllContext?) {
        codeContainer.Imports += CodeImport(
            Source = unQuote(ctx!!.StringLiteral().text),
            Kind = ImportKind.SIDE_EFFECT
        )
    }

    // see also in arrow function declaration
    override fun enterFunctionDeclaration(ctx: ArkTSParser.FunctionDeclarationContext?) {
        if (ctx == null) return
        if (ctx.identifierName() == null) return

        val funcName = ctx.identifierName().text
        val func = CodeFunction(FilePath = filePath)
        func.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature(), func)
        func.Position = this.buildPosition(ctx)
        func.Annotations = ctx.decoratorList()?.decorator()?.map(::buildAnnotation) ?: listOf()

        processingNewArrowFunc(func)
    }

    // todo: align logic to arrow functions
    override fun exitFunctionDeclaration(ctx: ArkTSParser.FunctionDeclarationContext?) {
        finishCurrentFunction(null)
    }

    // see also in function declaration
    override fun enterArrowFunctionDeclaration(ctx: ArkTSParser.ArrowFunctionDeclarationContext?) {
        // Try to find VariableDeclarationContext in parent chain (up to 5 levels)
        val varDecl = findParentOfType<ArkTSParser.VariableDeclarationContext>(ctx, 5)
        val argCtx = findParentOfType<ArkTSParser.ArgumentContext>(ctx, 5)
        val exprSeqCtx = findParentOfType<ArkTSParser.ExpressionSequenceContext>(ctx, 3)
        var ownsFunctionScope = false

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

                processingNewArrowFunc(func)
                ownsFunctionScope = true
            }

            argCtx != null -> {
                // todo: add arg ctx
                currentFunc.FunctionCalls += CodeCall(FunctionName = currentExprIdent, Type = CallType.ARROW)
            }
            // such as: `(e) => e.stopPropagation()`
            exprSeqCtx != null -> {
                // Callback expression: calls stay attached to the enclosing function.
            }
            // Other arrow functions are callbacks and do not own a Chapi function scope.
            else -> Unit
        }
        arrowFunctionScopeStack.push(ownsFunctionScope)

        if (ctx?.arrowFunctionBody() == null) return

        // Handle expression body: => expression
        if (ctx.arrowFunctionBody().singleExpression() != null) {
            parseSingleExpression(ctx.arrowFunctionBody().singleExpression())
        }
    }

    override fun enterReturnStatement(ctx: ArkTSParser.ReturnStatementContext?) {
        if (ctx?.expressionSequence() != null) {
            ctx.expressionSequence().singleExpression().forEach(::parseSingleExpression)
        }

        if (node.isJsxFile()) {
            currentFunc.IsReturnHtml = true
        }
    }

    private fun processingNewArrowFunc(func: CodeFunction) {
        if (funcsStackForCount.count() != 0) {
            currentFunc.InnerFunctions += func
        }

        funcsStackForCount.push(func)
        currentFunc = func
    }

    override fun exitArrowFunctionDeclaration(ctx: ArkTSParser.ArrowFunctionDeclarationContext?) {
        handleFuncDeclExit(arrowFunctionScopeStack.pop() ?: false)
    }

    private fun finishCurrentFunction(owner: CodeDataStruct?) {
        val finished = funcsStackForCount.pop() ?: return
        if (funcsStackForCount.count() != 0) {
            currentFunc = funcsStackForCount.peek()!!
        } else {
            if (owner == null) {
                defaultNode.Functions += finished
            } else {
                owner.Functions += finished
            }
            currentFunc = CodeFunction()
        }
        exitArrowCount = funcsStackForCount.count()
    }

    override fun enterArkUILeadingDotStatement(ctx: ArkTSParser.ArkUILeadingDotStatementContext?) {
        if (ctx == null || funcsStackForCount.isEmpty()) return
        ctx.identifierName().forEachIndexed { index, name ->
            currentFunc.FunctionCalls += CodeCall(
                Type = CallType.FUNCTION,
                FunctionName = name.text,
                Parameters = buildArguments(ctx.arguments(index)),
                Position = buildPosition(ctx),
                ReceiverExpr = "."
            )
        }
    }

    private fun handleFuncDeclExit(ownsFunctionScope: Boolean) {
        if (!ownsFunctionScope) return

        val pop = funcsStackForCount.pop()
        if (pop != null) {
            if (funcsStackForCount.count() != 0) {
                currentFunc = funcsStackForCount.peek()!!
            }
        }

        exitArrowCount = funcsStackForCount.count()
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

    private fun buildArrowFunctionParameters(arrowFuncCtx: ArkTSParser.ArrowFunctionParametersContext?): List<CodeProperty> {
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

    override fun enterFunctionExpression(ctx: ArkTSParser.FunctionExpressionContext?) {
        // Skip if this is an arrow function (handled by enterArrowFunctionDeclaration)
        val anonFunc = ctx?.anonymousFunction()
        if (anonFunc?.arrowFunctionDeclaration() != null) {
            return
        }

        // Try different parent levels to find VariableDeclarationContext
        val varDecl = findParentOfType<ArkTSParser.VariableDeclarationContext>(ctx, 5)

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

    private fun parseStatement(context: ArkTSParser.StatementContext) {
        when (val child = context.getChild(0)) {
            is ArkTSParser.ReturnStatementContext -> {

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

    private fun singleExpToText(ctx: ArkTSParser.SingleExpressionContext): String {
        var text = ctx.text
        when (ctx) {
            is ArkTSParser.LiteralExpressionContext -> {
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

    private fun parseSingleExpression(ctx: ArkTSParser.SingleExpressionContext) {
        when (ctx) {
            is IdentifierExpressionContext -> {
                currentExprIdent = ctx.identifierName().text

                if (ctx.singleExpression() != null) {
                    parseSingleExpression(ctx.singleExpression())
                }
            }

            is ArkTSParser.GenericTypesContext -> {
                if (ctx.expressionSequence() != null) {
                    parseExpressionSequence(ctx.expressionSequence())
                }
            }

            is ArkTSParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(ctx)
            }

            is ArkTSParser.GenericCallExpressionContext -> {
                // e.g. axios<Module[]>({ ... })
                val calleeText = ctx.identifierName().text
                currentExprIdent = calleeText
                val args = buildArguments(ctx.arguments())
                currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, args)
            }

            is ArkTSParser.OptionalCallExpressionContext -> {
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

    private fun argumentsExpressionToCall(argument: ArkTSParser.ArgumentsExpressionContext, varName: String = "") {
        val callee = argument.singleExpression()
        val args = buildArguments(argument.arguments())

        // For chained calls like axios(...).then(...).catch(...)
        if (callee is ArkTSParser.MemberDotExpressionContext) {
            val chainInfo = buildStructuredCallChain(callee)
            val rawFn = chainInfo.legacyName.ifBlank { callee.text }
            val fn = normalizeMemberCallName(rawFn)

            // Build structured CodeCall with new fields
            currentFunc.FunctionCalls += CodeCall(
                Type = CallType.FUNCTION,
                FunctionName = fn,
                Parameters = args,
                // New structured fields (Issue #41)
                ReceiverExpr = chainInfo.receiverExpr,
                Chain = chainInfo.chain,
                ChainArguments = chainInfo.chainArguments,
                IsOptional = chainInfo.isOptional
            )
            return
        }

        val calleeText = callee.text
        currentExprIdent = if (varName.isNotBlank()) varName else calleeText
        currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, args)
    }

    /**
     * Data class holding structured chain call information.
     */
    private data class ChainCallInfo(
        val receiverExpr: String = "",
        val firstMethod: String = "",
        val chain: List<String> = listOf(),
        val chainArguments: List<List<CodeProperty>> = listOf(),
        val isOptional: Boolean = false,
        val legacyName: String = "" // For backward compatibility
    )

    /**
     * Builds structured chain call info from MemberDotExpression.
     * Returns ChainCallInfo with receiver, chain methods, and their arguments.
     */
    private fun buildStructuredCallChain(expr: ArkTSParser.MemberDotExpressionContext): ChainCallInfo {
        val chainMethods = mutableListOf<String>()
        val chainArgs = mutableListOf<List<CodeProperty>>()
        var receiver = ""
        var isOptional = false

        // Walk down the chain to collect all method names and arguments
        fun walkChain(e: ArkTSParser.SingleExpressionContext?) {
            when (e) {
                is ArkTSParser.MemberDotExpressionContext -> {
                    // Add current method name to the front
                    chainMethods.add(0, e.identifierName().text)

                    // Check for optional chaining (?.method)
                    if (e.QuestionMark() != null) {
                        isOptional = true
                    }

                    // Continue walking down
                    walkChain(e.singleExpression())
                }
                is ArkTSParser.ArgumentsExpressionContext -> {
                    // Collect arguments for this call
                    val args = buildArguments(e.arguments())
                    chainArgs.add(0, args)
                    walkChain(e.singleExpression())
                }
                is ArkTSParser.GenericCallExpressionContext -> {
                    receiver = e.identifierName().text
                }
                is IdentifierExpressionContext -> {
                    receiver = e.identifierName().text
                }
                is ArkTSParser.OptionalCallExpressionContext -> {
                    isOptional = true
                    walkChain(e.singleExpression())
                }
                else -> {
                    // Try to extract identifier from raw text
                    val raw = e?.text ?: ""
                    val match = Regex("^[A-Za-z_$][A-Za-z0-9_$]*").find(raw)?.value
                    if (!match.isNullOrBlank()) {
                        receiver = match
                    }
                }
            }
        }

        walkChain(expr)

        // First method goes to FunctionName, rest go to Chain
        val firstMethod = chainMethods.firstOrNull() ?: ""
        val restChain = if (chainMethods.size > 1) chainMethods.drop(1) else listOf()

        // Build legacy name for backward compatibility
        val legacyName = if (receiver.isNotBlank()) {
            "$receiver->${chainMethods.joinToString("->")}"
        } else {
            chainMethods.joinToString("->")
        }

        return ChainCallInfo(
            receiverExpr = receiver,
            firstMethod = firstMethod,
            chain = restChain,
            chainArguments = if (chainArgs.size > 1) chainArgs.drop(1) else listOf(),
            isOptional = isOptional,
            legacyName = legacyName
        )
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

    private fun buildCallChain(expr: ArkTSParser.SingleExpressionContext?): String {
        if (expr == null) return ""
        return when (expr) {
            is ArkTSParser.MemberDotExpressionContext -> {
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

            is ArkTSParser.ArgumentsExpressionContext -> {
                buildCallChain(expr.singleExpression())
            }

            is ArkTSParser.GenericCallExpressionContext -> {
                expr.identifierName().text
            }

            is IdentifierExpressionContext -> {
                expr.identifierName().text
            }

            // identifierName + typeArguments is typically represented via IdentifierExpressionContext + GenericTypes child,
            // so we can usually ignore this and let recursion find the identifier.
            is ArkTSParser.GenericTypesContext -> {
                expr.expressionSequence()?.singleExpression()?.firstOrNull()?.let { buildCallChain(it) } ?: ""
            }

            else -> {
                // Fallback: best-effort take the prefix before '(' or '.'
                expr.text.substringBefore('(').substringBefore('.')
            }
        }
    }

    private fun parseArguments(argument: ArkTSParser.ArgumentsExpressionContext): List<CodeProperty> {
        var params = listOf<CodeProperty>()

        // for: `axios<Module[]>({parameter}).then`
        // create then and update parameter
        argument.children.forEach {
            when (it) {
                is IdentifierExpressionContext -> {
                    currentExprIdent = it.identifierName().text
                }

                is ArkTSParser.MemberDotExpressionContext -> {
                    val expr = it.singleExpression()
                    when (expr) {
                        is ArkTSParser.ParenthesizedExpressionContext -> {
                            params += parseParenthesizedExpression(expr)
                        }

                        is ArkTSParser.ArgumentsExpressionContext -> {
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
                is ArkTSParser.ObjectLiteralExpressionContext -> {
                    val objectLiteral = parseObjectLiteral(subSingle.objectLiteral())
                    parameter =
                        CodeProperty(TypeValue = subSingle.text, TypeType = "object", ObjectValue = objectLiteral)
                }

                is ArkTSParser.ArgumentsExpressionContext -> {
                    parseArguments(subSingle)
                }

                is ArkTSParser.IdentifierExpressionContext -> {
                    parameter = CodeProperty(TypeValue = subSingle.text, TypeType = "parameter")
                }

                is ArkTSParser.ArrayLiteralExpressionContext -> {
                    parameter = CodeProperty(TypeValue = "[]", TypeType = "parameter")
                }

                is ArkTSParser.LiteralExpressionContext -> {
                    parameter = CodeProperty(TypeValue = singleExpToText(subSingle), TypeType = "string")
                }

                else -> {
//                    println("todo -> ParenthesizedExpressionContext: ${subSingle.javaClass.simpleName}, text: ${subSingle.text}")
                }
            }

            parameter
        }
    }

    private fun parseObjectLiteral(objectLiteral: ArkTSParser.ObjectLiteralContext): List<CodeProperty> {
        return objectLiteral.propertyAssignment().mapNotNull { property ->
            when (property) {
                is ArkTSParser.PropertyExpressionAssignmentContext -> {
                    val text = if (property.singleExpression() != null) {
                        property.singleExpression().text
                    } else {
                        property.text
                    }

                    val value = CodeProperty(TypeType = "value", TypeValue = text)
                    val propText = property.propertyName().text

                    CodeProperty(TypeType = "key", TypeValue = propText, ObjectValue = listOf(value))
                }

                is ArkTSParser.PropertyShorthandContext -> {
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

    private fun parseExpressionSequence(ctx: ArkTSParser.ExpressionSequenceContext) {
        ctx.singleExpression().forEach { singleExpressionContext ->
            parseSingleExpression(singleExpressionContext)
        }
    }

    private fun fillMethodFromCallSignature(
        callSignCtx: ArkTSParser.CallSignatureContext,
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

    private fun buildReturnTypeByType(typeAnnotationContext: ArkTSParser.TypeAnnotationContext?): CodeProperty =
        CodeProperty(
            TypeType = buildTypeAnnotation(typeAnnotationContext) ?: "",
            TypeValue = "",
            TypeRef = ArkTSTypeRefBuilder.build(typeAnnotationContext)
        )

    private fun buildReturnTypeByTypeRef(typeRefContext: ArkTSParser.TypeRefContext): CodeProperty =
        CodeProperty(
            TypeType = processRef(typeRefContext) ?: "",
            TypeValue = "",
            TypeRef = ArkTSTypeRefBuilder.buildFromTypeRef(typeRefContext)
        )

    override fun enterExpressionStatement(ctx: ArkTSParser.ExpressionStatementContext?) {
        if (ctx?.expressionSequence() == null) return

        for (singleExprCtx in ctx.expressionSequence().singleExpression()) {
            when (singleExprCtx) {
                is ArkTSParser.ArgumentsExpressionContext -> {
                    val nodeName = wrapTargetType(singleExprCtx)
                    val funcName = buildFunctionName(singleExprCtx)

                    // Check if this is a chained call (contains ->)
                    val callee = singleExprCtx.singleExpression()
                    val chainInfo = (callee as? ArkTSParser.MemberDotExpressionContext)?.let(::buildStructuredCallChain)
                    val modifierChain = chainInfo?.let { listOf(it.firstMethod) + it.chain }?.filter { it.isNotBlank() } ?: listOf()

                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = processArgumentList(singleExprCtx.arguments()?.argumentList()),
                        FunctionName = funcName,
                        NodeName = nodeName,
                        Position = buildPosition(ctx),
                        // New structured fields (Issue #41)
                        ReceiverExpr = chainInfo?.receiverExpr ?: nodeName,
                        Chain = modifierChain,
                        ChainArguments = chainInfo?.chainArguments ?: listOf(),
                        IsOptional = chainInfo?.isOptional ?: false
                    )

                    // ArkUI modifier calls are useful as standalone calls to downstream UI-flow analysis.
                    // Only expand chains whose receiver is itself a call (for example Text().fontSize()),
                    // not ordinary member calls such as console.info().
                    if (chainInfo != null && hasArgumentsReceiver(callee)) {
                        modifierChain.forEach { modifier ->
                            currentFunc.FunctionCalls += CodeCall(
                                Type = CallType.FUNCTION,
                                FunctionName = modifier,
                                ReceiverExpr = nodeName,
                                Position = buildPosition(ctx)
                            )
                        }
                    }
                }

                is ArkTSParser.IdentifierExpressionContext -> {
                    val child = singleExprCtx.singleExpression()
                    val params = when (child) {
                        is ParenthesizedExpressionContext -> parseParenthesizedExpression(child)
                        else -> listOf()
                    }
                    val chainedArguments = child as? ArkTSParser.ArgumentsExpressionContext
                    val memberExpression = chainedArguments?.singleExpression() as? ArkTSParser.MemberDotExpressionContext
                    val chainInfo = memberExpression?.let(::buildStructuredCallChain)
                    val modifierChain = chainInfo?.let { listOf(it.firstMethod) + it.chain }?.filter { it.isNotBlank() } ?: listOf()
                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = params,
                        FunctionName = singleExprCtx.identifierName().text,
                        NodeName = "",
                        Position = buildPosition(ctx),
                        ReceiverExpr = singleExprCtx.identifierName().text,
                        Chain = modifierChain,
                        ChainArguments = chainInfo?.chainArguments ?: listOf()
                    )
                    modifierChain.forEach { modifier ->
                        currentFunc.FunctionCalls += CodeCall(
                            Type = CallType.FUNCTION,
                            FunctionName = modifier,
                            ReceiverExpr = singleExprCtx.identifierName().text,
                            Position = buildPosition(ctx)
                        )
                    }
                }

                else -> {
//                    println("enterExpressionStatement : ${singleExprCtx.javaClass.simpleName}")
                }
            }

        }
    }

    private fun hasArgumentsReceiver(expression: ArkTSParser.SingleExpressionContext?): Boolean =
        when (expression) {
            is ArkTSParser.ArgumentsExpressionContext -> true
            is ArkTSParser.MemberDotExpressionContext -> hasArgumentsReceiver(expression.singleExpression())
            else -> false
        }

    private fun buildFunctionName(argsCtx: ArkTSParser.ArgumentsExpressionContext): String {
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

    private fun functionNameFromArguments(argsCtx: ArkTSParser.ArgumentsExpressionContext): String {
        return when (val singleExpr = argsCtx.singleExpression()) {
            is ArkTSParser.ArgumentsExpressionContext -> {
                functionNameFromArguments(singleExpr)
            }

            is ArkTSParser.MemberDotExpressionContext -> {
                when (val child = singleExpr.singleExpression()) {
                    is ArkTSParser.ArgumentsExpressionContext -> {
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

    private fun wrapTargetType(argsCtx: ArkTSParser.ArgumentsExpressionContext): String {
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
    override fun enterVariableDeclaration(ctx: ArkTSParser.VariableDeclarationContext?) {
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
            is ArkTSParser.NewExpressionContext -> {
                when (val newSingleExpr = initExpr.singleExpression()) {
                    // todo: legacy expression, remove
                    is IdentifierExpressionContext -> {
                        localVars[varName] = newSingleExpr.identifierName().text
                    }

                    is ArkTSParser.ArgumentsExpressionContext -> {
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

            is ArkTSParser.AwaitExpressionContext -> {
                parseSingleExpression(initExpr.singleExpression())
            }

            is ArkTSParser.ArrowFunctionExpressionLContext -> {
                // will recall by ArrowFunctionDeclaration
            }

            is ArkTSParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(initExpr, varName)
            }

            is ParenthesizedExpressionContext -> {
                parseParenthesizedExpression(initExpr)
            }
        }
    }

    private fun buildArguments(arguments: ArkTSParser.ArgumentsContext?): List<CodeProperty> {
        if (arguments?.getChild(1)?.text == ")") {
            return listOf()
        }

        val argumentList = arguments?.argumentList()
        return processArgumentList(argumentList)
    }

    private fun processArgumentList(argumentList: ArkTSParser.ArgumentListContext?) =
        argumentList?.argument()?.map {
            parseSingleExpression(it.singleExpression())
            val expr = it.singleExpression()
            when (expr) {
                is ArkTSParser.ObjectLiteralExpressionContext -> {
                    val objectLiteral = parseObjectLiteral(expr.objectLiteral())
                    return@map CodeProperty(
                        TypeValue = expr.text,
                        TypeType = "object",
                        ObjectValue = objectLiteral
                    )
                }
            }

            val typeValue: String = when (expr) {
                is ArkTSParser.LiteralExpressionContext -> {
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


    override fun enterExportElementDirectly(ctx: ArkTSParser.ExportElementDirectlyContext?) {
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

    override fun enterExportDefaultDeclaration(ctx: ArkTSParser.ExportDefaultDeclarationContext?) {
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

    override fun enterExportDeclaration(ctx: ArkTSParser.ExportDeclarationContext?) {
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
        val hasFields = defaultNode.Fields.isNotEmpty()
        // for export default function
        val hasFunctions = defaultNode.Functions.isNotEmpty()
        // for exports
        val hasExports = defaultNode.Exports.isNotEmpty()

        // New: populate TopLevel structure (Issue #41 - P0 adaptation)
        if (hasFunctions || hasFields || hasExports) {
            codeContainer.TopLevel = TopLevelScope(
                Functions = defaultNode.Functions,
                Fields = defaultNode.Fields,
                Exports = defaultNode.Exports
            )

            // Legacy: also maintain "default" node for backward compatibility
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
