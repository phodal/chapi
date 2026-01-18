package chapi.domain.core

import kotlinx.serialization.Serializable

@Serializable
data class CodeProperty(
    var Modifiers: List<String> = listOf(),
    var DefaultValue: String = "",
    var TypeValue: String,
    var TypeType: String,
    var Annotations: List<CodeAnnotation> = listOf(),
    /// for TypeScript and Parameter
    var ObjectValue: List<CodeProperty> = listOf(),
    var ReturnTypes: List<CodeProperty> = listOf(),
    /**
     * in Golang, we also use it to save fmt.Print series values, like `fmt.Println("hello", "world")`
     */
    var Parameters: List<CodeProperty> = listOf(),
    /** Structured type reference for cross-language type analysis. @since 2.3.0 */
    @Since("2.3.0")
    var TypeRef: CodeTypeRef? = null,
)
