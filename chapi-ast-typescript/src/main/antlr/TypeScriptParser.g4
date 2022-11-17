/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers (original author) and Alexandre Vitorelli (contributor -> ported to CSharp)
 * Copyright (c) 2017 by Ivan Kochurkin (Positive Technologies):
    added ECMAScript 6 support, cleared and transformed to the universal grammar.
 * Copyright (c) 2018 by Juan Alvarez (contributor -> ported to Go)
 * Copyright (c) 2019 by Andrii Artiushok (contributor -> added TypeScript support)
 * Copyright (c) 2022 by NumberFour AG (contributor -> overall restructuring and d.ts support)
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
parser grammar TypeScriptParser;

options {
    tokenVocab=TypeScriptLexer;
    superClass=TypeScriptParserBase;
}


program
    : statementList? EOF
    ;



// Statement

statement
    : block
    | importStatement
    | decoratorList
    | exportStatement
    | declareStatement
    | ifStatement
    | iterationStatement
    | continueStatement
    | breakStatement
    | returnStatement
    | withStatement
    | labelledStatement
    | switchStatement
    | throwStatement
    | tryStatement
    | debuggerStatement
    | expressionStatement
    | emptyStatement
    ;

declareStatement
    : Declare? declarationStatement
    ;

declarationStatement
    : moduleDeclaration
    | namespaceDeclaration
    | globalScopeAugmentation
    | interfaceDeclaration
    | typeAliasDeclaration
    | functionDeclaration
    | classDeclaration
    | enumDeclaration
    | variableStatement
    ;

block
    : '{' statementList? '}'
    ;

statementList
    : statement+
    ;



// Types References

typeAnnotation
    : ':' typeRef
    ;

typeRef
    : conditionalTypeRef
    ;

conditionalTypeRef
    : unionTypeExpression ('extends' unionTypeExpression '?' conditionalTypeRef ':' conditionalTypeRef)?
    ;

unionTypeExpression
    : '|'? intersectionTypeExpression ('|' intersectionTypeExpression)*
    ;

intersectionTypeExpression
    : '&'? operatorTypeRef ('&' operatorTypeRef)*
    ;

operatorTypeRef
    : typeOperator? arrayTypeExpression
    ;

typeOperator
    : Keyof
    | Unique
    | ReadOnly
    ;

arrayTypeExpression
    : primaryTypeExpression arrayTypeExpressionSuffix*
    ;

arrayTypeExpressionSuffix
    : '[' ']'
    | '[' typeRef ']' // index access type
    ;

primaryTypeExpression:
    ( literalType
    | arrowFunctionTypeExpression
    | tupleTypeExpression
    | queryTypeRef
    | importTypeRef
    | inferTypeRef
    | typeRefWithModifiers
    | parenthesizedTypeRef
    );



literalType
    : BooleanLiteral
    | StringLiteral
    | numericLiteral
    ;

arrowFunctionTypeExpression
    : (
        ('abstract'? 'new')?
        typeParameters?
        '(' parameterList? ')'
        '=>'
    ) (typePredicateWithOperatorTypeRef | unionTypeExpression)
    ;

tupleTypeExpression
    : '['
    (
        ']'
    |   tupleTypeArgument (',' tupleTypeArgument)* ','? ']'
    );

tupleTypeArgument
    : '...'? Infer? (Identifier ':')? typeRef '?'?
    ;

typeVariable
    : Identifier (Extends typeRef)?
    ;

typeRefWithModifiers
    : thisTypeRef
    | parameterizedTypeRef
    | objectLiteralTypeRef
    ;

parenthesizedTypeRef
    : '(' typeRef ')'
    ;

parameterizedTypeRef
    : typeName typeArguments?
    ;

typeName
    : typeReferenceName ('.' typeReferenceName)*
    ;

typeArguments
    : '<' (typeArgumentList ','?)? '>'
    ;

typeArgumentList
    : typeArgument (',' typeArgument)*
    ;

typeArgument
    : Infer? typeRef
    ;

objectLiteralTypeRef
    : '{' interfaceBody? '}'
    ;

thisTypeRef
    : This
    ;

queryTypeRef
    : 'typeof' propertyAccessExpressionInTypeRef
;

importTypeRef
    : Import '(' StringLiteral ')' ('.' parameterizedTypeRef)?
;

typePredicateWithOperatorTypeRef
    : Asserts? (This | bindingIdentifier) Is conditionalTypeRef
    | Asserts bindingIdentifier
;

bindingIdentifier
    : identifierName
    ;


propertyAccessExpressionInTypeRef
    : typeReferenceName ('.' typeReferenceName)* // e.g. Symbol.iterator or osConstants.priority (in node/constants.d.ts)
;

inferTypeRef:
	Infer typeReferenceName
;

typeAliasDeclaration
    : TypeAlias identifierName typeParameters? '=' typeRef SemiColon?
    ;

typeParameters
    : '<' typeParameterList? '>'
    ;

typeParameterList
    : typeParameter (',' typeParameter)*
    ;

typeParameter
    : identifierName constraint? ('=' defaultType)?
    ;

constraint
    : 'extends' typeRef
    ;

defaultType
    : typeRef
    ;


// Module

moduleDeclaration
    : Module moduleName block
    ;

moduleName
    : StringLiteral
    | Identifier
    ;



// Namespace

namespaceDeclaration
    : Namespace namespaceName block
    ;

namespaceName
    : typeReferenceName ('.' typeReferenceName)*
    ;

globalScopeAugmentation
    : Global block
    ;

// Decorators

decoratorList
    : decorator+ ;

decorator
    : '@' (decoratorMemberExpression | decoratorCallExpression)
    ;

decoratorMemberExpression
    : Identifier
    | decoratorMemberExpression '.' identifierName
    | '(' singleExpression ')'
    ;

decoratorCallExpression
    : decoratorMemberExpression arguments
    | objectLiteral
    ;

// Interface

interfaceDeclaration
    : Interface identifierName typeParameters? interfaceExtendsClause? '{' interfaceBody? '}' SemiColon?
    ;

interfaceExtendsClause
    : Extends classOrInterfaceTypeList
    ;

classOrInterfaceTypeList
    : parameterizedTypeRef (',' parameterizedTypeRef)*
    ;

interfaceBody
    : interfaceMemberList (SemiColon | ',')?
    ;

interfaceMemberList
    : interfaceMember (({lineTerminatorAhead();} | SemiColon | ',') interfaceMember)*
    ;

interfaceMember
    : constructSignature
    | callSignature
    | indexSignature
    | getAccessor
    | setAccessor
    | methodSignature
    | enumSignature
    | propertySignature
    | '[' typeRef In (Keyof | Typeof)* typeRef ']' '?'? typeAnnotation
    ;

enumSignature
    : identifierOrKeyWord '?'? ':' '|'? typeRef ('|' typeRef)*
    ;

constructSignature
    : 'new' typeParameters? parameterBlock typeAnnotation?
    ;

callSignature
    : typeParameters? parameterBlock ( ':' typePredicateWithOperatorTypeRef | typeAnnotation)?
    ;

indexSignature
    : ReadOnly? ('+'? 'readonly' | '-' 'readonly')? '[' indexSignatureElement ']' ('+'? '?' | '-' '?')? typeAnnotation
    ;

indexSignatureElement
    : identifierName ':' (Number|String)
    | Identifier In typeRef
    ;

methodSignature
    : propertyName '?'? callSignature
    ;

propertySignature
    : ReadOnly? propertyName '?'? typeAnnotation?
    ;



// Enum

enumDeclaration
    : Const? Enum identifierName '{' enumBody? '}' SemiColon?
    ;

enumBody
    : enumMemberList ','?
    ;

enumMemberList
    : enumMember (',' enumMember)*
    ;

enumMember
    : propertyName ('=' singleExpression)?
    ;



// Function Declaration

functionDeclaration
    : propertyMemberBase Function '*'? identifierName callSignature functionBody? SemiColon?
    ;

functionBody
    : statementList?
    ;


// Class Declaration

classDeclaration
    : Abstract? Class identifierName typeParameters? classHeritage classBody SemiColon?
    ;

classHeritage
    : classExtendsClause? classImplementsClause?
    ;

classExtendsClause
    : Extends parameterizedTypeRef
    ;

classImplementsClause
    : Implements classOrInterfaceTypeList
    ;

classBody
    :  '{' classMemberList? SemiColon? '}'
    ;

classMemberList
    : classMember (({lineTerminatorAhead();} | SemiColon) classMember)*
    ;

classMember
    : constructorDeclaration
    | indexSignature
    | decoratorList? propertyMemberDeclaration
    ;

constructorDeclaration
    : accessibilityModifier? Constructor '(' formalParameterList? ')' block?
    ;

//propertyMemberDeclaration
//    : abstractDeclaration
//    | propertyMember
//    ;

propertyMemberDeclaration
    : propertyMemberBase propertyName  '!'? '?'? typeAnnotation? initializer? SemiColon             # PropertyDeclarationExpression
    | propertyMemberBase propertyName callSignature ( ('{' functionBody '}') | SemiColon)           # MethodDeclarationExpression
    | propertyMemberBase (getAccessor | setAccessor)                                                # GetterSetterDeclarationExpression
    | abstractDeclaration                                                                           # AbstractMemberDeclaration
    ;

abstractDeclaration
    : Abstract (Identifier callSignature | variableStatement) eos
    ;

//propertyMember
//    : propertyMemberBase
//    (
//          getAccessor
//        | setAccessor
//        | propertyOrMethod
//    )
//    ;

propertyMemberBase
    : Async? accessibilityModifier? Static?
    ;

propertyOrMethod
    : ReadOnly? propertyName '?'? (
          (typeAnnotation? initializer?)
        | (callSignature block?)
    )
    ;

initializer
    : '=' singleExpression
    ;


parameterBlock
    : '(' (This typeAnnotation? ',')?  ( formalParameterList ','?)? ')'
    ;


formalParameterList
    : formalParameterArg (',' formalParameterArg)* (',' lastFormalParameterArg)? ','?
    ;

formalParameterArg
    : decoratorList? accessibilityModifier? ReadOnly? identifierOrKeyWord '?'? typeAnnotation? ('=' singleExpression)?      // ECMAScript 6: Initialization
    | lastFormalParameterArg
    // ([key, value]: [string, string[]])
//    | arrayLiteral (':' formalParameterList)?                                                                // ECMAScript 6: Parameter Context Matching
//    | objectLiteral (':' formalParameterList)?                                                               // ECMAScript 6: Parameter Context Matching
    // `addThing({ payload }, { call }){}`
//    | objectLiteral (',' objectLiteral)*                                                                     // ECMAScript 6: Parameter Context Matching
    ;

lastFormalParameterArg                        // ECMAScript 6: Rest Parameter
    : Ellipsis identifierOrPattern typeAnnotation?
    ;

parameterList
    : restParameter                                     #OnlyRestParameter
    | parameter (',' parameter)* (',' restParameter)?   #NormalParameter
    ;

parameter
    : requiredParameter
    | optionalParameter
    ;

requiredParameter
    : decoratorList? accessibilityModifier? identifierOrPattern typeAnnotation?
    ;

optionalParameter
    : decoratorList? ( accessibilityModifier? identifierOrPattern ('?' typeAnnotation? | typeAnnotation? initializer))
    ;

restParameter
    : '...' singleExpression typeAnnotation?
    ;

//parameterBlock:
//    '(' (This colonSepTypeRef? ',')?  parameterListTrailingComma? ')'
//    ;
//
//parameterListTrailingComma
//    : parameterList ','?
//    ;
//
//parameterList
//    : restParameter
//    | parameter (',' parameter)* (',' restParameter)?
//    ;
//
//restParameter
//    : '...' identifierOrPattern colonSepTypeRef?
//    ;
//
//parameter
//    : requiredParameter
//    | optionalParameter
//    ;
//
//requiredParameter
//    : decoratorList? accessibilityModifier? identifierOrPattern colonSepTypeRef?
//    ;
//
//optionalParameter
//    : decoratorList? accessibilityModifier? identifierOrPattern
//    (   '?' typeAnnotation?
//        | typeAnnotation? initializer
//    )
//    ;

accessibilityModifier
    : Public
    | Private
    | Protected
    | ReadOnly
    ;

identifierOrPattern
    : identifierName
    | bindingPattern
    ;





bindingPattern
    : arrayLiteral
    | objectLiteral
    ;



// Array Literal

arrayLiteral
    : '[' elementList? ']'
    ;

elementList
    : arrayElement (','+ arrayElement)* ','?
    ;

arrayElement
    : Ellipsis? bindingElement
    ;

bindingElement
    : bindingPattern
    | Identifier
    ;



// Object Literal

objectLiteral
    : '{' (propertyAssignment (',' propertyAssignment)* ','?)? '}'
    ;

propertyAssignment
    : propertyName (':' identifierOrKeyWord | bindingPattern)? ('=' singleExpression)?  # PropertyExpressionAssignment
    | propertyName '?'? (':' |'=') singleExpression               # PropertyExpressionAssignment
    | '[' singleExpression ']' '?'?  ':' singleExpression         # ComputedPropertyExpressionAssignment
    | propertyName '?'? (':' |'=') '[' singleExpression ']'       # PropertyExpressionAssignment
    | getAccessor                                                 # PropertyGetter
    | setAccessor                                                 # PropertySetter
    | generatorMethod                                             # MethodProperty
    | restParameter                                               # RestParameterInObject
    ;

propertyName
    : StringLiteral                                               # StringProperty
    | numericLiteral                                              # NumericProperty
    | computedPropertyName                                        # ComputedProperty
    | identifierName                                              # IdentifierProperty
    ;

computedPropertyName
    : '[' (identifierName '.' identifierName // usually Symbol.iterator
          | StringLiteral
          )
      ']'
    ;

getAccessor
    : getter '(' ')' typeAnnotation? block?
    ;

setAccessor
    : setter '(' (Identifier | bindingPattern) typeAnnotation? ')' block?
    ;

generatorMethod
    : '*'?  Identifier parameterBlock block
    ;

arguments
    : '(' (argumentList ','?)? ')'
    ;

argumentList
    : argument (',' argument)*
    ;

argument                      // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | Identifier)
    ;




// Import Statement

importStatement
    : Import (fromBlock | importAliasDeclaration | importAll)
    ;

fromBlock
    : importDefault? (Dollar | Lodash | importNamespace | moduleItems) From StringLiteral  eos
    | StringLiteral eos
    ;

moduleItems
    : '{' (aliasName ',')* (aliasName ','?)? '}'
    ;

importDefault
    : aliasName ','
    ;

aliasName
    : identifierName (As identifierName)?
    // for import { of } from 'rxjs';
    | Of
    ;

importNamespace
    : ('*' | identifierName) (As identifierName)?
    ;

importFrom
    : From StringLiteral
    ;

importAliasDeclaration
    : identifierName '=' namespaceName SemiColon
    | identifierName '=' Require '(' StringLiteral ')' SemiColon
    ;

importAll
    : StringLiteral
    ;


// Export Statement

exportStatement
    : Export Declare? exportStatementTail
    ;

exportStatementTail
    : Default? declarationStatement                     #ExportElementDirectly
    | Default identifierName                            #ExportElementAsDefault
    | multipleExportElements (From StringLiteral)?      #ExportElements
    | Multiply (As identifierName)? From StringLiteral  #ExportModule
    | As Namespace identifierName eos                   #ExportAsNamespace
    | '=' namespaceName eos                             #ExportEquals
    | Import identifierName '=' namespaceName eos       #ExportImport
    ;

multipleExportElements
    : '{' aliasName (',' aliasName)* ','? '}'
    ;


// Variable Statement

variableStatement
    : accessibilityModifier? ReadOnly? varModifier (bindingPatternBlock | variableDeclarationList) SemiColon?
    ;

varModifier
    : Var
    | Let
    | Const
    ;

bindingPatternBlock
    : bindingPattern typeAnnotation? initializer?
    ;

variableDeclarationList
    : variableDeclaration (',' variableDeclaration)*
    ;

variableDeclaration
//    : ( identifierName | arrayLiteral | objectLiteral) typeAnnotation? ('=' typeParameters? singleExpression)? // ECMAScript 6: Array & Object Matching
    : identifierName typeAnnotation? ('=' typeParameters? singleExpression)? // ECMAScript 6: Array & Object Matching
    ;



// Switch Statement

switchStatement
    : Switch '(' expressionSequence ')' caseBlock
    ;

caseBlock
    : '{' caseClauses? (defaultClause caseClauses?)? '}'
    ;

caseClauses
    : caseClause+
    ;

caseClause
    : Case expressionSequence ':' statementList?
    ;

defaultClause
    : Default ':' statementList?
    ;



// Try Statement

tryStatement
    : Try block (catchProduction finallyProduction? | finallyProduction)
    ;

catchProduction
    : Catch '(' Identifier ')' block
    ;

finallyProduction
    : Finally block
    ;




// Other Statements

emptyStatement
    : SemiColon
    ;


ifStatement
    : If '(' expressionSequence ')' statement (Else statement)?
    ;


iterationStatement
    : Do statement While '(' expressionSequence ')' eos                                                         # DoStatement
    | While '(' expressionSequence ')' statement                                                                # WhileStatement
    | For '(' expressionSequence? SemiColon expressionSequence? SemiColon expressionSequence? ')' statement     # ForStatement
    | For '(' varModifier variableDeclarationList SemiColon expressionSequence? SemiColon expressionSequence? ')'
          statement                                                                                             # ForVarStatement
    | For '(' singleExpression (In | Identifier{this.p("of")}?) expressionSequence ')' statement                # ForInStatement
    | For '(' varModifier variableDeclaration (In | Identifier{this.p("of")}?) expressionSequence ')' statement # ForVarInStatement
    ;


continueStatement
    : Continue ({this.notLineTerminator()}? Identifier)? eos
    ;


breakStatement
    : Break ({this.notLineTerminator()}? Identifier)? eos
    ;


returnStatement
    : Return ({this.notLineTerminator()}? expressionSequence)? eos
//    | Return '(' htmlElements ')' eos
    ;


withStatement
    : With '(' expressionSequence ')' statement
    ;


labelledStatement
    : Identifier ':' statement
    ;


throwStatement
    : Throw {this.notLineTerminator()}? expressionSequence eos
    ;


debuggerStatement
    : Debugger eos
    ;


expressionStatement
    : {this.notOpenBraceAndNotFunction()}? expressionSequence eos
    ;


expressionSequence
    : singleExpression (',' singleExpression)*
    ;



// Expressions

singleExpression
    : functionExpression                                                     # FunctionExpressionL
    | arrowFunctionDeclaration                                               # ArrowFunctionExpressionL
    | classExpression                                                        # ClassExpressionL

    | Yield ({this.notLineTerminator()}? expressionSequence)?                # YieldExpression
    | Await singleExpression                                                 # AwaitExpression

    // respect precedence by order of sub-rules
    | singleExpression assignmentOperator singleExpression                   # AssignmentExpression
    | singleExpression '?' singleExpression ':' singleExpression             # TernaryExpression
    | singleExpression '?''?' singleExpression                               # coalesceExpression
    | singleExpression ('||' | '&&') singleExpression                        # LogicalExpression
    | singleExpression ('&' | '^' | '|' ) singleExpression                   # BinaryExpression
    | singleExpression ('==' | '!=' | '===' | '!==') singleExpression        # EqualityExpression
    | singleExpression relationalOperator singleExpression                   # RelationalExpression
    | singleExpression ('<<' | '>''>' | '>''>''>') singleExpression          # BitShiftExpression
    | singleExpression ('+' | '-') singleExpression                          # AdditiveExpression
    | singleExpression ('*' | '/' | '%') singleExpression                    # MultiplicativeExpression
    | unaryOperator singleExpression                                         # UnaryExpression
    | singleExpression As typeRef                                            # CastAsExpression
    | singleExpression {this.notLineTerminator()}? ('++' | '--')             # PostfixExpression

    // this.form.value?.id?.[0]
    | singleExpression '?'? '!'? '.'? '[' expressionSequence ']'             # MemberIndexExpression
    // for: `onHotUpdateSuccess?.();`
    // onChange?.(userName || password || null)
    | singleExpression '?'? '!'? '.' '#'? identifierName? typeArguments?     # MemberDotExpression
    // for: `onHotUpdateSuccess?.();`
    | singleExpression '?'? '!'? '.' '#'? '(' identifierName? ')'            # MemberDotExpression
    // request('/api/system-info', { method: 'GET' });
//    | singleExpression arguments                                             # MemberDotExpression

    | singleExpression ('.' | '?''.') identifierName                         # PropertyAccessExpression
    |  New (Dot Target| singleExpression )                                   # NewExpression
    // find arguments first, then found the call expression
    | '(' expressionSequence ')'                                             # ParenthesizedExpression

  // TODO:
  //  | iteratorBlock                                                          # IteratorsExpression
  //  | generatorBlock                                                         # GeneratorsExpression

    | This                                                                   # ThisExpression
    | Super                                                                  # SuperExpression
    | typeArguments? identifierName                                          # IdentifierExpression
    | literal                                                                # LiteralExpression
    | arrayLiteral                                                           # ArrayLiteralExpression
    | objectLiteral                                                          # ObjectLiteralExpression
    | templateStringLiteral                                                  # TemplateStringExpression
    | singleExpression As asExpression                                       # CastAsExpression

    // TODO: careful use those
    | singleExpression typeArguments? arguments                              # ArgumentsExpression
    | htmlElements                                                           # HtmlElementExpression
    ;


asExpression
    : singleExpression
    ;

functionExpression
    : Function '*'? Identifier? '(' formalParameterList? ')' typeAnnotation? '{' functionBody '}'
    ;

arrowFunctionDeclaration
    : Async? arrowFunctionParameters? typeAnnotation? '=>' arrowFunctionBody
    ;

arrowFunctionParameters
    : identifierName
    | typeRef? '(' formalParameterList? ','? ')'
    ;

arrowFunctionBody
    : singleExpression
    | '{' statementList? '}'
    ;

classExpression
    : Class Identifier? classBody
    ;

assignmentOperator
    : '=' | '*=' | '/=' | '%=' | '+='
    | '-' '=' /* must be split into two literals since jsx attribute names may end with a dash as in attr-="value" */
    | '<<=' | '>' '>' '>'? '=' | '&=' | '^=' | '|='
    ;

relationalOperator
    : Instanceof | In | '<' | '>' | '<=' | '>='
    ;

unaryOperator
    : Delete | Void | Typeof | '++' | '--' | '+' | '-' | '~' | '!'
    ;




generatorBlock
    : '{' generatorDefinition (',' generatorDefinition)* ','? '}'
    ;

generatorDefinition
    : '*' iteratorDefinition
    ;

iteratorBlock
    : '{' iteratorDefinition (',' iteratorDefinition)* ','? '}'
    ;

iteratorDefinition
    : '[' singleExpression ']' parameterBlock block
    ;

literal
    : NullLiteral
    | BooleanLiteral
    | StringLiteral
    | templateStringLiteral
    | RegularExpressionLiteral
    | numericLiteral
    ;

templateStringLiteral
    : BackTick templateStringAtom* BackTick
    ;

templateStringAtom
    : TemplateStringAtom
    | TemplateStringStartExpression singleExpression CloseBrace
    ;

numericLiteral
    : '-'? DecimalLiteral
    | HexIntegerLiteral
    | OctalIntegerLiteral
    | OctalIntegerLiteral2
    | BinaryIntegerLiteral
    ;

identifierOrKeyWord
    : Identifier
    | Require
    | TypeAlias
    ;

identifierName
    : reservedWord
    | Identifier
    ;

reservedWord
    : keyword
    | BooleanLiteral
    ;


typeReferenceName
    : keywordAllowedInTypeReferences
    | BooleanLiteral
    | Identifier
    ;

keyword:
    keywordAllowedInTypeReferences
    | ReadOnly // cannot reference types named 'readonly'
    | Typeof
    ;

keywordAllowedInTypeReferences:
    Abstract
    | Any
    | As
    | Asserts
    | Async
    | Await
    | Break
    | Boolean
    | Case
    | Catch
    | Class
    | Const
    | Constructor
    | Continue
    | Debugger
    | Declare
    | Default
    | Delete
    | Do
    | Else
    | Enum
    | Export
    | Extends
    | Finally
    | For
    | From
    | Function
    | Get
    | Global
    | If
    | Implements
    | Import
    | In
    | Infer
    | Instanceof
    | Interface
    | Is
    | Let
    | Module
    | Namespace
    | Never
    | New
    | NullLiteral
    | Number
    | Package
    | Private
    | Protected
    | Public
    // | ReadOnly // cannot reference types named 'readonly'
    | Require
    | Return
    | Set
    | Static
    | String
    | Super
    | Switch
    | Symbol
    | Target
    | This
    | Throw
    | Try
    | TypeAlias
    // | Typeof // cannot reference types named 'typeof'
    | UndefinedLiteral
    | Unknown
    | Unique
    | Var
    | Void
    | While
    | With
    | Yield
    ;

getter
    : Get propertyName
    ;

setter
    : Set propertyName
    ;

eos
    : SemiColon
    | EOF
    | {this.lineTerminatorAhead()}?
    | {this.closeBrace()}?
    ;

htmlElements
    : htmlElement+
    ;

htmlElement
    : '<' htmlTagStartName htmlAttribute* '>' htmlContent '<''/' htmlTagClosingName '>'
    // for React
    | '<' '>' htmlContent '<''/' '>'
    | '<' htmlTagName htmlAttribute* htmlContent '/''>'
    | '<' htmlTagName htmlAttribute* '/''>'
    | '<' htmlTagName htmlAttribute* '>'
    ;

htmlContent
    : htmlChardata? ((htmlElement) htmlChardata?)*
    | htmlChardata? ((htmlElement | objectExpressionSequence) htmlChardata?)*
    ;

htmlTagStartName
    : htmlTagName {this.pushHtmlTagName($htmlTagName.text);}
    ;

htmlTagClosingName
    : htmlTagName {this.popHtmlTagName($htmlTagName.text)}?
    ;

htmlTagName
    : TagName
    | keyword
    | Identifier
    | Identifier ('.' Identifier)*   // bug fix: for <Form.Item></Form.Item>
    ;

htmlAttribute
    : htmlAttributeName '=' htmlAttributeValue
    | htmlAttributeName
    | objectLiteral
    ;

htmlAttributeName
    : TagName
    | identifierOrKeyWord
    | Identifier ('-' Identifier)*		// 2020/10/28 bugfix: '-' is recognized as MINUS and TagName is splited by '-'.
    ;

htmlChardata
    : ~('<'|'{')+
    ;

htmlAttributeValue
    : AttributeValue
    | StringLiteral
    | objectExpressionSequence
    ;

objectExpressionSequence
    : '{' expressionSequence '}'
    ;
