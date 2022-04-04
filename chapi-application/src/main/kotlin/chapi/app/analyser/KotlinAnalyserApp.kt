package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.kotlinast.AnalysisMode
import chapi.ast.kotlinast.KotlinAnalyser
import chapi.domain.core.CodeDataStruct

class KotlinAnalyserApp(config: ChapiConfig = ChapiConfig(language = Language.KOTLIN)) : BaseAnalyser(config) {
    private val analyser: KotlinAnalyser by lazy { KotlinAnalyser() }

    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> =
        files.flatMap(::analysisByFile).toTypedArray()

    override fun analysisByFile(file: AbstractFile): List<CodeDataStruct> {
        fun postProcess(it: CodeDataStruct): CodeDataStruct = it.apply { it.FilePath = file.absolutePath }

        val codeContainer = analyser.analysis(file.content, file.fileName, AnalysisMode.Full)

        return codeContainer.DataStructures.map(::postProcess)
    }
}
