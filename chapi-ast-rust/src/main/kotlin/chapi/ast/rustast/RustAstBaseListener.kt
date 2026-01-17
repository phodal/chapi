package chapi.ast.rustast

import chapi.ast.antlr.RustParser
import chapi.ast.antlr.RustParser.GenericArgsContext
import chapi.ast.antlr.RustParser.ItemContext
import chapi.ast.antlr.RustParser.SimplePathContext
import chapi.ast.antlr.RustParser.TypePathSegmentContext
import chapi.ast.antlr.RustParser.Type_Context
import chapi.ast.antlr.RustParserBaseListener
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import java.io.File


open class RustAstBaseListener(private val fileName: String) : RustParserBaseListener() {
    private val packageName: String = calculatePackageName(fileName)
    private val codeContainer: CodeContainer = CodeContainer(
        FullName = fileName,
        PackageName = packageName,
        Language = "rust",
        Kind = ContainerKind.MODULE,
        ResolvedModulePath = packageName
    )
    private val imports: MutableList<CodeImport> = mutableListOf()
    private var currentNode: CodeDataStruct = CodeDataStruct()
    protected open var currentFunction: CodeFunction = CodeFunction()
    private var isEnteredImplementation: Boolean = false
    protected var isEnteredIndividualFunction: Boolean = false

    protected lateinit var currentIndividualFunction: CodeFunction

    private val individualFields = mutableListOf<CodeField>()
    protected val individualFunctions = mutableListOf<CodeFunction>()
    var structMap = mutableMapOf<String, CodeDataStruct>()
    protected var currentModule: String = ""

    /// for testing
    private var lastModule: String = ""


    /**
     * localVars will store all local variables in the current scope
     * - let statements: let a = 1;
     * - function parameters: fn test(a: i32) {}
     */
    var localVars: MutableMap<String, String> = mutableMapOf()

    /**
     * localFunctions will store all local functions in the current scope
     *
     * for individual function: currentFunction.Name
     * for Implementation: currentNode.NodeName + "::" + currentFunction.Name
     *
     */
    var localFunctions: MutableMap<String, CodeFunction> = mutableMapOf()

    open fun getNodeInfo(): CodeContainer = codeContainer.apply {
        val allStruct = structMap.values
        DataStructures = (buildDedicatedStructs() + allStruct)
        Imports = imports
    }

    override fun enterModule(ctx: RustParser.ModuleContext?) {
        currentModule = (ctx?.identifier()?.text ?: return)
    }

    override fun exitModule(ctx: RustParser.ModuleContext?) {
        lastModule = currentModule
        currentModule = ""
    }

    override fun enterUseDeclaration(ctx: RustParser.UseDeclarationContext?) {
        val importString: List<List<String>> = buildToPath(ctx)
        
        // Check for glob import (use x::*)
        val isGlob = ctx?.useTree()?.text?.endsWith("*") == true

        importString.forEach { path ->
            val kind = when {
                isGlob -> ImportKind.WILDCARD
                path.first() == "crate" -> ImportKind.RELATIVE
                else -> ImportKind.NAMED
            }
            
            imports.add(
                CodeImport(
                    Source = path.joinToString("::"),
                    UsageName = path,
                    Scope = if (path.first() == "crate") "crate" else "cargo",
                    AsName = path.last(),
                    // New structured fields
                    Kind = kind,
                    PathSegments = path,
                    Specifiers = listOf(ImportSpecifier(
                        OriginalName = path.last(),
                        LocalName = path.last()
                    ))
                )
            )
        }
    }

    /**
     * Builds a list of paths based on the given `UseDeclarationContext`.
     *
     * @param ctx The `UseDeclarationContext` representing the use declaration.
     * @return A list of paths, where each path is represented as a list of strings.
     */
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
            Module = currentModule,
            Package = codeContainer.PackageName,
            Annotations = annotation,
            Fields = buildFields(ctx.structFields()),
            Position = buildPosition(ctx)
        )

        structMap[structName] = codeStruct
    }

    private fun buildFields(structFields: RustParser.StructFieldsContext?): List<CodeField> {
        return structFields?.structField()?.map {
            CodeField(
                TypeType = lookupType(it.type_()),
                Annotations = buildAttribute(it.outerAttribute()),
                TypeValue = it.identifier()?.text ?: "",
            )
        } ?: emptyList()
    }

    private fun lookupType(context: Type_Context?): String {
        val typePath = pathSegmentContexts(context)?.mapNotNull {
            it.pathIdentSegment()?.identifier()?.text
        }?.joinToString("::") ?: context?.text

        return lookupByType(typePath)
    }

    open fun lookupByType(typeText: String?): String {
        if (typeText == null) return ""
        var text = if (typeText.contains("::")) {
            val crateName = typeText.split("::").first()
            if (crateName == "std" || crateName == "crate") {
                typeText
            } else {
                if (imports.any { it.Source == typeText }) {
                    typeText
                } else {
                    crateName
                }
            }
        } else {
            typeText
        }

        if (localVars.containsKey(text)) {
            return localVars[text] ?: ""
        }

        if (localFunctions.containsKey(text)) {
            val function = localFunctions[text]!!
            text = if (function.MultipleReturns.isNotEmpty()) {
                function.MultipleReturns.first().TypeType
            } else {
                function.ReturnType
            }
        }

        imports.filter { it.AsName == text }.forEach {
            return it.Source
        }

        return text
    }

    private fun buildAttribute(outerAttribute: List<RustParser.OuterAttributeContext>): MutableList<CodeAnnotation> {
        return outerAttribute.map { attributeContext ->
            val annotationName = attributeContext.attr()?.simplePath()?.simplePathSegment()?.filter { it.identifier() != null }?.joinToString(".") {
                it.identifier().text
            } ?: ""

            val keyValues: List<AnnotationKeyValue> = attributeContext.attr()
                ?.attrInput()
                ?.delimTokenTree()?.tokenTree()
                ?.mapNotNull {
                    processAnnotationKeyValues(it)
                }?.flatten() ?: listOf()

            CodeAnnotation(
                Name = annotationName,
                KeyValues = keyValues,
                Position = buildPosition(attributeContext)
            )
        }.toMutableList()
    }

    private fun processAnnotationKeyValues(it: RustParser.TokenTreeContext): List<AnnotationKeyValue> {
        val tokenTreeToken = it.tokenTreeToken()
        val skippedIndex: MutableList<Int> = mutableListOf()

        return tokenTreeToken.mapIndexedNotNull { index, context ->
            when (val child = context.children.firstOrNull()) {
                is RustParser.MacroIdentifierLikeTokenContext -> {
                    /// if next is equal, it means it's a key-value pair
                    var value = child.identifier()?.text ?: ""
                    val hasNext = tokenTreeToken.getOrNull(index + 1)?.children?.firstOrNull()
                    if (hasNext is RustParser.MacroPunctuationTokenContext) {
                        if (hasNext.text == "=") {
                            skippedIndex.add(index + 2)
                            value = tokenTreeToken.getOrNull(index + 2)?.text ?: ""
                        }
                    }

                    AnnotationKeyValue(
                        Key = child.identifier().text,
                        Value = lookupByType(value)
                    )
                }

                is RustParser.MacroLiteralTokenContext -> {
                    if (skippedIndex.contains(index)) {
                        return@mapIndexedNotNull null
                    }

                    AnnotationKeyValue(
                        Key = child.text,
                        Value = lookupByType(child.text)
                    )
                }

                else -> {
                    null
                }
            }
        }
    }

    override fun exitStructStruct(ctx: RustParser.StructStructContext?) {

    }

    override fun enterEnumeration(ctx: RustParser.EnumerationContext?) {
        val enumName = ctx?.identifier()?.text ?: return

        val item = ctx.parent?.parent?.parent
        var annotation: MutableList<CodeAnnotation> = mutableListOf()
        if (item is ItemContext) {
            annotation = buildAttribute(item.outerAttribute())
        }

        val codeStruct = CodeDataStruct(
            NodeName = enumName,
            Module = currentModule,
            Package = codeContainer.PackageName,
            Annotations = annotation,
            Fields = buildEnumFields(ctx.enumItems()),
            Position = buildPosition(ctx),
            Type = DataStructType.ENUM
        )

        structMap[enumName] = codeStruct
    }

    private fun buildEnumFields(enumItems: RustParser.EnumItemsContext?): List<CodeField> {
        return enumItems?.enumItem()?.map {
            CodeField(
                TypeType = it.enumItemTuple()?.text ?: "",
                Annotations = buildAttribute(it.outerAttribute()),
                TypeValue = it.identifier()?.text ?: "",
            )
        } ?: emptyList()
    }

    override fun enterFunction_(ctx: RustParser.Function_Context?) {
        val item = ctx?.parent?.parent
        var annotations: MutableList<CodeAnnotation> = mutableListOf()
        if (item is ItemContext) {
            annotations = buildAttribute(item.outerAttribute())
        }

        val possibleReturnType = buildReturnType(ctx!!.functionReturnType())

        val multipleReturns = if (possibleReturnType == "Result") {
            buildMultipleReturns(ctx.functionReturnType())
        } else {
            listOf()
        }

        if (!isEnteredImplementation) {
            isEnteredIndividualFunction = true

            val functionName = ctx.identifier().text
            val function = CodeFunction(
                Name = functionName,
                Package = packageName,
                Position = buildPosition(ctx),
                Parameters = buildParameters(ctx.functionParameters()),
                ReturnType = possibleReturnType,
                Annotations = annotations,
                MultipleReturns = multipleReturns
            )

            currentIndividualFunction = function
        } else {
            val functionName = ctx.identifier().text
            val function = CodeFunction(
                Name = functionName,
                Package = packageName,
                Position = buildPosition(ctx),
                Parameters = buildParameters(ctx.functionParameters()),
                ReturnType = possibleReturnType,
                Annotations = annotations,
                MultipleReturns = multipleReturns
            )

            currentFunction = function
        }

        val vars = ctx.functionParameters()?.functionParam()?.associate {
            val pattern = it.functionParamPattern()
            val varName = pattern?.pattern()?.text ?: ""
            val varType = pattern?.type_()?.text ?: ""
            varName to varType
        } ?: mapOf()

        localVars += vars
    }

    open fun buildReturnType(functionReturnType: RustParser.FunctionReturnTypeContext?): String {
        val typeContext: Type_Context? = functionReturnType?.type_()
        return lookupType(typeContext)
    }

    private fun buildMultipleReturns(functionReturnType: RustParser.FunctionReturnTypeContext?): List<CodeProperty> {
        val typeContext = functionReturnType?.type_()
        return typeToProperties(typeContext).distinct()
    }

    private fun typeToProperties(typeContext: Type_Context?): List<CodeProperty> {
        val typePathSegment = typeContext?.typeNoBounds()?.traitObjectTypeOneBound()?.traitBound()?.typePath()?.typePathSegment()
        val genericArgs: List<GenericArgsContext> = typePathSegment?.mapNotNull {
            it.genericArgs()
        } ?: listOf()

        return genericArgs.map { genericArgsContext ->
            val typeList = genericArgsContext.genericArgsTypes().type_()
            val generics = typeList.map {
                typeToProperties(it)
            }.flatten()

            generics + typeList.map {
                CodeProperty(
                    TypeType = lookupType(it),
                    TypeValue = it.text,
                )
            }
        }.flatten()
    }

    open fun buildParameters(functionParameters: RustParser.FunctionParametersContext?): List<CodeProperty> {
        if (functionParameters == null) return listOf()

        return functionParameters.functionParam().map {
            val functionParamPattern = it.functionParamPattern()
            CodeProperty(
                TypeValue = functionParamPattern?.pattern()?.text ?: "",
                TypeType = functionParamPattern?.type_()?.text ?: "",
            )
        }
    }

    override fun exitFunction_(ctx: RustParser.Function_Context?) {
        if (isEnteredImplementation == false) {
            isEnteredIndividualFunction = false
            individualFunctions.add(currentIndividualFunction)
            localFunctions[currentIndividualFunction.Name] = currentIndividualFunction
        } else {
            currentNode.Functions += currentFunction
            localFunctions[currentNode.NodeName + "::" + currentFunction.Name] = currentFunction
        }

        localVars.clear()
    }

    override fun enterImplementation(ctx: RustParser.ImplementationContext?) {
        val nodeName = buildNodeName(ctx)
        currentNode = if (structMap.containsKey(nodeName)) {
            structMap[nodeName]!!
        } else {
            CodeDataStruct(
                NodeName = nodeName,
                Module = currentModule,
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

        val typeContext = types.firstOrNull()

        val pathIdentSegmentContext = pathSegmentContexts(typeContext)?.map {
            it.pathIdentSegment()
        }?.firstOrNull()

        return pathIdentSegmentContext?.identifier()?.text ?: return ""
    }

    private fun pathSegmentContexts(typeContext: Type_Context?): MutableList<TypePathSegmentContext>? = typeContext
        ?.typeNoBounds()
        ?.traitObjectTypeOneBound()
        ?.traitBound()
        ?.typePath()
        ?.typePathSegment()

    override fun exitImplementation(ctx: RustParser.ImplementationContext?) {
        isEnteredImplementation = false
        structMap[currentNode.NodeName] = currentNode
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        return CodePosition(
            StartLine = ctx.start.line,
            StartLinePosition = ctx.start.charPositionInLine,
            StopLine = ctx.stop.line,
            StopLinePosition = ctx.stop.charPositionInLine
        )
    }

    private fun buildDedicatedStructs(): List<CodeDataStruct> {
        if (individualFunctions.isEmpty() && individualFields.isEmpty()) return emptyList()

        return listOf(
            CodeDataStruct().apply {
                NodeName = fileName.substringBeforeLast('.')
                Module = lastModule
                Type = DataStructType.OBJECT
                Package = codeContainer.PackageName
                FilePath = codeContainer.FullName
                Imports = imports
                Functions = individualFunctions
                Fields = individualFields
            }
        )
    }

    companion object {
        val LIB_RS = "lib.rs"
        val MAIN_RS = "main.rs"

        /**
         * Calculates the package name for a given file name in the Kotlin language.
         *
         * @param fileName the name of the file for which the package name needs to be calculated
         * @return the calculated package name as a string
         *
         * The `calculatePackageName` method is used to determine the package name based on the given file name in the Kotlin language. The package name is an essential part of organizing code in Kotlin and is used to group related classes and files together.
         *
         * The package name is derived from the file name using the following rules:
         * - If the file name is "src/main.rs", the package name will be "main".
         * - If the file name is "enfer_core/src/lib.rs", the package name will be "enfer_core".
         * - If the file name is "enfer_core/src/document.rs", the package name will be "enfer_core::document".
         *
         * It is important to note that the package name is calculated based on the file path and may differ from the actual package declaration in the file.
         *
         * Example usage:
         * ```kotlin
         * val fileName = "enfer_core/src/document.rs"
         * val packageName = calculatePackageName(fileName)
         * println(packageName) // Output: enfer_core::document
         * ```
         */
        fun calculatePackageName(fileName: String): String {
            val modulePath = fileName.substringBeforeLast("src")
                .substringBeforeLast(File.separator)

            val paths = fileName.substringAfterLast("src").split(File.separator)

            // if pathSize == 1, it means the file is in the root directory
            if (paths.size == 1) {
                return when {
                    fileName.endsWith(LIB_RS) -> {
                        "lib"
                    }

                    fileName.endsWith(MAIN_RS) -> {
                        "main"
                    }

                    else -> {
                        fileName.substringBeforeLast(".")
                    }
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
    }
}
