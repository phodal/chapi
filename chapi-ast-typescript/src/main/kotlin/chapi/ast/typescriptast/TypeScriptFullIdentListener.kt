package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParser.IdentifierExpressionContext
import chapi.ast.antlr.TypeScriptParser.ParenthesizedExpressionContext
import chapi.domain.core.*
import chapi.infra.Stack
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeScriptFullIdentListener(node: TSIdentify) : TypeScriptAstListener() {
    private var hasHtmlElement: Boolean = false
    private var filePath: String = node.filePath

    private var exitArrowCount: Int = 0
    private var isCallbackOrAnonymousFunction: Boolean = false
    private var hasAnnotation: Boolean = false
    private var hasEnterClass = false
    private var currentExprIdent: String = ""
    private var localVars = mutableMapOf<String, String>()

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = node.filePath)

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

    override fun enterVariableStatement(ctx: TypeScriptParser.VariableStatementContext?) {
        val isExport = ctx!!.parent.parent.getChild(0).text == "export"

        if (!isExport && ctx.variableDeclarationList() != null) {
            defaultNode.Fields = variableToFields(ctx.variableDeclarationList())
        }

        // such as: `export const baseURL = '/api'`
        // todo: add support for not only const?
        if (isExport) {
            var modifier = ""
            ctx.children.forEach {
                when (it::class.java.simpleName) {
                    "VarModifierContext" -> {
                        modifier = it.text
                    }
                    "VariableDeclarationListContext" -> {
                        val varDeclList = it as TypeScriptParser.VariableDeclarationListContext
                        val fields = variableToFields(varDeclList, arrayOf(modifier))

                        if (fields.isNotEmpty()) {
                            defaultNode.Exports += CodeExport(
                                Name = fields[0].TypeKey,
                                Type = DataStructType.Variable,
                                SourceFile = codeContainer.FullName
                            )

                            defaultNode.Fields += fields
                        }
                    }
                    "TerminalNodeImpl" -> {

                    }
                    else -> {
                        println("enterVariableStatement: ${it::class.java.simpleName} === ${it.text}")
                    }
                }
            }
        }
    }

    private fun variableToFields(
        varDecl: TypeScriptParser.VariableDeclarationListContext,
        modifiers: Array<String> = arrayOf()
    ): Array<CodeField> {
        var fields = arrayOf<CodeField>()
        varDecl.variableDeclaration().forEach { it ->
            if (it.Assign() != null) {
                val key = it.getChild(0).text
                val singleExpression = it.singleExpression()

                val lastExpr = singleExpression.last()
                val field = CodeField(TypeKey = key, TypeValue = lastExpr.text, Modifiers = modifiers)

                when (lastExpr::class.simpleName) {
                    "LiteralExpressionContext" -> {
                        val literal = lastExpr as TypeScriptParser.LiteralExpressionContext
                        if (literal.literal().StringLiteral() != null) {
                            field.TypeValue = unQuote(literal.text)
                            field.TypeType = "String"
                        }
                    }
                    "IdentifierExpressionContext" -> {
                        val identExpr = lastExpr as IdentifierExpressionContext
                        singleExprToFieldCall(field, identExpr.singleExpression(), identExpr.identifierName().text)
                    }
                    "YieldExpressionContext" -> {
                        val yieldExpr = lastExpr as TypeScriptParser.YieldExpressionContext
                        if (yieldExpr.yieldStatement().expressionSequence() != null) {
                            val singeExprs = yieldExpr.yieldStatement().expressionSequence().singleExpression()
                            singeExprs.forEach { expr ->
                                singleExprToFieldCall(field, expr, expr.text)
                            }
                        }
                    }
                    else -> {
                        println("variableToFields -> ${lastExpr.text} === ${lastExpr.javaClass.simpleName}")
                    }
                }


                fields += field
            }
        }

        return fields
    }

    private fun singleExprToFieldCall(
        field: CodeField,
        singleExpr: TypeScriptParser.SingleExpressionContext?,
        funcName: String
    ) {
        if (singleExpr != null) {
            when (val name = singleExpr::class.simpleName) {
                "ParenthesizedExpressionContext" -> {
                    val parameters = parseParenthesizedExpression(singleExpr)
                    field.Calls += CodeCall("", CallType.FIELD, "", funcName, parameters)
                }
                "IdentifierExpressionContext" -> {
                    val identExpr = singleExpr as IdentifierExpressionContext
                    field.Calls += CodeCall("", CallType.FIELD, "", identExpr.identifierName().text)
                }
                else -> {
                    println("todo -> var -> decl call: $name")
                }
            }
        }
    }

    override fun enterDecoratorList(ctx: TypeScriptParser.DecoratorListContext?) {
        if (!hasEnterClass) {
            hasAnnotation = true

            for (decorator in ctx!!.decorator()) {
                val annotation = buildAnnotation(decorator)
                currentAnnotations += annotation
            }
        }
    }

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text

        hasEnterClass = true
        currentNode = CodeDataStruct(
            NodeName = nodeName,
            Type = DataStructType.CLASS,
            Package = this.namespaceName,
            FilePath = codeContainer.FullName
        )

        val heritageCtx = ctx.classHeritage()
        if (heritageCtx.implementsClause() != null) {
            val typeList = heritageCtx.implementsClause().classOrInterfaceTypeList()
            currentNode.Implements = buildImplements(typeList)
        }

        if (heritageCtx.classExtendsClause() != null) {
            val refCtx = heritageCtx.classExtendsClause().typeReference()
            currentNode.Extend = refCtx.typeName().text
        }

        // load annotations before class
        currentNode.Annotations = currentAnnotations
        currentAnnotations = arrayOf()
        hasAnnotation = false

        if (ctx.classTail() != null) {
            this.handleClassBodyElements(ctx.classTail())
        }

        classNodeStack.push(currentNode)
        nodeMap[nodeName] = currentNode
    }

    private fun handleClassBodyElements(classTailCtx: TypeScriptParser.ClassTailContext) {
        for (clzElementCtx in classTailCtx.classElement()) {
            val childCtx = clzElementCtx.getChild(0) ?: continue
            when (val childElementType = childCtx::class.java.simpleName) {
                "ConstructorDeclarationContext" -> {
                    val codeFunction =
                        this.buildConstructorMethod(childCtx as TypeScriptParser.ConstructorDeclarationContext)
                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }
                "PropertyDeclarationExpressionContext" -> {
                    val ctx = childCtx as TypeScriptParser.PropertyDeclarationExpressionContext
                    val codeField = CodeField(TypeValue = ctx.propertyName().text)

                    val modifier = ctx.propertyMemberBase().text
                    if (modifier != "") {
                        codeField.Modifiers += modifier
                    }
                    if (ctx.typeAnnotation() != null) {
                        codeField.TypeType = this.buildTypeAnnotation(ctx.typeAnnotation())!!
                    }

                    currentNode.Fields += codeField
                }
                "MethodDeclarationExpressionContext" -> {
                    val ctx = childCtx as TypeScriptParser.MethodDeclarationExpressionContext
                    val codeFunction = CodeFunction(
                        Name = ctx.propertyName().text, Position = buildPosition(ctx)
                    )
                    val callSignCtx = ctx.callSignature()

                    if (callSignCtx.typeAnnotation() != null) {
                        codeFunction.ReturnType = buildTypeAnnotation(callSignCtx.typeAnnotation())!!
                    }

                    codeFunction.FilePath = filePath
                    currentNode.Functions += codeFunction
                }
                else -> {
                    println("handleClassBodyElements -> childElementType : $childElementType")
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
        var implements: Array<String> = arrayOf()
        for (typeRefCtx in typeList!!.typeReference()) {
            implements += typeRefCtx.typeName().text
        }
        return implements
    }

    override fun exitClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        hasEnterClass = false
        classNodeStack.pop()
    }

    override fun enterInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        val currentType = DataStructType.INTERFACE

        currentNode = CodeDataStruct(
            NodeName = nodeName, Type = currentType, Package = this.namespaceName, FilePath = codeContainer.FullName
        )

        if (ctx.interfaceExtendsClause() != null) {
            val elements = buildImplements(ctx.interfaceExtendsClause().classOrInterfaceTypeList())
            currentNode.Extend = elements[0]
        }

        val objectTypeCtx = ctx.objectType()
        if (objectTypeCtx.typeBody() != null) {
            this.buildInterfaceBody(objectTypeCtx.typeBody().typeMemberList())
        }

        nodeMap[nodeName] = currentNode
    }

    override fun exitInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        currentNode = CodeDataStruct()
    }

    private fun buildInterfaceBody(typeMemberList: TypeScriptParser.TypeMemberListContext?) {
        for (memberContext in typeMemberList!!.typeMember()) {
            val memberChild = memberContext.getChild(0)
            when (memberChild::class.java.simpleName) {
                "PropertySignatureContext" -> {
                    buildInterfacePropertySignature(memberChild as TypeScriptParser.PropertySignatureContext)
                }
                "MethodSignatureContext" -> {
                    val methodSignCtx = memberChild as TypeScriptParser.MethodSignatureContext
                    val func = CodeFunction(
                        Name = methodSignCtx.propertyName().text
                    )

                    fillMethodFromCallSignature(methodSignCtx.callSignature(), func)

                    func.FilePath = filePath
                    currentNode.Functions += func
                }
                else -> {
                    println("enterInterfaceDeclaration -> buildInterfaceBody")
                }
            }
        }
    }

    private fun buildInterfacePropertySignature(signCtx: TypeScriptParser.PropertySignatureContext) {
        val typeType = buildTypeAnnotation(signCtx.typeAnnotation())!!
        val typeValue = signCtx.propertyName().text

        val isArrowFunc = signCtx.type_() != null
        if (isArrowFunc) {
            val codeFunction = CodeFunction(
                Name = typeValue
            )
            val param = CodeProperty(
                TypeValue = "any", TypeType = typeType
            )

            val returnType = CodeProperty(
                TypeType = signCtx.type_().text, TypeValue = ""
            )

            codeFunction.Parameters += param
            codeFunction.MultipleReturns += returnType

            codeFunction.FilePath = filePath
            currentNode.Functions += codeFunction
        } else {
            val codeField = CodeField(TypeType = typeType, TypeValue = typeValue)
            currentNode.Fields += codeField
        }
    }

    override fun enterFromBlock(ctx: TypeScriptParser.FromBlockContext?) {
        val imp = unQuote(ctx!!.StringLiteral().text)
        val codeImport = CodeImport(
            Source = imp
        )

        if (ctx.moduleItems() != null) {
            for (nameContext in ctx.moduleItems().aliasName()) {
                codeImport.UsageName += nameContext.identifierName()[0].text
                if (nameContext.As() != null) {
                    codeImport.AsName += nameContext.identifierName()[1].text
                }
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
            codeImport.UsageName += ctx.Identifier().text
        }

        codeContainer.Imports += codeImport
    }

    override fun enterImportAll(ctx: TypeScriptParser.ImportAllContext?) {
        val source = unQuote(ctx!!.StringLiteral().text)
        val imp = CodeImport(
            Source = source
        )

        codeContainer.Imports += imp
    }

    // see also in arrow function declaration
    override fun enterFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        val funcName = ctx!!.Identifier().text
        val func = CodeFunction(FilePath = filePath)
        func.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature(), func)
        func.Position = this.buildPosition(ctx)

        isCallbackOrAnonymousFunction = false
        processingNewArrowFunc(func)

        if (ctx.functionBody() == null) {
            return
        }

        if (ctx.functionBody().sourceElements() != null) {
            for (context in ctx.functionBody().sourceElements().sourceElement()) {
                parseStatement(context)
            }
        }
    }

    // todo: align logic to arrow functions
    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        handleFuncDeclExit()
    }

    // see also in function declaration
    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        isCallbackOrAnonymousFunction = false

        when (val parentName = statementParent::class.java.simpleName) {
            // for: const blabla = () => { }
            "VariableDeclarationContext" -> {
                val func = CodeFunction(FilePath = filePath)

                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                func.Name = varDeclCtx.identifierOrKeyWord().text
                func.Parameters = this.buildArrowFunctionParameters(ctx.arrowFunctionParameters())
                func.Position = this.buildPosition(ctx)

                if (ctx.typeAnnotation() != null) {
                    func.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }

                isCallbackOrAnonymousFunction = false
                processingNewArrowFunc(func)
            }
            "ArgumentContext" -> {
                // todo: add arg ctx
                val call = CodeCall(FunctionName = currentExprIdent, Type = CallType.ARROW)
                isCallbackOrAnonymousFunction = true
                currentFunc.FunctionCalls += call
            }
            // such as: `(e) => e.stopPropagation()`
            "ExpressionSequenceContext" -> {
                val func = CodeFunction(FilePath = filePath)
                func.Name = ""
                isCallbackOrAnonymousFunction = true
                processingNewArrowFunc(func)
            }
            else -> {
                val text = statementParent.text
                println("enterArrowFunctionDeclaration -> $parentName, $text")
            }
        }

        if (ctx.arrowFunctionBody() == null) {
            return
        }

        if (ctx.arrowFunctionBody() != null) {
            ctx.arrowFunctionBody()?.functionBody()?.sourceElements()?.sourceElement()?.forEach {
                parseStatement(it)
            }
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
        if (arrowFuncCtx.identifierOrKeyWord() != null) {
            val parameter = CodeProperty(
                TypeValue = arrowFuncCtx.identifierOrKeyWord().text, TypeType = "any"
            )
            parameters += parameter
        }

        return parameters
    }

    override fun enterFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        when (val parentName = statementParent::class.java.simpleName) {
            "VariableDeclarationContext" -> {
                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                currentFunc.Name = varDeclCtx.identifierOrKeyWord().text
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
            "IdentifierExpressionContext" -> {
                currentFunc.Position = this.buildPosition(ctx)
                currentFunc.FilePath = filePath
                defaultNode.Functions += currentFunc
            }
            else -> {
                println("enterFunctionExpressionDeclaration -> $parentName, ${statementParent.text} ")
            }
        }
    }

    private fun parseStatement(context: TypeScriptParser.SourceElementContext) {
        val stmtChild = context.statement().getChild(0)
        when (val childType = stmtChild::class.java.simpleName) {
            "ReturnStatementContext" -> {
                val returnStmt = stmtChild as TypeScriptParser.ReturnStatementContext
                hasHtmlElement = false;
                if (returnStmt.expressionSequence() != null) {
                    for (singleExpressionContext in returnStmt.expressionSequence().singleExpression()) {
                        parseSingleExpression(singleExpressionContext)
                    }
                }

                if (hasHtmlElement) {
                    currentFunc.IsReturnHtml = true
                }
            }
            else -> {
                println("parseStmt childType -> :$childType")
            }
        }
    }

    private fun singleExpToText(ctx: TypeScriptParser.SingleExpressionContext): String {
        val childType = ctx::class.java.simpleName
        var text = ctx.text
        when (childType) {
            "LiteralExpressionContext" -> {
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

    private fun parseSingleExpression(ctx: TypeScriptParser.SingleExpressionContext?) {
        when (ctx!!::class.java.simpleName) {
            "IdentifierExpressionContext" -> {
                val context = ctx as IdentifierExpressionContext
                currentExprIdent = context.identifierName().text

                if (context.singleExpression() != null) {
                    parseSingleExpression(context.singleExpression())
                }
            }
            "GenericTypesContext" -> {
                val context = ctx as TypeScriptParser.GenericTypesContext
                parseExpressionSequence(context.expressionSequence())
            }
            "ArgumentsExpressionContext" -> {
                argumentsExpressionToCall(ctx as TypeScriptParser.ArgumentsExpressionContext)
            }
            "ParenthesizedExpressionContext" -> {
                val parameters = parseParenthesizedExpression(ctx)
                currentFunc.FunctionCalls += CodeCall("", CallType.FUNCTION, "", currentExprIdent, parameters)
            }
            else -> {
                println("todo -> need support type: ${ctx::class.java.simpleName} ==== ${ctx.text}")
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
                println("argText with )." + Json.encodeToString(args))
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
            when (val childName = it::class.java.simpleName) {
                "MemberDotExpressionContext" -> {
                    val memberDot = it as TypeScriptParser.MemberDotExpressionContext
                    when (val subName = memberDot.singleExpression()::class.java.simpleName) {
                        "ParenthesizedExpressionContext" -> {
                            params += parseParenthesizedExpression(memberDot.singleExpression())
                        }
                        "ArgumentsExpressionContext" -> {
                            // request.get('/api/v1/xxx?id=1').then(function(response){console.log(response)}).catch()
                            val args = memberDot.singleExpression() as TypeScriptParser.ArgumentsExpressionContext
                            parseArguments(args)
                        }
                        "IdentifierExpressionContext" -> {
                            val ident = memberDot.singleExpression() as IdentifierExpressionContext
                            currentExprIdent = ident.identifierName().text
                        }
                        else -> {
                            println("MemberDotExpressionContext: -> $subName")
                        }
                    }

                    currentExprIdent += "->${memberDot.identifierName().text}"
                }
                "ParenthesizedExpressionContext" -> {
                    val prop = parseParenthesizedExpression(it as ParenthesizedExpressionContext)
                    params += prop
                }
                "ArgumentsContext" -> {

                }
                else -> {
                    println("singleExpression -> ArgumentsExpressionContext -> $childName")
                }
            }
        }


        return params
    }

    private fun parseParenthesizedExpression(ctx: TypeScriptParser.SingleExpressionContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        val context = ctx as ParenthesizedExpressionContext
        for (subSingle in context.expressionSequence().singleExpression()) {
            var parameter = CodeProperty(TypeValue = "", TypeType = "object")

            when (val simpleName = subSingle::class.java.simpleName) {
                "ObjectLiteralExpressionContext" -> {
                    val obj = subSingle as TypeScriptParser.ObjectLiteralExpressionContext
                    val objectLiteral = parseObjectLiteral(obj.objectLiteral())
                    parameter =
                        CodeProperty(TypeValue = subSingle.text, TypeType = "object", ObjectValue = objectLiteral)
                }
                "HtmlElementExpressionContext" -> {
                    hasHtmlElement = true
//                    println("todo -> HtmlElementExpressionContext: $simpleName, text: ${subSingle.text}")
                }
                "ArgumentsExpressionContext" -> {
                    parseArguments(subSingle as TypeScriptParser.ArgumentsExpressionContext)
                }
                "IdentifierExpressionContext" -> {
                    val identExpr = subSingle as IdentifierExpressionContext
                    parameter = CodeProperty(TypeValue = identExpr.text, TypeType = "parameter")
                }
                "ArrayLiteralExpressionContext" -> {
                    parameter = CodeProperty(TypeValue = "[]", TypeType = "parameter")
                }
                "LiteralExpressionContext" -> {
                    parameter = CodeProperty(TypeValue = singleExpToText(subSingle), TypeType = "string")
                }
                else -> {
                    println("todo -> ParenthesizedExpressionContext: $simpleName, text: ${subSingle.text}")
                }
            }

            parameters += parameter
        }

        return parameters
    }

    private fun parseObjectLiteral(objectLiteral: TypeScriptParser.ObjectLiteralContext): Array<CodeProperty> {
        var root: Array<CodeProperty> = arrayOf()
        objectLiteral.propertyAssignment().forEach { property ->
            when (val propName = property::class.java.simpleName) {
                "PropertyExpressionAssignmentContext" -> {
                    val assignCtx = property as TypeScriptParser.PropertyExpressionAssignmentContext
                    val text = singleExpToText(assignCtx.singleExpression())
                    val value = CodeProperty(TypeType = "value", TypeValue = text)
                    val propText = assignCtx.propertyName().text

                    root += CodeProperty(TypeType = "key", TypeValue = propText, ObjectValue = arrayOf(value))
                }
                "PropertyShorthandContext" -> {
                    val short = (property as TypeScriptParser.PropertyShorthandContext).text
                    val value = CodeProperty(TypeType = "value", TypeValue = short)
                    val prop = CodeProperty(TypeType = "key", TypeValue = short, ObjectValue = arrayOf(value))
                    root += prop
                }
                else -> {
                    println(propName)
                }
            }
        }

        return root
    }

    private fun parseExpressionSequence(ctx: TypeScriptParser.ExpressionSequenceContext) {
        for (singleExpressionContext in ctx.singleExpression()) {
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

        if (callSignCtx.typeAnnotation() != null) {
            val returnType = buildReturnTypeByType(callSignCtx.typeAnnotation())
            currentFunc.MultipleReturns += returnType
        }
    }

    private fun buildReturnTypeByType(typeAnnotationContext: TypeScriptParser.TypeAnnotationContext?): CodeProperty {
        val typeAnnotation = buildTypeAnnotation(typeAnnotationContext)

        return CodeProperty(
            TypeType = typeAnnotation!!, TypeValue = ""
        )
    }

    override fun enterExpressionStatement(ctx: TypeScriptParser.ExpressionStatementContext?) {
        if (ctx!!.expressionSequence() == null) {
            return
        }

        for (singleExprCtx in ctx.expressionSequence().singleExpression()) {
            when (val singleCtxType = singleExprCtx::class.java.simpleName) {
                "ArgumentsExpressionContext" -> {
                    val codeCall = CodeCall()

                    val argsCtx = singleExprCtx as TypeScriptParser.ArgumentsExpressionContext
                    codeCall.Parameters = this.buildArguments(argsCtx.arguments())
                    codeCall.FunctionName = buildFunctionName(argsCtx)
                    codeCall.NodeName = wrapTargetType(argsCtx)
                    codeCall.Position = this.buildPosition(ctx)

                    currentFunc.FunctionCalls += codeCall
                }
                "IdentifierExpressionContext" -> {
                    println("enterExpressionStatement -> IdentifierExpressionContext: ${singleExprCtx.text}")
                }
                else -> {
                    println("enterExpressionStatement : $singleCtxType")
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

    override fun enterVariableDeclaration(ctx: TypeScriptParser.VariableDeclarationContext?) {
        if (ctx == null) {
            return
        }
        if (ctx.children == null) {
            return
        }

        val varName = ctx.getChild(0).text
        if (ctx.singleExpression().size == 1 && ctx.typeParameters() == null) {
            val singleExprCtx = ctx.singleExpression()[0]
            when (val singleCtxType = singleExprCtx::class.java.simpleName) {
                "NewExpressionContext" -> {
                    val newExprCtx = singleExprCtx as TypeScriptParser.NewExpressionContext
                    val newSingleExpr = newExprCtx.singleExpression()
                    when (newSingleExpr::class.java.simpleName) {
                        "IdentifierExpressionContext" -> {
                            val identExprCtx = newSingleExpr as IdentifierExpressionContext
                            val varType = identExprCtx.identifierName().text

                            localVars[varName] = varType
                        }
                    }
                }
                "IdentifierExpressionContext" -> {
                    val identExpr = singleExprCtx as IdentifierExpressionContext
                    when (val identExprText = identExpr.identifierName().text) {
                        "await" -> {
                            parseSingleExpression(identExpr.singleExpression())
                        }
                        "Number" -> {
                            parseSingleExpression(identExpr.singleExpression())
                        }
                        else -> {
                            println("IdentifierExpressionContext -> others variable decl: $identExprText ==== ${singleExprCtx.text}")
                        }
                    }
                }
                "AwaitExpressionContext" -> {
                    val identExpr = singleExprCtx as TypeScriptParser.AwaitExpressionContext
                    parseSingleExpression(identExpr.singleExpression())
                }
                "ArrowFunctionExpressionContext" -> {
                    // will recall by ArrowFunctionDeclaration
                }
                "ArgumentsExpressionContext" -> {
                    argumentsExpressionToCall(singleExprCtx as TypeScriptParser.ArgumentsExpressionContext, varName)
                }
                "ParenthesizedExpressionContext" -> {
                    parseParenthesizedExpression(singleExprCtx as ParenthesizedExpressionContext)
                }
                else -> {
                    println("enterVariableDeclaration : $singleCtxType === ${ctx.text}")
                }
            }
        }
    }

    private fun buildArguments(arguments: TypeScriptParser.ArgumentsContext?): Array<CodeProperty> {
        var args: Array<CodeProperty> = arrayOf()
        val value = arguments!!.getChild(1).text
        if (value == ")") {
            return args
        }

        arguments.argumentList().argument().forEach {
            parseSingleExpression(it.singleExpression())
            val typeValue: String = when (it.singleExpression().javaClass.simpleName) {
                "LiteralExpressionContext" -> {
                    val literalExpr = (it.singleExpression() as TypeScriptParser.LiteralExpressionContext).literal()
                    if (literalExpr.templateStringLiteral() != null) {
                        literalExpr.templateStringLiteral().text.drop(1).dropLast(1)
                    } else {
                        it.text
                    }
                }
                else -> {
                    it.text;
                }
            }

            val arg = CodeProperty(TypeValue = typeValue, TypeType = "")
            args += arg
        }

        return args
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
            codeContainer.DataStructures += defaultNode
        } else if (fieldOnly) {
            defaultNode.NodeName = "default"
            defaultNode.FilePath = codeContainer.FullName
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }

//    override fun enterEveryRule(ctx: ParserRuleContext?) {
//        println(ctx!!.javaClass.simpleName)
//    }
}
