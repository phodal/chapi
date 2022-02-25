package chapi.ast.kotlinast

import chapi.domain.core.CodeContainer

/**
 * full identifier listener, with more identifiers of the class
 * - should include all functions in @link{chapi.ast.kotlinast.BasicIdentListener}
 * - functional features?
 * - coroutine?
 */
class KotlinFullIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer

    /** override hooks */
}
