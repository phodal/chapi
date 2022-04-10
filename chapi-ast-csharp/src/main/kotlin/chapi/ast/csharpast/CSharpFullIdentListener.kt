package chapi.ast.csharpast

import chapi.ast.antlr.CSharpParser
import chapi.ast.antlr.CSharpParser.Class_typeContext
import chapi.ast.antlr.CSharpParser.Type_declarationContext
import chapi.domain.core.*
import chapi.infra.Stack
import org.antlr.v4.runtime.ParserRuleContext

class CSharpFullIdentListener(fileName: String) : CSharpAstListener(fileName) {
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
                    field.Modifiers = arrayOf(nsOrType.identifier()[0].text)
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
}
