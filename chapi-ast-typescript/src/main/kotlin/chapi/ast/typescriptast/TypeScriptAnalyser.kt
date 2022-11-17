package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptLexer
import chapi.ast.antlr.TypeScriptParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class TypeScriptAnalyser {
    open fun analysis(str: String, filePath: String): CodeContainer {
        val context = this.parse(str, filePath).program()
        val listener = TypeScriptFullIdentListener(TSIdentify(filePath = filePath))
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String, filePath: String): TypeScriptParser =
        CharStreams.fromString(str)
            .let(::TypeScriptLexer)
            .let(::CommonTokenStream)
            .let {
                val typeScriptParser = TypeScriptParser(it)
                if (filePath.endsWith(".tsx")) {
                    typeScriptParser.isJsx = true
                } else if (filePath.endsWith(".d.ts")) {
                    typeScriptParser.isJsx = false
                }

                typeScriptParser
            }
}
