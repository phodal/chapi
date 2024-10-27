package chapi.ast.thrift

import chapi.ast.antlr.ThriftLexer
import chapi.ast.antlr.ThriftParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker

class ThriftAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).document()
        val listener = ThriftFullIdentListener(fileName = filePath)
        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): ThriftParser {
        val fromString = CharStreams.fromString(str)
        val lexer = ThriftLexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = ThriftParser(tokenStream)
        return parser
    }
}
