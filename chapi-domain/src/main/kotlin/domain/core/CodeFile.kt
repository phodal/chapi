package domain.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
open class CodeFile(
    var FullName: String = "",
    var PackageName: String = "",
    var Packages: Array<CodePackage> = arrayOf(),
    var Imports: Array<CodeImport> = arrayOf(),
    var Members: Array<CodeMember> = arrayOf(),
    var DataStructures: Array<CodeDataStruct> = arrayOf(),
    var Fields: Array<CodeField> = arrayOf()
) {
    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
