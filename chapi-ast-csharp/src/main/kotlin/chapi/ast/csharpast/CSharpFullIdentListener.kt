package chapi.ast.csharpast

import domain.core.CodeFile

class CSharpFullIdentListener(fileName: String) : CSharpAstListener() {
    private var codeFile: CodeFile = CodeFile(FullName = fileName)

    fun getNodeInfo(): CodeFile {
        return codeFile
    }
}
