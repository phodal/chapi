package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.domain.core.*
import chapi.domain.infra.Stack

class CSharpFullIdentListener(val fileName: String) : CSharpAstListener() {
    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)

    private var currentContainer: CodeContainer = codeContainer
    private var containerStack: Stack<CodeContainer> =
        Stack<CodeContainer>()

    private var currentPackage: CodePackage =
        CodePackage()

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
                println(usingType)
            }
        }

        codeContainer.Imports += codeImport
    }

    override fun enterNamespace_member_declaration(ctx: CSharpParser.Namespace_member_declarationContext?) {
        val namespaceDeclaration = ctx!!.namespace_declaration()
        if (namespaceDeclaration != null) {
            if (namespaceDeclaration.qualified_identifier() != null) {
                val nsName = ctx.namespace_declaration().qualified_identifier().text
                val container = CodeContainer(
                    FullName = fileName,
                    PackageName = nsName
                )

                currentContainer = container

                val lastContainer = containerStack.elements.last()
                lastContainer.Containers += currentContainer

                containerStack.push(currentContainer)
            }
        }
    }

    override fun exitNamespace_member_declaration(ctx: CSharpParser.Namespace_member_declarationContext?) {

    }

    override fun enterClass_definition(ctx: CSharpParser.Class_definitionContext?) {
        val className = ctx!!.identifier().text
        val codeDataStruct = CodeDataStruct(
            NodeName = className
        )

        val classMemberDeclarations = ctx.class_body().class_member_declarations()
        if (classMemberDeclarations != null) {
            for (classMemberDeclarationcontext in classMemberDeclarations.class_member_declaration()) {
                this.handleClassMember(classMemberDeclarationcontext, codeDataStruct)
            }
        }

        currentContainer.DataStructures += codeDataStruct
    }

    private fun handleClassMember(
        memberCtx: CSharpParser.Class_member_declarationContext?,
        codeDataStruct: CodeDataStruct
    ) {
        val memberDeclaration = memberCtx!!.common_member_declaration()
        if (memberDeclaration != null) {
            val memberType = memberDeclaration.getChild(0)::class.java.simpleName
            when (memberType) {
                "TerminalNodeImpl" -> {
                    val methodDeclaration = memberDeclaration.method_declaration()
                    val methodName = methodDeclaration.method_member_name()

                    val codeFunction = CodeFunction(
                        Package = codeDataStruct.Package,
                        Name = methodName.text,
                        Modifiers = buildFunctionModifiers(memberCtx)
                    )

                    val formalParameterList = methodDeclaration.formal_parameter_list()
                    if (formalParameterList != null) {
                        codeFunction.Parameters = this.buildFunctionParameters(formalParameterList)
                    }

                    codeDataStruct.Functions += codeFunction
                }
                else -> {
                    println(memberType)
                }
            }
        }
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
            for (allMemberModifiercontext in allMemberModifiers.all_member_modifier()) {
                modifiers += allMemberModifiercontext.text
            }
        }
        return modifiers
    }

    fun getNodeInfo(): CodeContainer {
        return codeContainer
    }
}
