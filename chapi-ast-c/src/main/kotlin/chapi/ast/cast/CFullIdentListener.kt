package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser

import domain.core.CodeFile

open class CFullIdentListener(fileName: String) : CBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {
        super.enterFunctionDefinition(ctx)
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
