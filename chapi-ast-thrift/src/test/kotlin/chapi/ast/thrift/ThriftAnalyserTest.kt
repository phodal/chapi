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

    var serviceCode = """service ThriftTest
{
  /**
   * Prints "testVoid()" and returns nothing.
   */
  void         testVoid(),

  /**
   * Prints 'testString("%s")' with thing as '%s'
   * @param string thing - the string to print
   * @return string - returns the string 'thing'
   */
  string       testString(1: string thing),

  /**
   * Prints 'testBool("%s")' where '%s' with thing as 'true' or 'false'
   * @param bool  thing - the bool data to print
   * @return bool  - returns the bool 'thing'
   */
  bool         testBool(1: bool thing),

  /**
   * Prints 'testByte("%d")' with thing as '%d'
   * The types i8 and byte are synonyms, use of i8 is encouraged, byte still exists for the sake of compatibility.
   * @param byte thing - the i8/byte to print
   * @return i8 - returns the i8/byte 'thing'
   */
  i8           testByte(1: i8 thing),

  /**
   * Prints 'testI32("%d")' with thing as '%d'
   * @param i32 thing - the i32 to print
   * @return i32 - returns the i32 'thing'
   */
  i32          testI32(1: i32 thing),

  /**
   * Prints 'testI64("%d")' with thing as '%d'
   * @param i64 thing - the i64 to print
   * @return i64 - returns the i64 'thing'
   */
  i64          testI64(1: i64 thing),

  /**
   * Prints 'testDouble("%f")' with thing as '%f'
   * @param double thing - the double to print
   * @return double - returns the double 'thing'
   */
  double       testDouble(1: double thing),

  /**
   * Prints 'testBinary("%s")' where '%s' is a hex-formatted string of thing's data
   * @param binary  thing - the binary data to print
   * @return binary  - returns the binary 'thing'
   */
  binary       testBinary(1: binary thing),

  /**
   * Prints 'testStruct("{%s}")' where thing has been formatted into a string of comma separated values
   * @param Xtruct thing - the Xtruct to print
   * @return Xtruct - returns the Xtruct 'thing'
   */
  Xtruct       testStruct(1: Xtruct thing),

  /**
   * Prints 'testNest("{%s}")' where thing has been formatted into a string of the nested struct
   * @param Xtruct2 thing - the Xtruct2 to print
   * @return Xtruct2 - returns the Xtruct2 'thing'
   */
  Xtruct2      testNest(1: Xtruct2 thing),

  /**
   * Prints 'testMap("{%s")' where thing has been formatted into a string of 'key => value' pairs
   *  separated by commas and new lines
   * @param map<i32,i32> thing - the map<i32,i32> to print
   * @return map<i32,i32> - returns the map<i32,i32> 'thing'
   */
  map<i32,i32> testMap(1: map<i32,i32> thing),

  /**
   * Prints 'testStringMap("{%s}")' where thing has been formatted into a string of 'key => value' pairs
   *  separated by commas and new lines
   * @param map<string,string> thing - the map<string,string> to print
   * @return map<string,string> - returns the map<string,string> 'thing'
   */
  map<string,string> testStringMap(1: map<string,string> thing),

  /**
   * Prints 'testSet("{%s}")' where thing has been formatted into a string of values
   *  separated by commas and new lines
   * @param set<i32> thing - the set<i32> to print
   * @return set<i32> - returns the set<i32> 'thing'
   */
  set<i32>     testSet(1: set<i32> thing),

  /**
   * Prints 'testList("{%s}")' where thing has been formatted into a string of values
   *  separated by commas and new lines
   * @param list<i32> thing - the list<i32> to print
   * @return list<i32> - returns the list<i32> 'thing'
   */
  list<i32>    testList(1: list<i32> thing),

  /**
   * Prints 'testEnum("%d")' where thing has been formatted into its numeric value
   * @param Numberz thing - the Numberz to print
   * @return Numberz - returns the Numberz 'thing'
   */
  Numberz      testEnum(1: Numberz thing),

  /**
   * Prints 'testTypedef("%d")' with thing as '%d'
   * @param UserId thing - the UserId to print
   * @return UserId - returns the UserId 'thing'
   */
  UserId       testTypedef(1: UserId thing),

  /**
   * Prints 'testMapMap("%d")' with hello as '%d'
   * @param i32 hello - the i32 to print
   * @return map<i32,map<i32,i32>> - returns a dictionary with these values:
   *   {-4 => {-4 => -4, -3 => -3, -2 => -2, -1 => -1, }, 4 => {1 => 1, 2 => 2, 3 => 3, 4 => 4, }, }
   */
  map<i32,map<i32,i32>> testMapMap(1: i32 hello),

  /**
   * So you think you've got this all worked out, eh?
   *
   * Creates a map with these values and prints it out:
   *   { 1 => { 2 => argument,
   *            3 => argument,
   *          },
   *     2 => { 6 => <empty Insanity struct>, },
   *   }
   * @return map<UserId, map<Numberz,Insanity>> - a map with the above values
   */
  map<UserId, map<Numberz,Insanity>> testInsanity(1: Insanity argument),

  /**
   * Prints 'testMulti()'
   * @param i8 arg0 -
   * @param i32 arg1 -
   * @param i64 arg2 -
   * @param map<i16, string> arg3 -
   * @param Numberz arg4 -
   * @param UserId arg5 -
   * @return Xtruct - returns an Xtruct with string_thing = "Hello2, byte_thing = arg0, i32_thing = arg1
   *    and i64_thing = arg2
   */
  Xtruct testMulti(1: i8 arg0, 2: i32 arg1, 3: i64 arg2, 4: map<i16, string> arg3, 5: Numberz arg4, 6: UserId arg5),

  /**
   * Print 'testException(%s)' with arg as '%s'
   * @param string arg - a string indication what type of exception to throw
   * if arg == "Xception" throw Xception with errorCode = 1001 and message = arg
   * else if arg == "TException" throw TException
   * else do not throw anything
   */
  void testException(1: string arg) throws(1: Xception err1),

  /**
   * Print 'testMultiException(%s, %s)' with arg0 as '%s' and arg1 as '%s'
   * @param string arg - a string indicating what type of exception to throw
   * if arg0 == "Xception" throw Xception with errorCode = 1001 and message = "This is an Xception"
   * else if arg0 == "Xception2" throw Xception2 with errorCode = 2002 and struct_thing.string_thing = "This is an Xception2"
   * else do not throw anything
   * @return Xtruct - an Xtruct with string_thing = arg1
   */
  Xtruct testMultiException(1: string arg0, 2: string arg1) throws(1: Xception err1, 2: Xception2 err2)

  /**
   * Print 'testOneway(%d): Sleeping...' with secondsToSleep as '%d'
   * sleep 'secondsToSleep'
   * Print 'testOneway(%d): done sleeping!' with secondsToSleep as '%d'
   * @param i32 secondsToSleep - the number of seconds to sleep
   */
  oneway void testOneway(1:i32 secondsToSleep)
}"""

    @Test
    fun `should success parse for service`() {
        val thriftAnalyser = ThriftAnalyser()

        @Language("Thrift")
        val validThriftCode = serviceCode
        val filePath = "MyService.thrift"

        // When
        val codeContainer = thriftAnalyser.analysis(validThriftCode, filePath)

        // Then
        assertEquals("MyService.thrift", codeContainer.FullName)
        val firstNode = codeContainer.DataStructures[0]
        assertEquals("ThriftTest", firstNode.NodeName)
        /// extend
        assertEquals("", firstNode.Extend)

        /// service should equal functions in DataStructures
        assertEquals(22, firstNode.Functions.size)

        // first function
        assertEquals("testVoid", firstNode.Functions[0].Name)
        assertEquals("void", firstNode.Functions[0].ReturnType)
        assertEquals(0, firstNode.Functions[0].Parameters.size)

        // sencod function
        assertEquals("testString", firstNode.Functions[1].Name)
        assertEquals("string", firstNode.Functions[1].ReturnType)
        assertEquals(1, firstNode.Functions[1].Parameters.size)
        assertEquals("string", firstNode.Functions[1].Parameters[0].TypeType)
    }
}
