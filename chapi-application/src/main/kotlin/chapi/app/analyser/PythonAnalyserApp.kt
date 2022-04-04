package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.goast.GoAnalyser
import chapi.ast.pythonast.PythonAnalyser
import chapi.domain.core.CodeDataStruct

class PythonAnalyserApp(config: ChapiConfig) : BaseAnalyser(config) {
    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> {
        return files.flatMap(::analysisByFile).toTypedArray()
    }

    override fun analysisByFile(file: AbstractFile): List<CodeDataStruct> {
        val codeFile = PythonAnalyser().analysis(file.content, file.fileName)

        return codeFile.DataStructures.map {
            it.apply {
                it.Imports = codeFile.Imports
                it.FilePath = file.absolutePath
            }
        }.toList()
    }
}
