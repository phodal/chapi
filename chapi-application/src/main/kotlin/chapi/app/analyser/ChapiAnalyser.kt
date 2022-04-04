package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.IAnalyser
import chapi.domain.core.CodeDataStruct

open class ChapiAnalyser(
    var config: ChapiConfig = ChapiConfig()
) {

    open fun analysis(path: String): Array<CodeDataStruct> {
        val appAnalyser = getAnalyserByLang(config.language)
        return appAnalyser.analysisNodeByPath(path)
    }

    open fun getAnalyserByLang(lang: String): IAnalyser {
        val langAnalyser: IAnalyser
        when (lang) {
            Language.JAVA -> {
                langAnalyser = JavaAnalyserApp(config)
            }
            "go" -> {
                langAnalyser = GoAnalyserApp(config)
            }
            "python" -> {
                langAnalyser = PythonAnalyserApp(config)
            }
            Language.TypeScript -> {
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
