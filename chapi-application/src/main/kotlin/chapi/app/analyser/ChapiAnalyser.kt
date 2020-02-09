package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.filter.FileFilter
import chapi.ast.javaast.JavaAnalyser
import domain.core.CodeDataStruct
import java.io.BufferedReader
import java.io.File

open class ChapiAnalyser(
    var config: ChapiConfig = ChapiConfig()
) {
    open fun analysisByPath(path: String = ""): Array<CodeDataStruct> {
        val files = getFilesByPath(path)
        return analysisByFiles(files)
    }

    open fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> {
        return analysisBasicInfo(files)
    }

    private fun analysisBasicInfo(files: Array<AbstractFile>): Array<CodeDataStruct> {
        var nodeInfos: Array<CodeDataStruct> = arrayOf()
        for (file in files) {
            val fileContent = readFileAsString(file.absolutePath)
            val codeFile = JavaAnalyser().identBasicInfo(fileContent, file.fileName)
            for (dataStructure in codeFile.DataStructures) {
                nodeInfos += dataStructure
            }
        }

        return nodeInfos
    }

    private fun readFileAsString(filepath: String): String {
        val bufferedReader: BufferedReader = File(filepath).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    private fun getFilesByPath(path: String): Array<AbstractFile> {
        var files: Array<AbstractFile> = arrayOf()
        for (file in File(path).walk()) {
            if (FileFilter.javaFileFilter(file.name)) {
                files += AbstractFile.toAbstractFile(file)
            }
        }
        return files
    }
}
