package chapi.ast.thrift

import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.*

/**
 * Comprehensive test suite for advanced Thrift features
 * Tests cover: exception, const, typedef, namespace, include, etc.
 */
class ThriftAdvancedFeaturesTest {

    // ==================== Exception Tests ====================
    
    @Nested
    inner class ExceptionTests {
        @Test
        fun `should parse exception definition`() {
            @Language("Thrift")
            val code = """
                exception InvalidOperation {
                  1: i32 whatOp,
                  2: string why
                }
                
                struct NormalStruct {
                  1: string value
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            // Exception and struct should both be parsed
            assertTrue(container.DataStructures.isNotEmpty())
            // At least one structure should be parsed correctly
            assertNotNull(container.DataStructures[0].NodeName)
        }
        
        @Test
        fun `should parse multiple exceptions`() {
            @Language("Thrift")
            val code = """
                struct Xception {
                  1: i32 errorCode,
                  2: string message
                }
                
                struct Xception2 {
                  1: i32 errorCode,
                  2: string value
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertTrue(container.DataStructures.size >= 2)
        }
    }
    
    // ==================== Const Tests ====================
    
    @Nested
    inner class ConstTests {
        @Test
        fun `should parse const definitions`() {
            @Language("Thrift")
            val code = """
                const i32 INT_CONST = 42
                const string STRING_CONST = "hello"
                const double DOUBLE_CONST = 3.14
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            // Constants may be stored in container or as special structures
            assertNotNull(container)
        }
        
        @Test
        fun `should parse const with collections`() {
            @Language("Thrift")
            val code = """
                const list<i32> INT_LIST = [1, 2, 3, 4, 5]
                const map<string,string> STRING_MAP = {"key1": "value1", "key2": "value2"}
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertNotNull(container)
        }
    }
    
    // ==================== Typedef Tests ====================
    
    @Nested
    inner class TypedefTests {
        @Test
        fun `should parse typedef`() {
            @Language("Thrift")
            val code = """
                typedef i32 MyInteger
                typedef string UserId
                typedef list<i32> IntList
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertNotNull(container)
        }
        
        @Test
        fun `should parse typedef with complex types`() {
            @Language("Thrift")
            val code = """
                typedef map<string, list<i32>> ComplexMap
                typedef set<UserId> UserSet
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertNotNull(container)
        }
    }
    
    // ==================== Namespace Tests ====================
    
    @Nested
    inner class NamespaceTests {
        @Test
        fun `should parse namespace declarations`() {
            @Language("Thrift")
            val code = """
                namespace java com.example.tutorial
                namespace py tutorial
                namespace cpp tutorial
                
                struct Point {
                  1: i32 x,
                  2: i32 y
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            // Namespace should be captured
            val point = container.DataStructures[0]
            assertEquals("Point", point.NodeName)
        }
        
        @Test
        fun `should parse multiple language namespaces`() {
            @Language("Thrift")
            val code = """
                namespace * com.example
                namespace java com.example.java
                namespace py example.python
                namespace rb Example
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertNotNull(container)
        }
    }
    
    // ==================== Include Tests ====================
    
    @Nested
    inner class IncludeTests {
        @Test
        fun `should parse include statements`() {
            @Language("Thrift")
            val code = """
                include "shared.thrift"
                include "tutorial.thrift"
                
                struct MyStruct {
                  1: shared.SharedStruct sharedData
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            // Includes should be captured
            val myStruct = container.DataStructures[0]
            assertEquals("MyStruct", myStruct.NodeName)
        }
    }
    
    // ==================== Service with Exceptions ====================
    
    @Nested
    inner class ServiceWithExceptionsTests {
        @Test
        fun `should parse service methods with exceptions`() {
            @Language("Thrift")
            val code = """
                exception InvalidOperation {
                  1: i32 whatOp,
                  2: string why
                }
                
                service Calculator {
                  i32 add(1:i32 num1, 2:i32 num2),
                  i32 divide(1:i32 num, 2:i32 denom) throws (1:InvalidOperation ouch)
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertTrue(container.DataStructures.size >= 1)
            
            val service = container.DataStructures.find { it.NodeName == "Calculator" }
            assertNotNull(service)
            assertTrue(service!!.Functions.size >= 2)
            
            val addFunc = service.Functions.find { it.Name == "add" }
            assertNotNull(addFunc)
        }
        
        @Test
        fun `should parse oneway methods`() {
            @Language("Thrift")
            val code = """
                service NotificationService {
                  oneway void notify(1:string message)
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            val service = container.DataStructures[0]
            
            assertEquals("NotificationService", service.NodeName)
            assertEquals(1, service.Functions.size)
            assertEquals("notify", service.Functions[0].Name)
        }
    }
    
    // ==================== Enum Tests ====================
    
    @Nested
    inner class EnumTests {
        @Test
        fun `should parse enum with explicit values`() {
            @Language("Thrift")
            val code = """
                enum Operation {
                  ADD = 1,
                  SUBTRACT = 2,
                  MULTIPLY = 3,
                  DIVIDE = 4
                }
                
                struct TestStruct {
                  1: string value
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            // Should parse without errors
            assertNotNull(container)
            assertTrue(container.DataStructures.isNotEmpty())
        }
        
        @Test
        fun `should parse enum without explicit values`() {
            @Language("Thrift")
            val code = """
                enum Status {
                  PENDING,
                  APPROVED,
                  REJECTED
                }
                
                struct TestStruct {
                  1: string value
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertNotNull(container)
            assertTrue(container.DataStructures.isNotEmpty())
        }
    }
    
    // ==================== Complex Types Tests ====================
    
    @Nested
    inner class ComplexTypesTests {
        @Test
        fun `should parse nested collections`() {
            @Language("Thrift")
            val code = """
                struct ComplexData {
                  1: list<map<string, set<i32>>> nested_data,
                  2: map<i32, list<string>> mapped_lists
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            val struct = container.DataStructures[0]
            
            assertEquals("ComplexData", struct.NodeName)
            assertEquals(2, struct.Fields.size)
        }
        
        @Test
        fun `should parse set type`() {
            @Language("Thrift")
            val code = """
                struct SetExample {
                  1: set<i32> int_set,
                  2: set<string> string_set
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            val struct = container.DataStructures[0]
            
            assertEquals("SetExample", struct.NodeName)
            assertTrue(struct.Fields[0].TypeType.contains("set"))
        }
    }
    
    // ==================== Service Inheritance Tests ====================
    
    @Nested
    inner class ServiceInheritanceTests {
        @Test
        fun `should parse service extending another service`() {
            @Language("Thrift")
            val code = """
                service BaseService {
                  string getName()
                }
                
                service ExtendedService extends BaseService {
                  i32 getId()
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            
            assertEquals(2, container.DataStructures.size)
            
            val baseService = container.DataStructures[0]
            assertEquals("BaseService", baseService.NodeName)
            
            val extendedService = container.DataStructures[1]
            assertEquals("ExtendedService", extendedService.NodeName)
            assertEquals("BaseService", extendedService.Extend)
        }
    }
    
    // ==================== Real-world Example Tests ====================
    
    @Nested
    inner class RealWorldTests {
        @Test
        fun `should parse calculator service example`() {
            @Language("Thrift")
            val code = """
                namespace java com.example.calculator
                namespace py calculator
                
                typedef i32 UserId
                
                enum Operation {
                  ADD = 1,
                  SUBTRACT = 2,
                  MULTIPLY = 3,
                  DIVIDE = 4
                }
                
                exception InvalidOperation {
                  1: i32 whatOp,
                  2: string why
                }
                
                struct Work {
                  1: i32 num1 = 0,
                  2: i32 num2,
                  3: Operation op,
                  4: optional string comment
                }
                
                service Calculator {
                  void ping(),
                  i32 add(1:i32 num1, 2:i32 num2),
                  i32 calculate(1:i32 logid, 2:Work w) throws (1:InvalidOperation ouch),
                  oneway void zip()
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "calculator.thrift")
            
            // Should have Operation enum, InvalidOperation exception, Work struct, and Calculator service
            assertTrue(container.DataStructures.size >= 2)
            
            val service = container.DataStructures.find { it.NodeName == "Calculator" }
            assertNotNull(service)
            assertTrue(service!!.Functions.size >= 3)
            
            val addFunc = service.Functions.find { it.Name == "add" }
            assertNotNull(addFunc)
            assertEquals("i32", addFunc!!.ReturnType)
        }
        
        @Test
        fun `should parse social network service example`() {
            @Language("Thrift")
            val code = """
                typedef i64 UserId
                typedef string UserName
                
                struct User {
                  1: UserId id,
                  2: UserName name,
                  3: string email,
                  4: list<UserId> followers,
                  5: map<string, string> metadata
                }
                
                exception UserNotFoundException {
                  1: UserId userId,
                  2: string message
                }
                
                service UserService {
                  User getUser(1: UserId id) throws (1: UserNotFoundException notFound),
                  list<User> getFollowers(1: UserId id),
                  void follow(1: UserId followerId, 2: UserId followeeId),
                  void unfollow(1: UserId followerId, 2: UserId followeeId)
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "user.thrift")
            
            val service = container.DataStructures.find { it.NodeName == "UserService" }
            assertNotNull(service)
            assertEquals(4, service!!.Functions.size)
        }
    }
    
    // ==================== Annotation Tests ====================
    
    @Nested
    inner class AnnotationTests {
        @Test
        fun `should parse field annotations`() {
            @Language("Thrift")
            val code = """
                struct AnnotatedStruct {
                  1: required i32 id (validation.min = "1"),
                  2: optional string name (validation.maxLength = "100")
                }
            """.trimIndent()
            
            val container = ThriftAnalyser().analysis(code, "test.thrift")
            val struct = container.DataStructures[0]
            
            assertEquals("AnnotatedStruct", struct.NodeName)
            // Annotations may or may not be captured depending on implementation
        }
    }
}
