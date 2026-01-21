package chapi.ast.swiftast

import chapi.domain.core.ContainerKind
import chapi.domain.core.DataStructType
import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SwiftAnalyserTest {
    
    @Test
    fun shouldParseImportsAndTypes() {
        val code = """
            import Foundation
            import SwiftUI

            enum State {
              case idle
              case loading
            }

            struct User {
              let id: Int
              func name() -> String { return "u" }
            }

            class Service {
              func fetch() -> Int { return 1 }
            }

            func topLevel() -> Void { }
        """.trimIndent()

        val container = SwiftAnalyser().analysis(code, "example.swift")

        assertEquals("swift", container.Language)
        assertEquals(ContainerKind.SOURCE_FILE, container.Kind)

        assertEquals(2, container.Imports.size)
        assertEquals("Foundation", container.Imports[0].Source)
        assertEquals("SwiftUI", container.Imports[1].Source)

        val enumDs = container.DataStructures.firstOrNull { it.NodeName == "State" }
        assertNotNull(enumDs)
        assertEquals(DataStructType.ENUM, enumDs.Type)

        val structDs = container.DataStructures.firstOrNull { it.NodeName == "User" }
        assertNotNull(structDs)
        assertEquals(DataStructType.STRUCT, structDs.Type)
        assertTrue(structDs.Functions.any { it.Name == "name" })

        val classDs = container.DataStructures.firstOrNull { it.NodeName == "Service" }
        assertNotNull(classDs)
        assertEquals(DataStructType.CLASS, classDs.Type)
        assertTrue(classDs.Functions.any { it.Name == "fetch" })

        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        assertTrue(topLevel.Functions.any { it.Name == "topLevel" })
    }
    
    @Test
    fun shouldParseFunctionParameters() {
        val code = """
            func greet(name: String, age: Int = 18) -> String {
                return "Hello"
            }
            
            func calculate(_ value: Int, with multiplier: Double) -> Double {
                return Double(value) * multiplier
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "params.swift")
        
        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        assertEquals(2, topLevel.Functions.size)
        
        val greet = topLevel.Functions.find { it.Name == "greet" }
        assertNotNull(greet)
        assertEquals(2, greet.Parameters.size)
        assertEquals("name", greet.Parameters[0].TypeValue)
        assertEquals("String", greet.Parameters[0].TypeType)
        assertEquals("age", greet.Parameters[1].TypeValue)
        assertEquals("Int", greet.Parameters[1].TypeType)
        assertEquals("18", greet.Parameters[1].DefaultValue)
        
        val calculate = topLevel.Functions.find { it.Name == "calculate" }
        assertNotNull(calculate)
        assertEquals(2, calculate.Parameters.size)
        assertEquals("Double", calculate.ReturnType)
    }
    
    @Test
    fun shouldParseClassInheritance() {
        val code = """
            class Animal {
                var name: String = ""
            }
            
            class Dog: Animal, Hashable, Equatable {
                var breed: String = ""
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "inheritance.swift")
        
        val dog = container.DataStructures.find { it.NodeName == "Dog" }
        assertNotNull(dog)
        assertEquals(DataStructType.CLASS, dog.Type)
        assertEquals("Animal", dog.Extend)
        assertTrue(dog.Implements.contains("Hashable"))
        assertTrue(dog.Implements.contains("Equatable"))
    }
    
    @Test
    fun shouldParseProtocol() {
        val code = """
            protocol Drawable {
                var color: String { get set }
                func draw() -> Void
                func resize(width: Int, height: Int)
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "protocol.swift")
        
        val drawable = container.DataStructures.find { it.NodeName == "Drawable" }
        assertNotNull(drawable)
        assertEquals(DataStructType.INTERFACE, drawable.Type)
        
        // Protocol property
        val colorField = drawable.Fields.find { it.TypeKey == "color" }
        assertNotNull(colorField)
        assertEquals("String", colorField.TypeType)
        
        // Protocol methods
        assertTrue(drawable.Functions.any { it.Name == "draw" })
        val resize = drawable.Functions.find { it.Name == "resize" }
        assertNotNull(resize)
        assertEquals(2, resize.Parameters.size)
    }
    
    @Test
    fun shouldParseExtension() {
        val code = """
            extension String: CustomStringConvertible {
                func trimmed() -> String {
                    return self.trimmingCharacters(in: .whitespaces)
                }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "extension.swift")
        
        val stringExt = container.DataStructures.find { it.NodeName == "String" }
        assertNotNull(stringExt)
        assertTrue(stringExt.Implements.contains("CustomStringConvertible"))
        assertTrue(stringExt.Functions.any { it.Name == "trimmed" })
    }
    
    @Test
    fun shouldParseInitializer() {
        val code = """
            class Person {
                var name: String
                var age: Int
                
                init(name: String, age: Int) {
                    self.name = name
                    self.age = age
                }
                
                init?(json: [String: Any]) {
                    guard let name = json["name"] as? String else { return nil }
                    self.name = name
                    self.age = 0
                }
                
                deinit {
                    print("Person deallocated")
                }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "init.swift")
        
        val person = container.DataStructures.find { it.NodeName == "Person" }
        assertNotNull(person)
        
        // Regular init
        val regularInit = person.Functions.find { it.Name == "init" && it.Parameters.size == 2 }
        assertNotNull(regularInit)
        assertTrue(regularInit.IsConstructor)
        
        // Failable init
        val failableInit = person.Functions.find { it.Name == "init?" }
        assertNotNull(failableInit)
        assertTrue(failableInit.IsConstructor)
        
        // Deinit
        val deinit = person.Functions.find { it.Name == "deinit" }
        assertNotNull(deinit)
    }
    
    @Test
    fun shouldParseStructWithFields() {
        val code = """
            struct Point {
                let x: Double
                let y: Double
                var label: String = "point"
                
                func distance(to other: Point) -> Double {
                    return 0.0
                }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "struct.swift")
        
        val point = container.DataStructures.find { it.NodeName == "Point" }
        assertNotNull(point)
        assertEquals(DataStructType.STRUCT, point.Type)
        
        assertEquals(3, point.Fields.size)
        
        val xField = point.Fields.find { it.TypeKey == "x" }
        assertNotNull(xField)
        assertEquals("Double", xField.TypeType)
        assertTrue(xField.Modifiers.contains("let"))
        
        val labelField = point.Fields.find { it.TypeKey == "label" }
        assertNotNull(labelField)
        assertEquals("\"point\"", labelField.TypeValue)
    }
    
    @Test
    fun shouldParseEnumWithCases() {
        val code = """
            enum NetworkError: Error {
                case timeout
                case notFound
                case serverError(code: Int)
            }
            
            enum Status: String {
                case active = "active"
                case inactive = "inactive"
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "enum.swift")
        
        val networkError = container.DataStructures.find { it.NodeName == "NetworkError" }
        assertNotNull(networkError)
        assertEquals(DataStructType.ENUM, networkError.Type)
        assertTrue(networkError.Implements.contains("Error"))
        assertEquals(3, networkError.Fields.size)
        
        val status = container.DataStructures.find { it.NodeName == "Status" }
        assertNotNull(status)
        assertEquals("String", status.Extend)
        
        val activeCase = status.Fields.find { it.TypeKey == "active" }
        assertNotNull(activeCase)
        assertEquals("\"active\"", activeCase.TypeValue)
    }
    
    @Test
    fun shouldParseAttributes() {
        val code = """
            @available(iOS 13.0, *)
            @MainActor
            class ViewController {
                @Published var count: Int = 0
                
                @discardableResult
                func increment() -> Int {
                    count += 1
                    return count
                }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "attributes.swift")
        
        val vc = container.DataStructures.find { it.NodeName == "ViewController" }
        assertNotNull(vc)
        assertTrue(vc.Annotations.any { it.Name == "available" })
        assertTrue(vc.Annotations.any { it.Name == "MainActor" })
        
        val countField = vc.Fields.find { it.TypeKey == "count" }
        assertNotNull(countField)
        assertTrue(countField.Annotations.any { it.Name == "Published" })
        
        val increment = vc.Functions.find { it.Name == "increment" }
        assertNotNull(increment)
        assertTrue(increment.Annotations.any { it.Name == "discardableResult" })
    }
    
    @Test
    fun shouldParseAccessModifiers() {
        val code = """
            public class APIClient {
                private var token: String = ""
                internal var baseURL: String = ""
                public private(set) var lastResponse: String = ""
                
                public func request() -> Void { }
                private func authenticate() -> Bool { return true }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "modifiers.swift")
        
        val client = container.DataStructures.find { it.NodeName == "APIClient" }
        assertNotNull(client)
        // Modifiers are stored as annotations for CodeDataStruct
        assertTrue(client.Annotations.any { it.Name == "public" })
        
        val tokenField = client.Fields.find { it.TypeKey == "token" }
        assertNotNull(tokenField)
        assertTrue(tokenField.Modifiers.contains("private"))
        
        val lastResponseField = client.Fields.find { it.TypeKey == "lastResponse" }
        assertNotNull(lastResponseField)
        assertTrue(lastResponseField.Modifiers.any { it.contains("public") })
        
        val requestFunc = client.Functions.find { it.Name == "request" }
        assertNotNull(requestFunc)
        assertTrue(requestFunc.Modifiers.contains("public"))
    }
    
    @Test
    fun shouldParseNestedTypes() {
        val code = """
            struct Outer {
                struct Inner {
                    var value: Int = 0
                }
                
                enum Status {
                    case active
                    case inactive
                }
                
                var inner: Inner = Inner()
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "nested.swift")
        
        val outer = container.DataStructures.find { it.NodeName == "Outer" }
        assertNotNull(outer)
        assertEquals(2, outer.InnerStructures.size)
        
        val inner = outer.InnerStructures.find { it.NodeName == "Inner" }
        assertNotNull(inner)
        assertEquals(DataStructType.STRUCT, inner.Type)
        
        val status = outer.InnerStructures.find { it.NodeName == "Status" }
        assertNotNull(status)
        assertEquals(DataStructType.ENUM, status.Type)
    }
    
    @Test
    fun shouldParseThrowingFunctions() {
        val code = """
            func loadData() throws -> Data {
                throw NSError()
            }
            
            func transform<T>(_ value: T) rethrows -> T {
                return value
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "throws.swift")
        
        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        
        val loadData = topLevel.Functions.find { it.Name == "loadData" }
        assertNotNull(loadData)
        assertTrue(loadData.Modifiers.contains("throws"))
        
        val transform = topLevel.Functions.find { it.Name == "transform" }
        assertNotNull(transform)
        assertTrue(transform.Modifiers.contains("rethrows"))
    }
}

// ==================== Swift 6 Features Tests ====================

class Swift6FeaturesTest {
    
    @Test
    fun shouldParseTypedThrows() {
        val code = """
            enum NetworkError: Error {
                case timeout
                case notFound
            }
            
            func fetchData() throws(NetworkError) -> Data {
                throw .timeout
            }
            
            func process() throws -> String {
                return ""
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "typed_throws.swift")
        
        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        
        val fetchData = topLevel.Functions.find { it.Name == "fetchData" }
        assertNotNull(fetchData)
        assertTrue(fetchData.Modifiers.any { it.contains("throws(NetworkError)") })
        
        val process = topLevel.Functions.find { it.Name == "process" }
        assertNotNull(process)
        assertTrue(process.Modifiers.contains("throws"))
    }
    
    @Test
    fun shouldParseAsyncFunctions() {
        val code = """
            func fetchUser() async -> User {
                return User()
            }
            
            func fetchWithError() async throws -> Data {
                return Data()
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "async.swift")
        
        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        
        val fetchUser = topLevel.Functions.find { it.Name == "fetchUser" }
        assertNotNull(fetchUser)
        assertTrue(fetchUser.Modifiers.contains("async"))
        
        val fetchWithError = topLevel.Functions.find { it.Name == "fetchWithError" }
        assertNotNull(fetchWithError)
        assertTrue(fetchWithError.Modifiers.contains("async"))
        assertTrue(fetchWithError.Modifiers.contains("throws"))
    }
    
    @Test
    fun shouldParseActorDeclaration() {
        val code = """
            actor BankAccount {
                var balance: Double = 0.0
                
                func deposit(amount: Double) {
                    balance += amount
                }
            }
            
            distributed actor GamePlayer {
                var score: Int = 0
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "actor.swift")
        
        val bankAccount = container.DataStructures.find { it.NodeName == "BankAccount" }
        assertNotNull(bankAccount)
        assertTrue(bankAccount.Functions.any { it.Name == "deposit" })
        assertTrue(bankAccount.Fields.any { it.TypeKey == "balance" })
        
        val gamePlayer = container.DataStructures.find { it.NodeName == "GamePlayer" }
        assertNotNull(gamePlayer)
        assertTrue(gamePlayer.Annotations.any { it.Name == "distributed" })
    }
    
    @Test
    fun shouldParseOwnershipModifiers() {
        val code = """
            struct FileHandle: ~Copyable {
                func read() -> Data { return Data() }
            }
            
            func process(consuming handle: FileHandle) {
                // handle is consumed
            }
            
            func inspect(borrowing handle: FileHandle) {
                // handle is borrowed
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "ownership.swift")
        
        val fileHandle = container.DataStructures.find { it.NodeName == "FileHandle" }
        assertNotNull(fileHandle)
        // Check for ~Copyable in inheritance
        assertTrue(fileHandle.Implements.any { it.contains("Copyable") })
        
        val topLevel = container.TopLevel
        assertNotNull(topLevel)
        
        val processFunc = topLevel.Functions.find { it.Name == "process" }
        assertNotNull(processFunc)
        val consumingParam = processFunc.Parameters.find { it.TypeValue == "handle" }
        assertNotNull(consumingParam)
        assertTrue(consumingParam.Modifiers.contains("consuming"))
        
        val inspectFunc = topLevel.Functions.find { it.Name == "inspect" }
        assertNotNull(inspectFunc)
        val borrowingParam = inspectFunc.Parameters.find { it.TypeValue == "handle" }
        assertNotNull(borrowingParam)
        assertTrue(borrowingParam.Modifiers.contains("borrowing"))
    }
    
    @Test
    fun shouldParseNonisolatedModifier() {
        val code = """
            actor DataStore {
                nonisolated let id: String = "store"
                
                nonisolated func description() -> String {
                    return id
                }
            }
        """.trimIndent()
        
        val container = SwiftAnalyser().analysis(code, "nonisolated.swift")
        
        val dataStore = container.DataStructures.find { it.NodeName == "DataStore" }
        assertNotNull(dataStore)
        
        val descFunc = dataStore.Functions.find { it.Name == "description" }
        assertNotNull(descFunc)
        assertTrue(descFunc.Modifiers.contains("nonisolated"))
    }
    
    @Test
    fun shouldParseSwiftUIApp() {
        val code = this::class.java.getResource("/swiftui_app.swift")?.readText()
        if (code == null) {
            // Skip if resource not found
            return
        }
        
        val container = SwiftAnalyser().analysis(code, "swiftui_app.swift")
        
        assertTrue(container.Imports.isNotEmpty())
        assertTrue(container.Imports.any { it.Source == "SwiftUI" })
        assertTrue(container.Imports.any { it.Source == "Combine" })
        
        // Check for main app
        val myApp = container.DataStructures.find { it.NodeName == "MyApp" }
        assertNotNull(myApp)
        assertTrue(myApp!!.Annotations.any { it.Name == "main" })
        
        // Check for ObservableObject
        val appState = container.DataStructures.find { it.NodeName == "AppState" }
        assertNotNull(appState)
        assertEquals(DataStructType.CLASS, appState!!.Type)
        
        // Check for Codable structs
        val user = container.DataStructures.find { it.NodeName == "User" }
        assertNotNull(user)
        assertTrue(user!!.Implements.any { it.contains("Codable") })
        assertTrue(user.Implements.any { it.contains("Identifiable") })
        
        // Check for Views
        val contentView = container.DataStructures.find { it.NodeName == "ContentView" }
        assertNotNull(contentView)
        assertTrue(contentView!!.Implements.contains("View"))
        
        // Check for actors
        val authService = container.DataStructures.find { it.NodeName == "AuthService" }
        assertNotNull(authService)
        
        // Check for property wrappers
        val clamped = container.DataStructures.find { it.NodeName == "Clamped" }
        assertNotNull(clamped)
        assertTrue(clamped!!.Annotations.any { it.Name == "propertyWrapper" })
        
        // Check for protocols with associated types
        val repository = container.DataStructures.find { it.NodeName == "Repository" }
        assertNotNull(repository)
        assertEquals(DataStructType.INTERFACE, repository!!.Type)
    }
}

class SwiftTypeRefBuilderTest {
    
    @Test
    fun shouldParseSimpleType() {
        val ref = SwiftTypeRefBuilder.build("String")
        assertEquals("String", ref.name)
        assertEquals(TypeRefKind.SIMPLE, ref.kind)
    }
    
    @Test
    fun shouldParseOptionalType() {
        val ref = SwiftTypeRefBuilder.build("String?")
        assertEquals("String", ref.name)
        assertTrue(ref.nullable)
    }
    
    @Test
    fun shouldParseArrayType() {
        val ref = SwiftTypeRefBuilder.build("[Int]")
        assertEquals(TypeRefKind.ARRAY, ref.kind)
        assertEquals("Int", ref.valueType?.name)
    }
    
    @Test
    fun shouldParseDictionaryType() {
        val ref = SwiftTypeRefBuilder.build("[String: Int]")
        assertEquals(TypeRefKind.MAP, ref.kind)
        assertEquals("String", ref.keyType?.name)
        assertEquals("Int", ref.valueType?.name)
    }
    
    @Test
    fun shouldParseGenericType() {
        val ref = SwiftTypeRefBuilder.build("Array<String>")
        assertEquals(TypeRefKind.GENERIC, ref.kind)
        assertEquals("Array", ref.name)
        assertEquals(1, ref.args.size)
        assertEquals("String", ref.args[0].name)
    }
    
    @Test
    fun shouldParseTupleType() {
        val ref = SwiftTypeRefBuilder.build("(Int, String)")
        assertEquals(TypeRefKind.TUPLE, ref.kind)
        assertEquals(2, ref.tupleElements.size)
        assertEquals("Int", ref.tupleElements[0].name)
        assertEquals("String", ref.tupleElements[1].name)
    }
    
    @Test
    fun shouldParseFunctionType() {
        val ref = SwiftTypeRefBuilder.build("(Int, String) -> Bool")
        assertEquals(TypeRefKind.FUNCTION, ref.kind)
        assertEquals(2, ref.parameterTypes.size)
        assertEquals("Bool", ref.returnType?.name)
    }
    
    @Test
    fun shouldParseProtocolComposition() {
        val ref = SwiftTypeRefBuilder.build("Hashable & Equatable")
        assertEquals(TypeRefKind.INTERSECTION, ref.kind)
        assertEquals(2, ref.intersection.size)
    }
}
