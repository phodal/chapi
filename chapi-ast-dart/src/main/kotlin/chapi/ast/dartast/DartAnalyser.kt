package chapi.ast.dartast

import chapi.ast.antlr.Dart2Lexer
import chapi.ast.antlr.Dart2Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class DartAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = parse(code).compilationUnit()
        val listener = DartFullIdentListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    open fun identBasicInfo(code: String, filePath: String): CodeContainer {
        val context = parse(code).compilationUnit()
        val listener = DartBasicIdentListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): Dart2Parser =
        CharStreams.fromString(str)
            .let(::Dart2Lexer)
            .let(::CommonTokenStream)
            .let(::Dart2Parser)
}
