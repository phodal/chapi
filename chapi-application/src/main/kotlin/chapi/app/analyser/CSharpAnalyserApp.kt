package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.csharpast.CSharpAnalyser
import chapi.domain.core.CodeDataStruct

open class CSharpAnalyserApp(var config: ChapiConfig = ChapiConfig(language = Language.CSharp)) : BaseAnalyser(config) {
    private val analyser: CSharpAnalyser by lazy { CSharpAnalyser() }

    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> =
        files.flatMap(::analysisByFile).toTypedArray()

    private fun analysisByFile(file: AbstractFile): List<CodeDataStruct> {
        fun postProcess(it: CodeDataStruct): CodeDataStruct = it.apply { it.FilePath = file.absolutePath }

        val code = readFileAsString(file.absolutePath)
        val codeContainer = analyser.analysis(code, file.fileName)

        println(codeContainer.FullName)
        println(codeContainer.DataStructures.size)
        return codeContainer.DataStructures.map(::postProcess)
    }
}
