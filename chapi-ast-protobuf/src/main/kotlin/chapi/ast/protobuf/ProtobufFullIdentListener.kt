package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf3BaseListener
import chapi.ast.antlr.Protobuf3Parser
import chapi.domain.core.*

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

    override fun enterExtendDef(ctx: Protobuf3Parser.ExtendDefContext?) {
        /// todo spike for scene
    }

    override fun enterServiceDef(ctx: Protobuf3Parser.ServiceDefContext?) {
        val dsName = ctx!!.serviceName().text
        val functions: List<CodeFunction> = ctx.serviceElement()?.mapNotNull { context ->
            if (context.rpc() == null) return@mapNotNull null

            val messageTypes = context.rpc().messageType().map { it.text }

            CodeFunction(
                Name = context.rpc().rpcName().text,
                FilePath = codeContainer.FullName,
                Package = codeContainer.PackageName,
                Type = FunctionType.RPC,
                Parameters = messageTypes.first()?.let { listOf(CodeProperty(TypeType = it, TypeValue = it)) } ?: emptyList(),
                ReturnType = messageTypes.last(),
            )
        } ?: emptyList()

        val codeDataStruct = CodeDataStruct(
            NodeName = dsName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Functions = functions
        )

        codeContainer.DataStructures += codeDataStruct
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
                    codeDataStruct.Fields += constructField(child)
                }

                is Protobuf3Parser.EnumDefContext -> {
                    val enumDs = constructEnum(child)

                    codeDataStruct.InnerStructures += enumDs
                }

                is Protobuf3Parser.MessageDefContext -> {
                    codeDataStruct.InnerStructures += constructMessageDef(child)
                }

                is Protobuf3Parser.ExtendDefContext -> {
                    // skip
                }

                is Protobuf3Parser.OptionStatementContext -> {
                    // skip
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

    private fun constructField(child: Protobuf3Parser.FieldContext): CodeField {
        return CodeField(
            TypeType = child.type_().text,
            TypeKey = child.fieldName().text,
            TypeValue = child.fieldNumber().text,
            Modifiers = child.fieldLabel()?.text?.let { listOf(it) } ?: emptyList()
        )
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
            when (val enumChild = it.getChild(0)) {
                is Protobuf3Parser.OptionStatementContext -> {
                    enumDs.Fields += CodeField(

                    )
                }

                is Protobuf3Parser.EnumFieldContext -> {
                    enumDs.Fields += CodeField(
                        TypeType = name,
                        TypeKey = enumChild.ident().text,
                        TypeValue = enumChild.intLit().text
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
