package chapi.ast.goast

import chapi.domain.core.*
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
                            NodeName = "fmt.Println",
                            Parameters = arrayOf(CodeProperty(TypeValue = "\"hello world\"", TypeType = ""))
                        )
                    ),
                )
            ),
        )
        assertEquals(Json.encodeToString(value), Json.encodeToString(expect))
    }

    @Test
    fun analysis2() {
        val helloworldApi = """package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	r.GET("/", func(c *gin.Context) {
		c.String(http.StatusOK, "hello world")
	})

	r.Run()
}"""
        val container = GoAnalyser().analysis(helloworldApi, "")
        println(Json.encodeToString(container))
    }
}
