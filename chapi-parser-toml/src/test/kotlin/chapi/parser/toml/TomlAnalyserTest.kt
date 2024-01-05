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

        assertEquals("database", firstChild.PackageName)
        assertEquals("true", firstChild.Fields[0].TypeValue)
        assertEquals("enabled", firstChild.Fields[0].TypeKey)
        assertEquals("Bool", firstChild.Fields[0].TypeType)
        assertEquals("[8000,8001,8002]", firstChild.Fields[1].TypeValue)
        val childValue = firstChild.Fields[1].ArrayValue
        assertEquals(3, childValue.size)
        assertEquals("8000", childValue[0].TypeValue)
        assertEquals("Int", childValue[1].TypeType)

//        assertEquals("ports", firstChild.Fields[1].TypeKey)
//        assertEquals("Array", firstChild.Fields[1].TypeType)
//        assertEquals("[[\"delta\",\"phi\"],[3.14]]", firstChild.Fields[2].TypeValue)
//        assertEquals("data", firstChild.Fields[2].TypeKey)
//        assertEquals("Array", firstChild.Fields[2].TypeType)
//        assertEquals("{cpu=79.5,case=72.0}", firstChild.Fields[3].TypeValue)
//        assertEquals("temp_targets", firstChild.Fields[3].TypeKey)
//        assertEquals("InlineTable", firstChild.Fields[3].TypeType)
    }
}
