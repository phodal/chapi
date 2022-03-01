package chapi.ast.kotlinast

import org.junit.jupiter.api.Nested
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

    @Nested
    inner class Classes {
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
            codeContainer.DataStructures[0].Functions[0].run {
                assertEquals(Name, "PrimaryConstructor")
                assertEquals(IsConstructor, true)
                assertEquals(ReturnType, "chapi.ast.kotlinast.Person")
                assertEquals(Parameters[0].TypeValue, "name")
                assertEquals(Parameters[0].TypeType, "kotlin.String")
            }
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
        internal fun `should identify multiple classes`() {
            val code = """
package chapi.ast.kotlinast

class Tom {}

class Jerry {}
"""

            val codeContainer = analyse(code)
            assertEquals(codeContainer.DataStructures[0].NodeName, "Tom")
            assertEquals(codeContainer.DataStructures[0].Package, "chapi.ast.kotlinast")
            assertEquals(codeContainer.DataStructures[1].NodeName, "Jerry")
            assertEquals(codeContainer.DataStructures[1].Package, "chapi.ast.kotlinast")
        }
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
            assertEquals(Parameters[0].TypeValue, "food")
            assertEquals(Parameters[0].TypeType, "hello.things.Food")
        }
        codeContainer.DataStructures[0].Functions[2].run {
            assertEquals(Name, "play")
            assertEquals(ReturnType, "kotlin.Boolean")
            assertEquals(Parameters[0].TypeValue, "platform")
            assertEquals(Parameters[0].TypeType, "kotlin.String")
            assertEquals(Parameters[1].TypeValue, "game")
            assertEquals(Parameters[1].TypeType, "kotlin.String")
        }
    }

    @Nested
    inner class Annotations {
        private val code = """
package chapi.ast.kotlinast

import hello.Human
import hello.things.Food
import hello.things.Mood
import hello.annotations.Entity
import hello.annotations.Method
import hello.annotations.Parameter
     
@Entity(scope = Entity.CLASS)
class Person(val name: String) : Human {
    @Method(scope = Method.FIRST)
    fun eat(@Parameter(name = "food") food: Food): Mood {
        if (food == Food.Pizza) {
            return Mood.Happy
        }
        return Mood.Sad
    }

    @Method(scope = Method.SECOND)
    fun play(@Parameter(required = true) platform: String, @Parameter(default = "Sekiro") game: String): Boolean {
        if (platform == "Epic" && game == "Fortnite") {
            return true
        }
        return false
    }
}
"""

        @Test
        fun `should identify annotations of class`() {
            val codeContainer = analyse(code)
            codeContainer.DataStructures[0].Annotations[0].run {
                assertEquals(Name, "Entity")
                assertEquals(KeyValues[0].Key, "scope")
                assertEquals(KeyValues[0].Value, "Entity.CLASS")
            }
        }

        @Test
        fun `should identify annotations of functions`() {
            val codeContainer = analyse(code)
            codeContainer.DataStructures[0].Functions[1].Annotations[0].run {
                assertEquals(Name, "Method")
                assertEquals(KeyValues[0].Key, "scope")
                assertEquals(KeyValues[0].Value, "Method.FIRST")
            }
            codeContainer.DataStructures[0].Functions[2].Annotations[0].run {
                assertEquals(Name, "Method")
                assertEquals(KeyValues[0].Key, "scope")
                assertEquals(KeyValues[0].Value, "Method.SECOND")
            }
        }

        @Test
        fun `should identify annotations of function parameters`() {
            val codeContainer = analyse(code)
            codeContainer.DataStructures[0].Functions[1].run {
                Parameters[0].Annotations[0].run {
                    assertEquals(Name, "Parameter")
                    assertEquals(KeyValues[0].Key, "name")
                    assertEquals(KeyValues[0].Value, "\"food\"")
                }
            }
            codeContainer.DataStructures[0].Functions[2].run {
                Parameters[0].Annotations[0].run {
                    assertEquals(Name, "Parameter")
                    assertEquals(KeyValues[0].Key, "required")
                    assertEquals(KeyValues[0].Value, "true")
                }
                Parameters[1].Annotations[0].run {
                    assertEquals(Name, "Parameter")
                    assertEquals(KeyValues[0].Key, "default")
                    assertEquals(KeyValues[0].Value, "\"Sekiro\"")
                }
            }
        }
    }
}
