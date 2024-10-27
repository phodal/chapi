package chapi.ast.thrift

import chapi.ast.antlr.ThriftBaseListener
import chapi.ast.antlr.ThriftParser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext

class ThriftFullIdentListener(fileName: String) : ThriftBaseListener() {
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterNamespace_(ctx: ThriftParser.Namespace_Context?) {
        val prefix = ctx!!.children[0].text
        val namespace = ctx.text.removePrefix(prefix).trim()
        codeContainer.PackageName = namespace
    }

    private fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    override fun enterStruct_(ctx: ThriftParser.Struct_Context?) {
        val codeDataStruct = constructStructDef(ctx)
        codeContainer.DataStructures += codeDataStruct

    }

    private fun constructStructDef(ctx: ThriftParser.Struct_Context?): CodeDataStruct {
        val codeDataStruct = CodeDataStruct(
            NodeName = ctx!!.IDENTIFIER().text,
            Module = codeContainer.PackageName,
            Position = buildPosition(ctx),
            FilePath = codeContainer.FullName,
            Package = codeContainer.PackageName,
            Type = DataStructType.STRUCT,
        )

        ctx.field().forEach {
            codeDataStruct.Fields += constructField(it)
        }

        return codeDataStruct
    }

    private fun constructField(child: ThriftParser.FieldContext): CodeField {
        return CodeField(
            TypeType = child.field_type().text,
            TypeKey = child.IDENTIFIER().text,
            TypeValue = child.field_id()?.integer()?.text ?: "",
            Modifiers = listOf(child.field_req()?.text ?: ""),
        )
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
