package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class KotlinAnalyser {
    open fun analysis(code: String, fileName: String): CodeContainer {
        val context = this.parse(code).kotlinFile()
        val listener = KotlinFullIdentListener(fileName)

        ParseTreeWalker().walk(listener, context)

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
