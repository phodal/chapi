package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodePackageInfo(
    var ProjectName: String = "",
    var Dependencies: Array<CodeDependency> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodePackageInfo

        if (ProjectName != other.ProjectName) return false
        if (!Dependencies.contentEquals(other.Dependencies)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ProjectName.hashCode()
        result = 31 * result + Dependencies.contentHashCode()
        return result
    }
}
