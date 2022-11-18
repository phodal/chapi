package chapi.ast.rustast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class RustFullIdentListenerTest {
    @Test
    fun structDef() {
        val str = """
            struct Point {
                x: i32,
                y: i32,
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        assertEquals(1, codeContainer.DataStructures.size)
        assertEquals("Point", codeContainer.DataStructures[0].NodeName)
    }
}
