package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener
import chapi.domain.core.CodeProperty

open class GoAstListener : GoParserBaseListener() {
    fun getStructNameFromReceiver(parameters: GoParser.ParametersContext?): String {
        parameters?.parameterDecl()?.forEach {
            var typeType = it.type_().text
            if (typeType.startsWith("*")) {
                typeType = typeType.removePrefix("*")
            }

            return typeType
        }

        return ""
    }

    protected fun buildReturnTypeFromSignature(signatureContext: GoParser.SignatureContext?): List<CodeProperty> {
        var returns: List<CodeProperty> = listOf()

        signatureContext?.result()?.let { result ->
            result.parameters()?.parameterDecl()?.forEach {
                returns += CodeProperty(TypeType = it.text, TypeValue = "")
            }

            if (result.type_() != null) {
                returns += CodeProperty(TypeType = result.type_().text, TypeValue = "")
            }
        }

        return returns;
    }

}
