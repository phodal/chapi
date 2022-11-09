package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener
import chapi.domain.core.CodeProperty

open class GoAstListener : GoParserBaseListener() {
    fun buildParameters(parametersCtx: GoParser.ParametersContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (paramCtx in parametersCtx!!.parameterDecl()) {
            val parameter = CodeProperty(
                TypeValue = paramCtx.identifierList().text,
                TypeType = paramCtx.type_().text
            )

            parameters += parameter
        }
        return parameters
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
            val parameters = result.parameters()
            if (parameters != null) {
                for (parameterDeclContext in parameters.parameterDecl()) {
                    val typeType = parameterDeclContext.text
                    val returnType =
                        CodeProperty(TypeType = typeType, TypeValue = "")
                    returns += returnType
                }
            }

            if (result.type_() != null) {
                val typeType = result.type_().text
                val returnType = CodeProperty(TypeType = typeType, TypeValue = "")
                returns += returnType
            }
        }

        return returns;
    }

}
