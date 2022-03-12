/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers (original author) and Alexandre Vitorelli (contributor -> ported to CSharp)
 * Copyright (c) 2017 by Ivan Kochurkin (Positive Technologies):
    added ECMAScript 6 support, cleared and transformed to the universal grammar.
 * Copyright (c) 2018 by Juan Alvarez (contributor -> ported to Go)
 * Copyright (c) 2019 by Andrii Artiushok (contributor -> added TypeScript support)
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

// SupportSyntax

initializer
    : '=' singleExpression
    ;

bindingPattern
    : (arrayLiteral | objectLiteral)
    ;

// TypeScript SPart
// A.1 Types

typeParameters
    : '<' typeParameterList? '>'
    ;

typeParameterList
    : typeParameter (',' typeParameter)*
    ;

typeParameter
    : Identifier constraint?
    | Identifier '=' objectType
    | Identifier '=' identifierOrKeyWord typeIncludeGeneric?
    | Identifier '=' primaryType typeIncludeGeneric?
    | typeParameters
    ;

constraint
    : 'extends' type_
    ;

typeArguments
    : '<' typeArgumentList? '>'
    ;

typeArgumentList
    : typeArgument (',' typeArgument)*
    ;

typeArgument
    : type_
    ;

type_
    : (Keyof | Typeof)* unionOrIntersectionOrPrimaryType
    | unionOrIntersectionOrPrimaryType
    | functionType
    | constructorType
    | typeGeneric
    | '|'? propertyName ('|' (propertyName))*
    ;

unionOrIntersectionOrPrimaryType
    : unionOrIntersectionOrPrimaryType '|' unionOrIntersectionOrPrimaryType #Union
    | unionOrIntersectionOrPrimaryType '&' unionOrIntersectionOrPrimaryType #Intersection
    | primaryType                                                           #Primary
    ;

primaryType
    : '(' type_ ')'                                 #ParenthesizedPrimType
    | predefinedType                                #PredefinedPrimType
    | typeReference                                 #ReferencePrimType
    | objectType                                    #ObjectPrimType
    | primaryType {notLineTerminator()}? '[' ']'    #ArrayPrimType
    | '[' tupleElementTypes ']'                     #TuplePrimType
    | typeQuery                                     #QueryPrimType
    | This                                          #ThisPrimType
    | This Is primaryType                           #RedefinitionOfThisType
    | typeReference Is primaryType                  #RedefinitionOfType
    | primaryType {notLineTerminator()}? '[' singleExpression ']'    #SingleExprPrimType
    ;

predefinedType
    : Any
    | Number
    | Boolean
    | String
    | Symbol
    | Void
    | NullLiteral
    | StringLiteral
    | Undefined
    ;

typeReference
    : typeName nestedTypeGeneric?
    ;

nestedTypeGeneric
    : typeIncludeGeneric
    | typeGeneric
    ;

// I tried recursive include, but it's not working.
// typeGeneric
//    : '<' typeArgumentList typeGeneric?'>'
//    ;
//
// TODO: Fix recursive
//
typeGeneric
    : '<' typeArgumentList '>'
    ;

typeIncludeGeneric
    :'<' typeArgumentList '<' typeArgumentList ('>' bindingPattern '>' | '>>')
    ;

typeName
    : Identifier
    | namespaceName
    ;

objectType
    : '{' typeBody? '}'
    ;

typeBody
    : typeMemberList (SemiColon | ',')?
    ;

typeMemberList
    : typeMember ((SemiColon | ',') typeMember)*
    ;

typeMember
    : propertySignature
    | callSignature
    | constructSignature
    | indexSignature
    | methodSignature ('=>' type_)?
    | enumSignature
    | '[' typeReference In (Keyof | Typeof)* typeReference ']' typeAnnotation
    ;

arrayType
    : primaryType {notLineTerminator()}? '[' ']'
    ;

tupleType
    : '[' tupleElementTypes ']'
    ;

tupleElementTypes
    : type_ (',' type_)*
    ;

functionType
    : typeParameters? '(' parameterList? ')' '=>' type_
    ;

constructorType
    : 'new' typeParameters? '(' parameterList? ')' '=>' type_
    ;

typeQuery
    : Typeof* typeQueryExpression
    ;

typeQueryExpression
    : Identifier
    | (identifierName '!'? '.')+ identifierName
    ;

propertySignature
    : ReadOnly? propertyName '?'? typeAnnotation? ('=>' type_)?
    ;

typeAnnotation
    : ':' type_
    ;

callSignature
    : typeParameters? '(' parameterList? ','? ')' typeAnnotation?
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

identifierOrPattern
    : identifierName
    | bindingPattern
    ;

accessibilityModifier
    : Public
    | Private
    | Protected
    ;

constructSignature
    : 'new' typeParameters? '(' parameterList? ')' typeAnnotation?
    ;

indexSignature
    : '[' Identifier ':' (Number|String) ']' typeAnnotation
    ;

enumSignature
    : identifierOrKeyWord '?'? ':' '|'? type_ ('|' type_)*
    ;

methodSignature
    : propertyName '?'? callSignature
    ;

typeAliasDeclaration
    : 'type' Identifier typeParameters? '=' type_ SemiColon?
    ;

constructorDeclaration
    : accessibilityModifier? Constructor '(' formalParameterList? ')' ( ('{' functionBody '}') | SemiColon)?
    ;

// A.5 Interface

interfaceDeclaration
    : Export? Declare? Interface Identifier typeParameters? interfaceExtendsClause? objectType SemiColon?
    ;

interfaceExtendsClause
    : Extends classOrInterfaceTypeList
    ;

classOrInterfaceTypeList
    : typeReference (',' typeReference)*
    ;

// A.7 Interface

enumDeclaration
    : Const? Enum Identifier '{' enumBody? '}'
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

// A.8 Namespaces

namespaceDeclaration
    : Namespace namespaceName '{' statementList? '}'
    ;

namespaceName
    : Identifier ('.'+ Identifier)*
    ;

importAliasDeclaration
    : Identifier '=' namespaceName SemiColon
    | Identifier '=' Require '(' StringLiteral ')' SemiColon
    ;

importAll
    : StringLiteral
    ;

// Ext.2 Additions to 1.8: Decorators

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
    : decoratorMemberExpression arguments;

// ECMAPart
program
    : sourceElements? EOF
    ;

sourceElement
    : Export? statement
    ;

statement
    : block
    | importStatement
    | exportStatement
    | emptyStatement_
    | abstractDeclaration //ADDED
    | decoratorList
    | classDeclaration
    | interfaceDeclaration //ADDED
    | namespaceDeclaration //ADDED
    | ifStatement
    | iterationStatement
    | continueStatement
    | breakStatement
    | returnStatement
    | yieldStatement
    | withStatement
    | labelledStatement
    | switchStatement
    | throwStatement
    | tryStatement
    | debuggerStatement
    | functionDeclaration
    | arrowFunctionDeclaration
    | generatorFunctionDeclaration
    | variableStatement
    | typeAliasDeclaration //ADDED
    | enumDeclaration      //ADDED
    | expressionStatement
    | Export statement
    ;

block
    : '{' statementList? '}'
    ;

statementList
    : statement+
    ;

abstractDeclaration
    : Abstract (Identifier callSignature | variableStatement) eos
    ;

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

exportStatement
    : Export (exportFromBlock | declaration) eos    # ExportDeclaration
    | Export Default singleExpression eos           # ExportDefaultDeclaration
    ;

importFrom
    : From StringLiteral
    ;

exportFromBlock
    : importNamespace importFrom eos
    | moduleItems importFrom? eos
    ;

declaration
    : variableStatement
    | classDeclaration
    | functionDeclaration
    ;

variableStatement
    : bindingPattern typeAnnotation? initializer SemiColon?
    | accessibilityModifier? varModifier? ReadOnly? variableDeclarationList SemiColon?
    | Declare varModifier? variableDeclarationList SemiColon?
    ;

variableDeclarationList
    : variableDeclaration (',' variableDeclaration)*
    ;

variableDeclaration
    : ( identifierOrKeyWord | arrayLiteral | objectLiteral) typeAnnotation? singleExpression? ('=' typeParameters? singleExpression)? // ECMAScript 6: Array & Object Matching
    ;

emptyStatement_
    : SemiColon
    ;

expressionStatement
    : {this.notOpenBraceAndNotFunction()}? expressionSequence SemiColon?
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
    | For Await? '(' (singleExpression | variableDeclarationList) 'of' expressionSequence ')' statement         # ForOfStatement
    ;

varModifier
    : Var
    | Let
    | Const
    ;

continueStatement
    : Continue ({this.notLineTerminator()}? Identifier)? eos
    ;

breakStatement
    : Break ({this.notLineTerminator()}? Identifier)? eos
    ;

returnStatement
    : Return ({this.notLineTerminator()}? expressionSequence)? eos
    | Return '(' htmlElements ')' eos
//    | Return '{' statement '}' eos
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
    : htmlChardata? ((htmlElement | objectExpressionSequence) htmlChardata?)*
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


yieldStatement
    : Yield ({this.notLineTerminator()}? expressionSequence)? eos
    ;

withStatement
    : With '(' expressionSequence ')' statement
    ;

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

labelledStatement
    : Identifier ':' statement
    ;

throwStatement
    : Throw {this.notLineTerminator()}? expressionSequence eos
    ;

tryStatement
    : Try block (catchProduction finallyProduction? | finallyProduction)
    ;

catchProduction
    : Catch '(' Identifier ')' block
    ;

finallyProduction
    : Finally block
    ;

debuggerStatement
    : Debugger eos
    ;

functionDeclaration
    : Default? Async? Function_ Identifier callSignature ( ('{' functionBody '}') | SemiColon)
    ;

//Ovveride ECMA
classDeclaration
    : Declare? Abstract? Class Identifier typeParameters? classHeritage classTail
    ;

classHeritage
    : classExtendsClause? implementsClause?
    ;

classTail
    :  '{' classElement* '}'
    ;

classExtendsClause
    : Extends typeReference
    ;

implementsClause
    : Implements classOrInterfaceTypeList
    ;

// Classes modified
classElement
    : constructorDeclaration
    | decoratorList? propertyMemberDeclaration
    | indexMemberDeclaration
    | statement
    ;

propertyMemberDeclaration
    : propertyMemberBase propertyName  '!'? '?'? typeAnnotation? initializer? SemiColon             # PropertyDeclarationExpression
    | propertyMemberBase propertyName callSignature ( ('{' functionBody '}') | SemiColon)           # MethodDeclarationExpression
    | propertyMemberBase (getAccessor | setAccessor)                                                # GetterSetterDeclarationExpression
    | abstractDeclaration                                                                           # AbstractMemberDeclaration
    ;

propertyMemberBase
    : Async? accessibilityModifier? Static? ReadOnly?
    ;

indexMemberDeclaration
    : indexSignature SemiColon
    ;

generatorMethod
    : '*'?  Identifier '(' formalParameterList? ')' typeAnnotation? '{' functionBody '}'
    ;

generatorFunctionDeclaration
    : Function_ '*' Identifier? '(' formalParameterList? ')' '{' functionBody '}'
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
    : '[' singleExpression ']' '(' formalParameterList? ')' '{' functionBody '}'
    ;

formalParameterList
    : formalParameterArg (',' formalParameterArg)* (',' lastFormalParameterArg)?
    | lastFormalParameterArg
    | arrayLiteral                              // ECMAScript 6: Parameter Context Matching
    | objectLiteral (':' formalParameterList)?  // ECMAScript 6: Parameter Context Matching
    ;

formalParameterArg
    : decorator? accessibilityModifier? identifierOrKeyWord '?'? typeAnnotation? ('=' singleExpression)?      // ECMAScript 6: Initialization
    ;

lastFormalParameterArg                        // ECMAScript 6: Rest Parameter
    : Ellipsis Identifier
    ;

functionBody
    : sourceElements?
    ;

sourceElements
    : sourceElement+
    ;

arrayLiteral
    : ('[' elementList? ']')
    ;

elementList
    : arrayElement (','+ arrayElement)*
    ;

arrayElement                      // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | Identifier) ','?
    ;

objectLiteral
    : '{' (propertyAssignment (',' propertyAssignment)* ','?)? '}'
    ;

// MODIFIED
propertyAssignment
    : propertyName (':' |'=') singleExpression                # PropertyExpressionAssignment
    | '[' singleExpression ']' ':' singleExpression           # ComputedPropertyExpressionAssignment
    | getAccessor                                             # PropertyGetter
    | setAccessor                                             # PropertySetter
    | generatorMethod                                         # MethodProperty
    | identifierOrKeyWord                                     # PropertyShorthand
    | restParameter                                           # RestParameterInObject
    ;

getAccessor
    : getter '(' ')' typeAnnotation? '{' functionBody '}'
    ;

setAccessor
    : setter '(' ( Identifier | bindingPattern) typeAnnotation? ')' '{' functionBody '}'
    ;

propertyName
    : identifierName
    | StringLiteral
    | numericLiteral
    | keyword
    | predefinedType
    ;

arguments
    : '(' (argumentList ','?)? ')'
    ;

argumentList
    : argument (',' argument)*
    ;

argument                      // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | identifierOrKeyWord)
    ;

expressionSequence
    : singleExpression (',' singleExpression)*
    ;

functionExpressionDeclaration
    : Function_ Identifier? '(' formalParameterList? ')' typeAnnotation? '{' functionBody '}'
    ;

jsxArrowFunction
    : '(' formalParameterList (',' formalParameterList)* ')' '=>' arrowFunctionBody      # JsxArrowFunctionDecl
    ;

singleExpression
    : functionExpressionDeclaration                                          # FunctionExpression
    | arrowFunctionDeclaration                                               # ArrowFunctionExpression   // ECMAScript 6
    | jsxArrowFunction                                                       # JsxArrowFunctionExpression
    | Class Identifier? classTail                                            # ClassExpression
    | singleExpression '[' expressionSequence ']'                            # MemberIndexExpression
    | singleExpression '?'? '!'? '.' '#'? identifierName nestedTypeGeneric?  # MemberDotExpression
    // for: `onHotUpdateSuccess?.();`
    | singleExpression '?'? '!'? '.' '#'? '(' identifierName? ')'            # MemberDotExpression
    // samples: `error?.response?.data?.message ?? error.message;`
    | singleExpression '??' singleExpression                                 # NullCoalesceExpression
    | singleExpression '!'                                                   # PropCheckExpression
    // Split to try `new Date()` first, then `new Date`.
    | New singleExpression typeArguments? arguments                          # NewExpression
    | New singleExpression typeArguments?                                    # NewExpression
    | singleExpression arguments                                             # ArgumentsExpression
    | singleExpression {this.notLineTerminator()}? '++'                      # PostIncrementExpression
    | singleExpression {this.notLineTerminator()}? '--'                      # PostDecreaseExpression
    | Delete singleExpression                                                # DeleteExpression
    | Void singleExpression                                                  # VoidExpression
    | Typeof singleExpression                                                # TypeofExpression
    | Keyof singleExpression                                                 # KeyofExpression
    | '++' singleExpression                                                  # PreIncrementExpression
    | '--' singleExpression                                                  # PreDecreaseExpression
    | '+' singleExpression                                                   # UnaryPlusExpression
    | '-' singleExpression                                                   # UnaryMinusExpression
    | '~' singleExpression                                                   # BitNotExpression
    | '!' singleExpression                                                   # NotExpression
    | Await singleExpression                                                 # AwaitExpression
    | <assoc=right> singleExpression '**' singleExpression                   # PowerExpression
    | singleExpression ('*' | '/' | '%') singleExpression                    # MultiplicativeExpression
    | singleExpression ('+' | '-') singleExpression                          # AdditiveExpression
    | singleExpression ('<<' | '>>' | '>>>') singleExpression                # BitShiftExpression
    | singleExpression ('<' | '>' | '<=' | '>=') singleExpression            # RelationalExpression
    | singleExpression Instanceof singleExpression                           # InstanceofExpression
    | singleExpression In singleExpression                                   # InExpression
    | singleExpression ('==' | '!=' | '===' | '!==') singleExpression        # EqualityExpression
    | singleExpression '&' singleExpression                                  # BitAndExpression
    | singleExpression '^' singleExpression                                  # BitXOrExpression
    | singleExpression '|' singleExpression                                  # BitOrExpression
    | singleExpression '&&' singleExpression                                 # LogicalAndExpression
    | singleExpression '||' singleExpression                                 # LogicalOrExpression
    | singleExpression '?' singleExpression ':' singleExpression             # TernaryExpression
    | singleExpression '=' singleExpression                                  # AssignmentExpression
    | singleExpression assignmentOperator singleExpression                   # AssignmentOperatorExpression
    | singleExpression templateStringLiteral                                 # TemplateStringExpression  // ECMAScript 6
    | iteratorBlock                                                          # IteratorsExpression // ECMAScript 6
    | generatorBlock                                                         # GeneratorsExpression // ECMAScript 6
    | generatorFunctionDeclaration                                           # GeneratorsFunctionExpression // ECMAScript 6
    | yieldStatement                                                         # YieldExpression // ECMAScript 6
    | This                                                                   # ThisExpression
    | identifierName nestedTypeGeneric? singleExpression?                    # IdentifierExpression
    | Super                                                                  # SuperExpression
    | literal                                                                # LiteralExpression
    | arrayLiteral                                                           # ArrayLiteralExpression
    | objectLiteral                                                          # ObjectLiteralExpression
    | '(' expressionSequence ')'                                             # ParenthesizedExpression
    | typeArguments expressionSequence?                                      # GenericTypes
    | singleExpression As asExpression                                       # CastAsExpression
    | htmlElements                                                           # HtmlElementExpression
    | Require '(' literal ')' '.'? Default?                                  # LazyRequiredExpression
    ;

asExpression
    : predefinedType ('[' ']')?
    | singleExpression
    | objectType
    | Unknown
    ;

arrowFunctionDeclaration
    : Async? arrowFunctionParameters typeAnnotation? '=>' arrowFunctionBody
    ;

arrowFunctionParameters
    : identifierOrKeyWord
    | nestedTypeGeneric? '(' formalParameterList? ','? ')'
    ;

arrowFunctionBody
    : singleExpression
    | '{' functionBody '}'
    ;

assignmentOperator
    : '*='
    | '/='
    | '%='
    | '+='
    | '-='
    | '<<='
    | '>>='
    | '>>>='
    | '&='
    | '^='
    | '|='
    ;

literal
    : NullLiteral
    | BooleanLiteral
    | StringLiteral
    | templateStringLiteral
    | RegularExpressionLiteral
    | numericLiteral
    | Module  // only for es6 dev config
    ;

templateStringLiteral
    : BackTick templateStringAtom* BackTick
    ;

templateStringAtom
    : TemplateStringAtom
    | TemplateStringStartExpression singleExpression TemplateCloseBrace
    ;

numericLiteral
    : DecimalLiteral
    | HexIntegerLiteral
    | OctalIntegerLiteral
    | OctalIntegerLiteral2
    | BinaryIntegerLiteral
    ;

identifierName
    : Identifier
    | reservedWord
    | Lodash Lodash?
    | Dollar Dollar?
    ;

identifierOrKeyWord
    : Identifier
    | TypeAlias
    | Require
    | Module
    | Default
    | Lodash Lodash?
    | Any
    ;

reservedWord
    : keyword
    | NullLiteral
    | BooleanLiteral
    | Module
    ;

keyword
    : Break
    | Do
    | Instanceof
    | Typeof
    | Keyof
    | Case
    | Else
    | New
    | Var
    | Catch
    | Finally
    | Return
    | Void
    | Continue
    | For
    | Switch
    | While
    | Debugger
    | Function_
    | This
    | Is
    | With
    | Default
    | If
    | Throw
    | Delete
    | In
    | Try
    | ReadOnly
    | Async
    | From
    | Class
    | Enum
    | Extends
    | Super
    | Const
    | Export
    | Import
    | Implements
    | Let
    | Private
    | Public
    | Interface
    | Package
    | Protected
    | Static
    | Yield
    | Get
    | Set
    | Require
    | TypeAlias
    | String
    | Undefined
    | Any
    | Namespace
    | Number
    | Boolean
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
