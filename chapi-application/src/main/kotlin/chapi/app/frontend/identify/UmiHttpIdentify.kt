package chapi.app.frontend.identify

import chapi.app.frontend.HttpApiCallInfo
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeImport

class UmiHttpIdentify : HttpIdentify {
    override fun isMatch(call: CodeCall, imports: Array<CodeImport>): Boolean {
        val imps = imports.filter { it.Source == "umi-request" }
        if (imps.isEmpty()) {
            return false
        }

        if (call.FunctionName == "request") {
            return true
        }

        return false
    }

    override fun convert(call: CodeCall): HttpApiCallInfo {
        val url = call.Parameters[0].TypeValue
        val httpApi = HttpApiCallInfo(url = url)

        for (codeProperty in call.Parameters[1].ObjectValue) {
            when (codeProperty.TypeValue) {
                "method" -> {
                    httpApi.method = codeProperty.ObjectValue[0].TypeValue
                }
                "data" -> {
                    httpApi.data = codeProperty.ObjectValue[0].TypeValue
                }
            }
        }

        return httpApi
    }
}
