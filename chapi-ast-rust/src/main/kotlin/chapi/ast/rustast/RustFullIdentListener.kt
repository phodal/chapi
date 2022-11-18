package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.*

open class RustFullIdentListener(fileName: String) : RustAstBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    private var defaultNode = CodeDataStruct()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var localVars = mutableMapOf<String, String>()

    private var currentFunction = CodeFunction(IsConstructor = false)

    override fun enterStructStruct(ctx: RustParser.StructStructContext?) {
        val structName = ctx!!.identifier().text

        val codeStruct = CodeDataStruct(
            NodeName = structName,
            Package = codeContainer.PackageName
        )

        structMap[structName] = codeStruct
        defaultNode = codeStruct
    }

    fun getNodeInfo(): CodeContainer {
        for (entry in structMap) {
            codeContainer.DataStructures += entry.value
        }

        if (defaultNode.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultNode
        }

        return codeContainer
    }
}
