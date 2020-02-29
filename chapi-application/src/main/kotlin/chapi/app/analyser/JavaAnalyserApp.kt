package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.javaast.JavaAnalyser
import chapi.domain.core.CodeDataStruct

open class JavaAnalyserApp(var config: ChapiConfig = ChapiConfig(language = "java")) : BaseAnalyser(config) {
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

        var classes: Array<String> = arrayOf()
        for (basicNode in basicNodes) {
            classes += basicNode.getClassFullName()
        }

        var nodeInfos: Array<CodeDataStruct> = buildNodeInfos(files, classes, basicNodes)

        return nodeInfos
    }

    private fun buildNodeInfos(
        files: Array<AbstractFile>,
        classes: Array<String>,
        basicNodes: Array<CodeDataStruct>
    ): Array<CodeDataStruct> {
        var nodeInfos: Array<CodeDataStruct> = arrayOf()
        for (file in files) {
            val fileContent = readFileAsString(file.absolutePath)
            val codeFile = JavaAnalyser().identFullInfo(fileContent, file.fileName, classes, basicNodes)
            for (dataStructure in codeFile.DataStructures) {
                dataStructure.Imports = codeFile.Imports
                nodeInfos += dataStructure
            }
        }
        return nodeInfos
    }

    private fun analysisBasicInfo(files: Array<AbstractFile>): Array<CodeDataStruct> {
        var nodeInfos: Array<CodeDataStruct> = arrayOf()
        for (file in files) {
            val fileContent = readFileAsString(file.absolutePath)
            val codeFile = JavaAnalyser().identBasicInfo(fileContent, file.fileName)
            for (dataStructure in codeFile.DataStructures) {
                dataStructure.Imports = codeFile.Imports
                nodeInfos += dataStructure
            }
        }

        return nodeInfos
    }
}
