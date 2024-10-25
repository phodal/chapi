package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf2Lexer
import chapi.ast.antlr.Protobuf2Parser
import chapi.ast.antlr.Protobuf3Lexer
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker

class ProtobufAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val protobuf3Analyser = Protobuf3Analyser()
        val analysis = protobuf3Analyser.analysis(code, filePath)
        if (protobuf3Analyser.errors.isNotEmpty()) {
            val protobuf2Analyser = Protobuf2Analyser()
            return protobuf2Analyser.analysis(code, filePath)
        }

        return analysis
    }
}


class SyntaxErrorListener : BaseErrorListener() {
    val errorMessages = mutableListOf<String>()

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?,
    ) {
        errorMessages.add("line $line:$charPositionInLine - $msg")
    }

    fun hasErrors(): Boolean {
        return errorMessages.isNotEmpty()
    }

    fun getErrors(): List<String> {
        return errorMessages
    }
}

class Protobuf3Analyser : Analyser {
    var errors = listOf<String>()

    override fun analysis(code: String, filePath: String): CodeContainer {
        val (context, errorListener) = this.parse(code)
        val listener = ProtobufFullIdentListener(fileName = filePath)
        ParseTreeWalker().walk(listener, context)

        val nodeInfo = listener.getNodeInfo()

        if (errorListener.hasErrors()) {
            errors = errorListener.getErrors()
        }

        return nodeInfo
    }

    private fun parse(str: String): Pair<Protobuf3Parser.ProtoContext, SyntaxErrorListener> {
        val fromString = CharStreams.fromString(str)
        val lexer = Protobuf3Lexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = Protobuf3Parser(tokenStream)

        // 创建并添加错误监听器
        val errorListener = SyntaxErrorListener()
        parser.removeErrorListeners()  // 移除默认控制台错误输出
        parser.addErrorListener(errorListener)

        return parser.proto() to errorListener
    }
}

class Protobuf2Analyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).proto()
        val listener = Protobuf2FullIdentListener(fileName = filePath)
        ParseTreeWalker().walk(listener, context)

        return listener.getNodeInfo()
    }

    private fun parse(str: String): Protobuf2Parser {
        val fromString = CharStreams.fromString(str)
        val lexer = Protobuf2Lexer(fromString)
        val tokenStream = CommonTokenStream(lexer)
        val parser = Protobuf2Parser(tokenStream)
        return parser
    }
}
