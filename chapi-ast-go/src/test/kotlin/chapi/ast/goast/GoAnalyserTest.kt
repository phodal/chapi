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
    fun analysisForRealword() {
        val helloworld = """
package server

import (
	"github.com/gin-gonic/gin"
	"github.com/chapi/pkg/middleware/auth"
)
            
func installController(g *gin.Engine) *gin.Engine {
	jwtStrategy, _ := newJWTAuth().(auth.JWTStrategy)
	g.POST("/login", jwtStrategy.LoginHandler)
	g.POST("/logout", jwtStrategy.LogoutHandler)

	return g
}
"""
        val codeContainer = GoAnalyser().analysis(helloworld, "")
        val value = codeContainer.DataStructures[0]
        val firstCall = value.Functions[0].FunctionCalls[0]
        println(Json.encodeToString(value))

        assertEquals(firstCall.NodeName, "*gin.Engine")
        assertEquals(firstCall.Package, "github.com/gin-gonic/gin")
    }

    @Test
    @Disabled
    fun analysisByDir() {
        val dir = "/Users/phodal/test/iam"
        val codeContainer = GoAnalyser().analysisByDir(dir)
        // write to file
        val json = Json.encodeToString(codeContainer)
        File("iam.json").writeText(json)
    }
}

private fun GoAnalyser.analysisByDir(dir: String): List<CodeContainer> {
    val files = File(dir).walkTopDown().filter { it.isFile && it.extension == "go" }.toList()
    return files.map {
        analysis(it.readText(), it.name)
    }.toList()
}
