package chapi.ast.cast

import chapi.ast.antlr.CBaseListener
import chapi.ast.antlr.CParser
import chapi.domain.core.*

open class CFullIdentListener(fileName: String) : CAstBaseListener() {
    private var currentDataStruct = CodeDataStruct()
    private val defaultDataStruct = CodeDataStruct()
    private var currentFunction = CodeFunction()
    private var structMap = mutableMapOf<String, CodeDataStruct>()
    private var codeContainer: CodeContainer =
        CodeContainer(FullName = fileName)


    override fun enterIncludeDeclaration(ctx: CParser.IncludeDeclarationContext?) {
        val importName = ctx!!.includeIdentifier().text
        val imp = CodeImport(
            Source = importName
        )
        codeContainer.Imports += imp
    }

    override fun enterStructOrUnionSpecifier(ctx: CParser.StructOrUnionSpecifierContext?) {
        val codeDataStruct = CodeDataStruct()
        if (ctx!!.Identifier() != null) {
            codeDataStruct.NodeName = ctx.Identifier().text
        }

        currentDataStruct = codeDataStruct

        if (ctx.structDeclarationList() != null) {
            val structDecl = ctx.structDeclarationList().structDeclaration()
            val specifierQualifierList = structDecl.specifierQualifierList()
            if (specifierQualifierList != null) {
                val key = specifierQualifierList.typeSpecifier().text
                val qualifierList = specifierQualifierList.specifierQualifierList()
                var value = ""
                if (qualifierList != null) {
                    value = qualifierList.text
                }

                val field = CodeField(
                    TypeType = key,
                    TypeValue = value
                )
                codeDataStruct.Fields += field
            }

        }
        structMap[codeDataStruct.NodeName] = codeDataStruct
        codeContainer.DataStructures += codeDataStruct
    }

    fun parseDirectDeclarator(ctx: CParser.DirectDeclaratorContext) {
        val directDeclaratorType = ctx::class.java.simpleName
        when(directDeclaratorType) {
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
        currentFunction.Parameters = arrayOf<CodeProperty>()
        val directDeclarator = ctx as CParser.ParameterDirectDeclaratorContext
        parseDirectDeclarator(ctx.directDeclarator())
        val parameterTypeList = directDeclarator.parameterTypeList().parameterList()
        var parameters: MutableList<CParser.ParameterDeclarationContext> = ArrayList()
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
        currentFunction.Parameters = arrayOf<CodeProperty>()
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
        val codeFunction = CodeFunction()
        currentFunction = codeFunction
        if (ctx!!.declarationSpecifiers() != null) {
            for (specifier in ctx.declarationSpecifiers().declarationSpecifier()) {
                if (specifier.typeSpecifier().text != null) {
                    if (specifier.typeSpecifier().typedefName() != null) {
                        codeFunction.Name = specifier.typeSpecifier().typedefName().text
                    } else {
                        codeFunction.ReturnType = specifier.typeSpecifier().text
                    }
                }

            }
        }

        parseDirectDeclarator(ctx.declarator().directDeclarator())
        if (currentFunction.Parameters.isNotEmpty()) {
            val firstParameter = currentFunction.Parameters[0]
            if(firstParameter.TypeType.endsWith('*')) {
                val pointerIndex = firstParameter.TypeType.length - 1
                val baseType = firstParameter.TypeType.substring(0, pointerIndex)
                if (structMap.get(baseType) != null) {
                    structMap[baseType]!!.Functions += currentFunction
                } else {
                    defaultDataStruct.Functions += currentFunction
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
        return codeContainer
    }
}
