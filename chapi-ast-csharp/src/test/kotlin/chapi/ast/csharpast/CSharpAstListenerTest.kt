package chapi.ast.csharpast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import chapi.domain.core.DataStructType
import kotlin.test.Ignore

internal class CSharpAstListenerTest {
    private val helloworld = """
    using System; 
      
    namespace HelloWorldApp { 
        class Geeks { 
            static void Main(string[] args) { 
                Console.WriteLine("Hello World!"); 
                Console.ReadKey(); 
            } 
        } 
    } 
    """

    @Test
    fun shouldNotCrashForGrammarFile() {
        val code = this::class.java.getResource("/grammar/AllInOneNoPreprocessor.cs").readText()
        CSharpAnalyser().analysis(code, "AllInOneNoPreprocessor.cs")
    }

    @Test
    fun shouldIdentUsingSystem() {
        val codeFile = CSharpAnalyser().analysis(helloworld, "hello.cs")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "System")
    }

    @Test
    fun shouldIdentUsingNamespace() {
        val code = """
using SomeNameSpace.Nested;

"""
        val codeFile = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "SomeNameSpace.Nested")
    }

    @Test
    fun shouldIdentUsingAlias() {
        val code = """
using generics = System.Collections.Generic;

"""
        val codeFile = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "System.Collections.Generic")
        assertEquals(codeFile.Imports[0].AsName, "generics")
    }

    @Test
    fun shouldIdentDeclNameSpace() {
        val code = """
using System; 
  
namespace HelloWorldApp {

}
"""
        val codeContainer = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeContainer.Containers.size, 1)
        assertEquals(codeContainer.Containers[0].PackageName, "HelloWorldApp")
    }

    @Test
    fun shouldIdentDeclMultipleNameSpace() {
        val code = """
using System; 
  
namespace HelloWorldApp {

}

namespace HelloWorldApp2 {

}
"""
        val codeContainer = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeContainer.Containers.size, 2)
        assertEquals(codeContainer.Containers[0].PackageName, "HelloWorldApp")
        assertEquals(codeContainer.Containers[1].PackageName, "HelloWorldApp2")
    }

    @Test
    fun shouldIdentDeclNameSpaceInNameSpace() {
        val code = """
using System; 
  
namespace HelloWorldApp {
  namespace HelloWorldApp2 {
    namespace HelloWorldApp3 {
    
    }
  }
}
"""
        val codeContainer = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeContainer.Containers.size, 1)
        assertEquals(codeContainer.Containers[0].PackageName, "HelloWorldApp")
        assertEquals(codeContainer.Containers[0].Containers[0].PackageName, "HelloWorldApp2")
        assertEquals(codeContainer.Containers[0].Containers[0].Containers[0].PackageName, "HelloWorldApp3")
    }

    @Test
    fun shouldIdentClassName() {
        val code = """
using System; 
  
namespace HelloWorldApp { 
    class Geeks { 

    } 
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeContainer.Containers[0].DataStructures.size, 1)
        assertEquals(codeContainer.Containers[0].DataStructures[0].NodeName, "Geeks")
    }

    @Test
    fun shouldIdentNameSpaceClassInNameSpaceName() {
        val code = """
using System; 
  
namespace HelloWorldApp { 
    class Geeks {}
    namespace HelloWorldApp2 {
        class Geeks2 {}    
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "hello.cs")
        assertEquals(codeContainer.Containers[0].DataStructures.size, 1)
        assertEquals(codeContainer.Containers[0].DataStructures[0].NodeName, "Geeks")

        assertEquals(codeContainer.Containers[0].Containers[0].DataStructures.size, 1)
        assertEquals(codeContainer.Containers[0].Containers[0].DataStructures[0].NodeName, "Geeks2")
    }

    @Test
    fun shouldIdentNameSpaceClassMethodSupport() {
        val codeContainer = CSharpAnalyser().analysis(helloworld, "hello.cs")
        val codeDataStruct = codeContainer.Containers[0].DataStructures[0]

        assertEquals(codeDataStruct.Functions.size, 1)
        assertEquals(codeDataStruct.Functions[0].Name, "Main")
        assertEquals(codeDataStruct.Functions[0].Modifiers.size, 1)
        assertEquals(codeDataStruct.Functions[0].Modifiers[0], "static")
    }

    @Test
    fun shouldIdentVoidReturnType() {
        val codeContainer = CSharpAnalyser().analysis(helloworld, "hello.cs")
        val codeDataStruct = codeContainer.Containers[0].DataStructures[0]
        assertEquals(codeDataStruct.Functions.size, 1)
        assertEquals(codeDataStruct.Functions[0].ReturnType, "void")
    }

    @Test
    fun shouldIdentNameSpaceClassParametersSupport() {
        val codeContainer = CSharpAnalyser().analysis(helloworld, "hello.cs")
        val codeDataStruct = codeContainer.Containers[0].DataStructures[0]
        assertEquals(codeDataStruct.Functions[0].Parameters.size, 1)
        assertEquals(codeDataStruct.Functions[0].Parameters[0].TypeType, "string[]")
        assertEquals(codeDataStruct.Functions[0].Parameters[0].TypeValue, "args")
    }

    @Test
    fun shouldIdentAnnotation() {
        val code = """
using System; 
  
namespace Chapi.Controller { 
    [RoutePrefix("api")]
    public class ChapiController {
        [HttpGet]
        [Route("book/")]
        public Book GetBook()
        {
            return new Book();
        }
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)

        val annotations = structs[0].Annotations
        assertEquals(1, annotations.size)
        assertEquals("RoutePrefix", annotations[0].Name)

        val firstFunction = structs[0].Functions[0]
        assertEquals(2, firstFunction.Annotations.size)
        assertEquals("HttpGet", firstFunction.Annotations[0].Name)
        assertEquals("Route", firstFunction.Annotations[1].Name)
        assertEquals("book/", firstFunction.Annotations[1].KeyValues[0].Value)
        assertEquals("Book", firstFunction.ReturnType)
    }

    @Test
    fun shouldIdentMultipleFunctions() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
        public Chapi() {}
        public Chapi(long id) {}
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)

        assertEquals(2, structs[0].Functions.size)
    }


    @Test
    fun shouldIdentClassPosition() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
        public Chapi() {}
        public Chapi(long id) {}
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)

        assertEquals(structs[0].Position.StartLine, 5)
        assertEquals(structs[0].Position.StopLine, 8)
    }

    @Test
    fun shouldIdentProperty() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
        public Chapi() {}
        public Chapi(long id) {}
        
        public long ChapiId { get; set; }
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)

        assertEquals(3, structs[0].Functions.size)
        assertEquals(1, structs[0].Fields.size)
        assertEquals("long", structs[0].Fields[0].TypeType)
        assertEquals("ChapiId", structs[0].Fields[0].TypeValue)
    }

    @Test
    fun shouldIdentPropertyInList() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
        public Chapi() {}
        public Chapi(long id) {}
        
        public List<DeviceParameter> Parameters { get; set; }
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)

        assertEquals(3, structs[0].Functions.size)
        assertEquals(1, structs[0].Fields.size)
        assertEquals("List", structs[0].Fields[0].Modifiers[0])
        assertEquals("DeviceParameter", structs[0].Fields[0].TypeType)
        assertEquals("Parameters", structs[0].Fields[0].TypeValue)
    }

    @Test
    fun variable_crash() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
       public getInput() {
            var input = new Input()
            {
                item = new Item()
                {
                    name = "Smple",
                    createdTime = $"{DateTime.Now:yyyy-MM-ddTHH:mm:ss}.08Z}",
                },
                param = new List<String>()
            };
       }
    }
} 
"""
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)
    }

    @Test
    fun template_string() {
        val code = """
using System; 
  
namespace Chapi { 
    public class Chapi {
       public getInput() {
          string s = $"get by date {DateTime.Now:yyyy-MM-dd HH:mm:ss,fff}";
       }
    }
} 
"""
        // expression will split by `:`, cause issues the
        val codeContainer = CSharpAnalyser().analysis(code, "ChapiController.cs")
        val structs = codeContainer.Containers[0].DataStructures
        assertEquals(structs.size, 1)
    }

    @Test
    fun macroPreprocessor() {
        val code = """
            using System.Text;
            namespace testns {
                #if DEBUG
                    public class testcls {
                        public static void Main(string []args) {
                            int x = 2;
                            int y  = 10;
                        }
                    }
                #endif
            }"""
        val codeContainer = CSharpAnalyser().analysis(code, "test.cs")
        val structs = codeContainer.Containers[0].DataStructures

        assertEquals(structs.size, 1)
        assertEquals(structs[0].NodeName, "testcls")
        assertEquals(structs[0].Functions.size, 1)
        assertEquals(structs[0].Functions[0].Name, "Main")
    }

    @Test
    fun shouldSupportRecordType() {
        val code = """
        namespace RecordTests;

        public record Person(string FirstName, string LastName);
        """
        val codeContainer = CSharpAnalyser().analysis(code, "test.cs")
        val structs = codeContainer.Containers[0].DataStructures

        assertEquals(1, structs.size)
        assertEquals("Person", structs[0].NodeName)
    }

    @Test
    fun shouldSupportFileScopedNamespace() {
        val code = """
        namespace MyApp.Services;

        public class MyService
        {
            public void DoSomething() { }
        }
        """
        val codeContainer = CSharpAnalyser().analysis(code, "test.cs")

        assertEquals(1, codeContainer.Containers.size)
        assertEquals("MyApp.Services", codeContainer.Containers[0].PackageName)
        assertEquals(1, codeContainer.Containers[0].DataStructures.size)
        assertEquals("MyService", codeContainer.Containers[0].DataStructures[0].NodeName)
    }

    @Test
    fun shouldSupportGlobalUsing() {
        val code = """
        global using System;
        global using System.Collections.Generic;

        namespace MyApp;

        public class Program { }
        """
        val codeContainer = CSharpAnalyser().analysis(code, "test.cs")

        assertEquals(2, codeContainer.Imports.size)
        assertEquals("System", codeContainer.Imports[0].Source)
        assertEquals("System.Collections.Generic", codeContainer.Imports[1].Source)
    }

    @Test
    fun shouldSupportInitAccessor() {
        val code = """
        namespace Test;

        public class Point
        {
            public int X { get; init; }
            public int Y { get; init; }
        }
        """
        val codeContainer = CSharpAnalyser().analysis(code, "test.cs")
        val structs = codeContainer.Containers[0].DataStructures

        assertEquals(1, structs.size)
        assertEquals("Point", structs[0].NodeName)
        assertEquals(2, structs[0].Fields.size)
    }
    
    @Test
    @Ignore
    fun shouldParseModernCSharpFeatures() {
        val code = this::class.java.getResource("/modern/ModernCSharpFeatures.cs")?.readText()
        if (code == null) {
            // Skip if resource not found
            return
        }
        
        val codeFile = CSharpAnalyser().analysis(code, "ModernCSharpFeatures.cs")
        
        assertTrue(codeFile.Imports.isNotEmpty())
        assertTrue(codeFile.Containers.isNotEmpty())
        
        val container = codeFile.Containers[0]
        assertEquals("ModernCSharp.Examples", container.PackageName)
        
        // Check for controller
        val controller = container.DataStructures.find { it.NodeName == "UsersController" }
        assertNotNull(controller)
        assertTrue(controller!!.Annotations.any { it.Name == "ApiController" })
        
        // Check for service with async methods
        val service = container.DataStructures.find { it.NodeName == "UserService" }
        assertNotNull(service)
        assertTrue(service!!.Functions.any { it.Modifiers.contains("async") })
        
        // Check for records
        val userDto = container.DataStructures.find { it.NodeName == "UserDto" }
        assertNotNull(userDto)
        
        val createUserRequest = container.DataStructures.find { it.NodeName == "CreateUserRequest" }
        assertNotNull(createUserRequest)
        
        // Check for interfaces
        val userServiceInterface = container.DataStructures.find { it.NodeName == "IUserService" }
        assertNotNull(userServiceInterface)
        assertEquals(DataStructType.INTERFACE, userServiceInterface!!.Type)
        
        // Check for repository
        val repository = container.DataStructures.find { it.NodeName == "UserRepository" }
        assertNotNull(repository)
        assertEquals("IUserRepository", repository!!.Extend)
        
        // Check for pattern matching examples
        val patternMatching = container.DataStructures.find { it.NodeName == "PatternMatchingExamples" }
        assertNotNull(patternMatching)
        assertTrue(patternMatching!!.Functions.isNotEmpty())
        
        // Check for LINQ examples
        val linqExamples = container.DataStructures.find { it.NodeName == "AdvancedLinqExamples" }
        assertNotNull(linqExamples)
        assertTrue(linqExamples!!.Functions.isNotEmpty())
        
        // Check for extension methods
        val extensions = container.DataStructures.find { it.NodeName == "EnumerableExtensions" }
        assertNotNull(extensions)
        assertTrue(extensions!!.Functions.all { it.Modifiers.contains("static") })
    }
}

