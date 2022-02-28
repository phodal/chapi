package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Paths

internal class KotlinAnalyserAppTest {
    @Test
    fun `should return nodes of the sample code file`() {
        val config = ChapiConfig(language = Language.KOTLIN)
        val resource = this.javaClass.classLoader.getResource("languages/kotlin")!!
        val path = Paths.get(resource.toURI()).toFile()

        val nodeInfos = KotlinAnalyserApp(config).analysisNodeByPath(path.absolutePath)

        Assertions.assertEquals(nodeInfos.size, 1)
        Assertions.assertEquals(nodeInfos[0].FilePath, "Hello.kt")
    }
}
