package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class AnnotationKeyValue(var Key: String = "", var Value: String = "")

@Serializable
data class CodeAnnotation(
    var Name: String = "",
    var KeyValues: List<AnnotationKeyValue> = listOf()
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
