package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.ast.antlr.RustParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import java.io.File
import java.util.concurrent.atomic.AtomicInteger



open class RustAstBaseListener(private val fileName: String) : RustParserBaseListener() {
    private val LIB_RS = "lib.rs"
    private val MAIN_RS = "main.rs"

    protected val codeContainer: CodeContainer = CodeContainer(FullName = fileName, PackageName = packageName)
    protected val classes: MutableList<CodeDataStruct> = mutableListOf()
    protected val imports: MutableList<CodeImport> = mutableListOf()
    protected var currentNode: CodeDataStruct = CodeDataStruct()
    protected open var currentFunction: CodeFunction = CodeFunction()
    protected val isEnteredClass = AtomicInteger(0)
    protected var isEnteredIndividualFunction: Boolean = false
    private var isEnteredClassFunction: Boolean = false

    protected lateinit var currentIndividualFunction: CodeFunction

    private val individualFunctions = mutableListOf<CodeFunction>()
    private val individualFields = mutableListOf<CodeField>()

    /**
     * packageName will parse from fileName, like:
     * - "src/main.rs" -> "main"
     * - "enfer_core/src/lib.rs" -> "enfer_core"
     * - "enfer_core/src/document.rs" -> "enfer_core::document"
     */
    private val packageName: String
        get() {
            val modulePath = fileName.substringBeforeLast("src")
                .substringBeforeLast(File.separator)

            val paths = fileName.substringAfterLast("src").split(File.separator)

            // if pathSize == 1, it means the file is in the root directory
            if (paths.size == 1) {
                return if (fileName.endsWith(LIB_RS) || fileName.endsWith(MAIN_RS)) {
                    ""
                } else {
                    fileName.substringBeforeLast(".")
                }
            }

            // if modulePath is empty, use paths as package names
            val packageName = paths
                .filter { it.isNotEmpty() && it != MAIN_RS && it != LIB_RS }
                .joinToString("::") { it.substringBeforeLast(".") }

            if (modulePath.isEmpty()) {
                return packageName
            }

            // if modulePath is not empty, use modulePath as package name
            if (packageName.isEmpty()) {
                return modulePath
            }

            return "$modulePath::$packageName"
        }

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

    override fun enterFunction_(ctx: RustParser.Function_Context?) {
        val functionName = ctx!!.identifier().text
        val function = CodeFunction(
            Name = functionName,
            Package = codeContainer.PackageName,
            Position = buildPosition(ctx)
        )

        currentIndividualFunction = function
        isEnteredIndividualFunction = true
    }

    override fun exitFunction_(ctx: RustParser.Function_Context?) {
        individualFunctions.add(currentIndividualFunction)
        isEnteredIndividualFunction = false
    }

    private fun buildDedicatedStructs(): List<CodeDataStruct> {
        if (individualFunctions.isEmpty() && individualFields.isEmpty()) return emptyList()

        return listOf(
            CodeDataStruct().apply {
                NodeName = fileName.substringBeforeLast('.')
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
