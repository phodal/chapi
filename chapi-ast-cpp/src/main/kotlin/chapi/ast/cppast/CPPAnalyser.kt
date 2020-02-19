package chapi.ast.cppast

import chapi.ast.antlr.CPPLexer
import chapi.ast.antlr.CPPParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CPPAnalyser  {
    open fun analysis(code: String, fileName: String): CodeContainer {
        val context = this.parse(code).translationunit()
        val listener = CPPFullIdentListener(fileName = fileName)

        ParseTreeWalker().walk(listener, context)

        val nodeInfo = listener.getNodeInfo()
        return nodeInfo
    }

    open fun parse(str: String): CPPParser {
        val fromString = CharStreams.fromString(str)
        val lexer = CPPLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        return CPPParser(tokenStream)
    }
}
