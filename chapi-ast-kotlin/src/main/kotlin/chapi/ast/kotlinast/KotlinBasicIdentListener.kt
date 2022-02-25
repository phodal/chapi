package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.domain.core.AnnotationKeyValue
import chapi.domain.core.CodeAnnotation
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import chapi.domain.core.CodeImport
import chapi.domain.core.CodePosition
import chapi.domain.core.CodeProperty
import chapi.domain.core.DataStructType
import org.antlr.v4.runtime.ParserRuleContext

/**
 * listen to basic identifier that represent file and class structures,
 * e.g.
 * - package, imports, class
 * - functions, fields, annotations
 * - parameter type and return type
 *
 * for kotlin featured
 * - multiple classes in one file
 * - companion object
 * - individual variable/function
 */
class KotlinBasicIdentListener(fileName: String) : KotlinAstListener() {
    /** inner storage */

    private val codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    private val classes: MutableList<CodeDataStruct> = mutableListOf()
    private val imports: MutableList<CodeImport> = mutableListOf()
    private lateinit var currentNode: CodeDataStruct
    private lateinit var currentFunction: CodeFunction

    /** outer interfaces */

    override fun getNodeInfo(): CodeContainer = codeContainer.apply {
        DataStructures = classes.toTypedArray()
    }

    /** override hooks */

    override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext) {
        if (ctx.childCount == 0) return

        codeContainer.PackageName = ctx.identifier().text
    }

    override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        val implements = ctx.delegationSpecifiers()?.text?.let(::getTypeFullName)?.let(::listOf) ?: emptyList()
        val annotations = ctx.modifiers().getAnnotations()

        currentNode = CodeDataStruct()
        currentNode.Type = DataStructType.CLASS
        currentNode.Package = codeContainer.PackageName
        currentNode.NodeName = ctx.simpleIdentifier().Identifier().text
        currentNode.Implements = implements.toTypedArray()
        currentNode.Annotations = annotations.toTypedArray()
    }

    override fun exitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
        classes.add(currentNode)
    }

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        val parameters = ctx.classParameters().classParameter().map(::buildProperty)
        val annotations = ctx.modifiers().getAnnotations()

        currentFunction = CodeFunction(
            Name = PRIMARY_CONSTRUCTOR_NAME,
            ReturnType = currentNode.getClassFullName(),
            Override = false,
            Position = ctx.getPosition(),
            IsConstructor = true,
            Package = codeContainer.PackageName,
            Parameters = parameters.toTypedArray(),
            Annotations = annotations.toTypedArray()
        )
    }

    override fun exitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        currentNode.Functions += currentFunction
    }

    override fun enterImportList(ctx: KotlinParser.ImportListContext) {
        fun buildImport(header: KotlinParser.ImportHeaderContext) = header.identifier().run {
            CodeImport(Source = text, AsName = text.substringAfterLast('.'))
        }

        ctx.importHeader().map(::buildImport).let(imports::addAll)
    }

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        val parameters = ctx.functionValueParameters().functionValueParameter()
            .map(KotlinParser.FunctionValueParameterContext::parameter)
            .map(::buildProperty)
        val annotations = ctx.modifiers().getAnnotations()

        currentFunction = CodeFunction(
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

    private fun buildProperty(it: KotlinParser.ClassParameterContext) =
        CodeProperty(TypeValue = it.simpleIdentifier().text, TypeType = getTypeFullName(it.type().text))

    private fun buildProperty(it: KotlinParser.ParameterContext) =
        CodeProperty(TypeValue = it.simpleIdentifier().text, TypeType = getTypeFullName(it.type().text))

    override fun exitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        currentNode.Functions += currentFunction
    }

    private fun KotlinParser.ModifiersContext?.getAnnotations(): List<CodeAnnotation> =
        this?.annotation()?.map(::buildAnnotation) ?: emptyList()

    private fun buildAnnotation(it: KotlinParser.AnnotationContext): CodeAnnotation {
        fun buildAnnotationKeyValue(argument: KotlinParser.ValueArgumentContext) =
            AnnotationKeyValue(Key = argument.simpleIdentifier().text, Value = argument.expression().text)

        return CodeAnnotation(
            Name = it.singleAnnotation().unescapedAnnotation().constructorInvocation()
                ?.run { userType().simpleUserType().first().simpleIdentifier().Identifier().text }
                ?: UNKNOWN_PLACEHOLDER,
            KeyValues = it.singleAnnotation().unescapedAnnotation().constructorInvocation()
                ?.run { valueArguments().valueArgument().map(::buildAnnotationKeyValue) }
                ?.toTypedArray()
                ?: emptyArray()
        )
    }

    private fun ParserRuleContext.getPosition(): CodePosition =
        CodePosition(
            StartLine = start.line,
            StartLinePosition = start.charPositionInLine,
            StopLine = stop.line,
            StopLinePosition = stop.charPositionInLine,
        )

    private fun getTypeFullName(name: String): String =
        imports.firstOrNull { it.AsName == name }?.Source ?: "kotlin.$name"

    companion object {
        const val UNKNOWN_PLACEHOLDER = "UNKNOWN"
        const val PRIMARY_CONSTRUCTOR_NAME = "PrimaryConstructor"
        const val UNIT_TYPE = "Unit"
    }
}
