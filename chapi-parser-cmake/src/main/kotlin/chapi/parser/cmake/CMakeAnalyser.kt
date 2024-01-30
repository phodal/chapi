package chapi.parser.cmake

import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class CMakeAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).file_()
        val listener = CMakeChapiListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): CMakeParser =
        CharStreams.fromString(str)
            .let(::CMakeLexer)
            .let(::CommonTokenStream)
            .let(::CMakeParser)

}
