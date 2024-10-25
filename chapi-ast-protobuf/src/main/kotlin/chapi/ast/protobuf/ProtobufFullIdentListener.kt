package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf3BaseListener
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField
import chapi.domain.core.DataStructType

class ProtobufFullIdentListener(var fileName: String) : Protobuf3BaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterPackageStatement(ctx: Protobuf3Parser.PackageStatementContext) {
        val packageName = ctx.fullIdent().text
        codeContainer.PackageName = packageName
    }

    override fun enterMessageDef(ctx: Protobuf3Parser.MessageDefContext) {
        val codeDataStruct = constructMessageDef(ctx)

        codeContainer.DataStructures += codeDataStruct
    }

    override fun enterEnumDef(ctx: Protobuf3Parser.EnumDefContext) {
        val enumDs = constructEnum(ctx)
        codeContainer.DataStructures += enumDs
    }

    private fun constructMessageDef(ctx: Protobuf3Parser.MessageDefContext): CodeDataStruct {
        val messageName = ctx.messageName().text
        val codeDataStruct = CodeDataStruct(
            NodeName = messageName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName
        )

        ctx.messageBody().messageElement().map { context ->
            when (val child = context.getChild(0)) {
                is Protobuf3Parser.FieldContext -> {
                    codeDataStruct.Fields += CodeField(
                        TypeType = child.type_().text,
                        TypeKey = child.fieldName().text,
                        TypeValue = child.fieldNumber().text
                    )
                }

                is Protobuf3Parser.EnumDefContext -> {
                    val enumDs = constructEnum(child)

                    codeDataStruct.InnerStructures += enumDs
                }

                is Protobuf3Parser.MessageDefContext -> {

                }

                is Protobuf3Parser.ExtendDefContext -> {

                }

                is Protobuf3Parser.OptionStatementContext -> {

                }

                is Protobuf3Parser.OneofContext -> {

                }

                is Protobuf3Parser.MapFieldContext -> {

                }

                is Protobuf3Parser.ReservedContext -> {

                }

                else -> {
                    println("Unknown message element: $child")
                }
            }
        }
        return codeDataStruct
    }

    private fun constructEnum(child: Protobuf3Parser.EnumDefContext): CodeDataStruct {
        val name = child.enumName().text
        val enumDs = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.ENUM,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName
        )

        child.enumBody().enumElement().map {
            when (val child = it.getChild(0)) {
                is Protobuf3Parser.OptionStatementContext -> {
                    enumDs.Fields += CodeField(

                    )
                }

                is Protobuf3Parser.EnumFieldContext -> {
                    enumDs.Fields += CodeField(
                        TypeType = name,
                        TypeKey = child.ident().text,
                        TypeValue = child.intLit().text
                    )
                }
            }
        }
        return enumDs
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
