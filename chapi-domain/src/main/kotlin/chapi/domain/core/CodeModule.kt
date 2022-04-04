package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeModule (
    var Packages : Array<CodePackage> = arrayOf(),
    var packageInfo : CodePackageInfo = CodePackageInfo()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeModule) return false

        if (!Packages.contentEquals(other.Packages)) return false
        if (packageInfo != other.packageInfo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Packages.contentHashCode()
        result = 31 * result + packageInfo.hashCode()
        return result
    }
}
