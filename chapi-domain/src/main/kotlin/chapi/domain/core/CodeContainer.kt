package chapi.domain.core

import chapi.domain.core.CodeDataStruct.Companion.contentByPosition
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
    var Containers: List<CodeContainer> = listOf(),
    @Since("2.2.5")
    var Content: String = "",
) {
    fun buildSourceCode(codeLines: List<String>) {
        this.DataStructures.map { ds ->
            ds.Imports = this.Imports

            ds.Content = contentByPosition(codeLines, ds.Position)
            ds.Functions.map {
                // in Chapi, the position of function is not correct, so we need to fix it
                val position = it.Position
                if (it.Annotations.isNotEmpty()) {
                    val annotationPos = it.Annotations.first().Position
                    position.StartLine = annotationPos.StartLine
                    position.StartLinePosition = annotationPos.StartLinePosition
                }

                it.Content = contentByPosition(codeLines, it.Position)
            }
        }
    }
}
