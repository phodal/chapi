package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import domain.core.CodePosition
import domain.core.CodeProperty
import org.antlr.v4.runtime.ParserRuleContext

open class TypeScriptAstListener() : TypeScriptParserBaseListener() {
    fun buildParameters(ctx: TypeScriptParser.ConstructorDeclarationContext): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (argCtx in ctx.formalParameterList().formalParameterArg()) {
            val typeType = this.getTypeType(argCtx.typeAnnotation())
            val parameter = CodeProperty(
                TypeValue = argCtx.Identifier().text,
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

    fun getTypeType(typeAnnotation: TypeScriptParser.TypeAnnotationContext?): String? {
        return typeAnnotation!!.type_().text
    }

}
