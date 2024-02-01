package chapi.ast.cast

import chapi.ast.antlr.CParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

open class CAstBaseListener : CParserBaseListener() {
    fun buildPosition(ctx: ParserRuleContext?): CodePosition {
        if (ctx == null) {
            return CodePosition()
        }

        return CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.start.charPositionInLine,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.stop.charPositionInLine
        )
    }
}
