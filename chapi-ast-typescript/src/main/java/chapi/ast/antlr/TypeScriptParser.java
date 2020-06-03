// Generated from TypeScriptParser.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TypeScriptParser extends TypeScriptBaseParser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MultiLineComment=1, SingleLineComment=2, RegularExpressionLiteral=3, OpenBracket=4, 
		CloseBracket=5, OpenParen=6, CloseParen=7, OpenBrace=8, CloseBrace=9, 
		SemiColon=10, Comma=11, Assign=12, QuestionMark=13, Colon=14, Ellipsis=15, 
		Dot=16, PlusPlus=17, MinusMinus=18, Plus=19, Minus=20, BitNot=21, Not=22, 
		Multiply=23, Divide=24, Modulus=25, RightShiftArithmetic=26, LeftShiftArithmetic=27, 
		RightShiftLogical=28, LessThan=29, MoreThan=30, LessThanEquals=31, GreaterThanEquals=32, 
		Equals_=33, NotEquals=34, IdentityEquals=35, IdentityNotEquals=36, BitAnd=37, 
		BitXOr=38, BitOr=39, And=40, Or=41, MultiplyAssign=42, DivideAssign=43, 
		ModulusAssign=44, PlusAssign=45, MinusAssign=46, LeftShiftArithmeticAssign=47, 
		RightShiftArithmeticAssign=48, RightShiftLogicalAssign=49, BitAndAssign=50, 
		BitXorAssign=51, BitOrAssign=52, ARROW=53, Lodash=54, Dollar=55, NullLiteral=56, 
		BooleanLiteral=57, DecimalLiteral=58, HexIntegerLiteral=59, OctalIntegerLiteral=60, 
		OctalIntegerLiteral2=61, BinaryIntegerLiteral=62, Break=63, Do=64, Instanceof=65, 
		Typeof=66, Case=67, Else=68, New=69, Var=70, Catch=71, Finally=72, Return=73, 
		Void=74, Continue=75, For=76, Switch=77, While=78, Debugger=79, Function=80, 
		This=81, With=82, Default=83, If=84, Throw=85, Delete=86, In=87, Try=88, 
		As=89, From=90, ReadOnly=91, Async=92, Class=93, Enum=94, Extends=95, 
		Super=96, Const=97, Export=98, Import=99, Implements=100, Let=101, Private=102, 
		Public=103, Interface=104, Package=105, Protected=106, Static=107, Yield=108, 
		Any=109, Number=110, Boolean=111, String=112, Symbol=113, TypeAlias=114, 
		Get=115, Set=116, Constructor=117, Namespace=118, Require=119, Module=120, 
		Declare=121, Abstract=122, Is=123, At=124, Identifier=125, StringLiteral=126, 
		TemplateStringLiteral=127, WhiteSpaces=128, LineTerminator=129, HtmlComment=130, 
		CDataComment=131, UnexpectedCharacter=132;
	public static final int
		RULE_initializer = 0, RULE_bindingPattern = 1, RULE_typeParameters = 2, 
		RULE_typeParameterList = 3, RULE_typeParameter = 4, RULE_constraint = 5, 
		RULE_typeArguments = 6, RULE_typeArgumentList = 7, RULE_typeArgument = 8, 
		RULE_type_ = 9, RULE_unionOrIntersectionOrPrimaryType = 10, RULE_primaryType = 11, 
		RULE_predefinedType = 12, RULE_typeReference = 13, RULE_nestedTypeGeneric = 14, 
		RULE_typeGeneric = 15, RULE_typeIncludeGeneric = 16, RULE_typeName = 17, 
		RULE_objectType = 18, RULE_typeBody = 19, RULE_typeMemberList = 20, RULE_typeMember = 21, 
		RULE_arrayType = 22, RULE_tupleType = 23, RULE_tupleElementTypes = 24, 
		RULE_functionType = 25, RULE_constructorType = 26, RULE_typeQuery = 27, 
		RULE_typeQueryExpression = 28, RULE_propertySignatur = 29, RULE_typeAnnotation = 30, 
		RULE_callSignature = 31, RULE_accessibilityModifier = 32, RULE_parameterList = 33, 
		RULE_requiredParameterList = 34, RULE_requiredParameter = 35, RULE_optionalParameterList = 36, 
		RULE_optionalParameter = 37, RULE_restParameter = 38, RULE_identifierOrPattern = 39, 
		RULE_constructSignature = 40, RULE_indexSignature = 41, RULE_methodSignature = 42, 
		RULE_typeAliasDeclaration = 43, RULE_constructorDeclaration = 44, RULE_interfaceDeclaration = 45, 
		RULE_interfaceExtendsClause = 46, RULE_classOrInterfaceTypeList = 47, 
		RULE_enumDeclaration = 48, RULE_enumBody = 49, RULE_enumMemberList = 50, 
		RULE_enumMember = 51, RULE_namespaceDeclaration = 52, RULE_namespaceName = 53, 
		RULE_importAliasDeclaration = 54, RULE_importAll = 55, RULE_decoratorList = 56, 
		RULE_decorator = 57, RULE_decoratorMemberExpression = 58, RULE_decoratorCallExpression = 59, 
		RULE_program = 60, RULE_sourceElement = 61, RULE_statement = 62, RULE_block = 63, 
		RULE_statementList = 64, RULE_abstractDeclaration = 65, RULE_importStatement = 66, 
		RULE_fromBlock = 67, RULE_multipleImportStatement = 68, RULE_exportStatement = 69, 
		RULE_variableStatement = 70, RULE_variableDeclarationList = 71, RULE_variableDeclaration = 72, 
		RULE_emptyStatement = 73, RULE_expressionStatement = 74, RULE_ifStatement = 75, 
		RULE_iterationStatement = 76, RULE_varModifier = 77, RULE_continueStatement = 78, 
		RULE_breakStatement = 79, RULE_returnStatement = 80, RULE_yieldStatement = 81, 
		RULE_withStatement = 82, RULE_switchStatement = 83, RULE_caseBlock = 84, 
		RULE_caseClauses = 85, RULE_caseClause = 86, RULE_defaultClause = 87, 
		RULE_labelledStatement = 88, RULE_throwStatement = 89, RULE_tryStatement = 90, 
		RULE_catchProduction = 91, RULE_finallyProduction = 92, RULE_debuggerStatement = 93, 
		RULE_functionDeclaration = 94, RULE_classDeclaration = 95, RULE_classHeritage = 96, 
		RULE_classTail = 97, RULE_classExtendsClause = 98, RULE_implementsClause = 99, 
		RULE_classElement = 100, RULE_propertyMemberDeclaration = 101, RULE_propertyMemberBase = 102, 
		RULE_indexMemberDeclaration = 103, RULE_generatorMethod = 104, RULE_generatorFunctionDeclaration = 105, 
		RULE_generatorBlock = 106, RULE_generatorDefinition = 107, RULE_iteratorBlock = 108, 
		RULE_iteratorDefinition = 109, RULE_formalParameterList = 110, RULE_formalParameterArg = 111, 
		RULE_lastFormalParameterArg = 112, RULE_functionBody = 113, RULE_sourceElements = 114, 
		RULE_arrayLiteral = 115, RULE_elementList = 116, RULE_arrayElement = 117, 
		RULE_objectLiteral = 118, RULE_propertyAssignment = 119, RULE_getAccessor = 120, 
		RULE_setAccessor = 121, RULE_propertyName = 122, RULE_arguments = 123, 
		RULE_argumentList = 124, RULE_argument = 125, RULE_expressionSequence = 126, 
		RULE_functionExpressionDeclaration = 127, RULE_singleExpression = 128, 
		RULE_asExpression = 129, RULE_arrowFunctionDeclaration = 130, RULE_arrowFunctionParameters = 131, 
		RULE_arrowFunctionBody = 132, RULE_assignmentOperator = 133, RULE_literal = 134, 
		RULE_numericLiteral = 135, RULE_identifierName = 136, RULE_identifierOrKeyWord = 137, 
		RULE_reservedWord = 138, RULE_keyword = 139, RULE_getter = 140, RULE_setter = 141, 
		RULE_eos = 142;
	private static String[] makeRuleNames() {
		return new String[] {
			"initializer", "bindingPattern", "typeParameters", "typeParameterList", 
			"typeParameter", "constraint", "typeArguments", "typeArgumentList", "typeArgument", 
			"type_", "unionOrIntersectionOrPrimaryType", "primaryType", "predefinedType", 
			"typeReference", "nestedTypeGeneric", "typeGeneric", "typeIncludeGeneric", 
			"typeName", "objectType", "typeBody", "typeMemberList", "typeMember", 
			"arrayType", "tupleType", "tupleElementTypes", "functionType", "constructorType", 
			"typeQuery", "typeQueryExpression", "propertySignatur", "typeAnnotation", 
			"callSignature", "accessibilityModifier", "parameterList", "requiredParameterList", 
			"requiredParameter", "optionalParameterList", "optionalParameter", "restParameter", 
			"identifierOrPattern", "constructSignature", "indexSignature", "methodSignature", 
			"typeAliasDeclaration", "constructorDeclaration", "interfaceDeclaration", 
			"interfaceExtendsClause", "classOrInterfaceTypeList", "enumDeclaration", 
			"enumBody", "enumMemberList", "enumMember", "namespaceDeclaration", "namespaceName", 
			"importAliasDeclaration", "importAll", "decoratorList", "decorator", 
			"decoratorMemberExpression", "decoratorCallExpression", "program", "sourceElement", 
			"statement", "block", "statementList", "abstractDeclaration", "importStatement", 
			"fromBlock", "multipleImportStatement", "exportStatement", "variableStatement", 
			"variableDeclarationList", "variableDeclaration", "emptyStatement", "expressionStatement", 
			"ifStatement", "iterationStatement", "varModifier", "continueStatement", 
			"breakStatement", "returnStatement", "yieldStatement", "withStatement", 
			"switchStatement", "caseBlock", "caseClauses", "caseClause", "defaultClause", 
			"labelledStatement", "throwStatement", "tryStatement", "catchProduction", 
			"finallyProduction", "debuggerStatement", "functionDeclaration", "classDeclaration", 
			"classHeritage", "classTail", "classExtendsClause", "implementsClause", 
			"classElement", "propertyMemberDeclaration", "propertyMemberBase", "indexMemberDeclaration", 
			"generatorMethod", "generatorFunctionDeclaration", "generatorBlock", 
			"generatorDefinition", "iteratorBlock", "iteratorDefinition", "formalParameterList", 
			"formalParameterArg", "lastFormalParameterArg", "functionBody", "sourceElements", 
			"arrayLiteral", "elementList", "arrayElement", "objectLiteral", "propertyAssignment", 
			"getAccessor", "setAccessor", "propertyName", "arguments", "argumentList", 
			"argument", "expressionSequence", "functionExpressionDeclaration", "singleExpression", 
			"asExpression", "arrowFunctionDeclaration", "arrowFunctionParameters", 
			"arrowFunctionBody", "assignmentOperator", "literal", "numericLiteral", 
			"identifierName", "identifierOrKeyWord", "reservedWord", "keyword", "getter", 
			"setter", "eos"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'['", "']'", "'('", "')'", "'{'", "'}'", "';'", 
			"','", "'='", "'?'", "':'", "'...'", "'.'", "'++'", "'--'", "'+'", "'-'", 
			"'~'", "'!'", "'*'", "'/'", "'%'", "'>>'", "'<<'", "'>>>'", "'<'", "'>'", 
			"'<='", "'>='", "'=='", "'!='", "'==='", "'!=='", "'&'", "'^'", "'|'", 
			"'&&'", "'||'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", 
			"'>>>='", "'&='", "'^='", "'|='", "'=>'", "'_'", "'$'", "'null'", null, 
			null, null, null, null, null, "'break'", "'do'", "'instanceof'", "'typeof'", 
			"'case'", "'else'", "'new'", "'var'", "'catch'", "'finally'", "'return'", 
			"'void'", "'continue'", "'for'", "'switch'", "'while'", "'debugger'", 
			"'function'", "'this'", "'with'", "'default'", "'if'", "'throw'", "'delete'", 
			"'in'", "'try'", "'as'", "'from'", "'readonly'", "'async'", "'class'", 
			"'enum'", "'extends'", "'super'", "'const'", "'export'", "'import'", 
			"'implements'", "'let'", "'private'", "'public'", "'interface'", "'package'", 
			"'protected'", "'static'", "'yield'", "'any'", "'number'", "'boolean'", 
			"'string'", "'symbol'", "'type'", "'get'", "'set'", "'constructor'", 
			"'namespace'", "'require'", "'module'", "'declare'", "'abstract'", "'is'", 
			"'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MultiLineComment", "SingleLineComment", "RegularExpressionLiteral", 
			"OpenBracket", "CloseBracket", "OpenParen", "CloseParen", "OpenBrace", 
			"CloseBrace", "SemiColon", "Comma", "Assign", "QuestionMark", "Colon", 
			"Ellipsis", "Dot", "PlusPlus", "MinusMinus", "Plus", "Minus", "BitNot", 
			"Not", "Multiply", "Divide", "Modulus", "RightShiftArithmetic", "LeftShiftArithmetic", 
			"RightShiftLogical", "LessThan", "MoreThan", "LessThanEquals", "GreaterThanEquals", 
			"Equals_", "NotEquals", "IdentityEquals", "IdentityNotEquals", "BitAnd", 
			"BitXOr", "BitOr", "And", "Or", "MultiplyAssign", "DivideAssign", "ModulusAssign", 
			"PlusAssign", "MinusAssign", "LeftShiftArithmeticAssign", "RightShiftArithmeticAssign", 
			"RightShiftLogicalAssign", "BitAndAssign", "BitXorAssign", "BitOrAssign", 
			"ARROW", "Lodash", "Dollar", "NullLiteral", "BooleanLiteral", "DecimalLiteral", 
			"HexIntegerLiteral", "OctalIntegerLiteral", "OctalIntegerLiteral2", "BinaryIntegerLiteral", 
			"Break", "Do", "Instanceof", "Typeof", "Case", "Else", "New", "Var", 
			"Catch", "Finally", "Return", "Void", "Continue", "For", "Switch", "While", 
			"Debugger", "Function", "This", "With", "Default", "If", "Throw", "Delete", 
			"In", "Try", "As", "From", "ReadOnly", "Async", "Class", "Enum", "Extends", 
			"Super", "Const", "Export", "Import", "Implements", "Let", "Private", 
			"Public", "Interface", "Package", "Protected", "Static", "Yield", "Any", 
			"Number", "Boolean", "String", "Symbol", "TypeAlias", "Get", "Set", "Constructor", 
			"Namespace", "Require", "Module", "Declare", "Abstract", "Is", "At", 
			"Identifier", "StringLiteral", "TemplateStringLiteral", "WhiteSpaces", 
			"LineTerminator", "HtmlComment", "CDataComment", "UnexpectedCharacter"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TypeScriptParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TypeScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InitializerContext extends ParserRuleContext {
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_initializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(Assign);
			setState(287);
			singleExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BindingPatternContext extends ParserRuleContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public BindingPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindingPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBindingPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBindingPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBindingPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindingPatternContext bindingPattern() throws RecognitionException {
		BindingPatternContext _localctx = new BindingPatternContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bindingPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBracket:
				{
				setState(289);
				arrayLiteral();
				}
				break;
			case OpenBrace:
				{
				setState(290);
				objectLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParametersContext extends ParserRuleContext {
		public TerminalNode LessThan() { return getToken(TypeScriptParser.LessThan, 0); }
		public TerminalNode MoreThan() { return getToken(TypeScriptParser.MoreThan, 0); }
		public TypeParameterListContext typeParameterList() {
			return getRuleContext(TypeParameterListContext.class,0);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(LessThan);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan || _la==Identifier) {
				{
				setState(294);
				typeParameterList();
				}
			}

			setState(297);
			match(MoreThan);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterListContext extends ParserRuleContext {
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public TypeParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterListContext typeParameterList() throws RecognitionException {
		TypeParameterListContext _localctx = new TypeParameterListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			typeParameter();
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(300);
				match(Comma);
				setState(301);
				typeParameter();
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeParameter);
		int _la;
		try {
			setState(312);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(Identifier);
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Extends) {
					{
					setState(308);
					constraint();
					}
				}

				}
				break;
			case LessThan:
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				typeParameters();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public TerminalNode Extends() { return getToken(TypeScriptParser.Extends, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(Extends);
			setState(315);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LessThan() { return getToken(TypeScriptParser.LessThan, 0); }
		public TerminalNode MoreThan() { return getToken(TypeScriptParser.MoreThan, 0); }
		public TypeArgumentListContext typeArgumentList() {
			return getRuleContext(TypeArgumentListContext.class,0);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(LessThan);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << LessThan))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Typeof - 66)) | (1L << (New - 66)) | (1L << (Void - 66)) | (1L << (This - 66)) | (1L << (Any - 66)) | (1L << (Number - 66)) | (1L << (Boolean - 66)) | (1L << (String - 66)) | (1L << (Symbol - 66)) | (1L << (Identifier - 66)) | (1L << (StringLiteral - 66)))) != 0)) {
				{
				setState(318);
				typeArgumentList();
				}
			}

			setState(321);
			match(MoreThan);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentListContext extends ParserRuleContext {
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public TypeArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentListContext typeArgumentList() throws RecognitionException {
		TypeArgumentListContext _localctx = new TypeArgumentListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeArgumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			typeArgument();
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(324);
				match(Comma);
				setState(325);
				typeArgument();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeArgument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_Context extends ParserRuleContext {
		public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType() {
			return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public ConstructorTypeContext constructorType() {
			return getRuleContext(ConstructorTypeContext.class,0);
		}
		public TypeGenericContext typeGeneric() {
			return getRuleContext(TypeGenericContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitType_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitType_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_);
		try {
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				unionOrIntersectionOrPrimaryType(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				functionType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				constructorType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
				typeGeneric();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(337);
				match(StringLiteral);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionOrIntersectionOrPrimaryTypeContext extends ParserRuleContext {
		public UnionOrIntersectionOrPrimaryTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionOrIntersectionOrPrimaryType; }
	 
		public UnionOrIntersectionOrPrimaryTypeContext() { }
		public void copyFrom(UnionOrIntersectionOrPrimaryTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntersectionContext extends UnionOrIntersectionOrPrimaryTypeContext {
		public List<UnionOrIntersectionOrPrimaryTypeContext> unionOrIntersectionOrPrimaryType() {
			return getRuleContexts(UnionOrIntersectionOrPrimaryTypeContext.class);
		}
		public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int i) {
			return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class,i);
		}
		public TerminalNode BitAnd() { return getToken(TypeScriptParser.BitAnd, 0); }
		public IntersectionContext(UnionOrIntersectionOrPrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIntersection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIntersection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIntersection(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryContext extends UnionOrIntersectionOrPrimaryTypeContext {
		public PrimaryTypeContext primaryType() {
			return getRuleContext(PrimaryTypeContext.class,0);
		}
		public PrimaryContext(UnionOrIntersectionOrPrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnionContext extends UnionOrIntersectionOrPrimaryTypeContext {
		public List<UnionOrIntersectionOrPrimaryTypeContext> unionOrIntersectionOrPrimaryType() {
			return getRuleContexts(UnionOrIntersectionOrPrimaryTypeContext.class);
		}
		public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int i) {
			return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class,i);
		}
		public TerminalNode BitOr() { return getToken(TypeScriptParser.BitOr, 0); }
		public UnionContext(UnionOrIntersectionOrPrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitUnion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType() throws RecognitionException {
		return unionOrIntersectionOrPrimaryType(0);
	}

	private UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		UnionOrIntersectionOrPrimaryTypeContext _localctx = new UnionOrIntersectionOrPrimaryTypeContext(_ctx, _parentState);
		UnionOrIntersectionOrPrimaryTypeContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_unionOrIntersectionOrPrimaryType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PrimaryContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(341);
			primaryType(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(351);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(349);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new UnionContext(new UnionOrIntersectionOrPrimaryTypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_unionOrIntersectionOrPrimaryType);
						setState(343);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(344);
						match(BitOr);
						setState(345);
						unionOrIntersectionOrPrimaryType(4);
						}
						break;
					case 2:
						{
						_localctx = new IntersectionContext(new UnionOrIntersectionOrPrimaryTypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_unionOrIntersectionOrPrimaryType);
						setState(346);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(347);
						match(BitAnd);
						setState(348);
						unionOrIntersectionOrPrimaryType(3);
						}
						break;
					}
					} 
				}
				setState(353);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryTypeContext extends ParserRuleContext {
		public PrimaryTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryType; }
	 
		public PrimaryTypeContext() { }
		public void copyFrom(PrimaryTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RedefinitionOfTypeContext extends PrimaryTypeContext {
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public TerminalNode Is() { return getToken(TypeScriptParser.Is, 0); }
		public PrimaryTypeContext primaryType() {
			return getRuleContext(PrimaryTypeContext.class,0);
		}
		public RedefinitionOfTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRedefinitionOfType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRedefinitionOfType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRedefinitionOfType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PredefinedPrimTypeContext extends PrimaryTypeContext {
		public PredefinedTypeContext predefinedType() {
			return getRuleContext(PredefinedTypeContext.class,0);
		}
		public PredefinedPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPredefinedPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPredefinedPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPredefinedPrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayPrimTypeContext extends PrimaryTypeContext {
		public PrimaryTypeContext primaryType() {
			return getRuleContext(PrimaryTypeContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public ArrayPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrayPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrayPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrayPrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesizedPrimTypeContext extends PrimaryTypeContext {
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public ParenthesizedPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterParenthesizedPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitParenthesizedPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitParenthesizedPrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisPrimTypeContext extends PrimaryTypeContext {
		public TerminalNode This() { return getToken(TypeScriptParser.This, 0); }
		public ThisPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterThisPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitThisPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitThisPrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TuplePrimTypeContext extends PrimaryTypeContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TupleElementTypesContext tupleElementTypes() {
			return getRuleContext(TupleElementTypesContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public TuplePrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTuplePrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTuplePrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTuplePrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectPrimTypeContext extends PrimaryTypeContext {
		public ObjectTypeContext objectType() {
			return getRuleContext(ObjectTypeContext.class,0);
		}
		public ObjectPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterObjectPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitObjectPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitObjectPrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReferencePrimTypeContext extends PrimaryTypeContext {
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public ReferencePrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterReferencePrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitReferencePrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitReferencePrimType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QueryPrimTypeContext extends PrimaryTypeContext {
		public TypeQueryContext typeQuery() {
			return getRuleContext(TypeQueryContext.class,0);
		}
		public QueryPrimTypeContext(PrimaryTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterQueryPrimType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitQueryPrimType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitQueryPrimType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryTypeContext primaryType() throws RecognitionException {
		return primaryType(0);
	}

	private PrimaryTypeContext primaryType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PrimaryTypeContext _localctx = new PrimaryTypeContext(_ctx, _parentState);
		PrimaryTypeContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_primaryType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ParenthesizedPrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(355);
				match(OpenParen);
				setState(356);
				type_();
				setState(357);
				match(CloseParen);
				}
				break;
			case 2:
				{
				_localctx = new PredefinedPrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(359);
				predefinedType();
				}
				break;
			case 3:
				{
				_localctx = new ReferencePrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(360);
				typeReference();
				}
				break;
			case 4:
				{
				_localctx = new ObjectPrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(361);
				objectType();
				}
				break;
			case 5:
				{
				_localctx = new TuplePrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(362);
				match(OpenBracket);
				setState(363);
				tupleElementTypes();
				setState(364);
				match(CloseBracket);
				}
				break;
			case 6:
				{
				_localctx = new QueryPrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(366);
				typeQuery();
				}
				break;
			case 7:
				{
				_localctx = new ThisPrimTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(367);
				match(This);
				}
				break;
			case 8:
				{
				_localctx = new RedefinitionOfTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(368);
				typeReference();
				setState(369);
				match(Is);
				setState(370);
				primaryType(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayPrimTypeContext(new PrimaryTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_primaryType);
					setState(374);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(375);
					if (!(notLineTerminator())) throw new FailedPredicateException(this, "notLineTerminator()");
					setState(376);
					match(OpenBracket);
					setState(377);
					match(CloseBracket);
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PredefinedTypeContext extends ParserRuleContext {
		public TerminalNode Any() { return getToken(TypeScriptParser.Any, 0); }
		public TerminalNode Number() { return getToken(TypeScriptParser.Number, 0); }
		public TerminalNode Boolean() { return getToken(TypeScriptParser.Boolean, 0); }
		public TerminalNode String() { return getToken(TypeScriptParser.String, 0); }
		public TerminalNode Symbol() { return getToken(TypeScriptParser.Symbol, 0); }
		public TerminalNode Void() { return getToken(TypeScriptParser.Void, 0); }
		public PredefinedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefinedType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPredefinedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPredefinedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPredefinedType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredefinedTypeContext predefinedType() throws RecognitionException {
		PredefinedTypeContext _localctx = new PredefinedTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_predefinedType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			_la = _input.LA(1);
			if ( !(((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (Void - 74)) | (1L << (Any - 74)) | (1L << (Number - 74)) | (1L << (Boolean - 74)) | (1L << (String - 74)) | (1L << (Symbol - 74)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeReferenceContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public NestedTypeGenericContext nestedTypeGeneric() {
			return getRuleContext(NestedTypeGenericContext.class,0);
		}
		public TypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeReferenceContext typeReference() throws RecognitionException {
		TypeReferenceContext _localctx = new TypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			typeName();
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(386);
				nestedTypeGeneric();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NestedTypeGenericContext extends ParserRuleContext {
		public TypeIncludeGenericContext typeIncludeGeneric() {
			return getRuleContext(TypeIncludeGenericContext.class,0);
		}
		public TypeGenericContext typeGeneric() {
			return getRuleContext(TypeGenericContext.class,0);
		}
		public NestedTypeGenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedTypeGeneric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNestedTypeGeneric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNestedTypeGeneric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNestedTypeGeneric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NestedTypeGenericContext nestedTypeGeneric() throws RecognitionException {
		NestedTypeGenericContext _localctx = new NestedTypeGenericContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nestedTypeGeneric);
		try {
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(389);
				typeIncludeGeneric();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(390);
				typeGeneric();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeGenericContext extends ParserRuleContext {
		public TerminalNode LessThan() { return getToken(TypeScriptParser.LessThan, 0); }
		public TypeArgumentListContext typeArgumentList() {
			return getRuleContext(TypeArgumentListContext.class,0);
		}
		public TerminalNode MoreThan() { return getToken(TypeScriptParser.MoreThan, 0); }
		public TypeGenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeGeneric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeGeneric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeGeneric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeGeneric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeGenericContext typeGeneric() throws RecognitionException {
		TypeGenericContext _localctx = new TypeGenericContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typeGeneric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(LessThan);
			setState(394);
			typeArgumentList();
			setState(395);
			match(MoreThan);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeIncludeGenericContext extends ParserRuleContext {
		public List<TerminalNode> LessThan() { return getTokens(TypeScriptParser.LessThan); }
		public TerminalNode LessThan(int i) {
			return getToken(TypeScriptParser.LessThan, i);
		}
		public List<TypeArgumentListContext> typeArgumentList() {
			return getRuleContexts(TypeArgumentListContext.class);
		}
		public TypeArgumentListContext typeArgumentList(int i) {
			return getRuleContext(TypeArgumentListContext.class,i);
		}
		public List<TerminalNode> MoreThan() { return getTokens(TypeScriptParser.MoreThan); }
		public TerminalNode MoreThan(int i) {
			return getToken(TypeScriptParser.MoreThan, i);
		}
		public BindingPatternContext bindingPattern() {
			return getRuleContext(BindingPatternContext.class,0);
		}
		public TerminalNode RightShiftArithmetic() { return getToken(TypeScriptParser.RightShiftArithmetic, 0); }
		public TypeIncludeGenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIncludeGeneric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeIncludeGeneric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeIncludeGeneric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeIncludeGeneric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIncludeGenericContext typeIncludeGeneric() throws RecognitionException {
		TypeIncludeGenericContext _localctx = new TypeIncludeGenericContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_typeIncludeGeneric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(LessThan);
			setState(398);
			typeArgumentList();
			setState(399);
			match(LessThan);
			setState(400);
			typeArgumentList();
			setState(406);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MoreThan:
				{
				setState(401);
				match(MoreThan);
				setState(402);
				bindingPattern();
				setState(403);
				match(MoreThan);
				}
				break;
			case RightShiftArithmetic:
				{
				setState(405);
				match(RightShiftArithmetic);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public NamespaceNameContext namespaceName() {
			return getRuleContext(NamespaceNameContext.class,0);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeName);
		try {
			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				namespaceName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectTypeContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TypeBodyContext typeBody() {
			return getRuleContext(TypeBodyContext.class,0);
		}
		public ObjectTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterObjectType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitObjectType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitObjectType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectTypeContext objectType() throws RecognitionException {
		ObjectTypeContext _localctx = new ObjectTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_objectType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(OpenBrace);
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenParen) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(413);
				typeBody();
				}
			}

			setState(416);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBodyContext extends ParserRuleContext {
		public TypeMemberListContext typeMemberList() {
			return getRuleContext(TypeMemberListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode Comma() { return getToken(TypeScriptParser.Comma, 0); }
		public TypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeBodyContext typeBody() throws RecognitionException {
		TypeBodyContext _localctx = new TypeBodyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			typeMemberList();
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SemiColon || _la==Comma) {
				{
				setState(419);
				_la = _input.LA(1);
				if ( !(_la==SemiColon || _la==Comma) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeMemberListContext extends ParserRuleContext {
		public List<TypeMemberContext> typeMember() {
			return getRuleContexts(TypeMemberContext.class);
		}
		public TypeMemberContext typeMember(int i) {
			return getRuleContext(TypeMemberContext.class,i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(TypeScriptParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(TypeScriptParser.SemiColon, i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public TypeMemberListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeMemberList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeMemberList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeMemberList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeMemberList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeMemberListContext typeMemberList() throws RecognitionException {
		TypeMemberListContext _localctx = new TypeMemberListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeMemberList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			typeMember();
			setState(427);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(423);
					_la = _input.LA(1);
					if ( !(_la==SemiColon || _la==Comma) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(424);
					typeMember();
					}
					} 
				}
				setState(429);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeMemberContext extends ParserRuleContext {
		public PropertySignaturContext propertySignatur() {
			return getRuleContext(PropertySignaturContext.class,0);
		}
		public CallSignatureContext callSignature() {
			return getRuleContext(CallSignatureContext.class,0);
		}
		public ConstructSignatureContext constructSignature() {
			return getRuleContext(ConstructSignatureContext.class,0);
		}
		public IndexSignatureContext indexSignature() {
			return getRuleContext(IndexSignatureContext.class,0);
		}
		public MethodSignatureContext methodSignature() {
			return getRuleContext(MethodSignatureContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(TypeScriptParser.ARROW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeMember; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeMemberContext typeMember() throws RecognitionException {
		TypeMemberContext _localctx = new TypeMemberContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeMember);
		int _la;
		try {
			setState(439);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(430);
				propertySignatur();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				callSignature();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(432);
				constructSignature();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				indexSignature();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(434);
				methodSignature();
				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ARROW) {
					{
					setState(435);
					match(ARROW);
					setState(436);
					type_();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public PrimaryTypeContext primaryType() {
			return getRuleContext(PrimaryTypeContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			primaryType(0);
			setState(442);
			if (!(notLineTerminator())) throw new FailedPredicateException(this, "notLineTerminator()");
			setState(443);
			match(OpenBracket);
			setState(444);
			match(CloseBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleTypeContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TupleElementTypesContext tupleElementTypes() {
			return getRuleContext(TupleElementTypesContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public TupleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTupleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTupleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTupleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleTypeContext tupleType() throws RecognitionException {
		TupleTypeContext _localctx = new TupleTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tupleType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(OpenBracket);
			setState(447);
			tupleElementTypes();
			setState(448);
			match(CloseBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleElementTypesContext extends ParserRuleContext {
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public TupleElementTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleElementTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTupleElementTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTupleElementTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTupleElementTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleElementTypesContext tupleElementTypes() throws RecognitionException {
		TupleElementTypesContext _localctx = new TupleElementTypesContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_tupleElementTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			type_();
			setState(455);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(451);
				match(Comma);
				setState(452);
				type_();
				}
				}
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode ARROW() { return getToken(TypeScriptParser.ARROW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_functionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(458);
				typeParameters();
				}
			}

			setState(461);
			match(OpenParen);
			setState(463);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (Any - 64)) | (1L << (Number - 64)) | (1L << (Boolean - 64)) | (1L << (String - 64)) | (1L << (Symbol - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (At - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(462);
				parameterList();
				}
			}

			setState(465);
			match(CloseParen);
			setState(466);
			match(ARROW);
			setState(467);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorTypeContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(TypeScriptParser.New, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode ARROW() { return getToken(TypeScriptParser.ARROW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ConstructorTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterConstructorType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitConstructorType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitConstructorType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorTypeContext constructorType() throws RecognitionException {
		ConstructorTypeContext _localctx = new ConstructorTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constructorType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			match(New);
			setState(471);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(470);
				typeParameters();
				}
			}

			setState(473);
			match(OpenParen);
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (Any - 64)) | (1L << (Number - 64)) | (1L << (Boolean - 64)) | (1L << (String - 64)) | (1L << (Symbol - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (At - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(474);
				parameterList();
				}
			}

			setState(477);
			match(CloseParen);
			setState(478);
			match(ARROW);
			setState(479);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeQueryContext extends ParserRuleContext {
		public TerminalNode Typeof() { return getToken(TypeScriptParser.Typeof, 0); }
		public TypeQueryExpressionContext typeQueryExpression() {
			return getRuleContext(TypeQueryExpressionContext.class,0);
		}
		public TypeQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeQueryContext typeQuery() throws RecognitionException {
		TypeQueryContext _localctx = new TypeQueryContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_typeQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			match(Typeof);
			setState(482);
			typeQueryExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeQueryExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public List<IdentifierNameContext> identifierName() {
			return getRuleContexts(IdentifierNameContext.class);
		}
		public IdentifierNameContext identifierName(int i) {
			return getRuleContext(IdentifierNameContext.class,i);
		}
		public List<TerminalNode> Dot() { return getTokens(TypeScriptParser.Dot); }
		public TerminalNode Dot(int i) {
			return getToken(TypeScriptParser.Dot, i);
		}
		public TypeQueryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeQueryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeQueryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeQueryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeQueryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeQueryExpressionContext typeQueryExpression() throws RecognitionException {
		TypeQueryExpressionContext _localctx = new TypeQueryExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_typeQueryExpression);
		try {
			int _alt;
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(484);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(488); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(485);
						identifierName();
						setState(486);
						match(Dot);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(490); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(492);
				identifierName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertySignaturContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public TerminalNode ReadOnly() { return getToken(TypeScriptParser.ReadOnly, 0); }
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(TypeScriptParser.ARROW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public PropertySignaturContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertySignatur; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertySignatur(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertySignatur(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertySignatur(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertySignaturContext propertySignatur() throws RecognitionException {
		PropertySignaturContext _localctx = new PropertySignaturContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_propertySignatur);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(496);
				match(ReadOnly);
				}
				break;
			}
			setState(499);
			propertyName();
			setState(501);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QuestionMark) {
				{
				setState(500);
				match(QuestionMark);
				}
			}

			setState(504);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(503);
				typeAnnotation();
				}
			}

			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(506);
				match(ARROW);
				setState(507);
				type_();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAnnotationContext extends ParserRuleContext {
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAnnotationContext typeAnnotation() throws RecognitionException {
		TypeAnnotationContext _localctx = new TypeAnnotationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_typeAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			match(Colon);
			setState(511);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallSignatureContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public CallSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCallSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCallSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCallSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallSignatureContext callSignature() throws RecognitionException {
		CallSignatureContext _localctx = new CallSignatureContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_callSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(513);
				typeParameters();
				}
			}

			setState(516);
			match(OpenParen);
			setState(518);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (Any - 64)) | (1L << (Number - 64)) | (1L << (Boolean - 64)) | (1L << (String - 64)) | (1L << (Symbol - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (At - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(517);
				parameterList();
				}
			}

			setState(520);
			match(CloseParen);
			setState(522);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(521);
				typeAnnotation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccessibilityModifierContext extends ParserRuleContext {
		public TerminalNode Public() { return getToken(TypeScriptParser.Public, 0); }
		public TerminalNode Private() { return getToken(TypeScriptParser.Private, 0); }
		public TerminalNode Protected() { return getToken(TypeScriptParser.Protected, 0); }
		public AccessibilityModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessibilityModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAccessibilityModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAccessibilityModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAccessibilityModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessibilityModifierContext accessibilityModifier() throws RecognitionException {
		AccessibilityModifierContext _localctx = new AccessibilityModifierContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_accessibilityModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			_la = _input.LA(1);
			if ( !(((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public RestParameterContext restParameter() {
			return getRuleContext(RestParameterContext.class,0);
		}
		public List<PredefinedTypeContext> predefinedType() {
			return getRuleContexts(PredefinedTypeContext.class);
		}
		public PredefinedTypeContext predefinedType(int i) {
			return getRuleContext(PredefinedTypeContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public OptionalParameterListContext optionalParameterList() {
			return getRuleContext(OptionalParameterListContext.class,0);
		}
		public RequiredParameterListContext requiredParameterList() {
			return getRuleContext(RequiredParameterListContext.class,0);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_parameterList);
		int _la;
		try {
			setState(552);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(526);
				restParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(527);
				predefinedType();
				setState(532);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(528);
					match(Comma);
					setState(529);
					predefinedType();
					}
					}
					setState(534);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(535);
				optionalParameterList();
				setState(538);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(536);
					match(Comma);
					setState(537);
					restParameter();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(540);
				requiredParameterList();
				setState(550);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(541);
					match(Comma);
					setState(548);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OpenBracket:
					case OpenBrace:
					case NullLiteral:
					case BooleanLiteral:
					case Break:
					case Do:
					case Instanceof:
					case Typeof:
					case Case:
					case Else:
					case New:
					case Var:
					case Catch:
					case Finally:
					case Return:
					case Void:
					case Continue:
					case For:
					case Switch:
					case While:
					case Debugger:
					case Function:
					case This:
					case With:
					case Default:
					case If:
					case Throw:
					case Delete:
					case In:
					case Try:
					case From:
					case ReadOnly:
					case Async:
					case Class:
					case Enum:
					case Extends:
					case Super:
					case Const:
					case Export:
					case Import:
					case Implements:
					case Let:
					case Private:
					case Public:
					case Interface:
					case Package:
					case Protected:
					case Static:
					case Yield:
					case String:
					case TypeAlias:
					case Get:
					case Set:
					case Require:
					case At:
					case Identifier:
						{
						setState(542);
						optionalParameterList();
						setState(545);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==Comma) {
							{
							setState(543);
							match(Comma);
							setState(544);
							restParameter();
							}
						}

						}
						break;
					case Ellipsis:
						{
						setState(547);
						restParameter();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequiredParameterListContext extends ParserRuleContext {
		public List<RequiredParameterContext> requiredParameter() {
			return getRuleContexts(RequiredParameterContext.class);
		}
		public RequiredParameterContext requiredParameter(int i) {
			return getRuleContext(RequiredParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public RequiredParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiredParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRequiredParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRequiredParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRequiredParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequiredParameterListContext requiredParameterList() throws RecognitionException {
		RequiredParameterListContext _localctx = new RequiredParameterListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_requiredParameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			requiredParameter();
			setState(559);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(555);
					match(Comma);
					setState(556);
					requiredParameter();
					}
					} 
				}
				setState(561);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequiredParameterContext extends ParserRuleContext {
		public IdentifierOrPatternContext identifierOrPattern() {
			return getRuleContext(IdentifierOrPatternContext.class,0);
		}
		public DecoratorListContext decoratorList() {
			return getRuleContext(DecoratorListContext.class,0);
		}
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public RequiredParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiredParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRequiredParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRequiredParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRequiredParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequiredParameterContext requiredParameter() throws RecognitionException {
		RequiredParameterContext _localctx = new RequiredParameterContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_requiredParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==At) {
				{
				setState(562);
				decoratorList();
				}
			}

			setState(566);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(565);
				accessibilityModifier();
				}
				break;
			}
			setState(568);
			identifierOrPattern();
			setState(570);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(569);
				typeAnnotation();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalParameterListContext extends ParserRuleContext {
		public List<OptionalParameterContext> optionalParameter() {
			return getRuleContexts(OptionalParameterContext.class);
		}
		public OptionalParameterContext optionalParameter(int i) {
			return getRuleContext(OptionalParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public OptionalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterOptionalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitOptionalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitOptionalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalParameterListContext optionalParameterList() throws RecognitionException {
		OptionalParameterListContext _localctx = new OptionalParameterListContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_optionalParameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			optionalParameter();
			setState(577);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(573);
					match(Comma);
					setState(574);
					optionalParameter();
					}
					} 
				}
				setState(579);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalParameterContext extends ParserRuleContext {
		public IdentifierOrPatternContext identifierOrPattern() {
			return getRuleContext(IdentifierOrPatternContext.class,0);
		}
		public DecoratorListContext decoratorList() {
			return getRuleContext(DecoratorListContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public OptionalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterOptionalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitOptionalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitOptionalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalParameterContext optionalParameter() throws RecognitionException {
		OptionalParameterContext _localctx = new OptionalParameterContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_optionalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==At) {
				{
				setState(580);
				decoratorList();
				}
			}

			{
			setState(584);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(583);
				accessibilityModifier();
				}
				break;
			}
			setState(586);
			identifierOrPattern();
			setState(595);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QuestionMark:
				{
				setState(587);
				match(QuestionMark);
				setState(589);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(588);
					typeAnnotation();
					}
				}

				}
				break;
			case Assign:
			case Colon:
				{
				setState(592);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(591);
					typeAnnotation();
					}
				}

				setState(594);
				initializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RestParameterContext extends ParserRuleContext {
		public TerminalNode Ellipsis() { return getToken(TypeScriptParser.Ellipsis, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public RestParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRestParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRestParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRestParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestParameterContext restParameter() throws RecognitionException {
		RestParameterContext _localctx = new RestParameterContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_restParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597);
			match(Ellipsis);
			setState(598);
			singleExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierOrPatternContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public BindingPatternContext bindingPattern() {
			return getRuleContext(BindingPatternContext.class,0);
		}
		public IdentifierOrPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierOrPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIdentifierOrPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIdentifierOrPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIdentifierOrPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierOrPatternContext identifierOrPattern() throws RecognitionException {
		IdentifierOrPatternContext _localctx = new IdentifierOrPatternContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_identifierOrPattern);
		try {
			setState(602);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case From:
			case ReadOnly:
			case Async:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Implements:
			case Let:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Yield:
			case String:
			case TypeAlias:
			case Get:
			case Set:
			case Require:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(600);
				identifierName();
				}
				break;
			case OpenBracket:
			case OpenBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(601);
				bindingPattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructSignatureContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(TypeScriptParser.New, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public ConstructSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterConstructSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitConstructSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitConstructSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructSignatureContext constructSignature() throws RecognitionException {
		ConstructSignatureContext _localctx = new ConstructSignatureContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_constructSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(604);
			match(New);
			setState(606);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(605);
				typeParameters();
				}
			}

			setState(608);
			match(OpenParen);
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (Any - 64)) | (1L << (Number - 64)) | (1L << (Boolean - 64)) | (1L << (String - 64)) | (1L << (Symbol - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (At - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(609);
				parameterList();
				}
			}

			setState(612);
			match(CloseParen);
			setState(614);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(613);
				typeAnnotation();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexSignatureContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode Number() { return getToken(TypeScriptParser.Number, 0); }
		public TerminalNode String() { return getToken(TypeScriptParser.String, 0); }
		public IndexSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIndexSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIndexSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIndexSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexSignatureContext indexSignature() throws RecognitionException {
		IndexSignatureContext _localctx = new IndexSignatureContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_indexSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			match(OpenBracket);
			setState(617);
			match(Identifier);
			setState(618);
			match(Colon);
			setState(619);
			_la = _input.LA(1);
			if ( !(_la==Number || _la==String) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(620);
			match(CloseBracket);
			setState(621);
			typeAnnotation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodSignatureContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public CallSignatureContext callSignature() {
			return getRuleContext(CallSignatureContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public MethodSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMethodSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMethodSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMethodSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodSignatureContext methodSignature() throws RecognitionException {
		MethodSignatureContext _localctx = new MethodSignatureContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			propertyName();
			setState(625);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QuestionMark) {
				{
				setState(624);
				match(QuestionMark);
				}
			}

			setState(627);
			callSignature();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAliasDeclarationContext extends ParserRuleContext {
		public TerminalNode TypeAlias() { return getToken(TypeScriptParser.TypeAlias, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TypeAliasDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAliasDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeAliasDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeAliasDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeAliasDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAliasDeclarationContext typeAliasDeclaration() throws RecognitionException {
		TypeAliasDeclarationContext _localctx = new TypeAliasDeclarationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_typeAliasDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(629);
			match(TypeAlias);
			setState(630);
			match(Identifier);
			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(631);
				typeParameters();
				}
			}

			setState(634);
			match(Assign);
			setState(635);
			type_();
			setState(636);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public TerminalNode Constructor() { return getToken(TypeScriptParser.Constructor, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)))) != 0)) {
				{
				setState(638);
				accessibilityModifier();
				}
			}

			setState(641);
			match(Constructor);
			setState(642);
			match(OpenParen);
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
				{
				setState(643);
				formalParameterList();
				}
			}

			setState(646);
			match(CloseParen);
			setState(652);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				{
				setState(647);
				match(OpenBrace);
				setState(648);
				functionBody();
				setState(649);
				match(CloseBrace);
				}
				}
				break;
			case 2:
				{
				setState(651);
				match(SemiColon);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode Interface() { return getToken(TypeScriptParser.Interface, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ObjectTypeContext objectType() {
			return getRuleContext(ObjectTypeContext.class,0);
		}
		public TerminalNode Export() { return getToken(TypeScriptParser.Export, 0); }
		public TerminalNode Declare() { return getToken(TypeScriptParser.Declare, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public InterfaceExtendsClauseContext interfaceExtendsClause() {
			return getRuleContext(InterfaceExtendsClauseContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitInterfaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitInterfaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Export) {
				{
				setState(654);
				match(Export);
				}
			}

			setState(658);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Declare) {
				{
				setState(657);
				match(Declare);
				}
			}

			setState(660);
			match(Interface);
			setState(661);
			match(Identifier);
			setState(663);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(662);
				typeParameters();
				}
			}

			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Extends) {
				{
				setState(665);
				interfaceExtendsClause();
				}
			}

			setState(668);
			objectType();
			setState(670);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(669);
				match(SemiColon);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceExtendsClauseContext extends ParserRuleContext {
		public TerminalNode Extends() { return getToken(TypeScriptParser.Extends, 0); }
		public ClassOrInterfaceTypeListContext classOrInterfaceTypeList() {
			return getRuleContext(ClassOrInterfaceTypeListContext.class,0);
		}
		public InterfaceExtendsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceExtendsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterInterfaceExtendsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitInterfaceExtendsClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitInterfaceExtendsClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceExtendsClauseContext interfaceExtendsClause() throws RecognitionException {
		InterfaceExtendsClauseContext _localctx = new InterfaceExtendsClauseContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_interfaceExtendsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			match(Extends);
			setState(673);
			classOrInterfaceTypeList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceTypeListContext extends ParserRuleContext {
		public List<TypeReferenceContext> typeReference() {
			return getRuleContexts(TypeReferenceContext.class);
		}
		public TypeReferenceContext typeReference(int i) {
			return getRuleContext(TypeReferenceContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public ClassOrInterfaceTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassOrInterfaceTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassOrInterfaceTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassOrInterfaceTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOrInterfaceTypeListContext classOrInterfaceTypeList() throws RecognitionException {
		ClassOrInterfaceTypeListContext _localctx = new ClassOrInterfaceTypeListContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_classOrInterfaceTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(675);
			typeReference();
			setState(680);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(676);
				match(Comma);
				setState(677);
				typeReference();
				}
				}
				setState(682);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode Enum() { return getToken(TypeScriptParser.Enum, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TerminalNode Const() { return getToken(TypeScriptParser.Const, 0); }
		public EnumBodyContext enumBody() {
			return getRuleContext(EnumBodyContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEnumDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEnumDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(684);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Const) {
				{
				setState(683);
				match(Const);
				}
			}

			setState(686);
			match(Enum);
			setState(687);
			match(Identifier);
			setState(688);
			match(OpenBrace);
			setState(690);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(689);
				enumBody();
				}
			}

			setState(692);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyContext extends ParserRuleContext {
		public EnumMemberListContext enumMemberList() {
			return getRuleContext(EnumMemberListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(TypeScriptParser.Comma, 0); }
		public EnumBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEnumBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEnumBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEnumBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumBodyContext enumBody() throws RecognitionException {
		EnumBodyContext _localctx = new EnumBodyContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_enumBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			enumMemberList();
			setState(696);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(695);
				match(Comma);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumMemberListContext extends ParserRuleContext {
		public List<EnumMemberContext> enumMember() {
			return getRuleContexts(EnumMemberContext.class);
		}
		public EnumMemberContext enumMember(int i) {
			return getRuleContext(EnumMemberContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public EnumMemberListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumMemberList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEnumMemberList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEnumMemberList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEnumMemberList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumMemberListContext enumMemberList() throws RecognitionException {
		EnumMemberListContext _localctx = new EnumMemberListContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_enumMemberList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			enumMember();
			setState(703);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(699);
					match(Comma);
					setState(700);
					enumMember();
					}
					} 
				}
				setState(705);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumMemberContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public EnumMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumMember; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEnumMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEnumMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEnumMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumMemberContext enumMember() throws RecognitionException {
		EnumMemberContext _localctx = new EnumMemberContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_enumMember);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			propertyName();
			setState(709);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(707);
				match(Assign);
				setState(708);
				singleExpression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceDeclarationContext extends ParserRuleContext {
		public TerminalNode Namespace() { return getToken(TypeScriptParser.Namespace, 0); }
		public NamespaceNameContext namespaceName() {
			return getRuleContext(NamespaceNameContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public NamespaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNamespaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNamespaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNamespaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceDeclarationContext namespaceDeclaration() throws RecognitionException {
		NamespaceDeclarationContext _localctx = new NamespaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_namespaceDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711);
			match(Namespace);
			setState(712);
			namespaceName();
			setState(713);
			match(OpenBrace);
			setState(715);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(714);
				statementList();
				}
				break;
			}
			setState(717);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceNameContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(TypeScriptParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(TypeScriptParser.Identifier, i);
		}
		public List<TerminalNode> Dot() { return getTokens(TypeScriptParser.Dot); }
		public TerminalNode Dot(int i) {
			return getToken(TypeScriptParser.Dot, i);
		}
		public NamespaceNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNamespaceName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNamespaceName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNamespaceName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceNameContext namespaceName() throws RecognitionException {
		NamespaceNameContext _localctx = new NamespaceNameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_namespaceName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(719);
			match(Identifier);
			setState(728);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(721); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(720);
						match(Dot);
						}
						}
						setState(723); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==Dot );
					setState(725);
					match(Identifier);
					}
					} 
				}
				setState(730);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportAliasDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public NamespaceNameContext namespaceName() {
			return getRuleContext(NamespaceNameContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode Require() { return getToken(TypeScriptParser.Require, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public ImportAliasDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importAliasDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterImportAliasDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitImportAliasDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitImportAliasDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportAliasDeclarationContext importAliasDeclaration() throws RecognitionException {
		ImportAliasDeclarationContext _localctx = new ImportAliasDeclarationContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_importAliasDeclaration);
		try {
			setState(743);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(731);
				match(Identifier);
				setState(732);
				match(Assign);
				setState(733);
				namespaceName();
				setState(734);
				match(SemiColon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(736);
				match(Identifier);
				setState(737);
				match(Assign);
				setState(738);
				match(Require);
				setState(739);
				match(OpenParen);
				setState(740);
				match(StringLiteral);
				setState(741);
				match(CloseParen);
				setState(742);
				match(SemiColon);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportAllContext extends ParserRuleContext {
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public ImportAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterImportAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitImportAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitImportAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportAllContext importAll() throws RecognitionException {
		ImportAllContext _localctx = new ImportAllContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_importAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratorListContext extends ParserRuleContext {
		public List<DecoratorContext> decorator() {
			return getRuleContexts(DecoratorContext.class);
		}
		public DecoratorContext decorator(int i) {
			return getRuleContext(DecoratorContext.class,i);
		}
		public DecoratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decoratorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDecoratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDecoratorList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDecoratorList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorListContext decoratorList() throws RecognitionException {
		DecoratorListContext _localctx = new DecoratorListContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_decoratorList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(748); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(747);
					decorator();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(750); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratorContext extends ParserRuleContext {
		public TerminalNode At() { return getToken(TypeScriptParser.At, 0); }
		public DecoratorMemberExpressionContext decoratorMemberExpression() {
			return getRuleContext(DecoratorMemberExpressionContext.class,0);
		}
		public DecoratorCallExpressionContext decoratorCallExpression() {
			return getRuleContext(DecoratorCallExpressionContext.class,0);
		}
		public DecoratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDecorator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDecorator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDecorator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorContext decorator() throws RecognitionException {
		DecoratorContext _localctx = new DecoratorContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_decorator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(752);
			match(At);
			setState(755);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				{
				setState(753);
				decoratorMemberExpression(0);
				}
				break;
			case 2:
				{
				setState(754);
				decoratorCallExpression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratorMemberExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public DecoratorMemberExpressionContext decoratorMemberExpression() {
			return getRuleContext(DecoratorMemberExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(TypeScriptParser.Dot, 0); }
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public DecoratorMemberExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decoratorMemberExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDecoratorMemberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDecoratorMemberExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDecoratorMemberExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorMemberExpressionContext decoratorMemberExpression() throws RecognitionException {
		return decoratorMemberExpression(0);
	}

	private DecoratorMemberExpressionContext decoratorMemberExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DecoratorMemberExpressionContext _localctx = new DecoratorMemberExpressionContext(_ctx, _parentState);
		DecoratorMemberExpressionContext _prevctx = _localctx;
		int _startState = 116;
		enterRecursionRule(_localctx, 116, RULE_decoratorMemberExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(763);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(758);
				match(Identifier);
				}
				break;
			case OpenParen:
				{
				setState(759);
				match(OpenParen);
				setState(760);
				singleExpression(0);
				setState(761);
				match(CloseParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(770);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DecoratorMemberExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_decoratorMemberExpression);
					setState(765);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(766);
					match(Dot);
					setState(767);
					identifierName();
					}
					} 
				}
				setState(772);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DecoratorCallExpressionContext extends ParserRuleContext {
		public DecoratorMemberExpressionContext decoratorMemberExpression() {
			return getRuleContext(DecoratorMemberExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public DecoratorCallExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decoratorCallExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDecoratorCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDecoratorCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDecoratorCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorCallExpressionContext decoratorCallExpression() throws RecognitionException {
		DecoratorCallExpressionContext _localctx = new DecoratorCallExpressionContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_decoratorCallExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(773);
			decoratorMemberExpression(0);
			setState(774);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TypeScriptParser.EOF, 0); }
		public SourceElementsContext sourceElements() {
			return getRuleContext(SourceElementsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(777);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(776);
				sourceElements();
				}
				break;
			}
			setState(779);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceElementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Export() { return getToken(TypeScriptParser.Export, 0); }
		public SourceElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSourceElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSourceElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSourceElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceElementContext sourceElement() throws RecognitionException {
		SourceElementContext _localctx = new SourceElementContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_sourceElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(782);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(781);
				match(Export);
				}
				break;
			}
			setState(784);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VariableStatementContext variableStatement() {
			return getRuleContext(VariableStatementContext.class,0);
		}
		public ImportStatementContext importStatement() {
			return getRuleContext(ImportStatementContext.class,0);
		}
		public ExportStatementContext exportStatement() {
			return getRuleContext(ExportStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public AbstractDeclarationContext abstractDeclaration() {
			return getRuleContext(AbstractDeclarationContext.class,0);
		}
		public DecoratorListContext decoratorList() {
			return getRuleContext(DecoratorListContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public NamespaceDeclarationContext namespaceDeclaration() {
			return getRuleContext(NamespaceDeclarationContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public YieldStatementContext yieldStatement() {
			return getRuleContext(YieldStatementContext.class,0);
		}
		public WithStatementContext withStatement() {
			return getRuleContext(WithStatementContext.class,0);
		}
		public LabelledStatementContext labelledStatement() {
			return getRuleContext(LabelledStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public TryStatementContext tryStatement() {
			return getRuleContext(TryStatementContext.class,0);
		}
		public DebuggerStatementContext debuggerStatement() {
			return getRuleContext(DebuggerStatementContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ArrowFunctionDeclarationContext arrowFunctionDeclaration() {
			return getRuleContext(ArrowFunctionDeclarationContext.class,0);
		}
		public GeneratorFunctionDeclarationContext generatorFunctionDeclaration() {
			return getRuleContext(GeneratorFunctionDeclarationContext.class,0);
		}
		public TypeAliasDeclarationContext typeAliasDeclaration() {
			return getRuleContext(TypeAliasDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public TerminalNode Export() { return getToken(TypeScriptParser.Export, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_statement);
		try {
			setState(816);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(786);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(787);
				variableStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(788);
				importStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(789);
				exportStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(790);
				emptyStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(791);
				abstractDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(792);
				decoratorList();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(793);
				classDeclaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(794);
				interfaceDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(795);
				namespaceDeclaration();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(796);
				ifStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(797);
				iterationStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(798);
				continueStatement();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(799);
				breakStatement();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(800);
				returnStatement();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(801);
				yieldStatement();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(802);
				withStatement();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(803);
				labelledStatement();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(804);
				switchStatement();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(805);
				throwStatement();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(806);
				tryStatement();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(807);
				debuggerStatement();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(808);
				functionDeclaration();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(809);
				arrowFunctionDeclaration();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(810);
				generatorFunctionDeclaration();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(811);
				typeAliasDeclaration();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(812);
				enumDeclaration();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(813);
				expressionStatement();
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(814);
				match(Export);
				setState(815);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(818);
			match(OpenBrace);
			setState(820);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(819);
				statementList();
				}
				break;
			}
			setState(822);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementListContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitStatementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		StatementListContext _localctx = new StatementListContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_statementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(825); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(824);
					statement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(827); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbstractDeclarationContext extends ParserRuleContext {
		public TerminalNode Abstract() { return getToken(TypeScriptParser.Abstract, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public CallSignatureContext callSignature() {
			return getRuleContext(CallSignatureContext.class,0);
		}
		public VariableStatementContext variableStatement() {
			return getRuleContext(VariableStatementContext.class,0);
		}
		public AbstractDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstractDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAbstractDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAbstractDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAbstractDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbstractDeclarationContext abstractDeclaration() throws RecognitionException {
		AbstractDeclarationContext _localctx = new AbstractDeclarationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_abstractDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(829);
			match(Abstract);
			setState(833);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(830);
				match(Identifier);
				setState(831);
				callSignature();
				}
				break;
			case 2:
				{
				setState(832);
				variableStatement();
				}
				break;
			}
			setState(835);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportStatementContext extends ParserRuleContext {
		public TerminalNode Import() { return getToken(TypeScriptParser.Import, 0); }
		public FromBlockContext fromBlock() {
			return getRuleContext(FromBlockContext.class,0);
		}
		public ImportAliasDeclarationContext importAliasDeclaration() {
			return getRuleContext(ImportAliasDeclarationContext.class,0);
		}
		public ImportAllContext importAll() {
			return getRuleContext(ImportAllContext.class,0);
		}
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_importStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			match(Import);
			setState(841);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				{
				setState(838);
				fromBlock();
				}
				break;
			case 2:
				{
				setState(839);
				importAliasDeclaration();
				}
				break;
			case 3:
				{
				setState(840);
				importAll();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromBlockContext extends ParserRuleContext {
		public TerminalNode From() { return getToken(TypeScriptParser.From, 0); }
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public TerminalNode Dollar() { return getToken(TypeScriptParser.Dollar, 0); }
		public TerminalNode Lodash() { return getToken(TypeScriptParser.Lodash, 0); }
		public TerminalNode Multiply() { return getToken(TypeScriptParser.Multiply, 0); }
		public MultipleImportStatementContext multipleImportStatement() {
			return getRuleContext(MultipleImportStatementContext.class,0);
		}
		public TerminalNode As() { return getToken(TypeScriptParser.As, 0); }
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public FromBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFromBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFromBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFromBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromBlockContext fromBlock() throws RecognitionException {
		FromBlockContext _localctx = new FromBlockContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_fromBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Dollar:
				{
				setState(843);
				match(Dollar);
				}
				break;
			case Lodash:
				{
				setState(844);
				match(Lodash);
				}
				break;
			case Multiply:
				{
				setState(845);
				match(Multiply);
				}
				break;
			case OpenBrace:
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case From:
			case ReadOnly:
			case Async:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Implements:
			case Let:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Yield:
			case String:
			case TypeAlias:
			case Get:
			case Set:
			case Require:
			case Identifier:
				{
				setState(846);
				multipleImportStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(851);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==As) {
				{
				setState(849);
				match(As);
				setState(850);
				identifierName();
				}
			}

			setState(853);
			match(From);
			setState(854);
			match(StringLiteral);
			setState(855);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleImportStatementContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public List<IdentifierNameContext> identifierName() {
			return getRuleContexts(IdentifierNameContext.class);
		}
		public IdentifierNameContext identifierName(int i) {
			return getRuleContext(IdentifierNameContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public MultipleImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleImportStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMultipleImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMultipleImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMultipleImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleImportStatementContext multipleImportStatement() throws RecognitionException {
		MultipleImportStatementContext _localctx = new MultipleImportStatementContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_multipleImportStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(860);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NullLiteral) | (1L << BooleanLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)))) != 0)) {
				{
				setState(857);
				identifierName();
				setState(858);
				match(Comma);
				}
			}

			setState(862);
			match(OpenBrace);
			setState(863);
			identifierName();
			setState(868);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(864);
				match(Comma);
				setState(865);
				identifierName();
				}
				}
				setState(870);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(871);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExportStatementContext extends ParserRuleContext {
		public TerminalNode Export() { return getToken(TypeScriptParser.Export, 0); }
		public FromBlockContext fromBlock() {
			return getRuleContext(FromBlockContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Default() { return getToken(TypeScriptParser.Default, 0); }
		public ExportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterExportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitExportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitExportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportStatementContext exportStatement() throws RecognitionException {
		ExportStatementContext _localctx = new ExportStatementContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_exportStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(873);
			match(Export);
			setState(875);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(874);
				match(Default);
				}
				break;
			}
			setState(879);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(877);
				fromBlock();
				}
				break;
			case 2:
				{
				setState(878);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableStatementContext extends ParserRuleContext {
		public BindingPatternContext bindingPattern() {
			return getRuleContext(BindingPatternContext.class,0);
		}
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public VarModifierContext varModifier() {
			return getRuleContext(VarModifierContext.class,0);
		}
		public TerminalNode ReadOnly() { return getToken(TypeScriptParser.ReadOnly, 0); }
		public TerminalNode Declare() { return getToken(TypeScriptParser.Declare, 0); }
		public VariableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterVariableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitVariableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitVariableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableStatementContext variableStatement() throws RecognitionException {
		VariableStatementContext _localctx = new VariableStatementContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_variableStatement);
		int _la;
		try {
			setState(910);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(881);
				bindingPattern();
				setState(883);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(882);
					typeAnnotation();
					}
				}

				setState(885);
				initializer();
				setState(887);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(886);
					match(SemiColon);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(890);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)))) != 0)) {
					{
					setState(889);
					accessibilityModifier();
					}
				}

				setState(893);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Var - 70)) | (1L << (Const - 70)) | (1L << (Let - 70)))) != 0)) {
					{
					setState(892);
					varModifier();
					}
				}

				setState(896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ReadOnly) {
					{
					setState(895);
					match(ReadOnly);
					}
				}

				setState(898);
				variableDeclarationList();
				setState(900);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(899);
					match(SemiColon);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(902);
				match(Declare);
				setState(904);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Var - 70)) | (1L << (Const - 70)) | (1L << (Let - 70)))) != 0)) {
					{
					setState(903);
					varModifier();
					}
				}

				setState(906);
				variableDeclarationList();
				setState(908);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(907);
					match(SemiColon);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationListContext extends ParserRuleContext {
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public VariableDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterVariableDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitVariableDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitVariableDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationListContext variableDeclarationList() throws RecognitionException {
		VariableDeclarationListContext _localctx = new VariableDeclarationListContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_variableDeclarationList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(912);
			variableDeclaration();
			setState(917);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(913);
					match(Comma);
					setState(914);
					variableDeclaration();
					}
					} 
				}
				setState(919);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public IdentifierOrKeyWordContext identifierOrKeyWord() {
			return getRuleContext(IdentifierOrKeyWordContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(923);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TypeAlias:
			case Require:
			case Identifier:
				{
				setState(920);
				identifierOrKeyWord();
				}
				break;
			case OpenBracket:
				{
				setState(921);
				arrayLiteral();
				}
				break;
			case OpenBrace:
				{
				setState(922);
				objectLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(926);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				setState(925);
				typeAnnotation();
				}
				break;
			}
			setState(929);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(928);
				singleExpression(0);
				}
				break;
			}
			setState(936);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				{
				setState(931);
				match(Assign);
				setState(933);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(932);
					typeParameters();
					}
					break;
				}
				setState(935);
				singleExpression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(938);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(940);
			if (!(this.notOpenBraceAndNotFunction())) throw new FailedPredicateException(this, "this.notOpenBraceAndNotFunction()");
			setState(941);
			expressionSequence();
			setState(943);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(942);
				match(SemiColon);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(TypeScriptParser.If, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(TypeScriptParser.Else, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(945);
			match(If);
			setState(946);
			match(OpenParen);
			setState(947);
			expressionSequence();
			setState(948);
			match(CloseParen);
			setState(949);
			statement();
			setState(952);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(950);
				match(Else);
				setState(951);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	 
		public IterationStatementContext() { }
		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DoStatementContext extends IterationStatementContext {
		public TerminalNode Do() { return getToken(TypeScriptParser.Do, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode While() { return getToken(TypeScriptParser.While, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public DoStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDoStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForVarStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(TypeScriptParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public VarModifierContext varModifier() {
			return getRuleContext(VarModifierContext.class,0);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public List<TerminalNode> SemiColon() { return getTokens(TypeScriptParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(TypeScriptParser.SemiColon, i);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionSequenceContext> expressionSequence() {
			return getRuleContexts(ExpressionSequenceContext.class);
		}
		public ExpressionSequenceContext expressionSequence(int i) {
			return getRuleContext(ExpressionSequenceContext.class,i);
		}
		public ForVarStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterForVarStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitForVarStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitForVarStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForVarInStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(TypeScriptParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public VarModifierContext varModifier() {
			return getRuleContext(VarModifierContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode In() { return getToken(TypeScriptParser.In, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ForVarInStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterForVarInStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitForVarInStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitForVarInStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatementContext extends IterationStatementContext {
		public TerminalNode While() { return getToken(TypeScriptParser.While, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(TypeScriptParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public List<TerminalNode> SemiColon() { return getTokens(TypeScriptParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(TypeScriptParser.SemiColon, i);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionSequenceContext> expressionSequence() {
			return getRuleContexts(ExpressionSequenceContext.class);
		}
		public ExpressionSequenceContext expressionSequence(int i) {
			return getRuleContext(ExpressionSequenceContext.class,i);
		}
		public ForStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForInStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(TypeScriptParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode In() { return getToken(TypeScriptParser.In, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ForInStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterForInStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitForInStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitForInStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_iterationStatement);
		int _la;
		try {
			setState(1023);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				_localctx = new DoStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(954);
				match(Do);
				setState(955);
				statement();
				setState(956);
				match(While);
				setState(957);
				match(OpenParen);
				setState(958);
				expressionSequence();
				setState(959);
				match(CloseParen);
				setState(960);
				eos();
				}
				break;
			case 2:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(962);
				match(While);
				setState(963);
				match(OpenParen);
				setState(964);
				expressionSequence();
				setState(965);
				match(CloseParen);
				setState(966);
				statement();
				}
				break;
			case 3:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(968);
				match(For);
				setState(969);
				match(OpenParen);
				setState(971);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
					{
					setState(970);
					expressionSequence();
					}
				}

				setState(973);
				match(SemiColon);
				setState(975);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
					{
					setState(974);
					expressionSequence();
					}
				}

				setState(977);
				match(SemiColon);
				setState(979);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
					{
					setState(978);
					expressionSequence();
					}
				}

				setState(981);
				match(CloseParen);
				setState(982);
				statement();
				}
				break;
			case 4:
				_localctx = new ForVarStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(983);
				match(For);
				setState(984);
				match(OpenParen);
				setState(985);
				varModifier();
				setState(986);
				variableDeclarationList();
				setState(987);
				match(SemiColon);
				setState(989);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
					{
					setState(988);
					expressionSequence();
					}
				}

				setState(991);
				match(SemiColon);
				setState(993);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
					{
					setState(992);
					expressionSequence();
					}
				}

				setState(995);
				match(CloseParen);
				setState(996);
				statement();
				}
				break;
			case 5:
				_localctx = new ForInStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(998);
				match(For);
				setState(999);
				match(OpenParen);
				setState(1000);
				singleExpression(0);
				setState(1004);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case In:
					{
					setState(1001);
					match(In);
					}
					break;
				case Identifier:
					{
					setState(1002);
					match(Identifier);
					setState(1003);
					if (!(this.p("of"))) throw new FailedPredicateException(this, "this.p(\"of\")");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1006);
				expressionSequence();
				setState(1007);
				match(CloseParen);
				setState(1008);
				statement();
				}
				break;
			case 6:
				_localctx = new ForVarInStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1010);
				match(For);
				setState(1011);
				match(OpenParen);
				setState(1012);
				varModifier();
				setState(1013);
				variableDeclaration();
				setState(1017);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case In:
					{
					setState(1014);
					match(In);
					}
					break;
				case Identifier:
					{
					setState(1015);
					match(Identifier);
					setState(1016);
					if (!(this.p("of"))) throw new FailedPredicateException(this, "this.p(\"of\")");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1019);
				expressionSequence();
				setState(1020);
				match(CloseParen);
				setState(1021);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarModifierContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(TypeScriptParser.Var, 0); }
		public TerminalNode Let() { return getToken(TypeScriptParser.Let, 0); }
		public TerminalNode Const() { return getToken(TypeScriptParser.Const, 0); }
		public VarModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterVarModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitVarModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitVarModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarModifierContext varModifier() throws RecognitionException {
		VarModifierContext _localctx = new VarModifierContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_varModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1025);
			_la = _input.LA(1);
			if ( !(((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Var - 70)) | (1L << (Const - 70)) | (1L << (Let - 70)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(TypeScriptParser.Continue, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1027);
			match(Continue);
			setState(1030);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
			case 1:
				{
				setState(1028);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(1029);
				match(Identifier);
				}
				break;
			}
			setState(1032);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(TypeScriptParser.Break, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1034);
			match(Break);
			setState(1037);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				{
				setState(1035);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(1036);
				match(Identifier);
				}
				break;
			}
			setState(1039);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(TypeScriptParser.Return, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1041);
			match(Return);
			setState(1044);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				{
				setState(1042);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(1043);
				expressionSequence();
				}
				break;
			}
			setState(1046);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YieldStatementContext extends ParserRuleContext {
		public TerminalNode Yield() { return getToken(TypeScriptParser.Yield, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public YieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterYieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitYieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitYieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YieldStatementContext yieldStatement() throws RecognitionException {
		YieldStatementContext _localctx = new YieldStatementContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_yieldStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1048);
			match(Yield);
			setState(1051);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				{
				setState(1049);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(1050);
				expressionSequence();
				}
				break;
			}
			setState(1053);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithStatementContext extends ParserRuleContext {
		public TerminalNode With() { return getToken(TypeScriptParser.With, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WithStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterWithStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitWithStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitWithStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithStatementContext withStatement() throws RecognitionException {
		WithStatementContext _localctx = new WithStatementContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_withStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1055);
			match(With);
			setState(1056);
			match(OpenParen);
			setState(1057);
			expressionSequence();
			setState(1058);
			match(CloseParen);
			setState(1059);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode Switch() { return getToken(TypeScriptParser.Switch, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public CaseBlockContext caseBlock() {
			return getRuleContext(CaseBlockContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_switchStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1061);
			match(Switch);
			setState(1062);
			match(OpenParen);
			setState(1063);
			expressionSequence();
			setState(1064);
			match(CloseParen);
			setState(1065);
			caseBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseBlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<CaseClausesContext> caseClauses() {
			return getRuleContexts(CaseClausesContext.class);
		}
		public CaseClausesContext caseClauses(int i) {
			return getRuleContext(CaseClausesContext.class,i);
		}
		public DefaultClauseContext defaultClause() {
			return getRuleContext(DefaultClauseContext.class,0);
		}
		public CaseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCaseBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCaseBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCaseBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseBlockContext caseBlock() throws RecognitionException {
		CaseBlockContext _localctx = new CaseBlockContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_caseBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1067);
			match(OpenBrace);
			setState(1069);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Case) {
				{
				setState(1068);
				caseClauses();
				}
			}

			setState(1075);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Default) {
				{
				setState(1071);
				defaultClause();
				setState(1073);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Case) {
					{
					setState(1072);
					caseClauses();
					}
				}

				}
			}

			setState(1077);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseClausesContext extends ParserRuleContext {
		public List<CaseClauseContext> caseClause() {
			return getRuleContexts(CaseClauseContext.class);
		}
		public CaseClauseContext caseClause(int i) {
			return getRuleContext(CaseClauseContext.class,i);
		}
		public CaseClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCaseClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCaseClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCaseClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClausesContext caseClauses() throws RecognitionException {
		CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_caseClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1080); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1079);
				caseClause();
				}
				}
				setState(1082); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Case );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseClauseContext extends ParserRuleContext {
		public TerminalNode Case() { return getToken(TypeScriptParser.Case, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCaseClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCaseClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_caseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1084);
			match(Case);
			setState(1085);
			expressionSequence();
			setState(1086);
			match(Colon);
			setState(1088);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				{
				setState(1087);
				statementList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultClauseContext extends ParserRuleContext {
		public TerminalNode Default() { return getToken(TypeScriptParser.Default, 0); }
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public DefaultClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDefaultClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDefaultClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDefaultClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultClauseContext defaultClause() throws RecognitionException {
		DefaultClauseContext _localctx = new DefaultClauseContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_defaultClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1090);
			match(Default);
			setState(1091);
			match(Colon);
			setState(1093);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,126,_ctx) ) {
			case 1:
				{
				setState(1092);
				statementList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelledStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public LabelledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLabelledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLabelledStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLabelledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelledStatementContext labelledStatement() throws RecognitionException {
		LabelledStatementContext _localctx = new LabelledStatementContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_labelledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1095);
			match(Identifier);
			setState(1096);
			match(Colon);
			setState(1097);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public TerminalNode Throw() { return getToken(TypeScriptParser.Throw, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1099);
			match(Throw);
			setState(1100);
			if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
			setState(1101);
			expressionSequence();
			setState(1102);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryStatementContext extends ParserRuleContext {
		public TerminalNode Try() { return getToken(TypeScriptParser.Try, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CatchProductionContext catchProduction() {
			return getRuleContext(CatchProductionContext.class,0);
		}
		public FinallyProductionContext finallyProduction() {
			return getRuleContext(FinallyProductionContext.class,0);
		}
		public TryStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTryStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTryStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryStatementContext tryStatement() throws RecognitionException {
		TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_tryStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1104);
			match(Try);
			setState(1105);
			block();
			setState(1111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Catch:
				{
				setState(1106);
				catchProduction();
				setState(1108);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
				case 1:
					{
					setState(1107);
					finallyProduction();
					}
					break;
				}
				}
				break;
			case Finally:
				{
				setState(1110);
				finallyProduction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchProductionContext extends ParserRuleContext {
		public TerminalNode Catch() { return getToken(TypeScriptParser.Catch, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CatchProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCatchProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCatchProduction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCatchProduction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchProductionContext catchProduction() throws RecognitionException {
		CatchProductionContext _localctx = new CatchProductionContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_catchProduction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1113);
			match(Catch);
			setState(1114);
			match(OpenParen);
			setState(1115);
			match(Identifier);
			setState(1116);
			match(CloseParen);
			setState(1117);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyProductionContext extends ParserRuleContext {
		public TerminalNode Finally() { return getToken(TypeScriptParser.Finally, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFinallyProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFinallyProduction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFinallyProduction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyProductionContext finallyProduction() throws RecognitionException {
		FinallyProductionContext _localctx = new FinallyProductionContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_finallyProduction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1119);
			match(Finally);
			setState(1120);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DebuggerStatementContext extends ParserRuleContext {
		public TerminalNode Debugger() { return getToken(TypeScriptParser.Debugger, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public DebuggerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_debuggerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDebuggerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDebuggerStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDebuggerStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DebuggerStatementContext debuggerStatement() throws RecognitionException {
		DebuggerStatementContext _localctx = new DebuggerStatementContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_debuggerStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1122);
			match(Debugger);
			setState(1123);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode Function() { return getToken(TypeScriptParser.Function, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public CallSignatureContext callSignature() {
			return getRuleContext(CallSignatureContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_functionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1125);
			match(Function);
			setState(1126);
			match(Identifier);
			setState(1127);
			callSignature();
			setState(1133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBrace:
				{
				{
				setState(1128);
				match(OpenBrace);
				setState(1129);
				functionBody();
				setState(1130);
				match(CloseBrace);
				}
				}
				break;
			case SemiColon:
				{
				setState(1132);
				match(SemiColon);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(TypeScriptParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ClassHeritageContext classHeritage() {
			return getRuleContext(ClassHeritageContext.class,0);
		}
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public TerminalNode Abstract() { return getToken(TypeScriptParser.Abstract, 0); }
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Abstract) {
				{
				setState(1135);
				match(Abstract);
				}
			}

			setState(1138);
			match(Class);
			setState(1139);
			match(Identifier);
			setState(1141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LessThan) {
				{
				setState(1140);
				typeParameters();
				}
			}

			setState(1143);
			classHeritage();
			setState(1144);
			classTail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassHeritageContext extends ParserRuleContext {
		public ClassExtendsClauseContext classExtendsClause() {
			return getRuleContext(ClassExtendsClauseContext.class,0);
		}
		public ImplementsClauseContext implementsClause() {
			return getRuleContext(ImplementsClauseContext.class,0);
		}
		public ClassHeritageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classHeritage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassHeritage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassHeritage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassHeritage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassHeritageContext classHeritage() throws RecognitionException {
		ClassHeritageContext _localctx = new ClassHeritageContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_classHeritage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Extends) {
				{
				setState(1146);
				classExtendsClause();
				}
			}

			setState(1150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Implements) {
				{
				setState(1149);
				implementsClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTailContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<ClassElementContext> classElement() {
			return getRuleContexts(ClassElementContext.class);
		}
		public ClassElementContext classElement(int i) {
			return getRuleContext(ClassElementContext.class,i);
		}
		public ClassTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTailContext classTail() throws RecognitionException {
		ClassTailContext _localctx = new ClassTailContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_classTail);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1152);
			match(OpenBrace);
			setState(1156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1153);
					classElement();
					}
					} 
				}
				setState(1158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
			}
			setState(1159);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassExtendsClauseContext extends ParserRuleContext {
		public TerminalNode Extends() { return getToken(TypeScriptParser.Extends, 0); }
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public ClassExtendsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classExtendsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassExtendsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassExtendsClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassExtendsClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassExtendsClauseContext classExtendsClause() throws RecognitionException {
		ClassExtendsClauseContext _localctx = new ClassExtendsClauseContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_classExtendsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1161);
			match(Extends);
			setState(1162);
			typeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplementsClauseContext extends ParserRuleContext {
		public TerminalNode Implements() { return getToken(TypeScriptParser.Implements, 0); }
		public ClassOrInterfaceTypeListContext classOrInterfaceTypeList() {
			return getRuleContext(ClassOrInterfaceTypeListContext.class,0);
		}
		public ImplementsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implementsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterImplementsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitImplementsClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitImplementsClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplementsClauseContext implementsClause() throws RecognitionException {
		ImplementsClauseContext _localctx = new ImplementsClauseContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_implementsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1164);
			match(Implements);
			setState(1165);
			classOrInterfaceTypeList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassElementContext extends ParserRuleContext {
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public PropertyMemberDeclarationContext propertyMemberDeclaration() {
			return getRuleContext(PropertyMemberDeclarationContext.class,0);
		}
		public DecoratorListContext decoratorList() {
			return getRuleContext(DecoratorListContext.class,0);
		}
		public IndexMemberDeclarationContext indexMemberDeclaration() {
			return getRuleContext(IndexMemberDeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ClassElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassElementContext classElement() throws RecognitionException {
		ClassElementContext _localctx = new ClassElementContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_classElement);
		int _la;
		try {
			setState(1174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1167);
				constructorDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==At) {
					{
					setState(1168);
					decoratorList();
					}
				}

				setState(1171);
				propertyMemberDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1172);
				indexMemberDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1173);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyMemberDeclarationContext extends ParserRuleContext {
		public PropertyMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyMemberDeclaration; }
	 
		public PropertyMemberDeclarationContext() { }
		public void copyFrom(PropertyMemberDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PropertyDeclarationExpressionContext extends PropertyMemberDeclarationContext {
		public PropertyMemberBaseContext propertyMemberBase() {
			return getRuleContext(PropertyMemberBaseContext.class,0);
		}
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public PropertyDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyDeclarationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyDeclarationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyDeclarationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodDeclarationExpressionContext extends PropertyMemberDeclarationContext {
		public PropertyMemberBaseContext propertyMemberBase() {
			return getRuleContext(PropertyMemberBaseContext.class,0);
		}
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public CallSignatureContext callSignature() {
			return getRuleContext(CallSignatureContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public MethodDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMethodDeclarationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMethodDeclarationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMethodDeclarationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetterSetterDeclarationExpressionContext extends PropertyMemberDeclarationContext {
		public PropertyMemberBaseContext propertyMemberBase() {
			return getRuleContext(PropertyMemberBaseContext.class,0);
		}
		public GetAccessorContext getAccessor() {
			return getRuleContext(GetAccessorContext.class,0);
		}
		public SetAccessorContext setAccessor() {
			return getRuleContext(SetAccessorContext.class,0);
		}
		public GetterSetterDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGetterSetterDeclarationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGetterSetterDeclarationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGetterSetterDeclarationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AbstractMemberDeclarationContext extends PropertyMemberDeclarationContext {
		public AbstractDeclarationContext abstractDeclaration() {
			return getRuleContext(AbstractDeclarationContext.class,0);
		}
		public AbstractMemberDeclarationContext(PropertyMemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAbstractMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAbstractMemberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAbstractMemberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyMemberDeclarationContext propertyMemberDeclaration() throws RecognitionException {
		PropertyMemberDeclarationContext _localctx = new PropertyMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_propertyMemberDeclaration);
		int _la;
		try {
			setState(1205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
			case 1:
				_localctx = new PropertyDeclarationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1176);
				propertyMemberBase();
				setState(1177);
				propertyName();
				setState(1179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QuestionMark) {
					{
					setState(1178);
					match(QuestionMark);
					}
				}

				setState(1182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(1181);
					typeAnnotation();
					}
				}

				setState(1185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(1184);
					initializer();
					}
				}

				setState(1187);
				match(SemiColon);
				}
				break;
			case 2:
				_localctx = new MethodDeclarationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1189);
				propertyMemberBase();
				setState(1190);
				propertyName();
				setState(1191);
				callSignature();
				setState(1197);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OpenBrace:
					{
					{
					setState(1192);
					match(OpenBrace);
					setState(1193);
					functionBody();
					setState(1194);
					match(CloseBrace);
					}
					}
					break;
				case SemiColon:
					{
					setState(1196);
					match(SemiColon);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new GetterSetterDeclarationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1199);
				propertyMemberBase();
				setState(1202);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,141,_ctx) ) {
				case 1:
					{
					setState(1200);
					getAccessor();
					}
					break;
				case 2:
					{
					setState(1201);
					setAccessor();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new AbstractMemberDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1204);
				abstractDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyMemberBaseContext extends ParserRuleContext {
		public TerminalNode Async() { return getToken(TypeScriptParser.Async, 0); }
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public TerminalNode Static() { return getToken(TypeScriptParser.Static, 0); }
		public TerminalNode ReadOnly() { return getToken(TypeScriptParser.ReadOnly, 0); }
		public PropertyMemberBaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyMemberBase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyMemberBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyMemberBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyMemberBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyMemberBaseContext propertyMemberBase() throws RecognitionException {
		PropertyMemberBaseContext _localctx = new PropertyMemberBaseContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_propertyMemberBase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,143,_ctx) ) {
			case 1:
				{
				setState(1207);
				match(Async);
				}
				break;
			}
			setState(1211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				{
				setState(1210);
				accessibilityModifier();
				}
				break;
			}
			setState(1214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,145,_ctx) ) {
			case 1:
				{
				setState(1213);
				match(Static);
				}
				break;
			}
			setState(1217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
			case 1:
				{
				setState(1216);
				match(ReadOnly);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexMemberDeclarationContext extends ParserRuleContext {
		public IndexSignatureContext indexSignature() {
			return getRuleContext(IndexSignatureContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public IndexMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIndexMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIndexMemberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIndexMemberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexMemberDeclarationContext indexMemberDeclaration() throws RecognitionException {
		IndexMemberDeclarationContext _localctx = new IndexMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_indexMemberDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1219);
			indexSignature();
			setState(1220);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneratorMethodContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TerminalNode Multiply() { return getToken(TypeScriptParser.Multiply, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public GeneratorMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatorMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorMethodContext generatorMethod() throws RecognitionException {
		GeneratorMethodContext _localctx = new GeneratorMethodContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_generatorMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Multiply) {
				{
				setState(1222);
				match(Multiply);
				}
			}

			setState(1225);
			match(Identifier);
			setState(1226);
			match(OpenParen);
			setState(1228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
				{
				setState(1227);
				formalParameterList();
				}
			}

			setState(1230);
			match(CloseParen);
			setState(1231);
			match(OpenBrace);
			setState(1232);
			functionBody();
			setState(1233);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneratorFunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode Function() { return getToken(TypeScriptParser.Function, 0); }
		public TerminalNode Multiply() { return getToken(TypeScriptParser.Multiply, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public GeneratorFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatorFunctionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorFunctionDeclarationContext generatorFunctionDeclaration() throws RecognitionException {
		GeneratorFunctionDeclarationContext _localctx = new GeneratorFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_generatorFunctionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1235);
			match(Function);
			setState(1236);
			match(Multiply);
			setState(1238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1237);
				match(Identifier);
				}
			}

			setState(1240);
			match(OpenParen);
			setState(1242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
				{
				setState(1241);
				formalParameterList();
				}
			}

			setState(1244);
			match(CloseParen);
			setState(1245);
			match(OpenBrace);
			setState(1246);
			functionBody();
			setState(1247);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneratorBlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public List<GeneratorDefinitionContext> generatorDefinition() {
			return getRuleContexts(GeneratorDefinitionContext.class);
		}
		public GeneratorDefinitionContext generatorDefinition(int i) {
			return getRuleContext(GeneratorDefinitionContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public GeneratorBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatorBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorBlockContext generatorBlock() throws RecognitionException {
		GeneratorBlockContext _localctx = new GeneratorBlockContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_generatorBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1249);
			match(OpenBrace);
			setState(1250);
			generatorDefinition();
			setState(1255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,151,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1251);
					match(Comma);
					setState(1252);
					generatorDefinition();
					}
					} 
				}
				setState(1257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,151,_ctx);
			}
			setState(1259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(1258);
				match(Comma);
				}
			}

			setState(1261);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneratorDefinitionContext extends ParserRuleContext {
		public TerminalNode Multiply() { return getToken(TypeScriptParser.Multiply, 0); }
		public IteratorDefinitionContext iteratorDefinition() {
			return getRuleContext(IteratorDefinitionContext.class,0);
		}
		public GeneratorDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generatorDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorDefinitionContext generatorDefinition() throws RecognitionException {
		GeneratorDefinitionContext _localctx = new GeneratorDefinitionContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_generatorDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1263);
			match(Multiply);
			setState(1264);
			iteratorDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IteratorBlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public List<IteratorDefinitionContext> iteratorDefinition() {
			return getRuleContexts(IteratorDefinitionContext.class);
		}
		public IteratorDefinitionContext iteratorDefinition(int i) {
			return getRuleContext(IteratorDefinitionContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public IteratorBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteratorBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIteratorBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIteratorBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIteratorBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteratorBlockContext iteratorBlock() throws RecognitionException {
		IteratorBlockContext _localctx = new IteratorBlockContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_iteratorBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1266);
			match(OpenBrace);
			setState(1267);
			iteratorDefinition();
			setState(1272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1268);
					match(Comma);
					setState(1269);
					iteratorDefinition();
					}
					} 
				}
				setState(1274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
			}
			setState(1276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(1275);
				match(Comma);
				}
			}

			setState(1278);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IteratorDefinitionContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public IteratorDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteratorDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIteratorDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIteratorDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIteratorDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteratorDefinitionContext iteratorDefinition() throws RecognitionException {
		IteratorDefinitionContext _localctx = new IteratorDefinitionContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_iteratorDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1280);
			match(OpenBracket);
			setState(1281);
			singleExpression(0);
			setState(1282);
			match(CloseBracket);
			setState(1283);
			match(OpenParen);
			setState(1285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
				{
				setState(1284);
				formalParameterList();
				}
			}

			setState(1287);
			match(CloseParen);
			setState(1288);
			match(OpenBrace);
			setState(1289);
			functionBody();
			setState(1290);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterArgContext> formalParameterArg() {
			return getRuleContexts(FormalParameterArgContext.class);
		}
		public FormalParameterArgContext formalParameterArg(int i) {
			return getRuleContext(FormalParameterArgContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public LastFormalParameterArgContext lastFormalParameterArg() {
			return getRuleContext(LastFormalParameterArgContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFormalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(1311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Private:
			case Public:
			case Protected:
			case TypeAlias:
			case Require:
			case At:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1292);
				formalParameterArg();
				setState(1297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1293);
						match(Comma);
						setState(1294);
						formalParameterArg();
						}
						} 
					}
					setState(1299);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
				}
				setState(1302);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(1300);
					match(Comma);
					setState(1301);
					lastFormalParameterArg();
					}
				}

				}
				break;
			case Ellipsis:
				enterOuterAlt(_localctx, 2);
				{
				setState(1304);
				lastFormalParameterArg();
				}
				break;
			case OpenBracket:
				enterOuterAlt(_localctx, 3);
				{
				setState(1305);
				arrayLiteral();
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 4);
				{
				setState(1306);
				objectLiteral();
				setState(1309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(1307);
					match(Colon);
					setState(1308);
					formalParameterList();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterArgContext extends ParserRuleContext {
		public IdentifierOrKeyWordContext identifierOrKeyWord() {
			return getRuleContext(IdentifierOrKeyWordContext.class,0);
		}
		public DecoratorContext decorator() {
			return getRuleContext(DecoratorContext.class,0);
		}
		public AccessibilityModifierContext accessibilityModifier() {
			return getRuleContext(AccessibilityModifierContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public FormalParameterArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFormalParameterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFormalParameterArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFormalParameterArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterArgContext formalParameterArg() throws RecognitionException {
		FormalParameterArgContext _localctx = new FormalParameterArgContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_formalParameterArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==At) {
				{
				setState(1313);
				decorator();
				}
			}

			setState(1317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)))) != 0)) {
				{
				setState(1316);
				accessibilityModifier();
				}
			}

			setState(1319);
			identifierOrKeyWord();
			setState(1321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QuestionMark) {
				{
				setState(1320);
				match(QuestionMark);
				}
			}

			setState(1324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(1323);
				typeAnnotation();
				}
			}

			setState(1328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(1326);
				match(Assign);
				setState(1327);
				singleExpression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LastFormalParameterArgContext extends ParserRuleContext {
		public TerminalNode Ellipsis() { return getToken(TypeScriptParser.Ellipsis, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public LastFormalParameterArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameterArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLastFormalParameterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLastFormalParameterArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLastFormalParameterArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LastFormalParameterArgContext lastFormalParameterArg() throws RecognitionException {
		LastFormalParameterArgContext _localctx = new LastFormalParameterArgContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_lastFormalParameterArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1330);
			match(Ellipsis);
			setState(1331);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public SourceElementsContext sourceElements() {
			return getRuleContext(SourceElementsContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1334);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,165,_ctx) ) {
			case 1:
				{
				setState(1333);
				sourceElements();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceElementsContext extends ParserRuleContext {
		public List<SourceElementContext> sourceElement() {
			return getRuleContexts(SourceElementContext.class);
		}
		public SourceElementContext sourceElement(int i) {
			return getRuleContext(SourceElementContext.class,i);
		}
		public SourceElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSourceElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSourceElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSourceElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceElementsContext sourceElements() throws RecognitionException {
		SourceElementsContext _localctx = new SourceElementsContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_sourceElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1337); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1336);
					sourceElement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1339); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,166,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(1341);
			match(OpenBracket);
			setState(1343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
				{
				setState(1342);
				elementList();
				}
			}

			setState(1345);
			match(CloseBracket);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementListContext extends ParserRuleContext {
		public List<ArrayElementContext> arrayElement() {
			return getRuleContexts(ArrayElementContext.class);
		}
		public ArrayElementContext arrayElement(int i) {
			return getRuleContext(ArrayElementContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitElementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_elementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1347);
			arrayElement();
			setState(1356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1349); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1348);
					match(Comma);
					}
					}
					setState(1351); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==Comma );
				setState(1353);
				arrayElement();
				}
				}
				setState(1358);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElementContext extends ParserRuleContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Ellipsis() { return getToken(TypeScriptParser.Ellipsis, 0); }
		public TerminalNode Comma() { return getToken(TypeScriptParser.Comma, 0); }
		public ArrayElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrayElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrayElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrayElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElementContext arrayElement() throws RecognitionException {
		ArrayElementContext _localctx = new ArrayElementContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_arrayElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(1359);
				match(Ellipsis);
				}
			}

			setState(1364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,171,_ctx) ) {
			case 1:
				{
				setState(1362);
				singleExpression(0);
				}
				break;
			case 2:
				{
				setState(1363);
				match(Identifier);
				}
				break;
			}
			setState(1367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
			case 1:
				{
				setState(1366);
				match(Comma);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectLiteralContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public List<PropertyAssignmentContext> propertyAssignment() {
			return getRuleContexts(PropertyAssignmentContext.class);
		}
		public PropertyAssignmentContext propertyAssignment(int i) {
			return getRuleContext(PropertyAssignmentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitObjectLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitObjectLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectLiteralContext objectLiteral() throws RecognitionException {
		ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_objectLiteral);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1369);
			match(OpenBrace);
			setState(1378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << Ellipsis) | (1L << Multiply) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)))) != 0)) {
				{
				setState(1370);
				propertyAssignment();
				setState(1375);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,173,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1371);
						match(Comma);
						setState(1372);
						propertyAssignment();
						}
						} 
					}
					setState(1377);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,173,_ctx);
				}
				}
			}

			setState(1381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(1380);
				match(Comma);
				}
			}

			setState(1383);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyAssignmentContext extends ParserRuleContext {
		public PropertyAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyAssignment; }
	 
		public PropertyAssignmentContext() { }
		public void copyFrom(PropertyAssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PropertyExpressionAssignmentContext extends PropertyAssignmentContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public PropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComputedPropertyExpressionAssignmentContext extends PropertyAssignmentContext {
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public ComputedPropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterComputedPropertyExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitComputedPropertyExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitComputedPropertyExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PropertyShorthandContext extends PropertyAssignmentContext {
		public IdentifierOrKeyWordContext identifierOrKeyWord() {
			return getRuleContext(IdentifierOrKeyWordContext.class,0);
		}
		public PropertyShorthandContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyShorthand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyShorthand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyShorthand(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PropertySetterContext extends PropertyAssignmentContext {
		public SetAccessorContext setAccessor() {
			return getRuleContext(SetAccessorContext.class,0);
		}
		public PropertySetterContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertySetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertySetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertySetter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PropertyGetterContext extends PropertyAssignmentContext {
		public GetAccessorContext getAccessor() {
			return getRuleContext(GetAccessorContext.class,0);
		}
		public PropertyGetterContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyGetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyGetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyGetter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RestParameterInObjectContext extends PropertyAssignmentContext {
		public RestParameterContext restParameter() {
			return getRuleContext(RestParameterContext.class,0);
		}
		public RestParameterInObjectContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRestParameterInObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRestParameterInObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRestParameterInObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodPropertyContext extends PropertyAssignmentContext {
		public GeneratorMethodContext generatorMethod() {
			return getRuleContext(GeneratorMethodContext.class,0);
		}
		public MethodPropertyContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMethodProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMethodProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMethodProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyAssignmentContext propertyAssignment() throws RecognitionException {
		PropertyAssignmentContext _localctx = new PropertyAssignmentContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_propertyAssignment);
		int _la;
		try {
			setState(1400);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
			case 1:
				_localctx = new PropertyExpressionAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1385);
				propertyName();
				setState(1386);
				_la = _input.LA(1);
				if ( !(_la==Assign || _la==Colon) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1387);
				singleExpression(0);
				}
				break;
			case 2:
				_localctx = new ComputedPropertyExpressionAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1389);
				match(OpenBracket);
				setState(1390);
				singleExpression(0);
				setState(1391);
				match(CloseBracket);
				setState(1392);
				match(Colon);
				setState(1393);
				singleExpression(0);
				}
				break;
			case 3:
				_localctx = new PropertyGetterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1395);
				getAccessor();
				}
				break;
			case 4:
				_localctx = new PropertySetterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1396);
				setAccessor();
				}
				break;
			case 5:
				_localctx = new MethodPropertyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1397);
				generatorMethod();
				}
				break;
			case 6:
				_localctx = new PropertyShorthandContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1398);
				identifierOrKeyWord();
				}
				break;
			case 7:
				_localctx = new RestParameterInObjectContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(1399);
				restParameter();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GetAccessorContext extends ParserRuleContext {
		public GetterContext getter() {
			return getRuleContext(GetterContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public GetAccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getAccessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGetAccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGetAccessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGetAccessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetAccessorContext getAccessor() throws RecognitionException {
		GetAccessorContext _localctx = new GetAccessorContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_getAccessor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1402);
			getter();
			setState(1403);
			match(OpenParen);
			setState(1404);
			match(CloseParen);
			setState(1406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(1405);
				typeAnnotation();
				}
			}

			setState(1408);
			match(OpenBrace);
			setState(1409);
			functionBody();
			setState(1410);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetAccessorContext extends ParserRuleContext {
		public SetterContext setter() {
			return getRuleContext(SetterContext.class,0);
		}
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public BindingPatternContext bindingPattern() {
			return getRuleContext(BindingPatternContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public SetAccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setAccessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSetAccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSetAccessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSetAccessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetAccessorContext setAccessor() throws RecognitionException {
		SetAccessorContext _localctx = new SetAccessorContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_setAccessor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1412);
			setter();
			setState(1413);
			match(OpenParen);
			setState(1416);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(1414);
				match(Identifier);
				}
				break;
			case OpenBracket:
			case OpenBrace:
				{
				setState(1415);
				bindingPattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(1418);
				typeAnnotation();
				}
			}

			setState(1421);
			match(CloseParen);
			setState(1422);
			match(OpenBrace);
			setState(1423);
			functionBody();
			setState(1424);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyNameContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public PropertyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPropertyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPropertyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPropertyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyNameContext propertyName() throws RecognitionException {
		PropertyNameContext _localctx = new PropertyNameContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_propertyName);
		try {
			setState(1429);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case From:
			case ReadOnly:
			case Async:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Implements:
			case Let:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Yield:
			case String:
			case TypeAlias:
			case Get:
			case Set:
			case Require:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1426);
				identifierName();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1427);
				match(StringLiteral);
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(1428);
				numericLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(TypeScriptParser.Comma, 0); }
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1431);
			match(OpenParen);
			setState(1436);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RegularExpressionLiteral) | (1L << OpenBracket) | (1L << OpenParen) | (1L << OpenBrace) | (1L << Ellipsis) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Plus) | (1L << Minus) | (1L << BitNot) | (1L << Not) | (1L << LessThan) | (1L << NullLiteral) | (1L << BooleanLiteral) | (1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral) | (1L << Break))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (Do - 64)) | (1L << (Instanceof - 64)) | (1L << (Typeof - 64)) | (1L << (Case - 64)) | (1L << (Else - 64)) | (1L << (New - 64)) | (1L << (Var - 64)) | (1L << (Catch - 64)) | (1L << (Finally - 64)) | (1L << (Return - 64)) | (1L << (Void - 64)) | (1L << (Continue - 64)) | (1L << (For - 64)) | (1L << (Switch - 64)) | (1L << (While - 64)) | (1L << (Debugger - 64)) | (1L << (Function - 64)) | (1L << (This - 64)) | (1L << (With - 64)) | (1L << (Default - 64)) | (1L << (If - 64)) | (1L << (Throw - 64)) | (1L << (Delete - 64)) | (1L << (In - 64)) | (1L << (Try - 64)) | (1L << (From - 64)) | (1L << (ReadOnly - 64)) | (1L << (Async - 64)) | (1L << (Class - 64)) | (1L << (Enum - 64)) | (1L << (Extends - 64)) | (1L << (Super - 64)) | (1L << (Const - 64)) | (1L << (Export - 64)) | (1L << (Import - 64)) | (1L << (Implements - 64)) | (1L << (Let - 64)) | (1L << (Private - 64)) | (1L << (Public - 64)) | (1L << (Interface - 64)) | (1L << (Package - 64)) | (1L << (Protected - 64)) | (1L << (Static - 64)) | (1L << (Yield - 64)) | (1L << (String - 64)) | (1L << (TypeAlias - 64)) | (1L << (Get - 64)) | (1L << (Set - 64)) | (1L << (Require - 64)) | (1L << (Identifier - 64)) | (1L << (StringLiteral - 64)) | (1L << (TemplateStringLiteral - 64)))) != 0)) {
				{
				setState(1432);
				argumentList();
				setState(1434);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(1433);
					match(Comma);
					}
				}

				}
			}

			setState(1438);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_argumentList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1440);
			argument();
			setState(1445);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1441);
					match(Comma);
					setState(1442);
					argument();
					}
					} 
				}
				setState(1447);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode Ellipsis() { return getToken(TypeScriptParser.Ellipsis, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(1448);
				match(Ellipsis);
				}
			}

			setState(1453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,185,_ctx) ) {
			case 1:
				{
				setState(1451);
				singleExpression(0);
				}
				break;
			case 2:
				{
				setState(1452);
				match(Identifier);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionSequenceContext extends ParserRuleContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(TypeScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(TypeScriptParser.Comma, i);
		}
		public ExpressionSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterExpressionSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitExpressionSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitExpressionSequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionSequenceContext expressionSequence() throws RecognitionException {
		ExpressionSequenceContext _localctx = new ExpressionSequenceContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_expressionSequence);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1455);
			singleExpression(0);
			setState(1460);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,186,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1456);
					match(Comma);
					setState(1457);
					singleExpression(0);
					}
					} 
				}
				setState(1462);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,186,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionExpressionDeclarationContext extends ParserRuleContext {
		public TerminalNode Function() { return getToken(TypeScriptParser.Function, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public FunctionExpressionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionExpressionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFunctionExpressionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFunctionExpressionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFunctionExpressionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionExpressionDeclarationContext functionExpressionDeclaration() throws RecognitionException {
		FunctionExpressionDeclarationContext _localctx = new FunctionExpressionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_functionExpressionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1463);
			match(Function);
			setState(1465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1464);
				match(Identifier);
				}
			}

			setState(1467);
			match(OpenParen);
			setState(1469);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
				{
				setState(1468);
				formalParameterList();
				}
			}

			setState(1471);
			match(CloseParen);
			setState(1473);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(1472);
				typeAnnotation();
				}
			}

			setState(1475);
			match(OpenBrace);
			setState(1476);
			functionBody();
			setState(1477);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleExpressionContext extends ParserRuleContext {
		public SingleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleExpression; }
	 
		public SingleExpressionContext() { }
		public void copyFrom(SingleExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TemplateStringExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode TemplateStringLiteral() { return getToken(TypeScriptParser.TemplateStringLiteral, 0); }
		public TemplateStringExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTemplateStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTemplateStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTemplateStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TernaryExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode QuestionMark() { return getToken(TypeScriptParser.QuestionMark, 0); }
		public TerminalNode Colon() { return getToken(TypeScriptParser.Colon, 0); }
		public TernaryExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTernaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTernaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTernaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalAndExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode And() { return getToken(TypeScriptParser.And, 0); }
		public LogicalAndExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLogicalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GeneratorsExpressionContext extends SingleExpressionContext {
		public GeneratorBlockContext generatorBlock() {
			return getRuleContext(GeneratorBlockContext.class,0);
		}
		public GeneratorsExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreIncrementExpressionContext extends SingleExpressionContext {
		public TerminalNode PlusPlus() { return getToken(TypeScriptParser.PlusPlus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public PreIncrementExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPreIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPreIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPreIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectLiteralExpressionContext extends SingleExpressionContext {
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public ObjectLiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterObjectLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitObjectLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitObjectLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode In() { return getToken(TypeScriptParser.In, 0); }
		public InExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Or() { return getToken(TypeScriptParser.Or, 0); }
		public LogicalOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLogicalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GenericTypesContext extends SingleExpressionContext {
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public GenericTypesContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGenericTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGenericTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGenericTypes(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpressionContext extends SingleExpressionContext {
		public TerminalNode Not() { return getToken(TypeScriptParser.Not, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public NotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreDecreaseExpressionContext extends SingleExpressionContext {
		public TerminalNode MinusMinus() { return getToken(TypeScriptParser.MinusMinus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public PreDecreaseExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPreDecreaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPreDecreaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPreDecreaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArgumentsExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ArgumentsExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArgumentsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArgumentsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArgumentsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisExpressionContext extends SingleExpressionContext {
		public TerminalNode This() { return getToken(TypeScriptParser.This, 0); }
		public ThisExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterThisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitThisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitThisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExpressionContext extends SingleExpressionContext {
		public FunctionExpressionDeclarationContext functionExpressionDeclaration() {
			return getRuleContext(FunctionExpressionDeclarationContext.class,0);
		}
		public FunctionExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryMinusExpressionContext extends SingleExpressionContext {
		public TerminalNode Minus() { return getToken(TypeScriptParser.Minus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public UnaryMinusExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterUnaryMinusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitUnaryMinusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitUnaryMinusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(TypeScriptParser.Assign, 0); }
		public AssignmentExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAssignmentExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAssignmentExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostDecreaseExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode MinusMinus() { return getToken(TypeScriptParser.MinusMinus, 0); }
		public PostDecreaseExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPostDecreaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPostDecreaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPostDecreaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeofExpressionContext extends SingleExpressionContext {
		public TerminalNode Typeof() { return getToken(TypeScriptParser.Typeof, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TypeofExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterTypeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitTypeofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitTypeofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstanceofExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Instanceof() { return getToken(TypeScriptParser.Instanceof, 0); }
		public InstanceofExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterInstanceofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitInstanceofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitInstanceofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryPlusExpressionContext extends SingleExpressionContext {
		public TerminalNode Plus() { return getToken(TypeScriptParser.Plus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public UnaryPlusExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterUnaryPlusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitUnaryPlusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitUnaryPlusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeleteExpressionContext extends SingleExpressionContext {
		public TerminalNode Delete() { return getToken(TypeScriptParser.Delete, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public DeleteExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterDeleteExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitDeleteExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitDeleteExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GeneratorsFunctionExpressionContext extends SingleExpressionContext {
		public GeneratorFunctionDeclarationContext generatorFunctionDeclaration() {
			return getRuleContext(GeneratorFunctionDeclarationContext.class,0);
		}
		public GeneratorsFunctionExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGeneratorsFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGeneratorsFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGeneratorsFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrowFunctionExpressionContext extends SingleExpressionContext {
		public ArrowFunctionDeclarationContext arrowFunctionDeclaration() {
			return getRuleContext(ArrowFunctionDeclarationContext.class,0);
		}
		public ArrowFunctionExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrowFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrowFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrowFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IteratorsExpressionContext extends SingleExpressionContext {
		public IteratorBlockContext iteratorBlock() {
			return getRuleContext(IteratorBlockContext.class,0);
		}
		public IteratorsExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIteratorsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIteratorsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIteratorsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Equals_() { return getToken(TypeScriptParser.Equals_, 0); }
		public TerminalNode NotEquals() { return getToken(TypeScriptParser.NotEquals, 0); }
		public TerminalNode IdentityEquals() { return getToken(TypeScriptParser.IdentityEquals, 0); }
		public TerminalNode IdentityNotEquals() { return getToken(TypeScriptParser.IdentityNotEquals, 0); }
		public EqualityExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitXOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitXOr() { return getToken(TypeScriptParser.BitXOr, 0); }
		public BitXOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBitXOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBitXOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBitXOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CastAsExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode As() { return getToken(TypeScriptParser.As, 0); }
		public AsExpressionContext asExpression() {
			return getRuleContext(AsExpressionContext.class,0);
		}
		public CastAsExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterCastAsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitCastAsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitCastAsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuperExpressionContext extends SingleExpressionContext {
		public TerminalNode Super() { return getToken(TypeScriptParser.Super, 0); }
		public SuperExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSuperExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSuperExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSuperExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicativeExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Multiply() { return getToken(TypeScriptParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(TypeScriptParser.Divide, 0); }
		public TerminalNode Modulus() { return getToken(TypeScriptParser.Modulus, 0); }
		public MultiplicativeExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitShiftExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode LeftShiftArithmetic() { return getToken(TypeScriptParser.LeftShiftArithmetic, 0); }
		public TerminalNode RightShiftArithmetic() { return getToken(TypeScriptParser.RightShiftArithmetic, 0); }
		public TerminalNode RightShiftLogical() { return getToken(TypeScriptParser.RightShiftLogical, 0); }
		public BitShiftExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBitShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBitShiftExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBitShiftExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesizedExpressionContext extends SingleExpressionContext {
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public ParenthesizedExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditiveExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Plus() { return getToken(TypeScriptParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(TypeScriptParser.Minus, 0); }
		public AdditiveExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationalExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode LessThan() { return getToken(TypeScriptParser.LessThan, 0); }
		public TerminalNode MoreThan() { return getToken(TypeScriptParser.MoreThan, 0); }
		public TerminalNode LessThanEquals() { return getToken(TypeScriptParser.LessThanEquals, 0); }
		public TerminalNode GreaterThanEquals() { return getToken(TypeScriptParser.GreaterThanEquals, 0); }
		public RelationalExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostIncrementExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(TypeScriptParser.PlusPlus, 0); }
		public PostIncrementExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterPostIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitPostIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitPostIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class YieldExpressionContext extends SingleExpressionContext {
		public YieldStatementContext yieldStatement() {
			return getRuleContext(YieldStatementContext.class,0);
		}
		public YieldExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterYieldExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitYieldExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitYieldExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitNotExpressionContext extends SingleExpressionContext {
		public TerminalNode BitNot() { return getToken(TypeScriptParser.BitNot, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public BitNotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBitNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewExpressionContext extends SingleExpressionContext {
		public TerminalNode New() { return getToken(TypeScriptParser.New, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public NewExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExpressionContext extends SingleExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayLiteralExpressionContext extends SingleExpressionContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ArrayLiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrayLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrayLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrayLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDotExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(TypeScriptParser.Dot, 0); }
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public NestedTypeGenericContext nestedTypeGeneric() {
			return getRuleContext(NestedTypeGenericContext.class,0);
		}
		public MemberDotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMemberDotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMemberDotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMemberDotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassExpressionContext extends SingleExpressionContext {
		public TerminalNode Class() { return getToken(TypeScriptParser.Class, 0); }
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ClassExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterClassExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitClassExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitClassExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberIndexExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public MemberIndexExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterMemberIndexExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitMemberIndexExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitMemberIndexExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExpressionContext extends SingleExpressionContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public IdentifierExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitAnd() { return getToken(TypeScriptParser.BitAnd, 0); }
		public BitAndExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBitAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitOr() { return getToken(TypeScriptParser.BitOr, 0); }
		public BitOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterBitOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitBitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitBitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentOperatorExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public AssignmentOperatorExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAssignmentOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAssignmentOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAssignmentOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidExpressionContext extends SingleExpressionContext {
		public TerminalNode Void() { return getToken(TypeScriptParser.Void, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public VoidExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterVoidExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitVoidExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitVoidExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleExpressionContext singleExpression() throws RecognitionException {
		return singleExpression(0);
	}

	private SingleExpressionContext singleExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SingleExpressionContext _localctx = new SingleExpressionContext(_ctx, _parentState);
		SingleExpressionContext _prevctx = _localctx;
		int _startState = 256;
		enterRecursionRule(_localctx, 256, RULE_singleExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1534);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1480);
				functionExpressionDeclaration();
				}
				break;
			case 2:
				{
				_localctx = new ArrowFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1481);
				arrowFunctionDeclaration();
				}
				break;
			case 3:
				{
				_localctx = new ClassExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1482);
				match(Class);
				setState(1484);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(1483);
					match(Identifier);
					}
				}

				setState(1486);
				classTail();
				}
				break;
			case 4:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1487);
				match(New);
				setState(1488);
				singleExpression(0);
				setState(1490);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,191,_ctx) ) {
				case 1:
					{
					setState(1489);
					typeArguments();
					}
					break;
				}
				setState(1493);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
				case 1:
					{
					setState(1492);
					arguments();
					}
					break;
				}
				}
				break;
			case 5:
				{
				_localctx = new DeleteExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1495);
				match(Delete);
				setState(1496);
				singleExpression(38);
				}
				break;
			case 6:
				{
				_localctx = new VoidExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1497);
				match(Void);
				setState(1498);
				singleExpression(37);
				}
				break;
			case 7:
				{
				_localctx = new TypeofExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1499);
				match(Typeof);
				setState(1500);
				singleExpression(36);
				}
				break;
			case 8:
				{
				_localctx = new PreIncrementExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1501);
				match(PlusPlus);
				setState(1502);
				singleExpression(35);
				}
				break;
			case 9:
				{
				_localctx = new PreDecreaseExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1503);
				match(MinusMinus);
				setState(1504);
				singleExpression(34);
				}
				break;
			case 10:
				{
				_localctx = new UnaryPlusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1505);
				match(Plus);
				setState(1506);
				singleExpression(33);
				}
				break;
			case 11:
				{
				_localctx = new UnaryMinusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1507);
				match(Minus);
				setState(1508);
				singleExpression(32);
				}
				break;
			case 12:
				{
				_localctx = new BitNotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1509);
				match(BitNot);
				setState(1510);
				singleExpression(31);
				}
				break;
			case 13:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1511);
				match(Not);
				setState(1512);
				singleExpression(30);
				}
				break;
			case 14:
				{
				_localctx = new IteratorsExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1513);
				iteratorBlock();
				}
				break;
			case 15:
				{
				_localctx = new GeneratorsExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1514);
				generatorBlock();
				}
				break;
			case 16:
				{
				_localctx = new GeneratorsFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1515);
				generatorFunctionDeclaration();
				}
				break;
			case 17:
				{
				_localctx = new YieldExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1516);
				yieldStatement();
				}
				break;
			case 18:
				{
				_localctx = new ThisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1517);
				match(This);
				}
				break;
			case 19:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1518);
				identifierName();
				setState(1520);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,193,_ctx) ) {
				case 1:
					{
					setState(1519);
					singleExpression(0);
					}
					break;
				}
				}
				break;
			case 20:
				{
				_localctx = new SuperExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1522);
				match(Super);
				}
				break;
			case 21:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1523);
				literal();
				}
				break;
			case 22:
				{
				_localctx = new ArrayLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1524);
				arrayLiteral();
				}
				break;
			case 23:
				{
				_localctx = new ObjectLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1525);
				objectLiteral();
				}
				break;
			case 24:
				{
				_localctx = new ParenthesizedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1526);
				match(OpenParen);
				setState(1527);
				expressionSequence();
				setState(1528);
				match(CloseParen);
				}
				break;
			case 25:
				{
				_localctx = new GenericTypesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1530);
				typeArguments();
				setState(1532);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,194,_ctx) ) {
				case 1:
					{
					setState(1531);
					expressionSequence();
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1609);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,197,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1536);
						if (!(precpred(_ctx, 29))) throw new FailedPredicateException(this, "precpred(_ctx, 29)");
						setState(1537);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Multiply) | (1L << Divide) | (1L << Modulus))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1538);
						singleExpression(30);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1539);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(1540);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1541);
						singleExpression(29);
						}
						break;
					case 3:
						{
						_localctx = new BitShiftExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1542);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(1543);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RightShiftArithmetic) | (1L << LeftShiftArithmetic) | (1L << RightShiftLogical))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1544);
						singleExpression(28);
						}
						break;
					case 4:
						{
						_localctx = new RelationalExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1545);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(1546);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LessThan) | (1L << MoreThan) | (1L << LessThanEquals) | (1L << GreaterThanEquals))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1547);
						singleExpression(27);
						}
						break;
					case 5:
						{
						_localctx = new InstanceofExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1548);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1549);
						match(Instanceof);
						setState(1550);
						singleExpression(26);
						}
						break;
					case 6:
						{
						_localctx = new InExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1551);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1552);
						match(In);
						setState(1553);
						singleExpression(25);
						}
						break;
					case 7:
						{
						_localctx = new EqualityExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1554);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1555);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Equals_) | (1L << NotEquals) | (1L << IdentityEquals) | (1L << IdentityNotEquals))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1556);
						singleExpression(24);
						}
						break;
					case 8:
						{
						_localctx = new BitAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1557);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1558);
						match(BitAnd);
						setState(1559);
						singleExpression(23);
						}
						break;
					case 9:
						{
						_localctx = new BitXOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1560);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(1561);
						match(BitXOr);
						setState(1562);
						singleExpression(22);
						}
						break;
					case 10:
						{
						_localctx = new BitOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1563);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1564);
						match(BitOr);
						setState(1565);
						singleExpression(21);
						}
						break;
					case 11:
						{
						_localctx = new LogicalAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1566);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1567);
						match(And);
						setState(1568);
						singleExpression(20);
						}
						break;
					case 12:
						{
						_localctx = new LogicalOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1569);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(1570);
						match(Or);
						setState(1571);
						singleExpression(19);
						}
						break;
					case 13:
						{
						_localctx = new TernaryExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1572);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(1573);
						match(QuestionMark);
						setState(1574);
						singleExpression(0);
						setState(1575);
						match(Colon);
						setState(1576);
						singleExpression(18);
						}
						break;
					case 14:
						{
						_localctx = new AssignmentExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1578);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1579);
						match(Assign);
						setState(1580);
						singleExpression(17);
						}
						break;
					case 15:
						{
						_localctx = new AssignmentOperatorExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1581);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(1582);
						assignmentOperator();
						setState(1583);
						singleExpression(16);
						}
						break;
					case 16:
						{
						_localctx = new MemberIndexExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1585);
						if (!(precpred(_ctx, 44))) throw new FailedPredicateException(this, "precpred(_ctx, 44)");
						setState(1586);
						match(OpenBracket);
						setState(1587);
						expressionSequence();
						setState(1588);
						match(CloseBracket);
						}
						break;
					case 17:
						{
						_localctx = new MemberDotExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1590);
						if (!(precpred(_ctx, 43))) throw new FailedPredicateException(this, "precpred(_ctx, 43)");
						setState(1591);
						match(Dot);
						setState(1592);
						identifierName();
						setState(1594);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
						case 1:
							{
							setState(1593);
							nestedTypeGeneric();
							}
							break;
						}
						}
						break;
					case 18:
						{
						_localctx = new ArgumentsExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1596);
						if (!(precpred(_ctx, 42))) throw new FailedPredicateException(this, "precpred(_ctx, 42)");
						setState(1597);
						arguments();
						}
						break;
					case 19:
						{
						_localctx = new PostIncrementExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1598);
						if (!(precpred(_ctx, 40))) throw new FailedPredicateException(this, "precpred(_ctx, 40)");
						setState(1599);
						if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
						setState(1600);
						match(PlusPlus);
						}
						break;
					case 20:
						{
						_localctx = new PostDecreaseExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1601);
						if (!(precpred(_ctx, 39))) throw new FailedPredicateException(this, "precpred(_ctx, 39)");
						setState(1602);
						if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
						setState(1603);
						match(MinusMinus);
						}
						break;
					case 21:
						{
						_localctx = new TemplateStringExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1604);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(1605);
						match(TemplateStringLiteral);
						}
						break;
					case 22:
						{
						_localctx = new CastAsExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(1606);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1607);
						match(As);
						setState(1608);
						asExpression();
						}
						break;
					}
					} 
				}
				setState(1613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AsExpressionContext extends ParserRuleContext {
		public PredefinedTypeContext predefinedType() {
			return getRuleContext(PredefinedTypeContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(TypeScriptParser.OpenBracket, 0); }
		public TerminalNode CloseBracket() { return getToken(TypeScriptParser.CloseBracket, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public AsExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAsExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsExpressionContext asExpression() throws RecognitionException {
		AsExpressionContext _localctx = new AsExpressionContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_asExpression);
		try {
			setState(1620);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,200,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1614);
				predefinedType();
				setState(1617);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
				case 1:
					{
					setState(1615);
					match(OpenBracket);
					setState(1616);
					match(CloseBracket);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1619);
				singleExpression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowFunctionDeclarationContext extends ParserRuleContext {
		public ArrowFunctionParametersContext arrowFunctionParameters() {
			return getRuleContext(ArrowFunctionParametersContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(TypeScriptParser.ARROW, 0); }
		public ArrowFunctionBodyContext arrowFunctionBody() {
			return getRuleContext(ArrowFunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(TypeScriptParser.Async, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public ArrowFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunctionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrowFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrowFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrowFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrowFunctionDeclarationContext arrowFunctionDeclaration() throws RecognitionException {
		ArrowFunctionDeclarationContext _localctx = new ArrowFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_arrowFunctionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1623);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Async) {
				{
				setState(1622);
				match(Async);
				}
			}

			setState(1625);
			arrowFunctionParameters();
			setState(1627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(1626);
				typeAnnotation();
				}
			}

			setState(1629);
			match(ARROW);
			setState(1630);
			arrowFunctionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowFunctionParametersContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(TypeScriptParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(TypeScriptParser.CloseParen, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ArrowFunctionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunctionParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrowFunctionParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrowFunctionParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrowFunctionParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrowFunctionParametersContext arrowFunctionParameters() throws RecognitionException {
		ArrowFunctionParametersContext _localctx = new ArrowFunctionParametersContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_arrowFunctionParameters);
		int _la;
		try {
			setState(1638);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1632);
				match(Identifier);
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(1633);
				match(OpenParen);
				setState(1635);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OpenBracket) | (1L << OpenBrace) | (1L << Ellipsis))) != 0) || ((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (Private - 102)) | (1L << (Public - 102)) | (1L << (Protected - 102)) | (1L << (TypeAlias - 102)) | (1L << (Require - 102)) | (1L << (At - 102)) | (1L << (Identifier - 102)))) != 0)) {
					{
					setState(1634);
					formalParameterList();
					}
				}

				setState(1637);
				match(CloseParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowFunctionBodyContext extends ParserRuleContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(TypeScriptParser.OpenBrace, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode CloseBrace() { return getToken(TypeScriptParser.CloseBrace, 0); }
		public ArrowFunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunctionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterArrowFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitArrowFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitArrowFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrowFunctionBodyContext arrowFunctionBody() throws RecognitionException {
		ArrowFunctionBodyContext _localctx = new ArrowFunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_arrowFunctionBody);
		try {
			setState(1645);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,205,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1640);
				singleExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1641);
				match(OpenBrace);
				setState(1642);
				functionBody();
				setState(1643);
				match(CloseBrace);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode MultiplyAssign() { return getToken(TypeScriptParser.MultiplyAssign, 0); }
		public TerminalNode DivideAssign() { return getToken(TypeScriptParser.DivideAssign, 0); }
		public TerminalNode ModulusAssign() { return getToken(TypeScriptParser.ModulusAssign, 0); }
		public TerminalNode PlusAssign() { return getToken(TypeScriptParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(TypeScriptParser.MinusAssign, 0); }
		public TerminalNode LeftShiftArithmeticAssign() { return getToken(TypeScriptParser.LeftShiftArithmeticAssign, 0); }
		public TerminalNode RightShiftArithmeticAssign() { return getToken(TypeScriptParser.RightShiftArithmeticAssign, 0); }
		public TerminalNode RightShiftLogicalAssign() { return getToken(TypeScriptParser.RightShiftLogicalAssign, 0); }
		public TerminalNode BitAndAssign() { return getToken(TypeScriptParser.BitAndAssign, 0); }
		public TerminalNode BitXorAssign() { return getToken(TypeScriptParser.BitXorAssign, 0); }
		public TerminalNode BitOrAssign() { return getToken(TypeScriptParser.BitOrAssign, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitAssignmentOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1647);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MultiplyAssign) | (1L << DivideAssign) | (1L << ModulusAssign) | (1L << PlusAssign) | (1L << MinusAssign) | (1L << LeftShiftArithmeticAssign) | (1L << RightShiftArithmeticAssign) | (1L << RightShiftLogicalAssign) | (1L << BitAndAssign) | (1L << BitXorAssign) | (1L << BitOrAssign))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NullLiteral() { return getToken(TypeScriptParser.NullLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(TypeScriptParser.BooleanLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(TypeScriptParser.StringLiteral, 0); }
		public TerminalNode TemplateStringLiteral() { return getToken(TypeScriptParser.TemplateStringLiteral, 0); }
		public TerminalNode RegularExpressionLiteral() { return getToken(TypeScriptParser.RegularExpressionLiteral, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_literal);
		try {
			setState(1655);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(1649);
				match(NullLiteral);
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1650);
				match(BooleanLiteral);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(1651);
				match(StringLiteral);
				}
				break;
			case TemplateStringLiteral:
				enterOuterAlt(_localctx, 4);
				{
				setState(1652);
				match(TemplateStringLiteral);
				}
				break;
			case RegularExpressionLiteral:
				enterOuterAlt(_localctx, 5);
				{
				setState(1653);
				match(RegularExpressionLiteral);
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
				enterOuterAlt(_localctx, 6);
				{
				setState(1654);
				numericLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(TypeScriptParser.DecimalLiteral, 0); }
		public TerminalNode HexIntegerLiteral() { return getToken(TypeScriptParser.HexIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral() { return getToken(TypeScriptParser.OctalIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral2() { return getToken(TypeScriptParser.OctalIntegerLiteral2, 0); }
		public TerminalNode BinaryIntegerLiteral() { return getToken(TypeScriptParser.BinaryIntegerLiteral, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitNumericLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitNumericLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1657);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DecimalLiteral) | (1L << HexIntegerLiteral) | (1L << OctalIntegerLiteral) | (1L << OctalIntegerLiteral2) | (1L << BinaryIntegerLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public ReservedWordContext reservedWord() {
			return getRuleContext(ReservedWordContext.class,0);
		}
		public IdentifierNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIdentifierName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIdentifierName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIdentifierName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierNameContext identifierName() throws RecognitionException {
		IdentifierNameContext _localctx = new IdentifierNameContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_identifierName);
		try {
			setState(1661);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1659);
				match(Identifier);
				}
				break;
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case From:
			case ReadOnly:
			case Async:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Implements:
			case Let:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Yield:
			case String:
			case TypeAlias:
			case Get:
			case Set:
			case Require:
				enterOuterAlt(_localctx, 2);
				{
				setState(1660);
				reservedWord();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierOrKeyWordContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public TerminalNode TypeAlias() { return getToken(TypeScriptParser.TypeAlias, 0); }
		public TerminalNode Require() { return getToken(TypeScriptParser.Require, 0); }
		public IdentifierOrKeyWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierOrKeyWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterIdentifierOrKeyWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitIdentifierOrKeyWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitIdentifierOrKeyWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierOrKeyWordContext identifierOrKeyWord() throws RecognitionException {
		IdentifierOrKeyWordContext _localctx = new IdentifierOrKeyWordContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_identifierOrKeyWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1663);
			_la = _input.LA(1);
			if ( !(((((_la - 114)) & ~0x3f) == 0 && ((1L << (_la - 114)) & ((1L << (TypeAlias - 114)) | (1L << (Require - 114)) | (1L << (Identifier - 114)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReservedWordContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode NullLiteral() { return getToken(TypeScriptParser.NullLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(TypeScriptParser.BooleanLiteral, 0); }
		public ReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitReservedWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitReservedWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReservedWordContext reservedWord() throws RecognitionException {
		ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_reservedWord);
		try {
			setState(1668);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case From:
			case ReadOnly:
			case Async:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Implements:
			case Let:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Yield:
			case String:
			case TypeAlias:
			case Get:
			case Set:
			case Require:
				enterOuterAlt(_localctx, 1);
				{
				setState(1665);
				keyword();
				}
				break;
			case NullLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1666);
				match(NullLiteral);
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(1667);
				match(BooleanLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(TypeScriptParser.Break, 0); }
		public TerminalNode Do() { return getToken(TypeScriptParser.Do, 0); }
		public TerminalNode Instanceof() { return getToken(TypeScriptParser.Instanceof, 0); }
		public TerminalNode Typeof() { return getToken(TypeScriptParser.Typeof, 0); }
		public TerminalNode Case() { return getToken(TypeScriptParser.Case, 0); }
		public TerminalNode Else() { return getToken(TypeScriptParser.Else, 0); }
		public TerminalNode New() { return getToken(TypeScriptParser.New, 0); }
		public TerminalNode Var() { return getToken(TypeScriptParser.Var, 0); }
		public TerminalNode Catch() { return getToken(TypeScriptParser.Catch, 0); }
		public TerminalNode Finally() { return getToken(TypeScriptParser.Finally, 0); }
		public TerminalNode Return() { return getToken(TypeScriptParser.Return, 0); }
		public TerminalNode Void() { return getToken(TypeScriptParser.Void, 0); }
		public TerminalNode Continue() { return getToken(TypeScriptParser.Continue, 0); }
		public TerminalNode For() { return getToken(TypeScriptParser.For, 0); }
		public TerminalNode Switch() { return getToken(TypeScriptParser.Switch, 0); }
		public TerminalNode While() { return getToken(TypeScriptParser.While, 0); }
		public TerminalNode Debugger() { return getToken(TypeScriptParser.Debugger, 0); }
		public TerminalNode Function() { return getToken(TypeScriptParser.Function, 0); }
		public TerminalNode This() { return getToken(TypeScriptParser.This, 0); }
		public TerminalNode With() { return getToken(TypeScriptParser.With, 0); }
		public TerminalNode Default() { return getToken(TypeScriptParser.Default, 0); }
		public TerminalNode If() { return getToken(TypeScriptParser.If, 0); }
		public TerminalNode Throw() { return getToken(TypeScriptParser.Throw, 0); }
		public TerminalNode Delete() { return getToken(TypeScriptParser.Delete, 0); }
		public TerminalNode In() { return getToken(TypeScriptParser.In, 0); }
		public TerminalNode Try() { return getToken(TypeScriptParser.Try, 0); }
		public TerminalNode ReadOnly() { return getToken(TypeScriptParser.ReadOnly, 0); }
		public TerminalNode Async() { return getToken(TypeScriptParser.Async, 0); }
		public TerminalNode From() { return getToken(TypeScriptParser.From, 0); }
		public TerminalNode Class() { return getToken(TypeScriptParser.Class, 0); }
		public TerminalNode Enum() { return getToken(TypeScriptParser.Enum, 0); }
		public TerminalNode Extends() { return getToken(TypeScriptParser.Extends, 0); }
		public TerminalNode Super() { return getToken(TypeScriptParser.Super, 0); }
		public TerminalNode Const() { return getToken(TypeScriptParser.Const, 0); }
		public TerminalNode Export() { return getToken(TypeScriptParser.Export, 0); }
		public TerminalNode Import() { return getToken(TypeScriptParser.Import, 0); }
		public TerminalNode Implements() { return getToken(TypeScriptParser.Implements, 0); }
		public TerminalNode Let() { return getToken(TypeScriptParser.Let, 0); }
		public TerminalNode Private() { return getToken(TypeScriptParser.Private, 0); }
		public TerminalNode Public() { return getToken(TypeScriptParser.Public, 0); }
		public TerminalNode Interface() { return getToken(TypeScriptParser.Interface, 0); }
		public TerminalNode Package() { return getToken(TypeScriptParser.Package, 0); }
		public TerminalNode Protected() { return getToken(TypeScriptParser.Protected, 0); }
		public TerminalNode Static() { return getToken(TypeScriptParser.Static, 0); }
		public TerminalNode Yield() { return getToken(TypeScriptParser.Yield, 0); }
		public TerminalNode Get() { return getToken(TypeScriptParser.Get, 0); }
		public TerminalNode Set() { return getToken(TypeScriptParser.Set, 0); }
		public TerminalNode Require() { return getToken(TypeScriptParser.Require, 0); }
		public TerminalNode TypeAlias() { return getToken(TypeScriptParser.TypeAlias, 0); }
		public TerminalNode String() { return getToken(TypeScriptParser.String, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1670);
			_la = _input.LA(1);
			if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (Break - 63)) | (1L << (Do - 63)) | (1L << (Instanceof - 63)) | (1L << (Typeof - 63)) | (1L << (Case - 63)) | (1L << (Else - 63)) | (1L << (New - 63)) | (1L << (Var - 63)) | (1L << (Catch - 63)) | (1L << (Finally - 63)) | (1L << (Return - 63)) | (1L << (Void - 63)) | (1L << (Continue - 63)) | (1L << (For - 63)) | (1L << (Switch - 63)) | (1L << (While - 63)) | (1L << (Debugger - 63)) | (1L << (Function - 63)) | (1L << (This - 63)) | (1L << (With - 63)) | (1L << (Default - 63)) | (1L << (If - 63)) | (1L << (Throw - 63)) | (1L << (Delete - 63)) | (1L << (In - 63)) | (1L << (Try - 63)) | (1L << (From - 63)) | (1L << (ReadOnly - 63)) | (1L << (Async - 63)) | (1L << (Class - 63)) | (1L << (Enum - 63)) | (1L << (Extends - 63)) | (1L << (Super - 63)) | (1L << (Const - 63)) | (1L << (Export - 63)) | (1L << (Import - 63)) | (1L << (Implements - 63)) | (1L << (Let - 63)) | (1L << (Private - 63)) | (1L << (Public - 63)) | (1L << (Interface - 63)) | (1L << (Package - 63)) | (1L << (Protected - 63)) | (1L << (Static - 63)) | (1L << (Yield - 63)) | (1L << (String - 63)) | (1L << (TypeAlias - 63)) | (1L << (Get - 63)) | (1L << (Set - 63)) | (1L << (Require - 63)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GetterContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public GetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterGetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitGetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitGetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterContext getter() throws RecognitionException {
		GetterContext _localctx = new GetterContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_getter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1672);
			match(Identifier);
			setState(1673);
			if (!(this.p("get"))) throw new FailedPredicateException(this, "this.p(\"get\")");
			setState(1674);
			propertyName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetterContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TypeScriptParser.Identifier, 0); }
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public SetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterSetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitSetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitSetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterContext setter() throws RecognitionException {
		SetterContext _localctx = new SetterContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_setter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1676);
			match(Identifier);
			setState(1677);
			if (!(this.p("set"))) throw new FailedPredicateException(this, "this.p(\"set\")");
			setState(1678);
			propertyName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EosContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(TypeScriptParser.SemiColon, 0); }
		public TerminalNode EOF() { return getToken(TypeScriptParser.EOF, 0); }
		public EosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).enterEos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TypeScriptParserListener ) ((TypeScriptParserListener)listener).exitEos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TypeScriptParserVisitor ) return ((TypeScriptParserVisitor<? extends T>)visitor).visitEos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EosContext eos() throws RecognitionException {
		EosContext _localctx = new EosContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_eos);
		try {
			setState(1684);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,209,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1680);
				match(SemiColon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1681);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1682);
				if (!(this.lineTerminatorAhead())) throw new FailedPredicateException(this, "this.lineTerminatorAhead()");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1683);
				if (!(this.closeBrace())) throw new FailedPredicateException(this, "this.closeBrace()");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return unionOrIntersectionOrPrimaryType_sempred((UnionOrIntersectionOrPrimaryTypeContext)_localctx, predIndex);
		case 11:
			return primaryType_sempred((PrimaryTypeContext)_localctx, predIndex);
		case 22:
			return arrayType_sempred((ArrayTypeContext)_localctx, predIndex);
		case 58:
			return decoratorMemberExpression_sempred((DecoratorMemberExpressionContext)_localctx, predIndex);
		case 74:
			return expressionStatement_sempred((ExpressionStatementContext)_localctx, predIndex);
		case 76:
			return iterationStatement_sempred((IterationStatementContext)_localctx, predIndex);
		case 78:
			return continueStatement_sempred((ContinueStatementContext)_localctx, predIndex);
		case 79:
			return breakStatement_sempred((BreakStatementContext)_localctx, predIndex);
		case 80:
			return returnStatement_sempred((ReturnStatementContext)_localctx, predIndex);
		case 81:
			return yieldStatement_sempred((YieldStatementContext)_localctx, predIndex);
		case 89:
			return throwStatement_sempred((ThrowStatementContext)_localctx, predIndex);
		case 128:
			return singleExpression_sempred((SingleExpressionContext)_localctx, predIndex);
		case 140:
			return getter_sempred((GetterContext)_localctx, predIndex);
		case 141:
			return setter_sempred((SetterContext)_localctx, predIndex);
		case 142:
			return eos_sempred((EosContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean unionOrIntersectionOrPrimaryType_sempred(UnionOrIntersectionOrPrimaryTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean primaryType_sempred(PrimaryTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return notLineTerminator();
		}
		return true;
	}
	private boolean arrayType_sempred(ArrayTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return notLineTerminator();
		}
		return true;
	}
	private boolean decoratorMemberExpression_sempred(DecoratorMemberExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expressionStatement_sempred(ExpressionStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return this.notOpenBraceAndNotFunction();
		}
		return true;
	}
	private boolean iterationStatement_sempred(IterationStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return this.p("of");
		case 8:
			return this.p("of");
		}
		return true;
	}
	private boolean continueStatement_sempred(ContinueStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean breakStatement_sempred(BreakStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean returnStatement_sempred(ReturnStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean yieldStatement_sempred(YieldStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean throwStatement_sempred(ThrowStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean singleExpression_sempred(SingleExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 29);
		case 15:
			return precpred(_ctx, 28);
		case 16:
			return precpred(_ctx, 27);
		case 17:
			return precpred(_ctx, 26);
		case 18:
			return precpred(_ctx, 25);
		case 19:
			return precpred(_ctx, 24);
		case 20:
			return precpred(_ctx, 23);
		case 21:
			return precpred(_ctx, 22);
		case 22:
			return precpred(_ctx, 21);
		case 23:
			return precpred(_ctx, 20);
		case 24:
			return precpred(_ctx, 19);
		case 25:
			return precpred(_ctx, 18);
		case 26:
			return precpred(_ctx, 17);
		case 27:
			return precpred(_ctx, 16);
		case 28:
			return precpred(_ctx, 15);
		case 29:
			return precpred(_ctx, 44);
		case 30:
			return precpred(_ctx, 43);
		case 31:
			return precpred(_ctx, 42);
		case 32:
			return precpred(_ctx, 40);
		case 33:
			return this.notLineTerminator();
		case 34:
			return precpred(_ctx, 39);
		case 35:
			return this.notLineTerminator();
		case 36:
			return precpred(_ctx, 14);
		case 37:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean getter_sempred(GetterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 38:
			return this.p("get");
		}
		return true;
	}
	private boolean setter_sempred(SetterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 39:
			return this.p("set");
		}
		return true;
	}
	private boolean eos_sempred(EosContext _localctx, int predIndex) {
		switch (predIndex) {
		case 40:
			return this.lineTerminatorAhead();
		case 41:
			return this.closeBrace();
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0086\u0699\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e"+
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\3\2\3\2\3\2\3\3\3\3\5\3\u0126"+
		"\n\3\3\4\3\4\5\4\u012a\n\4\3\4\3\4\3\5\3\5\3\5\7\5\u0131\n\5\f\5\16\5"+
		"\u0134\13\5\3\6\3\6\5\6\u0138\n\6\3\6\5\6\u013b\n\6\3\7\3\7\3\7\3\b\3"+
		"\b\5\b\u0142\n\b\3\b\3\b\3\t\3\t\3\t\7\t\u0149\n\t\f\t\16\t\u014c\13\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u0155\n\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\7\f\u0160\n\f\f\f\16\f\u0163\13\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0177\n\r\3\r"+
		"\3\r\3\r\3\r\7\r\u017d\n\r\f\r\16\r\u0180\13\r\3\16\3\16\3\17\3\17\5\17"+
		"\u0186\n\17\3\20\3\20\5\20\u018a\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0199\n\22\3\23\3\23\5\23\u019d"+
		"\n\23\3\24\3\24\5\24\u01a1\n\24\3\24\3\24\3\25\3\25\5\25\u01a7\n\25\3"+
		"\26\3\26\3\26\7\26\u01ac\n\26\f\26\16\26\u01af\13\26\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\5\27\u01b8\n\27\5\27\u01ba\n\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\7\32\u01c8\n\32\f\32\16\32\u01cb"+
		"\13\32\3\33\5\33\u01ce\n\33\3\33\3\33\5\33\u01d2\n\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\5\34\u01da\n\34\3\34\3\34\5\34\u01de\n\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\6\36\u01eb\n\36\r\36\16\36\u01ec"+
		"\3\36\3\36\5\36\u01f1\n\36\3\37\5\37\u01f4\n\37\3\37\3\37\5\37\u01f8\n"+
		"\37\3\37\5\37\u01fb\n\37\3\37\3\37\5\37\u01ff\n\37\3 \3 \3 \3!\5!\u0205"+
		"\n!\3!\3!\5!\u0209\n!\3!\3!\5!\u020d\n!\3\"\3\"\3#\3#\3#\3#\7#\u0215\n"+
		"#\f#\16#\u0218\13#\3#\3#\3#\5#\u021d\n#\3#\3#\3#\3#\3#\5#\u0224\n#\3#"+
		"\5#\u0227\n#\5#\u0229\n#\5#\u022b\n#\3$\3$\3$\7$\u0230\n$\f$\16$\u0233"+
		"\13$\3%\5%\u0236\n%\3%\5%\u0239\n%\3%\3%\5%\u023d\n%\3&\3&\3&\7&\u0242"+
		"\n&\f&\16&\u0245\13&\3\'\5\'\u0248\n\'\3\'\5\'\u024b\n\'\3\'\3\'\3\'\5"+
		"\'\u0250\n\'\3\'\5\'\u0253\n\'\3\'\5\'\u0256\n\'\3(\3(\3(\3)\3)\5)\u025d"+
		"\n)\3*\3*\5*\u0261\n*\3*\3*\5*\u0265\n*\3*\3*\5*\u0269\n*\3+\3+\3+\3+"+
		"\3+\3+\3+\3,\3,\5,\u0274\n,\3,\3,\3-\3-\3-\5-\u027b\n-\3-\3-\3-\3-\3."+
		"\5.\u0282\n.\3.\3.\3.\5.\u0287\n.\3.\3.\3.\3.\3.\3.\5.\u028f\n.\3/\5/"+
		"\u0292\n/\3/\5/\u0295\n/\3/\3/\3/\5/\u029a\n/\3/\5/\u029d\n/\3/\3/\5/"+
		"\u02a1\n/\3\60\3\60\3\60\3\61\3\61\3\61\7\61\u02a9\n\61\f\61\16\61\u02ac"+
		"\13\61\3\62\5\62\u02af\n\62\3\62\3\62\3\62\3\62\5\62\u02b5\n\62\3\62\3"+
		"\62\3\63\3\63\5\63\u02bb\n\63\3\64\3\64\3\64\7\64\u02c0\n\64\f\64\16\64"+
		"\u02c3\13\64\3\65\3\65\3\65\5\65\u02c8\n\65\3\66\3\66\3\66\3\66\5\66\u02ce"+
		"\n\66\3\66\3\66\3\67\3\67\6\67\u02d4\n\67\r\67\16\67\u02d5\3\67\7\67\u02d9"+
		"\n\67\f\67\16\67\u02dc\13\67\38\38\38\38\38\38\38\38\38\38\38\38\58\u02ea"+
		"\n8\39\39\3:\6:\u02ef\n:\r:\16:\u02f0\3;\3;\3;\5;\u02f6\n;\3<\3<\3<\3"+
		"<\3<\3<\5<\u02fe\n<\3<\3<\3<\7<\u0303\n<\f<\16<\u0306\13<\3=\3=\3=\3>"+
		"\5>\u030c\n>\3>\3>\3?\5?\u0311\n?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@"+
		"\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\5@\u0333"+
		"\n@\3A\3A\5A\u0337\nA\3A\3A\3B\6B\u033c\nB\rB\16B\u033d\3C\3C\3C\3C\5"+
		"C\u0344\nC\3C\3C\3D\3D\3D\3D\5D\u034c\nD\3E\3E\3E\3E\5E\u0352\nE\3E\3"+
		"E\5E\u0356\nE\3E\3E\3E\3E\3F\3F\3F\5F\u035f\nF\3F\3F\3F\3F\7F\u0365\n"+
		"F\fF\16F\u0368\13F\3F\3F\3G\3G\5G\u036e\nG\3G\3G\5G\u0372\nG\3H\3H\5H"+
		"\u0376\nH\3H\3H\5H\u037a\nH\3H\5H\u037d\nH\3H\5H\u0380\nH\3H\5H\u0383"+
		"\nH\3H\3H\5H\u0387\nH\3H\3H\5H\u038b\nH\3H\3H\5H\u038f\nH\5H\u0391\nH"+
		"\3I\3I\3I\7I\u0396\nI\fI\16I\u0399\13I\3J\3J\3J\5J\u039e\nJ\3J\5J\u03a1"+
		"\nJ\3J\5J\u03a4\nJ\3J\3J\5J\u03a8\nJ\3J\5J\u03ab\nJ\3K\3K\3L\3L\3L\5L"+
		"\u03b2\nL\3M\3M\3M\3M\3M\3M\3M\5M\u03bb\nM\3N\3N\3N\3N\3N\3N\3N\3N\3N"+
		"\3N\3N\3N\3N\3N\3N\3N\3N\5N\u03ce\nN\3N\3N\5N\u03d2\nN\3N\3N\5N\u03d6"+
		"\nN\3N\3N\3N\3N\3N\3N\3N\3N\5N\u03e0\nN\3N\3N\5N\u03e4\nN\3N\3N\3N\3N"+
		"\3N\3N\3N\3N\3N\5N\u03ef\nN\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\5N\u03fc"+
		"\nN\3N\3N\3N\3N\5N\u0402\nN\3O\3O\3P\3P\3P\5P\u0409\nP\3P\3P\3Q\3Q\3Q"+
		"\5Q\u0410\nQ\3Q\3Q\3R\3R\3R\5R\u0417\nR\3R\3R\3S\3S\3S\5S\u041e\nS\3S"+
		"\3S\3T\3T\3T\3T\3T\3T\3U\3U\3U\3U\3U\3U\3V\3V\5V\u0430\nV\3V\3V\5V\u0434"+
		"\nV\5V\u0436\nV\3V\3V\3W\6W\u043b\nW\rW\16W\u043c\3X\3X\3X\3X\5X\u0443"+
		"\nX\3Y\3Y\3Y\5Y\u0448\nY\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\5"+
		"\\\u0457\n\\\3\\\5\\\u045a\n\\\3]\3]\3]\3]\3]\3]\3^\3^\3^\3_\3_\3_\3`"+
		"\3`\3`\3`\3`\3`\3`\3`\5`\u0470\n`\3a\5a\u0473\na\3a\3a\3a\5a\u0478\na"+
		"\3a\3a\3a\3b\5b\u047e\nb\3b\5b\u0481\nb\3c\3c\7c\u0485\nc\fc\16c\u0488"+
		"\13c\3c\3c\3d\3d\3d\3e\3e\3e\3f\3f\5f\u0494\nf\3f\3f\3f\5f\u0499\nf\3"+
		"g\3g\3g\5g\u049e\ng\3g\5g\u04a1\ng\3g\5g\u04a4\ng\3g\3g\3g\3g\3g\3g\3"+
		"g\3g\3g\3g\5g\u04b0\ng\3g\3g\3g\5g\u04b5\ng\3g\5g\u04b8\ng\3h\5h\u04bb"+
		"\nh\3h\5h\u04be\nh\3h\5h\u04c1\nh\3h\5h\u04c4\nh\3i\3i\3i\3j\5j\u04ca"+
		"\nj\3j\3j\3j\5j\u04cf\nj\3j\3j\3j\3j\3j\3k\3k\3k\5k\u04d9\nk\3k\3k\5k"+
		"\u04dd\nk\3k\3k\3k\3k\3k\3l\3l\3l\3l\7l\u04e8\nl\fl\16l\u04eb\13l\3l\5"+
		"l\u04ee\nl\3l\3l\3m\3m\3m\3n\3n\3n\3n\7n\u04f9\nn\fn\16n\u04fc\13n\3n"+
		"\5n\u04ff\nn\3n\3n\3o\3o\3o\3o\3o\5o\u0508\no\3o\3o\3o\3o\3o\3p\3p\3p"+
		"\7p\u0512\np\fp\16p\u0515\13p\3p\3p\5p\u0519\np\3p\3p\3p\3p\3p\5p\u0520"+
		"\np\5p\u0522\np\3q\5q\u0525\nq\3q\5q\u0528\nq\3q\3q\5q\u052c\nq\3q\5q"+
		"\u052f\nq\3q\3q\5q\u0533\nq\3r\3r\3r\3s\5s\u0539\ns\3t\6t\u053c\nt\rt"+
		"\16t\u053d\3u\3u\5u\u0542\nu\3u\3u\3v\3v\6v\u0548\nv\rv\16v\u0549\3v\7"+
		"v\u054d\nv\fv\16v\u0550\13v\3w\5w\u0553\nw\3w\3w\5w\u0557\nw\3w\5w\u055a"+
		"\nw\3x\3x\3x\3x\7x\u0560\nx\fx\16x\u0563\13x\5x\u0565\nx\3x\5x\u0568\n"+
		"x\3x\3x\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\5y\u057b\ny\3z\3"+
		"z\3z\3z\5z\u0581\nz\3z\3z\3z\3z\3{\3{\3{\3{\5{\u058b\n{\3{\5{\u058e\n"+
		"{\3{\3{\3{\3{\3{\3|\3|\3|\5|\u0598\n|\3}\3}\3}\5}\u059d\n}\5}\u059f\n"+
		"}\3}\3}\3~\3~\3~\7~\u05a6\n~\f~\16~\u05a9\13~\3\177\5\177\u05ac\n\177"+
		"\3\177\3\177\5\177\u05b0\n\177\3\u0080\3\u0080\3\u0080\7\u0080\u05b5\n"+
		"\u0080\f\u0080\16\u0080\u05b8\13\u0080\3\u0081\3\u0081\5\u0081\u05bc\n"+
		"\u0081\3\u0081\3\u0081\5\u0081\u05c0\n\u0081\3\u0081\3\u0081\5\u0081\u05c4"+
		"\n\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\5\u0082\u05cf\n\u0082\3\u0082\3\u0082\3\u0082\3\u0082\5\u0082"+
		"\u05d5\n\u0082\3\u0082\5\u0082\u05d8\n\u0082\3\u0082\3\u0082\3\u0082\3"+
		"\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\5\u0082\u05f3\n\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\5\u0082"+
		"\u05ff\n\u0082\5\u0082\u0601\n\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3"+
		"\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\5\u0082\u063d\n\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\7\u0082\u064c"+
		"\n\u0082\f\u0082\16\u0082\u064f\13\u0082\3\u0083\3\u0083\3\u0083\5\u0083"+
		"\u0654\n\u0083\3\u0083\5\u0083\u0657\n\u0083\3\u0084\5\u0084\u065a\n\u0084"+
		"\3\u0084\3\u0084\5\u0084\u065e\n\u0084\3\u0084\3\u0084\3\u0084\3\u0085"+
		"\3\u0085\3\u0085\5\u0085\u0666\n\u0085\3\u0085\5\u0085\u0669\n\u0085\3"+
		"\u0086\3\u0086\3\u0086\3\u0086\3\u0086\5\u0086\u0670\n\u0086\3\u0087\3"+
		"\u0087\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\5\u0088\u067a\n"+
		"\u0088\3\u0089\3\u0089\3\u008a\3\u008a\5\u008a\u0680\n\u008a\3\u008b\3"+
		"\u008b\3\u008c\3\u008c\3\u008c\5\u008c\u0687\n\u008c\3\u008d\3\u008d\3"+
		"\u008e\3\u008e\3\u008e\3\u008e\3\u008f\3\u008f\3\u008f\3\u008f\3\u0090"+
		"\3\u0090\3\u0090\3\u0090\5\u0090\u0697\n\u0090\3\u0090\2\6\26\30v\u0102"+
		"\u0091\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<"+
		">@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba"+
		"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2"+
		"\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea"+
		"\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102"+
		"\u0104\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u011a"+
		"\u011c\u011e\2\21\4\2LLos\3\2\f\r\4\2hill\4\2pprr\5\2HHccgg\4\2\16\16"+
		"\20\20\3\2\31\33\3\2\25\26\3\2\34\36\3\2\37\"\3\2#&\3\2,\66\3\2<@\5\2"+
		"ttyy\177\177\7\2AZ\\nrrtvyy\2\u074c\2\u0120\3\2\2\2\4\u0125\3\2\2\2\6"+
		"\u0127\3\2\2\2\b\u012d\3\2\2\2\n\u013a\3\2\2\2\f\u013c\3\2\2\2\16\u013f"+
		"\3\2\2\2\20\u0145\3\2\2\2\22\u014d\3\2\2\2\24\u0154\3\2\2\2\26\u0156\3"+
		"\2\2\2\30\u0176\3\2\2\2\32\u0181\3\2\2\2\34\u0183\3\2\2\2\36\u0189\3\2"+
		"\2\2 \u018b\3\2\2\2\"\u018f\3\2\2\2$\u019c\3\2\2\2&\u019e\3\2\2\2(\u01a4"+
		"\3\2\2\2*\u01a8\3\2\2\2,\u01b9\3\2\2\2.\u01bb\3\2\2\2\60\u01c0\3\2\2\2"+
		"\62\u01c4\3\2\2\2\64\u01cd\3\2\2\2\66\u01d7\3\2\2\28\u01e3\3\2\2\2:\u01f0"+
		"\3\2\2\2<\u01f3\3\2\2\2>\u0200\3\2\2\2@\u0204\3\2\2\2B\u020e\3\2\2\2D"+
		"\u022a\3\2\2\2F\u022c\3\2\2\2H\u0235\3\2\2\2J\u023e\3\2\2\2L\u0247\3\2"+
		"\2\2N\u0257\3\2\2\2P\u025c\3\2\2\2R\u025e\3\2\2\2T\u026a\3\2\2\2V\u0271"+
		"\3\2\2\2X\u0277\3\2\2\2Z\u0281\3\2\2\2\\\u0291\3\2\2\2^\u02a2\3\2\2\2"+
		"`\u02a5\3\2\2\2b\u02ae\3\2\2\2d\u02b8\3\2\2\2f\u02bc\3\2\2\2h\u02c4\3"+
		"\2\2\2j\u02c9\3\2\2\2l\u02d1\3\2\2\2n\u02e9\3\2\2\2p\u02eb\3\2\2\2r\u02ee"+
		"\3\2\2\2t\u02f2\3\2\2\2v\u02fd\3\2\2\2x\u0307\3\2\2\2z\u030b\3\2\2\2|"+
		"\u0310\3\2\2\2~\u0332\3\2\2\2\u0080\u0334\3\2\2\2\u0082\u033b\3\2\2\2"+
		"\u0084\u033f\3\2\2\2\u0086\u0347\3\2\2\2\u0088\u0351\3\2\2\2\u008a\u035e"+
		"\3\2\2\2\u008c\u036b\3\2\2\2\u008e\u0390\3\2\2\2\u0090\u0392\3\2\2\2\u0092"+
		"\u039d\3\2\2\2\u0094\u03ac\3\2\2\2\u0096\u03ae\3\2\2\2\u0098\u03b3\3\2"+
		"\2\2\u009a\u0401\3\2\2\2\u009c\u0403\3\2\2\2\u009e\u0405\3\2\2\2\u00a0"+
		"\u040c\3\2\2\2\u00a2\u0413\3\2\2\2\u00a4\u041a\3\2\2\2\u00a6\u0421\3\2"+
		"\2\2\u00a8\u0427\3\2\2\2\u00aa\u042d\3\2\2\2\u00ac\u043a\3\2\2\2\u00ae"+
		"\u043e\3\2\2\2\u00b0\u0444\3\2\2\2\u00b2\u0449\3\2\2\2\u00b4\u044d\3\2"+
		"\2\2\u00b6\u0452\3\2\2\2\u00b8\u045b\3\2\2\2\u00ba\u0461\3\2\2\2\u00bc"+
		"\u0464\3\2\2\2\u00be\u0467\3\2\2\2\u00c0\u0472\3\2\2\2\u00c2\u047d\3\2"+
		"\2\2\u00c4\u0482\3\2\2\2\u00c6\u048b\3\2\2\2\u00c8\u048e\3\2\2\2\u00ca"+
		"\u0498\3\2\2\2\u00cc\u04b7\3\2\2\2\u00ce\u04ba\3\2\2\2\u00d0\u04c5\3\2"+
		"\2\2\u00d2\u04c9\3\2\2\2\u00d4\u04d5\3\2\2\2\u00d6\u04e3\3\2\2\2\u00d8"+
		"\u04f1\3\2\2\2\u00da\u04f4\3\2\2\2\u00dc\u0502\3\2\2\2\u00de\u0521\3\2"+
		"\2\2\u00e0\u0524\3\2\2\2\u00e2\u0534\3\2\2\2\u00e4\u0538\3\2\2\2\u00e6"+
		"\u053b\3\2\2\2\u00e8\u053f\3\2\2\2\u00ea\u0545\3\2\2\2\u00ec\u0552\3\2"+
		"\2\2\u00ee\u055b\3\2\2\2\u00f0\u057a\3\2\2\2\u00f2\u057c\3\2\2\2\u00f4"+
		"\u0586\3\2\2\2\u00f6\u0597\3\2\2\2\u00f8\u0599\3\2\2\2\u00fa\u05a2\3\2"+
		"\2\2\u00fc\u05ab\3\2\2\2\u00fe\u05b1\3\2\2\2\u0100\u05b9\3\2\2\2\u0102"+
		"\u0600\3\2\2\2\u0104\u0656\3\2\2\2\u0106\u0659\3\2\2\2\u0108\u0668\3\2"+
		"\2\2\u010a\u066f\3\2\2\2\u010c\u0671\3\2\2\2\u010e\u0679\3\2\2\2\u0110"+
		"\u067b\3\2\2\2\u0112\u067f\3\2\2\2\u0114\u0681\3\2\2\2\u0116\u0686\3\2"+
		"\2\2\u0118\u0688\3\2\2\2\u011a\u068a\3\2\2\2\u011c\u068e\3\2\2\2\u011e"+
		"\u0696\3\2\2\2\u0120\u0121\7\16\2\2\u0121\u0122\5\u0102\u0082\2\u0122"+
		"\3\3\2\2\2\u0123\u0126\5\u00e8u\2\u0124\u0126\5\u00eex\2\u0125\u0123\3"+
		"\2\2\2\u0125\u0124\3\2\2\2\u0126\5\3\2\2\2\u0127\u0129\7\37\2\2\u0128"+
		"\u012a\5\b\5\2\u0129\u0128\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\3\2"+
		"\2\2\u012b\u012c\7 \2\2\u012c\7\3\2\2\2\u012d\u0132\5\n\6\2\u012e\u012f"+
		"\7\r\2\2\u012f\u0131\5\n\6\2\u0130\u012e\3\2\2\2\u0131\u0134\3\2\2\2\u0132"+
		"\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\t\3\2\2\2\u0134\u0132\3\2\2\2"+
		"\u0135\u0137\7\177\2\2\u0136\u0138\5\f\7\2\u0137\u0136\3\2\2\2\u0137\u0138"+
		"\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u013b\5\6\4\2\u013a\u0135\3\2\2\2\u013a"+
		"\u0139\3\2\2\2\u013b\13\3\2\2\2\u013c\u013d\7a\2\2\u013d\u013e\5\24\13"+
		"\2\u013e\r\3\2\2\2\u013f\u0141\7\37\2\2\u0140\u0142\5\20\t\2\u0141\u0140"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\7 \2\2\u0144"+
		"\17\3\2\2\2\u0145\u014a\5\22\n\2\u0146\u0147\7\r\2\2\u0147\u0149\5\22"+
		"\n\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\21\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u014e\5\24\13"+
		"\2\u014e\23\3\2\2\2\u014f\u0155\5\26\f\2\u0150\u0155\5\64\33\2\u0151\u0155"+
		"\5\66\34\2\u0152\u0155\5 \21\2\u0153\u0155\7\u0080\2\2\u0154\u014f\3\2"+
		"\2\2\u0154\u0150\3\2\2\2\u0154\u0151\3\2\2\2\u0154\u0152\3\2\2\2\u0154"+
		"\u0153\3\2\2\2\u0155\25\3\2\2\2\u0156\u0157\b\f\1\2\u0157\u0158\5\30\r"+
		"\2\u0158\u0161\3\2\2\2\u0159\u015a\f\5\2\2\u015a\u015b\7)\2\2\u015b\u0160"+
		"\5\26\f\6\u015c\u015d\f\4\2\2\u015d\u015e\7\'\2\2\u015e\u0160\5\26\f\5"+
		"\u015f\u0159\3\2\2\2\u015f\u015c\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f"+
		"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\27\3\2\2\2\u0163\u0161\3\2\2\2\u0164"+
		"\u0165\b\r\1\2\u0165\u0166\7\b\2\2\u0166\u0167\5\24\13\2\u0167\u0168\7"+
		"\t\2\2\u0168\u0177\3\2\2\2\u0169\u0177\5\32\16\2\u016a\u0177\5\34\17\2"+
		"\u016b\u0177\5&\24\2\u016c\u016d\7\6\2\2\u016d\u016e\5\62\32\2\u016e\u016f"+
		"\7\7\2\2\u016f\u0177\3\2\2\2\u0170\u0177\58\35\2\u0171\u0177\7S\2\2\u0172"+
		"\u0173\5\34\17\2\u0173\u0174\7}\2\2\u0174\u0175\5\30\r\3\u0175\u0177\3"+
		"\2\2\2\u0176\u0164\3\2\2\2\u0176\u0169\3\2\2\2\u0176\u016a\3\2\2\2\u0176"+
		"\u016b\3\2\2\2\u0176\u016c\3\2\2\2\u0176\u0170\3\2\2\2\u0176\u0171\3\2"+
		"\2\2\u0176\u0172\3\2\2\2\u0177\u017e\3\2\2\2\u0178\u0179\f\7\2\2\u0179"+
		"\u017a\6\r\5\2\u017a\u017b\7\6\2\2\u017b\u017d\7\7\2\2\u017c\u0178\3\2"+
		"\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f"+
		"\31\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0182\t\2\2\2\u0182\33\3\2\2\2\u0183"+
		"\u0185\5$\23\2\u0184\u0186\5\36\20\2\u0185\u0184\3\2\2\2\u0185\u0186\3"+
		"\2\2\2\u0186\35\3\2\2\2\u0187\u018a\5\"\22\2\u0188\u018a\5 \21\2\u0189"+
		"\u0187\3\2\2\2\u0189\u0188\3\2\2\2\u018a\37\3\2\2\2\u018b\u018c\7\37\2"+
		"\2\u018c\u018d\5\20\t\2\u018d\u018e\7 \2\2\u018e!\3\2\2\2\u018f\u0190"+
		"\7\37\2\2\u0190\u0191\5\20\t\2\u0191\u0192\7\37\2\2\u0192\u0198\5\20\t"+
		"\2\u0193\u0194\7 \2\2\u0194\u0195\5\4\3\2\u0195\u0196\7 \2\2\u0196\u0199"+
		"\3\2\2\2\u0197\u0199\7\34\2\2\u0198\u0193\3\2\2\2\u0198\u0197\3\2\2\2"+
		"\u0199#\3\2\2\2\u019a\u019d\7\177\2\2\u019b\u019d\5l\67\2\u019c\u019a"+
		"\3\2\2\2\u019c\u019b\3\2\2\2\u019d%\3\2\2\2\u019e\u01a0\7\n\2\2\u019f"+
		"\u01a1\5(\25\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\3\2"+
		"\2\2\u01a2\u01a3\7\13\2\2\u01a3\'\3\2\2\2\u01a4\u01a6\5*\26\2\u01a5\u01a7"+
		"\t\3\2\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7)\3\2\2\2\u01a8"+
		"\u01ad\5,\27\2\u01a9\u01aa\t\3\2\2\u01aa\u01ac\5,\27\2\u01ab\u01a9\3\2"+
		"\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae"+
		"+\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01ba\5<\37\2\u01b1\u01ba\5@!\2\u01b2"+
		"\u01ba\5R*\2\u01b3\u01ba\5T+\2\u01b4\u01b7\5V,\2\u01b5\u01b6\7\67\2\2"+
		"\u01b6\u01b8\5\24\13\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01ba"+
		"\3\2\2\2\u01b9\u01b0\3\2\2\2\u01b9\u01b1\3\2\2\2\u01b9\u01b2\3\2\2\2\u01b9"+
		"\u01b3\3\2\2\2\u01b9\u01b4\3\2\2\2\u01ba-\3\2\2\2\u01bb\u01bc\5\30\r\2"+
		"\u01bc\u01bd\6\30\6\2\u01bd\u01be\7\6\2\2\u01be\u01bf\7\7\2\2\u01bf/\3"+
		"\2\2\2\u01c0\u01c1\7\6\2\2\u01c1\u01c2\5\62\32\2\u01c2\u01c3\7\7\2\2\u01c3"+
		"\61\3\2\2\2\u01c4\u01c9\5\24\13\2\u01c5\u01c6\7\r\2\2\u01c6\u01c8\5\24"+
		"\13\2\u01c7\u01c5\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9"+
		"\u01ca\3\2\2\2\u01ca\63\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01ce\5\6\4"+
		"\2\u01cd\u01cc\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d1"+
		"\7\b\2\2\u01d0\u01d2\5D#\2\u01d1\u01d0\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d3\u01d4\7\t\2\2\u01d4\u01d5\7\67\2\2\u01d5\u01d6\5"+
		"\24\13\2\u01d6\65\3\2\2\2\u01d7\u01d9\7G\2\2\u01d8\u01da\5\6\4\2\u01d9"+
		"\u01d8\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dd\7\b"+
		"\2\2\u01dc\u01de\5D#\2\u01dd\u01dc\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01df"+
		"\3\2\2\2\u01df\u01e0\7\t\2\2\u01e0\u01e1\7\67\2\2\u01e1\u01e2\5\24\13"+
		"\2\u01e2\67\3\2\2\2\u01e3\u01e4\7D\2\2\u01e4\u01e5\5:\36\2\u01e59\3\2"+
		"\2\2\u01e6\u01f1\7\177\2\2\u01e7\u01e8\5\u0112\u008a\2\u01e8\u01e9\7\22"+
		"\2\2\u01e9\u01eb\3\2\2\2\u01ea\u01e7\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec"+
		"\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ef\5\u0112"+
		"\u008a\2\u01ef\u01f1\3\2\2\2\u01f0\u01e6\3\2\2\2\u01f0\u01ea\3\2\2\2\u01f1"+
		";\3\2\2\2\u01f2\u01f4\7]\2\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f7\5\u00f6|\2\u01f6\u01f8\7\17\2\2\u01f7\u01f6"+
		"\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01fa\3\2\2\2\u01f9\u01fb\5> \2\u01fa"+
		"\u01f9\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fe\3\2\2\2\u01fc\u01fd\7\67"+
		"\2\2\u01fd\u01ff\5\24\13\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff"+
		"=\3\2\2\2\u0200\u0201\7\20\2\2\u0201\u0202\5\24\13\2\u0202?\3\2\2\2\u0203"+
		"\u0205\5\6\4\2\u0204\u0203\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\3\2"+
		"\2\2\u0206\u0208\7\b\2\2\u0207\u0209\5D#\2\u0208\u0207\3\2\2\2\u0208\u0209"+
		"\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u020c\7\t\2\2\u020b\u020d\5> \2\u020c"+
		"\u020b\3\2\2\2\u020c\u020d\3\2\2\2\u020dA\3\2\2\2\u020e\u020f\t\4\2\2"+
		"\u020fC\3\2\2\2\u0210\u022b\5N(\2\u0211\u0216\5\32\16\2\u0212\u0213\7"+
		"\r\2\2\u0213\u0215\5\32\16\2\u0214\u0212\3\2\2\2\u0215\u0218\3\2\2\2\u0216"+
		"\u0214\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u022b\3\2\2\2\u0218\u0216\3\2"+
		"\2\2\u0219\u021c\5J&\2\u021a\u021b\7\r\2\2\u021b\u021d\5N(\2\u021c\u021a"+
		"\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u022b\3\2\2\2\u021e\u0228\5F$\2\u021f"+
		"\u0226\7\r\2\2\u0220\u0223\5J&\2\u0221\u0222\7\r\2\2\u0222\u0224\5N(\2"+
		"\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0227\3\2\2\2\u0225\u0227"+
		"\5N(\2\u0226\u0220\3\2\2\2\u0226\u0225\3\2\2\2\u0227\u0229\3\2\2\2\u0228"+
		"\u021f\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022b\3\2\2\2\u022a\u0210\3\2"+
		"\2\2\u022a\u0211\3\2\2\2\u022a\u0219\3\2\2\2\u022a\u021e\3\2\2\2\u022b"+
		"E\3\2\2\2\u022c\u0231\5H%\2\u022d\u022e\7\r\2\2\u022e\u0230\5H%\2\u022f"+
		"\u022d\3\2\2\2\u0230\u0233\3\2\2\2\u0231\u022f\3\2\2\2\u0231\u0232\3\2"+
		"\2\2\u0232G\3\2\2\2\u0233\u0231\3\2\2\2\u0234\u0236\5r:\2\u0235\u0234"+
		"\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0238\3\2\2\2\u0237\u0239\5B\"\2\u0238"+
		"\u0237\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023c\5P"+
		")\2\u023b\u023d\5> \2\u023c\u023b\3\2\2\2\u023c\u023d\3\2\2\2\u023dI\3"+
		"\2\2\2\u023e\u0243\5L\'\2\u023f\u0240\7\r\2\2\u0240\u0242\5L\'\2\u0241"+
		"\u023f\3\2\2\2\u0242\u0245\3\2\2\2\u0243\u0241\3\2\2\2\u0243\u0244\3\2"+
		"\2\2\u0244K\3\2\2\2\u0245\u0243\3\2\2\2\u0246\u0248\5r:\2\u0247\u0246"+
		"\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u024a\3\2\2\2\u0249\u024b\5B\"\2\u024a"+
		"\u0249\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u0255\5P"+
		")\2\u024d\u024f\7\17\2\2\u024e\u0250\5> \2\u024f\u024e\3\2\2\2\u024f\u0250"+
		"\3\2\2\2\u0250\u0256\3\2\2\2\u0251\u0253\5> \2\u0252\u0251\3\2\2\2\u0252"+
		"\u0253\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0256\5\2\2\2\u0255\u024d\3\2"+
		"\2\2\u0255\u0252\3\2\2\2\u0256M\3\2\2\2\u0257\u0258\7\21\2\2\u0258\u0259"+
		"\5\u0102\u0082\2\u0259O\3\2\2\2\u025a\u025d\5\u0112\u008a\2\u025b\u025d"+
		"\5\4\3\2\u025c\u025a\3\2\2\2\u025c\u025b\3\2\2\2\u025dQ\3\2\2\2\u025e"+
		"\u0260\7G\2\2\u025f\u0261\5\6\4\2\u0260\u025f\3\2\2\2\u0260\u0261\3\2"+
		"\2\2\u0261\u0262\3\2\2\2\u0262\u0264\7\b\2\2\u0263\u0265\5D#\2\u0264\u0263"+
		"\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0266\3\2\2\2\u0266\u0268\7\t\2\2\u0267"+
		"\u0269\5> \2\u0268\u0267\3\2\2\2\u0268\u0269\3\2\2\2\u0269S\3\2\2\2\u026a"+
		"\u026b\7\6\2\2\u026b\u026c\7\177\2\2\u026c\u026d\7\20\2\2\u026d\u026e"+
		"\t\5\2\2\u026e\u026f\7\7\2\2\u026f\u0270\5> \2\u0270U\3\2\2\2\u0271\u0273"+
		"\5\u00f6|\2\u0272\u0274\7\17\2\2\u0273\u0272\3\2\2\2\u0273\u0274\3\2\2"+
		"\2\u0274\u0275\3\2\2\2\u0275\u0276\5@!\2\u0276W\3\2\2\2\u0277\u0278\7"+
		"t\2\2\u0278\u027a\7\177\2\2\u0279\u027b\5\6\4\2\u027a\u0279\3\2\2\2\u027a"+
		"\u027b\3\2\2\2\u027b\u027c\3\2\2\2\u027c\u027d\7\16\2\2\u027d\u027e\5"+
		"\24\13\2\u027e\u027f\7\f\2\2\u027fY\3\2\2\2\u0280\u0282\5B\"\2\u0281\u0280"+
		"\3\2\2\2\u0281\u0282\3\2\2\2\u0282\u0283\3\2\2\2\u0283\u0284\7w\2\2\u0284"+
		"\u0286\7\b\2\2\u0285\u0287\5\u00dep\2\u0286\u0285\3\2\2\2\u0286\u0287"+
		"\3\2\2\2\u0287\u0288\3\2\2\2\u0288\u028e\7\t\2\2\u0289\u028a\7\n\2\2\u028a"+
		"\u028b\5\u00e4s\2\u028b\u028c\7\13\2\2\u028c\u028f\3\2\2\2\u028d\u028f"+
		"\7\f\2\2\u028e\u0289\3\2\2\2\u028e\u028d\3\2\2\2\u028e\u028f\3\2\2\2\u028f"+
		"[\3\2\2\2\u0290\u0292\7d\2\2\u0291\u0290\3\2\2\2\u0291\u0292\3\2\2\2\u0292"+
		"\u0294\3\2\2\2\u0293\u0295\7{\2\2\u0294\u0293\3\2\2\2\u0294\u0295\3\2"+
		"\2\2\u0295\u0296\3\2\2\2\u0296\u0297\7j\2\2\u0297\u0299\7\177\2\2\u0298"+
		"\u029a\5\6\4\2\u0299\u0298\3\2\2\2\u0299\u029a\3\2\2\2\u029a\u029c\3\2"+
		"\2\2\u029b\u029d\5^\60\2\u029c\u029b\3\2\2\2\u029c\u029d\3\2\2\2\u029d"+
		"\u029e\3\2\2\2\u029e\u02a0\5&\24\2\u029f\u02a1\7\f\2\2\u02a0\u029f\3\2"+
		"\2\2\u02a0\u02a1\3\2\2\2\u02a1]\3\2\2\2\u02a2\u02a3\7a\2\2\u02a3\u02a4"+
		"\5`\61\2\u02a4_\3\2\2\2\u02a5\u02aa\5\34\17\2\u02a6\u02a7\7\r\2\2\u02a7"+
		"\u02a9\5\34\17\2\u02a8\u02a6\3\2\2\2\u02a9\u02ac\3\2\2\2\u02aa\u02a8\3"+
		"\2\2\2\u02aa\u02ab\3\2\2\2\u02aba\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ad\u02af"+
		"\7c\2\2\u02ae\u02ad\3\2\2\2\u02ae\u02af\3\2\2\2\u02af\u02b0\3\2\2\2\u02b0"+
		"\u02b1\7`\2\2\u02b1\u02b2\7\177\2\2\u02b2\u02b4\7\n\2\2\u02b3\u02b5\5"+
		"d\63\2\u02b4\u02b3\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6"+
		"\u02b7\7\13\2\2\u02b7c\3\2\2\2\u02b8\u02ba\5f\64\2\u02b9\u02bb\7\r\2\2"+
		"\u02ba\u02b9\3\2\2\2\u02ba\u02bb\3\2\2\2\u02bbe\3\2\2\2\u02bc\u02c1\5"+
		"h\65\2\u02bd\u02be\7\r\2\2\u02be\u02c0\5h\65\2\u02bf\u02bd\3\2\2\2\u02c0"+
		"\u02c3\3\2\2\2\u02c1\u02bf\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2g\3\2\2\2"+
		"\u02c3\u02c1\3\2\2\2\u02c4\u02c7\5\u00f6|\2\u02c5\u02c6\7\16\2\2\u02c6"+
		"\u02c8\5\u0102\u0082\2\u02c7\u02c5\3\2\2\2\u02c7\u02c8\3\2\2\2\u02c8i"+
		"\3\2\2\2\u02c9\u02ca\7x\2\2\u02ca\u02cb\5l\67\2\u02cb\u02cd\7\n\2\2\u02cc"+
		"\u02ce\5\u0082B\2\u02cd\u02cc\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02cf"+
		"\3\2\2\2\u02cf\u02d0\7\13\2\2\u02d0k\3\2\2\2\u02d1\u02da\7\177\2\2\u02d2"+
		"\u02d4\7\22\2\2\u02d3\u02d2\3\2\2\2\u02d4\u02d5\3\2\2\2\u02d5\u02d3\3"+
		"\2\2\2\u02d5\u02d6\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02d9\7\177\2\2\u02d8"+
		"\u02d3\3\2\2\2\u02d9\u02dc\3\2\2\2\u02da\u02d8\3\2\2\2\u02da\u02db\3\2"+
		"\2\2\u02dbm\3\2\2\2\u02dc\u02da\3\2\2\2\u02dd\u02de\7\177\2\2\u02de\u02df"+
		"\7\16\2\2\u02df\u02e0\5l\67\2\u02e0\u02e1\7\f\2\2\u02e1\u02ea\3\2\2\2"+
		"\u02e2\u02e3\7\177\2\2\u02e3\u02e4\7\16\2\2\u02e4\u02e5\7y\2\2\u02e5\u02e6"+
		"\7\b\2\2\u02e6\u02e7\7\u0080\2\2\u02e7\u02e8\7\t\2\2\u02e8\u02ea\7\f\2"+
		"\2\u02e9\u02dd\3\2\2\2\u02e9\u02e2\3\2\2\2\u02eao\3\2\2\2\u02eb\u02ec"+
		"\7\u0080\2\2\u02ecq\3\2\2\2\u02ed\u02ef\5t;\2\u02ee\u02ed\3\2\2\2\u02ef"+
		"\u02f0\3\2\2\2\u02f0\u02ee\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1s\3\2\2\2"+
		"\u02f2\u02f5\7~\2\2\u02f3\u02f6\5v<\2\u02f4\u02f6\5x=\2\u02f5\u02f3\3"+
		"\2\2\2\u02f5\u02f4\3\2\2\2\u02f6u\3\2\2\2\u02f7\u02f8\b<\1\2\u02f8\u02fe"+
		"\7\177\2\2\u02f9\u02fa\7\b\2\2\u02fa\u02fb\5\u0102\u0082\2\u02fb\u02fc"+
		"\7\t\2\2\u02fc\u02fe\3\2\2\2\u02fd\u02f7\3\2\2\2\u02fd\u02f9\3\2\2\2\u02fe"+
		"\u0304\3\2\2\2\u02ff\u0300\f\4\2\2\u0300\u0301\7\22\2\2\u0301\u0303\5"+
		"\u0112\u008a\2\u0302\u02ff\3\2\2\2\u0303\u0306\3\2\2\2\u0304\u0302\3\2"+
		"\2\2\u0304\u0305\3\2\2\2\u0305w\3\2\2\2\u0306\u0304\3\2\2\2\u0307\u0308"+
		"\5v<\2\u0308\u0309\5\u00f8}\2\u0309y\3\2\2\2\u030a\u030c\5\u00e6t\2\u030b"+
		"\u030a\3\2\2\2\u030b\u030c\3\2\2\2\u030c\u030d\3\2\2\2\u030d\u030e\7\2"+
		"\2\3\u030e{\3\2\2\2\u030f\u0311\7d\2\2\u0310\u030f\3\2\2\2\u0310\u0311"+
		"\3\2\2\2\u0311\u0312\3\2\2\2\u0312\u0313\5~@\2\u0313}\3\2\2\2\u0314\u0333"+
		"\5\u0080A\2\u0315\u0333\5\u008eH\2\u0316\u0333\5\u0086D\2\u0317\u0333"+
		"\5\u008cG\2\u0318\u0333\5\u0094K\2\u0319\u0333\5\u0084C\2\u031a\u0333"+
		"\5r:\2\u031b\u0333\5\u00c0a\2\u031c\u0333\5\\/\2\u031d\u0333\5j\66\2\u031e"+
		"\u0333\5\u0098M\2\u031f\u0333\5\u009aN\2\u0320\u0333\5\u009eP\2\u0321"+
		"\u0333\5\u00a0Q\2\u0322\u0333\5\u00a2R\2\u0323\u0333\5\u00a4S\2\u0324"+
		"\u0333\5\u00a6T\2\u0325\u0333\5\u00b2Z\2\u0326\u0333\5\u00a8U\2\u0327"+
		"\u0333\5\u00b4[\2\u0328\u0333\5\u00b6\\\2\u0329\u0333\5\u00bc_\2\u032a"+
		"\u0333\5\u00be`\2\u032b\u0333\5\u0106\u0084\2\u032c\u0333\5\u00d4k\2\u032d"+
		"\u0333\5X-\2\u032e\u0333\5b\62\2\u032f\u0333\5\u0096L\2\u0330\u0331\7"+
		"d\2\2\u0331\u0333\5~@\2\u0332\u0314\3\2\2\2\u0332\u0315\3\2\2\2\u0332"+
		"\u0316\3\2\2\2\u0332\u0317\3\2\2\2\u0332\u0318\3\2\2\2\u0332\u0319\3\2"+
		"\2\2\u0332\u031a\3\2\2\2\u0332\u031b\3\2\2\2\u0332\u031c\3\2\2\2\u0332"+
		"\u031d\3\2\2\2\u0332\u031e\3\2\2\2\u0332\u031f\3\2\2\2\u0332\u0320\3\2"+
		"\2\2\u0332\u0321\3\2\2\2\u0332\u0322\3\2\2\2\u0332\u0323\3\2\2\2\u0332"+
		"\u0324\3\2\2\2\u0332\u0325\3\2\2\2\u0332\u0326\3\2\2\2\u0332\u0327\3\2"+
		"\2\2\u0332\u0328\3\2\2\2\u0332\u0329\3\2\2\2\u0332\u032a\3\2\2\2\u0332"+
		"\u032b\3\2\2\2\u0332\u032c\3\2\2\2\u0332\u032d\3\2\2\2\u0332\u032e\3\2"+
		"\2\2\u0332\u032f\3\2\2\2\u0332\u0330\3\2\2\2\u0333\177\3\2\2\2\u0334\u0336"+
		"\7\n\2\2\u0335\u0337\5\u0082B\2\u0336\u0335\3\2\2\2\u0336\u0337\3\2\2"+
		"\2\u0337\u0338\3\2\2\2\u0338\u0339\7\13\2\2\u0339\u0081\3\2\2\2\u033a"+
		"\u033c\5~@\2\u033b\u033a\3\2\2\2\u033c\u033d\3\2\2\2\u033d\u033b\3\2\2"+
		"\2\u033d\u033e\3\2\2\2\u033e\u0083\3\2\2\2\u033f\u0343\7|\2\2\u0340\u0341"+
		"\7\177\2\2\u0341\u0344\5@!\2\u0342\u0344\5\u008eH\2\u0343\u0340\3\2\2"+
		"\2\u0343\u0342\3\2\2\2\u0344\u0345\3\2\2\2\u0345\u0346\5\u011e\u0090\2"+
		"\u0346\u0085\3\2\2\2\u0347\u034b\7e\2\2\u0348\u034c\5\u0088E\2\u0349\u034c"+
		"\5n8\2\u034a\u034c\5p9\2\u034b\u0348\3\2\2\2\u034b\u0349\3\2\2\2\u034b"+
		"\u034a\3\2\2\2\u034c\u0087\3\2\2\2\u034d\u0352\79\2\2\u034e\u0352\78\2"+
		"\2\u034f\u0352\7\31\2\2\u0350\u0352\5\u008aF\2\u0351\u034d\3\2\2\2\u0351"+
		"\u034e\3\2\2\2\u0351\u034f\3\2\2\2\u0351\u0350\3\2\2\2\u0352\u0355\3\2"+
		"\2\2\u0353\u0354\7[\2\2\u0354\u0356\5\u0112\u008a\2\u0355\u0353\3\2\2"+
		"\2\u0355\u0356\3\2\2\2\u0356\u0357\3\2\2\2\u0357\u0358\7\\\2\2\u0358\u0359"+
		"\7\u0080\2\2\u0359\u035a\5\u011e\u0090\2\u035a\u0089\3\2\2\2\u035b\u035c"+
		"\5\u0112\u008a\2\u035c\u035d\7\r\2\2\u035d\u035f\3\2\2\2\u035e\u035b\3"+
		"\2\2\2\u035e\u035f\3\2\2\2\u035f\u0360\3\2\2\2\u0360\u0361\7\n\2\2\u0361"+
		"\u0366\5\u0112\u008a\2\u0362\u0363\7\r\2\2\u0363\u0365\5\u0112\u008a\2"+
		"\u0364\u0362\3\2\2\2\u0365\u0368\3\2\2\2\u0366\u0364\3\2\2\2\u0366\u0367"+
		"\3\2\2\2\u0367\u0369\3\2\2\2\u0368\u0366\3\2\2\2\u0369\u036a\7\13\2\2"+
		"\u036a\u008b\3\2\2\2\u036b\u036d\7d\2\2\u036c\u036e\7U\2\2\u036d\u036c"+
		"\3\2\2\2\u036d\u036e\3\2\2\2\u036e\u0371\3\2\2\2\u036f\u0372\5\u0088E"+
		"\2\u0370\u0372\5~@\2\u0371\u036f\3\2\2\2\u0371\u0370\3\2\2\2\u0372\u008d"+
		"\3\2\2\2\u0373\u0375\5\4\3\2\u0374\u0376\5> \2\u0375\u0374\3\2\2\2\u0375"+
		"\u0376\3\2\2\2\u0376\u0377\3\2\2\2\u0377\u0379\5\2\2\2\u0378\u037a\7\f"+
		"\2\2\u0379\u0378\3\2\2\2\u0379\u037a\3\2\2\2\u037a\u0391\3\2\2\2\u037b"+
		"\u037d\5B\"\2\u037c\u037b\3\2\2\2\u037c\u037d\3\2\2\2\u037d\u037f\3\2"+
		"\2\2\u037e\u0380\5\u009cO\2\u037f\u037e\3\2\2\2\u037f\u0380\3\2\2\2\u0380"+
		"\u0382\3\2\2\2\u0381\u0383\7]\2\2\u0382\u0381\3\2\2\2\u0382\u0383\3\2"+
		"\2\2\u0383\u0384\3\2\2\2\u0384\u0386\5\u0090I\2\u0385\u0387\7\f\2\2\u0386"+
		"\u0385\3\2\2\2\u0386\u0387\3\2\2\2\u0387\u0391\3\2\2\2\u0388\u038a\7{"+
		"\2\2\u0389\u038b\5\u009cO\2\u038a\u0389\3\2\2\2\u038a\u038b\3\2\2\2\u038b"+
		"\u038c\3\2\2\2\u038c\u038e\5\u0090I\2\u038d\u038f\7\f\2\2\u038e\u038d"+
		"\3\2\2\2\u038e\u038f\3\2\2\2\u038f\u0391\3\2\2\2\u0390\u0373\3\2\2\2\u0390"+
		"\u037c\3\2\2\2\u0390\u0388\3\2\2\2\u0391\u008f\3\2\2\2\u0392\u0397\5\u0092"+
		"J\2\u0393\u0394\7\r\2\2\u0394\u0396\5\u0092J\2\u0395\u0393\3\2\2\2\u0396"+
		"\u0399\3\2\2\2\u0397\u0395\3\2\2\2\u0397\u0398\3\2\2\2\u0398\u0091\3\2"+
		"\2\2\u0399\u0397\3\2\2\2\u039a\u039e\5\u0114\u008b\2\u039b\u039e\5\u00e8"+
		"u\2\u039c\u039e\5\u00eex\2\u039d\u039a\3\2\2\2\u039d\u039b\3\2\2\2\u039d"+
		"\u039c\3\2\2\2\u039e\u03a0\3\2\2\2\u039f\u03a1\5> \2\u03a0\u039f\3\2\2"+
		"\2\u03a0\u03a1\3\2\2\2\u03a1\u03a3\3\2\2\2\u03a2\u03a4\5\u0102\u0082\2"+
		"\u03a3\u03a2\3\2\2\2\u03a3\u03a4\3\2\2\2\u03a4\u03aa\3\2\2\2\u03a5\u03a7"+
		"\7\16\2\2\u03a6\u03a8\5\6\4\2\u03a7\u03a6\3\2\2\2\u03a7\u03a8\3\2\2\2"+
		"\u03a8\u03a9\3\2\2\2\u03a9\u03ab\5\u0102\u0082\2\u03aa\u03a5\3\2\2\2\u03aa"+
		"\u03ab\3\2\2\2\u03ab\u0093\3\2\2\2\u03ac\u03ad\7\f\2\2\u03ad\u0095\3\2"+
		"\2\2\u03ae\u03af\6L\b\2\u03af\u03b1\5\u00fe\u0080\2\u03b0\u03b2\7\f\2"+
		"\2\u03b1\u03b0\3\2\2\2\u03b1\u03b2\3\2\2\2\u03b2\u0097\3\2\2\2\u03b3\u03b4"+
		"\7V\2\2\u03b4\u03b5\7\b\2\2\u03b5\u03b6\5\u00fe\u0080\2\u03b6\u03b7\7"+
		"\t\2\2\u03b7\u03ba\5~@\2\u03b8\u03b9\7F\2\2\u03b9\u03bb\5~@\2\u03ba\u03b8"+
		"\3\2\2\2\u03ba\u03bb\3\2\2\2\u03bb\u0099\3\2\2\2\u03bc\u03bd\7B\2\2\u03bd"+
		"\u03be\5~@\2\u03be\u03bf\7P\2\2\u03bf\u03c0\7\b\2\2\u03c0\u03c1\5\u00fe"+
		"\u0080\2\u03c1\u03c2\7\t\2\2\u03c2\u03c3\5\u011e\u0090\2\u03c3\u0402\3"+
		"\2\2\2\u03c4\u03c5\7P\2\2\u03c5\u03c6\7\b\2\2\u03c6\u03c7\5\u00fe\u0080"+
		"\2\u03c7\u03c8\7\t\2\2\u03c8\u03c9\5~@\2\u03c9\u0402\3\2\2\2\u03ca\u03cb"+
		"\7N\2\2\u03cb\u03cd\7\b\2\2\u03cc\u03ce\5\u00fe\u0080\2\u03cd\u03cc\3"+
		"\2\2\2\u03cd\u03ce\3\2\2\2\u03ce\u03cf\3\2\2\2\u03cf\u03d1\7\f\2\2\u03d0"+
		"\u03d2\5\u00fe\u0080\2\u03d1\u03d0\3\2\2\2\u03d1\u03d2\3\2\2\2\u03d2\u03d3"+
		"\3\2\2\2\u03d3\u03d5\7\f\2\2\u03d4\u03d6\5\u00fe\u0080\2\u03d5\u03d4\3"+
		"\2\2\2\u03d5\u03d6\3\2\2\2\u03d6\u03d7\3\2\2\2\u03d7\u03d8\7\t\2\2\u03d8"+
		"\u0402\5~@\2\u03d9\u03da\7N\2\2\u03da\u03db\7\b\2\2\u03db\u03dc\5\u009c"+
		"O\2\u03dc\u03dd\5\u0090I\2\u03dd\u03df\7\f\2\2\u03de\u03e0\5\u00fe\u0080"+
		"\2\u03df\u03de\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0\u03e1\3\2\2\2\u03e1\u03e3"+
		"\7\f\2\2\u03e2\u03e4\5\u00fe\u0080\2\u03e3\u03e2\3\2\2\2\u03e3\u03e4\3"+
		"\2\2\2\u03e4\u03e5\3\2\2\2\u03e5\u03e6\7\t\2\2\u03e6\u03e7\5~@\2\u03e7"+
		"\u0402\3\2\2\2\u03e8\u03e9\7N\2\2\u03e9\u03ea\7\b\2\2\u03ea\u03ee\5\u0102"+
		"\u0082\2\u03eb\u03ef\7Y\2\2\u03ec\u03ed\7\177\2\2\u03ed\u03ef\6N\t\2\u03ee"+
		"\u03eb\3\2\2\2\u03ee\u03ec\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0\u03f1\5\u00fe"+
		"\u0080\2\u03f1\u03f2\7\t\2\2\u03f2\u03f3\5~@\2\u03f3\u0402\3\2\2\2\u03f4"+
		"\u03f5\7N\2\2\u03f5\u03f6\7\b\2\2\u03f6\u03f7\5\u009cO\2\u03f7\u03fb\5"+
		"\u0092J\2\u03f8\u03fc\7Y\2\2\u03f9\u03fa\7\177\2\2\u03fa\u03fc\6N\n\2"+
		"\u03fb\u03f8\3\2\2\2\u03fb\u03f9\3\2\2\2\u03fc\u03fd\3\2\2\2\u03fd\u03fe"+
		"\5\u00fe\u0080\2\u03fe\u03ff\7\t\2\2\u03ff\u0400\5~@\2\u0400\u0402\3\2"+
		"\2\2\u0401\u03bc\3\2\2\2\u0401\u03c4\3\2\2\2\u0401\u03ca\3\2\2\2\u0401"+
		"\u03d9\3\2\2\2\u0401\u03e8\3\2\2\2\u0401\u03f4\3\2\2\2\u0402\u009b\3\2"+
		"\2\2\u0403\u0404\t\6\2\2\u0404\u009d\3\2\2\2\u0405\u0408\7M\2\2\u0406"+
		"\u0407\6P\13\2\u0407\u0409\7\177\2\2\u0408\u0406\3\2\2\2\u0408\u0409\3"+
		"\2\2\2\u0409\u040a\3\2\2\2\u040a\u040b\5\u011e\u0090\2\u040b\u009f\3\2"+
		"\2\2\u040c\u040f\7A\2\2\u040d\u040e\6Q\f\2\u040e\u0410\7\177\2\2\u040f"+
		"\u040d\3\2\2\2\u040f\u0410\3\2\2\2\u0410\u0411\3\2\2\2\u0411\u0412\5\u011e"+
		"\u0090\2\u0412\u00a1\3\2\2\2\u0413\u0416\7K\2\2\u0414\u0415\6R\r\2\u0415"+
		"\u0417\5\u00fe\u0080\2\u0416\u0414\3\2\2\2\u0416\u0417\3\2\2\2\u0417\u0418"+
		"\3\2\2\2\u0418\u0419\5\u011e\u0090\2\u0419\u00a3\3\2\2\2\u041a\u041d\7"+
		"n\2\2\u041b\u041c\6S\16\2\u041c\u041e\5\u00fe\u0080\2\u041d\u041b\3\2"+
		"\2\2\u041d\u041e\3\2\2\2\u041e\u041f\3\2\2\2\u041f\u0420\5\u011e\u0090"+
		"\2\u0420\u00a5\3\2\2\2\u0421\u0422\7T\2\2\u0422\u0423\7\b\2\2\u0423\u0424"+
		"\5\u00fe\u0080\2\u0424\u0425\7\t\2\2\u0425\u0426\5~@\2\u0426\u00a7\3\2"+
		"\2\2\u0427\u0428\7O\2\2\u0428\u0429\7\b\2\2\u0429\u042a\5\u00fe\u0080"+
		"\2\u042a\u042b\7\t\2\2\u042b\u042c\5\u00aaV\2\u042c\u00a9\3\2\2\2\u042d"+
		"\u042f\7\n\2\2\u042e\u0430\5\u00acW\2\u042f\u042e\3\2\2\2\u042f\u0430"+
		"\3\2\2\2\u0430\u0435\3\2\2\2\u0431\u0433\5\u00b0Y\2\u0432\u0434\5\u00ac"+
		"W\2\u0433\u0432\3\2\2\2\u0433\u0434\3\2\2\2\u0434\u0436\3\2\2\2\u0435"+
		"\u0431\3\2\2\2\u0435\u0436\3\2\2\2\u0436\u0437\3\2\2\2\u0437\u0438\7\13"+
		"\2\2\u0438\u00ab\3\2\2\2\u0439\u043b\5\u00aeX\2\u043a\u0439\3\2\2\2\u043b"+
		"\u043c\3\2\2\2\u043c\u043a\3\2\2\2\u043c\u043d\3\2\2\2\u043d\u00ad\3\2"+
		"\2\2\u043e\u043f\7E\2\2\u043f\u0440\5\u00fe\u0080\2\u0440\u0442\7\20\2"+
		"\2\u0441\u0443\5\u0082B\2\u0442\u0441\3\2\2\2\u0442\u0443\3\2\2\2\u0443"+
		"\u00af\3\2\2\2\u0444\u0445\7U\2\2\u0445\u0447\7\20\2\2\u0446\u0448\5\u0082"+
		"B\2\u0447\u0446\3\2\2\2\u0447\u0448\3\2\2\2\u0448\u00b1\3\2\2\2\u0449"+
		"\u044a\7\177\2\2\u044a\u044b\7\20\2\2\u044b\u044c\5~@\2\u044c\u00b3\3"+
		"\2\2\2\u044d\u044e\7W\2\2\u044e\u044f\6[\17\2\u044f\u0450\5\u00fe\u0080"+
		"\2\u0450\u0451\5\u011e\u0090\2\u0451\u00b5\3\2\2\2\u0452\u0453\7Z\2\2"+
		"\u0453\u0459\5\u0080A\2\u0454\u0456\5\u00b8]\2\u0455\u0457\5\u00ba^\2"+
		"\u0456\u0455\3\2\2\2\u0456\u0457\3\2\2\2\u0457\u045a\3\2\2\2\u0458\u045a"+
		"\5\u00ba^\2\u0459\u0454\3\2\2\2\u0459\u0458\3\2\2\2\u045a\u00b7\3\2\2"+
		"\2\u045b\u045c\7I\2\2\u045c\u045d\7\b\2\2\u045d\u045e\7\177\2\2\u045e"+
		"\u045f\7\t\2\2\u045f\u0460\5\u0080A\2\u0460\u00b9\3\2\2\2\u0461\u0462"+
		"\7J\2\2\u0462\u0463\5\u0080A\2\u0463\u00bb\3\2\2\2\u0464\u0465\7Q\2\2"+
		"\u0465\u0466\5\u011e\u0090\2\u0466\u00bd\3\2\2\2\u0467\u0468\7R\2\2\u0468"+
		"\u0469\7\177\2\2\u0469\u046f\5@!\2\u046a\u046b\7\n\2\2\u046b\u046c\5\u00e4"+
		"s\2\u046c\u046d\7\13\2\2\u046d\u0470\3\2\2\2\u046e\u0470\7\f\2\2\u046f"+
		"\u046a\3\2\2\2\u046f\u046e\3\2\2\2\u0470\u00bf\3\2\2\2\u0471\u0473\7|"+
		"\2\2\u0472\u0471\3\2\2\2\u0472\u0473\3\2\2\2\u0473\u0474\3\2\2\2\u0474"+
		"\u0475\7_\2\2\u0475\u0477\7\177\2\2\u0476\u0478\5\6\4\2\u0477\u0476\3"+
		"\2\2\2\u0477\u0478\3\2\2\2\u0478\u0479\3\2\2\2\u0479\u047a\5\u00c2b\2"+
		"\u047a\u047b\5\u00c4c\2\u047b\u00c1\3\2\2\2\u047c\u047e\5\u00c6d\2\u047d"+
		"\u047c\3\2\2\2\u047d\u047e\3\2\2\2\u047e\u0480\3\2\2\2\u047f\u0481\5\u00c8"+
		"e\2\u0480\u047f\3\2\2\2\u0480\u0481\3\2\2\2\u0481\u00c3\3\2\2\2\u0482"+
		"\u0486\7\n\2\2\u0483\u0485\5\u00caf\2\u0484\u0483\3\2\2\2\u0485\u0488"+
		"\3\2\2\2\u0486\u0484\3\2\2\2\u0486\u0487\3\2\2\2\u0487\u0489\3\2\2\2\u0488"+
		"\u0486\3\2\2\2\u0489\u048a\7\13\2\2\u048a\u00c5\3\2\2\2\u048b\u048c\7"+
		"a\2\2\u048c\u048d\5\34\17\2\u048d\u00c7\3\2\2\2\u048e\u048f\7f\2\2\u048f"+
		"\u0490\5`\61\2\u0490\u00c9\3\2\2\2\u0491\u0499\5Z.\2\u0492\u0494\5r:\2"+
		"\u0493\u0492\3\2\2\2\u0493\u0494\3\2\2\2\u0494\u0495\3\2\2\2\u0495\u0499"+
		"\5\u00ccg\2\u0496\u0499\5\u00d0i\2\u0497\u0499\5~@\2\u0498\u0491\3\2\2"+
		"\2\u0498\u0493\3\2\2\2\u0498\u0496\3\2\2\2\u0498\u0497\3\2\2\2\u0499\u00cb"+
		"\3\2\2\2\u049a\u049b\5\u00ceh\2\u049b\u049d\5\u00f6|\2\u049c\u049e\7\17"+
		"\2\2\u049d\u049c\3\2\2\2\u049d\u049e\3\2\2\2\u049e\u04a0\3\2\2\2\u049f"+
		"\u04a1\5> \2\u04a0\u049f\3\2\2\2\u04a0\u04a1\3\2\2\2\u04a1\u04a3\3\2\2"+
		"\2\u04a2\u04a4\5\2\2\2\u04a3\u04a2\3\2\2\2\u04a3\u04a4\3\2\2\2\u04a4\u04a5"+
		"\3\2\2\2\u04a5\u04a6\7\f\2\2\u04a6\u04b8\3\2\2\2\u04a7\u04a8\5\u00ceh"+
		"\2\u04a8\u04a9\5\u00f6|\2\u04a9\u04af\5@!\2\u04aa\u04ab\7\n\2\2\u04ab"+
		"\u04ac\5\u00e4s\2\u04ac\u04ad\7\13\2\2\u04ad\u04b0\3\2\2\2\u04ae\u04b0"+
		"\7\f\2\2\u04af\u04aa\3\2\2\2\u04af\u04ae\3\2\2\2\u04b0\u04b8\3\2\2\2\u04b1"+
		"\u04b4\5\u00ceh\2\u04b2\u04b5\5\u00f2z\2\u04b3\u04b5\5\u00f4{\2\u04b4"+
		"\u04b2\3\2\2\2\u04b4\u04b3\3\2\2\2\u04b5\u04b8\3\2\2\2\u04b6\u04b8\5\u0084"+
		"C\2\u04b7\u049a\3\2\2\2\u04b7\u04a7\3\2\2\2\u04b7\u04b1\3\2\2\2\u04b7"+
		"\u04b6\3\2\2\2\u04b8\u00cd\3\2\2\2\u04b9\u04bb\7^\2\2\u04ba\u04b9\3\2"+
		"\2\2\u04ba\u04bb\3\2\2\2\u04bb\u04bd\3\2\2\2\u04bc\u04be\5B\"\2\u04bd"+
		"\u04bc\3\2\2\2\u04bd\u04be\3\2\2\2\u04be\u04c0\3\2\2\2\u04bf\u04c1\7m"+
		"\2\2\u04c0\u04bf\3\2\2\2\u04c0\u04c1\3\2\2\2\u04c1\u04c3\3\2\2\2\u04c2"+
		"\u04c4\7]\2\2\u04c3\u04c2\3\2\2\2\u04c3\u04c4\3\2\2\2\u04c4\u00cf\3\2"+
		"\2\2\u04c5\u04c6\5T+\2\u04c6\u04c7\7\f\2\2\u04c7\u00d1\3\2\2\2\u04c8\u04ca"+
		"\7\31\2\2\u04c9\u04c8\3\2\2\2\u04c9\u04ca\3\2\2\2\u04ca\u04cb\3\2\2\2"+
		"\u04cb\u04cc\7\177\2\2\u04cc\u04ce\7\b\2\2\u04cd\u04cf\5\u00dep\2\u04ce"+
		"\u04cd\3\2\2\2\u04ce\u04cf\3\2\2\2\u04cf\u04d0\3\2\2\2\u04d0\u04d1\7\t"+
		"\2\2\u04d1\u04d2\7\n\2\2\u04d2\u04d3\5\u00e4s\2\u04d3\u04d4\7\13\2\2\u04d4"+
		"\u00d3\3\2\2\2\u04d5\u04d6\7R\2\2\u04d6\u04d8\7\31\2\2\u04d7\u04d9\7\177"+
		"\2\2\u04d8\u04d7\3\2\2\2\u04d8\u04d9\3\2\2\2\u04d9\u04da\3\2\2\2\u04da"+
		"\u04dc\7\b\2\2\u04db\u04dd\5\u00dep\2\u04dc\u04db\3\2\2\2\u04dc\u04dd"+
		"\3\2\2\2\u04dd\u04de\3\2\2\2\u04de\u04df\7\t\2\2\u04df\u04e0\7\n\2\2\u04e0"+
		"\u04e1\5\u00e4s\2\u04e1\u04e2\7\13\2\2\u04e2\u00d5\3\2\2\2\u04e3\u04e4"+
		"\7\n\2\2\u04e4\u04e9\5\u00d8m\2\u04e5\u04e6\7\r\2\2\u04e6\u04e8\5\u00d8"+
		"m\2\u04e7\u04e5\3\2\2\2\u04e8\u04eb\3\2\2\2\u04e9\u04e7\3\2\2\2\u04e9"+
		"\u04ea\3\2\2\2\u04ea\u04ed\3\2\2\2\u04eb\u04e9\3\2\2\2\u04ec\u04ee\7\r"+
		"\2\2\u04ed\u04ec\3\2\2\2\u04ed\u04ee\3\2\2\2\u04ee\u04ef\3\2\2\2\u04ef"+
		"\u04f0\7\13\2\2\u04f0\u00d7\3\2\2\2\u04f1\u04f2\7\31\2\2\u04f2\u04f3\5"+
		"\u00dco\2\u04f3\u00d9\3\2\2\2\u04f4\u04f5\7\n\2\2\u04f5\u04fa\5\u00dc"+
		"o\2\u04f6\u04f7\7\r\2\2\u04f7\u04f9\5\u00dco\2\u04f8\u04f6\3\2\2\2\u04f9"+
		"\u04fc\3\2\2\2\u04fa\u04f8\3\2\2\2\u04fa\u04fb\3\2\2\2\u04fb\u04fe\3\2"+
		"\2\2\u04fc\u04fa\3\2\2\2\u04fd\u04ff\7\r\2\2\u04fe\u04fd\3\2\2\2\u04fe"+
		"\u04ff\3\2\2\2\u04ff\u0500\3\2\2\2\u0500\u0501\7\13\2\2\u0501\u00db\3"+
		"\2\2\2\u0502\u0503\7\6\2\2\u0503\u0504\5\u0102\u0082\2\u0504\u0505\7\7"+
		"\2\2\u0505\u0507\7\b\2\2\u0506\u0508\5\u00dep\2\u0507\u0506\3\2\2\2\u0507"+
		"\u0508\3\2\2\2\u0508\u0509\3\2\2\2\u0509\u050a\7\t\2\2\u050a\u050b\7\n"+
		"\2\2\u050b\u050c\5\u00e4s\2\u050c\u050d\7\13\2\2\u050d\u00dd\3\2\2\2\u050e"+
		"\u0513\5\u00e0q\2\u050f\u0510\7\r\2\2\u0510\u0512\5\u00e0q\2\u0511\u050f"+
		"\3\2\2\2\u0512\u0515\3\2\2\2\u0513\u0511\3\2\2\2\u0513\u0514\3\2\2\2\u0514"+
		"\u0518\3\2\2\2\u0515\u0513\3\2\2\2\u0516\u0517\7\r\2\2\u0517\u0519\5\u00e2"+
		"r\2\u0518\u0516\3\2\2\2\u0518\u0519\3\2\2\2\u0519\u0522\3\2\2\2\u051a"+
		"\u0522\5\u00e2r\2\u051b\u0522\5\u00e8u\2\u051c\u051f\5\u00eex\2\u051d"+
		"\u051e\7\20\2\2\u051e\u0520\5\u00dep\2\u051f\u051d\3\2\2\2\u051f\u0520"+
		"\3\2\2\2\u0520\u0522\3\2\2\2\u0521\u050e\3\2\2\2\u0521\u051a\3\2\2\2\u0521"+
		"\u051b\3\2\2\2\u0521\u051c\3\2\2\2\u0522\u00df\3\2\2\2\u0523\u0525\5t"+
		";\2\u0524\u0523\3\2\2\2\u0524\u0525\3\2\2\2\u0525\u0527\3\2\2\2\u0526"+
		"\u0528\5B\"\2\u0527\u0526\3\2\2\2\u0527\u0528\3\2\2\2\u0528\u0529\3\2"+
		"\2\2\u0529\u052b\5\u0114\u008b\2\u052a\u052c\7\17\2\2\u052b\u052a\3\2"+
		"\2\2\u052b\u052c\3\2\2\2\u052c\u052e\3\2\2\2\u052d\u052f\5> \2\u052e\u052d"+
		"\3\2\2\2\u052e\u052f\3\2\2\2\u052f\u0532\3\2\2\2\u0530\u0531\7\16\2\2"+
		"\u0531\u0533\5\u0102\u0082\2\u0532\u0530\3\2\2\2\u0532\u0533\3\2\2\2\u0533"+
		"\u00e1\3\2\2\2\u0534\u0535\7\21\2\2\u0535\u0536\7\177\2\2\u0536\u00e3"+
		"\3\2\2\2\u0537\u0539\5\u00e6t\2\u0538\u0537\3\2\2\2\u0538\u0539\3\2\2"+
		"\2\u0539\u00e5\3\2\2\2\u053a\u053c\5|?\2\u053b\u053a\3\2\2\2\u053c\u053d"+
		"\3\2\2\2\u053d\u053b\3\2\2\2\u053d\u053e\3\2\2\2\u053e\u00e7\3\2\2\2\u053f"+
		"\u0541\7\6\2\2\u0540\u0542\5\u00eav\2\u0541\u0540\3\2\2\2\u0541\u0542"+
		"\3\2\2\2\u0542\u0543\3\2\2\2\u0543\u0544\7\7\2\2\u0544\u00e9\3\2\2\2\u0545"+
		"\u054e\5\u00ecw\2\u0546\u0548\7\r\2\2\u0547\u0546\3\2\2\2\u0548\u0549"+
		"\3\2\2\2\u0549\u0547\3\2\2\2\u0549\u054a\3\2\2\2\u054a\u054b\3\2\2\2\u054b"+
		"\u054d\5\u00ecw\2\u054c\u0547\3\2\2\2\u054d\u0550\3\2\2\2\u054e\u054c"+
		"\3\2\2\2\u054e\u054f\3\2\2\2\u054f\u00eb\3\2\2\2\u0550\u054e\3\2\2\2\u0551"+
		"\u0553\7\21\2\2\u0552\u0551\3\2\2\2\u0552\u0553\3\2\2\2\u0553\u0556\3"+
		"\2\2\2\u0554\u0557\5\u0102\u0082\2\u0555\u0557\7\177\2\2\u0556\u0554\3"+
		"\2\2\2\u0556\u0555\3\2\2\2\u0557\u0559\3\2\2\2\u0558\u055a\7\r\2\2\u0559"+
		"\u0558\3\2\2\2\u0559\u055a\3\2\2\2\u055a\u00ed\3\2\2\2\u055b\u0564\7\n"+
		"\2\2\u055c\u0561\5\u00f0y\2\u055d\u055e\7\r\2\2\u055e\u0560\5\u00f0y\2"+
		"\u055f\u055d\3\2\2\2\u0560\u0563\3\2\2\2\u0561\u055f\3\2\2\2\u0561\u0562"+
		"\3\2\2\2\u0562\u0565\3\2\2\2\u0563\u0561\3\2\2\2\u0564\u055c\3\2\2\2\u0564"+
		"\u0565\3\2\2\2\u0565\u0567\3\2\2\2\u0566\u0568\7\r\2\2\u0567\u0566\3\2"+
		"\2\2\u0567\u0568\3\2\2\2\u0568\u0569\3\2\2\2\u0569\u056a\7\13\2\2\u056a"+
		"\u00ef\3\2\2\2\u056b\u056c\5\u00f6|\2\u056c\u056d\t\7\2\2\u056d\u056e"+
		"\5\u0102\u0082\2\u056e\u057b\3\2\2\2\u056f\u0570\7\6\2\2\u0570\u0571\5"+
		"\u0102\u0082\2\u0571\u0572\7\7\2\2\u0572\u0573\7\20\2\2\u0573\u0574\5"+
		"\u0102\u0082\2\u0574\u057b\3\2\2\2\u0575\u057b\5\u00f2z\2\u0576\u057b"+
		"\5\u00f4{\2\u0577\u057b\5\u00d2j\2\u0578\u057b\5\u0114\u008b\2\u0579\u057b"+
		"\5N(\2\u057a\u056b\3\2\2\2\u057a\u056f\3\2\2\2\u057a\u0575\3\2\2\2\u057a"+
		"\u0576\3\2\2\2\u057a\u0577\3\2\2\2\u057a\u0578\3\2\2\2\u057a\u0579\3\2"+
		"\2\2\u057b\u00f1\3\2\2\2\u057c\u057d\5\u011a\u008e\2\u057d\u057e\7\b\2"+
		"\2\u057e\u0580\7\t\2\2\u057f\u0581\5> \2\u0580\u057f\3\2\2\2\u0580\u0581"+
		"\3\2\2\2\u0581\u0582\3\2\2\2\u0582\u0583\7\n\2\2\u0583\u0584\5\u00e4s"+
		"\2\u0584\u0585\7\13\2\2\u0585\u00f3\3\2\2\2\u0586\u0587\5\u011c\u008f"+
		"\2\u0587\u058a\7\b\2\2\u0588\u058b\7\177\2\2\u0589\u058b\5\4\3\2\u058a"+
		"\u0588\3\2\2\2\u058a\u0589\3\2\2\2\u058b\u058d\3\2\2\2\u058c\u058e\5>"+
		" \2\u058d\u058c\3\2\2\2\u058d\u058e\3\2\2\2\u058e\u058f\3\2\2\2\u058f"+
		"\u0590\7\t\2\2\u0590\u0591\7\n\2\2\u0591\u0592\5\u00e4s\2\u0592\u0593"+
		"\7\13\2\2\u0593\u00f5\3\2\2\2\u0594\u0598\5\u0112\u008a\2\u0595\u0598"+
		"\7\u0080\2\2\u0596\u0598\5\u0110\u0089\2\u0597\u0594\3\2\2\2\u0597\u0595"+
		"\3\2\2\2\u0597\u0596\3\2\2\2\u0598\u00f7\3\2\2\2\u0599\u059e\7\b\2\2\u059a"+
		"\u059c\5\u00fa~\2\u059b\u059d\7\r\2\2\u059c\u059b\3\2\2\2\u059c\u059d"+
		"\3\2\2\2\u059d\u059f\3\2\2\2\u059e\u059a\3\2\2\2\u059e\u059f\3\2\2\2\u059f"+
		"\u05a0\3\2\2\2\u05a0\u05a1\7\t\2\2\u05a1\u00f9\3\2\2\2\u05a2\u05a7\5\u00fc"+
		"\177\2\u05a3\u05a4\7\r\2\2\u05a4\u05a6\5\u00fc\177\2\u05a5\u05a3\3\2\2"+
		"\2\u05a6\u05a9\3\2\2\2\u05a7\u05a5\3\2\2\2\u05a7\u05a8\3\2\2\2\u05a8\u00fb"+
		"\3\2\2\2\u05a9\u05a7\3\2\2\2\u05aa\u05ac\7\21\2\2\u05ab\u05aa\3\2\2\2"+
		"\u05ab\u05ac\3\2\2\2\u05ac\u05af\3\2\2\2\u05ad\u05b0\5\u0102\u0082\2\u05ae"+
		"\u05b0\7\177\2\2\u05af\u05ad\3\2\2\2\u05af\u05ae\3\2\2\2\u05b0\u00fd\3"+
		"\2\2\2\u05b1\u05b6\5\u0102\u0082\2\u05b2\u05b3\7\r\2\2\u05b3\u05b5\5\u0102"+
		"\u0082\2\u05b4\u05b2\3\2\2\2\u05b5\u05b8\3\2\2\2\u05b6\u05b4\3\2\2\2\u05b6"+
		"\u05b7\3\2\2\2\u05b7\u00ff\3\2\2\2\u05b8\u05b6\3\2\2\2\u05b9\u05bb\7R"+
		"\2\2\u05ba\u05bc\7\177\2\2\u05bb\u05ba\3\2\2\2\u05bb\u05bc\3\2\2\2\u05bc"+
		"\u05bd\3\2\2\2\u05bd\u05bf\7\b\2\2\u05be\u05c0\5\u00dep\2\u05bf\u05be"+
		"\3\2\2\2\u05bf\u05c0\3\2\2\2\u05c0\u05c1\3\2\2\2\u05c1\u05c3\7\t\2\2\u05c2"+
		"\u05c4\5> \2\u05c3\u05c2\3\2\2\2\u05c3\u05c4\3\2\2\2\u05c4\u05c5\3\2\2"+
		"\2\u05c5\u05c6\7\n\2\2\u05c6\u05c7\5\u00e4s\2\u05c7\u05c8\7\13\2\2\u05c8"+
		"\u0101\3\2\2\2\u05c9\u05ca\b\u0082\1\2\u05ca\u0601\5\u0100\u0081\2\u05cb"+
		"\u0601\5\u0106\u0084\2\u05cc\u05ce\7_\2\2\u05cd\u05cf\7\177\2\2\u05ce"+
		"\u05cd\3\2\2\2\u05ce\u05cf\3\2\2\2\u05cf\u05d0\3\2\2\2\u05d0\u0601\5\u00c4"+
		"c\2\u05d1\u05d2\7G\2\2\u05d2\u05d4\5\u0102\u0082\2\u05d3\u05d5\5\16\b"+
		"\2\u05d4\u05d3\3\2\2\2\u05d4\u05d5\3\2\2\2\u05d5\u05d7\3\2\2\2\u05d6\u05d8"+
		"\5\u00f8}\2\u05d7\u05d6\3\2\2\2\u05d7\u05d8\3\2\2\2\u05d8\u0601\3\2\2"+
		"\2\u05d9\u05da\7X\2\2\u05da\u0601\5\u0102\u0082(\u05db\u05dc\7L\2\2\u05dc"+
		"\u0601\5\u0102\u0082\'\u05dd\u05de\7D\2\2\u05de\u0601\5\u0102\u0082&\u05df"+
		"\u05e0\7\23\2\2\u05e0\u0601\5\u0102\u0082%\u05e1\u05e2\7\24\2\2\u05e2"+
		"\u0601\5\u0102\u0082$\u05e3\u05e4\7\25\2\2\u05e4\u0601\5\u0102\u0082#"+
		"\u05e5\u05e6\7\26\2\2\u05e6\u0601\5\u0102\u0082\"\u05e7\u05e8\7\27\2\2"+
		"\u05e8\u0601\5\u0102\u0082!\u05e9\u05ea\7\30\2\2\u05ea\u0601\5\u0102\u0082"+
		" \u05eb\u0601\5\u00dan\2\u05ec\u0601\5\u00d6l\2\u05ed\u0601\5\u00d4k\2"+
		"\u05ee\u0601\5\u00a4S\2\u05ef\u0601\7S\2\2\u05f0\u05f2\5\u0112\u008a\2"+
		"\u05f1\u05f3\5\u0102\u0082\2\u05f2\u05f1\3\2\2\2\u05f2\u05f3\3\2\2\2\u05f3"+
		"\u0601\3\2\2\2\u05f4\u0601\7b\2\2\u05f5\u0601\5\u010e\u0088\2\u05f6\u0601"+
		"\5\u00e8u\2\u05f7\u0601\5\u00eex\2\u05f8\u05f9\7\b\2\2\u05f9\u05fa\5\u00fe"+
		"\u0080\2\u05fa\u05fb\7\t\2\2\u05fb\u0601\3\2\2\2\u05fc\u05fe\5\16\b\2"+
		"\u05fd\u05ff\5\u00fe\u0080\2\u05fe\u05fd\3\2\2\2\u05fe\u05ff\3\2\2\2\u05ff"+
		"\u0601\3\2\2\2\u0600\u05c9\3\2\2\2\u0600\u05cb\3\2\2\2\u0600\u05cc\3\2"+
		"\2\2\u0600\u05d1\3\2\2\2\u0600\u05d9\3\2\2\2\u0600\u05db\3\2\2\2\u0600"+
		"\u05dd\3\2\2\2\u0600\u05df\3\2\2\2\u0600\u05e1\3\2\2\2\u0600\u05e3\3\2"+
		"\2\2\u0600\u05e5\3\2\2\2\u0600\u05e7\3\2\2\2\u0600\u05e9\3\2\2\2\u0600"+
		"\u05eb\3\2\2\2\u0600\u05ec\3\2\2\2\u0600\u05ed\3\2\2\2\u0600\u05ee\3\2"+
		"\2\2\u0600\u05ef\3\2\2\2\u0600\u05f0\3\2\2\2\u0600\u05f4\3\2\2\2\u0600"+
		"\u05f5\3\2\2\2\u0600\u05f6\3\2\2\2\u0600\u05f7\3\2\2\2\u0600\u05f8\3\2"+
		"\2\2\u0600\u05fc\3\2\2\2\u0601\u064d\3\2\2\2\u0602\u0603\f\37\2\2\u0603"+
		"\u0604\t\b\2\2\u0604\u064c\5\u0102\u0082 \u0605\u0606\f\36\2\2\u0606\u0607"+
		"\t\t\2\2\u0607\u064c\5\u0102\u0082\37\u0608\u0609\f\35\2\2\u0609\u060a"+
		"\t\n\2\2\u060a\u064c\5\u0102\u0082\36\u060b\u060c\f\34\2\2\u060c\u060d"+
		"\t\13\2\2\u060d\u064c\5\u0102\u0082\35\u060e\u060f\f\33\2\2\u060f\u0610"+
		"\7C\2\2\u0610\u064c\5\u0102\u0082\34\u0611\u0612\f\32\2\2\u0612\u0613"+
		"\7Y\2\2\u0613\u064c\5\u0102\u0082\33\u0614\u0615\f\31\2\2\u0615\u0616"+
		"\t\f\2\2\u0616\u064c\5\u0102\u0082\32\u0617\u0618\f\30\2\2\u0618\u0619"+
		"\7\'\2\2\u0619\u064c\5\u0102\u0082\31\u061a\u061b\f\27\2\2\u061b\u061c"+
		"\7(\2\2\u061c\u064c\5\u0102\u0082\30\u061d\u061e\f\26\2\2\u061e\u061f"+
		"\7)\2\2\u061f\u064c\5\u0102\u0082\27\u0620\u0621\f\25\2\2\u0621\u0622"+
		"\7*\2\2\u0622\u064c\5\u0102\u0082\26\u0623\u0624\f\24\2\2\u0624\u0625"+
		"\7+\2\2\u0625\u064c\5\u0102\u0082\25\u0626\u0627\f\23\2\2\u0627\u0628"+
		"\7\17\2\2\u0628\u0629\5\u0102\u0082\2\u0629\u062a\7\20\2\2\u062a\u062b"+
		"\5\u0102\u0082\24\u062b\u064c\3\2\2\2\u062c\u062d\f\22\2\2\u062d\u062e"+
		"\7\16\2\2\u062e\u064c\5\u0102\u0082\23\u062f\u0630\f\21\2\2\u0630\u0631"+
		"\5\u010c\u0087\2\u0631\u0632\5\u0102\u0082\22\u0632\u064c\3\2\2\2\u0633"+
		"\u0634\f.\2\2\u0634\u0635\7\6\2\2\u0635\u0636\5\u00fe\u0080\2\u0636\u0637"+
		"\7\7\2\2\u0637\u064c\3\2\2\2\u0638\u0639\f-\2\2\u0639\u063a\7\22\2\2\u063a"+
		"\u063c\5\u0112\u008a\2\u063b\u063d\5\36\20\2\u063c\u063b\3\2\2\2\u063c"+
		"\u063d\3\2\2\2\u063d\u064c\3\2\2\2\u063e\u063f\f,\2\2\u063f\u064c\5\u00f8"+
		"}\2\u0640\u0641\f*\2\2\u0641\u0642\6\u0082#\2\u0642\u064c\7\23\2\2\u0643"+
		"\u0644\f)\2\2\u0644\u0645\6\u0082%\2\u0645\u064c\7\24\2\2\u0646\u0647"+
		"\f\20\2\2\u0647\u064c\7\u0081\2\2\u0648\u0649\f\3\2\2\u0649\u064a\7[\2"+
		"\2\u064a\u064c\5\u0104\u0083\2\u064b\u0602\3\2\2\2\u064b\u0605\3\2\2\2"+
		"\u064b\u0608\3\2\2\2\u064b\u060b\3\2\2\2\u064b\u060e\3\2\2\2\u064b\u0611"+
		"\3\2\2\2\u064b\u0614\3\2\2\2\u064b\u0617\3\2\2\2\u064b\u061a\3\2\2\2\u064b"+
		"\u061d\3\2\2\2\u064b\u0620\3\2\2\2\u064b\u0623\3\2\2\2\u064b\u0626\3\2"+
		"\2\2\u064b\u062c\3\2\2\2\u064b\u062f\3\2\2\2\u064b\u0633\3\2\2\2\u064b"+
		"\u0638\3\2\2\2\u064b\u063e\3\2\2\2\u064b\u0640\3\2\2\2\u064b\u0643\3\2"+
		"\2\2\u064b\u0646\3\2\2\2\u064b\u0648\3\2\2\2\u064c\u064f\3\2\2\2\u064d"+
		"\u064b\3\2\2\2\u064d\u064e\3\2\2\2\u064e\u0103\3\2\2\2\u064f\u064d\3\2"+
		"\2\2\u0650\u0653\5\32\16\2\u0651\u0652\7\6\2\2\u0652\u0654\7\7\2\2\u0653"+
		"\u0651\3\2\2\2\u0653\u0654\3\2\2\2\u0654\u0657\3\2\2\2\u0655\u0657\5\u0102"+
		"\u0082\2\u0656\u0650\3\2\2\2\u0656\u0655\3\2\2\2\u0657\u0105\3\2\2\2\u0658"+
		"\u065a\7^\2\2\u0659\u0658\3\2\2\2\u0659\u065a\3\2\2\2\u065a\u065b\3\2"+
		"\2\2\u065b\u065d\5\u0108\u0085\2\u065c\u065e\5> \2\u065d\u065c\3\2\2\2"+
		"\u065d\u065e\3\2\2\2\u065e\u065f\3\2\2\2\u065f\u0660\7\67\2\2\u0660\u0661"+
		"\5\u010a\u0086\2\u0661\u0107\3\2\2\2\u0662\u0669\7\177\2\2\u0663\u0665"+
		"\7\b\2\2\u0664\u0666\5\u00dep\2\u0665\u0664\3\2\2\2\u0665\u0666\3\2\2"+
		"\2\u0666\u0667\3\2\2\2\u0667\u0669\7\t\2\2\u0668\u0662\3\2\2\2\u0668\u0663"+
		"\3\2\2\2\u0669\u0109\3\2\2\2\u066a\u0670\5\u0102\u0082\2\u066b\u066c\7"+
		"\n\2\2\u066c\u066d\5\u00e4s\2\u066d\u066e\7\13\2\2\u066e\u0670\3\2\2\2"+
		"\u066f\u066a\3\2\2\2\u066f\u066b\3\2\2\2\u0670\u010b\3\2\2\2\u0671\u0672"+
		"\t\r\2\2\u0672\u010d\3\2\2\2\u0673\u067a\7:\2\2\u0674\u067a\7;\2\2\u0675"+
		"\u067a\7\u0080\2\2\u0676\u067a\7\u0081\2\2\u0677\u067a\7\5\2\2\u0678\u067a"+
		"\5\u0110\u0089\2\u0679\u0673\3\2\2\2\u0679\u0674\3\2\2\2\u0679\u0675\3"+
		"\2\2\2\u0679\u0676\3\2\2\2\u0679\u0677\3\2\2\2\u0679\u0678\3\2\2\2\u067a"+
		"\u010f\3\2\2\2\u067b\u067c\t\16\2\2\u067c\u0111\3\2\2\2\u067d\u0680\7"+
		"\177\2\2\u067e\u0680\5\u0116\u008c\2\u067f\u067d\3\2\2\2\u067f\u067e\3"+
		"\2\2\2\u0680\u0113\3\2\2\2\u0681\u0682\t\17\2\2\u0682\u0115\3\2\2\2\u0683"+
		"\u0687\5\u0118\u008d\2\u0684\u0687\7:\2\2\u0685\u0687\7;\2\2\u0686\u0683"+
		"\3\2\2\2\u0686\u0684\3\2\2\2\u0686\u0685\3\2\2\2\u0687\u0117\3\2\2\2\u0688"+
		"\u0689\t\20\2\2\u0689\u0119\3\2\2\2\u068a\u068b\7\177\2\2\u068b\u068c"+
		"\6\u008e(\2\u068c\u068d\5\u00f6|\2\u068d\u011b\3\2\2\2\u068e\u068f\7\177"+
		"\2\2\u068f\u0690\6\u008f)\2\u0690\u0691\5\u00f6|\2\u0691\u011d\3\2\2\2"+
		"\u0692\u0697\7\f\2\2\u0693\u0697\7\2\2\3\u0694\u0697\6\u0090*\2\u0695"+
		"\u0697\6\u0090+\2\u0696\u0692\3\2\2\2\u0696\u0693\3\2\2\2\u0696\u0694"+
		"\3\2\2\2\u0696\u0695\3\2\2\2\u0697\u011f\3\2\2\2\u00d4\u0125\u0129\u0132"+
		"\u0137\u013a\u0141\u014a\u0154\u015f\u0161\u0176\u017e\u0185\u0189\u0198"+
		"\u019c\u01a0\u01a6\u01ad\u01b7\u01b9\u01c9\u01cd\u01d1\u01d9\u01dd\u01ec"+
		"\u01f0\u01f3\u01f7\u01fa\u01fe\u0204\u0208\u020c\u0216\u021c\u0223\u0226"+
		"\u0228\u022a\u0231\u0235\u0238\u023c\u0243\u0247\u024a\u024f\u0252\u0255"+
		"\u025c\u0260\u0264\u0268\u0273\u027a\u0281\u0286\u028e\u0291\u0294\u0299"+
		"\u029c\u02a0\u02aa\u02ae\u02b4\u02ba\u02c1\u02c7\u02cd\u02d5\u02da\u02e9"+
		"\u02f0\u02f5\u02fd\u0304\u030b\u0310\u0332\u0336\u033d\u0343\u034b\u0351"+
		"\u0355\u035e\u0366\u036d\u0371\u0375\u0379\u037c\u037f\u0382\u0386\u038a"+
		"\u038e\u0390\u0397\u039d\u03a0\u03a3\u03a7\u03aa\u03b1\u03ba\u03cd\u03d1"+
		"\u03d5\u03df\u03e3\u03ee\u03fb\u0401\u0408\u040f\u0416\u041d\u042f\u0433"+
		"\u0435\u043c\u0442\u0447\u0456\u0459\u046f\u0472\u0477\u047d\u0480\u0486"+
		"\u0493\u0498\u049d\u04a0\u04a3\u04af\u04b4\u04b7\u04ba\u04bd\u04c0\u04c3"+
		"\u04c9\u04ce\u04d8\u04dc\u04e9\u04ed\u04fa\u04fe\u0507\u0513\u0518\u051f"+
		"\u0521\u0524\u0527\u052b\u052e\u0532\u0538\u053d\u0541\u0549\u054e\u0552"+
		"\u0556\u0559\u0561\u0564\u0567\u057a\u0580\u058a\u058d\u0597\u059c\u059e"+
		"\u05a7\u05ab\u05af\u05b6\u05bb\u05bf\u05c3\u05ce\u05d4\u05d7\u05f2\u05fe"+
		"\u0600\u063c\u064b\u064d\u0653\u0656\u0659\u065d\u0665\u0668\u066f\u0679"+
		"\u067f\u0686\u0696";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}