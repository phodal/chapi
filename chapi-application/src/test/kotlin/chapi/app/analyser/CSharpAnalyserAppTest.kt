package chapi.app.analyser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class CSharpAnalyserAppTest {
    @Test
    fun shouldIdentifySamePackage() {
        val resource = this.javaClass.classLoader.getResource("e2e/csharp/")
        val path = Paths.get(resource!!.toURI()).toFile().absolutePath

        val nodes = CSharpAnalyserApp().analysisNodeByPath(path)

        nodes.forEach { node ->
            val routeAnnotation = node.filterAnnotation("RoutePrefix")
            if (routeAnnotation.isNotEmpty()) {
                val baseUrl = routeAnnotation[0].KeyValues[0].Value
                node.Functions.forEach { func ->
                    var httpMethod = "";
                    var route = "";
                    for (annotation in func.Annotations) {
                        when (annotation.Name) {
                            "HttpGet" ->    httpMethod = "Get    "
                            "HttpPost" ->   httpMethod = "Post   "
                            "HttpDelete" -> httpMethod = "Delete "
                            "HttpPut" ->    httpMethod = "Put    "
                        }
                        if (annotation.Name == "Route") {
                            route = annotation.KeyValues[0].Value
                        }
                    }

                    if (route.isNotEmpty() && httpMethod.isNotEmpty()) {
                        println("$httpMethod : /$baseUrl/$route")
                    }
                }
            }
        }
    }
}
