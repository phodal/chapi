package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeDependency(
    var GroupId: String = "",
    var ArtifactId: String = "",
    var Scope: String = "",
    var Type: String = "",
    var Version: String = "",
    var Optional: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeDependency) return false

        if (GroupId != other.GroupId) return false
        if (ArtifactId != other.ArtifactId) return false
        if (Scope != other.Scope) return false
        if (Type != other.Type) return false
        if (Version != other.Version) return false
        if (Optional != other.Optional) return false

        return true
    }

    override fun hashCode(): Int {
        var result = GroupId.hashCode()
        result = 31 * result + ArtifactId.hashCode()
        result = 31 * result + Scope.hashCode()
        result = 31 * result + Type.hashCode()
        result = 31 * result + Version.hashCode()
        result = 31 * result + Optional.hashCode()
        return result
    }
}
