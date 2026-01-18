package chapi.ast.goast

import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GoTypeRefTest {

    private fun analyse(code: String) = GoAnalyser().analysis(code, "test.go")

    @Nested
    inner class SimpleTypes {
        @Test
        fun `should parse simple type in struct field`() {
            val code = """
                package main
                
                type User struct {
                    Name string
                    Age  int
                }
            """.trimIndent()
            
            val container = analyse(code)
            val userStruct = container.DataStructures.first { it.NodeName == "User" }
            
            assertEquals(2, userStruct.Fields.size)
            
            val nameField = userStruct.Fields.first { it.TypeValue == "Name" }
            assertNotNull(nameField.TypeRef)
            assertEquals("string", nameField.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameField.TypeRef?.kind)
            
            val ageField = userStruct.Fields.first { it.TypeValue == "Age" }
            assertNotNull(ageField.TypeRef)
            assertEquals("int", ageField.TypeRef?.name)
        }

        @Test
        fun `should parse function parameter types`() {
            val code = """
                package main
                
                func process(name string, count int) {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            
            assertEquals(2, func.Parameters.size)
            
            val nameParam = func.Parameters[0]
            assertEquals("string", nameParam.TypeType)
            assertNotNull(nameParam.TypeRef)
            assertEquals("string", nameParam.TypeRef?.name)
        }
    }

    @Nested
    inner class PointerTypes {
        @Test
        fun `should parse pointer type in struct field`() {
            val code = """
                package main
                
                type Node struct {
                    Next *Node
                }
            """.trimIndent()
            
            val container = analyse(code)
            val nodeStruct = container.DataStructures.first { it.NodeName == "Node" }
            val nextField = nodeStruct.Fields.first { it.TypeValue == "Next" }
            
            assertNotNull(nextField.TypeRef)
            assertEquals(TypeRefKind.POINTER, nextField.TypeRef?.kind)
            assertEquals(1, nextField.TypeRef?.pointerDepth)
            assertNotNull(nextField.TypeRef?.valueType)
            assertEquals("Node", nextField.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse pointer parameter`() {
            val code = """
                package main
                
                func modify(data *string) {}
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "modify" }
            val dataParam = func.Parameters.first()
            
            assertNotNull(dataParam.TypeRef)
            assertEquals(TypeRefKind.POINTER, dataParam.TypeRef?.kind)
            assertEquals("string", dataParam.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class SliceTypes {
        @Test
        fun `should parse slice type`() {
            val code = """
                package main
                
                type Config struct {
                    Items []string
                }
            """.trimIndent()
            
            val container = analyse(code)
            val configStruct = container.DataStructures.first { it.NodeName == "Config" }
            val itemsField = configStruct.Fields.first { it.TypeValue == "Items" }
            
            assertNotNull(itemsField.TypeRef)
            assertEquals(TypeRefKind.SLICE, itemsField.TypeRef?.kind)
            assertTrue(itemsField.TypeRef?.isSlice == true)
            assertNotNull(itemsField.TypeRef?.valueType)
            assertEquals("string", itemsField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class MapTypes {
        @Test
        fun `should parse map type`() {
            val code = """
                package main
                
                type Cache struct {
                    Data map[string]int
                }
            """.trimIndent()
            
            val container = analyse(code)
            val cacheStruct = container.DataStructures.first { it.NodeName == "Cache" }
            val dataField = cacheStruct.Fields.first { it.TypeValue == "Data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals(TypeRefKind.MAP, dataField.TypeRef?.kind)
            assertNotNull(dataField.TypeRef?.keyType)
            assertEquals("string", dataField.TypeRef?.keyType?.name)
            assertNotNull(dataField.TypeRef?.valueType)
            assertEquals("int", dataField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class ChannelTypes {
        @Test
        fun `should parse channel type`() {
            val code = """
                package main
                
                type Worker struct {
                    Jobs chan int
                }
            """.trimIndent()
            
            val container = analyse(code)
            val workerStruct = container.DataStructures.first { it.NodeName == "Worker" }
            val jobsField = workerStruct.Fields.first { it.TypeValue == "Jobs" }
            
            assertNotNull(jobsField.TypeRef)
            assertEquals(TypeRefKind.CHANNEL, jobsField.TypeRef?.kind)
            assertNotNull(jobsField.TypeRef?.valueType)
            assertEquals("int", jobsField.TypeRef?.valueType?.name)
        }
    }

    @Nested
    inner class ArrayTypes {
        @Test
        fun `should parse fixed array type`() {
            val code = """
                package main
                
                type Matrix struct {
                    Data [3]int
                }
            """.trimIndent()
            
            val container = analyse(code)
            val matrixStruct = container.DataStructures.first { it.NodeName == "Matrix" }
            val dataField = matrixStruct.Fields.first { it.TypeValue == "Data" }
            
            assertNotNull(dataField.TypeRef)
            assertEquals(TypeRefKind.ARRAY, dataField.TypeRef?.kind)
            assertEquals(1, dataField.TypeRef?.arrayDimensions)
            assertEquals(3, dataField.TypeRef?.arraySize)
        }
    }

    @Nested
    inner class InterfaceTypes {
        @Test
        fun `should parse empty interface type`() {
            val code = """
                package main
                
                type Container struct {
                    Value interface{}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val containerStruct = container.DataStructures.first { it.NodeName == "Container" }
            val valueField = containerStruct.Fields.first { it.TypeValue == "Value" }
            
            assertNotNull(valueField.TypeRef)
            assertEquals(TypeRefKind.INTERFACE, valueField.TypeRef?.kind)
        }
    }

    @Nested
    inner class ReturnTypes {
        @Test
        fun `should parse function return type`() {
            val code = """
                package main
                
                func getName() string {
                    return "test"
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "getName" }
            
            assertTrue(func.MultipleReturns.isNotEmpty())
            val returnType = func.MultipleReturns.first()
            assertNotNull(returnType.TypeRef)
            assertEquals("string", returnType.TypeRef?.name)
        }

        @Test
        fun `should parse multiple return types`() {
            val code = """
                package main
                
                func divide(a, b int) (int, error) {
                    return a / b, nil
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "divide" }
            
            assertEquals(2, func.MultipleReturns.size)
        }
    }

    @Nested
    inner class TypeRefBuilder {
        @Test
        fun `should build pointer type from string`() {
            val typeRef = GoTypeRefBuilder.buildFromString("*string")
            
            assertEquals(TypeRefKind.POINTER, typeRef.kind)
            assertEquals(1, typeRef.pointerDepth)
            assertEquals("string", typeRef.valueType?.name)
        }

        @Test
        fun `should build slice type from string`() {
            val typeRef = GoTypeRefBuilder.buildFromString("[]int")
            
            assertEquals(TypeRefKind.SLICE, typeRef.kind)
            assertTrue(typeRef.isSlice)
            assertEquals("int", typeRef.valueType?.name)
        }

        @Test
        fun `should build map type from string`() {
            val typeRef = GoTypeRefBuilder.buildFromString("map[string]int")
            
            assertEquals(TypeRefKind.MAP, typeRef.kind)
            assertEquals("string", typeRef.keyType?.name)
            assertEquals("int", typeRef.valueType?.name)
        }

        @Test
        fun `should build channel type from string`() {
            val typeRef = GoTypeRefBuilder.buildFromString("chan int")
            
            assertEquals(TypeRefKind.CHANNEL, typeRef.kind)
            assertEquals("int", typeRef.valueType?.name)
        }
    }
}
