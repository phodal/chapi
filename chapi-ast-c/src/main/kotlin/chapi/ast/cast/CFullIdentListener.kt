package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
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
        val directDeclaratorType = ctx!!::class.java.simpleName
        when (directDeclaratorType) {
            "ParameterDirectDeclaratorContext" -> {
                handleParamDirectDeclCtx(ctx)
            }

            "IdentifierDirectDeclaratorContext" -> {
                val directDeclarator = ctx as CParser.IdentifierDirectDeclaratorContext
                if (directDeclarator.Identifier().text != null) {
                    currentFunction.Name = directDeclarator.Identifier().text
                }
            }

            "DeclaratorDirectDeclaratorContext" -> {}
            "AssignmentExpressionDirectDeclaratorContext" -> {}
            "PreStaticAssignmentExpressionDirectDeclaratorContext" -> {}
            "PostStaticAssignmentExpressionDirectDeclaratorContext" -> {}
            "TypeQualifierListPointerDirectDeclaratorContext" -> {}
            "IdentifierListDirectDeclaratorContext" -> {}
            "BitFieldDirectDeclaratorContext" -> {}
            "FunctionPointerDirectDeclaratorContext" -> {
                handleFuncPointerDirectDeclCtx(ctx)
            }
        }
    }

    private fun handleParamDirectDeclCtx(ctx: CParser.DirectDeclaratorContext) {
        currentFunction.Parameters = listOf()
        val directDeclarator = ctx as CParser.ParameterDirectDeclaratorContext
        parseDirectDeclarator(ctx.directDeclarator())

        val parameterTypeList = directDeclarator.parameterTypeList().parameterList()
        val parameters: MutableList<CParser.ParameterDeclarationContext> = ArrayList()
        this.buildParameters(parameterTypeList, parameters)

        for (parameter in parameters) {
            var type: String? = null
            var name: String? = null
            for (specifier in parameter.declarationSpecifiers().declarationSpecifier()) {
                if (specifier.typeSpecifier().text != null) {
                    type = specifier.typeSpecifier().text
                }
            }
            val declarator = parameter.declarator().directDeclarator() as CParser.IdentifierDirectDeclaratorContext
            if (declarator.Identifier().text != null) {
                name = declarator.Identifier().text
            }
            if (type != null && name != null) {
                val codeParameter = CodeProperty(
                    TypeValue = name,
                    TypeType = type
                )
                currentFunction.Parameters += codeParameter
            }
        }
    }

    private fun handleFuncPointerDirectDeclCtx(ctx: CParser.DirectDeclaratorContext) {
        currentFunction.Parameters = listOf()
        var name: String? = null
        var type: String? = null
        val directDeclarator = ctx as CParser.FunctionPointerDirectDeclaratorContext
        if (directDeclarator.typeSpecifier() != null) {
            type = directDeclarator.typeSpecifier().text
        }
        val directDeclaratorType = directDeclarator.directDeclarator()::class.java.simpleName
        if (directDeclaratorType == "IdentifierDirectDeclaratorContext") {
            name = (directDeclarator.directDeclarator() as CParser.IdentifierDirectDeclaratorContext).Identifier().text
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
        if (currentFunction.Parameters.isNotEmpty()) {
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
