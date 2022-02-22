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

    @Test
    internal fun shouldIdentifyKotlinClass() {
        val code = """
class Person(val name: String) {
    val children: MutableList<Person> = mutableListOf()
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
"""

        val codeContainer = KotlinAnalyser().analysis(code, "hello.kt")
        assertEquals(codeContainer.DataStructures[0].NodeName, "Person")
    }
}
