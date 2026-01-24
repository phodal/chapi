package chapi.ast.javaast

import chapi.ast.antlr.JavaLexer
import chapi.ast.antlr.JavaParser
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeContainer
import chapi.parser.ParseMode
import chapi.parser.TwoStepAnalyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class JavaAnalyser : TwoStepAnalyser() {
    companion object {
        // Reuse ParseTreeWalker instance to reduce object creation
        private val walker = ParseTreeWalker()
    }

    override fun analysis(code: String, filePath: String, parseMode: ParseMode): CodeContainer {
        return when (parseMode) {
            ParseMode.Basic -> identBasicInfo(code, filePath)
            ParseMode.Full -> identFullInfo(code, filePath)
        }
    }

    open fun identFullInfo(
        str: String,
        fileName: String,
        classes: List<String> = listOf(),
        basicNodes: List<CodeDataStruct> = listOf()
    ): CodeContainer {
        val context = this.parse(str).compilationUnit()
        val listener = JavaFullIdentListener(fileName, classes)

        walker.walk(listener, context)
        return listener.getNodeInfo()
    }

    open fun identBasicInfo(str: String, fileName: String): CodeContainer {
        val context = this.parse(str).compilationUnit()
        val listener = JavaBasicIdentListener(fileName)

        walker.walk(listener, context)

        return listener.getNodeInfo()
    }

    open fun parse(str: String): JavaParser {
        val fromString = CharStreams.fromString(str)
        val lexer = JavaLexer(fromString)
        // Remove default error listeners to reduce I/O overhead
        lexer.removeErrorListeners()
        
        val tokenStream = CommonTokenStream(lexer)
        val parser = JavaParser(tokenStream)
        // Remove default error listeners
        parser.removeErrorListeners()
        return parser
    }
}
