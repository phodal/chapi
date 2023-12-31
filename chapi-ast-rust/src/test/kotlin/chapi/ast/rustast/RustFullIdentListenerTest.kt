package chapi.ast.rustast

import org.junit.jupiter.api.Disabled
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

        val functions = codeContainer.DataStructures[0].Functions
        assertEquals(1, functions.size)
        assertEquals("new", functions[0].Name)
    }

    @Test
    fun should_success_identify_node() {
        val str = """
            struct Point {
                x: i32,
                y: i32,
            }

            fn main() {
                let p = Point::new(1, 2);
            }

            impl Point {
                fn new(x: i32, y: i32) -> Self {
                    Self { x, y }
                }
            }

        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        assertEquals(2, codeContainer.DataStructures.size)
        assertEquals("Point", codeContainer.DataStructures[1].NodeName)

        val functions = codeContainer.DataStructures[1].Functions
        assertEquals(1, functions.size)
    }

    @Test
    fun should_identify_function_parameters() {
        val str = """
            fn say_hello(name: &str) {
                println!("Hello, {}!", name);
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        val functions = codeContainer.DataStructures[0].Functions
        assertEquals("say_hello", functions[0].Name)
        assertEquals(1, functions[0].Parameters.size)
        assertEquals("name", functions[0].Parameters[0].TypeValue)
        assertEquals("&str", functions[0].Parameters[0].TypeType)
    }

    @Test
    fun should_handle_return_type() {
        val str = """
            fn add(a: i32, b: i32) -> i32 {
                a + b
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        val functions = codeContainer.DataStructures[0].Functions
        assertEquals("add", functions[0].Name)
        assertEquals("i32", functions[0].ReturnType)
    }

    @Test
    fun should_pass_for_multiple_impl() {
        val str = """
            use std::cmp::Ordering;
            use crate::embedding::Embedding;

            #[derive(Debug, Clone)]
            pub struct EmbeddingMatch<Embedded: Clone + Ord> {
                score: f32,
                embedding_id: String,
                embedding: Embedding,
                embedded: Embedded,
            }

            impl<Embedded: Clone + Ord> EmbeddingMatch<Embedded> {
                pub fn new(score: f32, embedding_id: String, embedding: Embedding, embedded: Embedded) -> Self {
                    EmbeddingMatch {
                        score,
                        embedding_id,
                        embedding,
                        embedded,
                    }
                }
            }

            impl<Embedded: Clone + Ord> PartialOrd for EmbeddingMatch<Embedded> {
                fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
                    self.score.partial_cmp(&other.score)
                }
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        assertEquals(1, codeContainer.DataStructures.size)
        val functions = codeContainer.DataStructures[0].Functions
        assertEquals(2, functions.size)
    }
}
