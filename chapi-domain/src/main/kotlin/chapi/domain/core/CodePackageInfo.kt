package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodePackageInfo(
    var ProjectName: String = "",
    var Dependencies : Array<CodeDependency> = arrayOf()
) {

}
