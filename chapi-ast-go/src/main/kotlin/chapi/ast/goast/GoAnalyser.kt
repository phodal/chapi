package chapi.ast.goast

import chapi.ast.antlr.GoLexer
import chapi.ast.antlr.GoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class GoAnalyser {
    open fun analysis(str: String) {
        val context = this.parse(str).sourceFile()
        val listener = GoFullIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun parse(str: String): GoParser {
        val fromString = CharStreams.fromString(str)
        val lexer = GoLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        return GoParser(tokenStream)
    }
}
