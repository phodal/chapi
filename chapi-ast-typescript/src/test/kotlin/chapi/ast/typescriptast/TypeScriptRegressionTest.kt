package chapi.ast.typescriptast

import org.junit.jupiter.api.Test

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
}
