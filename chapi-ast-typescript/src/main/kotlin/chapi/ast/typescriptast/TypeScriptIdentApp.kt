package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptLexer
import chapi.ast.antlr.TypeScriptParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class TypeScriptIdentApp {
    open fun Analysis(Str: String) {
        val context = this.Parse(Str).initializer()
        val listener = TypeScriptIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun Parse(Str: String): TypeScriptParser {
        val fromString = CharStreams.fromString(Str)
        val lexer = TypeScriptLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        return TypeScriptParser(tokenStream)
    }
}
