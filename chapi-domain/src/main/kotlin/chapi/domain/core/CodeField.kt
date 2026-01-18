package chapi.domain.core

import kotlinx.serialization.Serializable

/**
 * Represents a field in a Java class.
 *
 * In Java, a field is defined as follows:
 *
 * ```java
 * private String helloText = "hello, world";
 * //       ||        ||          ||
 * //  <TypeType> <TypeKey> <TypeValue>
 * ```
 */
@Serializable
data class CodeField(
    var TypeType: String = "",
    var TypeValue: String = "",
    var TypeKey: String = "",
    var Annotations: List<CodeAnnotation> = listOf(),
    var Modifiers: List<String> = listOf(),
    /// for TypeScript and JavaScript only, examples: `export default sample = createHello() `
    var Calls: List<CodeCall> = listOf(),
    /// import 2.2.3 for Toml
    @Since("2.2.3")
    var ArrayValue: List<CodeField> = listOf(),
    /**
     * Structured type reference providing detailed type information for cross-language analysis.
     * This complements the string-based [TypeType] field with a structured representation
     * that supports generics, unions, intersections, pointers, and other complex type constructs.
     *
     * @see CodeTypeRef for detailed documentation on the structured type system
     * @since 2.3.0
     */
    @Since("2.3.0")
    var TypeRef: CodeTypeRef? = null,
)
