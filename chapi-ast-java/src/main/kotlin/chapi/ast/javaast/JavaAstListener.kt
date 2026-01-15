package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodePosition
import org.antlr.v4.runtime.ParserRuleContext

open class JavaAstListener : JavaParserBaseListener() {
    fun annotationName(ctx: JavaParser.AnnotationContext): String {
        return ctx.qualifiedName()?.text ?: ""
    }

    fun buildAnnotation(ctx: JavaParser.AnnotationContext): CodeAnnotation {
        val codeAnnotation = CodeAnnotation(
            Name = annotationName(ctx)
        )

        // Handle annotation field values (new grammar structure)
        val fieldValues = ctx.annotationFieldValues()
        if (fieldValues != null) {
            codeAnnotation.KeyValues = fieldValues.annotationFieldValue()
                ?.mapNotNull {
                    val identifier = it.identifier()
                    val value = it.annotationValue()
                    if (identifier != null && value != null) {
                        AnnotationKeyValue(identifier.text, value.text)
                    } else if (value != null) {
                        // Unnamed annotation value
                        AnnotationKeyValue(value.text, value.text)
                    } else {
                        null
                    }
                } ?: listOf()
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
        return targetTypeStr?.contains("(") == true && targetTypeStr.contains(")") && targetTypeStr.contains(".")
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
