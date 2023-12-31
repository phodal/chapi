package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class AnnotationKeyValue(var Key: String = "", var Value: String = "")

/**
 * Represents a code annotation.
 *
 * In TypeScript, it is a decorator.
 * In Rust, it is an attribute, like `#[derive(Debug, Clone)]`
 * In Java/Kotlin/Scala, it is an annotation.
 *
 * @property Name The name of the annotation.
 * @property KeyValues The list of key-value pairs associated with the annotation.
 * @property Position The position of the annotation in the code.
 */
@Serializable
data class CodeAnnotation(
    var Name: String = "",
    var KeyValues: List<AnnotationKeyValue> = listOf(),
    var Position: CodePosition = CodePosition(),
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
