package chapi.ast.typescriptast

import org.junit.jupiter.api.Test

class TypeScriptBugTest {

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
}
