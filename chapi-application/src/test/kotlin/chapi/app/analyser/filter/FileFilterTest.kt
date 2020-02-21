package chapi.app.analyser.filter

import chapi.app.analyser.config.ChapiConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FileFilterTest {
    @Test
    internal fun shouldReturnTrueWhenInputLangJava() {
        val config = ChapiConfig(
            language = "java"
        )
        val isJavaFile = FileFilter.filterByLanguage("hello.java", config)
        assertEquals(isJavaFile, true)
    }

    @Test
    internal fun shouldEnableRegexToMatchFile() {
        val config = ChapiConfig(
            language = "java",
            filterRule = ".*\\.java"
        )
        val isJavaFile = FileFilter.filterByLanguage("hello.java", config)
        assertEquals(isJavaFile, true)
    }
}
