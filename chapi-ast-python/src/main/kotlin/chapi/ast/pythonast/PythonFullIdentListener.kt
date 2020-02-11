package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import domain.core.CodeFile

class PythonFullIdentListener(var fileName: String) : PythonAstBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)
    private var pythonVersion: String = ""

    override fun enterRoot(ctx: PythonParser.RootContext?) {

    }

    override fun enterClass_or_func_def_stmt(ctx: PythonParser.Class_or_func_def_stmtContext?) {
        super.enterClass_or_func_def_stmt(ctx)
    }

    fun getNodeInfo() {

    }
}
