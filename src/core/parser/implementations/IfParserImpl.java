package core.parser.implementations;

import core.interpreter.interfaces.Condition;
import core.interpreter.interfaces.If;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class IfParserImpl implements Parser<If> {

	@Override
	public If parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<StatementSequence> stmtsp = parserContainer.getStatementSequenceParser();
		Parser<Condition> condp = parserContainer.getConditonParser();

		Parser.expectAndConsume(tokenizer, "if");
		Condition cond = condp.parse(parserContainer, tokenizer);
		Parser.expectAndConsume(tokenizer, "then");
		StatementSequence stmts = stmtsp.parse(parserContainer, tokenizer);

		If ret = null;

		if (tokenizer.getCurrentToken().equals("else")) {
			tokenizer.nextToken();
			StatementSequence elseSmts = stmtsp.parse(parserContainer, tokenizer);
			ret = If.createInstance(cond, stmts, elseSmts);
		} else {
			ret = If.createInstance(cond, stmts);
		}

		Parser.expectAndConsume(tokenizer, "end");
		Parser.expectAndConsume(tokenizer, ";");

		return ret;
	}

}
