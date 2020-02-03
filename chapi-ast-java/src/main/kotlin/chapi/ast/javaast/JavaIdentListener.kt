package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction
import domain.core.CodeImport

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
    private var nodes : Array<CodeDataStruct> = arrayOf()
    private var imports : Array<CodeImport> = arrayOf()
    private var currentNode = CodeDataStruct()
    private var currentFunction = CodeFunction()
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        super.enterPackageDeclaration(ctx)
        codeFile.PackageName = ctx?.qualifiedName()!!.text
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        super.enterImportDeclaration(ctx)
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeFile.Imports += codeImport
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
