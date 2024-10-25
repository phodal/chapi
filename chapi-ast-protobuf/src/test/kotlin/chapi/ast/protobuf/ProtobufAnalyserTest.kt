// Save this code in a file named ProtobufAnalyserTest.kt under test directory

package chapi.ast.protobuf

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ProtobufAnalyserTest {

    @Test
    fun `should parse valid protobuf code and return a CodeContainer`() {
        // Given
        val protobufCode = "syntax = \"proto3\";\npackage example;\n\nmessage Person {\n  string name = 1;\n  int32 id = 2;\n}"
        val filePath = "path/to/file.proto"
        val analyser = ProtobufAnalyser()

        // When
        val codeContainer = analyser.analysis(protobufCode, filePath)

        // Then
        assertNotNull(codeContainer)
        assertEquals("example", codeContainer.PackageName)
        assertTrue(codeContainer.DataStructures.isNotEmpty())
    }
}
