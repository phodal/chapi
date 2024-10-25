package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf3Lexer
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class ProtobufAnalyser: Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).proto()
        val listener = ProtobufFullIdentListener(fileName = filePath)
        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): Protobuf3Parser {
        val fromString = CharStreams.fromString(str)
        val lexer = Protobuf3Lexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = Protobuf3Parser(tokenStream)
        return parser
    }
}
