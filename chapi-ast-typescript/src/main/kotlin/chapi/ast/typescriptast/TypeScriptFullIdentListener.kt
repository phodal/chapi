package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import domain.core.*
import domain.infra.Stack

class TypeScriptFullIdentListener(private var node: TSIdentify) : TypeScriptAstListener() {
    private var dataStructQueue = arrayOf<CodeDataStruct>()
    private var hasEnterClass = false

    private var nodeMap = mutableMapOf<String, CodeDataStruct>()
    private var codeFile: CodeFile = CodeFile(FullName = node.fileName)

    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: String = ""
    private var namespaceName : String = ""

    private var classNodeStack = Stack<CodeDataStruct>()
    private var methodMap = mutableMapOf<String, CodeFunction>()

    override fun enterNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ctx!!.namespaceName().text
    }

    override fun exitNamespaceDeclaration(ctx: TypeScriptParser.NamespaceDeclarationContext?) {
        this.namespaceName = ""
    }

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        currentNode = CodeDataStruct(
            Type = "Class",
            NodeName = nodeName,
            Package = this.namespaceName
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
                "PropertyMemberDeclarationContext" -> {
                    this.buildPropertyMember(childCtx as TypeScriptParser.PropertyMemberDeclarationContext)
                }
                else -> {
                    println("handleClassBodyElements -> childElementType : $childElementType")
                }
            }
        }
    }

    private fun buildPropertyMember(ctx: TypeScriptParser.PropertyMemberDeclarationContext) {
        val isField = ctx.propertyName() != null
        if (isField) {
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

        val callSignaturePos = 3
        if (ctx.childCount >= callSignaturePos) {
            val callSignCtxPos = 2
            when (ctx.getChild(callSignCtxPos)::class.java.simpleName) {
                "CallSignatureContext" -> {
                    val codeFunction = buildMemberMethod(ctx)
                    val callSignCtx = ctx.callSignature()

                    if (callSignCtx.typeAnnotation() != null) {
                        codeFunction.ReturnType = buildTypeAnnotation(callSignCtx.typeAnnotation())!!
                    }

                    currentNode.Functions += codeFunction
                }
            }
        }
    }

    private fun buildMemberMethod(ctx: TypeScriptParser.PropertyMemberDeclarationContext): CodeFunction {
        val codeFunction = CodeFunction(
            Name = ctx.propertyName().text,
            Position = this.buildPosition(ctx)
        )

        return codeFunction
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
        classNodeStack.pop()
    }

    override fun enterInterfaceDeclaration(ctx: TypeScriptParser.InterfaceDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        val currentType = "Interface"

        currentNode = CodeDataStruct(
            Type = currentType,
            NodeName = nodeName,
            Package = this.namespaceName
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

        codeFile.Imports += codeImport
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

        codeFile.Imports += codeImport
    }

    override fun enterImportAll(ctx: TypeScriptParser.ImportAllContext?) {
        val source = removeQuote(ctx!!.StringLiteral().text)
        val imp = CodeImport(
            Source = source
        )

        codeFile.Imports += imp
    }

    override fun enterFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        val funcName = ctx!!.Identifier().text
        currentFunction.Name = funcName

        fillMethodFromCallSignature(ctx.callSignature())
        currentFunction.Position = this.buildPosition(ctx)

        defaultNode.Functions += currentFunction
    }

    override fun exitFunctionDeclaration(ctx: TypeScriptParser.FunctionDeclarationContext?) {
        currentFunction = CodeFunction()
    }

    override fun enterFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        when (statementParent::class.java.simpleName) {
            "VariableDeclarationContext" -> {
                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                currentFunction.Name = varDeclCtx.Identifier().text
                if (ctx.formalParameterList() != null) {
                    currentFunction.Parameters = this.buildParameters(ctx.formalParameterList())
                }

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

    override fun exitFunctionExpressionDeclaration(ctx: TypeScriptParser.FunctionExpressionDeclarationContext?) {
        currentFunction = CodeFunction()
    }

    override fun enterArrowFunctionDeclaration(ctx: TypeScriptParser.ArrowFunctionDeclarationContext?) {
        val statementParent = ctx!!.parent.parent
        when (statementParent::class.java.simpleName) {
            "VariableDeclarationContext" -> {
                val varDeclCtx = statementParent as TypeScriptParser.VariableDeclarationContext
                currentFunction.Name = varDeclCtx.Identifier().text
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
        var parameters : Array<CodeProperty> = arrayOf()
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

    fun getNodeInfo(): CodeFile {
        for (entry in nodeMap) {
            codeFile.DataStructures += entry.value
        }
        if (defaultNode.Functions.isNotEmpty()) {
            defaultNode.NodeName = "default"
            codeFile.DataStructures += defaultNode
        }

        return codeFile
    }
}
