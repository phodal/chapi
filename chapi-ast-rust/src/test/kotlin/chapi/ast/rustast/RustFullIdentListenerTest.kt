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

    @Test
    fun should_handle_for_attribute_as_annotation() {
        val str = """
            #[derive(Debug, Clone)]
            pub struct EmbeddingMatch<Embedded: Clone + Ord> {
                score: f32,
                embedding_id: String,
                embedding: Embedding,
                embedded: Embedded,
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(str, "test.rs")
        assertEquals(1, codeContainer.DataStructures[0].Annotations.size)

        val codeAnnotation = codeContainer.DataStructures[0].Annotations[0]
        assertEquals("derive", codeAnnotation.Name)
        assertEquals(2, codeAnnotation.KeyValues.size)
        assertEquals("Debug", codeAnnotation.KeyValues[0].Value)
        assertEquals("Clone", codeAnnotation.KeyValues[1].Value)
    }

    @Test
    fun should_analysis_struct_type() {
        val code = """
            use std::cmp::Ordering;
            use crate::{Document, Embedding};

            #[derive(Debug, Clone)]
            pub struct DocumentMatch {
                pub score: f32,
                pub embedding_id: String,
                pub embedding: Embedding,
                pub embedded: Document,
            }
            """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "test.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        assertEquals(3, codeContainer.Imports.size)
        assertEquals("std::cmp::Ordering", codeContainer.Imports[0].Source)
        assertEquals("crate::Document", codeContainer.Imports[1].Source)

        assertEquals(4, codeDataStruct.Fields.size)
        assertEquals("score", codeDataStruct.Fields[0].TypeValue)
        assertEquals("f32", codeDataStruct.Fields[0].TypeType)
        assertEquals("crate::Embedding", codeDataStruct.Fields[2].TypeType)
        assertEquals("crate::Document", codeDataStruct.Fields[3].TypeType)
    }

    @Test
    fun should_analysis_first_function_call() {
        val code = """
            use crate::Point;
            
            fn main() {
                let p = Point::new(1, 2);
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "test.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val functionCalls = codeDataStruct.Functions[0].FunctionCalls

        assertEquals(1, functionCalls.size)
        assertEquals("Point", functionCalls[0].NodeName)
        assertEquals("new", functionCalls[0].FunctionName)
    }

    @Test
    fun should_identify_function_call_with_method() {
        val code = """
            use crate::Point;
            
            fn main() {
                let p = Point::new(1, 2);
                p.print();
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "test.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val functionCalls = codeDataStruct.Functions[0].FunctionCalls
        assertEquals(2, functionCalls.size)

        assertEquals("crate::Point", functionCalls[1].NodeName)
        assertEquals("print", functionCalls[1].FunctionName)
        assertEquals("p", functionCalls[1].OriginNodeName)
    }

    @Test
    fun should_identify_self_function_call() {
        val code = """
            use crate::{Document, Embedding};
            
            pub fn add(id: String, embedding: Embedding, document: Document) -> String {
                let entry = Entry::new(id.clone(), embedding, document);
                id
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "test.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val functionCalls = codeDataStruct.Functions[0].FunctionCalls
        assertEquals(2, functionCalls.size)

        assertEquals("String", functionCalls[1].NodeName)
        assertEquals("clone", functionCalls[1].FunctionName)
        assertEquals("id", functionCalls[1].OriginNodeName)
    }

    @Test
    fun should_handle_system_func() {
        val code = """
            use std::sync::Arc;

            pub use embedding::Semantic;
            pub use embedding::semantic::SemanticError;

            pub fn init_semantic_with_path(model_path: &str, tokenizer_path: &str) -> Result<Arc<Semantic>, SemanticError> {
                let model = std::fs::read(model_path).map_err(|_| SemanticError::InitModelReadError)?;
                let tokenizer_data = std::fs::read(tokenizer_path).map_err(|_| SemanticError::InitTokenizerReadError)?;
            
                let result = Semantic::init_semantic(model, tokenizer_data)?;
                Ok(Arc::new(result))
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val functionCalls = codeDataStruct.Functions[0].FunctionCalls
        assertEquals(7, functionCalls.size)

        functionCalls.map {
            println("${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}")
        }

        assertEquals("std::fs::read", functionCalls[0].NodeName)
        assertEquals("map_err", functionCalls[0].FunctionName)
        assertEquals("std::fs::read", functionCalls[0].OriginNodeName)

        assertEquals("std::fs", functionCalls[1].NodeName)
        assertEquals("read", functionCalls[1].FunctionName)
        assertEquals("", functionCalls[1].OriginNodeName)
    }

    @Test
    fun should_process_function_outer_attribute() {
        val code = """
            #[test]
            #[cfg_attr(feature = "ci", ignore)]
            fn test_init_semantic() {

            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val annotations = codeDataStruct.Functions[0].Annotations

        assertEquals(2, annotations.size)
        assertEquals("test", annotations[0].Name)

        assertEquals("cfg_attr", annotations[1].Name)
        assertEquals(2, annotations[1].KeyValues.size)
        assertEquals("feature", annotations[1].KeyValues[0].Key)
        assertEquals("\"ci\"", annotations[1].KeyValues[0].Value)

        assertEquals("ignore", annotations[1].KeyValues[1].Value)
    }


    // Enum type
}
