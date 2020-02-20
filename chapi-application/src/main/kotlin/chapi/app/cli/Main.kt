@file:JvmName("Main")
package chapi.app.cli

import chapi.app.analyser.ChapiAnalyser
import java.io.File

fun main(vararg args: String) {
    val results = ChapiAnalyser().analysis(".")
    File("chapi.json").writeText(results.toList().toString())
}
