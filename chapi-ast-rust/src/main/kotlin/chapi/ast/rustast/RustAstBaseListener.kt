package chapi.ast.rustast

import chapi.ast.antlr.RustParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import java.util.concurrent.atomic.AtomicInteger

open class RustAstBaseListener(private val fileName: String) : RustParserBaseListener() {
    protected val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    protected val classes: MutableList<CodeDataStruct> = mutableListOf()
    protected val imports: MutableList<CodeImport> = mutableListOf()
    protected var currentNode: CodeDataStruct = CodeDataStruct()
    protected open var currentFunction: CodeFunction = CodeFunction()
    protected val isEnteredClass = AtomicInteger(0)
    protected lateinit var currentIndividualFunction: CodeFunction

    private val individualFunctions = mutableListOf<CodeFunction>()
    private val individualFields = mutableListOf<CodeField>()


    open fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = (buildDedicatedStructs() + classes)
        Imports = imports
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    private fun buildDedicatedStructs(): List<CodeDataStruct> {
        if (individualFunctions.isEmpty() && individualFields.isEmpty()) return emptyList()

        return listOf(
            CodeDataStruct().apply {
                NodeName = fileName.substringBeforeLast('.') + "rs"
                Type = DataStructType.OBJECT
                Package = codeContainer.PackageName
                FilePath = codeContainer.FullName
                Imports = imports
                Functions = individualFunctions
                Fields = individualFields
            }
        )
    }
}
