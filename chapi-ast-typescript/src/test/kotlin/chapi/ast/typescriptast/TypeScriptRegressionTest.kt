package chapi.ast.typescriptast

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TypeScriptRegressionTest {

    @Test
    internal fun reggrestion1() {
        val code = """
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.test = void 0;
function test() {
    alert("test");
}
exports.test = test;
"""

        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "test")
    }

    @Test
    fun of_keyword_lost() {
        val code = """
import {EMPTY, Observable, of} from 'rxjs';

function demo() {
if (node in graph) {
  for (const n of graph[node]) {

  }
}

of(data)
}

"""

        val codeContainer = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeContainer.DataStructures.size)
        assertEquals(1, codeContainer.DataStructures[0].Functions.size)

        assertEquals(codeContainer.DataStructures[0].Functions[0].Name, "demo")

    }

    @Test
    fun member_dot_issue() {
        val code = """export class DemoComponent implements OnInit, ControlValueAccessor {
    ngOnInit(): void {
        val data = this.onChange?.(userName || password || null);
    }
}"""
        val codeContainer = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, codeContainer.DataStructures.size)
        assertEquals(1, codeContainer.DataStructures[0].Functions.size)

        assertEquals(codeContainer.DataStructures[0].Functions[0].Name, "ngOnInit")
    }

    @Test
    fun end_with_dot() {
        val code = """export class SomeComponent implements OnInit, ControlValueAccessor {
  constructor(
    private fb: FormBuilder,
  ) { }
} """
        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "constructor")
    }

    @Test
    fun nested_type_type_error() {
        val code = """function demo() { Object.entries(values).forEach(([key, value]: [string, string[]]) => {
     
});}
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "demo")
    }

    @Test
    fun array_in_for_loop() {
        val code = """function demo() {
    this.names.forEach((it, index) => {
      multiSelect('', this.accessor[index])
    })
}
"""
        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "demo")
    }

    @Test
    fun query_array_literal() {
        val code = """
export class DemoComponent implements OnInit, ControlValueAccessor {
  confirm(): void {
      return this.form.value?.id?.[0] || null
  }
}"""
        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "confirm")
    }

    @Test
    fun readonly_property() {
        val code = """class DataSource extends Data<Item> {
  readonly pageSize = 5;

  constructor(public readonly type: TaskEnum,) {
    super();
  }
}"""

        val container = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "constructor")
    }

    @Test
    fun type_definition() {
        val code = """export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };"""
        TypeScriptAnalyser().analysis(code, "index.tsx")

        val code2 =
            """export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };"""
        TypeScriptAnalyser().analysis(code2, "index.tsx")
    }

    @Test
    fun type_in_interface() {
        val code = """export abstract class ReactiveOptionProviderService<T extends SelectOption> {
  abstract create?(name: string): Observable<T>;
}"""

        val container = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "create")
    }

    @Test
    fun numeric_separators() {
        val code = """function demo() {
   if (+value > 1_000_000_000) {
   }
}
        """.trimMargin()

        val container = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(1, container.DataStructures.size)
        assertEquals(1, container.DataStructures[0].Functions.size)
        assertEquals(container.DataStructures[0].Functions[0].Name, "demo")
    }

    @Test
    fun nested_type() {
        val code = """export type Query = Array<Array<Maybe<Number>>>"""

        TypeScriptAnalyser().analysis(code, "index.tsx")

        val code2 = """export type Query = Array<Array<Array<Maybe<Number>>>>"""
        TypeScriptAnalyser().analysis(code2, "index.tsx")
    }

    @Test
    fun nested_type2() {
        val code = """
export type CaseQuery = (
  { user?: Maybe<Array<Maybe<(
    UserQuery
  )>>> }
);"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun private_issue() {
        val code = """
export class PopupDirective {
  private openPopup(): void {
   
  }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "openPopup")
    }

    @Test
    fun this_issue() {
        val code = """
export class PopupDirective {
  get taskTitle(): string {
    return this.isSubTask ? this.subTask.name : this.commonTaskWorkOrder.title;
  }
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "get")
    }

    @Test
    fun enum_with_colon() {
        val code = """export class StatePipe implements PipeTransform {
  transform(state: Task): string {
    switch (state) {
      case Task.Created: {
        return '已创建';
      }
      case Task.Completed: {
        return '已完成';
      }
      default: {
        return '';a
      }
    }
  }
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "transform")
    }

    @Test
    fun enum_with_colon2() {
        val code = """export class Demo {
 get filter(): FilterInput {
    return this.fields.value ? filter : {
      ...filter,
      orgId: [province.id]
    };
  }
}
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "get")
    }

    @Test
    fun multiple_acc_modifier() {
        val code = """export class DataSource  {
    private readonly _subscription;           
    private readonly _dataStream = new BehaviorSubject<(string | undefined)[]>(this._cachedData);
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 0)
        assertEquals(codeFile.DataStructures[0].Fields.size, 2)
    }

    @Test
    fun const() {
        val code = """async function demo() {
   const source = edge.source!;
   const target = edge.target!;
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        val dataStructures = codeFile.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].Functions.size, 1)
        assertEquals(dataStructures[0].Functions[0].Name, "demo")
    }

    @Test
    @Disabled
    fun async() {
        val code = """test("unpaired wrapper", async () => {
  let tokens = lexer(`dep_name = '"\`/@`);
  expect(tokens.length).toBe(7);
});"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        println(Json.encodeToString(codeFile))

        val dataStructures = codeFile.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].Functions.size, 1)
        assertEquals(dataStructures[0].Functions[0].Name, "test")
    }

    @Test
    fun enum() {
        val code = """function valueTypeFromChar(char: string) {
  switch (char) {
    case SINGLE_QUOTE:
    case DOUBLE_QUOTE:
    case BACKTICK:
      return "string";
    case SLASH:
      return "regex";
    case AT:
      return "like";
    default:
      return "error";
  }
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        val dataStructures = codeFile.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].Functions.size, 1)
        assertEquals(dataStructures[0].Functions[0].Name, "valueTypeFromChar")
    }

    @Test
    fun arrow_in_arrow() {
        val code = """
export default function axiosAgent<T>(config: AxiosRequestConfig) {
  return (axiosInstance(config) as unknown) as Promise<T>;
}

export const axiosWithBaseURL = (baseURL: string) => <T>(
  config: Omit<AxiosRequestConfig, "baseURL">,
) => (axiosAgent({ ...config, baseURL }) as unknown) as Promise<T>;
"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        val dataStructures = codeFile.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].Functions.size, 2)
        assertEquals(dataStructures[0].Functions[0].Name, "axiosAgent")
        assertEquals(dataStructures[0].Functions[1].Name, "axiosWithBaseURL")
    }

    @Test
    fun type_before_function() {
        val code = """function useRequest<
  R = any,
  P extends any[] = any,
  U = any,
  UU extends U = any,
>(
  service: CombineService<R, P>,
  options: OptionsWithFormat<R, P, U, UU>,
): BaseResult<U, P>;"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")

        val dataStructures = codeFile.DataStructures
        assertEquals(dataStructures.size, 1)
        assertEquals(dataStructures[0].Functions.size, 1)
        assertEquals(dataStructures[0].Functions[0].Name, "useRequest")
    }

}

