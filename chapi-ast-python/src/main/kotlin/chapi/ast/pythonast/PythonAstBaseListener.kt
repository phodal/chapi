package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener
import domain.core.CodeProperty

open class PythonAstBaseListener : PythonParserBaseListener() {
    fun buildParameters(ctx: PythonParser.FuncdefContext): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (defParameter in ctx.typedargslist().def_parameters()) {
            val parameter = CodeProperty(
                TypeType = "",
                TypeValue = defParameter.text
            )
            parameters += parameter
        }
        return parameters
    }
}
