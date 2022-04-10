package chapi.ast.csharpast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {
    @Test
    fun shouldNotCrashForGrammarFile() {
        val code = this::class.java.getResource("/call/hello.cs").readText()
        val node = CSharpAnalyser().analysis(code, "hello.cs")

        val containers = node.Containers
        assertEquals(1, containers.size)

        val calls = containers[0].DataStructures[0].Functions[0].FunctionCalls
        assertEquals(2, calls.size)

        assertEquals("Console", calls[0].NodeName)
        assertEquals("WriteLine", calls[0].FunctionName)

        assertEquals("Console", calls[1].NodeName)
        assertEquals("ReadKey", calls[1].FunctionName)
    }

}
