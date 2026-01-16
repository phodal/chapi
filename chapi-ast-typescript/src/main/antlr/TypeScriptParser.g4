/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers (original author) and Alexandre Vitorelli (contributor -> ported to CSharp)
 * Copyright (c) 2017 by Ivan Kochurkin (Positive Technologies):
    added ECMAScript 6 support, cleared and transformed to the universal grammar.
 * Copyright (c) 2018 by Juan Alvarez (contributor -> ported to Go)
 * Copyright (c) 2019 by Andrii Artiushok (contributor -> added TypeScript support)
 * Copyright (c) 2024 by Andrew Leppard (www.wegrok.review)
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

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar TypeScriptParser;

options {
    tokenVocab = TypeScriptLexer;
    superClass = TypeScriptParserBase;
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
    : typeParameter (',' typeParameter)* ','?
    ;

typeParameter
    // Compatibility with old grammar: support both constraint and default type
    : identifierName constraint? ('=' type_)? 
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

// Union and intersection types can have a leading '|' or '&'
// See https://github.com/microsoft/TypeScript/pull/12386
type_
    : ('|' | '&')? unionOrIntersectionOrPrimaryType
    | functionType
    | constructorType
    | typeGeneric
    ;

unionOrIntersectionOrPrimaryType
    : unionOrIntersectionOrPrimaryType '|' unionOrIntersectionOrPrimaryType # Union
    | unionOrIntersectionOrPrimaryType '&' unionOrIntersectionOrPrimaryType # Intersection
    | primaryType                                                           # Primary
    ;

primaryType
    : '(' type_ ')'                              # ParenthesizedPrimType
    | predefinedType                             # PredefinedPrimType
    | typeReference                              # ReferencePrimType
    | objectType                                 # ObjectPrimType
    | primaryType {this.notLineTerminator()}? '[' primaryType? ']' # ArrayPrimType
    | '[' tupleElementTypes ']'                  # TuplePrimType
    | typeQuery                                  # QueryPrimType
    | This                                       # ThisPrimType
    | typeReference Is primaryType               # RedefinitionOfType
    | KeyOf primaryType                          # KeyOfType
    ;

predefinedType
    : Any
    | NullLiteral
    | Number
    | DecimalLiteral
    | Boolean
    | BooleanLiteral
    | String
    | StringLiteral
    | Unique? Symbol
    | Never
    | Undefined
    | Object
    | Void
    ;

typeReference
    : typeName typeGeneric?
    ;

typeGeneric
    : '<' typeArgumentList typeGeneric?'>'
    ;

typeName
    : identifier
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
    : propertySignatur
    | callSignature
    | constructSignature
    | indexSignature
    | methodSignature ('=>' type_)?
    ;

arrayType
    : primaryType {this.notLineTerminator()}? '[' ']'
    ;

tupleType
    : '[' tupleElementTypes ']'
    ;

// Tuples can have a trailing comma. See https://github.com/Microsoft/TypeScript/issues/28893
tupleElementTypes
    : type_ (',' type_)* ','?
    ;

functionType
    : typeParameters? '(' parameterList? ')' '=>' type_
    ;

constructorType
    : 'new' typeParameters? '(' parameterList? ')' '=>' type_
    ;

typeQuery
    : 'typeof' typeQueryExpression
    ;

typeQueryExpression
    : identifier
    | (identifierName '.')+ identifierName
    ;

propertySignatur
    : ReadOnly? propertyName '?'? typeAnnotation? ('=>' type_)?
    ;

typeAnnotation
    : ':' type_
    ;

callSignature
    : typeParameters? '(' parameterList? ','? ')' (':' typeRef)?
    ;

// Function parameter list can have a trailing comma.
// See https://github.com/Microsoft/TypeScript/issues/16152
parameterList
    : restParameter
    | parameter (',' parameter)* (',' restParameter)? ','?
    ;

requiredParameterList
    : requiredParameter (',' requiredParameter)*
    ;

parameter
    : requiredParameter
    | optionalParameter
    ;

optionalParameter
    : decoratorList? (
        accessibilityModifier? identifierOrPattern (
            '?' typeAnnotation?
            | typeAnnotation? initializer
        )
    )
    ;

restParameter
    : '...' singleExpression typeAnnotation?
    ;

requiredParameter
    : decoratorList? accessibilityModifier? identifierOrPattern typeAnnotation?
    ;

accessibilityModifier
    : Public
    | Private
    | Protected
    ;

identifierOrPattern
    : identifierName
    | bindingPattern
    ;

constructSignature
    : 'new' typeParameters? '(' parameterList? ')' typeAnnotation?
    ;

indexSignature
    : '[' identifier ':' (Number | String) ']' typeAnnotation
    ;

methodSignature
    : propertyName '?'? callSignature
    ;

typeAliasDeclaration
    : Export? 'type' identifier typeParameters? '=' type_ eos
    ;

constructorDeclaration
    : accessibilityModifier? Constructor '(' formalParameterList? ')' (
        ('{' functionBody '}')
        | SemiColon
    )?
    ;

// A.5 Interface

interfaceDeclaration
    : Export? Declare? Interface identifierName typeParameters? interfaceExtendsClause? interfaceBody SemiColon?
    ;

interfaceExtendsClause
    : Extends classOrInterfaceTypeList
    ;

classOrInterfaceTypeList
    : parameterizedTypeRef (',' parameterizedTypeRef)*
    ;

// A.7 Interface

enumDeclaration
    : Const? Enum identifier '{' enumBody? '}'
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
    : Declare? Namespace namespaceName '{' statementList? '}'
    ;

namespaceName
    : identifier ('.'+ identifier)*
    ;

importAliasDeclaration
    : identifierName '=' namespaceName SemiColon
    | identifierName '=' Require '(' StringLiteral ')' SemiColon
    ;

// Ext.2 Additions to 1.8: Decorators

decoratorList
    : decorator+
    ;

decorator
    : '@' (decoratorMemberExpression | decoratorCallExpression)
    ;

decoratorMemberExpression
    : identifier
    | decoratorMemberExpression '.' identifierName
    | '(' singleExpression ')'
    ;

decoratorCallExpression
    : decoratorMemberExpression arguments
    ;

// ECMAPart
program
    : sourceElements? EOF
    ;

sourceElement
    : statement
    ;

statement
    : block
    | variableStatement
    | importStatement
    | exportStatement
    | emptyStatement_
    | abstractDeclaration //ADDED
    | classDeclaration
    | functionDeclaration
    | expressionStatement
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
    | arrowFunctionDeclaration
    | generatorFunctionDeclaration
    | typeAliasDeclaration //ADDED
    | enumDeclaration      //ADDED
    ;

block
    : '{' statementList? '}'
    ;

statementList
    : statement+
    ;

abstractDeclaration
    // Compatibility with old grammar: abstract method or abstract property
    : Abstract (identifierName '?'? typeAnnotation? callSignature? | variableStatement) eos
    ;

importStatement
    : Import (fromBlock | importFromBlock | importAliasDeclaration | importAll)
    ;

importFromBlock
    : importDefault? (importNamespace | importModuleItems) importFrom eos
    | StringLiteral eos
    ;

importModuleItems
    : '{' (importAliasName ',')* (importAliasName ','?)? '}'
    ;

importAliasName
    : moduleExportName (As importedBinding)?
    ;

moduleExportName
    : identifierName
    | StringLiteral
    ;

// yield and await are permitted as BindingIdentifier in the grammar
importedBinding
    : Identifier
    | Yield
    | Await
    ;

importDefault
    : aliasName ','
    ;

importNamespace
    : ('*' | identifierName) (As identifierName)?
    ;

importFrom
    : From StringLiteral
    ;

aliasName
    : identifierName (As identifierName)?
    ;

exportStatement
    : Export Declare? Default? declarationStatement       # ExportElementDirectly
    | Export Default singleExpression eos                 # ExportDefaultDeclaration
    | Export Default? (exportFromBlock | declaration) eos # ExportDeclaration
    ;

exportFromBlock
    : importNamespace importFrom eos
    | exportModuleItems importFrom? eos
    ;

exportModuleItems
    : '{' (exportAliasName ',')* (exportAliasName ','?)? '}'
    ;

exportAliasName
    : moduleExportName (As moduleExportName)?
    ;

declaration
    : variableStatement
    | classDeclaration
    | functionDeclaration
    ;

variableStatement
    : bindingPattern typeAnnotation? initializer SemiColon?
    // Compatibility: require varModifier to avoid ambiguities where `let/const/var`
    // might be parsed as an identifier.
    | accessibilityModifier? varModifier ReadOnly? variableDeclarationList SemiColon?
    | Declare varModifier? variableDeclarationList SemiColon?
    ;

variableDeclarationList
    : variableDeclaration (',' variableDeclaration)*
    ;

variableDeclaration
    : (identifierOrKeyWord | arrayLiteral | objectLiteral) typeAnnotation? singleExpression? (
        '=' typeParameters? singleExpression
    )? // ECMAScript 6: Array & Object Matching
    ;

emptyStatement_
    : SemiColon
    ;

expressionStatement
    : {this.notOpenBraceAndNotStatementKeyword()}? expressionSequence SemiColon?
    ;

ifStatement
    : If '(' expressionSequence ')' statement (Else statement)?
    ;

iterationStatement
    : Do statement While '(' expressionSequence ')' eos                                                                     # DoStatement
    | While '(' expressionSequence ')' statement                                                                            # WhileStatement
    | For '(' expressionSequence? SemiColon expressionSequence? SemiColon expressionSequence? ')' statement                 # ForStatement
    | For '(' varModifier variableDeclarationList SemiColon expressionSequence? SemiColon expressionSequence? ')' statement # ForVarStatement
    | For '(' singleExpression In expressionSequence ')' statement                                                          # ForInStatement
    | For '(' varModifier variableDeclaration In expressionSequence ')' statement                                           # ForVarInStatement
    | For Await? '(' singleExpression identifier {this.p("of")}? expressionSequence (As type_)? ')' statement                            # ForOfStatement
    | For Await? '(' varModifier variableDeclaration identifier {this.p("of")}? expressionSequence (As type_)? ')' statement             # ForVarOfStatement
    ;

varModifier
    : Var
    | Let
    | Const
    ;

continueStatement
    : Continue ({this.notLineTerminator()}? identifier)? eos
    ;

breakStatement
    : Break ({this.notLineTerminator()}? identifier)? eos
    ;

returnStatement
    : Return ({this.notLineTerminator()}? expressionSequence)? eos
    ;

yieldStatement
    : (Yield | YieldStar) ({this.notLineTerminator()}? expressionSequence)? eos
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
    : identifier ':' statement
    ;

throwStatement
    : Throw {this.notLineTerminator()}? expressionSequence eos
    ;

tryStatement
    : Try block (catchProduction finallyProduction? | finallyProduction)
    ;

catchProduction
    : Catch ('(' identifier typeAnnotation? ')')? block
    ;

finallyProduction
    : Finally block
    ;

debuggerStatement
    : Debugger eos
    ;

functionDeclaration
    : Async? Function_ '*'? identifierName callSignature (('{' functionBody '}') | SemiColon)
    ;

//Ovveride ECMA
classDeclaration
    : decoratorList? (Export Default?)? Abstract? Class identifierName typeParameters? classHeritage classBody SemiColon?
    ;

classHeritage
    : classExtendsClause? classImplementsClause?
    ;

classBody
    : '{' classMemberList? SemiColon? '}'
    ;

classMemberList
    : classMember (({this.lineTerminatorAhead()}? | SemiColon) classMember)*
    ;

classMember
    : constructorDeclaration
    | decoratorList? propertyMemberDeclaration
    | indexMemberDeclaration
    | statement
    ;

classExtendsClause
    : Extends parameterizedTypeRef
    ;

classImplementsClause
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
    : propertyMemberBase propertyName ('?' | '!')? typeAnnotation? initializer? SemiColon        # PropertyDeclarationExpression
    | propertyMemberBase propertyName callSignature (('{' functionBody '}') | SemiColon) # MethodDeclarationExpression
    | propertyMemberBase (getAccessor | setAccessor)                                     # GetterSetterDeclarationExpression
    | abstractDeclaration                                                                # AbstractMemberDeclaration
    ;

propertyMemberBase
    : accessibilityModifier? Async? Static? ReadOnly?
    ;

indexMemberDeclaration
    : indexSignature SemiColon
    ;

generatorMethod
    : (Async {this.notLineTerminator()}?)? '*'? propertyName '(' formalParameterList? ')' '{' functionBody '}'
    ;

generatorFunctionDeclaration
    : Async? Function_ '*' identifier? '(' formalParameterList? ')' '{' functionBody '}'
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

classElementName
    : propertyName
    | privateIdentifier
    ;

privateIdentifier
    : '#' identifierName
    ;

formalParameterList
    : formalParameterArg (',' formalParameterArg)* (',' lastFormalParameterArg)? ','?
    | lastFormalParameterArg
    | arrayLiteral                             // ECMAScript 6: Parameter Context Matching
    | objectLiteral (':' formalParameterList)? // ECMAScript 6: Parameter Context Matching
    ;

formalParameterArg
    : decorator? accessibilityModifier? ReadOnly? identifierName '?'? typeAnnotation? ('=' singleExpression)?  // ECMAScript 6: Initialization
    | lastFormalParameterArg
    | arrayLiteral (':' formalParameterList)?                                  // ECMAScript 6: Parameter Context Matching
    | objectLiteral (':' formalParameterList)?                                 // ECMAScript 6: Parameter Context Matching
    | objectLiteral (',' objectLiteral)*                                       // ECMAScript 6: Parameter Context Matching
    ;

lastFormalParameterArg // ECMAScript 6: Rest Parameter
    : Ellipsis identifier typeAnnotation?
    ;

functionBody
    : sourceElements?
    ;

sourceElements
    : sourceElement+
    ;

arrayLiteral
    : ('[' elementList ']')
    ;

// JavaScript supports arrasys like [,,1,2,,].
elementList
    : ','* arrayElement? (','+ arrayElement) * ','* // Yes, everything is optional
    ;

arrayElement // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | identifier) ','?
    ;

objectLiteral
    : '{' (propertyAssignment (',' propertyAssignment)* ','?)? '}'
    ;

// MODIFIED
propertyAssignment
    : propertyName (':' | '=') singleExpression     # PropertyExpressionAssignment
    | '[' singleExpression ']' ':' singleExpression # ComputedPropertyExpressionAssignment
    | getAccessor                                   # PropertyGetter
    | setAccessor                                   # PropertySetter
    | generatorMethod                               # MethodProperty
    | identifierOrKeyWord                           # PropertyShorthand
    | Ellipsis? singleExpression                    # SpreadOperator
    | restParameter                                 # RestParameterInObject
    ;

getAccessor
    : getter '(' ')' typeAnnotation? '{' functionBody '}'
    ;

setAccessor
    : setter '(' formalParameterList? ')' '{' functionBody '}'
    ;

propertyName
    : identifierName
    | StringLiteral
    | numericLiteral
    | '[' singleExpression ']'
    ;

arguments
    : '(' (argumentList ','?)? ')'
    ;

argumentList
    : argument (',' argument)*
    ;

argument // ECMAScript 6: Spread Operator
    : Ellipsis? (singleExpression | identifier)
    ;

expressionSequence
    : singleExpression (',' singleExpression)*
    ;

singleExpression
    : anonymousFunction                                           # FunctionExpression
    | arrowFunctionDeclaration                                    # ArrowFunctionExpressionL
    | Class identifier? typeParameters? classHeritage classBody   # ClassExpression
    | htmlElement                                                 # HtmlElementExpression
    | singleExpression '?.'? '[' expressionSequence ']'           # MemberIndexExpression
    | singleExpression '?.' singleExpression                      # OptionalChainExpression
    | singleExpression '?.' arguments                              # OptionalCallExpression
    | singleExpression '!'? '.' '#'? identifierName typeGeneric?  # MemberDotExpression
    | singleExpression '?'? '.' '#'? identifierName typeGeneric?  # MemberDotExpression
    // Split to try `new Date()` first, then `new Date`.
    | New singleExpression typeArguments? arguments                   # NewExpression
    | New singleExpression typeArguments?                             # NewExpression
    | identifierName typeArguments arguments                          # GenericCallExpression
    | singleExpression arguments                                      # ArgumentsExpression
    | singleExpression {this.notLineTerminator()}? '++'               # PostIncrementExpression
    | singleExpression {this.notLineTerminator()}? '--'               # PostDecreaseExpression
    | Delete singleExpression                                         # DeleteExpression
    | Void singleExpression                                           # VoidExpression
    | Typeof singleExpression                                         # TypeofExpression
    | '++' singleExpression                                           # PreIncrementExpression
    | '--' singleExpression                                           # PreDecreaseExpression
    | '+' singleExpression                                            # UnaryPlusExpression
    | '-' singleExpression                                            # UnaryMinusExpression
    | '~' singleExpression                                            # BitNotExpression
    | '!' singleExpression                                            # NotExpression
    | Await singleExpression                                          # AwaitExpression
    | <assoc = right> singleExpression '**' singleExpression          # PowerExpression
    | singleExpression ('*' | '/' | '%') singleExpression             # MultiplicativeExpression
    | singleExpression ('+' | '-') singleExpression                   # AdditiveExpression
    | singleExpression '??' singleExpression                          # CoalesceExpression
    | singleExpression ('<<' | '>' '>' | '>' '>' '>') singleExpression # BitShiftExpression
    | singleExpression ('<' | '>' | '<=' | '>=') singleExpression     # RelationalExpression
    | singleExpression Instanceof singleExpression                    # InstanceofExpression
    | singleExpression In singleExpression                            # InExpression
    | singleExpression ('==' | '!=' | '===' | '!==') singleExpression # EqualityExpression
    | singleExpression '&' singleExpression                           # BitAndExpression
    | singleExpression '^' singleExpression                           # BitXOrExpression
    | singleExpression '|' singleExpression                           # BitOrExpression
    | singleExpression '&&' singleExpression                          # LogicalAndExpression
    | singleExpression '||' singleExpression                          # LogicalOrExpression
    | singleExpression '?' singleExpression ':' singleExpression      # TernaryExpression
    | singleExpression '=' singleExpression                           # AssignmentExpression
    | singleExpression assignmentOperator singleExpression            # AssignmentOperatorExpression
    | singleExpression templateStringLiteral                          # TemplateStringExpression     // ECMAScript 6
    | iteratorBlock                                                   # IteratorsExpression          // ECMAScript 6
    | generatorBlock                                                  # GeneratorsExpression         // ECMAScript 6
    | generatorFunctionDeclaration                                    # GeneratorsFunctionExpression // ECMAScript 6
    | yieldStatement                                                  # YieldExpression              // ECMAScript 6
    | This                                                            # ThisExpression
    | identifierName singleExpression?                                # IdentifierExpression
    | Super                                                           # SuperExpression
    | literal                                                         # LiteralExpression
    | arrayLiteral                                                    # ArrayLiteralExpression
    | objectLiteral                                                   # ObjectLiteralExpression
    | '(' expressionSequence ')'                                      # ParenthesizedExpression
    | typeArguments expressionSequence?                               # GenericTypes
    | singleExpression As asExpression                                # CastAsExpression
// TypeScript v2.0
    | singleExpression '!'                                            # NonNullAssertionExpression
    ;

asExpression
    : predefinedType ('[' ']')?
    | singleExpression
    ;

assignable
    : identifier
    | keyword
    | arrayLiteral
    | objectLiteral
    ;

anonymousFunction
    : functionDeclaration
    | Async? Function_ '*'? '(' formalParameterList? ')' typeAnnotation? '{' functionBody '}'
    | arrowFunctionDeclaration
    ;

arrowFunctionDeclaration
    : Async? arrowFunctionParameters typeAnnotation? '=>' arrowFunctionBody
    ;

arrowFunctionParameters
    : propertyName
    | '(' formalParameterList? ')'
    ;

arrowFunctionBody
    : '{' functionBody '}'
    | singleExpression
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
    | '**='
    | '??='
    ;

literal
    : NullLiteral
    | BooleanLiteral
    | StringLiteral
    | templateStringLiteral
    | RegularExpressionLiteral
    | numericLiteral
    | bigintLiteral
    ;

templateStringLiteral
    : BackTick templateStringAtom* BackTick
    ;

templateStringAtom
    : TemplateStringAtom
    | TemplateStringStartExpression singleExpression TemplateCloseBrace
    | TemplateStringEscapeAtom
    ;

numericLiteral
    : DecimalLiteral
    | HexIntegerLiteral
    | OctalIntegerLiteral
    | OctalIntegerLiteral2
    | BinaryIntegerLiteral
    ;

bigintLiteral
    : BigDecimalIntegerLiteral
    | BigHexIntegerLiteral
    | BigOctalIntegerLiteral
    | BigBinaryIntegerLiteral
    ;

getter
    : {this.n("get")}? identifier classElementName
    ;

setter
    : {this.n("set")}? identifier classElementName
    ;

identifierName
    : identifier
    | reservedWord
    ;

identifier
    : Identifier
    | Dollar
    | Lodash
    | Async
    | As
    | From
    | Yield
    | Of
    | Any
    | Any
    | Number
    | Boolean
    | String
    | Unique
    | Symbol
    | Never
    | Undefined
    | Object
    | KeyOf
    | TypeAlias
    | Constructor
    | Namespace
    | Abstract
    ;

identifierOrKeyWord
    : identifier
    | TypeAlias
    | Require
    ;

reservedWord
    : keyword
    | NullLiteral
    | BooleanLiteral
    ;

keyword
    : Break
    | Do
    | Instanceof
    | Typeof
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
    | With
    | Default
    | If
    | Throw
    | Delete
    | In
    | Try
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
    | Async
    | Await
    | ReadOnly
    | From
    | As
    | Require
    | TypeAlias
    | String
    | Boolean
    | Number
    | Module
    ;

eos
    : SemiColon
    | EOF
    | {this.lineTerminatorAhead()}?
    | {this.closeBrace()}?
    ;

// ============================================================================
// COMPATIBILITY LAYER - Aliases for backward compatibility with old grammar
// ============================================================================

// Type reference compatibility (old: typeRef -> new: type_)
typeRef
    : type_
    ;

// Parameterized type reference compatibility (old grammar style)
parameterizedTypeRef
    : typeName ('?' | '!')? typeArguments?
    ;

// Import compatibility: fromBlock (old grammar style)
fromBlock
    : importDefault? (Dollar | Lodash | importNamespace | moduleItems) From StringLiteral eos
    | StringLiteral eos
    ;

// Import compatibility: moduleItems (old grammar style)
moduleItems
    : '{' (aliasName ',')* (aliasName ','?)? '}'
    ;

// Import compatibility: importAll (old grammar style)
importAll
    : StringLiteral
    ;

// Interface body compatibility
interfaceBody
    : '{' interfaceMemberList? (SemiColon | ',')? '}'
    ;

// Interface member list compatibility
interfaceMemberList
    : interfaceMember (({this.lineTerminatorAhead()}? | SemiColon | ',') interfaceMember)*
    ;

// Interface member compatibility
interfaceMember
    : constructSignature
    | callSignature
    | indexSignature
    | getAccessor
    | setAccessor
    | methodSignature
    | propertySignature
    ;

// Property signature compatibility
propertySignature
    : ReadOnly? propertyName '?'? typeAnnotation?
    ;

// Declare statement compatibility
declareStatement
    : Declare? declarationStatement
    ;

// Declaration statement compatibility
declarationStatement
    : namespaceDeclaration
    | interfaceDeclaration
    | typeAliasDeclaration
    | functionDeclaration
    | classDeclaration
    | enumDeclaration
    | variableStatement
    ;

// ============================================================================
// JSX/TSX SUPPORT - HTML-like elements in TypeScript/JavaScript
// ============================================================================

// JSX element - supports full elements, self-closing, and fragments
// Note: We do NOT support unclosed tags like `<Tag>` alone, as it conflicts with 
// TypeScript generic syntax like `<T>`. JSX elements must have matching close tags.
htmlElement
    : '<' htmlTagStartName htmlAttribute* '>' htmlContent '<' '/' htmlTagClosingName '>'    # HtmlElementFull
    | '<' '>' htmlContent '<' '/' '>'                                                       # HtmlElementFragment
    | '<' htmlTagName htmlAttribute* '/' '>'                                                # HtmlElementSelfClosing
    ;

// JSX content between tags
htmlContent
    : htmlChardata? ((htmlElement | objectExpressionSequence) htmlChardata?)*
    ;

// Expression inside JSX braces: {expression}
objectExpressionSequence
    : '{' expressionSequence? '}'
    | '{' '/' '*' .*? '*' '/' '}'  // JSX comment: {/* comment */}
    ;

// Tag name tracking for matching open/close tags
htmlTagStartName
    : htmlTagName {this.pushHtmlTagName($htmlTagName.text);}
    ;

htmlTagClosingName
    : htmlTagName {this.popHtmlTagName($htmlTagName.text)}?
    ;

// Tag name: supports simple names and dotted names (e.g., Form.Item)
htmlTagName
    : identifierOrKeyWord ('.' identifierOrKeyWord)*
    ;

// JSX attributes
htmlAttribute
    : htmlAttributeName '=' htmlAttributeValue
    | htmlAttributeName
    | '{' Ellipsis? singleExpression '}'    // Spread attribute: {...props}
    ;

htmlAttributeName
    : identifierOrKeyWord ('-' identifierOrKeyWord)*
    ;

htmlAttributeValue
    : StringLiteral
    | '{' singleExpression? '}'
    ;

// Character data between JSX tags (anything except < and {)
htmlChardata
    : ~('<' | '{' | '}')+
    ;
