package chapi.ast.javaast

import chapi.ast.antlr.JavaLexer
import chapi.ast.antlr.JavaParser
import domain.core.CodeDataStruct
import domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class JavaAnalyser {
    open fun identFullInfo(
        str: String,
        fileName: String,
        classes: Array<String> = arrayOf(),
        basicNodes: Array<CodeDataStruct> = arrayOf()
    ): CodeContainer {
        val context = this.parse(str).compilationUnit()
        val listener = JavaFullIdentListener(fileName, classes, basicNodes)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    open fun identBasicInfo(str: String, fileName: String): CodeContainer {
        val context = this.parse(str).compilationUnit()
        val listener = JavaBasicIdentListener(fileName)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    open fun parse(str: String): JavaParser {
        val fromString = CharStreams.fromString(str)
        val lexer = JavaLexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = JavaParser(tokenStream)
        return parser
    }
}
