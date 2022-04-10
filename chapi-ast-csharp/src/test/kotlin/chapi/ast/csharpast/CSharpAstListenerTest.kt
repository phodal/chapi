package chapi.ast.csharpast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CSharpAstListenerTest  {
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

        assertEquals(2, structs[0].Functions.size)
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

        assertEquals(2, structs[0].Functions.size)
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
}

