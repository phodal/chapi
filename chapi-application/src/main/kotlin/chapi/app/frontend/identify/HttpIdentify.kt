package chapi.app.frontend.identify

import chapi.app.frontend.HttpApiCallInfo
import chapi.domain.core.CodeCall

interface HttpIdentify {
    fun isMatch(call: CodeCall): Boolean
    fun convert(call: CodeCall): HttpApiCallInfo
}
