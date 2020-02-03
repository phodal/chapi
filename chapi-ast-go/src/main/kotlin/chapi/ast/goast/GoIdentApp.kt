package chapi.ast.goast

import chapi.ast.antlr.GoLexer
import chapi.ast.antlr.GoParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class GoIdentApp {
    open fun Analysis(Str: String) {
        val context = this.Parse(Str).sourceFile()
        val listener = GoIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun Parse(Str: String): GoParser {
        val fromString = CharStreams.fromString(Str)
        val lexer = GoLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = GoParser(tokenStream)
        return parser
    }
}
