package chapi.ast.typescriptast

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TypeScriptRegressionTest {

    @Test
    internal fun backend_arrow_function() {
        val code = """
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.test = void 0;
function test() {
    alert("test");
}
exports.test = test;
"""

        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun of_keyword_lost() {
        val code = """
import {EMPTY, Observable, of} from 'rxjs';

if (node in graph) {
  for (const n of graph[node]) {

  }
}

of(data)
"""

        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun member_dot_issue() {
        val code = """export class DemoComponent implements OnInit, ControlValueAccessor {
    ngOnInit(): void {
        this.onChange?.(userName || password || null);
    }
}"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun end_with_dot() {
        val code = """export class SomeComponent implements OnInit, ControlValueAccessor {
  constructor(
    private fb: FormBuilder,
  ) { }
} """
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun nested_type_type_error() {
        val code = """Object.entries(values).forEach(([key, value]: [string, string[]]) => {
     
});
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun array_in_for_loop() {
        val code = """this.names.forEach((it, index) => {
  multiSelect('', this.accessor[index])
})
"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun query_array_literal() {
        val code = """
export class DemoComponent implements OnInit, ControlValueAccessor {
  confirm(): void {
      return this.form.value?.id?.[0] || null
  }
}"""
        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun readonly_property() {
        val code = """class DataSource extends Data<Item> {
  readonly pageSize = 5;

  constructor(public readonly type: TaskEnum,) {
    super();
  }
}"""

        TypeScriptAnalyser().analysis(code, "index.tsx")
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

        TypeScriptAnalyser().analysis(code, "index.tsx")
    }

    @Test
    fun numeric_separators() {
        val code = """if (+value > 1_000_000_000) {
    }"""

        TypeScriptAnalyser().analysis(code, "index.tsx")
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
    fun private_issue2() {
        val code = """
export class PopupDirective {
  get taskTitle(): string {
    return this.isSubTask ? this.subTask?.name : this.commonTaskWorkOrder?.title;
  }
}"""

        val codeFile = TypeScriptAnalyser().analysis(code, "index.tsx")
        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions.size, 1)
        assertEquals(codeFile.DataStructures[0].Functions[0].Name, "get")
    }
}
