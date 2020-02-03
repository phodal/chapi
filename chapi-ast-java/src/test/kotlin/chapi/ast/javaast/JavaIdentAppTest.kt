package chapi.ast.javaast

import org.junit.jupiter.api.Test

internal class JavaIdentAppTest {
    @Test
    internal fun shouldAnalysis() {
        val code = """
package chapi.ast.javaast;

import org.junit.Test;
"""
        JavaIdentApp().analysis(code)
    }
}
