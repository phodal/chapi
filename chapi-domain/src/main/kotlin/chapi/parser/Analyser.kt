package chapi.parser

import chapi.domain.core.CodeContainer
import chapi.domain.core.Since

enum class ParseMode {
    Basic, Full
}

/**
 * Analyser is a parser for a specific language.
 * For normal use, you can use the default implementation of the language.
 *
 * For C, it's a complex example:
 * ```kotlin
 * runBlocking {
 *      val analyser = CAnalyser()
 *      val fileFlow = File(content).walkTopDown().asFlow()
 *      fileFlow.mapNotNull {
 *          if (it.isFile && (it.extension == "h")) {
 *              analyser.addSource(it.readText())
 *          }
 *      }.collect()
 *
 *      val result: MutableList<CodeContainer> = mutableListOf()
 *      fileFlow.mapNotNull {
 *          if (it.isFile && (it.extension == "c")) {
 *              val start = System.currentTimeMillis()
 *              val analysis = analyser.analysis(it.readText(), it.name)
 *              val end = System.currentTimeMillis()
 *              result += analysis
 *          } else {
 *              null
 *          }
 *      }.collect()
 *
 *      File("result.json").writeText(Json.encodeToString(result))
 * }
 *```
 *
 */
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
