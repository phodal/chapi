package chapi.ast.kotlinast

import chapi.parser.ParseMode
import org.junit.jupiter.api.Test

class KotlinFunctionCallTest {
    @Test
    fun should_identify_constructor_call() {
        val code = """
            class A {
                
            }
            
            fun foo() {
                val a = A()
            }
        """.trimIndent()

        val codeContainer = KotlinAnalyser().analysis(code, "Test.kt", ParseMode.Full)
        val dataStructures = codeContainer.DataStructures.filter { it.NodeName == "TestKt" }
        val functionCall = dataStructures[0].Functions[0].FunctionCalls[0]

        assert(functionCall.FunctionName == "A")
    }
}
