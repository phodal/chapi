package chapi.ast.goast

import chapi.ast.antlr.GoParser
import domain.core.CodeDataStruct
import domain.core.CodeFile
import domain.core.CodeFunction

class GoFullIdentListener(var fileName: String) : GoAstListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)
    private var currentNode = CodeDataStruct()
    private var defaultNode = CodeDataStruct()
    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterImportDecl(ctx: GoParser.ImportDeclContext?) {
        super.enterImportDecl(ctx)
    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
