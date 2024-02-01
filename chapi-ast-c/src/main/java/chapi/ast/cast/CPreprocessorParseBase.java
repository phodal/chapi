package chapi.ast.cast;

import org.antlr.v4.codegen.OutputModelFactory;
import org.antlr.v4.codegen.model.Parser;
import org.antlr.v4.codegen.model.ParserFile;

public class CPreprocessorParseBase extends Parser {
    public CPreprocessorParseBase(OutputModelFactory factory, ParserFile file) {
        super(factory, file);
    }
}
