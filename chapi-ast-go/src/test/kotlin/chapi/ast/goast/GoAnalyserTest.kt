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
            Functions = listOf(
                CodeFunction(
                    Name = "main", Package = "main",
                    FunctionCalls = listOf(
                        CodeCall(
                            NodeName = "fmt",
                            FunctionName = "Println",
                            Parameters = listOf(CodeProperty(TypeValue = "hello world", TypeType = "string"))
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

        assertEquals(firstCall.NodeName, "*gin.Engine")
        assertEquals(firstCall.Package, "github.com/gin-gonic/gin")

        assertEquals(firstCall.FunctionName, "POST")
        assertEquals(firstCall.Parameters[0].TypeValue, "/login")
        assertEquals(firstCall.Parameters[0].TypeType, "string")
        assertEquals(firstCall.Parameters[1].TypeType, "FunctionCall")
        assertEquals(firstCall.Parameters[1].TypeValue, "jwtStrategy.LoginHandler")
    }

    @Test
    fun shouldIdentifyMethodCall() {
        val code = """package server

import (
	"github.com/gin-gonic/gin"
	"github.com/chapi/server/controller/v1/user"
	"github.com/chapi/server/store/mysql"
	_ "github.com/chapi/pkg/validator"
)

func installController(g *gin.Engine) *gin.Engine {
	storeIns, _ := mysql.GetMySQLFactoryOr(nil)
	v1 := g.Group("/v1")
	{
		userv1 := v1.Group("/users")
		{
			userController := user.NewUserController(storeIns)

			userv1.GET("", userController.List)
			userv1.POST("", userController.Create)
			userv1.GET(":name", userController.Get)
		}
    }

	return g
}"""

        val codeContainer = GoAnalyser().analysis(code, "")
        val container = codeContainer.DataStructures[0]

        val mainCall = container.Functions[0].FunctionCalls[1]

        assertEquals(container.Functions.size, 1)
        assertEquals(mainCall.FunctionName, "Group")
        assertEquals(mainCall.Parameters[0].TypeValue, "/v1")

        val firstBlock = container.Functions[0].InnerFunctions[0]
        val innelCall = firstBlock.FunctionCalls[0]

        assertEquals(innelCall.FunctionName, "Group")
        assertEquals(innelCall.NodeName, "*gin.Engine")
        assertEquals(innelCall.Parameters[0].TypeValue, "/users")

        val secondBlock = container.Functions[0].InnerFunctions[0].InnerFunctions[0]
        val innerInnerCall = secondBlock.FunctionCalls[0]

        assertEquals(innerInnerCall.FunctionName, "NewUserController")

        val innerInnerSecondCall = secondBlock.FunctionCalls[1]
        assertEquals(innerInnerSecondCall.FunctionName, "GET")
    }

    @Test
    @Disabled
    fun analysisByDir() {
        val dir = "/Users/phodal/test/iam"
        val files = File(dir).walkTopDown().filter { it.isFile && it.extension == "go" }.toList()
        val codeContainer = files.map {
            GoAnalyser().analysis(it.readText(), it.name)
        }.toList()
        // write to file
        val json = Json.encodeToString(codeContainer)
        File("iam.json").writeText(json)
    }
}

