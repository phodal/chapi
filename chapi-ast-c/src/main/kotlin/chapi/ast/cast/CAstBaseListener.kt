package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

open class CAstBaseListener: CBaseListener() {
    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition= ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    fun buildParameters(ctx: CParser.ParameterListContext, parameters: MutableList<CParser.ParameterDeclarationContext>) {
        if (ctx.parameterList() != null) {
            buildParameters(ctx.parameterList(), parameters)
        }
        parameters.add(ctx.parameterDeclaration())
    }
}
