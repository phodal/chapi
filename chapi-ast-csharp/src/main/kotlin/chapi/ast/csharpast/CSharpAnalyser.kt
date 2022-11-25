package chapi.ast.csharpast

import chapi.ast.antlr.CSharpLexer
import chapi.ast.antlr.CSharpParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class CSharpAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).compilation_unit()
        val listener = CSharpFullIdentListener(fileName = filePath)

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): CSharpParser =
        CharStreams.fromString(str)
            .let(::CSharpLexer)
            .let(::CommonTokenStream)
            .let(::CSharpParser)
}
