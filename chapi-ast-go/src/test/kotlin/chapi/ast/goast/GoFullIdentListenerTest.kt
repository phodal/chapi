package chapi.ast.goast

import chapi.domain.core.DataStructType
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GoFullIdentListenerTest {
    @Test
    internal fun shouldIdentifyPackageName() {
        val code= """
package main
"""

        val codeFile = GoAnalyser().analysis(code, "basic.go")
        assertEquals(codeFile.PackageName, "main")
        assertEquals(codeFile.FullName, "basic.go")
    }

    @Test
    internal fun shouldIdentifySingleImport() {
        val code= """
package main

import "fmt"
"""

        val codeFile = GoAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "fmt")
    }

    @Test
    internal fun shouldIdentifyMultipleLineImport() {
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].NodeName, "fmt")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Parameters.size, 1)
    }

    @Test
    internal fun shouldIdentifyFuncCall() {
        val code= """
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
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].NodeName, "fmt")
        assertEquals(codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Parameters.size, 1)
    }

    @Test
    internal fun shouldIdentifyFunctionLocalVars() {
        val code= """
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
        val code= """
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
        val code= """
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
        val code= """
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

    @Test
    internal fun shouldSuccessGetSqlOfNode() {
        @Language("Go")
        val code= """
package dao

import (
	"database/sql"
)

func (d *Dao) QueryBuglyProjectList() (projectList []string, err error) {
	var (
		rows *sql.Rows
	)
	sql := "select DISTINCT project_name from bugly_projects"
	if rows, err = d.db.Raw(sql).Rows(); err != nil {
		return
	}
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        println(functionCalls)

        assertEquals(functionCalls.size, 2)
        assertEquals(functionCalls[0].NodeName, "Dao.db")
        assertEquals(functionCalls[0].FunctionName, "Raw")
        assertEquals(functionCalls[0].Parameters.size, 1)
        assertEquals(functionCalls[0].Parameters[0].TypeValue, "\"select DISTINCT project_name from bugly_projects\"")

        assertEquals(functionCalls[1].NodeName, "Dao.db")
        assertEquals(functionCalls[1].FunctionName, "Rows")
    }

    @Test
    internal fun shouldIdentifyLocalVarWithText() {
        @Language("Go")
        val code= """
package dao

import (
	"database/sql"
	"fmt"

	"go-common/app/admin/ep/merlin/model"
)

func (d *Dao) MobileMachineLendCount() (mobileMachinesUsageCount []*model.MobileMachineUsageCount, err error) {
	var rows *sql.Rows

	SQL := fmt.Sprintf("select b.id,b.name,count(b.name) as count from mobile_machine_logs as a "+
		"left join mobile_machines as b on a.machine_id = b.id "+
		"where a.operation_type='%s' and a.operation_result = '%s' "+
		"group by b.id,b.`name` order by count desc", model.MBLendOutLog, model.OperationSuccessForMachineLog)

	if rows, err = d.db.Raw(SQL).Rows(); err != nil {
		return
	}

	defer rows.Close()
	for rows.Next() {
		mc := &model.MobileMachineUsageCount{}
		if err = rows.Scan(&mc.MobileMachineID, &mc.MobileMachineName, &mc.Count); err != nil {
			return
		}
		mobileMachinesUsageCount = append(mobileMachinesUsageCount, mc)
	}
	return
}
"""
        val codeFile = GoAnalyser().analysis(code, "")
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        println(functionCalls)

        assertEquals(functionCalls.size, 7)
        assertEquals(functionCalls[0].NodeName, "fmt")

        assertEquals(functionCalls[1].NodeName, "Dao.db")
        assertEquals(functionCalls[1].FunctionName, "Raw")
        assertEquals(functionCalls[1].Parameters.size, 1)
        assertEquals(functionCalls[1].Parameters[0].TypeValue, "\"select b.id,b.name,count(b.name) as count from mobile_machine_logs as a \"+\"left join mobile_machines as b on a.machine_id = b.id \"+\"where a.operation_type='%s' and a.operation_result = '%s' \"+\"group by b.id,b.`name` order by count desc\"")
    }
}
