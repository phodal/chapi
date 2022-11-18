package chapi.ast.rustast

import chapi.domain.core.*

open class RustFullIdentListener(fileName: String) : RustAstBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private val defaultDataStruct = CodeDataStruct()
    private var currentFunction = CodeFunction()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)


    fun getNodeInfo(): CodeContainer {
        if (defaultDataStruct.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultDataStruct
        }
        return codeContainer
    }
}
