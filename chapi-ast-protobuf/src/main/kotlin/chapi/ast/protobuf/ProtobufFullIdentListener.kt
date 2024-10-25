package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf3BaseListener
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct

class ProtobufFullIdentListener(var fileName: String) : Protobuf3BaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterPackageStatement(ctx: Protobuf3Parser.PackageStatementContext) {
        val packageName = ctx.fullIdent().text
        codeContainer.PackageName = packageName
    }

    override fun enterMessageDef(ctx: Protobuf3Parser.MessageDefContext?) {
        val messageName = ctx!!.messageName().text
        codeContainer.DataStructures += CodeDataStruct(
            NodeName = messageName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName
        )
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
