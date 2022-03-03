package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.IAnalyser
import chapi.domain.core.CodeDataStruct

open class ChapiAnalyser(
    var config: ChapiConfig = ChapiConfig()
) {

    open fun analysis(path: String): Array<CodeDataStruct> {
        val appAnalyser = getLangAppAnalyser()
        return appAnalyser.analysisNodeByPath(path)
    }

    open fun getLangAppAnalyser(): IAnalyser {
        val langAnalyser: IAnalyser
        when (config.language) {
            Language.JAVA -> {
                langAnalyser = JavaAnalyserApp(config)
            }
            "go" -> {
                langAnalyser = GoAnalyserApp(config)
            }
            "python" -> {
                langAnalyser = PythonAnalyserApp(config)
            }
            "typescript" -> {
                langAnalyser = TypeScriptAnalyserApp(config)
            }
            "scala" -> {
                langAnalyser = ScalaAnalyserApp(config)
            }
            Language.KOTLIN -> {
                langAnalyser = KotlinAnalyserApp(config)
            }
            Language.CSharp -> {
                langAnalyser = CSharpAnalyserApp(config)
            }
            else -> {
                langAnalyser = JavaAnalyserApp(config)
            }
        }

        return langAnalyser
    }
}
