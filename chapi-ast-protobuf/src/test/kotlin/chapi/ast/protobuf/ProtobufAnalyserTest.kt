package chapi.ast.protobuf

import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ProtobufAnalyserTest {

    @Test
    fun `should parse valid protobuf code and return a CodeContainer`() {
        // Given
        @Language("protobuf")
        val protobufCode = """
syntax = "proto3";

package example;

message Person {
  string name = 1;
  int32 id = 2;
}"""
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
        @Language("protobuf")
        val protobufCode = """
syntax = "proto3";

package example;

enum PhoneType {
  MOBILE = 0;
  HOME = 1;
  WORK = 2;
}"""
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

    /// should support message in message
    @Test
    fun `should parse valid protobuf code with message in message and return a CodeContainer`() {
        // Given
        @Language("protobuf")
        val protobufCode = """syntax = "proto3";
package example;

message Person {
  string name = 1;
  int32 id = 2;
  message Address {
    string street = 1;
    string city = 2;
  }
}"""
        val filePath = "path/to/file.proto"
        val analyser = ProtobufAnalyser()

        // When
        val codeContainer = analyser.analysis(protobufCode, filePath)

        val dataStruct = codeContainer.DataStructures.first()
        val nestedDataStruct = dataStruct.InnerStructures.first()

        assertEquals("Address", nestedDataStruct.NodeName)

        val nestedField1 = nestedDataStruct.Fields.first()
        assertEquals("string", nestedField1.TypeType)
        assertEquals("street", nestedField1.TypeKey)
        assertEquals("1", nestedField1.TypeValue)

        val nestedField2 = nestedDataStruct.Fields[1]
        assertEquals("string", nestedField2.TypeType)
        assertEquals("city", nestedField2.TypeKey)
        assertEquals("2", nestedField2.TypeValue)
    }
}
