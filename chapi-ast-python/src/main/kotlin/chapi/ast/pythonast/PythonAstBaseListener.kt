package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodeProperty
import org.antlr.v4.runtime.tree.ParseTree

open class PythonAstBaseListener : PythonParserBaseListener() {
    fun buildParameters(listCtx: PythonParser.TypedargslistContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (defParameters in listCtx!!.def_parameters()) {
            for (defParaCtx in defParameters.def_parameter()) {
                val parameter = CodeProperty(
                    TypeType = "",
                    TypeValue = defParaCtx.text
                )

                if (defParaCtx.ASSIGN() != null) {
                    parameter.DefaultValue = defParaCtx.test().text
                    parameter.TypeValue = defParaCtx.named_parameter().text
                }

                parameters += parameter
            }
        }

        return parameters
    }

    fun getNodeIndex(node: ParseTree?): Int {
        if (node == null || node.parent == null) {
            return -1
        }

        val parent = node.parent
        for (i in 0 until parent.childCount) {
            if (parent.getChild(i) == node) {
                return i
            }
        }
        return 0
    }

    fun buildAnnotationsByIndex(ctx: ParseTree, ctxIndex: Int): Array<CodeAnnotation> {
        var nodes: Array<PythonParser.DecoratorContext> = arrayOf()
        for (i in 0 until ctxIndex) {
            nodes += ctx.parent.getChild(i) as PythonParser.DecoratorContext
        }

        var annotations: Array<CodeAnnotation> = arrayOf()
        for (node in nodes) {
            annotations += this.buildAnnotation(node)
        }

        return annotations
    }

    fun buildAnnotation(node: PythonParser.DecoratorContext): CodeAnnotation {
        val codeAnnotation = CodeAnnotation(
            Name = node.dotted_name().text
        )

        if (node.arglist() != null) {
            codeAnnotation.KeyValues = this.buildArgList(node.arglist())
        }

        return codeAnnotation
    }

    private fun buildArgList(arglistCtx: PythonParser.ArglistContext?): Array<AnnotationKeyValue> {
        var arguments: Array<AnnotationKeyValue> = arrayOf()
        for (argCtx in arglistCtx!!.argument()) {
            val key = argCtx.test(0).text
            var value = ""
            if (argCtx.test().size > 1) {
                value = argCtx.test(1).text
            }

            val annotation = AnnotationKeyValue(
                Key = key,
                Value = value
            )
            arguments += annotation
        }

        return arguments
    }

    fun buildExprStmt(exprCtx: PythonParser.Expr_stmtContext) {
        val starExpr = exprCtx.getChild(0) as PythonParser.Testlist_star_exprContext
        println(starExpr.text)

        if (exprCtx.assign_part() != null) {
            println(exprCtx.assign_part().text)
        }
    }
}
