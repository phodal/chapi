package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener

class PythonIdentListener() : PythonParserBaseListener() {
    override fun enterClass_or_func_def_stmt(ctx: PythonParser.Class_or_func_def_stmtContext?) {
        super.enterClass_or_func_def_stmt(ctx)
    }

    fun getNodeInfo() {

    }
}
