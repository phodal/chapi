package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
open class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var TypeKey: String = "",
    var Annotaitons: Array<CodeAnnotation> = arrayOf(),
    var Modifiers: Array<String> = arrayOf(),
    // for TypeScript and JavaScript only, examples: `export default sample = createHello() `
    var Calls: Array<CodeCall> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeField) return false

        if (TypeType != other.TypeType) return false
        if (TypeValue != other.TypeValue) return false
        if (TypeKey != other.TypeKey) return false
        if (!Annotaitons.contentEquals(other.Annotaitons)) return false
        if (!Modifiers.contentEquals(other.Modifiers)) return false
        if (!Calls.contentEquals(other.Calls)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = TypeType.hashCode()
        result = 31 * result + TypeValue.hashCode()
        result = 31 * result + TypeKey.hashCode()
        result = 31 * result + Annotaitons.contentHashCode()
        result = 31 * result + Modifiers.contentHashCode()
        result = 31 * result + Calls.contentHashCode()
        return result
    }
}
