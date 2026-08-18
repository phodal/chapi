package chapi.ast.arkts

import chapi.ast.antlr.ArkTSLexer
import chapi.ast.antlr.ArkTSParser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.tree.ParseTreeWalker

data class ArkTSSyntaxDiagnostic(
    val line: Int,
    val column: Int,
    val message: String,
    val offendingSymbol: String = ""
)

data class ArkTSAnalysisResult(
    val container: CodeContainer,
    val diagnostics: List<ArkTSSyntaxDiagnostic>
)

open class ArkTSAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        return analysisWithDiagnostics(code, filePath).container
    }

    fun analysisWithDiagnostics(code: String, filePath: String): ArkTSAnalysisResult {
        val diagnostics = mutableListOf<ArkTSSyntaxDiagnostic>()
        val errorListener = object : BaseErrorListener() {
            override fun syntaxError(
                recognizer: Recognizer<*, *>?,
                offendingSymbol: Any?,
                line: Int,
                charPositionInLine: Int,
                msg: String?,
                e: RecognitionException?
            ) {
                diagnostics += ArkTSSyntaxDiagnostic(
                    line = line,
                    column = charPositionInLine,
                    message = msg ?: "syntax error",
                    offendingSymbol = offendingSymbol?.toString() ?: ""
                )
            }
        }

        val lexer = ArkTSLexer(CharStreams.fromString(code)).apply {
            removeErrorListeners()
            addErrorListener(errorListener)
        }
        val parser = ArkTSParser(CommonTokenStream(lexer)).apply {
            removeErrorListeners()
            addErrorListener(errorListener)
        }
        val context = parser.program()
        val listener = ArkTSFullIdentListener(ArkTSIdentify(filePath = filePath))
        ParseTreeWalker().walk(listener, context)
        return ArkTSAnalysisResult(listener.getNodeInfo(), diagnostics)
    }
}
