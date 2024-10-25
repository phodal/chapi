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

        val dataStruct = codeContainer.DataStructures.first()
        assertEquals("Person", dataStruct.NodeName)
        assertEquals("example", dataStruct.Module)
        assertEquals("path/to/file.proto", dataStruct.FilePath)
        assertEquals("example", dataStruct.Package)
        assertTrue(dataStruct.Fields.isNotEmpty())

        val field = dataStruct.Fields.first()
        assertEquals("string", field.TypeType)
        assertEquals("name", field.TypeKey)
        assertEquals("1", field.TypeValue)
    }

    /// should parse for enum
    @Test
    fun `should parse valid protobuf code with enum and return a CodeContainer`() {
        // Given
        val protobufCode = "syntax = \"proto3\";\npackage example;\n\nenum PhoneType {\n  MOBILE = 0;\n  HOME = 1;\n  WORK = 2;\n}"
        val filePath = "path/to/file.proto"
        val analyser = ProtobufAnalyser()

        // When
        val codeContainer = analyser.analysis(protobufCode, filePath)

        // Then
        assertNotNull(codeContainer)
        assertEquals("example", codeContainer.PackageName)
        assertTrue(codeContainer.DataStructures.isNotEmpty())

        val dataStruct = codeContainer.DataStructures.first()
        assertEquals("PhoneType", dataStruct.NodeName)
        assertEquals("example", dataStruct.Module)
        assertEquals("path/to/file.proto", dataStruct.FilePath)
        assertEquals("example", dataStruct.Package)
        assertTrue(dataStruct.Fields.isNotEmpty())

        val field = dataStruct.Fields.first()
        assertEquals("PhoneType", field.TypeType)
        assertEquals("MOBILE", field.TypeKey)
        assertEquals("0", field.TypeValue)
    }
}
