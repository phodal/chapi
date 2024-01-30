package chapi.parser.cmake

import chapi.domain.core.CodeContainer
import chapi.parser.Analyser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

/**
 * The `CMakeAnalyser` class is a CMakelists.txt parser that implements the `Analyser` interface.
 * It provides a method to analyze the given code and extract relevant information from it.
 *
 * ```kotlin
 * val cmakeCode = """
 *     cmake_minimum_required(VERSION 3.0)
 *     project(HelloWorld)
 *     add_executable(HelloWorld HelloWorld.cpp)
 * """.trimIndent()
 * val container = CMakeAnalyser().analysis(cmakeCode, "CMakeLists.txt")
 * println(container.Fields.size) // 3
 * ```
 */
class CMakeAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        val context = this.parse(code).file_()
        val listener = CMakeBasicListener(filePath = filePath)
        ParseTreeWalker().walk(listener, context)
        return listener.getNodeInfo()
    }

    private fun parse(str: String): CMakeParser =
        CharStreams.fromString(str)
            .let(::CMakeLexer)
            .let(::CommonTokenStream)
            .let(::CMakeParser)

}
