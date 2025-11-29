package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.domain.core.*

class PythonFullIdentListener(var fileName: String) : PythonAstBaseListener() {
    private var hasEnterClass = false
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var currentNode: CodeDataStruct = CodeDataStruct()
    private var defaultNode: CodeDataStruct = CodeDataStruct(NodeName = "default")

    override fun enterImport_stmt(ctx: PythonParser.Import_stmtContext?) {
        val dotNames = ctx!!.dotted_as_names().dotted_as_name()
        val firstNameCtx = dotNames[0]

        val codeImport = CodeImport(Source = firstNameCtx.dotted_name().text)
        if (firstNameCtx.name() != null) {
            codeImport.UsageName += firstNameCtx.name().text
        }

        for (i in 1 until dotNames.size) {
            codeImport.UsageName += dotNames[i].text
        }

        codeContainer.Imports += codeImport
    }

    override fun enterFrom_stmt(ctx: PythonParser.From_stmtContext?) {
        var sourceName = ""
        if (ctx?.dotted_name() != null) {
            if (ctx.import_dot_ellipsis().size > 0) {
                sourceName = ctx.getChild(1).text
            }
            sourceName += ctx.dotted_name().text
        } else {
            sourceName = ctx?.getChild(1)?.text ?: ""
        }

        val codeImport = CodeImport(Source = sourceName)

        ctx?.import_as_names()?.import_as_name()?.forEach { nameContext ->
            val usageName = nameContext.name()[0].text
            codeImport.UsageName += usageName

            nameContext.AS()?.let {
                codeImport.AsName = nameContext.name().getOrNull(1)?.text ?: ""
            }
        }

        codeContainer.Imports += codeImport
    }

    override fun enterClassdef(ctx: PythonParser.ClassdefContext?) {
        hasEnterClass = true
        currentNode = CodeDataStruct(
            NodeName = ctx!!.name().text,
            Type = DataStructType.CLASS,
            FilePath = codeContainer.FullName,
            Position = buildPosition(ctx)
        )

        if (ctx.arglist() != null) {
            for (argumentContext in ctx.arglist().argument()) {
                currentNode.MultipleExtend += argumentContext.text
            }
        }

        val ctxIndex = this.getNodeIndex(ctx)
        if (ctxIndex > 0) {
            currentNode.Annotations = this.buildAnnotationsByIndex(ctx, ctxIndex)
        }
    }

    override fun exitClassdef(ctx: PythonParser.ClassdefContext?) {
        // Add class fields to the current node
        currentNode.Fields = classFields.toList()
        classFields.clear()

        hasEnterClass = false
        codeContainer.DataStructures += currentNode
        currentNode = CodeDataStruct()
    }

    override fun enterFuncdef(ctx: PythonParser.FuncdefContext?) {
        val funcName = ctx!!.name().text
        currentFunction = CodeFunction(
            Name = funcName,
            Position = buildPosition(ctx)
        )

        if (ctx.ASYNC() != null) {
            currentFunction.Modifiers += ctx.ASYNC().text
        }

        val ctxIndex = this.getNodeIndex(ctx)
        if (ctxIndex > 0) {
            currentFunction.Annotations = this.buildAnnotationsByIndex(ctx, ctxIndex)
        }

        if (ctx.typedargslist() != null) {
            currentFunction.Parameters = this.buildParameters(ctx.typedargslist())
        }

        // Extract return type annotation if present (ARROW test)
        if (ctx.ARROW() != null && ctx.test() != null) {
            currentFunction.ReturnType = ctx.test().text
        }

        // Mark that we're inside a function and clear local vars
        isInsideFunction = true
        localVars.clear()
    }

    override fun exitFuncdef(ctx: PythonParser.FuncdefContext?) {
        currentFunction.addVarsFromMap(this.localVars)
        if (currentNode.NodeName == "") {
            defaultNode.Functions += currentFunction
        } else {
            currentNode.Functions += currentFunction
        }
        currentFunction = CodeFunction()

        // Mark that we're no longer inside a function and clear local vars
        isInsideFunction = false
        localVars.clear()
    }

    override fun enterSimple_stmt(ctx: PythonParser.Simple_stmtContext?) {
        for (smallStmtCtx in ctx!!.small_stmt()) {
            when(smallStmtCtx) {
                is PythonParser.Expr_stmtContext -> {
                    this.buildExprStmt(smallStmtCtx, hasEnterClass)
                }
                else -> {
//                    println("enterSimple_stmt ->${smallStmtCtx::class.java.simpleName}")
                }
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty() || moduleFields.isNotEmpty()) {
            // Add module-level fields to the default node
            defaultNode.Fields = moduleFields.toList()
            this.codeContainer.DataStructures += defaultNode
        }

        return this.codeContainer
    }
}
