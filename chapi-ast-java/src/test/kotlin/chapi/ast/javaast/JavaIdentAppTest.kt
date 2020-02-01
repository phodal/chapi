package chapi.ast.javaast

import org.junit.Test

internal class JavaIdentAppTest {
    @Test
    internal fun shouldAnalysis() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        JavaIdentApp().Analysis(code)
    }
}
