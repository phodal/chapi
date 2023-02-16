package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeProperty(
    var Modifiers: Array<String> = arrayOf(),
    var DefaultValue: String = "",
    var TypeValue: String,
    var TypeType: String,
    var Annotations: Array<CodeAnnotation> = arrayOf(),
    // for TypeScript and Parameter
    var ObjectValue: Array<CodeProperty> = arrayOf(),
    var ReturnTypes: Array<CodeProperty> = arrayOf(),
    var Parameters: Array<CodeProperty> = arrayOf(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CodeProperty

        if (!Modifiers.contentEquals(other.Modifiers)) return false
        if (DefaultValue != other.DefaultValue) return false
        if (TypeValue != other.TypeValue) return false
        if (TypeType != other.TypeType) return false
        if (!Annotations.contentEquals(other.Annotations)) return false
        if (!ObjectValue.contentEquals(other.ObjectValue)) return false
        if (!ReturnTypes.contentEquals(other.ReturnTypes)) return false
        if (!Parameters.contentEquals(other.Parameters)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Modifiers.contentHashCode()
        result = 31 * result + DefaultValue.hashCode()
        result = 31 * result + TypeValue.hashCode()
        result = 31 * result + TypeType.hashCode()
        result = 31 * result + Annotations.contentHashCode()
        result = 31 * result + ObjectValue.contentHashCode()
        result = 31 * result + ReturnTypes.contentHashCode()
        result = 31 * result + Parameters.contentHashCode()
        return result
    }
}
