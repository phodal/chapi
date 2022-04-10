package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.ast.antlr.CSharpParser.Common_member_declarationContext
import chapi.ast.antlr.CSharpParserBaseListener
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext

open class CSharpAstListener(open val fileName: String) : CSharpParserBaseListener() {
    protected var currentNamespace: String = ""
    protected var currentStruct: CodeDataStruct = CodeDataStruct();
    protected var codeContainer: CodeContainer = CodeContainer(FullName = fileName)
    protected var currentContainer: CodeContainer = codeContainer
    protected var containerStack: Stack<CodeContainer> = Stack()
    protected var currentPackage: CodePackage = CodePackage()
    protected var currentFunction: CodeFunction = CodeFunction()

    override fun enterCompilation_unit(ctx: CSharpParser.Compilation_unitContext?) {
        containerStack.push(codeContainer)
    }

    override fun enterUsing_directives(ctx: CSharpParser.Using_directivesContext?) {
        for (usingCtx in ctx!!.using_directive()) {
            handleDirective(usingCtx!!)
        }
    }

    private fun handleDirective(usingCtx: CSharpParser.Using_directiveContext) {
        val usingType = usingCtx::class.java.simpleName
        val codeImport = CodeImport()

        when (usingType) {
            "UsingNamespaceDirectiveContext" -> {
                val directiveCtx = usingCtx as CSharpParser.UsingNamespaceDirectiveContext
                codeImport.Source = directiveCtx.namespace_or_type_name().text
            }
            "UsingAliasDirectiveContext" -> {
                val alisCtx = usingCtx as CSharpParser.UsingAliasDirectiveContext

                codeImport.Source = alisCtx.namespace_or_type_name().text
                codeImport.AsName = alisCtx.identifier().text
            }
            else -> {
                println("handleDirective: $usingType")
            }
        }

        codeContainer.Imports += codeImport
    }

    override fun enterNamespace_member_declaration(ctx: CSharpParser.Namespace_member_declarationContext?) {
        val namespaceDeclaration = ctx!!.namespace_declaration()
        if (namespaceDeclaration != null) {
            if (namespaceDeclaration.qualified_identifier() != null) {
                val nsName = ctx.namespace_declaration().qualified_identifier().text
                currentNamespace = nsName
                val container = CodeContainer(
                    FullName = fileName,
                    PackageName = nsName
                )

                pushContainer(container)
            }
        }
    }

    private fun pushContainer(container: CodeContainer) {
        currentContainer = container

        val DEFAULT_CODE_CONTAINER = 1
        if (containerStack.elements.size > DEFAULT_CODE_CONTAINER) {
            val lastContainer = containerStack.elements.last()
            lastContainer.Containers += currentContainer
            containerStack.elements[containerStack.elements.size - 1] = lastContainer
            containerStack.push(currentContainer)
        } else {
            codeContainer.Containers += currentContainer
            containerStack.push(currentContainer)
        }
    }

    override fun exitNamespace_declaration(ctx: CSharpParser.Namespace_declarationContext?) {
        containerStack.pop()
    }

    override fun enterClass_definition(ctx: CSharpParser.Class_definitionContext?) {
        val className = ctx!!.identifier().text
        val codeDataStruct = CodeDataStruct(
            NodeName = className,
            Package = currentNamespace,
            Position = buildPosition(ctx)
        )

        currentStruct = codeDataStruct;
//
//        val classMemberDeclarations = ctx.class_body().class_member_declarations()
//        if (classMemberDeclarations != null) {
//            for (classMemberDecl in classMemberDeclarations.class_member_declaration()) {
//                this.handleClassMember(classMemberDecl)
//            }
//        }

        val parent = ctx.parent
        when (parent.javaClass.simpleName) {
            "Type_declarationContext" -> {
                val typeDecl = parent as CSharpParser.Type_declarationContext
                currentStruct.Annotations = parseAnnotations(typeDecl.attributes())
            }
        }
    }

    override fun exitClass_definition(ctx: CSharpParser.Class_definitionContext?) {
        currentContainer.DataStructures += currentStruct
    }


    private fun handleClassMember(memberCtx: CSharpParser.Class_member_declarationContext?) {
        val memberDeclaration = memberCtx!!.common_member_declaration() ?: return
        val firstChild = memberDeclaration.getChild(0) ?: return

        var returnType = "";
        when (firstChild::class.java.simpleName) {
            "TerminalNodeImpl" -> {
                returnType = firstChild.text
            }
        }

        val annotations = parseAnnotations(memberCtx.attributes())
        val modifiers = buildFunctionModifiers(memberCtx)

        if (memberDeclaration.typed_member_declaration() != null) {
            val typedMember = memberDeclaration.typed_member_declaration()

            val methodDeclaration = typedMember.method_declaration()
            if (methodDeclaration != null) {
                val typeContext = typedMember.type_()
                if (typeContext != null) {
                    returnType = typeContext.text
                }

                currentFunction = createFunction(
                    returnType,
                    annotations,
                    currentStruct.Package,
                    modifiers,
                    methodDeclaration.method_member_name().text,
                    methodDeclaration.formal_parameter_list()
                )
            }

            val propertyDecl = typedMember.property_declaration()
            if (propertyDecl != null) {
                val typeContext = typedMember.type_()
                if (typeContext != null) {
                    returnType = typeContext.text
                }

                currentFunction = createFunction(
                    returnType,
                    annotations,
                    currentStruct.Package,
                    modifiers,
                    propertyDecl.member_name().text,
                    null
                )
            }

        }

        if (memberDeclaration.constructor_declaration() != null) {
            val constructorDecl = memberDeclaration.constructor_declaration()

            currentFunction = createFunction(
                returnType,
                annotations,
                currentStruct.Package,
                modifiers,
                constructorDecl.identifier().text,
                constructorDecl.formal_parameter_list()
            )
        }

        val methodDecl = memberDeclaration.method_declaration()
        if (methodDecl != null) {
            currentFunction = createFunction(
                returnType,
                annotations,
                currentStruct.Package,
                modifiers,
                methodDecl.method_member_name().text,
                methodDecl.formal_parameter_list()
            )
        }
    }

    override fun enterConstructor_declaration(ctx: CSharpParser.Constructor_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    override fun exitConstructor_declaration(ctx: CSharpParser.Constructor_declarationContext?) {
        currentStruct.Functions += currentFunction
        currentFunction = CodeFunction()
    }

    override fun enterTyped_member_declaration(ctx: CSharpParser.Typed_member_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    override fun exitTyped_member_declaration(ctx: CSharpParser.Typed_member_declarationContext?) {
        if(currentFunction.Name.isNotEmpty()) {
            currentStruct.Functions += currentFunction
        }

        currentFunction = CodeFunction()
    }

    override fun enterMethod_declaration(ctx: CSharpParser.Method_declarationContext?) {
        buildForMethodDecl(ctx)
    }

    override fun exitMethod_declaration(ctx: CSharpParser.Method_declarationContext?) {
        currentStruct.Functions += currentFunction
        currentFunction = CodeFunction()
    }

    private fun buildForMethodDecl(ctx: ParserRuleContext?) {
        if (ctx == null) return

        var commonMemberForClass: Common_member_declarationContext? = null
        if (ctx.parent.javaClass.simpleName == "Common_member_declarationContext") {
            commonMemberForClass = ctx.parent as Common_member_declarationContext
        } else if (ctx.parent.parent.javaClass.simpleName == "Common_member_declarationContext") {
            commonMemberForClass = ctx.parent.parent as Common_member_declarationContext
        }

        if (commonMemberForClass == null) return

        if (commonMemberForClass.parent.javaClass.simpleName == "Class_member_declarationContext") {
            handleClassMember(commonMemberForClass.parent as CSharpParser.Class_member_declarationContext?)
        }
    }

    private fun parseAnnotations(attributes: CSharpParser.AttributesContext?): Array<CodeAnnotation> {
        var annotations = arrayOf<CodeAnnotation>();

        attributes?.attribute_section()?.forEach { it ->
            it.attribute_list().attribute().forEach { attr ->
                val annotation = CodeAnnotation(Name = attr.namespace_or_type_name().text)
                attr.attribute_argument().forEach {
                    if (it.string_literal() != null) {
                        annotation.KeyValues += AnnotationKeyValue(Value = parseString(it.string_literal()))
                    } else {
                        annotation.KeyValues += AnnotationKeyValue(Value = it.text)
                    }
                }

                annotations += annotation
            }
        }

        return annotations
    }

    private fun parseString(stringLiteral: CSharpParser.String_literalContext): String {
        var str = "";
        if (stringLiteral.REGULAR_STRING() != null) {
            val text = stringLiteral.text
            str = text.substring(1, text.length - 1)
        }

        return str
    }

    private fun createFunction(
        returnType: String,
        annotations: Array<CodeAnnotation>,
        packageName: String,
        modifiers: Array<String>,
        methodName: String,
        formalParameterListcontext: CSharpParser.Formal_parameter_listContext?
    ): CodeFunction {

        val codeFunction = CodeFunction(
            Package = packageName,
            Name = methodName,
            ReturnType = returnType,
            Modifiers = modifiers,
            Annotations = annotations
        )

        if (formalParameterListcontext != null) {
            codeFunction.Parameters = this.buildFunctionParameters(formalParameterListcontext)
        }
        return codeFunction
    }

    private fun buildFunctionParameters(formalParameterList: CSharpParser.Formal_parameter_listContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        if (formalParameterList!!.fixed_parameters() != null) {
            for (fixedParamCtx in formalParameterList.fixed_parameters().fixed_parameter()) {
                val argDeclCtx = fixedParamCtx.arg_declaration()
                if (argDeclCtx != null) {
                    val parameter = CodeProperty(
                        TypeType = argDeclCtx.type_().text,
                        TypeValue = argDeclCtx.identifier().text
                    )

                    parameters += parameter
                }
            }
        }

        return parameters
    }

    private fun buildFunctionModifiers(memberCtx: CSharpParser.Class_member_declarationContext): Array<String> {
        var modifiers: Array<String> = arrayOf()
        val allMemberModifiers = memberCtx.all_member_modifiers()
        if (allMemberModifiers != null) {
            for (memberModifier in allMemberModifiers.all_member_modifier()) {
                modifiers += memberModifier.text
            }
        }
        return modifiers
    }


    private fun buildPosition(ctx: ParserRuleContext): CodePosition {
        val position = CodePosition()
        position.StartLine = ctx.start.line
        position.StartLinePosition = ctx.start.charPositionInLine
        position.StopLine = ctx.stop.line
        position.StopLinePosition = ctx.stop.charPositionInLine
        return position
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }

}
