package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.DataStructType

/**
 * basic identifier listener, include
 * - package
 * - class
 * - functions
 * - fields
 */
class KotlinBasicIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private val classNodes: MutableList<CodeDataStruct> = mutableListOf()
    private lateinit var currentNode: CodeDataStruct

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

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext?) {
        classNodes.add(currentNode)
    }

    companion object {
        const val UNKNOWN_PLACEHOLDER = "<UNKNOWN>"
    }
}
