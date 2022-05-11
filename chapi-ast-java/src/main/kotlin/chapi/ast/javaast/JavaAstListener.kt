package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import org.antlr.v4.runtime.ParserRuleContext

open class JavaAstListener : JavaParserBaseListener() {
    fun annotationName(ctx: JavaParser.AnnotationContext): String {
        var annotationName = ""
        if (ctx.qualifiedName() != null) {
            annotationName = ctx.qualifiedName().text
        }

        if (ctx.altAnnotationQualifiedName() != null) {
            annotationName = ctx.altAnnotationQualifiedName().text
        }

        return annotationName
    }

    fun buildAnnotation(ctx: JavaParser.AnnotationContext): CodeAnnotation {
        val annotationName = annotationName(ctx)
        val codeAnnotation = CodeAnnotation(
            Name = annotationName
        )
        if (ctx.elementValuePairs() != null) {
            for (pairContext in ctx.elementValuePairs().elementValuePair()) {
                val key = pairContext.identifier().text
                val value = pairContext.elementValue().text

                codeAnnotation.KeyValues += AnnotationKeyValue(key, value)
            }
        } else if (ctx.elementValue() != null) {
            val value = ctx.elementValue().text
            codeAnnotation.KeyValues += AnnotationKeyValue(value, value)
        }

        return codeAnnotation
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    fun isChainCall(targetTypeStr: String?): Boolean {
        return targetTypeStr!!.contains("(") && targetTypeStr.contains(")") && targetTypeStr.contains(".")
    }

    fun buildAnnotationForMethod(modifierCtx: JavaParser.ModifierContext): Array<CodeAnnotation> {
        var annotations: Array<CodeAnnotation> = arrayOf()
        if (modifierCtx.classOrInterfaceModifier() != null) {
            val childCtx = modifierCtx.classOrInterfaceModifier().getChild(0)
            if (childCtx::class.simpleName == "AnnotationContext") {
                val annotation = this.buildAnnotation(childCtx as JavaParser.AnnotationContext)
                annotations += annotation
            }
        }

        return annotations
    }
}
