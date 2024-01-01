package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeProperty


class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
    private val functionInstance
        get() = if (isEnteredIndividualFunction) {
            currentIndividualFunction
        } else {
            currentFunction
        }

    override fun enterLetStatement(ctx: RustParser.LetStatementContext?) {
        val varName = ctx?.patternNoTopAlt()?.patternWithoutRange()?.identifierPattern()?.identifier()?.text ?: ""
        when (ctx?.expression()) {
            is RustParser.CallExpressionContext -> {
                val functionName = ctx.expression().text
                val split = functionName.split("::")
                split.firstOrNull()?.let {
                    localVars[varName] = lookupByType(it)
                }
            }
        }
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
        val instanceVar = ctx?.expression()?.text
        val nodeName = localVars.getOrDefault(instanceVar ?: "", instanceVar)

        val functionName = lookupFunctionName(ctx?.pathExprSegment())

        // todo: handle method call
        functionInstance.FunctionCalls += CodeCall(
            Package = nodeName ?: "",
            NodeName = nodeName ?: "",
            OriginNodeName = instanceVar ?: "",
            FunctionName = functionName,
            Parameters = buildParameters(ctx?.callParams()),
        )
    }

    private fun lookupFunctionName(pathExprSegmentContext: RustParser.PathExprSegmentContext?): String {
        return pathExprSegmentContext?.pathIdentSegment()?.identifier()?.text ?: ""
    }

    private fun buildParameters(callParamsContext: RustParser.CallParamsContext?) = callParamsContext?.expression()?.map {
        CodeProperty(
            TypeValue = it.text,
            TypeType = it.text
        )
    } ?: listOf()
}
