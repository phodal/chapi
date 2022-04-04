package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class AnnotationKeyValue(var Key: String = "", var Value: String = "") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AnnotationKeyValue

        if (Key != other.Key) return false
        if (Value != other.Value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Key.hashCode()
        result = 31 * result + Value.hashCode()
        return result
    }
}

@Serializable
open class CodeAnnotation(
    var Name: String = "",
    var KeyValues: Array<AnnotationKeyValue> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeAnnotation) return false

        if (Name != other.Name) return false
        if (!KeyValues.contentEquals(other.KeyValues)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Name.hashCode()
        result = 31 * result + KeyValues.contentHashCode()
        return result
    }

    open fun isComponentOrRepository(): Boolean {
        return this.Name == "Component" || this.Name == "Repository"
    }

    open fun isTest(): Boolean {
        return this.Name == "Test"
    }

    open fun isIgnore(): Boolean {
        return this.Name == "Ignore"
    }

    open fun isIgnoreOrTest(): Boolean {
        return this.isIgnore() || this.isTest()
    }

    open fun isOverride(): Boolean {
        return this.Name == "Override"
    }
}
