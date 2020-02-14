package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport

class PythonFullIdentListener(var fileName: String) : PythonAstBaseListener() {
    private var currentFunction: CodeFunction = CodeFunction()
    private var hasEnterClass = false
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var currentNode: CodeDataStruct = CodeDataStruct()
    private var defaultNode: CodeDataStruct = CodeDataStruct(NodeName = "default")

    override fun enterImport_stmt(ctx: PythonParser.Import_stmtContext?) {
        val dotNames = ctx!!.dotted_as_names().dotted_as_name()
        val firstNameCtx = dotNames[0]

        var codeImport = CodeImport(
            Source = firstNameCtx.dotted_name().text
        )
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
        if (ctx!!.dotted_name() != null) {
            if (ctx.import_dot_ellipsis().size > 0) {
                sourceName = ctx.getChild(1).text
            }
            sourceName += ctx.dotted_name().text
        } else {
            sourceName = ctx.getChild(1).text
        }

        val codeImport = CodeImport(
            Source = sourceName
        )

        for (importAsNameCtx in ctx.import_as_names().import_as_name()) {
            val usageName = importAsNameCtx.name()[0].text
            codeImport.UsageName += usageName

            if (importAsNameCtx.AS() != null) {
                codeImport.AsName = importAsNameCtx.name()[1].text
            }
        }

        codeContainer.Imports += codeImport
    }

    override fun enterClassdef(ctx: PythonParser.ClassdefContext?) {
        hasEnterClass = true
        currentNode = CodeDataStruct(
            FilePath = codeContainer.FullName,
            NodeName = ctx!!.name().text
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
        hasEnterClass = false
        codeContainer.DataStructures += currentNode
        currentNode = CodeDataStruct()
    }

    override fun enterFuncdef(ctx: PythonParser.FuncdefContext?) {
        val funcName = ctx!!.name().text
        currentFunction = CodeFunction(
            Name = funcName
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
    }

    override fun exitFuncdef(ctx: PythonParser.FuncdefContext?) {
        if (currentNode.NodeName == "") {
            defaultNode.Functions += currentFunction
        } else {
            currentNode.Functions += currentFunction
        }
        currentFunction = CodeFunction()
    }

    override fun enterSimple_stmt(ctx: PythonParser.Simple_stmtContext?) {
        for (smallStmtCtx in ctx!!.small_stmt()) {
            val stmtType = smallStmtCtx::class.java.simpleName
            println(stmtType)
            when (stmtType) {
                "Expr_stmtContext" -> {
                    this.buildExprStmt(smallStmtCtx as PythonParser.Expr_stmtContext)
                }
                else -> {
                    println("enterSimple_stmt ->$stmtType")
                }
            }
        }
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultNode.Functions.isNotEmpty()) {
            this.codeContainer.DataStructures += defaultNode
        }
        return this.codeContainer
    }
}
