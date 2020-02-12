package chapi.ast.goast

import chapi.ast.antlr.GoParser
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction
import domain.core.CodeImport

class GoFullIdentListener(var fileName: String) : GoAstListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)
    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDecl(ctx: GoParser.ImportDeclContext?) {
        super.enterImportDecl(ctx)
    }

    override fun enterPackageClause(ctx: GoParser.PackageClauseContext?) {
        codeFile.PackageName = ctx!!.IDENTIFIER().text
    }

    override fun enterImportSpec(ctx: GoParser.ImportSpecContext?) {
        val originSource = ctx!!.importPath().text
        val sourceName = originSource.replace("\"", "")
        val codeImport = CodeImport(Source = sourceName)

        if (ctx.DOT() != null) {
            codeImport.AsName = "."
        }

        if (ctx.IDENTIFIER() != null) {
            codeImport.UsageName += ctx.IDENTIFIER().text
        }

        codeFile.Imports += codeImport
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
