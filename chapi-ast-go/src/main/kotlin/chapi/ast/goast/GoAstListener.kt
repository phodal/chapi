package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener
import chapi.domain.core.CodeProperty

open class GoAstListener : GoParserBaseListener() {
    fun buildParameters(parametersCtx: GoParser.ParametersContext?): Array<CodeProperty> {
        return parametersCtx?.parameterDecl()?.map {
            CodeProperty(
                TypeValue = it.identifierList().text,
                TypeType = it.type_().text
            )
        }?.toTypedArray() ?: return arrayOf()
    }

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

    protected fun buildReturnTypeFromSignature(signatureContext: GoParser.SignatureContext?): Array<CodeProperty> {
        var returns: Array<CodeProperty> = arrayOf()

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
