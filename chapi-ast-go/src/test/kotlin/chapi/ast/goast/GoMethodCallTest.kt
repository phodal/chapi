package chapi.ast.goast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GoMethodCallTest {
    @Test
    fun shouldIdentifyMethodCall() {
        val code = """package main

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

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls.size, 4)

        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].NodeName, "gin")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].FunctionName, "Default")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Package, "github.com/gin-gonic/gin")

        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[1].NodeName, "gin.Default()")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[2].NodeName, "c")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[2].Parameters.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[3].NodeName, "gin.Default()")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[3].FunctionName, "Run")
    }
}
