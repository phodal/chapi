package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodePosition
import chapi.domain.core.DataStructType

/**
 * basic identifier listener, include
 * - package
 * - imports
 * - class
 * - functions
 * - fields
 * - single class file
 * - multiple classes file
 * - companion object
 * - individual variable/function
 * - annotations
 * - parameter type and return type
 */
class KotlinBasicIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private val classNodes: MutableList<CodeDataStruct> = mutableListOf()
    private lateinit var currentNode: CodeDataStruct
    private lateinit var currentFunction: CodeFunction

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = classNodes.toTypedArray()
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
            currentNode.Implements = arrayOf(it.text)
        }
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        classNodes.add(currentNode)
    }

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentFunction = CodeFunction(
            Name = PRIMARY_CONSTRUCTOR_LABEL,
            ReturnType = currentNode.getClassFullName(),
            Override = false,
            Position = CodePosition(
                StartLine = ctx.start.line,
                StartLinePosition = ctx.start.charPositionInLine,
                StopLine = ctx.stop.line,
                StopLinePosition = ctx.stop.charPositionInLine,
            ),
            IsConstructor = true,
        )
    }

    override fun exitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentNode.Functions += currentFunction
    }

    companion object {
        const val PRIMARY_CONSTRUCTOR_LABEL = "PrimaryConstructor"
        const val UNKNOWN_PLACEHOLDER = "<UNKNOWN>"
    }
}
