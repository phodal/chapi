package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction
import domain.core.CodeImport

class PythonFullIdentListener(var fileName: String) : PythonAstBaseListener() {
    private var currentFunction: CodeFunction = CodeFunction()
    private var hasEnterClass = false
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    private var currentNode: CodeDataStruct = CodeDataStruct()
    private var defaultNode: CodeDataStruct = CodeDataStruct(
        NodeName = "default"
    )

    override fun enterImport_stmt(ctx: PythonParser.Import_stmtContext?) {
        val dotNames = ctx!!.dotted_as_names().dotted_as_name()
        val firstNameCtx = dotNames[0]

        var codeImport = CodeImport(
            Source = firstNameCtx.dotted_name().text
        )
        if (firstNameCtx.name() != null) {
            codeImport.UsageName += firstNameCtx.name().text
        }

        for (i in 1 until dotNames.size - 1) {
            codeImport.UsageName += dotNames[i].text
        }

        codeFile.Imports += codeImport
    }

    override fun enterFrom_stmt(ctx: PythonParser.From_stmtContext?) {
        super.enterFrom_stmt(ctx)
        println("super.enterFrom_stmt(ctx)")
    }

    override fun enterClassdef(ctx: PythonParser.ClassdefContext?) {
        hasEnterClass = true
        currentNode = CodeDataStruct(
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
        codeFile.DataStructures += currentNode
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

    fun getNodeInfo(): CodeFile {
        if (defaultNode.Functions.isNotEmpty()) {
            this.codeFile.DataStructures += defaultNode
        }
        return this.codeFile
    }
}
