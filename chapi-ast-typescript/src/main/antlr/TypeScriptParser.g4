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
    | extendRef (',' extendRef)* ','?
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

extendRef
    : operatorTypeRef Extends operatorTypeRef
    | operatorTypeRef Extends operatorTypeRef '=' operatorTypeRef
    | operatorTypeRef '=' operatorTypeRef
    ;



literalType
    : BooleanLiteral
    | StringLiteral
    | numericLiteral
    | keyword
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

typeRefWithModifiers
    : thisTypeRef
    | parameterizedTypeRef
    | objectLiteralTypeRef
    ;

parenthesizedTypeRef
    : '(' typeRef ')'
    ;

parameterizedTypeRef
    : typeName ('?' | '!')? typeArguments?
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
    | propertySignature
    | enumSignature
    | '[' typeRef In (Keyof | Typeof)* typeRef ']' '?'? typeAnnotation
    ;

enumSignature
    : identifierOrKeyWord '?'? ':' '|'? typeRef ('|' typeRef)*
    ;

constructSignature
    : 'new' typeParameters? parameterBlock typeAnnotation?
    ;

callSignature
    : typeParameters? '(' parameterList? ','? ')' (':' (typePredicateWithOperatorTypeRef | typeRef))?
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
    : propertyMemberBase Function '*'? identifierName callSignature ('{' functionBody? '}' | eos )
    ;

functionBody
    : statementList
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
    | decoratorList? propertyMemberDeclaration eos
    ;

constructorDeclaration
    : accessibilityModifier? Constructor '(' formalParameterList? ')' block?
    ;

propertyMemberDeclaration
    : propertyMemberBase (getAccessor | setAccessor)                                                # GetterSetterDeclarationExpression
    | abstractDeclaration                                                                           # AbstractMemberDeclaration
    | propertyMemberBase propertyName callSignature ( ('{' functionBody? '}') | eos)                # MethodDeclarationExpression
    | propertyMemberBase propertyName '!'? '?'? typeAnnotation? initializer?                        # PropertyDeclarationExpression
    ;

abstractDeclaration
    : Abstract (identifierName '?'?  typeAnnotation?  callSignature? | variableStatement)
    ;

propertyMemberBase
    : Async? accessibilityModifier* Static?
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
    : decoratorList? accessibilityModifier? ReadOnly? identifierName '?'? typeAnnotation? ('=' singleExpression)?      // ECMAScript 6: Initialization
    | lastFormalParameterArg
    // ([key, value]: [string, string[]])
    | arrayLiteral (':' formalParameterList)?                                                                // ECMAScript 6: Parameter Context Matching
    | objectLiteral (':' formalParameterList)?                                                               // ECMAScript 6: Parameter Context Matching
//     `addThing({ payload }, { call }){}`
    | objectLiteral (',' objectLiteral)*                                                                     // ECMAScript 6: Parameter Context Matching
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
    | singleExpression
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
    // for es6 { baseUrl , }
    | identifierName                                              # PropertyShorthand
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
    : getter identifierName? '(' ')' typeAnnotation? block?
    ;

setAccessor
    : setter identifierName? '(' (Identifier | bindingPattern) typeAnnotation? ')' block?
    ;

generatorMethod
    : '*'?  Identifier parameterBlock typeAnnotation? block
    ;

arguments
    : '(' (argumentList ','?)? ')'
    ;

argumentList
    : argument (',' argument)*
    ;

argument                      // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | Identifier) ('?' | '!')?
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
    ;

importNamespace
    : ('*' | identifierName) (As identifierName)?
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
    | Default identifierName                            #ExportDefaultDeclaration
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
    : accessibilityModifier? ReadOnly? varModifier (bindingPatternBlock | variableDeclarationList) eos
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
    : identifierName typeAnnotation? ('=' typeParameters? singleExpression)? ('?' | '!')? // ECMAScript 6: Array & Object Matching
    | arrayLiteral
    | objectLiteral
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
    | For '(' singleExpression (In | Of) expressionSequence ')' statement                # ForInStatement
    | For '(' varModifier variableDeclaration (In | Of) expressionSequence ')' statement # ForVarInStatement
    ;


continueStatement
    : Continue ({this.notLineTerminator()}? Identifier)? eos
    ;


breakStatement
    : Break ({this.notLineTerminator()}? Identifier)? eos
    ;


returnStatement
    : Return ({this.notLineTerminator()}? expressionSequence)? eos
    | Return htmlAssign eos
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

    | singleExpression templateStringLiteral                                 # TemplateStringExpression  // ECMAScript 6
    | iteratorBlock                                                          # IteratorsExpression // ECMAScript 6
    | generatorBlock                                                         # GeneratorsExpression // ECMAScript 6
    | generatorFunctionDeclaration                                           # GeneratorsFunctionExpression // ECMAScript 6

    // TODO: careful use those
    | singleExpression '(' (argumentList ','?)? ')'                          # ArgumentsExpression
    //  RealtionExpression will have conflict
    | singleExpression '<' typeArgumentList '>' '(' (argumentList ','?)? ')' # ArgumentsExpression

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

    // todo: rename #MemberDotExpression to #CallExpression
    // for: `onHotUpdateSuccess?.();`
    // onChange?.(userName || password || null)
    | singleExpression  ('?' | '!')* '.' '#'? identifierName typeArguments?  # MemberDotExpression
    // for: `onHotUpdateSuccess?.();`
    | singleExpression  ('?' | '!')* '.' '#'? '(' identifierName? ')'        # MemberDotExpression

    // onChange?.(userName || password || null)
    | singleExpression  '?''.' '(' singleExpression ')' typeArguments?       # MemberDotExpression
    // request('/api/system-info', { method: 'GET' });
//    | singleExpression arguments                                             # MemberDotExpression

    | singleExpression ('.' | '?''.') identifierName                         # PropertyAccessExpression
    |  New (Dot Target| singleExpression )                                   # NewExpression
    // find arguments first, then found the call expression
    | '(' expressionSequence ')'                                             # ParenthesizedExpression

  // TODO:
  //  | iteratorBlock                                                          # IteratorsExpression
  //  | generatorBlock                                                         # GeneratorsExpression

    | This ('.' singleExpression)?                                           # ThisExpression
    | Super                                                                  # SuperExpression
    | yieldStatement                                                         # YieldExpression // ECMAScript 6
    | Await singleExpression                                                 # AwaitExpression
    | typeArguments? identifierName singleExpression?                        # IdentifierExpression
    | typeArguments expressionSequence?                                      # GenericTypes
    | literal                                                                # LiteralExpression
    | arrayLiteral                                                           # ArrayLiteralExpression
    | objectLiteral                                                          # ObjectLiteralExpression
    | templateStringLiteral                                                  # TemplateStringExpression
    | singleExpression As asExpression                                       # CastAsExpression
    | singleExpression '=' htmlAssign                                        # HtmlAssignmentExpression
    ;

yieldStatement
    : Yield ({this.notLineTerminator()}? expressionSequence)? eos
    ;

asExpression
    : singleExpression
    ;

functionExpression
    : Function '*'? Identifier? '(' formalParameterList? ')' typeAnnotation? ('{' functionBody? '}' | eos)
    ;

arrowFunctionDeclaration
    : Async? arrowFunctionParameters typeAnnotation? '=>' arrowFunctionBody
    ;

arrowFunctionParameters
    : typeRef? '(' formalParameterList? ','? ')'
    |  identifierName
    ;

arrowFunctionBody
    : '{' statementList? '}'
    | singleExpression
    | htmlAssign
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




generatorFunctionDeclaration
    : Function '*' Identifier? '(' formalParameterList? ')' ('{' functionBody? '}' | eos)
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
    | Lodash
    | Dollar
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
//    | Case
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
    | Of
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

//htmlElements
//    : htmlElement+
//    ;

// case 1: const element = <h1>{title}</h1>;
// case 2:
// const element = (
//  <h1 className="greeting">
//    Hello, world!
//  </h1>
//);
htmlAssign
    :'('? '<' htmlTagCenter '>' ')'?
    ;

htmlElement
    : '<' htmlTagStartName htmlAttribute* '>' htmlContent '<''/' htmlTagClosingName '>'
    // for React
    | '<' '>' htmlContent '<''/' '>'
    | '<' htmlTagName htmlAttribute* htmlContent '/''>'
    | '<' htmlTagName htmlAttribute* '/''>'
    | '<' htmlTagName htmlAttribute* '>'
    ;

htmlTagCenter
    : htmlTagStartName htmlAttribute* '>' htmlContent '<''/' htmlTagClosingName
    | '>' htmlContent '<''/'
    | htmlTagName htmlAttribute* htmlContent '/'
    | htmlTagName htmlAttribute* '/'
    | htmlTagName htmlAttribute*
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
