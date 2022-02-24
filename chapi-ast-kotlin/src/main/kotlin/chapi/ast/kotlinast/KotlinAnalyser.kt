package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class KotlinAnalyser {
    fun analysis(code: String, fileName: String, type: AnalysisMode = AnalysisMode.Basic): CodeContainer {
        val listener = when (type) {
            AnalysisMode.Basic -> KotlinBasicIdentListener(fileName)
            AnalysisMode.Full -> KotlinFullIdentListener(fileName)
        }
        val context = when (isKotlinScript(fileName)) {
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

enum class AnalysisMode {
    Basic, Full
}
