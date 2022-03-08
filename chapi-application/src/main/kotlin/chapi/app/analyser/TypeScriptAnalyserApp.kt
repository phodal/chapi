package chapi.app.analyser

import chapi.app.analyser.config.ChapiConfig
import chapi.app.analyser.config.Language
import chapi.app.analyser.support.AbstractFile
import chapi.app.analyser.support.BaseAnalyser
import chapi.ast.typescriptast.TypeScriptAnalyser
import chapi.domain.core.CodeDataStruct

class TypeScriptAnalyserApp(config: ChapiConfig = ChapiConfig(language = Language.TypeScript)) : BaseAnalyser(config) {
    private val analyser: TypeScriptAnalyser by lazy { TypeScriptAnalyser() }
    override fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct> =
        files.flatMap(::analysisByFile).toTypedArray()

    private fun analysisByFile(file: AbstractFile): List<CodeDataStruct> {
        val codeContainer = analyser.analysis(readFileAsString(file.absolutePath), file.fileName)
        return codeContainer.DataStructures.map { it.apply { it.Imports = codeContainer.Imports } }
    }
}
