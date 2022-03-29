package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CallType
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport
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
            val functionCalls = buildFunctionCalls(ctx)
            if (functionCalls != null) {
                FunctionCalls = functionCalls.toTypedArray()
            }
        }

    private fun buildFunctionCalls(ctx: KotlinParser.FunctionDeclarationContext): List<CodeCall>? =
        ctx.functionBody()?.block()?.statements()?.statement()?.mapNotNull(::buildFunctionCall)

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
        for (clazz in classes) {
            this.refineWithClass(clazz) ?: return this
        }

        // search the node in imported classes/functions
        for (import in imports) {
            this.refineWithImport(import) ?: return this
        }

        // search the node in current class
        for (field in currentNode.Fields) {
            this.refineWithField(field) ?: return this
        }

        // not found yet, register a post handler to check the node in the next class
        postClassHandler.add { this.refineWithClass(it) }
        return this
    }

    // return null to quick exit
    private fun CodeCall.refineWithClass(it: CodeDataStruct): CodeCall? {
        if (it.NodeName == FunctionName) {
            Type = CallType.CREATOR
            Package = it.Package
            return null
        }
        return this
    }

    private fun CodeCall.refineWithImport(it: CodeImport): CodeCall? {
        if (it.AsName == NodeName) {
            Package = it.Source.substringBeforeLast('.')
            return null
        }

        if (it.AsName == FunctionName && FunctionName[0].isUpperCase()) {
            Package = it.Source
            NodeName = FunctionName
            return null;
        }

        if (it.AsName == FunctionName) {
            val fullNodeName = it.Source.substringBeforeLast('.')
            Package = fullNodeName.substringBeforeLast('.')
            NodeName = fullNodeName.substringAfterLast('.')
            return null
        }
        return this
    }

    private fun CodeCall.refineWithField(it: CodeField): CodeCall? {
        if (it.TypeKey == NodeName) {
            val fullNodeName = it.TypeType
            Package = fullNodeName.substringBeforeLast('.')
            NodeName = fullNodeName.substringAfterLast('.')
            return null
        }
        return this
    }

    override fun buildClass(ctx: KotlinParser.ClassDeclarationContext): CodeDataStruct {
        return super.buildClass(ctx).also {
            postClassHandler.forEach { callback -> callback(it) }
        }
    }
}
