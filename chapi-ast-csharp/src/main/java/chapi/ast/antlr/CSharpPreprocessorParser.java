// Generated from CSharpPreprocessorParser.g4 by ANTLR 4.8
package chapi.ast.antlr;
import java.util.Stack;
import java.util.HashSet;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CSharpPreprocessorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BYTE_ORDER_MARK=1, SINGLE_LINE_DOC_COMMENT=2, DELIMITED_DOC_COMMENT=3, 
		SINGLE_LINE_COMMENT=4, DELIMITED_COMMENT=5, WHITESPACES=6, SHARP=7, ABSTRACT=8, 
		ADD=9, ALIAS=10, ARGLIST=11, AS=12, ASCENDING=13, ASYNC=14, AWAIT=15, 
		BASE=16, BOOL=17, BREAK=18, BY=19, BYTE=20, CASE=21, CATCH=22, CHAR=23, 
		CHECKED=24, CLASS=25, CONST=26, CONTINUE=27, DECIMAL=28, DEFAULT=29, DELEGATE=30, 
		DESCENDING=31, DO=32, DOUBLE=33, DYNAMIC=34, ELSE=35, ENUM=36, EQUALS=37, 
		EVENT=38, EXPLICIT=39, EXTERN=40, FALSE=41, FINALLY=42, FIXED=43, FLOAT=44, 
		FOR=45, FOREACH=46, FROM=47, GET=48, GOTO=49, GROUP=50, IF=51, IMPLICIT=52, 
		IN=53, INT=54, INTERFACE=55, INTERNAL=56, INTO=57, IS=58, JOIN=59, LET=60, 
		LOCK=61, LONG=62, NAMEOF=63, NAMESPACE=64, NEW=65, NULL=66, OBJECT=67, 
		ON=68, OPERATOR=69, ORDERBY=70, OUT=71, OVERRIDE=72, PARAMS=73, PARTIAL=74, 
		PRIVATE=75, PROTECTED=76, PUBLIC=77, READONLY=78, REF=79, REMOVE=80, RETURN=81, 
		SBYTE=82, SEALED=83, SELECT=84, SET=85, SHORT=86, SIZEOF=87, STACKALLOC=88, 
		STATIC=89, STRING=90, STRUCT=91, SWITCH=92, THIS=93, THROW=94, TRUE=95, 
		TRY=96, TYPEOF=97, UINT=98, ULONG=99, UNCHECKED=100, UNSAFE=101, USHORT=102, 
		USING=103, VAR=104, VIRTUAL=105, VOID=106, VOLATILE=107, WHEN=108, WHERE=109, 
		WHILE=110, YIELD=111, IDENTIFIER=112, LITERAL_ACCESS=113, INTEGER_LITERAL=114, 
		HEX_INTEGER_LITERAL=115, REAL_LITERAL=116, CHARACTER_LITERAL=117, REGULAR_STRING=118, 
		VERBATIUM_STRING=119, INTERPOLATED_REGULAR_STRING_START=120, INTERPOLATED_VERBATIUM_STRING_START=121, 
		OPEN_BRACE=122, CLOSE_BRACE=123, OPEN_BRACKET=124, CLOSE_BRACKET=125, 
		OPEN_PARENS=126, CLOSE_PARENS=127, DOT=128, COMMA=129, COLON=130, SEMICOLON=131, 
		PLUS=132, MINUS=133, STAR=134, DIV=135, PERCENT=136, AMP=137, BITWISE_OR=138, 
		CARET=139, BANG=140, TILDE=141, ASSIGNMENT=142, LT=143, GT=144, INTERR=145, 
		DOUBLE_COLON=146, OP_COALESCING=147, OP_INC=148, OP_DEC=149, OP_AND=150, 
		OP_OR=151, OP_PTR=152, OP_EQ=153, OP_NE=154, OP_LE=155, OP_GE=156, OP_ADD_ASSIGNMENT=157, 
		OP_SUB_ASSIGNMENT=158, OP_MULT_ASSIGNMENT=159, OP_DIV_ASSIGNMENT=160, 
		OP_MOD_ASSIGNMENT=161, OP_AND_ASSIGNMENT=162, OP_OR_ASSIGNMENT=163, OP_XOR_ASSIGNMENT=164, 
		OP_LEFT_SHIFT=165, OP_LEFT_SHIFT_ASSIGNMENT=166, DOUBLE_CURLY_INSIDE=167, 
		OPEN_BRACE_INSIDE=168, REGULAR_CHAR_INSIDE=169, VERBATIUM_DOUBLE_QUOTE_INSIDE=170, 
		DOUBLE_QUOTE_INSIDE=171, REGULAR_STRING_INSIDE=172, VERBATIUM_INSIDE_STRING=173, 
		CLOSE_BRACE_INSIDE=174, FORMAT_STRING=175, DIRECTIVE_WHITESPACES=176, 
		DIGITS=177, DEFINE=178, UNDEF=179, ELIF=180, ENDIF=181, LINE=182, ERROR=183, 
		WARNING=184, REGION=185, ENDREGION=186, PRAGMA=187, DIRECTIVE_HIDDEN=188, 
		CONDITIONAL_SYMBOL=189, DIRECTIVE_NEW_LINE=190, TEXT=191, DOUBLE_CURLY_CLOSE_INSIDE=192;
	public static final int
		RULE_preprocessor_directive = 0, RULE_directive_new_line_or_sharp = 1, 
		RULE_preprocessor_expression = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"preprocessor_directive", "directive_new_line_or_sharp", "preprocessor_expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\u00EF\u00BB\u00BF'", null, null, null, null, null, "'#'", "'abstract'", 
			"'add'", "'alias'", "'__arglist'", "'as'", "'ascending'", "'async'", 
			"'await'", "'base'", "'bool'", "'break'", "'by'", "'byte'", "'case'", 
			"'catch'", "'char'", "'checked'", "'class'", "'const'", "'continue'", 
			"'decimal'", "'default'", "'delegate'", "'descending'", "'do'", "'double'", 
			"'dynamic'", "'else'", "'enum'", "'equals'", "'event'", "'explicit'", 
			"'extern'", "'false'", "'finally'", "'fixed'", "'float'", "'for'", "'foreach'", 
			"'from'", "'get'", "'goto'", "'group'", "'if'", "'implicit'", "'in'", 
			"'int'", "'interface'", "'internal'", "'into'", "'is'", "'join'", "'let'", 
			"'lock'", "'long'", "'nameof'", "'namespace'", "'new'", "'null'", "'object'", 
			"'on'", "'operator'", "'orderby'", "'out'", "'override'", "'params'", 
			"'partial'", "'private'", "'protected'", "'public'", "'readonly'", "'ref'", 
			"'remove'", "'return'", "'sbyte'", "'sealed'", "'select'", "'set'", "'short'", 
			"'sizeof'", "'stackalloc'", "'static'", "'string'", "'struct'", "'switch'", 
			"'this'", "'throw'", "'true'", "'try'", "'typeof'", "'uint'", "'ulong'", 
			"'unchecked'", "'unsafe'", "'ushort'", "'using'", "'var'", "'virtual'", 
			"'void'", "'volatile'", "'when'", "'where'", "'while'", "'yield'", null, 
			null, null, null, null, null, null, null, null, null, "'{'", "'}'", "'['", 
			"']'", "'('", "')'", "'.'", "','", "':'", "';'", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'&'", "'|'", "'^'", "'!'", "'~'", "'='", "'<'", "'>'", 
			"'?'", "'::'", "'??'", "'++'", "'--'", "'&&'", "'||'", "'->'", "'=='", 
			"'!='", "'<='", "'>='", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", 
			"'|='", "'^='", "'<<'", "'<<='", "'{{'", null, null, null, null, null, 
			null, null, null, null, null, "'define'", "'undef'", "'elif'", "'endif'", 
			"'line'", null, null, null, null, null, "'hidden'", null, null, null, 
			"'}}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BYTE_ORDER_MARK", "SINGLE_LINE_DOC_COMMENT", "DELIMITED_DOC_COMMENT", 
			"SINGLE_LINE_COMMENT", "DELIMITED_COMMENT", "WHITESPACES", "SHARP", "ABSTRACT", 
			"ADD", "ALIAS", "ARGLIST", "AS", "ASCENDING", "ASYNC", "AWAIT", "BASE", 
			"BOOL", "BREAK", "BY", "BYTE", "CASE", "CATCH", "CHAR", "CHECKED", "CLASS", 
			"CONST", "CONTINUE", "DECIMAL", "DEFAULT", "DELEGATE", "DESCENDING", 
			"DO", "DOUBLE", "DYNAMIC", "ELSE", "ENUM", "EQUALS", "EVENT", "EXPLICIT", 
			"EXTERN", "FALSE", "FINALLY", "FIXED", "FLOAT", "FOR", "FOREACH", "FROM", 
			"GET", "GOTO", "GROUP", "IF", "IMPLICIT", "IN", "INT", "INTERFACE", "INTERNAL", 
			"INTO", "IS", "JOIN", "LET", "LOCK", "LONG", "NAMEOF", "NAMESPACE", "NEW", 
			"NULL", "OBJECT", "ON", "OPERATOR", "ORDERBY", "OUT", "OVERRIDE", "PARAMS", 
			"PARTIAL", "PRIVATE", "PROTECTED", "PUBLIC", "READONLY", "REF", "REMOVE", 
			"RETURN", "SBYTE", "SEALED", "SELECT", "SET", "SHORT", "SIZEOF", "STACKALLOC", 
			"STATIC", "STRING", "STRUCT", "SWITCH", "THIS", "THROW", "TRUE", "TRY", 
			"TYPEOF", "UINT", "ULONG", "UNCHECKED", "UNSAFE", "USHORT", "USING", 
			"VAR", "VIRTUAL", "VOID", "VOLATILE", "WHEN", "WHERE", "WHILE", "YIELD", 
			"IDENTIFIER", "LITERAL_ACCESS", "INTEGER_LITERAL", "HEX_INTEGER_LITERAL", 
			"REAL_LITERAL", "CHARACTER_LITERAL", "REGULAR_STRING", "VERBATIUM_STRING", 
			"INTERPOLATED_REGULAR_STRING_START", "INTERPOLATED_VERBATIUM_STRING_START", 
			"OPEN_BRACE", "CLOSE_BRACE", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENS", 
			"CLOSE_PARENS", "DOT", "COMMA", "COLON", "SEMICOLON", "PLUS", "MINUS", 
			"STAR", "DIV", "PERCENT", "AMP", "BITWISE_OR", "CARET", "BANG", "TILDE", 
			"ASSIGNMENT", "LT", "GT", "INTERR", "DOUBLE_COLON", "OP_COALESCING", 
			"OP_INC", "OP_DEC", "OP_AND", "OP_OR", "OP_PTR", "OP_EQ", "OP_NE", "OP_LE", 
			"OP_GE", "OP_ADD_ASSIGNMENT", "OP_SUB_ASSIGNMENT", "OP_MULT_ASSIGNMENT", 
			"OP_DIV_ASSIGNMENT", "OP_MOD_ASSIGNMENT", "OP_AND_ASSIGNMENT", "OP_OR_ASSIGNMENT", 
			"OP_XOR_ASSIGNMENT", "OP_LEFT_SHIFT", "OP_LEFT_SHIFT_ASSIGNMENT", "DOUBLE_CURLY_INSIDE", 
			"OPEN_BRACE_INSIDE", "REGULAR_CHAR_INSIDE", "VERBATIUM_DOUBLE_QUOTE_INSIDE", 
			"DOUBLE_QUOTE_INSIDE", "REGULAR_STRING_INSIDE", "VERBATIUM_INSIDE_STRING", 
			"CLOSE_BRACE_INSIDE", "FORMAT_STRING", "DIRECTIVE_WHITESPACES", "DIGITS", 
			"DEFINE", "UNDEF", "ELIF", "ENDIF", "LINE", "ERROR", "WARNING", "REGION", 
			"ENDREGION", "PRAGMA", "DIRECTIVE_HIDDEN", "CONDITIONAL_SYMBOL", "DIRECTIVE_NEW_LINE", 
			"TEXT", "DOUBLE_CURLY_CLOSE_INSIDE"
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
	public String getGrammarFileName() { return "CSharpPreprocessorParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	Stack<Boolean> conditions = new Stack<Boolean>() {{ conditions.push(true); }};
	public HashSet<String> ConditionalSymbols = new HashSet<String>() {{ ConditionalSymbols.add("DEBUG"); }};

	private boolean allConditions() {
		for(boolean condition: conditions) {
			if (!condition)
				return false;
		}
		return true;
	}

	public CSharpPreprocessorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Preprocessor_directiveContext extends ParserRuleContext {
		public boolean value;
		public Preprocessor_directiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocessor_directive; }
	 
		public Preprocessor_directiveContext() { }
		public void copyFrom(Preprocessor_directiveContext ctx) {
			super.copyFrom(ctx);
			this.value = ctx.value;
		}
	}
	public static class PreprocessorDiagnosticContext extends Preprocessor_directiveContext {
		public TerminalNode ERROR() { return getToken(CSharpPreprocessorParser.ERROR, 0); }
		public TerminalNode TEXT() { return getToken(CSharpPreprocessorParser.TEXT, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public TerminalNode WARNING() { return getToken(CSharpPreprocessorParser.WARNING, 0); }
		public PreprocessorDiagnosticContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorDiagnostic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorDiagnostic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorDiagnostic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreprocessorRegionContext extends Preprocessor_directiveContext {
		public TerminalNode REGION() { return getToken(CSharpPreprocessorParser.REGION, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(CSharpPreprocessorParser.TEXT, 0); }
		public TerminalNode ENDREGION() { return getToken(CSharpPreprocessorParser.ENDREGION, 0); }
		public PreprocessorRegionContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorRegion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorRegion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorRegion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreprocessorDeclarationContext extends Preprocessor_directiveContext {
		public Token CONDITIONAL_SYMBOL;
		public TerminalNode DEFINE() { return getToken(CSharpPreprocessorParser.DEFINE, 0); }
		public TerminalNode CONDITIONAL_SYMBOL() { return getToken(CSharpPreprocessorParser.CONDITIONAL_SYMBOL, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public TerminalNode UNDEF() { return getToken(CSharpPreprocessorParser.UNDEF, 0); }
		public PreprocessorDeclarationContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreprocessorConditionalContext extends Preprocessor_directiveContext {
		public Preprocessor_expressionContext expr;
		public TerminalNode IF() { return getToken(CSharpPreprocessorParser.IF, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public Preprocessor_expressionContext preprocessor_expression() {
			return getRuleContext(Preprocessor_expressionContext.class,0);
		}
		public TerminalNode ELIF() { return getToken(CSharpPreprocessorParser.ELIF, 0); }
		public TerminalNode ELSE() { return getToken(CSharpPreprocessorParser.ELSE, 0); }
		public TerminalNode ENDIF() { return getToken(CSharpPreprocessorParser.ENDIF, 0); }
		public PreprocessorConditionalContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorConditional(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreprocessorPragmaContext extends Preprocessor_directiveContext {
		public TerminalNode PRAGMA() { return getToken(CSharpPreprocessorParser.PRAGMA, 0); }
		public TerminalNode TEXT() { return getToken(CSharpPreprocessorParser.TEXT, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public PreprocessorPragmaContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorPragma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorPragma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorPragma(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreprocessorLineContext extends Preprocessor_directiveContext {
		public TerminalNode LINE() { return getToken(CSharpPreprocessorParser.LINE, 0); }
		public Directive_new_line_or_sharpContext directive_new_line_or_sharp() {
			return getRuleContext(Directive_new_line_or_sharpContext.class,0);
		}
		public TerminalNode DIGITS() { return getToken(CSharpPreprocessorParser.DIGITS, 0); }
		public TerminalNode DEFAULT() { return getToken(CSharpPreprocessorParser.DEFAULT, 0); }
		public TerminalNode DIRECTIVE_HIDDEN() { return getToken(CSharpPreprocessorParser.DIRECTIVE_HIDDEN, 0); }
		public TerminalNode STRING() { return getToken(CSharpPreprocessorParser.STRING, 0); }
		public PreprocessorLineContext(Preprocessor_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessorLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessorLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessorLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Preprocessor_directiveContext preprocessor_directive() throws RecognitionException {
		Preprocessor_directiveContext _localctx = new Preprocessor_directiveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_preprocessor_directive);
		int _la;
		try {
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFINE:
				_localctx = new PreprocessorDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				match(DEFINE);
				setState(7);
				((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL = match(CONDITIONAL_SYMBOL);
				setState(8);
				directive_new_line_or_sharp();
				 ConditionalSymbols.add((((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL!=null?((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL.getText():null));
					   ((PreprocessorDeclarationContext)_localctx).value =  allConditions(); 
				}
				break;
			case UNDEF:
				_localctx = new PreprocessorDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				match(UNDEF);
				setState(12);
				((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL = match(CONDITIONAL_SYMBOL);
				setState(13);
				directive_new_line_or_sharp();
				 ConditionalSymbols.remove((((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL!=null?((PreprocessorDeclarationContext)_localctx).CONDITIONAL_SYMBOL.getText():null));
					   ((PreprocessorDeclarationContext)_localctx).value =  allConditions(); 
				}
				break;
			case IF:
				_localctx = new PreprocessorConditionalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(16);
				match(IF);
				setState(17);
				((PreprocessorConditionalContext)_localctx).expr = preprocessor_expression(0);
				setState(18);
				directive_new_line_or_sharp();
				 ((PreprocessorConditionalContext)_localctx).value =  ((PreprocessorConditionalContext)_localctx).expr.value.equals("true") && allConditions(); conditions.push(((PreprocessorConditionalContext)_localctx).expr.value.equals("true")); 
				}
				break;
			case ELIF:
				_localctx = new PreprocessorConditionalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(21);
				match(ELIF);
				setState(22);
				((PreprocessorConditionalContext)_localctx).expr = preprocessor_expression(0);
				setState(23);
				directive_new_line_or_sharp();
				 if (!conditions.peek()) { conditions.pop(); ((PreprocessorConditionalContext)_localctx).value =  ((PreprocessorConditionalContext)_localctx).expr.value.equals("true") && allConditions();
					     conditions.push(((PreprocessorConditionalContext)_localctx).expr.value.equals("true")); } else ((PreprocessorConditionalContext)_localctx).value =  false; 
				}
				break;
			case ELSE:
				_localctx = new PreprocessorConditionalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(26);
				match(ELSE);
				setState(27);
				directive_new_line_or_sharp();
				 if (!conditions.peek()) { conditions.pop(); ((PreprocessorConditionalContext)_localctx).value =  true && allConditions(); conditions.push(true); }
					    else ((PreprocessorConditionalContext)_localctx).value =  false; 
				}
				break;
			case ENDIF:
				_localctx = new PreprocessorConditionalContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(30);
				match(ENDIF);
				setState(31);
				directive_new_line_or_sharp();
				 conditions.pop(); ((PreprocessorConditionalContext)_localctx).value =  conditions.peek(); 
				}
				break;
			case LINE:
				_localctx = new PreprocessorLineContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(34);
				match(LINE);
				setState(41);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DIGITS:
					{
					setState(35);
					match(DIGITS);
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==STRING) {
						{
						setState(36);
						match(STRING);
						}
					}

					}
					break;
				case DEFAULT:
					{
					setState(39);
					match(DEFAULT);
					}
					break;
				case DIRECTIVE_HIDDEN:
					{
					setState(40);
					match(DIRECTIVE_HIDDEN);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(43);
				directive_new_line_or_sharp();
				 ((PreprocessorLineContext)_localctx).value =  allConditions(); 
				}
				break;
			case ERROR:
				_localctx = new PreprocessorDiagnosticContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(46);
				match(ERROR);
				setState(47);
				match(TEXT);
				setState(48);
				directive_new_line_or_sharp();
				 ((PreprocessorDiagnosticContext)_localctx).value =  allConditions(); 
				}
				break;
			case WARNING:
				_localctx = new PreprocessorDiagnosticContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(51);
				match(WARNING);
				setState(52);
				match(TEXT);
				setState(53);
				directive_new_line_or_sharp();
				 ((PreprocessorDiagnosticContext)_localctx).value =  allConditions(); 
				}
				break;
			case REGION:
				_localctx = new PreprocessorRegionContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(56);
				match(REGION);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXT) {
					{
					setState(57);
					match(TEXT);
					}
				}

				setState(60);
				directive_new_line_or_sharp();
				 ((PreprocessorRegionContext)_localctx).value =  allConditions(); 
				}
				break;
			case ENDREGION:
				_localctx = new PreprocessorRegionContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(63);
				match(ENDREGION);
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXT) {
					{
					setState(64);
					match(TEXT);
					}
				}

				setState(67);
				directive_new_line_or_sharp();
				 ((PreprocessorRegionContext)_localctx).value =  allConditions(); 
				}
				break;
			case PRAGMA:
				_localctx = new PreprocessorPragmaContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(70);
				match(PRAGMA);
				setState(71);
				match(TEXT);
				setState(72);
				directive_new_line_or_sharp();
				 ((PreprocessorPragmaContext)_localctx).value =  allConditions(); 
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

	public static class Directive_new_line_or_sharpContext extends ParserRuleContext {
		public TerminalNode DIRECTIVE_NEW_LINE() { return getToken(CSharpPreprocessorParser.DIRECTIVE_NEW_LINE, 0); }
		public TerminalNode EOF() { return getToken(CSharpPreprocessorParser.EOF, 0); }
		public Directive_new_line_or_sharpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_new_line_or_sharp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterDirective_new_line_or_sharp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitDirective_new_line_or_sharp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitDirective_new_line_or_sharp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Directive_new_line_or_sharpContext directive_new_line_or_sharp() throws RecognitionException {
		Directive_new_line_or_sharpContext _localctx = new Directive_new_line_or_sharpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_directive_new_line_or_sharp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==DIRECTIVE_NEW_LINE) ) {
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

	public static class Preprocessor_expressionContext extends ParserRuleContext {
		public String value;
		public Preprocessor_expressionContext expr1;
		public Token CONDITIONAL_SYMBOL;
		public Preprocessor_expressionContext expr;
		public Preprocessor_expressionContext expr2;
		public TerminalNode TRUE() { return getToken(CSharpPreprocessorParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CSharpPreprocessorParser.FALSE, 0); }
		public TerminalNode CONDITIONAL_SYMBOL() { return getToken(CSharpPreprocessorParser.CONDITIONAL_SYMBOL, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpPreprocessorParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpPreprocessorParser.CLOSE_PARENS, 0); }
		public List<Preprocessor_expressionContext> preprocessor_expression() {
			return getRuleContexts(Preprocessor_expressionContext.class);
		}
		public Preprocessor_expressionContext preprocessor_expression(int i) {
			return getRuleContext(Preprocessor_expressionContext.class,i);
		}
		public TerminalNode BANG() { return getToken(CSharpPreprocessorParser.BANG, 0); }
		public TerminalNode OP_EQ() { return getToken(CSharpPreprocessorParser.OP_EQ, 0); }
		public TerminalNode OP_NE() { return getToken(CSharpPreprocessorParser.OP_NE, 0); }
		public TerminalNode OP_AND() { return getToken(CSharpPreprocessorParser.OP_AND, 0); }
		public TerminalNode OP_OR() { return getToken(CSharpPreprocessorParser.OP_OR, 0); }
		public Preprocessor_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocessor_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).enterPreprocessor_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpPreprocessorParserListener ) ((CSharpPreprocessorParserListener)listener).exitPreprocessor_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpPreprocessorParserVisitor ) return ((CSharpPreprocessorParserVisitor<? extends T>)visitor).visitPreprocessor_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Preprocessor_expressionContext preprocessor_expression() throws RecognitionException {
		return preprocessor_expression(0);
	}

	private Preprocessor_expressionContext preprocessor_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Preprocessor_expressionContext _localctx = new Preprocessor_expressionContext(_ctx, _parentState);
		Preprocessor_expressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_preprocessor_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				{
				setState(80);
				match(TRUE);
				 ((Preprocessor_expressionContext)_localctx).value =  "true"; 
				}
				break;
			case FALSE:
				{
				setState(82);
				match(FALSE);
				 ((Preprocessor_expressionContext)_localctx).value =  "false"; 
				}
				break;
			case CONDITIONAL_SYMBOL:
				{
				setState(84);
				((Preprocessor_expressionContext)_localctx).CONDITIONAL_SYMBOL = match(CONDITIONAL_SYMBOL);
				 ((Preprocessor_expressionContext)_localctx).value =  ConditionalSymbols.contains((((Preprocessor_expressionContext)_localctx).CONDITIONAL_SYMBOL!=null?((Preprocessor_expressionContext)_localctx).CONDITIONAL_SYMBOL.getText():null)) ? "true" : "false"; 
				}
				break;
			case OPEN_PARENS:
				{
				setState(86);
				match(OPEN_PARENS);
				setState(87);
				((Preprocessor_expressionContext)_localctx).expr = preprocessor_expression(0);
				setState(88);
				match(CLOSE_PARENS);
				 ((Preprocessor_expressionContext)_localctx).value =  ((Preprocessor_expressionContext)_localctx).expr.value; 
				}
				break;
			case BANG:
				{
				setState(91);
				match(BANG);
				setState(92);
				((Preprocessor_expressionContext)_localctx).expr = preprocessor_expression(5);
				 ((Preprocessor_expressionContext)_localctx).value =  ((Preprocessor_expressionContext)_localctx).expr.value.equals("true") ? "false" : "true"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(117);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new Preprocessor_expressionContext(_parentctx, _parentState);
						_localctx.expr1 = _prevctx;
						_localctx.expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_preprocessor_expression);
						setState(97);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(98);
						match(OP_EQ);
						setState(99);
						((Preprocessor_expressionContext)_localctx).expr2 = preprocessor_expression(5);
						 ((Preprocessor_expressionContext)_localctx).value =  (((Preprocessor_expressionContext)_localctx).expr1.value == ((Preprocessor_expressionContext)_localctx).expr2.value ? "true" : "false"); 
						}
						break;
					case 2:
						{
						_localctx = new Preprocessor_expressionContext(_parentctx, _parentState);
						_localctx.expr1 = _prevctx;
						_localctx.expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_preprocessor_expression);
						setState(102);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(103);
						match(OP_NE);
						setState(104);
						((Preprocessor_expressionContext)_localctx).expr2 = preprocessor_expression(4);
						 ((Preprocessor_expressionContext)_localctx).value =  (((Preprocessor_expressionContext)_localctx).expr1.value != ((Preprocessor_expressionContext)_localctx).expr2.value ? "true" : "false"); 
						}
						break;
					case 3:
						{
						_localctx = new Preprocessor_expressionContext(_parentctx, _parentState);
						_localctx.expr1 = _prevctx;
						_localctx.expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_preprocessor_expression);
						setState(107);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(108);
						match(OP_AND);
						setState(109);
						((Preprocessor_expressionContext)_localctx).expr2 = preprocessor_expression(3);
						 ((Preprocessor_expressionContext)_localctx).value =  (((Preprocessor_expressionContext)_localctx).expr1.value.equals("true") && ((Preprocessor_expressionContext)_localctx).expr2.value.equals("true") ? "true" : "false"); 
						}
						break;
					case 4:
						{
						_localctx = new Preprocessor_expressionContext(_parentctx, _parentState);
						_localctx.expr1 = _prevctx;
						_localctx.expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_preprocessor_expression);
						setState(112);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(113);
						match(OP_OR);
						setState(114);
						((Preprocessor_expressionContext)_localctx).expr2 = preprocessor_expression(2);
						 ((Preprocessor_expressionContext)_localctx).value =  (((Preprocessor_expressionContext)_localctx).expr1.value.equals("true") || ((Preprocessor_expressionContext)_localctx).expr2.value.equals("true") ? "true" : "false"); 
						}
						break;
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return preprocessor_expression_sempred((Preprocessor_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean preprocessor_expression_sempred(Preprocessor_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00c2}\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2(\n\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2=\n\2\3\2\3\2\3\2\3\2\3\2\5\2D\n\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2N\n\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4b\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4x\n\4\f\4\16\4{\13\4"+
		"\3\4\2\3\6\5\2\4\6\2\3\3\3\u00c0\u00c0\2\u0091\2M\3\2\2\2\4O\3\2\2\2\6"+
		"a\3\2\2\2\b\t\7\u00b4\2\2\t\n\7\u00bf\2\2\n\13\5\4\3\2\13\f\b\2\1\2\f"+
		"N\3\2\2\2\r\16\7\u00b5\2\2\16\17\7\u00bf\2\2\17\20\5\4\3\2\20\21\b\2\1"+
		"\2\21N\3\2\2\2\22\23\7\65\2\2\23\24\5\6\4\2\24\25\5\4\3\2\25\26\b\2\1"+
		"\2\26N\3\2\2\2\27\30\7\u00b6\2\2\30\31\5\6\4\2\31\32\5\4\3\2\32\33\b\2"+
		"\1\2\33N\3\2\2\2\34\35\7%\2\2\35\36\5\4\3\2\36\37\b\2\1\2\37N\3\2\2\2"+
		" !\7\u00b7\2\2!\"\5\4\3\2\"#\b\2\1\2#N\3\2\2\2$+\7\u00b8\2\2%\'\7\u00b3"+
		"\2\2&(\7\\\2\2\'&\3\2\2\2\'(\3\2\2\2(,\3\2\2\2),\7\37\2\2*,\7\u00be\2"+
		"\2+%\3\2\2\2+)\3\2\2\2+*\3\2\2\2,-\3\2\2\2-.\5\4\3\2./\b\2\1\2/N\3\2\2"+
		"\2\60\61\7\u00b9\2\2\61\62\7\u00c1\2\2\62\63\5\4\3\2\63\64\b\2\1\2\64"+
		"N\3\2\2\2\65\66\7\u00ba\2\2\66\67\7\u00c1\2\2\678\5\4\3\289\b\2\1\29N"+
		"\3\2\2\2:<\7\u00bb\2\2;=\7\u00c1\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\5"+
		"\4\3\2?@\b\2\1\2@N\3\2\2\2AC\7\u00bc\2\2BD\7\u00c1\2\2CB\3\2\2\2CD\3\2"+
		"\2\2DE\3\2\2\2EF\5\4\3\2FG\b\2\1\2GN\3\2\2\2HI\7\u00bd\2\2IJ\7\u00c1\2"+
		"\2JK\5\4\3\2KL\b\2\1\2LN\3\2\2\2M\b\3\2\2\2M\r\3\2\2\2M\22\3\2\2\2M\27"+
		"\3\2\2\2M\34\3\2\2\2M \3\2\2\2M$\3\2\2\2M\60\3\2\2\2M\65\3\2\2\2M:\3\2"+
		"\2\2MA\3\2\2\2MH\3\2\2\2N\3\3\2\2\2OP\t\2\2\2P\5\3\2\2\2QR\b\4\1\2RS\7"+
		"a\2\2Sb\b\4\1\2TU\7+\2\2Ub\b\4\1\2VW\7\u00bf\2\2Wb\b\4\1\2XY\7\u0080\2"+
		"\2YZ\5\6\4\2Z[\7\u0081\2\2[\\\b\4\1\2\\b\3\2\2\2]^\7\u008e\2\2^_\5\6\4"+
		"\7_`\b\4\1\2`b\3\2\2\2aQ\3\2\2\2aT\3\2\2\2aV\3\2\2\2aX\3\2\2\2a]\3\2\2"+
		"\2by\3\2\2\2cd\f\6\2\2de\7\u009b\2\2ef\5\6\4\7fg\b\4\1\2gx\3\2\2\2hi\f"+
		"\5\2\2ij\7\u009c\2\2jk\5\6\4\6kl\b\4\1\2lx\3\2\2\2mn\f\4\2\2no\7\u0098"+
		"\2\2op\5\6\4\5pq\b\4\1\2qx\3\2\2\2rs\f\3\2\2st\7\u0099\2\2tu\5\6\4\4u"+
		"v\b\4\1\2vx\3\2\2\2wc\3\2\2\2wh\3\2\2\2wm\3\2\2\2wr\3\2\2\2x{\3\2\2\2"+
		"yw\3\2\2\2yz\3\2\2\2z\7\3\2\2\2{y\3\2\2\2\n\'+<CMawy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}