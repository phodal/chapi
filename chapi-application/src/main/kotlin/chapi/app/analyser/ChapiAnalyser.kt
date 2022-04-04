package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.IAnalyser
import chapi.domain.core.CodeDataStruct

open class ChapiAnalyser(var config: ChapiConfig = ChapiConfig()) {
    open fun analysis(path: String): Array<CodeDataStruct> {
        val appAnalyser = getAnalyserByLang(config.language)
        return appAnalyser.analysisNodeByPath(path)
    }

    open fun getAnalyserByLang(lang: String): IAnalyser {
        return when (lang) {
            Language.JAVA -> JavaAnalyserApp(config)
            Language.Golang -> GoAnalyserApp(config)
            Language.Python -> PythonAnalyserApp(config)
            Language.TypeScript -> TypeScriptAnalyserApp(config)
            Language.Scala -> ScalaAnalyserApp(config)
            Language.KOTLIN -> KotlinAnalyserApp(config)
            Language.CSharpStr,
            Language.CSharp -> CSharpAnalyserApp(config)
            else -> JavaAnalyserApp(config)
        }
    }
}
