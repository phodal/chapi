package chapi.app.frontend

import chapi.app.frontend.identify.AxiosHttpIdentify
import chapi.app.frontend.path.ecmaImportConvert
import chapi.app.frontend.path.relativeRoot
import chapi.domain.core.*

class FrontendApiAnalyser {
    private var componentCallMap: HashMap<String, MutableList<String>> = hashMapOf()
    private var componentInbounds: HashMap<String, MutableList<String>> = hashMapOf()
    private var callMap: HashMap<String, CodeCall> = hashMapOf()
    private var httpAdapterMap: HashMap<String, CodeCall> = hashMapOf()

    // for Axios Http Call
    private val axiosIdent = AxiosHttpIdentify()

    // 1. first create Component with FunctionCall maps based on Import
    // 2. build axios/umi-request to an API call method
    // 3. mapping for results
    fun analysis(nodes: Array<CodeDataStruct>, path: String): Array<ComponentHttpCallInfo> {
        nodes.forEach { node ->
            var isComponent: Boolean
            val outbounds: MutableList<String> = mutableListOf()
            // update
            // tsx || jsx file
            val isComponentExt = node.fileExt() == "tsx" || node.fileExt() == "jsx"
            val isNotInterface = node.Type != DataStructType.INTERFACE
            val inbounds = createInbounds(path, node.Imports, node.FilePath)

            val moduleName = relativeRoot(path, node.FilePath).substringBeforeLast('.', "")
            val componentName = naming(moduleName, node.NodeName)

            node.Functions.forEach { func ->
                isComponent = isNotInterface && isComponentExt && func.IsReturnHtml
                if (isComponent) {
                    componentCallMap[componentName] = mutableListOf()
                }

                outbounds += naming(moduleName, func.Name)

                var calleeName = naming(componentName, func.Name)
                if (isComponent) {
                    calleeName = componentName
                }

                run {
                    func.FunctionCalls.forEach { call ->
                        run {
                            callMap[calleeName] = call
                            if (axiosIdent.isMatch(call)) {
                                httpAdapterMap[calleeName] = call
                            }
                            if (isComponent) {
                                componentCallMap[componentName]?.plusAssign((call.FunctionName))
                            }
                        }
                    }
                    recursiveCall(func, calleeName, isComponent, componentName)
                }

                if (isComponent) {
                    componentInbounds[componentName] = inbounds
                }
            }
        }

        var componentCalls: Array<ComponentHttpCallInfo> = arrayOf()
        componentInbounds.forEach { map ->
            val componentRef = ComponentHttpCallInfo(name = map.key)
            map.value.forEach {
                if (httpAdapterMap[it] != null) {
                    val call = httpAdapterMap[it]!!
                    val httpApi = axiosIdent.convert(call)
                    httpApi.caller = it
                    componentRef.apiRef += httpApi
                }
            }

            if (componentRef.apiRef.isNotEmpty()) {
                componentCalls += componentRef
            }
        }

        return componentCalls
    }

    private fun createInbounds(path: String, imports: Array<CodeImport>, filePath: String): MutableList<String> {
        val inbounds: MutableList<String> = mutableListOf()

        imports.forEach { imp ->
            imp.UsageName.forEach {
                val source = ecmaImportConvert(path, filePath, imp.Source)
                inbounds += naming(source, it)
            }
        }

        return inbounds
    }

    private fun recursiveCall(func: CodeFunction, calleeName: String, isComponent: Boolean, componentName: String) {
        func.InnerFunctions.forEach { inner ->
            run {
                inner.FunctionCalls.forEach { innerCall ->
                    run {
                        callMap[calleeName] = innerCall

                        if (axiosIdent.isMatch(innerCall)) {
                            httpAdapterMap[calleeName] = innerCall
                        }

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
}
