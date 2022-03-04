package chapi.app.analyser

import chapi.domain.core.CodeDataStruct
import chapi.domain.core.CodeFunction
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

data class ApiModel(
    // todo: add code location information
    var CodeInfo: String = "",
    var Controller: String,
    var HttpMethod: String,
    var BaseUri: String,
    var RouteUri: String,
    var RequestModel: String = "",
    var ResponseModel: String = ""
)

internal class CSharpAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("e2e/csharp/")
        val path = Paths.get(resource!!.toURI()).toFile().absolutePath

        val models = mutableListOf<ApiModel>()
        val nodes = CSharpAnalyserApp().analysisNodeByPath(path)
        nodes.map { node ->
            val routeAnnotation = node.filterAnnotations("RoutePrefix", "Route")
            if (routeAnnotation.isNotEmpty()) {
                val baseUrl = routeAnnotation[0].KeyValues[0].Value
                node.Functions.forEach { createFunctionApi(it, baseUrl, models, node) }
            }
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
            println("$httpMethod : /$baseUrl/$route")
            models += ApiModel(
                Controller = node.NodeName,
                BaseUri = baseUrl,
                HttpMethod = httpMethod,
                RouteUri = route
            )
        }
    }
}
