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

    open fun analysisByFile(file: AbstractFile): List<CodeDataStruct> {
        println("processing file: ${file.absolutePath}")
        val codeContainer = analyser.analysis(file.content, file.fileName)

        return codeContainer.Containers.flatMap { container ->
            container.DataStructures.map {
                it.apply {
                    it.Imports = codeContainer.Imports
                    it.FilePath = file.absolutePath
                }
            }
        }.toList()
    }
}
