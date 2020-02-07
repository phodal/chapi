package domain.core

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
open class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var Modifiers: Array<String> = arrayOf<String>()
) {

}
