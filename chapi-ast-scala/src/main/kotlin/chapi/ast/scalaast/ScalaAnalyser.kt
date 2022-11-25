package chapi.ast.scalaast

import chapi.ast.antlr.ScalaLexer
import chapi.ast.antlr.ScalaParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class ScalaAnalyser: Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).compilationUnit()
        val listener = ScalaFullIdentListener(fileName = filePath)

        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): ScalaParser =
        CharStreams.fromString(str)
            .let(::ScalaLexer)
            .let(::CommonTokenStream)
            .let(::ScalaParser)
}
