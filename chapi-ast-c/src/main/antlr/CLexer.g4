lexer grammar CLexer;

channels { COMMENTS_CHANNEL, DIRECTIVE }

options { superClass = chapi.ast.antlr.CLexerBase; }

SINGLE_LINE_DOC_COMMENT: '///' InputCharacter*    -> channel(COMMENTS_CHANNEL);
EMPTY_DELIMITED_DOC_COMMENT: '/***/'              -> channel(COMMENTS_CHANNEL);
DELIMITED_DOC_COMMENT:       '/**' ~'/' .*? '*/'  -> channel(COMMENTS_CHANNEL);
SINGLE_LINE_COMMENT:     '//'  InputCharacter*    -> channel(COMMENTS_CHANNEL);
DELIMITED_COMMENT:       '/*'  .*? '*/'           -> channel(COMMENTS_CHANNEL);
WHITESPACES:   (Whitespace | Newline)+            -> channel(HIDDEN);
SHARP:         '#'                                -> mode(DIRECTIVE_MODE), skip;

//MultiLineMacro: '#' (~[\n]*? '\\' '\r'? '\n')+ ~ [\n]+ -> channel (HIDDEN);
//
//Directive:      '#' ~ [\n]* -> channel (HIDDEN);

Auto             : 'auto' ;
Break            : 'break' ;
Case             : 'case' ;
Char             : 'char' ;
Const            : 'const' ;
Continue         : 'continue' ;
Default          : 'default' ;
Do               : 'do' ;
Double           : 'double' ;
Else             : 'else' ;
Enum             : 'enum' ;
Extern           : 'extern' ;
Float            : 'float' ;
For              : 'for' ;
Goto             : 'goto' ;
If               : 'if' ;
Inline           : 'inline' ;
Int              : 'int' ;
Long             : 'long' ;
Register         : 'register' ;
Restrict         : 'restrict' ;
Return           : 'return' ;
Short            : 'short' ;
Signed           : 'signed' ;
Sizeof           : 'sizeof' ;
Static           : 'static' ;
Struct           : 'struct' ;
Switch           : 'switch' ;
Typedef          : 'typedef' ;
Union            : 'union' ;
Unsigned         : 'unsigned' ;
Void             : 'void' ;
Volatile         : 'volatile' ;
While            : 'while' ;
Alignas          : '_Alignas' ;
Alignof          : '_Alignof' ;
Atomic           : '_Atomic' ;
Bool             : '_Bool' ;
Complex          : '_Complex' ;
Generic          : '_Generic' ;
Imaginary        : '_Imaginary' ;
Noreturn         : '_Noreturn' ;
StaticAssert     : '_Static_assert' ;
ThreadLocal      : '_Thread_local' ;
OPEN_PARENS        : '(' ;
CLOSE_PARENS       : ')' ;
LeftBracket      : '[' ;
RightBracket     : ']' ;
LeftBrace        : '{' ;
RightBrace       : '}' ;
Less             : '<' ;
LessEqual        : '<=' ;
Greater          : '>' ;
GreaterEqual     : '>=' ;
LeftShift        : '<<' ;
RightShift       : '>>' ;
Plus             : '+' ;
PlusPlus         : '++' ;
Minus            : '-' ;
MinusMinus       : '--' ;
Star             : '*' ;
Div              : '/' ;
Mod              : '%' ;
And              : '&' ;
Or               : '|' ;
OP_AND           : '&&' ;
OP_OR             : '||' ;
Caret            : '^' ;
Not              : '!' ;
Tilde            : '~' ;
Question         : '?' ;
Colon            : ':' ;
Semi             : ';' ;
Comma            : ',' ;
OP_EQ           : '=' ;
StarAssign       : '*=' ;
DivAssign        : '/=' ;
ModAssign        : '%=' ;
PlusAssign       : '+=' ;
MinusAssign      : '-=' ;
LeftShiftAssign  : '<<=' ;
RightShiftAssign : '>>=' ;
AndAssign        : '&=' ;
XorAssign        : '^=' ;
OrAssign         : '|=' ;
Equal            : '==' ;
OP_NE         : '!=' ;
Arrow            : '->' ;
Dot              : '.' ;
Ellipsis         : '...' ;
TRUE             : 'true' ;
FALSE            : 'false' ;

// macro
//Ifdef:   'ifdef';
//Ifndef:  'ifndef';
Include: 'include';
Define:  'define';

// gcc
Extenion   : '__extension__';
EXT_BuiltinVaArg: '__builtin_va_arg';
EXT_BuiltinOffsetof: '__builtin_offsetof';
EXT_Error: '__error__';
EXT_M128: '__m128';
EXT_M128d: '__m128d';
EXT_M128i: '__m128i';
EXT_Typeof: '__typeof__';
EXT_Inline: '__inline__';
EXT_Stdcall: '__stdcall';
EXT_Declspec: '__declspec';
EXT_Cdecl: '__cdecl';
EXT_Clrcall: '__clrcall';
EXT_Fastcall: '__fastcall';
EXT_Thiscall: '__thiscall';
EXT_Vectorcall: '__vectorcall';
EXT_Asm: '__asm';
EXT_Asm_: '__asm__';
EXT_Attribute: '__attribute__';
EXT_Volatile: '__volatile__';


Identifier
    : IdentifierNondigit (IdentifierNondigit | Digit)*
    ;

// ignore the following asm blocks:
/*
    asm
    {
        mfspr x, 286;
    }
 */
AsmBlock
    : 'asm' ~'{'* '{' ~'}'* '}' -> channel(HIDDEN)
    ;

Whitespace
    : [ \t]+ -> channel(HIDDEN)
    ;

Newline
    : ('\r' '\n'? | '\n') -> channel(HIDDEN)
    ;

BlockComment
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

LineComment
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

// directive

mode DIRECTIVE_MODE;

DIRECTIVE_WHITESPACES:         Whitespace+                      -> channel(HIDDEN);
DIGITS:                        [0-9]+                           -> channel(DIRECTIVE);
DIRECTIVE_TRUE:                'true'                           -> channel(DIRECTIVE), type(TRUE);
DIRECTIVE_FALSE:               'false'                          -> channel(DIRECTIVE), type(FALSE);
INCLUDE:                       'include'                        -> channel(DIRECTIVE), type(Include);
DEFINE:                        'define'                         -> channel(DIRECTIVE);
UNDEF:                         'undef'                          -> channel(DIRECTIVE);
IFDEF:                         'ifdef'                          -> channel(DIRECTIVE), type(If);
IFNDEF:                        'ifndef'                         -> channel(DIRECTIVE), type(If);
DIRECTIVE_IF:                  'if'                             -> channel(DIRECTIVE), type(If);
ELIF:                          'elif'                           -> channel(DIRECTIVE);
DIRECTIVE_ELSE:                'else'                           -> channel(DIRECTIVE), type(Else);
ENDIF:                         'endif'                          -> channel(DIRECTIVE);
LINE:                          'line'                           -> channel(DIRECTIVE);
ERROR:                         'error' Whitespace+              -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
WARNING:                       'warning' Whitespace+            -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
REGION:                        'region' Whitespace*             -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
ENDREGION:                     'endregion' Whitespace*          -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
PRAGMA:                        'pragma' Whitespace+             -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
NULLABLE:                      'nullable' Whitespace+           -> channel(DIRECTIVE), mode(DIRECTIVE_TEXT);
DIRECTIVE_DEFAULT:             'default'                        -> channel(DIRECTIVE), type(Default);
DIRECTIVE_HIDDEN:              'hidden'                         -> channel(DIRECTIVE);
DIRECTIVE_OPEN_PARENS:         '('                              -> channel(DIRECTIVE), type(OPEN_PARENS);
DIRECTIVE_CLOSE_PARENS:        ')'                              -> channel(DIRECTIVE), type(CLOSE_PARENS);
DIRECTIVE_BANG:                '!'                              -> channel(DIRECTIVE), type(Not);
DIRECTIVE_OP_EQ:               '=='                             -> channel(DIRECTIVE), type(OP_EQ);
DIRECTIVE_OP_NE:               '!='                             -> channel(DIRECTIVE), type(OP_NE);
DIRECTIVE_OP_AND:              '&&'                             -> channel(DIRECTIVE), type(OP_AND);
DIRECTIVE_OP_OR:               '||'                             -> channel(DIRECTIVE), type(OP_OR);
DIRECTIVE_STRING:              '"' ~('"' | [\r\n\u0085\u2028\u2029])* '"' -> channel(DIRECTIVE), type(StringLiteral);
CONDITIONAL_SYMBOL:            Identifier                       -> channel(DIRECTIVE);
DIRECTIVE_SINGLE_LINE_COMMENT: '//' ~[\r\n\u0085\u2028\u2029]*  -> channel(COMMENTS_CHANNEL), type(SINGLE_LINE_COMMENT);
DIRECTIVE_NEW_LINE:            Newline                          -> channel(DIRECTIVE), mode(DEFAULT_MODE);

mode DIRECTIVE_TEXT;

TEXT:                          ~[\r\n\u0085\u2028\u2029]+       -> channel(DIRECTIVE);
TEXT_NEW_LINE:                 Newline    -> channel(DIRECTIVE), type(DIRECTIVE_NEW_LINE), mode(DEFAULT_MODE);

// Fragment

fragment InputCharacter:       ~[\r\n\u0085\u2028\u2029];

fragment IdentifierNondigit
    : Nondigit
    | UniversalCharacterName
    //|   // other implementation-defined characters...
    ;

fragment Nondigit
    : [a-zA-Z_]
    ;

fragment Digit
    : [0-9]
    ;

fragment UniversalCharacterName
    : '\\u' HexQuad
    | '\\U' HexQuad HexQuad
    ;

fragment HexQuad
    : HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
    ;

Constant
    : IntegerConstant
    | FloatingConstant
    //|   EnumerationConstant
    | CharacterConstant
    ;

fragment IntegerConstant
    : DecimalConstant IntegerSuffix?
    | OctalConstant IntegerSuffix?
    | HexadecimalConstant IntegerSuffix?
    | BinaryConstant
    ;

fragment BinaryConstant
    : '0' [bB] [0-1]+
    ;

fragment DecimalConstant
    : NonzeroDigit Digit*
    ;

fragment OctalConstant
    : '0' OctalDigit*
    ;

fragment HexadecimalConstant
    : HexadecimalPrefix HexadecimalDigit+
    ;

fragment HexadecimalPrefix
    : '0' [xX]
    ;

fragment NonzeroDigit
    : [1-9]
    ;

fragment OctalDigit
    : [0-7]
    ;

fragment HexadecimalDigit
    : [0-9a-fA-F]
    ;

fragment IntegerSuffix
    : UnsignedSuffix LongSuffix?
    | UnsignedSuffix LongLongSuffix
    | LongSuffix UnsignedSuffix?
    | LongLongSuffix UnsignedSuffix?
    ;

fragment UnsignedSuffix
    : [uU]
    ;

fragment LongSuffix
    : [lL]
    ;

fragment LongLongSuffix
    : 'll'
    | 'LL'
    ;

fragment FloatingConstant
    : DecimalFloatingConstant
    | HexadecimalFloatingConstant
    ;

fragment DecimalFloatingConstant
    : FractionalConstant ExponentPart? FloatingSuffix?
    | DigitSequence ExponentPart FloatingSuffix?
    ;

fragment HexadecimalFloatingConstant
    : HexadecimalPrefix (HexadecimalFractionalConstant | HexadecimalDigitSequence) BinaryExponentPart FloatingSuffix?
    ;

fragment FractionalConstant
    : DigitSequence? '.' DigitSequence
    | DigitSequence '.'
    ;

fragment ExponentPart
    : [eE] Sign? DigitSequence
    ;

fragment Sign
    : [+-]
    ;

DigitSequence
    : Digit+
    ;

fragment HexadecimalFractionalConstant
    : HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    | HexadecimalDigitSequence '.'
    ;

fragment BinaryExponentPart
    : [pP] Sign? DigitSequence
    ;

fragment HexadecimalDigitSequence
    : HexadecimalDigit+
    ;

fragment FloatingSuffix
    : [flFL]
    ;

fragment CharacterConstant
    : '\'' CCharSequence '\''
    | 'L\'' CCharSequence '\''
    | 'u\'' CCharSequence '\''
    | 'U\'' CCharSequence '\''
    ;

fragment CCharSequence
    : CChar+
    ;

fragment CChar
    : ~['\\\r\n]
    | EscapeSequence
    ;

fragment EscapeSequence
    : SimpleEscapeSequence
    | OctalEscapeSequence
    | HexadecimalEscapeSequence
    | UniversalCharacterName
    ;

fragment SimpleEscapeSequence
    : '\\' ['"?abfnrtv\\]
    ;

fragment OctalEscapeSequence
    : '\\' OctalDigit OctalDigit? OctalDigit?
    ;

fragment HexadecimalEscapeSequence
    : '\\x' HexadecimalDigit+
    ;

StringLiteral
    : EncodingPrefix? '"' SCharSequence? '"'
    ;

fragment EncodingPrefix
    : 'u8'
    | 'u'
    | 'U'
    | 'L'
    ;

fragment SCharSequence
    : SChar+
    ;

fragment SChar
    : ~["\\\r\n]
    | EscapeSequence
    | '\\\n'   // Added line
    | '\\\r\n' // Added line
    ;
