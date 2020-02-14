package chapi.domain.core

open class CodeDependency(
    var GroupId: String = "",
    var ArtifactId: String = "",
    var Scope: String = "",
    var Type: String = "",
    var Version: String = "",
    var Optional: Boolean = false
) {

}
