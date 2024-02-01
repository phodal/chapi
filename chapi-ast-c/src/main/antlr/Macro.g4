grammar Macro;

compilationUnit
    : '#'*  EOF
    ;

//singleLineMacroDeclaration
//    : include (StringLiteral | Identifier | ('<' includeIdentifier '>' ))             #includeDeclaration
//    | ('ifdef' | 'ifndef' | 'if') Identifier statement* ('#' 'else' statement*)? '#' 'endif'
//                                                                                      #ifdefDeclaration
//    | 'define' Identifier defineMacro                                                 #defineDeclaration
////    | macroKeywords (assignmentExpression)*
////                ('#' macroKeywords)? identifierList?                                  #macroDefineDeclaration
//    | '#'? Identifier                                                                 #macroCastDeclaration
//    | macroKeywords macroFunctionExpression?                                          #macroStatementDeclaration
//    ;
//
//defineMacro
//    : expressionStatement?                                        #macroAssignDeclaration
//    | postixCall Identifier postixCall                            #macroAliasDeclaration
//    | structOrUnionSpecifier                                      #macroStructureDeclaration
//    ;
//
//macroFunctionExpression
//    : Identifier
//    | Identifier '(' ( parameterTypeList| (macroType (',' macroType)*) ) ')'
//    | assignmentExpression
//    ;
//macroType
//    : typeQualifier? (typeKeywords | Identifier | '==' | '!=' | comparator) (Identifier | typeKeywords)* pointer?
//    | expressionStatement
//    ;
//
//macroKeywords
//    :  'if' | 'undef' | 'else' | 'pragma' | 'endif' | 'ifdef' | 'ifndef' | 'elif' | 'define' | 'ifndef' | 'error'
//    ;
