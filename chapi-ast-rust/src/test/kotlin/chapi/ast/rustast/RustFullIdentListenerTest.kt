package chapi.ast.rustast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class RustFullIdentListenerTest {
    @Test
    fun should_success_handle_for_rust_structure_node_def() {
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

    @Test
    fun should_binding_node_method_to_struct() {
        val str = """
            struct Point {
                x: i32,
                y: i32,
            }
            
            impl Point {
                fn new(x: i32, y: i32) -> Self {
                    Self { x, y }
                }
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        assertEquals(1, codeContainer.DataStructures.size)
        assertEquals("Point", codeContainer.DataStructures[0].NodeName)
        assertEquals(1, codeContainer.DataStructures[0].Functions.size)
        assertEquals("new", codeContainer.DataStructures[0].Functions[0].Name)
    }
}
