package chapi.ast.protobuf

import chapi.ast.antlr.Protobuf2BaseListener
import chapi.ast.antlr.Protobuf2Parser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

class Protobuf2FullIdentListener(var fileName: String) : Protobuf2BaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(
        FullName = fileName,
        Language = "protobuf",
        Kind = ContainerKind.IDL
    )

    override fun enterPackageStatement(ctx: Protobuf2Parser.PackageStatementContext) {
        val packageName = ctx.fullIdent().text
        codeContainer.PackageName = packageName
        codeContainer.DeclaredPackage = packageName
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    override fun enterMessageDef(ctx: Protobuf2Parser.MessageDefContext) {
        val codeDataStruct = constructMessageDef(ctx)

        codeContainer.DataStructures += codeDataStruct
    }

    override fun enterEnumDef(ctx: Protobuf2Parser.EnumDefContext) {
        val enumDs = constructEnum(ctx)
        codeContainer.DataStructures += enumDs
    }

    override fun enterExtendDef(ctx: Protobuf2Parser.ExtendDefContext?) {
        /// todo spike for scene
    }

    override fun enterServiceDef(ctx: Protobuf2Parser.ServiceDefContext?) {
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
                Position = buildPosition(context)
            )
        } ?: emptyList()

        val codeDataStruct = CodeDataStruct(
            NodeName = dsName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Functions = functions,
            Position = buildPosition(ctx),
            Type = DataStructType.INTERFACE
        )

        codeContainer.DataStructures += codeDataStruct
    }

    private fun constructMessageDef(ctx: Protobuf2Parser.MessageDefContext): CodeDataStruct {
        val messageName = ctx.messageName().text
        val codeDataStruct = CodeDataStruct(
            NodeName = messageName,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Type = DataStructType.STRUCT,
            Position = buildPosition(ctx)
        )

        ctx.messageBody().messageElement().map { context ->
            when (val child = context.getChild(0)) {
                is Protobuf2Parser.FieldContext -> {
                    codeDataStruct.Fields += constructField(child)
                }

                is Protobuf2Parser.EnumDefContext -> {
                    val enumDs = constructEnum(child)

                    codeDataStruct.InnerStructures += enumDs
                }

                is Protobuf2Parser.MessageDefContext -> {
                    codeDataStruct.InnerStructures += constructMessageDef(child)
                }

                is Protobuf2Parser.ExtendDefContext -> {
                    // skip
                }

                is Protobuf2Parser.OptionStatementContext -> {
                    // skip
                }

                is Protobuf2Parser.OneofContext -> {

                }

                is Protobuf2Parser.MapFieldContext -> {

                }

                is Protobuf2Parser.ReservedContext -> {

                }

                else -> {
                    println("Unknown message element: $child")
                }
            }
        }
        return codeDataStruct
    }

    private fun constructField(child: Protobuf2Parser.FieldContext): CodeField {
        return CodeField(
            TypeType = child.type_().text,
            TypeKey = child.fieldName().text,
            TypeValue = child.fieldNumber()?.text ?: "",
            Modifiers = child.fieldLabel()?.text?.let { listOf(it) } ?: emptyList()
        )
    }

    private fun constructEnum(child: Protobuf2Parser.EnumDefContext): CodeDataStruct {
        val name = child.enumName().text
        val enumDs = CodeDataStruct(
            NodeName = name,
            Type = DataStructType.ENUM,
            Module = codeContainer.PackageName,
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Position = buildPosition(child)
        )

        child.enumBody().enumElement().map {
            when (val enumChild = it.getChild(0)) {
                is Protobuf2Parser.OptionStatementContext -> {
                    enumDs.Fields += CodeField(
                        TypeType = "option",
                        TypeKey = enumChild.optionName().text,
                        TypeValue = enumChild.constant().text
                    )
                }

                is Protobuf2Parser.EnumFieldContext -> {
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
