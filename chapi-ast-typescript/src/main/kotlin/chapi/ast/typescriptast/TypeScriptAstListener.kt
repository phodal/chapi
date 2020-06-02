package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeProperty
import org.antlr.v4.runtime.ParserRuleContext

open class TypeScriptAstListener() : TypeScriptParserBaseListener() {
    fun buildParameters(
        formalParameterListContext: TypeScriptParser.FormalParameterListContext?
    ): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (argCtx in formalParameterListContext!!.formalParameterArg()) {
            val typeType = this.buildTypeAnnotation(argCtx.typeAnnotation())
            val parameter = CodeProperty(
                TypeValue = argCtx.identifierOrKeyWord().text,
                TypeType = typeType!!
            )

            if (argCtx.accessibilityModifier() != null) {
                parameter.Modifiers += argCtx.accessibilityModifier().text
            }

            parameters += parameter
        }
        return parameters
    }

    fun buildPosition(content: Any?): CodePosition {
        val ctx = content as ParserRuleContext
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    fun buildTypeAnnotation(typeAnnotation: TypeScriptParser.TypeAnnotationContext?): String? {
        val typeContext = typeAnnotation!!.type_()

        var type = typeContext.text
        when (typeContext.getChild(0)::class.java.simpleName) {
            "PrimaryContext" -> {
                type = handleTypeAnnotationPrimary(typeContext, type)
            }
        }
        return type
    }

    private fun handleTypeAnnotationPrimary(typeContext: TypeScriptParser.Type_Context, typ: String?): String? {
        var typeStr = typ
        val primaryContext = typeContext.getChild(0) as TypeScriptParser.PrimaryContext
        val childPrimaryCtx = primaryContext.getChild(0)

        when (childPrimaryCtx::class.java.simpleName) {
            "ParenthesizedPrimTypeContext" -> {
                val parentCtx = childPrimaryCtx as TypeScriptParser.ParenthesizedPrimTypeContext
                typeStr = parentCtx.type_().text
            }
        }
        return typeStr
    }

    fun buildMethodParameters(paramListCtx: TypeScriptParser.ParameterListContext?): Array<CodeProperty> {
        val childNode = paramListCtx!!.getChild(0)
        var parameters: Array<CodeProperty> = arrayOf()

        val childType = childNode::class.java.simpleName
        when (childType) {
            "RequiredParameterListContext" -> {
                val listCtx = childNode as TypeScriptParser.RequiredParameterListContext
                val requireParams = this.buildRequireParameterList(listCtx)
                parameters += requireParams

                if (paramListCtx.restParameter() != null) {
                    val restParameters = this.buildRestParameters(paramListCtx.restParameter())
                    parameters += restParameters
                }

            }
            "PredefinedTypeContext" -> {
                val predefinedTypeContext = childNode as TypeScriptParser.PredefinedTypeContext
                val predefinedType = CodeProperty(
                    TypeValue = "any",
                    TypeType = predefinedTypeContext.text
                )
                parameters += predefinedType
            }
            else -> {
                println("enterFunctionDeclaration -> TypeScriptAstListener::buildMethodParameters")
            }
        }

        return parameters
    }

    private fun buildRestParameters(restCtx: TypeScriptParser.RestParameterContext?): CodeProperty {
        val parameterContext = restCtx!!.getChild(0) as TypeScriptParser.RequiredParameterContext
        return this.buildRequireParameter(parameterContext)
    }

    private fun buildRequireParameterList(listCtx: TypeScriptParser.RequiredParameterListContext): Array<CodeProperty> {
        var requiredCodeParameters: Array<CodeProperty> = arrayOf()
        for (requiredParam in listCtx.requiredParameter()) {
            val param = this.buildRequireParameter(requiredParam)
            requiredCodeParameters += param
        }

        return requiredCodeParameters
    }

    private fun buildRequireParameter(paramCtx: TypeScriptParser.RequiredParameterContext?): CodeProperty {
        var paramType: String = ""
        if (paramCtx!!.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(paramCtx.typeAnnotation())!!
        }

        val parameter = CodeProperty(
            TypeValue = paramCtx.identifierOrPattern().text,
            TypeType = paramType
        )

        return parameter
    }
}
