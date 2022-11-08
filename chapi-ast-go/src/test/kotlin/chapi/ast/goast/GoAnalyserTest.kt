package chapi.ast.goast

import org.junit.Ignore
import org.junit.jupiter.api.Test

internal class GoAnalyserTest {
    @Test
    fun analysis() {
        var helloworld = """
package main
import "fmt"
func main() {
    fmt.Println("hello world")
}
"""
        GoAnalyser().analysis(helloworld, "")
    }

    @Test
    @Ignore
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
        val analysis = GoAnalyser().analysis(helloworldApi, "")
        println(analysis)
    }
}
