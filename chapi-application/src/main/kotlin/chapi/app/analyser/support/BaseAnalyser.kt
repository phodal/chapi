package chapi.app.analyser.support

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.filter.FileFilter
import chapi.domain.core.CodeDataStruct
import java.io.BufferedReader
import java.io.File
import kotlin.streams.asStream

abstract class BaseAnalyser(private var config: ChapiConfig) {
    abstract fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct>
    abstract fun analysisByFile(file: AbstractFile): List<CodeDataStruct>

    fun analysisNodeByPath(path: String): Array<CodeDataStruct> {
        val files = getFilesByPath(path)
        return this.analysisByFiles(files)
    }

    fun readFileAsString(filepath: String): String {
        val bufferedReader: BufferedReader = File(filepath).bufferedReader()
        var text = bufferedReader.use { it.readText() }

        // fix for Window issue
        if (text.startsWith("\uFEFF")) {
            text = text.substring(1);
        }
        return text
    }

    private fun getFilesByPath(path: String): Array<AbstractFile> {
        val workspace = File(path)
        return workspace.walk().asStream().parallel()
            .filter {
                FileFilter.filterByLanguage(it.absolutePath, config)
            }
            .map {
                val abstractFile = AbstractFile.toAbstractFile(it, workspace)
                abstractFile.content = readFileAsString(it.path)
                abstractFile
            }
            .toArray { length -> arrayOfNulls(length) }
    }

}
