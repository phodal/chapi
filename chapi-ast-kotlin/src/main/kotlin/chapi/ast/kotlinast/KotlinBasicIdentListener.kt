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
    protected var isEnteredIndividualFunction: Boolean = false
    private var isEnteredClassFunction: Boolean = false

    /** inner storage */

    protected val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    protected val classes: MutableList<CodeDataStruct> = mutableListOf()
    protected val imports: MutableList<CodeImport> = mutableListOf()
    protected var currentNode: CodeDataStruct = CodeDataStruct()
    protected var currentFunction: CodeFunction = CodeFunction()
    protected val isEnteredClass = AtomicInteger(0)
    private val individualFunctions = mutableListOf<CodeFunction>()
    protected lateinit var currentIndividualFunction: CodeFunction
    private val individualFields = mutableListOf<CodeField>()

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = (buildDedicatedClasses() + classes)
        Imports = imports
    }

    private fun buildDedicatedClasses(): List<CodeDataStruct> {
        if (individualFunctions.isEmpty() && individualFields.isEmpty()) return emptyList()

        return listOf(
            CodeDataStruct().apply {
                NodeName = fileName.substringBeforeLast('.') + "Kt"
                Type = DataStructType.OBJECT
                Package = codeContainer.PackageName
                FilePath = codeContainer.FullName
                Imports = imports
                Functions = individualFunctions
                Fields = individualFields
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
        if (functionDeclaration != null) {
            isEnteredIndividualFunction = true
            currentIndividualFunction = buildFunction(functionDeclaration)
        }
        // TODO replace enterClassDeclaration
        // TODO support object declaration
    }

    override fun exitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) {
        val functionDeclaration = ctx.declaration().functionDeclaration()
        if (functionDeclaration != null) {
            individualFunctions.add(currentIndividualFunction)
            isEnteredIndividualFunction = false
        }
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
            Implements = implements
            Annotations = annotations
            Imports = imports
            Position = buildPosition(ctx)
        }
    }

    fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        classes.add(currentNode)

        isEnteredClass.decrementAndGet()
    }

    override fun enterObjectDeclaration(ctx: KotlinParser.ObjectDeclarationContext?) {
        isEnteredClass.incrementAndGet()
        currentNode = buildObject(ctx!!)
    }

    private fun buildObject(ctx: KotlinParser.ObjectDeclarationContext): CodeDataStruct {
        val annotations = ctx.modifiers().getAnnotations()
        return CodeDataStruct().apply {
            NodeName = ctx.simpleIdentifier().Identifier().text
            Type = DataStructType.OBJECT
            Package = codeContainer.PackageName
            FilePath = codeContainer.FullName
            Annotations = annotations
            Imports = imports
            Position = buildPosition(ctx)
        }
    }

    override fun exitObjectDeclaration(ctx: KotlinParser.ObjectDeclarationContext?) {
        classes.add(currentNode)
        isEnteredClass.decrementAndGet()
    }

    override fun enterCompanionObject(ctx: KotlinParser.CompanionObjectContext?) {
        val classBody = ctx?.classBody() ?: return
        classBody.classMemberDeclarations().classMemberDeclaration().forEach {
            val propertyDeclaration = it.declaration()?.propertyDeclaration()
            val functionDeclaration = it.declaration()?.functionDeclaration()

            if (propertyDeclaration != null) currentNode.Fields += buildField(propertyDeclaration)
            if (functionDeclaration != null) currentNode.Functions += buildFunction(functionDeclaration)
        }
    }

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        val parameters = ctx.classParameters().classParameter().map(::buildProperty)
        val fields = ctx.classParameters().classParameter().mapNotNull(::buildField)
        val annotations = ctx.modifiers().getAnnotations()

        currentFunction = CodeFunction(
            Name = PRIMARY_CONSTRUCTOR_NAME,
            ReturnType = currentNode.getClassFullName(),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = true,
            Package = codeContainer.PackageName,
            Parameters = parameters,
            Annotations = annotations,
        )
        currentNode.Fields += fields
    }

    override fun exitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentNode.Functions += currentFunction
    }

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        // TODO scan function declaration under class declaration, skip individual function temporarily
        if (isEnteredClass.get() > 0) {
            isEnteredClassFunction = true
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
            Parameters = parameters,
            Annotations = annotations
        )
    }

    override fun exitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        if (isEnteredClass.get() > 0) {
            isEnteredClassFunction = false
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

        val annotations: List<CodeAnnotation> = it.modifiers()?.annotation()?.map {
            buildAnnotation(it)
        } ?: listOf()

        return CodeField(
            TypeType = getTypeFullName(it.type().text),
            TypeValue = it.expression()?.text ?: "",
            TypeKey = it.simpleIdentifier().text,
            Annotations = annotations,
            Modifiers = it.modifiers().getModifiers() + listOfNotNull(it.VAL()?.text, it.VAR()?.text),
        )
    }

    private fun buildField(it: KotlinParser.PropertyDeclarationContext): CodeField {
        val annotations: List<CodeAnnotation> = it.variableDeclaration().annotation()?.map {
            buildAnnotation(it)
        } ?: listOf()

        return CodeField(
            TypeType = it.variableDeclaration().type()?.run { getTypeFullName(text) } ?: UNKNOWN_PLACEHOLDER,
            TypeValue = it.expression()?.text ?: "",
            TypeKey = it.variableDeclaration().simpleIdentifier().text,
            Annotations = annotations,
            Modifiers = it.modifiers().getModifiers() + listOfNotNull(it.VAL()?.text, it.VAR()?.text),
        )
    }

    private fun KotlinParser.ModifiersContext?.getModifiers(): List<String> =
        this?.modifier()?.map { it.text }?.toList() ?: emptyList()

    private fun buildProperty(it: KotlinParser.ClassParameterContext): CodeProperty =
        CodeProperty(TypeValue = it.simpleIdentifier().text, TypeType = getTypeFullName(it.type().text))

    protected fun buildProperty(it: KotlinParser.FunctionValueParameterContext): CodeProperty =
        CodeProperty(
            TypeValue = it.parameter().simpleIdentifier().text,
            TypeType = getTypeFullName(it.parameter().type().text),
            Annotations = it.parameterModifiers().getAnnotations(),
        )

    private fun KotlinParser.ParameterModifiersContext?.getAnnotations(): List<CodeAnnotation> =
        this?.annotation()?.map(::buildAnnotation) ?: emptyList()

    protected fun KotlinParser.ModifiersContext?.getAnnotations(): List<CodeAnnotation> =
        this?.annotation()?.map(::buildAnnotation) ?: emptyList()

    private fun buildAnnotation(it: KotlinParser.AnnotationContext): CodeAnnotation {
        fun buildAnnotationKeyValue(argument: KotlinParser.ValueArgumentContext) =
            AnnotationKeyValue(Key = argument.simpleIdentifier()?.text ?: "value", Value = argument.expression().text)

        val codeAnnotation = CodeAnnotation(
            Name = it.singleAnnotation().unescapedAnnotation()
                .run { constructorInvocation()?.userType() ?: userType() }
                ?.run { simpleUserType().first().simpleIdentifier().Identifier().text }
                ?: UNKNOWN_PLACEHOLDER,
            KeyValues = it.singleAnnotation().unescapedAnnotation()
                .constructorInvocation()
                ?.run { valueArguments().valueArgument().map(::buildAnnotationKeyValue) }
                ?: emptyList()
        )

        codeAnnotation.Position = it.getPosition()
        return codeAnnotation
    }

    protected fun ParserRuleContext.getPosition(): CodePosition =
        CodePosition(
            StartLine = start.line,
            StartLinePosition = start.charPositionInLine,
            StopLine = stop.line,
            StopLinePosition = stop.charPositionInLine,
        )

    protected fun getTypeFullName(name: String): String {
        var resolveByImport = imports.firstOrNull { it.AsName == name }?.Source ?: "kotlin.$name"
        if (resolveByImport.contains("?\n")) {
            // in KotlinPaser.g4 QUEST_WS, NL is after quest
            resolveByImport = resolveByImport.replace("\n", "")
        }

        return resolveByImport
    }

    companion object {
        const val UNKNOWN_PLACEHOLDER = "<UNKNOWN>"
        const val PRIMARY_CONSTRUCTOR_NAME = "PrimaryConstructor"
        const val UNIT_TYPE = "Unit"
    }
}
