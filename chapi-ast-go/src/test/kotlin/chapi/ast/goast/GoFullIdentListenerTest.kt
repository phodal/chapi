package chapi.ast.goast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GoFullIdentListenerTest {
    @Test
    internal fun shouldIdentifyPackageName() {
        var code = """
package main
"""

        val codeFile = GoAnalyser().analysis(code, "basic.go")
        assertEquals(codeFile.PackageName, "main")
        assertEquals(codeFile.FullName, "basic.go")
    }

    @Test
    internal fun shouldIdentifySingleImport() {
        var code = """
package main

import "fmt"
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "fmt")
    }

    @Test
    internal fun shouldIdentifyMultipleLineImport() {
        var code = """
package main

import "fmt"
import . "time"
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 2)
        assertEquals(codeFile.Imports[0].Source, "fmt")
        assertEquals(codeFile.Imports[1].Source, "time")
        assertEquals(codeFile.Imports[1].AsName, ".")
    }

    @Test
    internal fun shouldIdentifyMultipleTogetherImport() {
        var code = """
package main

import (
    "fmt"
    "html/template"
    "os"
)
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 3)
        assertEquals(codeFile.Imports[0].Source, "fmt")
        assertEquals(codeFile.Imports[1].Source, "html/template")
        assertEquals(codeFile.Imports[2].Source, "os")
    }

    @Test
    internal fun shouldIdentifyBasicStruct() {
        var code = """
package main

type School struct {
    Id      bson.ObjectId
}
"""

        val codeFile = GoAnalyser().analysis(code, "basic.go")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "School")
        assertEquals(codeFile.DataStructures[0].FilePath, "basic.go")
        assertEquals(codeFile.DataStructures[0].Package, "main")
        assertEquals(codeFile.DataStructures[0].Fields.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "bson.ObjectId")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "Id")
    }

    @Test
    internal fun shouldIdentifyBasicStructFunction() {
        var code = """
package main

import "fmt"

type Animal struct {
	Age int
}

func (a *Animal) Move() {
	fmt.Println("Animal moved")
}
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Animal")
        assertEquals(codeFile.DataStructures[0].Fields.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "Move")
    }

    @Test
    internal fun shouldIdentifyStructFunctionReturnType() {
        var code = """
package main

import "fmt"

type Animal struct {
	Age int
}

func (a *Animal) Move() string {
	return ""
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].MultipleReturns.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].MultipleReturns[0].TypeType, "string")
    }

    @Test
    internal fun shouldIdentifyFunctionAsDefault() {
        var code = """
package main

func add(x int, y int) int {
	return x + y
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "add")
    }
}
