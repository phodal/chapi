package chapi.app.analyser.support

import chapi.app.analyser.support.AbstractFile
import chapi.domain.core.CodeDataStruct

interface IAnalyser {
    open fun analysisByPath(path: String = ""): Array<CodeDataStruct>
    open fun analysisByFiles(files: Array<AbstractFile>): Array<CodeDataStruct>
}
