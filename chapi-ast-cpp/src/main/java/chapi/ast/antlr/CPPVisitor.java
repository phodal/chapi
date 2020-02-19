// Generated from CPP.g4 by ANTLR 4.8
package chapi.ast.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPPParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPPVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPPParser#translationunit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationunit(CPPParser.TranslationunitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#primaryexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryexpression(CPPParser.PrimaryexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#idexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdexpression(CPPParser.IdexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#unqualifiedid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnqualifiedid(CPPParser.UnqualifiedidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#qualifiedid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedid(CPPParser.QualifiedidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#nestednamespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestednamespecifier(CPPParser.NestednamespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#lambdaexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaexpression(CPPParser.LambdaexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#lambdaintroducer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaintroducer(CPPParser.LambdaintroducerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#lambdacapture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdacapture(CPPParser.LambdacaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#capturedefault}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapturedefault(CPPParser.CapturedefaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#capturelist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapturelist(CPPParser.CapturelistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapture(CPPParser.CaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#simplecapture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplecapture(CPPParser.SimplecaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initcapture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitcapture(CPPParser.InitcaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#lambdadeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdadeclarator(CPPParser.LambdadeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#postfixexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixexpression(CPPParser.PostfixexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typeidofexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeidofexpr(CPPParser.TypeidofexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typeidofthetypeid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeidofthetypeid(CPPParser.TypeidofthetypeidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#expressionlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionlist(CPPParser.ExpressionlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#pseudodestructorname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudodestructorname(CPPParser.PseudodestructornameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#unaryexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryexpression(CPPParser.UnaryexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#unaryoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryoperator(CPPParser.UnaryoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#newexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewexpression(CPPParser.NewexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#newplacement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewplacement(CPPParser.NewplacementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#newtypeid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewtypeid(CPPParser.NewtypeidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#newdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewdeclarator(CPPParser.NewdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noptrnewdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoptrnewdeclarator(CPPParser.NoptrnewdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#newinitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewinitializer(CPPParser.NewinitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#deleteexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteexpression(CPPParser.DeleteexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noexceptexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoexceptexpression(CPPParser.NoexceptexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#castexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastexpression(CPPParser.CastexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#pmexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPmexpression(CPPParser.PmexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#multiplicativeexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeexpression(CPPParser.MultiplicativeexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#additiveexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveexpression(CPPParser.AdditiveexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#shiftexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftexpression(CPPParser.ShiftexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#shiftoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftoperator(CPPParser.ShiftoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#relationalexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalexpression(CPPParser.RelationalexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#equalityexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityexpression(CPPParser.EqualityexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#andexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndexpression(CPPParser.AndexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#exclusiveorexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveorexpression(CPPParser.ExclusiveorexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#inclusiveorexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveorexpression(CPPParser.InclusiveorexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#logicalandexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalandexpression(CPPParser.LogicalandexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#logicalorexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalorexpression(CPPParser.LogicalorexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#conditionalexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalexpression(CPPParser.ConditionalexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#assignmentexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentexpression(CPPParser.AssignmentexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#assignmentoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentoperator(CPPParser.AssignmentoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CPPParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#constantexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantexpression(CPPParser.ConstantexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CPPParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#labeledstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledstatement(CPPParser.LabeledstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#expressionstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionstatement(CPPParser.ExpressionstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#compoundstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundstatement(CPPParser.CompoundstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#statementseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementseq(CPPParser.StatementseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#selectionstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionstatement(CPPParser.SelectionstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(CPPParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#iterationstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationstatement(CPPParser.IterationstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#forinitstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForinitstatement(CPPParser.ForinitstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#forrangedeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForrangedeclaration(CPPParser.ForrangedeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#forrangeinitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForrangeinitializer(CPPParser.ForrangeinitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#jumpstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpstatement(CPPParser.JumpstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declarationstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationstatement(CPPParser.DeclarationstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declarationseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationseq(CPPParser.DeclarationseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(CPPParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#blockdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockdeclaration(CPPParser.BlockdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#aliasdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasdeclaration(CPPParser.AliasdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#simpledeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpledeclaration(CPPParser.SimpledeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#static_assertdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatic_assertdeclaration(CPPParser.Static_assertdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#emptydeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptydeclaration(CPPParser.EmptydeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributedeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributedeclaration(CPPParser.AttributedeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclspecifier(CPPParser.DeclspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declspecifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclspecifierseq(CPPParser.DeclspecifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#storageclassspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageclassspecifier(CPPParser.StorageclassspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#functionspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionspecifier(CPPParser.FunctionspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typedefname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefname(CPPParser.TypedefnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypespecifier(CPPParser.TypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#trailingtypespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailingtypespecifier(CPPParser.TrailingtypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typespecifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypespecifierseq(CPPParser.TypespecifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#trailingtypespecifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailingtypespecifierseq(CPPParser.TrailingtypespecifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#simpletypespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpletypespecifier(CPPParser.SimpletypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#thetypename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThetypename(CPPParser.ThetypenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#decltypespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecltypespecifier(CPPParser.DecltypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#elaboratedtypespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElaboratedtypespecifier(CPPParser.ElaboratedtypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumname(CPPParser.EnumnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumspecifier(CPPParser.EnumspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumhead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumhead(CPPParser.EnumheadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#opaqueenumdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpaqueenumdeclaration(CPPParser.OpaqueenumdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumkey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumkey(CPPParser.EnumkeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumbase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumbase(CPPParser.EnumbaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumeratorlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratorlist(CPPParser.EnumeratorlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumeratordefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratordefinition(CPPParser.EnumeratordefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#enumerator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerator(CPPParser.EnumeratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namespacename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacename(CPPParser.NamespacenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#originalnamespacename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOriginalnamespacename(CPPParser.OriginalnamespacenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namespacedefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacedefinition(CPPParser.NamespacedefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namednamespacedefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamednamespacedefinition(CPPParser.NamednamespacedefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#originalnamespacedefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOriginalnamespacedefinition(CPPParser.OriginalnamespacedefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#extensionnamespacedefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtensionnamespacedefinition(CPPParser.ExtensionnamespacedefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#unnamednamespacedefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnamednamespacedefinition(CPPParser.UnnamednamespacedefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namespacebody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacebody(CPPParser.NamespacebodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namespacealias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacealias(CPPParser.NamespacealiasContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#namespacealiasdefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacealiasdefinition(CPPParser.NamespacealiasdefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#qualifiednamespacespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiednamespacespecifier(CPPParser.QualifiednamespacespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#usingdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingdeclaration(CPPParser.UsingdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#usingdirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingdirective(CPPParser.UsingdirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#asmdefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsmdefinition(CPPParser.AsmdefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#linkagespecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkagespecification(CPPParser.LinkagespecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributespecifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributespecifierseq(CPPParser.AttributespecifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributespecifier(CPPParser.AttributespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#alignmentspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlignmentspecifier(CPPParser.AlignmentspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributelist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributelist(CPPParser.AttributelistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(CPPParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributetoken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributetoken(CPPParser.AttributetokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributescopedtoken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributescopedtoken(CPPParser.AttributescopedtokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributenamespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributenamespace(CPPParser.AttributenamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#attributeargumentclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeargumentclause(CPPParser.AttributeargumentclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#balancedtokenseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBalancedtokenseq(CPPParser.BalancedtokenseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#balancedtoken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBalancedtoken(CPPParser.BalancedtokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initdeclaratorlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitdeclaratorlist(CPPParser.InitdeclaratorlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitdeclarator(CPPParser.InitdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(CPPParser.DeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#ptrdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPtrdeclarator(CPPParser.PtrdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noptrdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoptrdeclarator(CPPParser.NoptrdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#parametersandqualifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersandqualifiers(CPPParser.ParametersandqualifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#trailingreturntype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailingreturntype(CPPParser.TrailingreturntypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#ptroperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPtroperator(CPPParser.PtroperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#cvqualifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCvqualifierseq(CPPParser.CvqualifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#cvqualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCvqualifier(CPPParser.CvqualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#refqualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefqualifier(CPPParser.RefqualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#declaratorid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaratorid(CPPParser.DeclaratoridContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#thetypeid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThetypeid(CPPParser.ThetypeidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#abstractdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractdeclarator(CPPParser.AbstractdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#ptrabstractdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPtrabstractdeclarator(CPPParser.PtrabstractdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noptrabstractdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoptrabstractdeclarator(CPPParser.NoptrabstractdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#abstractpackdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractpackdeclarator(CPPParser.AbstractpackdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noptrabstractpackdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoptrabstractpackdeclarator(CPPParser.NoptrabstractpackdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#parameterdeclarationclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterdeclarationclause(CPPParser.ParameterdeclarationclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#parameterdeclarationlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterdeclarationlist(CPPParser.ParameterdeclarationlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#parameterdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterdeclaration(CPPParser.ParameterdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#functiondefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctiondefinition(CPPParser.FunctiondefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#functionbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionbody(CPPParser.FunctionbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(CPPParser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#braceorequalinitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraceorequalinitializer(CPPParser.BraceorequalinitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initializerclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializerclause(CPPParser.InitializerclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#initializerlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializerlist(CPPParser.InitializerlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#bracedinitlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracedinitlist(CPPParser.BracedinitlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassname(CPPParser.ClassnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassspecifier(CPPParser.ClassspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classhead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasshead(CPPParser.ClassheadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classheadname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassheadname(CPPParser.ClassheadnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classvirtspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassvirtspecifier(CPPParser.ClassvirtspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classkey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasskey(CPPParser.ClasskeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#memberspecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberspecification(CPPParser.MemberspecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#memberdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberdeclaration(CPPParser.MemberdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#memberdeclaratorlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberdeclaratorlist(CPPParser.MemberdeclaratorlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#memberdeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberdeclarator(CPPParser.MemberdeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#virtspecifierseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVirtspecifierseq(CPPParser.VirtspecifierseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#virtspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVirtspecifier(CPPParser.VirtspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#purespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPurespecifier(CPPParser.PurespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#baseclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseclause(CPPParser.BaseclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#basespecifierlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasespecifierlist(CPPParser.BasespecifierlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#basespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasespecifier(CPPParser.BasespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#classordecltype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassordecltype(CPPParser.ClassordecltypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#basetypespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasetypespecifier(CPPParser.BasetypespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#accessspecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessspecifier(CPPParser.AccessspecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#conversionfunctionid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConversionfunctionid(CPPParser.ConversionfunctionidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#conversiontypeid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConversiontypeid(CPPParser.ConversiontypeidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#conversiondeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConversiondeclarator(CPPParser.ConversiondeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#ctorinitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCtorinitializer(CPPParser.CtorinitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#meminitializerlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeminitializerlist(CPPParser.MeminitializerlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#meminitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeminitializer(CPPParser.MeminitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#meminitializerid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeminitializerid(CPPParser.MeminitializeridContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#operatorfunctionid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperatorfunctionid(CPPParser.OperatorfunctionidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#literaloperatorid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteraloperatorid(CPPParser.LiteraloperatoridContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templatedeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatedeclaration(CPPParser.TemplatedeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templateparameterlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateparameterlist(CPPParser.TemplateparameterlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templateparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateparameter(CPPParser.TemplateparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typeparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeparameter(CPPParser.TypeparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#simpletemplateid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpletemplateid(CPPParser.SimpletemplateidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templateid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateid(CPPParser.TemplateidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templatename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplatename(CPPParser.TemplatenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templateargumentlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateargumentlist(CPPParser.TemplateargumentlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#templateargument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateargument(CPPParser.TemplateargumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typenamespecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypenamespecifier(CPPParser.TypenamespecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#explicitinstantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitinstantiation(CPPParser.ExplicitinstantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#explicitspecialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitspecialization(CPPParser.ExplicitspecializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#tryblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryblock(CPPParser.TryblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#functiontryblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctiontryblock(CPPParser.FunctiontryblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#handlerseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerseq(CPPParser.HandlerseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#handler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandler(CPPParser.HandlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#exceptiondeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptiondeclaration(CPPParser.ExceptiondeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#throwexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowexpression(CPPParser.ThrowexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#exceptionspecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptionspecification(CPPParser.ExceptionspecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#dynamicexceptionspecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamicexceptionspecification(CPPParser.DynamicexceptionspecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#typeidlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeidlist(CPPParser.TypeidlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#noexceptspecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoexceptspecification(CPPParser.NoexceptspecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#theoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTheoperator(CPPParser.TheoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CPPParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#booleanliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanliteral(CPPParser.BooleanliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#pointerliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerliteral(CPPParser.PointerliteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPParser#userdefinedliteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserdefinedliteral(CPPParser.UserdefinedliteralContext ctx);
}