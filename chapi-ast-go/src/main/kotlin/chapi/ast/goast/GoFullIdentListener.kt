package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener

class GoFullIdentListener() : GoAstListener() {
    override fun enterImportDecl(ctx: GoParser.ImportDeclContext?) {
        super.enterImportDecl(ctx)
    }

    fun getNodeInfo() {

    }
}
