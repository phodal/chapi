package chapi.app.analyser.filter

import chapi.app.analyser.config.ChapiConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Paths

internal class FileFilterTest {
    @Test
    internal fun shouldReturnTrueWhenInputLangJava() {
        val config = ChapiConfig(
            language = "java"
        )

        val resource = this.javaClass.classLoader.getResource("e2e/step2-java/Main.java")
        val path = Paths.get(resource!!.toURI()).toFile().absolutePath

        val isJavaFile = FileFilter.filterByLanguage(path, config)
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
