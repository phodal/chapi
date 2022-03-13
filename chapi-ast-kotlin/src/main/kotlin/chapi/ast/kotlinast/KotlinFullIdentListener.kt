package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CallType
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - call relationship
 * |    TODO call chain
 * - lambda expression
 * - coroutine
 */
class KotlinFullIdentListener(fileName: String) : KotlinBasicIdentListener(fileName) {

    override fun buildFunction(ctx: KotlinParser.FunctionDeclarationContext): CodeFunction =
        super.buildFunction(ctx).apply {
            FunctionCalls = buildCalls(ctx).toTypedArray()
        }

    private fun buildCalls(ctx: KotlinParser.FunctionDeclarationContext): MutableList<CodeCall> {
        val calls = mutableListOf<CodeCall>()
        ctx.functionBody().block().statements().statement().forEach {
            it.parseFunctionCall()?.let { call ->
                calls.add(call)
            }
        }
        return calls
    }

    private fun KotlinParser.StatementContext.parseFunctionCall(): CodeCall? {
        val result = Regex("(\\w+)\\((.*)\\)").find(text) ?: return null
        val functionName = result.groupValues[1]
        val parameters = arrayOf(
            CodeProperty(
                TypeType = getTypeFullName("String"),
                TypeValue = result.groupValues[2]
            )
        )

        return CodeCall(
            Package = codeContainer.PackageName,
            Type = CallType.FUNCTION,
            NodeName = "",
            FunctionName = functionName,
            Parameters = parameters,
            Position = getPosition(),
        )
    }
}
