package domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeImport(
    var Source: String = "",
    var AsName: String = "",
    var UsageName: Array<String> = arrayOf<String>(),
    var Scope: String = ""
) {

}
