parser grammar JsxParser;


options {
    tokenVocab=TypeScriptLexer;
    superClass=TypeScriptParserBase;
}

import TypeScriptParser;

program
    : statementList? EOF
    ;

returnStatement
    : Return ({this.notLineTerminator()}? expressionSequence)? eos
    | Return '(' htmlElements ')' eos
    ;

javascriptExtends
    : literal
    | htmlElements
    ;
