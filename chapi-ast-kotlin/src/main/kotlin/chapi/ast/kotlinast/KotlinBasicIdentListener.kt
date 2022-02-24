package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer

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

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer

    /** override hooks */

    override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext?) {
        super.enterPackageHeader(ctx)
    }
}
