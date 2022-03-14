package chapi.ast.typescriptast

import chapi.domain.core.DataStructType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TypeScriptFullIdentListenerTest {

    @Test
    internal fun shouldIdentTypeScriptHelloWorldFunCall() {
        val content = """
let greeting = function() {
    console.log("Hello TypeScript!");
};
        """

        val codeFile = TypeScriptAnalyser().analysis(content, "")

        val firstFunc = codeFile.DataStructures[0].Functions[0]
        Assertions.assertEquals(firstFunc.FunctionCalls.size, 1)
        Assertions.assertEquals(firstFunc.FunctionCalls[0].NodeName, "console")
        Assertions.assertEquals(firstFunc.FunctionCalls[0].FunctionName, "log")
    }

    @Test
    internal fun shouldIndentTypeScriptDecorator() {
        val content = """
import { Injectable } from '@angular/core';

@Injectable()
export class LedgeStorageService {
  get storage() {
    return window.localStorage;
  }
}
        """

        val codeFile = TypeScriptAnalyser().analysis(content, "")
        val annotations = codeFile.DataStructures[0].Annotations
        Assertions.assertEquals(annotations.size, 1)
        Assertions.assertEquals(annotations[0].Name, "Injectable")
    }

    @Test
    internal fun shouldIndentTypeScriptDecoratorKeyValues() {
        val content = """
import { Component } from '@angular/core';

@Component({
  selector: 'app-design',
  templateUrl: './design.component.html',
  styleUrls: ['./design.component.scss'],
})
export class DesignComponent {

}

        """

        val codeFile = TypeScriptAnalyser().analysis(content, "")
        val annotations = codeFile.DataStructures[0].Annotations
        Assertions.assertEquals(annotations.size, 1)
    }

    @Test
    internal fun shouldIdentifyInterfaceName() {
        val code = """
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
    internal fun shouldIdentifyInterfacesWithMethod() {
        val code = """
export interface ProfileConfig {
  layer: LayerKeys;
  quota: string;
  operator: "BIGGER" | "LESS" | "EQUAL";
  value: number;
}

export interface Profile {
  id?: number;
  name: string;
  config: ProfileConfig[];
}

const QualityGateProfile = () => {

}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "iperson.ts")
        val structs = codeFile.DataStructures
        assertEquals(structs.size, 3)
        assertEquals("ProfileConfig", structs[0].NodeName)
        assertEquals("Profile", structs[1].NodeName)
        assertEquals("default", structs[2].NodeName)
    }

    @Test
    internal fun shouldIdentifyInnerClass() {
        val code = """
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
        val code = """
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
        val code = """
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
        val code = """
class Employee extends Person {

}"""
        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "Person")
    }

    // Todo:
    @Test
    internal fun shouldIdentifyInterfaceExtends() {
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
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Extend, "IPerson")
    }

    @Test
    internal fun shouldIdentifyBlockImportsSource() {
        val code = """
import { ZipCodeValidator } from "./ZipCodeValidator";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "ZipCodeValidator")
    }

    @Test
    internal fun shouldIdentifyMultipleBlockImportsSource() {
        val code = """
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
        val code = """
import zip = require("./ZipCodeValidator");

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "zip")
    }

    @Test
    internal fun shouldIdentifyImportAll() {
        val code = """
import "./module.js";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./module.js")
    }

    @Test
    internal fun shouldIdentifySpecificSymbol() {
        val code = """
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
        val code = """
import * as validator from "./ZipCodeValidator";

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 1)
        assertEquals(codeFile.Imports[0].Source, "./ZipCodeValidator")
        assertEquals(codeFile.Imports[0].UsageName[0], "*")
        assertEquals(codeFile.Imports[0].AsName, "validator")
    }

    @Test
    internal fun shouldIdentStyleImports() {
        val code = """
import { updateSystemInfo } from '@/api/addition/systemInfo';
import { BadSmellOption, useBadSmellOption } from "@/api/module/badSmellThresholds";
import useSystemList from '@/store/global-cache-state/useSystemList';
import { storage } from '@/store/storage/sessionStorage';
import { Button, Collapse, Form, notification, Radio } from "antd";
import { useForm } from 'antd/lib/form/Form';
import { Store } from 'antd/lib/form/interface';
import React, { useEffect } from "react";
import styles from "./BadSmellThreshold.less";
import BadSmellThresholdTable from "./components/BadSmellThresholdTable";
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "")
        assertEquals(codeFile.Imports.size, 10)
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
        val dataStruct = codeFile.DataStructures[0]
        val calls = dataStruct.Functions[0].FunctionCalls

        assertEquals(1, calls.size)
        assertEquals(1, dataStruct.Fields.size);
        assertEquals("systemInfoApi", dataStruct.Fields[0].TypeKey);
        assertEquals("/system-info", dataStruct.Fields[0].TypeValue);
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
        assertEquals(3, calls.size)
        assertEquals("request->get->then->catch", calls[0].FunctionName);
    }

    @Test
    internal fun shouldSetExportConstantsToContainer() {
        val content = """
export const baseURL = '/api'
        """

        val codeFile = TypeScriptAnalyser().analysis(content, "config.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val defaultStruct = codeFile.DataStructures[0]

        assertEquals("config.ts", defaultStruct.FilePath)
        assertEquals("default", defaultStruct.NodeName)
        assertEquals(1, defaultStruct.Fields.size)
        assertEquals(1, defaultStruct.Exports.size)
        assertEquals("/api", defaultStruct.Fields[0].TypeValue)
    }

    @Test
    internal fun shouldExportFieldForAlias() {
        val content = """

import createCacheState from "@/utils/utils";
import { queryAllQualityGateProfile } from "@/api/module/profile";

const useQualityGate = createCacheState(queryAllQualityGateProfile, []);
export default useQualityGate;

"""

        val codeFile = TypeScriptAnalyser().analysis(content, "config.ts")
        val defaultStruct = codeFile.DataStructures[0]

        assertEquals("default", defaultStruct.NodeName)
        assertEquals(1, defaultStruct.Fields.size)
    }

    @Test
    internal fun shouldIdentExportFunctions() {
        val content = """
export const baseURL = '/api'
        """

        val codeFile = TypeScriptAnalyser().analysis(content, "config.ts")
        val defaultStruct = codeFile.DataStructures[0]

        assertEquals(1, defaultStruct.Exports.size)
        assertEquals("baseURL", defaultStruct.Exports[0].Name)
    }

    @Test
    internal fun shouldIdentTsx() {
        val content = """
import { updateSystemInfo } from '@/api/addition/systemInfo';

const BadSmellThreshold = () => {
    const onFinish = (values: Store) => {
        updateSystemInfo(currentSystemInfo).then(() => {});
    }
    
    return ( <div />); 
}
"""

        val codeFile = TypeScriptAnalyser().analysis(content, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]

        assertEquals(1, defaultStruct.Functions.size)

        val firstFunc = defaultStruct.Functions[0]
        assertEquals("index.tsx", firstFunc.FilePath)
        assertEquals("BadSmellThreshold", firstFunc.Name)

        val innerFunc = firstFunc.InnerFunctions[0]
        assertEquals("onFinish", innerFunc.Name)
        assertEquals("updateSystemInfo->then", innerFunc.FunctionCalls[0].FunctionName)
    }

    @Test
    internal fun shouldSupportNestedFunc() {
        val content = """
const BadSmellThreshold = () => {
    const renderRadio = (badSmellOption: BadSmellOption) => (
        <Radio value={badSmellOption.id}
            onClick={(e) => e.stopPropagation()}> 选择
        </Radio>
    ); 
    
    const onReset = () => {
        form.setFieldsValue({ badSmellThresholdSuiteId: currentSystemInfo!.badSmellThresholdSuiteId });
    };

    const onChange = (e: any) => {
        form.setFieldsValue({ badSmellThresholdSuiteId: e.target.value });
    }
}
"""

        val codeFile = TypeScriptAnalyser().analysis(content, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]

        assertEquals(1, defaultStruct.Functions.size)
        val innerFunctions = defaultStruct.Functions[0].InnerFunctions
        assertEquals(3, innerFunctions.size)

        assertEquals(2, innerFunctions[0].FunctionCalls.size)
        assertEquals(1, innerFunctions[1].FunctionCalls.size)
        assertEquals(1, innerFunctions[2].FunctionCalls.size)
    }

    @Test
    internal fun shouldIdentDotTag() {
        val code = """
const BadSmellThreshold = () => {
    return (
        <Form.Item></Form.Item>
    );
};"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
    }

    @Test
    internal fun shouldIdentAttributeWithKeyword() {
        val code = """
const BadSmellThreshold = () => {
    return (
        <Button type="primary" htmlType="submit">
        </Button>
    );
};"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
    }

    @Test
    internal fun shouldCallReturn() {
        val code = """
const BadSmellThreshold = () => {
    useEffect(() => {
        
    });

    return (
        <div></div>
    );
};"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
    }

    @Test
    internal fun supportOptionalCall() {
        val code = """
function tryApplyUpdates(onHotUpdateSuccess?: Function) {
    function handleApplyUpdates(err: Error | null, updatedModules: any) {
        onHotUpdateSuccess?.();
    }
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
        assertEquals("tryApplyUpdates", defaultStruct.Functions[0].Name)

        assertEquals(1, defaultStruct.Functions[0].InnerFunctions.size)
        assertEquals("handleApplyUpdates", defaultStruct.Functions[0].InnerFunctions[0].Name)
    }

    @Test
    internal fun supportSomeIssue() {
        val code = """
function tryApplyUpdates(onHotUpdateSuccess: Function) {
  if (!module.hot) {
    window.location.reload();
  }
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
        assertEquals("tryApplyUpdates", defaultStruct.Functions[0].Name)
    }

    @Test
    internal fun variableCallInNumber() {
        val code = """
function reload() {
  const currentSystemId = Number(storage.getSystemId());
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
        assertEquals(1, defaultStruct.Functions[0].FunctionCalls.size)
        assertEquals("storage->getSystemId", defaultStruct.Functions[0].FunctionCalls[0].FunctionName)
    }

    @Test
    internal fun correctFunctionCallName() {
        val code = """
interface DependenceConfigProps {
  visible: boolean;
  hide: Function;
}

const DependenceConfig = (props: DependenceConfigProps) => {
    return (<div></div>)
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(2, codeFile.DataStructures.size)

        val defaultStruct = codeFile.DataStructures[1]
        val functions = defaultStruct.Functions
        assertEquals(true, functions[0].IsReturnHtml)
    }

    @Test
    internal fun supportInterfaceMethodGeneric() {
        val code = """
const createJMethodNode = (jMethod: JMethod): TreeNode<JMethod> => {
  const { id, name, module, clazz } = jMethod;
  return {
    id,
    name,
    module,
    parents: [],
    children: [],
  };
};
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
    }

    @Test
    internal fun cleanTemplateString() {
        val code = """
function hello2() {
  console.log(`template-string`)
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val defaultStruct = codeFile.DataStructures[0]
        assertEquals(1, defaultStruct.Functions.size)
        assertEquals(1, defaultStruct.Functions[0].FunctionCalls.size)
        assertEquals("template-string", defaultStruct.Functions[0].FunctionCalls[0].Parameters[0].TypeValue)
    }

    @Test
    internal fun optionCheckAfterData() {
        val code = """
const QualityGateProfile = () => {
  updateQualityGateProfile(profile.id!, profile).then(() => {
    notification.success({
      message: "更新成功！",
    });
  });
};
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun supportForRestParameterInHtml() {
        val code = """
function PluginConfig(props: { data: ConfigData; updateData: Function }) {
    return (<Form.Item {...field}></Form.Item>);
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun supportForExprInFunction() {
        val code = """
function PluginConfig(props: { data: ConfigData; updateData: Function }) {
    return (
      <Form form={form} onFinish={onFinish}>
        <Form.List name={type}>
          {(fields, { add, remove }) => {
            
          }}
        </Form.List>
      </Form>
);
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun supportForLogCodeCallToField() {
        val code = """
const useQualityGate = createCacheState(queryAllQualityGateProfile, []);
export default useQualityGate;
        """

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals("createCacheState", codeFile.DataStructures[0].Fields[0].Calls[0].FunctionName)
        assertEquals(2, codeFile.DataStructures[0].Fields[0].Calls[0].Parameters.size)
        assertEquals(
            "queryAllQualityGateProfile",
            codeFile.DataStructures[0].Fields[0].Calls[0].Parameters[0].TypeValue
        )
    }

    @Test
    internal fun undefinedAndUnusedComma() {
        val code = """
export const getLayout = (options: LayoutOptions) => {
  const getSize = (
    size: number | number[] | undefined,
    index: number,
    defaultVaue = 16,
  ): number =>  { }
};

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun enumInUndefined() {
        val code = """
interface CollapsibleCardProps {
  collapsed?: boolean;
  title?: string | ReactNode;
  size?: "default" | "small" | undefined;
  extra?: ReactNode;
  children?: any;
  className?: string;
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(6, codeFile.DataStructures[0].Fields.size)
    }

    @Test
    internal fun handleNullCoalesce() {
        val code = """
const handleError = (error: any) => {
  const message = error?.response?.data?.message ?? error.message;
};
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun handleFunctionDeclEndWithCommon() {
        val code = """
export function queryDFMSMetricBy(by: "module" | "package" | "class", params: MetricParams, ) {

}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun moduleKeyword() {
        val code = """
const getModulesOption = (modules?: Module[]): FormItemOption[] => {
  return modules.map(module => {
    return { label: module.name, value: module.name }
  })
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun AsunknownIssue() {
        val code = """
export default function axiosAgent<T>(config: AxiosRequestConfig) {
  return (axiosInstance(config) as unknown) as Promise<T>;
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun classPropCheckIssue() {
        val code = """
export class ELKLayout {
  elk!: ELK;
  options!: LayoutOptions;
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun forOfStatement() {
        val code = """
function dfs() {
  if (node in graph) {
    for (const n of graph[node]) {
    }
  }
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun defaultInObjectLiteral() {
        val code = """
const options: Partial<GraphOptions> = {
  modes: {
    default: ["drag-canvas", "drag-node", "zoom-canvas"],
  },
  defaultNode: {
    type: "deps-node",
  }
};

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun commentInTsx() {
        val code = """
export function BuGrade(props: BuGradeProps) {
  return (
    <div className="BuGrade">
      <BaLabel text={props.text}>
        <div className="BuGrade-grade-icon">NA</div>
        {/* <div className="BuGrade-grade-icon">{props.grade.toUpperCase()}</div> */}
      </BaLabel>
    </div>
  );
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun optionalParameterBeforeRequiredParamter() {
        val code = """
export function initCytoscape(id = "cy", onEvent: { cxttap: () => MessageType }) {

}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun queryBeforeIdentify() {
        val code = """
export const switchFunc = (pagination, filters, sorter, {action}) => {
  switch (action) {
    case "paginate":
      return onPaginationChange?.(pagination);
    case "filter":
      return onFilterChange?.(filters);
    case "sort":
      return onSortChange?.(sorter);
  }
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun typeDefineIssue() {
        val code = """
function useUrlQuery<T = { [key: string]: string }>() {
  const location = useLocation() as { query: T; };
  return location.query ?? ({} as T);
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun nestedGeneric() {
        val code = """
function ModuleDependenceGraph() {
  const [graphData, setGraphData] = useState<GraphData<JavaItem>>();
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun anyKeywordInType() {
        val code = """
export default function createCacheState<T = any>(): () => [CacheState<T> | undefined, () => void] {
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun typeAlias() {
        val code = """
type State = { [key in ThresholdKey]: any };
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun declareClass() {
        val code = """
declare class ELK {
  constructor(options: any) {}
  layout(data: any): Promise<any>;
}
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun lodashIssueInObjectMethod() {
        val code = """
export default function CouplingList(props: CouplingListProps) {
  const props = firstItem.props.map((prop, index) => {
    return {
      render(_: any, item: CouplingRecord) {
        return <Text type={qualified ? undefined : "danger"}>{value}</Text>;
      },
    };
  })
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun symbolKeywordInObjectLiteral() {
        val code = """
const getMarkLineOpt = (coordX: number, coordY: number) => {
  return {
    data: [[
      { coord: [coordY, coordX], symbol: 'none' }
    ]]
  }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun typeByObject() {
        val code = """
const onStabilityChange = (value: DFMS["stability"]) => {

};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun lazyRequiredInObject() {
        val code = """
const docs = {
  index: require("./docs/index.md").default,
  "module-coupling": require("./docs/module-coupling.md").default,
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun keyofTypeOf() {
        val code = """
const getClazzColumnsBy = (type: keyof typeof columnCount) => {}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun anyInObjecLiteralType() {
        val code = """
export function getChartsOption(data?: number[]): EChartOption {
  return {
      series: !data ? defaultSeriesMarkLineOpt :
      [...defaultSeriesMarkLineOpt, {
        data: [data],
        tooltip: {
          formatter: ({ value }: any) => {}
        }
      },
    ]
  }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun reactDefaultNode() {
        val code = """
const SystemInfoForm = (props: SystemInfoFormProps, ref: any) => {
  return (
    <>
    </>
  );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun typeInStrings() {
        val code = """
type ThresholdKey = | "oversizedMethodByLine" | "oversizedMethodByCount"
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun thisIsInObjectType() {
        val code = """
interface SingularData {
  isNode(): this is NodeSingular;
}
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun isAsFunctionName() {
        val code = """
interface SingularData {
   is(selector: Selector): boolean;
}
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun typeAnnotationForObjectLiteralGenerateMethod() {
        val code = """
const store: IOptions<State, string, string, string> = {
  mutations: {
    updateThresholdState(state: State, payload): State {
      return Object.assign({}, state, { ...payload });
    }
  }
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun namespaceAsParameter() {
        val code = """
interface CoreGraphManipulationExt {
    scratch(namespace?: string): Scratchpad;
    scratch(namespace: string, value: any): this;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun typeEnumList() {
        val code = """
interface CytoscapeOptions {
  elements?:
    | ElementsDefinition
    | ElementDefinition[]
    | Promise<ElementsDefinition>
    | Promise<ElementDefinition[]>;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    internal fun multipleReturnTypeWithBoolean() {
        val code = """
type SearchVisitFunction = (
 v: NodeSingular,
 e: EdgeSingular,
 u: NodeSingular,
 i: number,
 depth: number,
) => boolean | void;
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun supportForGenericMap() {
        val code = """
export type Model<T extends keyof typeof models> = {
  [key in keyof typeof models]: ReturnType<typeof models[T]>;
};
"""

        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    internal fun parametersForString() {
        val code = """
export const querySystemInfo = (data) => {
    return request(`/api/system-info`, {
        method: 'GET',
    });
};

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val parameters = codeFile.DataStructures[0].Functions[0].FunctionCalls[0].Parameters
        assertEquals(parameters.size, 2)
        assertEquals(parameters[0].TypeValue, "/api/system-info")
    }

    @Test
    internal fun objectLiteralListInParameterList() {
        val code = """
const service = {
  effects: {
    *addThing({ payload }, { call }){
      const response = yield call(createThingRequest, payload)
      return response
    }
  }  
};

"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val ds = codeFile.DataStructures
        assertEquals(1, ds.size)
        assertEquals(1, ds[0].Fields[0].Calls.size)
    }

    @Test
    @Disabled
    internal fun conflictWithGenericType() {
        val code = """
export default function({a, b, c}) {
    return (c ? <DirectoryTree></DirectoryTree> : <Nodata/>)
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        val ds = codeFile.DataStructures
        assertEquals(1, ds.size)
    }


    @Test
    internal fun parameterObjectLiteral() {
        val code = """
const setting = {
  reducers: {
    changeSetting(_, { payload }) {
      return { ...defaultSettings, ...payload };
    },
  },
};
export default setting;
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    // TODO: fix nestedIssued
    @Test
    @Disabled
    internal fun multipleGeneric() {
        val code = """
const createTreeNode = <U = TreeNode, T extends JavaItem<T>>(): U => {};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    // TODO: fix nestedIssued
    @Test
//    @Disabled
    internal fun htmlElementIssue() {
        val code = """
const renderDataClassFields = (record: DataClass) => {
  return (
    <div key={field.name}>
      <span>字段</span>：<span>{field.name}</span>
    </div>
  );
};
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }
}
