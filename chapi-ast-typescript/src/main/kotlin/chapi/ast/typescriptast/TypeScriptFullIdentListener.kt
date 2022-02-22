package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParser.IdentifierExpressionContext
import chapi.domain.core.*
import chapi.infra.Stack

class TypeScriptFullIdentListener(private var node: TSIdentify) : TypeScriptAstListener() {
    private var hasAnnotation: Boolean = false;
    private var hasEnterClass = false
    private var currentExprIdent: String? = ""
    private var localVars = mutableMapOf<String, String>()

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = node.fileName)

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var namespaceName: String = ""
    private var currentAnnotations = arrayOf<CodeAnnotation>()

    private var classNodeStack = Stack<CodeDataStruct>()

    override fun enterNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ctx!!.namespaceName().text
    }

    override fun exitNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ""
    }

    override fun enterDecoratorList(ctx: TypeScriptParser.DecoratorListContext?) {
        if(!hasEnterClass) {
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
                    currentNode.Functions += codeFunction
                }
                "PropertyDeclarationExpressionContext" -> {
                    val ctx = childCtx as TypeScriptParser.PropertyDeclarationExpressionContext
                    val codeField = CodeField(
                        TypeValue = ctx.propertyName().text
                    )

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
                        Name = ctx.propertyName().text,
                        Position = buildPosition(ctx)
                    )
                    val callSignCtx = ctx.callSignature()

                    if (callSignCtx.typeAnnotation() != null) {
                        codeFunction.ReturnType = buildTypeAnnotation(callSignCtx.typeAnnotation())!!
                    }

                    currentNode.Functions += codeFunction
                }
                else -> {
                    println("handleClassBodyElements -> childElementType : $childElementType")
                }
            }
        }
    }

    private fun buildPropertyMember(originCtx: TypeScriptParser.PropertyMemberDeclarationContext) {
        val childType = originCtx::class.java.simpleName.toString()

        print(childType)
        when (childType) {

            else -> {
                println("handleClassBody -> buildPropertyMember")
            }
        }
    }

    private fun buildConstructorMethod(ctx: TypeScriptParser.ConstructorDeclarationContext): CodeFunction {
        val codeFunction = CodeFunction(
            Name = "constructor",
            Position = this.buildPosition(ctx)
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
            NodeName = nodeName,
            Type = currentType,
            Package = this.namespaceName,
            FilePath = codeContainer.FullName
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

    fun buildInterfaceBody(typeMemberList: TypeScriptParser.TypeMemberListContext?) {
        for (memberContext in typeMemberList!!.typeMember()) {
            val memberChild = memberContext.getChild(0)
            val childType = memberChild::class.java.simpleName

            when (childType) {
                "PropertySignaturContext" -> {
                    buildInterfacePropertySignature(memberChild as TypeScriptParser.PropertySignaturContext)
                }
                "MethodSignatureContext" -> {
                    val methodSignCtx = memberChild as TypeScriptParser.MethodSignatureContext
                    currentFunction = CodeFunction(
                        Name = methodSignCtx.propertyName().text
                    )

                    fillMethodFromCallSignature(methodSignCtx.callSignature())

                    currentNode.Functions += currentFunction
                    currentFunction = CodeFunction()
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
                TypeValue = "any",
                TypeType = typeType
            )

            val returnType = CodeProperty(
                TypeType = signCtx.type_().text,
                TypeValue = ""
            )

            codeFunction.Parameters += param
            codeFunction.MultipleReturns += returnType

            currentNode.Functions += codeFunction
        } else {
            val codeField = CodeField(
                TypeType = typeType,
                TypeValue = typeValue
            )

            currentNode.Fields += codeField
        }
    }


    override fun enterFromBlock(ctx: TypeScriptParser.FromBlockContext?) {
        val imp = removeQuote(ctx!!.StringLiteral().text)
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

    private fun removeQuote(text: String): String = text.replace("[\"']".toRegex(), "")

    override fun enterImportAliasDeclaration(ctx: TypeScriptParser.ImportAliasDeclarationContext?) {
        val imp = removeQuote(ctx!!.StringLiteral().text)
        val codeImport = CodeImport(
            Source = imp
        )

        if (ctx.Require() != null) {
            codeImport.UsageName += ctx.Identifier().text
        }

        codeContainer.Imports += codeImport
    }

    override fun enterImportAll(ctx: TypeScriptParser.ImportAllContext?) {
        val source = removeQuote(ctx!!.StringLiteral().text)
        val imp = CodeImport(
            Source = source
        )

        codeContainer.Imports += imp
    }

    override fun enterFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        val funcName = ctx!!.Identifier().text
        currentFunction.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature())
        currentFunction.Position = this.buildPosition(ctx)

        for (context in ctx.functionBody().sourceElements().sourceElement()) {
            parseStatement(context)
        }

        defaultNode.Functions += currentFunction
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
        val childType = ctx!!::class.java.simpleName
        when (childType) {
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
                val argument = ctx as TypeScriptParser.ArgumentsExpressionContext
                currentExprIdent = argument.singleExpression().text

                currentFunction.FunctionCalls += CodeCall(
                    "",
                    "",
                    "",
                    currentExprIdent.toString(),
                    arrayOf()
                )
            }
            "ParenthesizedExpressionContext" -> {
                val context = ctx as TypeScriptParser.ParenthesizedExpressionContext
                for (subSingle in context.expressionSequence().singleExpression()) {
                    val simpleName = subSingle::class.java.simpleName
                    when (simpleName) {
                        "ObjectLiteralExpressionContext" -> {
                            val obj = subSingle as TypeScriptParser.ObjectLiteralExpressionContext;
                            val objectLiteral = parseObjectLiteral(obj.objectLiteral())
                            val parameter =
                                CodeProperty(arrayOf(), "", subSingle.text, "object", ObjectValue = objectLiteral)

                            // append code call
                            currentFunction.FunctionCalls += CodeCall(
                                "",
                                "",
                                "",
                                currentExprIdent.toString(),
                                arrayOf(parameter)
                            )
                        }
                        else -> {
                            println("todo -> ParenthesizedExpressionContext: $simpleName")
                        }
                    }
                }
            }
            else -> {
                println("todo: need support type: $childType")
            }
        }
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

                    val prop = CodeProperty(
                        TypeType = "key",
                        TypeValue = assignCtx.propertyName().text,
                        ObjectValue = arrayOf(value)
                    )
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

    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        currentFunction = CodeFunction()
    }

    override fun enterFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        val parentName = statementParent::class.java.simpleName
        when (parentName) {
            "VariableDeclarationContext" -> {
                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                currentFunction.Name = varDeclCtx.identifierOrKeyWord().text
                if (ctx.formalParameterList() != null) {
                    currentFunction.Parameters = this.buildParameters(ctx.formalParameterList())
                }

                if (ctx.typeAnnotation() != null) {
                    currentFunction.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }
            }
            else -> {
                println("enterFunctionExpressionDeclaration.$parentName -> ")
            }
        }

        currentFunction.Position = this.buildPosition(ctx)
        defaultNode.Functions += currentFunction
    }

    override fun exitFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        currentFunction = CodeFunction()
    }

    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        when (statementParent::class.java.simpleName) {
            "VariableDeclarationContext" -> {
                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                currentFunction.Name = varDeclCtx.identifierOrKeyWord().text
                this.buildArrowFunctionParameters(ctx.arrowFunctionParameters())
                currentFunction.Parameters = this.buildArrowFunctionParameters(ctx.arrowFunctionParameters())

                if (ctx.typeAnnotation() != null) {
                    currentFunction.MultipleReturns += buildReturnTypeByType(ctx.typeAnnotation())
                }
            }
            else -> {
                println("enterFunctionExpressionDeclaration -> ")
            }
        }
        currentFunction.Position = this.buildPosition(ctx)
        defaultNode.Functions += currentFunction
    }

    private fun buildArrowFunctionParameters(arrowFuncCtx: TypeScriptParser.ArrowFunctionParametersContext?): Array<CodeProperty> {
        if (arrowFuncCtx!!.formalParameterList() != null) {
            return this.buildParameters(arrowFuncCtx.formalParameterList())
        }
        var parameters: Array<CodeProperty> = arrayOf()
        if (arrowFuncCtx.Identifier() != null) {
            val parameter = CodeProperty(
                TypeValue = arrowFuncCtx.Identifier().text,
                TypeType = "any"
            )
            parameters += parameter
        }
        return parameters
    }

    override fun exitArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        currentFunction = CodeFunction()
    }

    private fun fillMethodFromCallSignature(
        callSignCtx: TypeScriptParser.CallSignatureContext
    ) {
        if (callSignCtx.parameterList() != null) {
            val parameters = buildMethodParameters(callSignCtx.parameterList())
            currentFunction.Parameters = parameters
        }

        if (callSignCtx.typeAnnotation() != null) {
            val returnType = buildReturnTypeByType(callSignCtx.typeAnnotation())

            currentFunction.MultipleReturns += returnType
        }
    }

    private fun buildReturnTypeByType(
        typeAnnotationContext: TypeScriptParser.TypeAnnotationContext?
    ): CodeProperty {
        val typeAnnotation = buildTypeAnnotation(typeAnnotationContext)

        val returnType = CodeProperty(
            TypeType = typeAnnotation!!,
            TypeValue = ""
        )
        return returnType
    }

    override fun enterExpressionStatement(ctx: TypeScriptParser.ExpressionStatementContext?) {
        println("enterExpressionStatement : " + ctx!!.text)
        if(ctx.expressionSequence() == null) {
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

                    currentFunction.FunctionCalls += codeCall
                }
                else -> {
                    println("enterExpressionStatement :$singleCtxType")
                }
            }

        }
    }

    private fun buildFunctionName(argsCtx: TypeScriptParser.ArgumentsExpressionContext): String {
        var text = argsCtx.singleExpression().text
        if (text.contains(".")) {
            var split = text.split(".")
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
                            println("others variable decl: $identExprText")
                        }
                    }
                }
                else -> {
                    println("enterVariableDeclaration : $singleCtxType")
                }
            }
        }
    }

    private fun buildArguments(arguments: TypeScriptParser.ArgumentsContext?): Array<CodeProperty> {
        var args: Array<CodeProperty> = arrayOf()
        val value = arguments!!.getChild(1).text
        if (value == ")") {
            return args;
        }
        val arg = CodeProperty(
            TypeValue = value,
            TypeType = ""
        )
        args += arg
        return args
    }

    fun getNodeInfo(): CodeContainer {
        for (entry in nodeMap) {
            codeContainer.DataStructures += entry.value
        }
        if (defaultNode.Functions.isNotEmpty()) {
            defaultNode.NodeName = "default"
            defaultNode.FilePath = codeContainer.FullName
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
