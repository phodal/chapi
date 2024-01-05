package chapi.parser.toml;

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TomlAnalyserTest {

    @Test
    fun `analysis should return CodeContainer with correct field count`() {
        // given
        val tomlCode = "title = \"TOML Example\""
        val analyser = TomlAnalyser()

        // when
        val container = analyser.analysis(tomlCode, "path/to/file.toml")

        // then
        assertEquals(1, container.Fields.size)
        assertEquals("title", container.Fields[0].TypeKey)
        assertEquals("TOML Example", container.Fields[0].TypeValue)
        assertEquals("String", container.Fields[0].TypeType)
    }

    @Test
    fun `analysis should return CodeContainer with correct field count 2`() {
        // given
        val tomlCode = """
            title = "TOML Example"
            
            [owner]
            name = "Tom Preston-Werner"
            dob = 1979-05-27T07:32:00-08:00
        """.trimIndent()
        val analyser = TomlAnalyser()

        // when
        val container = analyser.analysis(tomlCode, "path/to/file.toml")

        // then
        assertEquals(1, container.Fields.size)
        val childContainer = container.Containers
        assertEquals(1, childContainer.size)
        val firstChild = childContainer[0]

        assertEquals("owner", firstChild.PackageName)
        assertEquals("Tom Preston-Werner", firstChild.Fields[0].TypeValue)
        assertEquals("name", firstChild.Fields[0].TypeKey)
        assertEquals("String", firstChild.Fields[0].TypeType)
        assertEquals("1979-05-27T07:32:00-08:00", firstChild.Fields[1].TypeValue)
        assertEquals("dob", firstChild.Fields[1].TypeKey)
        assertEquals("Date", firstChild.Fields[1].TypeType)
    }

    @Test
    fun `analysis should return CodeContainer with correct field count 3`() {
        // given
        val tomlCode = """
            [database]
            enabled = true
            ports = [ 8000, 8001, 8002 ]
            data = [ ["delta", "phi"], [3.14] ]
            temp_targets = { cpu = 79.5, case = 72.0 }
        """.trimIndent()
        val analyser = TomlAnalyser()

        // when
        val container = analyser.analysis(tomlCode, "path/to/file.toml")

        // then
        assertEquals(0, container.Fields.size)
        val childContainer = container.Containers
        assertEquals(1, childContainer.size)
        val firstChild = childContainer[0]

        assertEquals("[8000,8001,8002]", firstChild.Fields[1].TypeValue)
        val childValue = firstChild.Fields[1].ArrayValue
        assertEquals(3, childValue.size)
        assertEquals("8000", childValue[0].TypeValue)
        assertEquals("Int", childValue[1].TypeType)

        val secondArray = firstChild.Fields[2]
        assertEquals("[[\"delta\",\"phi\"],[3.14]]", secondArray.TypeValue)

        assertEquals("data", secondArray.TypeKey)
        assertEquals("Array", secondArray.TypeType)
        assertEquals("Array", secondArray.ArrayValue[0].TypeType)
        assertEquals("String", secondArray.ArrayValue[0].ArrayValue[0].TypeType)

        assertEquals("Array", secondArray.ArrayValue[1].TypeType)

        val childFloat = secondArray.ArrayValue[1].ArrayValue[0]
        assertEquals("3.14", childFloat.TypeValue)
        assertEquals("Float", childFloat.TypeType)
    }

    @Test
    fun `analysis should return CodeContainer with correct field count 4`() {
        // given
        val tomlCode = """
            [database]
            enabled = true
            ports = [ 8000, 8001, 8002 ]
            data = [ ["delta", "phi"], [3.14] ]
            temp_targets = { cpu = 79.5, case = 72.0 }
        """.trimIndent()

        val analyser = TomlAnalyser()

        val container = analyser.analysis(tomlCode, "path/to/file.toml")

        assertEquals(0, container.Fields.size)
        val childContainer = container.Containers
        assertEquals(1, childContainer.size)

        val firstChild = childContainer[0]
        val thirdField = firstChild.Fields[3]
        assertEquals("{cpu=79.5,case=72.0}", thirdField.TypeValue)
        assertEquals("temp_targets", thirdField.TypeKey)
        assertEquals("InlineTable", thirdField.TypeType)

        val childField = thirdField.ArrayValue[0]
        assertEquals("cpu", childField.TypeKey)
        assertEquals("79.5", childField.TypeValue)
        assertEquals("Float", childField.TypeType)
    }
}
