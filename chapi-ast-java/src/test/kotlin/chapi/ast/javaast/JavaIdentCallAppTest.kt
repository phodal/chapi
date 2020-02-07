package chapi.ast.javaast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JavaIdentCallAppTest {
    private val helloworld = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
    """

    @Test
    internal fun shouldIdentifyFunctionCallName() {
        val code = helloworld
        val codeFile = JavaIdentApp().analysis(code, "")
        val codeCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(codeCalls.size, 1)
        assertEquals(codeCalls[0].NodeName, "System.out")
        assertEquals(codeCalls[0].FunctionName, "println")
    }

    @Test
    internal fun shouldIdentifyFunctionCallPosition() {
        val code = helloworld
        val codeFile = JavaIdentApp().analysis(code, "")
        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[0]

        assertEquals(firstCall.Position.StartLine, 4)
        assertEquals(firstCall.Position.StartLinePosition, 18)
        assertEquals(firstCall.Position.StopLine, 4)
        assertEquals(firstCall.Position.StopLinePosition, 39)
    }

    @Test
    internal fun shouldIdentifyFunctionCallParameterValue() {
        val code = helloworld
        val codeFile = JavaIdentApp().analysis(code, "")
        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[0]
        val parameters = firstCall.Parameters

        assertEquals(parameters.size, 1)
        assertEquals(parameters[0].TypeValue, "\"Hello World\"")
    }

    @Test
    fun shouldIdentifyFormalParameterCall() {
        var code = """
package regression;

import hello.CreateBookCommand;

@Component
public class BookService implements Service {
    @Transactional
    public void getIsbnId(CreateBookCommand command) {
        command.getIsbn();
    }
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].FunctionName, "getIsbn")
        assertEquals(functionCalls[0].Package, "hello")
        assertEquals(functionCalls[0].NodeName, "CreateBookCommand")
    }

    @Test
    fun shouldIdentifyLocalVarCall() {
        var code = """
import hello.Outer;
            
public class ClassTwo {
  public static void main(String[] args) {
    Outer outer = new Outer();
    outer.hello();
  }
}
        """
        val codeFile = JavaIdentApp().analysis(code, "")

        println(codeFile.DataStructures[0].toJson())
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].Package, "hello")
        assertEquals(functionCalls[0].NodeName, "Outer")
        assertEquals(functionCalls[0].FunctionName, "hello")
    }

    @Test
    fun shouldIdentifyChainCall() {
        var code = """
package hello;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
            
public class PublishedBlogResource {
    public ResponseEntity post(UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents = uriComponentsBuilder.path("/published-blog/{id}").buildAndExpand("1234");
        return uriComponents.toUri();
    }
}
        """
        val codeFile = JavaIdentApp().analysis(code, "")

        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 3)
        assertEquals(functionCalls[0].NodeName, "UriComponentsBuilder")
        assertEquals(functionCalls[0].FunctionName, "path")

        assertEquals(functionCalls[1].NodeName, "UriComponentsBuilder")
        assertEquals(functionCalls[1].FunctionName, "buildAndExpand")

        assertEquals(functionCalls[2].NodeName, "UriComponents")
        assertEquals(functionCalls[2].FunctionName, "toUri")
    }

    @Test
    fun shouldIdentifyLambdaCall() {
        var code = """
package hello;

import domain.BlogPO;

@Component
public class BlogRepositoryImpl {
    public BlogPo getDomainModel() {
        return BlogPO::toDomainModel;
    }
}
        """
        val codeFile = JavaIdentApp().analysis(code, "")

        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].NodeName, "BlogPO")
        assertEquals(functionCalls[0].FunctionName, "toDomainModel")
    }
}
