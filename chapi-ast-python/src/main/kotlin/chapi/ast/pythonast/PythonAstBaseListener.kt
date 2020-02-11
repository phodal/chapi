package chapi.ast.pythonast

import chapi.ast.antlr.PythonParser
import chapi.ast.antlr.PythonParserBaseListener
import domain.core.CodeProperty

open class PythonAstBaseListener : PythonParserBaseListener() {
    fun buildParameters(listCtx: PythonParser.TypedargslistContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (defParameters in listCtx!!.def_parameters()) {
            for (defParaCtx in defParameters.def_parameter()) {
                val parameter = CodeProperty(
                    TypeType = "",
                    TypeValue = defParaCtx.text
                )
                parameters += parameter
            }
        }

        return parameters
    }
}
