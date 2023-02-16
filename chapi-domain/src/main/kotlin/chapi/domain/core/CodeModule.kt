package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeModule (
    var Packages : Array<CodePackage> = arrayOf(),
    var packageInfo : CodePackageInfo = CodePackageInfo()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodeModule

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
