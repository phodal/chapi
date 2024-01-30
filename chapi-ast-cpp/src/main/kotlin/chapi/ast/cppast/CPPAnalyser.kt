package chapi.ast.cppast

import chapi.ast.antlr.CPP14Lexer
import chapi.ast.antlr.CPP14Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CPPAnalyser: Analyser  {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).translationUnit()
        val listener = CPPBasicIdentListener(fileName = filePath)

        ParseTreeWalker().walk(listener, context)

        val nodeInfo = listener.getNodeInfo()
        return nodeInfo
    }

    open fun parse(str: String): CPP14Parser =
        CharStreams.fromString(str)
            .let(::CPP14Lexer)
            .let(::CommonTokenStream)
            .let(::CPP14Parser)
}
