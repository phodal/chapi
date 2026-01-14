package chapi.ast.goast

import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

/**
 * Tests for new Go number literal syntax (binary literals, underscore separators)
 */
internal class GoNumberLiteralsTest {

    @Test
    fun `should parse binary literals`() {
        @Language("Go")
        val code = """
package main

func main() {
    bin := 0b1010
    BIN := 0B1111_0000
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" }, 
            "main function should exist. Parsing binary literals should not fail.")
    }

    @Test
    fun `should parse underscore separators in integers`() {
        @Language("Go")
        val code = """
package main

func main() {
    million := 1_000_000
    bigNum := 123_456_789
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing underscore separators should not fail.")
    }

    @Test
    fun `should parse underscore separators in hex literals`() {
        @Language("Go")
        val code = """
package main

func main() {
    hex := 0xFF_FF_FF_FF
    hexLower := 0xff_ff
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing hex with underscores should not fail.")
    }

    @Test
    fun `should parse underscore separators in octal literals`() {
        @Language("Go")
        val code = """
package main

func main() {
    octal := 0o755
    octalOld := 0755
    octalNew := 0o7_7_7
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing octal literals should not fail.")
    }

    @Test
    fun `should parse underscore separators in float literals`() {
        @Language("Go")
        val code = """
package main

func main() {
    f1 := 1_000.000_001
    f2 := 1.234_567e+10
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing float with underscores should not fail.")
    }

    @Test
    fun `should parse hexadecimal float literals`() {
        @Language("Go")
        val code = """
package main

func main() {
    hexFloat := 0x1.Fp+0
    hexFloat2 := 0x1p-2
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing hex floats should not fail.")
    }

    @Test
    fun `should parse imaginary literals with all number types`() {
        @Language("Go")
        val code = """
package main

func main() {
    i1 := 1i
    i2 := 1.0i
    i3 := 0x1p0i
    i4 := 1_000i
}
"""
        val codeFile = GoAnalyser().analysis(code, "numbers.go")
        
        // Should parse without error
        val allFunctions = codeFile.DataStructures.flatMap { it.Functions }
        assertTrue(allFunctions.any { it.Name == "main" },
            "main function should exist. Parsing imaginary literals should not fail.")
    }
}
