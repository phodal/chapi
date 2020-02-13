package chapi.ast.scalaast

import domain.core.CodeContainer

class ScalaFullIdentListener(var fileName: String) : ScalaAstBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    fun getNodeInfo(): CodeContainer {
        return this.codeContainer
    }
}
