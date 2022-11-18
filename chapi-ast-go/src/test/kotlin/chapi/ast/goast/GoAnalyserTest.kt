package chapi.ast.goast

import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GoAnalyserTest {
    @Test
    fun analysis() {
        val helloworld = """
package main

import "fmt"

func main() {
    fmt.Println("hello world")
}
"""
        val codeContainer = GoAnalyser().analysis(helloworld, "")
        val value = codeContainer.DataStructures[0]
        val expect = CodeDataStruct(
            Functions = arrayOf(
                CodeFunction(
                    Name = "main", Package = "main",
                    FunctionCalls = arrayOf(
                        CodeCall(
                            NodeName = "fmt",
                            FunctionName = "Println",
                            Parameters = arrayOf(CodeProperty(TypeValue = "\"hello world\"", TypeType = ""))
                        )
                    ),
                )
            ),
        )
        assertEquals(Json.encodeToString(value), Json.encodeToString(expect))
    }
}
