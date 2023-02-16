package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeModule (
    var Packages : Array<CodePackage> = arrayOf(),
    var packageInfo : CodePackageInfo = CodePackageInfo()
)
