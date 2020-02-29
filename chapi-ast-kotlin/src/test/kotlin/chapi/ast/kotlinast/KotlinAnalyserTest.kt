package chapi.ast.kotlinast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class KotlinAnalyserTest {
    @Test
    internal fun shouldIdentifyKotlinScript() {
        val code = """
fun main() {
  println("Hello")
}
"""

        val codeContainer = KotlinAnalyser().analysis(code, "hello.kt")
        assertEquals(codeContainer.FullName, "hello.kt")
    }
}
