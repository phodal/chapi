package chapi.ast.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

abstract class CLexerBase extends Lexer {
    protected CLexerBase(CharStream input) {
        super(input);
    }
}
