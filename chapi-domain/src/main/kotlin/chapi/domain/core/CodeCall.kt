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
 * axios.get("/api").then(res => res.data).catch(handleError);
 * // ReceiverExpr="axios", Callee="get", Chain=["then", "catch"]
 *
 * user?.profile?.getName();
 * // ReceiverExpr="user?.profile", Callee="getName", IsOptional=true
 * ```
 *
 * ### Go
 * ```go
 * v1.Group("/api").GET("/users", handler)
 * // ReceiverExpr="v1", Callee="Group", Chain=["GET"]
 * // OriginNodeName="v1" (for backward compatibility)
 * ```
 *
 * ### Rust
 * ```rust
 * client.get(url).send().await?.json::<T>().await?
 * // ReceiverExpr="client", Callee="get", Chain=["send", "await", "json", "await"]
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
     * For Class/DataStruct, it's ClassName.
     * For Function, it's empty.
     *
     * For new code, prefer using [ReceiverExpr] for the receiver expression
     * and [ReceiverType] for the resolved type.
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
     * **Legacy field - kept for backward compatibility.**
     *
     * like "v1.Group", the v1 will be the OriginNodeName.
     *
     * For new code, prefer using [ReceiverExpr] for the full receiver expression.
     */
    @Since("2.0.0-Beta.9")
    var OriginNodeName: String = "",

    // ==================== New Structured Fields (Since 2.4.0) ====================

    /**
     * The original receiver expression as it appears in source code.
     *
     * Examples:
     * - `foo.bar()` → ReceiverExpr = "foo"
     * - `this.method()` → ReceiverExpr = "this"
     * - `obj?.prop?.method()` → ReceiverExpr = "obj?.prop"
     * - `getService().call()` → ReceiverExpr = "getService()"
     * - `SomeClass.staticMethod()` → ReceiverExpr = "SomeClass"
     *
     * Empty for standalone function calls without a receiver.
     */
    @Since("2.4.0")
    var ReceiverExpr: String = "",

    /**
     * The resolved type of the receiver, if type analysis is available.
     *
     * This allows cross-language type comparison and understanding of what
     * type the method is being called on.
     *
     * Examples:
     * - `axios.get()` → ReceiverType might be CodeTypeRef(name="AxiosInstance")
     * - `user.getName()` → ReceiverType might be CodeTypeRef(name="User")
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
     * Whether this is an optional call (TypeScript/JavaScript).
     *
     * Examples:
     * - `obj?.method()` → IsOptional = true
     * - `array?.[index]` → IsOptional = true (optional element access)
     * - `func?.()` → IsOptional = true (optional function call)
     */
    @Since("2.4.0")
    var IsOptional: Boolean = false,

    /**
     * Method/property names in a call chain, preserving the call sequence.
     *
     * This captures fluent API patterns and promise chains where multiple
     * methods are called in sequence on the results of previous calls.
     *
     * Examples:
     * - `axios.get(url).then(fn).catch(err)` → Chain = ["then", "catch"]
     * - `builder.setName(n).setAge(a).build()` → Chain = ["setAge", "build"]
     * - `query.where(cond).orderBy(col).limit(10)` → Chain = ["orderBy", "limit"]
     *
     * The first call (e.g., "get", "setName", "where") is stored in [FunctionName] or [Callee],
     * subsequent calls are stored here in order.
     */
    @Since("2.4.0")
    var Chain: List<String> = listOf(),

    /**
     * Arguments for each method in the call chain.
     *
     * Maps to [Chain] by index - ChainArguments[0] contains arguments for Chain[0], etc.
     * This allows tracking arguments passed to each method in a fluent API chain.
     *
     * Example:
     * - `builder.setName("Alice").setAge(30)` with Chain=["setAge"]
     * - ChainArguments = [[CodeProperty(TypeValue="30", TypeType="int")]]
     */
    @Since("2.4.0")
    var ChainArguments: List<List<CodeProperty>> = listOf(),

    /**
     * The return type of this call, if type analysis is available.
     *
     * Useful for understanding what type flows through a call chain.
     */
    @Since("2.4.0")
    var ReturnType: CodeTypeRef? = null,
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

    /**
     * Returns all method names in the call, including the primary function and chain.
     *
     * Example: For `axios.get(url).then(fn).catch(err)`:
     * - FunctionName = "get" (or Callee = "get")
     * - Chain = ["then", "catch"]
     * - allMethodNames() returns ["get", "then", "catch"]
     */
    fun allMethodNames(): List<String> {
        val primary = Callee.ifEmpty { FunctionName }
        return if (Chain.isEmpty()) {
            listOf(primary)
        } else {
            listOf(primary) + Chain
        }
    }

    /**
     * Returns true if this call represents a chain (multiple method calls).
     */
    fun isChainedCall(): Boolean = Chain.isNotEmpty()

    /**
     * Returns the last method name in the call chain.
     *
     * Example: For `axios.get(url).then(fn).catch(err)`, returns "catch"
     */
    fun lastMethodName(): String = Chain.lastOrNull() ?: (Callee.ifEmpty { FunctionName })

    /**
     * Returns the total number of method calls (primary + chain).
     */
    fun chainLength(): Int = 1 + Chain.size

    /**
     * Builds a string representation of the full call chain.
     *
     * Example: For ReceiverExpr="axios", Callee="get", Chain=["then", "catch"]
     * Returns: "axios.get().then().catch()"
     */
    fun buildChainString(): String {
        val receiver = ReceiverExpr.ifEmpty { NodeName }
        val methods = allMethodNames().joinToString(".") { "$it()" }
        return if (receiver.isNotEmpty()) "$receiver.$methods" else methods
    }

}
