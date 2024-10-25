package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf3BaseListener
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField

class ProtobufFullIdentListener(var fileName: String) : Protobuf3BaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterPackageStatement(ctx: Protobuf3Parser.PackageStatementContext) {
        val packageName = ctx.fullIdent().text
        codeContainer.PackageName = packageName
    }

    override fun enterMessageDef(ctx: Protobuf3Parser.MessageDefContext?) {
        val codeDataStruct = constructMessageDef(ctx)

        codeContainer.DataStructures += codeDataStruct
    }

    private fun constructMessageDef(ctx: Protobuf3Parser.MessageDefContext?): CodeDataStruct {
        val messageName = ctx!!.messageName().text
        val codeDataStruct = CodeDataStruct(
            NodeName = messageName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName
        )

        /// since a message element will be all def
        ctx.messageBody().messageElement().map {
            when (val child = it.getChild(0)) {
                is Protobuf3Parser.FieldContext -> {
                    codeDataStruct.Fields += CodeField(
                        TypeType = child.type_().text,
                        TypeKey = child.fieldName().text,
                        TypeValue = child.fieldNumber().text
                    )
                }
            }
        }
        return codeDataStruct
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
