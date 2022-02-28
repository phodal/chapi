package chapi.ast.kotlinast.spring

import chapi.ast.kotlinast.AnalysisMode
import chapi.ast.kotlinast.KotlinAnalyser
import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * verify the kotlin analyser is compatible with spring boot
 * test case is from: https://github.com/archguard/archguard-backend
 */
internal class KotlinSpringCompatibleTest {

    @Nested
    inner class Controller {
        private val code = """
package com.thoughtworks.archguard.clazz.controller

import com.thoughtworks.archguard.clazz.domain.JClass
import com.thoughtworks.archguard.clazz.domain.service.ClassService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/systems/{systemId}/classes")
class ClassController(val service: ClassService) {

    @GetMapping("/{name}/dependencies")
    fun getDependencies(@PathVariable("systemId") systemId: Long,
                        @PathVariable("name") name: String,
                        @RequestParam(value = "module", required = false, defaultValue = "") module: String,
                        @RequestParam("deep", required = false, defaultValue = "3") deep: Int): JClass {
        return service.getDependencies(systemId, module, name, deep)
    }

    @GetMapping("/{name}/invokes")
    fun getInvokes(@PathVariable("systemId") systemId: Long,
                   @PathVariable("name") name: String,
                   @RequestParam(value = "module", required = false, defaultValue = "") module: String,
                   @RequestParam(value = "deep", required = false, defaultValue = "3") deep: Int,
                   @RequestParam(value = "callerDeep", required = false) callerDeep: Int?,
                   @RequestParam(value = "calleeDeep", required = false) calleeDeep: Int?,
                   @RequestParam(value = "needIncludeImpl", required = false, defaultValue = "true") needIncludeImpl: Boolean?): JClass {
        return service.findInvokes(systemId, module, name, callerDeep ?: deep, calleeDeep ?: deep, needIncludeImpl
                ?: true)
    }

    @GetMapping("/{name}/methods_callees")
    fun getMethodsCallees(@PathVariable("systemId") systemId: Long,
                          @PathVariable("name") name: String,
                          @RequestParam(value = "module", required = false, defaultValue = "") module: String,
                          @RequestParam(value = "deep", required = false, defaultValue = "3") deep: Int,
                          @RequestParam(value = "needParents", required = false, defaultValue = "true") needParents: Boolean,
                          @RequestParam(value = "needIncludeImpl", required = false, defaultValue = "true") needIncludeImpl: Boolean): JClass {
        return service.findMethodsCallees(systemId, module, name, deep, needIncludeImpl, needParents)
    }
}
"""

        @Test
        fun `should parse class info of the controller class`() {
            val codeContainer = KotlinAnalyser().analysis(code, "ClassController.kt", AnalysisMode.Basic)

            assertEquals(codeContainer.FullName, "ClassController.kt")
            assertEquals(codeContainer.PackageName, "com.thoughtworks.archguard.clazz.controller")
            assertEquals(codeContainer.Imports.size, 7)

            codeContainer.DataStructures[0].run {
                assertEquals(NodeName, "ClassController")
                assertEquals(Type, DataStructType.CLASS)
                assertEquals(Package, "com.thoughtworks.archguard.clazz.controller")
                assertEquals(FilePath, "ClassController.kt")
                assertEquals(Functions.size, 4)
                assertEquals(Annotations.size, 2)
            }
        }

        @Test
        fun `should parse annotations on class of the controller class`() {
            val codeContainer = KotlinAnalyser().analysis(code, "ClassController.kt", AnalysisMode.Basic)

            codeContainer.DataStructures[0].run {
                assertEquals(Annotations.size, 2)
                assertEquals(Annotations[0].Name, "RestController")
                assertEquals(Annotations[1].Name, "RequestMapping")
                assertEquals(Annotations[1].KeyValues[0].Key, "value")
                assertEquals(Annotations[1].KeyValues[0].Value, "\"/systems/{systemId}/classes\"")
            }
        }

        @Test
        fun `should parse constructor and functions of the controller class`() {
            val codeContainer = KotlinAnalyser().analysis(code, "ClassController.kt", AnalysisMode.Basic)

            codeContainer.DataStructures[0].Functions[0].run {
                assertEquals(Name, "PrimaryConstructor")
                assertEquals(IsConstructor, true)
                assertEquals(Parameters.size, 1)
                assertEquals(Parameters[0].TypeValue, "service")
                assertEquals(Parameters[0].TypeType, "com.thoughtworks.archguard.clazz.domain.service.ClassService")
            }

            codeContainer.DataStructures[0].Functions[1].run {
                assertEquals(Name, "getDependencies")
                assertEquals(IsConstructor, false)
                assertEquals(Parameters.size, 4)
            }

            codeContainer.DataStructures[0].Functions[2].run {
                assertEquals(Name, "getInvokes")
                assertEquals(IsConstructor, false)
                assertEquals(Parameters.size, 7)
            }

            codeContainer.DataStructures[0].Functions[3].run {
                assertEquals(Name, "getMethodsCallees")
                assertEquals(IsConstructor, false)
                assertEquals(Parameters.size, 6)
            }
        }

        @Test
        fun `should parse annotations on functions of the controller class`() {
            val codeContainer = KotlinAnalyser().analysis(code, "ClassController.kt", AnalysisMode.Basic)

            codeContainer.DataStructures[0].Functions[1].run {
                assertEquals(Annotations[0].Name, "GetMapping")
                assertEquals(Annotations[0].KeyValues[0].Key, "value")
                assertEquals(Annotations[0].KeyValues[0].Value, "\"/{name}/dependencies\"")

            }
            codeContainer.DataStructures[0].Functions[2].run {
                assertEquals(Annotations[0].Name, "GetMapping")
                assertEquals(Annotations[0].KeyValues[0].Key, "value")
                assertEquals(Annotations[0].KeyValues[0].Value, "\"/{name}/invokes\"")
            }
            codeContainer.DataStructures[0].Functions[3].run {
                assertEquals(Annotations[0].Name, "GetMapping")
                assertEquals(Annotations[0].KeyValues[0].Key, "value")
                assertEquals(Annotations[0].KeyValues[0].Value, "\"/{name}/methods_callees\"")
            }
        }

        @Test
        fun `should parse annotations on parameters of the controller class`() {
            val codeContainer = KotlinAnalyser().analysis(code, "ClassController.kt", AnalysisMode.Basic)

            codeContainer.DataStructures[0].Functions[1].run {
                assertEquals(Parameters[0].Annotations[0].Name, "PathVariable")
                assertEquals(Parameters[0].Annotations[0].KeyValues[0].Value, "\"systemId\"")

                assertEquals(Parameters[1].Annotations[0].Name, "PathVariable")
                assertEquals(Parameters[1].Annotations[0].KeyValues[0].Value, "\"name\"")

                assertEquals(Parameters[2].Annotations[0].Name, "RequestParam")
                assertEquals(Parameters[2].Annotations[0].KeyValues[0].Value, "\"module\"")
                assertEquals(Parameters[2].Annotations[0].KeyValues[1].Key, "required")
                assertEquals(Parameters[2].Annotations[0].KeyValues[1].Value, "false")
                assertEquals(Parameters[2].Annotations[0].KeyValues[2].Key, "defaultValue")
                assertEquals(Parameters[2].Annotations[0].KeyValues[2].Value, "\"\"")

                assertEquals(Parameters[3].Annotations[0].Name, "RequestParam")
                assertEquals(Parameters[3].Annotations[0].KeyValues[0].Value, "\"deep\"")
                assertEquals(Parameters[3].Annotations[0].KeyValues[1].Key, "required")
                assertEquals(Parameters[3].Annotations[0].KeyValues[1].Value, "false")
                assertEquals(Parameters[3].Annotations[0].KeyValues[2].Key, "defaultValue")
                assertEquals(Parameters[3].Annotations[0].KeyValues[2].Value, "\"3\"")
            }
        }
    }
}
