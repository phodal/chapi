package chapi.app.analyser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.FileWriter

internal class TypeScriptAnalyserAppTest {
    @Test
    @Disabled
    fun shouldIdentifySamePackage() {
        val testPath = "/Volumes/source/archguard/archguard-frontend/archguard/src"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(testPath)
        println(nodes)

        FileWriter("output.json").use { it.write(Json.encodeToString(nodes)) }
    }
}
