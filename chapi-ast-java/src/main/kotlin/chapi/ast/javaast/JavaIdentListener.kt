package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener
import domain.core.CodeFile

class JavaIdentListener(fileName: String) : JavaParserBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        super.enterPackageDeclaration(ctx)
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
