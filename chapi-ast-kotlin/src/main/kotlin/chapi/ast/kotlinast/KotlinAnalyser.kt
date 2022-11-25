package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.parser.ParseMode
import chapi.parser.TwoStepAnalyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

enum class AnalysisMode {
    Basic, Full
}

// a compatible api for Analyser
@Deprecated("use AnalysisMode move to Global instead", ReplaceWith("ParseMode"))
fun KotlinAnalyser.analysis(code: String, filePath: String, mode: AnalysisMode): CodeContainer {
    return when (mode) {
        AnalysisMode.Basic -> analysis(code, filePath, ParseMode.Basic)
        AnalysisMode.Full -> analysis(code, filePath, ParseMode.Full)
    }
}

class KotlinAnalyser: TwoStepAnalyser() {
    override fun analysis(code: String, filePath: String, parseMode: ParseMode): CodeContainer {
        val listener = when (parseMode) {
            ParseMode.Basic -> KotlinBasicIdentListener(filePath)
            ParseMode.Full -> KotlinFullIdentListener(filePath)
        }
        val context = when (isKotlinScript(filePath)) {
            true -> this.parse(code).script()
            false -> this.parse(code).kotlinFile()
        }

        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): KotlinParser =
        CharStreams.fromString(str)
            .let(::KotlinLexer)
            .let(::CommonTokenStream)
            .let(::KotlinParser)

    private fun isKotlinScript(fileName: String): Boolean = fileName.endsWith(".kts")
}

