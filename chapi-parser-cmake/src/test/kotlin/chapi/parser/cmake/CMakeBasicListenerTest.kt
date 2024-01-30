package chapi.parser.cmake;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CMakeBasicListenerTest {

    @Test
    fun shouldReturnCodeContainerWithFilePathAsFullName() {
        // given
        val cmakeCode = """
            cmake_minimum_required(VERSION 3.0)
            project(HelloWorld)
            add_executable(HelloWorld HelloWorld.cpp)
        """.trimIndent()
        val container = CMakeAnalyser().analysis(cmakeCode, "CMakeLists.txt")

        // when
        assertEquals(3, container.Fields.size)
    }
}
