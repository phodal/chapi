package chapi.app.frontend.identify

import chapi.app.frontend.HttpApiCallInfo
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeImport

interface HttpIdentify {
    fun isMatch(call: CodeCall, imports: Array<CodeImport>): Boolean
    fun convert(call: CodeCall): HttpApiCallInfo
}
