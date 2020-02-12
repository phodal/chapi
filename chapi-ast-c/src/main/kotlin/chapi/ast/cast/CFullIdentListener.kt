package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser
import domain.core.CodeDataStruct

import domain.core.CodeFile
import domain.core.CodeImport

open class CFullIdentListener(fileName: String) : CBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {
        super.enterFunctionDefinition(ctx)
    }

    override fun enterIncludeDeclaration(ctx: CParser.IncludeDeclarationContext?) {
        val importName = ctx!!.includeIdentifier().text
        val imp = CodeImport(
            Source = importName
        )
        codeFile.Imports += imp
    }

    override fun enterStructOrUnionSpecifier(ctx: CParser.StructOrUnionSpecifierContext?) {
        val codeDataStruct = CodeDataStruct()
        if (ctx!!.Identifier() != null) {
            codeDataStruct.NodeName = ctx.Identifier().text
        }

        currentDataStruct = codeDataStruct
        codeFile.DataStructures += codeDataStruct
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
