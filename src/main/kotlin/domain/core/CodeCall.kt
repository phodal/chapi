package domain.core

open class CodeCall(
    var Package: String = "",
    var Type: String = "",
    var NodeName: String = "",
    var MethodName: String = "",
    var Parameters: Array<CodeProperty> = arrayOf<CodeProperty>(),
    var Position: CodePosition = CodePosition()
) {
    open fun buildClassFullName(): String {
        return this.Package + "." + this.NodeName
    }

    open fun buildFullMethodName(): String {
        if (MethodName == "") {
            return this.Package + "." + this.NodeName
        }

        return this.Package + "." + this.NodeName + "." + this.MethodName
    }

    open fun isSystemOutput(): Boolean {
        return this.NodeName == "System.out" && (this.MethodName == "println" || this.MethodName == "printf" || this.MethodName == "print")
    }

    open fun isThreadSleep(): Boolean {
        return this.NodeName == "Thread" && this.MethodName == "sleep"
    }

    open fun hasAssertion(): Boolean {
        val methodName = this.MethodName.toLowerCase()
        for (assertion in DomainConstants.ASSERTION_LIST) {
            if (methodName.startsWith(assertion)) {
                return true
            }
        }

        return false
    }
}
