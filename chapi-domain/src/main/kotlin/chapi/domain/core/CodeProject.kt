package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeProject(
    var Modules: Array<CodeModule> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodeProject

        if (!Modules.contentEquals(other.Modules)) return false

        return true
    }

    override fun hashCode(): Int {
        return Modules.contentHashCode()
    }
}
