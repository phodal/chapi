package chapi.ast.csharpast

import chapi.domain.core.CallType
import chapi.domain.core.DataStructType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Ignore
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Advanced C# feature tests including LINQ, async/await, properties, events, etc.
 */
internal class CSharpAdvancedFeaturesTest {
    
    // ==================== LINQ Tests ====================
    
    @Nested
    inner class LinqTests {
        @Test
        fun shouldIdentifyLinqQueryExpression() {
            val code = """
                using System.Linq;
                
                namespace TestApp {
                    public class DataProcessor {
                        public void ProcessData() {
                            var numbers = new[] { 1, 2, 3, 4, 5 };
                            var query = from n in numbers
                                        where n > 2
                                        select n * 2;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val processor = container.DataStructures[0]
            
            assertEquals("DataProcessor", processor.NodeName)
            val processData = processor.Functions.find { it.Name == "ProcessData" }
            assertNotNull(processData)
        }
        
        @Test
        fun shouldIdentifyLinqMethodSyntax() {
            val code = """
                using System.Linq;
                
                namespace TestApp {
                    public class QueryService {
                        public void Query() {
                            var items = new[] { 1, 2, 3 };
                            var filtered = items.Where(x => x > 1)
                                               .Select(x => x * 2)
                                               .OrderBy(x => x)
                                               .ToList();
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val service = container.DataStructures[0]
            
            val queryMethod = service.Functions.find { it.Name == "Query" }
            assertNotNull(queryMethod)
            assertTrue(queryMethod.FunctionCalls.isNotEmpty())
        }
        
        @Test
        fun shouldIdentifyComplexLinqQuery() {
            val code = """
                using System.Linq;
                
                namespace TestApp {
                    public class AdvancedQuery {
                        public void ComplexQuery() {
                            var query = from user in users
                                       join order in orders on user.Id equals order.UserId
                                       where user.IsActive && order.Total > 100
                                       group order by user.Name into userOrders
                                       select new {
                                           UserName = userOrders.Key,
                                           OrderCount = userOrders.Count(),
                                           TotalAmount = userOrders.Sum(o => o.Total)
                                       };
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertEquals(1, container.DataStructures.size)
        }
    }
    
    // ==================== Async/Await Tests ====================
    
    @Nested
    inner class AsyncAwaitTests {
        @Test
        fun shouldIdentifyAsyncMethod() {
            val code = """
                using System.Threading.Tasks;
                
                namespace TestApp {
                    public class AsyncService {
                        public async Task<string> FetchDataAsync() {
                            await Task.Delay(1000);
                            return "Data";
                        }
                        
                        public async Task ProcessAsync() {
                            await Task.Run(() => {
                                // Process
                            });
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val service = container.DataStructures[0]
            
            assertEquals(2, service.Functions.size)
            
            val fetchMethod = service.Functions.find { it.Name == "FetchDataAsync" }
            assertNotNull(fetchMethod)
            assertTrue(fetchMethod!!.Modifiers.contains("async"))
        }
        
        @Test
        fun shouldIdentifyAsyncVoidMethod() {
            val code = """
                using System.Threading.Tasks;
                
                namespace TestApp {
                    public class EventHandler {
                        public async void OnButtonClick() {
                            await Task.Delay(100);
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val handler = container.DataStructures[0]
            
            val onButtonClick = handler.Functions.find { it.Name == "OnButtonClick" }
            assertNotNull(onButtonClick)
            assertTrue(onButtonClick!!.Modifiers.contains("async"))
        }
        
        @Test
        fun shouldIdentifyAsyncLambda() {
            val code = """
                using System.Threading.Tasks;
                
                namespace TestApp {
                    public class TaskRunner {
                        public void RunTasks() {
                            Func<Task<int>> asyncFunc = async () => {
                                await Task.Delay(100);
                                return 42;
                            };
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertTrue(container.DataStructures.isNotEmpty())
        }
        
        @Test
        fun shouldIdentifyConfigureAwait() {
            val code = """
                using System.Threading.Tasks;
                
                namespace TestApp {
                    public class AsyncHelper {
                        public async Task<string> GetDataAsync() {
                            var result = await FetchAsync().ConfigureAwait(false);
                            return result;
                        }
                        
                        private Task<string> FetchAsync() {
                            return Task.FromResult("data");
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val helper = container.DataStructures[0]
            
            assertEquals(2, helper.Functions.size)
        }
    }
    
    // ==================== Property Tests ====================
    
    @Nested
    inner class PropertyTests {
        @Test
        fun shouldIdentifyAutoProperties() {
            val code = """
                namespace TestApp {
                    public class Person {
                        public string Name { get; set; }
                        public int Age { get; private set; }
                        public string Email { get; init; }
                        public int Id { get; }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val person = container.DataStructures[0]
            
            assertEquals(4, person.Fields.size)
            
            val nameField = person.Fields.find { it.TypeValue == "Name" }
            assertNotNull(nameField)
            assertEquals("string", nameField!!.TypeType)
        }
        
        @Test
        fun shouldIdentifyPropertyWithBackingField() {
            val code = """
                namespace TestApp {
                    public class Account {
                        private decimal _balance;
                        
                        public decimal Balance {
                            get { return _balance; }
                            set {
                                if (value >= 0)
                                    _balance = value;
                            }
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val account = container.DataStructures[0]
            
            assertTrue(account.Fields.size >= 1)
        }
        
        @Test
        fun shouldIdentifyExpressionBodiedProperty() {
            val code = """
                namespace TestApp {
                    public class Rectangle {
                        public double Width { get; set; }
                        public double Height { get; set; }
                        
                        public double Area => Width * Height;
                        public double Perimeter => 2 * (Width + Height);
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val rectangle = container.DataStructures[0]
            
            assertTrue(rectangle.Fields.size >= 2)
        }
        
        @Test
        fun shouldIdentifyIndexerProperty() {
            val code = """
                namespace TestApp {
                    public class Collection {
                        private string[] items = new string[10];
                        
                        public string this[int index] {
                            get { return items[index]; }
                            set { items[index] = value; }
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val collection = container.DataStructures[0]
            
            assertNotNull(collection)
        }
    }
    
    // ==================== Event Tests ====================
    
    @Nested
    inner class EventTests {
        @Test
        @Ignore
        fun shouldIdentifyEvents() {
            val code = """
                using System;
                
                namespace TestApp {
                    public class Button {
                        public event EventHandler Click;
                        public event EventHandler<MouseEventArgs> MouseMove;
                        
                        protected virtual void OnClick() {
                            Click?.Invoke(this, EventArgs.Empty);
                        }
                    }
                }
            """.trimIndent()

            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val button = container.DataStructures[0]

            assertTrue(button.Fields.size >= 2)
        }

        @Test
        fun shouldIdentifyCustomEventAccessors() {
            val code = """
                using System;
                
                namespace TestApp {
                    public class Publisher {
                        private EventHandler _dataReceived;
                        
                        public event EventHandler DataReceived {
                            add { _dataReceived += value; }
                            remove { _dataReceived -= value; }
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val publisher = container.DataStructures[0]
            
            assertTrue(publisher.Fields.isNotEmpty())
        }
    }
    
    // ==================== Delegate and Lambda Tests ====================
    
    @Nested
    inner class DelegateTests {
        @Test
        fun shouldIdentifyDelegateDeclaration() {
            val code = """
                namespace TestApp {
                    public delegate void ProcessHandler(string data);
                    public delegate int CompareFunc(int a, int b);
                    
                    public class DelegateUser {
                        public void UseDelegate() {
                            ProcessHandler handler = (data) => {
                                Console.WriteLine(data);
                            };
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertTrue(container.DataStructures.isNotEmpty())
        }
        
        @Test
        fun shouldIdentifyLambdaExpressions() {
            val code = """
                using System.Linq;
                
                namespace TestApp {
                    public class LambdaExample {
                        public void Process() {
                            Func<int, int> square = x => x * x;
                            Action<string> print = s => Console.WriteLine(s);
                            
                            var numbers = new[] { 1, 2, 3 };
                            var result = numbers.Select(n => n * 2);
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val example = container.DataStructures[0]
            
            val processMethod = example.Functions.find { it.Name == "Process" }
            assertNotNull(processMethod)
        }
        
        @Test
        fun shouldIdentifyMultilineLambda() {
            val code = """
                namespace TestApp {
                    public class ComplexLambda {
                        public void Execute() {
                            Func<int, int, int> calculate = (a, b) => {
                                int sum = a + b;
                                int product = a * b;
                                return sum + product;
                            };
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertTrue(container.DataStructures.isNotEmpty())
        }
    }
    
    // ==================== Generic Tests ====================
    
    @Nested
    inner class GenericTests {
        @Test
        fun shouldIdentifyGenericClass() {
            val code = """
                namespace TestApp {
                    public class Container<T> {
                        private T _value;
                        
                        public T GetValue() {
                            return _value;
                        }
                        
                        public void SetValue(T value) {
                            _value = value;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val containerClass = container.DataStructures[0]
            
            assertEquals("Container", containerClass.NodeName)
            assertEquals(2, containerClass.Functions.size)
        }
        
        @Test
        fun shouldIdentifyGenericConstraints() {
            val code = """
                namespace TestApp {
                    public class Repository<T> where T : class, IEntity, new() {
                        public void Add(T entity) { }
                    }
                    
                    public class Comparer<T> where T : struct {
                        public bool Compare(T a, T b) {
                            return a.Equals(b);
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertEquals(2, container.DataStructures.size)
        }
        
        @Test
        fun shouldIdentifyGenericMethod() {
            val code = """
                namespace TestApp {
                    public class Utils {
                        public T Create<T>() where T : new() {
                            return new T();
                        }
                        
                        public void Swap<T>(ref T a, ref T b) {
                            T temp = a;
                            a = b;
                            b = temp;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val utils = container.DataStructures[0]
            
            assertEquals(2, utils.Functions.size)
        }
    }
    
    // ==================== Extension Method Tests ====================
    
    @Nested
    inner class ExtensionMethodTests {
        @Test
        fun shouldIdentifyExtensionMethod() {
            val code = """
                namespace TestApp {
                    public static class StringExtensions {
                        public static string Reverse(this string str) {
                            char[] charArray = str.ToCharArray();
                            Array.Reverse(charArray);
                            return new string(charArray);
                        }
                        
                        public static bool IsNullOrEmpty(this string str) {
                            return string.IsNullOrEmpty(str);
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val extensions = container.DataStructures[0]
            
            assertEquals("StringExtensions", extensions.NodeName)
            assertEquals(2, extensions.Functions.size)
            
            val reverseMethod = extensions.Functions.find { it.Name == "Reverse" }
            assertNotNull(reverseMethod)
            assertTrue(reverseMethod!!.Modifiers.contains("static"))
        }
    }
    
    // ==================== Record Tests (C# 9+) ====================
    
    @Nested
    inner class RecordTests {
        @Test
        @Ignore
        fun shouldIdentifyPositionalRecord() {
            val code = """
                namespace TestApp {
                    public record Person(string Name, int Age);
                    
                    public record Student(string Name, int Age, string School) : Person(Name, Age);
                }
            """.trimIndent()

            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]

            assertEquals(2, container.DataStructures.size)

            val person = container.DataStructures.find { it.NodeName == "Person" }
            assertNotNull(person)

            val student = container.DataStructures.find { it.NodeName == "Student" }
            assertNotNull(student)
            assertEquals("Person", student!!.Extend)
        }

        @Test
        fun shouldIdentifyRecordWithMembers() {
            val code = """
                namespace TestApp {
                    public record Employee {
                        public string Name { get; init; }
                        public int Id { get; init; }
                        
                        public string GetDisplayName() {
                            return $"{Name} ({Id})";
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val employee = container.DataStructures[0]
            
            assertEquals("Employee", employee.NodeName)
            assertTrue(employee.Fields.size >= 2)
            assertTrue(employee.Functions.size >= 1)
        }
    }
    
    // ==================== Pattern Matching Tests ====================
    
    @Nested
    inner class PatternMatchingTests {
        @Test
        fun shouldIdentifyPatternMatching() {
            val code = """
                namespace TestApp {
                    public class PatternMatcher {
                        public string MatchType(object obj) {
                            return obj switch {
                                int i => $"Integer: {i}",
                                string s => $"String: {s}",
                                null => "Null",
                                _ => "Unknown"
                            };
                        }
                        
                        public bool IsValidRange(int value) {
                            return value is >= 0 and <= 100;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val matcher = container.DataStructures[0]
            
            assertEquals(2, matcher.Functions.size)
        }
        
        @Test
        fun shouldIdentifyPropertyPattern() {
            val code = """
                namespace TestApp {
                    public record Point(int X, int Y);
                    
                    public class PatternExample {
                        public string Describe(Point point) {
                            return point switch {
                                { X: 0, Y: 0 } => "Origin",
                                { X: var x, Y: 0 } => $"X-axis at {x}",
                                { X: 0, Y: var y } => $"Y-axis at {y}",
                                _ => "Other"
                            };
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            
            assertTrue(container.DataStructures.size >= 2)
        }
    }
    
    // ==================== Nullable Reference Type Tests (C# 8+) ====================
    
    @Nested
    inner class NullableReferenceTests {
        @Test
        fun shouldIdentifyNullableReferenceTypes() {
            val code = """
                #nullable enable
                
                namespace TestApp {
                    public class NullableExample {
                        public string? Name { get; set; }
                        public string Email { get; set; } = "";
                        
                        public string? TryGetName() {
                            return Name;
                        }
                        
                        public string GetEmail() {
                            return Email!;
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val example = container.DataStructures[0]
            
            assertTrue(example.Fields.size >= 2)
            assertTrue(example.Functions.size >= 2)
        }
    }
    
    // ==================== Tuple Tests ====================
    
    @Nested
    inner class TupleTests {
        @Test
        fun shouldIdentifyTuples() {
            val code = """
                namespace TestApp {
                    public class TupleExample {
                        public (int, string) GetData() {
                            return (42, "answer");
                        }
                        
                        public (int Sum, int Product) Calculate(int a, int b) {
                            return (a + b, a * b);
                        }
                        
                        public void UseTuple() {
                            var (number, text) = GetData();
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val example = container.DataStructures[0]
            
            assertEquals(3, example.Functions.size)
        }
    }
    
    // ==================== Expression-bodied Member Tests ====================
    
    @Nested
    inner class ExpressionBodiedTests {
        @Test
        fun shouldIdentifyExpressionBodiedMembers() {
            val code = """
                namespace TestApp {
                    public class Calculator {
                        public int Add(int a, int b) => a + b;
                        public int Multiply(int a, int b) => a * b;
                        
                        public string Name => "Calculator";
                        
                        public void Log(string message) => Console.WriteLine(message);
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val calculator = container.DataStructures[0]
            
            assertTrue(calculator.Functions.size >= 3)
        }
    }
    
    // ==================== Local Function Tests ====================
    
    @Nested
    inner class LocalFunctionTests {
        @Test
        fun shouldHandleLocalFunctions() {
            val code = """
                namespace TestApp {
                    public class LocalFuncExample {
                        public int Calculate(int x) {
                            return Multiply(x, 2);
                            
                            int Multiply(int a, int b) {
                                return a * b;
                            }
                        }
                    }
                }
            """.trimIndent()
            
            val node = CSharpAnalyser().analysis(code, "test.cs")
            val container = node.Containers[0]
            val example = container.DataStructures[0]
            
            assertTrue(example.Functions.isNotEmpty())
        }
    }
}
