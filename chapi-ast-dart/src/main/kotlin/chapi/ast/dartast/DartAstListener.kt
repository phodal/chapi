package chapi.ast.dartast

import chapi.ast.antlr.Dart2Parser
import chapi.ast.antlr.Dart2ParserBaseListener
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import org.antlr.v4.runtime.ParserRuleContext

/**
 * Base class for Dart AST listeners providing common utility methods.
 */
open class DartAstListener : Dart2ParserBaseListener() {

    /**
     * Build a CodePosition from a parser rule context
     */
    protected fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop?.line ?: ctx.start.line
        position.StopLinePosition = ctx.stop?.charPositionInLine ?: ctx.start.charPositionInLine
        return position
    }

    /**
     * Build a CodeAnnotation from a metadatum context
     */
    protected fun buildAnnotation(ctx: Dart2Parser.MetadatumContext): CodeAnnotation {
        val annotation = CodeAnnotation()
        
        // Get annotation name from identifier or qualified name
        val identifier = ctx.identifier()
        val qualifiedName = ctx.qualifiedName()
        
        annotation.Name = when {
            identifier != null -> identifier.text
            qualifiedName != null -> qualifiedName.text
            else -> ""
        }
        
        annotation.Position = buildPosition(ctx)
        return annotation
    }

    /**
     * Build a list of CodeAnnotations from a metadata context
     */
    protected fun buildAnnotations(ctx: Dart2Parser.MetadataContext?): List<CodeAnnotation> {
        if (ctx == null) return emptyList()
        return ctx.metadatum().map { buildAnnotation(it) }
    }

    /**
     * Extract type name from a type context
     */
    protected fun getTypeName(ctx: Dart2Parser.TypeContext?): String {
        if (ctx == null) return ""
        return ctx.text
    }

    /**
     * Extract type name from typeNotVoid context
     */
    protected fun getTypeNotVoidName(ctx: Dart2Parser.TypeNotVoidContext?): String {
        if (ctx == null) return ""
        return ctx.text
    }

    /**
     * Check if context has abstract modifier
     */
    protected fun isAbstract(ctx: ParserRuleContext): Boolean {
        return ctx.children?.any { 
            it.text == "abstract" 
        } ?: false
    }

    /**
     * Check if context has static modifier
     */
    protected fun isStatic(ctx: ParserRuleContext): Boolean {
        return ctx.children?.any { 
            it.text == "static" 
        } ?: false
    }

    /**
     * Extract identifier text safely
     */
    protected fun getIdentifierText(ctx: Dart2Parser.IdentifierContext?): String {
        return ctx?.text ?: ""
    }

    /**
     * Extract type identifier text safely
     */
    protected fun getTypeIdentifierText(ctx: Dart2Parser.TypeIdentifierContext?): String {
        return ctx?.text ?: ""
    }
}
