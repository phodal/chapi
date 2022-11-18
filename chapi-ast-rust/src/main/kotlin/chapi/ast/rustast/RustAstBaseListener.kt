package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.ast.antlr.RustParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

open class RustAstBaseListener : RustParserBaseListener() {
    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }
}
