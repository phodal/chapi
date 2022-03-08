package chapi.app.analyser

import chapi.domain.core.CodeCall
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodePosition
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

@Serializable
data class ApiAdapter(
    var SourceFile: String = "",
    var SourceFunc: String = "",
    var TargetApi: String = "",
    var TypeValue: String = ""
)

internal class TypeScriptAnalyserAppTest {
    var callMap: HashMap<String, CodeCall> = hashMapOf()

    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        // fileName::componentName
        val componentList: MutableList<String> = mutableListOf()

        // 1. first create Component with FunctionCall maps based on Import
        // 2. build axios/umi-request to a API call method
        // 3. mapping for results
        val sb = StringBuilder()
        val adapters: MutableList<ApiAdapter> = mutableListOf();
        nodes.forEach {
            val isComponent = it.fileExt() == "tsx"
            if(isComponent) {
                componentList += "${it.FilePath}::${it.NodeName}"
            }
            it.Functions.forEach { func ->
                var calleeName = "${it.FilePath}::${it.NodeName}::${func.Name}"
                if(isComponent) {
                    calleeName = "${it.FilePath}::${it.NodeName}"
                }

                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                        }
                    }
                    func.InnerFunctions.forEach { inner ->
                        run {
                            inner.FunctionCalls.forEach { innerCall ->
                                run {
                                    callMap[calleeName] = innerCall
//                                    identAxios(innerCall, sb, func, adapters)
                                }
                            }
                        }
                    }
                }
            }
        }

        println(Json.encodeToString(adapters))
        print(sb)
        print(callMap)
    }

    private fun identAxios(
        call: CodeCall,
        sb: StringBuilder,
        func: CodeFunction,
        adapters: MutableList<ApiAdapter>
    ) {
        val callName = call.FunctionName
        if (callName == "axios" || callName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                val adapter = ApiAdapter()
                sb.append("${func.Name} -> $callName -> ${call.Parameters[0].TypeValue}\n")
                adapter.SourceFunc = func.Name
                adapter.TypeValue = call.Parameters[0].TypeValue
                adapters += adapter
            }
        }
    }
}
