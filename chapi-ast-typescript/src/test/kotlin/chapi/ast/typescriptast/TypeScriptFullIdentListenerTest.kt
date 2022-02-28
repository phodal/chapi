package chapi.ast.typescriptast

import chapi.domain.core.DataStructType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TypeScriptFullIdentListenerTest {
    @Test
    internal fun shouldIdentifyInterfaceName() {
        var code = """
export interface IPerson {
    name: string;
    gender: string;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "iperson.ts")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "IPerson")
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.INTERFACE)
        assertEquals(codeFile.DataStructures[0].FilePath, "iperson.ts")
    }

    @Test
    internal fun shouldIdentifyInnerClass() {
        var code = """
class Foo {
    static Bar = class {

    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.CLASS)
        assertEquals(codeFile.DataStructures[0].NodeName, "Foo")
    }

    @Test
    internal fun shouldIdentifyMultipleNode() {
        var code = """
interface IPerson {
    name: string;
}

class Person implements IPerson {
    public publicString: string;
    private privateString: string;
    protected protectedString: string;
    readonly readonlyString: string;
    name: string;

    constructor(name: string) {
        this.name = name;
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 2)
        assertEquals(codeFile.DataStructures[0].NodeName, "IPerson")
        assertEquals(codeFile.DataStructures[1].NodeName, "Person")
    }

    @Test
    internal fun shouldIdentifyImplements() {
        var code = """
class Person implements IPerson {
    public publicString: string;
    private privateString: string;
    protected protectedString: string;
    readonly readonlyString: string;
    name: string;

    constructor(name: string) {
        this.name = name;
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Implements[0], "IPerson")
    }

    @Test
    internal fun shouldIdentifyClassExtends() {
        var code = """
class Employee extends Person {

}"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "Person")
    }

    @Test
    internal fun shouldIdentifyInterfaceExtends() {
        var code = """
interface IEmployee extends IPerson {
    empCode: number;
    readonly empName: string;
    empDept?:string;
    getSalary: (number) => number; // arrow function
    getManagerName(number): string;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "IPerson")
    }

    @Test
    internal fun shouldIdentifyBlockImportsSource() {
        var code = """
import { ZipCodeValidator } from "./ZipCodeValidator";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "ZipCodeValidator")
    }

    @Test
    internal fun shouldIdentifyMultipleBlockImportsSource() {
        var code = """
import { ZipCodeValidator, ZipCodeGenerator } from "./ZipCodeValidator";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName.size, 2)
        assertEquals(codeFile.Imports[0].UsageName[0], "ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[1], "ZipCodeGenerator")
    }

    @Test
    internal fun shouldIdentifyRequireImport() {
        var code = """
import zip = require("./ZipCodeValidator");

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "zip")
    }

    @Test
    internal fun shouldIdentifyImportAll() {
        var code = """
import "./module.js";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./module.js")
    }

    @Test
    internal fun shouldIdentifySpecificSymbol() {
        var code = """
import $ from "jquery";
import _ from "lodash";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 2)
        assertEquals(codeFile.Imports[0].Source, "jquery")
        assertEquals(codeFile.Imports[0].UsageName[0], "$")
        assertEquals(codeFile.Imports[1].Source, "lodash")
        assertEquals(codeFile.Imports[1].UsageName[0], "_")
    }

    @Test
    internal fun shouldIdentifyImportAs() {
        var code = """
import * as validator from "./ZipCodeValidator";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "validator")
    }

    private val personClassCode = """class Person implements IPerson {
        public publicString: string;
        private privateString: string;
        protected protectedString: string;
        readonly readonlyString: string;
        name: string;
    
        constructor(name: string) {
            this.name = name;
        }
    }"""

    @Test
    internal fun shouldIdentifyClassConstructorMethod() {
        val codeFile = TypeScriptAnalyser().analysis(personClassCode, "")
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "constructor")
        val parameters = codeFile.DataStructures[0].Functions[0].Parameters
        assertEquals(parameters.size, 1)
        assertEquals(parameters[0].TypeValue, "name")
        assertEquals(parameters[0].TypeType, "string")
    }

    @Test
    internal fun shouldIdentifyClassFields() {
        val codeFile = TypeScriptAnalyser().analysis(personClassCode, "")
        assertEquals(codeFile.DataStructures[0].Fields.size, 5)
        assertEquals(codeFile.DataStructures[0].Fields[0].Modifiers[0], "public")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeValue, "publicString")
        assertEquals(codeFile.DataStructures[0].Fields[0].TypeType, "string")
        assertEquals(codeFile.DataStructures[0].Fields[1].TypeType, "string")
        assertEquals(codeFile.DataStructures[0].Fields[4].Modifiers.size, 0)
    }

    @Test
    internal fun shouldIdentifyNormalClassFunction() {
        val normalClassFunction = """
class Employee extends Person {
    empCode: number;
    static pi: number = 3.14;

    constructor(empcode: number, name:string) {
        super(name);
        this.empCode = empcode;
    }

    displayName():void {
        console.log("Name = " + this.name +  ", Employee Code = " + this.empCode);
    }
} 
        """

        val codeFile = TypeScriptAnalyser().analysis(normalClassFunction, "")
        assertEquals(codeFile.DataStructures[0].Functions.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[1].Name, "displayName")
        assertEquals(codeFile.DataStructures[0].Functions[1].ReturnType, "void")
    }

    @Test
    internal fun shouldIdentifyNormalPureFunction() {
        val normalClassFunction = """
function Sum(x: number, y: number) : void {
    console.log('processNumKeyPairs: key = ' + key + ', value = ' + value)
    return x + y;
}
        """

        val codeFile = TypeScriptAnalyser().analysis(normalClassFunction, "demo.ts")
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        assertEquals(codeFile.DataStructures[0].FilePath, "demo.ts")
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "Sum")
        assertEquals(codeFile.DataStructures[0].Functions[0].Parameters.size, 2)
        assertEquals(codeFile.DataStructures[0].Functions[0].MultipleReturns.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].MultipleReturns[0].TypeType, "void")
    }

    @Test
    internal fun shouldIdentifyInterfaceProperty() {
        val code = """
export interface IPerson {
    name: string;
    gender: string;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Fields.size, 2)
    }

    @Test
    internal fun shouldIdentifyInterfacePropertyFunction() {
        val code = """
interface IEmployee extends IPerson {
    empCode: number;
    readonly empName: string;
    empDept?:string;
    getSalary: (number) => number; // arrow function
    getManagerName(number): string;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures[0].Fields.size, 3)
        assertEquals(codeFile.DataStructures[0].Functions.size, 2)

        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.Name, "getSalary")
        assertEquals(firstFunc.MultipleReturns[0].TypeType, "number")
        assertEquals(firstFunc.Parameters[0].TypeType, "number")

        val secondFunc = codeFile.DataStructures[0].Functions[1]
        assertEquals(secondFunc.Name, "getManagerName")
        assertEquals(secondFunc.MultipleReturns[0].TypeType, "string")
//        assertEquals(secondFunc.Parameters[0].TypeType, "number")
    }

    @Test
    internal fun shouldIdentifyFunctionExpressionFunction() {
        val code = """
let greeting = function() {
    console.log("Hello TypeScript!");
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "greeting")
    }

    @Test
    internal fun shouldIdentifyFunctionExpressionParameterAndReturnType() {
        val code = """
let SumAnon = function(x: number, y: number) : number
{
    return x + y;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        val functions = codeFile.DataStructures[0].Functions
        assertEquals(functions[0].Parameters.size, 2)
        assertEquals(functions[0].Parameters[0].TypeType, "number")
        assertEquals(functions[0].Parameters[0].TypeValue, "x")
        assertEquals(functions[0].Parameters[1].TypeType, "number")
        assertEquals(functions[0].Parameters[1].TypeValue, "y")
        assertEquals(functions[0].MultipleReturns[0].TypeType, "number")
    }

    @Test
    internal fun shouldIdentifyArrayFunction() {
        val code = """
let Print = () => console.log("Hello TypeScript");
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        val functions = codeFile.DataStructures[0].Functions
        assertEquals(functions.size, 1)
        assertEquals(functions[0].Name, "Print")
    }

    @Test
    internal fun shouldIdentifyArrayFunctionParameters() {
        val code = """
let sumArrow = (x: number, y: number): number => {
    return x + y
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        val functions = codeFile.DataStructures[0].Functions
        assertEquals(functions.size, 1)
        assertEquals(functions[0].Name, "sumArrow")
        assertEquals(functions[0].Parameters.size, 2)
        assertEquals(functions[0].Parameters[1].TypeType, "number")
        assertEquals(functions[0].Parameters[1].TypeValue, "y")
        assertEquals(functions[0].MultipleReturns[0].TypeType, "number")
    }

    @Test
    internal fun shouldIdentifyNameSpaceAsPackage() {
        val code = """
export namespace Polygons {
    export class Triangle { }
    export class Square { }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 2)
        assertEquals(codeFile.DataStructures[0].Package, "Polygons")
        assertEquals(codeFile.DataStructures[1].Package, "Polygons")
    }

    @Test
    internal fun shouldIdentifyHelloWorldFunctionCall() {
        val code = """
function helloworld() {
  console.log("hello, world")
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        val functionCalls = codeFile.DataStructures[0].Functions[0].FunctionCalls
        assertEquals(functionCalls.size, 1)
        assertEquals(functionCalls[0].FunctionName, "log")
        assertEquals(functionCalls[0].NodeName, "console")
        assertEquals(functionCalls[0].Parameters[0].TypeValue, "\"hello, world\"")
    }

    @Test
    internal fun shouldIdentifyApiFunctionCallWithObjectAndGeneric() {
        val code = """
export function queryScannerConfig() {
  return axios<ScannerConfigType>({
    baseURL,
    url: '/config',
    method: "GET",
  });
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)

        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(1, firstCall.size)
        assertEquals("axios", firstCall[0].FunctionName);

        val parameters = firstCall[0].Parameters
        assertEquals(1, parameters.size);
        assertEquals(3, parameters[0].ObjectValue.size)
    }

    @Test
    internal fun shouldIdentifyApiFunctionCallWithString() {
        val code = """
async function getUser() {
  try {
    const response = await axios.get('/user?ID=12345');
    console.log(response);
  } catch (error) {
    console.error(error);
  }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)

        val firstCall = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(3, firstCall.size)
        assertEquals("axios.get", firstCall[0].FunctionName);
    }

    @Test
    internal fun shouldIdentifyForType() {
        val code = """
export function queryModule() {
  return axios<Module[]>({
    baseURL,
    url: "/logic-modules",
    method: "GET",
  }).then((res) => _.orderBy(res, ["status", "name"], ["desc", "asc"]));
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)

        val calls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(3, calls.size)
        assertEquals("axios->then", calls[0].FunctionName);
        assertEquals("axios->then", calls[1].FunctionName);
    }

    @Test
    internal fun shouldIdentifyLocalVars() {
        val code = """
const systemInfoApi = "/system-info";

export function querySystemInfo() {
  return axios<SystemInfo[]>({
    baseURL,
    url: systemInfoApi,
    method: "GET",
  });
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        val calls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        assertEquals(1, calls.size)
        assertEquals(1, codeFile.Fields.size);
        assertEquals("systemInfoApi", codeFile.Fields[0].TypeKey);
        assertEquals("\"/system-info\"", codeFile.Fields[0].TypeValue);
    }

    @Test
    internal fun shouldIdentifyUmiRequest() {
        val code = """
import request from 'umi-request';

export function demo() {
  return request
    .get('/api/v1/xxx?id=1')
    .then(function(response) {
      console.log(response);
    })
    .catch(function(error) {
      console.log(error);
    });
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)

        assertEquals(1, codeFile.DataStructures[0].Functions.size)
        val calls = codeFile.DataStructures[0].Functions[0].FunctionCalls

        println(Json.encodeToString(calls))
        assertEquals(2, calls.size)
        assertEquals("request->get->then->catch", calls[0].FunctionName);
    }
}
