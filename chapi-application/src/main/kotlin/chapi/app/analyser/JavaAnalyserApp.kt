package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.javaast.JavaAnalyser
import chapi.domain.core.CodeDataStruct
import chapi.domain.core.DataStructType

open class JavaAnalyserApp(var config: ChapiConfig = ChapiConfig(language = Language.JAVA)) : BaseAnalyser(config) {
    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> {
        val basicNodes = analysisBasicInfo(files)
        return analysisFullNodes(files, basicNodes)
    }

    private fun analysisFullNodes(
        files: Array<AbstractFile>,
        basicNodes: Array<CodeDataStruct>
    ): Array<CodeDataStruct> {
        val identMap = HashMap<String, CodeDataStruct>()
        for (node in basicNodes) {
            identMap[node.getClassFullName()] = node
        }

        val classes = basicNodes.map { it.getClassFullName() }.toTypedArray()

        return buildNodeInfos(files, classes, basicNodes)
    }

    private fun buildNodeInfos(
        files: Array<AbstractFile>,
        classes: Array<String>,
        basicNodes: Array<CodeDataStruct>
    ): Array<CodeDataStruct> {
        return files.flatMap {
            val codeContainer = JavaAnalyser().identFullInfo(readFileAsString(it.absolutePath), it.fileName, classes, basicNodes)
            codeContainer.DataStructures.map { ds -> ds.apply { ds.Imports = codeContainer.Imports } }
        }.toTypedArray()
    }

    private fun analysisBasicInfo(files: Array<AbstractFile>): Array<CodeDataStruct> {
        return files.flatMap {
            val codeContainer = JavaAnalyser().identBasicInfo(readFileAsString(it.absolutePath), it.fileName)
            codeContainer.DataStructures.map { ds -> ds.apply { ds.Imports = codeContainer.Imports } }
        }.toTypedArray()
    }
}
