// Generated from PythonParser.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PythonParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PythonParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PythonParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(PythonParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#single_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_input(PythonParser.Single_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(PythonParser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#eval_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_input(PythonParser.Eval_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(PythonParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(PythonParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(PythonParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code for_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(PythonParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code try_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTry_stmt(PythonParser.Try_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code with_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_stmt(PythonParser.With_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_or_func_def_stmt}
	 * labeled alternative in {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_or_func_def_stmt(PythonParser.Class_or_func_def_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(PythonParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(PythonParser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#elif_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElif_clause(PythonParser.Elif_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#else_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_clause(PythonParser.Else_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#finally_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinally_clause(PythonParser.Finally_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#with_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_item(PythonParser.With_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#except_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExcept_clause(PythonParser.Except_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(PythonParser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(PythonParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedargslist(PythonParser.TypedargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(PythonParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#kwargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKwargs(PythonParser.KwargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#def_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_parameters(PythonParser.Def_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#def_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_parameter(PythonParser.Def_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#named_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_parameter(PythonParser.Named_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(PythonParser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(PythonParser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stmt(PythonParser.Print_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code del_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel_stmt(PythonParser.Del_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pass_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPass_stmt(PythonParser.Pass_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code break_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_stmt(PythonParser.Break_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continue_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_stmt(PythonParser.Continue_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(PythonParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code raise_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_stmt(PythonParser.Raise_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code yield_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_stmt(PythonParser.Yield_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code import_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(PythonParser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code from_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom_stmt(PythonParser.From_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code global_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_stmt(PythonParser.Global_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exec_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_stmt(PythonParser.Exec_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assert_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssert_stmt(PythonParser.Assert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonlocal_stmt}
	 * labeled alternative in {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonlocal_stmt(PythonParser.Nonlocal_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_star_expr(PythonParser.Testlist_star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_expr(PythonParser.Star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#assign_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_part(PythonParser.Assign_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprlist(PythonParser.ExprlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(PythonParser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(PythonParser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_names(PythonParser.Dotted_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_name(PythonParser.Dotted_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(PythonParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#varargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargslist(PythonParser.VarargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#vardef_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef_parameters(PythonParser.Vardef_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#vardef_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef_parameter(PythonParser.Vardef_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#varargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargs(PythonParser.VarargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#varkwargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarkwargs(PythonParser.VarkwargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#logical_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_test(PythonParser.Logical_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(PythonParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PythonParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(PythonParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dictorsetmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictorsetmaker(PythonParser.DictorsetmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_comp(PythonParser.Testlist_compContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist(PythonParser.TestlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_name(PythonParser.Dotted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(PythonParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PythonParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(PythonParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#yield_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_expr(PythonParser.Yield_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#yield_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_arg(PythonParser.Yield_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailer(PythonParser.TrailerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(PythonParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(PythonParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(PythonParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptlist(PythonParser.SubscriptlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(PythonParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#sliceop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceop(PythonParser.SliceopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_for(PythonParser.Comp_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_iter(PythonParser.Comp_iterContext ctx);
}