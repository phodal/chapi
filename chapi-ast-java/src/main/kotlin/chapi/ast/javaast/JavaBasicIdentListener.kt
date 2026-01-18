package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.domain.core.*

open class JavaBasicIdentListener(fileName: String) : JavaAstListener() {
    private var isOverrideMethod: Boolean = false
    private var hasEnterClass: Boolean = false
    private var codeContainer: CodeContainer =
        CodeContainer(
            FullName = fileName,
            Language = "java",
            Kind = ContainerKind.SOURCE_FILE
        )
    private var classNodes: List<CodeDataStruct> = listOf()
    private var imports: List<CodeImport> = listOf()

    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val fullSource = ctx!!.qualifiedName()!!.text
        val isStatic = ctx.STATIC() != null
        val isWildcard = ctx.MUL() != null
        
        val codeImport = CodeImport(Source = fullSource)

        if (isStatic && isWildcard) {
            // Static wildcard import: import static pkg.Class.*;
            codeImport.Kind = ImportKind.STATIC
            codeImport.Scope = "static"
            codeImport.UsageName = listOf("*")
            // Source remains as fullSource for static wildcard imports
        } else if (isStatic) {
            // Single static member import: import static pkg.Class.member;
            val sourceSplit = fullSource.split(".")
            codeImport.UsageName = listOf(sourceSplit.last())
            codeImport.Source = sourceSplit.dropLast(1).joinToString(".")
            codeImport.Kind = ImportKind.STATIC
            codeImport.Scope = "static"
            codeImport.Specifiers = listOf(ImportSpecifier(
                OriginalName = sourceSplit.last(),
                LocalName = sourceSplit.last()
            ))
        } else if (isWildcard) {
            codeImport.Kind = ImportKind.WILDCARD
            codeImport.UsageName = listOf("*")
        } else {
            // Named import: import pkg.Class;
            codeImport.Kind = ImportKind.NAMED
            val className = fullSource.substringAfterLast('.')
            codeImport.UsageName = listOf(className)
            codeImport.Specifiers = listOf(ImportSpecifier(
                OriginalName = className,
                LocalName = className
            ))
        }
        
        codeImport.PathSegments = fullSource.split(".")

        imports += codeImport
        codeContainer.Imports += codeImport
    }

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        val packageName = ctx?.qualifiedName()!!.text
        codeContainer.PackageName = packageName
        codeContainer.DeclaredPackage = packageName
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

    // Handle return statements - check if expression is in a return statement
    override fun enterStatement(ctx: JavaParser.StatementContext?) {
        if (ctx?.RETURN() != null) {
            val expression = ctx.expression(0)
            if (expression != null) {
                val isReturnNull = expression.text == "null"
                currentFunction.addExtension("IsReturnNull", isReturnNull.toString())
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

