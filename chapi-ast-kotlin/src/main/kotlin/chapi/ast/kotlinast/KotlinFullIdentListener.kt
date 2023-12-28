package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CallType
import chapi.domain.core.CodeCall
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField
import chapi.domain.core.CodeImport
import chapi.domain.core.CodeProperty

/**
 * listen to full identifier with complex type and sceneries, such as:
 * - call relationship
 * - lambda expression
 * - coroutine
 */
open class KotlinFullIdentListener(fileName: String) : KotlinBasicIdentListener(fileName) {

    private val postClassHandler = mutableListOf<(CodeDataStruct) -> Unit>()
    private var VARIABLE_POOL: MutableMap<String, String> = mutableMapOf()

    override fun enterPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext?) {
        if (ctx!!.variableDeclaration() != null) {
            val varDecl = ctx.variableDeclaration()
            val key = varDecl.simpleIdentifier().text
            if (ctx.ASSIGNMENT() != null) {
                if (ctx.propertyDelegate() != null) {
                    VARIABLE_POOL[key] = ctx.propertyDelegate().text
                }
                if (ctx.expression() != null) {
                    VARIABLE_POOL[key] = ctx.expression().text
                }
            }

            var varType = ""
            if (varDecl.type() != null) {
                varType = varDecl.type().text
            }

            currentFunction.LocalVariables += CodeProperty(TypeValue = key, TypeType = varType)
        }
    }

    private fun typeFromText(text: String): String {
        val typeType = when {
            text.matches(Regex("\".*\"")) -> getTypeFullName("String")
            text.matches(Regex("\\d+")) -> getTypeFullName("Number")
            else -> ""
        }
        return typeType
    }

    private val VAR_IN_STRING = "(\\$[a-zA-Z_]+)".toRegex()
    private fun parseParameter(text: String): CodeProperty {
        var typeType = typeFromText(text)

        var value = text.removePrefix("\"").removeSuffix("\"")
        // if a argument/parameter type is not a string and not a number, it could be a variable
        // so, try to load value from VARIABLE_POOL
        if (typeType == "") {
            if (VARIABLE_POOL[value] != null) {
                value = VARIABLE_POOL[value].toString()

                // value contains with String
                val matches = VAR_IN_STRING.find(value)
                matches?.groups?.forEach {
                    if (it?.value != null) {
                        val variable = it.value
                        val withoutVariable = variable.removePrefix("$")
                        val pool = VARIABLE_POOL[withoutVariable]
                        if (pool != null) {
                            var poolText = pool
                            if (poolText.startsWith("\"") && poolText.endsWith("\"")) {
                                poolText = poolText.removePrefix("\"").removeSuffix("\"")
                            }
                            value = value.replace(variable, poolText)
                        }
                    }
                }

                typeType = typeFromText(value)
            }
        }

        return CodeProperty(TypeType = typeType, TypeValue = value)
    }

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
        if (FunctionName.isNotEmpty() && it.NodeName == FunctionName && FunctionName.first().isUpperCase()) {
            Type = CallType.CREATOR
            Package = it.Package
            NodeName = it.NodeName
            this.FunctionName = "constructor"
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
        var calls: MutableList<CodeCall> = mutableListOf()
        var lastIdentifier = ""
        var lastPackage = ""
        var lastFunctionName = ""
        var lastNodeName = ""

        var lastPostfixChildType = ""
        ctx!!.children.forEach { child ->
            when (child) {
                // sometimes, it just a tests.
                is KotlinParser.PrimaryExpressionContext -> {
                    lastIdentifier = textFromPrimaryExpr(child)
                }

                is KotlinParser.PostfixUnarySuffixContext -> {
                    when (val postfix = child.children[0]) {
                        is KotlinParser.CallSuffixContext -> {
                            val valueArguments = postfix.valueArguments()

                            var parameters: List<CodeProperty> = listOf()
                            if (valueArguments != null) {
                                parameters = valueArguments.valueArgument().map {
                                    // todo: handle for has sub expression
                                    parseParameter(it.text)
                                }
                            }

                            if (lastPostfixChildType == "NavigationSuffixContext") {
                                calls = calls.dropLast(1).toMutableList()
                                lastIdentifier = lastFunctionName
                            }

                            val call = CodeCall(
                                NodeName = lastNodeName,
                                FunctionName = lastIdentifier,
                                Parameters = parameters,
                                Position = ctx.getPosition()
                            ).refineIfExistsCreator()
                            calls += call

                            lastPostfixChildType = "CallSuffixContext"
                        }

                        is KotlinParser.NavigationSuffixContext -> {
                            if (postfix.simpleIdentifier() != null) {
                                val navigationName = postfix.simpleIdentifier().text
                                var parameters: List<CodeProperty> = listOf()
                                if (postfix.parenthesizedExpression() != null) {
                                    val param = postfix.parenthesizedExpression().expression().text
                                    parameters += parseParameter(param)
                                }

                                lastFunctionName = navigationName

                                val call = CodeCall(
                                    NodeName = lastIdentifier,
                                    FunctionName = navigationName,
                                    Parameters = parameters,
                                    Package = lastPackage,
                                    Position = ctx.getPosition()
                                ).refineIfExistsCreator()
                                lastPackage = call.Package
                                lastNodeName = call.NodeName
                                calls += call
                            }

                            lastPostfixChildType = "NavigationSuffixContext"
                        }

                        else -> {
                            lastPostfixChildType = child.children[0].javaClass.simpleName
                        }
                    }
                }
            }
        }

        if (calls.isNotEmpty()) {
            if (isEnteredIndividualFunction) {
                currentIndividualFunction.FunctionCalls += calls
            } else {
                currentFunction.FunctionCalls += calls
            }
        }
    }

    private fun textFromPrimaryExpr(
        child: KotlinParser.PrimaryExpressionContext,
    ): String {
        return when {
            child.simpleIdentifier() != null -> {
                child.simpleIdentifier().text
            }

            child.stringLiteral() != null -> {
                child.stringLiteral().text
            }

            else -> {
                child.text
            }
        }
    }

    override fun buildClass(ctx: KotlinParser.ClassDeclarationContext): CodeDataStruct {
        return super.buildClass(ctx).also {
            postClassHandler.forEach { callback -> callback(it) }
        }
    }
}
