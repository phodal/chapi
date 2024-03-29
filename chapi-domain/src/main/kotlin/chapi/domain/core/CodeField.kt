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
)
