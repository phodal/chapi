package chapi.ast.cast

import chapi.ast.antlr.CParser
import chapi.domain.core.*

open class CFullIdentListener(fileName: String) : CAstBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private val defaultDataStruct = CodeDataStruct()
    private var currentFunction = CodeFunction()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterIncludeDeclaration(ctx: CParser.IncludeDeclarationContext?) {
        val includeIdentifier = ctx?.includeIdentifier()
        val importName = includeIdentifier?.text ?: ""
        val imp = CodeImport(
            Source = importName,
            AsName = includeIdentifier?.Identifier()?.text ?: ""
        )

        codeContainer.Imports += imp
    }

    override fun enterStructOrUnionSpecifier(ctx: CParser.StructOrUnionSpecifierContext?) {
        val nodeName = ctx?.Identifier()?.text ?: ""
        structMap.getOrPut(nodeName) {
            CodeDataStruct(NodeName = nodeName, Position = buildPosition(ctx))
        }.let {
            currentDataStruct = it
        }

        ctx?.structDeclarationList()?.structDeclaration()?.let {
            val structDecl = it
            val specifierQualifierList = structDecl.specifierQualifierList()
            if (specifierQualifierList != null) {
                val key = specifierQualifierList.typeSpecifier().text
                val qualifierList = specifierQualifierList.specifierQualifierList()
                val field = CodeField(
                    TypeType = key,
                    TypeValue = qualifierList?.text ?: ""
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
                val directDeclarator = ctx as CParser.IdentifierDirectDeclaratorContext
                if (directDeclarator.Identifier().text != null) {
                    currentFunction.Name = directDeclarator.Identifier().text
                }
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
            val type = it.declarationSpecifiers().declarationSpecifier().firstOrNull()?.typeSpecifier()?.text

            val name = it.declarator().directDeclarator()?.let { directDeclarator ->
                when (directDeclarator) {
                    is CParser.IdentifierDirectDeclaratorContext -> {
                        directDeclarator.Identifier().text
                    }
                    else -> null
                }
            }

            if (type != null && name != null) {
                CodeProperty(TypeValue = name, TypeType = type)
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

        ctx?.declarationSpecifiers()?.declarationSpecifier()?.map {
            if (it.typeSpecifier().text != null) {
                if (it.typeSpecifier().typedefName() != null) {
                    currentFunction.Name = it.typeSpecifier().typedefName().text
                } else {
                    currentFunction.ReturnType = it.typeSpecifier().text
                }
            }
        }

        parseDirectDeclarator(ctx?.declarator()?.directDeclarator())

        if (currentFunction.Parameters.isEmpty()) return

        // handle for pointer
        val firstParameter = currentFunction.Parameters[0]
        if (firstParameter.TypeType.endsWith('*')) {
            val pointerIndex = firstParameter.TypeType.length - 1
            val baseType = firstParameter.TypeType.substring(0, pointerIndex)
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

    fun getNodeInfo(): CodeContainer {
        if (defaultDataStruct.Functions.isNotEmpty()) {
            codeContainer.DataStructures += defaultDataStruct
        }

        codeContainer.DataStructures += structMap.values
        return codeContainer
    }
}
