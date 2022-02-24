package chapi.ast.kotlinast

import chapi.domain.core.CodeContainer

/**
 * full identifier listener, include
 * - all functions in @link{chapi.ast.kotlinast.BasicIdentListener}
 * - annotations
 * - parameter type and return type
 */
class KotlinFullIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer

    /** override hooks */
}
