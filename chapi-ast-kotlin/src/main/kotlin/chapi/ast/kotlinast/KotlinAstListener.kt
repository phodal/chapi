package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParserBaseListener
import chapi.domain.core.CodeContainer

// the base listener for the Kotlin parser, implement common methods and expose the output interface
abstract class KotlinAstListener : KotlinParserBaseListener() {
    abstract fun getNodeInfo(): CodeContainer
}
