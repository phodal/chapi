package chapi.app.cli

import chapi.app.analyser.ChapiAnalyser
import chapi.app.analyser.TypeScriptAnalyserApp
import chapi.app.analyser.config.ChapiConfig
import chapi.app.frontend.ContainerService
import chapi.app.frontend.FrontendApiAnalyser
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Ast : CliktCommand() {
    val path: String by option(help="the path to analysis").default(".")
    val language: String by option(help="the path to analysis").default("java")

    override fun run() {
        val results = ChapiAnalyser(ChapiConfig(language)).analysis(path)
        File("chapi.json").writeText(Json.encodeToString(results))
    }
}

class Chapi: CliktCommand() {
    override fun run() = Unit
}


class Api : CliktCommand() {
    val path: String by option(help="the path to analysis").default(".")

    override fun run() {
        val nodes = TypeScriptAnalyserApp().analysisNodeByPath(path)
        val componentCalls: Array<ContainerService> = FrontendApiAnalyser().analysis(nodes, path)

        File("nodes.json").writeText(Json.encodeToString(nodes))
        File("api.json").writeText(Json.encodeToString(componentCalls))
    }
}

fun main(args: Array<String>) = Chapi()
    .subcommands(Ast(), Api())
    .main(args)
