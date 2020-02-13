// Generated from Scala.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ScalaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ScalaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ScalaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ScalaParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#qualId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualId(ScalaParser.QualIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#ids}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIds(ScalaParser.IdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#stableId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStableId(ScalaParser.StableIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassQualifier(ScalaParser.ClassQualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(ScalaParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#functionArgTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArgTypes(ScalaParser.FunctionArgTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#existentialClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialClause(ScalaParser.ExistentialClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#existentialDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistentialDcl(ScalaParser.ExistentialDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#infixType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixType(ScalaParser.InfixTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#compoundType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundType(ScalaParser.CompoundTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#annotType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotType(ScalaParser.AnnotTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(ScalaParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typeArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgs(ScalaParser.TypeArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#types}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypes(ScalaParser.TypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#refinement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefinement(ScalaParser.RefinementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#refineStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefineStat(ScalaParser.RefineStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typePat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypePat(ScalaParser.TypePatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#ascription}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAscription(ScalaParser.AscriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ScalaParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr1(ScalaParser.Expr1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#prefixDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixDef(ScalaParser.PrefixDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpr(ScalaParser.PostfixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#infixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixExpr(ScalaParser.InfixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExpr(ScalaParser.PrefixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#simpleExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpr(ScalaParser.SimpleExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#simpleExpr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpr1(ScalaParser.SimpleExpr1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprs(ScalaParser.ExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#argumentExprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentExprs(ScalaParser.ArgumentExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#blockExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpr(ScalaParser.BlockExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ScalaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#blockStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(ScalaParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#resultExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultExpr(ScalaParser.ResultExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#enumerators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerators(ScalaParser.EnumeratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#generator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenerator(ScalaParser.GeneratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#caseClauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseClauses(ScalaParser.CaseClausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#caseClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseClause(ScalaParser.CaseClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#guard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGuard(ScalaParser.GuardContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(ScalaParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#pattern1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern1(ScalaParser.Pattern1Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#pattern2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern2(ScalaParser.Pattern2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#pattern3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern3(ScalaParser.Pattern3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#simplePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplePattern(ScalaParser.SimplePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#patterns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatterns(ScalaParser.PatternsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typeParamClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParamClause(ScalaParser.TypeParamClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#funTypeParamClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunTypeParamClause(ScalaParser.FunTypeParamClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#variantTypeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariantTypeParam(ScalaParser.VariantTypeParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParam(ScalaParser.TypeParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#paramClauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamClauses(ScalaParser.ParamClausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#paramClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamClause(ScalaParser.ParamClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(ScalaParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(ScalaParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#paramType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamType(ScalaParser.ParamTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classParamClauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassParamClauses(ScalaParser.ClassParamClausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classParamClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassParamClause(ScalaParser.ClassParamClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassParams(ScalaParser.ClassParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassParam(ScalaParser.ClassParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#bindings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBindings(ScalaParser.BindingsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#binding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinding(ScalaParser.BindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(ScalaParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#localModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalModifier(ScalaParser.LocalModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#accessModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessModifier(ScalaParser.AccessModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#accessQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessQualifier(ScalaParser.AccessQualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(ScalaParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#constrAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstrAnnotation(ScalaParser.ConstrAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#templateBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateBody(ScalaParser.TemplateBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#templateStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStat(ScalaParser.TemplateStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#selfType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfType(ScalaParser.SelfTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#import_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_(ScalaParser.Import_Context ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#importExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportExpr(ScalaParser.ImportExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#importSelectors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportSelectors(ScalaParser.ImportSelectorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#importSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportSelector(ScalaParser.ImportSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#dcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl(ScalaParser.DclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#valDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValDcl(ScalaParser.ValDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#varDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDcl(ScalaParser.VarDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#funDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDcl(ScalaParser.FunDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#funSig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunSig(ScalaParser.FunSigContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typeDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDcl(ScalaParser.TypeDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#patVarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatVarDef(ScalaParser.PatVarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(ScalaParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#patDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatDef(ScalaParser.PatDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(ScalaParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#funDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDef(ScalaParser.FunDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#typeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDef(ScalaParser.TypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#tmplDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTmplDef(ScalaParser.TmplDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(ScalaParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#traitDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitDef(ScalaParser.TraitDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#objectDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectDef(ScalaParser.ObjectDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classTemplateOpt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassTemplateOpt(ScalaParser.ClassTemplateOptContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#traitTemplateOpt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitTemplateOpt(ScalaParser.TraitTemplateOptContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classTemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassTemplate(ScalaParser.ClassTemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#traitTemplate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitTemplate(ScalaParser.TraitTemplateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#classParents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassParents(ScalaParser.ClassParentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#traitParents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitParents(ScalaParser.TraitParentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#constr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstr(ScalaParser.ConstrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#earlyDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEarlyDefs(ScalaParser.EarlyDefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#earlyDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEarlyDef(ScalaParser.EarlyDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#constrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstrExpr(ScalaParser.ConstrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#constrBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstrBlock(ScalaParser.ConstrBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#selfInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfInvocation(ScalaParser.SelfInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#topStatSeq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopStatSeq(ScalaParser.TopStatSeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#topStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopStat(ScalaParser.TopStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#packaging}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackaging(ScalaParser.PackagingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#packageObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageObject(ScalaParser.PackageObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScalaParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(ScalaParser.CompilationUnitContext ctx);
}