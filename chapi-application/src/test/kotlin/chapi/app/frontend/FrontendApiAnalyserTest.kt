package chapi.app.frontend

import chapi.app.analyser.TypeScriptAnalyserApp
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths

internal class FrontendApiAnalyserTest {
    @Test
    fun shouldSupportIdentifyComponentApi() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

//        val path = "/Volumes/source/archguard/archguard-frontend/archguard/src"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(5, nodes.size)

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

    @Test
    fun supportArchGuard() {
        val path = "/Volumes/source/archguard/archguard-frontend/archguard/src"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        File("nodes.json").writeText(Json.encodeToString(nodes))

        val componentCalls: Array<ComponentHttpCallInfo> = FrontendApiAnalyser().analysis(nodes, path)
        File("api.json").writeText(Json.encodeToString(componentCalls))
    }

    @Test
    internal fun shouldCorrectComponentName() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/interface-error")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        File("nodes.json").writeText(Json.encodeToString(nodes))

        val componentCalls: Array<ComponentHttpCallInfo> = FrontendApiAnalyser().analysis(nodes, path)
        File("api.json").writeText(Json.encodeToString(componentCalls))
    }

    @Test
    internal fun shouldSaveApiAdapter() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/api-adapter")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        File("nodes.json").writeText(Json.encodeToString(nodes))

        val componentCalls: Array<ComponentHttpCallInfo> = FrontendApiAnalyser().analysis(nodes, path)
        assertEquals(4, componentCalls.size)
        File("api.json").writeText(Json.encodeToString(componentCalls))
    }
}