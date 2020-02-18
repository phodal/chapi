package chapi.app.analyser.support

import chapi.domain.core.CodeDataStruct

interface IAnalyser {
    open fun analysisNodeByPath(path: String = ""): Array<CodeDataStruct>
//    open fun analysisProject(path: String = ""): Array<CodeDataStruct>
}
