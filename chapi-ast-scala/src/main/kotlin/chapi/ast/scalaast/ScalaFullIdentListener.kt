package chapi.ast.scalaast

import chapi.ast.antlr.ScalaParser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.DataStructType
import chapi.infra.Stack

class ScalaFullIdentListener(var fileName: String) : ScalaAstBaseListener() {
    private val structStack: Stack<CodeDataStruct> = Stack()
    private var currentDataStruct: CodeDataStruct = CodeDataStruct()
    private var codeContainer: CodeContainer = CodeContainer(FullName = fileName)

    override fun enterObjectDef(ctx: ScalaParser.ObjectDefContext?) {
        val objectName = ctx!!.Id().text
        val codeDataStruct = CodeDataStruct(
            NodeName = objectName,
            Type = DataStructType.OBJECT,
            FilePath = codeContainer.FullName
        )

        updateStruct(codeDataStruct)
    }

    private fun updateStruct(codeDataStruct: CodeDataStruct) {
        currentDataStruct = codeDataStruct

        if (structStack.elements.size > 0) {
            val lastStruct = structStack.elements.last()
            lastStruct.InnerStructures += currentDataStruct
        } else {
            codeContainer.DataStructures += currentDataStruct
        }

        structStack.push(currentDataStruct)
    }

    override fun enterClassDef(ctx: ScalaParser.ClassDefContext?) {
        val className = ctx!!.Id().text

        val codeDataStruct = CodeDataStruct(
            NodeName = className,
            Type = DataStructType.CLASS,
            FilePath = codeContainer.FullName
        )

        codeDataStruct.Parameters = this.buildParameters(ctx.classParamClauses())

        if (ctx.classTemplateOpt() != null) {
            if (ctx.classTemplateOpt().classTemplate() != null) {
                val extend = ctx.classTemplateOpt().classTemplate().text
                codeDataStruct.Extend = extend
            }
        }

        updateStruct(codeDataStruct)
    }

    fun getNodeInfo(): CodeContainer {
        return this.codeContainer
    }
}
