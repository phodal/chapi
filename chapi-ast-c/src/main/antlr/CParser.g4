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
    | STRING+
    | OPEN_PARENS expression CLOSE_PARENS
    | genericSelection
    | Extenion? OPEN_PARENS compoundStatement CLOSE_PARENS // Blocks (GCC extension)
    | '__builtin_va_arg' OPEN_PARENS unaryExpression ',' typeName CLOSE_PARENS
    | '__builtin_offsetof' OPEN_PARENS typeName ',' unaryExpression CLOSE_PARENS
    // for macro support
    ;

genericSelection
    : '_Generic' OPEN_PARENS assignmentExpression ',' genericAssocList CLOSE_PARENS
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

extensionExpression : Extenion? OPEN_PARENS typeName CLOSE_PARENS LeftBrace initializerList ','? RightBrace ;

postixCall
        :LeftBracket expression RightBracket                           #arrayAccessPostfixExpression
        // for macro support: ph_gen(, hpdata_age_heap, hpdata_t, age_link, hpdata_age_comp)
        | OPEN_PARENS ','? argumentExpressionList? CLOSE_PARENS                         #functionCallPostfixExpression
        | ('.' | '->') Identifier                                      #memberAccessPostfixExpression
        ;

//macroPostixCall
//        : postixCall
//        | Identifier OPEN_PARENS statement* CLOSE_PARENS
//        ;
//
argumentExpressionList
    : assignmentExpression (',' assignmentExpression)*
    ;

unaryExpression
    : ('++' | '--' | Sizeof)* (
        postfixExpression
        | unaryOperator castExpression
        | (Sizeof | '_Alignof') OPEN_PARENS typeName CLOSE_PARENS
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
    : Extenion? OPEN_PARENS typeName CLOSE_PARENS castExpression
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
    : additiveExpression ((LeftShift | '>>') additiveExpression)*
    ;

relationalExpression
    : shiftExpression ((comparator) shiftExpression)*
    ;

comparator : Less | Greater | LessEqual | GreaterEqual ;

equalityExpression
    : relationalExpression ((OP_EQ | OP_NE) relationalExpression)*
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
    : OP_EQ
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
    : Static?  declarationSpecifier+ initDeclaratorList? Semi
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
    : declarator (OP_EQ initializer)?
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
    | Extenion OPEN_PARENS ('__m128' | '__m128d' | '__m128i') CLOSE_PARENS
    | atomicTypeSpecifier
    | structOrUnionSpecifier
    | enumSpecifier
    | typedefName
    | EXT_Typeof OPEN_PARENS constantExpression CLOSE_PARENS // GCC extension
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
    : specifierQualifierList structDeclaratorList? Semi
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
    : enumerationConstant (OP_EQ constantExpression)?
    ;

enumerationConstant
    : Identifier
    ;

atomicTypeSpecifier
    : '_Atomic' OPEN_PARENS typeName CLOSE_PARENS
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
    | '__declspec' OPEN_PARENS Identifier CLOSE_PARENS
    ;

alignmentSpecifier
    : '_Alignas' OPEN_PARENS (typeName | constantExpression) CLOSE_PARENS
    ;

declarator
    : pointer? directDeclarator gccDeclaratorExtension*
    ;

directDeclarator
    :   Identifier                                                                  #identifierDirectDeclarator
    |   OPEN_PARENS declarator CLOSE_PARENS                                                          #declaratorDirectDeclarator
    |   directDeclarator LeftBracket typeQualifierList? assignmentExpression? RightBracket           #assignmentExpressionDirectDeclarator
    |   directDeclarator LeftBracket Static typeQualifierList? assignmentExpression RightBracket   #preStaticAssignmentExpressionDirectDeclarator
    |   directDeclarator LeftBracket typeQualifierList Static assignmentExpression RightBracket    #postStaticAssignmentExpressionDirectDeclarator
    |   directDeclarator LeftBracket typeQualifierList? '*' RightBracket                             #typeQualifierListPointerDirectDeclarator
    |   directDeclarator OPEN_PARENS parameterTypeList CLOSE_PARENS                                  #parameterDirectDeclarator
    |   directDeclarator OPEN_PARENS identifierList? CLOSE_PARENS                                    #identifierListDirectDeclarator
    |   Identifier Colon DigitSequence                                                #bitFieldDirectDeclarator  // bit field
    |   vcSpecificModifer Identifier                                                #vcSpecificModiferDirectDeclarator
    |   OPEN_PARENS typeSpecifier? pointer directDeclarator CLOSE_PARENS                             #functionPointerDirectDeclarator // function pointer like: (__cdecl *f)
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
    : EXT_Asm OPEN_PARENS STRING+ CLOSE_PARENS
    | gccAttributeSpecifier
    ;

gccAttributeSpecifier
    : '__attribute__' OPEN_PARENS OPEN_PARENS gccAttributeList CLOSE_PARENS CLOSE_PARENS
    ;

gccAttributeList
    : gccAttribute? (',' gccAttribute?)*
    ;

gccAttribute
    : ~(',' | OPEN_PARENS | CLOSE_PARENS) // relaxed def for "identifier or reserved word"
    (OPEN_PARENS argumentExpressionList? CLOSE_PARENS)?
    ;

nestedParenthesesBlock
    : (~(OPEN_PARENS | CLOSE_PARENS) | OPEN_PARENS nestedParenthesesBlock CLOSE_PARENS)*
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
    : OPEN_PARENS abstractDeclarator CLOSE_PARENS gccDeclaratorExtension*
    | LeftBracket typeQualifierList? assignmentExpression? RightBracket
    | LeftBracket Static typeQualifierList? assignmentExpression RightBracket
    | LeftBracket typeQualifierList Static assignmentExpression RightBracket
    | LeftBracket '*' RightBracket
    | OPEN_PARENS parameterTypeList? CLOSE_PARENS gccDeclaratorExtension*
    | directAbstractDeclarator LeftBracket typeQualifierList? assignmentExpression? RightBracket
    | directAbstractDeclarator LeftBracket Static typeQualifierList? assignmentExpression RightBracket
    | directAbstractDeclarator LeftBracket typeQualifierList Static assignmentExpression RightBracket
    | directAbstractDeclarator LeftBracket '*' RightBracket
    | directAbstractDeclarator OPEN_PARENS parameterTypeList? CLOSE_PARENS gccDeclaratorExtension*
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
    : designatorList Assign
    | directDeclarator
    ;

designatorList
    : designator+
    ;

designator
    : LeftBracket constantExpression RightBracket
    | '.' Identifier
    ;

staticAssertDeclaration
    : '_Static_assert' OPEN_PARENS constantExpression ',' STRING+ CLOSE_PARENS Semi
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
    | (EXT_Asm | EXT_Asm_) (Volatile | EXT_Volatile)? asmBody ;

asmBody
    : OPEN_PARENS (logicals)? (Colon (logicals)?)* CLOSE_PARENS Semi?
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
    : expression? Semi
    | expression Semi?
    ;

selectionStatement
    // the macro may break the grammar, so statement is optional
    : If OPEN_PARENS expression CLOSE_PARENS statement? (Else statement)?
    | Switch OPEN_PARENS expression CLOSE_PARENS statement
    ;

iterationStatement
    : While OPEN_PARENS expression CLOSE_PARENS statement
    | Do statement While OPEN_PARENS expression CLOSE_PARENS Semi
    | For OPEN_PARENS forCondition CLOSE_PARENS statement
    ;

//    |   For OPEN_PARENS expression? Semi expression?  Semi forUpdate? CLOSE_PARENS statement
//    |   For OPEN_PARENS declaration  expression? Semi expression? CLOSE_PARENS statement

forCondition
    : (forDeclaration | expression?) Semi forExpression? Semi forExpression?
    ;

forDeclaration
    : declarationSpecifier+ initDeclaratorList?
    ;

forExpression
    : assignmentExpression (',' assignmentExpression)*
    ;

jumpStatement
    : (
        Goto Identifier
        | Continue
        | Break
        | Return expression?
        | Goto unaryExpression // GCC extension
    ) Semi
    ;

externalDeclaration
    : functionDefinition
    | declaration
    | Semi    // stray ;
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
    | If
    | Else
    | Switch
    | While
    | Do
    | For
    | Goto
    | Continue
    | Break
    | Return
    ;
