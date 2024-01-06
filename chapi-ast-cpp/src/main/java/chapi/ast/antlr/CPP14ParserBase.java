package chapi.ast.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class CPP14ParserBase extends Parser {
    protected CPP14ParserBase(TokenStream input) {
        super(input);
    }

    protected boolean IsPureSpecifierAllowed() {
        try {
            ParserRuleContext x = this._ctx;// memberDeclarator
            ParseTree c = x.getChild(0).getChild(0);
            ParseTree c2 = c.getChild(0);
            ParseTree p = c2.getChild(1);
            if (p == null) return false;
            return (p instanceof CPP14Parser.ParametersAndQualifiersContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
