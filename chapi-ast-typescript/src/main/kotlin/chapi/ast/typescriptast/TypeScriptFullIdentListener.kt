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
    private var currentFunction = CodeFunction(IsConstructor = false)
    private var currentType: String = ""

    private var classNodeStack = Stack<CodeDataStruct>()
    private var methodMap = mutableMapOf<String, CodeFunction>()

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        val nodeName = ctx!!.Identifier().text
        currentNode = CodeDataStruct(
            Type = "Interface",
            NodeName = nodeName
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
                codeField.TypeType = this.getTypeType(ctx.typeAnnotation())!!
            }

            currentNode.Fields += codeField
        }

        val callSignaturePos = 3
        if (ctx.childCount >= callSignaturePos) {
            val callSignCtxPos = 2
            when (ctx.getChild(callSignCtxPos)::class.java.simpleName) {
                "CallSignatureContext" -> {
                    val codeFunction = buildMemberMethod(ctx)
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
            codeFunction.Parameters += buildParameters(ctx)
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
            NodeName = nodeName
        )

        if (ctx.interfaceExtendsClause() != null) {
            val elements = buildImplements(ctx.interfaceExtendsClause().classOrInterfaceTypeList())
            currentNode.Extend = elements[0]
        }

        nodeMap[nodeName] = currentNode
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

    fun getNodeInfo(): CodeFile {
        for (entry in nodeMap) {
            codeFile.DataStructures += entry.value
        }
        return codeFile
    }
}
