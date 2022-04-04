package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Paths

internal class GoAnalyserAppTest {
    @Test
    internal fun shouldAnalysis() {
        // given
        val config = ChapiConfig(language = "go")
        val resource = this.javaClass.classLoader.getResource("languages/go")
        val path = Paths.get(resource!!.toURI()).toFile()

        //when
        val nodeInfos = GoAnalyserApp(config).analysisNodeByPath(path.absolutePath)

        //then
        assertEquals(nodeInfos.size, 1)
        assert(nodeInfos[0].FilePath.contains("hello.go"))
    }
}
