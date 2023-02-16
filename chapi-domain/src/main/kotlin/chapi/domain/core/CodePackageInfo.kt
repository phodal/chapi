package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodePackageInfo(
    var ProjectName: String = "",
    var Dependencies: List<CodeDependency> = listOf()
)
