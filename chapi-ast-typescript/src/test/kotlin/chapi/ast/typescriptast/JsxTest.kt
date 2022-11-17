package chapi.ast.typescriptast

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JsxTest {
    @Test
    internal fun shouldIdentReactComponents() {
        val content = this::class.java.getResource("/realworld/BadSmellThreshold.tsx")!!.readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "BadSmellThreshold.tsx")

        Assertions.assertEquals(codeFile.DataStructures.size, 1)
        Assertions.assertEquals(codeFile.DataStructures[0].Exports[0].Name, "BadSmellThreshold")
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
        assertEquals("updateSystemInfo", innerFunc.FunctionCalls[0].FunctionName)
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


    // TODO: fix nestedIssued
    @Test
    @Disabled
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
