package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class KotlinAnalyser {
    open fun analysis(code: String, fileName: String, type: String = "file"): CodeContainer {
        val listener = KotlinFullIdentListener(fileName)

        // TODO .kt=file .kts=script
        val isKotlinScript = type == "script"
        if (isKotlinScript) {
            val context = this.parse(code).script()
            ParseTreeWalker().walk(listener, context)
        } else {
            val context = this.parse(code).kotlinFile()
            ParseTreeWalker().walk(listener, context)
        }

        return listener.getNodeInfo()
    }

    open fun parse(str: String): KotlinParser {
        val fromString = CharStreams.fromString(str)
        val lexer = KotlinLexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = KotlinParser(tokenStream)
        return parser
    }
}
