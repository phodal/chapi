package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser

import domain.core.CodeFile
import domain.core.CodeImport

open class CFullIdentListener(fileName: String) : CBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {
        super.enterFunctionDefinition(ctx)
    }

    override fun enterIncludeDirective(ctx: CParser.IncludeDirectiveContext?) {
        val importName = ctx!!.includeIdentifier().text
        val imp = CodeImport(
            Source = importName
        )
        codeFile.Imports += imp
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
