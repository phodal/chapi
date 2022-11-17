package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeProperty
import org.antlr.v4.runtime.ParserRuleContext

open class TypeScriptAstListener : TypeScriptParserBaseListener() {
    fun buildParameters(
        formalParameterListContext: TypeScriptParser.FormalParameterListContext?
    ): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (argCtx in formalParameterListContext!!.formalParameterArg()) {
            val typeType = this.buildTypeAnnotation(argCtx.typeAnnotation())
            var typeValue = argCtx.text
            if (argCtx.identifierName() != null) {
                typeValue = argCtx.identifierName().text
            }

            val parameter = CodeProperty(TypeValue = typeValue, TypeType = typeType)

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

    fun buildTypeAnnotation(typeAnnotation: TypeScriptParser.TypeAnnotationContext?): String {
        if (typeAnnotation == null) {
            return ""
        }

        val typeContext = typeAnnotation.typeRef() ?: return ""
        var type = typeContext.text

        if (typeContext.children == null) {
            return type
        }

//        when (val primaryCtx = typeContext.getChild(0)) {
//            is TypeScriptParser.PrimaryContext -> {
//                type = handleTypeAnnotationPrimary(primaryCtx, type)
//            }
//        }

        return processRef(typeContext)
    }

    fun processRef(type: TypeScriptParser.TypeRefContext): String {
        return type.text
    }

//    private fun handleTypeAnnotationPrimary(typeContext: TypeScriptParser.PrimaryContext, typ: String?): String? {
//        var typeStr = typ
//        when (val childPrimaryCtx = typeContext.getChild(0)) {
//            is TypeScriptParser.ParenthesizedPrimTypeContext -> {
//                typeStr = childPrimaryCtx.typeRef().text
//            }
//        }
//        return typeStr
//    }

    fun buildMethodParameters(paramListCtx: TypeScriptParser.ParameterListContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()

        val type = paramListCtx!!.getChild(0)
        when (type.parent) {
            is TypeScriptParser.OnlyRestParameterContext -> {
                val restCtx = paramListCtx as TypeScriptParser.OnlyRestParameterContext
                val restParameters = this.buildRestParameter(restCtx.restParameter())
                parameters += restParameters
            }

            is TypeScriptParser.NormalParameterContext -> {
                val normalParam = paramListCtx as TypeScriptParser.NormalParameterContext

                normalParam.parameter().forEach {
                    if (it.requiredParameter() != null) {
                        parameters += this.buildRequireParameter(it.requiredParameter())
                    }
                    if (it.optionalParameter() != null) {
                        parameters += this.buildOptionalParameter(it.optionalParameter())
                    }
                }

                if (normalParam.restParameter() != null) {
                    val restParameters = this.buildRestParameter(paramListCtx.restParameter())
                    parameters += restParameters
                }
            }
        }

        return parameters
    }

    private fun buildRestParameter(restCtx: TypeScriptParser.RestParameterContext?): CodeProperty {
        var paramType = ""
        if (restCtx!!.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(restCtx.typeAnnotation())!!
        }

        return CodeProperty(
            TypeValue = restCtx.text,
            TypeType = paramType
        )
    }

    private fun buildOptionalParameter(paramCtx: TypeScriptParser.OptionalParameterContext): CodeProperty {
        var paramType = ""
        if (paramCtx.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(paramCtx.typeAnnotation())!!
        }

        return CodeProperty(
            TypeValue = paramCtx.identifierOrPattern().text,
            TypeType = paramType
        )
    }

    private fun buildRequireParameter(paramCtx: TypeScriptParser.RequiredParameterContext): CodeProperty {
        var paramType = ""
        if (paramCtx.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(paramCtx.typeAnnotation())!!
        }

        return CodeProperty(
            TypeValue = paramCtx.identifierOrPattern().text,
            TypeType = paramType
        )
    }

    fun buildAnnotation(decorator: TypeScriptParser.DecoratorContext): CodeAnnotation {
        val annotation = CodeAnnotation()
        val memberExpression = decorator.decoratorMemberExpression()
        val callExpression = decorator.decoratorCallExpression()

        if (memberExpression?.Identifier() != null) {
            annotation.Name = memberExpression.Identifier().text
        }
        if (callExpression != null) {
            val member = callExpression.decoratorMemberExpression()
            annotation.Name = member.Identifier().text
        }
        return annotation
    }
}
