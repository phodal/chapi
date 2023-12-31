package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.ast.antlr.RustParser.ItemContext
import chapi.ast.antlr.RustParser.SimplePathContext
import chapi.ast.antlr.RustParser.TypePathSegmentContext
import chapi.ast.antlr.RustParser.Type_Context
import chapi.ast.antlr.RustParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import java.io.File


open class RustAstBaseListener(private val fileName: String) : RustParserBaseListener() {
    private val LIB_RS = "lib.rs"
    private val MAIN_RS = "main.rs"

    protected val codeContainer: CodeContainer = CodeContainer(FullName = fileName, PackageName = packageName)
    protected val imports: MutableList<CodeImport> = mutableListOf()
    protected var currentNode: CodeDataStruct = CodeDataStruct()
    protected open var currentFunction: CodeFunction = CodeFunction()
    protected var isEnteredImplementation: Boolean = false
    protected var isEnteredIndividualFunction: Boolean = false

    protected lateinit var currentIndividualFunction: CodeFunction

    private val individualFunctions = mutableListOf<CodeFunction>()
    private val individualFields = mutableListOf<CodeField>()

    private var structMap = mutableMapOf<String, CodeDataStruct>()

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
        val allStruct = structMap.values
        DataStructures = (buildDedicatedStructs() + allStruct)
        Imports = imports
    }

    override fun enterUseDeclaration(ctx: RustParser.UseDeclarationContext?) {
        val importString: List<List<String>> = buildToPath(ctx)

        importString.forEach { path ->
            imports.add(
                CodeImport(
                    Source = path.joinToString("::"),
                    UsageName = path,
                    Scope = if (path.first() == "crate") "crate" else "cargo",
                    AsName = path.last()
                )
            )
        }
    }

    private fun buildToPath(ctx: RustParser.UseDeclarationContext?): List<List<String>> {
        val simplePath = ctx?.useTree()?.simplePath()
        val useTree = ctx?.useTree()?.useTree() ?: emptyList()

        if (simplePath != null && useTree.isEmpty()) {
            return listOf(buildSinglePath(simplePath).flatten())
        }

        if (simplePath != null) {
            val basePath = buildSinglePath(simplePath)
            val lists = useTree.mapNotNull { treeContext ->
                treeContext.simplePath()?.let {
                    buildSinglePath(it)
                }
            }.flatten()

            return lists.map { list ->
                basePath.map { base ->
                    base + list
                }
            }.flatten()
        }

        return listOf()
    }

    private fun buildSinglePath(simplePath: SimplePathContext): List<List<String>> {
        return simplePath.simplePathSegment().mapNotNull {
            if (it.identifier() != null) {
                it.identifier().text
            } else {
                it.text
            }
        }.map { listOf(it) }
    }

    override fun enterStructStruct(ctx: RustParser.StructStructContext?) {
        val structName = ctx?.identifier()?.text ?: return

        val item = ctx.parent?.parent?.parent
        var annotation: MutableList<CodeAnnotation> = mutableListOf()
        if (item is ItemContext) {
            annotation = buildAttribute(item.outerAttribute())
        }

        val codeStruct = CodeDataStruct(
            NodeName = structName,
            Package = codeContainer.PackageName,
            Annotations = annotation,
            Fields = buildFields(ctx.structFields())
        )

        structMap[structName] = codeStruct
    }

    private fun buildFields(structFields: RustParser.StructFieldsContext?): List<CodeField> {
        return structFields?.structField()?.map {
            CodeField(
                TypeType = lookupType(it.type_()),
                Annotations = buildAttribute(it.outerAttribute()),
                TypeValue = it.identifier()?.text ?: ""
            )
        } ?: emptyList()
    }

    private fun lookupType(type_: Type_Context?): String {
        val typeText = type_?.text
        imports.filter { it.AsName == typeText }.forEach {
            return it.Source
        }

        return typeText ?: ""
    }

    private fun buildAttribute(outerAttribute: List<RustParser.OuterAttributeContext>): MutableList<CodeAnnotation> {
        return outerAttribute.map { attributeContext ->
            val annotationName = attributeContext.attr()?.simplePath()?.simplePathSegment()?.filter { it.identifier() != null }?.map {
                it.identifier().text
            }?.joinToString(".") ?: ""

            val keyValues = attributeContext.attr().attrInput().delimTokenTree().tokenTree()
                .mapNotNull {
                    it.tokenTreeToken()
                        .mapNotNull { token -> token.macroIdentifierLikeToken() }
                        .map { tokenContext ->
                            AnnotationKeyValue(
                                Key = tokenContext.identifier().text,
                                Value = tokenContext.identifier().text
                            )
                        }
                }.flatten()

            CodeAnnotation(
                Name = annotationName,
                KeyValues = keyValues
            )
        }.toMutableList()
    }

    override fun exitStructStruct(ctx: RustParser.StructStructContext?) {

    }

    override fun enterFunction_(ctx: RustParser.Function_Context?) {
        if (!isEnteredImplementation) {
            val functionName = ctx!!.identifier().text
            val function = CodeFunction(
                Name = functionName,
                Package = codeContainer.PackageName,
                Position = buildPosition(ctx),
                Parameters = buildParameters(ctx.functionParameters()),
                ReturnType = buildReturnType(ctx.functionReturnType())
            )

            currentIndividualFunction = function
            isEnteredIndividualFunction = true
        } else {
            val functionName = ctx!!.identifier().text
            val function = CodeFunction(
                Name = functionName,
                Package = codeContainer.PackageName,
                Position = buildPosition(ctx),
                Parameters = buildParameters(ctx.functionParameters())
            )

            currentFunction = function
        }
    }

    open fun buildReturnType(functionReturnType: RustParser.FunctionReturnTypeContext?): String {
        return functionReturnType?.type_()?.text ?: ""
    }

    open fun buildParameters(functionParameters: RustParser.FunctionParametersContext?): List<CodeProperty> {
        if (functionParameters == null) return listOf()

        return functionParameters.functionParam().map {
            val functionParamPattern = it.functionParamPattern()
            CodeProperty(
                TypeValue = functionParamPattern.pattern()?.text ?: "",
                TypeType = functionParamPattern.type_()?.text ?: "",
            )
        }
    }

    override fun exitFunction_(ctx: RustParser.Function_Context?) {
        if (isEnteredImplementation == false) {
            isEnteredIndividualFunction = false
            individualFunctions.add(currentIndividualFunction)
        } else {
            currentNode.Functions += currentFunction
        }
    }

    override fun enterImplementation(ctx: RustParser.ImplementationContext?) {
        val nodeName = buildNodeName(ctx)
        if (structMap.containsKey(nodeName)) {
            currentNode = structMap[nodeName]!!
        } else {
            currentNode = CodeDataStruct(
                NodeName = nodeName,
                Package = codeContainer.PackageName,
                Position = buildPosition(ctx ?: return)
            )
        }

        isEnteredImplementation = true
    }

    private fun buildNodeName(ctx: RustParser.ImplementationContext?): String {
        // keep this for better to debug
        val types = when {
            ctx?.inherentImpl()?.type_() != null -> listOf(ctx.inherentImpl().type_())
            ctx?.traitImpl()?.type_() != null -> listOf(ctx.traitImpl().type_())
            else -> emptyList()
        }

        val typePathSegment: List<TypePathSegmentContext>? = types.firstOrNull()
            ?.typeNoBounds()
            ?.traitObjectTypeOneBound()
            ?.traitBound()
            ?.typePath()
            ?.typePathSegment()

        val pathIdentSegmentContext = typePathSegment?.map {
            it.pathIdentSegment()
        }?.firstOrNull()

        return pathIdentSegmentContext?.identifier()?.text ?: return ""
    }

    override fun exitImplementation(ctx: RustParser.ImplementationContext?) {
        isEnteredImplementation = false
        structMap[currentNode.NodeName] = currentNode
    }

    private fun buildPosition(ctx: ParserRuleContext): CodePosition {
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
