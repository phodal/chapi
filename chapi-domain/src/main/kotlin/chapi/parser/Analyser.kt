package chapi.parser

import chapi.domain.core.CodeContainer

enum class AnalysisMode {
    Basic, Full
}

// todo: add support for kotlin & java
interface Analyser {
    fun analysis(code: String, filePath: String): CodeContainer
}

abstract class TwoStepAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        return this.analysis(code, filePath, AnalysisMode.Basic)
    }

    abstract fun analysis(code: String, filePath: String, mode: AnalysisMode = AnalysisMode.Basic): CodeContainer
}
