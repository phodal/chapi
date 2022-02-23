package chapi.app.analyser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.FileWriter

internal class TypeScriptAnalyserAppTest {
    @Test
//    @Disabled
    fun shouldIdentifySamePackage() {
        val testPath = "/Volumes/source/archguard/archguard-frontend/archguard/src/api"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(testPath)
        println(nodes)

        FileWriter("output.json").use { it.write(Json.encodeToString(nodes)) }

        nodes.forEach {
            it.Functions.forEach { func ->
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            val nodeName = call.NodeName
                            if (!(nodeName == "axios" || nodeName.startsWith("axios."))) {
                                if (call.Parameters.isNotEmpty()) {
                                    println("$nodeName -> ${call.Parameters[0].TypeValue}")
                                } else {
                                    println("${func.Name}.$nodeName")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
