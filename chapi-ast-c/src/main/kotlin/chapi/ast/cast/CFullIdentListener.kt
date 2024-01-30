package chapi.ast.cast

import chapi.ast.antlr.CParser
import chapi.domain.core.*

open class CFullIdentListener(fileName: String) : CAstBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private val defaultDataStruct = CodeDataStruct(NodeName = "default")
    private var currentFunction = CodeFunction()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterIncludeDeclaration(ctx: CParser.IncludeDeclarationContext?) {
        val identifier = ctx?.includeIdentifier()
        val imp = CodeImport(
            Source = identifier?.text ?: "",
            AsName = identifier?.text ?: ""
        )

        codeContainer.Imports += imp
    }


    override fun enterDeclaration(ctx: CParser.DeclarationContext?) {
        val isTypeDef = ctx?.declarationSpecifier()?.any {
            it.storageClassSpecifier()?.Typedef() != null
        } ?: false

        val maybeNodeName = ctx?.declarationSpecifier()?.filter {
            it.typeSpecifier()?.typedefName() != null
        }?.map {
            it.typeSpecifier()?.typedefName()?.text
        }?.firstOrNull()

        val structOrUnionSpecifier = ctx?.declarationSpecifier()?.filter {
            it.typeSpecifier()?.structOrUnionSpecifier() != null
        }?.map {
            it.typeSpecifier()?.structOrUnionSpecifier()
        }?.firstOrNull()

        structOrUnionSpecifier?.let {
            var nodeName = maybeNodeName ?: structOrUnionSpecifier.Identifier()?.text
            if (nodeName.isNullOrEmpty()) {
                nodeName = structOrUnionSpecifier?.structOrUnion()?.text ?: ""
            }

            handleStructOrUnion(structOrUnionSpecifier, nodeName ?: "")
        }
    }

    private fun handleStructOrUnion(ctx: CParser.StructOrUnionSpecifierContext?, nodeName: String) {
        structMap.getOrPut(nodeName) {
            CodeDataStruct(NodeName = nodeName, Position = buildPosition(ctx))
        }.let {
            currentDataStruct = it
        }

        ctx?.structDeclarationList()?.structDeclaration()?.forEach { structDeclCtx ->
            /// for forward struct declaration
            structDeclCtx.structDeclaratorList()?.let {
                val type = structDeclCtx.specifierQualifierList()?.typeSpecifier()?.let {
                    val specifier = it.structOrUnionSpecifier()
                    specifier?.structOrUnion()?.text + " " + specifier?.Identifier()?.text
                }
                val value = structDeclCtx.specifierQualifierList()?.specifierQualifierList()?.text ?: ""

                val field = CodeField(
                    TypeType = type ?: "",
                    TypeValue = value
                )

                currentDataStruct.Fields += field
                return@forEach
            }

            structDeclCtx.specifierQualifierList()?.let { qualifierList ->
                val field = CodeField(
                    TypeType = qualifierList.typeSpecifier()?.text ?: "",
                    TypeValue = qualifierList.specifierQualifierList()?.text ?: ""
                )

                currentDataStruct.Fields += field
            }
        }
    }

    private fun parseDirectDeclarator(ctx: CParser.DirectDeclaratorContext?) {
        when (ctx) {
            is CParser.ParameterDirectDeclaratorContext -> {
                handleParamDirectDeclCtx(ctx)
            }

            is CParser.IdentifierDirectDeclaratorContext -> {
                currentFunction.Name = ctx.Identifier().text
            }

            is CParser.DeclaratorDirectDeclaratorContext -> {}
            is CParser.AssignmentExpressionDirectDeclaratorContext -> {}
            is CParser.PreStaticAssignmentExpressionDirectDeclaratorContext -> {}
            is CParser.PostStaticAssignmentExpressionDirectDeclaratorContext -> {}
            is CParser.TypeQualifierListPointerDirectDeclaratorContext -> {}
            is CParser.IdentifierListDirectDeclaratorContext -> {}
            is CParser.BitFieldDirectDeclaratorContext -> {}
            is CParser.FunctionPointerDirectDeclaratorContext -> {
                handleFuncPointerDirectDeclCtx(ctx)
            }

            else -> {}
        }
    }

    private fun handleParamDirectDeclCtx(ctx: CParser.ParameterDirectDeclaratorContext) {
        parseDirectDeclarator(ctx.directDeclarator())

        currentFunction.Parameters = ctx.parameterTypeList().parameterList().parameterDeclaration().mapNotNull {
            val type = it.declarationSpecifier().firstOrNull()?.typeSpecifier()?.text

            val name = it.declarator()?.directDeclarator()?.let { directDeclarator ->
                when (directDeclarator) {
                    is CParser.IdentifierDirectDeclaratorContext -> {
                        directDeclarator.Identifier().text
                    }

                    else -> null
                }
            }

            val pointer = it.declarator()?.pointer()?.text ?: ""

            if (type != null && name != null) {
                CodeProperty(TypeValue = name, TypeType = type + pointer)
            } else {
                null
            }
        }.toMutableList()
    }

    private fun handleFuncPointerDirectDeclCtx(ctx: CParser.FunctionPointerDirectDeclaratorContext) {
        val type = ctx.typeSpecifier()?.text
        val name = when (val directDeclaratorType = ctx.directDeclarator()) {
            is CParser.IdentifierDirectDeclaratorContext -> {
                directDeclaratorType.Identifier().text
            }

            else -> null
        }

        if (name != null && type != null) {
            currentFunction.Parameters += CodeProperty(
                TypeValue = name,
                TypeType = "$type*"
            )
        }
    }

    override fun enterFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {
        currentFunction = CodeFunction(Position = buildPosition(ctx))
        ctx?.declarationSpecifier()?.map {
            if (it.typeSpecifier()?.text != null) {
                if (it.typeSpecifier()?.typedefName() != null) {
                    currentFunction.Name = it.typeSpecifier().typedefName().text
                } else {
                    currentFunction.ReturnType = it.typeSpecifier().text
                }
            }
        }

        parseDirectDeclarator(ctx?.declarator()?.directDeclarator())

        // handle for a pointer
        val firstParameter = currentFunction.Parameters.firstOrNull()
        if (firstParameter?.TypeType?.endsWith('*') == true) {
            val baseType = firstParameter.TypeType.removeSuffix("*")

            structMap.getOrPut(baseType) {
                CodeDataStruct(NodeName = baseType)
            }.let {
                it.Functions += currentFunction
            }
        } else {
            defaultDataStruct.Functions += currentFunction
        }
    }

    override fun exitFunctionDefinition(ctx: CParser.FunctionDefinitionContext?) {

    }

    override fun enterPostfixExpression(ctx: CParser.PostfixExpressionContext?) {
        val call = ctx?.postixCall() ?: return
        val functionName = ctx.primaryExpression()?.Identifier()?.firstOrNull()?.text ?: return

        val children = call.firstOrNull()?.children ?: return
        // function call children should be '(', some parameters?, ')', so the size should be at least 2
        if (children.size < 2) return

        val codeCall = CodeCall(
            FunctionName = functionName,
            Parameters = buildParameters(call),
            Position = buildPosition(ctx)
        )

        currentFunction.FunctionCalls += codeCall
    }

    private fun buildParameters(call: List<CParser.PostixCallContext>): List<CodeProperty> {
        return call.mapNotNull { callContext ->
            when(callContext) {
                is CParser.ArrayAccessPostfixExpressionContext -> {
                    null
                }
                is CParser.FunctionCallPostfixExpressionContext -> {
                    callContext.argumentExpressionList()?.assignmentExpression()?.map {
                        CodeProperty(
                            TypeType = it.text,
                            TypeValue = it.text
                        )
                    }?.toList() ?: listOf()
                }

                is CParser.MemberAccessPostfixExpressionContext -> {
                    null
                }

                else -> null
            }
        }.flatten()
    }

    fun getNodeInfo(): CodeContainer {
        if (defaultDataStruct.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultDataStruct
        }

        codeContainer.DataStructures += structMap.values
        return codeContainer
    }
}
