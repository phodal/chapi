package chapi.ast.rustast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import java.io.File
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
        assertEquals("crate::Point", functionCalls[0].NodeName)
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

        assertEquals("std::fs::read", functionCalls[0].NodeName)
        assertEquals("map_err", functionCalls[0].FunctionName)
        assertEquals("std::fs::read", functionCalls[0].OriginNodeName)

        assertEquals("std::fs", functionCalls[1].NodeName)
        assertEquals("read", functionCalls[1].FunctionName)
        assertEquals("std::fs", functionCalls[1].OriginNodeName)
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

    @Test
    fun should_inference_attribute_type() {
        val code = """
            use serde::{Serialize, Deserialize};

            #[derive(Serialize, Deserialize, Debug)]
            struct Point {
                x: i32,
                y: i32,
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val annotations = codeDataStruct.Annotations

        assertEquals(1, annotations.size)
        assertEquals("derive", annotations[0].Name)
        assertEquals("serde::Serialize", annotations[0].KeyValues[0].Value)
    }


    @Test
    fun should_process_enum_type() {
        val code = """
            enum Color {
                Red,
                Green,
                Blue,
                RgbColor(u8, u8, u8), // tuple
                CmykColor { cyan: u8, magenta: u8, yellow: u8, black: u8 }, // struct
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]

        assertEquals(DataStructType.ENUM, codeDataStruct.Type)
        assertEquals(5, codeDataStruct.Fields.size)

        assertEquals("Red", codeDataStruct.Fields[0].TypeValue)
        assertEquals("Green", codeDataStruct.Fields[1].TypeValue)
        assertEquals("Blue", codeDataStruct.Fields[2].TypeValue)
        assertEquals("RgbColor", codeDataStruct.Fields[3].TypeValue)
        assertEquals("", codeDataStruct.Fields[4].TypeType)

        assertEquals("CmykColor", codeDataStruct.Fields[4].TypeValue)
    }

    @Test
    fun should_identify_rocket_rs_url() {
        val code = """
            #[macro_use] extern crate rocket;

            #[get("/hello/<name>/<age>")]
            fn hello(name: &str, age: u8) -> String {
                format!("Hello, {} year old named {}!", age, name)
            }
            
            #[launch]
            fn rocket() -> _ {
                rocket::build().mount("/", routes![hello])
            }
            """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val firstFunction = codeDataStruct.Functions[0]
        // annotation
        assertEquals(1, firstFunction.Annotations.size)
        assertEquals("get", firstFunction.Annotations[0].Name)

        assertEquals(1, firstFunction.Annotations[0].KeyValues.size)
        assertEquals("\"/hello/<name>/<age>\"", firstFunction.Annotations[0].KeyValues[0].Value)

        val secondFunction = codeDataStruct.Functions[1]
        assertEquals("rocket", secondFunction.Name)
        assertEquals("_", secondFunction.ReturnType)
        assertEquals(2, secondFunction.FunctionCalls.size)
        assertEquals("rocket", secondFunction.FunctionCalls[0].NodeName)
    }

    @Test
    fun should_handle_actix_web_framework() {
        val code = """
            use actix_web::{get, web, App, HttpServer, Responder};
            
            #[get("/")]
            async fn hello() -> impl Responder {
                "Hello world!"
            }
            
            #[actix_web::main]
            async fn main() -> std::io::Result<()> {
                HttpServer::new(|| {
                    App::new()
                        .service(hello)
                        .service(echo)
                        .route("/hey", web::get().to(manual_hello))
                })
                .bind(("127.0.0.1", 8080))?
                .run()
                .await
            }
            """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]
        val firstFunction = codeDataStruct.Functions[0]
        // annotation
        assertEquals(1, firstFunction.Annotations.size)
        assertEquals("get", firstFunction.Annotations[0].Name)

        assertEquals(1, firstFunction.Annotations[0].KeyValues.size)
        assertEquals("\"/\"", firstFunction.Annotations[0].KeyValues[0].Value)

        val secondFunction = codeDataStruct.Functions[1]
        assertEquals("main", secondFunction.Name)
        assertEquals("std::io::Result", secondFunction.ReturnType)
        assertEquals(9, secondFunction.FunctionCalls.size)

        val calls = secondFunction.FunctionCalls.map {
            "${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}"
        }.joinToString("\n")

        assertEquals(
            calls, """
            actix_web::HttpServer -> run -> HttpServer::new
            actix_web::HttpServer -> bind -> HttpServer::new
            actix_web::HttpServer -> new -> HttpServer
            actix_web::App -> route -> App::new
            actix_web::App -> service -> App::new
            actix_web::App -> service -> App::new
            actix_web::App -> new -> App
            actix_web::web -> to -> web::get
            actix_web::web -> get -> web
            """.trimIndent()
        )
    }

    @Test
    fun should_handle_test_mod() {
        val code = """
            #[cfg(test)]
            mod tests {
                use super::*;
            
                #[test]
                fn test_add() {
                    assert_eq!(add(1, 2), 3);
                }
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures[0]

        assertEquals("tests", codeDataStruct.Module)
    }

    @Test
    fun should_handle_for_comments() {
        val code = """
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

fn main() {
    uniffi::generate_scaffolding("src/inference.udl").unwrap();
}

""".trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures
        assertEquals(1, codeDataStruct.size)
    }

    @Test
    fun should_handle_for_result() {
        val code = """
            use std::sync::Arc;

            pub use embedding::Semantic;
            pub use embedding::semantic::SemanticError;

            pub fn init_semantic(model: Vec<u8>, tokenizer_data: Vec<u8>) -> Result<Arc<Semantic>, SemanticError> {
                let result = Semantic::init_semantic(model, tokenizer_data)?;
                Ok(Arc::new(result))
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures
        assertEquals(1, codeDataStruct.size)

        val firstFunction = codeDataStruct[0].Functions[0]
        assertEquals("Result", firstFunction.ReturnType)
        assertEquals(3, firstFunction.MultipleReturns.size)
        assertEquals("embedding::Semantic", firstFunction.MultipleReturns[0].TypeType)
        assertEquals("std::sync::Arc", firstFunction.MultipleReturns[1].TypeType)
        assertEquals("embedding::semantic::SemanticError", firstFunction.MultipleReturns[2].TypeType)
    }

    @Test
    fun should_handle_for_node_type_in_function_call() {
        val code = """
            use std::sync::Arc;

            pub use embedding::Semantic;
            pub use embedding::semantic::SemanticError;

            pub fn init_semantic(model: Vec<u8>, tokenizer_data: Vec<u8>) -> Result<Arc<Semantic>, SemanticError> {
                let result = Semantic::init_semantic(model, tokenizer_data)?;
                Ok(Arc::new(result))
            }
            
            pub fn embed() -> Embedding {
                let model = std::fs::read("../model/model.onnx").unwrap();
                let tokenizer_data = std::fs::read("../model/tokenizer.json").unwrap();

                let semantic = init_semantic(model, tokenizer_data).unwrap();
                semantic.embed("hello world").unwrap()
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures
        val embedFunc = codeDataStruct[0].Functions[1]

        val functionCalls = embedFunc.FunctionCalls
        val outputs = functionCalls.joinToString("\n") {
            "${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}"
        }

        functionCalls.forEach {
            println("${it.Package} -> ${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}")
        }

        assertEquals(8, functionCalls.size)
        assertEquals(
            outputs, """
            std::fs::read -> unwrap -> std::fs::read
            std::fs -> read -> std::fs
            std::fs::read -> unwrap -> std::fs::read
            std::fs -> read -> std::fs
            embedding::Semantic -> unwrap -> init_semantic
            embedding::Semantic -> init_semantic -> init_semantic
            semantic.embed -> unwrap -> semantic.embed
            embedding::Semantic -> embed -> semantic
        """.trimIndent()
        )
    }

    @Test
    fun should_handle_for_marco_call() {
        val code = """
            fn test_init_semantic() {
                let model = std::fs::read("../model/model.onnx").unwrap();
                let tokenizer_data = std::fs::read("../model/tokenizer.json").unwrap();

                let semantic = init_semantic(model, tokenizer_data).unwrap();
                let embedding = semantic.embed("hello world").unwrap();
                assert_eq!(embedding.len(), 128);
            }
        """.trimIndent()

        val codeContainer = RustAnalyser().analysis(code, "lib.rs")
        val codeDataStruct = codeContainer.DataStructures
        val testFunc = codeDataStruct[0].Functions[0]

        val functionCalls = testFunc.FunctionCalls
        val outputs = functionCalls.joinToString("\n") {
            "${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}"
        }

        assertEquals(9, functionCalls.size)
        assertEquals(
            outputs, """
            std::fs::read -> unwrap -> std::fs::read
            std::fs -> read -> std::fs
            std::fs::read -> unwrap -> std::fs::read
            std::fs -> read -> std::fs
            init_semantic -> unwrap -> init_semantic
            init_semantic -> init_semantic -> init_semantic
            semantic.embed -> unwrap -> semantic.embed
            init_semantic -> embed -> semantic
            assert_eq -> assert_eq -> assert_eq
        """.trimIndent()
        )
    }

    @Test
    fun should_handle_for_function_call() {
        val code = """
            use crate::domain::git::coco_tag::CocoTag;

            pub struct GitTagParser {
                tags: Vec<CocoTag>
            }
            
            impl Default for GitTagParser {
                fn default() -> Self {
                    GitTagParser { tags: vec![] }
                }
            }
            
            impl GitTagParser {
                pub fn parse(str: &str) -> Vec<CocoTag> {
                    vec![]
                }
            }
            
            #[cfg(test)]
            mod test {
                use crate::infrastructure::git::git_tag_parser::GitTagParser;
            
                #[test]
                pub fn should_parse_commit_id() {
                    let input = "92fffa9b 1571521692  (tag: v0.21.0)
            1fec6a3c 1570655888
            71db1ab2 1541570931";
            
                    let tags = GitTagParser::parse(input);
                    assert_eq!(1, tags.len());
                }
            }
        """.trimIndent()

        val filePath = listOf("src", "infrastructure", "git", "git_tag_parser.rs").joinToString(File.separator)

        val codeContainer = RustAnalyser().analysis(code, filePath)
        val codeDataStruct = codeContainer.DataStructures

        assertEquals(2, codeDataStruct.size)
        val testFunc = codeDataStruct[0].Functions[0]
        testFunc.FunctionCalls.map {
            println("${it.Package} -> ${it.NodeName} -> ${it.FunctionName} -> ${it.OriginNodeName}")
        }

        assertEquals(2, testFunc.FunctionCalls.size)
        assertEquals("infrastructure::git::git_tag_parser", testFunc.FunctionCalls[0].Package)
    }
}
