package chapi.domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
open class CodeContainer(
    var FullName: String = "",
    var PackageName: String = "",
    var Imports: Array<CodeImport> = arrayOf(),
    var Members: Array<CodeMember> = arrayOf(),
    var DataStructures: Array<CodeDataStruct> = arrayOf(),
    var Fields: Array<CodeField> = arrayOf(),
    var Containers: Array<CodeContainer> = arrayOf()
) {
    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
