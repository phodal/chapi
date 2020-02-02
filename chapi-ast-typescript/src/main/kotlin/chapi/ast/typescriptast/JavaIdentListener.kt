package chapi.ast.typescriptast

import chapi.ast.antlr.TypeScriptParser
import chapi.ast.antlr.TypeScriptParserBaseListener

class TypeScriptIdentListener() : TypeScriptParserBaseListener() {
    override fun enterClassDeclaration(ctx: TypeScriptParser.ClassDeclarationContext?) {
        super.enterClassDeclaration(ctx)
    }

    fun getNodeInfo() {

    }
}
