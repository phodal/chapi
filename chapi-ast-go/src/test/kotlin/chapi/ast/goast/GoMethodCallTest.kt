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

        val rInitCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[0]
        assertEquals(rInitCall.NodeName, "gin")
        assertEquals(rInitCall.FunctionName, "Default")
        assertEquals(rInitCall.Package, "github.com/gin-gonic/gin")

        val rGetCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[1]
        assertEquals(rGetCall.NodeName, "gin")
        assertEquals(rGetCall.FunctionName, "GET")
        assertEquals(rGetCall.Parameters.size, 2)
        assertEquals(rGetCall.Parameters[0].TypeValue, "/")
        assertEquals(rGetCall.Parameters[1].TypeValue, "func(c*gin.Context){c.String(http.StatusOK,\"hello world\")\n" +
            "}")

        assertEquals(rGetCall.Package, "github.com/gin-gonic/gin")

        val callback = codeFile.DataStructures[0].Functions[0].FunctionCalls[2]
        assertEquals(callback.NodeName, "c")
        assertEquals(callback.FunctionName, "String")
        assertEquals(callback.Parameters.size, 2)
        assertEquals(callback.Parameters[0].TypeValue, "http.StatusOK")
        assertEquals(callback.Parameters[1].TypeValue, "hello world")

        val rRun = codeFile.DataStructures[0].Functions[0].FunctionCalls[3]
        assertEquals(rRun.NodeName, "gin")
        assertEquals(rRun.FunctionName, "Run")
        assertEquals(rRun.Package, "github.com/gin-gonic/gin")
    }
}
