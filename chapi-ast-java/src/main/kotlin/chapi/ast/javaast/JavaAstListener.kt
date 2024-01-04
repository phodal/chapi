package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import org.antlr.v4.runtime.ParserRuleContext

open class JavaAstListener : JavaParserBaseListener() {
    fun annotationName(ctx: JavaParser.AnnotationContext): String {
        val annotationName = ctx.qualifiedName()?.text ?: ""
        return ctx.altAnnotationQualifiedName()?.text ?: annotationName
    }

    fun buildAnnotation(ctx: JavaParser.AnnotationContext): CodeAnnotation {
        val codeAnnotation = CodeAnnotation(
            Name = annotationName(ctx)
        )

        when {
            ctx.elementValuePairs() != null -> {
                codeAnnotation.KeyValues = ctx.elementValuePairs()?.elementValuePair()
                    ?.map {
                        AnnotationKeyValue(it.identifier().text, it.elementValue().text)
                    } ?: listOf()
            }

            ctx.elementValue() != null -> {
                val value = ctx.elementValue().text
                codeAnnotation.KeyValues += AnnotationKeyValue(value, value)
            }
        }

        codeAnnotation.Position = buildPosition(ctx)
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

    fun buildAnnotationForMethod(modifierCtx: JavaParser.ModifierContext): List<CodeAnnotation> {
        return when (val child = modifierCtx.classOrInterfaceModifier()?.getChild(0)) {
            is JavaParser.AnnotationContext -> {
                val annotation = buildAnnotation(child)
                annotation.Position = buildPosition(modifierCtx)
                listOf(annotation)
            }

            else -> {
                listOf()
            }
        }
    }
}
