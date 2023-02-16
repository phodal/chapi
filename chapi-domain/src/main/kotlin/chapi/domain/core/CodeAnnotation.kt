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
data class CodeAnnotation(
    var Name: String = "",
    var KeyValues: Array<AnnotationKeyValue> = arrayOf()
) {
    @Deprecated("is for Java/Kotlin Only", ReplaceWith("this.Name == \"Component\" || this.Name == \"Repository\""))
    fun isComponentOrRepository(): Boolean {
        return this.Name == "Component" || this.Name == "Repository"
    }

    @Deprecated("is for Java/Kotlin Only", ReplaceWith("this.Name == \"Test\""))
    fun isTest(): Boolean {
        return this.Name == "Test"
    }

    @Deprecated("is for Java/Kotlin Only", ReplaceWith("this.Name == \"Ignore\""))
    fun isIgnore(): Boolean {
        return this.Name == "Ignore"
    }

    fun isIgnoreOrTest(): Boolean {
        return this.isIgnore() || this.isTest()
    }

    @Deprecated("is for Java/Kotlin Only", ReplaceWith("this.Name == \"Override\""))
    fun isOverride(): Boolean {
        return this.Name == "Override"
    }
}
