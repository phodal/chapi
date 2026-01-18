package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTree

open class PythonAstBaseListener : PythonParserBaseListener() {
    var currentFunction: CodeFunction = CodeFunction()
    var localVars = mutableMapOf<String, String>()
    var isInsideFunction: Boolean = false
    var classFields = mutableListOf<CodeField>()
    var moduleFields = mutableListOf<CodeField>()

    fun buildParameters(listCtx: PythonParser.TypedargslistContext?): List<CodeProperty> {
        val parameters = listCtx?.def_parameters()?.mapNotNull { defParameters ->
            defParameters.def_parameter().mapNotNull { defParaCtx ->
                if (defParaCtx.text == "self") return@mapNotNull null

                val typeAnnotation = defParaCtx.named_parameter()?.test()
                val parameter = CodeProperty(
                    TypeValue = defParaCtx.named_parameter()?.name()?.text ?: "",
                    TypeType = typeAnnotation?.text ?: "",
                    TypeRef = PythonTypeRefBuilder.build(typeAnnotation)
                )

                if (defParaCtx.ASSIGN() != null) {
                    parameter.DefaultValue = defParaCtx?.test()?.text ?: ""
                }

                parameter
            }
        }?.flatten() ?: listOf()

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
        val nodes = mutableListOf<PythonParser.DecoratorContext>()
        for (i in 0 until ctxIndex) {
            nodes += ctx.parent.getChild(i) as PythonParser.DecoratorContext
        }

        val annotations = nodes.map { buildAnnotation(it) }
        return annotations
    }

    private fun buildAnnotation(node: PythonParser.DecoratorContext): CodeAnnotation {
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
        val arguments = arglistCtx!!.argument().map { argCtx ->
            val key = argCtx.test(0).text
            val value = if (argCtx.test().size > 1) argCtx.test(1).text else ""
            AnnotationKeyValue(key, value)
        }

        return arguments
    }

    private fun buildCallParameters(arglistCtx: PythonParser.ArglistContext?): List<CodeProperty> {
        if (arglistCtx == null) {
            return listOf()
        }

        return arglistCtx.argument().map { argCtx ->
            // Check if this is an unpacking operator (* or **)
            val typeValue = when {
                argCtx.STAR() != null -> {
                    // Single star unpacking: *args
                    "*${argCtx.test(0).text}"
                }
                argCtx.POWER() != null -> {
                    // Double star unpacking: **kwargs
                    "**${argCtx.test(0).text}"
                }
                argCtx.ASSIGN() != null -> {
                    // Keyword argument: key=value
                    "${argCtx.test(0).text}=${argCtx.test(1).text}"
                }
                else -> {
                    // Regular positional argument
                    argCtx.test(0).text
                }
            }

            CodeProperty(TypeValue = typeValue, TypeType = "")
        }
    }

    fun buildExprStmt(exprCtx: PythonParser.Expr_stmtContext, hasEnterClass: Boolean) {
        var leftPart = ""
        val starExpr = exprCtx.getChild(0) as? PythonParser.Testlist_star_exprContext
        val childType = starExpr?.getChild(0)
        if (childType is PythonParser.TestlistContext) {
            for (testContext in starExpr.testlist().test()) {
                buildTestContext(testContext)
                leftPart = testContext.text
            }
        }

        val assignPartCtx = exprCtx.assign_part() ?: return
        if (assignPartCtx.ASSIGN() == null) return

        val rightObj = buildAssignPart(assignPartCtx)

        // Determine where to store the variable based on context
        if (isInsideFunction) {
            // Inside a function - add to local variables
            localVars[leftPart] = rightObj
        } else if (hasEnterClass) {
            // Inside a class but not in a function - add to class fields
            classFields.add(CodeField(
                TypeKey = leftPart,
                TypeValue = rightObj
            ))
        } else {
            // At module level - add to module fields
            moduleFields.add(CodeField(
                TypeKey = leftPart,
                TypeValue = rightObj
            ))
        }
    }

    private fun buildAssignPart(assignPartCtx: PythonParser.Assign_partContext): String {
        var returnAtom = ""
        for (starExprCtx in assignPartCtx.testlist_star_expr()) {
            when (val child = starExprCtx?.getChild(0)) {
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
        when (val childCtx = testContext.getChild(0)?.getChild(0)?.getChild(0)) {
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
        val atomName = exprCtx.atom().text

        val codeCalls = exprCtx.trailer().map { trailerContext ->
            val caller = if (trailerContext.DOT() != null) {
                trailerContext?.name()?.text ?: ""
            } else {
                atomName
            }

            val nodeName = if (trailerContext.DOT() != null) atomName else ""

            // Extract parameters from arguments if present
            val parameters = trailerContext.arguments()?.let { argumentsCtx ->
                // Check if it's a function call (OPEN_PAREN) not array access (OPEN_BRACKET)
                if (argumentsCtx.OPEN_PAREN() != null) {
                    buildCallParameters(argumentsCtx.arglist())
                } else {
                    listOf()
                }
            } ?: listOf()

            CodeCall(
                NodeName = nodeName,
                FunctionName = caller,
                Parameters = parameters,
                Position = buildPosition(trailerContext)
            )
        }

        currentFunction.FunctionCalls = codeCalls

        return atomName
    }
}
