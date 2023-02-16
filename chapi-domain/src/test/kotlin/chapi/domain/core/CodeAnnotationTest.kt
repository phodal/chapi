package chapi.domain.core

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CodeAnnotationTest {
    @Test
    fun shouldHandleIsRepository() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Repository", emptyStringArray).isComponentOrRepository()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsComponent() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Component", emptyStringArray).isComponentOrRepository()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsTest() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Test", emptyStringArray).isTest()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsIgnore() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Ignore", emptyStringArray).isIgnore()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldHandleIsIgnoreOrTest() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        val isComponent = CodeAnnotation("Ignore", emptyStringArray).isIgnoreOrTest()
        assertEquals(isComponent, true)
    }

    @Test
    fun shouldReturnTrueWhenIsOverride() {
        val emptyStringArray = listOf<AnnotationKeyValue>()
        assertEquals(CodeAnnotation("Override", emptyStringArray).isOverride(), true)
    }

    @Test
    fun shouldCompareAnnotation() {
        assertEquals(CodeAnnotation("Sample"), CodeAnnotation("Sample"))
        assertEquals(
            CodeAnnotation("Sample", KeyValues = listOf(AnnotationKeyValue(Key = "key", Value = "value"))),
            CodeAnnotation("Sample", KeyValues = listOf(AnnotationKeyValue(Key = "key", Value = "value")))
        )
    }
}

