package chapi.ast.typescriptast

import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TypeScriptTypeRefTest {

    private fun analyse(code: String) = TypeScriptAnalyser().analysis(code, "test.ts")

    @Nested
    inner class SimpleTypes {
        @Test
        fun `should parse primitive type in parameter`() {
            val code = """
                function test(name: string, age: number): void {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "test" }
            
            assertEquals(2, func.Parameters.size)
            
            val nameParam = func.Parameters[0]
            assertEquals("string", nameParam.TypeType)
            assertNotNull(nameParam.TypeRef)
            assertEquals("string", nameParam.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameParam.TypeRef?.kind)
            
            val ageParam = func.Parameters[1]
            assertEquals("number", ageParam.TypeType)
            assertNotNull(ageParam.TypeRef)
            assertEquals("number", ageParam.TypeRef?.name)
        }

        @Test
        fun `should parse interface field type`() {
            val code = """
                interface User {
                    name: string;
                    age: number;
                }
            """.trimIndent()
            
            val container = analyse(code)
            val userInterface = container.DataStructures.first { it.NodeName == "User" }
            
            assertEquals(2, userInterface.Fields.size)
            
            val nameField = userInterface.Fields.first { it.TypeValue == "name" }
            assertNotNull(nameField.TypeRef)
            assertEquals("string", nameField.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameField.TypeRef?.kind)
        }
    }

    @Nested
    inner class GenericTypes {
        @Test
        fun `should parse Promise generic type`() {
            val code = """
                function fetchData(): Promise<string> {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "fetchData" }
            
            assertTrue(func.MultipleReturns.isNotEmpty())
            val returnType = func.MultipleReturns.first()
            assertNotNull(returnType.TypeRef)
            assertEquals("Promise", returnType.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, returnType.TypeRef?.kind)
            assertEquals(1, returnType.TypeRef?.args?.size)
            assertEquals("string", returnType.TypeRef?.args?.first()?.name)
        }

        @Test
        fun `should parse Array generic type`() {
            val code = """
                interface Config {
                    items: Array<number>;
                }
            """.trimIndent()
            
            val container = analyse(code)
            val configInterface = container.DataStructures.first { it.NodeName == "Config" }
            val itemsField = configInterface.Fields.first { it.TypeValue == "items" }
            
            assertNotNull(itemsField.TypeRef)
            assertEquals("Array", itemsField.TypeRef?.name)
            assertEquals(TypeRefKind.ARRAY, itemsField.TypeRef?.kind)
            assertNotNull(itemsField.TypeRef?.valueType)
            assertEquals("number", itemsField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse Map generic type`() {
            val code = """
                interface Cache {
                    data: Map<string, number>;
                }
            """.trimIndent()
            
            val container = analyse(code)
            val cacheInterface = container.DataStructures.first { it.NodeName == "Cache" }
            val dataField = cacheInterface.Fields.first { it.TypeValue == "data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals("Map", dataField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataField.TypeRef?.kind)
            assertEquals(2, dataField.TypeRef?.args?.size)
        }
    }

    @Nested
    inner class UnionTypes {
        @Test
        fun `should parse union type`() {
            val code = """
                function process(input: string | number): void {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val inputParam = func.Parameters.first()
            
            assertNotNull(inputParam.TypeRef)
            assertEquals(TypeRefKind.UNION, inputParam.TypeRef?.kind)
            assertEquals(2, inputParam.TypeRef?.union?.size)
            
            val unionTypes = inputParam.TypeRef?.union?.map { it.name }
            assertTrue(unionTypes?.contains("string") == true)
            assertTrue(unionTypes?.contains("number") == true)
        }

        @Test
        fun `should parse nullable union type`() {
            val code = """
                function getValue(): string | null {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "getValue" }
            
            assertTrue(func.MultipleReturns.isNotEmpty())
            val returnType = func.MultipleReturns.first()
            assertNotNull(returnType.TypeRef)
            assertEquals(TypeRefKind.UNION, returnType.TypeRef?.kind)
            assertTrue(returnType.TypeRef?.nullable == true)
        }
    }

    @Nested
    inner class ArrayTypes {
        @Test
        fun `should parse array bracket notation`() {
            val code = """
                interface Config {
                    tags: string[];
                }
            """.trimIndent()
            
            val container = analyse(code)
            val configInterface = container.DataStructures.first { it.NodeName == "Config" }
            val tagsField = configInterface.Fields.first { it.TypeValue == "tags" }
            
            assertNotNull(tagsField.TypeRef)
            assertEquals(TypeRefKind.ARRAY, tagsField.TypeRef?.kind)
            assertEquals(1, tagsField.TypeRef?.arrayDimensions)
            assertNotNull(tagsField.TypeRef?.valueType)
            assertEquals("string", tagsField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class TupleTypes {
        @Test
        fun `should parse tuple type`() {
            val code = """
                function getCoords(): [number, number] {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "getCoords" }
            
            assertTrue(func.MultipleReturns.isNotEmpty())
            val returnType = func.MultipleReturns.first()
            assertNotNull(returnType.TypeRef)
            assertEquals(TypeRefKind.TUPLE, returnType.TypeRef?.kind)
            assertEquals(2, returnType.TypeRef?.tupleElements?.size)
            assertTrue(returnType.TypeRef?.tupleElements?.all { it.name == "number" } == true)
        }
    }

    @Nested
    inner class ClassTypes {
        @Test
        fun `should parse class field with type`() {
            val code = """
                class User {
                    private name: string;
                    public age: number;
                }
            """.trimIndent()
            
            val container = analyse(code)
            val userClass = container.DataStructures.first { it.NodeName == "User" }
            
            assertEquals(2, userClass.Fields.size)
            
            val nameField = userClass.Fields.first { it.TypeKey == "name" }
            assertNotNull(nameField.TypeRef)
            assertEquals("string", nameField.TypeRef?.name)
        }
    }
}
