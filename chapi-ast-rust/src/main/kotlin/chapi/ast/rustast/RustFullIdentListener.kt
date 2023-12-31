package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.domain.core.*
import java.io.File

open class RustFullIdentListener(val fileName: String) : RustAstBaseListener(fileName) {
    private var defaultNode = CodeDataStruct()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var localVars = mutableMapOf<String, String>()

    override fun enterStructStruct(ctx: RustParser.StructStructContext?) {
        val structName = ctx!!.identifier().text

        val codeStruct = CodeDataStruct(
            NodeName = structName,
            Package = codeContainer.PackageName
        )

        structMap[structName] = codeStruct
        defaultNode = codeStruct
    }
}
