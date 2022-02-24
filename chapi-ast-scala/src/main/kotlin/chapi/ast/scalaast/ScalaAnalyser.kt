package chapi.ast.scalaast

import chapi.ast.antlr.ScalaLexer
import chapi.ast.antlr.ScalaParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class ScalaAnalyser() {
    open fun analysis(str: String, fileName: String): CodeContainer {
        val context = this.parse(str).compilationUnit()
        val listener = ScalaFullIdentListener(fileName = fileName)

        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): ScalaParser =
        CharStreams.fromString(str)
            .let(::ScalaLexer)
            .let(::CommonTokenStream)
            .let(::ScalaParser)
}
