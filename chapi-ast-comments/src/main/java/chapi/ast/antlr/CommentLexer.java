// Generated from CommentLexer.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommentLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, LINE_COMMENT=2, PYTHON_COMMENT=3, DECIMAL_LITERAL=4, HEX_LITERAL=5, 
		OCT_LITERAL=6, BINARY_LITERAL=7, FLOAT_LITERAL=8, HEX_FLOAT_LITERAL=9, 
		BOOL_LITERAL=10, CHAR_LITERAL=11, STRING_LITERAL=12, NULL_LITERAL=13, 
		LPAREN=14, RPAREN=15, LBRACE=16, RBRACE=17, LBRACK=18, RBRACK=19, SEMI=20, 
		COMMA=21, DOT=22, ASSIGN=23, GT=24, LT=25, BANG=26, TILDE=27, QUESTION=28, 
		COLON=29, EQUAL=30, LE=31, GE=32, NOTEQUAL=33, AND=34, OR=35, INC=36, 
		DEC=37, ADD=38, SUB=39, MUL=40, DIV=41, BITAND=42, BITOR=43, CARET=44, 
		MOD=45, ADD_ASSIGN=46, SUB_ASSIGN=47, MUL_ASSIGN=48, DIV_ASSIGN=49, AND_ASSIGN=50, 
		OR_ASSIGN=51, XOR_ASSIGN=52, MOD_ASSIGN=53, LSHIFT_ASSIGN=54, RSHIFT_ASSIGN=55, 
		URSHIFT_ASSIGN=56, TemplateStringLiteral=57, ARROW=58, COLONCOLON=59, 
		AT=60, ELLIPSIS=61, WS=62, IDENTIFIER=63;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "LINE_COMMENT", "PYTHON_COMMENT", "DECIMAL_LITERAL", "HEX_LITERAL", 
			"OCT_LITERAL", "BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
			"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
			"ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", 
			"LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", 
			"DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", "SUB_ASSIGN", 
			"MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
			"MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "TemplateStringLiteral", 
			"ARROW", "COLONCOLON", "AT", "ELLIPSIS", "WS", "IDENTIFIER", "ExponentPart", 
			"EscapeSequence", "HexDigits", "HexDigit", "Digits", "LetterOrDigit", 
			"Letter"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "'null'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", 
			"'.'", "'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", 
			"'>='", "'!='", "'&&'", "'||'", "'++'", "'--'", "'+'", "'-'", "'*'", 
			"'/'", "'&'", "'|'", "'^'", "'%'", "'+='", "'-='", "'*='", "'/='", "'&='", 
			"'|='", "'^='", "'%='", "'<<='", "'>>='", "'>>>='", null, "'->'", "'::'", 
			"'@'", "'...'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "LINE_COMMENT", "PYTHON_COMMENT", "DECIMAL_LITERAL", 
			"HEX_LITERAL", "OCT_LITERAL", "BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
			"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
			"ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", 
			"LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", 
			"DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", "SUB_ASSIGN", 
			"MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
			"MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "TemplateStringLiteral", 
			"ARROW", "COLONCOLON", "AT", "ELLIPSIS", "WS", "IDENTIFIER"
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


	public CommentLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CommentLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2A\u0214\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3"+
		"\2\3\2\7\2\u0094\n\2\f\2\16\2\u0097\13\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\7\3\u00a2\n\3\f\3\16\3\u00a5\13\3\3\3\3\3\3\4\3\4\7\4\u00ab\n\4"+
		"\f\4\16\4\u00ae\13\4\3\4\3\4\3\5\3\5\3\5\5\5\u00b5\n\5\3\5\6\5\u00b8\n"+
		"\5\r\5\16\5\u00b9\3\5\5\5\u00bd\n\5\5\5\u00bf\n\5\3\5\5\5\u00c2\n\5\3"+
		"\6\3\6\3\6\3\6\7\6\u00c8\n\6\f\6\16\6\u00cb\13\6\3\6\5\6\u00ce\n\6\3\6"+
		"\5\6\u00d1\n\6\3\7\3\7\7\7\u00d5\n\7\f\7\16\7\u00d8\13\7\3\7\3\7\7\7\u00dc"+
		"\n\7\f\7\16\7\u00df\13\7\3\7\5\7\u00e2\n\7\3\7\5\7\u00e5\n\7\3\b\3\b\3"+
		"\b\3\b\7\b\u00eb\n\b\f\b\16\b\u00ee\13\b\3\b\5\b\u00f1\n\b\3\b\5\b\u00f4"+
		"\n\b\3\t\3\t\3\t\5\t\u00f9\n\t\3\t\3\t\5\t\u00fd\n\t\3\t\5\t\u0100\n\t"+
		"\3\t\5\t\u0103\n\t\3\t\3\t\3\t\5\t\u0108\n\t\3\t\5\t\u010b\n\t\5\t\u010d"+
		"\n\t\3\n\3\n\3\n\3\n\5\n\u0113\n\n\3\n\5\n\u0116\n\n\3\n\3\n\5\n\u011a"+
		"\n\n\3\n\3\n\5\n\u011e\n\n\3\n\3\n\5\n\u0122\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\5\13\u012d\n\13\3\f\3\f\3\f\5\f\u0132\n\f\3\f"+
		"\3\f\3\r\3\r\3\r\7\r\u0139\n\r\f\r\16\r\u013c\13\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\""+
		"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3"+
		"*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62"+
		"\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\3\67\38\38\38\38\39\39\39\39\39\3:\3:\3:\3:\7:\u01b6\n"+
		":\f:\16:\u01b9\13:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3>\3>\3>\3>\3?\6?\u01ca"+
		"\n?\r?\16?\u01cb\3?\3?\3@\3@\7@\u01d2\n@\f@\16@\u01d5\13@\3A\3A\5A\u01d9"+
		"\nA\3A\3A\3B\3B\3B\3B\5B\u01e1\nB\3B\5B\u01e4\nB\3B\3B\3B\6B\u01e9\nB"+
		"\rB\16B\u01ea\3B\3B\3B\3B\3B\5B\u01f2\nB\3C\3C\3C\7C\u01f7\nC\fC\16C\u01fa"+
		"\13C\3C\5C\u01fd\nC\3D\3D\3E\3E\7E\u0203\nE\fE\16E\u0206\13E\3E\5E\u0209"+
		"\nE\3F\3F\5F\u020d\nF\3G\3G\3G\3G\5G\u0213\nG\3\u0095\2H\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{"+
		"?}@\177A\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\3\2\36"+
		"\5\2\f\f\17\17\u202a\u202b\4\2\f\f\16\17\3\2\63;\4\2NNnn\4\2ZZzz\5\2\62"+
		";CHch\6\2\62;CHaach\3\2\629\4\2\629aa\4\2DDdd\3\2\62\63\4\2\62\63aa\6"+
		"\2FFHHffhh\4\2RRrr\4\2--//\6\2\f\f\17\17))^^\6\2\f\f\17\17$$^^\3\2bb\5"+
		"\2\13\f\16\17\"\"\4\2GGgg\n\2$$))^^ddhhppttvv\3\2\62\65\3\2\62;\4\2\62"+
		";aa\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001"+
		"\2\u0240\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y"+
		"\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\3\u008f\3\2\2\2\5\u009d\3"+
		"\2\2\2\7\u00a8\3\2\2\2\t\u00be\3\2\2\2\13\u00c3\3\2\2\2\r\u00d2\3\2\2"+
		"\2\17\u00e6\3\2\2\2\21\u010c\3\2\2\2\23\u010e\3\2\2\2\25\u012c\3\2\2\2"+
		"\27\u012e\3\2\2\2\31\u0135\3\2\2\2\33\u013f\3\2\2\2\35\u0144\3\2\2\2\37"+
		"\u0146\3\2\2\2!\u0148\3\2\2\2#\u014a\3\2\2\2%\u014c\3\2\2\2\'\u014e\3"+
		"\2\2\2)\u0150\3\2\2\2+\u0152\3\2\2\2-\u0154\3\2\2\2/\u0156\3\2\2\2\61"+
		"\u0158\3\2\2\2\63\u015a\3\2\2\2\65\u015c\3\2\2\2\67\u015e\3\2\2\29\u0160"+
		"\3\2\2\2;\u0162\3\2\2\2=\u0164\3\2\2\2?\u0167\3\2\2\2A\u016a\3\2\2\2C"+
		"\u016d\3\2\2\2E\u0170\3\2\2\2G\u0173\3\2\2\2I\u0176\3\2\2\2K\u0179\3\2"+
		"\2\2M\u017c\3\2\2\2O\u017e\3\2\2\2Q\u0180\3\2\2\2S\u0182\3\2\2\2U\u0184"+
		"\3\2\2\2W\u0186\3\2\2\2Y\u0188\3\2\2\2[\u018a\3\2\2\2]\u018c\3\2\2\2_"+
		"\u018f\3\2\2\2a\u0192\3\2\2\2c\u0195\3\2\2\2e\u0198\3\2\2\2g\u019b\3\2"+
		"\2\2i\u019e\3\2\2\2k\u01a1\3\2\2\2m\u01a4\3\2\2\2o\u01a8\3\2\2\2q\u01ac"+
		"\3\2\2\2s\u01b1\3\2\2\2u\u01bc\3\2\2\2w\u01bf\3\2\2\2y\u01c2\3\2\2\2{"+
		"\u01c4\3\2\2\2}\u01c9\3\2\2\2\177\u01cf\3\2\2\2\u0081\u01d6\3\2\2\2\u0083"+
		"\u01f1\3\2\2\2\u0085\u01f3\3\2\2\2\u0087\u01fe\3\2\2\2\u0089\u0200\3\2"+
		"\2\2\u008b\u020c\3\2\2\2\u008d\u0212\3\2\2\2\u008f\u0090\7\61\2\2\u0090"+
		"\u0091\7,\2\2\u0091\u0095\3\2\2\2\u0092\u0094\13\2\2\2\u0093\u0092\3\2"+
		"\2\2\u0094\u0097\3\2\2\2\u0095\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\7,\2\2\u0099\u009a\7\61"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\2\2\2\u009c\4\3\2\2\2\u009d\u009e"+
		"\7\61\2\2\u009e\u009f\7\61\2\2\u009f\u00a3\3\2\2\2\u00a0\u00a2\n\2\2\2"+
		"\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\b\3\2\2\u00a7"+
		"\6\3\2\2\2\u00a8\u00ac\7%\2\2\u00a9\u00ab\n\3\2\2\u00aa\u00a9\3\2\2\2"+
		"\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\b\4\2\2\u00b0\b\3\2\2\2\u00b1"+
		"\u00bf\7\62\2\2\u00b2\u00bc\t\4\2\2\u00b3\u00b5\5\u0089E\2\u00b4\u00b3"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00bd\3\2\2\2\u00b6\u00b8\7a\2\2\u00b7"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\5\u0089E\2\u00bc\u00b4\3\2\2\2\u00bc"+
		"\u00b7\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00b1\3\2\2\2\u00be\u00b2\3\2"+
		"\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00c2\t\5\2\2\u00c1\u00c0\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\n\3\2\2\2\u00c3\u00c4\7\62\2\2\u00c4\u00c5\t\6\2"+
		"\2\u00c5\u00cd\t\7\2\2\u00c6\u00c8\t\b\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00ce\t\7\2\2\u00cd\u00c9\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00d1\t\5\2\2\u00d0\u00cf\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\f\3\2\2\2\u00d2\u00d6\7\62\2\2\u00d3\u00d5\7a\2\2"+
		"\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7"+
		"\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00e1\t\t\2\2\u00da"+
		"\u00dc\t\n\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2"+
		"\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0"+
		"\u00e2\t\t\2\2\u00e1\u00dd\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2"+
		"\2\2\u00e3\u00e5\t\5\2\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\16\3\2\2\2\u00e6\u00e7\7\62\2\2\u00e7\u00e8\t\13\2\2\u00e8\u00f0\t\f"+
		"\2\2\u00e9\u00eb\t\r\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2"+
		"\2\2\u00ef\u00f1\t\f\2\2\u00f0\u00ec\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f3\3\2\2\2\u00f2\u00f4\t\5\2\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\20\3\2\2\2\u00f5\u00f6\5\u0089E\2\u00f6\u00f8\7\60\2\2\u00f7"+
		"\u00f9\5\u0089E\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fd"+
		"\3\2\2\2\u00fa\u00fb\7\60\2\2\u00fb\u00fd\5\u0089E\2\u00fc\u00f5\3\2\2"+
		"\2\u00fc\u00fa\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u0100\5\u0081A\2\u00ff"+
		"\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u0103\t\16"+
		"\2\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u010d\3\2\2\2\u0104"+
		"\u010a\5\u0089E\2\u0105\u0107\5\u0081A\2\u0106\u0108\t\16\2\2\u0107\u0106"+
		"\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u010b\t\16\2\2"+
		"\u010a\u0105\3\2\2\2\u010a\u0109\3\2\2\2\u010b\u010d\3\2\2\2\u010c\u00fc"+
		"\3\2\2\2\u010c\u0104\3\2\2\2\u010d\22\3\2\2\2\u010e\u010f\7\62\2\2\u010f"+
		"\u0119\t\6\2\2\u0110\u0112\5\u0085C\2\u0111\u0113\7\60\2\2\u0112\u0111"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u011a\3\2\2\2\u0114\u0116\5\u0085C"+
		"\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118"+
		"\7\60\2\2\u0118\u011a\5\u0085C\2\u0119\u0110\3\2\2\2\u0119\u0115\3\2\2"+
		"\2\u011a\u011b\3\2\2\2\u011b\u011d\t\17\2\2\u011c\u011e\t\20\2\2\u011d"+
		"\u011c\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\5\u0089"+
		"E\2\u0120\u0122\t\16\2\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122"+
		"\24\3\2\2\2\u0123\u0124\7v\2\2\u0124\u0125\7t\2\2\u0125\u0126\7w\2\2\u0126"+
		"\u012d\7g\2\2\u0127\u0128\7h\2\2\u0128\u0129\7c\2\2\u0129\u012a\7n\2\2"+
		"\u012a\u012b\7u\2\2\u012b\u012d\7g\2\2\u012c\u0123\3\2\2\2\u012c\u0127"+
		"\3\2\2\2\u012d\26\3\2\2\2\u012e\u0131\7)\2\2\u012f\u0132\n\21\2\2\u0130"+
		"\u0132\5\u0083B\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132\u0133"+
		"\3\2\2\2\u0133\u0134\7)\2\2\u0134\30\3\2\2\2\u0135\u013a\7$\2\2\u0136"+
		"\u0139\n\22\2\2\u0137\u0139\5\u0083B\2\u0138\u0136\3\2\2\2\u0138\u0137"+
		"\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		"\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7$\2\2\u013e\32\3\2\2\2"+
		"\u013f\u0140\7p\2\2\u0140\u0141\7w\2\2\u0141\u0142\7n\2\2\u0142\u0143"+
		"\7n\2\2\u0143\34\3\2\2\2\u0144\u0145\7*\2\2\u0145\36\3\2\2\2\u0146\u0147"+
		"\7+\2\2\u0147 \3\2\2\2\u0148\u0149\7}\2\2\u0149\"\3\2\2\2\u014a\u014b"+
		"\7\177\2\2\u014b$\3\2\2\2\u014c\u014d\7]\2\2\u014d&\3\2\2\2\u014e\u014f"+
		"\7_\2\2\u014f(\3\2\2\2\u0150\u0151\7=\2\2\u0151*\3\2\2\2\u0152\u0153\7"+
		".\2\2\u0153,\3\2\2\2\u0154\u0155\7\60\2\2\u0155.\3\2\2\2\u0156\u0157\7"+
		"?\2\2\u0157\60\3\2\2\2\u0158\u0159\7@\2\2\u0159\62\3\2\2\2\u015a\u015b"+
		"\7>\2\2\u015b\64\3\2\2\2\u015c\u015d\7#\2\2\u015d\66\3\2\2\2\u015e\u015f"+
		"\7\u0080\2\2\u015f8\3\2\2\2\u0160\u0161\7A\2\2\u0161:\3\2\2\2\u0162\u0163"+
		"\7<\2\2\u0163<\3\2\2\2\u0164\u0165\7?\2\2\u0165\u0166\7?\2\2\u0166>\3"+
		"\2\2\2\u0167\u0168\7>\2\2\u0168\u0169\7?\2\2\u0169@\3\2\2\2\u016a\u016b"+
		"\7@\2\2\u016b\u016c\7?\2\2\u016cB\3\2\2\2\u016d\u016e\7#\2\2\u016e\u016f"+
		"\7?\2\2\u016fD\3\2\2\2\u0170\u0171\7(\2\2\u0171\u0172\7(\2\2\u0172F\3"+
		"\2\2\2\u0173\u0174\7~\2\2\u0174\u0175\7~\2\2\u0175H\3\2\2\2\u0176\u0177"+
		"\7-\2\2\u0177\u0178\7-\2\2\u0178J\3\2\2\2\u0179\u017a\7/\2\2\u017a\u017b"+
		"\7/\2\2\u017bL\3\2\2\2\u017c\u017d\7-\2\2\u017dN\3\2\2\2\u017e\u017f\7"+
		"/\2\2\u017fP\3\2\2\2\u0180\u0181\7,\2\2\u0181R\3\2\2\2\u0182\u0183\7\61"+
		"\2\2\u0183T\3\2\2\2\u0184\u0185\7(\2\2\u0185V\3\2\2\2\u0186\u0187\7~\2"+
		"\2\u0187X\3\2\2\2\u0188\u0189\7`\2\2\u0189Z\3\2\2\2\u018a\u018b\7\'\2"+
		"\2\u018b\\\3\2\2\2\u018c\u018d\7-\2\2\u018d\u018e\7?\2\2\u018e^\3\2\2"+
		"\2\u018f\u0190\7/\2\2\u0190\u0191\7?\2\2\u0191`\3\2\2\2\u0192\u0193\7"+
		",\2\2\u0193\u0194\7?\2\2\u0194b\3\2\2\2\u0195\u0196\7\61\2\2\u0196\u0197"+
		"\7?\2\2\u0197d\3\2\2\2\u0198\u0199\7(\2\2\u0199\u019a\7?\2\2\u019af\3"+
		"\2\2\2\u019b\u019c\7~\2\2\u019c\u019d\7?\2\2\u019dh\3\2\2\2\u019e\u019f"+
		"\7`\2\2\u019f\u01a0\7?\2\2\u01a0j\3\2\2\2\u01a1\u01a2\7\'\2\2\u01a2\u01a3"+
		"\7?\2\2\u01a3l\3\2\2\2\u01a4\u01a5\7>\2\2\u01a5\u01a6\7>\2\2\u01a6\u01a7"+
		"\7?\2\2\u01a7n\3\2\2\2\u01a8\u01a9\7@\2\2\u01a9\u01aa\7@\2\2\u01aa\u01ab"+
		"\7?\2\2\u01abp\3\2\2\2\u01ac\u01ad\7@\2\2\u01ad\u01ae\7@\2\2\u01ae\u01af"+
		"\7@\2\2\u01af\u01b0\7?\2\2\u01b0r\3\2\2\2\u01b1\u01b7\7b\2\2\u01b2\u01b3"+
		"\7^\2\2\u01b3\u01b6\7b\2\2\u01b4\u01b6\n\23\2\2\u01b5\u01b2\3\2\2\2\u01b5"+
		"\u01b4\3\2\2\2\u01b6\u01b9\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2"+
		"\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01b7\3\2\2\2\u01ba\u01bb\7b\2\2\u01bb"+
		"t\3\2\2\2\u01bc\u01bd\7/\2\2\u01bd\u01be\7@\2\2\u01bev\3\2\2\2\u01bf\u01c0"+
		"\7<\2\2\u01c0\u01c1\7<\2\2\u01c1x\3\2\2\2\u01c2\u01c3\7B\2\2\u01c3z\3"+
		"\2\2\2\u01c4\u01c5\7\60\2\2\u01c5\u01c6\7\60\2\2\u01c6\u01c7\7\60\2\2"+
		"\u01c7|\3\2\2\2\u01c8\u01ca\t\24\2\2\u01c9\u01c8\3\2\2\2\u01ca\u01cb\3"+
		"\2\2\2\u01cb\u01c9\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"\u01ce\b?\2\2\u01ce~\3\2\2\2\u01cf\u01d3\5\u008dG\2\u01d0\u01d2\5\u008b"+
		"F\2\u01d1\u01d0\3\2\2\2\u01d2\u01d5\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d3"+
		"\u01d4\3\2\2\2\u01d4\u0080\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d6\u01d8\t\25"+
		"\2\2\u01d7\u01d9\t\20\2\2\u01d8\u01d7\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9"+
		"\u01da\3\2\2\2\u01da\u01db\5\u0089E\2\u01db\u0082\3\2\2\2\u01dc\u01dd"+
		"\7^\2\2\u01dd\u01f2\t\26\2\2\u01de\u01e3\7^\2\2\u01df\u01e1\t\27\2\2\u01e0"+
		"\u01df\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e4\t\t"+
		"\2\2\u01e3\u01e0\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5"+
		"\u01f2\t\t\2\2\u01e6\u01e8\7^\2\2\u01e7\u01e9\7w\2\2\u01e8\u01e7\3\2\2"+
		"\2\u01e9\u01ea\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec"+
		"\3\2\2\2\u01ec\u01ed\5\u0087D\2\u01ed\u01ee\5\u0087D\2\u01ee\u01ef\5\u0087"+
		"D\2\u01ef\u01f0\5\u0087D\2\u01f0\u01f2\3\2\2\2\u01f1\u01dc\3\2\2\2\u01f1"+
		"\u01de\3\2\2\2\u01f1\u01e6\3\2\2\2\u01f2\u0084\3\2\2\2\u01f3\u01fc\5\u0087"+
		"D\2\u01f4\u01f7\5\u0087D\2\u01f5\u01f7\7a\2\2\u01f6\u01f4\3\2\2\2\u01f6"+
		"\u01f5\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2"+
		"\2\2\u01f9\u01fb\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fd\5\u0087D\2\u01fc"+
		"\u01f8\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u0086\3\2\2\2\u01fe\u01ff\t\7"+
		"\2\2\u01ff\u0088\3\2\2\2\u0200\u0208\t\30\2\2\u0201\u0203\t\31\2\2\u0202"+
		"\u0201\3\2\2\2\u0203\u0206\3\2\2\2\u0204\u0202\3\2\2\2\u0204\u0205\3\2"+
		"\2\2\u0205\u0207\3\2\2\2\u0206\u0204\3\2\2\2\u0207\u0209\t\30\2\2\u0208"+
		"\u0204\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u008a\3\2\2\2\u020a\u020d\5\u008d"+
		"G\2\u020b\u020d\t\30\2\2\u020c\u020a\3\2\2\2\u020c\u020b\3\2\2\2\u020d"+
		"\u008c\3\2\2\2\u020e\u0213\t\32\2\2\u020f\u0213\n\33\2\2\u0210\u0211\t"+
		"\34\2\2\u0211\u0213\t\35\2\2\u0212\u020e\3\2\2\2\u0212\u020f\3\2\2\2\u0212"+
		"\u0210\3\2\2\2\u0213\u008e\3\2\2\2\65\2\u0095\u00a3\u00ac\u00b4\u00b9"+
		"\u00bc\u00be\u00c1\u00c9\u00cd\u00d0\u00d6\u00dd\u00e1\u00e4\u00ec\u00f0"+
		"\u00f3\u00f8\u00fc\u00ff\u0102\u0107\u010a\u010c\u0112\u0115\u0119\u011d"+
		"\u0121\u012c\u0131\u0138\u013a\u01b5\u01b7\u01cb\u01d3\u01d8\u01e0\u01e3"+
		"\u01ea\u01f1\u01f6\u01f8\u01fc\u0204\u0208\u020c\u0212\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}