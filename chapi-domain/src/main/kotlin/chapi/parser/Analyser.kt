package chapi.parser

import chapi.domain.core.CodeContainer
import chapi.domain.core.Since

enum class ParseMode {
    Basic, Full
}

// todo: add support for kotlin & java
interface Analyser {

    /**
     * Adds a source code to the program, for example, C header files.
     */
    @Since("2.3.4")
    fun addSource(code: String) {

    }

    fun analysis(code: String, filePath: String): CodeContainer
}

abstract class TwoStepAnalyser : Analyser {
    override fun analysis(code: String, filePath: String): CodeContainer {
        return this.analysis(code, filePath, ParseMode.Basic)
    }

    abstract fun analysis(code: String, filePath: String, parseMode: ParseMode = ParseMode.Basic): CodeContainer
}
