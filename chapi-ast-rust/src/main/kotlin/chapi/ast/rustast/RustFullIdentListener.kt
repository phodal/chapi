package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeProperty


open class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
    override fun enterCallExpression(ctx: RustParser.CallExpressionContext?) {
        val function = if (isEnteredIndividualFunction) {
            currentIndividualFunction
        } else {
            currentFunction
        }

        val functionName = ctx?.expression()?.text
        val split = functionName?.split("::")
        function.FunctionCalls += CodeCall(
            Package = split?.dropLast(1)?.joinToString("::") ?: "",
            NodeName = split?.dropLast(1)?.joinToString("::") ?: "",
            FunctionName = split?.last() ?: "",
            Parameters = ctx?.callParams()?.expression()?.map {
                CodeProperty(
                    TypeValue = it.text,
                    TypeType = it.text
                )
            } ?: listOf(),
        )
    }
}
