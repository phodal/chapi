package chapi.ast.csharpast

import chapi.ast.antlr.CSharpLexer
import chapi.ast.antlr.CSharpParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CSharpAnalyser {
    open fun analysis(code: String, fileName: String): CodeContainer {
        val context = this.parse(code).compilation_unit()
        val listener = CSharpFullIdentListener(fileName = fileName)

        ParseTreeWalker().walk(listener, context)

        val nodeInfo = listener.getNodeInfo()
        return nodeInfo
    }

    open fun parse(str: String): CSharpParser {
        val fromString = CharStreams.fromString(str)
        val lexer = CSharpLexer (fromString)
        val tokenStream = CommonTokenStream(lexer)
        return CSharpParser(tokenStream)
    }
}
