/*
 [The "BSD licence"]
 Copyright (c) 2013 Sam Harwell
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/** C 2011 grammar built from the C11 Spec */

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar CParser;

options { tokenVocab=CLexer; }

compilationUnit
    : externalDeclaration* EOF
    ;

primaryExpression
    : Identifier
    | Constant
    | StringLiteral+
    | '(' expression ')'
    | genericSelection
    | '__extension__'? '(' compoundStatement ')' // Blocks (GCC extension)
    | '__builtin_va_arg' '(' unaryExpression ',' typeName ')'
    | '__builtin_offsetof' '(' typeName ',' unaryExpression ')'
    // for macro support
//    | typeQualifier? (typeKeywords | Identifier) (Identifier | typeKeywords)* pointer?
//    | StringLiteral? (directDeclarator | StringLiteral | ERROR)+
//    | Ellipsis
    ;

genericSelection
    : '_Generic' '(' assignmentExpression ',' genericAssocList ')'
    ;

genericAssocList
    : genericAssociation (',' genericAssociation)*
    ;

genericAssociation
    : (typeName | Default) Colon assignmentExpression
    ;

postfixExpression
    : (primaryExpression | extensionExpression) (postixCall | '++' | '--')*
    ;

extensionExpression : '__extension__'? '(' typeName ')' LeftBrace initializerList ','? RightBrace ;

postixCall
        :'[' expression ']'               #arrayAccessPostfixExpression
        // for macro support: ph_gen(, hpdata_age_heap, hpdata_t, age_link, hpdata_age_comp)
        | '(' ','? argumentExpressionList? ')'                         #functionCallPostfixExpression
        | ('.' | '->') Identifier                                      #memberAccessPostfixExpression
        ;

//macroPostixCall
//        : postixCall
//        | Identifier '(' statement* ')'
//        ;
//
argumentExpressionList
    : assignmentExpression (',' assignmentExpression)*
    ;

unaryExpression
    : ('++' | '--' | 'sizeof')* (
        postfixExpression
        | unaryOperator castExpression
        | ('sizeof' | '_Alignof') '(' typeName ')'
        | OP_AND Identifier // GCC extension address of label
    )
    ;

unaryOperator
    : And
    | '*'
    | '+'
    | '-'
    | '~'
    | '!'
    ;

castExpression
    : '__extension__'? '(' typeName ')' castExpression
    | unaryExpression
    | DigitSequence // for
    ;

multiplicativeExpression
    : castExpression (('*' | '/' | '%') castExpression)*
    ;

additiveExpression
    : multiplicativeExpression (('+' | '-') multiplicativeExpression)*
    ;

shiftExpression
    : additiveExpression (('<<' | '>>') additiveExpression)*
    ;

relationalExpression
    : shiftExpression ((comparator) shiftExpression)*
    ;

comparator : Less | Greater | LessEqual | GreaterEqual ;

equalityExpression
    : relationalExpression ((Equal | OP_NE) relationalExpression)*
    ;

andExpression
    : equalityExpression (And equalityExpression)*
    ;

exclusiveOrExpression
    : andExpression (Caret andExpression)*
    ;

inclusiveOrExpression
    : exclusiveOrExpression (Or exclusiveOrExpression)*
    ;

logicalAndExpression
    : inclusiveOrExpression (OP_AND inclusiveOrExpression)*
    ;

logicalOrExpression
    : logicalAndExpression (OP_OR logicalAndExpression)*
    ;

conditionalExpression
    : logicalOrExpression (Question expression Colon conditionalExpression)?
    ;

assignmentExpression
    : conditionalExpression
    | unaryExpression assignmentOperator assignmentExpression
    | DigitSequence
    // for support macro like: ph_gen(, hpdata_age_heap, &=)
//    | macroStatement
//    | assignmentOperator
//    | macroPostixCall
    ;

assignmentOperator
    : Equal
    | '*='
    | '/='
    | '%='
    | '+='
    | '-='
    | '<<='
    | '>>='
    | '&='
    | '^='
    | '|='
    ;

expression
    : assignmentExpression (',' assignmentExpression)*
    // for macro support, like ph_gen(, edata_avail, edata_t, avail_link, edata_esnead_comp)
    | ','
    ;

constantExpression
    : conditionalExpression
    ;

declaration
    : Static?  declarationSpecifier+ initDeclaratorList? ';'
    | staticAssertDeclaration
//    | macroStatement
    ;

declarationSpecifier
    : storageClassSpecifier
    | typeSpecifier
    | typeQualifier
    | functionSpecifier
    | alignmentSpecifier
    ;

initDeclaratorList
    : initDeclarator (',' initDeclarator)*
    ;

initDeclarator
    : declarator (Equal initializer)?
    ;

storageClassSpecifier
    : Typedef
    | Extern
    | Static
    | '_Thread_local'
    | Auto
    | Register
    ;

typeSpecifier
    : Void
    | Char
    | Short
    | Int
    | Long
    | Float
    | Double
    | Signed
    | Unsigned
    | '_Bool'
    | '_Complex'
    | '__m128'
    | '__m128d'
    | '__m128i'
    | '__extension__' '(' ('__m128' | '__m128d' | '__m128i') ')'
    | atomicTypeSpecifier
    | structOrUnionSpecifier
    | enumSpecifier
    | typedefName
    | EXT_Typeof '(' constantExpression ')' // GCC extension
    | postfixExpression
    ;

structOrUnionSpecifier
    : structOrUnion Identifier? LeftBrace structDeclarationList RightBrace
    | structOrUnion Identifier
    ;

structOrUnion
    : Struct
    | Union
    ;

structDeclarationList
    : structDeclaration+
    ;

structDeclaration // The first two rules have priority order and cannot be simplified to one expression.
    : specifierQualifierList structDeclaratorList? ';'
    | staticAssertDeclaration
//    | macroStatement
    ;

specifierQualifierList
    : (typeSpecifier | typeQualifier) specifierQualifierList?
    ;

structDeclaratorList
    : structDeclarator (',' structDeclarator)*
    ;

structDeclarator
    : declarator
    | declarator? Colon constantExpression
    ;

enumSpecifier
    : Enum Identifier? LeftBrace enumeratorList ','? RightBrace
    | Enum Identifier
    ;

enumeratorList
    : enumerator (',' enumerator)*
    ;

enumerator
    : enumerationConstant (Equal constantExpression)?
    ;

enumerationConstant
    : Identifier
    ;

atomicTypeSpecifier
    : '_Atomic' '(' typeName ')'
    ;

typeQualifier
    : Const
    | Restrict
    | Volatile
    | '_Atomic'
    ;

functionSpecifier
    : Inline
    | '_Noreturn'
    | '__inline__' // GCC extension
    | '__stdcall'
    | gccAttributeSpecifier
    | '__declspec' '(' Identifier ')'
    ;

alignmentSpecifier
    : '_Alignas' '(' (typeName | constantExpression) ')'
    ;

declarator
    : pointer? directDeclarator gccDeclaratorExtension*
    ;

directDeclarator
    :   Identifier                                                                  #identifierDirectDeclarator
    |   '(' declarator ')'                                                          #declaratorDirectDeclarator
    |   directDeclarator '[' typeQualifierList? assignmentExpression? ']'           #assignmentExpressionDirectDeclarator
    |   directDeclarator '[' Static typeQualifierList? assignmentExpression ']'   #preStaticAssignmentExpressionDirectDeclarator
    |   directDeclarator '[' typeQualifierList Static assignmentExpression ']'    #postStaticAssignmentExpressionDirectDeclarator
    |   directDeclarator '[' typeQualifierList? '*' ']'                             #typeQualifierListPointerDirectDeclarator
    |   directDeclarator '(' parameterTypeList ')'                                  #parameterDirectDeclarator
    |   directDeclarator '(' identifierList? ')'                                    #identifierListDirectDeclarator
    |   Identifier Colon DigitSequence                                                #bitFieldDirectDeclarator  // bit field
    |   vcSpecificModifer Identifier                                                #vcSpecificModiferDirectDeclarator
    |   '(' typeSpecifier? pointer directDeclarator ')'                             #functionPointerDirectDeclarator // function pointer like: (__cdecl *f)
    // #define KUMAX(x)	((uintmax_t)x##ULL)
//    | macroStatement                                                                #singleLineMacroDirectDeclarator
    ;

vcSpecificModifer
    : '__cdecl'
    | '__clrcall'
    | '__stdcall'
    | '__fastcall'
    | '__thiscall'
    | '__vectorcall'
    ;

gccDeclaratorExtension
    : Ext_Asm '(' StringLiteral+ ')'
    | gccAttributeSpecifier
    ;

gccAttributeSpecifier
    : '__attribute__' '(' '(' gccAttributeList ')' ')'
    ;

gccAttributeList
    : gccAttribute? (',' gccAttribute?)*
    ;

gccAttribute
    : ~(',' | '(' | ')') // relaxed def for "identifier or reserved word"
    ('(' argumentExpressionList? ')')?
    ;

nestedParenthesesBlock
    : (~('(' | ')') | '(' nestedParenthesesBlock ')')*
    ;

pointer
    : (('*' | Caret) typeQualifierList?)+ // ^ - Blocks language extension
    ;

typeQualifierList
    : typeQualifier+
    ;

parameterTypeList
    : parameterList (',' '...')?
    ;

parameterList
    : parameterDeclaration (',' parameterDeclaration)*
    ;

parameterDeclaration
    : declarationSpecifier+ declarator
    | declarationSpecifier+ abstractDeclarator?
    ;

identifierList
    : Identifier (',' Identifier)*
    ;

typeName
    : specifierQualifierList abstractDeclarator?
    ;

abstractDeclarator
    : pointer
    | pointer? directAbstractDeclarator gccDeclaratorExtension*
    ;

directAbstractDeclarator
    : '(' abstractDeclarator ')' gccDeclaratorExtension*
    | '[' typeQualifierList? assignmentExpression? ']'
    | '[' Static typeQualifierList? assignmentExpression ']'
    | '[' typeQualifierList Static assignmentExpression ']'
    | '[' '*' ']'
    | '(' parameterTypeList? ')' gccDeclaratorExtension*
    | directAbstractDeclarator '[' typeQualifierList? assignmentExpression? ']'
    | directAbstractDeclarator '[' Static typeQualifierList? assignmentExpression ']'
    | directAbstractDeclarator '[' typeQualifierList Static assignmentExpression ']'
    | directAbstractDeclarator '[' '*' ']'
    | directAbstractDeclarator '(' parameterTypeList? ')' gccDeclaratorExtension*
    ;

typedefName
    : Identifier
    ;

initializer
    : assignmentExpression
    | LeftBrace initializerList ','? RightBrace
//    | '#if' expressionStatement initializerList '#' 'endif'
//    | macroStatement ','? initializerList ','? macroStatement
    ;

initializerList
    : designation? initializer (',' designation? initializer)*
    ;

designation
    : designatorList Equal
    | directDeclarator
    ;

designatorList
    : designator+
    ;

designator
    : '[' constantExpression ']'
    | '.' Identifier
    ;

staticAssertDeclaration
    : '_Static_assert' '(' constantExpression ',' StringLiteral+ ')' ';'
    ;

statement
    : normalStatement
//    | macroStatement
    ;

normalStatement
    : labeledStatement
    | compoundStatement
    | expressionStatement
    | selectionStatement
    | iterationStatement
    | jumpStatement
    | (Ext_Asm | '__asm__') (Volatile | '__volatile__')? asmBody ;

asmBody
    : '(' (logicals)? (Colon (logicals)?)* ')' ';'?
    | typeSpecifier expression
    ;

logicals : logicalOrExpression (',' logicalOrExpression)* ;

//macroStatement
//    : PreprocessorBlock                                                                #preprocessorDeclaration
//    ;
//
labeledStatement
    : Identifier Colon statement?
    | Case constantExpression Colon statement
    | Default Colon statement
    ;

compoundStatement
    : LeftBrace blockItemList? RightBrace
    ;

blockItemList
    : blockItem+
    ;

blockItem
    : statement
    | declaration
    ;

expressionStatement
    : expression? ';'
    | expression ';'?
    ;

selectionStatement
    // the macro may break the grammar, so statement is optional
    : 'if' '(' expression ')' statement? ('else' statement)?
    | 'switch' '(' expression ')' statement
    ;

iterationStatement
    : While '(' expression ')' statement
    | Do statement While '(' expression ')' ';'
    | For '(' forCondition ')' statement
    ;

//    |   'for' '(' expression? ';' expression?  ';' forUpdate? ')' statement
//    |   For '(' declaration  expression? ';' expression? ')' statement

forCondition
    : (forDeclaration | expression?) ';' forExpression? ';' forExpression?
    ;

forDeclaration
    : declarationSpecifier+ initDeclaratorList?
    ;

forExpression
    : assignmentExpression (',' assignmentExpression)*
    ;

jumpStatement
    : (
        'goto' Identifier
        | 'continue'
        | 'break'
        | 'return' expression?
        | 'goto' unaryExpression // GCC extension
    ) ';'
    ;

externalDeclaration
    : functionDefinition
    | declaration
    | ';'    // stray ;
    ;

functionDefinition
    : (declarationSpecifier+)? declarator declarationList? compoundStatement
    ;

declarationList
    : declaration+
    ;

typeKeywords
    : Char
    | Short
    | Int
    | Long
    | Float
    | Double
    | Signed
    | Unsigned
    | Void
    | Static
    ;

keywords
    : Float
    | Double
    | Char
    | Int
    | Long
    | Short
    | Signed
    | Unsigned
    | Void
    | Const
    | Volatile
    | Extern
    | Static
    | Auto
    | Register
    | Typedef
    | Struct
    | Union
    | Enum
    | Case
    | Default
    | 'if'
    | 'else'
    | 'switch'
    | 'while'
    | 'do'
    | 'for'
    | 'goto'
    | 'continue'
    | 'break'
    | 'return'
    ;
