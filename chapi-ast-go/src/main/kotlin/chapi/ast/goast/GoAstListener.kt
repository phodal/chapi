package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener

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

}
