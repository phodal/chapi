package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeField
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeProperty
import chapi.domain.core.DataStructType
import org.antlr.v4.runtime.ParserRuleContext
import java.util.concurrent.atomic.AtomicInteger

/**
 * listen to basic identifier that represent file and class structures,
 * e.g.
 * - package, imports, class
 * - functions, fields, annotations
 * - parameter type and return type
 *
 * for kotlin featured
 * - multiple classes in one file
 * - companion object, inner class
 * |    TODO inner structure
 * - individual variable/function
 */
open class KotlinBasicIdentListener(private val fileName: String) : KotlinAstListener() {
    /** inner storage */

    protected val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    protected val classes: MutableList<CodeDataStruct> = mutableListOf()
    protected val imports: MutableList<CodeImport> = mutableListOf()
    protected var currentNode: CodeDataStruct = CodeDataStruct()
    protected lateinit var currentFunction: CodeFunction
    protected val isEnteredClass = AtomicInteger(0)
    private val individualFunctions = mutableListOf<CodeFunction>()
    private val individualFields = mutableListOf<CodeField>()

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = (buildDedicatedClasses() + classes).toTypedArray()
        Imports = imports.toTypedArray()
    }

    private fun buildDedicatedClasses(): List<CodeDataStruct> {
        if (individualFunctions.isEmpty() && individualFields.isEmpty()) return emptyList()

        return listOf(
            CodeDataStruct().apply {
                NodeName = fileName.substringBeforeLast('.') + "Kt"
                Type = DataStructType.OBJECT
                Package = codeContainer.PackageName
                FilePath = codeContainer.FullName
                Imports = imports.toTypedArray()
                Functions = individualFunctions.toTypedArray()
                Fields = individualFields.toTypedArray()
            }
        )
    }

    /** override hooks */

    override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext) {
        if (ctx.childCount == 0) return

        codeContainer.PackageName = ctx.identifier().text
    }

    override fun enterImportList(ctx: KotlinParser.ImportListContext) {
        fun buildImport(header: KotlinParser.ImportHeaderContext) = header.identifier().run {
            CodeImport(Source = text, AsName = text.substringAfterLast('.'))
        }

        ctx.importHeader().map(::buildImport).let(imports::addAll)
    }

    // TODO go with the tree to avoid duplication route
    override fun enterTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) {
        val propertyDeclaration = ctx.declaration().propertyDeclaration()
        val functionDeclaration = ctx.declaration().functionDeclaration()

        if (propertyDeclaration != null) individualFields.add(buildField(propertyDeclaration))
        if (functionDeclaration != null) individualFunctions.add(buildFunction(functionDeclaration))
        // TODO replace enterClassDeclaration
        // TODO support object declaration
    }

    override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        isEnteredClass.incrementAndGet()
        currentNode = buildClass(ctx)
    }

    open fun buildClass(ctx: KotlinParser.ClassDeclarationContext): CodeDataStruct {
        val implements = ctx.delegationSpecifiers()?.text?.let(::getTypeFullName)?.let(::listOf) ?: emptyList()
        val annotations = ctx.modifiers().getAnnotations()
        return CodeDataStruct().apply {
            NodeName = ctx.simpleIdentifier().Identifier().text
            Type = DataStructType.CLASS
            Package = codeContainer.PackageName
            FilePath = codeContainer.FullName
            Implements = implements.toTypedArray()
            Annotations = annotations.toTypedArray()
            Imports = imports.toTypedArray()
        }
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        classes.add(currentNode)

        isEnteredClass.decrementAndGet()
    }

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        val parameters = ctx.classParameters().classParameter().map(::buildProperty)
        val fields = ctx.classParameters().classParameter()
            .mapNotNull(::buildField)
        val annotations = ctx.modifiers().getAnnotations()

        currentFunction = CodeFunction(
            Name = PRIMARY_CONSTRUCTOR_NAME,
            ReturnType = currentNode.getClassFullName(),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = true,
            Package = codeContainer.PackageName,
            Parameters = parameters.toTypedArray(),
            Annotations = annotations.toTypedArray(),
        )
        currentNode.Fields += fields
    }

    override fun exitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentNode.Functions += currentFunction
    }

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        // TODO scan function declaration under class declaration, skip individual function temporarily
        if (isEnteredClass.get() > 0) {
            currentFunction = buildFunction(ctx)
        }
    }

    open fun buildFunction(ctx: KotlinParser.FunctionDeclarationContext): CodeFunction {
        val parameters = ctx.functionValueParameters().functionValueParameter()
            .map(::buildProperty)
        val annotations = ctx.modifiers().getAnnotations()

        return CodeFunction(
            Name = ctx.simpleIdentifier()?.Identifier()?.text ?: UNKNOWN_PLACEHOLDER,
            ReturnType = getTypeFullName(ctx.type()?.typeReference()?.text ?: UNIT_TYPE),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = false,
            Package = codeContainer.PackageName,
            Parameters = parameters.toTypedArray(),
            Annotations = annotations.toTypedArray()
        )
    }

    override fun exitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        if (isEnteredClass.get() > 0) {
            currentNode.Functions += currentFunction
        }
    }

    // @see line 100
    override fun enterClassMemberDeclaration(ctx: KotlinParser.ClassMemberDeclarationContext) {
        val propertyDeclaration = ctx.declaration()?.propertyDeclaration()

        if (propertyDeclaration != null) currentNode.Fields += propertyDeclaration.let(::buildField)
    }

    /** utils */

    private fun buildField(it: KotlinParser.ClassParameterContext): CodeField? {
        if (it.VAL() == null && it.VAR() == null) return null

        return CodeField(
            TypeType = getTypeFullName(it.type().text),
            TypeValue = it.expression()?.text ?: "",
            TypeKey = it.simpleIdentifier().text,
            Modifiers = it.modifiers().getModifiers() + listOfNotNull(it.VAL()?.text, it.VAR()?.text),
        )
    }

    private fun buildField(it: KotlinParser.PropertyDeclarationContext): CodeField =
        CodeField(
            TypeType = it.variableDeclaration().type()?.run { getTypeFullName(text) } ?: UNKNOWN_PLACEHOLDER,
            TypeValue = it.expression()?.text ?: "",
            TypeKey = it.variableDeclaration().simpleIdentifier().text,
            Modifiers = it.modifiers().getModifiers() + listOfNotNull(it.VAL()?.text, it.VAR()?.text),
        )

    private fun KotlinParser.ModifiersContext?.getModifiers(): Array<String> =
        this?.modifier()?.map { it.text }?.toTypedArray() ?: emptyArray()

    private fun buildProperty(it: KotlinParser.ClassParameterContext): CodeProperty =
        CodeProperty(TypeValue = it.simpleIdentifier().text, TypeType = getTypeFullName(it.type().text))

    protected fun buildProperty(it: KotlinParser.FunctionValueParameterContext): CodeProperty =
        CodeProperty(
            TypeValue = it.parameter().simpleIdentifier().text,
            TypeType = getTypeFullName(it.parameter().type().text),
            Annotations = it.parameterModifiers().getAnnotations().toTypedArray(),
        )

    private fun KotlinParser.ParameterModifiersContext?.getAnnotations(): List<CodeAnnotation> =
        this?.annotation()?.map(::buildAnnotation) ?: emptyList()

    protected fun KotlinParser.ModifiersContext?.getAnnotations(): List<CodeAnnotation> =
        this?.annotation()?.map(::buildAnnotation) ?: emptyList()

    private fun buildAnnotation(it: KotlinParser.AnnotationContext): CodeAnnotation {
        fun buildAnnotationKeyValue(argument: KotlinParser.ValueArgumentContext) =
            AnnotationKeyValue(Key = argument.simpleIdentifier()?.text ?: "value", Value = argument.expression().text)

        return CodeAnnotation(
            Name = it.singleAnnotation().unescapedAnnotation()
                .run { constructorInvocation()?.userType() ?: userType() }
                ?.run { simpleUserType().first().simpleIdentifier().Identifier().text }
                ?: UNKNOWN_PLACEHOLDER,
            KeyValues = it.singleAnnotation().unescapedAnnotation()
                .constructorInvocation()
                ?.run { valueArguments().valueArgument().map(::buildAnnotationKeyValue) }
                ?.toTypedArray()
                ?: emptyArray()
        )
    }

    protected fun ParserRuleContext.getPosition(): CodePosition =
        CodePosition(
            StartLine = start.line,
            StartLinePosition = start.charPositionInLine,
            StopLine = stop.line,
            StopLinePosition = stop.charPositionInLine,
        )

    protected fun getTypeFullName(name: String): String =
        imports.firstOrNull { it.AsName == name }?.Source ?: "kotlin.$name"

    companion object {
        const val UNKNOWN_PLACEHOLDER = "<UNKNOWN>"
        const val PRIMARY_CONSTRUCTOR_NAME = "PrimaryConstructor"
        const val UNIT_TYPE = "Unit"
    }
}
