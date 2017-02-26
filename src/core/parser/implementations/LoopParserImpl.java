package core.parser.implementations;

import core.interpreter.interfaces.Condition;
import core.interpreter.interfaces.Loop;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class LoopParserImpl implements Parser<Loop> {

	@Override
	public Loop parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Condition> condp = parserContainer.getConditonParser();
		Parser<StatementSequence> stmtsp = parserContainer.getStatementSequenceParser();

		Parser.expectAndConsume(tokenizer, "while");
		Condition cond = condp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, "loop");

		StatementSequence stmts = stmtsp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, "end");
		Parser.expectAndConsume(tokenizer, ";");

		return Loop.createInstance(cond, stmts);
	}

}
