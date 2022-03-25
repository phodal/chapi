package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.*

open class JavaBasicIdentListener(fileName: String) : JavaAstListener() {
    private var isOverrideMethod: Boolean = false
    private var hasEnterClass: Boolean = false
    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)
    private var classNodes: Array<CodeDataStruct> = arrayOf()
    private var imports: Array<CodeImport> = arrayOf()

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeContainer.Imports += codeImport
    }

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        codeContainer.PackageName = ctx?.qualifiedName()!!.text
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        hasEnterClass = true
        currentNode.Type = DataStructType.CLASS
        currentNode.Package = codeContainer.PackageName

        if (ctx!!.identifier() != null) {
            currentNode.NodeName = ctx.identifier().text
        }

        if (ctx.EXTENDS() != null) {
            currentNode.Extend = ctx.typeType().text
        }

        if (ctx.IMPLEMENTS() != null) {
            currentNode.Implements = buildImplements(ctx)
        }

        currentFunction = CodeFunction()
    }

    override fun exitClassBodyDeclaration(ctx: JavaParser.ClassBodyDeclarationContext?) {
        hasEnterClass = false
        if (currentNode.NodeName != "") {
            classNodes += currentNode
        }
        currentNode = CodeDataStruct()
    }

    open fun buildImplements(ctx: JavaParser.ClassDeclarationContext): Array<String> {
        var implements = arrayOf<String>()
        for (_type in ctx.typeList()) {
            val typeText = _type.text
            for (imp in imports) {
                if (imp.Source.endsWith(".$typeText")) {
                    implements += typeText
                }
            }

        }
        return implements
    }

    override fun enterInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        hasEnterClass = true
        currentNode = CodeDataStruct(
            NodeName = ctx!!.identifier().text,
            Type = DataStructType.INTERFACE,
            Package = codeContainer.PackageName
        )
    }

    override fun exitInterfaceDeclaration(ctx: JavaParser.InterfaceDeclarationContext?) {
        hasEnterClass = false
        if (currentNode.NodeName != "") {
            classNodes += currentNode
        }
    }

    override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        val name = ctx!!.identifier().text
        val typeType = ctx.typeTypeOrVoid().text

        var codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = buildPosition(ctx),
            IsConstructor = false
        )

        val mayModifierCtx = ctx.parent.parent.getChild(0)
        if (mayModifierCtx::class.simpleName == "ModifierContext") {
            codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext)
        }
        currentFunction = codeFunction

        if (ctx.parent.parent::class.simpleName == "ClassBodyDeclarationContext") {
            val bodyDeclCtx = ctx.parent.parent as JavaParser.ClassBodyDeclarationContext
            for (modifierContext in bodyDeclCtx.modifier()) {
                val text = modifierContext!!.text
                if (!text.contains("@")) {
                    currentFunction.Modifiers += text
                }
            }
        }

        isOverrideMethod = false
    }

    override fun exitMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        currentNode.Functions += currentFunction
        currentFunction = CodeFunction()
    }

    override fun enterInterfaceMethodDeclaration(ctx: JavaParser.InterfaceMethodDeclarationContext?) {
        val name = ctx!!.interfaceCommonBodyDeclaration().identifier().text
        val typeType = ctx.interfaceCommonBodyDeclaration().typeTypeOrVoid().text

        val position = buildPosition(ctx)
        var codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = position,
            IsConstructor = false
        )

        val mayModifierCtx = ctx.parent.parent.getChild(0)
        if (mayModifierCtx::class.simpleName == "ModifierContext") {
            codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext)
        }

        currentFunction = codeFunction
    }

    override fun exitInterfaceMethodDeclaration(ctx: JavaParser.InterfaceMethodDeclarationContext?) {
        currentNode.Functions += currentFunction
        currentFunction = CodeFunction()
    }

    override fun enterConstructorDeclaration(ctx: JavaParser.ConstructorDeclarationContext?) {
        val codePosition = buildPosition(ctx!!)
        val codeFunction = CodeFunction(
            Name = ctx.identifier().text,
            ReturnType = "",
            Override = isOverrideMethod,
            Position = codePosition,
            IsConstructor = true
        )

        val mayModifierCtx = ctx.parent.parent.getChild(0)
        if (mayModifierCtx::class.simpleName == "ModifierContext") {
            codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx as JavaParser.ModifierContext)
        }

        currentFunction = codeFunction
    }

    override fun exitConstructorDeclaration(ctx: JavaParser.ConstructorDeclarationContext?) {
        currentNode.Functions += currentFunction
    }

    override fun enterExpression(ctx: JavaParser.ExpressionContext?) {
        if (ctx!!.parent::class.java.simpleName == "StatementContext") {
            val statementCtx = ctx.parent as JavaParser.StatementContext
            val firstChild = statementCtx.getChild(0).text

            if (firstChild.lowercase() == "return") {
                val isReturnNull = ctx.text == "null"
                currentFunction.addExtension("IsReturnNull", isReturnNull.toString())
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }
}
