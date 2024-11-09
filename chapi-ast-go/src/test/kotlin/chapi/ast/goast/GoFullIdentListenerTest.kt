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
        assertEquals(codeFile.DataStructures[0].Functions[0].LocalVariables[3].TypeType, "-1")
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
    internal fun shouldIdentifyConstLocalVars() {
        @Language("Go")
        val code= """
package dao


const (
	_taskSQL      = "SELECT id,business_id,flow_id,rid,admin_id,uid,state,weight,utime,gtime,mid,fans,`group`,reason,ctime,mtime from task WHERE id=?"
	_listCheckSQL = "SELECT id FROM task WHERE id IN (%s)"

	_dispatchByIDSQL = "UPDATE task SET gtime=? WHERE id=? AND state=? AND uid=? AND gtime=0"
	_queryGtimeSQL   = "SELECT gtime FROM task WHERE id=? AND state=? AND uid=?"
	_dispatchSQL     = "UPDATE task SET gtime=? WHERE state=? AND uid=? AND gtime='0000-00-00 00:00:00' ORDER BY weight LIMIT ?"
	_releaseSQL      = "UPDATE task SET admin_id=0,uid=0,state=0,gtime='0000-00-00 00:00:00' WHERE business_id=? AND flow_id=? AND uid=? AND (state=? OR (state=0 AND admin_id>0))"
	_resetGtimeSQL   = "UPDATE task SET gtime='0000-00-00 00:00:00' WHERE state=? AND business_id=? AND flow_id=? AND uid=?"
	_seizeSQL        = "UPDATE task SET state=?,uid=? WHERE id=? AND state=?"
	_submitSQL       = "UPDATE task SET state=?,uid=?,utime=? WHERE id=? AND state=? AND uid=?"
	_delaySQL        = "UPDATE task SET state=?,uid=?,reason=?,gtime='0000-00-00 00:00:00' WHERE id=? AND state=? AND uid=?"

	_consumerSQL     = "INSERT INTO task_consumer (business_id,flow_id,uid,state) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE state=?"
	_onlinesSQL      = "SELECT uid,mtime FROM task_consumer WHERE business_id=? AND flow_id=? AND state=?"
	_isconsumerOnSQL = "SELECT state FROM task_consumer WHERE business_id=? AND flow_id=? AND uid=?"

	_queryTaskSQL     = "SELECT id,business_id,flow_id,uid,weight FROM task WHERE state=? AND mtime<=? AND id>? ORDER BY id LIMIT ?"
	_countPersonalSQL = "SELECT count(*) FROM task WHERE state=? AND business_id=? AND flow_id=? AND uid=?"
	_queryForSeizeSQL = "SELECT id FROM task WHERE state=? AND business_id=? AND flow_id=? AND uid IN (0,?) ORDER BY weight DESC LIMIT ?"
	_listTasksSQL     = "SELECT `id`,`business_id`,`flow_id`,`rid`,`admin_id`,`uid`,`state`,`weight`,`utime`,`gtime`,`mid`,`fans`,`group`,`reason`,`ctime`,`mtime` FROM task %s ORDER BY weight DESC LIMIT ?,?"
)

func (d *Dao) CountPersonal(c context.Context, opt *common.BaseOptions) (count int64, err error) {
	if err = d.db.QueryRow(c, _countPersonalSQL, modtask.TaskStateDispatch, opt.BusinessID, opt.FlowID, opt.UID).Scan(&count); err != nil {
		log.Error("QueryRow error(%v)", errors.WithStack(err))
		return
	}
	return
}
"""

        val codeFile = GoAnalyser().analysis(code, "")
        val vars = codeFile.DataStructures[0].Functions[0].LocalVariables

        assertEquals(vars.size, 19)
        // get first var
        assertEquals(vars[0].TypeValue, "_taskSQL")
        assertEquals(vars[0].TypeType, "\"SELECT id,business_id,flow_id,rid,admin_id,uid,state,weight,utime,gtime,mid,fans,`group`,reason,ctime,mtime from task WHERE id=?\"")
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
