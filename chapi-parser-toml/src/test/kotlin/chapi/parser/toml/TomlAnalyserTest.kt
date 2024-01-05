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
}
