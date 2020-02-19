// Generated from CPP.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPPParser}.
 */
public interface CPPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPPParser#translationunit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationunit(CPPParser.TranslationunitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#translationunit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationunit(CPPParser.TranslationunitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#primaryexpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryexpression(CPPParser.PrimaryexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#primaryexpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryexpression(CPPParser.PrimaryexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#idexpression}.
	 * @param ctx the parse tree
	 */
	void enterIdexpression(CPPParser.IdexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#idexpression}.
	 * @param ctx the parse tree
	 */
	void exitIdexpression(CPPParser.IdexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#unqualifiedid}.
	 * @param ctx the parse tree
	 */
	void enterUnqualifiedid(CPPParser.UnqualifiedidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#unqualifiedid}.
	 * @param ctx the parse tree
	 */
	void exitUnqualifiedid(CPPParser.UnqualifiedidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#qualifiedid}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedid(CPPParser.QualifiedidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#qualifiedid}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedid(CPPParser.QualifiedidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#nestednamespecifier}.
	 * @param ctx the parse tree
	 */
	void enterNestednamespecifier(CPPParser.NestednamespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#nestednamespecifier}.
	 * @param ctx the parse tree
	 */
	void exitNestednamespecifier(CPPParser.NestednamespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#lambdaexpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaexpression(CPPParser.LambdaexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#lambdaexpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaexpression(CPPParser.LambdaexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#lambdaintroducer}.
	 * @param ctx the parse tree
	 */
	void enterLambdaintroducer(CPPParser.LambdaintroducerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#lambdaintroducer}.
	 * @param ctx the parse tree
	 */
	void exitLambdaintroducer(CPPParser.LambdaintroducerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#lambdacapture}.
	 * @param ctx the parse tree
	 */
	void enterLambdacapture(CPPParser.LambdacaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#lambdacapture}.
	 * @param ctx the parse tree
	 */
	void exitLambdacapture(CPPParser.LambdacaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#capturedefault}.
	 * @param ctx the parse tree
	 */
	void enterCapturedefault(CPPParser.CapturedefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#capturedefault}.
	 * @param ctx the parse tree
	 */
	void exitCapturedefault(CPPParser.CapturedefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#capturelist}.
	 * @param ctx the parse tree
	 */
	void enterCapturelist(CPPParser.CapturelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#capturelist}.
	 * @param ctx the parse tree
	 */
	void exitCapturelist(CPPParser.CapturelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(CPPParser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(CPPParser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#simplecapture}.
	 * @param ctx the parse tree
	 */
	void enterSimplecapture(CPPParser.SimplecaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#simplecapture}.
	 * @param ctx the parse tree
	 */
	void exitSimplecapture(CPPParser.SimplecaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initcapture}.
	 * @param ctx the parse tree
	 */
	void enterInitcapture(CPPParser.InitcaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initcapture}.
	 * @param ctx the parse tree
	 */
	void exitInitcapture(CPPParser.InitcaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#lambdadeclarator}.
	 * @param ctx the parse tree
	 */
	void enterLambdadeclarator(CPPParser.LambdadeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#lambdadeclarator}.
	 * @param ctx the parse tree
	 */
	void exitLambdadeclarator(CPPParser.LambdadeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#postfixexpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixexpression(CPPParser.PostfixexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#postfixexpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixexpression(CPPParser.PostfixexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typeidofexpr}.
	 * @param ctx the parse tree
	 */
	void enterTypeidofexpr(CPPParser.TypeidofexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typeidofexpr}.
	 * @param ctx the parse tree
	 */
	void exitTypeidofexpr(CPPParser.TypeidofexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typeidofthetypeid}.
	 * @param ctx the parse tree
	 */
	void enterTypeidofthetypeid(CPPParser.TypeidofthetypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typeidofthetypeid}.
	 * @param ctx the parse tree
	 */
	void exitTypeidofthetypeid(CPPParser.TypeidofthetypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#expressionlist}.
	 * @param ctx the parse tree
	 */
	void enterExpressionlist(CPPParser.ExpressionlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#expressionlist}.
	 * @param ctx the parse tree
	 */
	void exitExpressionlist(CPPParser.ExpressionlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#pseudodestructorname}.
	 * @param ctx the parse tree
	 */
	void enterPseudodestructorname(CPPParser.PseudodestructornameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#pseudodestructorname}.
	 * @param ctx the parse tree
	 */
	void exitPseudodestructorname(CPPParser.PseudodestructornameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#unaryexpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryexpression(CPPParser.UnaryexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#unaryexpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryexpression(CPPParser.UnaryexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#unaryoperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryoperator(CPPParser.UnaryoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#unaryoperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryoperator(CPPParser.UnaryoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#newexpression}.
	 * @param ctx the parse tree
	 */
	void enterNewexpression(CPPParser.NewexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#newexpression}.
	 * @param ctx the parse tree
	 */
	void exitNewexpression(CPPParser.NewexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#newplacement}.
	 * @param ctx the parse tree
	 */
	void enterNewplacement(CPPParser.NewplacementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#newplacement}.
	 * @param ctx the parse tree
	 */
	void exitNewplacement(CPPParser.NewplacementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#newtypeid}.
	 * @param ctx the parse tree
	 */
	void enterNewtypeid(CPPParser.NewtypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#newtypeid}.
	 * @param ctx the parse tree
	 */
	void exitNewtypeid(CPPParser.NewtypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#newdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNewdeclarator(CPPParser.NewdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#newdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNewdeclarator(CPPParser.NewdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noptrnewdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoptrnewdeclarator(CPPParser.NoptrnewdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noptrnewdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoptrnewdeclarator(CPPParser.NoptrnewdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#newinitializer}.
	 * @param ctx the parse tree
	 */
	void enterNewinitializer(CPPParser.NewinitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#newinitializer}.
	 * @param ctx the parse tree
	 */
	void exitNewinitializer(CPPParser.NewinitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#deleteexpression}.
	 * @param ctx the parse tree
	 */
	void enterDeleteexpression(CPPParser.DeleteexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#deleteexpression}.
	 * @param ctx the parse tree
	 */
	void exitDeleteexpression(CPPParser.DeleteexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noexceptexpression}.
	 * @param ctx the parse tree
	 */
	void enterNoexceptexpression(CPPParser.NoexceptexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noexceptexpression}.
	 * @param ctx the parse tree
	 */
	void exitNoexceptexpression(CPPParser.NoexceptexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#castexpression}.
	 * @param ctx the parse tree
	 */
	void enterCastexpression(CPPParser.CastexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#castexpression}.
	 * @param ctx the parse tree
	 */
	void exitCastexpression(CPPParser.CastexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#pmexpression}.
	 * @param ctx the parse tree
	 */
	void enterPmexpression(CPPParser.PmexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#pmexpression}.
	 * @param ctx the parse tree
	 */
	void exitPmexpression(CPPParser.PmexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#multiplicativeexpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeexpression(CPPParser.MultiplicativeexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#multiplicativeexpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeexpression(CPPParser.MultiplicativeexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#additiveexpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveexpression(CPPParser.AdditiveexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#additiveexpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveexpression(CPPParser.AdditiveexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#shiftexpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftexpression(CPPParser.ShiftexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#shiftexpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftexpression(CPPParser.ShiftexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#shiftoperator}.
	 * @param ctx the parse tree
	 */
	void enterShiftoperator(CPPParser.ShiftoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#shiftoperator}.
	 * @param ctx the parse tree
	 */
	void exitShiftoperator(CPPParser.ShiftoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#relationalexpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalexpression(CPPParser.RelationalexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#relationalexpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalexpression(CPPParser.RelationalexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#equalityexpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityexpression(CPPParser.EqualityexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#equalityexpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityexpression(CPPParser.EqualityexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#andexpression}.
	 * @param ctx the parse tree
	 */
	void enterAndexpression(CPPParser.AndexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#andexpression}.
	 * @param ctx the parse tree
	 */
	void exitAndexpression(CPPParser.AndexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#exclusiveorexpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveorexpression(CPPParser.ExclusiveorexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#exclusiveorexpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveorexpression(CPPParser.ExclusiveorexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#inclusiveorexpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveorexpression(CPPParser.InclusiveorexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#inclusiveorexpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveorexpression(CPPParser.InclusiveorexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#logicalandexpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalandexpression(CPPParser.LogicalandexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#logicalandexpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalandexpression(CPPParser.LogicalandexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#logicalorexpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalorexpression(CPPParser.LogicalorexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#logicalorexpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalorexpression(CPPParser.LogicalorexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#conditionalexpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalexpression(CPPParser.ConditionalexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#conditionalexpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalexpression(CPPParser.ConditionalexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#assignmentexpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentexpression(CPPParser.AssignmentexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#assignmentexpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentexpression(CPPParser.AssignmentexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#assignmentoperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentoperator(CPPParser.AssignmentoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#assignmentoperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentoperator(CPPParser.AssignmentoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CPPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CPPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#constantexpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantexpression(CPPParser.ConstantexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#constantexpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantexpression(CPPParser.ConstantexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CPPParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CPPParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#labeledstatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledstatement(CPPParser.LabeledstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#labeledstatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledstatement(CPPParser.LabeledstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#expressionstatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionstatement(CPPParser.ExpressionstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#expressionstatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionstatement(CPPParser.ExpressionstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#compoundstatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundstatement(CPPParser.CompoundstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#compoundstatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundstatement(CPPParser.CompoundstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#statementseq}.
	 * @param ctx the parse tree
	 */
	void enterStatementseq(CPPParser.StatementseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#statementseq}.
	 * @param ctx the parse tree
	 */
	void exitStatementseq(CPPParser.StatementseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#selectionstatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionstatement(CPPParser.SelectionstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#selectionstatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionstatement(CPPParser.SelectionstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(CPPParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(CPPParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#iterationstatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationstatement(CPPParser.IterationstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#iterationstatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationstatement(CPPParser.IterationstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#forinitstatement}.
	 * @param ctx the parse tree
	 */
	void enterForinitstatement(CPPParser.ForinitstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#forinitstatement}.
	 * @param ctx the parse tree
	 */
	void exitForinitstatement(CPPParser.ForinitstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#forrangedeclaration}.
	 * @param ctx the parse tree
	 */
	void enterForrangedeclaration(CPPParser.ForrangedeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#forrangedeclaration}.
	 * @param ctx the parse tree
	 */
	void exitForrangedeclaration(CPPParser.ForrangedeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#forrangeinitializer}.
	 * @param ctx the parse tree
	 */
	void enterForrangeinitializer(CPPParser.ForrangeinitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#forrangeinitializer}.
	 * @param ctx the parse tree
	 */
	void exitForrangeinitializer(CPPParser.ForrangeinitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#jumpstatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpstatement(CPPParser.JumpstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#jumpstatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpstatement(CPPParser.JumpstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declarationstatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationstatement(CPPParser.DeclarationstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declarationstatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationstatement(CPPParser.DeclarationstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declarationseq}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationseq(CPPParser.DeclarationseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declarationseq}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationseq(CPPParser.DeclarationseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CPPParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CPPParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#blockdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBlockdeclaration(CPPParser.BlockdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#blockdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBlockdeclaration(CPPParser.BlockdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#aliasdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAliasdeclaration(CPPParser.AliasdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#aliasdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAliasdeclaration(CPPParser.AliasdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#simpledeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpledeclaration(CPPParser.SimpledeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#simpledeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpledeclaration(CPPParser.SimpledeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#static_assertdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStatic_assertdeclaration(CPPParser.Static_assertdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#static_assertdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStatic_assertdeclaration(CPPParser.Static_assertdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#emptydeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEmptydeclaration(CPPParser.EmptydeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#emptydeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEmptydeclaration(CPPParser.EmptydeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributedeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAttributedeclaration(CPPParser.AttributedeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributedeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAttributedeclaration(CPPParser.AttributedeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declspecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclspecifier(CPPParser.DeclspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declspecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclspecifier(CPPParser.DeclspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declspecifierseq}.
	 * @param ctx the parse tree
	 */
	void enterDeclspecifierseq(CPPParser.DeclspecifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declspecifierseq}.
	 * @param ctx the parse tree
	 */
	void exitDeclspecifierseq(CPPParser.DeclspecifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#storageclassspecifier}.
	 * @param ctx the parse tree
	 */
	void enterStorageclassspecifier(CPPParser.StorageclassspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#storageclassspecifier}.
	 * @param ctx the parse tree
	 */
	void exitStorageclassspecifier(CPPParser.StorageclassspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#functionspecifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionspecifier(CPPParser.FunctionspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#functionspecifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionspecifier(CPPParser.FunctionspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typedefname}.
	 * @param ctx the parse tree
	 */
	void enterTypedefname(CPPParser.TypedefnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typedefname}.
	 * @param ctx the parse tree
	 */
	void exitTypedefname(CPPParser.TypedefnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typespecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypespecifier(CPPParser.TypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typespecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypespecifier(CPPParser.TypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#trailingtypespecifier}.
	 * @param ctx the parse tree
	 */
	void enterTrailingtypespecifier(CPPParser.TrailingtypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#trailingtypespecifier}.
	 * @param ctx the parse tree
	 */
	void exitTrailingtypespecifier(CPPParser.TrailingtypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typespecifierseq}.
	 * @param ctx the parse tree
	 */
	void enterTypespecifierseq(CPPParser.TypespecifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typespecifierseq}.
	 * @param ctx the parse tree
	 */
	void exitTypespecifierseq(CPPParser.TypespecifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#trailingtypespecifierseq}.
	 * @param ctx the parse tree
	 */
	void enterTrailingtypespecifierseq(CPPParser.TrailingtypespecifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#trailingtypespecifierseq}.
	 * @param ctx the parse tree
	 */
	void exitTrailingtypespecifierseq(CPPParser.TrailingtypespecifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#simpletypespecifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpletypespecifier(CPPParser.SimpletypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#simpletypespecifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpletypespecifier(CPPParser.SimpletypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#thetypename}.
	 * @param ctx the parse tree
	 */
	void enterThetypename(CPPParser.ThetypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#thetypename}.
	 * @param ctx the parse tree
	 */
	void exitThetypename(CPPParser.ThetypenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#decltypespecifier}.
	 * @param ctx the parse tree
	 */
	void enterDecltypespecifier(CPPParser.DecltypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#decltypespecifier}.
	 * @param ctx the parse tree
	 */
	void exitDecltypespecifier(CPPParser.DecltypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#elaboratedtypespecifier}.
	 * @param ctx the parse tree
	 */
	void enterElaboratedtypespecifier(CPPParser.ElaboratedtypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#elaboratedtypespecifier}.
	 * @param ctx the parse tree
	 */
	void exitElaboratedtypespecifier(CPPParser.ElaboratedtypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumname}.
	 * @param ctx the parse tree
	 */
	void enterEnumname(CPPParser.EnumnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumname}.
	 * @param ctx the parse tree
	 */
	void exitEnumname(CPPParser.EnumnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumspecifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumspecifier(CPPParser.EnumspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumspecifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumspecifier(CPPParser.EnumspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumhead}.
	 * @param ctx the parse tree
	 */
	void enterEnumhead(CPPParser.EnumheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumhead}.
	 * @param ctx the parse tree
	 */
	void exitEnumhead(CPPParser.EnumheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#opaqueenumdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterOpaqueenumdeclaration(CPPParser.OpaqueenumdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#opaqueenumdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitOpaqueenumdeclaration(CPPParser.OpaqueenumdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumkey}.
	 * @param ctx the parse tree
	 */
	void enterEnumkey(CPPParser.EnumkeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumkey}.
	 * @param ctx the parse tree
	 */
	void exitEnumkey(CPPParser.EnumkeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumbase}.
	 * @param ctx the parse tree
	 */
	void enterEnumbase(CPPParser.EnumbaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumbase}.
	 * @param ctx the parse tree
	 */
	void exitEnumbase(CPPParser.EnumbaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumeratorlist}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorlist(CPPParser.EnumeratorlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumeratorlist}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorlist(CPPParser.EnumeratorlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumeratordefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratordefinition(CPPParser.EnumeratordefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumeratordefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratordefinition(CPPParser.EnumeratordefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void enterEnumerator(CPPParser.EnumeratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void exitEnumerator(CPPParser.EnumeratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namespacename}.
	 * @param ctx the parse tree
	 */
	void enterNamespacename(CPPParser.NamespacenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namespacename}.
	 * @param ctx the parse tree
	 */
	void exitNamespacename(CPPParser.NamespacenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#originalnamespacename}.
	 * @param ctx the parse tree
	 */
	void enterOriginalnamespacename(CPPParser.OriginalnamespacenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#originalnamespacename}.
	 * @param ctx the parse tree
	 */
	void exitOriginalnamespacename(CPPParser.OriginalnamespacenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namespacedefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespacedefinition(CPPParser.NamespacedefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namespacedefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespacedefinition(CPPParser.NamespacedefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namednamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamednamespacedefinition(CPPParser.NamednamespacedefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namednamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamednamespacedefinition(CPPParser.NamednamespacedefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#originalnamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void enterOriginalnamespacedefinition(CPPParser.OriginalnamespacedefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#originalnamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void exitOriginalnamespacedefinition(CPPParser.OriginalnamespacedefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#extensionnamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void enterExtensionnamespacedefinition(CPPParser.ExtensionnamespacedefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#extensionnamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void exitExtensionnamespacedefinition(CPPParser.ExtensionnamespacedefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#unnamednamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void enterUnnamednamespacedefinition(CPPParser.UnnamednamespacedefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#unnamednamespacedefinition}.
	 * @param ctx the parse tree
	 */
	void exitUnnamednamespacedefinition(CPPParser.UnnamednamespacedefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namespacebody}.
	 * @param ctx the parse tree
	 */
	void enterNamespacebody(CPPParser.NamespacebodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namespacebody}.
	 * @param ctx the parse tree
	 */
	void exitNamespacebody(CPPParser.NamespacebodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namespacealias}.
	 * @param ctx the parse tree
	 */
	void enterNamespacealias(CPPParser.NamespacealiasContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namespacealias}.
	 * @param ctx the parse tree
	 */
	void exitNamespacealias(CPPParser.NamespacealiasContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#namespacealiasdefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespacealiasdefinition(CPPParser.NamespacealiasdefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#namespacealiasdefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespacealiasdefinition(CPPParser.NamespacealiasdefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#qualifiednamespacespecifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiednamespacespecifier(CPPParser.QualifiednamespacespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#qualifiednamespacespecifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiednamespacespecifier(CPPParser.QualifiednamespacespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#usingdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterUsingdeclaration(CPPParser.UsingdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#usingdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitUsingdeclaration(CPPParser.UsingdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#usingdirective}.
	 * @param ctx the parse tree
	 */
	void enterUsingdirective(CPPParser.UsingdirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#usingdirective}.
	 * @param ctx the parse tree
	 */
	void exitUsingdirective(CPPParser.UsingdirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#asmdefinition}.
	 * @param ctx the parse tree
	 */
	void enterAsmdefinition(CPPParser.AsmdefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#asmdefinition}.
	 * @param ctx the parse tree
	 */
	void exitAsmdefinition(CPPParser.AsmdefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#linkagespecification}.
	 * @param ctx the parse tree
	 */
	void enterLinkagespecification(CPPParser.LinkagespecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#linkagespecification}.
	 * @param ctx the parse tree
	 */
	void exitLinkagespecification(CPPParser.LinkagespecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributespecifierseq}.
	 * @param ctx the parse tree
	 */
	void enterAttributespecifierseq(CPPParser.AttributespecifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributespecifierseq}.
	 * @param ctx the parse tree
	 */
	void exitAttributespecifierseq(CPPParser.AttributespecifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributespecifier}.
	 * @param ctx the parse tree
	 */
	void enterAttributespecifier(CPPParser.AttributespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributespecifier}.
	 * @param ctx the parse tree
	 */
	void exitAttributespecifier(CPPParser.AttributespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#alignmentspecifier}.
	 * @param ctx the parse tree
	 */
	void enterAlignmentspecifier(CPPParser.AlignmentspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#alignmentspecifier}.
	 * @param ctx the parse tree
	 */
	void exitAlignmentspecifier(CPPParser.AlignmentspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void enterAttributelist(CPPParser.AttributelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributelist}.
	 * @param ctx the parse tree
	 */
	void exitAttributelist(CPPParser.AttributelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(CPPParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(CPPParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributetoken}.
	 * @param ctx the parse tree
	 */
	void enterAttributetoken(CPPParser.AttributetokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributetoken}.
	 * @param ctx the parse tree
	 */
	void exitAttributetoken(CPPParser.AttributetokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributescopedtoken}.
	 * @param ctx the parse tree
	 */
	void enterAttributescopedtoken(CPPParser.AttributescopedtokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributescopedtoken}.
	 * @param ctx the parse tree
	 */
	void exitAttributescopedtoken(CPPParser.AttributescopedtokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributenamespace}.
	 * @param ctx the parse tree
	 */
	void enterAttributenamespace(CPPParser.AttributenamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributenamespace}.
	 * @param ctx the parse tree
	 */
	void exitAttributenamespace(CPPParser.AttributenamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#attributeargumentclause}.
	 * @param ctx the parse tree
	 */
	void enterAttributeargumentclause(CPPParser.AttributeargumentclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#attributeargumentclause}.
	 * @param ctx the parse tree
	 */
	void exitAttributeargumentclause(CPPParser.AttributeargumentclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#balancedtokenseq}.
	 * @param ctx the parse tree
	 */
	void enterBalancedtokenseq(CPPParser.BalancedtokenseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#balancedtokenseq}.
	 * @param ctx the parse tree
	 */
	void exitBalancedtokenseq(CPPParser.BalancedtokenseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#balancedtoken}.
	 * @param ctx the parse tree
	 */
	void enterBalancedtoken(CPPParser.BalancedtokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#balancedtoken}.
	 * @param ctx the parse tree
	 */
	void exitBalancedtoken(CPPParser.BalancedtokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initdeclaratorlist}.
	 * @param ctx the parse tree
	 */
	void enterInitdeclaratorlist(CPPParser.InitdeclaratorlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initdeclaratorlist}.
	 * @param ctx the parse tree
	 */
	void exitInitdeclaratorlist(CPPParser.InitdeclaratorlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitdeclarator(CPPParser.InitdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitdeclarator(CPPParser.InitdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(CPPParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(CPPParser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#ptrdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPtrdeclarator(CPPParser.PtrdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#ptrdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPtrdeclarator(CPPParser.PtrdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noptrdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoptrdeclarator(CPPParser.NoptrdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noptrdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoptrdeclarator(CPPParser.NoptrdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#parametersandqualifiers}.
	 * @param ctx the parse tree
	 */
	void enterParametersandqualifiers(CPPParser.ParametersandqualifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#parametersandqualifiers}.
	 * @param ctx the parse tree
	 */
	void exitParametersandqualifiers(CPPParser.ParametersandqualifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#trailingreturntype}.
	 * @param ctx the parse tree
	 */
	void enterTrailingreturntype(CPPParser.TrailingreturntypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#trailingreturntype}.
	 * @param ctx the parse tree
	 */
	void exitTrailingreturntype(CPPParser.TrailingreturntypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#ptroperator}.
	 * @param ctx the parse tree
	 */
	void enterPtroperator(CPPParser.PtroperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#ptroperator}.
	 * @param ctx the parse tree
	 */
	void exitPtroperator(CPPParser.PtroperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#cvqualifierseq}.
	 * @param ctx the parse tree
	 */
	void enterCvqualifierseq(CPPParser.CvqualifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#cvqualifierseq}.
	 * @param ctx the parse tree
	 */
	void exitCvqualifierseq(CPPParser.CvqualifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#cvqualifier}.
	 * @param ctx the parse tree
	 */
	void enterCvqualifier(CPPParser.CvqualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#cvqualifier}.
	 * @param ctx the parse tree
	 */
	void exitCvqualifier(CPPParser.CvqualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#refqualifier}.
	 * @param ctx the parse tree
	 */
	void enterRefqualifier(CPPParser.RefqualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#refqualifier}.
	 * @param ctx the parse tree
	 */
	void exitRefqualifier(CPPParser.RefqualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#declaratorid}.
	 * @param ctx the parse tree
	 */
	void enterDeclaratorid(CPPParser.DeclaratoridContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#declaratorid}.
	 * @param ctx the parse tree
	 */
	void exitDeclaratorid(CPPParser.DeclaratoridContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#thetypeid}.
	 * @param ctx the parse tree
	 */
	void enterThetypeid(CPPParser.ThetypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#thetypeid}.
	 * @param ctx the parse tree
	 */
	void exitThetypeid(CPPParser.ThetypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#abstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractdeclarator(CPPParser.AbstractdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#abstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractdeclarator(CPPParser.AbstractdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#ptrabstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPtrabstractdeclarator(CPPParser.PtrabstractdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#ptrabstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPtrabstractdeclarator(CPPParser.PtrabstractdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noptrabstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoptrabstractdeclarator(CPPParser.NoptrabstractdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noptrabstractdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoptrabstractdeclarator(CPPParser.NoptrabstractdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#abstractpackdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractpackdeclarator(CPPParser.AbstractpackdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#abstractpackdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractpackdeclarator(CPPParser.AbstractpackdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noptrabstractpackdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterNoptrabstractpackdeclarator(CPPParser.NoptrabstractpackdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noptrabstractpackdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitNoptrabstractpackdeclarator(CPPParser.NoptrabstractpackdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#parameterdeclarationclause}.
	 * @param ctx the parse tree
	 */
	void enterParameterdeclarationclause(CPPParser.ParameterdeclarationclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#parameterdeclarationclause}.
	 * @param ctx the parse tree
	 */
	void exitParameterdeclarationclause(CPPParser.ParameterdeclarationclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#parameterdeclarationlist}.
	 * @param ctx the parse tree
	 */
	void enterParameterdeclarationlist(CPPParser.ParameterdeclarationlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#parameterdeclarationlist}.
	 * @param ctx the parse tree
	 */
	void exitParameterdeclarationlist(CPPParser.ParameterdeclarationlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#parameterdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterdeclaration(CPPParser.ParameterdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#parameterdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterdeclaration(CPPParser.ParameterdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#functiondefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondefinition(CPPParser.FunctiondefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#functiondefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondefinition(CPPParser.FunctiondefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#functionbody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionbody(CPPParser.FunctionbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#functionbody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionbody(CPPParser.FunctionbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(CPPParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(CPPParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#braceorequalinitializer}.
	 * @param ctx the parse tree
	 */
	void enterBraceorequalinitializer(CPPParser.BraceorequalinitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#braceorequalinitializer}.
	 * @param ctx the parse tree
	 */
	void exitBraceorequalinitializer(CPPParser.BraceorequalinitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initializerclause}.
	 * @param ctx the parse tree
	 */
	void enterInitializerclause(CPPParser.InitializerclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initializerclause}.
	 * @param ctx the parse tree
	 */
	void exitInitializerclause(CPPParser.InitializerclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#initializerlist}.
	 * @param ctx the parse tree
	 */
	void enterInitializerlist(CPPParser.InitializerlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#initializerlist}.
	 * @param ctx the parse tree
	 */
	void exitInitializerlist(CPPParser.InitializerlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#bracedinitlist}.
	 * @param ctx the parse tree
	 */
	void enterBracedinitlist(CPPParser.BracedinitlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#bracedinitlist}.
	 * @param ctx the parse tree
	 */
	void exitBracedinitlist(CPPParser.BracedinitlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classname}.
	 * @param ctx the parse tree
	 */
	void enterClassname(CPPParser.ClassnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classname}.
	 * @param ctx the parse tree
	 */
	void exitClassname(CPPParser.ClassnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classspecifier}.
	 * @param ctx the parse tree
	 */
	void enterClassspecifier(CPPParser.ClassspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classspecifier}.
	 * @param ctx the parse tree
	 */
	void exitClassspecifier(CPPParser.ClassspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classhead}.
	 * @param ctx the parse tree
	 */
	void enterClasshead(CPPParser.ClassheadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classhead}.
	 * @param ctx the parse tree
	 */
	void exitClasshead(CPPParser.ClassheadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classheadname}.
	 * @param ctx the parse tree
	 */
	void enterClassheadname(CPPParser.ClassheadnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classheadname}.
	 * @param ctx the parse tree
	 */
	void exitClassheadname(CPPParser.ClassheadnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classvirtspecifier}.
	 * @param ctx the parse tree
	 */
	void enterClassvirtspecifier(CPPParser.ClassvirtspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classvirtspecifier}.
	 * @param ctx the parse tree
	 */
	void exitClassvirtspecifier(CPPParser.ClassvirtspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classkey}.
	 * @param ctx the parse tree
	 */
	void enterClasskey(CPPParser.ClasskeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classkey}.
	 * @param ctx the parse tree
	 */
	void exitClasskey(CPPParser.ClasskeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#memberspecification}.
	 * @param ctx the parse tree
	 */
	void enterMemberspecification(CPPParser.MemberspecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#memberspecification}.
	 * @param ctx the parse tree
	 */
	void exitMemberspecification(CPPParser.MemberspecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#memberdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberdeclaration(CPPParser.MemberdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#memberdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberdeclaration(CPPParser.MemberdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#memberdeclaratorlist}.
	 * @param ctx the parse tree
	 */
	void enterMemberdeclaratorlist(CPPParser.MemberdeclaratorlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#memberdeclaratorlist}.
	 * @param ctx the parse tree
	 */
	void exitMemberdeclaratorlist(CPPParser.MemberdeclaratorlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#memberdeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMemberdeclarator(CPPParser.MemberdeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#memberdeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMemberdeclarator(CPPParser.MemberdeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#virtspecifierseq}.
	 * @param ctx the parse tree
	 */
	void enterVirtspecifierseq(CPPParser.VirtspecifierseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#virtspecifierseq}.
	 * @param ctx the parse tree
	 */
	void exitVirtspecifierseq(CPPParser.VirtspecifierseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#virtspecifier}.
	 * @param ctx the parse tree
	 */
	void enterVirtspecifier(CPPParser.VirtspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#virtspecifier}.
	 * @param ctx the parse tree
	 */
	void exitVirtspecifier(CPPParser.VirtspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#purespecifier}.
	 * @param ctx the parse tree
	 */
	void enterPurespecifier(CPPParser.PurespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#purespecifier}.
	 * @param ctx the parse tree
	 */
	void exitPurespecifier(CPPParser.PurespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#baseclause}.
	 * @param ctx the parse tree
	 */
	void enterBaseclause(CPPParser.BaseclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#baseclause}.
	 * @param ctx the parse tree
	 */
	void exitBaseclause(CPPParser.BaseclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#basespecifierlist}.
	 * @param ctx the parse tree
	 */
	void enterBasespecifierlist(CPPParser.BasespecifierlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#basespecifierlist}.
	 * @param ctx the parse tree
	 */
	void exitBasespecifierlist(CPPParser.BasespecifierlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#basespecifier}.
	 * @param ctx the parse tree
	 */
	void enterBasespecifier(CPPParser.BasespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#basespecifier}.
	 * @param ctx the parse tree
	 */
	void exitBasespecifier(CPPParser.BasespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#classordecltype}.
	 * @param ctx the parse tree
	 */
	void enterClassordecltype(CPPParser.ClassordecltypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#classordecltype}.
	 * @param ctx the parse tree
	 */
	void exitClassordecltype(CPPParser.ClassordecltypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#basetypespecifier}.
	 * @param ctx the parse tree
	 */
	void enterBasetypespecifier(CPPParser.BasetypespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#basetypespecifier}.
	 * @param ctx the parse tree
	 */
	void exitBasetypespecifier(CPPParser.BasetypespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#accessspecifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessspecifier(CPPParser.AccessspecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#accessspecifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessspecifier(CPPParser.AccessspecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#conversionfunctionid}.
	 * @param ctx the parse tree
	 */
	void enterConversionfunctionid(CPPParser.ConversionfunctionidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#conversionfunctionid}.
	 * @param ctx the parse tree
	 */
	void exitConversionfunctionid(CPPParser.ConversionfunctionidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#conversiontypeid}.
	 * @param ctx the parse tree
	 */
	void enterConversiontypeid(CPPParser.ConversiontypeidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#conversiontypeid}.
	 * @param ctx the parse tree
	 */
	void exitConversiontypeid(CPPParser.ConversiontypeidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#conversiondeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConversiondeclarator(CPPParser.ConversiondeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#conversiondeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConversiondeclarator(CPPParser.ConversiondeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#ctorinitializer}.
	 * @param ctx the parse tree
	 */
	void enterCtorinitializer(CPPParser.CtorinitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#ctorinitializer}.
	 * @param ctx the parse tree
	 */
	void exitCtorinitializer(CPPParser.CtorinitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#meminitializerlist}.
	 * @param ctx the parse tree
	 */
	void enterMeminitializerlist(CPPParser.MeminitializerlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#meminitializerlist}.
	 * @param ctx the parse tree
	 */
	void exitMeminitializerlist(CPPParser.MeminitializerlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#meminitializer}.
	 * @param ctx the parse tree
	 */
	void enterMeminitializer(CPPParser.MeminitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#meminitializer}.
	 * @param ctx the parse tree
	 */
	void exitMeminitializer(CPPParser.MeminitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#meminitializerid}.
	 * @param ctx the parse tree
	 */
	void enterMeminitializerid(CPPParser.MeminitializeridContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#meminitializerid}.
	 * @param ctx the parse tree
	 */
	void exitMeminitializerid(CPPParser.MeminitializeridContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#operatorfunctionid}.
	 * @param ctx the parse tree
	 */
	void enterOperatorfunctionid(CPPParser.OperatorfunctionidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#operatorfunctionid}.
	 * @param ctx the parse tree
	 */
	void exitOperatorfunctionid(CPPParser.OperatorfunctionidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#literaloperatorid}.
	 * @param ctx the parse tree
	 */
	void enterLiteraloperatorid(CPPParser.LiteraloperatoridContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#literaloperatorid}.
	 * @param ctx the parse tree
	 */
	void exitLiteraloperatorid(CPPParser.LiteraloperatoridContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templatedeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTemplatedeclaration(CPPParser.TemplatedeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templatedeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTemplatedeclaration(CPPParser.TemplatedeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templateparameterlist}.
	 * @param ctx the parse tree
	 */
	void enterTemplateparameterlist(CPPParser.TemplateparameterlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templateparameterlist}.
	 * @param ctx the parse tree
	 */
	void exitTemplateparameterlist(CPPParser.TemplateparameterlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templateparameter}.
	 * @param ctx the parse tree
	 */
	void enterTemplateparameter(CPPParser.TemplateparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templateparameter}.
	 * @param ctx the parse tree
	 */
	void exitTemplateparameter(CPPParser.TemplateparameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typeparameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeparameter(CPPParser.TypeparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typeparameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeparameter(CPPParser.TypeparameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#simpletemplateid}.
	 * @param ctx the parse tree
	 */
	void enterSimpletemplateid(CPPParser.SimpletemplateidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#simpletemplateid}.
	 * @param ctx the parse tree
	 */
	void exitSimpletemplateid(CPPParser.SimpletemplateidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templateid}.
	 * @param ctx the parse tree
	 */
	void enterTemplateid(CPPParser.TemplateidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templateid}.
	 * @param ctx the parse tree
	 */
	void exitTemplateid(CPPParser.TemplateidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templatename}.
	 * @param ctx the parse tree
	 */
	void enterTemplatename(CPPParser.TemplatenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templatename}.
	 * @param ctx the parse tree
	 */
	void exitTemplatename(CPPParser.TemplatenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templateargumentlist}.
	 * @param ctx the parse tree
	 */
	void enterTemplateargumentlist(CPPParser.TemplateargumentlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templateargumentlist}.
	 * @param ctx the parse tree
	 */
	void exitTemplateargumentlist(CPPParser.TemplateargumentlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#templateargument}.
	 * @param ctx the parse tree
	 */
	void enterTemplateargument(CPPParser.TemplateargumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#templateargument}.
	 * @param ctx the parse tree
	 */
	void exitTemplateargument(CPPParser.TemplateargumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typenamespecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypenamespecifier(CPPParser.TypenamespecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typenamespecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypenamespecifier(CPPParser.TypenamespecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#explicitinstantiation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitinstantiation(CPPParser.ExplicitinstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#explicitinstantiation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitinstantiation(CPPParser.ExplicitinstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#explicitspecialization}.
	 * @param ctx the parse tree
	 */
	void enterExplicitspecialization(CPPParser.ExplicitspecializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#explicitspecialization}.
	 * @param ctx the parse tree
	 */
	void exitExplicitspecialization(CPPParser.ExplicitspecializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#tryblock}.
	 * @param ctx the parse tree
	 */
	void enterTryblock(CPPParser.TryblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#tryblock}.
	 * @param ctx the parse tree
	 */
	void exitTryblock(CPPParser.TryblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#functiontryblock}.
	 * @param ctx the parse tree
	 */
	void enterFunctiontryblock(CPPParser.FunctiontryblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#functiontryblock}.
	 * @param ctx the parse tree
	 */
	void exitFunctiontryblock(CPPParser.FunctiontryblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#handlerseq}.
	 * @param ctx the parse tree
	 */
	void enterHandlerseq(CPPParser.HandlerseqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#handlerseq}.
	 * @param ctx the parse tree
	 */
	void exitHandlerseq(CPPParser.HandlerseqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#handler}.
	 * @param ctx the parse tree
	 */
	void enterHandler(CPPParser.HandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#handler}.
	 * @param ctx the parse tree
	 */
	void exitHandler(CPPParser.HandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#exceptiondeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExceptiondeclaration(CPPParser.ExceptiondeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#exceptiondeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExceptiondeclaration(CPPParser.ExceptiondeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#throwexpression}.
	 * @param ctx the parse tree
	 */
	void enterThrowexpression(CPPParser.ThrowexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#throwexpression}.
	 * @param ctx the parse tree
	 */
	void exitThrowexpression(CPPParser.ThrowexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#exceptionspecification}.
	 * @param ctx the parse tree
	 */
	void enterExceptionspecification(CPPParser.ExceptionspecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#exceptionspecification}.
	 * @param ctx the parse tree
	 */
	void exitExceptionspecification(CPPParser.ExceptionspecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#dynamicexceptionspecification}.
	 * @param ctx the parse tree
	 */
	void enterDynamicexceptionspecification(CPPParser.DynamicexceptionspecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#dynamicexceptionspecification}.
	 * @param ctx the parse tree
	 */
	void exitDynamicexceptionspecification(CPPParser.DynamicexceptionspecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#typeidlist}.
	 * @param ctx the parse tree
	 */
	void enterTypeidlist(CPPParser.TypeidlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#typeidlist}.
	 * @param ctx the parse tree
	 */
	void exitTypeidlist(CPPParser.TypeidlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#noexceptspecification}.
	 * @param ctx the parse tree
	 */
	void enterNoexceptspecification(CPPParser.NoexceptspecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#noexceptspecification}.
	 * @param ctx the parse tree
	 */
	void exitNoexceptspecification(CPPParser.NoexceptspecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#theoperator}.
	 * @param ctx the parse tree
	 */
	void enterTheoperator(CPPParser.TheoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#theoperator}.
	 * @param ctx the parse tree
	 */
	void exitTheoperator(CPPParser.TheoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CPPParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CPPParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#booleanliteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanliteral(CPPParser.BooleanliteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#booleanliteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanliteral(CPPParser.BooleanliteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#pointerliteral}.
	 * @param ctx the parse tree
	 */
	void enterPointerliteral(CPPParser.PointerliteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#pointerliteral}.
	 * @param ctx the parse tree
	 */
	void exitPointerliteral(CPPParser.PointerliteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPParser#userdefinedliteral}.
	 * @param ctx the parse tree
	 */
	void enterUserdefinedliteral(CPPParser.UserdefinedliteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPParser#userdefinedliteral}.
	 * @param ctx the parse tree
	 */
	void exitUserdefinedliteral(CPPParser.UserdefinedliteralContext ctx);
}