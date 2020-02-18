package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Paths

internal class ChapiAnalyserTest {
    @Test
    internal fun shouldAnalysisGo() {
        val resource = this.javaClass.classLoader.getResource("languages/go/hello.go")
        val path = Paths.get(resource!!.toURI()).toFile()
        val config = ChapiConfig(language = "go")

        val nodes = ChapiAnalyser(config).analysis(path.absolutePath)
        assertEquals(nodes.size, 1)
        assertEquals(nodes[0].Functions[0].Name, "TestTbsApp_EmptyTest")
    }
}
