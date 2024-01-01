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

@Serializable
data class CodeCall(
    var Package: String = "",
    /**
     * for Java, it can be CreatorClass, lambda
     * for TypeScript, can be anonymous function, arrow function
     */
    var Type: CallType = CallType.FUNCTION,
    /**
     * for Class/DataStruct, it's ClassName
     * for Function, it's empty
     */
    var NodeName: String = "",
    var FunctionName: String = "",
    var Parameters: List<CodeProperty> = listOf(),
    var Position: CodePosition = CodePosition(),
    /**
     * like "v1.Group", the v1 will be the OriginNodeName
     *
     * @since 2.0.0-Beta.9
    */
    var OriginNodeName: String = "",
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
