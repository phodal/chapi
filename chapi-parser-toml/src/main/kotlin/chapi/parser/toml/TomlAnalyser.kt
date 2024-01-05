package chapi.parser.toml

import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

/**
 * The [TomlAnalyser] class is responsible for parsing TOML (Tom's Obvious, Minimal Language) code and converting it into a
 * [CodeContainer] object.
 *
 * Example usage:
 * ```kotlin
 * val container = TomlAnalyser().analysis("title = "TOML Example"")
 * assert(container.Fields.size == 1)
 * assert(container.Fields[0].TypeKey == "title")
 * assert(container.Fields[0].TypeValue == "TOML Example")
 * assert(container.Fields[0].TypeValue == "String")
 * ```
 *
 */
class TomlAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).document()
        val listener = TomlListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): TomlParser =
        CharStreams.fromString(str)
            .let(::TomlLexer)
            .let(::CommonTokenStream)
            .let(::TomlParser)

}
