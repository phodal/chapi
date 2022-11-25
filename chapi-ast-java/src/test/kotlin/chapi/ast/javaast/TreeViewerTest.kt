package chapi.ast.javaast

import org.junit.jupiter.api.Test

internal class TreeViewerTest {
    @Test
    fun show() {
        JavaAnalyser().parse("public class Test { }").let {
            val ruleNamesList: List<String> = it.ruleNames.toList()
            println(TreeViewer().toPrettyTree(it.compilationUnit(), ruleNamesList))
        }
    }
}
