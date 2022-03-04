package chapi.ast.typescriptast

import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class TypeScriptAnalyserTest {
    @Test
    internal fun shouldAnalysisTypeScriptMultipleClass() {
        val content = this::class.java.getResource("/grammar/Class.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 3)
        assertEquals(codeFile.DataStructures[1].Implements.size, 1)
        assertEquals(codeFile.DataStructures[1].Implements[0], "IPerson")
        assertEquals(codeFile.DataStructures[1].Fields.size, 5)
        assertEquals(codeFile.DataStructures[1].Fields[0].Modifiers[0], "public")
    }

    @Test
    internal fun shouldAnalysisTypeScriptAbstractClass() {
        val content = this::class.java.getResource("/grammar/AbstractClass.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 2)
        assertEquals(codeFile.DataStructures[0].Type, DataStructType.CLASS)
        assertEquals(codeFile.DataStructures[1].NodeName, "Employee")
        assertEquals(codeFile.DataStructures[1].Extend, "Person")
    }

    @Test
    internal fun shouldAnalysisTypeScriptModule() {
        val content = this::class.java.getResource("/grammar/Module.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "Employee")
    }

    @Test
    internal fun shouldAnalysisTypeScriptFunctions() {
        val content = this::class.java.getResource("/grammar/Function.ts").readText()
        val codeFile = TypeScriptAnalyser().analysis(content, "")

        assertEquals(codeFile.DataStructures.size, 1)
        assertEquals(codeFile.DataStructures[0].NodeName, "default")
        val functions = codeFile.DataStructures[0].Functions
        assertEquals(functions.size, 9)
    }

    @Test
    internal fun shouldIdentTypeScriptHelloWorldFunCall() {
        val content = """
let greeting = function() {
    console.log("Hello TypeScript!");
};
        """

        val codeFile = TypeScriptAnalyser().analysis(content, "")

        val firstFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals(firstFunc.FunctionCalls.size, 1)
        assertEquals(firstFunc.FunctionCalls[0].NodeName, "console")
        assertEquals(firstFunc.FunctionCalls[0].FunctionName, "log")
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
        assertEquals(annotations.size, 1)
        assertEquals(annotations[0].Name, "Injectable")
    }

    @Test @Disabled
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
        assertEquals(annotations.size, 1)
    }
}
