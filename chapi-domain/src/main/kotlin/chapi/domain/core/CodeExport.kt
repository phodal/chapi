package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
class CodeExport(
    var Name: String = "",
    var SourceFile: String = "",
    var Type: DataStructType = DataStructType.CLASS
) {

}
