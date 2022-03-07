package chapi.domain.core

import chapi.domain.DomainConstants
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
open class CodeCall(
    var Package: String = "",
    // for Java, it can be CreatorClass, lambda
    // for TypeScript, can be anonymous function, arrow function
    var Type: String = "",
    // for Class/DataStruct, it's ClassName
    // for Function, it's empty
    var NodeName: String = "",
    var FunctionName: String = "",
    var Parameters: Array<CodeProperty> = arrayOf(),
    var Position: CodePosition = CodePosition()
) {
    open fun buildClassFullName(): String {
        return this.Package + "." + this.NodeName
    }

    open fun buildFullMethodName(): String {
        if (FunctionName == "") {
            return this.Package + "." + this.NodeName
        }

        return this.Package + "." + this.NodeName + "." + this.FunctionName
    }

    open fun isSystemOutput(): Boolean {
        return this.NodeName == "System.out" && (this.FunctionName == "println" || this.FunctionName == "printf" || this.FunctionName == "print")
    }

    open fun isThreadSleep(): Boolean {
        return this.NodeName == "Thread" && this.FunctionName == "sleep"
    }

    open fun hasAssertion(): Boolean {
        val methodName = this.FunctionName.lowercase()
        for (assertion in DomainConstants.ASSERTION_LIST) {
            if (methodName.startsWith(assertion)) {
                return true
            }
        }

        return false
    }
}
