package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

open class CAstBaseListener: CBaseListener() {
    fun buildPosition(ctx: ParserRuleContext?): CodePosition {
        if (ctx == null) {
            return CodePosition()
        }

        return CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition= ctx.start.charPositionInLine,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.stop.charPositionInLine
        )
    }

    fun buildParameters(ctx: CParser.ParameterListContext, parameters: MutableList<CParser.ParameterDeclarationContext>) {
        if (ctx.parameterList() != null) {
            buildParameters(ctx.parameterList(), parameters)
        }
        parameters.add(ctx.parameterDeclaration())
    }
}
