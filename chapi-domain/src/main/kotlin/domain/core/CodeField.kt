package domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var Modifiers: Array<String> = arrayOf<String>()
) {

}
