package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

open class PythonAstBaseListener : PythonParserBaseListener() {
    var currentFunction: CodeFunction = CodeFunction()
    var localVars = mutableMapOf<String, String>()

    fun buildParameters(listCtx: PythonParser.TypedargslistContext?): List<CodeProperty> {
        val parameters: MutableList<CodeProperty> = mutableListOf()
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

    fun buildAnnotationsByIndex(ctx: ParseTree, ctxIndex: Int): List<CodeAnnotation> {
        val nodes: MutableList<PythonParser.DecoratorContext> = mutableListOf()
        for (i in 0 until ctxIndex) {
            nodes += ctx.parent.getChild(i) as PythonParser.DecoratorContext
        }

        val annotations: MutableList<CodeAnnotation> = mutableListOf()
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

        codeAnnotation.Position = buildPosition(node)
        return codeAnnotation
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        return CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.start.charPositionInLine,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.stop.charPositionInLine
        )
    }

    private fun buildArgList(arglistCtx: PythonParser.ArglistContext?): List<AnnotationKeyValue> {
        var arguments: List<AnnotationKeyValue> = listOf()
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
        var leftPart = ""
        val starExpr = exprCtx.getChild(0) as PythonParser.Testlist_star_exprContext
        val childType = starExpr.getChild(0)::class.java.simpleName
        if (childType == "TestlistContext") {
            for (testContext in starExpr.testlist().test()) {
                buildTestContext(testContext)
                leftPart = testContext.text
            }
        }

        val assignPartCtx = exprCtx.assign_part()
        if (assignPartCtx != null) {
            if (assignPartCtx.ASSIGN() != null) {
                val rightObj = buildAssignPart(assignPartCtx)
                localVars[leftPart] = rightObj
            }
        }
    }

    private fun buildAssignPart(assignPartCtx: PythonParser.Assign_partContext): String {
        var returnAtom = ""
        for (starExprCtx in assignPartCtx.testlist_star_expr()) {
            when (val child = starExprCtx.getChild(0)) {
                is PythonParser.TestlistContext -> {
                    child.test().forEach { testContext ->
                        returnAtom = this.buildTestContext(testContext)
                    }
                }
            }
        }

        return returnAtom
    }

    private fun buildTestContext(testContext: PythonParser.TestContext): String {
        var returnType = ""
        when (val childCtx = testContext.getChild(0).getChild(0).getChild(0)) {
            is PythonParser.ExprContext -> {
                val exprChild = childCtx.getChild(0)
                when (exprChild) {
                    is PythonParser.AtomContext -> {
                        returnType = buildAtomExpr(childCtx)
                    }
                }
            }
            else -> {
//                println("buildTestContext -> $childCtx")
            }
        }

        return returnType
    }

    private fun buildAtomExpr(exprCtx: PythonParser.ExprContext): String {
        var funcCalls: List<CodeCall> = listOf()
        val atomName = exprCtx.atom().text
        for (trailerContext in exprCtx.trailer()) {
            var caller = ""
            var nodeName = ""
            if (trailerContext.DOT() != null) {
                if (trailerContext.name() != null) {
                    caller = trailerContext.name().text
                }
                nodeName = atomName
            } else {
                caller = atomName
            }

            funcCalls += CodeCall(
                NodeName = nodeName,
                FunctionName = caller,
                Position = buildPosition(trailerContext)
            )
        }

        currentFunction.FunctionCalls = funcCalls

        return atomName
    }
}
