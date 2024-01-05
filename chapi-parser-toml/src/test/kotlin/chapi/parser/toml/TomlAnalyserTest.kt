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
}
