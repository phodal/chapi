package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeImport

open class JavaBasicIdentListener(fileName: String) : JavaParserBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)
    private var classNodes: Array<CodeDataStruct> = arrayOf()
    private var imports: Array<CodeImport> = arrayOf()

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        val codeImport = CodeImport(Source = ctx!!.qualifiedName()!!.text)
        imports += codeImport

        codeFile.Imports += codeImport
    }

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        codeFile.PackageName = ctx?.qualifiedName()!!.text
    }

    fun getNodeInfo(): CodeFile {
        codeFile.DataStructures = classNodes
        return codeFile
    }
}
