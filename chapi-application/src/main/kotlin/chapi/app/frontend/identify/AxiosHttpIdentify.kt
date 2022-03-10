package chapi.app.frontend.identify

import chapi.app.frontend.HttpApiCallInfo
import chapi.domain.core.CodeCall

open class AxiosHttpIdentify : HttpIdentify {
    override fun isMatch(call: CodeCall): Boolean {
        if (call.FunctionName == "axios" || call.FunctionName.startsWith("axios.")) {
            if (call.Parameters.isNotEmpty()) {
                return true
            }
        }

        return false
    }

    override fun convert(call: CodeCall): HttpApiCallInfo {
        val httpApi = HttpApiCallInfo()
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

        return httpApi
    }
}
