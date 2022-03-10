package chapi.app.analyser

import chapi.app.frontend.ComponentHttpCall
import chapi.app.frontend.FeHttpApiCall
import chapi.app.path.ecmaImportConvert
import chapi.app.path.relativeRoot
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeFunction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Paths

internal class TypeScriptAnalyserAppTest {
    private var componentCallMap: HashMap<String, MutableList<String>> = hashMapOf()
    private var componentInbounds: HashMap<String, MutableList<String>> = hashMapOf()
    private var callMap: HashMap<String, CodeCall> = hashMapOf()
    private var httpAdapterMap: HashMap<String, CodeCall> = hashMapOf()

    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("languages/ts/apicall")!!
        val path = Paths.get(resource.toURI()).toFile().absolutePath

//        val path = "/Volumes/source/archguard/archguard-frontend/archguard/src"
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        assertEquals(4, nodes.size)

        // fileName::componentName
        val componentList: MutableList<String> = mutableListOf()

        // 1. first create Component with FunctionCall maps based on Import
        // 2. build axios/umi-request to an API call method
        // 3. mapping for results
        nodes.forEach { node ->
            val inbounds: MutableList<String> = mutableListOf()
            val outbounds: MutableList<String> = mutableListOf()

            val isComponent = node.fileExt() == "tsx"
            val moduleName = relativeRoot(path, node.FilePath).substringBeforeLast('.', "")

            node.Imports.forEach { imp ->
                imp.UsageName.forEach {
                    val source = ecmaImportConvert(path, node.FilePath, imp.Source)
                    inbounds += "${source}::${it}"
                }
            }

            val componentName = naming(moduleName, node.NodeName)
            if (isComponent) {
                componentList += componentName
                componentCallMap[componentName] = mutableListOf()
            }

            node.Functions.forEach { func ->
                outbounds += moduleName + "::" + func.Name

                var calleeName = "$componentName::${func.Name}"
                if (isComponent) {
                    calleeName = componentName
                }

                // todo: recursion to collection all calls
                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                            findAxiosToHttpList(call, httpAdapterMap, calleeName)
                            if (isComponent) {
                                componentCallMap[componentName]?.plusAssign((call.FunctionName))
                            }
                        }
                    }
                    recursiveCall(func, calleeName, isComponent, componentName)
                }
            }

            if (isComponent) {
                componentInbounds[componentName] = inbounds
            }
        }

        var componentCalls: Array<ComponentHttpCall> = arrayOf();
        componentInbounds.forEach { map ->
            val componentRef = ComponentHttpCall(name = map.key)
            map.value.forEach {
                if (httpAdapterMap[it] != null) {
                    val call = httpAdapterMap[it]!!
                    val httpApi = FeHttpApiCall()

                    httpApi.caller = it
                    call.Parameters.forEach { prop ->
                        for (codeProperty in prop.ObjectValue) {
                            when (codeProperty.TypeValue) {
                                "baseURL" -> {
                                    httpApi.base = codeProperty.ObjectValue[0].TypeValue
                                }
                                "url" -> {
                                    httpApi.url = codeProperty.ObjectValue[0].TypeValue
                                }
                                "method" -> {
                                    httpApi.method = codeProperty.ObjectValue[0].TypeValue
                                }
                                "data" -> {
                                    httpApi.data = codeProperty.ObjectValue[0].TypeValue
                                }
                            }
                        }
                    }

                    httpApi.caller = "api/addition/systemInfo::updateSystemInfo"
                    componentRef.apiRef += httpApi
                }
            }

            if (componentRef.apiRef.isNotEmpty()) {
                componentCalls += componentRef
            }
        }

        File("api.json").writeText(Json.encodeToString(componentCalls))

        assertEquals(1, componentCalls.size)
        assertEquals("BadSmellThreshold/BadSmellThreshold", componentCalls[0].name)
        val apiRef = componentCalls[0].apiRef[0]
        assertEquals("api/addition/systemInfo::updateSystemInfo", apiRef.caller)
        assertEquals("baseURL", apiRef.base)
        assertEquals("systemInfoApi", apiRef.url)
        assertEquals("PUT", apiRef.method)
        assertEquals("parameter", apiRef.data)
    }

    private fun recursiveCall(func: CodeFunction, calleeName: String, isComponent: Boolean, componentName: String) {
        func.InnerFunctions.forEach { inner ->
            run {
                inner.FunctionCalls.forEach { innerCall ->
                    run {
                        callMap[calleeName] = innerCall
                        findAxiosToHttpList(innerCall, httpAdapterMap, calleeName)
                        if (isComponent) {
                            componentCallMap[componentName]?.plusAssign((innerCall.FunctionName))
                        }
                    }
                }
            }

            func.InnerFunctions.forEach {
                recursiveCall(it, calleeName, isComponent, componentName)
            }
        }
    }

    private fun naming(moduleName: String, nodeName: String): String {
        if (nodeName == "default") {
            return moduleName
        }

        return "${moduleName}::${nodeName}"
    }

    private fun findAxiosToHttpList(call: CodeCall, httpAdapterMap: HashMap<String, CodeCall>, calleeName: String) {
        val callName = call.FunctionName
        if (callName == "axios" || callName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                httpAdapterMap[calleeName] = call
            }
        }
    }
}
