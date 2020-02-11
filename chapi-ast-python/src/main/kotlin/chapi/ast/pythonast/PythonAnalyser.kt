package chapi.ast.pythonast

import chapi.ast.antlr.PythonLexer
import chapi.ast.antlr.PythonParser
import domain.core.CodeFile
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

open class PythonAnalyser() {
    open fun analysis(str: String, fileName: String): CodeFile {
        val context = this.parse(str).root()
        val listener = PythonFullIdentListener(fileName = fileName)

        ParseTreeWalker().walk(listener, context)

        val codeFile = listener.getNodeInfo()
        return codeFile
    }

    private fun parse(str: String): PythonParser {
        val fromString = CharStreams.fromString(str)
        val lexer = PythonLexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = PythonParser(tokenStream)
        return parser
    }
}
