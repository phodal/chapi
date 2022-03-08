package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeImport(
    var Source: String = "",
    // todo: define for new usage
    var AsName: String = "",
    // import UsageName from 'usage'
    // import AsSource as UsageName from 'source'
    var UsageName: Array<String> = arrayOf(),
    var Scope: String = ""
) {

}
