package chapi.app.analyser.support

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.filter.FileFilter
import chapi.domain.core.CodeDataStruct
import java.io.BufferedReader
import java.io.File


abstract class BaseAnalyser(private var config: ChapiConfig) : IAnalyser {
    override fun analysisNodeByPath(path: String): Array<CodeDataStruct> {
        val files = getFilesByPath(path)
        return this.analysisByFiles(files)
    }

    fun readFileAsString(filepath: String): String {
        val bufferedReader: BufferedReader = File(filepath).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    abstract fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct>

    private fun getFilesByPath(path: String): Array<AbstractFile> {
        var files: Array<AbstractFile> = arrayOf()
        for (file in File(path).walk()) {
            val isCorrectLanguage = FileFilter.filterByLanguage(file.absolutePath, config.language)
            if (isCorrectLanguage) {
                files += AbstractFile.toAbstractFile(file)
            }
        }

        return files
    }

}
