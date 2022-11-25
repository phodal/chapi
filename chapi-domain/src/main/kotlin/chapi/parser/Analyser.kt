package chapi.parser

import chapi.domain.core.CodeContainer

enum class ParseMode {
    Basic, Full
}

// todo: add support for kotlin & java
interface Analyser {
    fun analysis(code: String, filePath: String): CodeContainer
}

abstract class TwoStepAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        return this.analysis(code, filePath, ParseMode.Basic)
    }

    abstract fun analysis(code: String, filePath: String, parseMode: ParseMode = ParseMode.Basic): CodeContainer
}
