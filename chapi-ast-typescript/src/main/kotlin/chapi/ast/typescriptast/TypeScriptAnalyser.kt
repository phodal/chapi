package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptLexer
import chapi.ast.antlr.TypeScriptParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class TypeScriptAnalyser: Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).program()
        val listener = TypeScriptFullIdentListener(TSIdentify(filePath = filePath))
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): TypeScriptParser =
        CharStreams.fromString(str)
            .let(::TypeScriptLexer)
            .let(::CommonTokenStream)
            .let(::TypeScriptParser)
}
