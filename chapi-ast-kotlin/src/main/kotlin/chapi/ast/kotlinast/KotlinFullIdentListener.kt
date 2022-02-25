package chapi.ast.kotlinast

import chapi.domain.core.CodeContainer

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - lambda expression
 * - call relationship
 * - coroutine
 */
class KotlinFullIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer

    /** override hooks */
}
