package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.typescriptast.TypeScriptAnalyser
import chapi.domain.core.CodeDataStruct

class TypeScriptAnalyserApp(config: ChapiConfig) : BaseAnalyser(config) {
    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> {
        var nodeInfos: Array<CodeDataStruct> = arrayOf()
        for (file in files) {
            val fileContent = readFileAsString(file.absolutePath)
            val codeFile = TypeScriptAnalyser().analysis(fileContent, file.fileName)
            for (dataStructure in codeFile.DataStructures) {
                dataStructure.Imports = codeFile.Imports
                nodeInfos += dataStructure
            }
        }

        return nodeInfos
    }
}
