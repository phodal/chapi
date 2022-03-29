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

    override fun enterPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext?) {
        // one or more call, maybe call map
        var calls: Array<CodeCall> = arrayOf()
        var params: Array<String> = arrayOf()
        var lastIdentifier = ""
        ctx!!.children.forEach { child ->
            println("Child: ${child.javaClass.simpleName} -> Text: ${child.text}")
            when (child) {
                is KotlinParser.PrimaryExpressionContext -> {
                    if (child.simpleIdentifier() != null) {
                        lastIdentifier = child.simpleIdentifier().text
                    } else if (child.stringLiteral() != null) {
                        params += child.stringLiteral().text
                    } else {
                        println("PrimaryExpressionContext: ${child.javaClass.simpleName}")
                    }

                    // todo: parse from parameters
                }

                is KotlinParser.PostfixUnarySuffixContext -> {
                    println("PostfixUnaryType: ${child.children[0].javaClass.simpleName}")

                    when (val postfix = child.children[0]) {
                        is KotlinParser.CallSuffixContext -> {
                            var parameters: Array<CodeProperty> = arrayOf()
                            if (postfix.typeArguments() != null) {
                                parameters = postfix.typeArguments().typeProjection().map {
                                    CodeProperty(TypeValue = it.text, TypeType = "")
                                }.toTypedArray()
                            }

                            calls += CodeCall(
                                FunctionName = lastIdentifier,
                                Parameters = parameters
                            ).refineIfExistsCreator()
                        }
                        is KotlinParser.NavigationSuffixContext -> {
                            // parameters ?
                            if (postfix.parenthesizedExpression() != null) {
                                println("todo: parse parameters")
                            }
                            if (postfix.simpleIdentifier() != null) {
                                val navigationName = postfix.simpleIdentifier().text
                                var parameters: Array<CodeProperty> = arrayOf()
                                if (postfix.parenthesizedExpression() != null) {
                                    val param = postfix.parenthesizedExpression().expression().text
                                    parameters += CodeProperty(TypeValue = param, TypeType = "")
                                }

                                calls += CodeCall(
                                    NodeName = lastIdentifier,
                                    FunctionName = navigationName,
                                    Parameters = parameters
                                ).refineIfExistsCreator()
                            }
                        } else -> {
                            println("todo: PostfixUnaryType: ${child.children[0].javaClass.simpleName}")
                        }
                    }
                }
            }
        }

//        println("..........................")
//        currentFunction.FunctionCalls += calls
    }

    override fun buildClass(ctx: KotlinParser.ClassDeclarationContext): CodeDataStruct {
        return super.buildClass(ctx).also {
            postClassHandler.forEach { callback -> callback(it) }
        }
    }
}
