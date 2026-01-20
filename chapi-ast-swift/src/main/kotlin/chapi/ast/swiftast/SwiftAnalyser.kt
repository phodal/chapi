package chapi.ast.swiftast

import chapi.ast.antlr.Swift5Lexer
import chapi.ast.antlr.Swift5Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class SwiftAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = parse(code).top_level()
        val listener = SwiftFullIdentListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): Swift5Parser =
        CharStreams.fromString(str)
            .let(::Swift5Lexer)
            .let(::CommonTokenStream)
            .let(::Swift5Parser)
}

