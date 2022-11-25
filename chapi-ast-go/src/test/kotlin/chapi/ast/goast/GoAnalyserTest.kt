package chapi.ast.goast

import chapi.domain.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
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

    @Test
    @Disabled
    fun analysisByDir() {
        val dir = "/iam"
        val codeContainer = GoAnalyser().analysisByDir(dir)
        println(codeContainer)
    }
}

private fun GoAnalyser.analysisByDir(dir: String): List<CodeContainer> {
    val codeContainers = mutableListOf<CodeContainer>()
    val files = File(dir).walkTopDown().filter { it.isFile && it.extension == "go" }.toList()
    files.forEach {
        println(it.absolutePath)
        val codeContainer = analysis(it.readText(), it.name)
        codeContainers.add(codeContainer)
    }
    return codeContainers
}
