package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener
import domain.core.CodeFile

class TypeScriptIdentListener(fileName: String) : TypeScriptParserBaseListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {

    }

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
