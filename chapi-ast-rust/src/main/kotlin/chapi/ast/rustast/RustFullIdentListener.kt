package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeProperty


class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
    val functionInstance = if (isEnteredIndividualFunction) {
        currentIndividualFunction
    } else {
        currentFunction
    }

    var localVars: MutableMap<String, String> = mutableMapOf()

    override fun enterLetStatement(ctx: RustParser.LetStatementContext?) {
        val varName = ctx?.patternNoTopAlt()?.patternWithoutRange()?.identifierPattern()?.identifier()?.text ?: ""
        // TODO: handle type
    }

    override fun enterCallExpression(ctx: RustParser.CallExpressionContext?) {
        val functionName = ctx?.expression()?.text
        val split = functionName?.split("::")
        functionInstance.FunctionCalls += CodeCall(
            Package = split?.dropLast(1)?.joinToString("::") ?: "",
            NodeName = split?.dropLast(1)?.joinToString("::") ?: "",
            FunctionName = split?.last() ?: "",
            Parameters = buildParameters(ctx?.callParams()),
        )
    }

    override fun enterMethodCallExpression(ctx: RustParser.MethodCallExpressionContext?) {
        val functionName = ctx?.expression()?.text
        val split = functionName?.split("::")
        // todo: handle method call
        functionInstance.FunctionCalls += CodeCall(
            Package = split?.dropLast(1)?.joinToString("::") ?: "",
            NodeName = split?.dropLast(1)?.joinToString("::") ?: "",
            FunctionName = split?.last() ?: "",
            Parameters = buildParameters(ctx?.callParams()),
        )
    }

    private fun buildParameters(callParamsContext: RustParser.CallParamsContext?) = callParamsContext?.expression()?.map {
        CodeProperty(
            TypeValue = it.text,
            TypeType = it.text
        )
    } ?: listOf()
}
