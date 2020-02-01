package chapi.ast.javaast

import chapi.ast.antlr.JavaLexer
import chapi.ast.antlr.JavaParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class JavaIdentApp {
    open fun Analysis(Str: String) {
        val context = this.Parse(Str).compilationUnit()
        val listener = JavaIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun Parse(Str: String): JavaParser {
        val fromString = CharStreams.fromString(Str)
        val lexer: JavaLexer = JavaLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        val javaParser = JavaParser(tokenStream)
        return javaParser
    }
}
