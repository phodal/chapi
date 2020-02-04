package chapi.ast.javaast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JavaIdentAppTest {
    @Test
    internal fun shouldAnalysis() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val fileName = "import-demo.java"
        val codeFile = JavaIdentApp().analysis(code, fileName)
        assertEquals(codeFile.FullName, fileName)
    }

    @Test
    internal fun shouldGetFilePackageName() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.PackageName, "chapi.ast.javaast")
    }

    @Test
    internal fun shouldGetFileImports() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.Imports[0].Source, "org.junit.Test")
    }

    @Test
    internal fun shouldGetDataStructureName() {
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
    internal fun shouldGetFunctionParameters() {
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
    internal fun shouldGetDataStructureMethodName() {
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
    internal fun shouldGetExtendsName() {
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
    internal fun shouldGetImplementName() {
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

    @Test
    internal fun shouldGetInnerStructureName() {
        val code = """
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
        val codeFile = JavaIdentApp().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "pr")
    }
}
