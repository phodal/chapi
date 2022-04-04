package chapi.app.analyser.support

import chapi.domain.core.CodeDataStruct

interface IAnalyser {
    fun analysisNodeByPath(path: String = ""): Array<CodeDataStruct>
}
