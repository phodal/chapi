package chapi.ast.kotlinast

import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KotlinTypeRefTest {

    private fun analyse(code: String) = KotlinAnalyser().analysis(code, "Test.kt")

    @Nested
    inner class SimpleTypes {
        @Test
        fun `should parse simple type in class parameter`() {
            val code = """
                class User(val name: String, val age: Int)
            """.trimIndent()
            
            val container = analyse(code)
            val userClass = container.DataStructures.first { it.NodeName == "User" }
            
            assertEquals(2, userClass.Fields.size)
            
            val nameField = userClass.Fields.first { it.TypeKey == "name" }
            assertNotNull(nameField.TypeRef)
            assertEquals("String", nameField.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameField.TypeRef?.kind)
            
            val ageField = userClass.Fields.first { it.TypeKey == "age" }
            assertNotNull(ageField.TypeRef)
            assertEquals("Int", ageField.TypeRef?.name)
        }

        @Test
        fun `should parse function parameter types`() {
            val code = """
                class Test {
                    fun process(name: String, count: Int) {}
                }
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
    inner class NullableTypes {
        @Test
        fun `should parse nullable type`() {
            val code = """
                class User(val email: String?)
            """.trimIndent()
            
            val container = analyse(code)
            val userClass = container.DataStructures.first { it.NodeName == "User" }
            val emailField = userClass.Fields.first { it.TypeKey == "email" }
            
            assertNotNull(emailField.TypeRef)
            assertEquals(TypeRefKind.NULLABLE, emailField.TypeRef?.kind)
            assertTrue(emailField.TypeRef?.nullable == true)
            assertEquals("String", emailField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class GenericTypes {
        @Test
        fun `should parse List generic type`() {
            val code = """
                class Config(val items: List<String>)
            """.trimIndent()
            
            val container = analyse(code)
            val configClass = container.DataStructures.first { it.NodeName == "Config" }
            val itemsField = configClass.Fields.first { it.TypeKey == "items" }
            
            assertNotNull(itemsField.TypeRef)
            assertEquals("List", itemsField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, itemsField.TypeRef?.kind)
            assertEquals(1, itemsField.TypeRef?.args?.size)
            assertEquals("String", itemsField.TypeRef?.args?.first()?.name)
            assertEquals("String", itemsField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse Map generic type`() {
            val code = """
                class Cache(val data: Map<String, Int>)
            """.trimIndent()
            
            val container = analyse(code)
            val cacheClass = container.DataStructures.first { it.NodeName == "Cache" }
            val dataField = cacheClass.Fields.first { it.TypeKey == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals("Map", dataField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataField.TypeRef?.kind)
            assertEquals(2, dataField.TypeRef?.args?.size)
            assertEquals("String", dataField.TypeRef?.keyType?.name)
            assertEquals("Int", dataField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse nullable generic type`() {
            val code = """
                class User(val tags: List<String>?)
            """.trimIndent()
            
            val container = analyse(code)
            val userClass = container.DataStructures.first { it.NodeName == "User" }
            val tagsField = userClass.Fields.first { it.TypeKey == "tags" }
            
            assertNotNull(tagsField.TypeRef)
            assertEquals(TypeRefKind.NULLABLE, tagsField.TypeRef?.kind)
            assertTrue(tagsField.TypeRef?.nullable == true)
            
            val innerType = tagsField.TypeRef?.valueType
            assertNotNull(innerType)
            assertEquals("List", innerType.name)
        }
    }

    @Nested
    inner class TypeRefBuilder {
        @Test
        fun `should build simple type from string`() {
            val typeRef = KotlinTypeRefBuilder.buildFromString("String")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("String", typeRef.name)
        }

        @Test
        fun `should build nullable type from string`() {
            val typeRef = KotlinTypeRefBuilder.buildFromString("String?")
            
            assertEquals(TypeRefKind.NULLABLE, typeRef.kind)
            assertTrue(typeRef.nullable)
            assertEquals("String", typeRef.valueType?.name)
        }

        @Test
        fun `should build generic type from string`() {
            val typeRef = KotlinTypeRefBuilder.buildFromString("List<String>")
            
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals("List", typeRef.name)
            assertEquals(1, typeRef.args.size)
            assertEquals("String", typeRef.args.first().name)
        }

        @Test
        fun `should build map type from string`() {
            val typeRef = KotlinTypeRefBuilder.buildFromString("Map<String, Int>")
            
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals("Map", typeRef.name)
            assertEquals(2, typeRef.args.size)
        }

        @Test
        fun `should build qualified type from string`() {
            val typeRef = KotlinTypeRefBuilder.buildFromString("kotlin.collections.List")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("List", typeRef.name)
            assertEquals("kotlin.collections.List", typeRef.qualified)
        }
    }
}
