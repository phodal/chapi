package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeProperty


class RustFullIdentListener(fileName: String) : RustAstBaseListener(fileName) {
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
        val nodeName = split?.dropLast(1)?.joinToString("::") ?: ""
        functionInstance.FunctionCalls += CodeCall(
            Package = split?.dropLast(1)?.joinToString("::") ?: "",
            NodeName = lookupByType(nodeName),
            FunctionName = split?.last() ?: "",
            OriginNodeName = nodeName,
            Parameters = buildParameters(ctx?.callParams()),
            Position = buildPosition(ctx ?: return)
        )
    }

    override fun enterMethodCallExpression(ctx: RustParser.MethodCallExpressionContext?) {
        var instanceVar = ctx?.expression()?.text ?: ""
        if (instanceVar.contains("(") && instanceVar.contains(")")) {
            instanceVar = instanceVar.substringBefore("(")
        }

        val nodeName = localVars.getOrDefault(instanceVar, instanceVar)

        val functionName = lookupFunctionName(ctx?.pathExprSegment())

        // todo: handle method call
        functionInstance.FunctionCalls += CodeCall(
            Package = nodeName,
            NodeName = lookupByType(nodeName),
            OriginNodeName = instanceVar.ifEmpty { nodeName },
            FunctionName = functionName,
            Parameters = buildParameters(ctx?.callParams()),
            Position = buildPosition(ctx ?: return)
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
