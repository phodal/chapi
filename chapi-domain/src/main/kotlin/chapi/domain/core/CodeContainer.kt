package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeContainer(
    var FullName: String = "",
    /**
     * In toml, the package name is the table name.
     */
    var PackageName: String = "",
    var Imports: List<CodeImport> = listOf(),
    var Members: List<CodeMember> = listOf(),
    var DataStructures: List<CodeDataStruct> = listOf(),
    /**
     * In toml, the fields are the key-value pairs.
     */
    var Fields: List<CodeField> = listOf(),
    var Containers: List<CodeContainer> = listOf()
)
