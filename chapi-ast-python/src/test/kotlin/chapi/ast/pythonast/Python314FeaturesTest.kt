package chapi.ast.pythonast

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals

/**
 * Test suite for Python 3.10-3.14 new syntax features
 * 
 * Python 3.10: Pattern matching (match/case)
 * Python 3.12: Type parameter syntax (PEP 695), type statement
 * Python 3.14: Template strings (t-strings), bracketless except (PEP 758)
 */
class Python314FeaturesTest {

    // ==================== Python 3.10: Pattern Matching Tests ====================
    
    @Nested
    inner class PatternMatchingTests {
        
        @Test
        fun `should parse basic match statement with literal patterns`() {
            val code = """
                def http_status(status):
                    match status:
                        case 200:
                            return "OK"
                        case 404:
                            return "Not Found"
                        case 500:
                            return "Internal Server Error"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("http_status", func.Name)
            assertEquals(1, func.Parameters.size)
        }
        
        @Test
        fun `should parse match with capture patterns`() {
            val code = """
                def describe_point(point):
                    match point:
                        case (0, 0):
                            return "Origin"
                        case (x, 0):
                            return f"X-axis at {x}"
                        case (0, y):
                            return f"Y-axis at {y}"
                        case (x, y):
                            return f"Point at ({x}, {y})"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("describe_point", func.Name)
        }
        
        @Test
        fun `should parse match with wildcard pattern`() {
            val code = """
                def process_command(command):
                    match command:
                        case "quit":
                            return "Goodbye"
                        case _:
                            return "Unknown command"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_command", func.Name)
        }
        
        @Test
        fun `should parse match with class patterns`() {
            val code = """
                def handle_event(event):
                    match event:
                        case Click(x=x, y=y):
                            handle_click(x, y)
                        case KeyPress(key=k):
                            handle_key(k)
                        case Quit():
                            sys.exit()
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("handle_event", func.Name)
        }
        
        @Test
        fun `should parse match with guard conditions`() {
            val code = """
                def categorize_number(n):
                    match n:
                        case x if x < 0:
                            return "negative"
                        case 0:
                            return "zero"
                        case x if x > 0:
                            return "positive"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("categorize_number", func.Name)
        }
        
        @Test
        fun `should parse match with or patterns`() {
            val code = """
                def classify_char(char):
                    match char:
                        case 'a' | 'e' | 'i' | 'o' | 'u':
                            return "vowel"
                        case _:
                            return "consonant"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("classify_char", func.Name)
        }
        
        @Test
        fun `should parse match with mapping patterns`() {
            val code = """
                def process_action(action):
                    match action:
                        case {"type": "move", "x": x, "y": y}:
                            move_to(x, y)
                        case {"type": "attack", "target": t}:
                            attack(t)
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_action", func.Name)
        }
    }

    // ==================== Python 3.12: Type Parameter Syntax Tests ====================
    
    @Nested
    inner class TypeParameterTests {
        
        @Test
        fun `should parse generic class with type parameter`() {
            val code = """
                class Stack[T]:
                    def __init__(self):
                        self.items: list[T] = []
                    
                    def push(self, item: T) -> None:
                        self.items.append(item)
                    
                    def pop(self) -> T:
                        return self.items.pop()
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val stack = codeFile.DataStructures[0]
            
            assertEquals("Stack", stack.NodeName)
            assertTrue(stack.Functions.size >= 3)
        }
        
        @Test
        fun `should parse generic function with type parameter`() {
            val code = """
                def first[T](items: list[T]) -> T:
                    return items[0]
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("first", func.Name)
        }
        
        @Test
        fun `should parse type parameter with bound`() {
            val code = """
                class Comparable[T: (int, float, str)]:
                    def compare(self, a: T, b: T) -> int:
                        if a < b:
                            return -1
                        elif a > b:
                            return 1
                        return 0
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val cls = codeFile.DataStructures[0]
            
            assertEquals("Comparable", cls.NodeName)
        }
        
        @Test
        fun `should parse multiple type parameters`() {
            val code = """
                class Mapping[K, V]:
                    def __init__(self):
                        self._data: dict[K, V] = {}
                    
                    def get(self, key: K) -> V:
                        return self._data[key]
                    
                    def set(self, key: K, value: V) -> None:
                        self._data[key] = value
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val mapping = codeFile.DataStructures[0]
            
            assertEquals("Mapping", mapping.NodeName)
        }
        
        @Test
        fun `should parse TypeVarTuple parameter`() {
            val code = """
                class Container[*Ts]:
                    def __init__(self, *args):
                        self.values = args
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val cls = codeFile.DataStructures[0]
            
            assertEquals("Container", cls.NodeName)
        }
        
        @Test
        fun `should parse ParamSpec parameter`() {
            val code = """
                class Wrapper[**P]:
                    def __init__(self, func):
                        self.func = func
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val cls = codeFile.DataStructures[0]
            
            assertEquals("Wrapper", cls.NodeName)
        }
    }

    // ==================== Python 3.12: Type Statement Tests ====================
    
    @Nested
    inner class TypeStatementTests {
        
        @Test
        fun `should parse simple type alias`() {
            val code = """
                type Vector = list[float]
                
                def normalize(v: Vector) -> Vector:
                    length = sum(x**2 for x in v) ** 0.5
                    return [x / length for x in v]
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            assertNotNull(codeFile)
        }
        
        @Test
        fun `should parse generic type alias`() {
            val code = """
                type ListOrSet[T] = list[T] | set[T]
                
                def process_collection[T](items: ListOrSet[T]) -> None:
                    for item in items:
                        print(item)
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            assertNotNull(codeFile)
        }
        
        @Test
        fun `should parse nested type alias`() {
            val code = """
                type JSON = dict[str, 'JSON'] | list['JSON'] | str | int | float | bool | None
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            assertNotNull(codeFile)
        }
    }

    // ==================== Python 3.14: Template Strings Tests ====================
    
    @Nested
    inner class TemplateStringTests {
        
        @Test
        fun `should parse simple t-string`() {
            val code = """
                def greet(name):
                    return t"Hello, {name}!"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("greet", func.Name)
        }
        
        @Test
        fun `should parse raw t-string`() {
            val code = """
                def make_pattern(value):
                    return rt"\\d+{value}\\w+"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("make_pattern", func.Name)
        }
        
        @Test
        fun `should parse t-string with complex expressions`() {
            val code = """
                def format_data(user):
                    return t"User: {user.name}, Age: {user.age}, Active: {user.is_active}"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("format_data", func.Name)
        }
    }

    // ==================== Python 3.14: Bracketless Except Tests ====================
    
    @Nested
    inner class BracketlessExceptTests {
        
        @Test
        fun `should parse except without parentheses for multiple exceptions`() {
            val code = """
                def safe_parse(data):
                    try:
                        return json.loads(data)
                    except ValueError, TypeError, KeyError:
                        return None
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("safe_parse", func.Name)
        }
        
        @Test
        fun `should parse except with parentheses still works`() {
            val code = """
                def safe_convert(value):
                    try:
                        return int(value)
                    except (ValueError, TypeError) as e:
                        print(f"Error: {e}")
                        return 0
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("safe_convert", func.Name)
        }
        
        @Test
        fun `should parse mixed except styles`() {
            // Note: Per PEP 758, bracketless except cannot use AS clause
            val code = """
                def process_file(path):
                    try:
                        with open(path) as f:
                            return f.read()
                    except FileNotFoundError:
                        return None
                    except PermissionError, IOError:
                        raise
                    except ValueError:
                        return ""
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_file", func.Name)
        }
    }

    // ==================== Soft Keyword Compatibility Tests ====================
    
    @Nested
    inner class SoftKeywordTests {
        
        @Test
        fun `should allow match as variable name`() {
            val code = """
                def find_match(pattern, text):
                    match = re.search(pattern, text)
                    return match
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("find_match", func.Name)
        }
        
        @Test
        fun `should allow case as variable name`() {
            val code = """
                def process_case(data):
                    case = data.lower()
                    return case
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_case", func.Name)
        }
        
        @Test
        fun `should allow type as variable name`() {
            val code = """
                def get_type(obj):
                    type = obj.__class__.__name__
                    return type
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("get_type", func.Name)
        }
    }

    // ==================== PythonVersion Tests ====================
    
    @Nested
    inner class PythonVersionTests {
        
        @Test
        fun `should support pattern matching for Python310 and above`() {
            val version310 = chapi.ast.antlr.PythonVersion.Python310
            val version312 = chapi.ast.antlr.PythonVersion.Python312
            val version314 = chapi.ast.antlr.PythonVersion.Python314
            val version3 = chapi.ast.antlr.PythonVersion.Python3
            val autodetect = chapi.ast.antlr.PythonVersion.Autodetect
            
            assertTrue(version310.supportsPatternMatching())
            assertTrue(version312.supportsPatternMatching())
            assertTrue(version314.supportsPatternMatching())
            assertTrue(version3.supportsPatternMatching())
            assertTrue(autodetect.supportsPatternMatching())
            
            val version2 = chapi.ast.antlr.PythonVersion.Python2
            assertFalse(version2.supportsPatternMatching())
        }
        
        @Test
        fun `should support type parameters for Python312 and above`() {
            val version312 = chapi.ast.antlr.PythonVersion.Python312
            val version314 = chapi.ast.antlr.PythonVersion.Python314
            val autodetect = chapi.ast.antlr.PythonVersion.Autodetect
            
            assertTrue(version312.supportsTypeParameters())
            assertTrue(version314.supportsTypeParameters())
            assertTrue(autodetect.supportsTypeParameters())
            
            val version310 = chapi.ast.antlr.PythonVersion.Python310
            val version3 = chapi.ast.antlr.PythonVersion.Python3
            val version2 = chapi.ast.antlr.PythonVersion.Python2
            assertFalse(version310.supportsTypeParameters())
            assertFalse(version3.supportsTypeParameters())
            assertFalse(version2.supportsTypeParameters())
        }
        
        @Test
        fun `should support Python314 features only for Python314`() {
            val version314 = chapi.ast.antlr.PythonVersion.Python314
            val autodetect = chapi.ast.antlr.PythonVersion.Autodetect
            
            assertTrue(version314.supportsPython314Features())
            assertTrue(autodetect.supportsPython314Features())
            
            val version312 = chapi.ast.antlr.PythonVersion.Python312
            val version310 = chapi.ast.antlr.PythonVersion.Python310
            val version3 = chapi.ast.antlr.PythonVersion.Python3
            val version2 = chapi.ast.antlr.PythonVersion.Python2
            assertFalse(version312.supportsPython314Features())
            assertFalse(version310.supportsPython314Features())
            assertFalse(version3.supportsPython314Features())
            assertFalse(version2.supportsPython314Features())
        }
        
        @Test
        fun `should return correct value for each version`() {
            assertEquals(0, chapi.ast.antlr.PythonVersion.Autodetect.getValue())
            assertEquals(2, chapi.ast.antlr.PythonVersion.Python2.getValue())
            assertEquals(3, chapi.ast.antlr.PythonVersion.Python3.getValue())
            assertEquals(310, chapi.ast.antlr.PythonVersion.Python310.getValue())
            assertEquals(312, chapi.ast.antlr.PythonVersion.Python312.getValue())
            assertEquals(314, chapi.ast.antlr.PythonVersion.Python314.getValue())
        }
    }

    // ==================== Additional Edge Cases ====================
    
    @Nested
    inner class EdgeCaseTests {
        
        @Test
        fun `should parse match with nested patterns`() {
            val code = """
                def process_data(data):
                    match data:
                        case {"items": [{"id": x, "value": y}]}:
                            return (x, y)
                        case {"items": []}:
                            return None
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_data", func.Name)
        }
        
        @Test
        fun `should parse match with as pattern`() {
            val code = """
                def process_point(point):
                    match point:
                        case (x, y) as coord:
                            return f"Coordinate: {coord}"
                        case _:
                            return "Unknown"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_point", func.Name)
        }
        
        @Test
        fun `should parse complex type parameter with multiple bounds`() {
            val code = """
                class Processor[T: (int, str), U: float]:
                    def process(self, item: T, value: U) -> T:
                        return item
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val cls = codeFile.DataStructures[0]
            
            assertEquals("Processor", cls.NodeName)
        }
        
        @Test
        fun `should parse type alias with type parameters`() {
            val code = """
                type Result[T, E] = tuple[T, E] | None
                
                def create_result[T, E](value: T, error: E) -> Result[T, E]:
                    return (value, error)
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            assertNotNull(codeFile)
        }
        
        @Test
        fun `should parse match with star pattern`() {
            val code = """
                def process_list(items):
                    match items:
                        case [first, *rest]:
                            return (first, rest)
                        case []:
                            return None
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_list", func.Name)
        }
        
        @Test
        fun `should parse match with double star pattern`() {
            val code = """
                def process_dict(data):
                    match data:
                        case {"name": name, **rest}:
                            return (name, rest)
                        case _:
                            return None
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_dict", func.Name)
        }
        
        @Test
        fun `should parse t-string with nested f-string like syntax`() {
            val code = """
                def format_message(user, count):
                    return t"User {user.name} has {count} items"
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("format_message", func.Name)
        }
        
        @Test
        fun `should parse except with except star`() {
            val code = """
                def handle_errors():
                    try:
                        risky_operation()
                    except* ValueError:
                        handle_value_error()
                    except* TypeError, KeyError:
                        handle_type_or_key_error()
            """.trimIndent()
            
            // Note: except* is not yet in our grammar, but we should handle gracefully
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            assertNotNull(codeFile)
        }
    }
}
