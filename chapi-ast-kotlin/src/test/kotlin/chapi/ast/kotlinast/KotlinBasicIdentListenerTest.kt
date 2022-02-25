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
        assertEquals(codeContainer.DataStructures[0].Implements[0], "Human")
    }

    // TODO identify method
    // TODO identify method with parameter and return type
    // TODO identify fields
    // TODO identify imports
}
