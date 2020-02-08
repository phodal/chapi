package chapi.ast.javaast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JavaIdentAppTest {
    @Test
    fun shouldIdentifyFilePackageName() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.PackageName, "chapi.ast.javaast")
    }

    @Test
    fun shouldIdentifyFileImports() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.Imports[0].Source, "org.junit.Test")
    }

    @Test
    fun shouldIdentifyDataStructureName() {
        val code = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].NodeName, "HelloWorld")
    }

    @Test
    fun shouldIdentifyFunctionParameters() {
        val code = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        val firstParameter = codeFile.DataStructures[0].Functions[0].Parameters[0]
        assertEquals(firstParameter.TypeType, "String[]")
        assertEquals(firstParameter.TypeValue, "args")
    }

    @Test
    fun shouldIdentifyDataStructureMethodName() {
        val code = """
public class HelloWorld {
    public static void main(String []args) {
       System.out.println("Hello World");
    }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "main")
    }

    @Test
    fun shouldIdentifyExtendsName() {
        val code = """
  class Inner extends HasStatic {
    static final int x = 3;
    static int y = 4;
    public static void pr() {

    }
  }
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Extend, "HasStatic")
    }

    @Test
    fun shouldIdentifyFields() {
        val code = """
package hello;

public class JavaCallApp {
    private JavaDaoParser daoParser;

    public daoCall() {
        daoParser.Call();
    }
}

"""
        val codeFile = JavaIdentApp().analysis(code, "")
        println(codeFile.DataStructures[0].Fields.size)

        assertEquals(codeFile.DataStructures[0].Fields.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "JavaDaoParser")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "daoParser")
    }

    @Test
    fun shouldIdentifyImplementName() {
        val code = """
class Pig implements Animal {
  public void animalSound() {
    System.out.println("The pig says: wee wee");
  }
  public void sleep() {
    System.out.println("Zzz");
  }
}
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Implements[0], "Animal")
    }

    private val innerCode = """
    public class Outer {
      final int z=10;
    
      class Inner extends HasStatic {
        static final int x = 3;
        static int y = 4;
        public static void pr() {
    
        }
      }
    
      public static void main(String[] args) {
        Outer outer = new Outer();
        System.out.println(outer.new Inner().y);
      }
    }
    """

    @Test
    fun shouldIdentifyInnerStructureName() {
        val codeFile = JavaIdentApp().analysis(innerCode, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Outer")
        assertEquals(codeFile.DataStructures[0].InnerStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].InnerStructures[0].NodeName, "Inner")
    }

    @Test
    fun shouldIdentifyInnerStructureFunction() {
        val codeFile = JavaIdentApp().analysis(innerCode, "")

        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "main")
        assertEquals(codeFile.DataStructures[0].InnerStructures[0].Functions[0].Name, "pr")
    }

    @Test
    fun shouldIdentifyInterface() {
        var code = """
interface AnotherInterface extends Runnable { // local interface
    void work();
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].NodeName, "AnotherInterface")
        assertEquals(codeFile.DataStructures[0].Extend, "Runnable")
    }

    @Test
    fun shouldIdentifyInterfaceMethod() {
        var code = """
interface AnotherInterface extends Runnable { // local interface
    void work();
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].NodeName, "AnotherInterface")
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "work")
        assertEquals(codeFile.DataStructures[0].Functions[0].ReturnType, "void")
    }

    @Test
    fun shouldIdentifyInterfaceMethodAnnotation() {
        var code = """
public interface BlogRepository extends Repository {
    @ServiceMethod(value="/hello")
    long count(BlogCriteria criteria);
}

        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].Functions[0].Annotations[0].Name, "ServiceMethod")
        assertEquals(codeFile.DataStructures[0].Functions[0].Annotations[0].KeyValues[0].Key, "value")
        assertEquals(codeFile.DataStructures[0].Functions[0].Annotations[0].KeyValues[0].Value, "\"/hello\"")
    }

    @Test
    fun shouldIdentifyClassAnnotation() {
        var code = """
package adapters.outbound.persistence.blog;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BlogPO implements PersistenceObject<Blog> {

}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].Annotations.size, 3)
        assertEquals(codeFile.DataStructures[0].Annotations[0].Name, "NoArgsConstructor")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Key, "access")
        assertEquals(codeFile.DataStructures[0].Annotations[0].KeyValues[0].Value, "AccessLevel.PROTECTED")
        assertEquals(codeFile.DataStructures[0].Annotations[1].Name, "AllArgsConstructor")
        assertEquals(codeFile.DataStructures[0].Annotations[2].Name, "Getter")
    }

    @Test
    fun shouldIdentifyMethodAnnotation() {
        var code = """
package adapters.outbound.persistence.blog;

public class BlogPO implements PersistenceObject<Blog> {
    @Override
    public Blog toDomainModel() {

    }
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].Functions[0].Annotations.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Annotations[0].Name, "Override")
        assertEquals(codeFile.DataStructures[0].Functions[0].isOverride(), true)
    }

    @Test
    fun shouldIdentifyConstructorMethod() {
        var code = """
package adapters.outbound.persistence.blog;

public class PublishedBlogResource {
    @Autowired
    public PublishedBlogResource(EditBlogUseCase editBlogUseCase, QueryPublishedBlogUseCase queryPublishedBlogUseCase) {
        this.editBlogUseCase = editBlogUseCase;
        this.queryPublishedBlogUseCase = queryPublishedBlogUseCase;
    }
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")

        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].IsConstructor, true)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "PublishedBlogResource")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 2)
    }

    @Test
    fun shouldIdentifyCreator() {
        val code = """
package cc.arduino.packages.contributions;

import org.junit.Test;
import processing.app.Platform;

public class HostDependentDownloadableContribution {
  public void macOsXPositiveTest() {
    HostDependentDownloadableContributionStub contribution = new HostDependentDownloadableContributionStub() {
      @Override
      public String getHost() {
        return "x86_64-apple-darwin";
      }
    };

    Platform platform = new Platform() {
      @Override
      public String getOsName() {
        return "Mac OS X";
      }

      @Override
      public String getOsArch() {
        return "x86_64";
      }
    };
  }
}
        """

        val codeFile = JavaIdentApp().analysis(code, "")
        println(codeFile.DataStructures[0].toString())
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        val dsFunction = codeFile.DataStructures[0].Functions[0]
        assertEquals(dsFunction.InnerStructures.size, 2)
        assertEquals(dsFunction.InnerStructures[0].NodeName, "HostDependentDownloadableContributionStub")
        assertEquals(dsFunction.InnerStructures[0].Functions[0].Name, "getHost")
        assertEquals(dsFunction.InnerStructures[1].NodeName, "Platform")
        assertEquals(dsFunction.InnerStructures[1].Functions.size, 2)
        assertEquals(dsFunction.InnerStructures[1].Functions[0].Name, "getOsName")
        assertEquals(dsFunction.InnerStructures[1].Functions[1].Name, "getOsArch")
    }
}
