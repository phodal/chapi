package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeProperty

open class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
    override fun buildParameters(functionParameters: RustParser.FunctionParametersContext?): List<CodeProperty> {
        if (functionParameters == null) return listOf()

        return functionParameters.functionParam().map {
            val functionParamPattern = it.functionParamPattern()
            CodeProperty(
                TypeValue = functionParamPattern.pattern()?.text ?: "",
                TypeType = functionParamPattern.type_()?.text ?: "",
            )
        }
    }
}
