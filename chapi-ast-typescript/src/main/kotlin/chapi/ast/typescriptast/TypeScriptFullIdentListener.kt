package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParser.IdentifierExpressionContext
import chapi.ast.antlr.TypeScriptParser.ParenthesizedExpressionContext
import chapi.ast.antlr.TypeScriptParser.VariableStatementContext
import chapi.domain.core.*
import chapi.infra.Stack
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
    private var codeContainer: CodeContainer = CodeContainer(FullName = node.filePath, PackageName = node.resolvePackage())

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunc = CodeFunction(IsConstructor = false)
    private var namespaceName: String = ""
    private var currentAnnotations = arrayOf<CodeAnnotation>()

    private var funcsStackForCount = Stack<CodeFunction>()

    private var classNodeStack = Stack<CodeDataStruct>()

    override fun enterNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ctx!!.namespaceName().text
    }

    override fun exitNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ""
    }

    override fun enterVariableStatement(ctx: VariableStatementContext?) {
        val isExport = ctx!!.parent.parent.getChild(0).text == "export"

        if (!isExport && ctx.variableDeclarationList() != null) {
            defaultNode.Fields = variableToFields(ctx.variableDeclarationList())
        }

        // such as: `export const baseURL = '/api'`
        // todo: add support for not only const?
        if (!isExport) return

        var modifier = ""
        ctx.children.forEach { child ->
            when (child) {
                is TypeScriptParser.VarModifierContext -> {
                    modifier = child.text
                }
                is TypeScriptParser.VariableDeclarationListContext -> {
                    val fields = variableToFields(child, arrayOf(modifier))

                    if (fields.isNotEmpty()) {
                        defaultNode.Exports += CodeExport(
                            Name = fields[0].TypeKey,
                            Type = DataStructType.Variable,
                            SourceFile = codeContainer.FullName
                        )

                        defaultNode.Fields += fields
                    }
                }
                is TerminalNodeImpl -> {

                }
                else -> {
//                        println("enterVariableStatement: ${it::class.java.simpleName} === ${it.text}")
                }
            }
        }
    }

    private fun variableToFields(
        varDecl: TypeScriptParser.VariableDeclarationListContext,
        modifiers: Array<String> = arrayOf()
    ): Array<CodeField> {
        return varDecl.variableDeclaration()
            .filter { it.Assign() != null }
            .map { decl ->
                variableToField(decl, modifiers)
            }.toTypedArray()
    }

    private fun variableToField(
        it: TypeScriptParser.VariableDeclarationContext,
        modifiers: Array<String>
    ): CodeField {
        val key = it.getChild(0).text
        val singleExpression = it.singleExpression()

        val lastExpr = singleExpression
        val field = CodeField(TypeKey = key, TypeValue = lastExpr.text, Modifiers = modifiers)

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
                is IdentifierExpressionContext -> {
                    field.Calls += CodeCall("", CallType.FIELD, "", singleExpr.identifierName().text)
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
        currentNode = CodeDataStruct(
            NodeName = nodeName,
            Type = DataStructType.CLASS,
            Package = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Position = buildPosition(ctx)
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

        // load annotations before class
        currentNode.Annotations = currentAnnotations
        currentAnnotations = arrayOf()
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
                    }

                    currentNode.Fields += codeField
                }
                is TypeScriptParser.MethodDeclarationExpressionContext -> {
                    val codeFunction = CodeFunction(
                        Name = childCtx.propertyName().text, Position = buildPosition(childCtx)
                    )
                    val callSignCtx = childCtx.callSignature()

                    if (callSignCtx.typeRef() != null) {
                        codeFunction.ReturnType = processRef(callSignCtx.typeRef())
                    }

                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }
                is TypeScriptParser.GetterSetterDeclarationExpressionContext -> {
                    val name = if(childCtx.getAccessor() != null) {
                        childCtx.getAccessor().identifierName()?.text ?: "get"
                    } else {
                        childCtx.setAccessor().identifierName()?.text ?: "set"
                    }

                    currentNode.Functions += CodeFunction(
                        Name = name, Position = buildPosition(childCtx)
                    )
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

    private fun buildImplements(typeList: TypeScriptParser.ClassOrInterfaceTypeListContext?): Array<String> {
        return typeList?.parameterizedTypeRef()?.map { typeRefCtx ->
            typeRefCtx.typeName().text
        }?.toTypedArray() ?: arrayOf()
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


        val codeField = CodeField(TypeType = typeType, TypeValue = typeValue)
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

        if (ctx.moduleItems() != null) {
            for (nameContext in ctx.moduleItems().aliasName()) {
                if (nameContext.identifierName().isNotEmpty()) {
                    codeImport.UsageName += nameContext.identifierName()[0].text
                    if (nameContext.As() != null) {
                        codeImport.AsName += nameContext.identifierName()[1].text
                    }
                }
//                if (nameContext.Of() != null) {
//                    codeImport.UsageName += nameContext.Of().text
//                    codeImport.AsName += nameContext.Of().text
//                }
            }
        }

        val importNamespace = ctx.importNamespace()
        if (importNamespace != null) {
            // for examples: import '*' from 'blabla';
            val isImportAll = importNamespace.Multiply() != null
            if (isImportAll) {
                codeImport.UsageName += "*"
                if (importNamespace.As() != null) {
                    codeImport.AsName += importNamespace.identifierName()[0].text
                }
            } else {
                codeImport.UsageName += importNamespace.identifierName()[0].text
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

        codeContainer.Imports += codeImport
    }

    private fun unQuote(text: String): String = text.replace("[\"']".toRegex(), "")

    override fun enterImportAliasDeclaration(ctx: TypeScriptParser.ImportAliasDeclarationContext?) {
        val imp = unQuote(ctx!!.StringLiteral().text)
        val codeImport = CodeImport(
            Source = imp
        )

        if (ctx.Require() != null) {
            codeImport.UsageName += ctx.identifierName().text
        }

        codeContainer.Imports += codeImport
    }

    override fun enterImportAll(ctx: TypeScriptParser.ImportAllContext?) {
        codeContainer.Imports += CodeImport(
            Source = unQuote(ctx!!.StringLiteral().text)
        )
    }

    // see also in arrow function declaration
    override fun enterFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        if(ctx == null) return
        if(ctx.identifierName() == null) return

        val funcName = ctx.identifierName().text
        val func = CodeFunction(FilePath = filePath)
        func.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature(), func)
        func.Position = this.buildPosition(ctx)

        isCallbackOrAnonymousFunction = false
        processingNewArrowFunc(func)

        if (ctx.functionBody() == null) {
            return
        }

        ctx.functionBody()?.statementList()?.statement()?.forEach { context ->
            parseStatement(context)
        }
    }

    // todo: align logic to arrow functions
    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        handleFuncDeclExit()
    }

    // see also in function declaration
    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        isCallbackOrAnonymousFunction = false

        when (val grad = ctx?.parent?.parent) {
            // for: const blabla = () => { }
            is TypeScriptParser.VariableDeclarationContext -> {
                val func = CodeFunction(
                    FilePath = filePath,
                    Name = grad.identifierName().text,
                    Parameters = this.buildArrowFunctionParameters(ctx.arrowFunctionParameters()),
                    Position = this.buildPosition(ctx)
                )

                if (ctx.typeAnnotation() != null) {
                    func.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }

                isCallbackOrAnonymousFunction = false
                processingNewArrowFunc(func)
            }

            is TypeScriptParser.ArgumentContext -> {
                // todo: add arg ctx
                isCallbackOrAnonymousFunction = true
                currentFunc.FunctionCalls += CodeCall(FunctionName = currentExprIdent, Type = CallType.ARROW)
            }
            // such as: `(e) => e.stopPropagation()`
            is TypeScriptParser.ExpressionSequenceContext -> {
                val func = CodeFunction(FilePath = filePath, Name = "")

                isCallbackOrAnonymousFunction = true
                processingNewArrowFunc(func)
            }

            else -> {
//                val text = ctx!!.parent.parent.text
//                println("enterArrowFunctionDeclaration -> $parentName, $text")
            }
        }

        if (ctx?.arrowFunctionBody() == null) return

        ctx.arrowFunctionBody()?.statementList()?.statement()?.forEach {
            parseStatement(it)
        }

        if (ctx.arrowFunctionBody().singleExpression() != null) {
            parseSingleExpression(ctx.arrowFunctionBody().singleExpression())
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
            defaultNode.Functions += currentFunc
            currentFunc = CodeFunction()
        }
    }

    private fun buildArrowFunctionParameters(arrowFuncCtx: TypeScriptParser.ArrowFunctionParametersContext?): Array<CodeProperty> {
        if (arrowFuncCtx!!.formalParameterList() != null) {
            return this.buildParameters(arrowFuncCtx.formalParameterList())
        }

        var parameters: Array<CodeProperty> = arrayOf()
        if (arrowFuncCtx.identifierName() != null) {
            parameters += CodeProperty(
                TypeValue = arrowFuncCtx.identifierName().text, TypeType = "any"
            )
        }

        return parameters
    }

    override fun enterFunctionExpression(ctx: TypeScriptParser.FunctionExpressionContext?) {
        when (val grad = ctx?.parent?.parent) {
            is TypeScriptParser.VariableDeclarationContext -> {
                currentFunc.Name = grad.identifierName().text

                if (ctx.formalParameterList() != null) {
                    currentFunc.Parameters = this.buildParameters(ctx.formalParameterList())
                }

                if (ctx.typeAnnotation() != null) {
                    currentFunc.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }

                currentFunc.Position = this.buildPosition(ctx)
                currentFunc.FilePath = filePath
                defaultNode.Functions += currentFunc
            }

            is IdentifierExpressionContext -> {
                currentFunc.Position = this.buildPosition(ctx)
                currentFunc.FilePath = filePath
                defaultNode.Functions += currentFunc
            }

            else -> {
//                println("enterFunctionExpressionDeclaration -> $parentName, ${statementParent.text} ")
            }
        }
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
                parseExpressionSequence(ctx.expressionSequence())
            }
            is TypeScriptParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(ctx)
            }
            is ParenthesizedExpressionContext -> {
                val parameters = parseParenthesizedExpression(ctx)
                currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, parameters)
            }
            else -> {
//                println("todo -> need support type: ${ctx::class.java.simpleName} ==== ${ctx.text}")
            }
        }
    }

    private fun argumentsExpressionToCall(argument: TypeScriptParser.ArgumentsExpressionContext, varName: String = "") {
        val argText = argument.singleExpression().text

        if (varName != "") {
            currentExprIdent = varName
        }

        if (argText.startsWith("(")) {
            // axios("/demo")
            val args = parseArguments(argument)
            currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, args)
        } else {
            currentExprIdent = argText

            // todo: add other case for call chain in arrow function
            if (argText.contains(").")) {
                val args = parseArguments(argument)
//                println("argText with )." + Json.encodeToString(args))
            }

            // axios.get() or _.orderBy()
            currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, arrayOf())
        }
    }

    private fun parseArguments(argument: TypeScriptParser.ArgumentsExpressionContext): Array<CodeProperty> {
        var params = arrayOf<CodeProperty>()

        // for: `axios<Module[]>({parameter}).then`
        // create then and update parameter
        argument.children.forEach {
            when (it) {
                is TypeScriptParser.MemberDotExpressionContext -> {
                    val singleExpression = it.singleExpression()
                    when (val expr = singleExpression.first()) {
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

    private fun parseParenthesizedExpression(context: ParenthesizedExpressionContext): Array<CodeProperty> {
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
                    println("todo -> ParenthesizedExpressionContext: ${subSingle.javaClass.simpleName}, text: ${subSingle.text}")
                }
            }

            parameter
        }.toTypedArray()
    }

    private fun parseObjectLiteral(objectLiteral: TypeScriptParser.ObjectLiteralContext): Array<CodeProperty> {
        return objectLiteral.propertyAssignment().mapNotNull { property ->
            when (property) {
                is TypeScriptParser.PropertyExpressionAssignmentContext -> {
                    val text = if(property.singleExpression() != null) {
                        property.singleExpression().text
                    } else {
                        property.text
                    }

                    val value = CodeProperty(TypeType = "value", TypeValue = text)
                    val propText = property.propertyName().text

                    CodeProperty(TypeType = "key", TypeValue = propText, ObjectValue = arrayOf(value))
                }

                is TypeScriptParser.PropertyShorthandContext -> {
                    val short = property.text
                    val value = CodeProperty(TypeType = "value", TypeValue = short)

                    CodeProperty(TypeType = "key", TypeValue = short, ObjectValue = arrayOf(value))
                }

                else -> {
                    null
                }
            }
        }.toTypedArray()
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
            TypeType = buildTypeAnnotation(typeAnnotationContext) ?: "", TypeValue = ""
        )

    private fun buildReturnTypeByTypeRef(typeRefContext: TypeScriptParser.TypeRefContext): CodeProperty =
        CodeProperty(
            TypeType = processRef(typeRefContext) ?: "", TypeValue = ""
        )

    override fun enterExpressionStatement(ctx: TypeScriptParser.ExpressionStatementContext?) {
        if (ctx?.expressionSequence() == null) return

        for (singleExprCtx in ctx.expressionSequence().singleExpression()) {
            when (singleExprCtx) {
                is TypeScriptParser.ArgumentsExpressionContext -> {
                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = processArgumentList(singleExprCtx.argumentList()),
                        FunctionName = buildFunctionName(singleExprCtx),
                        NodeName = wrapTargetType(singleExprCtx),
                        Position = buildPosition(ctx)
                    )
                }

                is TypeScriptParser.IdentifierExpressionContext -> {
                    currentFunc.FunctionCalls += CodeCall(
                        Parameters = arrayOf(),
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
        var text = argsCtx.singleExpression().text
        if (text.contains(".")) {
            val split = text.split(".")
            text = split[split.size - 1]
        }

        return text
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
        when (val singleExprCtx = ctx.singleExpression()) {
            is TypeScriptParser.NewExpressionContext -> {
                when (val newSingleExpr = singleExprCtx.singleExpression()) {
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
                when (singleExprCtx.identifierName().text) {
                    "await" -> {
                        parseSingleExpression(singleExprCtx.singleExpression())
                    }
                    "Number" -> {
                        parseSingleExpression(singleExprCtx.singleExpression())
                    }
                    else -> {
//                        println("IdentifierExpressionContext ->  ${singleExprCtx.text}")
                    }
                }
            }

            is TypeScriptParser.AwaitExpressionContext -> {
                parseSingleExpression(singleExprCtx.singleExpression())
            }
            is TypeScriptParser.ArrowFunctionExpressionLContext -> {
                // will recall by ArrowFunctionDeclaration
            }
            is TypeScriptParser.ArgumentsExpressionContext -> {
                argumentsExpressionToCall(singleExprCtx, varName)
            }
            is ParenthesizedExpressionContext -> {
                parseParenthesizedExpression(singleExprCtx)
            }
        }
    }

    private fun buildArguments(arguments: TypeScriptParser.ArgumentsContext?): Array<CodeProperty> {
        if (arguments?.getChild(1)?.text == ")") {
            return arrayOf()
        }

        val argumentList = arguments?.argumentList()
        return processArgumentList(argumentList)
    }

    private fun processArgumentList(argumentList: TypeScriptParser.ArgumentListContext?) =
        argumentList?.argument()?.map {
            parseSingleExpression(it.singleExpression())
            val typeValue: String = when (val expr = it.singleExpression()) {
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
        }?.toTypedArray() ?: arrayOf()


    override fun enterExportElementDirectly(ctx: TypeScriptParser.ExportElementDirectlyContext?) {
        when (val stmt = ctx?.declarationStatement()?.getChild(0)) {
            is VariableStatementContext -> {
                stmt.variableDeclarationList().variableDeclaration().forEach {
                    val text = it.identifierName().text
                    currentNode.Exports += CodeExport(text)
                    defaultNode.Exports += CodeExport(text)
                }
            }
        }
    }

    override fun enterExportDefaultDeclaration(ctx: TypeScriptParser.ExportDefaultDeclarationContext?) {
        val name = ctx!!.identifierName()
        if (name != null) {
            currentNode.Exports += CodeExport(name.text)
            defaultNode.Exports += CodeExport(name.text)
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
