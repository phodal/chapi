package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.CodeContainer

class KotlinFullIdentListener(fileName: String) : KotlinAstListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        super.enterFunctionDeclaration(ctx)
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
