package chapi.ast.cppast

import chapi.ast.antlr.CPPBaseListener
import chapi.domain.core.CodeContainer

class CPPFullIdentListener(fileName: String) : CPPBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
