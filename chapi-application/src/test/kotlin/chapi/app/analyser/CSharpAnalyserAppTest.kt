package chapi.app.analyser

import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

data class ApiModel(
    // todo: add code location information
    var CodeInfo: String = "",
    var SourceNode: String,
    var HttpMethod: String,
    var BaseUri: String,
    var RouteUri: String,
    var Request: String = "",
    var ResponseModel: String = ""
)

internal class CSharpAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("e2e/csharp/")
        val path = Paths.get(resource!!.toURI()).toFile().absolutePath

        val models = mutableListOf<ApiModel>()
        val nodes = CSharpAnalyserApp().analysisNodeByPath(path)

        println("|  Controller   | Method     | URI          | RequestModel     | ReturnModel       |")
        println("|---------------|------------|--------------|------------------|-------------------|")
        nodes.map { node ->
            val routeAnnotation = node.filterAnnotations("RoutePrefix", "Route")
            if (routeAnnotation.isNotEmpty()) {
                val baseUrl = routeAnnotation[0].KeyValues[0].Value
                node.Functions.forEach { createFunctionApi(it, baseUrl, models, node) }
            }
        }

        models.forEach {
            println("| ${it.SourceNode} | ${it.HttpMethod} | /${it.BaseUri}/${it.RouteUri} | ${it.Request} | ${it.ResponseModel}  |")
        }

        assertEquals(0, models.size);
    }

    private fun createFunctionApi(
        func: CodeFunction,
        baseUrl: String,
        models: MutableList<ApiModel>,
        node: CodeDataStruct
    ) {
        var httpMethod = "";
        var route = "";
        for (annotation in func.Annotations) {
            when (annotation.Name) {
                "HttpGet" -> httpMethod = "Get    "
                "HttpPost" -> httpMethod = "Post   "
                "HttpDelete" -> httpMethod = "Delete "
                "HttpPut" -> httpMethod = "Put    "
            }
            if (annotation.Name == "Route") {
                route = annotation.KeyValues[0].Value
            }
        }

        if (route.isNotEmpty() && httpMethod.isNotEmpty()) {
            val params = func.Parameters.map {
                it.TypeType + ":" + it.TypeValue
            }.toList().joinToString(",")

            models += ApiModel(
                SourceNode = node.NodeName,
                BaseUri = baseUrl,
                HttpMethod = httpMethod,
                RouteUri = route,
                Request = params
            )
        }
    }
}
