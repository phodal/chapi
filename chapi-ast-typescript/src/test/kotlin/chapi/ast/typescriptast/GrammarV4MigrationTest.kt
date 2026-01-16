package chapi.ast.typescriptast

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for new grammar features added during ANTLR v4 migration.
 * 
 * Key syntax changes:
 * 1. JSX Support - htmlElement, htmlContent, htmlAttribute rules
 * 2. Definite Assignment Assertion (`!`) in class properties
 * 3. Arrow function body ordering (block body vs expression body)
 * 4. Export statement handling
 */
class GrammarV4MigrationTest {

    // ============================================
    // JSX SUPPORT TESTS
    // ============================================

    @Test
    fun `jsx fragment element`() {
        val code = """
const Component = () => {
    return (
        <>
            <div>First</div>
            <div>Second</div>
        </>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Component.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
        assertEquals("Component", codeFile.DataStructures[0].Functions[0].Name)
    }

    @Test
    fun `jsx self-closing element`() {
        val code = """
const Icon = () => {
    return <img src="icon.png" alt="icon" />;
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Icon.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    @Test
    fun `jsx spread attribute`() {
        val code = """
interface Props {
    name: string;
}

const Component = (props: Props) => {
    return <div {...props} className="container" />;
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Component.tsx")
        assertEquals(2, codeFile.DataStructures.size)
        val funcs = codeFile.DataStructures[1].Functions
        assertEquals(1, funcs.size)
        assertEquals("Component", funcs[0].Name)
    }

    @Test
    fun `jsx expression attribute`() {
        val code = """
const Button = () => {
    const handleClick = () => console.log('clicked');
    return <button onClick={handleClick}>Click me</button>;
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Button.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        val mainFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals("Button", mainFunc.Name)
        assertEquals(1, mainFunc.InnerFunctions.size)
        assertEquals("handleClick", mainFunc.InnerFunctions[0].Name)
    }

    @Test
    fun `jsx dotted tag name`() {
        val code = """
import { Form, Input } from 'antd';

const MyForm = () => {
    return (
        <Form>
            <Form.Item label="Name">
                <Input />
            </Form.Item>
        </Form>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "MyForm.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    @Test
    fun `jsx data attribute with hyphen`() {
        val code = """
const Component = () => {
    return <div data-testid="my-component" aria-label="test"></div>;
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Component.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    @Test
    fun `jsx comment syntax`() {
        val code = """
const Component = () => {
    return (
        <div>
            {/* This is a JSX comment */}
            <span>Content</span>
        </div>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Component.tsx")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    fun `jsx nested expression`() {
        val code = """
const List = () => {
    const items = [1, 2, 3];
    return (
        <ul>
            {items.map(item => (
                <li key={item}>{item}</li>
            ))}
        </ul>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "List.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        val mainFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals("List", mainFunc.Name)
    }

    @Test
    fun `jsx inline arrow function in attribute`() {
        val code = """
const Button = () => {
    return (
        <button onClick={(e) => e.preventDefault()}>
            Submit
        </button>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Button.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    // ============================================
    // DEFINITE ASSIGNMENT ASSERTION TESTS
    // ============================================

    @Test
    fun `class property with definite assignment assertion`() {
        val code = """
class MyService {
    private elk!: ELK;
    private logger!: Logger;
    
    constructor() {
        this.init();
    }
    
    private init() {
        this.elk = new ELK();
        this.logger = new Logger();
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "MyService.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val ds = codeFile.DataStructures[0]
        assertEquals("MyService", ds.NodeName)
        assertEquals(2, ds.Fields.size)
        assertEquals("elk", ds.Fields[0].TypeKey)
        assertEquals("logger", ds.Fields[1].TypeKey)
    }

    @Test
    fun `class property with optional marker`() {
        val code = """
class Config {
    name?: string;
    value?: number;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Config.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val ds = codeFile.DataStructures[0]
        assertEquals("Config", ds.NodeName)
        assertEquals(2, ds.Fields.size)
    }

    @Test
    fun `class property with both markers in different properties`() {
        val code = """
class MixedClass {
    required!: string;
    optional?: number;
    normal: boolean = false;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "MixedClass.ts")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(3, codeFile.DataStructures[0].Fields.size)
    }

    // ============================================
    // ARROW FUNCTION BODY TESTS
    // ============================================

    @Test
    fun `arrow function with block body and return statement`() {
        val code = """
const add = (a: number, b: number) => {
    const result = a + b;
    return result;
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "math.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val func = codeFile.DataStructures[0].Functions[0]
        assertEquals("add", func.Name)
    }

    @Test
    fun `arrow function with expression body`() {
        val code = """
const multiply = (a: number, b: number) => a * b;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "math.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val func = codeFile.DataStructures[0].Functions[0]
        assertEquals("multiply", func.Name)
    }

    @Test
    fun `arrow function with object literal expression body`() {
        val code = """
const createUser = (name: string) => ({ name, id: Date.now() });
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "user.ts")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    @Test
    fun `arrow function returning jsx`() {
        val code = """
const Greeting = (props: { name: string }) => {
    return (
        <div>Hello, {props.name}!</div>
    );
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Greeting.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        val func = codeFile.DataStructures[0].Functions[0]
        assertEquals("Greeting", func.Name)
        assertTrue(func.IsReturnHtml)
    }

    @Test
    fun `arrow function with implicit jsx return`() {
        val code = """
const Greeting = (props: { name: string }) => (
    <div>Hello, {props.name}!</div>
);
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "Greeting.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        assertEquals(1, codeFile.DataStructures[0].Functions.size)
    }

    // ============================================
    // EXPORT STATEMENT TESTS
    // ============================================

    @Test
    fun `export default identifier`() {
        val code = """
const MyComponent = () => {
    return <div>Hello</div>;
};

export default MyComponent;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "MyComponent.tsx")
        assertEquals(1, codeFile.DataStructures.size)
        val exports = codeFile.DataStructures[0].Exports
        assertEquals(1, exports.size)
        assertEquals("MyComponent", exports[0].Name)
    }

    @Test
    fun `export const variable`() {
        val code = """
export const API_URL = 'https://api.example.com';
export const MAX_RETRIES = 3;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "config.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val ds = codeFile.DataStructures[0]
        assertEquals(2, ds.Fields.size)
        assertTrue(ds.Exports.size >= 1)
    }

    @Test
    fun `export function declaration`() {
        val code = """
export function calculateSum(a: number, b: number): number {
    return a + b;
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "math.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val func = codeFile.DataStructures[0].Functions[0]
        assertEquals("calculateSum", func.Name)
    }

    @Test
    fun `export class declaration`() {
        val code = """
export class UserService {
    private users: User[] = [];
    
    addUser(user: User): void {
        this.users.push(user);
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "UserService.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val ds = codeFile.DataStructures[0]
        assertEquals("UserService", ds.NodeName)
    }

    @Test
    fun `multiple export types in one file`() {
        val code = """
export const VERSION = '1.0.0';

export interface Config {
    name: string;
}

export class Service {
    config: Config;
}

const helper = () => {};

export default Service;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "module.ts")
        assertTrue(codeFile.DataStructures.isNotEmpty())
    }

    // ============================================
    // GENERIC TYPE VS JSX DISAMBIGUATION TESTS
    // ============================================

    @Test
    fun `generic function call should not conflict with jsx`() {
        val code = """
const result = fetch<Response>('/api/data');
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "api.ts")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    fun `arrow function with generic parameter`() {
        val code = """
const identity = <T>(value: T): T => value;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "utils.ts")
        assertEquals(1, codeFile.DataStructures.size)
    }

    @Test
    fun `nested arrow with generic in outer function`() {
        val code = """
const axiosWithBaseURL = (baseURL: string) => <T>(url: string) => {
    return fetch<T>(baseURL + url);
};
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "http.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val mainFunc = codeFile.DataStructures[0].Functions[0]
        assertEquals("axiosWithBaseURL", mainFunc.Name)
    }

    // ============================================
    // COMPLEX REAL-WORLD SCENARIOS
    // ============================================

    @Test
    fun `react component with hooks and jsx`() {
        val code = """
import React, { useState, useEffect } from 'react';

interface User {
    id: number;
    name: string;
}

const UserList = () => {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(true);
    
    useEffect(() => {
        fetch('/api/users')
            .then(res => res.json())
            .then(data => {
                setUsers(data);
                setLoading(false);
            });
    }, []);
    
    if (loading) {
        return <div>Loading...</div>;
    }
    
    return (
        <ul>
            {users.map(user => (
                <li key={user.id}>{user.name}</li>
            ))}
        </ul>
    );
};

export default UserList;
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "UserList.tsx")
        assertTrue(codeFile.DataStructures.isNotEmpty())
        
        // Find the default DataStructure with functions
        val dsWithFuncs = codeFile.DataStructures.find { it.Functions.isNotEmpty() }
        assertTrue(dsWithFuncs != null)
        
        val userListFunc = dsWithFuncs!!.Functions.find { it.Name == "UserList" }
        assertTrue(userListFunc != null)
    }

    @Test
    fun `class with decorators and definite assignment`() {
        val code = """
@Injectable()
class DatabaseService {
    private connection!: Connection;
    private logger!: Logger;
    
    @Inject()
    private config!: DatabaseConfig;
    
    async connect(): Promise<void> {
        this.connection = await createConnection(this.config);
    }
}
"""
        val codeFile = TypeScriptAnalyser().analysis(code, "DatabaseService.ts")
        assertEquals(1, codeFile.DataStructures.size)
        val ds = codeFile.DataStructures[0]
        assertEquals("DatabaseService", ds.NodeName)
        assertTrue(ds.Fields.size >= 2)
    }
}
