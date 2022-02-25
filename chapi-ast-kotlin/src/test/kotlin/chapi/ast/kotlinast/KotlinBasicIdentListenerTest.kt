package chapi.ast.kotlinast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinBasicIdentListenerTest {
    private fun analyse(code: String, fileName: String = "hello.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Basic)

    @Test
    internal fun `should identify kt file`() {
        val code = """
// this is a kotlin file            
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.FullName, "hello.kt")
    }

    @Test
    internal fun `should identify package`() {
        val code = """
package chapi.ast.kotlinast
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.PackageName, "chapi.ast.kotlinast")
    }

    @Test
    internal fun `should identify package with useless semicolon`() {
        val code = """
package chapi.ast.kotlinast;
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.PackageName, "chapi.ast.kotlinast")
    }

    @Test
    internal fun `should identify class`() {
        val code = """
class Person {}
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.DataStructures[0].NodeName, "Person")
        assertEquals(codeContainer.DataStructures[0].Functions.size, 0)
    }

    @Test
    internal fun `should identify class with constructor`() {
        val code = """
package chapi.ast.kotlinast

class Person(val name: String) {}
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.DataStructures[0].NodeName, "Person")
        assertEquals(codeContainer.DataStructures[0].Functions.size, 1)
        assertEquals(codeContainer.DataStructures[0].Functions[0].Name, "PrimaryConstructor")
        assertEquals(codeContainer.DataStructures[0].Functions[0].IsConstructor, true)
        assertEquals(codeContainer.DataStructures[0].Functions[0].ReturnType, "chapi.ast.kotlinast.Person")
    }

    @Test
    internal fun `should identify class with imported interface`() {
        val code = """
package chapi.ast.kotlinast

import hello.Human;
     
class Person(val name: String) : Human {}
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.DataStructures[0].NodeName, "Person")
        assertEquals(codeContainer.DataStructures[0].Implements[0], "hello.Human")
    }

    @Test
    internal fun `should identify method`() {
        val code = """
package chapi.ast.kotlinast

import hello.Human
     
class Person(val name: String) : Human {
    fun sayHello() {
        println("Hello")
    }
}
"""

        val codeContainer = analyse(code)
        codeContainer.DataStructures[0].Functions[1].run {
            assertEquals(IsConstructor, false)
            assertEquals(Name, "sayHello")
            assertEquals(ReturnType, "kotlin.Unit")
        }
    }

    @Test
    internal fun `should identify method with parameters and return type`() {
        val code = """
package chapi.ast.kotlinast

import hello.Human
import hello.things.Food
import hello.things.Mood
     
class Person(val name: String) : Human {
    fun eat(food: Food): Mood {
        if (food == Food.Pizza) {
            return Mood.Happy
        }
        return Mood.Sad
    }
    
    fun play(platform: String, game: String): Boolean {
        if (platform == "Epic" && game == "Fortnite") {
            return true
        }
        return false
    } 
}
"""

        val codeContainer = analyse(code)
        codeContainer.DataStructures[0].Functions[1].run {
            assertEquals(Name, "eat")
            assertEquals(ReturnType, "hello.things.Mood")
        }
        codeContainer.DataStructures[0].Functions[2].run {
            assertEquals(Name, "play")
            assertEquals(ReturnType, "kotlin.Boolean")
        }
    }
}
