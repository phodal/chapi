package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeProject(
    var Modules: Array<CodeModule> = arrayOf()
) {

}
