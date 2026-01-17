package chapi.ast.csharpast

import chapi.domain.core.CallType
import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

import org.junit.jupiter.api.Assertions.*

internal class CSharpFullIdentListenerTest {
    @Test
    fun should_handle_for_simple_java_hello_world() {
        val code = this::class.java.getResource("/call/hello.cs").readText()
        val node = CSharpAnalyser().analysis(code, "hello.cs")

        val containers = node.Containers
        assertEquals(1, containers.size)

        val calls = containers[0].DataStructures[0].Functions[0].FunctionCalls
        assertEquals(2, calls.size)

        assertEquals("Console", calls[0].NodeName)
        assertEquals("WriteLine", calls[0].FunctionName)

        assertEquals(1, calls[0].Parameters.size)
        assertEquals("\"Hello World!\"", calls[0].Parameters[0].TypeValue)

        assertEquals("Console", calls[1].NodeName)
        assertEquals("ReadKey", calls[1].FunctionName)
    }

    @Test
    fun should_get_type_from_property() {
        val code = this::class.java.getResource("/inference/HistoryService.cs").readText()
        val node = CSharpAnalyser().analysis(code, "HistoryService.cs")

        val container = node.Containers[0]
        assertEquals(2, container.DataStructures[0].Functions.size)
    }
    
    @Nested
    inner class InheritanceTests {
        @Test
        fun should_identify_class_inheritance() {
            val code = """
                using System;
                namespace TestApp {
                    public class Animal {
                        public virtual void Speak() { }
                    }
                    
                    public class Dog : Animal {
                        public override void Speak() {
                            Console.WriteLine("Woof!");
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertEquals(2, container.DataStructures.size)
            
            val dogClass = container.DataStructures[1]
            assertEquals("Dog", dogClass.NodeName)
            assertEquals("Animal", dogClass.Extend)
            assertEquals(DataStructType.CLASS, dogClass.Type)
        }
        
        @Test
        fun should_identify_interface_implementation() {
            val code = """
                namespace TestApp {
                    public class UserRepository : IRepository {
                        public void Save() { }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            val userRepo = container.DataStructures[0]
            assertEquals("UserRepository", userRepo.NodeName)
            assertEquals("IRepository", userRepo.Extend)
        }
        
        @Test
        fun should_identify_multiple_interfaces() {
            val code = """
                namespace TestApp {
                    public class Service : BaseService, IDisposable, ICloneable {
                        public void Dispose() { }
                        public object Clone() { return null; }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            val service = container.DataStructures[0]
            assertEquals("Service", service.NodeName)
            assertEquals("BaseService", service.Extend)
            assertEquals(2, service.Implements.size)
            assertTrue(service.Implements.contains("IDisposable"))
            assertTrue(service.Implements.contains("ICloneable"))
        }
    }
    
    @Nested
    inner class FieldTests {
        @Test
        fun should_identify_field_declarations() {
            val code = """
                namespace TestApp {
                    public class UserService {
                        private IUserRepository userRepository;
                        private readonly string connectionString;
                        
                        public UserService(IUserRepository repo) {
                            this.userRepository = repo;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val service = container.DataStructures[0]
            
            // Should have at least 2 fields
            assertTrue(service.Fields.size >= 2)
        }
    }
    
    @Nested
    inner class ObjectCreationTests {
        @Test
        fun should_identify_object_creation() {
            val code = """
                namespace TestApp {
                    public class Startup {
                        public void Configure() {
                            var service = new UserService("connection");
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val startup = container.DataStructures[0]
            
            val configureFunc = startup.Functions[0]
            val creatorCalls = configureFunc.FunctionCalls.filter { it.Type == CallType.CREATOR }
            
            assertTrue(creatorCalls.isNotEmpty())
            val firstCreator = creatorCalls.first()
            assertEquals("UserService", firstCreator.NodeName)
            assertEquals("<init>", firstCreator.FunctionName)
        }
    }
    
    @Nested
    inner class LocalVariableTests {
        @Test
        fun should_identify_local_variables() {
            val code = """
                namespace TestApp {
                    public class Calculator {
                        public int Add(int a, int b) {
                            int result = a + b;
                            var formatted = result.ToString();
                            return result;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val calculator = container.DataStructures[0]
            
            val addFunc = calculator.Functions[0]
            assertEquals("Add", addFunc.Name)
            assertTrue(addFunc.LocalVariables.isNotEmpty())
        }
    }
}
