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

    private val postClassHandler = mutableListOf<(CodeDataStruct) -> Unit>()

    private fun KotlinParser.StatementContext.parseFunctionCall(): CodeCall? {
        val result = Regex("(\\w+\\.?)+\\((.*)\\)").find(text) ?: return null
        val matchedExpression = result.value

        val functionName = result.groupValues[1]
        val parameters = arrayOf(
            parseParameter(result.groupValues[2])
        )
        val matchedStart = result.groups[0]!!.range.first
        val functionStart = result.groups[1]!!.range.first
        val nodeName = matchedExpression.substring(0, maxOf(functionStart - matchedStart - 1, 0))

        return CodeCall(
            Type = CallType.FUNCTION,
            NodeName = nodeName,
            FunctionName = functionName,
            Parameters = parameters,
            Position = getPosition(),
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

    private fun CodeCall.refineIfExistsCreator(): CodeCall {
        classes.find { it.NodeName == FunctionName }?.let {
            return apply {
                Package = it.Package
                Type = CallType.CREATOR
                // TODO match/copy the parameter type (property)
            }
        }

        currentNode.Fields.find { it.TypeKey == NodeName }?.let {
            val fullNodeName = it.TypeType
            Package = fullNodeName.substringBeforeLast('.')
            NodeName = fullNodeName.substringAfterLast('.')
            Type = if (FunctionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION
        }

        // import node
        imports.find { it.AsName == NodeName }?.let {
            return apply {
                Package = it.Source.substringBeforeLast('.')
                Type = if (FunctionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION
            }
        }

        // import function
        imports.find { it.AsName == FunctionName }?.let {
            return apply {
                val fullNodeName = it.Source.substringBeforeLast('.')
                Package = fullNodeName.substringBeforeLast('.')
                NodeName = fullNodeName.substringAfterLast('.')
                Type = if (FunctionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION
            }
        }

        // simple function or not scanned yet, register a post handler to find the creator of the class
        postClassHandler.add {
            if (it.NodeName == FunctionName) {
                Package = it.Package
                Type = CallType.CREATOR
                // TODO match/copy the parameter type (property)
            }

            it.Fields.find { it.TypeValue == NodeName }?.let {
                val fullNodeName = it.TypeType
                Package = fullNodeName.substringBeforeLast('.')
                NodeName = fullNodeName.substringAfterLast('.')
                Type = if (FunctionName[0].isUpperCase()) CallType.CREATOR else CallType.FUNCTION
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
