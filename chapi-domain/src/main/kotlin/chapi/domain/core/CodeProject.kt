package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeProject(
    var Modules: Array<CodeModule> = arrayOf()
)
