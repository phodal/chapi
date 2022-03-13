package chapi.ast.kotlinast

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinFullIdentListenerTest {
    private fun analyse(code: String, fileName: String = "hello.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Full)

    @Nested
    inner class Calls {
        private fun calls(code: String) = analyse(code).DataStructures[0].Functions[0].FunctionCalls

        @Test
        internal fun `should identify function call of individual function`() {
            val code = """
fun main() {
    println("Hello world!")
}     
"""

            val calls = calls(code)

            assertEquals(calls.size, 1)
            assertEquals(calls[0].NodeName, "")
            assertEquals(calls[0].FunctionName, "println")
            assertEquals(calls[0].Parameters.size, 1)
            assertEquals(calls[0].Parameters[0].TypeType, "kotlin.String")
            assertEquals(calls[0].Parameters[0].TypeValue, "\"Hello world!\"")
        }
    }
}
