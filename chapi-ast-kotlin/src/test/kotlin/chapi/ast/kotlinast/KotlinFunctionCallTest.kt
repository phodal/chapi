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

        assert(functionCall.FunctionName == "PrimaryConstructor")
        assert(functionCall.NodeName == "A")
    }

    @Test
    fun should_handle_for_kotlin_object() {
        val code = """
            object A {
                fun foo() {
                    println("Hello, world!")
                }
            }
        """.trimIndent()

        val codeContainer = KotlinAnalyser().analysis(code, "Test.kt", ParseMode.Full)
        val dataStructures = codeContainer.DataStructures.filter { it.NodeName == "A" }
        val codeFunction = dataStructures[0].Functions[0]

        assert(codeFunction.Name == "foo")
    }

    @Test
    fun should_support_for_companion_object() {
        val code = """
            class A {
                companion object {
                    fun foo() {
                        println("Hello, world!")
                    }
                }
            }
        """.trimIndent()

        val codeContainer = KotlinAnalyser().analysis(code, "Test.kt", ParseMode.Full)
        val dataStructures = codeContainer.DataStructures.filter { it.NodeName == "A" }
        val codeFunction = dataStructures[0].Functions[0]

        assert(codeFunction.Name == "foo")
    }


    @Test
    fun should_success_parse_test_usecase() {
        val code = """
        package cc.unitmesh.pick

        class SingleProjectCodePickerTest {
            @Test
            fun shouldCheckoutTestCode() {
                val picker = SingleProjectCodePicker(
                    InsPickerOption(
                        url = "https://github.com/unit-mesh/unit-eval-testing",
                        completionTypeSize = 10,
                        maxCharInCode = 100,
                    )
                )
        
                val outputFile = File("test.jsonl")
                runBlocking {
                    val output: MutableList<Instruction> = picker.execute()
                    outputFile.writeText(output.joinToString("\n") {
                        Json.encodeToString(it)
                    })
                }
            }
        }""".trimIndent()

        val codeContainer = KotlinAnalyser().analysis(code, "Test.kt", ParseMode.Full)
        val dataStructures = codeContainer.DataStructures.filter { it.NodeName == "SingleProjectCodePickerTest" }
        val functionCall = dataStructures[0].Functions[0].FunctionCalls[0]
        println(functionCall)
    }
}
