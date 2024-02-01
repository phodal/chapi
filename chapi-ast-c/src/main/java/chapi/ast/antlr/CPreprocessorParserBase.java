package chapi.ast.antlr;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

import java.util.HashSet;
import java.util.Stack;

abstract class CPreprocessorParserBase extends Parser {
    protected CPreprocessorParserBase(TokenStream input) {
        super(input);
        conditions.push(true);
        ConditionalSymbols.add("DEBUG");
    }

    Stack<Boolean> conditions = new Stack<Boolean>();
    public HashSet<String> ConditionalSymbols = new HashSet<String>();
    public HashSet<String> IncludeSymbols = new HashSet<String>();

    protected Boolean AllConditions() {
        for (Boolean condition : conditions) {
            if (!condition)
                return false;
        }
        return true;
    }

    protected void OnPreprocessorDirectiveInclude() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorIncludeDeclarationContext d = (CPreprocessorParser.PreprocessorIncludeDeclarationContext) c;
        IncludeSymbols.add(d.IncludeText().getText());
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveDefine() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDeclarationContext d = (CPreprocessorParser.PreprocessorDeclarationContext) c;
        ConditionalSymbols.add(d.CONDITIONAL_SYMBOL().getText());
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveUndef() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDeclarationContext d = (CPreprocessorParser.PreprocessorDeclarationContext) c;
        ConditionalSymbols.remove(d.CONDITIONAL_SYMBOL().getText());
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveIf() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorConditionalContext d = (CPreprocessorParser.PreprocessorConditionalContext) c;
        if (d.expr.value != null) {
            d.value = d.expr.value.equals("true") && AllConditions();
            conditions.push(d.expr.value.equals("true"));
        }
    }

    protected void OnPreprocessorDirectiveIfdef() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDeclarationContext d = (CPreprocessorParser.PreprocessorDeclarationContext) c;
        ConditionalSymbols.add(d.CONDITIONAL_SYMBOL().getText());
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveIfndef() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDeclarationContext d = (CPreprocessorParser.PreprocessorDeclarationContext) c;
        ConditionalSymbols.add(d.CONDITIONAL_SYMBOL().getText());
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveElif() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorConditionalContext d = (CPreprocessorParser.PreprocessorConditionalContext) c;
        if (!conditions.peek()) {
            conditions.pop();
            d.value = d.expr.value.equals("true") && AllConditions();
            conditions.push(d.expr.value.equals("true"));
        } else {
            d.value = false;
        }
    }

    protected void OnPreprocessorDirectiveElse() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorConditionalContext d = (CPreprocessorParser.PreprocessorConditionalContext) c;
        if (!conditions.isEmpty()) {
            if (!conditions.peek()) {
                conditions.pop();
                d.value = true && AllConditions();
                conditions.push(true);
            } else {
                d.value = false;
            }
        } else {
            d.value = false;
        }
    }

    protected void OnPreprocessorDirectiveEndif() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorConditionalContext d = (CPreprocessorParser.PreprocessorConditionalContext) c;
        if (!conditions.isEmpty()) {
            conditions.pop();
            if (!conditions.isEmpty()) {
                d.value = conditions.peek();
            }
        }
    }

    protected void OnPreprocessorDirectiveError() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDiagnosticContext d = (CPreprocessorParser.PreprocessorDiagnosticContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveWarning() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorDiagnosticContext d = (CPreprocessorParser.PreprocessorDiagnosticContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveRegion() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorRegionContext d = (CPreprocessorParser.PreprocessorRegionContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveEndregion() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorRegionContext d = (CPreprocessorParser.PreprocessorRegionContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectivePragma() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorPragmaContext d = (CPreprocessorParser.PreprocessorPragmaContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorDirectiveNullable() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.PreprocessorNullableContext d = (CPreprocessorParser.PreprocessorNullableContext) c;
        d.value = AllConditions();
    }

    protected void OnPreprocessorExpressionTrue() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = "true";
    }

    protected void OnPreprocessorExpressionFalse() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = "false";
    }

    protected void OnPreprocessorExpressionConditionalSymbol() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = ConditionalSymbols.contains(d.CONDITIONAL_SYMBOL().getText()) ? "true" : "false";
    }

    protected void OnPreprocessorExpressionConditionalOpenParens() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = d.expr.value;
    }

    protected void OnPreprocessorExpressionConditionalBang() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = d.expr.value.equals("true") ? "false" : "true";
    }

    protected void OnPreprocessorExpressionConditionalEq() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = (d.expr1.value == d.expr2.value ? "true" : "false");
    }

    protected void OnPreprocessorExpressionConditionalNe() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = (d.expr1.value != d.expr2.value ? "true" : "false");
    }

    protected void OnPreprocessorExpressionConditionalAnd() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = (d.expr1.value.equals("true") && d.expr2.value.equals("true") ? "true" : "false");
    }

    protected void OnPreprocessorExpressionConditionalOr() {
        ParserRuleContext c = this._ctx;
        CPreprocessorParser.Preprocessor_expressionContext d = (CPreprocessorParser.Preprocessor_expressionContext) c;
        d.value = (d.expr1.value.equals("true") || d.expr2.value.equals("true") ? "true" : "false");
    }
}
