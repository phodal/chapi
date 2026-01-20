package chapi.ast.swiftast

import chapi.ast.antlr.Swift5Parser
import chapi.ast.antlr.Swift5ParserBaseListener
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeTypeRef
import chapi.domain.core.ContainerKind
import chapi.domain.core.DataStructType
import chapi.domain.core.TopLevelScope
import chapi.domain.core.CodeDataStruct
import org.antlr.v4.runtime.ParserRuleContext

class SwiftFullIdentListener(private val filePath: String) : Swift5ParserBaseListener() {
    private val imports = mutableListOf<CodeImport>()
    private val dataStructures = mutableListOf<CodeDataStruct>()
    private val stack = ArrayDeque<CodeDataStruct>()
    private val topLevelFunctions = mutableListOf<CodeFunction>()

    override fun enterImport_declaration(ctx: Swift5Parser.Import_declarationContext) {
        val source = ctx.import_path()?.text ?: return
        imports += CodeImport(Source = source)
    }

    override fun enterStruct_declaration(ctx: Swift5Parser.Struct_declarationContext) {
        val name = ctx.struct_name()?.text ?: return
        stack.addLast(
            CodeDataStruct(
                NodeName = name,
                Type = DataStructType.STRUCT,
                FilePath = filePath,
                Position = ctx.toPosition(),
            )
        )
    }

    override fun exitStruct_declaration(ctx: Swift5Parser.Struct_declarationContext) {
        finishDataStruct()
    }

    override fun enterClass_declaration(ctx: Swift5Parser.Class_declarationContext) {
        val name = ctx.class_name()?.text ?: return
        stack.addLast(
            CodeDataStruct(
                NodeName = name,
                Type = DataStructType.CLASS,
                FilePath = filePath,
                Position = ctx.toPosition(),
            )
        )
    }

    override fun exitClass_declaration(ctx: Swift5Parser.Class_declarationContext) {
        finishDataStruct()
    }

    override fun enterEnum_declaration(ctx: Swift5Parser.Enum_declarationContext) {
        val name = ctx.union_style_enum()?.enum_name()?.text
            ?: ctx.raw_value_style_enum()?.enum_name()?.text
            ?: return
        stack.addLast(
            CodeDataStruct(
                NodeName = name,
                Type = DataStructType.ENUM,
                FilePath = filePath,
                Position = ctx.toPosition(),
            )
        )
    }

    override fun exitEnum_declaration(ctx: Swift5Parser.Enum_declarationContext) {
        finishDataStruct()
    }

    override fun enterFunction_declaration(ctx: Swift5Parser.Function_declarationContext) {
        val name = ctx.function_name()?.text ?: return

        val returnTypeText = ctx.function_signature()
            ?.function_result()
            ?.type()
            ?.text
            ?.takeIf { it.isNotBlank() }

        val function = CodeFunction(
            Name = name,
            ReturnType = returnTypeText ?: "",
            ReturnTypeRef = returnTypeText?.let { CodeTypeRef.simple(it) },
            Position = ctx.toPosition(),
        )

        if (stack.isEmpty()) {
            topLevelFunctions += function
        } else {
            val current = stack.last()
            current.Functions = current.Functions + function
        }
    }

    fun getNodeInfo(): CodeContainer {
        val topLevel = if (topLevelFunctions.isEmpty()) null else TopLevelScope(Functions = topLevelFunctions.toList())

        return CodeContainer(
            FullName = filePath,
            PackageName = "",
            Imports = imports.toList(),
            DataStructures = dataStructures.toList(),
            Language = "swift",
            Kind = ContainerKind.SOURCE_FILE,
            DeclaredPackage = "",
            TopLevel = topLevel,
        )
    }

    private fun finishDataStruct() {
        if (stack.isEmpty()) return
        val finished = stack.removeLast()
        if (stack.isEmpty()) {
            dataStructures += finished
        } else {
            val parent = stack.last()
            parent.InnerStructures = parent.InnerStructures + finished
        }
    }
}

private fun ParserRuleContext.toPosition(): CodePosition {
    val startToken = this.start
    val stopToken = this.stop
    return CodePosition(
        StartLine = startToken?.line ?: 0,
        StartLinePosition = startToken?.charPositionInLine ?: 0,
        StopLine = stopToken?.line ?: 0,
        StopLinePosition = stopToken?.charPositionInLine ?: 0,
    )
}

