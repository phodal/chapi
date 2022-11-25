package chapi.ast.rustast

import chapi.ast.antlr.RustLexer
import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class RustAnalyser : Analyser {
    override fun analysis(str: String, filePath: String): CodeContainer {
        val context = this.parse(str).crate()
        val listener = RustFullIdentListener(filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): RustParser =
        CharStreams.fromString(str)
            .let(::RustLexer)
            .let(::CommonTokenStream)
            .let(::RustParser)

}
