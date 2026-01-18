package chapi.ast.javaast

import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class JavaTypeRefTest {

    private fun analyse(code: String) = JavaAnalyser().identFullInfo(code, "Test.java")

    @Nested
    inner class SimpleTypes {
        @Test
        fun `should parse primitive type in parameter`() {
            val code = """
                public class Test {
                    public void process(int count, boolean flag) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            
            assertEquals(2, func.Parameters.size)
            
            val countParam = func.Parameters[0]
            assertEquals("int", countParam.TypeType)
            assertNotNull(countParam.TypeRef)
            assertEquals("int", countParam.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, countParam.TypeRef?.kind)
            
            val flagParam = func.Parameters[1]
            assertEquals("boolean", flagParam.TypeType)
            assertNotNull(flagParam.TypeRef)
            assertEquals("boolean", flagParam.TypeRef?.name)
        }

        @Test
        fun `should parse class type in parameter`() {
            val code = """
                public class Test {
                    public void setName(String name) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "setName" }
            val nameParam = func.Parameters.first()
            
            assertNotNull(nameParam.TypeRef)
            assertEquals("String", nameParam.TypeRef?.name)
            assertEquals(TypeRefKind.SIMPLE, nameParam.TypeRef?.kind)
        }
    }

    @Nested
    inner class GenericTypes {
        @Test
        fun `should parse List generic type`() {
            val code = """
                import java.util.List;
                public class Test {
                    public void process(List<String> items) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val itemsParam = func.Parameters.first()
            
            assertNotNull(itemsParam.TypeRef)
            assertEquals("List", itemsParam.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, itemsParam.TypeRef?.kind)
            assertEquals(1, itemsParam.TypeRef?.args?.size)
            assertEquals("String", itemsParam.TypeRef?.args?.first()?.name)
            assertEquals("String", itemsParam.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse Map generic type`() {
            val code = """
                import java.util.Map;
                public class Test {
                    public void process(Map<String, Integer> data) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val dataParam = func.Parameters.first()
            
            assertNotNull(dataParam.TypeRef)
            assertEquals("Map", dataParam.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataParam.TypeRef?.kind)
            assertEquals(2, dataParam.TypeRef?.args?.size)
            assertEquals("String", dataParam.TypeRef?.keyType?.name)
            assertEquals("Integer", dataParam.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse Optional as nullable`() {
            val code = """
                import java.util.Optional;
                public class Test {
                    public void process(Optional<String> value) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val valueParam = func.Parameters.first()
            
            assertNotNull(valueParam.TypeRef)
            assertEquals("Optional", valueParam.TypeRef?.name)
            assertEquals(TypeRefKind.NULLABLE, valueParam.TypeRef?.kind)
            assertTrue(valueParam.TypeRef?.nullable == true)
            assertEquals("String", valueParam.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse nested generic type`() {
            val code = """
                import java.util.List;
                import java.util.Map;
                public class Test {
                    public void process(Map<String, List<Integer>> data) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val dataParam = func.Parameters.first()
            
            assertNotNull(dataParam.TypeRef)
            assertEquals("Map", dataParam.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, dataParam.TypeRef?.kind)
            assertEquals(2, dataParam.TypeRef?.args?.size)
            
            val valueArg = dataParam.TypeRef?.args?.get(1)
            assertEquals("List", valueArg?.name)
            assertEquals(TypeRefKind.GENERIC, valueArg?.kind)
        }
    }

    @Nested
    inner class ArrayTypes {
        @Test
        fun `should parse primitive array type`() {
            val code = """
                public class Test {
                    public void process(int[] numbers) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val numbersParam = func.Parameters.first()
            
            assertNotNull(numbersParam.TypeRef)
            assertEquals(TypeRefKind.ARRAY, numbersParam.TypeRef?.kind)
            assertEquals(1, numbersParam.TypeRef?.arrayDimensions)
            assertEquals("int", numbersParam.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse object array type`() {
            val code = """
                public class Test {
                    public void process(String[] names) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val namesParam = func.Parameters.first()
            
            assertNotNull(namesParam.TypeRef)
            assertEquals(TypeRefKind.ARRAY, namesParam.TypeRef?.kind)
            assertEquals("String", namesParam.TypeRef?.valueType?.name)
        }

        @Test
        fun `should parse multi-dimensional array`() {
            val code = """
                public class Test {
                    public void process(int[][] matrix) {}
                }
            """.trimIndent()
            
            val container = analyse(code)
            val func = container.DataStructures.flatMap { it.Functions }.first { it.Name == "process" }
            val matrixParam = func.Parameters.first()
            
            assertNotNull(matrixParam.TypeRef)
            assertEquals(TypeRefKind.ARRAY, matrixParam.TypeRef?.kind)
            assertEquals(2, matrixParam.TypeRef?.arrayDimensions)
        }
    }

    @Nested
    inner class FieldTypes {
        @Test
        fun `should parse field with TypeRef`() {
            val code = """
                import java.util.List;
                public class Test {
                    private List<String> items;
                }
            """.trimIndent()
            
            val container = analyse(code)
            val testClass = container.DataStructures.first { it.NodeName == "Test" }
            val itemsField = testClass.Fields.first { it.TypeKey == "items" }
            
            assertNotNull(itemsField.TypeRef)
            assertEquals("List", itemsField.TypeRef?.name)
            assertEquals(TypeRefKind.GENERIC, itemsField.TypeRef?.kind)
        }
    }

    @Nested
    inner class TypeRefBuilder {
        @Test
        fun `should build simple type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("String")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("String", typeRef.name)
        }

        @Test
        fun `should build primitive type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("int")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("int", typeRef.name)
        }

        @Test
        fun `should build array type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("int[]")
            
            assertEquals(TypeRefKind.ARRAY, typeRef.kind)
            assertEquals(1, typeRef.arrayDimensions)
            assertEquals("int", typeRef.valueType?.name)
        }

        @Test
        fun `should build multi-dimensional array from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("String[][]")
            
            assertEquals(TypeRefKind.ARRAY, typeRef.kind)
            assertEquals(2, typeRef.arrayDimensions)
        }

        @Test
        fun `should build generic type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("List<String>")
            
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals("List", typeRef.name)
            assertEquals(1, typeRef.args.size)
            assertEquals("String", typeRef.args.first().name)
        }

        @Test
        fun `should build nested generic type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("Map<String, List<Integer>>")
            
            assertEquals(TypeRefKind.GENERIC, typeRef.kind)
            assertEquals("Map", typeRef.name)
            assertEquals(2, typeRef.args.size)
            assertEquals("String", typeRef.args[0].name)
            assertEquals("List", typeRef.args[1].name)
        }

        @Test
        fun `should build qualified type from string`() {
            val typeRef = JavaTypeRefBuilder.buildFromString("java.util.List")
            
            assertEquals(TypeRefKind.SIMPLE, typeRef.kind)
            assertEquals("List", typeRef.name)
            assertEquals("java.util.List", typeRef.qualified)
        }
    }
}
