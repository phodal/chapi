package chapi.ast.javaast

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class JavaBasicIdentListenerTest {
    @Test
    internal fun shouldIdentifyImport() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.PackageName, "chapi.ast.javaast")
    }

    @Test
    internal fun shouldIdentifyClass() {
        val code = """
package chapi.ast.javaast;

import hello.Expandable;

class IntegerArray implements Expandable {
    void addItem(Integer item) {
    }
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "IntegerArray")
        assertEquals(codeFile.DataStructures[0].Implements[0], "Expandable")
    }

    @Test
    fun shouldAnalysisStaticImport() {
        val code = this::class.java.getResource("/importstatic/SourceBatch.java").readText()
        val codeContainer = JavaAnalyser().identBasicInfo(code, "SourceBatch.java")

        assertEquals("infrastructure.utils.SqlGenerator", codeContainer.Imports[0].Source)
    }

    @Test
    internal fun shouldIdentifyExtends() {
        val code = """
package chapi.ast.javaast;

import hello.Expandable;

class IntegerArray extends Expandable {
    void addItem(Integer item) {
    }
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "IntegerArray")
        assertEquals(codeFile.DataStructures[0].Extend, "Expandable")
    }

    @Test
    internal fun shouldIdentifyMethod() {
        val code = """
package chapi.ast.javaast;

import hello.Expandable;

class IntegerArray extends Expandable {
    void addItem(Integer item) {
    }
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "addItem")
    }

    @Test
    internal fun shouldInterfaceName() {
        val code = """
package chapi.ast.javaast;

interface Expandable {
    void addItem(T item);
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Expandable")
    }

    @Test
    internal fun shouldInterfaceMethod() {
        val code = """
package chapi.ast.javaast;

interface Expandable {
    void addItem(T item);
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "addItem")
    }

    @Test
    internal fun shouldIdentifyConstructor() {
        val code = """
package chapi.ast.javaast;

import hello.Expandable;

public class PublishedBlogResource {
    public PublishedBlogResource() {

    }
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].IsConstructor, true)
    }

    @Test
    internal fun shouldIdentifyReturnNull() {
        val code = """
package chapi.ast.javaast;

import hello.Expandable;

public class PublishedBlogResource {
    public PublishedBlogResource() {
        return null;
    }
}
"""
        val codeFile = JavaAnalyser().identBasicInfo(code, "basic")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)

        val codeFunction = codeFile.DataStructures[0].Functions[0]

        assertEquals(codeFunction.Extension.jsonObject["IsReturnNull"], JsonPrimitive("true"))
        assertEquals(codeFunction.isReturnNull(), true)
    }
}
