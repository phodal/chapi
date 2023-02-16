package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeModule (
    var Packages : List<CodePackage> = listOf(),
    var packageInfo : CodePackageInfo = CodePackageInfo()
)
