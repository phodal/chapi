package chapi.ast.javaast

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
    fun shouldIdentifyMethod() {
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
        assertEquals(codeFile.DataStructures[0].Functions[0].BodyHash, "{}".hashCode());
    }

    @Test
    fun bodyHashShouldReturnTrueWhenIdentifyTheSameCode() {
        val oldCode = """
            package chapi.ast.javaast;

            import hello.Expandable;

            class IntegerArray extends Expandable {
                void getItem() {
                    return item;
                }
            }
        """
        val newCode = """
            package chapi.ast.javaast;
            
            import hello.Expandable;
            
            class IntegerArray extends Expandable {
                void getItem() {
                    return item;
                }
            }
        """
        val originalCodeAnalysisFile = JavaAnalyser().identBasicInfo(oldCode, "basic")
        val newCodeAnalysisFile = JavaAnalyser().identBasicInfo(newCode, "basic")
        val originalGetItemFunction = originalCodeAnalysisFile.DataStructures[0].Functions
        assertEquals(1, originalGetItemFunction.size)
        assertEquals("getItem", originalGetItemFunction.get(0).Name)

        val newGetItemFunction = newCodeAnalysisFile.DataStructures[0].Functions
        assertEquals(1, newGetItemFunction.size)
        assertEquals("getItem", newGetItemFunction.get(0).Name)
        assertTrue { originalGetItemFunction.contentEquals(newGetItemFunction) }
    }
    
    @Test
    fun bodyHashShouldReturnFalseWhenMethodHasBeenChangeInTheNextCommit() {
        val oldCode = """
            package chapi.ast.javaast;

            import hello.Expandable;

            class IntegerArray extends Expandable {
                void getItem() {
                    return item;
                }
            }
        """
        val newCode = """
            package chapi.ast.javaast;
            
            import hello.Expandable;
            
            class IntegerArray extends Expandable {
                void getItem() {
                    doSomeCalc();
                    return item;
                }
            }
        """
        val originalCodeAnalysisFile = JavaAnalyser().identBasicInfo(oldCode, "basic")
        val newCodeAnalysisFile = JavaAnalyser().identBasicInfo(newCode, "basic")
        val originalGetItemFunction = originalCodeAnalysisFile.DataStructures[0].Functions
        assertEquals(1, originalGetItemFunction.size)
        assertEquals("getItem", originalGetItemFunction.get(0).Name)

        val newGetItemFunction = newCodeAnalysisFile.DataStructures[0].Functions
        assertEquals(1, newGetItemFunction.size)
        assertEquals("getItem", newGetItemFunction.get(0).Name)
        assertFalse { originalGetItemFunction.contentEquals(newGetItemFunction) }
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
