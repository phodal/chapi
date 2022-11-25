package chapi.ast.helper

import chapi.ast.javaast.JavaAnalyser
import org.junit.jupiter.api.Test

internal class TreeViewerTest {
    @Test
    fun show() {
        val javaAnalyser = JavaAnalyser()
        javaAnalyser.parse("public class Test { }").let {
            val ruleNamesList: List<String> = it.ruleNames.toList()
            println(TreeViewer().toPrettyTree(it.compilationUnit(), ruleNamesList))
        }
    }
}
