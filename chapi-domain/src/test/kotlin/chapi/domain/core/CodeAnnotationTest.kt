package chapi.domain.core

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CodeAnnotationTest {
    @Test
    fun shouldHandleIsRepository() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Repository", emptyStringArray).isComponentOrRepository()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsComponent() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Component", emptyStringArray).isComponentOrRepository()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsTest() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Test", emptyStringArray).isTest()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsIgnore() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Ignore", emptyStringArray).isIgnore()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsIgnoreOrTest() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Ignore", emptyStringArray).isIgnoreOrTest()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldReturnTrueWhenIsOverride() {
        val emptyStringArray = arrayOf<AnnotationKeyValue>()
        assertEquals(CodeAnnotation("Override", emptyStringArray).isOverride(), true)
    }

    @Test
    fun shouldCompareAnnotation() {
        assertEquals(CodeAnnotation("Sample"), CodeAnnotation("Sample"))
        assertEquals(
            CodeAnnotation("Sample", KeyValues = arrayOf(AnnotationKeyValue(Key = "key", Value = "value"))),
            CodeAnnotation("Sample", KeyValues = arrayOf(AnnotationKeyValue(Key = "key", Value = "value")))
        )
    }
}

