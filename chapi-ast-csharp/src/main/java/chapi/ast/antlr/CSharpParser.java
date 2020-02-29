// Generated from CSharpParser.g4 by ANTLR 4.8
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
public class CSharpParser extends Parser {
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
		RULE_compilation_unit = 0, RULE_namespace_or_type_name = 1, RULE_type_ = 2, 
		RULE_base_type = 3, RULE_simple_type = 4, RULE_numeric_type = 5, RULE_integral_type = 6, 
		RULE_floating_point_type = 7, RULE_class_type = 8, RULE_type_argument_list = 9, 
		RULE_argument_list = 10, RULE_argument = 11, RULE_expression = 12, RULE_non_assignment_expression = 13, 
		RULE_assignment = 14, RULE_assignment_operator = 15, RULE_conditional_expression = 16, 
		RULE_null_coalescing_expression = 17, RULE_conditional_or_expression = 18, 
		RULE_conditional_and_expression = 19, RULE_inclusive_or_expression = 20, 
		RULE_exclusive_or_expression = 21, RULE_and_expression = 22, RULE_equality_expression = 23, 
		RULE_relational_expression = 24, RULE_shift_expression = 25, RULE_additive_expression = 26, 
		RULE_multiplicative_expression = 27, RULE_unary_expression = 28, RULE_primary_expression = 29, 
		RULE_primary_expression_start = 30, RULE_member_access = 31, RULE_bracket_expression = 32, 
		RULE_indexer_argument = 33, RULE_predefined_type = 34, RULE_expression_list = 35, 
		RULE_object_or_collection_initializer = 36, RULE_object_initializer = 37, 
		RULE_member_initializer_list = 38, RULE_member_initializer = 39, RULE_initializer_value = 40, 
		RULE_collection_initializer = 41, RULE_element_initializer = 42, RULE_anonymous_object_initializer = 43, 
		RULE_member_declarator_list = 44, RULE_member_declarator = 45, RULE_unbound_type_name = 46, 
		RULE_generic_dimension_specifier = 47, RULE_isType = 48, RULE_lambda_expression = 49, 
		RULE_anonymous_function_signature = 50, RULE_explicit_anonymous_function_parameter_list = 51, 
		RULE_explicit_anonymous_function_parameter = 52, RULE_implicit_anonymous_function_parameter_list = 53, 
		RULE_anonymous_function_body = 54, RULE_query_expression = 55, RULE_from_clause = 56, 
		RULE_query_body = 57, RULE_query_body_clause = 58, RULE_let_clause = 59, 
		RULE_where_clause = 60, RULE_combined_join_clause = 61, RULE_orderby_clause = 62, 
		RULE_ordering = 63, RULE_select_or_group_clause = 64, RULE_query_continuation = 65, 
		RULE_statement = 66, RULE_labeled_Statement = 67, RULE_embedded_statement = 68, 
		RULE_simple_embedded_statement = 69, RULE_block = 70, RULE_local_variable_declaration = 71, 
		RULE_local_variable_type = 72, RULE_local_variable_declarator = 73, RULE_local_variable_initializer = 74, 
		RULE_local_constant_declaration = 75, RULE_if_body = 76, RULE_switch_section = 77, 
		RULE_switch_label = 78, RULE_statement_list = 79, RULE_for_initializer = 80, 
		RULE_for_iterator = 81, RULE_catch_clauses = 82, RULE_specific_catch_clause = 83, 
		RULE_general_catch_clause = 84, RULE_exception_filter = 85, RULE_finally_clause = 86, 
		RULE_resource_acquisition = 87, RULE_namespace_declaration = 88, RULE_qualified_identifier = 89, 
		RULE_namespace_body = 90, RULE_extern_alias_directives = 91, RULE_extern_alias_directive = 92, 
		RULE_using_directives = 93, RULE_using_directive = 94, RULE_namespace_member_declarations = 95, 
		RULE_namespace_member_declaration = 96, RULE_type_declaration = 97, RULE_qualified_alias_member = 98, 
		RULE_type_parameter_list = 99, RULE_type_parameter = 100, RULE_class_base = 101, 
		RULE_interface_type_list = 102, RULE_type_parameter_constraints_clauses = 103, 
		RULE_type_parameter_constraints_clause = 104, RULE_type_parameter_constraints = 105, 
		RULE_primary_constraint = 106, RULE_secondary_constraints = 107, RULE_constructor_constraint = 108, 
		RULE_class_body = 109, RULE_class_member_declarations = 110, RULE_class_member_declaration = 111, 
		RULE_all_member_modifiers = 112, RULE_all_member_modifier = 113, RULE_common_member_declaration = 114, 
		RULE_typed_member_declaration = 115, RULE_constant_declarators = 116, 
		RULE_constant_declarator = 117, RULE_variable_declarators = 118, RULE_variable_declarator = 119, 
		RULE_variable_initializer = 120, RULE_return_type = 121, RULE_member_name = 122, 
		RULE_method_body = 123, RULE_formal_parameter_list = 124, RULE_fixed_parameters = 125, 
		RULE_fixed_parameter = 126, RULE_parameter_modifier = 127, RULE_parameter_array = 128, 
		RULE_accessor_declarations = 129, RULE_get_accessor_declaration = 130, 
		RULE_set_accessor_declaration = 131, RULE_accessor_modifier = 132, RULE_accessor_body = 133, 
		RULE_event_accessor_declarations = 134, RULE_add_accessor_declaration = 135, 
		RULE_remove_accessor_declaration = 136, RULE_overloadable_operator = 137, 
		RULE_conversion_operator_declarator = 138, RULE_constructor_initializer = 139, 
		RULE_body = 140, RULE_struct_interfaces = 141, RULE_struct_body = 142, 
		RULE_struct_member_declaration = 143, RULE_array_type = 144, RULE_rank_specifier = 145, 
		RULE_array_initializer = 146, RULE_variant_type_parameter_list = 147, 
		RULE_variant_type_parameter = 148, RULE_variance_annotation = 149, RULE_interface_base = 150, 
		RULE_interface_body = 151, RULE_interface_member_declaration = 152, RULE_interface_accessors = 153, 
		RULE_enum_base = 154, RULE_enum_body = 155, RULE_enum_member_declaration = 156, 
		RULE_global_attribute_section = 157, RULE_global_attribute_target = 158, 
		RULE_attributes = 159, RULE_attribute_section = 160, RULE_attribute_target = 161, 
		RULE_attribute_list = 162, RULE_attribute = 163, RULE_attribute_argument = 164, 
		RULE_pointer_type = 165, RULE_fixed_pointer_declarators = 166, RULE_fixed_pointer_declarator = 167, 
		RULE_fixed_pointer_initializer = 168, RULE_fixed_size_buffer_declarator = 169, 
		RULE_local_variable_initializer_unsafe = 170, RULE_right_arrow = 171, 
		RULE_right_shift = 172, RULE_right_shift_assignment = 173, RULE_literal = 174, 
		RULE_boolean_literal = 175, RULE_string_literal = 176, RULE_interpolated_regular_string = 177, 
		RULE_interpolated_verbatium_string = 178, RULE_interpolated_regular_string_part = 179, 
		RULE_interpolated_verbatium_string_part = 180, RULE_interpolated_string_expression = 181, 
		RULE_keyword = 182, RULE_class_definition = 183, RULE_struct_definition = 184, 
		RULE_interface_definition = 185, RULE_enum_definition = 186, RULE_delegate_definition = 187, 
		RULE_event_declaration = 188, RULE_field_declaration = 189, RULE_property_declaration = 190, 
		RULE_constant_declaration = 191, RULE_indexer_declaration = 192, RULE_destructor_definition = 193, 
		RULE_constructor_declaration = 194, RULE_method_declaration = 195, RULE_method_member_name = 196, 
		RULE_operator_declaration = 197, RULE_arg_declaration = 198, RULE_method_invocation = 199, 
		RULE_object_creation_expression = 200, RULE_identifier = 201;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilation_unit", "namespace_or_type_name", "type_", "base_type", "simple_type", 
			"numeric_type", "integral_type", "floating_point_type", "class_type", 
			"type_argument_list", "argument_list", "argument", "expression", "non_assignment_expression", 
			"assignment", "assignment_operator", "conditional_expression", "null_coalescing_expression", 
			"conditional_or_expression", "conditional_and_expression", "inclusive_or_expression", 
			"exclusive_or_expression", "and_expression", "equality_expression", "relational_expression", 
			"shift_expression", "additive_expression", "multiplicative_expression", 
			"unary_expression", "primary_expression", "primary_expression_start", 
			"member_access", "bracket_expression", "indexer_argument", "predefined_type", 
			"expression_list", "object_or_collection_initializer", "object_initializer", 
			"member_initializer_list", "member_initializer", "initializer_value", 
			"collection_initializer", "element_initializer", "anonymous_object_initializer", 
			"member_declarator_list", "member_declarator", "unbound_type_name", "generic_dimension_specifier", 
			"isType", "lambda_expression", "anonymous_function_signature", "explicit_anonymous_function_parameter_list", 
			"explicit_anonymous_function_parameter", "implicit_anonymous_function_parameter_list", 
			"anonymous_function_body", "query_expression", "from_clause", "query_body", 
			"query_body_clause", "let_clause", "where_clause", "combined_join_clause", 
			"orderby_clause", "ordering", "select_or_group_clause", "query_continuation", 
			"statement", "labeled_Statement", "embedded_statement", "simple_embedded_statement", 
			"block", "local_variable_declaration", "local_variable_type", "local_variable_declarator", 
			"local_variable_initializer", "local_constant_declaration", "if_body", 
			"switch_section", "switch_label", "statement_list", "for_initializer", 
			"for_iterator", "catch_clauses", "specific_catch_clause", "general_catch_clause", 
			"exception_filter", "finally_clause", "resource_acquisition", "namespace_declaration", 
			"qualified_identifier", "namespace_body", "extern_alias_directives", 
			"extern_alias_directive", "using_directives", "using_directive", "namespace_member_declarations", 
			"namespace_member_declaration", "type_declaration", "qualified_alias_member", 
			"type_parameter_list", "type_parameter", "class_base", "interface_type_list", 
			"type_parameter_constraints_clauses", "type_parameter_constraints_clause", 
			"type_parameter_constraints", "primary_constraint", "secondary_constraints", 
			"constructor_constraint", "class_body", "class_member_declarations", 
			"class_member_declaration", "all_member_modifiers", "all_member_modifier", 
			"common_member_declaration", "typed_member_declaration", "constant_declarators", 
			"constant_declarator", "variable_declarators", "variable_declarator", 
			"variable_initializer", "return_type", "member_name", "method_body", 
			"formal_parameter_list", "fixed_parameters", "fixed_parameter", "parameter_modifier", 
			"parameter_array", "accessor_declarations", "get_accessor_declaration", 
			"set_accessor_declaration", "accessor_modifier", "accessor_body", "event_accessor_declarations", 
			"add_accessor_declaration", "remove_accessor_declaration", "overloadable_operator", 
			"conversion_operator_declarator", "constructor_initializer", "body", 
			"struct_interfaces", "struct_body", "struct_member_declaration", "array_type", 
			"rank_specifier", "array_initializer", "variant_type_parameter_list", 
			"variant_type_parameter", "variance_annotation", "interface_base", "interface_body", 
			"interface_member_declaration", "interface_accessors", "enum_base", "enum_body", 
			"enum_member_declaration", "global_attribute_section", "global_attribute_target", 
			"attributes", "attribute_section", "attribute_target", "attribute_list", 
			"attribute", "attribute_argument", "pointer_type", "fixed_pointer_declarators", 
			"fixed_pointer_declarator", "fixed_pointer_initializer", "fixed_size_buffer_declarator", 
			"local_variable_initializer_unsafe", "right_arrow", "right_shift", "right_shift_assignment", 
			"literal", "boolean_literal", "string_literal", "interpolated_regular_string", 
			"interpolated_verbatium_string", "interpolated_regular_string_part", 
			"interpolated_verbatium_string_part", "interpolated_string_expression", 
			"keyword", "class_definition", "struct_definition", "interface_definition", 
			"enum_definition", "delegate_definition", "event_declaration", "field_declaration", 
			"property_declaration", "constant_declaration", "indexer_declaration", 
			"destructor_definition", "constructor_declaration", "method_declaration", 
			"method_member_name", "operator_declaration", "arg_declaration", "method_invocation", 
			"object_creation_expression", "identifier"
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
	public String getGrammarFileName() { return "CSharpParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CSharpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Compilation_unitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CSharpParser.EOF, 0); }
		public TerminalNode BYTE_ORDER_MARK() { return getToken(CSharpParser.BYTE_ORDER_MARK, 0); }
		public Extern_alias_directivesContext extern_alias_directives() {
			return getRuleContext(Extern_alias_directivesContext.class,0);
		}
		public Using_directivesContext using_directives() {
			return getRuleContext(Using_directivesContext.class,0);
		}
		public List<Global_attribute_sectionContext> global_attribute_section() {
			return getRuleContexts(Global_attribute_sectionContext.class);
		}
		public Global_attribute_sectionContext global_attribute_section(int i) {
			return getRuleContext(Global_attribute_sectionContext.class,i);
		}
		public Namespace_member_declarationsContext namespace_member_declarations() {
			return getRuleContext(Namespace_member_declarationsContext.class,0);
		}
		public Compilation_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilation_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCompilation_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCompilation_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCompilation_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compilation_unitContext compilation_unit() throws RecognitionException {
		Compilation_unitContext _localctx = new Compilation_unitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilation_unit);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BYTE_ORDER_MARK) {
				{
				setState(404);
				match(BYTE_ORDER_MARK);
				}
			}

			setState(408);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(407);
				extern_alias_directives();
				}
				break;
			}
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(410);
				using_directives();
				}
			}

			setState(416);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(413);
					global_attribute_section();
					}
					} 
				}
				setState(418);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << CLASS) | (1L << DELEGATE) | (1L << ENUM) | (1L << EXTERN) | (1L << INTERFACE) | (1L << INTERNAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (NAMESPACE - 64)) | (1L << (NEW - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARTIAL - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (SEALED - 64)) | (1L << (STATIC - 64)) | (1L << (STRUCT - 64)) | (1L << (UNSAFE - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOLATILE - 64)) | (1L << (OPEN_BRACKET - 64)))) != 0)) {
				{
				setState(419);
				namespace_member_declarations();
				}
			}

			setState(422);
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

	public static class Namespace_or_type_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public Qualified_alias_memberContext qualified_alias_member() {
			return getRuleContext(Qualified_alias_memberContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Type_argument_listContext> type_argument_list() {
			return getRuleContexts(Type_argument_listContext.class);
		}
		public Type_argument_listContext type_argument_list(int i) {
			return getRuleContext(Type_argument_listContext.class,i);
		}
		public Namespace_or_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_or_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_or_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_or_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNamespace_or_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_or_type_nameContext namespace_or_type_name() throws RecognitionException {
		Namespace_or_type_nameContext _localctx = new Namespace_or_type_nameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespace_or_type_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(424);
				identifier();
				setState(426);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(425);
					type_argument_list();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(428);
				qualified_alias_member();
				}
				break;
			}
			setState(438);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(431);
					match(DOT);
					setState(432);
					identifier();
					setState(434);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(433);
						type_argument_list();
						}
						break;
					}
					}
					} 
				}
				setState(440);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class Type_Context extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			base_type();
			setState(447);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(445);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case INTERR:
						{
						setState(442);
						match(INTERR);
						}
						break;
					case OPEN_BRACKET:
						{
						setState(443);
						rank_specifier();
						}
						break;
					case STAR:
						{
						setState(444);
						match(STAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(449);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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

	public static class Base_typeContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public Base_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBase_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBase_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBase_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Base_typeContext base_type() throws RecognitionException {
		Base_typeContext _localctx = new Base_typeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_base_type);
		try {
			setState(454);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(450);
				simple_type();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case STRING:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(451);
				class_type();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(452);
				match(VOID);
				setState(453);
				match(STAR);
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

	public static class Simple_typeContext extends ParserRuleContext {
		public Numeric_typeContext numeric_type() {
			return getRuleContext(Numeric_typeContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public Simple_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSimple_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSimple_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSimple_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_typeContext simple_type() throws RecognitionException {
		Simple_typeContext _localctx = new Simple_typeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_simple_type);
		try {
			setState(458);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(456);
				numeric_type();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(457);
				match(BOOL);
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

	public static class Numeric_typeContext extends ParserRuleContext {
		public Integral_typeContext integral_type() {
			return getRuleContext(Integral_typeContext.class,0);
		}
		public Floating_point_typeContext floating_point_type() {
			return getRuleContext(Floating_point_typeContext.class,0);
		}
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public Numeric_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNumeric_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNumeric_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNumeric_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_typeContext numeric_type() throws RecognitionException {
		Numeric_typeContext _localctx = new Numeric_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_numeric_type);
		try {
			setState(463);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				integral_type();
				}
				break;
			case DOUBLE:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(461);
				floating_point_type();
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(462);
				match(DECIMAL);
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

	public static class Integral_typeContext extends ParserRuleContext {
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public Integral_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integral_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIntegral_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIntegral_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIntegral_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integral_typeContext integral_type() throws RecognitionException {
		Integral_typeContext _localctx = new Integral_typeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_integral_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTE) | (1L << CHAR) | (1L << INT) | (1L << LONG))) != 0) || ((((_la - 82)) & ~0x3f) == 0 && ((1L << (_la - 82)) & ((1L << (SBYTE - 82)) | (1L << (SHORT - 82)) | (1L << (UINT - 82)) | (1L << (ULONG - 82)) | (1L << (USHORT - 82)))) != 0)) ) {
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

	public static class Floating_point_typeContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public Floating_point_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_point_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFloating_point_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFloating_point_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFloating_point_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Floating_point_typeContext floating_point_type() throws RecognitionException {
		Floating_point_typeContext _localctx = new Floating_point_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_floating_point_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			_la = _input.LA(1);
			if ( !(_la==DOUBLE || _la==FLOAT) ) {
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

	public static class Class_typeContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode DYNAMIC() { return getToken(CSharpParser.DYNAMIC, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public Class_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_typeContext class_type() throws RecognitionException {
		Class_typeContext _localctx = new Class_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_class_type);
		try {
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(469);
				namespace_or_type_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(470);
				match(OBJECT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(471);
				match(DYNAMIC);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(472);
				match(STRING);
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

	public static class Type_argument_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Type_argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_argument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_argument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_argument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_argument_listContext type_argument_list() throws RecognitionException {
		Type_argument_listContext _localctx = new Type_argument_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(LT);
			setState(476);
			type_();
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(477);
				match(COMMA);
				setState(478);
				type_();
				}
				}
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(484);
			match(GT);
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

	public static class Argument_listContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArgument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArgument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitArgument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			argument();
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(487);
				match(COMMA);
				setState(488);
				argument();
				}
				}
				setState(493);
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

	public static class ArgumentContext extends ParserRuleContext {
		public Token refout;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(494);
				identifier();
				setState(495);
				match(COLON);
				}
				break;
			}
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OUT || _la==REF) {
				{
				setState(499);
				((ArgumentContext)_localctx).refout = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==OUT || _la==REF) ) {
					((ArgumentContext)_localctx).refout = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(504);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(502);
				match(VAR);
				}
				break;
			case 2:
				{
				setState(503);
				type_();
				}
				break;
			}
			setState(506);
			expression();
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

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Non_assignment_expressionContext non_assignment_expression() {
			return getRuleContext(Non_assignment_expressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression);
		try {
			setState(510);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(508);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(509);
				non_assignment_expression();
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

	public static class Non_assignment_expressionContext extends ParserRuleContext {
		public Lambda_expressionContext lambda_expression() {
			return getRuleContext(Lambda_expressionContext.class,0);
		}
		public Query_expressionContext query_expression() {
			return getRuleContext(Query_expressionContext.class,0);
		}
		public Conditional_expressionContext conditional_expression() {
			return getRuleContext(Conditional_expressionContext.class,0);
		}
		public Non_assignment_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_assignment_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNon_assignment_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNon_assignment_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNon_assignment_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_assignment_expressionContext non_assignment_expression() throws RecognitionException {
		Non_assignment_expressionContext _localctx = new Non_assignment_expressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_non_assignment_expression);
		try {
			setState(515);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(512);
				lambda_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(513);
				query_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(514);
				conditional_expression();
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

	public static class AssignmentContext extends ParserRuleContext {
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public Assignment_operatorContext assignment_operator() {
			return getRuleContext(Assignment_operatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			unary_expression();
			setState(518);
			assignment_operator();
			setState(519);
			expression();
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

	public static class Assignment_operatorContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public TerminalNode OP_ADD_ASSIGNMENT() { return getToken(CSharpParser.OP_ADD_ASSIGNMENT, 0); }
		public TerminalNode OP_SUB_ASSIGNMENT() { return getToken(CSharpParser.OP_SUB_ASSIGNMENT, 0); }
		public TerminalNode OP_MULT_ASSIGNMENT() { return getToken(CSharpParser.OP_MULT_ASSIGNMENT, 0); }
		public TerminalNode OP_DIV_ASSIGNMENT() { return getToken(CSharpParser.OP_DIV_ASSIGNMENT, 0); }
		public TerminalNode OP_MOD_ASSIGNMENT() { return getToken(CSharpParser.OP_MOD_ASSIGNMENT, 0); }
		public TerminalNode OP_AND_ASSIGNMENT() { return getToken(CSharpParser.OP_AND_ASSIGNMENT, 0); }
		public TerminalNode OP_OR_ASSIGNMENT() { return getToken(CSharpParser.OP_OR_ASSIGNMENT, 0); }
		public TerminalNode OP_XOR_ASSIGNMENT() { return getToken(CSharpParser.OP_XOR_ASSIGNMENT, 0); }
		public TerminalNode OP_LEFT_SHIFT_ASSIGNMENT() { return getToken(CSharpParser.OP_LEFT_SHIFT_ASSIGNMENT, 0); }
		public Right_shift_assignmentContext right_shift_assignment() {
			return getRuleContext(Right_shift_assignmentContext.class,0);
		}
		public Assignment_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAssignment_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAssignment_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAssignment_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assignment_operatorContext assignment_operator() throws RecognitionException {
		Assignment_operatorContext _localctx = new Assignment_operatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignment_operator);
		try {
			setState(532);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(521);
				match(ASSIGNMENT);
				}
				break;
			case OP_ADD_ASSIGNMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(522);
				match(OP_ADD_ASSIGNMENT);
				}
				break;
			case OP_SUB_ASSIGNMENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(523);
				match(OP_SUB_ASSIGNMENT);
				}
				break;
			case OP_MULT_ASSIGNMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(524);
				match(OP_MULT_ASSIGNMENT);
				}
				break;
			case OP_DIV_ASSIGNMENT:
				enterOuterAlt(_localctx, 5);
				{
				setState(525);
				match(OP_DIV_ASSIGNMENT);
				}
				break;
			case OP_MOD_ASSIGNMENT:
				enterOuterAlt(_localctx, 6);
				{
				setState(526);
				match(OP_MOD_ASSIGNMENT);
				}
				break;
			case OP_AND_ASSIGNMENT:
				enterOuterAlt(_localctx, 7);
				{
				setState(527);
				match(OP_AND_ASSIGNMENT);
				}
				break;
			case OP_OR_ASSIGNMENT:
				enterOuterAlt(_localctx, 8);
				{
				setState(528);
				match(OP_OR_ASSIGNMENT);
				}
				break;
			case OP_XOR_ASSIGNMENT:
				enterOuterAlt(_localctx, 9);
				{
				setState(529);
				match(OP_XOR_ASSIGNMENT);
				}
				break;
			case OP_LEFT_SHIFT_ASSIGNMENT:
				enterOuterAlt(_localctx, 10);
				{
				setState(530);
				match(OP_LEFT_SHIFT_ASSIGNMENT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 11);
				{
				setState(531);
				right_shift_assignment();
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

	public static class Conditional_expressionContext extends ParserRuleContext {
		public Null_coalescing_expressionContext null_coalescing_expression() {
			return getRuleContext(Null_coalescing_expressionContext.class,0);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Conditional_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConditional_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_expressionContext conditional_expression() throws RecognitionException {
		Conditional_expressionContext _localctx = new Conditional_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_conditional_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			null_coalescing_expression();
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTERR) {
				{
				setState(535);
				match(INTERR);
				setState(536);
				expression();
				setState(537);
				match(COLON);
				setState(538);
				expression();
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

	public static class Null_coalescing_expressionContext extends ParserRuleContext {
		public Conditional_or_expressionContext conditional_or_expression() {
			return getRuleContext(Conditional_or_expressionContext.class,0);
		}
		public TerminalNode OP_COALESCING() { return getToken(CSharpParser.OP_COALESCING, 0); }
		public Null_coalescing_expressionContext null_coalescing_expression() {
			return getRuleContext(Null_coalescing_expressionContext.class,0);
		}
		public Null_coalescing_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_null_coalescing_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNull_coalescing_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNull_coalescing_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNull_coalescing_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Null_coalescing_expressionContext null_coalescing_expression() throws RecognitionException {
		Null_coalescing_expressionContext _localctx = new Null_coalescing_expressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_null_coalescing_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			conditional_or_expression();
			setState(545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_COALESCING) {
				{
				setState(543);
				match(OP_COALESCING);
				setState(544);
				null_coalescing_expression();
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

	public static class Conditional_or_expressionContext extends ParserRuleContext {
		public List<Conditional_and_expressionContext> conditional_and_expression() {
			return getRuleContexts(Conditional_and_expressionContext.class);
		}
		public Conditional_and_expressionContext conditional_and_expression(int i) {
			return getRuleContext(Conditional_and_expressionContext.class,i);
		}
		public List<TerminalNode> OP_OR() { return getTokens(CSharpParser.OP_OR); }
		public TerminalNode OP_OR(int i) {
			return getToken(CSharpParser.OP_OR, i);
		}
		public Conditional_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_or_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConditional_or_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_or_expressionContext conditional_or_expression() throws RecognitionException {
		Conditional_or_expressionContext _localctx = new Conditional_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_conditional_or_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			conditional_and_expression();
			setState(552);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_OR) {
				{
				{
				setState(548);
				match(OP_OR);
				setState(549);
				conditional_and_expression();
				}
				}
				setState(554);
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

	public static class Conditional_and_expressionContext extends ParserRuleContext {
		public List<Inclusive_or_expressionContext> inclusive_or_expression() {
			return getRuleContexts(Inclusive_or_expressionContext.class);
		}
		public Inclusive_or_expressionContext inclusive_or_expression(int i) {
			return getRuleContext(Inclusive_or_expressionContext.class,i);
		}
		public List<TerminalNode> OP_AND() { return getTokens(CSharpParser.OP_AND); }
		public TerminalNode OP_AND(int i) {
			return getToken(CSharpParser.OP_AND, i);
		}
		public Conditional_and_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_and_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_and_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_and_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConditional_and_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_and_expressionContext conditional_and_expression() throws RecognitionException {
		Conditional_and_expressionContext _localctx = new Conditional_and_expressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_conditional_and_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(555);
			inclusive_or_expression();
			setState(560);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_AND) {
				{
				{
				setState(556);
				match(OP_AND);
				setState(557);
				inclusive_or_expression();
				}
				}
				setState(562);
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

	public static class Inclusive_or_expressionContext extends ParserRuleContext {
		public List<Exclusive_or_expressionContext> exclusive_or_expression() {
			return getRuleContexts(Exclusive_or_expressionContext.class);
		}
		public Exclusive_or_expressionContext exclusive_or_expression(int i) {
			return getRuleContext(Exclusive_or_expressionContext.class,i);
		}
		public List<TerminalNode> BITWISE_OR() { return getTokens(CSharpParser.BITWISE_OR); }
		public TerminalNode BITWISE_OR(int i) {
			return getToken(CSharpParser.BITWISE_OR, i);
		}
		public Inclusive_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusive_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInclusive_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInclusive_or_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInclusive_or_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inclusive_or_expressionContext inclusive_or_expression() throws RecognitionException {
		Inclusive_or_expressionContext _localctx = new Inclusive_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_inclusive_or_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563);
			exclusive_or_expression();
			setState(568);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITWISE_OR) {
				{
				{
				setState(564);
				match(BITWISE_OR);
				setState(565);
				exclusive_or_expression();
				}
				}
				setState(570);
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

	public static class Exclusive_or_expressionContext extends ParserRuleContext {
		public List<And_expressionContext> and_expression() {
			return getRuleContexts(And_expressionContext.class);
		}
		public And_expressionContext and_expression(int i) {
			return getRuleContext(And_expressionContext.class,i);
		}
		public List<TerminalNode> CARET() { return getTokens(CSharpParser.CARET); }
		public TerminalNode CARET(int i) {
			return getToken(CSharpParser.CARET, i);
		}
		public Exclusive_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusive_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExclusive_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExclusive_or_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExclusive_or_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exclusive_or_expressionContext exclusive_or_expression() throws RecognitionException {
		Exclusive_or_expressionContext _localctx = new Exclusive_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_exclusive_or_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			and_expression();
			setState(576);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CARET) {
				{
				{
				setState(572);
				match(CARET);
				setState(573);
				and_expression();
				}
				}
				setState(578);
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

	public static class And_expressionContext extends ParserRuleContext {
		public List<Equality_expressionContext> equality_expression() {
			return getRuleContexts(Equality_expressionContext.class);
		}
		public Equality_expressionContext equality_expression(int i) {
			return getRuleContext(Equality_expressionContext.class,i);
		}
		public List<TerminalNode> AMP() { return getTokens(CSharpParser.AMP); }
		public TerminalNode AMP(int i) {
			return getToken(CSharpParser.AMP, i);
		}
		public And_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnd_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnd_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAnd_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_expressionContext and_expression() throws RecognitionException {
		And_expressionContext _localctx = new And_expressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_and_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(579);
			equality_expression();
			setState(584);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(580);
					match(AMP);
					setState(581);
					equality_expression();
					}
					} 
				}
				setState(586);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	public static class Equality_expressionContext extends ParserRuleContext {
		public List<Relational_expressionContext> relational_expression() {
			return getRuleContexts(Relational_expressionContext.class);
		}
		public Relational_expressionContext relational_expression(int i) {
			return getRuleContext(Relational_expressionContext.class,i);
		}
		public List<TerminalNode> OP_EQ() { return getTokens(CSharpParser.OP_EQ); }
		public TerminalNode OP_EQ(int i) {
			return getToken(CSharpParser.OP_EQ, i);
		}
		public List<TerminalNode> OP_NE() { return getTokens(CSharpParser.OP_NE); }
		public TerminalNode OP_NE(int i) {
			return getToken(CSharpParser.OP_NE, i);
		}
		public Equality_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEquality_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEquality_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEquality_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Equality_expressionContext equality_expression() throws RecognitionException {
		Equality_expressionContext _localctx = new Equality_expressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_equality_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			relational_expression();
			setState(592);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_EQ || _la==OP_NE) {
				{
				{
				setState(588);
				_la = _input.LA(1);
				if ( !(_la==OP_EQ || _la==OP_NE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(589);
				relational_expression();
				}
				}
				setState(594);
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

	public static class Relational_expressionContext extends ParserRuleContext {
		public List<Shift_expressionContext> shift_expression() {
			return getRuleContexts(Shift_expressionContext.class);
		}
		public Shift_expressionContext shift_expression(int i) {
			return getRuleContext(Shift_expressionContext.class,i);
		}
		public List<TerminalNode> IS() { return getTokens(CSharpParser.IS); }
		public TerminalNode IS(int i) {
			return getToken(CSharpParser.IS, i);
		}
		public List<IsTypeContext> isType() {
			return getRuleContexts(IsTypeContext.class);
		}
		public IsTypeContext isType(int i) {
			return getRuleContext(IsTypeContext.class,i);
		}
		public List<TerminalNode> AS() { return getTokens(CSharpParser.AS); }
		public TerminalNode AS(int i) {
			return getToken(CSharpParser.AS, i);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(CSharpParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(CSharpParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(CSharpParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CSharpParser.GT, i);
		}
		public List<TerminalNode> OP_LE() { return getTokens(CSharpParser.OP_LE); }
		public TerminalNode OP_LE(int i) {
			return getToken(CSharpParser.OP_LE, i);
		}
		public List<TerminalNode> OP_GE() { return getTokens(CSharpParser.OP_GE); }
		public TerminalNode OP_GE(int i) {
			return getToken(CSharpParser.OP_GE, i);
		}
		public Relational_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRelational_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRelational_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRelational_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Relational_expressionContext relational_expression() throws RecognitionException {
		Relational_expressionContext _localctx = new Relational_expressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_relational_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			shift_expression();
			setState(604);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AS || _la==IS || ((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (LT - 143)) | (1L << (GT - 143)) | (1L << (OP_LE - 143)) | (1L << (OP_GE - 143)))) != 0)) {
				{
				setState(602);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LT:
				case GT:
				case OP_LE:
				case OP_GE:
					{
					setState(596);
					_la = _input.LA(1);
					if ( !(((((_la - 143)) & ~0x3f) == 0 && ((1L << (_la - 143)) & ((1L << (LT - 143)) | (1L << (GT - 143)) | (1L << (OP_LE - 143)) | (1L << (OP_GE - 143)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(597);
					shift_expression();
					}
					break;
				case IS:
					{
					setState(598);
					match(IS);
					setState(599);
					isType();
					}
					break;
				case AS:
					{
					setState(600);
					match(AS);
					setState(601);
					type_();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(606);
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

	public static class Shift_expressionContext extends ParserRuleContext {
		public List<Additive_expressionContext> additive_expression() {
			return getRuleContexts(Additive_expressionContext.class);
		}
		public Additive_expressionContext additive_expression(int i) {
			return getRuleContext(Additive_expressionContext.class,i);
		}
		public List<TerminalNode> OP_LEFT_SHIFT() { return getTokens(CSharpParser.OP_LEFT_SHIFT); }
		public TerminalNode OP_LEFT_SHIFT(int i) {
			return getToken(CSharpParser.OP_LEFT_SHIFT, i);
		}
		public List<Right_shiftContext> right_shift() {
			return getRuleContexts(Right_shiftContext.class);
		}
		public Right_shiftContext right_shift(int i) {
			return getRuleContext(Right_shiftContext.class,i);
		}
		public Shift_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shift_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterShift_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitShift_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitShift_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shift_expressionContext shift_expression() throws RecognitionException {
		Shift_expressionContext _localctx = new Shift_expressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_shift_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			additive_expression();
			setState(615);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(610);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OP_LEFT_SHIFT:
						{
						setState(608);
						match(OP_LEFT_SHIFT);
						}
						break;
					case GT:
						{
						setState(609);
						right_shift();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(612);
					additive_expression();
					}
					} 
				}
				setState(617);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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

	public static class Additive_expressionContext extends ParserRuleContext {
		public List<Multiplicative_expressionContext> multiplicative_expression() {
			return getRuleContexts(Multiplicative_expressionContext.class);
		}
		public Multiplicative_expressionContext multiplicative_expression(int i) {
			return getRuleContext(Multiplicative_expressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(CSharpParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(CSharpParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(CSharpParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(CSharpParser.MINUS, i);
		}
		public Additive_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAdditive_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAdditive_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAdditive_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Additive_expressionContext additive_expression() throws RecognitionException {
		Additive_expressionContext _localctx = new Additive_expressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_additive_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			multiplicative_expression();
			setState(623);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(619);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(620);
					multiplicative_expression();
					}
					} 
				}
				setState(625);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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

	public static class Multiplicative_expressionContext extends ParserRuleContext {
		public List<Unary_expressionContext> unary_expression() {
			return getRuleContexts(Unary_expressionContext.class);
		}
		public Unary_expressionContext unary_expression(int i) {
			return getRuleContext(Unary_expressionContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CSharpParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CSharpParser.DIV, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(CSharpParser.PERCENT); }
		public TerminalNode PERCENT(int i) {
			return getToken(CSharpParser.PERCENT, i);
		}
		public Multiplicative_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMultiplicative_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMultiplicative_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMultiplicative_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiplicative_expressionContext multiplicative_expression() throws RecognitionException {
		Multiplicative_expressionContext _localctx = new Multiplicative_expressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_multiplicative_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(626);
			unary_expression();
			setState(631);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(627);
					_la = _input.LA(1);
					if ( !(((((_la - 134)) & ~0x3f) == 0 && ((1L << (_la - 134)) & ((1L << (STAR - 134)) | (1L << (DIV - 134)) | (1L << (PERCENT - 134)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(628);
					unary_expression();
					}
					} 
				}
				setState(633);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	public static class Unary_expressionContext extends ParserRuleContext {
		public Primary_expressionContext primary_expression() {
			return getRuleContext(Primary_expressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(CSharpParser.PLUS, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(CSharpParser.MINUS, 0); }
		public TerminalNode BANG() { return getToken(CSharpParser.BANG, 0); }
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public TerminalNode OP_INC() { return getToken(CSharpParser.OP_INC, 0); }
		public TerminalNode OP_DEC() { return getToken(CSharpParser.OP_DEC, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode AWAIT() { return getToken(CSharpParser.AWAIT, 0); }
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnary_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUnary_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_unary_expression);
		try {
			setState(658);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(634);
				primary_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(635);
				match(PLUS);
				setState(636);
				unary_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(637);
				match(MINUS);
				setState(638);
				unary_expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(639);
				match(BANG);
				setState(640);
				unary_expression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(641);
				match(TILDE);
				setState(642);
				unary_expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(643);
				match(OP_INC);
				setState(644);
				unary_expression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(645);
				match(OP_DEC);
				setState(646);
				unary_expression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(647);
				match(OPEN_PARENS);
				setState(648);
				type_();
				setState(649);
				match(CLOSE_PARENS);
				setState(650);
				unary_expression();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(652);
				match(AWAIT);
				setState(653);
				unary_expression();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(654);
				match(AMP);
				setState(655);
				unary_expression();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(656);
				match(STAR);
				setState(657);
				unary_expression();
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

	public static class Primary_expressionContext extends ParserRuleContext {
		public Primary_expression_startContext pe;
		public Primary_expression_startContext primary_expression_start() {
			return getRuleContext(Primary_expression_startContext.class,0);
		}
		public List<Bracket_expressionContext> bracket_expression() {
			return getRuleContexts(Bracket_expressionContext.class);
		}
		public Bracket_expressionContext bracket_expression(int i) {
			return getRuleContext(Bracket_expressionContext.class,i);
		}
		public List<Member_accessContext> member_access() {
			return getRuleContexts(Member_accessContext.class);
		}
		public Member_accessContext member_access(int i) {
			return getRuleContext(Member_accessContext.class,i);
		}
		public List<Method_invocationContext> method_invocation() {
			return getRuleContexts(Method_invocationContext.class);
		}
		public Method_invocationContext method_invocation(int i) {
			return getRuleContext(Method_invocationContext.class,i);
		}
		public List<TerminalNode> OP_INC() { return getTokens(CSharpParser.OP_INC); }
		public TerminalNode OP_INC(int i) {
			return getToken(CSharpParser.OP_INC, i);
		}
		public List<TerminalNode> OP_DEC() { return getTokens(CSharpParser.OP_DEC); }
		public TerminalNode OP_DEC(int i) {
			return getToken(CSharpParser.OP_DEC, i);
		}
		public List<TerminalNode> OP_PTR() { return getTokens(CSharpParser.OP_PTR); }
		public TerminalNode OP_PTR(int i) {
			return getToken(CSharpParser.OP_PTR, i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public Primary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPrimary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPrimary_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitPrimary_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primary_expressionContext primary_expression() throws RecognitionException {
		Primary_expressionContext _localctx = new Primary_expressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_primary_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			((Primary_expressionContext)_localctx).pe = primary_expression_start();
			setState(664);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(661);
					bracket_expression();
					}
					} 
				}
				setState(666);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(683);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(673);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case DOT:
					case INTERR:
						{
						setState(667);
						member_access();
						}
						break;
					case OPEN_PARENS:
						{
						setState(668);
						method_invocation();
						}
						break;
					case OP_INC:
						{
						setState(669);
						match(OP_INC);
						}
						break;
					case OP_DEC:
						{
						setState(670);
						match(OP_DEC);
						}
						break;
					case OP_PTR:
						{
						setState(671);
						match(OP_PTR);
						setState(672);
						identifier();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(678);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(675);
							bracket_expression();
							}
							} 
						}
						setState(680);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					}
					}
					} 
				}
				setState(685);
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

	public static class Primary_expression_startContext extends ParserRuleContext {
		public Primary_expression_startContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression_start; }
	 
		public Primary_expression_startContext() { }
		public void copyFrom(Primary_expression_startContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LiteralAccessExpressionContext extends Primary_expression_startContext {
		public TerminalNode LITERAL_ACCESS() { return getToken(CSharpParser.LITERAL_ACCESS, 0); }
		public LiteralAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteralAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteralAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLiteralAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefaultValueExpressionContext extends Primary_expression_startContext {
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public DefaultValueExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDefaultValueExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDefaultValueExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitDefaultValueExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseAccessExpressionContext extends Primary_expression_startContext {
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public BaseAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBaseAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBaseAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBaseAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SizeofExpressionContext extends Primary_expression_startContext {
		public TerminalNode SIZEOF() { return getToken(CSharpParser.SIZEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public SizeofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSizeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSizeofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSizeofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisExpressionsContext extends Primary_expression_startContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public ParenthesisExpressionsContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParenthesisExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParenthesisExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitParenthesisExpressions(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisReferenceExpressionContext extends Primary_expression_startContext {
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public ThisReferenceExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThisReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThisReferenceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitThisReferenceExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectCreationExpressionContext extends Primary_expression_startContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Anonymous_object_initializerContext anonymous_object_initializer() {
			return getRuleContext(Anonymous_object_initializerContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Object_creation_expressionContext object_creation_expression() {
			return getRuleContext(Object_creation_expressionContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public ObjectCreationExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObjectCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObjectCreationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitObjectCreationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AnonymousMethodExpressionContext extends Primary_expression_startContext {
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() {
			return getRuleContext(Explicit_anonymous_function_parameter_listContext.class,0);
		}
		public AnonymousMethodExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymousMethodExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymousMethodExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAnonymousMethodExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeofExpressionContext extends Primary_expression_startContext {
		public TerminalNode TYPEOF() { return getToken(CSharpParser.TYPEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Unbound_type_nameContext unbound_type_name() {
			return getRuleContext(Unbound_type_nameContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TypeofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTypeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTypeofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitTypeofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UncheckedExpressionContext extends Primary_expression_startContext {
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public UncheckedExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUncheckedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUncheckedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUncheckedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleNameExpressionContext extends Primary_expression_startContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public SimpleNameExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSimpleNameExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSimpleNameExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSimpleNameExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberAccessExpressionContext extends Primary_expression_startContext {
		public Predefined_typeContext predefined_type() {
			return getRuleContext(Predefined_typeContext.class,0);
		}
		public Qualified_alias_memberContext qualified_alias_member() {
			return getRuleContext(Qualified_alias_memberContext.class,0);
		}
		public MemberAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMemberAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMemberAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMemberAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckedExpressionContext extends Primary_expression_startContext {
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public CheckedExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCheckedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCheckedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCheckedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExpressionContext extends Primary_expression_startContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameofExpressionContext extends Primary_expression_startContext {
		public TerminalNode NAMEOF() { return getToken(CSharpParser.NAMEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public NameofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNameofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNameofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNameofExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primary_expression_startContext primary_expression_start() throws RecognitionException {
		Primary_expression_startContext _localctx = new Primary_expression_startContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_primary_expression_start);
		int _la;
		try {
			int _alt;
			setState(795);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				_localctx = new LiteralExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(686);
				literal();
				}
				break;
			case 2:
				_localctx = new SimpleNameExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(687);
				identifier();
				setState(689);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(688);
					type_argument_list();
					}
					break;
				}
				}
				break;
			case 3:
				_localctx = new ParenthesisExpressionsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(691);
				match(OPEN_PARENS);
				setState(692);
				expression();
				setState(693);
				match(CLOSE_PARENS);
				}
				break;
			case 4:
				_localctx = new MemberAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(695);
				predefined_type();
				}
				break;
			case 5:
				_localctx = new MemberAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(696);
				qualified_alias_member();
				}
				break;
			case 6:
				_localctx = new LiteralAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(697);
				match(LITERAL_ACCESS);
				}
				break;
			case 7:
				_localctx = new ThisReferenceExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(698);
				match(THIS);
				}
				break;
			case 8:
				_localctx = new BaseAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(699);
				match(BASE);
				setState(709);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(700);
					match(DOT);
					setState(701);
					identifier();
					setState(703);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
					case 1:
						{
						setState(702);
						type_argument_list();
						}
						break;
					}
					}
					break;
				case OPEN_BRACKET:
					{
					setState(705);
					match(OPEN_BRACKET);
					setState(706);
					expression_list();
					setState(707);
					match(CLOSE_BRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 9:
				_localctx = new ObjectCreationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(711);
				match(NEW);
				setState(740);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BOOL:
				case BY:
				case BYTE:
				case CHAR:
				case DECIMAL:
				case DESCENDING:
				case DOUBLE:
				case DYNAMIC:
				case EQUALS:
				case FLOAT:
				case FROM:
				case GET:
				case GROUP:
				case INT:
				case INTO:
				case JOIN:
				case LET:
				case LONG:
				case NAMEOF:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SBYTE:
				case SELECT:
				case SET:
				case SHORT:
				case STRING:
				case UINT:
				case ULONG:
				case USHORT:
				case VAR:
				case VOID:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(712);
					type_();
					setState(734);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
					case 1:
						{
						setState(713);
						object_creation_expression();
						}
						break;
					case 2:
						{
						setState(714);
						object_or_collection_initializer();
						}
						break;
					case 3:
						{
						setState(715);
						match(OPEN_BRACKET);
						setState(716);
						expression_list();
						setState(717);
						match(CLOSE_BRACKET);
						setState(721);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(718);
								rank_specifier();
								}
								} 
							}
							setState(723);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
						}
						setState(725);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==OPEN_BRACE) {
							{
							setState(724);
							array_initializer();
							}
						}

						}
						break;
					case 4:
						{
						setState(728); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(727);
							rank_specifier();
							}
							}
							setState(730); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==OPEN_BRACKET );
						setState(732);
						array_initializer();
						}
						break;
					}
					}
					break;
				case OPEN_BRACE:
					{
					setState(736);
					anonymous_object_initializer();
					}
					break;
				case OPEN_BRACKET:
					{
					setState(737);
					rank_specifier();
					setState(738);
					array_initializer();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 10:
				_localctx = new TypeofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(742);
				match(TYPEOF);
				setState(743);
				match(OPEN_PARENS);
				setState(747);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(744);
					unbound_type_name();
					}
					break;
				case 2:
					{
					setState(745);
					type_();
					}
					break;
				case 3:
					{
					setState(746);
					match(VOID);
					}
					break;
				}
				setState(749);
				match(CLOSE_PARENS);
				}
				break;
			case 11:
				_localctx = new CheckedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(750);
				match(CHECKED);
				setState(751);
				match(OPEN_PARENS);
				setState(752);
				expression();
				setState(753);
				match(CLOSE_PARENS);
				}
				break;
			case 12:
				_localctx = new UncheckedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(755);
				match(UNCHECKED);
				setState(756);
				match(OPEN_PARENS);
				setState(757);
				expression();
				setState(758);
				match(CLOSE_PARENS);
				}
				break;
			case 13:
				_localctx = new DefaultValueExpressionContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(760);
				match(DEFAULT);
				setState(761);
				match(OPEN_PARENS);
				setState(762);
				type_();
				setState(763);
				match(CLOSE_PARENS);
				}
				break;
			case 14:
				_localctx = new AnonymousMethodExpressionContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(766);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASYNC) {
					{
					setState(765);
					match(ASYNC);
					}
				}

				setState(768);
				match(DELEGATE);
				setState(774);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENS) {
					{
					setState(769);
					match(OPEN_PARENS);
					setState(771);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
						{
						setState(770);
						explicit_anonymous_function_parameter_list();
						}
					}

					setState(773);
					match(CLOSE_PARENS);
					}
				}

				setState(776);
				block();
				}
				break;
			case 15:
				_localctx = new SizeofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(777);
				match(SIZEOF);
				setState(778);
				match(OPEN_PARENS);
				setState(779);
				type_();
				setState(780);
				match(CLOSE_PARENS);
				}
				break;
			case 16:
				_localctx = new NameofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(782);
				match(NAMEOF);
				setState(783);
				match(OPEN_PARENS);
				setState(789);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(784);
						identifier();
						setState(785);
						match(DOT);
						}
						} 
					}
					setState(791);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				}
				setState(792);
				identifier();
				setState(793);
				match(CLOSE_PARENS);
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

	public static class Member_accessContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public Member_accessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_access(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_access(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_access(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_accessContext member_access() throws RecognitionException {
		Member_accessContext _localctx = new Member_accessContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_member_access);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(798);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTERR) {
				{
				setState(797);
				match(INTERR);
				}
			}

			setState(800);
			match(DOT);
			setState(801);
			identifier();
			setState(803);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(802);
				type_argument_list();
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

	public static class Bracket_expressionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public List<Indexer_argumentContext> indexer_argument() {
			return getRuleContexts(Indexer_argumentContext.class);
		}
		public Indexer_argumentContext indexer_argument(int i) {
			return getRuleContext(Indexer_argumentContext.class,i);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Bracket_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBracket_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBracket_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBracket_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bracket_expressionContext bracket_expression() throws RecognitionException {
		Bracket_expressionContext _localctx = new Bracket_expressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_bracket_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTERR) {
				{
				setState(805);
				match(INTERR);
				}
			}

			setState(808);
			match(OPEN_BRACKET);
			setState(809);
			indexer_argument();
			setState(814);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(810);
				match(COMMA);
				setState(811);
				indexer_argument();
				}
				}
				setState(816);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(817);
			match(CLOSE_BRACKET);
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

	public static class Indexer_argumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Indexer_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexer_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIndexer_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIndexer_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIndexer_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Indexer_argumentContext indexer_argument() throws RecognitionException {
		Indexer_argumentContext _localctx = new Indexer_argumentContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_indexer_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(822);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(819);
				identifier();
				setState(820);
				match(COLON);
				}
				break;
			}
			setState(824);
			expression();
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

	public static class Predefined_typeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public Predefined_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefined_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPredefined_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPredefined_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitPredefined_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predefined_typeContext predefined_type() throws RecognitionException {
		Predefined_typeContext _localctx = new Predefined_typeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_predefined_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(826);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (SBYTE - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)))) != 0)) ) {
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

	public static class Expression_listContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpression_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExpression_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(828);
			expression();
			setState(833);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(829);
				match(COMMA);
				setState(830);
				expression();
				}
				}
				setState(835);
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

	public static class Object_or_collection_initializerContext extends ParserRuleContext {
		public Object_initializerContext object_initializer() {
			return getRuleContext(Object_initializerContext.class,0);
		}
		public Collection_initializerContext collection_initializer() {
			return getRuleContext(Collection_initializerContext.class,0);
		}
		public Object_or_collection_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_or_collection_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_or_collection_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_or_collection_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitObject_or_collection_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Object_or_collection_initializerContext object_or_collection_initializer() throws RecognitionException {
		Object_or_collection_initializerContext _localctx = new Object_or_collection_initializerContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_object_or_collection_initializer);
		try {
			setState(838);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(836);
				object_initializer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(837);
				collection_initializer();
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

	public static class Object_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Member_initializer_listContext member_initializer_list() {
			return getRuleContext(Member_initializer_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Object_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitObject_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Object_initializerContext object_initializer() throws RecognitionException {
		Object_initializerContext _localctx = new Object_initializerContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_object_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(840);
			match(OPEN_BRACE);
			setState(845);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << NAMEOF))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ON - 68)) | (1L << (ORDERBY - 68)) | (1L << (PARTIAL - 68)) | (1L << (REMOVE - 68)) | (1L << (SELECT - 68)) | (1L << (SET - 68)) | (1L << (VAR - 68)) | (1L << (WHEN - 68)) | (1L << (WHERE - 68)) | (1L << (YIELD - 68)) | (1L << (IDENTIFIER - 68)) | (1L << (OPEN_BRACKET - 68)))) != 0)) {
				{
				setState(841);
				member_initializer_list();
				setState(843);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(842);
					match(COMMA);
					}
				}

				}
			}

			setState(847);
			match(CLOSE_BRACE);
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

	public static class Member_initializer_listContext extends ParserRuleContext {
		public List<Member_initializerContext> member_initializer() {
			return getRuleContexts(Member_initializerContext.class);
		}
		public Member_initializerContext member_initializer(int i) {
			return getRuleContext(Member_initializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Member_initializer_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_initializer_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_initializer_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_initializer_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_initializer_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_initializer_listContext member_initializer_list() throws RecognitionException {
		Member_initializer_listContext _localctx = new Member_initializer_listContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_member_initializer_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(849);
			member_initializer();
			setState(854);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(850);
					match(COMMA);
					setState(851);
					member_initializer();
					}
					} 
				}
				setState(856);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
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

	public static class Member_initializerContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Initializer_valueContext initializer_value() {
			return getRuleContext(Initializer_valueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Member_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_initializerContext member_initializer() throws RecognitionException {
		Member_initializerContext _localctx = new Member_initializerContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_member_initializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				{
				setState(857);
				identifier();
				}
				break;
			case OPEN_BRACKET:
				{
				setState(858);
				match(OPEN_BRACKET);
				setState(859);
				expression();
				setState(860);
				match(CLOSE_BRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(864);
			match(ASSIGNMENT);
			setState(865);
			initializer_value();
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

	public static class Initializer_valueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public Initializer_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInitializer_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInitializer_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInitializer_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Initializer_valueContext initializer_value() throws RecognitionException {
		Initializer_valueContext _localctx = new Initializer_valueContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_initializer_value);
		try {
			setState(869);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(867);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(868);
				object_or_collection_initializer();
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

	public static class Collection_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public List<Element_initializerContext> element_initializer() {
			return getRuleContexts(Element_initializerContext.class);
		}
		public Element_initializerContext element_initializer(int i) {
			return getRuleContext(Element_initializerContext.class,i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Collection_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCollection_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCollection_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCollection_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Collection_initializerContext collection_initializer() throws RecognitionException {
		Collection_initializerContext _localctx = new Collection_initializerContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_collection_initializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(871);
			match(OPEN_BRACE);
			setState(872);
			element_initializer();
			setState(877);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(873);
					match(COMMA);
					setState(874);
					element_initializer();
					}
					} 
				}
				setState(879);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			}
			setState(881);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(880);
				match(COMMA);
				}
			}

			setState(883);
			match(CLOSE_BRACE);
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

	public static class Element_initializerContext extends ParserRuleContext {
		public Non_assignment_expressionContext non_assignment_expression() {
			return getRuleContext(Non_assignment_expressionContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Element_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterElement_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitElement_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitElement_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Element_initializerContext element_initializer() throws RecognitionException {
		Element_initializerContext _localctx = new Element_initializerContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_element_initializer);
		try {
			setState(890);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(885);
				non_assignment_expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(886);
				match(OPEN_BRACE);
				setState(887);
				expression_list();
				setState(888);
				match(CLOSE_BRACE);
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

	public static class Anonymous_object_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Member_declarator_listContext member_declarator_list() {
			return getRuleContext(Member_declarator_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Anonymous_object_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_object_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_object_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_object_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAnonymous_object_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Anonymous_object_initializerContext anonymous_object_initializer() throws RecognitionException {
		Anonymous_object_initializerContext _localctx = new Anonymous_object_initializerContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_anonymous_object_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(892);
			match(OPEN_BRACE);
			setState(897);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0)) {
				{
				setState(893);
				member_declarator_list();
				setState(895);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(894);
					match(COMMA);
					}
				}

				}
			}

			setState(899);
			match(CLOSE_BRACE);
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

	public static class Member_declarator_listContext extends ParserRuleContext {
		public List<Member_declaratorContext> member_declarator() {
			return getRuleContexts(Member_declaratorContext.class);
		}
		public Member_declaratorContext member_declarator(int i) {
			return getRuleContext(Member_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Member_declarator_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_declarator_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_declarator_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_declarator_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_declarator_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_declarator_listContext member_declarator_list() throws RecognitionException {
		Member_declarator_listContext _localctx = new Member_declarator_listContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_member_declarator_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(901);
			member_declarator();
			setState(906);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(902);
					match(COMMA);
					setState(903);
					member_declarator();
					}
					} 
				}
				setState(908);
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

	public static class Member_declaratorContext extends ParserRuleContext {
		public Primary_expressionContext primary_expression() {
			return getRuleContext(Primary_expressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Member_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_declaratorContext member_declarator() throws RecognitionException {
		Member_declaratorContext _localctx = new Member_declaratorContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_member_declarator);
		try {
			setState(914);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(909);
				primary_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(910);
				identifier();
				setState(911);
				match(ASSIGNMENT);
				setState(912);
				expression();
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

	public static class Unbound_type_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Generic_dimension_specifierContext> generic_dimension_specifier() {
			return getRuleContexts(Generic_dimension_specifierContext.class);
		}
		public Generic_dimension_specifierContext generic_dimension_specifier(int i) {
			return getRuleContext(Generic_dimension_specifierContext.class,i);
		}
		public Unbound_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unbound_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnbound_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnbound_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUnbound_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unbound_type_nameContext unbound_type_name() throws RecognitionException {
		Unbound_type_nameContext _localctx = new Unbound_type_nameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_unbound_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(916);
			identifier();
			setState(925);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLOSE_PARENS:
			case DOT:
			case LT:
				{
				setState(918);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(917);
					generic_dimension_specifier();
					}
				}

				}
				break;
			case DOUBLE_COLON:
				{
				setState(920);
				match(DOUBLE_COLON);
				setState(921);
				identifier();
				setState(923);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(922);
					generic_dimension_specifier();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(934);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(927);
				match(DOT);
				setState(928);
				identifier();
				setState(930);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(929);
					generic_dimension_specifier();
					}
				}

				}
				}
				setState(936);
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

	public static class Generic_dimension_specifierContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Generic_dimension_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_dimension_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGeneric_dimension_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGeneric_dimension_specifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGeneric_dimension_specifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Generic_dimension_specifierContext generic_dimension_specifier() throws RecognitionException {
		Generic_dimension_specifierContext _localctx = new Generic_dimension_specifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_generic_dimension_specifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937);
			match(LT);
			setState(941);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(938);
				match(COMMA);
				}
				}
				setState(943);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(944);
			match(GT);
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

	public static class IsTypeContext extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public IsTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIsType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIsType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIsType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IsTypeContext isType() throws RecognitionException {
		IsTypeContext _localctx = new IsTypeContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_isType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(946);
			base_type();
			setState(951);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(949);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPEN_BRACKET:
						{
						setState(947);
						rank_specifier();
						}
						break;
					case STAR:
						{
						setState(948);
						match(STAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(953);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(955);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				{
				setState(954);
				match(INTERR);
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

	public static class Lambda_expressionContext extends ParserRuleContext {
		public Anonymous_function_signatureContext anonymous_function_signature() {
			return getRuleContext(Anonymous_function_signatureContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Anonymous_function_bodyContext anonymous_function_body() {
			return getRuleContext(Anonymous_function_bodyContext.class,0);
		}
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public Lambda_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLambda_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLambda_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLambda_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lambda_expressionContext lambda_expression() throws RecognitionException {
		Lambda_expressionContext _localctx = new Lambda_expressionContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_lambda_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(958);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(957);
				match(ASYNC);
				}
				break;
			}
			setState(960);
			anonymous_function_signature();
			setState(961);
			right_arrow();
			setState(962);
			anonymous_function_body();
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

	public static class Anonymous_function_signatureContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() {
			return getRuleContext(Explicit_anonymous_function_parameter_listContext.class,0);
		}
		public Implicit_anonymous_function_parameter_listContext implicit_anonymous_function_parameter_list() {
			return getRuleContext(Implicit_anonymous_function_parameter_listContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Anonymous_function_signatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_function_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_function_signature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_function_signature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAnonymous_function_signature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Anonymous_function_signatureContext anonymous_function_signature() throws RecognitionException {
		Anonymous_function_signatureContext _localctx = new Anonymous_function_signatureContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_anonymous_function_signature);
		try {
			setState(975);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(964);
				match(OPEN_PARENS);
				setState(965);
				match(CLOSE_PARENS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(966);
				match(OPEN_PARENS);
				setState(967);
				explicit_anonymous_function_parameter_list();
				setState(968);
				match(CLOSE_PARENS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(970);
				match(OPEN_PARENS);
				setState(971);
				implicit_anonymous_function_parameter_list();
				setState(972);
				match(CLOSE_PARENS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(974);
				identifier();
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

	public static class Explicit_anonymous_function_parameter_listContext extends ParserRuleContext {
		public List<Explicit_anonymous_function_parameterContext> explicit_anonymous_function_parameter() {
			return getRuleContexts(Explicit_anonymous_function_parameterContext.class);
		}
		public Explicit_anonymous_function_parameterContext explicit_anonymous_function_parameter(int i) {
			return getRuleContext(Explicit_anonymous_function_parameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Explicit_anonymous_function_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicit_anonymous_function_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExplicit_anonymous_function_parameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() throws RecognitionException {
		Explicit_anonymous_function_parameter_listContext _localctx = new Explicit_anonymous_function_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_explicit_anonymous_function_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(977);
			explicit_anonymous_function_parameter();
			setState(982);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(978);
				match(COMMA);
				setState(979);
				explicit_anonymous_function_parameter();
				}
				}
				setState(984);
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

	public static class Explicit_anonymous_function_parameterContext extends ParserRuleContext {
		public Token refout;
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public Explicit_anonymous_function_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicit_anonymous_function_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExplicit_anonymous_function_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExplicit_anonymous_function_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExplicit_anonymous_function_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Explicit_anonymous_function_parameterContext explicit_anonymous_function_parameter() throws RecognitionException {
		Explicit_anonymous_function_parameterContext _localctx = new Explicit_anonymous_function_parameterContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_explicit_anonymous_function_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(986);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OUT || _la==REF) {
				{
				setState(985);
				((Explicit_anonymous_function_parameterContext)_localctx).refout = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==OUT || _la==REF) ) {
					((Explicit_anonymous_function_parameterContext)_localctx).refout = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(988);
			type_();
			setState(989);
			identifier();
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

	public static class Implicit_anonymous_function_parameter_listContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Implicit_anonymous_function_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicit_anonymous_function_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterImplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitImplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitImplicit_anonymous_function_parameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Implicit_anonymous_function_parameter_listContext implicit_anonymous_function_parameter_list() throws RecognitionException {
		Implicit_anonymous_function_parameter_listContext _localctx = new Implicit_anonymous_function_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_implicit_anonymous_function_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(991);
			identifier();
			setState(996);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(992);
				match(COMMA);
				setState(993);
				identifier();
				}
				}
				setState(998);
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

	public static class Anonymous_function_bodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Anonymous_function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_function_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_function_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAnonymous_function_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Anonymous_function_bodyContext anonymous_function_body() throws RecognitionException {
		Anonymous_function_bodyContext _localctx = new Anonymous_function_bodyContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_anonymous_function_body);
		try {
			setState(1001);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(999);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1000);
				block();
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

	public static class Query_expressionContext extends ParserRuleContext {
		public From_clauseContext from_clause() {
			return getRuleContext(From_clauseContext.class,0);
		}
		public Query_bodyContext query_body() {
			return getRuleContext(Query_bodyContext.class,0);
		}
		public Query_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQuery_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Query_expressionContext query_expression() throws RecognitionException {
		Query_expressionContext _localctx = new Query_expressionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_query_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003);
			from_clause();
			setState(1004);
			query_body();
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

	public static class From_clauseContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(CSharpParser.FROM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public From_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFrom_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFrom_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFrom_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final From_clauseContext from_clause() throws RecognitionException {
		From_clauseContext _localctx = new From_clauseContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_from_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1006);
			match(FROM);
			setState(1008);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(1007);
				type_();
				}
				break;
			}
			setState(1010);
			identifier();
			setState(1011);
			match(IN);
			setState(1012);
			expression();
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

	public static class Query_bodyContext extends ParserRuleContext {
		public Select_or_group_clauseContext select_or_group_clause() {
			return getRuleContext(Select_or_group_clauseContext.class,0);
		}
		public List<Query_body_clauseContext> query_body_clause() {
			return getRuleContexts(Query_body_clauseContext.class);
		}
		public Query_body_clauseContext query_body_clause(int i) {
			return getRuleContext(Query_body_clauseContext.class,i);
		}
		public Query_continuationContext query_continuation() {
			return getRuleContext(Query_continuationContext.class,0);
		}
		public Query_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQuery_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Query_bodyContext query_body() throws RecognitionException {
		Query_bodyContext _localctx = new Query_bodyContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_query_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1017);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (FROM - 47)) | (1L << (JOIN - 47)) | (1L << (LET - 47)) | (1L << (ORDERBY - 47)) | (1L << (WHERE - 47)))) != 0)) {
				{
				{
				setState(1014);
				query_body_clause();
				}
				}
				setState(1019);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1020);
			select_or_group_clause();
			setState(1022);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(1021);
				query_continuation();
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

	public static class Query_body_clauseContext extends ParserRuleContext {
		public From_clauseContext from_clause() {
			return getRuleContext(From_clauseContext.class,0);
		}
		public Let_clauseContext let_clause() {
			return getRuleContext(Let_clauseContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Combined_join_clauseContext combined_join_clause() {
			return getRuleContext(Combined_join_clauseContext.class,0);
		}
		public Orderby_clauseContext orderby_clause() {
			return getRuleContext(Orderby_clauseContext.class,0);
		}
		public Query_body_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_body_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_body_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_body_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQuery_body_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Query_body_clauseContext query_body_clause() throws RecognitionException {
		Query_body_clauseContext _localctx = new Query_body_clauseContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_query_body_clause);
		try {
			setState(1029);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1024);
				from_clause();
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 2);
				{
				setState(1025);
				let_clause();
				}
				break;
			case WHERE:
				enterOuterAlt(_localctx, 3);
				{
				setState(1026);
				where_clause();
				}
				break;
			case JOIN:
				enterOuterAlt(_localctx, 4);
				{
				setState(1027);
				combined_join_clause();
				}
				break;
			case ORDERBY:
				enterOuterAlt(_localctx, 5);
				{
				setState(1028);
				orderby_clause();
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

	public static class Let_clauseContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(CSharpParser.LET, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Let_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLet_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLet_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLet_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_clauseContext let_clause() throws RecognitionException {
		Let_clauseContext _localctx = new Let_clauseContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_let_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1031);
			match(LET);
			setState(1032);
			identifier();
			setState(1033);
			match(ASSIGNMENT);
			setState(1034);
			expression();
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

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitWhere_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitWhere_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_where_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1036);
			match(WHERE);
			setState(1037);
			expression();
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

	public static class Combined_join_clauseContext extends ParserRuleContext {
		public TerminalNode JOIN() { return getToken(CSharpParser.JOIN, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ON() { return getToken(CSharpParser.ON, 0); }
		public TerminalNode EQUALS() { return getToken(CSharpParser.EQUALS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public Combined_join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combined_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCombined_join_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCombined_join_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCombined_join_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Combined_join_clauseContext combined_join_clause() throws RecognitionException {
		Combined_join_clauseContext _localctx = new Combined_join_clauseContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_combined_join_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1039);
			match(JOIN);
			setState(1041);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				{
				setState(1040);
				type_();
				}
				break;
			}
			setState(1043);
			identifier();
			setState(1044);
			match(IN);
			setState(1045);
			expression();
			setState(1046);
			match(ON);
			setState(1047);
			expression();
			setState(1048);
			match(EQUALS);
			setState(1049);
			expression();
			setState(1052);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(1050);
				match(INTO);
				setState(1051);
				identifier();
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

	public static class Orderby_clauseContext extends ParserRuleContext {
		public TerminalNode ORDERBY() { return getToken(CSharpParser.ORDERBY, 0); }
		public List<OrderingContext> ordering() {
			return getRuleContexts(OrderingContext.class);
		}
		public OrderingContext ordering(int i) {
			return getRuleContext(OrderingContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Orderby_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderby_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOrderby_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOrderby_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitOrderby_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Orderby_clauseContext orderby_clause() throws RecognitionException {
		Orderby_clauseContext _localctx = new Orderby_clauseContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_orderby_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1054);
			match(ORDERBY);
			setState(1055);
			ordering();
			setState(1060);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1056);
				match(COMMA);
				setState(1057);
				ordering();
				}
				}
				setState(1062);
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

	public static class OrderingContext extends ParserRuleContext {
		public Token dir;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ASCENDING() { return getToken(CSharpParser.ASCENDING, 0); }
		public TerminalNode DESCENDING() { return getToken(CSharpParser.DESCENDING, 0); }
		public OrderingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOrdering(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOrdering(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitOrdering(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderingContext ordering() throws RecognitionException {
		OrderingContext _localctx = new OrderingContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_ordering);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1063);
			expression();
			setState(1065);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASCENDING || _la==DESCENDING) {
				{
				setState(1064);
				((OrderingContext)_localctx).dir = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ASCENDING || _la==DESCENDING) ) {
					((OrderingContext)_localctx).dir = (Token)_errHandler.recoverInline(this);
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

	public static class Select_or_group_clauseContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(CSharpParser.SELECT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GROUP() { return getToken(CSharpParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(CSharpParser.BY, 0); }
		public Select_or_group_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_or_group_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSelect_or_group_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSelect_or_group_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSelect_or_group_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_or_group_clauseContext select_or_group_clause() throws RecognitionException {
		Select_or_group_clauseContext _localctx = new Select_or_group_clauseContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_select_or_group_clause);
		try {
			setState(1074);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1067);
				match(SELECT);
				setState(1068);
				expression();
				}
				break;
			case GROUP:
				enterOuterAlt(_localctx, 2);
				{
				setState(1069);
				match(GROUP);
				setState(1070);
				expression();
				setState(1071);
				match(BY);
				setState(1072);
				expression();
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

	public static class Query_continuationContext extends ParserRuleContext {
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Query_bodyContext query_body() {
			return getRuleContext(Query_bodyContext.class,0);
		}
		public Query_continuationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_continuation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_continuation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_continuation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQuery_continuation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Query_continuationContext query_continuation() throws RecognitionException {
		Query_continuationContext _localctx = new Query_continuationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_query_continuation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1076);
			match(INTO);
			setState(1077);
			identifier();
			setState(1078);
			query_body();
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
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DeclarationStatementContext extends StatementContext {
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public Local_constant_declarationContext local_constant_declaration() {
			return getRuleContext(Local_constant_declarationContext.class,0);
		}
		public DeclarationStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmbeddedStatementContext extends StatementContext {
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public EmbeddedStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEmbeddedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEmbeddedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEmbeddedStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LabeledStatementContext extends StatementContext {
		public Labeled_StatementContext labeled_Statement() {
			return getRuleContext(Labeled_StatementContext.class,0);
		}
		public LabeledStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLabeledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLabeledStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLabeledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_statement);
		try {
			setState(1088);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				_localctx = new LabeledStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1080);
				labeled_Statement();
				}
				break;
			case 2:
				_localctx = new DeclarationStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1083);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BOOL:
				case BY:
				case BYTE:
				case CHAR:
				case DECIMAL:
				case DESCENDING:
				case DOUBLE:
				case DYNAMIC:
				case EQUALS:
				case FLOAT:
				case FROM:
				case GET:
				case GROUP:
				case INT:
				case INTO:
				case JOIN:
				case LET:
				case LONG:
				case NAMEOF:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SBYTE:
				case SELECT:
				case SET:
				case SHORT:
				case STRING:
				case UINT:
				case ULONG:
				case USHORT:
				case VAR:
				case VOID:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(1081);
					local_variable_declaration();
					}
					break;
				case CONST:
					{
					setState(1082);
					local_constant_declaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1085);
				match(SEMICOLON);
				}
				break;
			case 3:
				_localctx = new EmbeddedStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1087);
				embedded_statement();
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

	public static class Labeled_StatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Labeled_StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeled_Statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLabeled_Statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLabeled_Statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLabeled_Statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Labeled_StatementContext labeled_Statement() throws RecognitionException {
		Labeled_StatementContext _localctx = new Labeled_StatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_labeled_Statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1090);
			identifier();
			setState(1091);
			match(COLON);
			setState(1092);
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

	public static class Embedded_statementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Simple_embedded_statementContext simple_embedded_statement() {
			return getRuleContext(Simple_embedded_statementContext.class,0);
		}
		public Embedded_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_embedded_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEmbedded_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEmbedded_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEmbedded_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Embedded_statementContext embedded_statement() throws RecognitionException {
		Embedded_statementContext _localctx = new Embedded_statementContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_embedded_statement);
		try {
			setState(1096);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1094);
				block();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BREAK:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DO:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case FROM:
			case GET:
			case GOTO:
			case GROUP:
			case IF:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LOCK:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case RETURN:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VAR:
			case WHEN:
			case WHERE:
			case WHILE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case SEMICOLON:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1095);
				simple_embedded_statement();
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

	public static class Simple_embedded_statementContext extends ParserRuleContext {
		public Simple_embedded_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_embedded_statement; }
	 
		public Simple_embedded_statementContext() { }
		public void copyFrom(Simple_embedded_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TryStatementContext extends Simple_embedded_statementContext {
		public TerminalNode TRY() { return getToken(CSharpParser.TRY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Catch_clausesContext catch_clauses() {
			return getRuleContext(Catch_clausesContext.class,0);
		}
		public Finally_clauseContext finally_clause() {
			return getRuleContext(Finally_clauseContext.class,0);
		}
		public TryStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTryStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitTryStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CheckedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCheckedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCheckedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCheckedStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThrowStatementContext extends Simple_embedded_statementContext {
		public TerminalNode THROW() { return getToken(CSharpParser.THROW, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TheEmptyStatementContext extends Simple_embedded_statementContext {
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TheEmptyStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTheEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTheEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitTheEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnsafeStatementContext extends Simple_embedded_statementContext {
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public UnsafeStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnsafeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnsafeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUnsafeStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FOR() { return getToken(CSharpParser.FOR, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CSharpParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CSharpParser.SEMICOLON, i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public For_initializerContext for_initializer() {
			return getRuleContext(For_initializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public For_iteratorContext for_iterator() {
			return getRuleContext(For_iteratorContext.class,0);
		}
		public ForStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStatementContext extends Simple_embedded_statementContext {
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public BreakStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatementContext extends Simple_embedded_statementContext {
		public TerminalNode IF() { return getToken(CSharpParser.IF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<If_bodyContext> if_body() {
			return getRuleContexts(If_bodyContext.class);
		}
		public If_bodyContext if_body(int i) {
			return getRuleContext(If_bodyContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CSharpParser.ELSE, 0); }
		public IfStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatementContext extends Simple_embedded_statementContext {
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GotoStatementContext extends Simple_embedded_statementContext {
		public TerminalNode GOTO() { return getToken(CSharpParser.GOTO, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public GotoStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGotoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGotoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGotoStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SwitchStatementContext extends Simple_embedded_statementContext {
		public TerminalNode SWITCH() { return getToken(CSharpParser.SWITCH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Switch_sectionContext> switch_section() {
			return getRuleContexts(Switch_sectionContext.class);
		}
		public Switch_sectionContext switch_section(int i) {
			return getRuleContext(Switch_sectionContext.class,i);
		}
		public SwitchStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FixedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Pointer_typeContext pointer_type() {
			return getRuleContext(Pointer_typeContext.class,0);
		}
		public Fixed_pointer_declaratorsContext fixed_pointer_declarators() {
			return getRuleContext(Fixed_pointer_declaratorsContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public FixedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixedStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatementContext extends Simple_embedded_statementContext {
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public WhileStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoStatementContext extends Simple_embedded_statementContext {
		public TerminalNode DO() { return getToken(CSharpParser.DO, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public DoStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitDoStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForeachStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FOREACH() { return getToken(CSharpParser.FOREACH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Local_variable_typeContext local_variable_type() {
			return getRuleContext(Local_variable_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public ForeachStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitForeachStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitForeachStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UncheckedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public UncheckedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUncheckedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUncheckedStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUncheckedStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionStatementContext extends Simple_embedded_statementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStatementContext extends Simple_embedded_statementContext {
		public TerminalNode CONTINUE() { return getToken(CSharpParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ContinueStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UsingStatementContext extends Simple_embedded_statementContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Resource_acquisitionContext resource_acquisition() {
			return getRuleContext(Resource_acquisitionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public UsingStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUsingStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockStatementContext extends Simple_embedded_statementContext {
		public TerminalNode LOCK() { return getToken(CSharpParser.LOCK, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public LockStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class YieldStatementContext extends Simple_embedded_statementContext {
		public TerminalNode YIELD() { return getToken(CSharpParser.YIELD, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public YieldStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterYieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitYieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitYieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_embedded_statementContext simple_embedded_statement() throws RecognitionException {
		Simple_embedded_statementContext _localctx = new Simple_embedded_statementContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_simple_embedded_statement);
		int _la;
		try {
			setState(1225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				_localctx = new TheEmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1098);
				match(SEMICOLON);
				}
				break;
			case 2:
				_localctx = new ExpressionStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1099);
				expression();
				setState(1100);
				match(SEMICOLON);
				}
				break;
			case 3:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1102);
				match(IF);
				setState(1103);
				match(OPEN_PARENS);
				setState(1104);
				expression();
				setState(1105);
				match(CLOSE_PARENS);
				setState(1106);
				if_body();
				setState(1109);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					{
					setState(1107);
					match(ELSE);
					setState(1108);
					if_body();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new SwitchStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1111);
				match(SWITCH);
				setState(1112);
				match(OPEN_PARENS);
				setState(1113);
				expression();
				setState(1114);
				match(CLOSE_PARENS);
				setState(1115);
				match(OPEN_BRACE);
				setState(1119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1116);
					switch_section();
					}
					}
					setState(1121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1122);
				match(CLOSE_BRACE);
				}
				break;
			case 5:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1124);
				match(WHILE);
				setState(1125);
				match(OPEN_PARENS);
				setState(1126);
				expression();
				setState(1127);
				match(CLOSE_PARENS);
				setState(1128);
				embedded_statement();
				}
				break;
			case 6:
				_localctx = new DoStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1130);
				match(DO);
				setState(1131);
				embedded_statement();
				setState(1132);
				match(WHILE);
				setState(1133);
				match(OPEN_PARENS);
				setState(1134);
				expression();
				setState(1135);
				match(CLOSE_PARENS);
				setState(1136);
				match(SEMICOLON);
				}
				break;
			case 7:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(1138);
				match(FOR);
				setState(1139);
				match(OPEN_PARENS);
				setState(1141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(1140);
					for_initializer();
					}
				}

				setState(1143);
				match(SEMICOLON);
				setState(1145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(1144);
					expression();
					}
				}

				setState(1147);
				match(SEMICOLON);
				setState(1149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(1148);
					for_iterator();
					}
				}

				setState(1151);
				match(CLOSE_PARENS);
				setState(1152);
				embedded_statement();
				}
				break;
			case 8:
				_localctx = new ForeachStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(1153);
				match(FOREACH);
				setState(1154);
				match(OPEN_PARENS);
				setState(1155);
				local_variable_type();
				setState(1156);
				identifier();
				setState(1157);
				match(IN);
				setState(1158);
				expression();
				setState(1159);
				match(CLOSE_PARENS);
				setState(1160);
				embedded_statement();
				}
				break;
			case 9:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(1162);
				match(BREAK);
				setState(1163);
				match(SEMICOLON);
				}
				break;
			case 10:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(1164);
				match(CONTINUE);
				setState(1165);
				match(SEMICOLON);
				}
				break;
			case 11:
				_localctx = new GotoStatementContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(1166);
				match(GOTO);
				setState(1171);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BY:
				case DESCENDING:
				case DYNAMIC:
				case EQUALS:
				case FROM:
				case GET:
				case GROUP:
				case INTO:
				case JOIN:
				case LET:
				case NAMEOF:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SELECT:
				case SET:
				case VAR:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(1167);
					identifier();
					}
					break;
				case CASE:
					{
					setState(1168);
					match(CASE);
					setState(1169);
					expression();
					}
					break;
				case DEFAULT:
					{
					setState(1170);
					match(DEFAULT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1173);
				match(SEMICOLON);
				}
				break;
			case 12:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(1174);
				match(RETURN);
				setState(1176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(1175);
					expression();
					}
				}

				setState(1178);
				match(SEMICOLON);
				}
				break;
			case 13:
				_localctx = new ThrowStatementContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(1179);
				match(THROW);
				setState(1181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(1180);
					expression();
					}
				}

				setState(1183);
				match(SEMICOLON);
				}
				break;
			case 14:
				_localctx = new TryStatementContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(1184);
				match(TRY);
				setState(1185);
				block();
				setState(1191);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1186);
					catch_clauses();
					setState(1188);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1187);
						finally_clause();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1190);
					finally_clause();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 15:
				_localctx = new CheckedStatementContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(1193);
				match(CHECKED);
				setState(1194);
				block();
				}
				break;
			case 16:
				_localctx = new UncheckedStatementContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(1195);
				match(UNCHECKED);
				setState(1196);
				block();
				}
				break;
			case 17:
				_localctx = new LockStatementContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(1197);
				match(LOCK);
				setState(1198);
				match(OPEN_PARENS);
				setState(1199);
				expression();
				setState(1200);
				match(CLOSE_PARENS);
				setState(1201);
				embedded_statement();
				}
				break;
			case 18:
				_localctx = new UsingStatementContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(1203);
				match(USING);
				setState(1204);
				match(OPEN_PARENS);
				setState(1205);
				resource_acquisition();
				setState(1206);
				match(CLOSE_PARENS);
				setState(1207);
				embedded_statement();
				}
				break;
			case 19:
				_localctx = new YieldStatementContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(1209);
				match(YIELD);
				setState(1213);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RETURN:
					{
					setState(1210);
					match(RETURN);
					setState(1211);
					expression();
					}
					break;
				case BREAK:
					{
					setState(1212);
					match(BREAK);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1215);
				match(SEMICOLON);
				}
				break;
			case 20:
				_localctx = new UnsafeStatementContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(1216);
				match(UNSAFE);
				setState(1217);
				block();
				}
				break;
			case 21:
				_localctx = new FixedStatementContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(1218);
				match(FIXED);
				setState(1219);
				match(OPEN_PARENS);
				setState(1220);
				pointer_type();
				setState(1221);
				fixed_pointer_declarators();
				setState(1222);
				match(CLOSE_PARENS);
				setState(1223);
				embedded_statement();
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
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1227);
			match(OPEN_BRACE);
			setState(1229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BREAK) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << CONST) | (1L << CONTINUE) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DO) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FIXED) | (1L << FLOAT) | (1L << FOR) | (1L << FOREACH) | (1L << FROM) | (1L << GET) | (1L << GOTO) | (1L << GROUP) | (1L << IF) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LOCK) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (RETURN - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (SWITCH - 65)) | (1L << (THIS - 65)) | (1L << (THROW - 65)) | (1L << (TRUE - 65)) | (1L << (TRY - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (UNSAFE - 65)) | (1L << (USHORT - 65)) | (1L << (USING - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (WHILE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_BRACE - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 131)) & ~0x3f) == 0 && ((1L << (_la - 131)) & ((1L << (SEMICOLON - 131)) | (1L << (PLUS - 131)) | (1L << (MINUS - 131)) | (1L << (STAR - 131)) | (1L << (AMP - 131)) | (1L << (BANG - 131)) | (1L << (TILDE - 131)) | (1L << (OP_INC - 131)) | (1L << (OP_DEC - 131)))) != 0)) {
				{
				setState(1228);
				statement_list();
				}
			}

			setState(1231);
			match(CLOSE_BRACE);
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

	public static class Local_variable_declarationContext extends ParserRuleContext {
		public Local_variable_typeContext local_variable_type() {
			return getRuleContext(Local_variable_typeContext.class,0);
		}
		public List<Local_variable_declaratorContext> local_variable_declarator() {
			return getRuleContexts(Local_variable_declaratorContext.class);
		}
		public Local_variable_declaratorContext local_variable_declarator(int i) {
			return getRuleContext(Local_variable_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Local_variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_variable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_variable_declarationContext local_variable_declaration() throws RecognitionException {
		Local_variable_declarationContext _localctx = new Local_variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_local_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1233);
			local_variable_type();
			setState(1234);
			local_variable_declarator();
			setState(1239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1235);
				match(COMMA);
				setState(1236);
				local_variable_declarator();
				}
				}
				setState(1241);
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

	public static class Local_variable_typeContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Local_variable_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_variable_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_variable_typeContext local_variable_type() throws RecognitionException {
		Local_variable_typeContext _localctx = new Local_variable_typeContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_local_variable_type);
		try {
			setState(1244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1242);
				match(VAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1243);
				type_();
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

	public static class Local_variable_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Local_variable_initializerContext local_variable_initializer() {
			return getRuleContext(Local_variable_initializerContext.class,0);
		}
		public Local_variable_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_variable_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_variable_declaratorContext local_variable_declarator() throws RecognitionException {
		Local_variable_declaratorContext _localctx = new Local_variable_declaratorContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_local_variable_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1246);
			identifier();
			setState(1249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(1247);
				match(ASSIGNMENT);
				setState(1248);
				local_variable_initializer();
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

	public static class Local_variable_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Local_variable_initializer_unsafeContext local_variable_initializer_unsafe() {
			return getRuleContext(Local_variable_initializer_unsafeContext.class,0);
		}
		public Local_variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_variable_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_variable_initializerContext local_variable_initializer() throws RecognitionException {
		Local_variable_initializerContext _localctx = new Local_variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_local_variable_initializer);
		try {
			setState(1254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(1251);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1252);
				array_initializer();
				}
				break;
			case STACKALLOC:
				enterOuterAlt(_localctx, 3);
				{
				setState(1253);
				local_variable_initializer_unsafe();
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

	public static class Local_constant_declarationContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Constant_declaratorsContext constant_declarators() {
			return getRuleContext(Constant_declaratorsContext.class,0);
		}
		public Local_constant_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_constant_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_constant_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_constant_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_constant_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_constant_declarationContext local_constant_declaration() throws RecognitionException {
		Local_constant_declarationContext _localctx = new Local_constant_declarationContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_local_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1256);
			match(CONST);
			setState(1257);
			type_();
			setState(1258);
			constant_declarators();
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

	public static class If_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Simple_embedded_statementContext simple_embedded_statement() {
			return getRuleContext(Simple_embedded_statementContext.class,0);
		}
		public If_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIf_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIf_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIf_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_bodyContext if_body() throws RecognitionException {
		If_bodyContext _localctx = new If_bodyContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_if_body);
		try {
			setState(1262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1260);
				block();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BREAK:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DO:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case FROM:
			case GET:
			case GOTO:
			case GROUP:
			case IF:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LOCK:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case RETURN:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VAR:
			case WHEN:
			case WHERE:
			case WHILE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case SEMICOLON:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1261);
				simple_embedded_statement();
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

	public static class Switch_sectionContext extends ParserRuleContext {
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public List<Switch_labelContext> switch_label() {
			return getRuleContexts(Switch_labelContext.class);
		}
		public Switch_labelContext switch_label(int i) {
			return getRuleContext(Switch_labelContext.class,i);
		}
		public Switch_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_section(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSwitch_section(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Switch_sectionContext switch_section() throws RecognitionException {
		Switch_sectionContext _localctx = new Switch_sectionContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_switch_section);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1265); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1264);
					switch_label();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1267); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(1269);
			statement_list();
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

	public static class Switch_labelContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public Switch_labelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_label(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_label(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSwitch_label(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Switch_labelContext switch_label() throws RecognitionException {
		Switch_labelContext _localctx = new Switch_labelContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_switch_label);
		try {
			setState(1277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1271);
				match(CASE);
				setState(1272);
				expression();
				setState(1273);
				match(COLON);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1275);
				match(DEFAULT);
				setState(1276);
				match(COLON);
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

	public static class Statement_listContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Statement_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStatement_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStatement_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitStatement_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statement_listContext statement_list() throws RecognitionException {
		Statement_listContext _localctx = new Statement_listContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_statement_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1280); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1279);
					statement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1282); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,122,_ctx);
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

	public static class For_initializerContext extends ParserRuleContext {
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public For_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFor_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFor_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFor_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_initializerContext for_initializer() throws RecognitionException {
		For_initializerContext _localctx = new For_initializerContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_for_initializer);
		int _la;
		try {
			setState(1293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1284);
				local_variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1285);
				expression();
				setState(1290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1286);
					match(COMMA);
					setState(1287);
					expression();
					}
					}
					setState(1292);
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

	public static class For_iteratorContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public For_iteratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_iterator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFor_iterator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFor_iterator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFor_iterator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_iteratorContext for_iterator() throws RecognitionException {
		For_iteratorContext _localctx = new For_iteratorContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_for_iterator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1295);
			expression();
			setState(1300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1296);
				match(COMMA);
				setState(1297);
				expression();
				}
				}
				setState(1302);
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

	public static class Catch_clausesContext extends ParserRuleContext {
		public List<Specific_catch_clauseContext> specific_catch_clause() {
			return getRuleContexts(Specific_catch_clauseContext.class);
		}
		public Specific_catch_clauseContext specific_catch_clause(int i) {
			return getRuleContext(Specific_catch_clauseContext.class,i);
		}
		public General_catch_clauseContext general_catch_clause() {
			return getRuleContext(General_catch_clauseContext.class,0);
		}
		public Catch_clausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catch_clauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCatch_clauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCatch_clauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCatch_clauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Catch_clausesContext catch_clauses() throws RecognitionException {
		Catch_clausesContext _localctx = new Catch_clausesContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_catch_clauses);
		int _la;
		try {
			int _alt;
			setState(1314);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,128,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1303);
				specific_catch_clause();
				setState(1307);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1304);
						specific_catch_clause();
						}
						} 
					}
					setState(1309);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
				}
				setState(1311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CATCH) {
					{
					setState(1310);
					general_catch_clause();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1313);
				general_catch_clause();
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

	public static class Specific_catch_clauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Exception_filterContext exception_filter() {
			return getRuleContext(Exception_filterContext.class,0);
		}
		public Specific_catch_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specific_catch_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSpecific_catch_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSpecific_catch_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSpecific_catch_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Specific_catch_clauseContext specific_catch_clause() throws RecognitionException {
		Specific_catch_clauseContext _localctx = new Specific_catch_clauseContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_specific_catch_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1316);
			match(CATCH);
			setState(1317);
			match(OPEN_PARENS);
			setState(1318);
			class_type();
			setState(1320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << NAMEOF))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ON - 68)) | (1L << (ORDERBY - 68)) | (1L << (PARTIAL - 68)) | (1L << (REMOVE - 68)) | (1L << (SELECT - 68)) | (1L << (SET - 68)) | (1L << (VAR - 68)) | (1L << (WHEN - 68)) | (1L << (WHERE - 68)) | (1L << (YIELD - 68)) | (1L << (IDENTIFIER - 68)))) != 0)) {
				{
				setState(1319);
				identifier();
				}
			}

			setState(1322);
			match(CLOSE_PARENS);
			setState(1324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHEN) {
				{
				setState(1323);
				exception_filter();
				}
			}

			setState(1326);
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

	public static class General_catch_clauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Exception_filterContext exception_filter() {
			return getRuleContext(Exception_filterContext.class,0);
		}
		public General_catch_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_general_catch_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGeneral_catch_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGeneral_catch_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGeneral_catch_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final General_catch_clauseContext general_catch_clause() throws RecognitionException {
		General_catch_clauseContext _localctx = new General_catch_clauseContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_general_catch_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1328);
			match(CATCH);
			setState(1330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHEN) {
				{
				setState(1329);
				exception_filter();
				}
			}

			setState(1332);
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

	public static class Exception_filterContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(CSharpParser.WHEN, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Exception_filterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exception_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterException_filter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitException_filter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitException_filter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exception_filterContext exception_filter() throws RecognitionException {
		Exception_filterContext _localctx = new Exception_filterContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_exception_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1334);
			match(WHEN);
			setState(1335);
			match(OPEN_PARENS);
			setState(1336);
			expression();
			setState(1337);
			match(CLOSE_PARENS);
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

	public static class Finally_clauseContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(CSharpParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Finally_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finally_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFinally_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFinally_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFinally_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Finally_clauseContext finally_clause() throws RecognitionException {
		Finally_clauseContext _localctx = new Finally_clauseContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_finally_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1339);
			match(FINALLY);
			setState(1340);
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

	public static class Resource_acquisitionContext extends ParserRuleContext {
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Resource_acquisitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource_acquisition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterResource_acquisition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitResource_acquisition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitResource_acquisition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Resource_acquisitionContext resource_acquisition() throws RecognitionException {
		Resource_acquisitionContext _localctx = new Resource_acquisitionContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_resource_acquisition);
		try {
			setState(1344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,132,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1342);
				local_variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1343);
				expression();
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

	public static class Namespace_declarationContext extends ParserRuleContext {
		public Qualified_identifierContext qi;
		public TerminalNode NAMESPACE() { return getToken(CSharpParser.NAMESPACE, 0); }
		public Namespace_bodyContext namespace_body() {
			return getRuleContext(Namespace_bodyContext.class,0);
		}
		public Qualified_identifierContext qualified_identifier() {
			return getRuleContext(Qualified_identifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Namespace_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNamespace_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_declarationContext namespace_declaration() throws RecognitionException {
		Namespace_declarationContext _localctx = new Namespace_declarationContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_namespace_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1346);
			match(NAMESPACE);
			setState(1347);
			((Namespace_declarationContext)_localctx).qi = qualified_identifier();
			setState(1348);
			namespace_body();
			setState(1350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(1349);
				match(SEMICOLON);
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

	public static class Qualified_identifierContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public Qualified_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQualified_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQualified_identifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQualified_identifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Qualified_identifierContext qualified_identifier() throws RecognitionException {
		Qualified_identifierContext _localctx = new Qualified_identifierContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_qualified_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1352);
			identifier();
			setState(1357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(1353);
				match(DOT);
				setState(1354);
				identifier();
				}
				}
				setState(1359);
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

	public static class Namespace_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Extern_alias_directivesContext extern_alias_directives() {
			return getRuleContext(Extern_alias_directivesContext.class,0);
		}
		public Using_directivesContext using_directives() {
			return getRuleContext(Using_directivesContext.class,0);
		}
		public Namespace_member_declarationsContext namespace_member_declarations() {
			return getRuleContext(Namespace_member_declarationsContext.class,0);
		}
		public Namespace_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNamespace_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_bodyContext namespace_body() throws RecognitionException {
		Namespace_bodyContext _localctx = new Namespace_bodyContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_namespace_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1360);
			match(OPEN_BRACE);
			setState(1362);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
			case 1:
				{
				setState(1361);
				extern_alias_directives();
				}
				break;
			}
			setState(1365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1364);
				using_directives();
				}
			}

			setState(1368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << CLASS) | (1L << DELEGATE) | (1L << ENUM) | (1L << EXTERN) | (1L << INTERFACE) | (1L << INTERNAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (NAMESPACE - 64)) | (1L << (NEW - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARTIAL - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (SEALED - 64)) | (1L << (STATIC - 64)) | (1L << (STRUCT - 64)) | (1L << (UNSAFE - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOLATILE - 64)) | (1L << (OPEN_BRACKET - 64)))) != 0)) {
				{
				setState(1367);
				namespace_member_declarations();
				}
			}

			setState(1370);
			match(CLOSE_BRACE);
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

	public static class Extern_alias_directivesContext extends ParserRuleContext {
		public List<Extern_alias_directiveContext> extern_alias_directive() {
			return getRuleContexts(Extern_alias_directiveContext.class);
		}
		public Extern_alias_directiveContext extern_alias_directive(int i) {
			return getRuleContext(Extern_alias_directiveContext.class,i);
		}
		public Extern_alias_directivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extern_alias_directives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExtern_alias_directives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExtern_alias_directives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExtern_alias_directives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Extern_alias_directivesContext extern_alias_directives() throws RecognitionException {
		Extern_alias_directivesContext _localctx = new Extern_alias_directivesContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_extern_alias_directives);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1373); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1372);
					extern_alias_directive();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1375); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
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

	public static class Extern_alias_directiveContext extends ParserRuleContext {
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode ALIAS() { return getToken(CSharpParser.ALIAS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Extern_alias_directiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extern_alias_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExtern_alias_directive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExtern_alias_directive(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitExtern_alias_directive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Extern_alias_directiveContext extern_alias_directive() throws RecognitionException {
		Extern_alias_directiveContext _localctx = new Extern_alias_directiveContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_extern_alias_directive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1377);
			match(EXTERN);
			setState(1378);
			match(ALIAS);
			setState(1379);
			identifier();
			setState(1380);
			match(SEMICOLON);
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

	public static class Using_directivesContext extends ParserRuleContext {
		public List<Using_directiveContext> using_directive() {
			return getRuleContexts(Using_directiveContext.class);
		}
		public Using_directiveContext using_directive(int i) {
			return getRuleContext(Using_directiveContext.class,i);
		}
		public Using_directivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_using_directives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsing_directives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsing_directives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUsing_directives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Using_directivesContext using_directives() throws RecognitionException {
		Using_directivesContext _localctx = new Using_directivesContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_using_directives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1383); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1382);
				using_directive();
				}
				}
				setState(1385); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==USING );
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

	public static class Using_directiveContext extends ParserRuleContext {
		public Using_directiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_using_directive; }
	 
		public Using_directiveContext() { }
		public void copyFrom(Using_directiveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UsingAliasDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingAliasDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingAliasDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingAliasDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUsingAliasDirective(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UsingNamespaceDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingNamespaceDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingNamespaceDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingNamespaceDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUsingNamespaceDirective(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UsingStaticDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingStaticDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingStaticDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingStaticDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitUsingStaticDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Using_directiveContext using_directive() throws RecognitionException {
		Using_directiveContext _localctx = new Using_directiveContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_using_directive);
		try {
			setState(1402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,140,_ctx) ) {
			case 1:
				_localctx = new UsingAliasDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1387);
				match(USING);
				setState(1388);
				identifier();
				setState(1389);
				match(ASSIGNMENT);
				setState(1390);
				namespace_or_type_name();
				setState(1391);
				match(SEMICOLON);
				}
				break;
			case 2:
				_localctx = new UsingNamespaceDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1393);
				match(USING);
				setState(1394);
				namespace_or_type_name();
				setState(1395);
				match(SEMICOLON);
				}
				break;
			case 3:
				_localctx = new UsingStaticDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1397);
				match(USING);
				setState(1398);
				match(STATIC);
				setState(1399);
				namespace_or_type_name();
				setState(1400);
				match(SEMICOLON);
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

	public static class Namespace_member_declarationsContext extends ParserRuleContext {
		public List<Namespace_member_declarationContext> namespace_member_declaration() {
			return getRuleContexts(Namespace_member_declarationContext.class);
		}
		public Namespace_member_declarationContext namespace_member_declaration(int i) {
			return getRuleContext(Namespace_member_declarationContext.class,i);
		}
		public Namespace_member_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_member_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_member_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_member_declarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNamespace_member_declarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_member_declarationsContext namespace_member_declarations() throws RecognitionException {
		Namespace_member_declarationsContext _localctx = new Namespace_member_declarationsContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_namespace_member_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1405); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1404);
				namespace_member_declaration();
				}
				}
				setState(1407); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << CLASS) | (1L << DELEGATE) | (1L << ENUM) | (1L << EXTERN) | (1L << INTERFACE) | (1L << INTERNAL))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (NAMESPACE - 64)) | (1L << (NEW - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARTIAL - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (SEALED - 64)) | (1L << (STATIC - 64)) | (1L << (STRUCT - 64)) | (1L << (UNSAFE - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOLATILE - 64)) | (1L << (OPEN_BRACKET - 64)))) != 0) );
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

	public static class Namespace_member_declarationContext extends ParserRuleContext {
		public Namespace_declarationContext namespace_declaration() {
			return getRuleContext(Namespace_declarationContext.class,0);
		}
		public Type_declarationContext type_declaration() {
			return getRuleContext(Type_declarationContext.class,0);
		}
		public Namespace_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitNamespace_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_member_declarationContext namespace_member_declaration() throws RecognitionException {
		Namespace_member_declarationContext _localctx = new Namespace_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_namespace_member_declaration);
		try {
			setState(1411);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1409);
				namespace_declaration();
				}
				break;
			case ABSTRACT:
			case ASYNC:
			case CLASS:
			case DELEGATE:
			case ENUM:
			case EXTERN:
			case INTERFACE:
			case INTERNAL:
			case NEW:
			case OVERRIDE:
			case PARTIAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case READONLY:
			case SEALED:
			case STATIC:
			case STRUCT:
			case UNSAFE:
			case VIRTUAL:
			case VOLATILE:
			case OPEN_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(1410);
				type_declaration();
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

	public static class Type_declarationContext extends ParserRuleContext {
		public Class_definitionContext class_definition() {
			return getRuleContext(Class_definitionContext.class,0);
		}
		public Struct_definitionContext struct_definition() {
			return getRuleContext(Struct_definitionContext.class,0);
		}
		public Interface_definitionContext interface_definition() {
			return getRuleContext(Interface_definitionContext.class,0);
		}
		public Enum_definitionContext enum_definition() {
			return getRuleContext(Enum_definitionContext.class,0);
		}
		public Delegate_definitionContext delegate_definition() {
			return getRuleContext(Delegate_definitionContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public Type_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_declarationContext type_declaration() throws RecognitionException {
		Type_declarationContext _localctx = new Type_declarationContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_type_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1413);
				attributes();
				}
			}

			setState(1417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << EXTERN) | (1L << INTERNAL))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OVERRIDE - 65)) | (1L << (PARTIAL - 65)) | (1L << (PRIVATE - 65)) | (1L << (PROTECTED - 65)) | (1L << (PUBLIC - 65)) | (1L << (READONLY - 65)) | (1L << (SEALED - 65)) | (1L << (STATIC - 65)) | (1L << (UNSAFE - 65)) | (1L << (VIRTUAL - 65)) | (1L << (VOLATILE - 65)))) != 0)) {
				{
				setState(1416);
				all_member_modifiers();
				}
			}

			setState(1424);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(1419);
				class_definition();
				}
				break;
			case STRUCT:
				{
				setState(1420);
				struct_definition();
				}
				break;
			case INTERFACE:
				{
				setState(1421);
				interface_definition();
				}
				break;
			case ENUM:
				{
				setState(1422);
				enum_definition();
				}
				break;
			case DELEGATE:
				{
				setState(1423);
				delegate_definition();
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

	public static class Qualified_alias_memberContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public Qualified_alias_memberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_alias_member; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQualified_alias_member(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQualified_alias_member(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitQualified_alias_member(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Qualified_alias_memberContext qualified_alias_member() throws RecognitionException {
		Qualified_alias_memberContext _localctx = new Qualified_alias_memberContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_qualified_alias_member);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1426);
			identifier();
			setState(1427);
			match(DOUBLE_COLON);
			setState(1428);
			identifier();
			setState(1430);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
			case 1:
				{
				setState(1429);
				type_argument_list();
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

	public static class Type_parameter_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Type_parameterContext> type_parameter() {
			return getRuleContexts(Type_parameterContext.class);
		}
		public Type_parameterContext type_parameter(int i) {
			return getRuleContext(Type_parameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Type_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_parameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_parameter_listContext type_parameter_list() throws RecognitionException {
		Type_parameter_listContext _localctx = new Type_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_type_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1432);
			match(LT);
			setState(1433);
			type_parameter();
			setState(1438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1434);
				match(COMMA);
				setState(1435);
				type_parameter();
				}
				}
				setState(1440);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1441);
			match(GT);
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

	public static class Type_parameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Type_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_parameterContext type_parameter() throws RecognitionException {
		Type_parameterContext _localctx = new Type_parameterContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_type_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1444);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1443);
				attributes();
				}
			}

			setState(1446);
			identifier();
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

	public static class Class_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public Class_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_base(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_base(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_baseContext class_base() throws RecognitionException {
		Class_baseContext _localctx = new Class_baseContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_class_base);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1448);
			match(COLON);
			setState(1449);
			class_type();
			setState(1454);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1450);
				match(COMMA);
				setState(1451);
				namespace_or_type_name();
				}
				}
				setState(1456);
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

	public static class Interface_type_listContext extends ParserRuleContext {
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Interface_type_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_type_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_type_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_type_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_type_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_type_listContext interface_type_list() throws RecognitionException {
		Interface_type_listContext _localctx = new Interface_type_listContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_interface_type_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1457);
			namespace_or_type_name();
			setState(1462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1458);
				match(COMMA);
				setState(1459);
				namespace_or_type_name();
				}
				}
				setState(1464);
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

	public static class Type_parameter_constraints_clausesContext extends ParserRuleContext {
		public List<Type_parameter_constraints_clauseContext> type_parameter_constraints_clause() {
			return getRuleContexts(Type_parameter_constraints_clauseContext.class);
		}
		public Type_parameter_constraints_clauseContext type_parameter_constraints_clause(int i) {
			return getRuleContext(Type_parameter_constraints_clauseContext.class,i);
		}
		public Type_parameter_constraints_clausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints_clauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints_clauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints_clauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_parameter_constraints_clauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() throws RecognitionException {
		Type_parameter_constraints_clausesContext _localctx = new Type_parameter_constraints_clausesContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_type_parameter_constraints_clauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1466); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1465);
				type_parameter_constraints_clause();
				}
				}
				setState(1468); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHERE );
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

	public static class Type_parameter_constraints_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Type_parameter_constraintsContext type_parameter_constraints() {
			return getRuleContext(Type_parameter_constraintsContext.class,0);
		}
		public Type_parameter_constraints_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_parameter_constraints_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_parameter_constraints_clauseContext type_parameter_constraints_clause() throws RecognitionException {
		Type_parameter_constraints_clauseContext _localctx = new Type_parameter_constraints_clauseContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_type_parameter_constraints_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1470);
			match(WHERE);
			setState(1471);
			identifier();
			setState(1472);
			match(COLON);
			setState(1473);
			type_parameter_constraints();
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

	public static class Type_parameter_constraintsContext extends ParserRuleContext {
		public Constructor_constraintContext constructor_constraint() {
			return getRuleContext(Constructor_constraintContext.class,0);
		}
		public Primary_constraintContext primary_constraint() {
			return getRuleContext(Primary_constraintContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Secondary_constraintsContext secondary_constraints() {
			return getRuleContext(Secondary_constraintsContext.class,0);
		}
		public Type_parameter_constraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitType_parameter_constraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_parameter_constraintsContext type_parameter_constraints() throws RecognitionException {
		Type_parameter_constraintsContext _localctx = new Type_parameter_constraintsContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_type_parameter_constraints);
		int _la;
		try {
			setState(1485);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEW:
				enterOuterAlt(_localctx, 1);
				{
				setState(1475);
				constructor_constraint();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case CLASS:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case STRING:
			case STRUCT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1476);
				primary_constraint();
				setState(1479);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,152,_ctx) ) {
				case 1:
					{
					setState(1477);
					match(COMMA);
					setState(1478);
					secondary_constraints();
					}
					break;
				}
				setState(1483);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1481);
					match(COMMA);
					setState(1482);
					constructor_constraint();
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

	public static class Primary_constraintContext extends ParserRuleContext {
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public Primary_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPrimary_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPrimary_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitPrimary_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primary_constraintContext primary_constraint() throws RecognitionException {
		Primary_constraintContext _localctx = new Primary_constraintContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_primary_constraint);
		try {
			setState(1490);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case STRING:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1487);
				class_type();
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 2);
				{
				setState(1488);
				match(CLASS);
				}
				break;
			case STRUCT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1489);
				match(STRUCT);
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

	public static class Secondary_constraintsContext extends ParserRuleContext {
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Secondary_constraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_secondary_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSecondary_constraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSecondary_constraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSecondary_constraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Secondary_constraintsContext secondary_constraints() throws RecognitionException {
		Secondary_constraintsContext _localctx = new Secondary_constraintsContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_secondary_constraints);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1492);
			namespace_or_type_name();
			setState(1497);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1493);
					match(COMMA);
					setState(1494);
					namespace_or_type_name();
					}
					} 
				}
				setState(1499);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
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

	public static class Constructor_constraintContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Constructor_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstructor_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constructor_constraintContext constructor_constraint() throws RecognitionException {
		Constructor_constraintContext _localctx = new Constructor_constraintContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_constructor_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1500);
			match(NEW);
			setState(1501);
			match(OPEN_PARENS);
			setState(1502);
			match(CLOSE_PARENS);
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

	public static class Class_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Class_member_declarationsContext class_member_declarations() {
			return getRuleContext(Class_member_declarationsContext.class,0);
		}
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_class_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1504);
			match(OPEN_BRACE);
			setState(1506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONST) | (1L << DECIMAL) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << ENUM) | (1L << EQUALS) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IMPLICIT) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OVERRIDE - 65)) | (1L << (PARTIAL - 65)) | (1L << (PRIVATE - 65)) | (1L << (PROTECTED - 65)) | (1L << (PUBLIC - 65)) | (1L << (READONLY - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SEALED - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (STATIC - 65)) | (1L << (STRING - 65)) | (1L << (STRUCT - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNSAFE - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VIRTUAL - 65)) | (1L << (VOID - 65)) | (1L << (VOLATILE - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (OPEN_BRACKET - 65)))) != 0) || _la==TILDE) {
				{
				setState(1505);
				class_member_declarations();
				}
			}

			setState(1508);
			match(CLOSE_BRACE);
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

	public static class Class_member_declarationsContext extends ParserRuleContext {
		public List<Class_member_declarationContext> class_member_declaration() {
			return getRuleContexts(Class_member_declarationContext.class);
		}
		public Class_member_declarationContext class_member_declaration(int i) {
			return getRuleContext(Class_member_declarationContext.class,i);
		}
		public Class_member_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_member_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_member_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_member_declarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_member_declarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_member_declarationsContext class_member_declarations() throws RecognitionException {
		Class_member_declarationsContext _localctx = new Class_member_declarationsContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_class_member_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1511); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1510);
				class_member_declaration();
				}
				}
				setState(1513); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONST) | (1L << DECIMAL) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << ENUM) | (1L << EQUALS) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IMPLICIT) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OVERRIDE - 65)) | (1L << (PARTIAL - 65)) | (1L << (PRIVATE - 65)) | (1L << (PROTECTED - 65)) | (1L << (PUBLIC - 65)) | (1L << (READONLY - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SEALED - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (STATIC - 65)) | (1L << (STRING - 65)) | (1L << (STRUCT - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNSAFE - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VIRTUAL - 65)) | (1L << (VOID - 65)) | (1L << (VOLATILE - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (OPEN_BRACKET - 65)))) != 0) || _la==TILDE );
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

	public static class Class_member_declarationContext extends ParserRuleContext {
		public Common_member_declarationContext common_member_declaration() {
			return getRuleContext(Common_member_declarationContext.class,0);
		}
		public Destructor_definitionContext destructor_definition() {
			return getRuleContext(Destructor_definitionContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public Class_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_member_declarationContext class_member_declaration() throws RecognitionException {
		Class_member_declarationContext _localctx = new Class_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_class_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1516);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1515);
				attributes();
				}
			}

			setState(1519);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
			case 1:
				{
				setState(1518);
				all_member_modifiers();
				}
				break;
			}
			setState(1523);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CLASS:
			case CONST:
			case DECIMAL:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case ENUM:
			case EQUALS:
			case EVENT:
			case EXPLICIT:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case IMPLICIT:
			case INT:
			case INTERFACE:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case STRUCT:
			case UINT:
			case ULONG:
			case USHORT:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				{
				setState(1521);
				common_member_declaration();
				}
				break;
			case TILDE:
				{
				setState(1522);
				destructor_definition();
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

	public static class All_member_modifiersContext extends ParserRuleContext {
		public List<All_member_modifierContext> all_member_modifier() {
			return getRuleContexts(All_member_modifierContext.class);
		}
		public All_member_modifierContext all_member_modifier(int i) {
			return getRuleContext(All_member_modifierContext.class,i);
		}
		public All_member_modifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_member_modifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAll_member_modifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAll_member_modifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAll_member_modifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_member_modifiersContext all_member_modifiers() throws RecognitionException {
		All_member_modifiersContext _localctx = new All_member_modifiersContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_all_member_modifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1526); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1525);
					all_member_modifier();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1528); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,162,_ctx);
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

	public static class All_member_modifierContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode PUBLIC() { return getToken(CSharpParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public TerminalNode VOLATILE() { return getToken(CSharpParser.VOLATILE, 0); }
		public TerminalNode VIRTUAL() { return getToken(CSharpParser.VIRTUAL, 0); }
		public TerminalNode SEALED() { return getToken(CSharpParser.SEALED, 0); }
		public TerminalNode OVERRIDE() { return getToken(CSharpParser.OVERRIDE, 0); }
		public TerminalNode ABSTRACT() { return getToken(CSharpParser.ABSTRACT, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode PARTIAL() { return getToken(CSharpParser.PARTIAL, 0); }
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public All_member_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_member_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAll_member_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAll_member_modifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAll_member_modifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_member_modifierContext all_member_modifier() throws RecognitionException {
		All_member_modifierContext _localctx = new All_member_modifierContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_all_member_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1530);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << EXTERN) | (1L << INTERNAL))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OVERRIDE - 65)) | (1L << (PARTIAL - 65)) | (1L << (PRIVATE - 65)) | (1L << (PROTECTED - 65)) | (1L << (PUBLIC - 65)) | (1L << (READONLY - 65)) | (1L << (SEALED - 65)) | (1L << (STATIC - 65)) | (1L << (UNSAFE - 65)) | (1L << (VIRTUAL - 65)) | (1L << (VOLATILE - 65)))) != 0)) ) {
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

	public static class Common_member_declarationContext extends ParserRuleContext {
		public Constant_declarationContext constant_declaration() {
			return getRuleContext(Constant_declarationContext.class,0);
		}
		public Typed_member_declarationContext typed_member_declaration() {
			return getRuleContext(Typed_member_declarationContext.class,0);
		}
		public Event_declarationContext event_declaration() {
			return getRuleContext(Event_declarationContext.class,0);
		}
		public Conversion_operator_declaratorContext conversion_operator_declarator() {
			return getRuleContext(Conversion_operator_declaratorContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Constructor_declarationContext constructor_declaration() {
			return getRuleContext(Constructor_declarationContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Class_definitionContext class_definition() {
			return getRuleContext(Class_definitionContext.class,0);
		}
		public Struct_definitionContext struct_definition() {
			return getRuleContext(Struct_definitionContext.class,0);
		}
		public Interface_definitionContext interface_definition() {
			return getRuleContext(Interface_definitionContext.class,0);
		}
		public Enum_definitionContext enum_definition() {
			return getRuleContext(Enum_definitionContext.class,0);
		}
		public Delegate_definitionContext delegate_definition() {
			return getRuleContext(Delegate_definitionContext.class,0);
		}
		public Common_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_common_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCommon_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCommon_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitCommon_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Common_member_declarationContext common_member_declaration() throws RecognitionException {
		Common_member_declarationContext _localctx = new Common_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_common_member_declaration);
		try {
			setState(1551);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,164,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1532);
				constant_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1533);
				typed_member_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1534);
				event_declaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1535);
				conversion_operator_declarator();
				setState(1541);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPEN_BRACE:
				case SEMICOLON:
					{
					setState(1536);
					body();
					}
					break;
				case ASSIGNMENT:
					{
					setState(1537);
					right_arrow();
					setState(1538);
					expression();
					setState(1539);
					match(SEMICOLON);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1543);
				constructor_declaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1544);
				match(VOID);
				setState(1545);
				method_declaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1546);
				class_definition();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1547);
				struct_definition();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1548);
				interface_definition();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1549);
				enum_definition();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1550);
				delegate_definition();
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

	public static class Typed_member_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public Indexer_declarationContext indexer_declaration() {
			return getRuleContext(Indexer_declarationContext.class,0);
		}
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Property_declarationContext property_declaration() {
			return getRuleContext(Property_declarationContext.class,0);
		}
		public Operator_declarationContext operator_declaration() {
			return getRuleContext(Operator_declarationContext.class,0);
		}
		public Field_declarationContext field_declaration() {
			return getRuleContext(Field_declarationContext.class,0);
		}
		public Typed_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typed_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTyped_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTyped_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitTyped_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Typed_member_declarationContext typed_member_declaration() throws RecognitionException {
		Typed_member_declarationContext _localctx = new Typed_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_typed_member_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1553);
			type_();
			setState(1563);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,165,_ctx) ) {
			case 1:
				{
				setState(1554);
				namespace_or_type_name();
				setState(1555);
				match(DOT);
				setState(1556);
				indexer_declaration();
				}
				break;
			case 2:
				{
				setState(1558);
				method_declaration();
				}
				break;
			case 3:
				{
				setState(1559);
				property_declaration();
				}
				break;
			case 4:
				{
				setState(1560);
				indexer_declaration();
				}
				break;
			case 5:
				{
				setState(1561);
				operator_declaration();
				}
				break;
			case 6:
				{
				setState(1562);
				field_declaration();
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

	public static class Constant_declaratorsContext extends ParserRuleContext {
		public List<Constant_declaratorContext> constant_declarator() {
			return getRuleContexts(Constant_declaratorContext.class);
		}
		public Constant_declaratorContext constant_declarator(int i) {
			return getRuleContext(Constant_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Constant_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstant_declarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_declaratorsContext constant_declarators() throws RecognitionException {
		Constant_declaratorsContext _localctx = new Constant_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_constant_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1565);
			constant_declarator();
			setState(1570);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1566);
				match(COMMA);
				setState(1567);
				constant_declarator();
				}
				}
				setState(1572);
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

	public static class Constant_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Constant_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstant_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_declaratorContext constant_declarator() throws RecognitionException {
		Constant_declaratorContext _localctx = new Constant_declaratorContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_constant_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1573);
			identifier();
			setState(1574);
			match(ASSIGNMENT);
			setState(1575);
			expression();
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

	public static class Variable_declaratorsContext extends ParserRuleContext {
		public List<Variable_declaratorContext> variable_declarator() {
			return getRuleContexts(Variable_declaratorContext.class);
		}
		public Variable_declaratorContext variable_declarator(int i) {
			return getRuleContext(Variable_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Variable_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_declarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariable_declarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declaratorsContext variable_declarators() throws RecognitionException {
		Variable_declaratorsContext _localctx = new Variable_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_variable_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1577);
			variable_declarator();
			setState(1582);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1578);
				match(COMMA);
				setState(1579);
				variable_declarator();
				}
				}
				setState(1584);
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

	public static class Variable_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Variable_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariable_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declaratorContext variable_declarator() throws RecognitionException {
		Variable_declaratorContext _localctx = new Variable_declaratorContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_variable_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1585);
			identifier();
			setState(1588);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(1586);
				match(ASSIGNMENT);
				setState(1587);
				variable_initializer();
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

	public static class Variable_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariable_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_initializerContext variable_initializer() throws RecognitionException {
		Variable_initializerContext _localctx = new Variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_variable_initializer);
		try {
			setState(1592);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(1590);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1591);
				array_initializer();
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

	public static class Return_typeContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitReturn_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitReturn_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_return_type);
		try {
			setState(1596);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1594);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1595);
				match(VOID);
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

	public static class Member_nameContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public Member_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMember_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Member_nameContext member_name() throws RecognitionException {
		Member_nameContext _localctx = new Member_nameContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_member_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1598);
			namespace_or_type_name();
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

	public static class Method_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Method_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMethod_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_bodyContext method_body() throws RecognitionException {
		Method_bodyContext _localctx = new Method_bodyContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_method_body);
		try {
			setState(1602);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1600);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(1601);
				match(SEMICOLON);
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

	public static class Formal_parameter_listContext extends ParserRuleContext {
		public Parameter_arrayContext parameter_array() {
			return getRuleContext(Parameter_arrayContext.class,0);
		}
		public Fixed_parametersContext fixed_parameters() {
			return getRuleContext(Fixed_parametersContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Formal_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFormal_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFormal_parameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFormal_parameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parameter_listContext formal_parameter_list() throws RecognitionException {
		Formal_parameter_listContext _localctx = new Formal_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_formal_parameter_list);
		int _la;
		try {
			setState(1610);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,173,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1604);
				parameter_array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1605);
				fixed_parameters();
				setState(1608);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1606);
					match(COMMA);
					setState(1607);
					parameter_array();
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

	public static class Fixed_parametersContext extends ParserRuleContext {
		public List<Fixed_parameterContext> fixed_parameter() {
			return getRuleContexts(Fixed_parameterContext.class);
		}
		public Fixed_parameterContext fixed_parameter(int i) {
			return getRuleContext(Fixed_parameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Fixed_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_parameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_parametersContext fixed_parameters() throws RecognitionException {
		Fixed_parametersContext _localctx = new Fixed_parametersContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_fixed_parameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1612);
			fixed_parameter();
			setState(1617);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,174,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1613);
					match(COMMA);
					setState(1614);
					fixed_parameter();
					}
					} 
				}
				setState(1619);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,174,_ctx);
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

	public static class Fixed_parameterContext extends ParserRuleContext {
		public Arg_declarationContext arg_declaration() {
			return getRuleContext(Arg_declarationContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Parameter_modifierContext parameter_modifier() {
			return getRuleContext(Parameter_modifierContext.class,0);
		}
		public TerminalNode ARGLIST() { return getToken(CSharpParser.ARGLIST, 0); }
		public Fixed_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_parameterContext fixed_parameter() throws RecognitionException {
		Fixed_parameterContext _localctx = new Fixed_parameterContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_fixed_parameter);
		int _la;
		try {
			setState(1628);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,177,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1621);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_BRACKET) {
					{
					setState(1620);
					attributes();
					}
				}

				setState(1624);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (OUT - 71)) | (1L << (REF - 71)) | (1L << (THIS - 71)))) != 0)) {
					{
					setState(1623);
					parameter_modifier();
					}
				}

				setState(1626);
				arg_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1627);
				match(ARGLIST);
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

	public static class Parameter_modifierContext extends ParserRuleContext {
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public Parameter_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParameter_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParameter_modifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitParameter_modifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_modifierContext parameter_modifier() throws RecognitionException {
		Parameter_modifierContext _localctx = new Parameter_modifierContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_parameter_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1630);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (OUT - 71)) | (1L << (REF - 71)) | (1L << (THIS - 71)))) != 0)) ) {
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

	public static class Parameter_arrayContext extends ParserRuleContext {
		public TerminalNode PARAMS() { return getToken(CSharpParser.PARAMS, 0); }
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Parameter_arrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParameter_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParameter_array(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitParameter_array(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameter_arrayContext parameter_array() throws RecognitionException {
		Parameter_arrayContext _localctx = new Parameter_arrayContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_parameter_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1633);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1632);
				attributes();
				}
			}

			setState(1635);
			match(PARAMS);
			setState(1636);
			array_type();
			setState(1637);
			identifier();
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

	public static class Accessor_declarationsContext extends ParserRuleContext {
		public AttributesContext attrs;
		public Accessor_modifierContext mods;
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Set_accessor_declarationContext set_accessor_declaration() {
			return getRuleContext(Set_accessor_declarationContext.class,0);
		}
		public Get_accessor_declarationContext get_accessor_declaration() {
			return getRuleContext(Get_accessor_declarationContext.class,0);
		}
		public Accessor_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_declarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAccessor_declarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Accessor_declarationsContext accessor_declarations() throws RecognitionException {
		Accessor_declarationsContext _localctx = new Accessor_declarationsContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_accessor_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1640);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1639);
				((Accessor_declarationsContext)_localctx).attrs = attributes();
				}
			}

			setState(1643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 56)) & ~0x3f) == 0 && ((1L << (_la - 56)) & ((1L << (INTERNAL - 56)) | (1L << (PRIVATE - 56)) | (1L << (PROTECTED - 56)))) != 0)) {
				{
				setState(1642);
				((Accessor_declarationsContext)_localctx).mods = accessor_modifier();
				}
			}

			setState(1655);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(1645);
				match(GET);
				setState(1646);
				accessor_body();
				setState(1648);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INTERNAL || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (PRIVATE - 75)) | (1L << (PROTECTED - 75)) | (1L << (SET - 75)) | (1L << (OPEN_BRACKET - 75)))) != 0)) {
					{
					setState(1647);
					set_accessor_declaration();
					}
				}

				}
				break;
			case SET:
				{
				setState(1650);
				match(SET);
				setState(1651);
				accessor_body();
				setState(1653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GET || _la==INTERNAL || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (PRIVATE - 75)) | (1L << (PROTECTED - 75)) | (1L << (OPEN_BRACKET - 75)))) != 0)) {
					{
					setState(1652);
					get_accessor_declaration();
					}
				}

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

	public static class Get_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Get_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGet_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGet_accessor_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGet_accessor_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Get_accessor_declarationContext get_accessor_declaration() throws RecognitionException {
		Get_accessor_declarationContext _localctx = new Get_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_get_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1658);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1657);
				attributes();
				}
			}

			setState(1661);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 56)) & ~0x3f) == 0 && ((1L << (_la - 56)) & ((1L << (INTERNAL - 56)) | (1L << (PRIVATE - 56)) | (1L << (PROTECTED - 56)))) != 0)) {
				{
				setState(1660);
				accessor_modifier();
				}
			}

			setState(1663);
			match(GET);
			setState(1664);
			accessor_body();
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

	public static class Set_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Set_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSet_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSet_accessor_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitSet_accessor_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_accessor_declarationContext set_accessor_declaration() throws RecognitionException {
		Set_accessor_declarationContext _localctx = new Set_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_set_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1667);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1666);
				attributes();
				}
			}

			setState(1670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 56)) & ~0x3f) == 0 && ((1L << (_la - 56)) & ((1L << (INTERNAL - 56)) | (1L << (PRIVATE - 56)) | (1L << (PROTECTED - 56)))) != 0)) {
				{
				setState(1669);
				accessor_modifier();
				}
			}

			setState(1672);
			match(SET);
			setState(1673);
			accessor_body();
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

	public static class Accessor_modifierContext extends ParserRuleContext {
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public Accessor_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_modifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAccessor_modifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Accessor_modifierContext accessor_modifier() throws RecognitionException {
		Accessor_modifierContext _localctx = new Accessor_modifierContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_accessor_modifier);
		try {
			setState(1682);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,188,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1675);
				match(PROTECTED);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1676);
				match(INTERNAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1677);
				match(PRIVATE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1678);
				match(PROTECTED);
				setState(1679);
				match(INTERNAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1680);
				match(INTERNAL);
				setState(1681);
				match(PROTECTED);
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

	public static class Accessor_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Accessor_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAccessor_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Accessor_bodyContext accessor_body() throws RecognitionException {
		Accessor_bodyContext _localctx = new Accessor_bodyContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_accessor_body);
		try {
			setState(1686);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1684);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(1685);
				match(SEMICOLON);
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

	public static class Event_accessor_declarationsContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Remove_accessor_declarationContext remove_accessor_declaration() {
			return getRuleContext(Remove_accessor_declarationContext.class,0);
		}
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public Add_accessor_declarationContext add_accessor_declaration() {
			return getRuleContext(Add_accessor_declarationContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Event_accessor_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_accessor_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEvent_accessor_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEvent_accessor_declarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEvent_accessor_declarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Event_accessor_declarationsContext event_accessor_declarations() throws RecognitionException {
		Event_accessor_declarationsContext _localctx = new Event_accessor_declarationsContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_event_accessor_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1689);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1688);
				attributes();
				}
			}

			setState(1699);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				{
				setState(1691);
				match(ADD);
				setState(1692);
				block();
				setState(1693);
				remove_accessor_declaration();
				}
				break;
			case REMOVE:
				{
				setState(1695);
				match(REMOVE);
				setState(1696);
				block();
				setState(1697);
				add_accessor_declaration();
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

	public static class Add_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Add_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAdd_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAdd_accessor_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAdd_accessor_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Add_accessor_declarationContext add_accessor_declaration() throws RecognitionException {
		Add_accessor_declarationContext _localctx = new Add_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_add_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1702);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1701);
				attributes();
				}
			}

			setState(1704);
			match(ADD);
			setState(1705);
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

	public static class Remove_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Remove_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRemove_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRemove_accessor_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRemove_accessor_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Remove_accessor_declarationContext remove_accessor_declaration() throws RecognitionException {
		Remove_accessor_declarationContext _localctx = new Remove_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_remove_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1707);
				attributes();
				}
			}

			setState(1710);
			match(REMOVE);
			setState(1711);
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

	public static class Overloadable_operatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(CSharpParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CSharpParser.MINUS, 0); }
		public TerminalNode BANG() { return getToken(CSharpParser.BANG, 0); }
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public TerminalNode OP_INC() { return getToken(CSharpParser.OP_INC, 0); }
		public TerminalNode OP_DEC() { return getToken(CSharpParser.OP_DEC, 0); }
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public TerminalNode DIV() { return getToken(CSharpParser.DIV, 0); }
		public TerminalNode PERCENT() { return getToken(CSharpParser.PERCENT, 0); }
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public TerminalNode BITWISE_OR() { return getToken(CSharpParser.BITWISE_OR, 0); }
		public TerminalNode CARET() { return getToken(CSharpParser.CARET, 0); }
		public TerminalNode OP_LEFT_SHIFT() { return getToken(CSharpParser.OP_LEFT_SHIFT, 0); }
		public Right_shiftContext right_shift() {
			return getRuleContext(Right_shiftContext.class,0);
		}
		public TerminalNode OP_EQ() { return getToken(CSharpParser.OP_EQ, 0); }
		public TerminalNode OP_NE() { return getToken(CSharpParser.OP_NE, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public TerminalNode OP_GE() { return getToken(CSharpParser.OP_GE, 0); }
		public TerminalNode OP_LE() { return getToken(CSharpParser.OP_LE, 0); }
		public Overloadable_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overloadable_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOverloadable_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOverloadable_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitOverloadable_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Overloadable_operatorContext overloadable_operator() throws RecognitionException {
		Overloadable_operatorContext _localctx = new Overloadable_operatorContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_overloadable_operator);
		try {
			setState(1735);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,194,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1713);
				match(PLUS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1714);
				match(MINUS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1715);
				match(BANG);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1716);
				match(TILDE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1717);
				match(OP_INC);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1718);
				match(OP_DEC);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1719);
				match(TRUE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1720);
				match(FALSE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1721);
				match(STAR);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1722);
				match(DIV);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1723);
				match(PERCENT);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1724);
				match(AMP);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1725);
				match(BITWISE_OR);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1726);
				match(CARET);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1727);
				match(OP_LEFT_SHIFT);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1728);
				right_shift();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1729);
				match(OP_EQ);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(1730);
				match(OP_NE);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1731);
				match(GT);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(1732);
				match(LT);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(1733);
				match(OP_GE);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(1734);
				match(OP_LE);
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

	public static class Conversion_operator_declaratorContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Arg_declarationContext arg_declaration() {
			return getRuleContext(Arg_declarationContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode IMPLICIT() { return getToken(CSharpParser.IMPLICIT, 0); }
		public TerminalNode EXPLICIT() { return getToken(CSharpParser.EXPLICIT, 0); }
		public Conversion_operator_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conversion_operator_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConversion_operator_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConversion_operator_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConversion_operator_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conversion_operator_declaratorContext conversion_operator_declarator() throws RecognitionException {
		Conversion_operator_declaratorContext _localctx = new Conversion_operator_declaratorContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_conversion_operator_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1737);
			_la = _input.LA(1);
			if ( !(_la==EXPLICIT || _la==IMPLICIT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1738);
			match(OPERATOR);
			setState(1739);
			type_();
			setState(1740);
			match(OPEN_PARENS);
			setState(1741);
			arg_declaration();
			setState(1742);
			match(CLOSE_PARENS);
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

	public static class Constructor_initializerContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Constructor_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstructor_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constructor_initializerContext constructor_initializer() throws RecognitionException {
		Constructor_initializerContext _localctx = new Constructor_initializerContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_constructor_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1744);
			match(COLON);
			setState(1745);
			_la = _input.LA(1);
			if ( !(_la==BASE || _la==THIS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1746);
			match(OPEN_PARENS);
			setState(1748);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OUT - 65)) | (1L << (PARTIAL - 65)) | (1L << (REF - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
				{
				setState(1747);
				argument_list();
				}
			}

			setState(1750);
			match(CLOSE_PARENS);
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

	public static class BodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_body);
		try {
			setState(1754);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1752);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(1753);
				match(SEMICOLON);
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

	public static class Struct_interfacesContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Interface_type_listContext interface_type_list() {
			return getRuleContext(Interface_type_listContext.class,0);
		}
		public Struct_interfacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_interfaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_interfaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_interfaces(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitStruct_interfaces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_interfacesContext struct_interfaces() throws RecognitionException {
		Struct_interfacesContext _localctx = new Struct_interfacesContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_struct_interfaces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1756);
			match(COLON);
			setState(1757);
			interface_type_list();
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

	public static class Struct_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Struct_member_declarationContext> struct_member_declaration() {
			return getRuleContexts(Struct_member_declarationContext.class);
		}
		public Struct_member_declarationContext struct_member_declaration(int i) {
			return getRuleContext(Struct_member_declarationContext.class,i);
		}
		public Struct_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitStruct_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_bodyContext struct_body() throws RecognitionException {
		Struct_bodyContext _localctx = new Struct_bodyContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_struct_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1759);
			match(OPEN_BRACE);
			setState(1763);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONST) | (1L << DECIMAL) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << ENUM) | (1L << EQUALS) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FIXED) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IMPLICIT) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OVERRIDE - 65)) | (1L << (PARTIAL - 65)) | (1L << (PRIVATE - 65)) | (1L << (PROTECTED - 65)) | (1L << (PUBLIC - 65)) | (1L << (READONLY - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SEALED - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (STATIC - 65)) | (1L << (STRING - 65)) | (1L << (STRUCT - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNSAFE - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VIRTUAL - 65)) | (1L << (VOID - 65)) | (1L << (VOLATILE - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (OPEN_BRACKET - 65)))) != 0)) {
				{
				{
				setState(1760);
				struct_member_declaration();
				}
				}
				setState(1765);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1766);
			match(CLOSE_BRACE);
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

	public static class Struct_member_declarationContext extends ParserRuleContext {
		public Common_member_declarationContext common_member_declaration() {
			return getRuleContext(Common_member_declarationContext.class,0);
		}
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public List<Fixed_size_buffer_declaratorContext> fixed_size_buffer_declarator() {
			return getRuleContexts(Fixed_size_buffer_declaratorContext.class);
		}
		public Fixed_size_buffer_declaratorContext fixed_size_buffer_declarator(int i) {
			return getRuleContext(Fixed_size_buffer_declaratorContext.class,i);
		}
		public Struct_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitStruct_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_member_declarationContext struct_member_declaration() throws RecognitionException {
		Struct_member_declarationContext _localctx = new Struct_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_struct_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1769);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1768);
				attributes();
				}
			}

			setState(1772);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
			case 1:
				{
				setState(1771);
				all_member_modifiers();
				}
				break;
			}
			setState(1784);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CLASS:
			case CONST:
			case DECIMAL:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case ENUM:
			case EQUALS:
			case EVENT:
			case EXPLICIT:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case IMPLICIT:
			case INT:
			case INTERFACE:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case STRUCT:
			case UINT:
			case ULONG:
			case USHORT:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				{
				setState(1774);
				common_member_declaration();
				}
				break;
			case FIXED:
				{
				setState(1775);
				match(FIXED);
				setState(1776);
				type_();
				setState(1778); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1777);
					fixed_size_buffer_declarator();
					}
					}
					setState(1780); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << NAMEOF))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ON - 68)) | (1L << (ORDERBY - 68)) | (1L << (PARTIAL - 68)) | (1L << (REMOVE - 68)) | (1L << (SELECT - 68)) | (1L << (SET - 68)) | (1L << (VAR - 68)) | (1L << (WHEN - 68)) | (1L << (WHERE - 68)) | (1L << (YIELD - 68)) | (1L << (IDENTIFIER - 68)))) != 0) );
				setState(1782);
				match(SEMICOLON);
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

	public static class Array_typeContext extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public Array_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArray_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArray_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitArray_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_typeContext array_type() throws RecognitionException {
		Array_typeContext _localctx = new Array_typeContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_array_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1786);
			base_type();
			setState(1794); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1790);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STAR || _la==INTERR) {
					{
					{
					setState(1787);
					_la = _input.LA(1);
					if ( !(_la==STAR || _la==INTERR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(1792);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1793);
				rank_specifier();
				}
				}
				setState(1796); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & ((1L << (OPEN_BRACKET - 124)) | (1L << (STAR - 124)) | (1L << (INTERR - 124)))) != 0) );
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

	public static class Rank_specifierContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Rank_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rank_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRank_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRank_specifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRank_specifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rank_specifierContext rank_specifier() throws RecognitionException {
		Rank_specifierContext _localctx = new Rank_specifierContext(_ctx, getState());
		enterRule(_localctx, 290, RULE_rank_specifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1798);
			match(OPEN_BRACKET);
			setState(1802);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1799);
				match(COMMA);
				}
				}
				setState(1804);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1805);
			match(CLOSE_BRACKET);
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

	public static class Array_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Variable_initializerContext> variable_initializer() {
			return getRuleContexts(Variable_initializerContext.class);
		}
		public Variable_initializerContext variable_initializer(int i) {
			return getRuleContext(Variable_initializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Array_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArray_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArray_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitArray_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_initializerContext array_initializer() throws RecognitionException {
		Array_initializerContext _localctx = new Array_initializerContext(_ctx, getState());
		enterRule(_localctx, 292, RULE_array_initializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1807);
			match(OPEN_BRACE);
			setState(1819);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_BRACE - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
				{
				setState(1808);
				variable_initializer();
				setState(1813);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,205,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1809);
						match(COMMA);
						setState(1810);
						variable_initializer();
						}
						} 
					}
					setState(1815);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,205,_ctx);
				}
				setState(1817);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1816);
					match(COMMA);
					}
				}

				}
			}

			setState(1821);
			match(CLOSE_BRACE);
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

	public static class Variant_type_parameter_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Variant_type_parameterContext> variant_type_parameter() {
			return getRuleContexts(Variant_type_parameterContext.class);
		}
		public Variant_type_parameterContext variant_type_parameter(int i) {
			return getRuleContext(Variant_type_parameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Variant_type_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant_type_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariant_type_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariant_type_parameter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariant_type_parameter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variant_type_parameter_listContext variant_type_parameter_list() throws RecognitionException {
		Variant_type_parameter_listContext _localctx = new Variant_type_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 294, RULE_variant_type_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1823);
			match(LT);
			setState(1824);
			variant_type_parameter();
			setState(1829);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1825);
				match(COMMA);
				setState(1826);
				variant_type_parameter();
				}
				}
				setState(1831);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1832);
			match(GT);
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

	public static class Variant_type_parameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Variance_annotationContext variance_annotation() {
			return getRuleContext(Variance_annotationContext.class,0);
		}
		public Variant_type_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant_type_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariant_type_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariant_type_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariant_type_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variant_type_parameterContext variant_type_parameter() throws RecognitionException {
		Variant_type_parameterContext _localctx = new Variant_type_parameterContext(_ctx, getState());
		enterRule(_localctx, 296, RULE_variant_type_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1835);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1834);
				attributes();
				}
			}

			setState(1838);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN || _la==OUT) {
				{
				setState(1837);
				variance_annotation();
				}
			}

			setState(1840);
			identifier();
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

	public static class Variance_annotationContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public Variance_annotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variance_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariance_annotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariance_annotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitVariance_annotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variance_annotationContext variance_annotation() throws RecognitionException {
		Variance_annotationContext _localctx = new Variance_annotationContext(_ctx, getState());
		enterRule(_localctx, 298, RULE_variance_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1842);
			_la = _input.LA(1);
			if ( !(_la==IN || _la==OUT) ) {
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

	public static class Interface_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Interface_type_listContext interface_type_list() {
			return getRuleContext(Interface_type_listContext.class,0);
		}
		public Interface_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_base(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_base(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_baseContext interface_base() throws RecognitionException {
		Interface_baseContext _localctx = new Interface_baseContext(_ctx, getState());
		enterRule(_localctx, 300, RULE_interface_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1844);
			match(COLON);
			setState(1845);
			interface_type_list();
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

	public static class Interface_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Interface_member_declarationContext> interface_member_declaration() {
			return getRuleContexts(Interface_member_declarationContext.class);
		}
		public Interface_member_declarationContext interface_member_declaration(int i) {
			return getRuleContext(Interface_member_declarationContext.class,i);
		}
		public Interface_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_bodyContext interface_body() throws RecognitionException {
		Interface_bodyContext _localctx = new Interface_bodyContext(_ctx, getState());
		enterRule(_localctx, 302, RULE_interface_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1847);
			match(OPEN_BRACE);
			setState(1851);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << EVENT) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (STRING - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNSAFE - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (OPEN_BRACKET - 65)))) != 0)) {
				{
				{
				setState(1848);
				interface_member_declaration();
				}
				}
				setState(1853);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1854);
			match(CLOSE_BRACE);
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

	public static class Interface_member_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Interface_accessorsContext interface_accessors() {
			return getRuleContext(Interface_accessorsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Interface_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_member_declarationContext interface_member_declaration() throws RecognitionException {
		Interface_member_declarationContext _localctx = new Interface_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 304, RULE_interface_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1857);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1856);
				attributes();
				}
			}

			setState(1860);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW) {
				{
				setState(1859);
				match(NEW);
				}
			}

			setState(1918);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,223,_ctx) ) {
			case 1:
				{
				setState(1863);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSAFE) {
					{
					setState(1862);
					match(UNSAFE);
					}
				}

				setState(1865);
				type_();
				setState(1893);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,218,_ctx) ) {
				case 1:
					{
					setState(1866);
					identifier();
					setState(1868);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1867);
						type_parameter_list();
						}
					}

					setState(1870);
					match(OPEN_PARENS);
					setState(1872);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARAMS - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (THIS - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)) | (1L << (OPEN_BRACKET - 67)))) != 0)) {
						{
						setState(1871);
						formal_parameter_list();
						}
					}

					setState(1874);
					match(CLOSE_PARENS);
					setState(1876);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WHERE) {
						{
						setState(1875);
						type_parameter_constraints_clauses();
						}
					}

					setState(1878);
					match(SEMICOLON);
					}
					break;
				case 2:
					{
					setState(1880);
					identifier();
					setState(1881);
					match(OPEN_BRACE);
					setState(1882);
					interface_accessors();
					setState(1883);
					match(CLOSE_BRACE);
					}
					break;
				case 3:
					{
					setState(1885);
					match(THIS);
					setState(1886);
					match(OPEN_BRACKET);
					setState(1887);
					formal_parameter_list();
					setState(1888);
					match(CLOSE_BRACKET);
					setState(1889);
					match(OPEN_BRACE);
					setState(1890);
					interface_accessors();
					setState(1891);
					match(CLOSE_BRACE);
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(1896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSAFE) {
					{
					setState(1895);
					match(UNSAFE);
					}
				}

				setState(1898);
				match(VOID);
				setState(1899);
				identifier();
				setState(1901);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1900);
					type_parameter_list();
					}
				}

				setState(1903);
				match(OPEN_PARENS);
				setState(1905);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARAMS - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (THIS - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)) | (1L << (OPEN_BRACKET - 67)))) != 0)) {
					{
					setState(1904);
					formal_parameter_list();
					}
				}

				setState(1907);
				match(CLOSE_PARENS);
				setState(1909);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(1908);
					type_parameter_constraints_clauses();
					}
				}

				setState(1911);
				match(SEMICOLON);
				}
				break;
			case 3:
				{
				setState(1913);
				match(EVENT);
				setState(1914);
				type_();
				setState(1915);
				identifier();
				setState(1916);
				match(SEMICOLON);
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

	public static class Interface_accessorsContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CSharpParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CSharpParser.SEMICOLON, i);
		}
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public List<AttributesContext> attributes() {
			return getRuleContexts(AttributesContext.class);
		}
		public AttributesContext attributes(int i) {
			return getRuleContext(AttributesContext.class,i);
		}
		public Interface_accessorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_accessors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_accessors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_accessors(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_accessors(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_accessorsContext interface_accessors() throws RecognitionException {
		Interface_accessorsContext _localctx = new Interface_accessorsContext(_ctx, getState());
		enterRule(_localctx, 306, RULE_interface_accessors);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1921);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1920);
				attributes();
				}
			}

			setState(1941);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(1923);
				match(GET);
				setState(1924);
				match(SEMICOLON);
				setState(1930);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SET || _la==OPEN_BRACKET) {
					{
					setState(1926);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OPEN_BRACKET) {
						{
						setState(1925);
						attributes();
						}
					}

					setState(1928);
					match(SET);
					setState(1929);
					match(SEMICOLON);
					}
				}

				}
				break;
			case SET:
				{
				setState(1932);
				match(SET);
				setState(1933);
				match(SEMICOLON);
				setState(1939);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GET || _la==OPEN_BRACKET) {
					{
					setState(1935);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OPEN_BRACKET) {
						{
						setState(1934);
						attributes();
						}
					}

					setState(1937);
					match(GET);
					setState(1938);
					match(SEMICOLON);
					}
				}

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

	public static class Enum_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Enum_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_base(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEnum_base(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_baseContext enum_base() throws RecognitionException {
		Enum_baseContext _localctx = new Enum_baseContext(_ctx, getState());
		enterRule(_localctx, 308, RULE_enum_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1943);
			match(COLON);
			setState(1944);
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

	public static class Enum_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Enum_member_declarationContext> enum_member_declaration() {
			return getRuleContexts(Enum_member_declarationContext.class);
		}
		public Enum_member_declarationContext enum_member_declaration(int i) {
			return getRuleContext(Enum_member_declarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Enum_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEnum_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_bodyContext enum_body() throws RecognitionException {
		Enum_bodyContext _localctx = new Enum_bodyContext(_ctx, getState());
		enterRule(_localctx, 310, RULE_enum_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1946);
			match(OPEN_BRACE);
			setState(1958);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << NAMEOF))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ON - 68)) | (1L << (ORDERBY - 68)) | (1L << (PARTIAL - 68)) | (1L << (REMOVE - 68)) | (1L << (SELECT - 68)) | (1L << (SET - 68)) | (1L << (VAR - 68)) | (1L << (WHEN - 68)) | (1L << (WHERE - 68)) | (1L << (YIELD - 68)) | (1L << (IDENTIFIER - 68)) | (1L << (OPEN_BRACKET - 68)))) != 0)) {
				{
				setState(1947);
				enum_member_declaration();
				setState(1952);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1948);
						match(COMMA);
						setState(1949);
						enum_member_declaration();
						}
						} 
					}
					setState(1954);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
				}
				setState(1956);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1955);
					match(COMMA);
					}
				}

				}
			}

			setState(1960);
			match(CLOSE_BRACE);
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

	public static class Enum_member_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Enum_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_member_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEnum_member_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_member_declarationContext enum_member_declaration() throws RecognitionException {
		Enum_member_declarationContext _localctx = new Enum_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 312, RULE_enum_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1963);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1962);
				attributes();
				}
			}

			setState(1965);
			identifier();
			setState(1968);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(1966);
				match(ASSIGNMENT);
				setState(1967);
				expression();
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

	public static class Global_attribute_sectionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Global_attribute_targetContext global_attribute_target() {
			return getRuleContext(Global_attribute_targetContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Global_attribute_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_attribute_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGlobal_attribute_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGlobal_attribute_section(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGlobal_attribute_section(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_attribute_sectionContext global_attribute_section() throws RecognitionException {
		Global_attribute_sectionContext _localctx = new Global_attribute_sectionContext(_ctx, getState());
		enterRule(_localctx, 314, RULE_global_attribute_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1970);
			match(OPEN_BRACKET);
			setState(1971);
			global_attribute_target();
			setState(1972);
			match(COLON);
			setState(1973);
			attribute_list();
			setState(1975);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1974);
				match(COMMA);
				}
			}

			setState(1977);
			match(CLOSE_BRACKET);
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

	public static class Global_attribute_targetContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Global_attribute_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_attribute_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGlobal_attribute_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGlobal_attribute_target(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitGlobal_attribute_target(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_attribute_targetContext global_attribute_target() throws RecognitionException {
		Global_attribute_targetContext _localctx = new Global_attribute_targetContext(_ctx, getState());
		enterRule(_localctx, 316, RULE_global_attribute_target);
		try {
			setState(1981);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case AS:
			case BASE:
			case BOOL:
			case BREAK:
			case BYTE:
			case CASE:
			case CATCH:
			case CHAR:
			case CHECKED:
			case CLASS:
			case CONST:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DO:
			case DOUBLE:
			case ELSE:
			case ENUM:
			case EVENT:
			case EXPLICIT:
			case EXTERN:
			case FALSE:
			case FINALLY:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case GOTO:
			case IF:
			case IMPLICIT:
			case IN:
			case INT:
			case INTERFACE:
			case INTERNAL:
			case IS:
			case LOCK:
			case LONG:
			case NAMESPACE:
			case NEW:
			case NULL:
			case OBJECT:
			case OPERATOR:
			case OUT:
			case OVERRIDE:
			case PARAMS:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case READONLY:
			case REF:
			case RETURN:
			case SBYTE:
			case SEALED:
			case SHORT:
			case SIZEOF:
			case STACKALLOC:
			case STATIC:
			case STRING:
			case STRUCT:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VIRTUAL:
			case VOID:
			case VOLATILE:
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1979);
				keyword();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1980);
				identifier();
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

	public static class AttributesContext extends ParserRuleContext {
		public List<Attribute_sectionContext> attribute_section() {
			return getRuleContexts(Attribute_sectionContext.class);
		}
		public Attribute_sectionContext attribute_section(int i) {
			return getRuleContext(Attribute_sectionContext.class,i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 318, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1984); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1983);
				attribute_section();
				}
				}
				setState(1986); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPEN_BRACKET );
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

	public static class Attribute_sectionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Attribute_targetContext attribute_target() {
			return getRuleContext(Attribute_targetContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Attribute_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_section(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttribute_section(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_sectionContext attribute_section() throws RecognitionException {
		Attribute_sectionContext _localctx = new Attribute_sectionContext(_ctx, getState());
		enterRule(_localctx, 320, RULE_attribute_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1988);
			match(OPEN_BRACKET);
			setState(1992);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,238,_ctx) ) {
			case 1:
				{
				setState(1989);
				attribute_target();
				setState(1990);
				match(COLON);
				}
				break;
			}
			setState(1994);
			attribute_list();
			setState(1996);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1995);
				match(COMMA);
				}
			}

			setState(1998);
			match(CLOSE_BRACKET);
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

	public static class Attribute_targetContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Attribute_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_target(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttribute_target(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_targetContext attribute_target() throws RecognitionException {
		Attribute_targetContext _localctx = new Attribute_targetContext(_ctx, getState());
		enterRule(_localctx, 322, RULE_attribute_target);
		try {
			setState(2002);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case AS:
			case BASE:
			case BOOL:
			case BREAK:
			case BYTE:
			case CASE:
			case CATCH:
			case CHAR:
			case CHECKED:
			case CLASS:
			case CONST:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DO:
			case DOUBLE:
			case ELSE:
			case ENUM:
			case EVENT:
			case EXPLICIT:
			case EXTERN:
			case FALSE:
			case FINALLY:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case GOTO:
			case IF:
			case IMPLICIT:
			case IN:
			case INT:
			case INTERFACE:
			case INTERNAL:
			case IS:
			case LOCK:
			case LONG:
			case NAMESPACE:
			case NEW:
			case NULL:
			case OBJECT:
			case OPERATOR:
			case OUT:
			case OVERRIDE:
			case PARAMS:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case READONLY:
			case REF:
			case RETURN:
			case SBYTE:
			case SEALED:
			case SHORT:
			case SIZEOF:
			case STACKALLOC:
			case STATIC:
			case STRING:
			case STRUCT:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VIRTUAL:
			case VOID:
			case VOLATILE:
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2000);
				keyword();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(2001);
				identifier();
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

	public static class Attribute_listContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Attribute_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttribute_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_listContext attribute_list() throws RecognitionException {
		Attribute_listContext _localctx = new Attribute_listContext(_ctx, getState());
		enterRule(_localctx, 324, RULE_attribute_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2004);
			attribute();
			setState(2009);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,241,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2005);
					match(COMMA);
					setState(2006);
					attribute();
					}
					} 
				}
				setState(2011);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,241,_ctx);
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

	public static class AttributeContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<Attribute_argumentContext> attribute_argument() {
			return getRuleContexts(Attribute_argumentContext.class);
		}
		public Attribute_argumentContext attribute_argument(int i) {
			return getRuleContext(Attribute_argumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 326, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2012);
			namespace_or_type_name();
			setState(2025);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PARENS) {
				{
				setState(2013);
				match(OPEN_PARENS);
				setState(2022);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
					{
					setState(2014);
					attribute_argument();
					setState(2019);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(2015);
						match(COMMA);
						setState(2016);
						attribute_argument();
						}
						}
						setState(2021);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(2024);
				match(CLOSE_PARENS);
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

	public static class Attribute_argumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Attribute_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitAttribute_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Attribute_argumentContext attribute_argument() throws RecognitionException {
		Attribute_argumentContext _localctx = new Attribute_argumentContext(_ctx, getState());
		enterRule(_localctx, 328, RULE_attribute_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2030);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,245,_ctx) ) {
			case 1:
				{
				setState(2027);
				identifier();
				setState(2028);
				match(COLON);
				}
				break;
			}
			setState(2032);
			expression();
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

	public static class Pointer_typeContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Pointer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPointer_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPointer_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitPointer_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pointer_typeContext pointer_type() throws RecognitionException {
		Pointer_typeContext _localctx = new Pointer_typeContext(_ctx, getState());
		enterRule(_localctx, 330, RULE_pointer_type);
		int _la;
		try {
			setState(2049);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case UINT:
			case ULONG:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(2036);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOL:
				case BYTE:
				case CHAR:
				case DECIMAL:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SBYTE:
				case SHORT:
				case UINT:
				case ULONG:
				case USHORT:
					{
					setState(2034);
					simple_type();
					}
					break;
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BY:
				case DESCENDING:
				case DYNAMIC:
				case EQUALS:
				case FROM:
				case GET:
				case GROUP:
				case INTO:
				case JOIN:
				case LET:
				case NAMEOF:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SELECT:
				case SET:
				case STRING:
				case VAR:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(2035);
					class_type();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(2042);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_BRACKET || _la==INTERR) {
					{
					setState(2040);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPEN_BRACKET:
						{
						setState(2038);
						rank_specifier();
						}
						break;
					case INTERR:
						{
						setState(2039);
						match(INTERR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(2044);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2045);
				match(STAR);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(2047);
				match(VOID);
				setState(2048);
				match(STAR);
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

	public static class Fixed_pointer_declaratorsContext extends ParserRuleContext {
		public List<Fixed_pointer_declaratorContext> fixed_pointer_declarator() {
			return getRuleContexts(Fixed_pointer_declaratorContext.class);
		}
		public Fixed_pointer_declaratorContext fixed_pointer_declarator(int i) {
			return getRuleContext(Fixed_pointer_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Fixed_pointer_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_declarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_pointer_declarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_pointer_declaratorsContext fixed_pointer_declarators() throws RecognitionException {
		Fixed_pointer_declaratorsContext _localctx = new Fixed_pointer_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 332, RULE_fixed_pointer_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2051);
			fixed_pointer_declarator();
			setState(2056);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2052);
				match(COMMA);
				setState(2053);
				fixed_pointer_declarator();
				}
				}
				setState(2058);
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

	public static class Fixed_pointer_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Fixed_pointer_initializerContext fixed_pointer_initializer() {
			return getRuleContext(Fixed_pointer_initializerContext.class,0);
		}
		public Fixed_pointer_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_pointer_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_pointer_declaratorContext fixed_pointer_declarator() throws RecognitionException {
		Fixed_pointer_declaratorContext _localctx = new Fixed_pointer_declaratorContext(_ctx, getState());
		enterRule(_localctx, 334, RULE_fixed_pointer_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2059);
			identifier();
			setState(2060);
			match(ASSIGNMENT);
			setState(2061);
			fixed_pointer_initializer();
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

	public static class Fixed_pointer_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public Local_variable_initializer_unsafeContext local_variable_initializer_unsafe() {
			return getRuleContext(Local_variable_initializer_unsafeContext.class,0);
		}
		public Fixed_pointer_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_pointer_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_pointer_initializerContext fixed_pointer_initializer() throws RecognitionException {
		Fixed_pointer_initializerContext _localctx = new Fixed_pointer_initializerContext(_ctx, getState());
		enterRule(_localctx, 336, RULE_fixed_pointer_initializer);
		try {
			setState(2068);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(2064);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,251,_ctx) ) {
				case 1:
					{
					setState(2063);
					match(AMP);
					}
					break;
				}
				setState(2066);
				expression();
				}
				break;
			case STACKALLOC:
				enterOuterAlt(_localctx, 2);
				{
				setState(2067);
				local_variable_initializer_unsafe();
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

	public static class Fixed_size_buffer_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Fixed_size_buffer_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_size_buffer_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_size_buffer_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_size_buffer_declarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitFixed_size_buffer_declarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fixed_size_buffer_declaratorContext fixed_size_buffer_declarator() throws RecognitionException {
		Fixed_size_buffer_declaratorContext _localctx = new Fixed_size_buffer_declaratorContext(_ctx, getState());
		enterRule(_localctx, 338, RULE_fixed_size_buffer_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2070);
			identifier();
			setState(2071);
			match(OPEN_BRACKET);
			setState(2072);
			expression();
			setState(2073);
			match(CLOSE_BRACKET);
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

	public static class Local_variable_initializer_unsafeContext extends ParserRuleContext {
		public TerminalNode STACKALLOC() { return getToken(CSharpParser.STACKALLOC, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Local_variable_initializer_unsafeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_initializer_unsafe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_initializer_unsafe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_initializer_unsafe(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLocal_variable_initializer_unsafe(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_variable_initializer_unsafeContext local_variable_initializer_unsafe() throws RecognitionException {
		Local_variable_initializer_unsafeContext _localctx = new Local_variable_initializer_unsafeContext(_ctx, getState());
		enterRule(_localctx, 340, RULE_local_variable_initializer_unsafe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2075);
			match(STACKALLOC);
			setState(2076);
			type_();
			setState(2077);
			match(OPEN_BRACKET);
			setState(2078);
			expression();
			setState(2079);
			match(CLOSE_BRACKET);
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

	public static class Right_arrowContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public Right_arrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_arrow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_arrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_arrow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRight_arrow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Right_arrowContext right_arrow() throws RecognitionException {
		Right_arrowContext _localctx = new Right_arrowContext(_ctx, getState());
		enterRule(_localctx, 342, RULE_right_arrow);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2081);
			((Right_arrowContext)_localctx).first = match(ASSIGNMENT);
			setState(2082);
			((Right_arrowContext)_localctx).second = match(GT);
			setState(2083);
			if (!((((Right_arrowContext)_localctx).first!=null?((Right_arrowContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_arrowContext)_localctx).second!=null?((Right_arrowContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
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

	public static class Right_shiftContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public List<TerminalNode> GT() { return getTokens(CSharpParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CSharpParser.GT, i);
		}
		public Right_shiftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_shift; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_shift(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_shift(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRight_shift(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Right_shiftContext right_shift() throws RecognitionException {
		Right_shiftContext _localctx = new Right_shiftContext(_ctx, getState());
		enterRule(_localctx, 344, RULE_right_shift);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2085);
			((Right_shiftContext)_localctx).first = match(GT);
			setState(2086);
			((Right_shiftContext)_localctx).second = match(GT);
			setState(2087);
			if (!((((Right_shiftContext)_localctx).first!=null?((Right_shiftContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shiftContext)_localctx).second!=null?((Right_shiftContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
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

	public static class Right_shift_assignmentContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public TerminalNode OP_GE() { return getToken(CSharpParser.OP_GE, 0); }
		public Right_shift_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_shift_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_shift_assignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_shift_assignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitRight_shift_assignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Right_shift_assignmentContext right_shift_assignment() throws RecognitionException {
		Right_shift_assignmentContext _localctx = new Right_shift_assignmentContext(_ctx, getState());
		enterRule(_localctx, 346, RULE_right_shift_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2089);
			((Right_shift_assignmentContext)_localctx).first = match(GT);
			setState(2090);
			((Right_shift_assignmentContext)_localctx).second = match(OP_GE);
			setState(2091);
			if (!((((Right_shift_assignmentContext)_localctx).first!=null?((Right_shift_assignmentContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shift_assignmentContext)_localctx).second!=null?((Right_shift_assignmentContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
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
		public Boolean_literalContext boolean_literal() {
			return getRuleContext(Boolean_literalContext.class,0);
		}
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public TerminalNode INTEGER_LITERAL() { return getToken(CSharpParser.INTEGER_LITERAL, 0); }
		public TerminalNode HEX_INTEGER_LITERAL() { return getToken(CSharpParser.HEX_INTEGER_LITERAL, 0); }
		public TerminalNode REAL_LITERAL() { return getToken(CSharpParser.REAL_LITERAL, 0); }
		public TerminalNode CHARACTER_LITERAL() { return getToken(CSharpParser.CHARACTER_LITERAL, 0); }
		public TerminalNode NULL() { return getToken(CSharpParser.NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 348, RULE_literal);
		try {
			setState(2100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2093);
				boolean_literal();
				}
				break;
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(2094);
				string_literal();
				}
				break;
			case INTEGER_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(2095);
				match(INTEGER_LITERAL);
				}
				break;
			case HEX_INTEGER_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(2096);
				match(HEX_INTEGER_LITERAL);
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(2097);
				match(REAL_LITERAL);
				}
				break;
			case CHARACTER_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(2098);
				match(CHARACTER_LITERAL);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 7);
				{
				setState(2099);
				match(NULL);
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

	public static class Boolean_literalContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public Boolean_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBoolean_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBoolean_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitBoolean_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_literalContext boolean_literal() throws RecognitionException {
		Boolean_literalContext _localctx = new Boolean_literalContext(_ctx, getState());
		enterRule(_localctx, 350, RULE_boolean_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2102);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
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

	public static class String_literalContext extends ParserRuleContext {
		public Interpolated_regular_stringContext interpolated_regular_string() {
			return getRuleContext(Interpolated_regular_stringContext.class,0);
		}
		public Interpolated_verbatium_stringContext interpolated_verbatium_string() {
			return getRuleContext(Interpolated_verbatium_stringContext.class,0);
		}
		public TerminalNode REGULAR_STRING() { return getToken(CSharpParser.REGULAR_STRING, 0); }
		public TerminalNode VERBATIUM_STRING() { return getToken(CSharpParser.VERBATIUM_STRING, 0); }
		public String_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitString_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitString_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 352, RULE_string_literal);
		try {
			setState(2108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTERPOLATED_REGULAR_STRING_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(2104);
				interpolated_regular_string();
				}
				break;
			case INTERPOLATED_VERBATIUM_STRING_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(2105);
				interpolated_verbatium_string();
				}
				break;
			case REGULAR_STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(2106);
				match(REGULAR_STRING);
				}
				break;
			case VERBATIUM_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(2107);
				match(VERBATIUM_STRING);
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

	public static class Interpolated_regular_stringContext extends ParserRuleContext {
		public TerminalNode INTERPOLATED_REGULAR_STRING_START() { return getToken(CSharpParser.INTERPOLATED_REGULAR_STRING_START, 0); }
		public TerminalNode DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.DOUBLE_QUOTE_INSIDE, 0); }
		public List<Interpolated_regular_string_partContext> interpolated_regular_string_part() {
			return getRuleContexts(Interpolated_regular_string_partContext.class);
		}
		public Interpolated_regular_string_partContext interpolated_regular_string_part(int i) {
			return getRuleContext(Interpolated_regular_string_partContext.class,i);
		}
		public Interpolated_regular_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_regular_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_regular_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_regular_string(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterpolated_regular_string(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interpolated_regular_stringContext interpolated_regular_string() throws RecognitionException {
		Interpolated_regular_stringContext _localctx = new Interpolated_regular_stringContext(_ctx, getState());
		enterRule(_localctx, 354, RULE_interpolated_regular_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2110);
			match(INTERPOLATED_REGULAR_STRING_START);
			setState(2114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)) | (1L << (DOUBLE_CURLY_INSIDE - 132)) | (1L << (REGULAR_CHAR_INSIDE - 132)) | (1L << (REGULAR_STRING_INSIDE - 132)))) != 0)) {
				{
				{
				setState(2111);
				interpolated_regular_string_part();
				}
				}
				setState(2116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2117);
			match(DOUBLE_QUOTE_INSIDE);
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

	public static class Interpolated_verbatium_stringContext extends ParserRuleContext {
		public TerminalNode INTERPOLATED_VERBATIUM_STRING_START() { return getToken(CSharpParser.INTERPOLATED_VERBATIUM_STRING_START, 0); }
		public TerminalNode DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.DOUBLE_QUOTE_INSIDE, 0); }
		public List<Interpolated_verbatium_string_partContext> interpolated_verbatium_string_part() {
			return getRuleContexts(Interpolated_verbatium_string_partContext.class);
		}
		public Interpolated_verbatium_string_partContext interpolated_verbatium_string_part(int i) {
			return getRuleContext(Interpolated_verbatium_string_partContext.class,i);
		}
		public Interpolated_verbatium_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_verbatium_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_verbatium_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_verbatium_string(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterpolated_verbatium_string(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interpolated_verbatium_stringContext interpolated_verbatium_string() throws RecognitionException {
		Interpolated_verbatium_stringContext _localctx = new Interpolated_verbatium_stringContext(_ctx, getState());
		enterRule(_localctx, 356, RULE_interpolated_verbatium_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2119);
			match(INTERPOLATED_VERBATIUM_STRING_START);
			setState(2123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)) | (1L << (DOUBLE_CURLY_INSIDE - 132)) | (1L << (VERBATIUM_DOUBLE_QUOTE_INSIDE - 132)) | (1L << (VERBATIUM_INSIDE_STRING - 132)))) != 0)) {
				{
				{
				setState(2120);
				interpolated_verbatium_string_part();
				}
				}
				setState(2125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2126);
			match(DOUBLE_QUOTE_INSIDE);
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

	public static class Interpolated_regular_string_partContext extends ParserRuleContext {
		public Interpolated_string_expressionContext interpolated_string_expression() {
			return getRuleContext(Interpolated_string_expressionContext.class,0);
		}
		public TerminalNode DOUBLE_CURLY_INSIDE() { return getToken(CSharpParser.DOUBLE_CURLY_INSIDE, 0); }
		public TerminalNode REGULAR_CHAR_INSIDE() { return getToken(CSharpParser.REGULAR_CHAR_INSIDE, 0); }
		public TerminalNode REGULAR_STRING_INSIDE() { return getToken(CSharpParser.REGULAR_STRING_INSIDE, 0); }
		public Interpolated_regular_string_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_regular_string_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_regular_string_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_regular_string_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterpolated_regular_string_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interpolated_regular_string_partContext interpolated_regular_string_part() throws RecognitionException {
		Interpolated_regular_string_partContext _localctx = new Interpolated_regular_string_partContext(_ctx, getState());
		enterRule(_localctx, 358, RULE_interpolated_regular_string_part);
		try {
			setState(2132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(2128);
				interpolated_string_expression();
				}
				break;
			case DOUBLE_CURLY_INSIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(2129);
				match(DOUBLE_CURLY_INSIDE);
				}
				break;
			case REGULAR_CHAR_INSIDE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2130);
				match(REGULAR_CHAR_INSIDE);
				}
				break;
			case REGULAR_STRING_INSIDE:
				enterOuterAlt(_localctx, 4);
				{
				setState(2131);
				match(REGULAR_STRING_INSIDE);
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

	public static class Interpolated_verbatium_string_partContext extends ParserRuleContext {
		public Interpolated_string_expressionContext interpolated_string_expression() {
			return getRuleContext(Interpolated_string_expressionContext.class,0);
		}
		public TerminalNode DOUBLE_CURLY_INSIDE() { return getToken(CSharpParser.DOUBLE_CURLY_INSIDE, 0); }
		public TerminalNode VERBATIUM_DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.VERBATIUM_DOUBLE_QUOTE_INSIDE, 0); }
		public TerminalNode VERBATIUM_INSIDE_STRING() { return getToken(CSharpParser.VERBATIUM_INSIDE_STRING, 0); }
		public Interpolated_verbatium_string_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_verbatium_string_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_verbatium_string_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_verbatium_string_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterpolated_verbatium_string_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interpolated_verbatium_string_partContext interpolated_verbatium_string_part() throws RecognitionException {
		Interpolated_verbatium_string_partContext _localctx = new Interpolated_verbatium_string_partContext(_ctx, getState());
		enterRule(_localctx, 360, RULE_interpolated_verbatium_string_part);
		try {
			setState(2138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(2134);
				interpolated_string_expression();
				}
				break;
			case DOUBLE_CURLY_INSIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(2135);
				match(DOUBLE_CURLY_INSIDE);
				}
				break;
			case VERBATIUM_DOUBLE_QUOTE_INSIDE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2136);
				match(VERBATIUM_DOUBLE_QUOTE_INSIDE);
				}
				break;
			case VERBATIUM_INSIDE_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(2137);
				match(VERBATIUM_INSIDE_STRING);
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

	public static class Interpolated_string_expressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public List<TerminalNode> FORMAT_STRING() { return getTokens(CSharpParser.FORMAT_STRING); }
		public TerminalNode FORMAT_STRING(int i) {
			return getToken(CSharpParser.FORMAT_STRING, i);
		}
		public Interpolated_string_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_string_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_string_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_string_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterpolated_string_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interpolated_string_expressionContext interpolated_string_expression() throws RecognitionException {
		Interpolated_string_expressionContext _localctx = new Interpolated_string_expressionContext(_ctx, getState());
		enterRule(_localctx, 362, RULE_interpolated_string_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2140);
			expression();
			setState(2145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2141);
				match(COMMA);
				setState(2142);
				expression();
				}
				}
				setState(2147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2148);
				match(COLON);
				setState(2150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(2149);
					match(FORMAT_STRING);
					}
					}
					setState(2152); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==FORMAT_STRING );
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

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(CSharpParser.ABSTRACT, 0); }
		public TerminalNode AS() { return getToken(CSharpParser.AS, 0); }
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public TerminalNode CONTINUE() { return getToken(CSharpParser.CONTINUE, 0); }
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public TerminalNode DO() { return getToken(CSharpParser.DO, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public TerminalNode ELSE() { return getToken(CSharpParser.ELSE, 0); }
		public TerminalNode ENUM() { return getToken(CSharpParser.ENUM, 0); }
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public TerminalNode EXPLICIT() { return getToken(CSharpParser.EXPLICIT, 0); }
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public TerminalNode FINALLY() { return getToken(CSharpParser.FINALLY, 0); }
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode FOR() { return getToken(CSharpParser.FOR, 0); }
		public TerminalNode FOREACH() { return getToken(CSharpParser.FOREACH, 0); }
		public TerminalNode GOTO() { return getToken(CSharpParser.GOTO, 0); }
		public TerminalNode IF() { return getToken(CSharpParser.IF, 0); }
		public TerminalNode IMPLICIT() { return getToken(CSharpParser.IMPLICIT, 0); }
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode INTERFACE() { return getToken(CSharpParser.INTERFACE, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode IS() { return getToken(CSharpParser.IS, 0); }
		public TerminalNode LOCK() { return getToken(CSharpParser.LOCK, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode NAMESPACE() { return getToken(CSharpParser.NAMESPACE, 0); }
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode NULL() { return getToken(CSharpParser.NULL, 0); }
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode OVERRIDE() { return getToken(CSharpParser.OVERRIDE, 0); }
		public TerminalNode PARAMS() { return getToken(CSharpParser.PARAMS, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode PUBLIC() { return getToken(CSharpParser.PUBLIC, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode SEALED() { return getToken(CSharpParser.SEALED, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode SIZEOF() { return getToken(CSharpParser.SIZEOF, 0); }
		public TerminalNode STACKALLOC() { return getToken(CSharpParser.STACKALLOC, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public TerminalNode SWITCH() { return getToken(CSharpParser.SWITCH, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode THROW() { return getToken(CSharpParser.THROW, 0); }
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode TRY() { return getToken(CSharpParser.TRY, 0); }
		public TerminalNode TYPEOF() { return getToken(CSharpParser.TYPEOF, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode VIRTUAL() { return getToken(CSharpParser.VIRTUAL, 0); }
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TerminalNode VOLATILE() { return getToken(CSharpParser.VOLATILE, 0); }
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 364, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2156);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << AS) | (1L << BASE) | (1L << BOOL) | (1L << BREAK) | (1L << BYTE) | (1L << CASE) | (1L << CATCH) | (1L << CHAR) | (1L << CHECKED) | (1L << CLASS) | (1L << CONST) | (1L << CONTINUE) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DO) | (1L << DOUBLE) | (1L << ELSE) | (1L << ENUM) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FALSE) | (1L << FINALLY) | (1L << FIXED) | (1L << FLOAT) | (1L << FOR) | (1L << FOREACH) | (1L << GOTO) | (1L << IF) | (1L << IMPLICIT) | (1L << IN) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << IS) | (1L << LOCK) | (1L << LONG))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (NAMESPACE - 64)) | (1L << (NEW - 64)) | (1L << (NULL - 64)) | (1L << (OBJECT - 64)) | (1L << (OPERATOR - 64)) | (1L << (OUT - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARAMS - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (REF - 64)) | (1L << (RETURN - 64)) | (1L << (SBYTE - 64)) | (1L << (SEALED - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STACKALLOC - 64)) | (1L << (STATIC - 64)) | (1L << (STRING - 64)) | (1L << (STRUCT - 64)) | (1L << (SWITCH - 64)) | (1L << (THIS - 64)) | (1L << (THROW - 64)) | (1L << (TRUE - 64)) | (1L << (TRY - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNSAFE - 64)) | (1L << (USHORT - 64)) | (1L << (USING - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOID - 64)) | (1L << (VOLATILE - 64)) | (1L << (WHILE - 64)))) != 0)) ) {
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

	public static class Class_definitionContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Class_baseContext class_base() {
			return getRuleContext(Class_baseContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Class_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitClass_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_definitionContext class_definition() throws RecognitionException {
		Class_definitionContext _localctx = new Class_definitionContext(_ctx, getState());
		enterRule(_localctx, 366, RULE_class_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2158);
			match(CLASS);
			setState(2159);
			identifier();
			setState(2161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2160);
				type_parameter_list();
				}
			}

			setState(2164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2163);
				class_base();
				}
			}

			setState(2167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2166);
				type_parameter_constraints_clauses();
				}
			}

			setState(2169);
			class_body();
			setState(2171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2170);
				match(SEMICOLON);
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

	public static class Struct_definitionContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Struct_bodyContext struct_body() {
			return getRuleContext(Struct_bodyContext.class,0);
		}
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Struct_interfacesContext struct_interfaces() {
			return getRuleContext(Struct_interfacesContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Struct_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitStruct_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_definitionContext struct_definition() throws RecognitionException {
		Struct_definitionContext _localctx = new Struct_definitionContext(_ctx, getState());
		enterRule(_localctx, 368, RULE_struct_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2173);
			match(STRUCT);
			setState(2174);
			identifier();
			setState(2176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2175);
				type_parameter_list();
				}
			}

			setState(2179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2178);
				struct_interfaces();
				}
			}

			setState(2182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2181);
				type_parameter_constraints_clauses();
				}
			}

			setState(2184);
			struct_body();
			setState(2186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2185);
				match(SEMICOLON);
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

	public static class Interface_definitionContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(CSharpParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Interface_bodyContext interface_body() {
			return getRuleContext(Interface_bodyContext.class,0);
		}
		public Variant_type_parameter_listContext variant_type_parameter_list() {
			return getRuleContext(Variant_type_parameter_listContext.class,0);
		}
		public Interface_baseContext interface_base() {
			return getRuleContext(Interface_baseContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Interface_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitInterface_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interface_definitionContext interface_definition() throws RecognitionException {
		Interface_definitionContext _localctx = new Interface_definitionContext(_ctx, getState());
		enterRule(_localctx, 370, RULE_interface_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2188);
			match(INTERFACE);
			setState(2189);
			identifier();
			setState(2191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2190);
				variant_type_parameter_list();
				}
			}

			setState(2194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2193);
				interface_base();
				}
			}

			setState(2197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2196);
				type_parameter_constraints_clauses();
				}
			}

			setState(2199);
			interface_body();
			setState(2201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2200);
				match(SEMICOLON);
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

	public static class Enum_definitionContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(CSharpParser.ENUM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Enum_bodyContext enum_body() {
			return getRuleContext(Enum_bodyContext.class,0);
		}
		public Enum_baseContext enum_base() {
			return getRuleContext(Enum_baseContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Enum_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEnum_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_definitionContext enum_definition() throws RecognitionException {
		Enum_definitionContext _localctx = new Enum_definitionContext(_ctx, getState());
		enterRule(_localctx, 372, RULE_enum_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2203);
			match(ENUM);
			setState(2204);
			identifier();
			setState(2206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2205);
				enum_base();
				}
			}

			setState(2208);
			enum_body();
			setState(2210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2209);
				match(SEMICOLON);
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

	public static class Delegate_definitionContext extends ParserRuleContext {
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Variant_type_parameter_listContext variant_type_parameter_list() {
			return getRuleContext(Variant_type_parameter_listContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Delegate_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegate_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDelegate_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDelegate_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitDelegate_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delegate_definitionContext delegate_definition() throws RecognitionException {
		Delegate_definitionContext _localctx = new Delegate_definitionContext(_ctx, getState());
		enterRule(_localctx, 374, RULE_delegate_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2212);
			match(DELEGATE);
			setState(2213);
			return_type();
			setState(2214);
			identifier();
			setState(2216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2215);
				variant_type_parameter_list();
				}
			}

			setState(2218);
			match(OPEN_PARENS);
			setState(2220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARAMS - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (THIS - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)) | (1L << (OPEN_BRACKET - 67)))) != 0)) {
				{
				setState(2219);
				formal_parameter_list();
				}
			}

			setState(2222);
			match(CLOSE_PARENS);
			setState(2224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2223);
				type_parameter_constraints_clauses();
				}
			}

			setState(2226);
			match(SEMICOLON);
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

	public static class Event_declarationContext extends ParserRuleContext {
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Variable_declaratorsContext variable_declarators() {
			return getRuleContext(Variable_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Member_nameContext member_name() {
			return getRuleContext(Member_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Event_accessor_declarationsContext event_accessor_declarations() {
			return getRuleContext(Event_accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Event_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEvent_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEvent_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitEvent_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Event_declarationContext event_declaration() throws RecognitionException {
		Event_declarationContext _localctx = new Event_declarationContext(_ctx, getState());
		enterRule(_localctx, 376, RULE_event_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2228);
			match(EVENT);
			setState(2229);
			type_();
			setState(2238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,279,_ctx) ) {
			case 1:
				{
				setState(2230);
				variable_declarators();
				setState(2231);
				match(SEMICOLON);
				}
				break;
			case 2:
				{
				setState(2233);
				member_name();
				setState(2234);
				match(OPEN_BRACE);
				setState(2235);
				event_accessor_declarations();
				setState(2236);
				match(CLOSE_BRACE);
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

	public static class Field_declarationContext extends ParserRuleContext {
		public Variable_declaratorsContext variable_declarators() {
			return getRuleContext(Variable_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Field_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterField_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitField_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitField_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_declarationContext field_declaration() throws RecognitionException {
		Field_declarationContext _localctx = new Field_declarationContext(_ctx, getState());
		enterRule(_localctx, 378, RULE_field_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2240);
			variable_declarators();
			setState(2241);
			match(SEMICOLON);
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

	public static class Property_declarationContext extends ParserRuleContext {
		public Member_nameContext member_name() {
			return getRuleContext(Member_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Accessor_declarationsContext accessor_declarations() {
			return getRuleContext(Accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Property_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterProperty_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitProperty_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitProperty_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Property_declarationContext property_declaration() throws RecognitionException {
		Property_declarationContext _localctx = new Property_declarationContext(_ctx, getState());
		enterRule(_localctx, 380, RULE_property_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2243);
			member_name();
			setState(2257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				{
				setState(2244);
				match(OPEN_BRACE);
				setState(2245);
				accessor_declarations();
				setState(2246);
				match(CLOSE_BRACE);
				setState(2251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGNMENT) {
					{
					setState(2247);
					match(ASSIGNMENT);
					setState(2248);
					variable_initializer();
					setState(2249);
					match(SEMICOLON);
					}
				}

				}
				break;
			case ASSIGNMENT:
				{
				setState(2253);
				right_arrow();
				setState(2254);
				expression();
				setState(2255);
				match(SEMICOLON);
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

	public static class Constant_declarationContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Constant_declaratorsContext constant_declarators() {
			return getRuleContext(Constant_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Constant_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstant_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_declarationContext constant_declaration() throws RecognitionException {
		Constant_declarationContext _localctx = new Constant_declarationContext(_ctx, getState());
		enterRule(_localctx, 382, RULE_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2259);
			match(CONST);
			setState(2260);
			type_();
			setState(2261);
			constant_declarators();
			setState(2262);
			match(SEMICOLON);
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

	public static class Indexer_declarationContext extends ParserRuleContext {
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Accessor_declarationsContext accessor_declarations() {
			return getRuleContext(Accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Indexer_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexer_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIndexer_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIndexer_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIndexer_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Indexer_declarationContext indexer_declaration() throws RecognitionException {
		Indexer_declarationContext _localctx = new Indexer_declarationContext(_ctx, getState());
		enterRule(_localctx, 384, RULE_indexer_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2264);
			match(THIS);
			setState(2265);
			match(OPEN_BRACKET);
			setState(2266);
			formal_parameter_list();
			setState(2267);
			match(CLOSE_BRACKET);
			setState(2276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				{
				setState(2268);
				match(OPEN_BRACE);
				setState(2269);
				accessor_declarations();
				setState(2270);
				match(CLOSE_BRACE);
				}
				break;
			case ASSIGNMENT:
				{
				setState(2272);
				right_arrow();
				setState(2273);
				expression();
				setState(2274);
				match(SEMICOLON);
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

	public static class Destructor_definitionContext extends ParserRuleContext {
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Destructor_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destructor_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDestructor_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDestructor_definition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitDestructor_definition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Destructor_definitionContext destructor_definition() throws RecognitionException {
		Destructor_definitionContext _localctx = new Destructor_definitionContext(_ctx, getState());
		enterRule(_localctx, 386, RULE_destructor_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2278);
			match(TILDE);
			setState(2279);
			identifier();
			setState(2280);
			match(OPEN_PARENS);
			setState(2281);
			match(CLOSE_PARENS);
			setState(2282);
			body();
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

	public static class Constructor_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Constructor_initializerContext constructor_initializer() {
			return getRuleContext(Constructor_initializerContext.class,0);
		}
		public Constructor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitConstructor_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constructor_declarationContext constructor_declaration() throws RecognitionException {
		Constructor_declarationContext _localctx = new Constructor_declarationContext(_ctx, getState());
		enterRule(_localctx, 388, RULE_constructor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2284);
			identifier();
			setState(2285);
			match(OPEN_PARENS);
			setState(2287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARAMS - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (THIS - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)) | (1L << (OPEN_BRACKET - 67)))) != 0)) {
				{
				setState(2286);
				formal_parameter_list();
				}
			}

			setState(2289);
			match(CLOSE_PARENS);
			setState(2291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2290);
				constructor_initializer();
				}
			}

			setState(2293);
			body();
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

	public static class Method_declarationContext extends ParserRuleContext {
		public Method_member_nameContext method_member_name() {
			return getRuleContext(Method_member_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Method_bodyContext method_body() {
			return getRuleContext(Method_bodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Method_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMethod_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_declarationContext method_declaration() throws RecognitionException {
		Method_declarationContext _localctx = new Method_declarationContext(_ctx, getState());
		enterRule(_localctx, 390, RULE_method_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2295);
			method_member_name();
			setState(2297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2296);
				type_parameter_list();
				}
			}

			setState(2299);
			match(OPEN_PARENS);
			setState(2301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (OBJECT - 67)) | (1L << (ON - 67)) | (1L << (ORDERBY - 67)) | (1L << (OUT - 67)) | (1L << (PARAMS - 67)) | (1L << (PARTIAL - 67)) | (1L << (REF - 67)) | (1L << (REMOVE - 67)) | (1L << (SBYTE - 67)) | (1L << (SELECT - 67)) | (1L << (SET - 67)) | (1L << (SHORT - 67)) | (1L << (STRING - 67)) | (1L << (THIS - 67)) | (1L << (UINT - 67)) | (1L << (ULONG - 67)) | (1L << (USHORT - 67)) | (1L << (VAR - 67)) | (1L << (VOID - 67)) | (1L << (WHEN - 67)) | (1L << (WHERE - 67)) | (1L << (YIELD - 67)) | (1L << (IDENTIFIER - 67)) | (1L << (OPEN_BRACKET - 67)))) != 0)) {
				{
				setState(2300);
				formal_parameter_list();
				}
			}

			setState(2303);
			match(CLOSE_PARENS);
			setState(2305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2304);
				type_parameter_constraints_clauses();
				}
			}

			setState(2312);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case SEMICOLON:
				{
				setState(2307);
				method_body();
				}
				break;
			case ASSIGNMENT:
				{
				setState(2308);
				right_arrow();
				setState(2309);
				expression();
				setState(2310);
				match(SEMICOLON);
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

	public static class Method_member_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Type_argument_listContext> type_argument_list() {
			return getRuleContexts(Type_argument_listContext.class);
		}
		public Type_argument_listContext type_argument_list(int i) {
			return getRuleContext(Type_argument_listContext.class,i);
		}
		public Method_member_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_member_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_member_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_member_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMethod_member_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_member_nameContext method_member_name() throws RecognitionException {
		Method_member_nameContext _localctx = new Method_member_nameContext(_ctx, getState());
		enterRule(_localctx, 392, RULE_method_member_name);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,289,_ctx) ) {
			case 1:
				{
				setState(2314);
				identifier();
				}
				break;
			case 2:
				{
				setState(2315);
				identifier();
				setState(2316);
				match(DOUBLE_COLON);
				setState(2317);
				identifier();
				}
				break;
			}
			setState(2328);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,291,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2322);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(2321);
						type_argument_list();
						}
					}

					setState(2324);
					match(DOT);
					setState(2325);
					identifier();
					}
					} 
				}
				setState(2330);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,291,_ctx);
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

	public static class Operator_declarationContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public Overloadable_operatorContext overloadable_operator() {
			return getRuleContext(Overloadable_operatorContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<Arg_declarationContext> arg_declaration() {
			return getRuleContexts(Arg_declarationContext.class);
		}
		public Arg_declarationContext arg_declaration(int i) {
			return getRuleContext(Arg_declarationContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Operator_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOperator_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOperator_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitOperator_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operator_declarationContext operator_declaration() throws RecognitionException {
		Operator_declarationContext _localctx = new Operator_declarationContext(_ctx, getState());
		enterRule(_localctx, 394, RULE_operator_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2331);
			match(OPERATOR);
			setState(2332);
			overloadable_operator();
			setState(2333);
			match(OPEN_PARENS);
			setState(2334);
			arg_declaration();
			setState(2337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2335);
				match(COMMA);
				setState(2336);
				arg_declaration();
				}
			}

			setState(2339);
			match(CLOSE_PARENS);
			setState(2345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case SEMICOLON:
				{
				setState(2340);
				body();
				}
				break;
			case ASSIGNMENT:
				{
				setState(2341);
				right_arrow();
				setState(2342);
				expression();
				setState(2343);
				match(SEMICOLON);
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

	public static class Arg_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Arg_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArg_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArg_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitArg_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arg_declarationContext arg_declaration() throws RecognitionException {
		Arg_declarationContext _localctx = new Arg_declarationContext(_ctx, getState());
		enterRule(_localctx, 396, RULE_arg_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2347);
			type_();
			setState(2348);
			identifier();
			setState(2351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(2349);
				match(ASSIGNMENT);
				setState(2350);
				expression();
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

	public static class Method_invocationContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Method_invocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_invocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_invocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_invocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitMethod_invocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_invocationContext method_invocation() throws RecognitionException {
		Method_invocationContext _localctx = new Method_invocationContext(_ctx, getState());
		enterRule(_localctx, 398, RULE_method_invocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2353);
			match(OPEN_PARENS);
			setState(2355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OUT - 65)) | (1L << (PARTIAL - 65)) | (1L << (REF - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
				{
				setState(2354);
				argument_list();
				}
			}

			setState(2357);
			match(CLOSE_PARENS);
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

	public static class Object_creation_expressionContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public Object_creation_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_creation_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_creation_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_creation_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitObject_creation_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Object_creation_expressionContext object_creation_expression() throws RecognitionException {
		Object_creation_expressionContext _localctx = new Object_creation_expressionContext(_ctx, getState());
		enterRule(_localctx, 400, RULE_object_creation_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2359);
			match(OPEN_PARENS);
			setState(2361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LONG) | (1L << NAMEOF))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NEW - 65)) | (1L << (NULL - 65)) | (1L << (OBJECT - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (OUT - 65)) | (1L << (PARTIAL - 65)) | (1L << (REF - 65)) | (1L << (REMOVE - 65)) | (1L << (SBYTE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (SHORT - 65)) | (1L << (SIZEOF - 65)) | (1L << (STRING - 65)) | (1L << (THIS - 65)) | (1L << (TRUE - 65)) | (1L << (TYPEOF - 65)) | (1L << (UINT - 65)) | (1L << (ULONG - 65)) | (1L << (UNCHECKED - 65)) | (1L << (USHORT - 65)) | (1L << (VAR - 65)) | (1L << (VOID - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)) | (1L << (LITERAL_ACCESS - 65)) | (1L << (INTEGER_LITERAL - 65)) | (1L << (HEX_INTEGER_LITERAL - 65)) | (1L << (REAL_LITERAL - 65)) | (1L << (CHARACTER_LITERAL - 65)) | (1L << (REGULAR_STRING - 65)) | (1L << (VERBATIUM_STRING - 65)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 65)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 65)) | (1L << (OPEN_PARENS - 65)))) != 0) || ((((_la - 132)) & ~0x3f) == 0 && ((1L << (_la - 132)) & ((1L << (PLUS - 132)) | (1L << (MINUS - 132)) | (1L << (STAR - 132)) | (1L << (AMP - 132)) | (1L << (BANG - 132)) | (1L << (TILDE - 132)) | (1L << (OP_INC - 132)) | (1L << (OP_DEC - 132)))) != 0)) {
				{
				setState(2360);
				argument_list();
				}
			}

			setState(2363);
			match(CLOSE_PARENS);
			setState(2365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACE) {
				{
				setState(2364);
				object_or_collection_initializer();
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CSharpParser.IDENTIFIER, 0); }
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public TerminalNode ALIAS() { return getToken(CSharpParser.ALIAS, 0); }
		public TerminalNode ARGLIST() { return getToken(CSharpParser.ARGLIST, 0); }
		public TerminalNode ASCENDING() { return getToken(CSharpParser.ASCENDING, 0); }
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public TerminalNode AWAIT() { return getToken(CSharpParser.AWAIT, 0); }
		public TerminalNode BY() { return getToken(CSharpParser.BY, 0); }
		public TerminalNode DESCENDING() { return getToken(CSharpParser.DESCENDING, 0); }
		public TerminalNode DYNAMIC() { return getToken(CSharpParser.DYNAMIC, 0); }
		public TerminalNode EQUALS() { return getToken(CSharpParser.EQUALS, 0); }
		public TerminalNode FROM() { return getToken(CSharpParser.FROM, 0); }
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public TerminalNode GROUP() { return getToken(CSharpParser.GROUP, 0); }
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public TerminalNode JOIN() { return getToken(CSharpParser.JOIN, 0); }
		public TerminalNode LET() { return getToken(CSharpParser.LET, 0); }
		public TerminalNode NAMEOF() { return getToken(CSharpParser.NAMEOF, 0); }
		public TerminalNode ON() { return getToken(CSharpParser.ON, 0); }
		public TerminalNode ORDERBY() { return getToken(CSharpParser.ORDERBY, 0); }
		public TerminalNode PARTIAL() { return getToken(CSharpParser.PARTIAL, 0); }
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public TerminalNode SELECT() { return getToken(CSharpParser.SELECT, 0); }
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public TerminalNode WHEN() { return getToken(CSharpParser.WHEN, 0); }
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public TerminalNode YIELD() { return getToken(CSharpParser.YIELD, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CSharpParserVisitor ) return ((CSharpParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 402, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2367);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << NAMEOF))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ON - 68)) | (1L << (ORDERBY - 68)) | (1L << (PARTIAL - 68)) | (1L << (REMOVE - 68)) | (1L << (SELECT - 68)) | (1L << (SET - 68)) | (1L << (VAR - 68)) | (1L << (WHEN - 68)) | (1L << (WHERE - 68)) | (1L << (YIELD - 68)) | (1L << (IDENTIFIER - 68)))) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 171:
			return right_arrow_sempred((Right_arrowContext)_localctx, predIndex);
		case 172:
			return right_shift_sempred((Right_shiftContext)_localctx, predIndex);
		case 173:
			return right_shift_assignment_sempred((Right_shift_assignmentContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean right_arrow_sempred(Right_arrowContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return (((Right_arrowContext)_localctx).first!=null?((Right_arrowContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_arrowContext)_localctx).second!=null?((Right_arrowContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}
	private boolean right_shift_sempred(Right_shiftContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return (((Right_shiftContext)_localctx).first!=null?((Right_shiftContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shiftContext)_localctx).second!=null?((Right_shiftContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}
	private boolean right_shift_assignment_sempred(Right_shift_assignmentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return (((Right_shift_assignmentContext)_localctx).first!=null?((Right_shift_assignmentContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shift_assignmentContext)_localctx).second!=null?((Right_shift_assignmentContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00c2\u0944\4\2\t"+
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
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092"+
		"\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096\4\u0097"+
		"\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b\t\u009b"+
		"\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f\4\u00a0"+
		"\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4\t\u00a4"+
		"\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8\4\u00a9"+
		"\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad\t\u00ad"+
		"\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1\4\u00b2"+
		"\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6\t\u00b6"+
		"\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba\4\u00bb"+
		"\t\u00bb\4\u00bc\t\u00bc\4\u00bd\t\u00bd\4\u00be\t\u00be\4\u00bf\t\u00bf"+
		"\4\u00c0\t\u00c0\4\u00c1\t\u00c1\4\u00c2\t\u00c2\4\u00c3\t\u00c3\4\u00c4"+
		"\t\u00c4\4\u00c5\t\u00c5\4\u00c6\t\u00c6\4\u00c7\t\u00c7\4\u00c8\t\u00c8"+
		"\4\u00c9\t\u00c9\4\u00ca\t\u00ca\4\u00cb\t\u00cb\3\2\5\2\u0198\n\2\3\2"+
		"\5\2\u019b\n\2\3\2\5\2\u019e\n\2\3\2\7\2\u01a1\n\2\f\2\16\2\u01a4\13\2"+
		"\3\2\5\2\u01a7\n\2\3\2\3\2\3\3\3\3\5\3\u01ad\n\3\3\3\5\3\u01b0\n\3\3\3"+
		"\3\3\3\3\5\3\u01b5\n\3\7\3\u01b7\n\3\f\3\16\3\u01ba\13\3\3\4\3\4\3\4\3"+
		"\4\7\4\u01c0\n\4\f\4\16\4\u01c3\13\4\3\5\3\5\3\5\3\5\5\5\u01c9\n\5\3\6"+
		"\3\6\5\6\u01cd\n\6\3\7\3\7\3\7\5\7\u01d2\n\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\5\n\u01dc\n\n\3\13\3\13\3\13\3\13\7\13\u01e2\n\13\f\13\16\13\u01e5"+
		"\13\13\3\13\3\13\3\f\3\f\3\f\7\f\u01ec\n\f\f\f\16\f\u01ef\13\f\3\r\3\r"+
		"\3\r\5\r\u01f4\n\r\3\r\5\r\u01f7\n\r\3\r\3\r\5\r\u01fb\n\r\3\r\3\r\3\16"+
		"\3\16\5\16\u0201\n\16\3\17\3\17\3\17\5\17\u0206\n\17\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0217"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u021f\n\22\3\23\3\23\3\23\5\23"+
		"\u0224\n\23\3\24\3\24\3\24\7\24\u0229\n\24\f\24\16\24\u022c\13\24\3\25"+
		"\3\25\3\25\7\25\u0231\n\25\f\25\16\25\u0234\13\25\3\26\3\26\3\26\7\26"+
		"\u0239\n\26\f\26\16\26\u023c\13\26\3\27\3\27\3\27\7\27\u0241\n\27\f\27"+
		"\16\27\u0244\13\27\3\30\3\30\3\30\7\30\u0249\n\30\f\30\16\30\u024c\13"+
		"\30\3\31\3\31\3\31\7\31\u0251\n\31\f\31\16\31\u0254\13\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\7\32\u025d\n\32\f\32\16\32\u0260\13\32\3\33\3"+
		"\33\3\33\5\33\u0265\n\33\3\33\7\33\u0268\n\33\f\33\16\33\u026b\13\33\3"+
		"\34\3\34\3\34\7\34\u0270\n\34\f\34\16\34\u0273\13\34\3\35\3\35\3\35\7"+
		"\35\u0278\n\35\f\35\16\35\u027b\13\35\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\5\36\u0295\n\36\3\37\3\37\7\37\u0299\n\37\f\37\16\37"+
		"\u029c\13\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u02a4\n\37\3\37\7\37\u02a7"+
		"\n\37\f\37\16\37\u02aa\13\37\7\37\u02ac\n\37\f\37\16\37\u02af\13\37\3"+
		" \3 \3 \5 \u02b4\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u02c2\n \3"+
		" \3 \3 \3 \5 \u02c8\n \3 \3 \3 \3 \3 \3 \3 \3 \7 \u02d2\n \f \16 \u02d5"+
		"\13 \3 \5 \u02d8\n \3 \6 \u02db\n \r \16 \u02dc\3 \3 \5 \u02e1\n \3 \3"+
		" \3 \3 \5 \u02e7\n \3 \3 \3 \3 \3 \5 \u02ee\n \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u0301\n \3 \3 \3 \5 \u0306\n \3 \5 \u0309"+
		"\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u0316\n \f \16 \u0319\13 \3 \3"+
		" \3 \5 \u031e\n \3!\5!\u0321\n!\3!\3!\3!\5!\u0326\n!\3\"\5\"\u0329\n\""+
		"\3\"\3\"\3\"\3\"\7\"\u032f\n\"\f\"\16\"\u0332\13\"\3\"\3\"\3#\3#\3#\5"+
		"#\u0339\n#\3#\3#\3$\3$\3%\3%\3%\7%\u0342\n%\f%\16%\u0345\13%\3&\3&\5&"+
		"\u0349\n&\3\'\3\'\3\'\5\'\u034e\n\'\5\'\u0350\n\'\3\'\3\'\3(\3(\3(\7("+
		"\u0357\n(\f(\16(\u035a\13(\3)\3)\3)\3)\3)\5)\u0361\n)\3)\3)\3)\3*\3*\5"+
		"*\u0368\n*\3+\3+\3+\3+\7+\u036e\n+\f+\16+\u0371\13+\3+\5+\u0374\n+\3+"+
		"\3+\3,\3,\3,\3,\3,\5,\u037d\n,\3-\3-\3-\5-\u0382\n-\5-\u0384\n-\3-\3-"+
		"\3.\3.\3.\7.\u038b\n.\f.\16.\u038e\13.\3/\3/\3/\3/\3/\5/\u0395\n/\3\60"+
		"\3\60\5\60\u0399\n\60\3\60\3\60\3\60\5\60\u039e\n\60\5\60\u03a0\n\60\3"+
		"\60\3\60\3\60\5\60\u03a5\n\60\7\60\u03a7\n\60\f\60\16\60\u03aa\13\60\3"+
		"\61\3\61\7\61\u03ae\n\61\f\61\16\61\u03b1\13\61\3\61\3\61\3\62\3\62\3"+
		"\62\7\62\u03b8\n\62\f\62\16\62\u03bb\13\62\3\62\5\62\u03be\n\62\3\63\5"+
		"\63\u03c1\n\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\5\64\u03d2\n\64\3\65\3\65\3\65\7\65\u03d7\n\65\f"+
		"\65\16\65\u03da\13\65\3\66\5\66\u03dd\n\66\3\66\3\66\3\66\3\67\3\67\3"+
		"\67\7\67\u03e5\n\67\f\67\16\67\u03e8\13\67\38\38\58\u03ec\n8\39\39\39"+
		"\3:\3:\5:\u03f3\n:\3:\3:\3:\3:\3;\7;\u03fa\n;\f;\16;\u03fd\13;\3;\3;\5"+
		";\u0401\n;\3<\3<\3<\3<\3<\5<\u0408\n<\3=\3=\3=\3=\3=\3>\3>\3>\3?\3?\5"+
		"?\u0414\n?\3?\3?\3?\3?\3?\3?\3?\3?\3?\5?\u041f\n?\3@\3@\3@\3@\7@\u0425"+
		"\n@\f@\16@\u0428\13@\3A\3A\5A\u042c\nA\3B\3B\3B\3B\3B\3B\3B\5B\u0435\n"+
		"B\3C\3C\3C\3C\3D\3D\3D\5D\u043e\nD\3D\3D\3D\5D\u0443\nD\3E\3E\3E\3E\3"+
		"F\3F\5F\u044b\nF\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u0458\nG\3G\3G\3"+
		"G\3G\3G\3G\7G\u0460\nG\fG\16G\u0463\13G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G"+
		"\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u0478\nG\3G\3G\5G\u047c\nG\3G\3G\5G\u0480"+
		"\nG\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u0496"+
		"\nG\3G\3G\3G\5G\u049b\nG\3G\3G\3G\5G\u04a0\nG\3G\3G\3G\3G\3G\5G\u04a7"+
		"\nG\3G\5G\u04aa\nG\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G"+
		"\3G\3G\3G\5G\u04c0\nG\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u04cc\nG\3H\3H"+
		"\5H\u04d0\nH\3H\3H\3I\3I\3I\3I\7I\u04d8\nI\fI\16I\u04db\13I\3J\3J\5J\u04df"+
		"\nJ\3K\3K\3K\5K\u04e4\nK\3L\3L\3L\5L\u04e9\nL\3M\3M\3M\3M\3N\3N\5N\u04f1"+
		"\nN\3O\6O\u04f4\nO\rO\16O\u04f5\3O\3O\3P\3P\3P\3P\3P\3P\5P\u0500\nP\3"+
		"Q\6Q\u0503\nQ\rQ\16Q\u0504\3R\3R\3R\3R\7R\u050b\nR\fR\16R\u050e\13R\5"+
		"R\u0510\nR\3S\3S\3S\7S\u0515\nS\fS\16S\u0518\13S\3T\3T\7T\u051c\nT\fT"+
		"\16T\u051f\13T\3T\5T\u0522\nT\3T\5T\u0525\nT\3U\3U\3U\3U\5U\u052b\nU\3"+
		"U\3U\5U\u052f\nU\3U\3U\3V\3V\5V\u0535\nV\3V\3V\3W\3W\3W\3W\3W\3X\3X\3"+
		"X\3Y\3Y\5Y\u0543\nY\3Z\3Z\3Z\3Z\5Z\u0549\nZ\3[\3[\3[\7[\u054e\n[\f[\16"+
		"[\u0551\13[\3\\\3\\\5\\\u0555\n\\\3\\\5\\\u0558\n\\\3\\\5\\\u055b\n\\"+
		"\3\\\3\\\3]\6]\u0560\n]\r]\16]\u0561\3^\3^\3^\3^\3^\3_\6_\u056a\n_\r_"+
		"\16_\u056b\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\5`\u057d\n`\3"+
		"a\6a\u0580\na\ra\16a\u0581\3b\3b\5b\u0586\nb\3c\5c\u0589\nc\3c\5c\u058c"+
		"\nc\3c\3c\3c\3c\3c\5c\u0593\nc\3d\3d\3d\3d\5d\u0599\nd\3e\3e\3e\3e\7e"+
		"\u059f\ne\fe\16e\u05a2\13e\3e\3e\3f\5f\u05a7\nf\3f\3f\3g\3g\3g\3g\7g\u05af"+
		"\ng\fg\16g\u05b2\13g\3h\3h\3h\7h\u05b7\nh\fh\16h\u05ba\13h\3i\6i\u05bd"+
		"\ni\ri\16i\u05be\3j\3j\3j\3j\3j\3k\3k\3k\3k\5k\u05ca\nk\3k\3k\5k\u05ce"+
		"\nk\5k\u05d0\nk\3l\3l\3l\5l\u05d5\nl\3m\3m\3m\7m\u05da\nm\fm\16m\u05dd"+
		"\13m\3n\3n\3n\3n\3o\3o\5o\u05e5\no\3o\3o\3p\6p\u05ea\np\rp\16p\u05eb\3"+
		"q\5q\u05ef\nq\3q\5q\u05f2\nq\3q\3q\5q\u05f6\nq\3r\6r\u05f9\nr\rr\16r\u05fa"+
		"\3s\3s\3t\3t\3t\3t\3t\3t\3t\3t\3t\5t\u0608\nt\3t\3t\3t\3t\3t\3t\3t\3t"+
		"\5t\u0612\nt\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\5u\u061e\nu\3v\3v\3v\7v\u0623"+
		"\nv\fv\16v\u0626\13v\3w\3w\3w\3w\3x\3x\3x\7x\u062f\nx\fx\16x\u0632\13"+
		"x\3y\3y\3y\5y\u0637\ny\3z\3z\5z\u063b\nz\3{\3{\5{\u063f\n{\3|\3|\3}\3"+
		"}\5}\u0645\n}\3~\3~\3~\3~\5~\u064b\n~\5~\u064d\n~\3\177\3\177\3\177\7"+
		"\177\u0652\n\177\f\177\16\177\u0655\13\177\3\u0080\5\u0080\u0658\n\u0080"+
		"\3\u0080\5\u0080\u065b\n\u0080\3\u0080\3\u0080\5\u0080\u065f\n\u0080\3"+
		"\u0081\3\u0081\3\u0082\5\u0082\u0664\n\u0082\3\u0082\3\u0082\3\u0082\3"+
		"\u0082\3\u0083\5\u0083\u066b\n\u0083\3\u0083\5\u0083\u066e\n\u0083\3\u0083"+
		"\3\u0083\3\u0083\5\u0083\u0673\n\u0083\3\u0083\3\u0083\3\u0083\5\u0083"+
		"\u0678\n\u0083\5\u0083\u067a\n\u0083\3\u0084\5\u0084\u067d\n\u0084\3\u0084"+
		"\5\u0084\u0680\n\u0084\3\u0084\3\u0084\3\u0084\3\u0085\5\u0085\u0686\n"+
		"\u0085\3\u0085\5\u0085\u0689\n\u0085\3\u0085\3\u0085\3\u0085\3\u0086\3"+
		"\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\5\u0086\u0695\n\u0086\3"+
		"\u0087\3\u0087\5\u0087\u0699\n\u0087\3\u0088\5\u0088\u069c\n\u0088\3\u0088"+
		"\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\5\u0088\u06a6"+
		"\n\u0088\3\u0089\5\u0089\u06a9\n\u0089\3\u0089\3\u0089\3\u0089\3\u008a"+
		"\5\u008a\u06af\n\u008a\3\u008a\3\u008a\3\u008a\3\u008b\3\u008b\3\u008b"+
		"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b"+
		"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b"+
		"\3\u008b\5\u008b\u06ca\n\u008b\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c"+
		"\3\u008c\3\u008c\3\u008d\3\u008d\3\u008d\3\u008d\5\u008d\u06d7\n\u008d"+
		"\3\u008d\3\u008d\3\u008e\3\u008e\5\u008e\u06dd\n\u008e\3\u008f\3\u008f"+
		"\3\u008f\3\u0090\3\u0090\7\u0090\u06e4\n\u0090\f\u0090\16\u0090\u06e7"+
		"\13\u0090\3\u0090\3\u0090\3\u0091\5\u0091\u06ec\n\u0091\3\u0091\5\u0091"+
		"\u06ef\n\u0091\3\u0091\3\u0091\3\u0091\3\u0091\6\u0091\u06f5\n\u0091\r"+
		"\u0091\16\u0091\u06f6\3\u0091\3\u0091\5\u0091\u06fb\n\u0091\3\u0092\3"+
		"\u0092\7\u0092\u06ff\n\u0092\f\u0092\16\u0092\u0702\13\u0092\3\u0092\6"+
		"\u0092\u0705\n\u0092\r\u0092\16\u0092\u0706\3\u0093\3\u0093\7\u0093\u070b"+
		"\n\u0093\f\u0093\16\u0093\u070e\13\u0093\3\u0093\3\u0093\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\7\u0094\u0716\n\u0094\f\u0094\16\u0094\u0719\13\u0094"+
		"\3\u0094\5\u0094\u071c\n\u0094\5\u0094\u071e\n\u0094\3\u0094\3\u0094\3"+
		"\u0095\3\u0095\3\u0095\3\u0095\7\u0095\u0726\n\u0095\f\u0095\16\u0095"+
		"\u0729\13\u0095\3\u0095\3\u0095\3\u0096\5\u0096\u072e\n\u0096\3\u0096"+
		"\5\u0096\u0731\n\u0096\3\u0096\3\u0096\3\u0097\3\u0097\3\u0098\3\u0098"+
		"\3\u0098\3\u0099\3\u0099\7\u0099\u073c\n\u0099\f\u0099\16\u0099\u073f"+
		"\13\u0099\3\u0099\3\u0099\3\u009a\5\u009a\u0744\n\u009a\3\u009a\5\u009a"+
		"\u0747\n\u009a\3\u009a\5\u009a\u074a\n\u009a\3\u009a\3\u009a\3\u009a\5"+
		"\u009a\u074f\n\u009a\3\u009a\3\u009a\5\u009a\u0753\n\u009a\3\u009a\3\u009a"+
		"\5\u009a\u0757\n\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\5\u009a\u0768\n\u009a\3\u009a\5\u009a\u076b\n\u009a\3\u009a\3\u009a\3"+
		"\u009a\5\u009a\u0770\n\u009a\3\u009a\3\u009a\5\u009a\u0774\n\u009a\3\u009a"+
		"\3\u009a\5\u009a\u0778\n\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\3\u009a\3\u009a\5\u009a\u0781\n\u009a\3\u009b\5\u009b\u0784\n\u009b\3"+
		"\u009b\3\u009b\3\u009b\5\u009b\u0789\n\u009b\3\u009b\3\u009b\5\u009b\u078d"+
		"\n\u009b\3\u009b\3\u009b\3\u009b\5\u009b\u0792\n\u009b\3\u009b\3\u009b"+
		"\5\u009b\u0796\n\u009b\5\u009b\u0798\n\u009b\3\u009c\3\u009c\3\u009c\3"+
		"\u009d\3\u009d\3\u009d\3\u009d\7\u009d\u07a1\n\u009d\f\u009d\16\u009d"+
		"\u07a4\13\u009d\3\u009d\5\u009d\u07a7\n\u009d\5\u009d\u07a9\n\u009d\3"+
		"\u009d\3\u009d\3\u009e\5\u009e\u07ae\n\u009e\3\u009e\3\u009e\3\u009e\5"+
		"\u009e\u07b3\n\u009e\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\5\u009f\u07ba"+
		"\n\u009f\3\u009f\3\u009f\3\u00a0\3\u00a0\5\u00a0\u07c0\n\u00a0\3\u00a1"+
		"\6\u00a1\u07c3\n\u00a1\r\u00a1\16\u00a1\u07c4\3\u00a2\3\u00a2\3\u00a2"+
		"\3\u00a2\5\u00a2\u07cb\n\u00a2\3\u00a2\3\u00a2\5\u00a2\u07cf\n\u00a2\3"+
		"\u00a2\3\u00a2\3\u00a3\3\u00a3\5\u00a3\u07d5\n\u00a3\3\u00a4\3\u00a4\3"+
		"\u00a4\7\u00a4\u07da\n\u00a4\f\u00a4\16\u00a4\u07dd\13\u00a4\3\u00a5\3"+
		"\u00a5\3\u00a5\3\u00a5\3\u00a5\7\u00a5\u07e4\n\u00a5\f\u00a5\16\u00a5"+
		"\u07e7\13\u00a5\5\u00a5\u07e9\n\u00a5\3\u00a5\5\u00a5\u07ec\n\u00a5\3"+
		"\u00a6\3\u00a6\3\u00a6\5\u00a6\u07f1\n\u00a6\3\u00a6\3\u00a6\3\u00a7\3"+
		"\u00a7\5\u00a7\u07f7\n\u00a7\3\u00a7\3\u00a7\7\u00a7\u07fb\n\u00a7\f\u00a7"+
		"\16\u00a7\u07fe\13\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\5\u00a7\u0804"+
		"\n\u00a7\3\u00a8\3\u00a8\3\u00a8\7\u00a8\u0809\n\u00a8\f\u00a8\16\u00a8"+
		"\u080c\13\u00a8\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00aa\5\u00aa\u0813"+
		"\n\u00aa\3\u00aa\3\u00aa\5\u00aa\u0817\n\u00aa\3\u00ab\3\u00ab\3\u00ab"+
		"\3\u00ab\3\u00ab\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ad\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00af\3\u00af"+
		"\3\u00af\3\u00af\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0"+
		"\5\u00b0\u0837\n\u00b0\3\u00b1\3\u00b1\3\u00b2\3\u00b2\3\u00b2\3\u00b2"+
		"\5\u00b2\u083f\n\u00b2\3\u00b3\3\u00b3\7\u00b3\u0843\n\u00b3\f\u00b3\16"+
		"\u00b3\u0846\13\u00b3\3\u00b3\3\u00b3\3\u00b4\3\u00b4\7\u00b4\u084c\n"+
		"\u00b4\f\u00b4\16\u00b4\u084f\13\u00b4\3\u00b4\3\u00b4\3\u00b5\3\u00b5"+
		"\3\u00b5\3\u00b5\5\u00b5\u0857\n\u00b5\3\u00b6\3\u00b6\3\u00b6\3\u00b6"+
		"\5\u00b6\u085d\n\u00b6\3\u00b7\3\u00b7\3\u00b7\7\u00b7\u0862\n\u00b7\f"+
		"\u00b7\16\u00b7\u0865\13\u00b7\3\u00b7\3\u00b7\6\u00b7\u0869\n\u00b7\r"+
		"\u00b7\16\u00b7\u086a\5\u00b7\u086d\n\u00b7\3\u00b8\3\u00b8\3\u00b9\3"+
		"\u00b9\3\u00b9\5\u00b9\u0874\n\u00b9\3\u00b9\5\u00b9\u0877\n\u00b9\3\u00b9"+
		"\5\u00b9\u087a\n\u00b9\3\u00b9\3\u00b9\5\u00b9\u087e\n\u00b9\3\u00ba\3"+
		"\u00ba\3\u00ba\5\u00ba\u0883\n\u00ba\3\u00ba\5\u00ba\u0886\n\u00ba\3\u00ba"+
		"\5\u00ba\u0889\n\u00ba\3\u00ba\3\u00ba\5\u00ba\u088d\n\u00ba\3\u00bb\3"+
		"\u00bb\3\u00bb\5\u00bb\u0892\n\u00bb\3\u00bb\5\u00bb\u0895\n\u00bb\3\u00bb"+
		"\5\u00bb\u0898\n\u00bb\3\u00bb\3\u00bb\5\u00bb\u089c\n\u00bb\3\u00bc\3"+
		"\u00bc\3\u00bc\5\u00bc\u08a1\n\u00bc\3\u00bc\3\u00bc\5\u00bc\u08a5\n\u00bc"+
		"\3\u00bd\3\u00bd\3\u00bd\3\u00bd\5\u00bd\u08ab\n\u00bd\3\u00bd\3\u00bd"+
		"\5\u00bd\u08af\n\u00bd\3\u00bd\3\u00bd\5\u00bd\u08b3\n\u00bd\3\u00bd\3"+
		"\u00bd\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be"+
		"\3\u00be\3\u00be\5\u00be\u08c1\n\u00be\3\u00bf\3\u00bf\3\u00bf\3\u00c0"+
		"\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\5\u00c0\u08ce"+
		"\n\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\5\u00c0\u08d4\n\u00c0\3\u00c1"+
		"\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2"+
		"\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\5\u00c2\u08e7"+
		"\n\u00c2\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c4\3\u00c4"+
		"\3\u00c4\5\u00c4\u08f2\n\u00c4\3\u00c4\3\u00c4\5\u00c4\u08f6\n\u00c4\3"+
		"\u00c4\3\u00c4\3\u00c5\3\u00c5\5\u00c5\u08fc\n\u00c5\3\u00c5\3\u00c5\5"+
		"\u00c5\u0900\n\u00c5\3\u00c5\3\u00c5\5\u00c5\u0904\n\u00c5\3\u00c5\3\u00c5"+
		"\3\u00c5\3\u00c5\3\u00c5\5\u00c5\u090b\n\u00c5\3\u00c6\3\u00c6\3\u00c6"+
		"\3\u00c6\3\u00c6\5\u00c6\u0912\n\u00c6\3\u00c6\5\u00c6\u0915\n\u00c6\3"+
		"\u00c6\3\u00c6\7\u00c6\u0919\n\u00c6\f\u00c6\16\u00c6\u091c\13\u00c6\3"+
		"\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\5\u00c7\u0924\n\u00c7\3"+
		"\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\3\u00c7\5\u00c7\u092c\n\u00c7\3"+
		"\u00c8\3\u00c8\3\u00c8\3\u00c8\5\u00c8\u0932\n\u00c8\3\u00c9\3\u00c9\5"+
		"\u00c9\u0936\n\u00c9\3\u00c9\3\u00c9\3\u00ca\3\u00ca\5\u00ca\u093c\n\u00ca"+
		"\3\u00ca\3\u00ca\5\u00ca\u0940\n\u00ca\3\u00cb\3\u00cb\3\u00cb\2\2\u00cc"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFH"+
		"JLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4"+
		"\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc"+
		"\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4"+
		"\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec"+
		"\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104"+
		"\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u011a\u011c"+
		"\u011e\u0120\u0122\u0124\u0126\u0128\u012a\u012c\u012e\u0130\u0132\u0134"+
		"\u0136\u0138\u013a\u013c\u013e\u0140\u0142\u0144\u0146\u0148\u014a\u014c"+
		"\u014e\u0150\u0152\u0154\u0156\u0158\u015a\u015c\u015e\u0160\u0162\u0164"+
		"\u0166\u0168\u016a\u016c\u016e\u0170\u0172\u0174\u0176\u0178\u017a\u017c"+
		"\u017e\u0180\u0182\u0184\u0186\u0188\u018a\u018c\u018e\u0190\u0192\u0194"+
		"\2\24\n\2\26\26\31\3188@@TTXXdehh\4\2##..\4\2IIQQ\3\2\u009b\u009c\4\2"+
		"\u0091\u0092\u009d\u009e\3\2\u0086\u0087\3\2\u0088\u008a\20\2\23\23\26"+
		"\26\31\31\36\36##..88@@EETTXX\\\\dehh\4\2\17\17!!\16\2\n\n\20\20**::C"+
		"CJJLPUU[[ggkkmm\5\2IIQQ__\4\2))\66\66\4\2\22\22__\4\2\u0088\u0088\u0093"+
		"\u0093\4\2\67\67II\4\2++aa\25\2\n\n\16\16\22\24\26 \"#%&(\60\63\63\65"+
		":<<?@BEGGIKMQSUXikmpp\25\2\13\r\17\21\25\25!!$$\'\'\61\62\64\64;;=>AA"+
		"FFHHLLRRVWjjnoqr\2\u0a23\2\u0197\3\2\2\2\4\u01af\3\2\2\2\6\u01bb\3\2\2"+
		"\2\b\u01c8\3\2\2\2\n\u01cc\3\2\2\2\f\u01d1\3\2\2\2\16\u01d3\3\2\2\2\20"+
		"\u01d5\3\2\2\2\22\u01db\3\2\2\2\24\u01dd\3\2\2\2\26\u01e8\3\2\2\2\30\u01f3"+
		"\3\2\2\2\32\u0200\3\2\2\2\34\u0205\3\2\2\2\36\u0207\3\2\2\2 \u0216\3\2"+
		"\2\2\"\u0218\3\2\2\2$\u0220\3\2\2\2&\u0225\3\2\2\2(\u022d\3\2\2\2*\u0235"+
		"\3\2\2\2,\u023d\3\2\2\2.\u0245\3\2\2\2\60\u024d\3\2\2\2\62\u0255\3\2\2"+
		"\2\64\u0261\3\2\2\2\66\u026c\3\2\2\28\u0274\3\2\2\2:\u0294\3\2\2\2<\u0296"+
		"\3\2\2\2>\u031d\3\2\2\2@\u0320\3\2\2\2B\u0328\3\2\2\2D\u0338\3\2\2\2F"+
		"\u033c\3\2\2\2H\u033e\3\2\2\2J\u0348\3\2\2\2L\u034a\3\2\2\2N\u0353\3\2"+
		"\2\2P\u0360\3\2\2\2R\u0367\3\2\2\2T\u0369\3\2\2\2V\u037c\3\2\2\2X\u037e"+
		"\3\2\2\2Z\u0387\3\2\2\2\\\u0394\3\2\2\2^\u0396\3\2\2\2`\u03ab\3\2\2\2"+
		"b\u03b4\3\2\2\2d\u03c0\3\2\2\2f\u03d1\3\2\2\2h\u03d3\3\2\2\2j\u03dc\3"+
		"\2\2\2l\u03e1\3\2\2\2n\u03eb\3\2\2\2p\u03ed\3\2\2\2r\u03f0\3\2\2\2t\u03fb"+
		"\3\2\2\2v\u0407\3\2\2\2x\u0409\3\2\2\2z\u040e\3\2\2\2|\u0411\3\2\2\2~"+
		"\u0420\3\2\2\2\u0080\u0429\3\2\2\2\u0082\u0434\3\2\2\2\u0084\u0436\3\2"+
		"\2\2\u0086\u0442\3\2\2\2\u0088\u0444\3\2\2\2\u008a\u044a\3\2\2\2\u008c"+
		"\u04cb\3\2\2\2\u008e\u04cd\3\2\2\2\u0090\u04d3\3\2\2\2\u0092\u04de\3\2"+
		"\2\2\u0094\u04e0\3\2\2\2\u0096\u04e8\3\2\2\2\u0098\u04ea\3\2\2\2\u009a"+
		"\u04f0\3\2\2\2\u009c\u04f3\3\2\2\2\u009e\u04ff\3\2\2\2\u00a0\u0502\3\2"+
		"\2\2\u00a2\u050f\3\2\2\2\u00a4\u0511\3\2\2\2\u00a6\u0524\3\2\2\2\u00a8"+
		"\u0526\3\2\2\2\u00aa\u0532\3\2\2\2\u00ac\u0538\3\2\2\2\u00ae\u053d\3\2"+
		"\2\2\u00b0\u0542\3\2\2\2\u00b2\u0544\3\2\2\2\u00b4\u054a\3\2\2\2\u00b6"+
		"\u0552\3\2\2\2\u00b8\u055f\3\2\2\2\u00ba\u0563\3\2\2\2\u00bc\u0569\3\2"+
		"\2\2\u00be\u057c\3\2\2\2\u00c0\u057f\3\2\2\2\u00c2\u0585\3\2\2\2\u00c4"+
		"\u0588\3\2\2\2\u00c6\u0594\3\2\2\2\u00c8\u059a\3\2\2\2\u00ca\u05a6\3\2"+
		"\2\2\u00cc\u05aa\3\2\2\2\u00ce\u05b3\3\2\2\2\u00d0\u05bc\3\2\2\2\u00d2"+
		"\u05c0\3\2\2\2\u00d4\u05cf\3\2\2\2\u00d6\u05d4\3\2\2\2\u00d8\u05d6\3\2"+
		"\2\2\u00da\u05de\3\2\2\2\u00dc\u05e2\3\2\2\2\u00de\u05e9\3\2\2\2\u00e0"+
		"\u05ee\3\2\2\2\u00e2\u05f8\3\2\2\2\u00e4\u05fc\3\2\2\2\u00e6\u0611\3\2"+
		"\2\2\u00e8\u0613\3\2\2\2\u00ea\u061f\3\2\2\2\u00ec\u0627\3\2\2\2\u00ee"+
		"\u062b\3\2\2\2\u00f0\u0633\3\2\2\2\u00f2\u063a\3\2\2\2\u00f4\u063e\3\2"+
		"\2\2\u00f6\u0640\3\2\2\2\u00f8\u0644\3\2\2\2\u00fa\u064c\3\2\2\2\u00fc"+
		"\u064e\3\2\2\2\u00fe\u065e\3\2\2\2\u0100\u0660\3\2\2\2\u0102\u0663\3\2"+
		"\2\2\u0104\u066a\3\2\2\2\u0106\u067c\3\2\2\2\u0108\u0685\3\2\2\2\u010a"+
		"\u0694\3\2\2\2\u010c\u0698\3\2\2\2\u010e\u069b\3\2\2\2\u0110\u06a8\3\2"+
		"\2\2\u0112\u06ae\3\2\2\2\u0114\u06c9\3\2\2\2\u0116\u06cb\3\2\2\2\u0118"+
		"\u06d2\3\2\2\2\u011a\u06dc\3\2\2\2\u011c\u06de\3\2\2\2\u011e\u06e1\3\2"+
		"\2\2\u0120\u06eb\3\2\2\2\u0122\u06fc\3\2\2\2\u0124\u0708\3\2\2\2\u0126"+
		"\u0711\3\2\2\2\u0128\u0721\3\2\2\2\u012a\u072d\3\2\2\2\u012c\u0734\3\2"+
		"\2\2\u012e\u0736\3\2\2\2\u0130\u0739\3\2\2\2\u0132\u0743\3\2\2\2\u0134"+
		"\u0783\3\2\2\2\u0136\u0799\3\2\2\2\u0138\u079c\3\2\2\2\u013a\u07ad\3\2"+
		"\2\2\u013c\u07b4\3\2\2\2\u013e\u07bf\3\2\2\2\u0140\u07c2\3\2\2\2\u0142"+
		"\u07c6\3\2\2\2\u0144\u07d4\3\2\2\2\u0146\u07d6\3\2\2\2\u0148\u07de\3\2"+
		"\2\2\u014a\u07f0\3\2\2\2\u014c\u0803\3\2\2\2\u014e\u0805\3\2\2\2\u0150"+
		"\u080d\3\2\2\2\u0152\u0816\3\2\2\2\u0154\u0818\3\2\2\2\u0156\u081d\3\2"+
		"\2\2\u0158\u0823\3\2\2\2\u015a\u0827\3\2\2\2\u015c\u082b\3\2\2\2\u015e"+
		"\u0836\3\2\2\2\u0160\u0838\3\2\2\2\u0162\u083e\3\2\2\2\u0164\u0840\3\2"+
		"\2\2\u0166\u0849\3\2\2\2\u0168\u0856\3\2\2\2\u016a\u085c\3\2\2\2\u016c"+
		"\u085e\3\2\2\2\u016e\u086e\3\2\2\2\u0170\u0870\3\2\2\2\u0172\u087f\3\2"+
		"\2\2\u0174\u088e\3\2\2\2\u0176\u089d\3\2\2\2\u0178\u08a6\3\2\2\2\u017a"+
		"\u08b6\3\2\2\2\u017c\u08c2\3\2\2\2\u017e\u08c5\3\2\2\2\u0180\u08d5\3\2"+
		"\2\2\u0182\u08da\3\2\2\2\u0184\u08e8\3\2\2\2\u0186\u08ee\3\2\2\2\u0188"+
		"\u08f9\3\2\2\2\u018a\u0911\3\2\2\2\u018c\u091d\3\2\2\2\u018e\u092d\3\2"+
		"\2\2\u0190\u0933\3\2\2\2\u0192\u0939\3\2\2\2\u0194\u0941\3\2\2\2\u0196"+
		"\u0198\7\3\2\2\u0197\u0196\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u019a\3\2"+
		"\2\2\u0199\u019b\5\u00b8]\2\u019a\u0199\3\2\2\2\u019a\u019b\3\2\2\2\u019b"+
		"\u019d\3\2\2\2\u019c\u019e\5\u00bc_\2\u019d\u019c\3\2\2\2\u019d\u019e"+
		"\3\2\2\2\u019e\u01a2\3\2\2\2\u019f\u01a1\5\u013c\u009f\2\u01a0\u019f\3"+
		"\2\2\2\u01a1\u01a4\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		"\u01a6\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a5\u01a7\5\u00c0a\2\u01a6\u01a5"+
		"\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a9\7\2\2\3\u01a9"+
		"\3\3\2\2\2\u01aa\u01ac\5\u0194\u00cb\2\u01ab\u01ad\5\24\13\2\u01ac\u01ab"+
		"\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01b0\5\u00c6d"+
		"\2\u01af\u01aa\3\2\2\2\u01af\u01ae\3\2\2\2\u01b0\u01b8\3\2\2\2\u01b1\u01b2"+
		"\7\u0082\2\2\u01b2\u01b4\5\u0194\u00cb\2\u01b3\u01b5\5\24\13\2\u01b4\u01b3"+
		"\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b7\3\2\2\2\u01b6\u01b1\3\2\2\2\u01b7"+
		"\u01ba\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\5\3\2\2\2"+
		"\u01ba\u01b8\3\2\2\2\u01bb\u01c1\5\b\5\2\u01bc\u01c0\7\u0093\2\2\u01bd"+
		"\u01c0\5\u0124\u0093\2\u01be\u01c0\7\u0088\2\2\u01bf\u01bc\3\2\2\2\u01bf"+
		"\u01bd\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0\u01c3\3\2\2\2\u01c1\u01bf\3\2"+
		"\2\2\u01c1\u01c2\3\2\2\2\u01c2\7\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c4\u01c9"+
		"\5\n\6\2\u01c5\u01c9\5\22\n\2\u01c6\u01c7\7l\2\2\u01c7\u01c9\7\u0088\2"+
		"\2\u01c8\u01c4\3\2\2\2\u01c8\u01c5\3\2\2\2\u01c8\u01c6\3\2\2\2\u01c9\t"+
		"\3\2\2\2\u01ca\u01cd\5\f\7\2\u01cb\u01cd\7\23\2\2\u01cc\u01ca\3\2\2\2"+
		"\u01cc\u01cb\3\2\2\2\u01cd\13\3\2\2\2\u01ce\u01d2\5\16\b\2\u01cf\u01d2"+
		"\5\20\t\2\u01d0\u01d2\7\36\2\2\u01d1\u01ce\3\2\2\2\u01d1\u01cf\3\2\2\2"+
		"\u01d1\u01d0\3\2\2\2\u01d2\r\3\2\2\2\u01d3\u01d4\t\2\2\2\u01d4\17\3\2"+
		"\2\2\u01d5\u01d6\t\3\2\2\u01d6\21\3\2\2\2\u01d7\u01dc\5\4\3\2\u01d8\u01dc"+
		"\7E\2\2\u01d9\u01dc\7$\2\2\u01da\u01dc\7\\\2\2\u01db\u01d7\3\2\2\2\u01db"+
		"\u01d8\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01da\3\2\2\2\u01dc\23\3\2\2"+
		"\2\u01dd\u01de\7\u0091\2\2\u01de\u01e3\5\6\4\2\u01df\u01e0\7\u0083\2\2"+
		"\u01e0\u01e2\5\6\4\2\u01e1\u01df\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3\u01e1"+
		"\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6"+
		"\u01e7\7\u0092\2\2\u01e7\25\3\2\2\2\u01e8\u01ed\5\30\r\2\u01e9\u01ea\7"+
		"\u0083\2\2\u01ea\u01ec\5\30\r\2\u01eb\u01e9\3\2\2\2\u01ec\u01ef\3\2\2"+
		"\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\27\3\2\2\2\u01ef\u01ed"+
		"\3\2\2\2\u01f0\u01f1\5\u0194\u00cb\2\u01f1\u01f2\7\u0084\2\2\u01f2\u01f4"+
		"\3\2\2\2\u01f3\u01f0\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f6\3\2\2\2\u01f5"+
		"\u01f7\t\4\2\2\u01f6\u01f5\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01fa\3\2"+
		"\2\2\u01f8\u01fb\7j\2\2\u01f9\u01fb\5\6\4\2\u01fa\u01f8\3\2\2\2\u01fa"+
		"\u01f9\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fd\5\32"+
		"\16\2\u01fd\31\3\2\2\2\u01fe\u0201\5\36\20\2\u01ff\u0201\5\34\17\2\u0200"+
		"\u01fe\3\2\2\2\u0200\u01ff\3\2\2\2\u0201\33\3\2\2\2\u0202\u0206\5d\63"+
		"\2\u0203\u0206\5p9\2\u0204\u0206\5\"\22\2\u0205\u0202\3\2\2\2\u0205\u0203"+
		"\3\2\2\2\u0205\u0204\3\2\2\2\u0206\35\3\2\2\2\u0207\u0208\5:\36\2\u0208"+
		"\u0209\5 \21\2\u0209\u020a\5\32\16\2\u020a\37\3\2\2\2\u020b\u0217\7\u0090"+
		"\2\2\u020c\u0217\7\u009f\2\2\u020d\u0217\7\u00a0\2\2\u020e\u0217\7\u00a1"+
		"\2\2\u020f\u0217\7\u00a2\2\2\u0210\u0217\7\u00a3\2\2\u0211\u0217\7\u00a4"+
		"\2\2\u0212\u0217\7\u00a5\2\2\u0213\u0217\7\u00a6\2\2\u0214\u0217\7\u00a8"+
		"\2\2\u0215\u0217\5\u015c\u00af\2\u0216\u020b\3\2\2\2\u0216\u020c\3\2\2"+
		"\2\u0216\u020d\3\2\2\2\u0216\u020e\3\2\2\2\u0216\u020f\3\2\2\2\u0216\u0210"+
		"\3\2\2\2\u0216\u0211\3\2\2\2\u0216\u0212\3\2\2\2\u0216\u0213\3\2\2\2\u0216"+
		"\u0214\3\2\2\2\u0216\u0215\3\2\2\2\u0217!\3\2\2\2\u0218\u021e\5$\23\2"+
		"\u0219\u021a\7\u0093\2\2\u021a\u021b\5\32\16\2\u021b\u021c\7\u0084\2\2"+
		"\u021c\u021d\5\32\16\2\u021d\u021f\3\2\2\2\u021e\u0219\3\2\2\2\u021e\u021f"+
		"\3\2\2\2\u021f#\3\2\2\2\u0220\u0223\5&\24\2\u0221\u0222\7\u0095\2\2\u0222"+
		"\u0224\5$\23\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224%\3\2\2\2"+
		"\u0225\u022a\5(\25\2\u0226\u0227\7\u0099\2\2\u0227\u0229\5(\25\2\u0228"+
		"\u0226\3\2\2\2\u0229\u022c\3\2\2\2\u022a\u0228\3\2\2\2\u022a\u022b\3\2"+
		"\2\2\u022b\'\3\2\2\2\u022c\u022a\3\2\2\2\u022d\u0232\5*\26\2\u022e\u022f"+
		"\7\u0098\2\2\u022f\u0231\5*\26\2\u0230\u022e\3\2\2\2\u0231\u0234\3\2\2"+
		"\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233)\3\2\2\2\u0234\u0232"+
		"\3\2\2\2\u0235\u023a\5,\27\2\u0236\u0237\7\u008c\2\2\u0237\u0239\5,\27"+
		"\2\u0238\u0236\3\2\2\2\u0239\u023c\3\2\2\2\u023a\u0238\3\2\2\2\u023a\u023b"+
		"\3\2\2\2\u023b+\3\2\2\2\u023c\u023a\3\2\2\2\u023d\u0242\5.\30\2\u023e"+
		"\u023f\7\u008d\2\2\u023f\u0241\5.\30\2\u0240\u023e\3\2\2\2\u0241\u0244"+
		"\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243-\3\2\2\2\u0244"+
		"\u0242\3\2\2\2\u0245\u024a\5\60\31\2\u0246\u0247\7\u008b\2\2\u0247\u0249"+
		"\5\60\31\2\u0248\u0246\3\2\2\2\u0249\u024c\3\2\2\2\u024a\u0248\3\2\2\2"+
		"\u024a\u024b\3\2\2\2\u024b/\3\2\2\2\u024c\u024a\3\2\2\2\u024d\u0252\5"+
		"\62\32\2\u024e\u024f\t\5\2\2\u024f\u0251\5\62\32\2\u0250\u024e\3\2\2\2"+
		"\u0251\u0254\3\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\61"+
		"\3\2\2\2\u0254\u0252\3\2\2\2\u0255\u025e\5\64\33\2\u0256\u0257\t\6\2\2"+
		"\u0257\u025d\5\64\33\2\u0258\u0259\7<\2\2\u0259\u025d\5b\62\2\u025a\u025b"+
		"\7\16\2\2\u025b\u025d\5\6\4\2\u025c\u0256\3\2\2\2\u025c\u0258\3\2\2\2"+
		"\u025c\u025a\3\2\2\2\u025d\u0260\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025f"+
		"\3\2\2\2\u025f\63\3\2\2\2\u0260\u025e\3\2\2\2\u0261\u0269\5\66\34\2\u0262"+
		"\u0265\7\u00a7\2\2\u0263\u0265\5\u015a\u00ae\2\u0264\u0262\3\2\2\2\u0264"+
		"\u0263\3\2\2\2\u0265\u0266\3\2\2\2\u0266\u0268\5\66\34\2\u0267\u0264\3"+
		"\2\2\2\u0268\u026b\3\2\2\2\u0269\u0267\3\2\2\2\u0269\u026a\3\2\2\2\u026a"+
		"\65\3\2\2\2\u026b\u0269\3\2\2\2\u026c\u0271\58\35\2\u026d\u026e\t\7\2"+
		"\2\u026e\u0270\58\35\2\u026f\u026d\3\2\2\2\u0270\u0273\3\2\2\2\u0271\u026f"+
		"\3\2\2\2\u0271\u0272\3\2\2\2\u0272\67\3\2\2\2\u0273\u0271\3\2\2\2\u0274"+
		"\u0279\5:\36\2\u0275\u0276\t\b\2\2\u0276\u0278\5:\36\2\u0277\u0275\3\2"+
		"\2\2\u0278\u027b\3\2\2\2\u0279\u0277\3\2\2\2\u0279\u027a\3\2\2\2\u027a"+
		"9\3\2\2\2\u027b\u0279\3\2\2\2\u027c\u0295\5<\37\2\u027d\u027e\7\u0086"+
		"\2\2\u027e\u0295\5:\36\2\u027f\u0280\7\u0087\2\2\u0280\u0295\5:\36\2\u0281"+
		"\u0282\7\u008e\2\2\u0282\u0295\5:\36\2\u0283\u0284\7\u008f\2\2\u0284\u0295"+
		"\5:\36\2\u0285\u0286\7\u0096\2\2\u0286\u0295\5:\36\2\u0287\u0288\7\u0097"+
		"\2\2\u0288\u0295\5:\36\2\u0289\u028a\7\u0080\2\2\u028a\u028b\5\6\4\2\u028b"+
		"\u028c\7\u0081\2\2\u028c\u028d\5:\36\2\u028d\u0295\3\2\2\2\u028e\u028f"+
		"\7\21\2\2\u028f\u0295\5:\36\2\u0290\u0291\7\u008b\2\2\u0291\u0295\5:\36"+
		"\2\u0292\u0293\7\u0088\2\2\u0293\u0295\5:\36\2\u0294\u027c\3\2\2\2\u0294"+
		"\u027d\3\2\2\2\u0294\u027f\3\2\2\2\u0294\u0281\3\2\2\2\u0294\u0283\3\2"+
		"\2\2\u0294\u0285\3\2\2\2\u0294\u0287\3\2\2\2\u0294\u0289\3\2\2\2\u0294"+
		"\u028e\3\2\2\2\u0294\u0290\3\2\2\2\u0294\u0292\3\2\2\2\u0295;\3\2\2\2"+
		"\u0296\u029a\5> \2\u0297\u0299\5B\"\2\u0298\u0297\3\2\2\2\u0299\u029c"+
		"\3\2\2\2\u029a\u0298\3\2\2\2\u029a\u029b\3\2\2\2\u029b\u02ad\3\2\2\2\u029c"+
		"\u029a\3\2\2\2\u029d\u02a4\5@!\2\u029e\u02a4\5\u0190\u00c9\2\u029f\u02a4"+
		"\7\u0096\2\2\u02a0\u02a4\7\u0097\2\2\u02a1\u02a2\7\u009a\2\2\u02a2\u02a4"+
		"\5\u0194\u00cb\2\u02a3\u029d\3\2\2\2\u02a3\u029e\3\2\2\2\u02a3\u029f\3"+
		"\2\2\2\u02a3\u02a0\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a4\u02a8\3\2\2\2\u02a5"+
		"\u02a7\5B\"\2\u02a6\u02a5\3\2\2\2\u02a7\u02aa\3\2\2\2\u02a8\u02a6\3\2"+
		"\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02ac\3\2\2\2\u02aa\u02a8\3\2\2\2\u02ab"+
		"\u02a3\3\2\2\2\u02ac\u02af\3\2\2\2\u02ad\u02ab\3\2\2\2\u02ad\u02ae\3\2"+
		"\2\2\u02ae=\3\2\2\2\u02af\u02ad\3\2\2\2\u02b0\u031e\5\u015e\u00b0\2\u02b1"+
		"\u02b3\5\u0194\u00cb\2\u02b2\u02b4\5\24\13\2\u02b3\u02b2\3\2\2\2\u02b3"+
		"\u02b4\3\2\2\2\u02b4\u031e\3\2\2\2\u02b5\u02b6\7\u0080\2\2\u02b6\u02b7"+
		"\5\32\16\2\u02b7\u02b8\7\u0081\2\2\u02b8\u031e\3\2\2\2\u02b9\u031e\5F"+
		"$\2\u02ba\u031e\5\u00c6d\2\u02bb\u031e\7s\2\2\u02bc\u031e\7_\2\2\u02bd"+
		"\u02c7\7\22\2\2\u02be\u02bf\7\u0082\2\2\u02bf\u02c1\5\u0194\u00cb\2\u02c0"+
		"\u02c2\5\24\13\2\u02c1\u02c0\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2\u02c8\3"+
		"\2\2\2\u02c3\u02c4\7~\2\2\u02c4\u02c5\5H%\2\u02c5\u02c6\7\177\2\2\u02c6"+
		"\u02c8\3\2\2\2\u02c7\u02be\3\2\2\2\u02c7\u02c3\3\2\2\2\u02c8\u031e\3\2"+
		"\2\2\u02c9\u02e6\7C\2\2\u02ca\u02e0\5\6\4\2\u02cb\u02e1\5\u0192\u00ca"+
		"\2\u02cc\u02e1\5J&\2\u02cd\u02ce\7~\2\2\u02ce\u02cf\5H%\2\u02cf\u02d3"+
		"\7\177\2\2\u02d0\u02d2\5\u0124\u0093\2\u02d1\u02d0\3\2\2\2\u02d2\u02d5"+
		"\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02d7\3\2\2\2\u02d5"+
		"\u02d3\3\2\2\2\u02d6\u02d8\5\u0126\u0094\2\u02d7\u02d6\3\2\2\2\u02d7\u02d8"+
		"\3\2\2\2\u02d8\u02e1\3\2\2\2\u02d9\u02db\5\u0124\u0093\2\u02da\u02d9\3"+
		"\2\2\2\u02db\u02dc\3\2\2\2\u02dc\u02da\3\2\2\2\u02dc\u02dd\3\2\2\2\u02dd"+
		"\u02de\3\2\2\2\u02de\u02df\5\u0126\u0094\2\u02df\u02e1\3\2\2\2\u02e0\u02cb"+
		"\3\2\2\2\u02e0\u02cc\3\2\2\2\u02e0\u02cd\3\2\2\2\u02e0\u02da\3\2\2\2\u02e1"+
		"\u02e7\3\2\2\2\u02e2\u02e7\5X-\2\u02e3\u02e4\5\u0124\u0093\2\u02e4\u02e5"+
		"\5\u0126\u0094\2\u02e5\u02e7\3\2\2\2\u02e6\u02ca\3\2\2\2\u02e6\u02e2\3"+
		"\2\2\2\u02e6\u02e3\3\2\2\2\u02e7\u031e\3\2\2\2\u02e8\u02e9\7c\2\2\u02e9"+
		"\u02ed\7\u0080\2\2\u02ea\u02ee\5^\60\2\u02eb\u02ee\5\6\4\2\u02ec\u02ee"+
		"\7l\2\2\u02ed\u02ea\3\2\2\2\u02ed\u02eb\3\2\2\2\u02ed\u02ec\3\2\2\2\u02ee"+
		"\u02ef\3\2\2\2\u02ef\u031e\7\u0081\2\2\u02f0\u02f1\7\32\2\2\u02f1\u02f2"+
		"\7\u0080\2\2\u02f2\u02f3\5\32\16\2\u02f3\u02f4\7\u0081\2\2\u02f4\u031e"+
		"\3\2\2\2\u02f5\u02f6\7f\2\2\u02f6\u02f7\7\u0080\2\2\u02f7\u02f8\5\32\16"+
		"\2\u02f8\u02f9\7\u0081\2\2\u02f9\u031e\3\2\2\2\u02fa\u02fb\7\37\2\2\u02fb"+
		"\u02fc\7\u0080\2\2\u02fc\u02fd\5\6\4\2\u02fd\u02fe\7\u0081\2\2\u02fe\u031e"+
		"\3\2\2\2\u02ff\u0301\7\20\2\2\u0300\u02ff\3\2\2\2\u0300\u0301\3\2\2\2"+
		"\u0301\u0302\3\2\2\2\u0302\u0308\7 \2\2\u0303\u0305\7\u0080\2\2\u0304"+
		"\u0306\5h\65\2\u0305\u0304\3\2\2\2\u0305\u0306\3\2\2\2\u0306\u0307\3\2"+
		"\2\2\u0307\u0309\7\u0081\2\2\u0308\u0303\3\2\2\2\u0308\u0309\3\2\2\2\u0309"+
		"\u030a\3\2\2\2\u030a\u031e\5\u008eH\2\u030b\u030c\7Y\2\2\u030c\u030d\7"+
		"\u0080\2\2\u030d\u030e\5\6\4\2\u030e\u030f\7\u0081\2\2\u030f\u031e\3\2"+
		"\2\2\u0310\u0311\7A\2\2\u0311\u0317\7\u0080\2\2\u0312\u0313\5\u0194\u00cb"+
		"\2\u0313\u0314\7\u0082\2\2\u0314\u0316\3\2\2\2\u0315\u0312\3\2\2\2\u0316"+
		"\u0319\3\2\2\2\u0317\u0315\3\2\2\2\u0317\u0318\3\2\2\2\u0318\u031a\3\2"+
		"\2\2\u0319\u0317\3\2\2\2\u031a\u031b\5\u0194\u00cb\2\u031b\u031c\7\u0081"+
		"\2\2\u031c\u031e\3\2\2\2\u031d\u02b0\3\2\2\2\u031d\u02b1\3\2\2\2\u031d"+
		"\u02b5\3\2\2\2\u031d\u02b9\3\2\2\2\u031d\u02ba\3\2\2\2\u031d\u02bb\3\2"+
		"\2\2\u031d\u02bc\3\2\2\2\u031d\u02bd\3\2\2\2\u031d\u02c9\3\2\2\2\u031d"+
		"\u02e8\3\2\2\2\u031d\u02f0\3\2\2\2\u031d\u02f5\3\2\2\2\u031d\u02fa\3\2"+
		"\2\2\u031d\u0300\3\2\2\2\u031d\u030b\3\2\2\2\u031d\u0310\3\2\2\2\u031e"+
		"?\3\2\2\2\u031f\u0321\7\u0093\2\2\u0320\u031f\3\2\2\2\u0320\u0321\3\2"+
		"\2\2\u0321\u0322\3\2\2\2\u0322\u0323\7\u0082\2\2\u0323\u0325\5\u0194\u00cb"+
		"\2\u0324\u0326\5\24\13\2\u0325\u0324\3\2\2\2\u0325\u0326\3\2\2\2\u0326"+
		"A\3\2\2\2\u0327\u0329\7\u0093\2\2\u0328\u0327\3\2\2\2\u0328\u0329\3\2"+
		"\2\2\u0329\u032a\3\2\2\2\u032a\u032b\7~\2\2\u032b\u0330\5D#\2\u032c\u032d"+
		"\7\u0083\2\2\u032d\u032f\5D#\2\u032e\u032c\3\2\2\2\u032f\u0332\3\2\2\2"+
		"\u0330\u032e\3\2\2\2\u0330\u0331\3\2\2\2\u0331\u0333\3\2\2\2\u0332\u0330"+
		"\3\2\2\2\u0333\u0334\7\177\2\2\u0334C\3\2\2\2\u0335\u0336\5\u0194\u00cb"+
		"\2\u0336\u0337\7\u0084\2\2\u0337\u0339\3\2\2\2\u0338\u0335\3\2\2\2\u0338"+
		"\u0339\3\2\2\2\u0339\u033a\3\2\2\2\u033a\u033b\5\32\16\2\u033bE\3\2\2"+
		"\2\u033c\u033d\t\t\2\2\u033dG\3\2\2\2\u033e\u0343\5\32\16\2\u033f\u0340"+
		"\7\u0083\2\2\u0340\u0342\5\32\16\2\u0341\u033f\3\2\2\2\u0342\u0345\3\2"+
		"\2\2\u0343\u0341\3\2\2\2\u0343\u0344\3\2\2\2\u0344I\3\2\2\2\u0345\u0343"+
		"\3\2\2\2\u0346\u0349\5L\'\2\u0347\u0349\5T+\2\u0348\u0346\3\2\2\2\u0348"+
		"\u0347\3\2\2\2\u0349K\3\2\2\2\u034a\u034f\7|\2\2\u034b\u034d\5N(\2\u034c"+
		"\u034e\7\u0083\2\2\u034d\u034c\3\2\2\2\u034d\u034e\3\2\2\2\u034e\u0350"+
		"\3\2\2\2\u034f\u034b\3\2\2\2\u034f\u0350\3\2\2\2\u0350\u0351\3\2\2\2\u0351"+
		"\u0352\7}\2\2\u0352M\3\2\2\2\u0353\u0358\5P)\2\u0354\u0355\7\u0083\2\2"+
		"\u0355\u0357\5P)\2\u0356\u0354\3\2\2\2\u0357\u035a\3\2\2\2\u0358\u0356"+
		"\3\2\2\2\u0358\u0359\3\2\2\2\u0359O\3\2\2\2\u035a\u0358\3\2\2\2\u035b"+
		"\u0361\5\u0194\u00cb\2\u035c\u035d\7~\2\2\u035d\u035e\5\32\16\2\u035e"+
		"\u035f\7\177\2\2\u035f\u0361\3\2\2\2\u0360\u035b\3\2\2\2\u0360\u035c\3"+
		"\2\2\2\u0361\u0362\3\2\2\2\u0362\u0363\7\u0090\2\2\u0363\u0364\5R*\2\u0364"+
		"Q\3\2\2\2\u0365\u0368\5\32\16\2\u0366\u0368\5J&\2\u0367\u0365\3\2\2\2"+
		"\u0367\u0366\3\2\2\2\u0368S\3\2\2\2\u0369\u036a\7|\2\2\u036a\u036f\5V"+
		",\2\u036b\u036c\7\u0083\2\2\u036c\u036e\5V,\2\u036d\u036b\3\2\2\2\u036e"+
		"\u0371\3\2\2\2\u036f\u036d\3\2\2\2\u036f\u0370\3\2\2\2\u0370\u0373\3\2"+
		"\2\2\u0371\u036f\3\2\2\2\u0372\u0374\7\u0083\2\2\u0373\u0372\3\2\2\2\u0373"+
		"\u0374\3\2\2\2\u0374\u0375\3\2\2\2\u0375\u0376\7}\2\2\u0376U\3\2\2\2\u0377"+
		"\u037d\5\34\17\2\u0378\u0379\7|\2\2\u0379\u037a\5H%\2\u037a\u037b\7}\2"+
		"\2\u037b\u037d\3\2\2\2\u037c\u0377\3\2\2\2\u037c\u0378\3\2\2\2\u037dW"+
		"\3\2\2\2\u037e\u0383\7|\2\2\u037f\u0381\5Z.\2\u0380\u0382\7\u0083\2\2"+
		"\u0381\u0380\3\2\2\2\u0381\u0382\3\2\2\2\u0382\u0384\3\2\2\2\u0383\u037f"+
		"\3\2\2\2\u0383\u0384\3\2\2\2\u0384\u0385\3\2\2\2\u0385\u0386\7}\2\2\u0386"+
		"Y\3\2\2\2\u0387\u038c\5\\/\2\u0388\u0389\7\u0083\2\2\u0389\u038b\5\\/"+
		"\2\u038a\u0388\3\2\2\2\u038b\u038e\3\2\2\2\u038c\u038a\3\2\2\2\u038c\u038d"+
		"\3\2\2\2\u038d[\3\2\2\2\u038e\u038c\3\2\2\2\u038f\u0395\5<\37\2\u0390"+
		"\u0391\5\u0194\u00cb\2\u0391\u0392\7\u0090\2\2\u0392\u0393\5\32\16\2\u0393"+
		"\u0395\3\2\2\2\u0394\u038f\3\2\2\2\u0394\u0390\3\2\2\2\u0395]\3\2\2\2"+
		"\u0396\u039f\5\u0194\u00cb\2\u0397\u0399\5`\61\2\u0398\u0397\3\2\2\2\u0398"+
		"\u0399\3\2\2\2\u0399\u03a0\3\2\2\2\u039a\u039b\7\u0094\2\2\u039b\u039d"+
		"\5\u0194\u00cb\2\u039c\u039e\5`\61\2\u039d\u039c\3\2\2\2\u039d\u039e\3"+
		"\2\2\2\u039e\u03a0\3\2\2\2\u039f\u0398\3\2\2\2\u039f\u039a\3\2\2\2\u03a0"+
		"\u03a8\3\2\2\2\u03a1\u03a2\7\u0082\2\2\u03a2\u03a4\5\u0194\u00cb\2\u03a3"+
		"\u03a5\5`\61\2\u03a4\u03a3\3\2\2\2\u03a4\u03a5\3\2\2\2\u03a5\u03a7\3\2"+
		"\2\2\u03a6\u03a1\3\2\2\2\u03a7\u03aa\3\2\2\2\u03a8\u03a6\3\2\2\2\u03a8"+
		"\u03a9\3\2\2\2\u03a9_\3\2\2\2\u03aa\u03a8\3\2\2\2\u03ab\u03af\7\u0091"+
		"\2\2\u03ac\u03ae\7\u0083\2\2\u03ad\u03ac\3\2\2\2\u03ae\u03b1\3\2\2\2\u03af"+
		"\u03ad\3\2\2\2\u03af\u03b0\3\2\2\2\u03b0\u03b2\3\2\2\2\u03b1\u03af\3\2"+
		"\2\2\u03b2\u03b3\7\u0092\2\2\u03b3a\3\2\2\2\u03b4\u03b9\5\b\5\2\u03b5"+
		"\u03b8\5\u0124\u0093\2\u03b6\u03b8\7\u0088\2\2\u03b7\u03b5\3\2\2\2\u03b7"+
		"\u03b6\3\2\2\2\u03b8\u03bb\3\2\2\2\u03b9\u03b7\3\2\2\2\u03b9\u03ba\3\2"+
		"\2\2\u03ba\u03bd\3\2\2\2\u03bb\u03b9\3\2\2\2\u03bc\u03be\7\u0093\2\2\u03bd"+
		"\u03bc\3\2\2\2\u03bd\u03be\3\2\2\2\u03bec\3\2\2\2\u03bf\u03c1\7\20\2\2"+
		"\u03c0\u03bf\3\2\2\2\u03c0\u03c1\3\2\2\2\u03c1\u03c2\3\2\2\2\u03c2\u03c3"+
		"\5f\64\2\u03c3\u03c4\5\u0158\u00ad\2\u03c4\u03c5\5n8\2\u03c5e\3\2\2\2"+
		"\u03c6\u03c7\7\u0080\2\2\u03c7\u03d2\7\u0081\2\2\u03c8\u03c9\7\u0080\2"+
		"\2\u03c9\u03ca\5h\65\2\u03ca\u03cb\7\u0081\2\2\u03cb\u03d2\3\2\2\2\u03cc"+
		"\u03cd\7\u0080\2\2\u03cd\u03ce\5l\67\2\u03ce\u03cf\7\u0081\2\2\u03cf\u03d2"+
		"\3\2\2\2\u03d0\u03d2\5\u0194\u00cb\2\u03d1\u03c6\3\2\2\2\u03d1\u03c8\3"+
		"\2\2\2\u03d1\u03cc\3\2\2\2\u03d1\u03d0\3\2\2\2\u03d2g\3\2\2\2\u03d3\u03d8"+
		"\5j\66\2\u03d4\u03d5\7\u0083\2\2\u03d5\u03d7\5j\66\2\u03d6\u03d4\3\2\2"+
		"\2\u03d7\u03da\3\2\2\2\u03d8\u03d6\3\2\2\2\u03d8\u03d9\3\2\2\2\u03d9i"+
		"\3\2\2\2\u03da\u03d8\3\2\2\2\u03db\u03dd\t\4\2\2\u03dc\u03db\3\2\2\2\u03dc"+
		"\u03dd\3\2\2\2\u03dd\u03de\3\2\2\2\u03de\u03df\5\6\4\2\u03df\u03e0\5\u0194"+
		"\u00cb\2\u03e0k\3\2\2\2\u03e1\u03e6\5\u0194\u00cb\2\u03e2\u03e3\7\u0083"+
		"\2\2\u03e3\u03e5\5\u0194\u00cb\2\u03e4\u03e2\3\2\2\2\u03e5\u03e8\3\2\2"+
		"\2\u03e6\u03e4\3\2\2\2\u03e6\u03e7\3\2\2\2\u03e7m\3\2\2\2\u03e8\u03e6"+
		"\3\2\2\2\u03e9\u03ec\5\32\16\2\u03ea\u03ec\5\u008eH\2\u03eb\u03e9\3\2"+
		"\2\2\u03eb\u03ea\3\2\2\2\u03eco\3\2\2\2\u03ed\u03ee\5r:\2\u03ee\u03ef"+
		"\5t;\2\u03efq\3\2\2\2\u03f0\u03f2\7\61\2\2\u03f1\u03f3\5\6\4\2\u03f2\u03f1"+
		"\3\2\2\2\u03f2\u03f3\3\2\2\2\u03f3\u03f4\3\2\2\2\u03f4\u03f5\5\u0194\u00cb"+
		"\2\u03f5\u03f6\7\67\2\2\u03f6\u03f7\5\32\16\2\u03f7s\3\2\2\2\u03f8\u03fa"+
		"\5v<\2\u03f9\u03f8\3\2\2\2\u03fa\u03fd\3\2\2\2\u03fb\u03f9\3\2\2\2\u03fb"+
		"\u03fc\3\2\2\2\u03fc\u03fe\3\2\2\2\u03fd\u03fb\3\2\2\2\u03fe\u0400\5\u0082"+
		"B\2\u03ff\u0401\5\u0084C\2\u0400\u03ff\3\2\2\2\u0400\u0401\3\2\2\2\u0401"+
		"u\3\2\2\2\u0402\u0408\5r:\2\u0403\u0408\5x=\2\u0404\u0408\5z>\2\u0405"+
		"\u0408\5|?\2\u0406\u0408\5~@\2\u0407\u0402\3\2\2\2\u0407\u0403\3\2\2\2"+
		"\u0407\u0404\3\2\2\2\u0407\u0405\3\2\2\2\u0407\u0406\3\2\2\2\u0408w\3"+
		"\2\2\2\u0409\u040a\7>\2\2\u040a\u040b\5\u0194\u00cb\2\u040b\u040c\7\u0090"+
		"\2\2\u040c\u040d\5\32\16\2\u040dy\3\2\2\2\u040e\u040f\7o\2\2\u040f\u0410"+
		"\5\32\16\2\u0410{\3\2\2\2\u0411\u0413\7=\2\2\u0412\u0414\5\6\4\2\u0413"+
		"\u0412\3\2\2\2\u0413\u0414\3\2\2\2\u0414\u0415\3\2\2\2\u0415\u0416\5\u0194"+
		"\u00cb\2\u0416\u0417\7\67\2\2\u0417\u0418\5\32\16\2\u0418\u0419\7F\2\2"+
		"\u0419\u041a\5\32\16\2\u041a\u041b\7\'\2\2\u041b\u041e\5\32\16\2\u041c"+
		"\u041d\7;\2\2\u041d\u041f\5\u0194\u00cb\2\u041e\u041c\3\2\2\2\u041e\u041f"+
		"\3\2\2\2\u041f}\3\2\2\2\u0420\u0421\7H\2\2\u0421\u0426\5\u0080A\2\u0422"+
		"\u0423\7\u0083\2\2\u0423\u0425\5\u0080A\2\u0424\u0422\3\2\2\2\u0425\u0428"+
		"\3\2\2\2\u0426\u0424\3\2\2\2\u0426\u0427\3\2\2\2\u0427\177\3\2\2\2\u0428"+
		"\u0426\3\2\2\2\u0429\u042b\5\32\16\2\u042a\u042c\t\n\2\2\u042b\u042a\3"+
		"\2\2\2\u042b\u042c\3\2\2\2\u042c\u0081\3\2\2\2\u042d\u042e\7V\2\2\u042e"+
		"\u0435\5\32\16\2\u042f\u0430\7\64\2\2\u0430\u0431\5\32\16\2\u0431\u0432"+
		"\7\25\2\2\u0432\u0433\5\32\16\2\u0433\u0435\3\2\2\2\u0434\u042d\3\2\2"+
		"\2\u0434\u042f\3\2\2\2\u0435\u0083\3\2\2\2\u0436\u0437\7;\2\2\u0437\u0438"+
		"\5\u0194\u00cb\2\u0438\u0439\5t;\2\u0439\u0085\3\2\2\2\u043a\u0443\5\u0088"+
		"E\2\u043b\u043e\5\u0090I\2\u043c\u043e\5\u0098M\2\u043d\u043b\3\2\2\2"+
		"\u043d\u043c\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u0440\7\u0085\2\2\u0440"+
		"\u0443\3\2\2\2\u0441\u0443\5\u008aF\2\u0442\u043a\3\2\2\2\u0442\u043d"+
		"\3\2\2\2\u0442\u0441\3\2\2\2\u0443\u0087\3\2\2\2\u0444\u0445\5\u0194\u00cb"+
		"\2\u0445\u0446\7\u0084\2\2\u0446\u0447\5\u0086D\2\u0447\u0089\3\2\2\2"+
		"\u0448\u044b\5\u008eH\2\u0449\u044b\5\u008cG\2\u044a\u0448\3\2\2\2\u044a"+
		"\u0449\3\2\2\2\u044b\u008b\3\2\2\2\u044c\u04cc\7\u0085\2\2\u044d\u044e"+
		"\5\32\16\2\u044e\u044f\7\u0085\2\2\u044f\u04cc\3\2\2\2\u0450\u0451\7\65"+
		"\2\2\u0451\u0452\7\u0080\2\2\u0452\u0453\5\32\16\2\u0453\u0454\7\u0081"+
		"\2\2\u0454\u0457\5\u009aN\2\u0455\u0456\7%\2\2\u0456\u0458\5\u009aN\2"+
		"\u0457\u0455\3\2\2\2\u0457\u0458\3\2\2\2\u0458\u04cc\3\2\2\2\u0459\u045a"+
		"\7^\2\2\u045a\u045b\7\u0080\2\2\u045b\u045c\5\32\16\2\u045c\u045d\7\u0081"+
		"\2\2\u045d\u0461\7|\2\2\u045e\u0460\5\u009cO\2\u045f\u045e\3\2\2\2\u0460"+
		"\u0463\3\2\2\2\u0461\u045f\3\2\2\2\u0461\u0462\3\2\2\2\u0462\u0464\3\2"+
		"\2\2\u0463\u0461\3\2\2\2\u0464\u0465\7}\2\2\u0465\u04cc\3\2\2\2\u0466"+
		"\u0467\7p\2\2\u0467\u0468\7\u0080\2\2\u0468\u0469\5\32\16\2\u0469\u046a"+
		"\7\u0081\2\2\u046a\u046b\5\u008aF\2\u046b\u04cc\3\2\2\2\u046c\u046d\7"+
		"\"\2\2\u046d\u046e\5\u008aF\2\u046e\u046f\7p\2\2\u046f\u0470\7\u0080\2"+
		"\2\u0470\u0471\5\32\16\2\u0471\u0472\7\u0081\2\2\u0472\u0473\7\u0085\2"+
		"\2\u0473\u04cc\3\2\2\2\u0474\u0475\7/\2\2\u0475\u0477\7\u0080\2\2\u0476"+
		"\u0478\5\u00a2R\2\u0477\u0476\3\2\2\2\u0477\u0478\3\2\2\2\u0478\u0479"+
		"\3\2\2\2\u0479\u047b\7\u0085\2\2\u047a\u047c\5\32\16\2\u047b\u047a\3\2"+
		"\2\2\u047b\u047c\3\2\2\2\u047c\u047d\3\2\2\2\u047d\u047f\7\u0085\2\2\u047e"+
		"\u0480\5\u00a4S\2\u047f\u047e\3\2\2\2\u047f\u0480\3\2\2\2\u0480\u0481"+
		"\3\2\2\2\u0481\u0482\7\u0081\2\2\u0482\u04cc\5\u008aF\2\u0483\u0484\7"+
		"\60\2\2\u0484\u0485\7\u0080\2\2\u0485\u0486\5\u0092J\2\u0486\u0487\5\u0194"+
		"\u00cb\2\u0487\u0488\7\67\2\2\u0488\u0489\5\32\16\2\u0489\u048a\7\u0081"+
		"\2\2\u048a\u048b\5\u008aF\2\u048b\u04cc\3\2\2\2\u048c\u048d\7\24\2\2\u048d"+
		"\u04cc\7\u0085\2\2\u048e\u048f\7\35\2\2\u048f\u04cc\7\u0085\2\2\u0490"+
		"\u0495\7\63\2\2\u0491\u0496\5\u0194\u00cb\2\u0492\u0493\7\27\2\2\u0493"+
		"\u0496\5\32\16\2\u0494\u0496\7\37\2\2\u0495\u0491\3\2\2\2\u0495\u0492"+
		"\3\2\2\2\u0495\u0494\3\2\2\2\u0496\u0497\3\2\2\2\u0497\u04cc\7\u0085\2"+
		"\2\u0498\u049a\7S\2\2\u0499\u049b\5\32\16\2\u049a\u0499\3\2\2\2\u049a"+
		"\u049b\3\2\2\2\u049b\u049c\3\2\2\2\u049c\u04cc\7\u0085\2\2\u049d\u049f"+
		"\7`\2\2\u049e\u04a0\5\32\16\2\u049f\u049e\3\2\2\2\u049f\u04a0\3\2\2\2"+
		"\u04a0\u04a1\3\2\2\2\u04a1\u04cc\7\u0085\2\2\u04a2\u04a3\7b\2\2\u04a3"+
		"\u04a9\5\u008eH\2\u04a4\u04a6\5\u00a6T\2\u04a5\u04a7\5\u00aeX\2\u04a6"+
		"\u04a5\3\2\2\2\u04a6\u04a7\3\2\2\2\u04a7\u04aa\3\2\2\2\u04a8\u04aa\5\u00ae"+
		"X\2\u04a9\u04a4\3\2\2\2\u04a9\u04a8\3\2\2\2\u04aa\u04cc\3\2\2\2\u04ab"+
		"\u04ac\7\32\2\2\u04ac\u04cc\5\u008eH\2\u04ad\u04ae\7f\2\2\u04ae\u04cc"+
		"\5\u008eH\2\u04af\u04b0\7?\2\2\u04b0\u04b1\7\u0080\2\2\u04b1\u04b2\5\32"+
		"\16\2\u04b2\u04b3\7\u0081\2\2\u04b3\u04b4\5\u008aF\2\u04b4\u04cc\3\2\2"+
		"\2\u04b5\u04b6\7i\2\2\u04b6\u04b7\7\u0080\2\2\u04b7\u04b8\5\u00b0Y\2\u04b8"+
		"\u04b9\7\u0081\2\2\u04b9\u04ba\5\u008aF\2\u04ba\u04cc\3\2\2\2\u04bb\u04bf"+
		"\7q\2\2\u04bc\u04bd\7S\2\2\u04bd\u04c0\5\32\16\2\u04be\u04c0\7\24\2\2"+
		"\u04bf\u04bc\3\2\2\2\u04bf\u04be\3\2\2\2\u04c0\u04c1\3\2\2\2\u04c1\u04cc"+
		"\7\u0085\2\2\u04c2\u04c3\7g\2\2\u04c3\u04cc\5\u008eH\2\u04c4\u04c5\7-"+
		"\2\2\u04c5\u04c6\7\u0080\2\2\u04c6\u04c7\5\u014c\u00a7\2\u04c7\u04c8\5"+
		"\u014e\u00a8\2\u04c8\u04c9\7\u0081\2\2\u04c9\u04ca\5\u008aF\2\u04ca\u04cc"+
		"\3\2\2\2\u04cb\u044c\3\2\2\2\u04cb\u044d\3\2\2\2\u04cb\u0450\3\2\2\2\u04cb"+
		"\u0459\3\2\2\2\u04cb\u0466\3\2\2\2\u04cb\u046c\3\2\2\2\u04cb\u0474\3\2"+
		"\2\2\u04cb\u0483\3\2\2\2\u04cb\u048c\3\2\2\2\u04cb\u048e\3\2\2\2\u04cb"+
		"\u0490\3\2\2\2\u04cb\u0498\3\2\2\2\u04cb\u049d\3\2\2\2\u04cb\u04a2\3\2"+
		"\2\2\u04cb\u04ab\3\2\2\2\u04cb\u04ad\3\2\2\2\u04cb\u04af\3\2\2\2\u04cb"+
		"\u04b5\3\2\2\2\u04cb\u04bb\3\2\2\2\u04cb\u04c2\3\2\2\2\u04cb\u04c4\3\2"+
		"\2\2\u04cc\u008d\3\2\2\2\u04cd\u04cf\7|\2\2\u04ce\u04d0\5\u00a0Q\2\u04cf"+
		"\u04ce\3\2\2\2\u04cf\u04d0\3\2\2\2\u04d0\u04d1\3\2\2\2\u04d1\u04d2\7}"+
		"\2\2\u04d2\u008f\3\2\2\2\u04d3\u04d4\5\u0092J\2\u04d4\u04d9\5\u0094K\2"+
		"\u04d5\u04d6\7\u0083\2\2\u04d6\u04d8\5\u0094K\2\u04d7\u04d5\3\2\2\2\u04d8"+
		"\u04db\3\2\2\2\u04d9\u04d7\3\2\2\2\u04d9\u04da\3\2\2\2\u04da\u0091\3\2"+
		"\2\2\u04db\u04d9\3\2\2\2\u04dc\u04df\7j\2\2\u04dd\u04df\5\6\4\2\u04de"+
		"\u04dc\3\2\2\2\u04de\u04dd\3\2\2\2\u04df\u0093\3\2\2\2\u04e0\u04e3\5\u0194"+
		"\u00cb\2\u04e1\u04e2\7\u0090\2\2\u04e2\u04e4\5\u0096L\2\u04e3\u04e1\3"+
		"\2\2\2\u04e3\u04e4\3\2\2\2\u04e4\u0095\3\2\2\2\u04e5\u04e9\5\32\16\2\u04e6"+
		"\u04e9\5\u0126\u0094\2\u04e7\u04e9\5\u0156\u00ac\2\u04e8\u04e5\3\2\2\2"+
		"\u04e8\u04e6\3\2\2\2\u04e8\u04e7\3\2\2\2\u04e9\u0097\3\2\2\2\u04ea\u04eb"+
		"\7\34\2\2\u04eb\u04ec\5\6\4\2\u04ec\u04ed\5\u00eav\2\u04ed\u0099\3\2\2"+
		"\2\u04ee\u04f1\5\u008eH\2\u04ef\u04f1\5\u008cG\2\u04f0\u04ee\3\2\2\2\u04f0"+
		"\u04ef\3\2\2\2\u04f1\u009b\3\2\2\2\u04f2\u04f4\5\u009eP\2\u04f3\u04f2"+
		"\3\2\2\2\u04f4\u04f5\3\2\2\2\u04f5\u04f3\3\2\2\2\u04f5\u04f6\3\2\2\2\u04f6"+
		"\u04f7\3\2\2\2\u04f7\u04f8\5\u00a0Q\2\u04f8\u009d\3\2\2\2\u04f9\u04fa"+
		"\7\27\2\2\u04fa\u04fb\5\32\16\2\u04fb\u04fc\7\u0084\2\2\u04fc\u0500\3"+
		"\2\2\2\u04fd\u04fe\7\37\2\2\u04fe\u0500\7\u0084\2\2\u04ff\u04f9\3\2\2"+
		"\2\u04ff\u04fd\3\2\2\2\u0500\u009f\3\2\2\2\u0501\u0503\5\u0086D\2\u0502"+
		"\u0501\3\2\2\2\u0503\u0504\3\2\2\2\u0504\u0502\3\2\2\2\u0504\u0505\3\2"+
		"\2\2\u0505\u00a1\3\2\2\2\u0506\u0510\5\u0090I\2\u0507\u050c\5\32\16\2"+
		"\u0508\u0509\7\u0083\2\2\u0509\u050b\5\32\16\2\u050a\u0508\3\2\2\2\u050b"+
		"\u050e\3\2\2\2\u050c\u050a\3\2\2\2\u050c\u050d\3\2\2\2\u050d\u0510\3\2"+
		"\2\2\u050e\u050c\3\2\2\2\u050f\u0506\3\2\2\2\u050f\u0507\3\2\2\2\u0510"+
		"\u00a3\3\2\2\2\u0511\u0516\5\32\16\2\u0512\u0513\7\u0083\2\2\u0513\u0515"+
		"\5\32\16\2\u0514\u0512\3\2\2\2\u0515\u0518\3\2\2\2\u0516\u0514\3\2\2\2"+
		"\u0516\u0517\3\2\2\2\u0517\u00a5\3\2\2\2\u0518\u0516\3\2\2\2\u0519\u051d"+
		"\5\u00a8U\2\u051a\u051c\5\u00a8U\2\u051b\u051a\3\2\2\2\u051c\u051f\3\2"+
		"\2\2\u051d\u051b\3\2\2\2\u051d\u051e\3\2\2\2\u051e\u0521\3\2\2\2\u051f"+
		"\u051d\3\2\2\2\u0520\u0522\5\u00aaV\2\u0521\u0520\3\2\2\2\u0521\u0522"+
		"\3\2\2\2\u0522\u0525\3\2\2\2\u0523\u0525\5\u00aaV\2\u0524\u0519\3\2\2"+
		"\2\u0524\u0523\3\2\2\2\u0525\u00a7\3\2\2\2\u0526\u0527\7\30\2\2\u0527"+
		"\u0528\7\u0080\2\2\u0528\u052a\5\22\n\2\u0529\u052b\5\u0194\u00cb\2\u052a"+
		"\u0529\3\2\2\2\u052a\u052b\3\2\2\2\u052b\u052c\3\2\2\2\u052c\u052e\7\u0081"+
		"\2\2\u052d\u052f\5\u00acW\2\u052e\u052d\3\2\2\2\u052e\u052f\3\2\2\2\u052f"+
		"\u0530\3\2\2\2\u0530\u0531\5\u008eH\2\u0531\u00a9\3\2\2\2\u0532\u0534"+
		"\7\30\2\2\u0533\u0535\5\u00acW\2\u0534\u0533\3\2\2\2\u0534\u0535\3\2\2"+
		"\2\u0535\u0536\3\2\2\2\u0536\u0537\5\u008eH\2\u0537\u00ab\3\2\2\2\u0538"+
		"\u0539\7n\2\2\u0539\u053a\7\u0080\2\2\u053a\u053b\5\32\16\2\u053b\u053c"+
		"\7\u0081\2\2\u053c\u00ad\3\2\2\2\u053d\u053e\7,\2\2\u053e\u053f\5\u008e"+
		"H\2\u053f\u00af\3\2\2\2\u0540\u0543\5\u0090I\2\u0541\u0543\5\32\16\2\u0542"+
		"\u0540\3\2\2\2\u0542\u0541\3\2\2\2\u0543\u00b1\3\2\2\2\u0544\u0545\7B"+
		"\2\2\u0545\u0546\5\u00b4[\2\u0546\u0548\5\u00b6\\\2\u0547\u0549\7\u0085"+
		"\2\2\u0548\u0547\3\2\2\2\u0548\u0549\3\2\2\2\u0549\u00b3\3\2\2\2\u054a"+
		"\u054f\5\u0194\u00cb\2\u054b\u054c\7\u0082\2\2\u054c\u054e\5\u0194\u00cb"+
		"\2\u054d\u054b\3\2\2\2\u054e\u0551\3\2\2\2\u054f\u054d\3\2\2\2\u054f\u0550"+
		"\3\2\2\2\u0550\u00b5\3\2\2\2\u0551\u054f\3\2\2\2\u0552\u0554\7|\2\2\u0553"+
		"\u0555\5\u00b8]\2\u0554\u0553\3\2\2\2\u0554\u0555\3\2\2\2\u0555\u0557"+
		"\3\2\2\2\u0556\u0558\5\u00bc_\2\u0557\u0556\3\2\2\2\u0557\u0558\3\2\2"+
		"\2\u0558\u055a\3\2\2\2\u0559\u055b\5\u00c0a\2\u055a\u0559\3\2\2\2\u055a"+
		"\u055b\3\2\2\2\u055b\u055c\3\2\2\2\u055c\u055d\7}\2\2\u055d\u00b7\3\2"+
		"\2\2\u055e\u0560\5\u00ba^\2\u055f\u055e\3\2\2\2\u0560\u0561\3\2\2\2\u0561"+
		"\u055f\3\2\2\2\u0561\u0562\3\2\2\2\u0562\u00b9\3\2\2\2\u0563\u0564\7*"+
		"\2\2\u0564\u0565\7\f\2\2\u0565\u0566\5\u0194\u00cb\2\u0566\u0567\7\u0085"+
		"\2\2\u0567\u00bb\3\2\2\2\u0568\u056a\5\u00be`\2\u0569\u0568\3\2\2\2\u056a"+
		"\u056b\3\2\2\2\u056b\u0569\3\2\2\2\u056b\u056c\3\2\2\2\u056c\u00bd\3\2"+
		"\2\2\u056d\u056e\7i\2\2\u056e\u056f\5\u0194\u00cb\2\u056f\u0570\7\u0090"+
		"\2\2\u0570\u0571\5\4\3\2\u0571\u0572\7\u0085\2\2\u0572\u057d\3\2\2\2\u0573"+
		"\u0574\7i\2\2\u0574\u0575\5\4\3\2\u0575\u0576\7\u0085\2\2\u0576\u057d"+
		"\3\2\2\2\u0577\u0578\7i\2\2\u0578\u0579\7[\2\2\u0579\u057a\5\4\3\2\u057a"+
		"\u057b\7\u0085\2\2\u057b\u057d\3\2\2\2\u057c\u056d\3\2\2\2\u057c\u0573"+
		"\3\2\2\2\u057c\u0577\3\2\2\2\u057d\u00bf\3\2\2\2\u057e\u0580\5\u00c2b"+
		"\2\u057f\u057e\3\2\2\2\u0580\u0581\3\2\2\2\u0581\u057f\3\2\2\2\u0581\u0582"+
		"\3\2\2\2\u0582\u00c1\3\2\2\2\u0583\u0586\5\u00b2Z\2\u0584\u0586\5\u00c4"+
		"c\2\u0585\u0583\3\2\2\2\u0585\u0584\3\2\2\2\u0586\u00c3\3\2\2\2\u0587"+
		"\u0589\5\u0140\u00a1\2\u0588\u0587\3\2\2\2\u0588\u0589\3\2\2\2\u0589\u058b"+
		"\3\2\2\2\u058a\u058c\5\u00e2r\2\u058b\u058a\3\2\2\2\u058b\u058c\3\2\2"+
		"\2\u058c\u0592\3\2\2\2\u058d\u0593\5\u0170\u00b9\2\u058e\u0593\5\u0172"+
		"\u00ba\2\u058f\u0593\5\u0174\u00bb\2\u0590\u0593\5\u0176\u00bc\2\u0591"+
		"\u0593\5\u0178\u00bd\2\u0592\u058d\3\2\2\2\u0592\u058e\3\2\2\2\u0592\u058f"+
		"\3\2\2\2\u0592\u0590\3\2\2\2\u0592\u0591\3\2\2\2\u0593\u00c5\3\2\2\2\u0594"+
		"\u0595\5\u0194\u00cb\2\u0595\u0596\7\u0094\2\2\u0596\u0598\5\u0194\u00cb"+
		"\2\u0597\u0599\5\24\13\2\u0598\u0597\3\2\2\2\u0598\u0599\3\2\2\2\u0599"+
		"\u00c7\3\2\2\2\u059a\u059b\7\u0091\2\2\u059b\u05a0\5\u00caf\2\u059c\u059d"+
		"\7\u0083\2\2\u059d\u059f\5\u00caf\2\u059e\u059c\3\2\2\2\u059f\u05a2\3"+
		"\2\2\2\u05a0\u059e\3\2\2\2\u05a0\u05a1\3\2\2\2\u05a1\u05a3\3\2\2\2\u05a2"+
		"\u05a0\3\2\2\2\u05a3\u05a4\7\u0092\2\2\u05a4\u00c9\3\2\2\2\u05a5\u05a7"+
		"\5\u0140\u00a1\2\u05a6\u05a5\3\2\2\2\u05a6\u05a7\3\2\2\2\u05a7\u05a8\3"+
		"\2\2\2\u05a8\u05a9\5\u0194\u00cb\2\u05a9\u00cb\3\2\2\2\u05aa\u05ab\7\u0084"+
		"\2\2\u05ab\u05b0\5\22\n\2\u05ac\u05ad\7\u0083\2\2\u05ad\u05af\5\4\3\2"+
		"\u05ae\u05ac\3\2\2\2\u05af\u05b2\3\2\2\2\u05b0\u05ae\3\2\2\2\u05b0\u05b1"+
		"\3\2\2\2\u05b1\u00cd\3\2\2\2\u05b2\u05b0\3\2\2\2\u05b3\u05b8\5\4\3\2\u05b4"+
		"\u05b5\7\u0083\2\2\u05b5\u05b7\5\4\3\2\u05b6\u05b4\3\2\2\2\u05b7\u05ba"+
		"\3\2\2\2\u05b8\u05b6\3\2\2\2\u05b8\u05b9\3\2\2\2\u05b9\u00cf\3\2\2\2\u05ba"+
		"\u05b8\3\2\2\2\u05bb\u05bd\5\u00d2j\2\u05bc\u05bb\3\2\2\2\u05bd\u05be"+
		"\3\2\2\2\u05be\u05bc\3\2\2\2\u05be\u05bf\3\2\2\2\u05bf\u00d1\3\2\2\2\u05c0"+
		"\u05c1\7o\2\2\u05c1\u05c2\5\u0194\u00cb\2\u05c2\u05c3\7\u0084\2\2\u05c3"+
		"\u05c4\5\u00d4k\2\u05c4\u00d3\3\2\2\2\u05c5\u05d0\5\u00dan\2\u05c6\u05c9"+
		"\5\u00d6l\2\u05c7\u05c8\7\u0083\2\2\u05c8\u05ca\5\u00d8m\2\u05c9\u05c7"+
		"\3\2\2\2\u05c9\u05ca\3\2\2\2\u05ca\u05cd\3\2\2\2\u05cb\u05cc\7\u0083\2"+
		"\2\u05cc\u05ce\5\u00dan\2\u05cd\u05cb\3\2\2\2\u05cd\u05ce\3\2\2\2\u05ce"+
		"\u05d0\3\2\2\2\u05cf\u05c5\3\2\2\2\u05cf\u05c6\3\2\2\2\u05d0\u00d5\3\2"+
		"\2\2\u05d1\u05d5\5\22\n\2\u05d2\u05d5\7\33\2\2\u05d3\u05d5\7]\2\2\u05d4"+
		"\u05d1\3\2\2\2\u05d4\u05d2\3\2\2\2\u05d4\u05d3\3\2\2\2\u05d5\u00d7\3\2"+
		"\2\2\u05d6\u05db\5\4\3\2\u05d7\u05d8\7\u0083\2\2\u05d8\u05da\5\4\3\2\u05d9"+
		"\u05d7\3\2\2\2\u05da\u05dd\3\2\2\2\u05db\u05d9\3\2\2\2\u05db\u05dc\3\2"+
		"\2\2\u05dc\u00d9\3\2\2\2\u05dd\u05db\3\2\2\2\u05de\u05df\7C\2\2\u05df"+
		"\u05e0\7\u0080\2\2\u05e0\u05e1\7\u0081\2\2\u05e1\u00db\3\2\2\2\u05e2\u05e4"+
		"\7|\2\2\u05e3\u05e5\5\u00dep\2\u05e4\u05e3\3\2\2\2\u05e4\u05e5\3\2\2\2"+
		"\u05e5\u05e6\3\2\2\2\u05e6\u05e7\7}\2\2\u05e7\u00dd\3\2\2\2\u05e8\u05ea"+
		"\5\u00e0q\2\u05e9\u05e8\3\2\2\2\u05ea\u05eb\3\2\2\2\u05eb\u05e9\3\2\2"+
		"\2\u05eb\u05ec\3\2\2\2\u05ec\u00df\3\2\2\2\u05ed\u05ef\5\u0140\u00a1\2"+
		"\u05ee\u05ed\3\2\2\2\u05ee\u05ef\3\2\2\2\u05ef\u05f1\3\2\2\2\u05f0\u05f2"+
		"\5\u00e2r\2\u05f1\u05f0\3\2\2\2\u05f1\u05f2\3\2\2\2\u05f2\u05f5\3\2\2"+
		"\2\u05f3\u05f6\5\u00e6t\2\u05f4\u05f6\5\u0184\u00c3\2\u05f5\u05f3\3\2"+
		"\2\2\u05f5\u05f4\3\2\2\2\u05f6\u00e1\3\2\2\2\u05f7\u05f9\5\u00e4s\2\u05f8"+
		"\u05f7\3\2\2\2\u05f9\u05fa\3\2\2\2\u05fa\u05f8\3\2\2\2\u05fa\u05fb\3\2"+
		"\2\2\u05fb\u00e3\3\2\2\2\u05fc\u05fd\t\13\2\2\u05fd\u00e5\3\2\2\2\u05fe"+
		"\u0612\5\u0180\u00c1\2\u05ff\u0612\5\u00e8u\2\u0600\u0612\5\u017a\u00be"+
		"\2\u0601\u0607\5\u0116\u008c\2\u0602\u0608\5\u011a\u008e\2\u0603\u0604"+
		"\5\u0158\u00ad\2\u0604\u0605\5\32\16\2\u0605\u0606\7\u0085\2\2\u0606\u0608"+
		"\3\2\2\2\u0607\u0602\3\2\2\2\u0607\u0603\3\2\2\2\u0608\u0612\3\2\2\2\u0609"+
		"\u0612\5\u0186\u00c4\2\u060a\u060b\7l\2\2\u060b\u0612\5\u0188\u00c5\2"+
		"\u060c\u0612\5\u0170\u00b9\2\u060d\u0612\5\u0172\u00ba\2\u060e\u0612\5"+
		"\u0174\u00bb\2\u060f\u0612\5\u0176\u00bc\2\u0610\u0612\5\u0178\u00bd\2"+
		"\u0611\u05fe\3\2\2\2\u0611\u05ff\3\2\2\2\u0611\u0600\3\2\2\2\u0611\u0601"+
		"\3\2\2\2\u0611\u0609\3\2\2\2\u0611\u060a\3\2\2\2\u0611\u060c\3\2\2\2\u0611"+
		"\u060d\3\2\2\2\u0611\u060e\3\2\2\2\u0611\u060f\3\2\2\2\u0611\u0610\3\2"+
		"\2\2\u0612\u00e7\3\2\2\2\u0613\u061d\5\6\4\2\u0614\u0615\5\4\3\2\u0615"+
		"\u0616\7\u0082\2\2\u0616\u0617\5\u0182\u00c2\2\u0617\u061e\3\2\2\2\u0618"+
		"\u061e\5\u0188\u00c5\2\u0619\u061e\5\u017e\u00c0\2\u061a\u061e\5\u0182"+
		"\u00c2\2\u061b\u061e\5\u018c\u00c7\2\u061c\u061e\5\u017c\u00bf\2\u061d"+
		"\u0614\3\2\2\2\u061d\u0618\3\2\2\2\u061d\u0619\3\2\2\2\u061d\u061a\3\2"+
		"\2\2\u061d\u061b\3\2\2\2\u061d\u061c\3\2\2\2\u061e\u00e9\3\2\2\2\u061f"+
		"\u0624\5\u00ecw\2\u0620\u0621\7\u0083\2\2\u0621\u0623\5\u00ecw\2\u0622"+
		"\u0620\3\2\2\2\u0623\u0626\3\2\2\2\u0624\u0622\3\2\2\2\u0624\u0625\3\2"+
		"\2\2\u0625\u00eb\3\2\2\2\u0626\u0624\3\2\2\2\u0627\u0628\5\u0194\u00cb"+
		"\2\u0628\u0629\7\u0090\2\2\u0629\u062a\5\32\16\2\u062a\u00ed\3\2\2\2\u062b"+
		"\u0630\5\u00f0y\2\u062c\u062d\7\u0083\2\2\u062d\u062f\5\u00f0y\2\u062e"+
		"\u062c\3\2\2\2\u062f\u0632\3\2\2\2\u0630\u062e\3\2\2\2\u0630\u0631\3\2"+
		"\2\2\u0631\u00ef\3\2\2\2\u0632\u0630\3\2\2\2\u0633\u0636\5\u0194\u00cb"+
		"\2\u0634\u0635\7\u0090\2\2\u0635\u0637\5\u00f2z\2\u0636\u0634\3\2\2\2"+
		"\u0636\u0637\3\2\2\2\u0637\u00f1\3\2\2\2\u0638\u063b\5\32\16\2\u0639\u063b"+
		"\5\u0126\u0094\2\u063a\u0638\3\2\2\2\u063a\u0639\3\2\2\2\u063b\u00f3\3"+
		"\2\2\2\u063c\u063f\5\6\4\2\u063d\u063f\7l\2\2\u063e\u063c\3\2\2\2\u063e"+
		"\u063d\3\2\2\2\u063f\u00f5\3\2\2\2\u0640\u0641\5\4\3\2\u0641\u00f7\3\2"+
		"\2\2\u0642\u0645\5\u008eH\2\u0643\u0645\7\u0085\2\2\u0644\u0642\3\2\2"+
		"\2\u0644\u0643\3\2\2\2\u0645\u00f9\3\2\2\2\u0646\u064d\5\u0102\u0082\2"+
		"\u0647\u064a\5\u00fc\177\2\u0648\u0649\7\u0083\2\2\u0649\u064b\5\u0102"+
		"\u0082\2\u064a\u0648\3\2\2\2\u064a\u064b\3\2\2\2\u064b\u064d\3\2\2\2\u064c"+
		"\u0646\3\2\2\2\u064c\u0647\3\2\2\2\u064d\u00fb\3\2\2\2\u064e\u0653\5\u00fe"+
		"\u0080\2\u064f\u0650\7\u0083\2\2\u0650\u0652\5\u00fe\u0080\2\u0651\u064f"+
		"\3\2\2\2\u0652\u0655\3\2\2\2\u0653\u0651\3\2\2\2\u0653\u0654\3\2\2\2\u0654"+
		"\u00fd\3\2\2\2\u0655\u0653\3\2\2\2\u0656\u0658\5\u0140\u00a1\2\u0657\u0656"+
		"\3\2\2\2\u0657\u0658\3\2\2\2\u0658\u065a\3\2\2\2\u0659\u065b\5\u0100\u0081"+
		"\2\u065a\u0659\3\2\2\2\u065a\u065b\3\2\2\2\u065b\u065c\3\2\2\2\u065c\u065f"+
		"\5\u018e\u00c8\2\u065d\u065f\7\r\2\2\u065e\u0657\3\2\2\2\u065e\u065d\3"+
		"\2\2\2\u065f\u00ff\3\2\2\2\u0660\u0661\t\f\2\2\u0661\u0101\3\2\2\2\u0662"+
		"\u0664\5\u0140\u00a1\2\u0663\u0662\3\2\2\2\u0663\u0664\3\2\2\2\u0664\u0665"+
		"\3\2\2\2\u0665\u0666\7K\2\2\u0666\u0667\5\u0122\u0092\2\u0667\u0668\5"+
		"\u0194\u00cb\2\u0668\u0103\3\2\2\2\u0669\u066b\5\u0140\u00a1\2\u066a\u0669"+
		"\3\2\2\2\u066a\u066b\3\2\2\2\u066b\u066d\3\2\2\2\u066c\u066e\5\u010a\u0086"+
		"\2\u066d\u066c\3\2\2\2\u066d\u066e\3\2\2\2\u066e\u0679\3\2\2\2\u066f\u0670"+
		"\7\62\2\2\u0670\u0672\5\u010c\u0087\2\u0671\u0673\5\u0108\u0085\2\u0672"+
		"\u0671\3\2\2\2\u0672\u0673\3\2\2\2\u0673\u067a\3\2\2\2\u0674\u0675\7W"+
		"\2\2\u0675\u0677\5\u010c\u0087\2\u0676\u0678\5\u0106\u0084\2\u0677\u0676"+
		"\3\2\2\2\u0677\u0678\3\2\2\2\u0678\u067a\3\2\2\2\u0679\u066f\3\2\2\2\u0679"+
		"\u0674\3\2\2\2\u067a\u0105\3\2\2\2\u067b\u067d\5\u0140\u00a1\2\u067c\u067b"+
		"\3\2\2\2\u067c\u067d\3\2\2\2\u067d\u067f\3\2\2\2\u067e\u0680\5\u010a\u0086"+
		"\2\u067f\u067e\3\2\2\2\u067f\u0680\3\2\2\2\u0680\u0681\3\2\2\2\u0681\u0682"+
		"\7\62\2\2\u0682\u0683\5\u010c\u0087\2\u0683\u0107\3\2\2\2\u0684\u0686"+
		"\5\u0140\u00a1\2\u0685\u0684\3\2\2\2\u0685\u0686\3\2\2\2\u0686\u0688\3"+
		"\2\2\2\u0687\u0689\5\u010a\u0086\2\u0688\u0687\3\2\2\2\u0688\u0689\3\2"+
		"\2\2\u0689\u068a\3\2\2\2\u068a\u068b\7W\2\2\u068b\u068c\5\u010c\u0087"+
		"\2\u068c\u0109\3\2\2\2\u068d\u0695\7N\2\2\u068e\u0695\7:\2\2\u068f\u0695"+
		"\7M\2\2\u0690\u0691\7N\2\2\u0691\u0695\7:\2\2\u0692\u0693\7:\2\2\u0693"+
		"\u0695\7N\2\2\u0694\u068d\3\2\2\2\u0694\u068e\3\2\2\2\u0694\u068f\3\2"+
		"\2\2\u0694\u0690\3\2\2\2\u0694\u0692\3\2\2\2\u0695\u010b\3\2\2\2\u0696"+
		"\u0699\5\u008eH\2\u0697\u0699\7\u0085\2\2\u0698\u0696\3\2\2\2\u0698\u0697"+
		"\3\2\2\2\u0699\u010d\3\2\2\2\u069a\u069c\5\u0140\u00a1\2\u069b\u069a\3"+
		"\2\2\2\u069b\u069c\3\2\2\2\u069c\u06a5\3\2\2\2\u069d\u069e\7\13\2\2\u069e"+
		"\u069f\5\u008eH\2\u069f\u06a0\5\u0112\u008a\2\u06a0\u06a6\3\2\2\2\u06a1"+
		"\u06a2\7R\2\2\u06a2\u06a3\5\u008eH\2\u06a3\u06a4\5\u0110\u0089\2\u06a4"+
		"\u06a6\3\2\2\2\u06a5\u069d\3\2\2\2\u06a5\u06a1\3\2\2\2\u06a6\u010f\3\2"+
		"\2\2\u06a7\u06a9\5\u0140\u00a1\2\u06a8\u06a7\3\2\2\2\u06a8\u06a9\3\2\2"+
		"\2\u06a9\u06aa\3\2\2\2\u06aa\u06ab\7\13\2\2\u06ab\u06ac\5\u008eH\2\u06ac"+
		"\u0111\3\2\2\2\u06ad\u06af\5\u0140\u00a1\2\u06ae\u06ad\3\2\2\2\u06ae\u06af"+
		"\3\2\2\2\u06af\u06b0\3\2\2\2\u06b0\u06b1\7R\2\2\u06b1\u06b2\5\u008eH\2"+
		"\u06b2\u0113\3\2\2\2\u06b3\u06ca\7\u0086\2\2\u06b4\u06ca\7\u0087\2\2\u06b5"+
		"\u06ca\7\u008e\2\2\u06b6\u06ca\7\u008f\2\2\u06b7\u06ca\7\u0096\2\2\u06b8"+
		"\u06ca\7\u0097\2\2\u06b9\u06ca\7a\2\2\u06ba\u06ca\7+\2\2\u06bb\u06ca\7"+
		"\u0088\2\2\u06bc\u06ca\7\u0089\2\2\u06bd\u06ca\7\u008a\2\2\u06be\u06ca"+
		"\7\u008b\2\2\u06bf\u06ca\7\u008c\2\2\u06c0\u06ca\7\u008d\2\2\u06c1\u06ca"+
		"\7\u00a7\2\2\u06c2\u06ca\5\u015a\u00ae\2\u06c3\u06ca\7\u009b\2\2\u06c4"+
		"\u06ca\7\u009c\2\2\u06c5\u06ca\7\u0092\2\2\u06c6\u06ca\7\u0091\2\2\u06c7"+
		"\u06ca\7\u009e\2\2\u06c8\u06ca\7\u009d\2\2\u06c9\u06b3\3\2\2\2\u06c9\u06b4"+
		"\3\2\2\2\u06c9\u06b5\3\2\2\2\u06c9\u06b6\3\2\2\2\u06c9\u06b7\3\2\2\2\u06c9"+
		"\u06b8\3\2\2\2\u06c9\u06b9\3\2\2\2\u06c9\u06ba\3\2\2\2\u06c9\u06bb\3\2"+
		"\2\2\u06c9\u06bc\3\2\2\2\u06c9\u06bd\3\2\2\2\u06c9\u06be\3\2\2\2\u06c9"+
		"\u06bf\3\2\2\2\u06c9\u06c0\3\2\2\2\u06c9\u06c1\3\2\2\2\u06c9\u06c2\3\2"+
		"\2\2\u06c9\u06c3\3\2\2\2\u06c9\u06c4\3\2\2\2\u06c9\u06c5\3\2\2\2\u06c9"+
		"\u06c6\3\2\2\2\u06c9\u06c7\3\2\2\2\u06c9\u06c8\3\2\2\2\u06ca\u0115\3\2"+
		"\2\2\u06cb\u06cc\t\r\2\2\u06cc\u06cd\7G\2\2\u06cd\u06ce\5\6\4\2\u06ce"+
		"\u06cf\7\u0080\2\2\u06cf\u06d0\5\u018e\u00c8\2\u06d0\u06d1\7\u0081\2\2"+
		"\u06d1\u0117\3\2\2\2\u06d2\u06d3\7\u0084\2\2\u06d3\u06d4\t\16\2\2\u06d4"+
		"\u06d6\7\u0080\2\2\u06d5\u06d7\5\26\f\2\u06d6\u06d5\3\2\2\2\u06d6\u06d7"+
		"\3\2\2\2\u06d7\u06d8\3\2\2\2\u06d8\u06d9\7\u0081\2\2\u06d9\u0119\3\2\2"+
		"\2\u06da\u06dd\5\u008eH\2\u06db\u06dd\7\u0085\2\2\u06dc\u06da\3\2\2\2"+
		"\u06dc\u06db\3\2\2\2\u06dd\u011b\3\2\2\2\u06de\u06df\7\u0084\2\2\u06df"+
		"\u06e0\5\u00ceh\2\u06e0\u011d\3\2\2\2\u06e1\u06e5\7|\2\2\u06e2\u06e4\5"+
		"\u0120\u0091\2\u06e3\u06e2\3\2\2\2\u06e4\u06e7\3\2\2\2\u06e5\u06e3\3\2"+
		"\2\2\u06e5\u06e6\3\2\2\2\u06e6\u06e8\3\2\2\2\u06e7\u06e5\3\2\2\2\u06e8"+
		"\u06e9\7}\2\2\u06e9\u011f\3\2\2\2\u06ea\u06ec\5\u0140\u00a1\2\u06eb\u06ea"+
		"\3\2\2\2\u06eb\u06ec\3\2\2\2\u06ec\u06ee\3\2\2\2\u06ed\u06ef\5\u00e2r"+
		"\2\u06ee\u06ed\3\2\2\2\u06ee\u06ef\3\2\2\2\u06ef\u06fa\3\2\2\2\u06f0\u06fb"+
		"\5\u00e6t\2\u06f1\u06f2\7-\2\2\u06f2\u06f4\5\6\4\2\u06f3\u06f5\5\u0154"+
		"\u00ab\2\u06f4\u06f3\3\2\2\2\u06f5\u06f6\3\2\2\2\u06f6\u06f4\3\2\2\2\u06f6"+
		"\u06f7\3\2\2\2\u06f7\u06f8\3\2\2\2\u06f8\u06f9\7\u0085\2\2\u06f9\u06fb"+
		"\3\2\2\2\u06fa\u06f0\3\2\2\2\u06fa\u06f1\3\2\2\2\u06fb\u0121\3\2\2\2\u06fc"+
		"\u0704\5\b\5\2\u06fd\u06ff\t\17\2\2\u06fe\u06fd\3\2\2\2\u06ff\u0702\3"+
		"\2\2\2\u0700\u06fe\3\2\2\2\u0700\u0701\3\2\2\2\u0701\u0703\3\2\2\2\u0702"+
		"\u0700\3\2\2\2\u0703\u0705\5\u0124\u0093\2\u0704\u0700\3\2\2\2\u0705\u0706"+
		"\3\2\2\2\u0706\u0704\3\2\2\2\u0706\u0707\3\2\2\2\u0707\u0123\3\2\2\2\u0708"+
		"\u070c\7~\2\2\u0709\u070b\7\u0083\2\2\u070a\u0709\3\2\2\2\u070b\u070e"+
		"\3\2\2\2\u070c\u070a\3\2\2\2\u070c\u070d\3\2\2\2\u070d\u070f\3\2\2\2\u070e"+
		"\u070c\3\2\2\2\u070f\u0710\7\177\2\2\u0710\u0125\3\2\2\2\u0711\u071d\7"+
		"|\2\2\u0712\u0717\5\u00f2z\2\u0713\u0714\7\u0083\2\2\u0714\u0716\5\u00f2"+
		"z\2\u0715\u0713\3\2\2\2\u0716\u0719\3\2\2\2\u0717\u0715\3\2\2\2\u0717"+
		"\u0718\3\2\2\2\u0718\u071b\3\2\2\2\u0719\u0717\3\2\2\2\u071a\u071c\7\u0083"+
		"\2\2\u071b\u071a\3\2\2\2\u071b\u071c\3\2\2\2\u071c\u071e\3\2\2\2\u071d"+
		"\u0712\3\2\2\2\u071d\u071e\3\2\2\2\u071e\u071f\3\2\2\2\u071f\u0720\7}"+
		"\2\2\u0720\u0127\3\2\2\2\u0721\u0722\7\u0091\2\2\u0722\u0727\5\u012a\u0096"+
		"\2\u0723\u0724\7\u0083\2\2\u0724\u0726\5\u012a\u0096\2\u0725\u0723\3\2"+
		"\2\2\u0726\u0729\3\2\2\2\u0727\u0725\3\2\2\2\u0727\u0728\3\2\2\2\u0728"+
		"\u072a\3\2\2\2\u0729\u0727\3\2\2\2\u072a\u072b\7\u0092\2\2\u072b\u0129"+
		"\3\2\2\2\u072c\u072e\5\u0140\u00a1\2\u072d\u072c\3\2\2\2\u072d\u072e\3"+
		"\2\2\2\u072e\u0730\3\2\2\2\u072f\u0731\5\u012c\u0097\2\u0730\u072f\3\2"+
		"\2\2\u0730\u0731\3\2\2\2\u0731\u0732\3\2\2\2\u0732\u0733\5\u0194\u00cb"+
		"\2\u0733\u012b\3\2\2\2\u0734\u0735\t\20\2\2\u0735\u012d\3\2\2\2\u0736"+
		"\u0737\7\u0084\2\2\u0737\u0738\5\u00ceh\2\u0738\u012f\3\2\2\2\u0739\u073d"+
		"\7|\2\2\u073a\u073c\5\u0132\u009a\2\u073b\u073a\3\2\2\2\u073c\u073f\3"+
		"\2\2\2\u073d\u073b\3\2\2\2\u073d\u073e\3\2\2\2\u073e\u0740\3\2\2\2\u073f"+
		"\u073d\3\2\2\2\u0740\u0741\7}\2\2\u0741\u0131\3\2\2\2\u0742\u0744\5\u0140"+
		"\u00a1\2\u0743\u0742\3\2\2\2\u0743\u0744\3\2\2\2\u0744\u0746\3\2\2\2\u0745"+
		"\u0747\7C\2\2\u0746\u0745\3\2\2\2\u0746\u0747\3\2\2\2\u0747\u0780\3\2"+
		"\2\2\u0748\u074a\7g\2\2\u0749\u0748\3\2\2\2\u0749\u074a\3\2\2\2\u074a"+
		"\u074b\3\2\2\2\u074b\u0767\5\6\4\2\u074c\u074e\5\u0194\u00cb\2\u074d\u074f"+
		"\5\u00c8e\2\u074e\u074d\3\2\2\2\u074e\u074f\3\2\2\2\u074f\u0750\3\2\2"+
		"\2\u0750\u0752\7\u0080\2\2\u0751\u0753\5\u00fa~\2\u0752\u0751\3\2\2\2"+
		"\u0752\u0753\3\2\2\2\u0753\u0754\3\2\2\2\u0754\u0756\7\u0081\2\2\u0755"+
		"\u0757\5\u00d0i\2\u0756\u0755\3\2\2\2\u0756\u0757\3\2\2\2\u0757\u0758"+
		"\3\2\2\2\u0758\u0759\7\u0085\2\2\u0759\u0768\3\2\2\2\u075a\u075b\5\u0194"+
		"\u00cb\2\u075b\u075c\7|\2\2\u075c\u075d\5\u0134\u009b\2\u075d\u075e\7"+
		"}\2\2\u075e\u0768\3\2\2\2\u075f\u0760\7_\2\2\u0760\u0761\7~\2\2\u0761"+
		"\u0762\5\u00fa~\2\u0762\u0763\7\177\2\2\u0763\u0764\7|\2\2\u0764\u0765"+
		"\5\u0134\u009b\2\u0765\u0766\7}\2\2\u0766\u0768\3\2\2\2\u0767\u074c\3"+
		"\2\2\2\u0767\u075a\3\2\2\2\u0767\u075f\3\2\2\2\u0768\u0781\3\2\2\2\u0769"+
		"\u076b\7g\2\2\u076a\u0769\3\2\2\2\u076a\u076b\3\2\2\2\u076b\u076c\3\2"+
		"\2\2\u076c\u076d\7l\2\2\u076d\u076f\5\u0194\u00cb\2\u076e\u0770\5\u00c8"+
		"e\2\u076f\u076e\3\2\2\2\u076f\u0770\3\2\2\2\u0770\u0771\3\2\2\2\u0771"+
		"\u0773\7\u0080\2\2\u0772\u0774\5\u00fa~\2\u0773\u0772\3\2\2\2\u0773\u0774"+
		"\3\2\2\2\u0774\u0775\3\2\2\2\u0775\u0777\7\u0081\2\2\u0776\u0778\5\u00d0"+
		"i\2\u0777\u0776\3\2\2\2\u0777\u0778\3\2\2\2\u0778\u0779\3\2\2\2\u0779"+
		"\u077a\7\u0085\2\2\u077a\u0781\3\2\2\2\u077b\u077c\7(\2\2\u077c\u077d"+
		"\5\6\4\2\u077d\u077e\5\u0194\u00cb\2\u077e\u077f\7\u0085\2\2\u077f\u0781"+
		"\3\2\2\2\u0780\u0749\3\2\2\2\u0780\u076a\3\2\2\2\u0780\u077b\3\2\2\2\u0781"+
		"\u0133\3\2\2\2\u0782\u0784\5\u0140\u00a1\2\u0783\u0782\3\2\2\2\u0783\u0784"+
		"\3\2\2\2\u0784\u0797\3\2\2\2\u0785\u0786\7\62\2\2\u0786\u078c\7\u0085"+
		"\2\2\u0787\u0789\5\u0140\u00a1\2\u0788\u0787\3\2\2\2\u0788\u0789\3\2\2"+
		"\2\u0789\u078a\3\2\2\2\u078a\u078b\7W\2\2\u078b\u078d\7\u0085\2\2\u078c"+
		"\u0788\3\2\2\2\u078c\u078d\3\2\2\2\u078d\u0798\3\2\2\2\u078e\u078f\7W"+
		"\2\2\u078f\u0795\7\u0085\2\2\u0790\u0792\5\u0140\u00a1\2\u0791\u0790\3"+
		"\2\2\2\u0791\u0792\3\2\2\2\u0792\u0793\3\2\2\2\u0793\u0794\7\62\2\2\u0794"+
		"\u0796\7\u0085\2\2\u0795\u0791\3\2\2\2\u0795\u0796\3\2\2\2\u0796\u0798"+
		"\3\2\2\2\u0797\u0785\3\2\2\2\u0797\u078e\3\2\2\2\u0798\u0135\3\2\2\2\u0799"+
		"\u079a\7\u0084\2\2\u079a\u079b\5\6\4\2\u079b\u0137\3\2\2\2\u079c\u07a8"+
		"\7|\2\2\u079d\u07a2\5\u013a\u009e\2\u079e\u079f\7\u0083\2\2\u079f\u07a1"+
		"\5\u013a\u009e\2\u07a0\u079e\3\2\2\2\u07a1\u07a4\3\2\2\2\u07a2\u07a0\3"+
		"\2\2\2\u07a2\u07a3\3\2\2\2\u07a3\u07a6\3\2\2\2\u07a4\u07a2\3\2\2\2\u07a5"+
		"\u07a7\7\u0083\2\2\u07a6\u07a5\3\2\2\2\u07a6\u07a7\3\2\2\2\u07a7\u07a9"+
		"\3\2\2\2\u07a8\u079d\3\2\2\2\u07a8\u07a9\3\2\2\2\u07a9\u07aa\3\2\2\2\u07aa"+
		"\u07ab\7}\2\2\u07ab\u0139\3\2\2\2\u07ac\u07ae\5\u0140\u00a1\2\u07ad\u07ac"+
		"\3\2\2\2\u07ad\u07ae\3\2\2\2\u07ae\u07af\3\2\2\2\u07af\u07b2\5\u0194\u00cb"+
		"\2\u07b0\u07b1\7\u0090\2\2\u07b1\u07b3\5\32\16\2\u07b2\u07b0\3\2\2\2\u07b2"+
		"\u07b3\3\2\2\2\u07b3\u013b\3\2\2\2\u07b4\u07b5\7~\2\2\u07b5\u07b6\5\u013e"+
		"\u00a0\2\u07b6\u07b7\7\u0084\2\2\u07b7\u07b9\5\u0146\u00a4\2\u07b8\u07ba"+
		"\7\u0083\2\2\u07b9\u07b8\3\2\2\2\u07b9\u07ba\3\2\2\2\u07ba\u07bb\3\2\2"+
		"\2\u07bb\u07bc\7\177\2\2\u07bc\u013d\3\2\2\2\u07bd\u07c0\5\u016e\u00b8"+
		"\2\u07be\u07c0\5\u0194\u00cb\2\u07bf\u07bd\3\2\2\2\u07bf\u07be\3\2\2\2"+
		"\u07c0\u013f\3\2\2\2\u07c1\u07c3\5\u0142\u00a2\2\u07c2\u07c1\3\2\2\2\u07c3"+
		"\u07c4\3\2\2\2\u07c4\u07c2\3\2\2\2\u07c4\u07c5\3\2\2\2\u07c5\u0141\3\2"+
		"\2\2\u07c6\u07ca\7~\2\2\u07c7\u07c8\5\u0144\u00a3\2\u07c8\u07c9\7\u0084"+
		"\2\2\u07c9\u07cb\3\2\2\2\u07ca\u07c7\3\2\2\2\u07ca\u07cb\3\2\2\2\u07cb"+
		"\u07cc\3\2\2\2\u07cc\u07ce\5\u0146\u00a4\2\u07cd\u07cf\7\u0083\2\2\u07ce"+
		"\u07cd\3\2\2\2\u07ce\u07cf\3\2\2\2\u07cf\u07d0\3\2\2\2\u07d0\u07d1\7\177"+
		"\2\2\u07d1\u0143\3\2\2\2\u07d2\u07d5\5\u016e\u00b8\2\u07d3\u07d5\5\u0194"+
		"\u00cb\2\u07d4\u07d2\3\2\2\2\u07d4\u07d3\3\2\2\2\u07d5\u0145\3\2\2\2\u07d6"+
		"\u07db\5\u0148\u00a5\2\u07d7\u07d8\7\u0083\2\2\u07d8\u07da\5\u0148\u00a5"+
		"\2\u07d9\u07d7\3\2\2\2\u07da\u07dd\3\2\2\2\u07db\u07d9\3\2\2\2\u07db\u07dc"+
		"\3\2\2\2\u07dc\u0147\3\2\2\2\u07dd\u07db\3\2\2\2\u07de\u07eb\5\4\3\2\u07df"+
		"\u07e8\7\u0080\2\2\u07e0\u07e5\5\u014a\u00a6\2\u07e1\u07e2\7\u0083\2\2"+
		"\u07e2\u07e4\5\u014a\u00a6\2\u07e3\u07e1\3\2\2\2\u07e4\u07e7\3\2\2\2\u07e5"+
		"\u07e3\3\2\2\2\u07e5\u07e6\3\2\2\2\u07e6\u07e9\3\2\2\2\u07e7\u07e5\3\2"+
		"\2\2\u07e8\u07e0\3\2\2\2\u07e8\u07e9\3\2\2\2\u07e9\u07ea\3\2\2\2\u07ea"+
		"\u07ec\7\u0081\2\2\u07eb\u07df\3\2\2\2\u07eb\u07ec\3\2\2\2\u07ec\u0149"+
		"\3\2\2\2\u07ed\u07ee\5\u0194\u00cb\2\u07ee\u07ef\7\u0084\2\2\u07ef\u07f1"+
		"\3\2\2\2\u07f0\u07ed\3\2\2\2\u07f0\u07f1\3\2\2\2\u07f1\u07f2\3\2\2\2\u07f2"+
		"\u07f3\5\32\16\2\u07f3\u014b\3\2\2\2\u07f4\u07f7\5\n\6\2\u07f5\u07f7\5"+
		"\22\n\2\u07f6\u07f4\3\2\2\2\u07f6\u07f5\3\2\2\2\u07f7\u07fc\3\2\2\2\u07f8"+
		"\u07fb\5\u0124\u0093\2\u07f9\u07fb\7\u0093\2\2\u07fa\u07f8\3\2\2\2\u07fa"+
		"\u07f9\3\2\2\2\u07fb\u07fe\3\2\2\2\u07fc\u07fa\3\2\2\2\u07fc\u07fd\3\2"+
		"\2\2\u07fd\u07ff\3\2\2\2\u07fe\u07fc\3\2\2\2\u07ff\u0800\7\u0088\2\2\u0800"+
		"\u0804\3\2\2\2\u0801\u0802\7l\2\2\u0802\u0804\7\u0088\2\2\u0803\u07f6"+
		"\3\2\2\2\u0803\u0801\3\2\2\2\u0804\u014d\3\2\2\2\u0805\u080a\5\u0150\u00a9"+
		"\2\u0806\u0807\7\u0083\2\2\u0807\u0809\5\u0150\u00a9\2\u0808\u0806\3\2"+
		"\2\2\u0809\u080c\3\2\2\2\u080a\u0808\3\2\2\2\u080a\u080b\3\2\2\2\u080b"+
		"\u014f\3\2\2\2\u080c\u080a\3\2\2\2\u080d\u080e\5\u0194\u00cb\2\u080e\u080f"+
		"\7\u0090\2\2\u080f\u0810\5\u0152\u00aa\2\u0810\u0151\3\2\2\2\u0811\u0813"+
		"\7\u008b\2\2\u0812\u0811\3\2\2\2\u0812\u0813\3\2\2\2\u0813\u0814\3\2\2"+
		"\2\u0814\u0817\5\32\16\2\u0815\u0817\5\u0156\u00ac\2\u0816\u0812\3\2\2"+
		"\2\u0816\u0815\3\2\2\2\u0817\u0153\3\2\2\2\u0818\u0819\5\u0194\u00cb\2"+
		"\u0819\u081a\7~\2\2\u081a\u081b\5\32\16\2\u081b\u081c\7\177\2\2\u081c"+
		"\u0155\3\2\2\2\u081d\u081e\7Z\2\2\u081e\u081f\5\6\4\2\u081f\u0820\7~\2"+
		"\2\u0820\u0821\5\32\16\2\u0821\u0822\7\177\2\2\u0822\u0157\3\2\2\2\u0823"+
		"\u0824\7\u0090\2\2\u0824\u0825\7\u0092\2\2\u0825\u0826\6\u00ad\2\3\u0826"+
		"\u0159\3\2\2\2\u0827\u0828\7\u0092\2\2\u0828\u0829\7\u0092\2\2\u0829\u082a"+
		"\6\u00ae\3\3\u082a\u015b\3\2\2\2\u082b\u082c\7\u0092\2\2\u082c\u082d\7"+
		"\u009e\2\2\u082d\u082e\6\u00af\4\3\u082e\u015d\3\2\2\2\u082f\u0837\5\u0160"+
		"\u00b1\2\u0830\u0837\5\u0162\u00b2\2\u0831\u0837\7t\2\2\u0832\u0837\7"+
		"u\2\2\u0833\u0837\7v\2\2\u0834\u0837\7w\2\2\u0835\u0837\7D\2\2\u0836\u082f"+
		"\3\2\2\2\u0836\u0830\3\2\2\2\u0836\u0831\3\2\2\2\u0836\u0832\3\2\2\2\u0836"+
		"\u0833\3\2\2\2\u0836\u0834\3\2\2\2\u0836\u0835\3\2\2\2\u0837\u015f\3\2"+
		"\2\2\u0838\u0839\t\21\2\2\u0839\u0161\3\2\2\2\u083a\u083f\5\u0164\u00b3"+
		"\2\u083b\u083f\5\u0166\u00b4\2\u083c\u083f\7x\2\2\u083d\u083f\7y\2\2\u083e"+
		"\u083a\3\2\2\2\u083e\u083b\3\2\2\2\u083e\u083c\3\2\2\2\u083e\u083d\3\2"+
		"\2\2\u083f\u0163\3\2\2\2\u0840\u0844\7z\2\2\u0841\u0843\5\u0168\u00b5"+
		"\2\u0842\u0841\3\2\2\2\u0843\u0846\3\2\2\2\u0844\u0842\3\2\2\2\u0844\u0845"+
		"\3\2\2\2\u0845\u0847\3\2\2\2\u0846\u0844\3\2\2\2\u0847\u0848\7\u00ad\2"+
		"\2\u0848\u0165\3\2\2\2\u0849\u084d\7{\2\2\u084a\u084c\5\u016a\u00b6\2"+
		"\u084b\u084a\3\2\2\2\u084c\u084f\3\2\2\2\u084d\u084b\3\2\2\2\u084d\u084e"+
		"\3\2\2\2\u084e\u0850\3\2\2\2\u084f\u084d\3\2\2\2\u0850\u0851\7\u00ad\2"+
		"\2\u0851\u0167\3\2\2\2\u0852\u0857\5\u016c\u00b7\2\u0853\u0857\7\u00a9"+
		"\2\2\u0854\u0857\7\u00ab\2\2\u0855\u0857\7\u00ae\2\2\u0856\u0852\3\2\2"+
		"\2\u0856\u0853\3\2\2\2\u0856\u0854\3\2\2\2\u0856\u0855\3\2\2\2\u0857\u0169"+
		"\3\2\2\2\u0858\u085d\5\u016c\u00b7\2\u0859\u085d\7\u00a9\2\2\u085a\u085d"+
		"\7\u00ac\2\2\u085b\u085d\7\u00af\2\2\u085c\u0858\3\2\2\2\u085c\u0859\3"+
		"\2\2\2\u085c\u085a\3\2\2\2\u085c\u085b\3\2\2\2\u085d\u016b\3\2\2\2\u085e"+
		"\u0863\5\32\16\2\u085f\u0860\7\u0083\2\2\u0860\u0862\5\32\16\2\u0861\u085f"+
		"\3\2\2\2\u0862\u0865\3\2\2\2\u0863\u0861\3\2\2\2\u0863\u0864\3\2\2\2\u0864"+
		"\u086c\3\2\2\2\u0865\u0863\3\2\2\2\u0866\u0868\7\u0084\2\2\u0867\u0869"+
		"\7\u00b1\2\2\u0868\u0867\3\2\2\2\u0869\u086a\3\2\2\2\u086a\u0868\3\2\2"+
		"\2\u086a\u086b\3\2\2\2\u086b\u086d\3\2\2\2\u086c\u0866\3\2\2\2\u086c\u086d"+
		"\3\2\2\2\u086d\u016d\3\2\2\2\u086e\u086f\t\22\2\2\u086f\u016f\3\2\2\2"+
		"\u0870\u0871\7\33\2\2\u0871\u0873\5\u0194\u00cb\2\u0872\u0874\5\u00c8"+
		"e\2\u0873\u0872\3\2\2\2\u0873\u0874\3\2\2\2\u0874\u0876\3\2\2\2\u0875"+
		"\u0877\5\u00ccg\2\u0876\u0875\3\2\2\2\u0876\u0877\3\2\2\2\u0877\u0879"+
		"\3\2\2\2\u0878\u087a\5\u00d0i\2\u0879\u0878\3\2\2\2\u0879\u087a\3\2\2"+
		"\2\u087a\u087b\3\2\2\2\u087b\u087d\5\u00dco\2\u087c\u087e\7\u0085\2\2"+
		"\u087d\u087c\3\2\2\2\u087d\u087e\3\2\2\2\u087e\u0171\3\2\2\2\u087f\u0880"+
		"\7]\2\2\u0880\u0882\5\u0194\u00cb\2\u0881\u0883\5\u00c8e\2\u0882\u0881"+
		"\3\2\2\2\u0882\u0883\3\2\2\2\u0883\u0885\3\2\2\2\u0884\u0886\5\u011c\u008f"+
		"\2\u0885\u0884\3\2\2\2\u0885\u0886\3\2\2\2\u0886\u0888\3\2\2\2\u0887\u0889"+
		"\5\u00d0i\2\u0888\u0887\3\2\2\2\u0888\u0889\3\2\2\2\u0889\u088a\3\2\2"+
		"\2\u088a\u088c\5\u011e\u0090\2\u088b\u088d\7\u0085\2\2\u088c\u088b\3\2"+
		"\2\2\u088c\u088d\3\2\2\2\u088d\u0173\3\2\2\2\u088e\u088f\79\2\2\u088f"+
		"\u0891\5\u0194\u00cb\2\u0890\u0892\5\u0128\u0095\2\u0891\u0890\3\2\2\2"+
		"\u0891\u0892\3\2\2\2\u0892\u0894\3\2\2\2\u0893\u0895\5\u012e\u0098\2\u0894"+
		"\u0893\3\2\2\2\u0894\u0895\3\2\2\2\u0895\u0897\3\2\2\2\u0896\u0898\5\u00d0"+
		"i\2\u0897\u0896\3\2\2\2\u0897\u0898\3\2\2\2\u0898\u0899\3\2\2\2\u0899"+
		"\u089b\5\u0130\u0099\2\u089a\u089c\7\u0085\2\2\u089b\u089a\3\2\2\2\u089b"+
		"\u089c\3\2\2\2\u089c\u0175\3\2\2\2\u089d\u089e\7&\2\2\u089e\u08a0\5\u0194"+
		"\u00cb\2\u089f\u08a1\5\u0136\u009c\2\u08a0\u089f\3\2\2\2\u08a0\u08a1\3"+
		"\2\2\2\u08a1\u08a2\3\2\2\2\u08a2\u08a4\5\u0138\u009d\2\u08a3\u08a5\7\u0085"+
		"\2\2\u08a4\u08a3\3\2\2\2\u08a4\u08a5\3\2\2\2\u08a5\u0177\3\2\2\2\u08a6"+
		"\u08a7\7 \2\2\u08a7\u08a8\5\u00f4{\2\u08a8\u08aa\5\u0194\u00cb\2\u08a9"+
		"\u08ab\5\u0128\u0095\2\u08aa\u08a9\3\2\2\2\u08aa\u08ab\3\2\2\2\u08ab\u08ac"+
		"\3\2\2\2\u08ac\u08ae\7\u0080\2\2\u08ad\u08af\5\u00fa~\2\u08ae\u08ad\3"+
		"\2\2\2\u08ae\u08af\3\2\2\2\u08af\u08b0\3\2\2\2\u08b0\u08b2\7\u0081\2\2"+
		"\u08b1\u08b3\5\u00d0i\2\u08b2\u08b1\3\2\2\2\u08b2\u08b3\3\2\2\2\u08b3"+
		"\u08b4\3\2\2\2\u08b4\u08b5\7\u0085\2\2\u08b5\u0179\3\2\2\2\u08b6\u08b7"+
		"\7(\2\2\u08b7\u08c0\5\6\4\2\u08b8\u08b9\5\u00eex\2\u08b9\u08ba\7\u0085"+
		"\2\2\u08ba\u08c1\3\2\2\2\u08bb\u08bc\5\u00f6|\2\u08bc\u08bd\7|\2\2\u08bd"+
		"\u08be\5\u010e\u0088\2\u08be\u08bf\7}\2\2\u08bf\u08c1\3\2\2\2\u08c0\u08b8"+
		"\3\2\2\2\u08c0\u08bb\3\2\2\2\u08c1\u017b\3\2\2\2\u08c2\u08c3\5\u00eex"+
		"\2\u08c3\u08c4\7\u0085\2\2\u08c4\u017d\3\2\2\2\u08c5\u08d3\5\u00f6|\2"+
		"\u08c6\u08c7\7|\2\2\u08c7\u08c8\5\u0104\u0083\2\u08c8\u08cd\7}\2\2\u08c9"+
		"\u08ca\7\u0090\2\2\u08ca\u08cb\5\u00f2z\2\u08cb\u08cc\7\u0085\2\2\u08cc"+
		"\u08ce\3\2\2\2\u08cd\u08c9\3\2\2\2\u08cd\u08ce\3\2\2\2\u08ce\u08d4\3\2"+
		"\2\2\u08cf\u08d0\5\u0158\u00ad\2\u08d0\u08d1\5\32\16\2\u08d1\u08d2\7\u0085"+
		"\2\2\u08d2\u08d4\3\2\2\2\u08d3\u08c6\3\2\2\2\u08d3\u08cf\3\2\2\2\u08d4"+
		"\u017f\3\2\2\2\u08d5\u08d6\7\34\2\2\u08d6\u08d7\5\6\4\2\u08d7\u08d8\5"+
		"\u00eav\2\u08d8\u08d9\7\u0085\2\2\u08d9\u0181\3\2\2\2\u08da\u08db\7_\2"+
		"\2\u08db\u08dc\7~\2\2\u08dc\u08dd\5\u00fa~\2\u08dd\u08e6\7\177\2\2\u08de"+
		"\u08df\7|\2\2\u08df\u08e0\5\u0104\u0083\2\u08e0\u08e1\7}\2\2\u08e1\u08e7"+
		"\3\2\2\2\u08e2\u08e3\5\u0158\u00ad\2\u08e3\u08e4\5\32\16\2\u08e4\u08e5"+
		"\7\u0085\2\2\u08e5\u08e7\3\2\2\2\u08e6\u08de\3\2\2\2\u08e6\u08e2\3\2\2"+
		"\2\u08e7\u0183\3\2\2\2\u08e8\u08e9\7\u008f\2\2\u08e9\u08ea\5\u0194\u00cb"+
		"\2\u08ea\u08eb\7\u0080\2\2\u08eb\u08ec\7\u0081\2\2\u08ec\u08ed\5\u011a"+
		"\u008e\2\u08ed\u0185\3\2\2\2\u08ee\u08ef\5\u0194\u00cb\2\u08ef\u08f1\7"+
		"\u0080\2\2\u08f0\u08f2\5\u00fa~\2\u08f1\u08f0\3\2\2\2\u08f1\u08f2\3\2"+
		"\2\2\u08f2\u08f3\3\2\2\2\u08f3\u08f5\7\u0081\2\2\u08f4\u08f6\5\u0118\u008d"+
		"\2\u08f5\u08f4\3\2\2\2\u08f5\u08f6\3\2\2\2\u08f6\u08f7\3\2\2\2\u08f7\u08f8"+
		"\5\u011a\u008e\2\u08f8\u0187\3\2\2\2\u08f9\u08fb\5\u018a\u00c6\2\u08fa"+
		"\u08fc\5\u00c8e\2\u08fb\u08fa\3\2\2\2\u08fb\u08fc\3\2\2\2\u08fc\u08fd"+
		"\3\2\2\2\u08fd\u08ff\7\u0080\2\2\u08fe\u0900\5\u00fa~\2\u08ff\u08fe\3"+
		"\2\2\2\u08ff\u0900\3\2\2\2\u0900\u0901\3\2\2\2\u0901\u0903\7\u0081\2\2"+
		"\u0902\u0904\5\u00d0i\2\u0903\u0902\3\2\2\2\u0903\u0904\3\2\2\2\u0904"+
		"\u090a\3\2\2\2\u0905\u090b\5\u00f8}\2\u0906\u0907\5\u0158\u00ad\2\u0907"+
		"\u0908\5\32\16\2\u0908\u0909\7\u0085\2\2\u0909\u090b\3\2\2\2\u090a\u0905"+
		"\3\2\2\2\u090a\u0906\3\2\2\2\u090b\u0189\3\2\2\2\u090c\u0912\5\u0194\u00cb"+
		"\2\u090d\u090e\5\u0194\u00cb\2\u090e\u090f\7\u0094\2\2\u090f\u0910\5\u0194"+
		"\u00cb\2\u0910\u0912\3\2\2\2\u0911\u090c\3\2\2\2\u0911\u090d\3\2\2\2\u0912"+
		"\u091a\3\2\2\2\u0913\u0915\5\24\13\2\u0914\u0913\3\2\2\2\u0914\u0915\3"+
		"\2\2\2\u0915\u0916\3\2\2\2\u0916\u0917\7\u0082\2\2\u0917\u0919\5\u0194"+
		"\u00cb\2\u0918\u0914\3\2\2\2\u0919\u091c\3\2\2\2\u091a\u0918\3\2\2\2\u091a"+
		"\u091b\3\2\2\2\u091b\u018b\3\2\2\2\u091c\u091a\3\2\2\2\u091d\u091e\7G"+
		"\2\2\u091e\u091f\5\u0114\u008b\2\u091f\u0920\7\u0080\2\2\u0920\u0923\5"+
		"\u018e\u00c8\2\u0921\u0922\7\u0083\2\2\u0922\u0924\5\u018e\u00c8\2\u0923"+
		"\u0921\3\2\2\2\u0923\u0924\3\2\2\2\u0924\u0925\3\2\2\2\u0925\u092b\7\u0081"+
		"\2\2\u0926\u092c\5\u011a\u008e\2\u0927\u0928\5\u0158\u00ad\2\u0928\u0929"+
		"\5\32\16\2\u0929\u092a\7\u0085\2\2\u092a\u092c\3\2\2\2\u092b\u0926\3\2"+
		"\2\2\u092b\u0927\3\2\2\2\u092c\u018d\3\2\2\2\u092d\u092e\5\6\4\2\u092e"+
		"\u0931\5\u0194\u00cb\2\u092f\u0930\7\u0090\2\2\u0930\u0932\5\32\16\2\u0931"+
		"\u092f\3\2\2\2\u0931\u0932\3\2\2\2\u0932\u018f\3\2\2\2\u0933\u0935\7\u0080"+
		"\2\2\u0934\u0936\5\26\f\2\u0935\u0934\3\2\2\2\u0935\u0936\3\2\2\2\u0936"+
		"\u0937\3\2\2\2\u0937\u0938\7\u0081\2\2\u0938\u0191\3\2\2\2\u0939\u093b"+
		"\7\u0080\2\2\u093a\u093c\5\26\f\2\u093b\u093a\3\2\2\2\u093b\u093c\3\2"+
		"\2\2\u093c\u093d\3\2\2\2\u093d\u093f\7\u0081\2\2\u093e\u0940\5J&\2\u093f"+
		"\u093e\3\2\2\2\u093f\u0940\3\2\2\2\u0940\u0193\3\2\2\2\u0941\u0942\t\23"+
		"\2\2\u0942\u0195\3\2\2\2\u012c\u0197\u019a\u019d\u01a2\u01a6\u01ac\u01af"+
		"\u01b4\u01b8\u01bf\u01c1\u01c8\u01cc\u01d1\u01db\u01e3\u01ed\u01f3\u01f6"+
		"\u01fa\u0200\u0205\u0216\u021e\u0223\u022a\u0232\u023a\u0242\u024a\u0252"+
		"\u025c\u025e\u0264\u0269\u0271\u0279\u0294\u029a\u02a3\u02a8\u02ad\u02b3"+
		"\u02c1\u02c7\u02d3\u02d7\u02dc\u02e0\u02e6\u02ed\u0300\u0305\u0308\u0317"+
		"\u031d\u0320\u0325\u0328\u0330\u0338\u0343\u0348\u034d\u034f\u0358\u0360"+
		"\u0367\u036f\u0373\u037c\u0381\u0383\u038c\u0394\u0398\u039d\u039f\u03a4"+
		"\u03a8\u03af\u03b7\u03b9\u03bd\u03c0\u03d1\u03d8\u03dc\u03e6\u03eb\u03f2"+
		"\u03fb\u0400\u0407\u0413\u041e\u0426\u042b\u0434\u043d\u0442\u044a\u0457"+
		"\u0461\u0477\u047b\u047f\u0495\u049a\u049f\u04a6\u04a9\u04bf\u04cb\u04cf"+
		"\u04d9\u04de\u04e3\u04e8\u04f0\u04f5\u04ff\u0504\u050c\u050f\u0516\u051d"+
		"\u0521\u0524\u052a\u052e\u0534\u0542\u0548\u054f\u0554\u0557\u055a\u0561"+
		"\u056b\u057c\u0581\u0585\u0588\u058b\u0592\u0598\u05a0\u05a6\u05b0\u05b8"+
		"\u05be\u05c9\u05cd\u05cf\u05d4\u05db\u05e4\u05eb\u05ee\u05f1\u05f5\u05fa"+
		"\u0607\u0611\u061d\u0624\u0630\u0636\u063a\u063e\u0644\u064a\u064c\u0653"+
		"\u0657\u065a\u065e\u0663\u066a\u066d\u0672\u0677\u0679\u067c\u067f\u0685"+
		"\u0688\u0694\u0698\u069b\u06a5\u06a8\u06ae\u06c9\u06d6\u06dc\u06e5\u06eb"+
		"\u06ee\u06f6\u06fa\u0700\u0706\u070c\u0717\u071b\u071d\u0727\u072d\u0730"+
		"\u073d\u0743\u0746\u0749\u074e\u0752\u0756\u0767\u076a\u076f\u0773\u0777"+
		"\u0780\u0783\u0788\u078c\u0791\u0795\u0797\u07a2\u07a6\u07a8\u07ad\u07b2"+
		"\u07b9\u07bf\u07c4\u07ca\u07ce\u07d4\u07db\u07e5\u07e8\u07eb\u07f0\u07f6"+
		"\u07fa\u07fc\u0803\u080a\u0812\u0816\u0836\u083e\u0844\u084d\u0856\u085c"+
		"\u0863\u086a\u086c\u0873\u0876\u0879\u087d\u0882\u0885\u0888\u088c\u0891"+
		"\u0894\u0897\u089b\u08a0\u08a4\u08aa\u08ae\u08b2\u08c0\u08cd\u08d3\u08e6"+
		"\u08f1\u08f5\u08fb\u08ff\u0903\u090a\u0911\u0914\u091a\u0923\u092b\u0931"+
		"\u0935\u093b\u093f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}