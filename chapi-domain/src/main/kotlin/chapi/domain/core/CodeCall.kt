package chapi.domain.core

import chapi.domain.DomainConstants
import kotlinx.serialization.Serializable

enum class CallType(val calltype: String) {
    FIELD("field"),
    LAMBDA("lambda"),
    ARROW("arrow"),

    // for Kotlin,
    CREATOR("creator"),
    FUNCTION("function"),

    // method come from parent
    SUPER("super"),
    SAME_PACKAGE("same package"),
    SELF("self"),
    CHAIN("chain"),
    STATIC("static")
}

/**
 * Represents a function/method call in source code.
 *
 * This class supports structured representation of calls across different languages,
 * including chained calls, optional chaining, and receiver type resolution.
 *
 * ## Examples by Language
 *
 * ### TypeScript/JavaScript
 * ```typescript
 * axios.get('/api').then(res => res.data);
 * // ReceiverExpr = "axios", Callee = "get", Chain = ["get", "then"]
 *
 * user?.profile?.name;
 * // IsOptionalChain = true
 * ```
 *
 * ### Go
 * ```go
 * client.Do(req)
 * // ReceiverExpr = "client", ReceiverType = CodeTypeRef("*http.Client"), Callee = "Do"
 * ```
 *
 * ### Rust
 * ```rust
 * v1::Group::new().route("/api", handler)
 * // ReceiverExpr = "v1::Group::new()", Callee = "route"
 * ```
 *
 * @since 2.0.0 (structured fields since 2.4.0)
 */
@Serializable
data class CodeCall(
    var Package: String = "",
    /**
     * for Java, it can be CreatorClass, lambda
     * for TypeScript, can be anonymous function, arrow function
     */
    var Type: CallType = CallType.FUNCTION,
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * for Class/DataStruct, it's ClassName
     * for Function, it's empty
     *
     * For new code, prefer using [ReceiverExpr] and [ReceiverType] instead.
     */
    var NodeName: String = "",
    /**
     * **Legacy field - kept for backward compatibility.**
     *
     * For new code, prefer using [Callee] instead.
     */
    var FunctionName: String = "",
    var Parameters: List<CodeProperty> = listOf(),
    var Position: CodePosition = CodePosition(),
    /**
     * like "v1.Group", the v1 will be the OriginNodeName
    */
    @Since("2.0.0-Beta.9")
    var OriginNodeName: String = "",

    // ==================== New Structured Fields (Since 2.4.0) ====================

    /**
     * The original receiver expression text as it appears in source code.
     *
     * Examples:
     * - `axios.get(...)` → ReceiverExpr = "axios"
     * - `this.service.call(...)` → ReceiverExpr = "this.service"
     * - `v1::Group::new().route(...)` → ReceiverExpr = "v1::Group::new()"
     * - `client.Do(req)` → ReceiverExpr = "client"
     *
     * This is the raw text of the expression on which the method is called.
     */
    @Since("2.4.0")
    var ReceiverExpr: String = "",

    /**
     * The resolved type of the receiver, if known.
     *
     * Examples:
     * - Go: `client.Do(req)` where client is `*http.Client` → ReceiverType = CodeTypeRef(name="*http.Client")
     * - TypeScript: `user.save()` where user is `User` → ReceiverType = CodeTypeRef(name="User")
     *
     * This field is optional and depends on the parser's ability to resolve types.
     */
    @Since("2.4.0")
    var ReceiverType: CodeTypeRef? = null,

    /**
     * The function/method name being called.
     *
     * This is a structured alternative to [FunctionName] that clearly represents
     * just the callee name without any receiver or chain information.
     *
     * Examples:
     * - `axios.get(...)` → Callee = "get"
     * - `foo.bar.baz()` → Callee = "baz"
     * - `println(...)` → Callee = "println"
     */
    @Since("2.4.0")
    var Callee: String = "",

    /**
     * Whether this call uses optional chaining (TypeScript/JavaScript).
     *
     * Examples:
     * - `user?.profile` → IsOptionalChain = true
     * - `obj?.method?.()` → IsOptionalChain = true
     * - `user.profile` → IsOptionalChain = false
     */
    @Since("2.4.0")
    var IsOptionalChain: Boolean = false,

    /**
     * The chain of method/property names in a chained call.
     *
     * For calls like `axios.get('/api').then(fn).catch(err)`, this preserves
     * the full chain for analysis.
     *
     * Examples:
     * - `axios.get('/api').then(fn).catch(err)` → Chain = ["get", "then", "catch"]
     * - `builder.setName("x").setAge(10).build()` → Chain = ["setName", "setAge", "build"]
     * - `foo.bar()` → Chain = ["bar"] (or empty if not a chain)
     *
     * Empty for non-chained calls.
     */
    @Since("2.4.0")
    var Chain: List<String> = listOf(),
) {

    fun buildClassFullName(): String {
        return "${this.Package}.${this.NodeName}"
    }

    fun buildFullMethodName(): String {
        if (FunctionName == "") {
            return "${this.Package}.${this.NodeName}"
        }

        return "${this.Package}.${this.NodeName}.${this.FunctionName}"
    }

    @Deprecated(
        "is for Java/Kotlin Only",
        ReplaceWith("this.NodeName == \"System.out\" && (this.FunctionName == \"println\" || this.FunctionName == \"printf\" || this.FunctionName == \"print\")")
    )
    fun isSystemOutput(): Boolean {
        return this.NodeName == "System.out" && (this.FunctionName == "println" || this.FunctionName == "printf" || this.FunctionName == "print")
    }

    @Deprecated("is for Java/Kotlin Only", ReplaceWith("this.NodeName == \"Thread\" && this.FunctionName == \"sleep\""))
    fun isThreadSleep(): Boolean {
        return this.NodeName == "Thread" && this.FunctionName == "sleep"
    }

    fun hasAssertion(): Boolean {
        val methodName = this.FunctionName.lowercase()
        DomainConstants.ASSERTION_LIST.forEach { assertion ->
            if (methodName.startsWith(assertion)) {
                return true
            }
        }

        return false
    }

}
