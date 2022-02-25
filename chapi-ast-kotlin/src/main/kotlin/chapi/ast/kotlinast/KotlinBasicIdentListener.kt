package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport
import chapi.domain.core.CodePosition
import chapi.domain.core.DataStructType
import org.antlr.v4.runtime.ParserRuleContext

/**
 * listen to basic identifier that represent file and class structures,
 * e.g.
 * - package, imports, class
 * - functions, fields, annotations
 * - parameter type and return type
 *
 * for kotlin featured
 * - multiple classes in one file
 * - companion object
 * - individual variable/function
 */
class KotlinBasicIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private val classes: MutableList<CodeDataStruct> = mutableListOf()
    private val imports: MutableList<CodeImport> = mutableListOf()
    private lateinit var currentNode: CodeDataStruct
    private lateinit var currentFunction: CodeFunction

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = classes.toTypedArray()
    }

    /** override hooks */

    override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext) {
        if (ctx.childCount == 0) return

        codeContainer.PackageName = ctx.identifier().text
    }

    override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        currentNode = CodeDataStruct()
        currentNode.Type = DataStructType.CLASS
        currentNode.Package = codeContainer.PackageName
        currentNode.NodeName = ctx.simpleIdentifier().Identifier().text

        ctx.delegationSpecifiers()?.let {
            currentNode.Implements = arrayOf(it.text.let(::getTypeFullName))
        }
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        classes.add(currentNode)
    }

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentFunction = CodeFunction(
            Name = PRIMARY_CONSTRUCTOR_NAME,
            ReturnType = currentNode.getClassFullName(),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = true,
        )
    }

    override fun exitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentNode.Functions += currentFunction
    }

    override fun enterImportList(ctx: KotlinParser.ImportListContext) {
        ctx.importHeader()
            .map(KotlinParser.ImportHeaderContext::identifier)
            .map { CodeImport(Source = it.text, AsName = it.text.substringAfterLast('.')) }
            .let(imports::addAll)
    }

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        currentFunction = CodeFunction(
            Name = ctx.simpleIdentifier().Identifier().text,
            ReturnType = getTypeFullName(ctx.type()?.typeReference()?.text ?: UNIT_TYPE),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = false,
        )
    }

    override fun exitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        currentNode.Functions += currentFunction
    }

    private fun ParserRuleContext.getPosition(): CodePosition =
        CodePosition(
            StartLine = start.line,
            StartLinePosition = start.charPositionInLine,
            StopLine = stop.line,
            StopLinePosition = stop.charPositionInLine,
        )

    private fun getTypeFullName(name: String): String =
        imports.firstOrNull { it.AsName == name }?.Source ?: "kotlin.$name"

    companion object {
        const val PRIMARY_CONSTRUCTOR_NAME = "PrimaryConstructor"
        const val UNIT_TYPE = "Unit"
    }
}
