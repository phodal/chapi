package chapi.ast.goast

import chapi.domain.core.DataStructType
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
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.STRUCT)
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

    @Test
    internal fun shouldIdentifyFunctionMultipleReturnType() {
        var code = """
package main

func get(x int, y int) (int, int) {
	return x, y
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].MultipleReturns.size, 2)
    }

    @Test
    internal fun shouldIdentifyFunctionParameters() {
        var code = """
package main

func get(x int, y int) (int, int) {
	return x, y
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeValue, "x")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters[0].TypeType, "int")
    }

    @Test
    internal fun shouldIdentifyStructFuncCall() {
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
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].NodeName, "fmt.Println")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Parameters.size, 1)
    }

    @Test
    internal fun shouldIdentifyFuncCall() {
        var code = """
package main

import "fmt"

func main() {
    fmt.Println("hello world")
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].NodeName, "fmt.Println")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Parameters.size, 1)
    }

    @Test
    internal fun shouldIdentifyFunctionLocalVars() {
        var code = """
package main

func VarDecls() {
	var a int        // +
	var b, c float64 // + strange extra levels
	var d = 1        // + doesn't show zero value
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables.size, 4)
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[1].TypeType, "float64")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[1].TypeValue, "b")
    }

    @Test
    internal fun shouldIdentifyFunctionShortVars() {
        var code = """
package main

func ShortDecls() {
    a, b := 0, 10
	c := func() int { return 7 }
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables.size, 3)
    }

    @Test
    internal fun shouldIdentifyFunctionConstVars() {
        var code = """
package main

func ConstDecls() {
	const Pi float64 = 3.14159265358979323846
	const zero = 0.0         // untyped floating-point constant
	const (
		size int64 = 1024
		eof        = -1  // untyped integer constant
	)
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables.size, 4)
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[3].TypeType, "")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[3].TypeValue, "eof")
    }

    @Test
    internal fun shouldIdentifyStructFunctionLocalVars() {
        var code = """
package main

import "fmt"

type Animal struct {
	Age int
}

func (a *Animal) Move() {
	const zero = 0.0
    a, b := 0, 10
    var c int
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        println(codeFile.DataStructures[0])
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables.size, 4)
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[0].TypeValue, "zero")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[1].TypeValue, "a")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[2].TypeValue, "b")
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[3].TypeValue, "c")
    }
}
