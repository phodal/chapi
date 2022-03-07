package chapi.app.analyser

import chapi.domain.core.CodeCall
import chapi.domain.core.CodePosition
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

data class ConsumerModel(
    // like component
    var ConsumerName: String = "",
    // API name
    var TargetName: String = "",
    // consumer file path
    var ConsumerFile: String = "",
    // a simple log for consumer
    var ConsumerCallLog: String = "",
    var ConsumerPosition: CodePosition = CodePosition()
)

@Serializable
data class ApiAdapter(
    var SourceFile: String = "",
    var SourceFunc: String = "",
    var TargetApi: String = "",
    var TypeValue: String = ""
)

internal class TypeScriptAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        // 1. first create Component with FunctionCall maps based on Import
        var calls: List<CodeCall> = listOf()
        nodes.map { ds ->
            ds.Functions.map {
                val isComponent = it.fileExt() == "tsx"
                if (isComponent) {
                    println("component: " + it.Name)
                }
            }
        }

        // 2. build axios/umi-request to a API call method
        val sb = StringBuilder()
        val adapters: MutableList<ApiAdapter> = mutableListOf();
        nodes.forEach {
            it.Functions.forEach { func ->
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            val callName = call.FunctionName
                            if (callName == "axios" || callName.startsWith("axios.")) {
                                val adapter = ApiAdapter()
                                if (call.Parameters.isNotEmpty()) {
                                    sb.append("$callName -> ${call.Parameters[0].TypeValue}\n")
                                    adapter.SourceFunc = callName
                                    adapter.TypeValue = call.Parameters[0].TypeValue
                                    adapters += adapter
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. mapping for results
        println(Json.encodeToString(adapters))
        print(sb)
    }
}
