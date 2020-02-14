package chapi.ast.scalaast

import chapi.ast.antlr.ScalaBaseListener
import chapi.ast.antlr.ScalaParser
import chapi.domain.core.CodeProperty

open class ScalaAstBaseListener : ScalaBaseListener() {
    fun buildParameters(classParamClauses: ScalaParser.ClassParamClausesContext?): Array<CodeProperty> {
        var parameters : Array<CodeProperty> = arrayOf()
        if (classParamClauses != null) {
            for (paramClauseContext in classParamClauses.classParamClause()) {
                for (classParamContext in paramClauseContext.classParams().classParam()) {
                    parameters += buildParameter(classParamContext)
                }
            }
        }

        return parameters
    }

    private fun buildParameter(classParamContext: ScalaParser.ClassParamContext): CodeProperty {
        val parameter = CodeProperty(
            TypeType = classParamContext.paramType().text,
            TypeValue = classParamContext.Id().text
        )

        return parameter
    }

}
