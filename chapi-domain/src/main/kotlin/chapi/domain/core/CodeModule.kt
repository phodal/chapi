package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeModule (
    var Packages : Array<CodePackage> = arrayOf<CodePackage>(),
    var packageInfo : CodePackageInfo = CodePackageInfo()
) {

}
