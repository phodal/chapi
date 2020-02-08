package domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeModule (
    var Packages : Array<CodePackage> = arrayOf<CodePackage>()
) {

}
