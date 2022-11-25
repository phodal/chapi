package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import chapi.parser.AnalysisMode
import chapi.parser.TwoStepAnalyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class KotlinAnalyser: TwoStepAnalyser() {
    override fun analysis(code: String, filePath: String, mode: AnalysisMode): CodeContainer {
        val listener = when (mode) {
            AnalysisMode.Basic -> KotlinBasicIdentListener(filePath)
            AnalysisMode.Full -> KotlinFullIdentListener(filePath)
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

