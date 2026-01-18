package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeProperty
import chapi.domain.core.CodeTypeRef
import org.antlr.v4.runtime.ParserRuleContext

open class TypeScriptAstListener : TypeScriptParserBaseListener() {
    fun buildParameters(
        formalParameterListContext: TypeScriptParser.FormalParameterListContext?
    ): List<CodeProperty> {
        var parameters: List<CodeProperty> = listOf()
        for (argCtx in formalParameterListContext!!.formalParameterArg()) {
            val typeType = this.buildTypeAnnotation(argCtx.typeAnnotation())
            val typeRef = TypeScriptTypeRefBuilder.build(argCtx.typeAnnotation())
            var typeValue = argCtx.text
            if (argCtx.identifierName() != null) {
                typeValue = argCtx.identifierName().text
            }

            val parameter = CodeProperty(TypeValue = typeValue, TypeType = typeType, TypeRef = typeRef)

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

        val typeContext = typeAnnotation.type_() ?: return ""

        if (typeContext.children == null) {
            return typeContext.text
        }

        return typeContext.text
    }

    fun processRef(typeRef: TypeScriptParser.TypeRefContext?): String? {
        if (typeRef == null) return null
        return typeRef.text
    }

    fun buildMethodParameters(paramListCtx: TypeScriptParser.ParameterListContext?): List<CodeProperty> {
        if (paramListCtx == null) return listOf()
        
        var parameters: List<CodeProperty> = listOf()

        // Handle rest-only parameter case
        if (paramListCtx.restParameter() != null && paramListCtx.parameter().isEmpty()) {
            val restParameters = this.buildRestParameter(paramListCtx.restParameter())
            parameters += restParameters
            return parameters
        }

        // Handle normal parameters
        paramListCtx.parameter().forEach {
            if (it.requiredParameter() != null) {
                parameters += this.buildRequireParameter(it.requiredParameter())
            }
            if (it.optionalParameter() != null) {
                parameters += this.buildOptionalParameter(it.optionalParameter())
            }
        }

        // Handle trailing rest parameter
        if (paramListCtx.restParameter() != null) {
            val restParameters = this.buildRestParameter(paramListCtx.restParameter())
            parameters += restParameters
        }

        return parameters
    }

    private fun buildRestParameter(restCtx: TypeScriptParser.RestParameterContext?): CodeProperty {
        var paramType = ""
        var typeRef: CodeTypeRef? = null
        if (restCtx!!.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(restCtx.typeAnnotation())
            typeRef = TypeScriptTypeRefBuilder.build(restCtx.typeAnnotation())
        }

        return CodeProperty(
            TypeValue = restCtx.text,
            TypeType = paramType,
            TypeRef = typeRef
        )
    }

    private fun buildOptionalParameter(paramCtx: TypeScriptParser.OptionalParameterContext): CodeProperty {
        var paramType = ""
        var typeRef: CodeTypeRef? = null
        if (paramCtx.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(paramCtx.typeAnnotation())
            typeRef = TypeScriptTypeRefBuilder.build(paramCtx.typeAnnotation())
        }

        return CodeProperty(
            TypeValue = paramCtx.identifierOrPattern().text,
            TypeType = paramType,
            TypeRef = typeRef
        )
    }

    private fun buildRequireParameter(paramCtx: TypeScriptParser.RequiredParameterContext): CodeProperty {
        var paramType = ""
        var typeRef: CodeTypeRef? = null
        if (paramCtx.typeAnnotation() != null) {
            paramType = buildTypeAnnotation(paramCtx.typeAnnotation())
            typeRef = TypeScriptTypeRefBuilder.build(paramCtx.typeAnnotation())
        }

        return CodeProperty(
            TypeValue = paramCtx.identifierOrPattern().text,
            TypeType = paramType,
            TypeRef = typeRef
        )
    }

    fun buildAnnotation(decorator: TypeScriptParser.DecoratorContext): CodeAnnotation {
        val annotation = CodeAnnotation()
        annotation.Position = buildPosition(decorator)
        val memberExpression = decorator.decoratorMemberExpression()
        val callExpression = decorator.decoratorCallExpression()

        if (memberExpression?.identifier() != null) {
            annotation.Name = memberExpression.identifier().text
        }
        if (callExpression != null) {
            val member = callExpression.decoratorMemberExpression()
            annotation.Name = member.identifier()?.text ?: ""
        }

        return annotation
    }
}
