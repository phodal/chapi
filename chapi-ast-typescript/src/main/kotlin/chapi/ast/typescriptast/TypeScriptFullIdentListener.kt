package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParser.IdentifierExpressionContext
import chapi.ast.antlr.TypeScriptParser.ParenthesizedExpressionContext
import chapi.domain.core.*
import chapi.infra.Stack
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TypeScriptFullIdentListener(private var node: TSIdentify) : TypeScriptAstListener() {
    private var exitArrowCount: Int = 0
    private var isCallbackOrAnonymousFunction: Boolean = false
    private lateinit var fileName: String
    private var hasAnnotation: Boolean = false;
    private var hasEnterClass = false
    private var currentExprIdent: String = ""
    private var localVars = mutableMapOf<String, String>()

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = node.fileName)

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
        if (ctx!!.variableDeclarationList() != null) {
            codeContainer.Fields = variableToFields(ctx.variableDeclarationList())
        }

        // such as: `export const baseURL = '/api'`
        // todo: add support for not only const?
        if (ctx.parent.parent.getChild(0).text == "export") {
            var modifier = "";
            ctx.children.forEach {
                when (it::class.java.simpleName) {
                    "VarModifierContext" -> {
                        modifier = it.text;
                    }
                    "VariableDeclarationListContext" -> {
                        val varDeclList = it as TypeScriptParser.VariableDeclarationListContext
                        val fields = variableToFields(varDeclList, arrayOf(modifier))

                        defaultNode.Exports += CodeExport(
                            Name = fields[0].TypeKey,
                            Type = DataStructType.Variable,
                            SourceFile = codeContainer.FullName
                        )

                        defaultNode.Fields += fields
                    }
                    else -> {
                        println(it::class.java.simpleName)
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
        varDecl.variableDeclaration().forEach {
            if (it.Assign() != null) {
                val key = it.getChild(0).text
                val lastExpr = it.singleExpression().last()
                val field = CodeField(TypeKey = key, TypeValue = lastExpr.text, Modifiers = modifiers)

                when (lastExpr::class.simpleName) {
                    "LiteralExpressionContext" -> {
                        val literal = lastExpr as TypeScriptParser.LiteralExpressionContext
                        if (literal.literal().StringLiteral() != null) {
                            field.TypeValue = unQuote(literal.text)
                            field.TypeType = "String"
                        }
                    }
                }


                fields += field
            }
        }

        return fields
    }

    override fun enterDecoratorList(ctx: TypeScriptParser.DecoratorListContext?) {
        if (!hasEnterClass) {
            hasAnnotation = true;

            for (decorator in ctx!!.decorator()) {
                val annotation = buildAnnotation(decorator)
                currentAnnotations += annotation
            }
        }
    }

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text

        hasEnterClass = true;
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
        currentNode.Annotations = currentAnnotations;
        currentAnnotations = arrayOf();
        hasAnnotation = false;

        this.handleClassBodyElements(ctx.classTail())

        classNodeStack.push(currentNode)
        nodeMap[nodeName] = currentNode
    }

    private fun handleClassBodyElements(classTailCtx: TypeScriptParser.ClassTailContext?) {
        for (clzElementCtx in classTailCtx!!.classElement()) {
            val childCtx = clzElementCtx.getChild(0)
            when (val childElementType = childCtx::class.java.simpleName) {
                "ConstructorDeclarationContext" -> {
                    val codeFunction =
                        this.buildConstructorMethod(childCtx as TypeScriptParser.ConstructorDeclarationContext)
                    codeFunction.FilePath = fileName
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

                    codeFunction.FilePath = fileName
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
        hasEnterClass = false;
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
            val childType = memberChild::class.java.simpleName

            when (childType) {
                "PropertySignaturContext" -> {
                    buildInterfacePropertySignature(memberChild as TypeScriptParser.PropertySignaturContext)
                }
                "MethodSignatureContext" -> {
                    val methodSignCtx = memberChild as TypeScriptParser.MethodSignatureContext
                    val func = CodeFunction(
                        Name = methodSignCtx.propertyName().text
                    )

                    fillMethodFromCallSignature(methodSignCtx.callSignature(), func)

                    func.FilePath = fileName
                    currentNode.Functions += func
                }
                else -> {
                    println("enterInterfaceDeclaration -> buildInterfaceBody")
                }
            }
        }
    }

    private fun buildInterfacePropertySignature(signCtx: TypeScriptParser.PropertySignaturContext) {
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

            codeFunction.FilePath = fileName
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

        if (ctx.multipleImportStatement() != null) {
            for (nameContext in ctx.multipleImportStatement().identifierName()) {
                codeImport.UsageName += nameContext.text
            }
        }

        if (ctx.Dollar() != null) {
            codeImport.UsageName += ctx.Dollar().text
        }

        if (ctx.Lodash() != null) {
            codeImport.UsageName += ctx.Lodash().text
        }

        if (ctx.As() != null) {
            codeImport.UsageName += ctx.identifierName().text
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
        currentFunc.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature(), currentFunc)
        currentFunc.Position = this.buildPosition(ctx)

        for (context in ctx.functionBody().sourceElements().sourceElement()) {
            parseStatement(context)
        }

        currentFunc.FilePath = fileName
        defaultNode.Functions += currentFunc
        funcsStackForCount.push(currentFunc)
    }

    // todo: align logic to arrow functions
    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        funcsStackForCount.pop()
        if (funcsStackForCount.count() == 0) {
            currentFunc = CodeFunction()
        }
    }

    private fun parseStatement(context: TypeScriptParser.SourceElementContext) {
        val stmtChild = context.statement().getChild(0)
        val childType = stmtChild::class.java.simpleName

        when (childType) {
            "ReturnStatementContext" -> {
                val stmt = stmtChild as TypeScriptParser.ReturnStatementContext
                for (singleExpressionContext in stmt.expressionSequence().singleExpression()) {
                    parseSingleExpression(singleExpressionContext)
                }
            }
            else -> {
                println("childType -> :$childType")
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
                if (singleStr || doubleStr) {
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
                val parameter = parseParenthesizedExpression(ctx)
                currentFunc.FunctionCalls += CodeCall("", "", "", currentExprIdent, arrayOf(parameter))
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
            currentFunc.FunctionCalls += CodeCall("", "", "", currentExprIdent, args)
        } else {
            currentExprIdent = argText

            // todo: add other case for call chain in arrow function
            if (argText.contains(").")) {
                val args = parseArguments(argument)
                println("argText with )." + Json.encodeToString(args))
            }

            // axios.get() or _.orderBy()
            currentFunc.FunctionCalls += CodeCall("", "", "", currentExprIdent, arrayOf())
        }
    }

    private fun parseArguments(argument: TypeScriptParser.ArgumentsExpressionContext): Array<CodeProperty> {
        var params = arrayOf<CodeProperty>()

        // for: `axios<Module[]>({parameter}).then`
        // create then and update parameter
        argument.children.forEach { it ->
            val child_name = it::class.java.simpleName
            when (child_name) {
                "MemberDotExpressionContext" -> {
                    val memberDot = it as TypeScriptParser.MemberDotExpressionContext
                    val subName = memberDot.singleExpression()::class.java.simpleName
                    when (subName) {
                        "ParenthesizedExpressionContext" -> {
                            params += parseParenthesizedExpression(memberDot.singleExpression())
                        }
                        "ArgumentsExpressionContext" -> {
                            // request.get('/api/v1/xxx?id=1').then(function(response){console.log(response);}).catch();
                            val args = memberDot.singleExpression() as TypeScriptParser.ArgumentsExpressionContext
                            val parseArguments = parseArguments(args)
                            println(Json.encodeToString(parseArguments))
                        }
                        "IdentifierExpressionContext" -> {
                            val ident = memberDot.singleExpression() as IdentifierExpressionContext
                            currentExprIdent = ident.identifierName().text
                        }
                        else -> {
                            println("MemberDotExpressionContext: -> $subName")
                        }
                    }

                    currentExprIdent += "->" + memberDot.identifierName().text
                }
                "ParenthesizedExpressionContext" -> {
                    val prop = parseParenthesizedExpression(it as ParenthesizedExpressionContext)
                    params += prop
                }
                "ArgumentsContext" -> {
//                    val args = it as TypeScriptParser.ArgumentsContext
//                    for (argumentContext in args.argumentList().argument()) {
//                        //
//                    }
                }
                else -> {
                    println("singleExpression -> ArgumentsExpressionContext -> $child_name")
                }
            }
        }


        return params
    }

    private fun parseParenthesizedExpression(ctx: TypeScriptParser.SingleExpressionContext?): CodeProperty {
        var parameter = CodeProperty(TypeValue = "", TypeType = "object")
        val context = ctx as ParenthesizedExpressionContext
        for (subSingle in context.expressionSequence().singleExpression()) {
            when (val simpleName = subSingle::class.java.simpleName) {
                "ObjectLiteralExpressionContext" -> {
                    val obj = subSingle as TypeScriptParser.ObjectLiteralExpressionContext;
                    val objectLiteral = parseObjectLiteral(obj.objectLiteral())
                    parameter =
                        CodeProperty(TypeValue = subSingle.text, TypeType = "object", ObjectValue = objectLiteral)
                }
                "HtmlElementExpressionContext" -> {
//                    println("todo -> HtmlElementExpressionContext: $simpleName, text: ${subSingle.text}")
                }
                else -> {
                    println("todo -> ParenthesizedExpressionContext: $simpleName, text: ${subSingle.text}")
                }
            }
        }

        return parameter
    }

    private fun parseObjectLiteral(objectLiteral: TypeScriptParser.ObjectLiteralContext): Array<CodeProperty> {
        var root: Array<CodeProperty> = arrayOf();
        objectLiteral.propertyAssignment().forEach { property ->
            val propName = property::class.java.simpleName
            when (propName) {
                "PropertyExpressionAssignmentContext" -> {
                    val assignCtx = property as TypeScriptParser.PropertyExpressionAssignmentContext
                    val text = singleExpToText(assignCtx.singleExpression())

                    val value = CodeProperty(TypeType = "value", TypeValue = text)

                    val propText = assignCtx.propertyName().text
                    val prop = CodeProperty(TypeType = "key", TypeValue = propText, ObjectValue = arrayOf(value))
                    root += prop
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

    override fun enterFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        val parentName = statementParent::class.java.simpleName
        when (parentName) {
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
                currentFunc.FilePath = fileName
                defaultNode.Functions += currentFunc
            }
            "IdentifierExpressionContext" -> {
                currentFunc.Position = this.buildPosition(ctx)
                currentFunc.FilePath = fileName
                defaultNode.Functions += currentFunc
            }
            else -> {
                println("enterFunctionExpressionDeclaration -> $parentName, ${statementParent.text} ")
            }
        }
    }

    // see also in function declaration
    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        isCallbackOrAnonymousFunction = false

        when (val parentName = statementParent::class.java.simpleName) {
            // for: const blabla = () => { }
            "VariableDeclarationContext" -> {
                val func = CodeFunction(FilePath = fileName)

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
                val call = CodeCall(FunctionName = currentExprIdent, Type = "ArrowFunction")
                isCallbackOrAnonymousFunction = true
                currentFunc.FunctionCalls += call;
            }
            // such as: `(e) => e.stopPropagation()`
            "ExpressionSequenceContext" -> {
                val func = CodeFunction(FilePath = fileName)
                func.Name = ""
                isCallbackOrAnonymousFunction = true
                processingNewArrowFunc(func)
            }
            else -> {
                val text = statementParent.text
                println("enterArrowFunctionDeclaration -> $parentName, $text")
            }
        }

        if (ctx.arrowFunctionBody().singleExpression() != null) {
            parseSingleExpression(ctx.arrowFunctionBody().singleExpression())
        }
        // todo: add sourceElements parse
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
        if (arrowFuncCtx.Identifier() != null) {
            val parameter = CodeProperty(
                TypeValue = arrowFuncCtx.Identifier().text, TypeType = "any"
            )
            parameters += parameter
        }

        return parameters
    }

    private fun fillMethodFromCallSignature(callSignCtx: TypeScriptParser.CallSignatureContext, currentFunc: CodeFunction) {
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
                    codeCall.Position = this.buildPosition(ctx);

                    currentFunc.FunctionCalls += codeCall
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

    override fun enterReturnStatement(ctx: TypeScriptParser.ReturnStatementContext?) {
//        println(ctx!!.text)
    }

    override fun enterVariableDeclaration(ctx: TypeScriptParser.VariableDeclarationContext?) {
        val varName = ctx!!.getChild(0).text
        if (ctx.singleExpression().size == 1 && ctx.typeParameters() == null) {
            val singleExprCtx = ctx.singleExpression()[0]
            val singleCtxType = singleExprCtx::class.java.simpleName
            when (singleCtxType) {
                "NewExpressionContext" -> {
                    val newExprCtx = singleExprCtx as TypeScriptParser.NewExpressionContext
                    val newSingleExpr = newExprCtx.singleExpression()
                    when (newSingleExpr::class.java.simpleName) {
                        "IdentifierExpressionContext" -> {
                            val identExprCtx = newSingleExpr as TypeScriptParser.IdentifierExpressionContext
                            val varType = identExprCtx.identifierName().text

                            localVars[varName] = varType
                        }
                    }
                }
                "IdentifierExpressionContext" -> {
                    val identExpr = singleExprCtx as IdentifierExpressionContext;
                    val identExprText = identExpr.identifierName().text
                    when (identExprText) {
                        "await" -> {
                            val singleExpression = identExpr.singleExpression()
                            parseSingleExpression(singleExpression)
                        }
                        else -> {
                            println("others variable decl: $identExprText == ${singleExprCtx.text}")
                        }
                    }
                }
                "ArrowFunctionExpressionContext" -> {
                    // will recall by ArrowFunctionDeclaration
                }
                "ArgumentsExpressionContext" -> {
                    argumentsExpressionToCall(singleExprCtx as TypeScriptParser.ArgumentsExpressionContext, varName)
                }
                else -> {
                    println("enterVariableDeclaration : $singleCtxType === ${ctx.text}")
                }
            }
        }
    }

//    override fun enterEveryRule(ctx: ParserRuleContext?) {
//        println(ctx!!.javaClass.simpleName)
//    }

    private fun buildArguments(arguments: TypeScriptParser.ArgumentsContext?): Array<CodeProperty> {
        var args: Array<CodeProperty> = arrayOf()
        val value = arguments!!.getChild(1).text
        if (value == ")") {
            return args;
        }
        val arg = CodeProperty(
            TypeValue = value, TypeType = ""
        )
        args += arg
        return args
    }

    fun getNodeInfo(): CodeContainer {
        for (entry in nodeMap) {
            codeContainer.DataStructures += entry.value
        }

        if (codeContainer.DataStructures.isEmpty()) {
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
        }

        return codeContainer
    }

    fun setPath(fileName: String) {
        this.fileName = fileName
    }
}
