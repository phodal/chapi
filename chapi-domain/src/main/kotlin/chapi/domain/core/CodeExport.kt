package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeExport(
    var Name: String = "",
    var SourceFile: String = "",
    var Type: DataStructType = DataStructType.CLASS
)
