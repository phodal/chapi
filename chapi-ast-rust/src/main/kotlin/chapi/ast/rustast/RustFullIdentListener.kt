package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.ast.antlr.RustParser.LetStatementContext
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
        val varName = getVarName(ctx)
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

    private fun getVarName(ctx: LetStatementContext?) = ctx?.patternNoTopAlt()?.patternWithoutRange()?.identifierPattern()?.identifier()?.text ?: ""

    override fun enterCallExpression(ctx: RustParser.CallExpressionContext?) {
        val functionName = ctx?.expression()?.text
        val split = functionName?.split("::")
        val lastType = split?.dropLast(1)?.joinToString("::")
        val nodeName = if (lastType.isNullOrEmpty()) {
            split?.firstOrNull() ?: ""
        } else {
            lastType
        }

        val pureFuncName = split?.last() ?: ""

        val lookedType = lookupByType(nodeName)

        functionInstance.FunctionCalls += CodeCall(
            Package = lookupPackage(pureFuncName, lookedType),
            NodeName = lookedType,
            FunctionName = pureFuncName,
            OriginNodeName = nodeName,
            Parameters = buildParameters(ctx?.callParams()),
            Position = buildPosition(ctx ?: return)
        )
    }

    /**
     * first try lookup [individualFunctions], then lookup [structMap]
     */
    private fun lookupPackage(typeText: String, nodeName: String): String {
        individualFunctions.firstOrNull { it.Name == typeText }?.let {
            return it.Package
        }

        if (currentModule == "test") {
            // crate::infrastructure::git::git_tag_parser::GitTagParser
            val split = nodeName.split("::")
            val structName = split.last()
            val structPkg = split.dropLast(1).joinToString("::").removePrefix("crate::")

            structMap.values.firstOrNull { ds ->
                ds.NodeName == structName && ds.Package == structPkg && ds.Functions.any { it.Name == typeText }
            }?.let {
                return it.Package
            }
        }

        return ""
    }

    override fun enterMethodCallExpression(ctx: RustParser.MethodCallExpressionContext?) {
        var instanceVar = ctx?.expression()?.text ?: ""
        if (instanceVar.contains("(") && instanceVar.contains(")")) {
            instanceVar = instanceVar.substringBefore("(")
        }

        val nodeName = localVars.getOrDefault(instanceVar, instanceVar)

        val functionName = lookupFunctionName(ctx?.pathExprSegment())

        // todo: handle method call
        val lookedType = lookupByType(nodeName)

        // check parent
        if (ctx?.parent is LetStatementContext) {
            val varName = getVarName(ctx.parent as LetStatementContext)
            localVars[varName] = lookedType
        }

        functionInstance.FunctionCalls += CodeCall(
            Package = lookupPackage(functionName, nodeName),
            NodeName = lookedType,
            OriginNodeName = instanceVar.ifEmpty { nodeName },
            FunctionName = functionName,
            Parameters = buildParameters(ctx?.callParams()),
            Position = buildPosition(ctx ?: return)
        )
    }

    override fun enterMacroInvocationSemi(ctx: RustParser.MacroInvocationSemiContext?) {
        ctx?.simplePath()?.simplePathSegment()?.map {
            it.identifier()?.text
        }?.joinToString("::")?.let { id ->
            val nodeName = lookupByType(id)

            functionInstance.FunctionCalls += CodeCall(
                Package = lookupPackage(id, nodeName),
                NodeName = nodeName,
                FunctionName = id,
                OriginNodeName = ctx.simplePath()?.text ?: "",
                Parameters = ctx.tokenTree().map {
                    CodeProperty(
                        TypeValue = it.text,
                        TypeType = it.text
                    )
                },
                Position = buildPosition(ctx)
            )
        }
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
