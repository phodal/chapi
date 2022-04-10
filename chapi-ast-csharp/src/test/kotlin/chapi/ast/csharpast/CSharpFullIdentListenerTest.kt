package chapi.ast.csharpast

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {
    @Test
    fun should_handle_for_simple_java_hello_world() {
        val code = this::class.java.getResource("/call/hello.cs").readText()
        val node = CSharpAnalyser().analysis(code, "hello.cs")

        val containers = node.Containers
        assertEquals(1, containers.size)

        val calls = containers[0].DataStructures[0].Functions[0].FunctionCalls
        assertEquals(2, calls.size)

        assertEquals("Console", calls[0].NodeName)
        assertEquals("WriteLine", calls[0].FunctionName)

        assertEquals(1, calls[0].Parameters.size)
        assertEquals("\"Hello World!\"", calls[0].Parameters[0].TypeValue)

        assertEquals("Console", calls[1].NodeName)
        assertEquals("ReadKey", calls[1].FunctionName)
    }

    @Test
    fun should_get_type_from_property() {
        val code = this::class.java.getResource("/inference/HistoryService.cs").readText()
        val node = CSharpAnalyser().analysis(code, "HistoryService.cs")

        val container = node.Containers[0]
        assertEquals(2, container.DataStructures[0].Functions.size)
    }
}
