// Generated from Scala.g4 by ANTLR 4.7.2
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
public class ScalaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, Id=62, BooleanLiteral=63, CharacterLiteral=64, SymbolLiteral=65, 
		IntegerLiteral=66, StringLiteral=67, FloatingPointLiteral=68, Varid=69, 
		BoundVarid=70, Paren=71, Delim=72, Semi=73, NL=74, NEWLINE=75, WS=76, 
		COMMENT=77, LINE_COMMENT=78;
	public static final int
		RULE_literal = 0, RULE_qualId = 1, RULE_ids = 2, RULE_stableId = 3, RULE_classQualifier = 4, 
		RULE_type_ = 5, RULE_functionArgTypes = 6, RULE_existentialClause = 7, 
		RULE_existentialDcl = 8, RULE_infixType = 9, RULE_compoundType = 10, RULE_annotType = 11, 
		RULE_simpleType = 12, RULE_typeArgs = 13, RULE_types = 14, RULE_refinement = 15, 
		RULE_refineStat = 16, RULE_typePat = 17, RULE_ascription = 18, RULE_expr = 19, 
		RULE_expr1 = 20, RULE_prefixDef = 21, RULE_postfixExpr = 22, RULE_infixExpr = 23, 
		RULE_prefixExpr = 24, RULE_simpleExpr = 25, RULE_simpleExpr1 = 26, RULE_exprs = 27, 
		RULE_argumentExprs = 28, RULE_blockExpr = 29, RULE_block = 30, RULE_blockStat = 31, 
		RULE_resultExpr = 32, RULE_enumerators = 33, RULE_generator = 34, RULE_caseClauses = 35, 
		RULE_caseClause = 36, RULE_guard = 37, RULE_pattern = 38, RULE_pattern1 = 39, 
		RULE_pattern2 = 40, RULE_pattern3 = 41, RULE_simplePattern = 42, RULE_patterns = 43, 
		RULE_typeParamClause = 44, RULE_funTypeParamClause = 45, RULE_variantTypeParam = 46, 
		RULE_typeParam = 47, RULE_paramClauses = 48, RULE_paramClause = 49, RULE_params = 50, 
		RULE_param = 51, RULE_paramType = 52, RULE_classParamClauses = 53, RULE_classParamClause = 54, 
		RULE_classParams = 55, RULE_classParam = 56, RULE_bindings = 57, RULE_binding = 58, 
		RULE_modifier = 59, RULE_localModifier = 60, RULE_accessModifier = 61, 
		RULE_accessQualifier = 62, RULE_annotation = 63, RULE_constrAnnotation = 64, 
		RULE_templateBody = 65, RULE_templateStat = 66, RULE_selfType = 67, RULE_import_ = 68, 
		RULE_importExpr = 69, RULE_importSelectors = 70, RULE_importSelector = 71, 
		RULE_dcl = 72, RULE_valDcl = 73, RULE_varDcl = 74, RULE_funDcl = 75, RULE_funSig = 76, 
		RULE_typeDcl = 77, RULE_patVarDef = 78, RULE_def = 79, RULE_patDef = 80, 
		RULE_varDef = 81, RULE_funDef = 82, RULE_typeDef = 83, RULE_tmplDef = 84, 
		RULE_classDef = 85, RULE_traitDef = 86, RULE_objectDef = 87, RULE_classTemplateOpt = 88, 
		RULE_traitTemplateOpt = 89, RULE_classTemplate = 90, RULE_traitTemplate = 91, 
		RULE_classParents = 92, RULE_traitParents = 93, RULE_constr = 94, RULE_earlyDefs = 95, 
		RULE_earlyDef = 96, RULE_constrExpr = 97, RULE_constrBlock = 98, RULE_selfInvocation = 99, 
		RULE_topStatSeq = 100, RULE_topStat = 101, RULE_packaging = 102, RULE_packageObject = 103, 
		RULE_compilationUnit = 104;
	private static String[] makeRuleNames() {
		return new String[] {
			"literal", "qualId", "ids", "stableId", "classQualifier", "type_", "functionArgTypes", 
			"existentialClause", "existentialDcl", "infixType", "compoundType", "annotType", 
			"simpleType", "typeArgs", "types", "refinement", "refineStat", "typePat", 
			"ascription", "expr", "expr1", "prefixDef", "postfixExpr", "infixExpr", 
			"prefixExpr", "simpleExpr", "simpleExpr1", "exprs", "argumentExprs", 
			"blockExpr", "block", "blockStat", "resultExpr", "enumerators", "generator", 
			"caseClauses", "caseClause", "guard", "pattern", "pattern1", "pattern2", 
			"pattern3", "simplePattern", "patterns", "typeParamClause", "funTypeParamClause", 
			"variantTypeParam", "typeParam", "paramClauses", "paramClause", "params", 
			"param", "paramType", "classParamClauses", "classParamClause", "classParams", 
			"classParam", "bindings", "binding", "modifier", "localModifier", "accessModifier", 
			"accessQualifier", "annotation", "constrAnnotation", "templateBody", 
			"templateStat", "selfType", "import_", "importExpr", "importSelectors", 
			"importSelector", "dcl", "valDcl", "varDcl", "funDcl", "funSig", "typeDcl", 
			"patVarDef", "def", "patDef", "varDef", "funDef", "typeDef", "tmplDef", 
			"classDef", "traitDef", "objectDef", "classTemplateOpt", "traitTemplateOpt", 
			"classTemplate", "traitTemplate", "classParents", "traitParents", "constr", 
			"earlyDefs", "earlyDef", "constrExpr", "constrBlock", "selfInvocation", 
			"topStatSeq", "topStat", "packaging", "packageObject", "compilationUnit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'null'", "'.'", "','", "'this'", "'super'", "'['", "']'", 
			"'=>'", "'('", "')'", "'forSome'", "'{'", "'}'", "'type'", "'val'", "'with'", 
			"'#'", "':'", "'_'", "'*'", "'implicit'", "'if'", "'else'", "'while'", 
			"'try'", "'catch'", "'finally'", "'do'", "'for'", "'yield'", "'throw'", 
			"'return'", "'='", "'match'", "'+'", "'~'", "'!'", "'new'", "'lazy'", 
			"'<-'", "'case'", "'|'", "'@'", "'>:'", "'<:'", "'<%'", "'var'", "'override'", 
			"'abstract'", "'final'", "'sealed'", "'private'", "'protected'", "'import'", 
			"'def'", "'class'", "'object'", "'trait'", "'extends'", "'package'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "Id", "BooleanLiteral", "CharacterLiteral", "SymbolLiteral", 
			"IntegerLiteral", "StringLiteral", "FloatingPointLiteral", "Varid", "BoundVarid", 
			"Paren", "Delim", "Semi", "NL", "NEWLINE", "WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "Scala.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScalaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(ScalaParser.IntegerLiteral, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(ScalaParser.FloatingPointLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(ScalaParser.BooleanLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(ScalaParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(ScalaParser.StringLiteral, 0); }
		public TerminalNode SymbolLiteral() { return getToken(ScalaParser.SymbolLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_literal);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(210);
					match(T__0);
					}
				}

				setState(213);
				match(IntegerLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(214);
					match(T__0);
					}
				}

				setState(217);
				match(FloatingPointLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				match(BooleanLiteral);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				match(CharacterLiteral);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(220);
				match(StringLiteral);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(221);
				match(SymbolLiteral);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(222);
				match(T__1);
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

	public static class QualIdContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public QualIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterQualId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitQualId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitQualId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualIdContext qualId() throws RecognitionException {
		QualIdContext _localctx = new QualIdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_qualId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(Id);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(226);
				match(T__2);
				setState(227);
				match(Id);
				}
				}
				setState(232);
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

	public static class IdsContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public IdsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ids; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterIds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitIds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitIds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdsContext ids() throws RecognitionException {
		IdsContext _localctx = new IdsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ids);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(Id);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(234);
				match(T__3);
				setState(235);
				match(Id);
				}
				}
				setState(240);
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

	public static class StableIdContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public ClassQualifierContext classQualifier() {
			return getRuleContext(ClassQualifierContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public StableIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stableId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterStableId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitStableId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitStableId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StableIdContext stableId() throws RecognitionException {
		return stableId(0);
	}

	private StableIdContext stableId(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StableIdContext _localctx = new StableIdContext(_ctx, _parentState);
		StableIdContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_stableId, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(242);
				match(Id);
				}
				break;
			case 2:
				{
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Id) {
					{
					setState(243);
					match(Id);
					setState(244);
					match(T__2);
					}
				}

				setState(254);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(247);
					match(T__4);
					}
					break;
				case T__5:
					{
					setState(248);
					match(T__5);
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__6) {
						{
						setState(249);
						classQualifier();
						}
					}

					setState(252);
					match(T__2);
					setState(253);
					match(Id);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StableIdContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stableId);
					setState(258);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(259);
					match(T__2);
					setState(260);
					match(Id);
					}
					} 
				}
				setState(265);
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

	public static class ClassQualifierContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classQualifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassQualifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassQualifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassQualifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassQualifierContext classQualifier() throws RecognitionException {
		ClassQualifierContext _localctx = new ClassQualifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classQualifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__6);
			setState(267);
			match(Id);
			setState(268);
			match(T__7);
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
		public FunctionArgTypesContext functionArgTypes() {
			return getRuleContext(FunctionArgTypesContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public ExistentialClauseContext existentialClause() {
			return getRuleContext(ExistentialClauseContext.class,0);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitType_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitType_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_);
		int _la;
		try {
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				functionArgTypes();
				setState(271);
				match(T__8);
				setState(272);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				infixType();
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(275);
					existentialClause();
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

	public static class FunctionArgTypesContext extends ParserRuleContext {
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public List<ParamTypeContext> paramType() {
			return getRuleContexts(ParamTypeContext.class);
		}
		public ParamTypeContext paramType(int i) {
			return getRuleContext(ParamTypeContext.class,i);
		}
		public FunctionArgTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunctionArgTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunctionArgTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitFunctionArgTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgTypesContext functionArgTypes() throws RecognitionException {
		FunctionArgTypesContext _localctx = new FunctionArgTypesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionArgTypes);
		int _la;
		try {
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				infixType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				match(T__9);
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << Id))) != 0) || _la==NL) {
					{
					setState(282);
					paramType();
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(283);
						match(T__3);
						setState(284);
						paramType();
						}
						}
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(292);
				match(T__10);
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

	public static class ExistentialClauseContext extends ParserRuleContext {
		public List<ExistentialDclContext> existentialDcl() {
			return getRuleContexts(ExistentialDclContext.class);
		}
		public ExistentialDclContext existentialDcl(int i) {
			return getRuleContext(ExistentialDclContext.class,i);
		}
		public ExistentialClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existentialClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExistentialClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExistentialClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitExistentialClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExistentialClauseContext existentialClause() throws RecognitionException {
		ExistentialClauseContext _localctx = new ExistentialClauseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_existentialClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(T__11);
			setState(296);
			match(T__12);
			setState(298); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(297);
				existentialDcl();
				}
				}
				setState(300); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__14 || _la==T__15 );
			setState(302);
			match(T__13);
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

	public static class ExistentialDclContext extends ParserRuleContext {
		public TypeDclContext typeDcl() {
			return getRuleContext(TypeDclContext.class,0);
		}
		public ValDclContext valDcl() {
			return getRuleContext(ValDclContext.class,0);
		}
		public ExistentialDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existentialDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExistentialDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExistentialDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitExistentialDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExistentialDclContext existentialDcl() throws RecognitionException {
		ExistentialDclContext _localctx = new ExistentialDclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_existentialDcl);
		try {
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				match(T__14);
				setState(305);
				typeDcl();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(T__15);
				setState(307);
				valDcl();
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

	public static class InfixTypeContext extends ParserRuleContext {
		public List<CompoundTypeContext> compoundType() {
			return getRuleContexts(CompoundTypeContext.class);
		}
		public CompoundTypeContext compoundType(int i) {
			return getRuleContext(CompoundTypeContext.class,i);
		}
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public InfixTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterInfixType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitInfixType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitInfixType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixTypeContext infixType() throws RecognitionException {
		InfixTypeContext _localctx = new InfixTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_infixType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			compoundType();
			setState(315);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					match(Id);
					setState(312);
					compoundType();
					}
					} 
				}
				setState(317);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class CompoundTypeContext extends ParserRuleContext {
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public RefinementContext refinement() {
			return getRuleContext(RefinementContext.class,0);
		}
		public CompoundTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCompoundType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCompoundType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitCompoundType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundTypeContext compoundType() throws RecognitionException {
		CompoundTypeContext _localctx = new CompoundTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compoundType);
		int _la;
		try {
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case T__9:
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				annotType();
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(319);
					match(T__16);
					setState(320);
					annotType();
					}
					}
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(327);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(326);
					refinement();
					}
					break;
				}
				}
				break;
			case T__12:
			case NL:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				refinement();
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

	public static class AnnotTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AnnotTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAnnotType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAnnotType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitAnnotType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotTypeContext annotType() throws RecognitionException {
		AnnotTypeContext _localctx = new AnnotTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_annotType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			simpleType(0);
			setState(336);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(333);
					annotation();
					}
					} 
				}
				setState(338);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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

	public static class SimpleTypeContext extends ParserRuleContext {
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public TypeArgsContext typeArgs() {
			return getRuleContext(TypeArgsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		return simpleType(0);
	}

	private SimpleTypeContext simpleType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, _parentState);
		SimpleTypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_simpleType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case Id:
				{
				setState(340);
				stableId(0);
				setState(343);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(341);
					match(T__2);
					setState(342);
					match(T__14);
					}
					break;
				}
				}
				break;
			case T__9:
				{
				setState(345);
				match(T__9);
				setState(346);
				types();
				setState(347);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(358);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(356);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new SimpleTypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleType);
						setState(351);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(352);
						typeArgs();
						}
						break;
					case 2:
						{
						_localctx = new SimpleTypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleType);
						setState(353);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(354);
						match(T__17);
						setState(355);
						match(Id);
						}
						break;
					}
					} 
				}
				setState(360);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class TypeArgsContext extends ParserRuleContext {
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public TypeArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypeArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgsContext typeArgs() throws RecognitionException {
		TypeArgsContext _localctx = new TypeArgsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeArgs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(T__6);
			setState(362);
			types();
			setState(363);
			match(T__7);
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

	public static class TypesContext extends ParserRuleContext {
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_types);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			type_();
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(366);
				match(T__3);
				setState(367);
				type_();
				}
				}
				setState(372);
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

	public static class RefinementContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public List<RefineStatContext> refineStat() {
			return getRuleContexts(RefineStatContext.class);
		}
		public RefineStatContext refineStat(int i) {
			return getRuleContext(RefineStatContext.class,i);
		}
		public RefinementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refinement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterRefinement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitRefinement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitRefinement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RefinementContext refinement() throws RecognitionException {
		RefinementContext _localctx = new RefinementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_refinement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(373);
				match(NL);
				}
			}

			setState(376);
			match(T__12);
			setState(378); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(377);
				refineStat();
				}
				}
				setState(380); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__47) | (1L << T__55))) != 0) );
			setState(382);
			match(T__13);
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

	public static class RefineStatContext extends ParserRuleContext {
		public DclContext dcl() {
			return getRuleContext(DclContext.class,0);
		}
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public RefineStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refineStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterRefineStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitRefineStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitRefineStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RefineStatContext refineStat() throws RecognitionException {
		RefineStatContext _localctx = new RefineStatContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_refineStat);
		try {
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				dcl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
				match(T__14);
				setState(386);
				typeDef();
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

	public static class TypePatContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypePatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typePat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypePat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypePat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypePat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypePatContext typePat() throws RecognitionException {
		TypePatContext _localctx = new TypePatContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typePat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
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

	public static class AscriptionContext extends ParserRuleContext {
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AscriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ascription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAscription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAscription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitAscription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AscriptionContext ascription() throws RecognitionException {
		AscriptionContext _localctx = new AscriptionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ascription);
		try {
			int _alt;
			setState(402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(T__18);
				setState(392);
				infixType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				match(T__18);
				setState(395); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(394);
						annotation();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(397); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(399);
				match(T__18);
				setState(400);
				match(T__19);
				setState(401);
				match(T__20);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BindingsContext bindings() {
			return getRuleContext(BindingsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr);
		int _la;
		try {
			setState(415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(410);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(404);
					bindings();
					}
					break;
				case T__21:
				case Id:
					{
					setState(406);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__21) {
						{
						setState(405);
						match(T__21);
						}
					}

					setState(408);
					match(Id);
					}
					break;
				case T__19:
					{
					setState(409);
					match(T__19);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(412);
				match(T__8);
				setState(413);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(414);
				expr1();
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

	public static class Expr1Context extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public EnumeratorsContext enumerators() {
			return getRuleContext(EnumeratorsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public AscriptionContext ascription() {
			return getRuleContext(AscriptionContext.class,0);
		}
		public CaseClausesContext caseClauses() {
			return getRuleContext(CaseClausesContext.class,0);
		}
		public Expr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExpr1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitExpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr1Context expr1() throws RecognitionException {
		Expr1Context _localctx = new Expr1Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_expr1);
		int _la;
		try {
			setState(512);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				match(T__22);
				setState(418);
				match(T__9);
				setState(419);
				expr();
				setState(420);
				match(T__10);
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(421);
					match(NL);
					}
					}
					setState(426);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(427);
				expr();
				setState(430);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(428);
					match(T__23);
					setState(429);
					expr();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(432);
				match(T__24);
				setState(433);
				match(T__9);
				setState(434);
				expr();
				setState(435);
				match(T__10);
				setState(439);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(436);
					match(NL);
					}
					}
					setState(441);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(442);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(444);
				match(T__25);
				setState(445);
				expr();
				setState(448);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(446);
					match(T__26);
					setState(447);
					expr();
					}
					break;
				}
				setState(452);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(450);
					match(T__27);
					setState(451);
					expr();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(454);
				match(T__28);
				setState(455);
				expr();
				setState(456);
				match(T__24);
				setState(457);
				match(T__9);
				setState(458);
				expr();
				setState(459);
				match(T__10);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(461);
				match(T__29);
				setState(470);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(462);
					match(T__9);
					setState(463);
					enumerators();
					setState(464);
					match(T__10);
					}
					break;
				case T__12:
					{
					setState(466);
					match(T__12);
					setState(467);
					enumerators();
					setState(468);
					match(T__13);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(473);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__30) {
					{
					setState(472);
					match(T__30);
					}
				}

				setState(475);
				expr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(477);
				match(T__31);
				setState(478);
				expr();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(479);
				match(T__32);
				setState(481);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(480);
					expr();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(492);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(488);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						setState(483);
						simpleExpr();
						}
						break;
					case 2:
						{
						setState(484);
						simpleExpr1(0);
						setState(486);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(485);
							match(T__19);
							}
						}

						}
						break;
					}
					setState(490);
					match(T__2);
					}
					break;
				}
				setState(494);
				match(Id);
				setState(495);
				match(T__33);
				setState(496);
				expr();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(497);
				simpleExpr1(0);
				setState(498);
				argumentExprs();
				setState(499);
				match(T__33);
				setState(500);
				expr();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(502);
				postfixExpr();
				setState(504);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(503);
					ascription();
					}
				}

				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(506);
				postfixExpr();
				setState(507);
				match(T__34);
				setState(508);
				match(T__12);
				setState(509);
				caseClauses();
				setState(510);
				match(T__13);
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

	public static class PrefixDefContext extends ParserRuleContext {
		public PrefixDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPrefixDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPrefixDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPrefixDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixDefContext prefixDef() throws RecognitionException {
		PrefixDefContext _localctx = new PrefixDefContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_prefixDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
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

	public static class PostfixExprContext extends ParserRuleContext {
		public InfixExprContext infixExpr() {
			return getRuleContext(InfixExprContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public List<PrefixDefContext> prefixDef() {
			return getRuleContexts(PrefixDefContext.class);
		}
		public PrefixDefContext prefixDef(int i) {
			return getRuleContext(PrefixDefContext.class,i);
		}
		public List<SimpleExpr1Context> simpleExpr1() {
			return getRuleContexts(SimpleExpr1Context.class);
		}
		public SimpleExpr1Context simpleExpr1(int i) {
			return getRuleContext(SimpleExpr1Context.class,i);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public PostfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPostfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPostfixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPostfixExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExprContext postfixExpr() throws RecognitionException {
		PostfixExprContext _localctx = new PostfixExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_postfixExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			infixExpr(0);
			setState(518);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(517);
				match(Id);
				}
				break;
			}
			setState(525);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(520);
					prefixDef();
					setState(521);
					simpleExpr1(0);
					}
					} 
				}
				setState(527);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(529);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(528);
				match(NL);
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

	public static class InfixExprContext extends ParserRuleContext {
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public List<InfixExprContext> infixExpr() {
			return getRuleContexts(InfixExprContext.class);
		}
		public InfixExprContext infixExpr(int i) {
			return getRuleContext(InfixExprContext.class,i);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public InfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterInfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitInfixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitInfixExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixExprContext infixExpr() throws RecognitionException {
		return infixExpr(0);
	}

	private InfixExprContext infixExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InfixExprContext _localctx = new InfixExprContext(_ctx, _parentState);
		InfixExprContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_infixExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(532);
			prefixExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(542);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InfixExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_infixExpr);
					setState(534);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(535);
					match(Id);
					setState(537);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(536);
						match(NL);
						}
					}

					setState(539);
					infixExpr(2);
					}
					} 
				}
				setState(544);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
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

	public static class PrefixExprContext extends ParserRuleContext {
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public PrefixDefContext prefixDef() {
			return getRuleContext(PrefixDefContext.class,0);
		}
		public PrefixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPrefixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPrefixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixExprContext prefixExpr() throws RecognitionException {
		PrefixExprContext _localctx = new PrefixExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prefixExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(545);
				prefixDef();
				}
				break;
			}
			setState(553);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(548);
				simpleExpr();
				}
				break;
			case 2:
				{
				setState(549);
				simpleExpr1(0);
				setState(551);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(550);
					match(T__19);
					}
					break;
				}
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

	public static class SimpleExprContext extends ParserRuleContext {
		public ClassTemplateContext classTemplate() {
			return getRuleContext(ClassTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public BlockExprContext blockExpr() {
			return getRuleContext(BlockExprContext.class,0);
		}
		public SimpleExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSimpleExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleExprContext simpleExpr() throws RecognitionException {
		SimpleExprContext _localctx = new SimpleExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_simpleExpr);
		try {
			setState(561);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(555);
				match(T__38);
				setState(558);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(556);
					classTemplate();
					}
					break;
				case 2:
					{
					setState(557);
					templateBody();
					}
					break;
				}
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(560);
				blockExpr();
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

	public static class SimpleExpr1Context extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeArgsContext typeArgs() {
			return getRuleContext(TypeArgsContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public SimpleExpr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleExpr1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSimpleExpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleExpr1Context simpleExpr1() throws RecognitionException {
		return simpleExpr1(0);
	}

	private SimpleExpr1Context simpleExpr1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SimpleExpr1Context _localctx = new SimpleExpr1Context(_ctx, _parentState);
		SimpleExpr1Context _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_simpleExpr1, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(564);
				literal();
				}
				break;
			case 2:
				{
				setState(565);
				stableId(0);
				}
				break;
			case 3:
				{
				setState(566);
				match(T__19);
				}
				break;
			case 4:
				{
				setState(567);
				match(T__9);
				setState(569);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
					{
					setState(568);
					exprs();
					}
				}

				setState(571);
				match(T__10);
				}
				break;
			case 5:
				{
				setState(572);
				simpleExpr();
				setState(573);
				match(T__2);
				setState(574);
				match(Id);
				}
				break;
			case 6:
				{
				setState(576);
				simpleExpr();
				setState(577);
				typeArgs();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(596);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(594);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
					case 1:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(581);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(583);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(582);
							match(T__19);
							}
						}

						setState(585);
						match(T__2);
						setState(586);
						match(Id);
						}
						break;
					case 2:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(587);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(589);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(588);
							match(T__19);
							}
						}

						setState(591);
						typeArgs();
						}
						break;
					case 3:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(592);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(593);
						argumentExprs();
						}
						break;
					}
					} 
				}
				setState(598);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
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

	public static class ExprsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExprs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_exprs);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(599);
			expr();
			setState(604);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(600);
					match(T__3);
					setState(601);
					expr();
					}
					} 
				}
				setState(606);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
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

	public static class ArgumentExprsContext extends ParserRuleContext {
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public BlockExprContext blockExpr() {
			return getRuleContext(BlockExprContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ArgumentExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentExprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterArgumentExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitArgumentExprs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitArgumentExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentExprsContext argumentExprs() throws RecognitionException {
		ArgumentExprsContext _localctx = new ArgumentExprsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_argumentExprs);
		int _la;
		try {
			setState(628);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(607);
				match(T__9);
				setState(609);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
					{
					setState(608);
					exprs();
					}
				}

				setState(611);
				match(T__10);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(612);
				match(T__9);
				setState(616);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(613);
					exprs();
					setState(614);
					match(T__3);
					}
					break;
				}
				setState(618);
				postfixExpr();
				setState(619);
				match(T__18);
				setState(620);
				match(T__19);
				setState(621);
				match(T__20);
				setState(622);
				match(T__10);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(625);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(624);
					match(NL);
					}
				}

				setState(627);
				blockExpr();
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

	public static class BlockExprContext extends ParserRuleContext {
		public CaseClausesContext caseClauses() {
			return getRuleContext(CaseClausesContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlockExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlockExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitBlockExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockExprContext blockExpr() throws RecognitionException {
		BlockExprContext _localctx = new BlockExprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_blockExpr);
		try {
			setState(638);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(630);
				match(T__12);
				setState(631);
				caseClauses();
				setState(632);
				match(T__13);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(634);
				match(T__12);
				setState(635);
				block();
				setState(636);
				match(T__13);
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
		public List<BlockStatContext> blockStat() {
			return getRuleContexts(BlockStatContext.class);
		}
		public BlockStatContext blockStat(int i) {
			return getRuleContext(BlockStatContext.class,i);
		}
		public ResultExprContext resultExpr() {
			return getRuleContext(ResultExprContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(641); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(640);
					blockStat();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(643); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(646);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
				{
				setState(645);
				resultExpr();
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

	public static class BlockStatContext extends ParserRuleContext {
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public List<LocalModifierContext> localModifier() {
			return getRuleContexts(LocalModifierContext.class);
		}
		public LocalModifierContext localModifier(int i) {
			return getRuleContext(LocalModifierContext.class,i);
		}
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public BlockStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatContext blockStat() throws RecognitionException {
		BlockStatContext _localctx = new BlockStatContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_blockStat);
		int _la;
		try {
			setState(673);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(648);
				import_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(649);
					annotation();
					}
					}
					setState(654);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21 || _la==T__39) {
					{
					setState(655);
					_la = _input.LA(1);
					if ( !(_la==T__21 || _la==T__39) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(658);
				def();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(659);
					annotation();
					}
					}
					setState(664);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(668);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) {
					{
					{
					setState(665);
					localModifier();
					}
					}
					setState(670);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(671);
				tmplDef();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(672);
				expr1();
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

	public static class ResultExprContext extends ParserRuleContext {
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BindingsContext bindings() {
			return getRuleContext(BindingsContext.class,0);
		}
		public CompoundTypeContext compoundType() {
			return getRuleContext(CompoundTypeContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ResultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterResultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitResultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitResultExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultExprContext resultExpr() throws RecognitionException {
		ResultExprContext _localctx = new ResultExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_resultExpr);
		int _la;
		try {
			setState(691);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(675);
				expr1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(686);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(676);
					bindings();
					}
					break;
				case T__19:
				case T__21:
				case Id:
					{
					setState(682);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__21:
					case Id:
						{
						setState(678);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__21) {
							{
							setState(677);
							match(T__21);
							}
						}

						setState(680);
						match(Id);
						}
						break;
					case T__19:
						{
						setState(681);
						match(T__19);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(684);
					match(T__18);
					setState(685);
					compoundType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(688);
				match(T__8);
				setState(689);
				block();
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

	public static class EnumeratorsContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}
		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class,i);
		}
		public EnumeratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumerators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEnumerators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEnumerators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitEnumerators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumeratorsContext enumerators() throws RecognitionException {
		EnumeratorsContext _localctx = new EnumeratorsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_enumerators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(693);
				generator();
				}
				}
				setState(696); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0) );
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

	public static class GeneratorContext extends ParserRuleContext {
		public List<Pattern1Context> pattern1() {
			return getRuleContexts(Pattern1Context.class);
		}
		public Pattern1Context pattern1(int i) {
			return getRuleContext(Pattern1Context.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<GuardContext> guard() {
			return getRuleContexts(GuardContext.class);
		}
		public GuardContext guard(int i) {
			return getRuleContext(GuardContext.class,i);
		}
		public GeneratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterGenerator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitGenerator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitGenerator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GeneratorContext generator() throws RecognitionException {
		GeneratorContext _localctx = new GeneratorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_generator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			pattern1();
			setState(699);
			match(T__40);
			setState(700);
			expr();
			setState(708);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(706);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__22:
						{
						setState(701);
						guard();
						}
						break;
					case T__0:
					case T__1:
					case T__4:
					case T__5:
					case T__9:
					case T__19:
					case Id:
					case BooleanLiteral:
					case CharacterLiteral:
					case SymbolLiteral:
					case IntegerLiteral:
					case StringLiteral:
					case FloatingPointLiteral:
					case Varid:
					case BoundVarid:
						{
						setState(702);
						pattern1();
						setState(703);
						match(T__33);
						setState(704);
						expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(710);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
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
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCaseClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCaseClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitCaseClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClausesContext caseClauses() throws RecognitionException {
		CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_caseClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(712); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(711);
				caseClause();
				}
				}
				setState(714); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__41 );
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
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public GuardContext guard() {
			return getRuleContext(GuardContext.class,0);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCaseClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitCaseClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_caseClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			match(T__41);
			setState(717);
			pattern();
			setState(719);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(718);
				guard();
				}
			}

			setState(721);
			match(T__8);
			setState(722);
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

	public static class GuardContext extends ParserRuleContext {
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public GuardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitGuard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitGuard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GuardContext guard() throws RecognitionException {
		GuardContext _localctx = new GuardContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			match(T__22);
			setState(725);
			postfixExpr();
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

	public static class PatternContext extends ParserRuleContext {
		public List<Pattern1Context> pattern1() {
			return getRuleContexts(Pattern1Context.class);
		}
		public Pattern1Context pattern1(int i) {
			return getRuleContext(Pattern1Context.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(727);
			pattern1();
			setState(732);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(728);
				match(T__42);
				setState(729);
				pattern1();
				}
				}
				setState(734);
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

	public static class Pattern1Context extends ParserRuleContext {
		public TypePatContext typePat() {
			return getRuleContext(TypePatContext.class,0);
		}
		public TerminalNode BoundVarid() { return getToken(ScalaParser.BoundVarid, 0); }
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Pattern2Context pattern2() {
			return getRuleContext(Pattern2Context.class,0);
		}
		public Pattern1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPattern1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern1Context pattern1() throws RecognitionException {
		Pattern1Context _localctx = new Pattern1Context(_ctx, getState());
		enterRule(_localctx, 78, RULE_pattern1);
		int _la;
		try {
			setState(739);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(735);
				_la = _input.LA(1);
				if ( !(((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (T__19 - 20)) | (1L << (Id - 20)) | (1L << (BoundVarid - 20)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(736);
				match(T__18);
				setState(737);
				typePat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(738);
				pattern2();
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

	public static class Pattern2Context extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Pattern3Context pattern3() {
			return getRuleContext(Pattern3Context.class,0);
		}
		public Pattern2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPattern2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern2Context pattern2() throws RecognitionException {
		Pattern2Context _localctx = new Pattern2Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_pattern2);
		int _la;
		try {
			setState(747);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(741);
				match(Id);
				setState(744);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__43) {
					{
					setState(742);
					match(T__43);
					setState(743);
					pattern3();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(746);
				pattern3();
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

	public static class Pattern3Context extends ParserRuleContext {
		public List<SimplePatternContext> simplePattern() {
			return getRuleContexts(SimplePatternContext.class);
		}
		public SimplePatternContext simplePattern(int i) {
			return getRuleContext(SimplePatternContext.class,i);
		}
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public Pattern3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPattern3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern3Context pattern3() throws RecognitionException {
		Pattern3Context _localctx = new Pattern3Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_pattern3);
		int _la;
		try {
			setState(761);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(749);
				simplePattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(750);
				simplePattern();
				setState(758);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Id) {
					{
					{
					setState(751);
					match(Id);
					setState(753);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(752);
						match(NL);
						}
					}

					setState(755);
					simplePattern();
					}
					}
					setState(760);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class SimplePatternContext extends ParserRuleContext {
		public TerminalNode Varid() { return getToken(ScalaParser.Varid, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public PatternsContext patterns() {
			return getRuleContext(PatternsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimplePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simplePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimplePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimplePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSimplePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimplePatternContext simplePattern() throws RecognitionException {
		SimplePatternContext _localctx = new SimplePatternContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_simplePattern);
		int _la;
		try {
			setState(794);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(763);
				match(T__19);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(764);
				match(Varid);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(765);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(766);
				stableId(0);
				setState(772);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__9) {
					{
					setState(767);
					match(T__9);
					setState(769);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0)) {
						{
						setState(768);
						patterns();
						}
					}

					setState(771);
					match(T__10);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(774);
				stableId(0);
				setState(775);
				match(T__9);
				setState(779);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(776);
					patterns();
					setState(777);
					match(T__3);
					}
					break;
				}
				setState(783);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Id) {
					{
					setState(781);
					match(Id);
					setState(782);
					match(T__43);
					}
				}

				setState(785);
				match(T__19);
				setState(786);
				match(T__20);
				setState(787);
				match(T__10);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(789);
				match(T__9);
				setState(791);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0)) {
					{
					setState(790);
					patterns();
					}
				}

				setState(793);
				match(T__10);
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

	public static class PatternsContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public PatternsContext patterns() {
			return getRuleContext(PatternsContext.class,0);
		}
		public PatternsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patterns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatterns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatterns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPatterns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternsContext patterns() throws RecognitionException {
		PatternsContext _localctx = new PatternsContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_patterns);
		try {
			setState(803);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(796);
				pattern();
				setState(799);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(797);
					match(T__3);
					setState(798);
					patterns();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(801);
				match(T__19);
				setState(802);
				match(T__20);
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

	public static class TypeParamClauseContext extends ParserRuleContext {
		public List<VariantTypeParamContext> variantTypeParam() {
			return getRuleContexts(VariantTypeParamContext.class);
		}
		public VariantTypeParamContext variantTypeParam(int i) {
			return getRuleContext(VariantTypeParamContext.class,i);
		}
		public TypeParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeParamClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypeParamClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParamClauseContext typeParamClause() throws RecognitionException {
		TypeParamClauseContext _localctx = new TypeParamClauseContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_typeParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(805);
			match(T__6);
			setState(806);
			variantTypeParam();
			setState(811);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(807);
				match(T__3);
				setState(808);
				variantTypeParam();
				}
				}
				setState(813);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(814);
			match(T__7);
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

	public static class FunTypeParamClauseContext extends ParserRuleContext {
		public List<TypeParamContext> typeParam() {
			return getRuleContexts(TypeParamContext.class);
		}
		public TypeParamContext typeParam(int i) {
			return getRuleContext(TypeParamContext.class,i);
		}
		public FunTypeParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funTypeParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunTypeParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunTypeParamClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitFunTypeParamClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunTypeParamClauseContext funTypeParamClause() throws RecognitionException {
		FunTypeParamClauseContext _localctx = new FunTypeParamClauseContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_funTypeParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(816);
			match(T__6);
			setState(817);
			typeParam();
			setState(822);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(818);
				match(T__3);
				setState(819);
				typeParam();
				}
				}
				setState(824);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(825);
			match(T__7);
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

	public static class VariantTypeParamContext extends ParserRuleContext {
		public TypeParamContext typeParam() {
			return getRuleContext(TypeParamContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public VariantTypeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variantTypeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVariantTypeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVariantTypeParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitVariantTypeParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariantTypeParamContext variantTypeParam() throws RecognitionException {
		VariantTypeParamContext _localctx = new VariantTypeParamContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_variantTypeParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(830);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(827);
				annotation();
				}
				}
				setState(832);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(834);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==T__35) {
				{
				setState(833);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==T__35) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(836);
			typeParam();
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

	public static class TypeParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypeParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParamContext typeParam() throws RecognitionException {
		TypeParamContext _localctx = new TypeParamContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_typeParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(838);
			_la = _input.LA(1);
			if ( !(_la==T__19 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(840);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(839);
				typeParamClause();
				}
			}

			setState(844);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(842);
				match(T__44);
				setState(843);
				type_();
				}
			}

			setState(848);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__45) {
				{
				setState(846);
				match(T__45);
				setState(847);
				type_();
				}
			}

			setState(854);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__46) {
				{
				{
				setState(850);
				match(T__46);
				setState(851);
				type_();
				}
				}
				setState(856);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(861);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(857);
				match(T__18);
				setState(858);
				type_();
				}
				}
				setState(863);
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

	public static class ParamClausesContext extends ParserRuleContext {
		public List<ParamClauseContext> paramClause() {
			return getRuleContexts(ParamClauseContext.class);
		}
		public ParamClauseContext paramClause(int i) {
			return getRuleContext(ParamClauseContext.class,i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitParamClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamClausesContext paramClauses() throws RecognitionException {
		ParamClausesContext _localctx = new ParamClausesContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_paramClauses);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(867);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,110,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(864);
					paramClause();
					}
					} 
				}
				setState(869);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,110,_ctx);
			}
			setState(878);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				{
				setState(871);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(870);
					match(NL);
					}
				}

				setState(873);
				match(T__9);
				setState(874);
				match(T__21);
				setState(875);
				params();
				setState(876);
				match(T__10);
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

	public static class ParamClauseContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitParamClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamClauseContext paramClause() throws RecognitionException {
		ParamClauseContext _localctx = new ParamClauseContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_paramClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(881);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(880);
				match(NL);
				}
			}

			setState(883);
			match(T__9);
			setState(885);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__43 || _la==Id) {
				{
				setState(884);
				params();
				}
			}

			setState(887);
			match(T__10);
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

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(889);
			param();
			setState(894);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(890);
				match(T__3);
				setState(891);
				param();
				}
				}
				setState(896);
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

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ParamTypeContext paramType() {
			return getRuleContext(ParamTypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(900);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(897);
				annotation();
				}
				}
				setState(902);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(903);
			match(Id);
			setState(906);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(904);
				match(T__18);
				setState(905);
				paramType();
				}
			}

			setState(910);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(908);
				match(T__33);
				setState(909);
				expr();
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

	public static class ParamTypeContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public ParamTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitParamType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamTypeContext paramType() throws RecognitionException {
		ParamTypeContext _localctx = new ParamTypeContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_paramType);
		try {
			setState(918);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(912);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(913);
				match(T__8);
				setState(914);
				type_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(915);
				type_();
				setState(916);
				match(T__20);
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

	public static class ClassParamClausesContext extends ParserRuleContext {
		public List<ClassParamClauseContext> classParamClause() {
			return getRuleContexts(ClassParamClauseContext.class);
		}
		public ClassParamClauseContext classParamClause(int i) {
			return getRuleContext(ClassParamClauseContext.class,i);
		}
		public ClassParamsContext classParams() {
			return getRuleContext(ClassParamsContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ClassParamClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParamClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParamClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParamClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassParamClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassParamClausesContext classParamClauses() throws RecognitionException {
		ClassParamClausesContext _localctx = new ClassParamClausesContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_classParamClauses);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(923);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(920);
					classParamClause();
					}
					} 
				}
				setState(925);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
			}
			setState(934);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
			case 1:
				{
				setState(927);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(926);
					match(NL);
					}
				}

				setState(929);
				match(T__9);
				setState(930);
				match(T__21);
				setState(931);
				classParams();
				setState(932);
				match(T__10);
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

	public static class ClassParamClauseContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ClassParamsContext classParams() {
			return getRuleContext(ClassParamsContext.class,0);
		}
		public ClassParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParamClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassParamClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassParamClauseContext classParamClause() throws RecognitionException {
		ClassParamClauseContext _localctx = new ClassParamClauseContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_classParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(936);
				match(NL);
				}
			}

			setState(939);
			match(T__9);
			setState(941);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__21) | (1L << T__39) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << Id))) != 0)) {
				{
				setState(940);
				classParams();
				}
			}

			setState(943);
			match(T__10);
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

	public static class ClassParamsContext extends ParserRuleContext {
		public List<ClassParamContext> classParam() {
			return getRuleContexts(ClassParamContext.class);
		}
		public ClassParamContext classParam(int i) {
			return getRuleContext(ClassParamContext.class,i);
		}
		public ClassParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassParamsContext classParams() throws RecognitionException {
		ClassParamsContext _localctx = new ClassParamsContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_classParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(945);
			classParam();
			setState(950);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(946);
				match(T__3);
				setState(947);
				classParam();
				}
				}
				setState(952);
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

	public static class ClassParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ParamTypeContext paramType() {
			return getRuleContext(ParamTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ClassParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassParamContext classParam() throws RecognitionException {
		ClassParamContext _localctx = new ClassParamContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_classParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(956);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(953);
				annotation();
				}
				}
				setState(958);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(962);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
				{
				{
				setState(959);
				modifier();
				}
				}
				setState(964);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(966);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15 || _la==T__47) {
				{
				setState(965);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__47) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(968);
			match(Id);
			setState(969);
			match(T__18);
			setState(970);
			paramType();
			setState(973);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(971);
				match(T__33);
				setState(972);
				expr();
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

	public static class BindingsContext extends ParserRuleContext {
		public List<BindingContext> binding() {
			return getRuleContexts(BindingContext.class);
		}
		public BindingContext binding(int i) {
			return getRuleContext(BindingContext.class,i);
		}
		public BindingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBindings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBindings(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitBindings(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindingsContext bindings() throws RecognitionException {
		BindingsContext _localctx = new BindingsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_bindings);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(975);
			match(T__9);
			setState(976);
			binding();
			setState(981);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(977);
				match(T__3);
				setState(978);
				binding();
				}
				}
				setState(983);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(984);
			match(T__10);
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

	public static class BindingContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public BindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindingContext binding() throws RecognitionException {
		BindingContext _localctx = new BindingContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_binding);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(986);
			_la = _input.LA(1);
			if ( !(_la==T__19 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(989);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(987);
				match(T__18);
				setState(988);
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

	public static class ModifierContext extends ParserRuleContext {
		public LocalModifierContext localModifier() {
			return getRuleContext(LocalModifierContext.class,0);
		}
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_modifier);
		try {
			setState(994);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
			case T__39:
			case T__49:
			case T__50:
			case T__51:
				enterOuterAlt(_localctx, 1);
				{
				setState(991);
				localModifier();
				}
				break;
			case T__52:
			case T__53:
				enterOuterAlt(_localctx, 2);
				{
				setState(992);
				accessModifier();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 3);
				{
				setState(993);
				match(T__48);
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

	public static class LocalModifierContext extends ParserRuleContext {
		public LocalModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterLocalModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitLocalModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitLocalModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalModifierContext localModifier() throws RecognitionException {
		LocalModifierContext _localctx = new LocalModifierContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_localModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(996);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) ) {
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

	public static class AccessModifierContext extends ParserRuleContext {
		public AccessQualifierContext accessQualifier() {
			return getRuleContext(AccessQualifierContext.class,0);
		}
		public AccessModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAccessModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAccessModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitAccessModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessModifierContext accessModifier() throws RecognitionException {
		AccessModifierContext _localctx = new AccessModifierContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_accessModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998);
			_la = _input.LA(1);
			if ( !(_la==T__52 || _la==T__53) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1000);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(999);
				accessQualifier();
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

	public static class AccessQualifierContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public AccessQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessQualifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAccessQualifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAccessQualifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitAccessQualifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessQualifierContext accessQualifier() throws RecognitionException {
		AccessQualifierContext _localctx = new AccessQualifierContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_accessQualifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002);
			match(T__6);
			setState(1003);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1004);
			match(T__7);
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

	public static class AnnotationContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_annotation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1006);
			match(T__43);
			setState(1007);
			simpleType(0);
			setState(1011);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1008);
					argumentExprs();
					}
					} 
				}
				setState(1013);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,134,_ctx);
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

	public static class ConstrAnnotationContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public ConstrAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitConstrAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstrAnnotationContext constrAnnotation() throws RecognitionException {
		ConstrAnnotationContext _localctx = new ConstrAnnotationContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_constrAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1014);
			match(T__43);
			setState(1015);
			simpleType(0);
			setState(1016);
			argumentExprs();
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

	public static class TemplateBodyContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public SelfTypeContext selfType() {
			return getRuleContext(SelfTypeContext.class,0);
		}
		public List<TemplateStatContext> templateStat() {
			return getRuleContexts(TemplateStatContext.class);
		}
		public TemplateStatContext templateStat(int i) {
			return getRuleContext(TemplateStatContext.class,i);
		}
		public TemplateBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTemplateBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTemplateBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTemplateBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateBodyContext templateBody() throws RecognitionException {
		TemplateBodyContext _localctx = new TemplateBodyContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_templateBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1019);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(1018);
				match(NL);
				}
			}

			setState(1021);
			match(T__12);
			setState(1023);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
			case 1:
				{
				setState(1022);
				selfType();
				}
				break;
			}
			setState(1026); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1025);
				templateStat();
				}
				}
				setState(1028); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0) );
			setState(1030);
			match(T__13);
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

	public static class TemplateStatContext extends ParserRuleContext {
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public DclContext dcl() {
			return getRuleContext(DclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TemplateStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTemplateStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTemplateStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTemplateStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateStatContext templateStat() throws RecognitionException {
		TemplateStatContext _localctx = new TemplateStatContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_templateStat);
		int _la;
		try {
			setState(1066);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1032);
				import_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1039);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1033);
					annotation();
					setState(1035);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1034);
						match(NL);
						}
					}

					}
					}
					setState(1041);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1045);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1042);
					modifier();
					}
					}
					setState(1047);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1048);
				def();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1055);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1049);
					annotation();
					setState(1051);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1050);
						match(NL);
						}
					}

					}
					}
					setState(1057);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1061);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1058);
					modifier();
					}
					}
					setState(1063);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1064);
				dcl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1065);
				expr();
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

	public static class SelfTypeContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public SelfTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSelfType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSelfType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSelfType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfTypeContext selfType() throws RecognitionException {
		SelfTypeContext _localctx = new SelfTypeContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_selfType);
		int _la;
		try {
			setState(1079);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(1068);
				match(Id);
				setState(1071);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(1069);
					match(T__18);
					setState(1070);
					type_();
					}
				}

				setState(1073);
				match(T__8);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(1074);
				match(T__4);
				setState(1075);
				match(T__18);
				setState(1076);
				type_();
				setState(1077);
				match(T__8);
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

	public static class Import_Context extends ParserRuleContext {
		public List<ImportExprContext> importExpr() {
			return getRuleContexts(ImportExprContext.class);
		}
		public ImportExprContext importExpr(int i) {
			return getRuleContext(ImportExprContext.class,i);
		}
		public Import_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImport_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImport_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitImport_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_Context import_() throws RecognitionException {
		Import_Context _localctx = new Import_Context(_ctx, getState());
		enterRule(_localctx, 136, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1081);
			match(T__54);
			setState(1082);
			importExpr();
			setState(1087);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(1083);
				match(T__3);
				setState(1084);
				importExpr();
				}
				}
				setState(1089);
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

	public static class ImportExprContext extends ParserRuleContext {
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ImportSelectorsContext importSelectors() {
			return getRuleContext(ImportSelectorsContext.class,0);
		}
		public ImportExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitImportExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportExprContext importExpr() throws RecognitionException {
		ImportExprContext _localctx = new ImportExprContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_importExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1090);
			stableId(0);
			setState(1097);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(1091);
				match(T__2);
				setState(1095);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Id:
					{
					setState(1092);
					match(Id);
					}
					break;
				case T__19:
					{
					setState(1093);
					match(T__19);
					}
					break;
				case T__12:
					{
					setState(1094);
					importSelectors();
					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static class ImportSelectorsContext extends ParserRuleContext {
		public List<ImportSelectorContext> importSelector() {
			return getRuleContexts(ImportSelectorContext.class);
		}
		public ImportSelectorContext importSelector(int i) {
			return getRuleContext(ImportSelectorContext.class,i);
		}
		public ImportSelectorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSelectors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportSelectors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportSelectors(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitImportSelectors(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportSelectorsContext importSelectors() throws RecognitionException {
		ImportSelectorsContext _localctx = new ImportSelectorsContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_importSelectors);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1099);
			match(T__12);
			setState(1105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,150,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1100);
					importSelector();
					setState(1101);
					match(T__3);
					}
					} 
				}
				setState(1107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,150,_ctx);
			}
			setState(1110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				{
				setState(1108);
				importSelector();
				}
				break;
			case T__19:
				{
				setState(1109);
				match(T__19);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1112);
			match(T__13);
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

	public static class ImportSelectorContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public ImportSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitImportSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportSelectorContext importSelector() throws RecognitionException {
		ImportSelectorContext _localctx = new ImportSelectorContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_importSelector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1114);
			match(Id);
			setState(1117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(1115);
				match(T__8);
				setState(1116);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==Id) ) {
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

	public static class DclContext extends ParserRuleContext {
		public ValDclContext valDcl() {
			return getRuleContext(ValDclContext.class,0);
		}
		public VarDclContext varDcl() {
			return getRuleContext(VarDclContext.class,0);
		}
		public FunDclContext funDcl() {
			return getRuleContext(FunDclContext.class,0);
		}
		public TypeDclContext typeDcl() {
			return getRuleContext(TypeDclContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public DclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DclContext dcl() throws RecognitionException {
		DclContext _localctx = new DclContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_dcl);
		int _la;
		try {
			setState(1133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(1119);
				match(T__15);
				setState(1120);
				valDcl();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(1121);
				match(T__47);
				setState(1122);
				varDcl();
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 3);
				{
				setState(1123);
				match(T__55);
				setState(1124);
				funDcl();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(1125);
				match(T__14);
				setState(1129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(1126);
					match(NL);
					}
					}
					setState(1131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1132);
				typeDcl();
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

	public static class ValDclContext extends ParserRuleContext {
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public ValDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterValDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitValDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitValDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValDclContext valDcl() throws RecognitionException {
		ValDclContext _localctx = new ValDclContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_valDcl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1135);
			ids();
			setState(1136);
			match(T__18);
			setState(1137);
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

	public static class VarDclContext extends ParserRuleContext {
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public VarDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVarDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVarDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitVarDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDclContext varDcl() throws RecognitionException {
		VarDclContext _localctx = new VarDclContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_varDcl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1139);
			ids();
			setState(1140);
			match(T__18);
			setState(1141);
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

	public static class FunDclContext extends ParserRuleContext {
		public FunSigContext funSig() {
			return getRuleContext(FunSigContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public FunDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitFunDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDclContext funDcl() throws RecognitionException {
		FunDclContext _localctx = new FunDclContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_funDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1143);
			funSig();
			setState(1146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(1144);
				match(T__18);
				setState(1145);
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

	public static class FunSigContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ParamClausesContext paramClauses() {
			return getRuleContext(ParamClausesContext.class,0);
		}
		public FunTypeParamClauseContext funTypeParamClause() {
			return getRuleContext(FunTypeParamClauseContext.class,0);
		}
		public FunSigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funSig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunSig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunSig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitFunSig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunSigContext funSig() throws RecognitionException {
		FunSigContext _localctx = new FunSigContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_funSig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1148);
			match(Id);
			setState(1150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1149);
				funTypeParamClause();
				}
			}

			setState(1152);
			paramClauses();
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

	public static class TypeDclContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypeDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeDcl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypeDcl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDclContext typeDcl() throws RecognitionException {
		TypeDclContext _localctx = new TypeDclContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_typeDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1154);
			match(Id);
			setState(1156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1155);
				typeParamClause();
				}
			}

			setState(1160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(1158);
				match(T__44);
				setState(1159);
				type_();
				}
			}

			setState(1164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__45) {
				{
				setState(1162);
				match(T__45);
				setState(1163);
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

	public static class PatVarDefContext extends ParserRuleContext {
		public PatDefContext patDef() {
			return getRuleContext(PatDefContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public PatVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPatVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatVarDefContext patVarDef() throws RecognitionException {
		PatVarDefContext _localctx = new PatVarDefContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_patVarDef);
		try {
			setState(1170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(1166);
				match(T__15);
				setState(1167);
				patDef();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(1168);
				match(T__47);
				setState(1169);
				varDef();
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

	public static class DefContext extends ParserRuleContext {
		public PatVarDefContext patVarDef() {
			return getRuleContext(PatVarDefContext.class,0);
		}
		public FunDefContext funDef() {
			return getRuleContext(FunDefContext.class,0);
		}
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_def);
		int _la;
		try {
			setState(1184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
			case T__47:
				enterOuterAlt(_localctx, 1);
				{
				setState(1172);
				patVarDef();
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 2);
				{
				setState(1173);
				match(T__55);
				setState(1174);
				funDef();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(1175);
				match(T__14);
				setState(1179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(1176);
					match(NL);
					}
					}
					setState(1181);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1182);
				typeDef();
				}
				break;
			case T__41:
			case T__56:
			case T__57:
			case T__58:
				enterOuterAlt(_localctx, 4);
				{
				setState(1183);
				tmplDef();
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

	public static class PatDefContext extends ParserRuleContext {
		public List<Pattern2Context> pattern2() {
			return getRuleContexts(Pattern2Context.class);
		}
		public Pattern2Context pattern2(int i) {
			return getRuleContext(Pattern2Context.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public PatDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPatDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatDefContext patDef() throws RecognitionException {
		PatDefContext _localctx = new PatDefContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_patDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1186);
			pattern2();
			setState(1191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(1187);
				match(T__3);
				setState(1188);
				pattern2();
				}
				}
				setState(1193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(1194);
				match(T__18);
				setState(1195);
				type_();
				}
			}

			setState(1198);
			match(T__33);
			setState(1199);
			expr();
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

	public static class VarDefContext extends ParserRuleContext {
		public PatDefContext patDef() {
			return getRuleContext(PatDefContext.class,0);
		}
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_varDef);
		try {
			setState(1208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,165,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1201);
				patDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1202);
				ids();
				setState(1203);
				match(T__18);
				setState(1204);
				type_();
				setState(1205);
				match(T__33);
				setState(1206);
				match(T__19);
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

	public static class FunDefContext extends ParserRuleContext {
		public FunSigContext funSig() {
			return getRuleContext(FunSigContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamClauseContext paramClause() {
			return getRuleContext(ParamClauseContext.class,0);
		}
		public ParamClausesContext paramClauses() {
			return getRuleContext(ParamClausesContext.class,0);
		}
		public ConstrExprContext constrExpr() {
			return getRuleContext(ConstrExprContext.class,0);
		}
		public ConstrBlockContext constrBlock() {
			return getRuleContext(ConstrBlockContext.class,0);
		}
		public FunDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitFunDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDefContext funDef() throws RecognitionException {
		FunDefContext _localctx = new FunDefContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_funDef);
		int _la;
		try {
			setState(1237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1210);
				funSig();
				setState(1213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(1211);
					match(T__18);
					setState(1212);
					type_();
					}
				}

				setState(1215);
				match(T__33);
				setState(1216);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1218);
				funSig();
				setState(1220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(1219);
					match(NL);
					}
				}

				setState(1222);
				match(T__12);
				setState(1223);
				block();
				setState(1224);
				match(T__13);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1226);
				match(T__4);
				setState(1227);
				paramClause();
				setState(1228);
				paramClauses();
				setState(1235);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__33:
					{
					setState(1229);
					match(T__33);
					setState(1230);
					constrExpr();
					}
					break;
				case T__12:
				case NL:
					{
					setState(1232);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1231);
						match(NL);
						}
					}

					setState(1234);
					constrBlock();
					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static class TypeDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_typeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1239);
			match(Id);
			setState(1241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1240);
				typeParamClause();
				}
			}

			setState(1243);
			match(T__33);
			setState(1244);
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

	public static class TmplDefContext extends ParserRuleContext {
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public ObjectDefContext objectDef() {
			return getRuleContext(ObjectDefContext.class,0);
		}
		public TraitDefContext traitDef() {
			return getRuleContext(TraitDefContext.class,0);
		}
		public TmplDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tmplDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTmplDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTmplDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTmplDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TmplDefContext tmplDef() throws RecognitionException {
		TmplDefContext _localctx = new TmplDefContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_tmplDef);
		int _la;
		try {
			setState(1258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,174,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__41) {
					{
					setState(1246);
					match(T__41);
					}
				}

				setState(1249);
				match(T__56);
				setState(1250);
				classDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__41) {
					{
					setState(1251);
					match(T__41);
					}
				}

				setState(1254);
				match(T__57);
				setState(1255);
				objectDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1256);
				match(T__58);
				setState(1257);
				traitDef();
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

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassParamClausesContext classParamClauses() {
			return getRuleContext(ClassParamClausesContext.class,0);
		}
		public ClassTemplateOptContext classTemplateOpt() {
			return getRuleContext(ClassTemplateOptContext.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<ConstrAnnotationContext> constrAnnotation() {
			return getRuleContexts(ConstrAnnotationContext.class);
		}
		public ConstrAnnotationContext constrAnnotation(int i) {
			return getRuleContext(ConstrAnnotationContext.class,i);
		}
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_classDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1260);
			match(Id);
			setState(1262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1261);
				typeParamClause();
				}
			}

			setState(1267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,176,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1264);
					constrAnnotation();
					}
					} 
				}
				setState(1269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,176,_ctx);
			}
			setState(1271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,177,_ctx) ) {
			case 1:
				{
				setState(1270);
				accessModifier();
				}
				break;
			}
			setState(1273);
			classParamClauses();
			setState(1274);
			classTemplateOpt();
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

	public static class TraitDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TraitTemplateOptContext traitTemplateOpt() {
			return getRuleContext(TraitTemplateOptContext.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public TraitDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTraitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitDefContext traitDef() throws RecognitionException {
		TraitDefContext _localctx = new TraitDefContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_traitDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1276);
			match(Id);
			setState(1278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1277);
				typeParamClause();
				}
			}

			setState(1280);
			traitTemplateOpt();
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

	public static class ObjectDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassTemplateOptContext classTemplateOpt() {
			return getRuleContext(ClassTemplateOptContext.class,0);
		}
		public ObjectDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterObjectDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitObjectDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitObjectDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectDefContext objectDef() throws RecognitionException {
		ObjectDefContext _localctx = new ObjectDefContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_objectDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1282);
			match(Id);
			setState(1283);
			classTemplateOpt();
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

	public static class ClassTemplateOptContext extends ParserRuleContext {
		public ClassTemplateContext classTemplate() {
			return getRuleContext(ClassTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public ClassTemplateOptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTemplateOpt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassTemplateOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassTemplateOpt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassTemplateOpt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTemplateOptContext classTemplateOpt() throws RecognitionException {
		ClassTemplateOptContext _localctx = new ClassTemplateOptContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_classTemplateOpt);
		int _la;
		try {
			setState(1293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1285);
				match(T__59);
				setState(1286);
				classTemplate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1291);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,180,_ctx) ) {
				case 1:
					{
					setState(1288);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__59) {
						{
						setState(1287);
						match(T__59);
						}
					}

					setState(1290);
					templateBody();
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

	public static class TraitTemplateOptContext extends ParserRuleContext {
		public TraitTemplateContext traitTemplate() {
			return getRuleContext(TraitTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TraitTemplateOptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitTemplateOpt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitTemplateOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitTemplateOpt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTraitTemplateOpt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitTemplateOptContext traitTemplateOpt() throws RecognitionException {
		TraitTemplateOptContext _localctx = new TraitTemplateOptContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_traitTemplateOpt);
		int _la;
		try {
			setState(1303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,184,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1295);
				match(T__59);
				setState(1296);
				traitTemplate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1301);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
				case 1:
					{
					setState(1298);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__59) {
						{
						setState(1297);
						match(T__59);
						}
					}

					setState(1300);
					templateBody();
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

	public static class ClassTemplateContext extends ParserRuleContext {
		public ClassParentsContext classParents() {
			return getRuleContext(ClassParentsContext.class,0);
		}
		public EarlyDefsContext earlyDefs() {
			return getRuleContext(EarlyDefsContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public ClassTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTemplateContext classTemplate() throws RecognitionException {
		ClassTemplateContext _localctx = new ClassTemplateContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_classTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(1305);
				earlyDefs();
				}
			}

			setState(1308);
			classParents();
			setState(1310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,186,_ctx) ) {
			case 1:
				{
				setState(1309);
				templateBody();
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

	public static class TraitTemplateContext extends ParserRuleContext {
		public TraitParentsContext traitParents() {
			return getRuleContext(TraitParentsContext.class,0);
		}
		public EarlyDefsContext earlyDefs() {
			return getRuleContext(EarlyDefsContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TraitTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitTemplate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTraitTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitTemplateContext traitTemplate() throws RecognitionException {
		TraitTemplateContext _localctx = new TraitTemplateContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_traitTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(1312);
				earlyDefs();
				}
			}

			setState(1315);
			traitParents();
			setState(1317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,188,_ctx) ) {
			case 1:
				{
				setState(1316);
				templateBody();
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

	public static class ClassParentsContext extends ParserRuleContext {
		public ConstrContext constr() {
			return getRuleContext(ConstrContext.class,0);
		}
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public ClassParentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitClassParents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassParentsContext classParents() throws RecognitionException {
		ClassParentsContext _localctx = new ClassParentsContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_classParents);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1319);
			constr();
			setState(1324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1320);
					match(T__16);
					setState(1321);
					annotType();
					}
					} 
				}
				setState(1326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
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

	public static class TraitParentsContext extends ParserRuleContext {
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public TraitParentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitParents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitParents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitParents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTraitParents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitParentsContext traitParents() throws RecognitionException {
		TraitParentsContext _localctx = new TraitParentsContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_traitParents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1327);
			annotType();
			setState(1332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(1328);
				match(T__16);
				setState(1329);
				annotType();
				}
				}
				setState(1334);
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

	public static class ConstrContext extends ParserRuleContext {
		public AnnotTypeContext annotType() {
			return getRuleContext(AnnotTypeContext.class,0);
		}
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public ConstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitConstr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstrContext constr() throws RecognitionException {
		ConstrContext _localctx = new ConstrContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_constr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1335);
			annotType();
			setState(1339);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1336);
					argumentExprs();
					}
					} 
				}
				setState(1341);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
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

	public static class EarlyDefsContext extends ParserRuleContext {
		public List<EarlyDefContext> earlyDef() {
			return getRuleContexts(EarlyDefContext.class);
		}
		public EarlyDefContext earlyDef(int i) {
			return getRuleContext(EarlyDefContext.class,i);
		}
		public EarlyDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_earlyDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEarlyDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEarlyDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitEarlyDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EarlyDefsContext earlyDefs() throws RecognitionException {
		EarlyDefsContext _localctx = new EarlyDefsContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_earlyDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1342);
			match(T__12);
			setState(1344); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1343);
				earlyDef();
				}
				}
				setState(1346); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__21) | (1L << T__39) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0) );
			setState(1348);
			match(T__13);
			setState(1349);
			match(T__16);
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

	public static class EarlyDefContext extends ParserRuleContext {
		public PatVarDefContext patVarDef() {
			return getRuleContext(PatVarDefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public EarlyDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_earlyDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEarlyDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEarlyDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitEarlyDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EarlyDefContext earlyDef() throws RecognitionException {
		EarlyDefContext _localctx = new EarlyDefContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_earlyDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(1351);
				annotation();
				setState(1353);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(1352);
					match(NL);
					}
				}

				}
				}
				setState(1359);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
				{
				{
				setState(1360);
				modifier();
				}
				}
				setState(1365);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1366);
			patVarDef();
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

	public static class ConstrExprContext extends ParserRuleContext {
		public SelfInvocationContext selfInvocation() {
			return getRuleContext(SelfInvocationContext.class,0);
		}
		public ConstrBlockContext constrBlock() {
			return getRuleContext(ConstrBlockContext.class,0);
		}
		public ConstrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitConstrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstrExprContext constrExpr() throws RecognitionException {
		ConstrExprContext _localctx = new ConstrExprContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_constrExpr);
		try {
			setState(1370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(1368);
				selfInvocation();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(1369);
				constrBlock();
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

	public static class ConstrBlockContext extends ParserRuleContext {
		public SelfInvocationContext selfInvocation() {
			return getRuleContext(SelfInvocationContext.class,0);
		}
		public List<BlockStatContext> blockStat() {
			return getRuleContexts(BlockStatContext.class);
		}
		public BlockStatContext blockStat(int i) {
			return getRuleContext(BlockStatContext.class,i);
		}
		public ConstrBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitConstrBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstrBlockContext constrBlock() throws RecognitionException {
		ConstrBlockContext _localctx = new ConstrBlockContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_constrBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1372);
			match(T__12);
			setState(1373);
			selfInvocation();
			setState(1377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__47) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
				{
				{
				setState(1374);
				blockStat();
				}
				}
				setState(1379);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1380);
			match(T__13);
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

	public static class SelfInvocationContext extends ParserRuleContext {
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public SelfInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSelfInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSelfInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitSelfInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfInvocationContext selfInvocation() throws RecognitionException {
		SelfInvocationContext _localctx = new SelfInvocationContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_selfInvocation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1382);
			match(T__4);
			setState(1384); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1383);
					argumentExprs();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1386); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
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

	public static class TopStatSeqContext extends ParserRuleContext {
		public List<TopStatContext> topStat() {
			return getRuleContexts(TopStatContext.class);
		}
		public TopStatContext topStat(int i) {
			return getRuleContext(TopStatContext.class,i);
		}
		public TopStatSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topStatSeq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTopStatSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTopStatSeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTopStatSeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopStatSeqContext topStatSeq() throws RecognitionException {
		TopStatSeqContext _localctx = new TopStatSeqContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_topStatSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1389); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1388);
				topStat();
				}
				}
				setState(1391); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60))) != 0) );
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

	public static class TopStatContext extends ParserRuleContext {
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public PackagingContext packaging() {
			return getRuleContext(PackagingContext.class,0);
		}
		public PackageObjectContext packageObject() {
			return getRuleContext(PackageObjectContext.class,0);
		}
		public TopStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTopStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTopStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitTopStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopStatContext topStat() throws RecognitionException {
		TopStatContext _localctx = new TopStatContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_topStat);
		int _la;
		try {
			setState(1412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,203,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1393);
					annotation();
					setState(1395);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1394);
						match(NL);
						}
					}

					}
					}
					setState(1401);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1405);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1402);
					modifier();
					}
					}
					setState(1407);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1408);
				tmplDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1409);
				import_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1410);
				packaging();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1411);
				packageObject();
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

	public static class PackagingContext extends ParserRuleContext {
		public QualIdContext qualId() {
			return getRuleContext(QualIdContext.class,0);
		}
		public TopStatSeqContext topStatSeq() {
			return getRuleContext(TopStatSeqContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public PackagingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packaging; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPackaging(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPackaging(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPackaging(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackagingContext packaging() throws RecognitionException {
		PackagingContext _localctx = new PackagingContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_packaging);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1414);
			match(T__60);
			setState(1415);
			qualId();
			setState(1417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(1416);
				match(NL);
				}
			}

			setState(1419);
			match(T__12);
			setState(1420);
			topStatSeq();
			setState(1421);
			match(T__13);
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

	public static class PackageObjectContext extends ParserRuleContext {
		public ObjectDefContext objectDef() {
			return getRuleContext(ObjectDefContext.class,0);
		}
		public PackageObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPackageObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPackageObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitPackageObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageObjectContext packageObject() throws RecognitionException {
		PackageObjectContext _localctx = new PackageObjectContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_packageObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1423);
			match(T__60);
			setState(1424);
			match(T__57);
			setState(1425);
			objectDef();
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

	public static class CompilationUnitContext extends ParserRuleContext {
		public TopStatSeqContext topStatSeq() {
			return getRuleContext(TopStatSeqContext.class,0);
		}
		public List<QualIdContext> qualId() {
			return getRuleContexts(QualIdContext.class);
		}
		public QualIdContext qualId(int i) {
			return getRuleContext(QualIdContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScalaVisitor ) return ((ScalaVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_compilationUnit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1431);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,205,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1427);
					match(T__60);
					setState(1428);
					qualId();
					}
					} 
				}
				setState(1433);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,205,_ctx);
			}
			setState(1434);
			topStatSeq();
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
		case 3:
			return stableId_sempred((StableIdContext)_localctx, predIndex);
		case 12:
			return simpleType_sempred((SimpleTypeContext)_localctx, predIndex);
		case 23:
			return infixExpr_sempred((InfixExprContext)_localctx, predIndex);
		case 26:
			return simpleExpr1_sempred((SimpleExpr1Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean stableId_sempred(StableIdContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean simpleType_sempred(SimpleTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean infixExpr_sempred(InfixExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simpleExpr1_sempred(SimpleExpr1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3P\u059f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\3\2\5"+
		"\2\u00d6\n\2\3\2\3\2\5\2\u00da\n\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u00e2\n"+
		"\2\3\3\3\3\3\3\7\3\u00e7\n\3\f\3\16\3\u00ea\13\3\3\4\3\4\3\4\7\4\u00ef"+
		"\n\4\f\4\16\4\u00f2\13\4\3\5\3\5\3\5\3\5\5\5\u00f8\n\5\3\5\3\5\3\5\5\5"+
		"\u00fd\n\5\3\5\3\5\5\5\u0101\n\5\5\5\u0103\n\5\3\5\3\5\3\5\7\5\u0108\n"+
		"\5\f\5\16\5\u010b\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0117"+
		"\n\7\5\7\u0119\n\7\3\b\3\b\3\b\3\b\3\b\7\b\u0120\n\b\f\b\16\b\u0123\13"+
		"\b\5\b\u0125\n\b\3\b\5\b\u0128\n\b\3\t\3\t\3\t\6\t\u012d\n\t\r\t\16\t"+
		"\u012e\3\t\3\t\3\n\3\n\3\n\3\n\5\n\u0137\n\n\3\13\3\13\3\13\7\13\u013c"+
		"\n\13\f\13\16\13\u013f\13\13\3\f\3\f\3\f\7\f\u0144\n\f\f\f\16\f\u0147"+
		"\13\f\3\f\5\f\u014a\n\f\3\f\5\f\u014d\n\f\3\r\3\r\7\r\u0151\n\r\f\r\16"+
		"\r\u0154\13\r\3\16\3\16\3\16\3\16\5\16\u015a\n\16\3\16\3\16\3\16\3\16"+
		"\5\16\u0160\n\16\3\16\3\16\3\16\3\16\3\16\7\16\u0167\n\16\f\16\16\16\u016a"+
		"\13\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\7\20\u0173\n\20\f\20\16\20\u0176"+
		"\13\20\3\21\5\21\u0179\n\21\3\21\3\21\6\21\u017d\n\21\r\21\16\21\u017e"+
		"\3\21\3\21\3\22\3\22\3\22\5\22\u0186\n\22\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\6\24\u018e\n\24\r\24\16\24\u018f\3\24\3\24\3\24\5\24\u0195\n\24\3\25"+
		"\3\25\5\25\u0199\n\25\3\25\3\25\5\25\u019d\n\25\3\25\3\25\3\25\5\25\u01a2"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\7\26\u01a9\n\26\f\26\16\26\u01ac\13\26"+
		"\3\26\3\26\3\26\5\26\u01b1\n\26\3\26\3\26\3\26\3\26\3\26\7\26\u01b8\n"+
		"\26\f\26\16\26\u01bb\13\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01c3\n"+
		"\26\3\26\3\26\5\26\u01c7\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01d9\n\26\3\26\5\26\u01dc"+
		"\n\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01e4\n\26\3\26\3\26\3\26\5\26"+
		"\u01e9\n\26\5\26\u01eb\n\26\3\26\3\26\5\26\u01ef\n\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01fb\n\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\5\26\u0203\n\26\3\27\3\27\3\30\3\30\5\30\u0209\n\30\3\30\3"+
		"\30\3\30\7\30\u020e\n\30\f\30\16\30\u0211\13\30\3\30\5\30\u0214\n\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\5\31\u021c\n\31\3\31\7\31\u021f\n\31\f\31"+
		"\16\31\u0222\13\31\3\32\5\32\u0225\n\32\3\32\3\32\3\32\5\32\u022a\n\32"+
		"\5\32\u022c\n\32\3\33\3\33\3\33\5\33\u0231\n\33\3\33\5\33\u0234\n\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\5\34\u023c\n\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\5\34\u0246\n\34\3\34\3\34\5\34\u024a\n\34\3\34\3\34\3"+
		"\34\3\34\5\34\u0250\n\34\3\34\3\34\3\34\7\34\u0255\n\34\f\34\16\34\u0258"+
		"\13\34\3\35\3\35\3\35\7\35\u025d\n\35\f\35\16\35\u0260\13\35\3\36\3\36"+
		"\5\36\u0264\n\36\3\36\3\36\3\36\3\36\3\36\5\36\u026b\n\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\5\36\u0274\n\36\3\36\5\36\u0277\n\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0281\n\37\3 \6 \u0284\n \r \16 \u0285"+
		"\3 \5 \u0289\n \3!\3!\7!\u028d\n!\f!\16!\u0290\13!\3!\5!\u0293\n!\3!\3"+
		"!\7!\u0297\n!\f!\16!\u029a\13!\3!\7!\u029d\n!\f!\16!\u02a0\13!\3!\3!\5"+
		"!\u02a4\n!\3\"\3\"\3\"\5\"\u02a9\n\"\3\"\3\"\5\"\u02ad\n\"\3\"\3\"\5\""+
		"\u02b1\n\"\3\"\3\"\3\"\5\"\u02b6\n\"\3#\6#\u02b9\n#\r#\16#\u02ba\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\7$\u02c5\n$\f$\16$\u02c8\13$\3%\6%\u02cb\n%\r%\16"+
		"%\u02cc\3&\3&\3&\5&\u02d2\n&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\7(\u02dd\n"+
		"(\f(\16(\u02e0\13(\3)\3)\3)\3)\5)\u02e6\n)\3*\3*\3*\5*\u02eb\n*\3*\5*"+
		"\u02ee\n*\3+\3+\3+\3+\5+\u02f4\n+\3+\7+\u02f7\n+\f+\16+\u02fa\13+\5+\u02fc"+
		"\n+\3,\3,\3,\3,\3,\3,\5,\u0304\n,\3,\5,\u0307\n,\3,\3,\3,\3,\3,\5,\u030e"+
		"\n,\3,\3,\5,\u0312\n,\3,\3,\3,\3,\3,\3,\5,\u031a\n,\3,\5,\u031d\n,\3-"+
		"\3-\3-\5-\u0322\n-\3-\3-\5-\u0326\n-\3.\3.\3.\3.\7.\u032c\n.\f.\16.\u032f"+
		"\13.\3.\3.\3/\3/\3/\3/\7/\u0337\n/\f/\16/\u033a\13/\3/\3/\3\60\7\60\u033f"+
		"\n\60\f\60\16\60\u0342\13\60\3\60\5\60\u0345\n\60\3\60\3\60\3\61\3\61"+
		"\5\61\u034b\n\61\3\61\3\61\5\61\u034f\n\61\3\61\3\61\5\61\u0353\n\61\3"+
		"\61\3\61\7\61\u0357\n\61\f\61\16\61\u035a\13\61\3\61\3\61\7\61\u035e\n"+
		"\61\f\61\16\61\u0361\13\61\3\62\7\62\u0364\n\62\f\62\16\62\u0367\13\62"+
		"\3\62\5\62\u036a\n\62\3\62\3\62\3\62\3\62\3\62\5\62\u0371\n\62\3\63\5"+
		"\63\u0374\n\63\3\63\3\63\5\63\u0378\n\63\3\63\3\63\3\64\3\64\3\64\7\64"+
		"\u037f\n\64\f\64\16\64\u0382\13\64\3\65\7\65\u0385\n\65\f\65\16\65\u0388"+
		"\13\65\3\65\3\65\3\65\5\65\u038d\n\65\3\65\3\65\5\65\u0391\n\65\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\5\66\u0399\n\66\3\67\7\67\u039c\n\67\f\67\16\67"+
		"\u039f\13\67\3\67\5\67\u03a2\n\67\3\67\3\67\3\67\3\67\3\67\5\67\u03a9"+
		"\n\67\38\58\u03ac\n8\38\38\58\u03b0\n8\38\38\39\39\39\79\u03b7\n9\f9\16"+
		"9\u03ba\139\3:\7:\u03bd\n:\f:\16:\u03c0\13:\3:\7:\u03c3\n:\f:\16:\u03c6"+
		"\13:\3:\5:\u03c9\n:\3:\3:\3:\3:\3:\5:\u03d0\n:\3;\3;\3;\3;\7;\u03d6\n"+
		";\f;\16;\u03d9\13;\3;\3;\3<\3<\3<\5<\u03e0\n<\3=\3=\3=\5=\u03e5\n=\3>"+
		"\3>\3?\3?\5?\u03eb\n?\3@\3@\3@\3@\3A\3A\3A\7A\u03f4\nA\fA\16A\u03f7\13"+
		"A\3B\3B\3B\3B\3C\5C\u03fe\nC\3C\3C\5C\u0402\nC\3C\6C\u0405\nC\rC\16C\u0406"+
		"\3C\3C\3D\3D\3D\5D\u040e\nD\7D\u0410\nD\fD\16D\u0413\13D\3D\7D\u0416\n"+
		"D\fD\16D\u0419\13D\3D\3D\3D\5D\u041e\nD\7D\u0420\nD\fD\16D\u0423\13D\3"+
		"D\7D\u0426\nD\fD\16D\u0429\13D\3D\3D\5D\u042d\nD\3E\3E\3E\5E\u0432\nE"+
		"\3E\3E\3E\3E\3E\3E\5E\u043a\nE\3F\3F\3F\3F\7F\u0440\nF\fF\16F\u0443\13"+
		"F\3G\3G\3G\3G\3G\5G\u044a\nG\5G\u044c\nG\3H\3H\3H\3H\7H\u0452\nH\fH\16"+
		"H\u0455\13H\3H\3H\5H\u0459\nH\3H\3H\3I\3I\3I\5I\u0460\nI\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\7J\u046a\nJ\fJ\16J\u046d\13J\3J\5J\u0470\nJ\3K\3K\3K\3K\3L"+
		"\3L\3L\3L\3M\3M\3M\5M\u047d\nM\3N\3N\5N\u0481\nN\3N\3N\3O\3O\5O\u0487"+
		"\nO\3O\3O\5O\u048b\nO\3O\3O\5O\u048f\nO\3P\3P\3P\3P\5P\u0495\nP\3Q\3Q"+
		"\3Q\3Q\3Q\7Q\u049c\nQ\fQ\16Q\u049f\13Q\3Q\3Q\5Q\u04a3\nQ\3R\3R\3R\7R\u04a8"+
		"\nR\fR\16R\u04ab\13R\3R\3R\5R\u04af\nR\3R\3R\3R\3S\3S\3S\3S\3S\3S\3S\5"+
		"S\u04bb\nS\3T\3T\3T\5T\u04c0\nT\3T\3T\3T\3T\3T\5T\u04c7\nT\3T\3T\3T\3"+
		"T\3T\3T\3T\3T\3T\3T\5T\u04d3\nT\3T\5T\u04d6\nT\5T\u04d8\nT\3U\3U\5U\u04dc"+
		"\nU\3U\3U\3U\3V\5V\u04e2\nV\3V\3V\3V\5V\u04e7\nV\3V\3V\3V\3V\5V\u04ed"+
		"\nV\3W\3W\5W\u04f1\nW\3W\7W\u04f4\nW\fW\16W\u04f7\13W\3W\5W\u04fa\nW\3"+
		"W\3W\3W\3X\3X\5X\u0501\nX\3X\3X\3Y\3Y\3Y\3Z\3Z\3Z\5Z\u050b\nZ\3Z\5Z\u050e"+
		"\nZ\5Z\u0510\nZ\3[\3[\3[\5[\u0515\n[\3[\5[\u0518\n[\5[\u051a\n[\3\\\5"+
		"\\\u051d\n\\\3\\\3\\\5\\\u0521\n\\\3]\5]\u0524\n]\3]\3]\5]\u0528\n]\3"+
		"^\3^\3^\7^\u052d\n^\f^\16^\u0530\13^\3_\3_\3_\7_\u0535\n_\f_\16_\u0538"+
		"\13_\3`\3`\7`\u053c\n`\f`\16`\u053f\13`\3a\3a\6a\u0543\na\ra\16a\u0544"+
		"\3a\3a\3a\3b\3b\5b\u054c\nb\7b\u054e\nb\fb\16b\u0551\13b\3b\7b\u0554\n"+
		"b\fb\16b\u0557\13b\3b\3b\3c\3c\5c\u055d\nc\3d\3d\3d\7d\u0562\nd\fd\16"+
		"d\u0565\13d\3d\3d\3e\3e\6e\u056b\ne\re\16e\u056c\3f\6f\u0570\nf\rf\16"+
		"f\u0571\3g\3g\5g\u0576\ng\7g\u0578\ng\fg\16g\u057b\13g\3g\7g\u057e\ng"+
		"\fg\16g\u0581\13g\3g\3g\3g\3g\5g\u0587\ng\3h\3h\3h\5h\u058c\nh\3h\3h\3"+
		"h\3h\3i\3i\3i\3i\3j\3j\7j\u0598\nj\fj\16j\u059b\13j\3j\3j\3j\2\6\b\32"+
		"\60\66k\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba"+
		"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2"+
		"\2\13\4\2\3\3&(\4\2\30\30**\5\2\26\26@@HH\4\2\3\3&&\4\2\26\26@@\4\2\22"+
		"\22\62\62\5\2\30\30**\64\66\3\2\678\4\2\7\7@@\2\u062c\2\u00e1\3\2\2\2"+
		"\4\u00e3\3\2\2\2\6\u00eb\3\2\2\2\b\u0102\3\2\2\2\n\u010c\3\2\2\2\f\u0118"+
		"\3\2\2\2\16\u0127\3\2\2\2\20\u0129\3\2\2\2\22\u0136\3\2\2\2\24\u0138\3"+
		"\2\2\2\26\u014c\3\2\2\2\30\u014e\3\2\2\2\32\u015f\3\2\2\2\34\u016b\3\2"+
		"\2\2\36\u016f\3\2\2\2 \u0178\3\2\2\2\"\u0185\3\2\2\2$\u0187\3\2\2\2&\u0194"+
		"\3\2\2\2(\u01a1\3\2\2\2*\u0202\3\2\2\2,\u0204\3\2\2\2.\u0206\3\2\2\2\60"+
		"\u0215\3\2\2\2\62\u0224\3\2\2\2\64\u0233\3\2\2\2\66\u0245\3\2\2\28\u0259"+
		"\3\2\2\2:\u0276\3\2\2\2<\u0280\3\2\2\2>\u0283\3\2\2\2@\u02a3\3\2\2\2B"+
		"\u02b5\3\2\2\2D\u02b8\3\2\2\2F\u02bc\3\2\2\2H\u02ca\3\2\2\2J\u02ce\3\2"+
		"\2\2L\u02d6\3\2\2\2N\u02d9\3\2\2\2P\u02e5\3\2\2\2R\u02ed\3\2\2\2T\u02fb"+
		"\3\2\2\2V\u031c\3\2\2\2X\u0325\3\2\2\2Z\u0327\3\2\2\2\\\u0332\3\2\2\2"+
		"^\u0340\3\2\2\2`\u0348\3\2\2\2b\u0365\3\2\2\2d\u0373\3\2\2\2f\u037b\3"+
		"\2\2\2h\u0386\3\2\2\2j\u0398\3\2\2\2l\u039d\3\2\2\2n\u03ab\3\2\2\2p\u03b3"+
		"\3\2\2\2r\u03be\3\2\2\2t\u03d1\3\2\2\2v\u03dc\3\2\2\2x\u03e4\3\2\2\2z"+
		"\u03e6\3\2\2\2|\u03e8\3\2\2\2~\u03ec\3\2\2\2\u0080\u03f0\3\2\2\2\u0082"+
		"\u03f8\3\2\2\2\u0084\u03fd\3\2\2\2\u0086\u042c\3\2\2\2\u0088\u0439\3\2"+
		"\2\2\u008a\u043b\3\2\2\2\u008c\u0444\3\2\2\2\u008e\u044d\3\2\2\2\u0090"+
		"\u045c\3\2\2\2\u0092\u046f\3\2\2\2\u0094\u0471\3\2\2\2\u0096\u0475\3\2"+
		"\2\2\u0098\u0479\3\2\2\2\u009a\u047e\3\2\2\2\u009c\u0484\3\2\2\2\u009e"+
		"\u0494\3\2\2\2\u00a0\u04a2\3\2\2\2\u00a2\u04a4\3\2\2\2\u00a4\u04ba\3\2"+
		"\2\2\u00a6\u04d7\3\2\2\2\u00a8\u04d9\3\2\2\2\u00aa\u04ec\3\2\2\2\u00ac"+
		"\u04ee\3\2\2\2\u00ae\u04fe\3\2\2\2\u00b0\u0504\3\2\2\2\u00b2\u050f\3\2"+
		"\2\2\u00b4\u0519\3\2\2\2\u00b6\u051c\3\2\2\2\u00b8\u0523\3\2\2\2\u00ba"+
		"\u0529\3\2\2\2\u00bc\u0531\3\2\2\2\u00be\u0539\3\2\2\2\u00c0\u0540\3\2"+
		"\2\2\u00c2\u054f\3\2\2\2\u00c4\u055c\3\2\2\2\u00c6\u055e\3\2\2\2\u00c8"+
		"\u0568\3\2\2\2\u00ca\u056f\3\2\2\2\u00cc\u0586\3\2\2\2\u00ce\u0588\3\2"+
		"\2\2\u00d0\u0591\3\2\2\2\u00d2\u0599\3\2\2\2\u00d4\u00d6\7\3\2\2\u00d5"+
		"\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00e2\7D"+
		"\2\2\u00d8\u00da\7\3\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00e2\7F\2\2\u00dc\u00e2\7A\2\2\u00dd\u00e2\7B\2"+
		"\2\u00de\u00e2\7E\2\2\u00df\u00e2\7C\2\2\u00e0\u00e2\7\4\2\2\u00e1\u00d5"+
		"\3\2\2\2\u00e1\u00d9\3\2\2\2\u00e1\u00dc\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e1"+
		"\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\3\3\2\2\2"+
		"\u00e3\u00e8\7@\2\2\u00e4\u00e5\7\5\2\2\u00e5\u00e7\7@\2\2\u00e6\u00e4"+
		"\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\5\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00f0\7@\2\2\u00ec\u00ed\7\6\2\2"+
		"\u00ed\u00ef\7@\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\7\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f4\b\5\1\2\u00f4\u0103\7@\2\2\u00f5\u00f6\7@\2\2\u00f6\u00f8\7\5\2"+
		"\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u0100\3\2\2\2\u00f9\u0101"+
		"\7\7\2\2\u00fa\u00fc\7\b\2\2\u00fb\u00fd\5\n\6\2\u00fc\u00fb\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\7\5\2\2\u00ff\u0101\7@"+
		"\2\2\u0100\u00f9\3\2\2\2\u0100\u00fa\3\2\2\2\u0101\u0103\3\2\2\2\u0102"+
		"\u00f3\3\2\2\2\u0102\u00f7\3\2\2\2\u0103\u0109\3\2\2\2\u0104\u0105\f\4"+
		"\2\2\u0105\u0106\7\5\2\2\u0106\u0108\7@\2\2\u0107\u0104\3\2\2\2\u0108"+
		"\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\t\3\2\2\2"+
		"\u010b\u0109\3\2\2\2\u010c\u010d\7\t\2\2\u010d\u010e\7@\2\2\u010e\u010f"+
		"\7\n\2\2\u010f\13\3\2\2\2\u0110\u0111\5\16\b\2\u0111\u0112\7\13\2\2\u0112"+
		"\u0113\5\f\7\2\u0113\u0119\3\2\2\2\u0114\u0116\5\24\13\2\u0115\u0117\5"+
		"\20\t\2\u0116\u0115\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2\2\2\u0118"+
		"\u0110\3\2\2\2\u0118\u0114\3\2\2\2\u0119\r\3\2\2\2\u011a\u0128\5\24\13"+
		"\2\u011b\u0124\7\f\2\2\u011c\u0121\5j\66\2\u011d\u011e\7\6\2\2\u011e\u0120"+
		"\5j\66\2\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u011c\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0128\7\r\2\2\u0127"+
		"\u011a\3\2\2\2\u0127\u011b\3\2\2\2\u0128\17\3\2\2\2\u0129\u012a\7\16\2"+
		"\2\u012a\u012c\7\17\2\2\u012b\u012d\5\22\n\2\u012c\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2"+
		"\2\2\u0130\u0131\7\20\2\2\u0131\21\3\2\2\2\u0132\u0133\7\21\2\2\u0133"+
		"\u0137\5\u009cO\2\u0134\u0135\7\22\2\2\u0135\u0137\5\u0094K\2\u0136\u0132"+
		"\3\2\2\2\u0136\u0134\3\2\2\2\u0137\23\3\2\2\2\u0138\u013d\5\26\f\2\u0139"+
		"\u013a\7@\2\2\u013a\u013c\5\26\f\2\u013b\u0139\3\2\2\2\u013c\u013f\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\25\3\2\2\2\u013f\u013d"+
		"\3\2\2\2\u0140\u0145\5\30\r\2\u0141\u0142\7\23\2\2\u0142\u0144\5\30\r"+
		"\2\u0143\u0141\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146"+
		"\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u014a\5 \21\2\u0149"+
		"\u0148\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u014d\5 "+
		"\21\2\u014c\u0140\3\2\2\2\u014c\u014b\3\2\2\2\u014d\27\3\2\2\2\u014e\u0152"+
		"\5\32\16\2\u014f\u0151\5\u0080A\2\u0150\u014f\3\2\2\2\u0151\u0154\3\2"+
		"\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\31\3\2\2\2\u0154\u0152"+
		"\3\2\2\2\u0155\u0156\b\16\1\2\u0156\u0159\5\b\5\2\u0157\u0158\7\5\2\2"+
		"\u0158\u015a\7\21\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0160"+
		"\3\2\2\2\u015b\u015c\7\f\2\2\u015c\u015d\5\36\20\2\u015d\u015e\7\r\2\2"+
		"\u015e\u0160\3\2\2\2\u015f\u0155\3\2\2\2\u015f\u015b\3\2\2\2\u0160\u0168"+
		"\3\2\2\2\u0161\u0162\f\6\2\2\u0162\u0167\5\34\17\2\u0163\u0164\f\5\2\2"+
		"\u0164\u0165\7\24\2\2\u0165\u0167\7@\2\2\u0166\u0161\3\2\2\2\u0166\u0163"+
		"\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\33\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016c\7\t\2\2\u016c\u016d\5\36\20"+
		"\2\u016d\u016e\7\n\2\2\u016e\35\3\2\2\2\u016f\u0174\5\f\7\2\u0170\u0171"+
		"\7\6\2\2\u0171\u0173\5\f\7\2\u0172\u0170\3\2\2\2\u0173\u0176\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\37\3\2\2\2\u0176\u0174\3\2\2"+
		"\2\u0177\u0179\7L\2\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a"+
		"\3\2\2\2\u017a\u017c\7\17\2\2\u017b\u017d\5\"\22\2\u017c\u017b\3\2\2\2"+
		"\u017d\u017e\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180"+
		"\3\2\2\2\u0180\u0181\7\20\2\2\u0181!\3\2\2\2\u0182\u0186\5\u0092J\2\u0183"+
		"\u0184\7\21\2\2\u0184\u0186\5\u00a8U\2\u0185\u0182\3\2\2\2\u0185\u0183"+
		"\3\2\2\2\u0186#\3\2\2\2\u0187\u0188\5\f\7\2\u0188%\3\2\2\2\u0189\u018a"+
		"\7\25\2\2\u018a\u0195\5\24\13\2\u018b\u018d\7\25\2\2\u018c\u018e\5\u0080"+
		"A\2\u018d\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0195\3\2\2\2\u0191\u0192\7\25\2\2\u0192\u0193\7"+
		"\26\2\2\u0193\u0195\7\27\2\2\u0194\u0189\3\2\2\2\u0194\u018b\3\2\2\2\u0194"+
		"\u0191\3\2\2\2\u0195\'\3\2\2\2\u0196\u019d\5t;\2\u0197\u0199\7\30\2\2"+
		"\u0198\u0197\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019d"+
		"\7@\2\2\u019b\u019d\7\26\2\2\u019c\u0196\3\2\2\2\u019c\u0198\3\2\2\2\u019c"+
		"\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\7\13\2\2\u019f\u01a2\5"+
		"(\25\2\u01a0\u01a2\5*\26\2\u01a1\u019c\3\2\2\2\u01a1\u01a0\3\2\2\2\u01a2"+
		")\3\2\2\2\u01a3\u01a4\7\31\2\2\u01a4\u01a5\7\f\2\2\u01a5\u01a6\5(\25\2"+
		"\u01a6\u01aa\7\r\2\2\u01a7\u01a9\7L\2\2\u01a8\u01a7\3\2\2\2\u01a9\u01ac"+
		"\3\2\2\2\u01aa\u01a8\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ad\3\2\2\2\u01ac"+
		"\u01aa\3\2\2\2\u01ad\u01b0\5(\25\2\u01ae\u01af\7\32\2\2\u01af\u01b1\5"+
		"(\25\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u0203\3\2\2\2\u01b2"+
		"\u01b3\7\33\2\2\u01b3\u01b4\7\f\2\2\u01b4\u01b5\5(\25\2\u01b5\u01b9\7"+
		"\r\2\2\u01b6\u01b8\7L\2\2\u01b7\u01b6\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9"+
		"\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bc\3\2\2\2\u01bb\u01b9\3\2"+
		"\2\2\u01bc\u01bd\5(\25\2\u01bd\u0203\3\2\2\2\u01be\u01bf\7\34\2\2\u01bf"+
		"\u01c2\5(\25\2\u01c0\u01c1\7\35\2\2\u01c1\u01c3\5(\25\2\u01c2\u01c0\3"+
		"\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c6\3\2\2\2\u01c4\u01c5\7\36\2\2\u01c5"+
		"\u01c7\5(\25\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u0203\3\2"+
		"\2\2\u01c8\u01c9\7\37\2\2\u01c9\u01ca\5(\25\2\u01ca\u01cb\7\33\2\2\u01cb"+
		"\u01cc\7\f\2\2\u01cc\u01cd\5(\25\2\u01cd\u01ce\7\r\2\2\u01ce\u0203\3\2"+
		"\2\2\u01cf\u01d8\7 \2\2\u01d0\u01d1\7\f\2\2\u01d1\u01d2\5D#\2\u01d2\u01d3"+
		"\7\r\2\2\u01d3\u01d9\3\2\2\2\u01d4\u01d5\7\17\2\2\u01d5\u01d6\5D#\2\u01d6"+
		"\u01d7\7\20\2\2\u01d7\u01d9\3\2\2\2\u01d8\u01d0\3\2\2\2\u01d8\u01d4\3"+
		"\2\2\2\u01d9\u01db\3\2\2\2\u01da\u01dc\7!\2\2\u01db\u01da\3\2\2\2\u01db"+
		"\u01dc\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01de\5(\25\2\u01de\u0203\3\2"+
		"\2\2\u01df\u01e0\7\"\2\2\u01e0\u0203\5(\25\2\u01e1\u01e3\7#\2\2\u01e2"+
		"\u01e4\5(\25\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u0203\3\2"+
		"\2\2\u01e5\u01eb\5\64\33\2\u01e6\u01e8\5\66\34\2\u01e7\u01e9\7\26\2\2"+
		"\u01e8\u01e7\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01eb\3\2\2\2\u01ea\u01e5"+
		"\3\2\2\2\u01ea\u01e6\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\7\5\2\2\u01ed"+
		"\u01ef\3\2\2\2\u01ee\u01ea\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\3\2"+
		"\2\2\u01f0\u01f1\7@\2\2\u01f1\u01f2\7$\2\2\u01f2\u0203\5(\25\2\u01f3\u01f4"+
		"\5\66\34\2\u01f4\u01f5\5:\36\2\u01f5\u01f6\7$\2\2\u01f6\u01f7\5(\25\2"+
		"\u01f7\u0203\3\2\2\2\u01f8\u01fa\5.\30\2\u01f9\u01fb\5&\24\2\u01fa\u01f9"+
		"\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u0203\3\2\2\2\u01fc\u01fd\5.\30\2\u01fd"+
		"\u01fe\7%\2\2\u01fe\u01ff\7\17\2\2\u01ff\u0200\5H%\2\u0200\u0201\7\20"+
		"\2\2\u0201\u0203\3\2\2\2\u0202\u01a3\3\2\2\2\u0202\u01b2\3\2\2\2\u0202"+
		"\u01be\3\2\2\2\u0202\u01c8\3\2\2\2\u0202\u01cf\3\2\2\2\u0202\u01df\3\2"+
		"\2\2\u0202\u01e1\3\2\2\2\u0202\u01ee\3\2\2\2\u0202\u01f3\3\2\2\2\u0202"+
		"\u01f8\3\2\2\2\u0202\u01fc\3\2\2\2\u0203+\3\2\2\2\u0204\u0205\t\2\2\2"+
		"\u0205-\3\2\2\2\u0206\u0208\5\60\31\2\u0207\u0209\7@\2\2\u0208\u0207\3"+
		"\2\2\2\u0208\u0209\3\2\2\2\u0209\u020f\3\2\2\2\u020a\u020b\5,\27\2\u020b"+
		"\u020c\5\66\34\2\u020c\u020e\3\2\2\2\u020d\u020a\3\2\2\2\u020e\u0211\3"+
		"\2\2\2\u020f\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0213\3\2\2\2\u0211"+
		"\u020f\3\2\2\2\u0212\u0214\7L\2\2\u0213\u0212\3\2\2\2\u0213\u0214\3\2"+
		"\2\2\u0214/\3\2\2\2\u0215\u0216\b\31\1\2\u0216\u0217\5\62\32\2\u0217\u0220"+
		"\3\2\2\2\u0218\u0219\f\3\2\2\u0219\u021b\7@\2\2\u021a\u021c\7L\2\2\u021b"+
		"\u021a\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021f\5\60"+
		"\31\4\u021e\u0218\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u021e\3\2\2\2\u0220"+
		"\u0221\3\2\2\2\u0221\61\3\2\2\2\u0222\u0220\3\2\2\2\u0223\u0225\5,\27"+
		"\2\u0224\u0223\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u022b\3\2\2\2\u0226\u022c"+
		"\5\64\33\2\u0227\u0229\5\66\34\2\u0228\u022a\7\26\2\2\u0229\u0228\3\2"+
		"\2\2\u0229\u022a\3\2\2\2\u022a\u022c\3\2\2\2\u022b\u0226\3\2\2\2\u022b"+
		"\u0227\3\2\2\2\u022c\63\3\2\2\2\u022d\u0230\7)\2\2\u022e\u0231\5\u00b6"+
		"\\\2\u022f\u0231\5\u0084C\2\u0230\u022e\3\2\2\2\u0230\u022f\3\2\2\2\u0231"+
		"\u0234\3\2\2\2\u0232\u0234\5<\37\2\u0233\u022d\3\2\2\2\u0233\u0232\3\2"+
		"\2\2\u0234\65\3\2\2\2\u0235\u0236\b\34\1\2\u0236\u0246\5\2\2\2\u0237\u0246"+
		"\5\b\5\2\u0238\u0246\7\26\2\2\u0239\u023b\7\f\2\2\u023a\u023c\58\35\2"+
		"\u023b\u023a\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u0246"+
		"\7\r\2\2\u023e\u023f\5\64\33\2\u023f\u0240\7\5\2\2\u0240\u0241\7@\2\2"+
		"\u0241\u0246\3\2\2\2\u0242\u0243\5\64\33\2\u0243\u0244\5\34\17\2\u0244"+
		"\u0246\3\2\2\2\u0245\u0235\3\2\2\2\u0245\u0237\3\2\2\2\u0245\u0238\3\2"+
		"\2\2\u0245\u0239\3\2\2\2\u0245\u023e\3\2\2\2\u0245\u0242\3\2\2\2\u0246"+
		"\u0256\3\2\2\2\u0247\u0249\f\6\2\2\u0248\u024a\7\26\2\2\u0249\u0248\3"+
		"\2\2\2\u0249\u024a\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024c\7\5\2\2\u024c"+
		"\u0255\7@\2\2\u024d\u024f\f\4\2\2\u024e\u0250\7\26\2\2\u024f\u024e\3\2"+
		"\2\2\u024f\u0250\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0255\5\34\17\2\u0252"+
		"\u0253\f\3\2\2\u0253\u0255\5:\36\2\u0254\u0247\3\2\2\2\u0254\u024d\3\2"+
		"\2\2\u0254\u0252\3\2\2\2\u0255\u0258\3\2\2\2\u0256\u0254\3\2\2\2\u0256"+
		"\u0257\3\2\2\2\u0257\67\3\2\2\2\u0258\u0256\3\2\2\2\u0259\u025e\5(\25"+
		"\2\u025a\u025b\7\6\2\2\u025b\u025d\5(\25\2\u025c\u025a\3\2\2\2\u025d\u0260"+
		"\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f9\3\2\2\2\u0260"+
		"\u025e\3\2\2\2\u0261\u0263\7\f\2\2\u0262\u0264\58\35\2\u0263\u0262\3\2"+
		"\2\2\u0263\u0264\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0277\7\r\2\2\u0266"+
		"\u026a\7\f\2\2\u0267\u0268\58\35\2\u0268\u0269\7\6\2\2\u0269\u026b\3\2"+
		"\2\2\u026a\u0267\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
		"\u026d\5.\30\2\u026d\u026e\7\25\2\2\u026e\u026f\7\26\2\2\u026f\u0270\7"+
		"\27\2\2\u0270\u0271\7\r\2\2\u0271\u0277\3\2\2\2\u0272\u0274\7L\2\2\u0273"+
		"\u0272\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0275\3\2\2\2\u0275\u0277\5<"+
		"\37\2\u0276\u0261\3\2\2\2\u0276\u0266\3\2\2\2\u0276\u0273\3\2\2\2\u0277"+
		";\3\2\2\2\u0278\u0279\7\17\2\2\u0279\u027a\5H%\2\u027a\u027b\7\20\2\2"+
		"\u027b\u0281\3\2\2\2\u027c\u027d\7\17\2\2\u027d\u027e\5> \2\u027e\u027f"+
		"\7\20\2\2\u027f\u0281\3\2\2\2\u0280\u0278\3\2\2\2\u0280\u027c\3\2\2\2"+
		"\u0281=\3\2\2\2\u0282\u0284\5@!\2\u0283\u0282\3\2\2\2\u0284\u0285\3\2"+
		"\2\2\u0285\u0283\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u0288\3\2\2\2\u0287"+
		"\u0289\5B\"\2\u0288\u0287\3\2\2\2\u0288\u0289\3\2\2\2\u0289?\3\2\2\2\u028a"+
		"\u02a4\5\u008aF\2\u028b\u028d\5\u0080A\2\u028c\u028b\3\2\2\2\u028d\u0290"+
		"\3\2\2\2\u028e\u028c\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0292\3\2\2\2\u0290"+
		"\u028e\3\2\2\2\u0291\u0293\t\3\2\2\u0292\u0291\3\2\2\2\u0292\u0293\3\2"+
		"\2\2\u0293\u0294\3\2\2\2\u0294\u02a4\5\u00a0Q\2\u0295\u0297\5\u0080A\2"+
		"\u0296\u0295\3\2\2\2\u0297\u029a\3\2\2\2\u0298\u0296\3\2\2\2\u0298\u0299"+
		"\3\2\2\2\u0299\u029e\3\2\2\2\u029a\u0298\3\2\2\2\u029b\u029d\5z>\2\u029c"+
		"\u029b\3\2\2\2\u029d\u02a0\3\2\2\2\u029e\u029c\3\2\2\2\u029e\u029f\3\2"+
		"\2\2\u029f\u02a1\3\2\2\2\u02a0\u029e\3\2\2\2\u02a1\u02a4\5\u00aaV\2\u02a2"+
		"\u02a4\5*\26\2\u02a3\u028a\3\2\2\2\u02a3\u028e\3\2\2\2\u02a3\u0298\3\2"+
		"\2\2\u02a3\u02a2\3\2\2\2\u02a4A\3\2\2\2\u02a5\u02b6\5*\26\2\u02a6\u02b1"+
		"\5t;\2\u02a7\u02a9\7\30\2\2\u02a8\u02a7\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9"+
		"\u02aa\3\2\2\2\u02aa\u02ad\7@\2\2\u02ab\u02ad\7\26\2\2\u02ac\u02a8\3\2"+
		"\2\2\u02ac\u02ab\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\u02af\7\25\2\2\u02af"+
		"\u02b1\5\26\f\2\u02b0\u02a6\3\2\2\2\u02b0\u02ac\3\2\2\2\u02b1\u02b2\3"+
		"\2\2\2\u02b2\u02b3\7\13\2\2\u02b3\u02b4\5> \2\u02b4\u02b6\3\2\2\2\u02b5"+
		"\u02a5\3\2\2\2\u02b5\u02b0\3\2\2\2\u02b6C\3\2\2\2\u02b7\u02b9\5F$\2\u02b8"+
		"\u02b7\3\2\2\2\u02b9\u02ba\3\2\2\2\u02ba\u02b8\3\2\2\2\u02ba\u02bb\3\2"+
		"\2\2\u02bbE\3\2\2\2\u02bc\u02bd\5P)\2\u02bd\u02be\7+\2\2\u02be\u02c6\5"+
		"(\25\2\u02bf\u02c5\5L\'\2\u02c0\u02c1\5P)\2\u02c1\u02c2\7$\2\2\u02c2\u02c3"+
		"\5(\25\2\u02c3\u02c5\3\2\2\2\u02c4\u02bf\3\2\2\2\u02c4\u02c0\3\2\2\2\u02c5"+
		"\u02c8\3\2\2\2\u02c6\u02c4\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7G\3\2\2\2"+
		"\u02c8\u02c6\3\2\2\2\u02c9\u02cb\5J&\2\u02ca\u02c9\3\2\2\2\u02cb\u02cc"+
		"\3\2\2\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cdI\3\2\2\2\u02ce"+
		"\u02cf\7,\2\2\u02cf\u02d1\5N(\2\u02d0\u02d2\5L\'\2\u02d1\u02d0\3\2\2\2"+
		"\u02d1\u02d2\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4\7\13\2\2\u02d4\u02d5"+
		"\5> \2\u02d5K\3\2\2\2\u02d6\u02d7\7\31\2\2\u02d7\u02d8\5.\30\2\u02d8M"+
		"\3\2\2\2\u02d9\u02de\5P)\2\u02da\u02db\7-\2\2\u02db\u02dd\5P)\2\u02dc"+
		"\u02da\3\2\2\2\u02dd\u02e0\3\2\2\2\u02de\u02dc\3\2\2\2\u02de\u02df\3\2"+
		"\2\2\u02dfO\3\2\2\2\u02e0\u02de\3\2\2\2\u02e1\u02e2\t\4\2\2\u02e2\u02e3"+
		"\7\25\2\2\u02e3\u02e6\5$\23\2\u02e4\u02e6\5R*\2\u02e5\u02e1\3\2\2\2\u02e5"+
		"\u02e4\3\2\2\2\u02e6Q\3\2\2\2\u02e7\u02ea\7@\2\2\u02e8\u02e9\7.\2\2\u02e9"+
		"\u02eb\5T+\2\u02ea\u02e8\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02ee\3\2\2"+
		"\2\u02ec\u02ee\5T+\2\u02ed\u02e7\3\2\2\2\u02ed\u02ec\3\2\2\2\u02eeS\3"+
		"\2\2\2\u02ef\u02fc\5V,\2\u02f0\u02f8\5V,\2\u02f1\u02f3\7@\2\2\u02f2\u02f4"+
		"\7L\2\2\u02f3\u02f2\3\2\2\2\u02f3\u02f4\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5"+
		"\u02f7\5V,\2\u02f6\u02f1\3\2\2\2\u02f7\u02fa\3\2\2\2\u02f8\u02f6\3\2\2"+
		"\2\u02f8\u02f9\3\2\2\2\u02f9\u02fc\3\2\2\2\u02fa\u02f8\3\2\2\2\u02fb\u02ef"+
		"\3\2\2\2\u02fb\u02f0\3\2\2\2\u02fcU\3\2\2\2\u02fd\u031d\7\26\2\2\u02fe"+
		"\u031d\7G\2\2\u02ff\u031d\5\2\2\2\u0300\u0306\5\b\5\2\u0301\u0303\7\f"+
		"\2\2\u0302\u0304\5X-\2\u0303\u0302\3\2\2\2\u0303\u0304\3\2\2\2\u0304\u0305"+
		"\3\2\2\2\u0305\u0307\7\r\2\2\u0306\u0301\3\2\2\2\u0306\u0307\3\2\2\2\u0307"+
		"\u031d\3\2\2\2\u0308\u0309\5\b\5\2\u0309\u030d\7\f\2\2\u030a\u030b\5X"+
		"-\2\u030b\u030c\7\6\2\2\u030c\u030e\3\2\2\2\u030d\u030a\3\2\2\2\u030d"+
		"\u030e\3\2\2\2\u030e\u0311\3\2\2\2\u030f\u0310\7@\2\2\u0310\u0312\7.\2"+
		"\2\u0311\u030f\3\2\2\2\u0311\u0312\3\2\2\2\u0312\u0313\3\2\2\2\u0313\u0314"+
		"\7\26\2\2\u0314\u0315\7\27\2\2\u0315\u0316\7\r\2\2\u0316\u031d\3\2\2\2"+
		"\u0317\u0319\7\f\2\2\u0318\u031a\5X-\2\u0319\u0318\3\2\2\2\u0319\u031a"+
		"\3\2\2\2\u031a\u031b\3\2\2\2\u031b\u031d\7\r\2\2\u031c\u02fd\3\2\2\2\u031c"+
		"\u02fe\3\2\2\2\u031c\u02ff\3\2\2\2\u031c\u0300\3\2\2\2\u031c\u0308\3\2"+
		"\2\2\u031c\u0317\3\2\2\2\u031dW\3\2\2\2\u031e\u0321\5N(\2\u031f\u0320"+
		"\7\6\2\2\u0320\u0322\5X-\2\u0321\u031f\3\2\2\2\u0321\u0322\3\2\2\2\u0322"+
		"\u0326\3\2\2\2\u0323\u0324\7\26\2\2\u0324\u0326\7\27\2\2\u0325\u031e\3"+
		"\2\2\2\u0325\u0323\3\2\2\2\u0326Y\3\2\2\2\u0327\u0328\7\t\2\2\u0328\u032d"+
		"\5^\60\2\u0329\u032a\7\6\2\2\u032a\u032c\5^\60\2\u032b\u0329\3\2\2\2\u032c"+
		"\u032f\3\2\2\2\u032d\u032b\3\2\2\2\u032d\u032e\3\2\2\2\u032e\u0330\3\2"+
		"\2\2\u032f\u032d\3\2\2\2\u0330\u0331\7\n\2\2\u0331[\3\2\2\2\u0332\u0333"+
		"\7\t\2\2\u0333\u0338\5`\61\2\u0334\u0335\7\6\2\2\u0335\u0337\5`\61\2\u0336"+
		"\u0334\3\2\2\2\u0337\u033a\3\2\2\2\u0338\u0336\3\2\2\2\u0338\u0339\3\2"+
		"\2\2\u0339\u033b\3\2\2\2\u033a\u0338\3\2\2\2\u033b\u033c\7\n\2\2\u033c"+
		"]\3\2\2\2\u033d\u033f\5\u0080A\2\u033e\u033d\3\2\2\2\u033f\u0342\3\2\2"+
		"\2\u0340\u033e\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0344\3\2\2\2\u0342\u0340"+
		"\3\2\2\2\u0343\u0345\t\5\2\2\u0344\u0343\3\2\2\2\u0344\u0345\3\2\2\2\u0345"+
		"\u0346\3\2\2\2\u0346\u0347\5`\61\2\u0347_\3\2\2\2\u0348\u034a\t\6\2\2"+
		"\u0349\u034b\5Z.\2\u034a\u0349\3\2\2\2\u034a\u034b\3\2\2\2\u034b\u034e"+
		"\3\2\2\2\u034c\u034d\7/\2\2\u034d\u034f\5\f\7\2\u034e\u034c\3\2\2\2\u034e"+
		"\u034f\3\2\2\2\u034f\u0352\3\2\2\2\u0350\u0351\7\60\2\2\u0351\u0353\5"+
		"\f\7\2\u0352\u0350\3\2\2\2\u0352\u0353\3\2\2\2\u0353\u0358\3\2\2\2\u0354"+
		"\u0355\7\61\2\2\u0355\u0357\5\f\7\2\u0356\u0354\3\2\2\2\u0357\u035a\3"+
		"\2\2\2\u0358\u0356\3\2\2\2\u0358\u0359\3\2\2\2\u0359\u035f\3\2\2\2\u035a"+
		"\u0358\3\2\2\2\u035b\u035c\7\25\2\2\u035c\u035e\5\f\7\2\u035d\u035b\3"+
		"\2\2\2\u035e\u0361\3\2\2\2\u035f\u035d\3\2\2\2\u035f\u0360\3\2\2\2\u0360"+
		"a\3\2\2\2\u0361\u035f\3\2\2\2\u0362\u0364\5d\63\2\u0363\u0362\3\2\2\2"+
		"\u0364\u0367\3\2\2\2\u0365\u0363\3\2\2\2\u0365\u0366\3\2\2\2\u0366\u0370"+
		"\3\2\2\2\u0367\u0365\3\2\2\2\u0368\u036a\7L\2\2\u0369\u0368\3\2\2\2\u0369"+
		"\u036a\3\2\2\2\u036a\u036b\3\2\2\2\u036b\u036c\7\f\2\2\u036c\u036d\7\30"+
		"\2\2\u036d\u036e\5f\64\2\u036e\u036f\7\r\2\2\u036f\u0371\3\2\2\2\u0370"+
		"\u0369\3\2\2\2\u0370\u0371\3\2\2\2\u0371c\3\2\2\2\u0372\u0374\7L\2\2\u0373"+
		"\u0372\3\2\2\2\u0373\u0374\3\2\2\2\u0374\u0375\3\2\2\2\u0375\u0377\7\f"+
		"\2\2\u0376\u0378\5f\64\2\u0377\u0376\3\2\2\2\u0377\u0378\3\2\2\2\u0378"+
		"\u0379\3\2\2\2\u0379\u037a\7\r\2\2\u037ae\3\2\2\2\u037b\u0380\5h\65\2"+
		"\u037c\u037d\7\6\2\2\u037d\u037f\5h\65\2\u037e\u037c\3\2\2\2\u037f\u0382"+
		"\3\2\2\2\u0380\u037e\3\2\2\2\u0380\u0381\3\2\2\2\u0381g\3\2\2\2\u0382"+
		"\u0380\3\2\2\2\u0383\u0385\5\u0080A\2\u0384\u0383\3\2\2\2\u0385\u0388"+
		"\3\2\2\2\u0386\u0384\3\2\2\2\u0386\u0387\3\2\2\2\u0387\u0389\3\2\2\2\u0388"+
		"\u0386\3\2\2\2\u0389\u038c\7@\2\2\u038a\u038b\7\25\2\2\u038b\u038d\5j"+
		"\66\2\u038c\u038a\3\2\2\2\u038c\u038d\3\2\2\2\u038d\u0390\3\2\2\2\u038e"+
		"\u038f\7$\2\2\u038f\u0391\5(\25\2\u0390\u038e\3\2\2\2\u0390\u0391\3\2"+
		"\2\2\u0391i\3\2\2\2\u0392\u0399\5\f\7\2\u0393\u0394\7\13\2\2\u0394\u0399"+
		"\5\f\7\2\u0395\u0396\5\f\7\2\u0396\u0397\7\27\2\2\u0397\u0399\3\2\2\2"+
		"\u0398\u0392\3\2\2\2\u0398\u0393\3\2\2\2\u0398\u0395\3\2\2\2\u0399k\3"+
		"\2\2\2\u039a\u039c\5n8\2\u039b\u039a\3\2\2\2\u039c\u039f\3\2\2\2\u039d"+
		"\u039b\3\2\2\2\u039d\u039e\3\2\2\2\u039e\u03a8\3\2\2\2\u039f\u039d\3\2"+
		"\2\2\u03a0\u03a2\7L\2\2\u03a1\u03a0\3\2\2\2\u03a1\u03a2\3\2\2\2\u03a2"+
		"\u03a3\3\2\2\2\u03a3\u03a4\7\f\2\2\u03a4\u03a5\7\30\2\2\u03a5\u03a6\5"+
		"p9\2\u03a6\u03a7\7\r\2\2\u03a7\u03a9\3\2\2\2\u03a8\u03a1\3\2\2\2\u03a8"+
		"\u03a9\3\2\2\2\u03a9m\3\2\2\2\u03aa\u03ac\7L\2\2\u03ab\u03aa\3\2\2\2\u03ab"+
		"\u03ac\3\2\2\2\u03ac\u03ad\3\2\2\2\u03ad\u03af\7\f\2\2\u03ae\u03b0\5p"+
		"9\2\u03af\u03ae\3\2\2\2\u03af\u03b0\3\2\2\2\u03b0\u03b1\3\2\2\2\u03b1"+
		"\u03b2\7\r\2\2\u03b2o\3\2\2\2\u03b3\u03b8\5r:\2\u03b4\u03b5\7\6\2\2\u03b5"+
		"\u03b7\5r:\2\u03b6\u03b4\3\2\2\2\u03b7\u03ba\3\2\2\2\u03b8\u03b6\3\2\2"+
		"\2\u03b8\u03b9\3\2\2\2\u03b9q\3\2\2\2\u03ba\u03b8\3\2\2\2\u03bb\u03bd"+
		"\5\u0080A\2\u03bc\u03bb\3\2\2\2\u03bd\u03c0\3\2\2\2\u03be\u03bc\3\2\2"+
		"\2\u03be\u03bf\3\2\2\2\u03bf\u03c4\3\2\2\2\u03c0\u03be\3\2\2\2\u03c1\u03c3"+
		"\5x=\2\u03c2\u03c1\3\2\2\2\u03c3\u03c6\3\2\2\2\u03c4\u03c2\3\2\2\2\u03c4"+
		"\u03c5\3\2\2\2\u03c5\u03c8\3\2\2\2\u03c6\u03c4\3\2\2\2\u03c7\u03c9\t\7"+
		"\2\2\u03c8\u03c7\3\2\2\2\u03c8\u03c9\3\2\2\2\u03c9\u03ca\3\2\2\2\u03ca"+
		"\u03cb\7@\2\2\u03cb\u03cc\7\25\2\2\u03cc\u03cf\5j\66\2\u03cd\u03ce\7$"+
		"\2\2\u03ce\u03d0\5(\25\2\u03cf\u03cd\3\2\2\2\u03cf\u03d0\3\2\2\2\u03d0"+
		"s\3\2\2\2\u03d1\u03d2\7\f\2\2\u03d2\u03d7\5v<\2\u03d3\u03d4\7\6\2\2\u03d4"+
		"\u03d6\5v<\2\u03d5\u03d3\3\2\2\2\u03d6\u03d9\3\2\2\2\u03d7\u03d5\3\2\2"+
		"\2\u03d7\u03d8\3\2\2\2\u03d8\u03da\3\2\2\2\u03d9\u03d7\3\2\2\2\u03da\u03db"+
		"\7\r\2\2\u03dbu\3\2\2\2\u03dc\u03df\t\6\2\2\u03dd\u03de\7\25\2\2\u03de"+
		"\u03e0\5\f\7\2\u03df\u03dd\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0w\3\2\2\2"+
		"\u03e1\u03e5\5z>\2\u03e2\u03e5\5|?\2\u03e3\u03e5\7\63\2\2\u03e4\u03e1"+
		"\3\2\2\2\u03e4\u03e2\3\2\2\2\u03e4\u03e3\3\2\2\2\u03e5y\3\2\2\2\u03e6"+
		"\u03e7\t\b\2\2\u03e7{\3\2\2\2\u03e8\u03ea\t\t\2\2\u03e9\u03eb\5~@\2\u03ea"+
		"\u03e9\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb}\3\2\2\2\u03ec\u03ed\7\t\2\2"+
		"\u03ed\u03ee\t\n\2\2\u03ee\u03ef\7\n\2\2\u03ef\177\3\2\2\2\u03f0\u03f1"+
		"\7.\2\2\u03f1\u03f5\5\32\16\2\u03f2\u03f4\5:\36\2\u03f3\u03f2\3\2\2\2"+
		"\u03f4\u03f7\3\2\2\2\u03f5\u03f3\3\2\2\2\u03f5\u03f6\3\2\2\2\u03f6\u0081"+
		"\3\2\2\2\u03f7\u03f5\3\2\2\2\u03f8\u03f9\7.\2\2\u03f9\u03fa\5\32\16\2"+
		"\u03fa\u03fb\5:\36\2\u03fb\u0083\3\2\2\2\u03fc\u03fe\7L\2\2\u03fd\u03fc"+
		"\3\2\2\2\u03fd\u03fe\3\2\2\2\u03fe\u03ff\3\2\2\2\u03ff\u0401\7\17\2\2"+
		"\u0400\u0402\5\u0088E\2\u0401\u0400\3\2\2\2\u0401\u0402\3\2\2\2\u0402"+
		"\u0404\3\2\2\2\u0403\u0405\5\u0086D\2\u0404\u0403\3\2\2\2\u0405\u0406"+
		"\3\2\2\2\u0406\u0404\3\2\2\2\u0406\u0407\3\2\2\2\u0407\u0408\3\2\2\2\u0408"+
		"\u0409\7\20\2\2\u0409\u0085\3\2\2\2\u040a\u042d\5\u008aF\2\u040b\u040d"+
		"\5\u0080A\2\u040c\u040e\7L\2\2\u040d\u040c\3\2\2\2\u040d\u040e\3\2\2\2"+
		"\u040e\u0410\3\2\2\2\u040f\u040b\3\2\2\2\u0410\u0413\3\2\2\2\u0411\u040f"+
		"\3\2\2\2\u0411\u0412\3\2\2\2\u0412\u0417\3\2\2\2\u0413\u0411\3\2\2\2\u0414"+
		"\u0416\5x=\2\u0415\u0414\3\2\2\2\u0416\u0419\3\2\2\2\u0417\u0415\3\2\2"+
		"\2\u0417\u0418\3\2\2\2\u0418\u041a\3\2\2\2\u0419\u0417\3\2\2\2\u041a\u042d"+
		"\5\u00a0Q\2\u041b\u041d\5\u0080A\2\u041c\u041e\7L\2\2\u041d\u041c\3\2"+
		"\2\2\u041d\u041e\3\2\2\2\u041e\u0420\3\2\2\2\u041f\u041b\3\2\2\2\u0420"+
		"\u0423\3\2\2\2\u0421\u041f\3\2\2\2\u0421\u0422\3\2\2\2\u0422\u0427\3\2"+
		"\2\2\u0423\u0421\3\2\2\2\u0424\u0426\5x=\2\u0425\u0424\3\2\2\2\u0426\u0429"+
		"\3\2\2\2\u0427\u0425\3\2\2\2\u0427\u0428\3\2\2\2\u0428\u042a\3\2\2\2\u0429"+
		"\u0427\3\2\2\2\u042a\u042d\5\u0092J\2\u042b\u042d\5(\25\2\u042c\u040a"+
		"\3\2\2\2\u042c\u0411\3\2\2\2\u042c\u0421\3\2\2\2\u042c\u042b\3\2\2\2\u042d"+
		"\u0087\3\2\2\2\u042e\u0431\7@\2\2\u042f\u0430\7\25\2\2\u0430\u0432\5\f"+
		"\7\2\u0431\u042f\3\2\2\2\u0431\u0432\3\2\2\2\u0432\u0433\3\2\2\2\u0433"+
		"\u043a\7\13\2\2\u0434\u0435\7\7\2\2\u0435\u0436\7\25\2\2\u0436\u0437\5"+
		"\f\7\2\u0437\u0438\7\13\2\2\u0438\u043a\3\2\2\2\u0439\u042e\3\2\2\2\u0439"+
		"\u0434\3\2\2\2\u043a\u0089\3\2\2\2\u043b\u043c\79\2\2\u043c\u0441\5\u008c"+
		"G\2\u043d\u043e\7\6\2\2\u043e\u0440\5\u008cG\2\u043f\u043d\3\2\2\2\u0440"+
		"\u0443\3\2\2\2\u0441\u043f\3\2\2\2\u0441\u0442\3\2\2\2\u0442\u008b\3\2"+
		"\2\2\u0443\u0441\3\2\2\2\u0444\u044b\5\b\5\2\u0445\u0449\7\5\2\2\u0446"+
		"\u044a\7@\2\2\u0447\u044a\7\26\2\2\u0448\u044a\5\u008eH\2\u0449\u0446"+
		"\3\2\2\2\u0449\u0447\3\2\2\2\u0449\u0448\3\2\2\2\u044a\u044c\3\2\2\2\u044b"+
		"\u0445\3\2\2\2\u044b\u044c\3\2\2\2\u044c\u008d\3\2\2\2\u044d\u0453\7\17"+
		"\2\2\u044e\u044f\5\u0090I\2\u044f\u0450\7\6\2\2\u0450\u0452\3\2\2\2\u0451"+
		"\u044e\3\2\2\2\u0452\u0455\3\2\2\2\u0453\u0451\3\2\2\2\u0453\u0454\3\2"+
		"\2\2\u0454\u0458\3\2\2\2\u0455\u0453\3\2\2\2\u0456\u0459\5\u0090I\2\u0457"+
		"\u0459\7\26\2\2\u0458\u0456\3\2\2\2\u0458\u0457\3\2\2\2\u0459\u045a\3"+
		"\2\2\2\u045a\u045b\7\20\2\2\u045b\u008f\3\2\2\2\u045c\u045f\7@\2\2\u045d"+
		"\u045e\7\13\2\2\u045e\u0460\t\6\2\2\u045f\u045d\3\2\2\2\u045f\u0460\3"+
		"\2\2\2\u0460\u0091\3\2\2\2\u0461\u0462\7\22\2\2\u0462\u0470\5\u0094K\2"+
		"\u0463\u0464\7\62\2\2\u0464\u0470\5\u0096L\2\u0465\u0466\7:\2\2\u0466"+
		"\u0470\5\u0098M\2\u0467\u046b\7\21\2\2\u0468\u046a\7L\2\2\u0469\u0468"+
		"\3\2\2\2\u046a\u046d\3\2\2\2\u046b\u0469\3\2\2\2\u046b\u046c\3\2\2\2\u046c"+
		"\u046e\3\2\2\2\u046d\u046b\3\2\2\2\u046e\u0470\5\u009cO\2\u046f\u0461"+
		"\3\2\2\2\u046f\u0463\3\2\2\2\u046f\u0465\3\2\2\2\u046f\u0467\3\2\2\2\u0470"+
		"\u0093\3\2\2\2\u0471\u0472\5\6\4\2\u0472\u0473\7\25\2\2\u0473\u0474\5"+
		"\f\7\2\u0474\u0095\3\2\2\2\u0475\u0476\5\6\4\2\u0476\u0477\7\25\2\2\u0477"+
		"\u0478\5\f\7\2\u0478\u0097\3\2\2\2\u0479\u047c\5\u009aN\2\u047a\u047b"+
		"\7\25\2\2\u047b\u047d\5\f\7\2\u047c\u047a\3\2\2\2\u047c\u047d\3\2\2\2"+
		"\u047d\u0099\3\2\2\2\u047e\u0480\7@\2\2\u047f\u0481\5\\/\2\u0480\u047f"+
		"\3\2\2\2\u0480\u0481\3\2\2\2\u0481\u0482\3\2\2\2\u0482\u0483\5b\62\2\u0483"+
		"\u009b\3\2\2\2\u0484\u0486\7@\2\2\u0485\u0487\5Z.\2\u0486\u0485\3\2\2"+
		"\2\u0486\u0487\3\2\2\2\u0487\u048a\3\2\2\2\u0488\u0489\7/\2\2\u0489\u048b"+
		"\5\f\7\2\u048a\u0488\3\2\2\2\u048a\u048b\3\2\2\2\u048b\u048e\3\2\2\2\u048c"+
		"\u048d\7\60\2\2\u048d\u048f\5\f\7\2\u048e\u048c\3\2\2\2\u048e\u048f\3"+
		"\2\2\2\u048f\u009d\3\2\2\2\u0490\u0491\7\22\2\2\u0491\u0495\5\u00a2R\2"+
		"\u0492\u0493\7\62\2\2\u0493\u0495\5\u00a4S\2\u0494\u0490\3\2\2\2\u0494"+
		"\u0492\3\2\2\2\u0495\u009f\3\2\2\2\u0496\u04a3\5\u009eP\2\u0497\u0498"+
		"\7:\2\2\u0498\u04a3\5\u00a6T\2\u0499\u049d\7\21\2\2\u049a\u049c\7L\2\2"+
		"\u049b\u049a\3\2\2\2\u049c\u049f\3\2\2\2\u049d\u049b\3\2\2\2\u049d\u049e"+
		"\3\2\2\2\u049e\u04a0\3\2\2\2\u049f\u049d\3\2\2\2\u04a0\u04a3\5\u00a8U"+
		"\2\u04a1\u04a3\5\u00aaV\2\u04a2\u0496\3\2\2\2\u04a2\u0497\3\2\2\2\u04a2"+
		"\u0499\3\2\2\2\u04a2\u04a1\3\2\2\2\u04a3\u00a1\3\2\2\2\u04a4\u04a9\5R"+
		"*\2\u04a5\u04a6\7\6\2\2\u04a6\u04a8\5R*\2\u04a7\u04a5\3\2\2\2\u04a8\u04ab"+
		"\3\2\2\2\u04a9\u04a7\3\2\2\2\u04a9\u04aa\3\2\2\2\u04aa\u04ae\3\2\2\2\u04ab"+
		"\u04a9\3\2\2\2\u04ac\u04ad\7\25\2\2\u04ad\u04af\5\f\7\2\u04ae\u04ac\3"+
		"\2\2\2\u04ae\u04af\3\2\2\2\u04af\u04b0\3\2\2\2\u04b0\u04b1\7$\2\2\u04b1"+
		"\u04b2\5(\25\2\u04b2\u00a3\3\2\2\2\u04b3\u04bb\5\u00a2R\2\u04b4\u04b5"+
		"\5\6\4\2\u04b5\u04b6\7\25\2\2\u04b6\u04b7\5\f\7\2\u04b7\u04b8\7$\2\2\u04b8"+
		"\u04b9\7\26\2\2\u04b9\u04bb\3\2\2\2\u04ba\u04b3\3\2\2\2\u04ba\u04b4\3"+
		"\2\2\2\u04bb\u00a5\3\2\2\2\u04bc\u04bf\5\u009aN\2\u04bd\u04be\7\25\2\2"+
		"\u04be\u04c0\5\f\7\2\u04bf\u04bd\3\2\2\2\u04bf\u04c0\3\2\2\2\u04c0\u04c1"+
		"\3\2\2\2\u04c1\u04c2\7$\2\2\u04c2\u04c3\5(\25\2\u04c3\u04d8\3\2\2\2\u04c4"+
		"\u04c6\5\u009aN\2\u04c5\u04c7\7L\2\2\u04c6\u04c5\3\2\2\2\u04c6\u04c7\3"+
		"\2\2\2\u04c7\u04c8\3\2\2\2\u04c8\u04c9\7\17\2\2\u04c9\u04ca\5> \2\u04ca"+
		"\u04cb\7\20\2\2\u04cb\u04d8\3\2\2\2\u04cc\u04cd\7\7\2\2\u04cd\u04ce\5"+
		"d\63\2\u04ce\u04d5\5b\62\2\u04cf\u04d0\7$\2\2\u04d0\u04d6\5\u00c4c\2\u04d1"+
		"\u04d3\7L\2\2\u04d2\u04d1\3\2\2\2\u04d2\u04d3\3\2\2\2\u04d3\u04d4\3\2"+
		"\2\2\u04d4\u04d6\5\u00c6d\2\u04d5\u04cf\3\2\2\2\u04d5\u04d2\3\2\2\2\u04d6"+
		"\u04d8\3\2\2\2\u04d7\u04bc\3\2\2\2\u04d7\u04c4\3\2\2\2\u04d7\u04cc\3\2"+
		"\2\2\u04d8\u00a7\3\2\2\2\u04d9\u04db\7@\2\2\u04da\u04dc\5Z.\2\u04db\u04da"+
		"\3\2\2\2\u04db\u04dc\3\2\2\2\u04dc\u04dd\3\2\2\2\u04dd\u04de\7$\2\2\u04de"+
		"\u04df\5\f\7\2\u04df\u00a9\3\2\2\2\u04e0\u04e2\7,\2\2\u04e1\u04e0\3\2"+
		"\2\2\u04e1\u04e2\3\2\2\2\u04e2\u04e3\3\2\2\2\u04e3\u04e4\7;\2\2\u04e4"+
		"\u04ed\5\u00acW\2\u04e5\u04e7\7,\2\2\u04e6\u04e5\3\2\2\2\u04e6\u04e7\3"+
		"\2\2\2\u04e7\u04e8\3\2\2\2\u04e8\u04e9\7<\2\2\u04e9\u04ed\5\u00b0Y\2\u04ea"+
		"\u04eb\7=\2\2\u04eb\u04ed\5\u00aeX\2\u04ec\u04e1\3\2\2\2\u04ec\u04e6\3"+
		"\2\2\2\u04ec\u04ea\3\2\2\2\u04ed\u00ab\3\2\2\2\u04ee\u04f0\7@\2\2\u04ef"+
		"\u04f1\5Z.\2\u04f0\u04ef\3\2\2\2\u04f0\u04f1\3\2\2\2\u04f1\u04f5\3\2\2"+
		"\2\u04f2\u04f4\5\u0082B\2\u04f3\u04f2\3\2\2\2\u04f4\u04f7\3\2\2\2\u04f5"+
		"\u04f3\3\2\2\2\u04f5\u04f6\3\2\2\2\u04f6\u04f9\3\2\2\2\u04f7\u04f5\3\2"+
		"\2\2\u04f8\u04fa\5|?\2\u04f9\u04f8\3\2\2\2\u04f9\u04fa\3\2\2\2\u04fa\u04fb"+
		"\3\2\2\2\u04fb\u04fc\5l\67\2\u04fc\u04fd\5\u00b2Z\2\u04fd\u00ad\3\2\2"+
		"\2\u04fe\u0500\7@\2\2\u04ff\u0501\5Z.\2\u0500\u04ff\3\2\2\2\u0500\u0501"+
		"\3\2\2\2\u0501\u0502\3\2\2\2\u0502\u0503\5\u00b4[\2\u0503\u00af\3\2\2"+
		"\2\u0504\u0505\7@\2\2\u0505\u0506\5\u00b2Z\2\u0506\u00b1\3\2\2\2\u0507"+
		"\u0508\7>\2\2\u0508\u0510\5\u00b6\\\2\u0509\u050b\7>\2\2\u050a\u0509\3"+
		"\2\2\2\u050a\u050b\3\2\2\2\u050b\u050c\3\2\2\2\u050c\u050e\5\u0084C\2"+
		"\u050d\u050a\3\2\2\2\u050d\u050e\3\2\2\2\u050e\u0510\3\2\2\2\u050f\u0507"+
		"\3\2\2\2\u050f\u050d\3\2\2\2\u0510\u00b3\3\2\2\2\u0511\u0512\7>\2\2\u0512"+
		"\u051a\5\u00b8]\2\u0513\u0515\7>\2\2\u0514\u0513\3\2\2\2\u0514\u0515\3"+
		"\2\2\2\u0515\u0516\3\2\2\2\u0516\u0518\5\u0084C\2\u0517\u0514\3\2\2\2"+
		"\u0517\u0518\3\2\2\2\u0518\u051a\3\2\2\2\u0519\u0511\3\2\2\2\u0519\u0517"+
		"\3\2\2\2\u051a\u00b5\3\2\2\2\u051b\u051d\5\u00c0a\2\u051c\u051b\3\2\2"+
		"\2\u051c\u051d\3\2\2\2\u051d\u051e\3\2\2\2\u051e\u0520\5\u00ba^\2\u051f"+
		"\u0521\5\u0084C\2\u0520\u051f\3\2\2\2\u0520\u0521\3\2\2\2\u0521\u00b7"+
		"\3\2\2\2\u0522\u0524\5\u00c0a\2\u0523\u0522\3\2\2\2\u0523\u0524\3\2\2"+
		"\2\u0524\u0525\3\2\2\2\u0525\u0527\5\u00bc_\2\u0526\u0528\5\u0084C\2\u0527"+
		"\u0526\3\2\2\2\u0527\u0528\3\2\2\2\u0528\u00b9\3\2\2\2\u0529\u052e\5\u00be"+
		"`\2\u052a\u052b\7\23\2\2\u052b\u052d\5\30\r\2\u052c\u052a\3\2\2\2\u052d"+
		"\u0530\3\2\2\2\u052e\u052c\3\2\2\2\u052e\u052f\3\2\2\2\u052f\u00bb\3\2"+
		"\2\2\u0530\u052e\3\2\2\2\u0531\u0536\5\30\r\2\u0532\u0533\7\23\2\2\u0533"+
		"\u0535\5\30\r\2\u0534\u0532\3\2\2\2\u0535\u0538\3\2\2\2\u0536\u0534\3"+
		"\2\2\2\u0536\u0537\3\2\2\2\u0537\u00bd\3\2\2\2\u0538\u0536\3\2\2\2\u0539"+
		"\u053d\5\30\r\2\u053a\u053c\5:\36\2\u053b\u053a\3\2\2\2\u053c\u053f\3"+
		"\2\2\2\u053d\u053b\3\2\2\2\u053d\u053e\3\2\2\2\u053e\u00bf\3\2\2\2\u053f"+
		"\u053d\3\2\2\2\u0540\u0542\7\17\2\2\u0541\u0543\5\u00c2b\2\u0542\u0541"+
		"\3\2\2\2\u0543\u0544\3\2\2\2\u0544\u0542\3\2\2\2\u0544\u0545\3\2\2\2\u0545"+
		"\u0546\3\2\2\2\u0546\u0547\7\20\2\2\u0547\u0548\7\23\2\2\u0548\u00c1\3"+
		"\2\2\2\u0549\u054b\5\u0080A\2\u054a\u054c\7L\2\2\u054b\u054a\3\2\2\2\u054b"+
		"\u054c\3\2\2\2\u054c\u054e\3\2\2\2\u054d\u0549\3\2\2\2\u054e\u0551\3\2"+
		"\2\2\u054f\u054d\3\2\2\2\u054f\u0550\3\2\2\2\u0550\u0555\3\2\2\2\u0551"+
		"\u054f\3\2\2\2\u0552\u0554\5x=\2\u0553\u0552\3\2\2\2\u0554\u0557\3\2\2"+
		"\2\u0555\u0553\3\2\2\2\u0555\u0556\3\2\2\2\u0556\u0558\3\2\2\2\u0557\u0555"+
		"\3\2\2\2\u0558\u0559\5\u009eP\2\u0559\u00c3\3\2\2\2\u055a\u055d\5\u00c8"+
		"e\2\u055b\u055d\5\u00c6d\2\u055c\u055a\3\2\2\2\u055c\u055b\3\2\2\2\u055d"+
		"\u00c5\3\2\2\2\u055e\u055f\7\17\2\2\u055f\u0563\5\u00c8e\2\u0560\u0562"+
		"\5@!\2\u0561\u0560\3\2\2\2\u0562\u0565\3\2\2\2\u0563\u0561\3\2\2\2\u0563"+
		"\u0564\3\2\2\2\u0564\u0566\3\2\2\2\u0565\u0563\3\2\2\2\u0566\u0567\7\20"+
		"\2\2\u0567\u00c7\3\2\2\2\u0568\u056a\7\7\2\2\u0569\u056b\5:\36\2\u056a"+
		"\u0569\3\2\2\2\u056b\u056c\3\2\2\2\u056c\u056a\3\2\2\2\u056c\u056d\3\2"+
		"\2\2\u056d\u00c9\3\2\2\2\u056e\u0570\5\u00ccg\2\u056f\u056e\3\2\2\2\u0570"+
		"\u0571\3\2\2\2\u0571\u056f\3\2\2\2\u0571\u0572\3\2\2\2\u0572\u00cb\3\2"+
		"\2\2\u0573\u0575\5\u0080A\2\u0574\u0576\7L\2\2\u0575\u0574\3\2\2\2\u0575"+
		"\u0576\3\2\2\2\u0576\u0578\3\2\2\2\u0577\u0573\3\2\2\2\u0578\u057b\3\2"+
		"\2\2\u0579\u0577\3\2\2\2\u0579\u057a\3\2\2\2\u057a\u057f\3\2\2\2\u057b"+
		"\u0579\3\2\2\2\u057c\u057e\5x=\2\u057d\u057c\3\2\2\2\u057e\u0581\3\2\2"+
		"\2\u057f\u057d\3\2\2\2\u057f\u0580\3\2\2\2\u0580\u0582\3\2\2\2\u0581\u057f"+
		"\3\2\2\2\u0582\u0587\5\u00aaV\2\u0583\u0587\5\u008aF\2\u0584\u0587\5\u00ce"+
		"h\2\u0585\u0587\5\u00d0i\2\u0586\u0579\3\2\2\2\u0586\u0583\3\2\2\2\u0586"+
		"\u0584\3\2\2\2\u0586\u0585\3\2\2\2\u0587\u00cd\3\2\2\2\u0588\u0589\7?"+
		"\2\2\u0589\u058b\5\4\3\2\u058a\u058c\7L\2\2\u058b\u058a\3\2\2\2\u058b"+
		"\u058c\3\2\2\2\u058c\u058d\3\2\2\2\u058d\u058e\7\17\2\2\u058e\u058f\5"+
		"\u00caf\2\u058f\u0590\7\20\2\2\u0590\u00cf\3\2\2\2\u0591\u0592\7?\2\2"+
		"\u0592\u0593\7<\2\2\u0593\u0594\5\u00b0Y\2\u0594\u00d1\3\2\2\2\u0595\u0596"+
		"\7?\2\2\u0596\u0598\5\4\3\2\u0597\u0595\3\2\2\2\u0598\u059b\3\2\2\2\u0599"+
		"\u0597\3\2\2\2\u0599\u059a\3\2\2\2\u059a\u059c\3\2\2\2\u059b\u0599\3\2"+
		"\2\2\u059c\u059d\5\u00caf\2\u059d\u00d3\3\2\2\2\u00d0\u00d5\u00d9\u00e1"+
		"\u00e8\u00f0\u00f7\u00fc\u0100\u0102\u0109\u0116\u0118\u0121\u0124\u0127"+
		"\u012e\u0136\u013d\u0145\u0149\u014c\u0152\u0159\u015f\u0166\u0168\u0174"+
		"\u0178\u017e\u0185\u018f\u0194\u0198\u019c\u01a1\u01aa\u01b0\u01b9\u01c2"+
		"\u01c6\u01d8\u01db\u01e3\u01e8\u01ea\u01ee\u01fa\u0202\u0208\u020f\u0213"+
		"\u021b\u0220\u0224\u0229\u022b\u0230\u0233\u023b\u0245\u0249\u024f\u0254"+
		"\u0256\u025e\u0263\u026a\u0273\u0276\u0280\u0285\u0288\u028e\u0292\u0298"+
		"\u029e\u02a3\u02a8\u02ac\u02b0\u02b5\u02ba\u02c4\u02c6\u02cc\u02d1\u02de"+
		"\u02e5\u02ea\u02ed\u02f3\u02f8\u02fb\u0303\u0306\u030d\u0311\u0319\u031c"+
		"\u0321\u0325\u032d\u0338\u0340\u0344\u034a\u034e\u0352\u0358\u035f\u0365"+
		"\u0369\u0370\u0373\u0377\u0380\u0386\u038c\u0390\u0398\u039d\u03a1\u03a8"+
		"\u03ab\u03af\u03b8\u03be\u03c4\u03c8\u03cf\u03d7\u03df\u03e4\u03ea\u03f5"+
		"\u03fd\u0401\u0406\u040d\u0411\u0417\u041d\u0421\u0427\u042c\u0431\u0439"+
		"\u0441\u0449\u044b\u0453\u0458\u045f\u046b\u046f\u047c\u0480\u0486\u048a"+
		"\u048e\u0494\u049d\u04a2\u04a9\u04ae\u04ba\u04bf\u04c6\u04d2\u04d5\u04d7"+
		"\u04db\u04e1\u04e6\u04ec\u04f0\u04f5\u04f9\u0500\u050a\u050d\u050f\u0514"+
		"\u0517\u0519\u051c\u0520\u0523\u0527\u052e\u0536\u053d\u0544\u054b\u054f"+
		"\u0555\u055c\u0563\u056c\u0571\u0575\u0579\u057f\u0586\u058b\u0599";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}