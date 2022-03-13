package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CallType
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeProperty

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - call relationship
 * - lambda expression
 * - coroutine
 */
class KotlinFullIdentListener(fileName: String) : KotlinBasicIdentListener(fileName) {

    private val postClassHandler = mutableListOf<(CodeDataStruct) -> Unit>()
    override fun buildFunction(ctx: KotlinParser.FunctionDeclarationContext): CodeFunction =
        super.buildFunction(ctx).apply {
            FunctionCalls = buildFunctionCalls(ctx).toTypedArray()
        }

    private fun buildFunctionCalls(ctx: KotlinParser.FunctionDeclarationContext): List<CodeCall> =
        ctx.functionBody().block().statements().statement().mapNotNull(::buildFunctionCall)

    private fun buildFunctionCall(it: KotlinParser.StatementContext): CodeCall? {
        val result = Regex("(\\w+\\.?)+\\((.*)\\)").find(it.text) ?: return null

        val matchedExpression = result.value
        val functionName = result.groupValues[1]
        val parameters = arrayOf(
            parseParameter(result.groupValues[2])
        )

        val matchedStart = result.groups[0]!!.range.first
        val functionStart = result.groups[1]!!.range.first
        val nodeName = matchedExpression.substring(0, maxOf(functionStart - matchedStart - 1, 0))

        return CodeCall(
            Type = if (functionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION,
            NodeName = nodeName,
            FunctionName = functionName,
            Parameters = parameters,
            Position = it.getPosition(),
        ).refineIfExistsCreator()
    }

    private fun parseParameter(text: String) = CodeProperty(
        TypeType = when {
            text.matches(Regex("\".*\"")) -> getTypeFullName("String")
            text.matches(Regex("\\d+")) -> getTypeFullName("Number")
            else -> ""
        },
        TypeValue = text
    )

    // correct the function info
    private fun CodeCall.refineIfExistsCreator(): CodeCall {
        // search the node in declared classes (for individual function)
        classes.find { it.NodeName == FunctionName }?.let {
            return apply {
                Type = CallType.CREATOR
                Package = it.Package
            }
        }
        // search the node in imported classes
        imports.find { it.AsName == NodeName }?.let {
            return apply {
                Package = it.Source.substringBeforeLast('.')
            }
        }
        // search the node in imported functions
        imports.find { it.AsName == FunctionName }?.let {
            return apply {
                val fullNodeName = it.Source.substringBeforeLast('.')
                Package = fullNodeName.substringBeforeLast('.')
                NodeName = fullNodeName.substringAfterLast('.')
                Type = if (FunctionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION
            }
        }
        // search the node in current class
        currentNode.Fields.find { it.TypeKey == NodeName }?.let {
            return apply {
                val fullNodeName = it.TypeType
                Package = fullNodeName.substringBeforeLast('.')
                NodeName = fullNodeName.substringAfterLast('.')
            }
        }

        // not found yet, register a post handler to check the node in the next class
        postClassHandler.add {
            if (it.NodeName == FunctionName) {
                Type = CallType.CREATOR
                Package = it.Package
            }
        }
        return this
    }

    override fun buildClass(ctx: KotlinParser.ClassDeclarationContext): CodeDataStruct {
        return super.buildClass(ctx).also {
            postClassHandler.forEach { callback -> callback(it) }
        }
    }
}
