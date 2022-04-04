package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeProject(
    var Modules: Array<CodeModule> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeProject) return false

        if (!Modules.contentEquals(other.Modules)) return false

        return true
    }

    override fun hashCode(): Int {
        return Modules.contentHashCode()
    }
}
