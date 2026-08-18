package chapi.ast.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import java.util.Stack;

/**
 * All parser methods that used in grammar (p, prev, notLineTerminator, etc.)
 * should start with lower case char similar to parser rules.
 */
public abstract class ArkTSParserBase extends Parser
{

    boolean jsx = false;

    public boolean isJsx() {
        return jsx;
    }

    public void setJsx(boolean value) {
        jsx = value;
    }

    private Stack<String> _tagNames = new Stack<String>();

    public ArkTSParserBase(TokenStream input) {
        super(input);
    }

    /**
     * Short form for prev(String str)
     */
    protected boolean p(String str) {
        return prev(str);
    }

    /**
     * Whether the previous token value equals to @param str
     */
    protected boolean prev(String str) {
        return _input.LT(-1).getText().equals(str);
    }

    /**
     * Short form for next(String str)
     */
    protected boolean n(String str) {
        return next(str);
    }

    /**
     * Whether the next token value equals to @param str
     */
    protected boolean next(String str) {
        return _input.LT(1).getText().equals(str);
    }

    protected boolean notLineTerminator() {
        return !here(ArkTSParser.LineTerminator);
    }

    protected boolean notOpenBraceAndNotFunction() {
        int nextTokenType = _input.LT(1).getType();
        return nextTokenType != ArkTSParser.OpenBrace && nextTokenType != ArkTSParser.Function_;
    }

    protected boolean notOpenBraceAndNotFunctionAndNotInterface() {
        int nextTokenType = _input.LT(1).getType();
        return nextTokenType != ArkTSParser.OpenBrace &&
               nextTokenType != ArkTSParser.Function_ &&
               nextTokenType != ArkTSParser.Interface;
    }

    protected boolean notOpenBraceAndNotFunctionAndNotInterfaceAndNotReturn() {
        int nextTokenType = _input.LT(1).getType();
        return nextTokenType != ArkTSParser.OpenBrace &&
               nextTokenType != ArkTSParser.Function_ &&
               nextTokenType != ArkTSParser.Interface &&
               nextTokenType != ArkTSParser.Return;
    }

    protected boolean notOpenBraceAndNotStatementKeyword() {
        int t = _input.LT(1).getType();
        return t != ArkTSParser.OpenBrace &&
               t != ArkTSParser.Function_ &&
               t != ArkTSParser.Interface &&
               t != ArkTSParser.Return &&
               t != ArkTSParser.Try &&
               t != ArkTSParser.Throw &&
               t != ArkTSParser.If &&
               t != ArkTSParser.For &&
               t != ArkTSParser.While &&
               t != ArkTSParser.Do &&
               t != ArkTSParser.Switch &&
               t != ArkTSParser.Break &&
               t != ArkTSParser.Continue &&
               t != ArkTSParser.Class &&
               t != ArkTSParser.Import &&
               t != ArkTSParser.Export;
    }

    protected boolean closeBrace() {
        return _input.LT(1).getType() == ArkTSParser.CloseBrace;
    }

    /**
     * Returns {@code true} iff on the current index of the parser's
     * token stream a token of the given {@code type} exists on the
     * {@code HIDDEN} channel.
     *
     * @param type
     *         the type of the token on the {@code HIDDEN} channel
     *         to check.
     *
     * @return {@code true} iff on the current index of the parser's
     * token stream a token of the given {@code type} exists on the
     * {@code HIDDEN} channel.
     */
    private boolean here(final int type) {

        // Get the token ahead of the current index.
        int possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 1;
        Token ahead = _input.get(possibleIndexEosToken);

        // Check if the token resides on the HIDDEN channel and if it's of the
        // provided type.
        return (ahead.getChannel() == Lexer.HIDDEN) && (ahead.getType() == type);
    }

    /**
     * Returns {@code true} iff on the current index of the parser's
     * token stream a token exists on the {@code HIDDEN} channel which
     * either is a line terminator, or is a multi line comment that
     * contains a line terminator.
     *
     * @return {@code true} iff on the current index of the parser's
     * token stream a token exists on the {@code HIDDEN} channel which
     * either is a line terminator, or is a multi line comment that
     * contains a line terminator.
     */
    protected boolean lineTerminatorAhead() {

        // Get the token ahead of the current index.
        int possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 1;
        Token ahead = _input.get(possibleIndexEosToken);

        if (ahead.getChannel() != Lexer.HIDDEN) {
            // We're only interested in tokens on the HIDDEN channel.
            return false;
        }

        if (ahead.getType() == ArkTSParser.LineTerminator) {
            // There is definitely a line terminator ahead.
            return true;
        }

        if (ahead.getType() == ArkTSParser.WhiteSpaces) {
            // Get the token ahead of the current whitespaces.
            possibleIndexEosToken = this.getCurrentToken().getTokenIndex() - 2;
            ahead = _input.get(possibleIndexEosToken);
        }

        // Get the token's text and type.
        String text = ahead.getText();
        int type = ahead.getType();

        // Check if the token is, or contains a line terminator.
        return (type == ArkTSParser.MultiLineComment && (text.contains("\r") || text.contains("\n"))) ||
                (type == ArkTSParser.LineTerminator);
    }

    // 2020/10/27 for jsx
    protected void pushHtmlTagName(String tagName)
    {
        _tagNames.push(tagName);
    }

    protected boolean popHtmlTagName(String tagName)
    {
//        return String.Equals(_tagNames.pop(), tagName, StringComparison.InvariantCulture);
        return tagName.equalsIgnoreCase(_tagNames.pop());
    }
}
