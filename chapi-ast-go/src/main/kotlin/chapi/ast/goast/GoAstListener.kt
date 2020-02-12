package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener
import domain.core.CodeFunction
import domain.core.CodeProperty

open class GoAstListener : GoParserBaseListener() {
    fun buildParameters(parameters: GoParser.ParametersContext?) {
        println("buildParameters")
    }

    fun getStructNameFromReceiver(parameters: GoParser.ParametersContext?): String? {
        val parameterDecls = parameters!!.parameterDecl()
        for (paramCtx in parameterDecls) {
            var typeType = paramCtx.type_().text
            if (typeType.startsWith("*")) {
                typeType = typeType.removePrefix("*")
            }
            return typeType
        }

        return ""
    }

    protected fun buildReturnTypeFromSignature(codeFunction: CodeFunction, signatureContext: GoParser.SignatureContext?): Array<CodeProperty> {
        val result = signatureContext!!.result()
        var returns : Array<CodeProperty> = arrayOf()
        if (result != null) {
            val returnType = CodeProperty(
                TypeType = result.text,
                TypeValue = ""
            )

            returns += returnType
        }

        return returns;
    }

}
