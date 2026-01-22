package chapi.ast.pythonast

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals

/**
 * Comprehensive test suite for advanced Python features
 * Tests cover: decorators, async/await, type annotations, comprehensions, dataclasses, etc.
 */
class PythonAdvancedFeaturesTest {

    // ==================== Decorator Tests ====================
    
    @Nested
    inner class DecoratorTests {
        @Test
        fun `should parse property decorator`() {
            val code = """
                class Circle:
                    def __init__(self, radius):
                        self._radius = radius
                    
                    @property
                    def radius(self):
                        return self._radius
                    
                    @radius.setter
                    def radius(self, value):
                        self._radius = value
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val circle = codeFile.DataStructures[0]
            
            assertEquals("Circle", circle.NodeName)
            assertTrue(circle.Functions.size >= 3)
            
            // Property getter should have decorator
            val getter = circle.Functions.find { it.Name == "radius" && it.Annotations.any { a -> a.Name == "property" } }
            assertNotNull(getter)
        }
        
        @Test
        fun `should parse staticmethod and classmethod decorators`() {
            val code = """
                class MyClass:
                    @staticmethod
                    def static_method(x, y):
                        return x + y
                    
                    @classmethod
                    def class_method(cls):
                        return cls()
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val myClass = codeFile.DataStructures[0]
            
            assertEquals(2, myClass.Functions.size)
            
            val staticMethod = myClass.Functions[0]
            assertTrue(staticMethod.Annotations.any { it.Name == "staticmethod" })
            
            val classMethod = myClass.Functions[1]
            assertTrue(classMethod.Annotations.any { it.Name == "classmethod" })
        }
        
        @Test
        fun `should parse custom decorators with arguments`() {
            val code = """
                @app.route('/users', methods=['GET', 'POST'])
                @require_auth
                def users():
                    pass
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("users", func.Name)
            assertTrue(func.Annotations.size >= 2)
        }
    }
    
    // ==================== Async/Await Tests ====================
    
    @Nested
    inner class AsyncAwaitTests {
        @Test
        fun `should parse async function with await`() {
            val code = """
                async def fetch_user(user_id):
                    result = await db.query(user_id)
                    return result
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("fetch_user", func.Name)
            assertTrue(func.Modifiers.contains("async"))
        }
        
        @Test
        fun `should parse async context manager`() {
            val code = """
                async def process_data():
                    async with aiohttp.ClientSession() as session:
                        data = await session.get('http://example.com')
                        return data
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_data", func.Name)
            assertTrue(func.Modifiers.contains("async"))
        }
        
        @Test
        fun `should parse async generator`() {
            val code = """
                async def async_range(n):
                    for i in range(n):
                        await asyncio.sleep(0.1)
                        yield i
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("async_range", func.Name)
            assertTrue(func.Modifiers.contains("async"))
        }
    }
    
    // ==================== Type Annotation Tests ====================
    
    @Nested
    inner class TypeAnnotationTests {
        @Test
        fun `should parse complex type annotations`() {
            val code = """
                from typing import List, Dict, Optional, Union
                
                def process_data(
                    items: List[Dict[str, int]],
                    optional_param: Optional[str] = None
                ) -> Dict[str, Union[int, str]]:
                    return {}
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("process_data", func.Name)
            // Type annotation may be simplified
            assertNotNull(func.ReturnType)
            assertTrue(func.Parameters.size >= 2)
        }
        
        @Test
        fun `should parse variable type annotations`() {
            val code = """
                name: str = "Alice"
                age: int = 30
                scores: list[int] = [90, 85, 88]
                data: dict[str, any] = {}
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val defaultNode = codeFile.DataStructures[0]
            
            // Variables with type annotations should be captured
            assertTrue(defaultNode.Fields.isNotEmpty())
        }
        
        @Test
        fun `should parse class variable annotations`() {
            val code = """
                class Config:
                    app_name: str = "MyApp"
                    debug: bool = True
                    max_connections: int = 100
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val config = codeFile.DataStructures[0]
            
            assertEquals("Config", config.NodeName)
            assertEquals(3, config.Fields.size)
        }
    }
    
    // ==================== Comprehension Tests ====================
    
    @Nested
    inner class ComprehensionTests {
        @Test
        fun `should parse list comprehension`() {
            val code = """
                def get_squares(n):
                    squares = [x**2 for x in range(n)]
                    return squares
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("get_squares", func.Name)
            assertTrue(func.LocalVariables.any { it.TypeValue == "squares" })
        }
        
        @Test
        fun `should parse dict comprehension`() {
            val code = """
                def create_lookup(items):
                    lookup = {item.id: item.name for item in items}
                    return lookup
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("create_lookup", func.Name)
            assertTrue(func.LocalVariables.any { it.TypeValue == "lookup" })
        }
        
        @Test
        fun `should parse set comprehension`() {
            val code = """
                def unique_lengths(words):
                    lengths = {len(word) for word in words}
                    return lengths
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("unique_lengths", func.Name)
        }
    }
    
    // ==================== Dataclass Tests ====================
    
    @Nested
    inner class DataclassTests {
        @Test
        fun `should parse dataclass decorator`() {
            val code = """
                from dataclasses import dataclass
                
                @dataclass
                class User:
                    name: str
                    age: int
                    email: str = ""
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val user = codeFile.DataStructures[0]
            
            assertEquals("User", user.NodeName)
            assertTrue(user.Annotations.any { it.Name == "dataclass" })
        }
        
        @Test
        fun `should parse dataclass with methods`() {
            val code = """
                from dataclasses import dataclass, field
                
                @dataclass
                class Product:
                    name: str
                    price: float
                    tags: list = field(default_factory=list)
                    
                    def apply_discount(self, percentage):
                        self.price *= (1 - percentage / 100)
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val product = codeFile.DataStructures[0]
            
            assertEquals("Product", product.NodeName)
            assertTrue(product.Functions.isNotEmpty())
        }
    }
    
    // ==================== Context Manager Tests ====================
    
    @Nested
    inner class ContextManagerTests {
        @Test
        fun `should parse context manager methods`() {
            val code = """
                class DatabaseConnection:
                    def __enter__(self):
                        self.connect()
                        return self
                    
                    def __exit__(self, exc_type, exc_val, exc_tb):
                        self.disconnect()
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val dbConn = codeFile.DataStructures[0]
            
            assertEquals("DatabaseConnection", dbConn.NodeName)
            assertTrue(dbConn.Functions.any { it.Name == "__enter__" })
            assertTrue(dbConn.Functions.any { it.Name == "__exit__" })
        }
    }
    
    // ==================== Generator Tests ====================
    
    @Nested
    inner class GeneratorTests {
        @Test
        fun `should parse generator function`() {
            val code = """
                def fibonacci(n):
                    a, b = 0, 1
                    for _ in range(n):
                        yield a
                        a, b = b, a + b
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("fibonacci", func.Name)
        }
    }
    
    // ==================== Lambda Tests ====================
    
    @Nested
    inner class LambdaTests {
        @Test
        fun `should parse lambda expressions`() {
            val code = """
                def create_multiplier(n):
                    multiplier = lambda x: x * n
                    return multiplier
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("create_multiplier", func.Name)
        }
    }
    
    // ==================== Exception Handling Tests ====================
    
    @Nested
    inner class ExceptionTests {
        @Test
        fun `should parse custom exception class`() {
            val code = """
                class ValidationError(Exception):
                    def __init__(self, message, field=None):
                        self.message = message
                        self.field = field
                        super().__init__(self.message)
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val exception = codeFile.DataStructures[0]
            
            assertEquals("ValidationError", exception.NodeName)
            assertEquals("Exception", exception.MultipleExtend[0])
        }
        
        @Test
        fun `should parse try-except blocks`() {
            val code = """
                def safe_divide(a, b):
                    try:
                        result = a / b
                        return result
                    except ZeroDivisionError:
                        return 0
                    except Exception as e:
                        raise
                    finally:
                        pass
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val func = codeFile.DataStructures[0].Functions[0]
            
            assertEquals("safe_divide", func.Name)
        }
    }
    
    // ==================== Multiple Inheritance Tests ====================
    
    @Nested
    inner class MultipleInheritanceTests {
        @Test
        fun `should parse multiple inheritance`() {
            val code = """
                class Flyable:
                    def fly(self):
                        pass
                
                class Swimmable:
                    def swim(self):
                        pass
                
                class Duck(Flyable, Swimmable):
                    pass
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            
            assertEquals(3, codeFile.DataStructures.size)
            val duck = codeFile.DataStructures[2]
            assertEquals("Duck", duck.NodeName)
            assertEquals(2, duck.MultipleExtend.size)
            assertTrue(duck.MultipleExtend.contains("Flyable"))
            assertTrue(duck.MultipleExtend.contains("Swimmable"))
        }
        
        @Test
        fun `should parse mixin pattern`() {
            val code = """
                class JSONMixin:
                    def to_json(self):
                        return json.dumps(self.__dict__)
                
                class User(JSONMixin):
                    def __init__(self, name):
                        self.name = name
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            
            val user = codeFile.DataStructures[1]
            assertEquals("User", user.NodeName)
            assertEquals("JSONMixin", user.MultipleExtend[0])
        }
    }
    
    // ==================== Real-world Django/FastAPI Tests ====================
    
    @Nested
    inner class WebFrameworkTests {
        @Test
        fun `should parse FastAPI route handlers`() {
            val code = """
                from fastapi import FastAPI, HTTPException
                from typing import List
                
                app = FastAPI()
                
                @app.get("/users", response_model=List[User])
                async def get_users(skip: int = 0, limit: int = 100):
                    users = await db.fetch_users(skip, limit)
                    return users
                
                @app.post("/users", status_code=201)
                async def create_user(user: UserCreate):
                    db_user = await db.create_user(user)
                    return db_user
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            val defaultNode = codeFile.DataStructures[0]
            
            assertTrue(defaultNode.Functions.size >= 2)
            
            val getUsers = defaultNode.Functions.find { it.Name == "get_users" }
            assertNotNull(getUsers)
            assertTrue(getUsers!!.Modifiers.contains("async"))
        }
        
        @Test
        fun `should parse Django model class`() {
            val code = """
                from django.db import models
                
                class Article(models.Model):
                    title = models.CharField(max_length=200)
                    content = models.TextField()
                    published_date = models.DateTimeField(auto_now_add=True)
                    
                    def __str__(self):
                        return self.title
            """.trimIndent()
            
            val codeFile = PythonAnalyser().analysis(code, "test.py")
            
            // Should have Article class
            val article = codeFile.DataStructures.find { it.NodeName == "Article" }
            assertNotNull(article)
            // Inheritance should be captured
            assertTrue(article!!.MultipleExtend.isNotEmpty())
            // Fields should be captured
            assertTrue(article.Fields.isNotEmpty())
        }
    }
}
