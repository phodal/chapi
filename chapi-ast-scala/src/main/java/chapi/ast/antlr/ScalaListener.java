// Generated from Scala.g4 by ANTLR 4.7.2
package chapi.ast.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ScalaParser}.
 */
public interface ScalaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ScalaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ScalaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ScalaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#qualId}.
	 * @param ctx the parse tree
	 */
	void enterQualId(ScalaParser.QualIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#qualId}.
	 * @param ctx the parse tree
	 */
	void exitQualId(ScalaParser.QualIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#ids}.
	 * @param ctx the parse tree
	 */
	void enterIds(ScalaParser.IdsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#ids}.
	 * @param ctx the parse tree
	 */
	void exitIds(ScalaParser.IdsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#stableId}.
	 * @param ctx the parse tree
	 */
	void enterStableId(ScalaParser.StableIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#stableId}.
	 * @param ctx the parse tree
	 */
	void exitStableId(ScalaParser.StableIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classQualifier}.
	 * @param ctx the parse tree
	 */
	void enterClassQualifier(ScalaParser.ClassQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classQualifier}.
	 * @param ctx the parse tree
	 */
	void exitClassQualifier(ScalaParser.ClassQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(ScalaParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(ScalaParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#functionArgTypes}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgTypes(ScalaParser.FunctionArgTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#functionArgTypes}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgTypes(ScalaParser.FunctionArgTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#existentialClause}.
	 * @param ctx the parse tree
	 */
	void enterExistentialClause(ScalaParser.ExistentialClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#existentialClause}.
	 * @param ctx the parse tree
	 */
	void exitExistentialClause(ScalaParser.ExistentialClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#existentialDcl}.
	 * @param ctx the parse tree
	 */
	void enterExistentialDcl(ScalaParser.ExistentialDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#existentialDcl}.
	 * @param ctx the parse tree
	 */
	void exitExistentialDcl(ScalaParser.ExistentialDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#infixType}.
	 * @param ctx the parse tree
	 */
	void enterInfixType(ScalaParser.InfixTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#infixType}.
	 * @param ctx the parse tree
	 */
	void exitInfixType(ScalaParser.InfixTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#compoundType}.
	 * @param ctx the parse tree
	 */
	void enterCompoundType(ScalaParser.CompoundTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#compoundType}.
	 * @param ctx the parse tree
	 */
	void exitCompoundType(ScalaParser.CompoundTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#annotType}.
	 * @param ctx the parse tree
	 */
	void enterAnnotType(ScalaParser.AnnotTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#annotType}.
	 * @param ctx the parse tree
	 */
	void exitAnnotType(ScalaParser.AnnotTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(ScalaParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(ScalaParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgs(ScalaParser.TypeArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typeArgs}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgs(ScalaParser.TypeArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#types}.
	 * @param ctx the parse tree
	 */
	void enterTypes(ScalaParser.TypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#types}.
	 * @param ctx the parse tree
	 */
	void exitTypes(ScalaParser.TypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#refinement}.
	 * @param ctx the parse tree
	 */
	void enterRefinement(ScalaParser.RefinementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#refinement}.
	 * @param ctx the parse tree
	 */
	void exitRefinement(ScalaParser.RefinementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#refineStat}.
	 * @param ctx the parse tree
	 */
	void enterRefineStat(ScalaParser.RefineStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#refineStat}.
	 * @param ctx the parse tree
	 */
	void exitRefineStat(ScalaParser.RefineStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typePat}.
	 * @param ctx the parse tree
	 */
	void enterTypePat(ScalaParser.TypePatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typePat}.
	 * @param ctx the parse tree
	 */
	void exitTypePat(ScalaParser.TypePatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#ascription}.
	 * @param ctx the parse tree
	 */
	void enterAscription(ScalaParser.AscriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#ascription}.
	 * @param ctx the parse tree
	 */
	void exitAscription(ScalaParser.AscriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ScalaParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ScalaParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterExpr1(ScalaParser.Expr1Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitExpr1(ScalaParser.Expr1Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#prefixDef}.
	 * @param ctx the parse tree
	 */
	void enterPrefixDef(ScalaParser.PrefixDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#prefixDef}.
	 * @param ctx the parse tree
	 */
	void exitPrefixDef(ScalaParser.PrefixDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpr(ScalaParser.PostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpr(ScalaParser.PostfixExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#infixExpr}.
	 * @param ctx the parse tree
	 */
	void enterInfixExpr(ScalaParser.InfixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#infixExpr}.
	 * @param ctx the parse tree
	 */
	void exitInfixExpr(ScalaParser.InfixExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(ScalaParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(ScalaParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#simpleExpr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpr(ScalaParser.SimpleExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#simpleExpr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpr(ScalaParser.SimpleExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#simpleExpr1}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpr1(ScalaParser.SimpleExpr1Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#simpleExpr1}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpr1(ScalaParser.SimpleExpr1Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#exprs}.
	 * @param ctx the parse tree
	 */
	void enterExprs(ScalaParser.ExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#exprs}.
	 * @param ctx the parse tree
	 */
	void exitExprs(ScalaParser.ExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#argumentExprs}.
	 * @param ctx the parse tree
	 */
	void enterArgumentExprs(ScalaParser.ArgumentExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#argumentExprs}.
	 * @param ctx the parse tree
	 */
	void exitArgumentExprs(ScalaParser.ArgumentExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#blockExpr}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpr(ScalaParser.BlockExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#blockExpr}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpr(ScalaParser.BlockExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ScalaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ScalaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(ScalaParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(ScalaParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#resultExpr}.
	 * @param ctx the parse tree
	 */
	void enterResultExpr(ScalaParser.ResultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#resultExpr}.
	 * @param ctx the parse tree
	 */
	void exitResultExpr(ScalaParser.ResultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#enumerators}.
	 * @param ctx the parse tree
	 */
	void enterEnumerators(ScalaParser.EnumeratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#enumerators}.
	 * @param ctx the parse tree
	 */
	void exitEnumerators(ScalaParser.EnumeratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#generator}.
	 * @param ctx the parse tree
	 */
	void enterGenerator(ScalaParser.GeneratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#generator}.
	 * @param ctx the parse tree
	 */
	void exitGenerator(ScalaParser.GeneratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#caseClauses}.
	 * @param ctx the parse tree
	 */
	void enterCaseClauses(ScalaParser.CaseClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#caseClauses}.
	 * @param ctx the parse tree
	 */
	void exitCaseClauses(ScalaParser.CaseClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void enterCaseClause(ScalaParser.CaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void exitCaseClause(ScalaParser.CaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#guard}.
	 * @param ctx the parse tree
	 */
	void enterGuard(ScalaParser.GuardContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#guard}.
	 * @param ctx the parse tree
	 */
	void exitGuard(ScalaParser.GuardContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(ScalaParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(ScalaParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#pattern1}.
	 * @param ctx the parse tree
	 */
	void enterPattern1(ScalaParser.Pattern1Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#pattern1}.
	 * @param ctx the parse tree
	 */
	void exitPattern1(ScalaParser.Pattern1Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#pattern2}.
	 * @param ctx the parse tree
	 */
	void enterPattern2(ScalaParser.Pattern2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#pattern2}.
	 * @param ctx the parse tree
	 */
	void exitPattern2(ScalaParser.Pattern2Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#pattern3}.
	 * @param ctx the parse tree
	 */
	void enterPattern3(ScalaParser.Pattern3Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#pattern3}.
	 * @param ctx the parse tree
	 */
	void exitPattern3(ScalaParser.Pattern3Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#simplePattern}.
	 * @param ctx the parse tree
	 */
	void enterSimplePattern(ScalaParser.SimplePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#simplePattern}.
	 * @param ctx the parse tree
	 */
	void exitSimplePattern(ScalaParser.SimplePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#patterns}.
	 * @param ctx the parse tree
	 */
	void enterPatterns(ScalaParser.PatternsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#patterns}.
	 * @param ctx the parse tree
	 */
	void exitPatterns(ScalaParser.PatternsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typeParamClause}.
	 * @param ctx the parse tree
	 */
	void enterTypeParamClause(ScalaParser.TypeParamClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typeParamClause}.
	 * @param ctx the parse tree
	 */
	void exitTypeParamClause(ScalaParser.TypeParamClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#funTypeParamClause}.
	 * @param ctx the parse tree
	 */
	void enterFunTypeParamClause(ScalaParser.FunTypeParamClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#funTypeParamClause}.
	 * @param ctx the parse tree
	 */
	void exitFunTypeParamClause(ScalaParser.FunTypeParamClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#variantTypeParam}.
	 * @param ctx the parse tree
	 */
	void enterVariantTypeParam(ScalaParser.VariantTypeParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#variantTypeParam}.
	 * @param ctx the parse tree
	 */
	void exitVariantTypeParam(ScalaParser.VariantTypeParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typeParam}.
	 * @param ctx the parse tree
	 */
	void enterTypeParam(ScalaParser.TypeParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typeParam}.
	 * @param ctx the parse tree
	 */
	void exitTypeParam(ScalaParser.TypeParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#paramClauses}.
	 * @param ctx the parse tree
	 */
	void enterParamClauses(ScalaParser.ParamClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#paramClauses}.
	 * @param ctx the parse tree
	 */
	void exitParamClauses(ScalaParser.ParamClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#paramClause}.
	 * @param ctx the parse tree
	 */
	void enterParamClause(ScalaParser.ParamClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#paramClause}.
	 * @param ctx the parse tree
	 */
	void exitParamClause(ScalaParser.ParamClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(ScalaParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(ScalaParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(ScalaParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(ScalaParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#paramType}.
	 * @param ctx the parse tree
	 */
	void enterParamType(ScalaParser.ParamTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#paramType}.
	 * @param ctx the parse tree
	 */
	void exitParamType(ScalaParser.ParamTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classParamClauses}.
	 * @param ctx the parse tree
	 */
	void enterClassParamClauses(ScalaParser.ClassParamClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classParamClauses}.
	 * @param ctx the parse tree
	 */
	void exitClassParamClauses(ScalaParser.ClassParamClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classParamClause}.
	 * @param ctx the parse tree
	 */
	void enterClassParamClause(ScalaParser.ClassParamClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classParamClause}.
	 * @param ctx the parse tree
	 */
	void exitClassParamClause(ScalaParser.ClassParamClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classParams}.
	 * @param ctx the parse tree
	 */
	void enterClassParams(ScalaParser.ClassParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classParams}.
	 * @param ctx the parse tree
	 */
	void exitClassParams(ScalaParser.ClassParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classParam}.
	 * @param ctx the parse tree
	 */
	void enterClassParam(ScalaParser.ClassParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classParam}.
	 * @param ctx the parse tree
	 */
	void exitClassParam(ScalaParser.ClassParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#bindings}.
	 * @param ctx the parse tree
	 */
	void enterBindings(ScalaParser.BindingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#bindings}.
	 * @param ctx the parse tree
	 */
	void exitBindings(ScalaParser.BindingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#binding}.
	 * @param ctx the parse tree
	 */
	void enterBinding(ScalaParser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#binding}.
	 * @param ctx the parse tree
	 */
	void exitBinding(ScalaParser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(ScalaParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(ScalaParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#localModifier}.
	 * @param ctx the parse tree
	 */
	void enterLocalModifier(ScalaParser.LocalModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#localModifier}.
	 * @param ctx the parse tree
	 */
	void exitLocalModifier(ScalaParser.LocalModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessModifier(ScalaParser.AccessModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessModifier(ScalaParser.AccessModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#accessQualifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessQualifier(ScalaParser.AccessQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#accessQualifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessQualifier(ScalaParser.AccessQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(ScalaParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(ScalaParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#constrAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterConstrAnnotation(ScalaParser.ConstrAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#constrAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitConstrAnnotation(ScalaParser.ConstrAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#templateBody}.
	 * @param ctx the parse tree
	 */
	void enterTemplateBody(ScalaParser.TemplateBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#templateBody}.
	 * @param ctx the parse tree
	 */
	void exitTemplateBody(ScalaParser.TemplateBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#templateStat}.
	 * @param ctx the parse tree
	 */
	void enterTemplateStat(ScalaParser.TemplateStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#templateStat}.
	 * @param ctx the parse tree
	 */
	void exitTemplateStat(ScalaParser.TemplateStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#selfType}.
	 * @param ctx the parse tree
	 */
	void enterSelfType(ScalaParser.SelfTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#selfType}.
	 * @param ctx the parse tree
	 */
	void exitSelfType(ScalaParser.SelfTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#import_}.
	 * @param ctx the parse tree
	 */
	void enterImport_(ScalaParser.Import_Context ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#import_}.
	 * @param ctx the parse tree
	 */
	void exitImport_(ScalaParser.Import_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#importExpr}.
	 * @param ctx the parse tree
	 */
	void enterImportExpr(ScalaParser.ImportExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#importExpr}.
	 * @param ctx the parse tree
	 */
	void exitImportExpr(ScalaParser.ImportExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#importSelectors}.
	 * @param ctx the parse tree
	 */
	void enterImportSelectors(ScalaParser.ImportSelectorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#importSelectors}.
	 * @param ctx the parse tree
	 */
	void exitImportSelectors(ScalaParser.ImportSelectorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#importSelector}.
	 * @param ctx the parse tree
	 */
	void enterImportSelector(ScalaParser.ImportSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#importSelector}.
	 * @param ctx the parse tree
	 */
	void exitImportSelector(ScalaParser.ImportSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#dcl}.
	 * @param ctx the parse tree
	 */
	void enterDcl(ScalaParser.DclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#dcl}.
	 * @param ctx the parse tree
	 */
	void exitDcl(ScalaParser.DclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#valDcl}.
	 * @param ctx the parse tree
	 */
	void enterValDcl(ScalaParser.ValDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#valDcl}.
	 * @param ctx the parse tree
	 */
	void exitValDcl(ScalaParser.ValDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void enterVarDcl(ScalaParser.VarDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#varDcl}.
	 * @param ctx the parse tree
	 */
	void exitVarDcl(ScalaParser.VarDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#funDcl}.
	 * @param ctx the parse tree
	 */
	void enterFunDcl(ScalaParser.FunDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#funDcl}.
	 * @param ctx the parse tree
	 */
	void exitFunDcl(ScalaParser.FunDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#funSig}.
	 * @param ctx the parse tree
	 */
	void enterFunSig(ScalaParser.FunSigContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#funSig}.
	 * @param ctx the parse tree
	 */
	void exitFunSig(ScalaParser.FunSigContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typeDcl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDcl(ScalaParser.TypeDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typeDcl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDcl(ScalaParser.TypeDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#patVarDef}.
	 * @param ctx the parse tree
	 */
	void enterPatVarDef(ScalaParser.PatVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#patVarDef}.
	 * @param ctx the parse tree
	 */
	void exitPatVarDef(ScalaParser.PatVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(ScalaParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(ScalaParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#patDef}.
	 * @param ctx the parse tree
	 */
	void enterPatDef(ScalaParser.PatDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#patDef}.
	 * @param ctx the parse tree
	 */
	void exitPatDef(ScalaParser.PatDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(ScalaParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(ScalaParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#funDef}.
	 * @param ctx the parse tree
	 */
	void enterFunDef(ScalaParser.FunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#funDef}.
	 * @param ctx the parse tree
	 */
	void exitFunDef(ScalaParser.FunDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(ScalaParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(ScalaParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#tmplDef}.
	 * @param ctx the parse tree
	 */
	void enterTmplDef(ScalaParser.TmplDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#tmplDef}.
	 * @param ctx the parse tree
	 */
	void exitTmplDef(ScalaParser.TmplDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(ScalaParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(ScalaParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#traitDef}.
	 * @param ctx the parse tree
	 */
	void enterTraitDef(ScalaParser.TraitDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#traitDef}.
	 * @param ctx the parse tree
	 */
	void exitTraitDef(ScalaParser.TraitDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#objectDef}.
	 * @param ctx the parse tree
	 */
	void enterObjectDef(ScalaParser.ObjectDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#objectDef}.
	 * @param ctx the parse tree
	 */
	void exitObjectDef(ScalaParser.ObjectDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classTemplateOpt}.
	 * @param ctx the parse tree
	 */
	void enterClassTemplateOpt(ScalaParser.ClassTemplateOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classTemplateOpt}.
	 * @param ctx the parse tree
	 */
	void exitClassTemplateOpt(ScalaParser.ClassTemplateOptContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#traitTemplateOpt}.
	 * @param ctx the parse tree
	 */
	void enterTraitTemplateOpt(ScalaParser.TraitTemplateOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#traitTemplateOpt}.
	 * @param ctx the parse tree
	 */
	void exitTraitTemplateOpt(ScalaParser.TraitTemplateOptContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classTemplate}.
	 * @param ctx the parse tree
	 */
	void enterClassTemplate(ScalaParser.ClassTemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classTemplate}.
	 * @param ctx the parse tree
	 */
	void exitClassTemplate(ScalaParser.ClassTemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#traitTemplate}.
	 * @param ctx the parse tree
	 */
	void enterTraitTemplate(ScalaParser.TraitTemplateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#traitTemplate}.
	 * @param ctx the parse tree
	 */
	void exitTraitTemplate(ScalaParser.TraitTemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#classParents}.
	 * @param ctx the parse tree
	 */
	void enterClassParents(ScalaParser.ClassParentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#classParents}.
	 * @param ctx the parse tree
	 */
	void exitClassParents(ScalaParser.ClassParentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#traitParents}.
	 * @param ctx the parse tree
	 */
	void enterTraitParents(ScalaParser.TraitParentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#traitParents}.
	 * @param ctx the parse tree
	 */
	void exitTraitParents(ScalaParser.TraitParentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#constr}.
	 * @param ctx the parse tree
	 */
	void enterConstr(ScalaParser.ConstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#constr}.
	 * @param ctx the parse tree
	 */
	void exitConstr(ScalaParser.ConstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#earlyDefs}.
	 * @param ctx the parse tree
	 */
	void enterEarlyDefs(ScalaParser.EarlyDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#earlyDefs}.
	 * @param ctx the parse tree
	 */
	void exitEarlyDefs(ScalaParser.EarlyDefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#earlyDef}.
	 * @param ctx the parse tree
	 */
	void enterEarlyDef(ScalaParser.EarlyDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#earlyDef}.
	 * @param ctx the parse tree
	 */
	void exitEarlyDef(ScalaParser.EarlyDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#constrExpr}.
	 * @param ctx the parse tree
	 */
	void enterConstrExpr(ScalaParser.ConstrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#constrExpr}.
	 * @param ctx the parse tree
	 */
	void exitConstrExpr(ScalaParser.ConstrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#constrBlock}.
	 * @param ctx the parse tree
	 */
	void enterConstrBlock(ScalaParser.ConstrBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#constrBlock}.
	 * @param ctx the parse tree
	 */
	void exitConstrBlock(ScalaParser.ConstrBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#selfInvocation}.
	 * @param ctx the parse tree
	 */
	void enterSelfInvocation(ScalaParser.SelfInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#selfInvocation}.
	 * @param ctx the parse tree
	 */
	void exitSelfInvocation(ScalaParser.SelfInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#topStatSeq}.
	 * @param ctx the parse tree
	 */
	void enterTopStatSeq(ScalaParser.TopStatSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#topStatSeq}.
	 * @param ctx the parse tree
	 */
	void exitTopStatSeq(ScalaParser.TopStatSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#topStat}.
	 * @param ctx the parse tree
	 */
	void enterTopStat(ScalaParser.TopStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#topStat}.
	 * @param ctx the parse tree
	 */
	void exitTopStat(ScalaParser.TopStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#packaging}.
	 * @param ctx the parse tree
	 */
	void enterPackaging(ScalaParser.PackagingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#packaging}.
	 * @param ctx the parse tree
	 */
	void exitPackaging(ScalaParser.PackagingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#packageObject}.
	 * @param ctx the parse tree
	 */
	void enterPackageObject(ScalaParser.PackageObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#packageObject}.
	 * @param ctx the parse tree
	 */
	void exitPackageObject(ScalaParser.PackageObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(ScalaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(ScalaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScalaParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void enterPackageDecl(ScalaParser.PackageDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScalaParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void exitPackageDecl(ScalaParser.PackageDeclContext ctx);
}