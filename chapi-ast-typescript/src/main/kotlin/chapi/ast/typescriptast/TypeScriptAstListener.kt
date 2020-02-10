package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import domain.core.CodePosition
import org.antlr.v4.runtime.ParserRuleContext

open class TypeScriptAstListener() : TypeScriptParserBaseListener() {
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
