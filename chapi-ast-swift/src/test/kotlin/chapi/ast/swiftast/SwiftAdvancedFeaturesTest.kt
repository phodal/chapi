package chapi.ast.swiftast

import chapi.domain.core.DataStructType
import chapi.domain.core.TypeRefKind
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.Ignore

/**
 * Advanced feature tests for Swift including generics, closures, protocol extensions, etc.
 */
class SwiftAdvancedFeaturesTest {
    
    // ==================== Generic Tests ====================
    
    @Nested
    inner class GenericTests {
        @Test
        fun shouldParseGenericClass() {
            val code = """
                class Stack<Element> {
                    var items: [Element] = []
                    
                    func push(_ item: Element) {
                        items.append(item)
                    }
                    
                    func pop() -> Element? {
                        return items.popLast()
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "generic.swift")
            
            val stack = container.DataStructures.find { it.NodeName == "Stack" }
            assertNotNull(stack)
            assertEquals(DataStructType.CLASS, stack.Type)
            assertEquals(2, stack.Functions.size)
        }
        
        @Test
        fun shouldParseGenericFunction() {
            val code = """
                func swap<T>(_ a: inout T, _ b: inout T) {
                    let temp = a
                    a = b
                    b = temp
                }
                
                func findIndex<T: Equatable>(of valueToFind: T, in array: [T]) -> Int? {
                    for (index, value) in array.enumerated() {
                        if value == valueToFind {
                            return index
                        }
                    }
                    return nil
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "generic_func.swift")
            
            val topLevel = container.TopLevel
            assertNotNull(topLevel)
            
            val swapFunc = topLevel.Functions.find { it.Name == "swap" }
            assertNotNull(swapFunc)
            assertEquals(2, swapFunc.Parameters.size)
            
            val findIndexFunc = topLevel.Functions.find { it.Name == "findIndex" }
            assertNotNull(findIndexFunc)
        }
        
        @Test
        fun shouldParseGenericConstraints() {
            val code = """
                protocol Container {
                    associatedtype Item
                    func add(_ item: Item)
                }
                
                struct IntContainer: Container {
                    typealias Item = Int
                    func add(_ item: Int) {}
                }
                
                func process<T: Container>(container: T) where T.Item == Int {
                    // Process
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "constraints.swift")
            
            assertTrue(container.DataStructures.isNotEmpty())
        }
        
        @Test
        fun shouldParseMultipleGenericParameters() {
            val code = """
                class Dictionary<Key: Hashable, Value> {
                    private var storage: [Key: Value] = [:]
                    
                    func set(key: Key, value: Value) {
                        storage[key] = value
                    }
                    
                    func get(key: Key) -> Value? {
                        return storage[key]
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "dict.swift")
            
            val dict = container.DataStructures.find { it.NodeName == "Dictionary" }
            assertNotNull(dict)
            assertEquals(2, dict.Functions.size)
        }
    }
    
    // ==================== Closure Tests ====================
    
    @Nested
    inner class ClosureTests {
        @Test
        fun shouldParseTrailingClosure() {
            val code = """
                func processArray(numbers: [Int], transform: (Int) -> Int) -> [Int] {
                    return numbers.map(transform)
                }
                
                class Calculator {
                    func calculate() {
                        let doubled = processArray(numbers: [1, 2, 3]) { $0 * 2 }
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "closure.swift")
            
            val calcClass = container.DataStructures.find { it.NodeName == "Calculator" }
            assertNotNull(calcClass)
        }
        
        @Test
        fun shouldParseClosureParameters() {
            val code = """
                class Handler {
                    func fetchData(completion: @escaping (Result<String, Error>) -> Void) {
                        // Async operation
                    }
                    
                    func process(onSuccess: () -> Void, onError: (Error) -> Void) {
                        // Process
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "closure_params.swift")
            
            val handler = container.DataStructures.find { it.NodeName == "Handler" }
            assertNotNull(handler)
            assertEquals(2, handler.Functions.size)
        }
        
        @Test
        fun shouldParseEscapingClosure() {
            val code = """
                class AsyncService {
                    var completionHandlers: [() -> Void] = []
                    
                    func register(@escaping completion: () -> Void) {
                        completionHandlers.append(completion)
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "escaping.swift")
            
            val service = container.DataStructures.find { it.NodeName == "AsyncService" }
            assertNotNull(service)
        }
        
        @Test
        fun shouldParseAutoClosureParameter() {
            val code = """
                func assert(_ condition: @autoclosure () -> Bool, message: String) {
                    if !condition() {
                        print(message)
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "autoclosure.swift")
            
            val topLevel = container.TopLevel
            assertNotNull(topLevel)
            
            val assertFunc = topLevel.Functions.find { it.Name == "assert" }
            assertNotNull(assertFunc)
        }
    }
    
    // ==================== Protocol Extension Tests ====================
    
    @Nested
    inner class ProtocolExtensionTests {
        @Test
        fun shouldParseProtocolExtension() {
            val code = """
                protocol Printable {
                    func description() -> String
                }
                
                extension Printable {
                    func printDescription() {
                        print(description())
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "proto_ext.swift")
            
            val printable = container.DataStructures.find { it.NodeName == "Printable" }
            assertNotNull(printable)
            assertEquals(DataStructType.INTERFACE, printable.Type)
        }
        
        @Test
        fun shouldParseProtocolWithAssociatedType() {
            val code = """
                protocol Container {
                    associatedtype Item
                    var count: Int { get }
                    func append(_ item: Item)
                    subscript(i: Int) -> Item { get }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "associated_type.swift")
            
            val containerProto = container.DataStructures.find { it.NodeName == "Container" }
            assertNotNull(containerProto)
            assertEquals(DataStructType.INTERFACE, containerProto.Type)
        }
        
        @Test
        fun shouldParseProtocolComposition() {
            val code = """
                protocol Named {
                    var name: String { get }
                }
                
                protocol Aged {
                    var age: Int { get }
                }
                
                class Person: Named, Aged {
                    var name: String = ""
                    var age: Int = 0
                }
                
                func celebrate(_ entity: Named & Aged) {
                    print("\(entity.name) is \(entity.age) years old")
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "composition.swift")
            
            val person = container.DataStructures.find { it.NodeName == "Person" }
            assertNotNull(person)
            // Should implement at least one protocol
            assertTrue(person!!.Implements.isNotEmpty())
        }
    }
    
    // ==================== Enum Associated Values ====================
    
    @Nested
    inner class EnumAdvancedTests {
        @Test
        fun shouldParseEnumWithAssociatedValues() {
            val code = """
                enum Result {
                    case success(value: String)
                    case failure(error: Error)
                }
                
                enum Barcode {
                    case upc(Int, Int, Int, Int)
                    case qrCode(String)
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "enum_values.swift")
            
            val result = container.DataStructures.find { it.NodeName == "Result" }
            assertNotNull(result)
            assertEquals(DataStructType.ENUM, result.Type)
            
            val barcode = container.DataStructures.find { it.NodeName == "Barcode" }
            assertNotNull(barcode)
        }
        
        @Test
        fun shouldParseEnumWithMethods() {
            val code = """
                enum Direction {
                    case north, south, east, west
                    
                    func opposite() -> Direction {
                        switch self {
                        case .north: return .south
                        case .south: return .north
                        case .east: return .west
                        case .west: return .east
                        }
                    }
                    
                    var description: String {
                        switch self {
                        case .north: return "North"
                        case .south: return "South"
                        case .east: return "East"
                        case .west: return "West"
                        }
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "enum_methods.swift")
            
            val direction = container.DataStructures.find { it.NodeName == "Direction" }
            assertNotNull(direction)
            assertTrue(direction.Functions.isNotEmpty())
        }
        
        @Test
        fun shouldParseEnumRawValues() {
            val code = """
                enum HTTPStatus: Int {
                    case ok = 200
                    case notFound = 404
                    case serverError = 500
                }
                
                enum Planet: Int, CaseIterable {
                    case mercury = 1, venus, earth, mars
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "enum_raw.swift")
            
            val httpStatus = container.DataStructures.find { it.NodeName == "HTTPStatus" }
            assertNotNull(httpStatus)
            assertEquals("Int", httpStatus.Extend)
        }
    }
    
    // ==================== Property Wrapper Tests ====================
    
    @Nested
    inner class PropertyWrapperTests {
        @Test
        fun shouldParsePropertyWrapper() {
            val code = """
                @propertyWrapper
                struct Capitalized {
                    private var value: String = ""
                    
                    var wrappedValue: String {
                        get { value }
                        set { value = newValue.capitalized }
                    }
                }
                
                struct User {
                    @Capitalized var name: String = ""
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "wrapper.swift")
            
            val capitalized = container.DataStructures.find { it.NodeName == "Capitalized" }
            assertNotNull(capitalized)
            assertTrue(capitalized.Annotations.any { it.Name == "propertyWrapper" })
        }
        
        @Test
        @Ignore
        fun shouldParseBuiltInPropertyWrappers() {
            val code = """
                struct ViewModel {
                    @Published var count: Int = 0
                    @State private var isActive: Bool = false
                    @Binding var text: String
                    @Environment(\\.colorScheme) var colorScheme
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "builtin_wrappers.swift")
            
            val viewModel = container.DataStructures.find { it.NodeName == "ViewModel" }
            assertNotNull(viewModel)
            
            val publishedField = viewModel!!.Fields.find { it.TypeKey == "count" }
            assertNotNull(publishedField)
            // Property wrapper annotations should be captured
        }
    }
    
    // ==================== Result Builder Tests ====================
    
    @Nested
    inner class ResultBuilderTests {
        @Test
        fun shouldParseResultBuilder() {
            val code = """
                @resultBuilder
                struct StringBuilder {
                    static func buildBlock(_ parts: String...) -> String {
                        parts.joined()
                    }
                }
                
                @StringBuilder
                func makeGreeting() -> String {
                    "Hello"
                    " "
                    "World"
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "result_builder.swift")
            
            val stringBuilder = container.DataStructures.find { it.NodeName == "StringBuilder" }
            assertNotNull(stringBuilder)
            assertTrue(stringBuilder.Annotations.any { it.Name == "resultBuilder" })
        }
    }
    
    // ==================== Concurrency Tests (Swift 5.5+) ====================
    
    @Nested
    inner class ConcurrencyAdvancedTests {
        @Test
        fun shouldParseAsyncSequence() {
            val code = """
                struct AsyncNumberSequence: AsyncSequence {
                    typealias Element = Int
                    
                    struct AsyncIterator: AsyncIteratorProtocol {
                        var current = 0
                        
                        mutating func next() async -> Int? {
                            current += 1
                            return current <= 10 ? current : nil
                        }
                    }
                    
                    func makeAsyncIterator() -> AsyncIterator {
                        return AsyncIterator()
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "async_sequence.swift")
            
            val asyncSeq = container.DataStructures.find { it.NodeName == "AsyncNumberSequence" }
            assertNotNull(asyncSeq)
            assertTrue(asyncSeq.Implements.contains("AsyncSequence"))
        }
        
        @Test
        @Ignore
        fun shouldParseTaskGroups() {
            val code = """
                class TaskManager {
                    func fetchMultipleResources() async throws -> [String] {
                        return try await withThrowingTaskGroup(of: String.self) { group in
                            group.addTask { try await self.fetchResource1() }
                            group.addTask { try await self.fetchResource2() }
                            
                            var results: [String] = []
                            for try await result in group {
                                results.append(result)
                            }
                            return results
                        }
                    }
                    
                    func fetchResource1() async throws -> String { return "Resource1" }
                    func fetchResource2() async throws -> String { return "Resource2" }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "task_group.swift")
            
            val taskManager = container.DataStructures.find { it.NodeName == "TaskManager" }
            assertNotNull(taskManager)
            // Should have at least 2 functions (fetchResource1 and fetchResource2)
            assertTrue(taskManager!!.Functions.size >= 2)
        }
        
        @Test
        fun shouldParseMainActorAnnotation() {
            val code = """
                @MainActor
                class UIUpdater {
                    var count: Int = 0
                    
                    func updateUI() {
                        count += 1
                    }
                    
                    nonisolated func getData() -> Int {
                        return 42
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "main_actor.swift")
            
            val updater = container.DataStructures.find { it.NodeName == "UIUpdater" }
            assertNotNull(updater)
            assertTrue(updater.Annotations.any { it.Name == "MainActor" })
            
            val getData = updater.Functions.find { it.Name == "getData" }
            assertNotNull(getData)
            assertTrue(getData.Modifiers.contains("nonisolated"))
        }
    }
    
    // ==================== Opaque Types Tests ====================
    
    @Nested
    inner class OpaqueTypeTests {
        @Test
        fun shouldParseOpaqueReturnType() {
            val code = """
                protocol Shape {
                    func draw() -> String
                }
                
                struct Circle: Shape {
                    func draw() -> String { return "Circle" }
                }
                
                func makeShape() -> some Shape {
                    return Circle()
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "opaque.swift")
            
            val topLevel = container.TopLevel
            assertNotNull(topLevel)
            
            val makeShape = topLevel.Functions.find { it.Name == "makeShape" }
            assertNotNull(makeShape)
        }
    }
    
    // ==================== Sendable and Concurrency Tests ====================
    
    @Nested
    inner class SendableTests {
        @Test
        fun shouldParseSendableProtocol() {
            val code = """
                struct Message: Sendable {
                    let id: Int
                    let content: String
                }
                
                @Sendable
                func processMessage(_ message: Message) {
                    print(message.content)
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "sendable.swift")
            
            val message = container.DataStructures.find { it.NodeName == "Message" }
            assertNotNull(message)
            assertTrue(message.Implements.contains("Sendable"))
        }
    }
    
    // ==================== Subscript Tests ====================
    
    @Nested
    inner class SubscriptTests {
        @Test
        fun shouldParseSubscripts() {
            val code = """
                struct Matrix {
                    var data: [[Int]]
                    
                    subscript(row: Int, column: Int) -> Int {
                        get {
                            return data[row][column]
                        }
                        set {
                            data[row][column] = newValue
                        }
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "subscript.swift")
            
            val matrix = container.DataStructures.find { it.NodeName == "Matrix" }
            assertNotNull(matrix)
        }
    }
    
    // ==================== Macro Tests (Swift 5.9+) ====================
    
    @Nested
    inner class MacroTests {
        @Test
        fun shouldParseMacroDefinition() {
            val code = """
                @freestanding(expression)
                macro stringify<T>(_ value: T) -> (T, String)
                
                @attached(member)
                macro AddCompletionHandler()
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "macro.swift")
            
            // Macros are relatively new and may not be fully parsed yet
            // Just verify the code doesn't crash the parser
            assertNotNull(container)
        }
    }
    
    // ==================== Complex Type Inference ====================
    
    @Nested
    inner class TypeInferenceTests {
        @Test
        fun shouldHandleComplexTypeInference() {
            val code = """
                struct Container {
                    func map<T>(_ transform: (Int) -> T) -> [T] {
                        return []
                    }
                    
                    func complexChain() {
                        let result = [1, 2, 3]
                            .map { $0 * 2 }
                            .filter { $0 > 2 }
                            .reduce(0, +)
                    }
                }
            """.trimIndent()
            
            val container = SwiftAnalyser().analysis(code, "inference.swift")
            
            val containerStruct = container.DataStructures.find { it.NodeName == "Container" }
            assertNotNull(containerStruct)
        }
    }
}
