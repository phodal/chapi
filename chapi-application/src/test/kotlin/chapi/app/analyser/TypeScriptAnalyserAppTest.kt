package chapi.app.analyser

import chapi.app.frontend.ComponentHttpCallInfo
import chapi.app.frontend.FrontendApiAnalyser
import chapi.app.frontend.naming
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths

internal class TypeScriptAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

//        val path = "/Volumes/source/archguard/archguard-frontend/archguard/src"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        val componentCalls: Array<ComponentHttpCallInfo> = FrontendApiAnalyser().analysis(nodes, path)
        File("api.json").writeText(Json.encodeToString(componentCalls))

        assertEquals(1, componentCalls.size)
        assertEquals("BadSmellThreshold/BadSmellThreshold", componentCalls[0].name)
        val apiRef = componentCalls[0].apiRef[0]
        assertEquals(naming("api/addition/systemInfo", "updateSystemInfo"), apiRef.caller)
        assertEquals("baseURL", apiRef.base)
        assertEquals("systemInfoApi", apiRef.url)
        assertEquals("PUT", apiRef.method)
        assertEquals("parameter", apiRef.data)
    }
}
