package chapi.domain.core

import kotlinx.serialization.Serializable

// position need to remove in functions, if one function change, others position will also change
@Serializable
data class CodePosition(
    var StartLine: Int = 0,
    var StartLinePosition: Int = 0,
    var StopLine: Int = 0,
    var StopLinePosition: Int = 0
)
