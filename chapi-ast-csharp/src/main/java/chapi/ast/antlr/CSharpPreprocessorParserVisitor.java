// Generated from CSharpPreprocessorParser.g4 by ANTLR 4.8
package chapi.ast.antlr;
import java.util.Stack;
import java.util.HashSet;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CSharpPreprocessorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CSharpPreprocessorParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code preprocessorDeclaration}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorDeclaration(CSharpPreprocessorParser.PreprocessorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preprocessorConditional}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorConditional(CSharpPreprocessorParser.PreprocessorConditionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preprocessorLine}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorLine(CSharpPreprocessorParser.PreprocessorLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preprocessorDiagnostic}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorDiagnostic(CSharpPreprocessorParser.PreprocessorDiagnosticContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preprocessorRegion}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorRegion(CSharpPreprocessorParser.PreprocessorRegionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preprocessorPragma}
	 * labeled alternative in {@link CSharpPreprocessorParser#preprocessor_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorPragma(CSharpPreprocessorParser.PreprocessorPragmaContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSharpPreprocessorParser#directive_new_line_or_sharp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective_new_line_or_sharp(CSharpPreprocessorParser.Directive_new_line_or_sharpContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSharpPreprocessorParser#preprocessor_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessor_expression(CSharpPreprocessorParser.Preprocessor_expressionContext ctx);
}