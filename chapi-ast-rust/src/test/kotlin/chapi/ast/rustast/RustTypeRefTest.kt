package chapi.ast.rustast

import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RustTypeRefTest {

    private fun analyse(code: String) = RustAnalyser().analysis(code, "test.rs")

    @Nested
    inner class SimpleTypes {
        @Test
        fun `should parse simple type in struct field`() {
            val code = """
                struct User {
                    name: String,
                    age: i32,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val userStruct = container.DataStructures.first { it.NodeName == "User" }
            
            assertEquals(2, userStruct.Fields.size)
            
            val nameField = userStruct.Fields.first { it.TypeValue == "name" }
            assertNotNull(nameField.TypeRef)
            assertEquals("String", nameField.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameField.TypeRef?.kind)
            
            val ageField = userStruct.Fields.first { it.TypeValue == "age" }
            assertNotNull(ageField.TypeRef)
            assertEquals("i32", ageField.TypeRef?.name)
        }

        @Test
        fun `should parse function parameter types`() {
            val code = """
                fn process(name: String, count: i32) {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            
            assertEquals(2, func.Parameters.size)
            
            val nameParam = func.Parameters[0]
            assertNotNull(nameParam.TypeRef)
            assertEquals("String", nameParam.TypeRef?.name)
        }
    }

    @Nested
    inner class ReferenceTypes {
        @Test
        fun `should parse reference type`() {
            val code = """
                struct Container {
                    data: &str,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val containerStruct = container.DataStructures.first { it.NodeName == "Container" }
            val dataField = containerStruct.Fields.first { it.TypeValue == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals(TypeRefKind.REFERENCE, dataField.TypeRef?.kind)
            assertTrue(dataField.TypeRef?.isReference == true)
            assertEquals("str", dataField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse mutable reference type`() {
            val code = """
                fn modify(data: &mut String) {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "modify" }
            val dataParam = func.Parameters.first()
            
            assertNotNull(dataParam.TypeRef)
            assertEquals(TypeRefKind.REFERENCE, dataParam.TypeRef?.kind)
            assertTrue(dataParam.TypeRef?.isConst == false) // mutable
        }
    }

    @Nested
    inner class GenericTypes {
        @Test
        fun `should parse Vec generic type`() {
            val code = """
                struct Config {
                    items: Vec<String>,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val configStruct = container.DataStructures.first { it.NodeName == "Config" }
            val itemsField = configStruct.Fields.first { it.TypeValue == "items" }
            
            assertNotNull(itemsField.TypeRef)
            assertEquals("Vec", itemsField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, itemsField.TypeRef?.kind)
            assertEquals(1, itemsField.TypeRef?.args?.size)
            assertEquals("String", itemsField.TypeRef?.args?.first()?.name)
        }

        @Test
        fun `should parse Option as nullable`() {
            val code = """
                struct User {
                    email: Option<String>,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val userStruct = container.DataStructures.first { it.NodeName == "User" }
            val emailField = userStruct.Fields.first { it.TypeValue == "email" }
            
            assertNotNull(emailField.TypeRef)
            assertEquals("Option", emailField.TypeRef?.name)
            assertEquals(TypeRefKind.NULLABLE, emailField.TypeRef?.kind)
            assertTrue(emailField.TypeRef?.nullable == true)
            assertEquals("String", emailField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse Result type`() {
            val code = """
                struct Response {
                    data: Result<String, Error>,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val responseStruct = container.DataStructures.first { it.NodeName == "Response" }
            val dataField = responseStruct.Fields.first { it.TypeValue == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals("Result", dataField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataField.TypeRef?.kind)
            assertEquals(2, dataField.TypeRef?.args?.size)
        }

        @Test
        fun `should parse HashMap type`() {
            val code = """
                struct Cache {
                    data: HashMap<String, i32>,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val cacheStruct = container.DataStructures.first { it.NodeName == "Cache" }
            val dataField = cacheStruct.Fields.first { it.TypeValue == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals("HashMap", dataField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataField.TypeRef?.kind)
            assertEquals(2, dataField.TypeRef?.args?.size)
        }
    }

    @Nested
    inner class BoxAndSmartPointers {
        @Test
        fun `should parse Box type`() {
            val code = """
                struct Node {
                    child: Box<Node>,
                }
            """.trimIndent()
            
            val container = analyse(code)
            val nodeStruct = container.DataStructures.first { it.NodeName == "Node" }
            val childField = nodeStruct.Fields.first { it.TypeValue == "child" }
            
            assertNotNull(childField.TypeRef)
            assertEquals("Box", childField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, childField.TypeRef?.kind)
            assertEquals("Node", childField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class SliceAndArrayTypes {
        @Test
        fun `should parse array type`() {
            val code = """
                struct Matrix {
                    data: [i32; 3],
                }
            """.trimIndent()
            
            val container = analyse(code)
            val matrixStruct = container.DataStructures.first { it.NodeName == "Matrix" }
            val dataField = matrixStruct.Fields.first { it.TypeValue == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals(TypeRefKind.ARRAY, dataField.TypeRef?.kind)
            assertEquals(3, dataField.TypeRef?.arraySize)
        }
    }

    @Nested
    inner class TypeRefBuilder {
        @Test
        fun `should build reference type from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("&str")
            
            assertEquals(TypeRefKind.REFERENCE, typeRef.kind)
            assertTrue(typeRef.isReference)
            assertEquals("str", typeRef.valueType?.name)
        }

        @Test
        fun `should build mutable reference from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("&mut String")
            
            assertEquals(TypeRefKind.REFERENCE, typeRef.kind)
            assertTrue(typeRef.isReference)
            assertTrue(typeRef.isConst == false) // mutable
        }

        @Test
        fun `should build Option type from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("Option<String>")
            
            assertEquals(TypeRefKind.NULLABLE, typeRef.kind)
            assertTrue(typeRef.nullable)
            assertEquals("String", typeRef.valueType?.name)
        }

        @Test
        fun `should build Vec type from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("Vec<i32>")
            
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals("Vec", typeRef.name)
            assertEquals(1, typeRef.args.size)
        }

        @Test
        fun `should build slice type from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("[u8]")
            
            assertEquals(TypeRefKind.SLICE, typeRef.kind)
            assertTrue(typeRef.isSlice)
        }

        @Test
        fun `should build array type from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("[i32; 10]")
            
            assertEquals(TypeRefKind.ARRAY, typeRef.kind)
            assertEquals(10, typeRef.arraySize)
        }

        @Test
        fun `should build raw pointer from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("*const u8")
            
            assertEquals(TypeRefKind.POINTER, typeRef.kind)
            assertTrue(typeRef.isConst)
        }

        @Test
        fun `should build qualified path from string`() {
            val typeRef = RustTypeRefBuilder.buildFromString("std::collections::HashMap")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("HashMap", typeRef.name)
            assertEquals("std::collections::HashMap", typeRef.qualified)
        }
    }
}
