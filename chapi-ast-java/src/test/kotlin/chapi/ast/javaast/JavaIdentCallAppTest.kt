package chapi.ast.javaast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JavaIdentCallAppTest {
    @Test
    fun shouldNotCrashForJava8() {
        val code = this::class.java.getResource("/grammar/AllInOne8.java").readText()
        JavaAnalyser().identFullInfo(code, "AllInOne8.java")
    }

    @Test
    fun shouldNotCrashForJava11() {
        val code = this::class.java.getResource("/grammar/AllInOne11.java").readText()
        JavaAnalyser().identFullInfo(code, "AllInOne8.java")
    }

    @Test
    fun shouldNotCrashForJava17() {
        val code = this::class.java.getResource("/grammar/AllInOne17.java").readText()
        JavaAnalyser().identFullInfo(code, "AllInOne8.java")
    }

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
        val codeFile = JavaAnalyser().identFullInfo(code, "")
        val codeCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(codeCalls.size, 1)
        assertEquals(codeCalls[0].NodeName, "System.out")
        assertEquals(codeCalls[0].FunctionName, "println")
    }

    @Test
    internal fun shouldIdentifyFunctionCallPosition() {
        val code = helloworld
        val codeFile = JavaAnalyser().identFullInfo(code, "")
        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls[0]

        assertEquals(firstCall.Position.StartLine, 4)
        assertEquals(firstCall.Position.StartLinePosition, 18)
        assertEquals(firstCall.Position.StopLine, 4)
        assertEquals(firstCall.Position.StopLinePosition, 39)
    }

    @Test
    internal fun shouldIdentifyFunctionCallParameterValue() {
        val code = helloworld
        val codeFile = JavaAnalyser().identFullInfo(code, "")
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

        val codeFile = JavaAnalyser().identFullInfo(code, "")

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
        val codeFile = JavaAnalyser().identFullInfo(code, "")

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
        val codeFile = JavaAnalyser().identFullInfo(code, "")

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
        val codeFile = JavaAnalyser().identFullInfo(code, "")

        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].NodeName, "BlogPO")
        assertEquals(functionCalls[0].FunctionName, "toDomainModel")
    }

    @Test
    fun shouldSuccessGetFunctionCallNodeName() {
        val code = this::class.java.getResource("/regression/CallAssertInClassTests.java").readText()
        val codeContainer = JavaAnalyser().identFullInfo(code, "CallAssertInClassTests.java")

        val firstFunc = codeContainer.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls[0].NodeName, "CallAssertInClassTests")
    }

    @Test
    fun shouldAnalysisStaticCall() {
        val code = this::class.java.getResource("/importstatic/SourceBatch.java").readText()
        val codeContainer = JavaAnalyser().identFullInfo(code, "SourceBatch.java")

        val firstFunc = codeContainer.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "generateBatchInsertSql")
        assertEquals(firstFunc.FunctionCalls[0].NodeName, "SqlGenerator")
    }
}
