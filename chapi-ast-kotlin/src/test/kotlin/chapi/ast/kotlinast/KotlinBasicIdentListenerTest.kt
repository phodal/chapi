package chapi.ast.kotlinast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinBasicIdentListenerTest {
    private fun analyse(code: String, fileName: String = "hello.kt") =
        KotlinAnalyser().analysis(code, fileName, AnalysisMode.Basic)

    @Test
    internal fun shouldIdentifyKtFile() {
        val code = """
// this is a kotlin file            
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.FullName, "hello.kt")
    }

    @Test
    internal fun shouldIdentifyPackage() {
        val code = """
package chapi.ast.kotlinast;
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.PackageName, "chapi.ast.kotlinast")
    }

    @Test
    internal fun shouldIdentifyKotlinClass() {
        val code = """
class Person(val name: String) {}
"""

        val codeContainer = analyse(code)
        assertEquals(codeContainer.DataStructures[0].NodeName, "Person")
    }
}
