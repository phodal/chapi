lexer grammar CPreprocessorLexer;

channels { COMMENTS_CHANNEL, DIRECTIVE }

options { superClass = chapi.ast.antlr.CPreprocessorLexerBase; }

AsmBlock
    : 'asm' ~'{'* '{' ~'}'* '}' -> channel(HIDDEN)
    ;

mode INTERPOLATION_FORMAT;

DOUBLE_CURLY_CLOSE_INSIDE:      '}}' -> type(FORMAT_STRING);
CLOSE_BRACE_INSIDE:             '}' { this.OnCloseBraceInside(); }   -> skip, popMode;
FORMAT_STRING:                  ~'}'+;
