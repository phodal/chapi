package chapi.ast.javaast

import chapi.ast.antlr.JavaLexer
import chapi.ast.antlr.JavaParser
import domain.core.CodeFile
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class JavaIdentApp {
    open fun analysis(str: String, fileName: String): CodeFile {
        val context = this.parse(str).compilationUnit()
        val listener = JavaIdentListener(fileName)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    open fun parse(str: String): JavaParser {
        val fromString = CharStreams.fromString(str)
        val lexer: JavaLexer = JavaLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        val javaParser = JavaParser(tokenStream)
        return javaParser
    }
}
