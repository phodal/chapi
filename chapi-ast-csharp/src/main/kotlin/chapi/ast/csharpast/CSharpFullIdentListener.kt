package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.domain.core.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class CSharpFullIdentListener(fileName: String) : CSharpAstListener(fileName) {
    private fun handleClassMember(memberCtx: CSharpParser.Class_member_declarationContext?) {
        val memberDeclaration = memberCtx!!.common_member_declaration() ?: return
        val firstChild = memberDeclaration.getChild(0) ?: return

        var returnType = "";
        when (firstChild) {
            is TerminalNodeImpl -> {
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
        if (currentFunction.Name.isNotEmpty()) {
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

        var commonMemberForClass: CSharpParser.Common_member_declarationContext? = null
        when (val parent = ctx.parent) {
            is CSharpParser.Common_member_declarationContext -> {
                commonMemberForClass = parent
            }

            is CSharpParser.Class_member_declarationContext -> {
                commonMemberForClass = parent.parent as CSharpParser.Common_member_declarationContext
            }
        }

        if (commonMemberForClass == null) return

        if (commonMemberForClass.parent is CSharpParser.Class_member_declarationContext) {
            handleClassMember(commonMemberForClass.parent as CSharpParser.Class_member_declarationContext?)
        }
    }

    override fun enterProperty_declaration(ctx: CSharpParser.Property_declarationContext?) {
        when (val parent = ctx?.parent) {
            is CSharpParser.Typed_member_declarationContext -> {
                val typeValue = ctx.member_name().text
                val typeContext = parent.type_()

                val field = createField(typeValue, typeContext)

                currentStruct.Fields += field
            }
        }
    }

    private fun createField(typeValue: String, typeContext: CSharpParser.Type_Context): CodeField {
        val field = CodeField(TypeValue = typeValue)
        field.TypeType = typeContext.text

        when (val child = typeContext.base_type().getChild(0)) {
            is CSharpParser.Class_typeContext -> {
                val nsOrType = child.namespace_or_type_name()
                if (nsOrType != null) {
                    field.Modifiers = listOf(nsOrType.identifier()[0].text)
                    if (nsOrType.type_argument_list() != null) {
                        nsOrType.type_argument_list()
                            .asSequence()
                            .flatMap { it.type_().asSequence() }
                            .forEach { field.TypeType = it.text }
                    }
                }
            }
        }

        return field
    }

    override fun enterField_declaration(ctx: CSharpParser.Field_declarationContext?) {
        println(ctx!!.text)
    }

    // call from method invocation parent will be easy to search for method call
    override fun enterMethod_invocation(ctx: CSharpParser.Method_invocationContext?) {
        val parent = ctx?.parent ?: return
        val primaryExpr = when (parent) {
            is CSharpParser.Primary_expressionContext -> {
                ctx.parent as CSharpParser.Primary_expressionContext
            }

            else -> {
                null
            }
        } ?: return

        var ident = ""
        var member = ""
        var params: List<CodeProperty> = listOf()
        primaryExpr.children.forEach {
            when (it) {
                // todo: merge to primary expression
                is CSharpParser.SimpleNameExpressionContext -> {
                    ident = it.identifier().text
                }

                is CSharpParser.Member_accessContext -> {
                    member = it.identifier().text
                }

                is CSharpParser.Method_invocationContext -> {
                    // todo: parse parameters
                    it.argument_list()?.let { argumentList ->
                        params = parseParameters(argumentList)
                    }
                }

                else -> {
                    println(it.javaClass.simpleName)
                }
            }
        }

        val codeCall = CodeCall(Package = "", NodeName = ident, FunctionName = member, Parameters = params)
        currentFunction.FunctionCalls += codeCall
    }

    private fun parseParameters(argumentList: CSharpParser.Argument_listContext): List<CodeProperty> {
        return argumentList.argument().map {
            CodeProperty(
                TypeType = it.refout?.text ?: "",
                TypeValue = it.expression().text
            )
        }
    }
}
