package domain.core

import domain.DomainConstants
import java.lang.reflect.Modifier
import java.util.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
open class CodeCall(
    var Package: String = "",
    var Type: String = "",
    var NodeName: String = "",
    var FunctionName: String = "",
    var Parameters: Array<CodeProperty> = arrayOf<CodeProperty>(),
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
        val methodName = this.FunctionName.toLowerCase()
        for (assertion in DomainConstants.ASSERTION_LIST) {
            if (methodName.startsWith(assertion)) {
                return true
            }
        }

        return false
    }

    override fun toString(): String {
        return Json(JsonConfiguration.Stable).stringify(serializer(), this)
    }
}
