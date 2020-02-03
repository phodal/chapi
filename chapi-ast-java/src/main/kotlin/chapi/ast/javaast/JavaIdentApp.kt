package chapi.ast.javaast

import chapi.ast.antlr.JavaLexer
import chapi.ast.antlr.JavaParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class JavaIdentApp {
    open fun analysis(str: String) {
        val context = this.parse(str).compilationUnit()
        val listener = JavaIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun parse(str: String): JavaParser {
        val fromString = CharStreams.fromString(str)
        val lexer: JavaLexer = JavaLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        val javaParser = JavaParser(tokenStream)
        return javaParser
    }
}
