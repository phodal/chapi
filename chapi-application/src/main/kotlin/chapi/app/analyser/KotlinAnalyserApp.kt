package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.kotlinast.AnalysisMode
import chapi.ast.kotlinast.KotlinAnalyser
import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct

class KotlinAnalyserApp(config: ChapiConfig = ChapiConfig(language = Language.KOTLIN)) : BaseAnalyser(config) {
    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> {
        val analyser = KotlinAnalyser()

        return files
            .map { analyser.analysis(readFileAsString(it.absolutePath), it.fileName, AnalysisMode.Full) }
            .map(CodeContainer::DataStructures)
            .flatMap(Array<CodeDataStruct>::toList)
            .toTypedArray()
    }
}
