package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.*

open class JavaBasicIdentListener(fileName: String) : JavaAstListener() {
    private var isOverrideMethod: Boolean = false
    private var hasEnterClass: Boolean = false
    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)
    private var classNodes: List<CodeDataStruct> = listOf()
    private var imports: List<CodeImport> = listOf()

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)

        if (ctx.STATIC() != null) {
            val sourceSplit = codeImport.Source.split(".")
            codeImport.UsageName = listOf(sourceSplit.last())

            val split = sourceSplit.dropLast(1)
            codeImport.Source = split.joinToString(".")
        }

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
        currentNode.NodeName = ctx?.identifier()?.text ?: ""
        currentNode.Extend = ctx?.typeType()?.text ?: ""

        if (ctx?.IMPLEMENTS() != null) {
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

    open fun buildImplements(ctx: JavaParser.ClassDeclarationContext): List<String> {
        return ctx.typeList()
            .map { it.text }
            .filter { imports.containsType(it) }
    }

    private fun <T> List<T>.containsType(it: String?): Boolean {
        return imports.filter { imp -> imp.Source.endsWith(".$it") }.toTypedArray().isNotEmpty()
    }

    open fun buildImplements(ctx: JavaParser.EnumDeclarationContext): List<String> {
        val typeText = ctx.typeList().text
        return when {
            imports.containsType(typeText) -> listOf(typeText)
            else -> listOf()
        }
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

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = buildPosition(ctx),
            IsConstructor = false,
            BodyHash = ctx.methodBody().text.hashCode()
        )

        when (val mayModifierCtx = ctx.parent.parent.getChild(0)) {
            is JavaParser.ModifierContext -> {
                codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx)
            }
        }

        currentFunction = codeFunction

        when (val child = ctx.parent.parent) {
            is JavaParser.ClassBodyDeclarationContext -> {
                currentFunction.Modifiers = child.modifier()
                    .map { it.text }
                    .filter { "@" in it }
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

        val codeFunction = CodeFunction(
            Name = name,
            ReturnType = typeType,
            Position = buildPosition(ctx),
            IsConstructor = false
        )

        when (val mayModifierCtx = ctx.parent.parent.getChild(0)) {
            is JavaParser.ModifierContext -> {
                codeFunction.Annotations = this.buildAnnotationForMethod(mayModifierCtx)
            }
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

        when (val modifier = ctx.parent.parent.getChild(0)) {
            is JavaParser.ModifierContext -> {
                codeFunction.Annotations = this.buildAnnotationForMethod(modifier)
            }
        }

        currentFunction = codeFunction
    }

    override fun exitConstructorDeclaration(ctx: JavaParser.ConstructorDeclarationContext?) {
        currentNode.Functions += currentFunction
    }

    override fun enterExpression(ctx: JavaParser.ExpressionContext?) {
        when (val parent = ctx!!.parent) {
            is JavaParser.StatementContext -> {
                val firstChild = parent.getChild(0).text

                if (firstChild.lowercase() == "return") {
                    val isReturnNull = ctx.text == "null"
                    currentFunction.addExtension("IsReturnNull", isReturnNull.toString())
                }
            }
        }
    }

    override fun enterEnumDeclaration(ctx: JavaParser.EnumDeclarationContext?) {
        hasEnterClass = true
        currentNode.Type = DataStructType.ENUM
        currentNode.Package = codeContainer.PackageName

        currentNode.NodeName = ctx?.identifier()?.text ?: ""

        if (ctx?.IMPLEMENTS() != null) {
            currentNode.Implements = buildImplements(ctx)
        }

        currentFunction = CodeFunction()
    }

    override fun exitEnumBodyDeclarations(ctx: JavaParser.EnumBodyDeclarationsContext?) {
        hasEnterClass = false
        if (currentNode.NodeName != "") {
            classNodes += currentNode
        }
        currentNode = CodeDataStruct()
    }

    fun getNodeInfo(): CodeContainer {
        codeContainer.DataStructures = classNodes
        return codeContainer
    }
}

