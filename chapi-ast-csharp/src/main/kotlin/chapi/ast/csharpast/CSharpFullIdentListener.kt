package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.ast.antlr.CSharpParser.Class_typeContext
import chapi.ast.antlr.CSharpParser.Primary_expressionContext
import chapi.ast.antlr.CSharpParser.Type_declarationContext
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext

class CSharpFullIdentListener(fileName: String) : CSharpAstListener(fileName) {

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
        if (ctx.parent.javaClass.simpleName == "Common_member_declarationContext") {
            commonMemberForClass = ctx.parent as CSharpParser.Common_member_declarationContext
        } else if (ctx.parent.parent.javaClass.simpleName == "Common_member_declarationContext") {
            commonMemberForClass = ctx.parent.parent as CSharpParser.Common_member_declarationContext
        }

        if (commonMemberForClass == null) return

        if (commonMemberForClass.parent.javaClass.simpleName == "Class_member_declarationContext") {
            handleClassMember(commonMemberForClass.parent as CSharpParser.Class_member_declarationContext?)
        }
    }

    override fun enterProperty_declaration(ctx: CSharpParser.Property_declarationContext?) {
        val memberName = ctx!!.member_name()
        when (ctx.parent.javaClass.simpleName) {
            "Typed_member_declarationContext" -> {
                val typedMember = ctx.parent as CSharpParser.Typed_member_declarationContext
                val typeValue = memberName.text
                val typeContext = typedMember.type_()

                val field = createField(typeValue, typeContext)
                currentStruct.Fields += field
            }
        }
    }

    private fun createField(typeValue: String, typeContext: CSharpParser.Type_Context): CodeField {
        val field = CodeField(TypeValue = typeValue)
        field.TypeType = typeContext.text

        val child = typeContext.base_type().getChild(0)
        when (child.javaClass.simpleName) {
            "Class_typeContext" -> {
                val clazzType = child as Class_typeContext
                val nsOrType = clazzType.namespace_or_type_name()
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
        if (ctx == null) {
            return
        }
        val primaryExpr = ctx.parent as Primary_expressionContext

        var ident = ""
        var member = ""
        var params: List<CodeProperty> = listOf()
        primaryExpr.children.forEach {
            when (it.javaClass.simpleName) {
                // todo: merge to primary expression
                "SimpleNameExpressionContext" -> {
                    ident = (it as CSharpParser.SimpleNameExpressionContext).identifier().text
                }
                "Member_accessContext" -> {
                    member = (it as CSharpParser.Member_accessContext).identifier().text
                }
                "Method_invocationContext" -> {
                    // todo: parse parameters
                    val argumentList = (it as CSharpParser.Method_invocationContext).argument_list()
                    if (argumentList != null) {
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

//    //for debug only
//    override fun enterEveryRule(ctx: ParserRuleContext?) {
//        println(ctx!!.javaClass.simpleName)
//    }
}
