package chapi.ast.antlr;

/**
 * Python version enumeration for syntax compatibility.
 * 
 * Version history:
 * - Python 3.10: Pattern matching (match/case)
 * - Python 3.12: Type parameter syntax (PEP 695), type statement
 * - Python 3.14: Template strings (t-strings), bracketless except (PEP 758)
 */
public enum PythonVersion {
    Autodetect(0),
    Python2(2),
    Python3(3),
    Python310(310),  // Pattern matching
    Python312(312),  // Type parameter syntax
    Python314(314);  // Template strings, bracketless except

    private final int value;

    PythonVersion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Check if this version supports Python 3.10+ features (pattern matching)
     */
    public boolean supportsPatternMatching() {
        return this == Autodetect || value >= 310 || this == Python3;
    }

    /**
     * Check if this version supports Python 3.12+ features (type parameters)
     */
    public boolean supportsTypeParameters() {
        return this == Autodetect || value >= 312;
    }

    /**
     * Check if this version supports Python 3.14+ features (t-strings, bracketless except)
     */
    public boolean supportsPython314Features() {
        return this == Autodetect || value >= 314;
    }
}
