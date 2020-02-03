package chapi.ast.goast

import chapi.ast.antlr.KotlinLexer
import chapi.ast.antlr.KotlinParser
import chapi.ast.kotlinast.KotlinIdentListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class KotlinIdentApp {
    open fun Analysis(Str: String) {
        val context = this.Parse(Str).kotlinFile()
        val listener = KotlinIdentListener()

        ParseTreeWalker().walk(listener, context)

        listener.getNodeInfo()
    }

    open fun Parse(Str: String): KotlinParser {
        val fromString = CharStreams.fromString(Str)
        val lexer = KotlinLexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = KotlinParser(tokenStream)
        return parser
    }
}
