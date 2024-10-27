package chapi.ast.thrift

import chapi.domain.core.CodeContainer
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ThriftAnalyserTest {
    @Test
    fun `should success parse for struct`() {
        val thriftAnalyser = ThriftAnalyser()

        @Language("Thrift")
        val validThriftCode = """
struct CrazyNesting {
  1: string string_field,
  2: optional set<Insanity> set_field,
  // Do not insert line break as test/go/Makefile.am is removing this line with pattern match
  3: required list<map<set<i32> (python.immutable = ""), map<i32,set<list<map<Insanity,string>(python.immutable = "")> (python.immutable = "")>>>> list_field,
  4: binary binary_field
}
        """
        val filePath = "MyService.thrift"

        // When
        val codeContainer = thriftAnalyser.analysis(validThriftCode, filePath)

        // Then
        assertEquals("MyService.thrift", codeContainer.FullName)
        assertEquals("CrazyNesting", codeContainer.DataStructures[0].NodeName)

        //first field
        assertEquals("string", codeContainer.DataStructures[0].Fields[0].TypeType)
        assertEquals("string_field", codeContainer.DataStructures[0].Fields[0].TypeKey)
        assertEquals("1", codeContainer.DataStructures[0].Fields[0].TypeValue)

        // second field
        assertEquals("set<Insanity>", codeContainer.DataStructures[0].Fields[1].TypeType)
        assertEquals("set_field", codeContainer.DataStructures[0].Fields[1].TypeKey)
        assertEquals("2", codeContainer.DataStructures[0].Fields[1].TypeValue)
        assertEquals(listOf("optional"), codeContainer.DataStructures[0].Fields[1].Modifiers)
    }

    /// union
    @Test
    fun `should success parse for union`() {
        val thriftAnalyser = ThriftAnalyser()

        @Language("Thrift")
        val validThriftCode = """
union SomeUnion {
  1: map<Numberz, UserId> map_thing,
  2: string string_thing,
  3: i32 i32_thing,
  4: Xtruct3 xtruct_thing,
  5: Insanity insanity_thing
}
        """
        val filePath = "MyService.thrift"

        // When
        val codeContainer = thriftAnalyser.analysis(validThriftCode, filePath)

        // Then
        assertEquals("MyService.thrift", codeContainer.FullName)
        assertEquals("SomeUnion", codeContainer.DataStructures[0].NodeName)

        //first field
        assertEquals("map<Numberz,UserId>", codeContainer.DataStructures[0].Fields[0].TypeType)
        assertEquals("map_thing", codeContainer.DataStructures[0].Fields[0].TypeKey)
        assertEquals("1", codeContainer.DataStructures[0].Fields[0].TypeValue)

        // second field
        assertEquals("string", codeContainer.DataStructures[0].Fields[1].TypeType)
        assertEquals("string_thing", codeContainer.DataStructures[0].Fields[1].TypeKey)
        assertEquals("2", codeContainer.DataStructures[0].Fields[1].TypeValue)
    }
}
