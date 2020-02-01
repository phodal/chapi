package chapi.ast.javaast

import chapi.ast.antlr.JavaParser
import chapi.ast.antlr.JavaParserBaseListener

class JavaIdentListener : JavaParserBaseListener {
    constructor() : super()

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        super.enterPackageDeclaration(ctx)
    }

    fun getNodeInfo() {

    }
}
