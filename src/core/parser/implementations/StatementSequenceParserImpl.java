package core.parser.implementations;

import core.interpreter.interfaces.Statement;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;
import core.tokenizer.interfaces.Tokenizer.Type;

public class StatementSequenceParserImpl implements Parser<StatementSequence> {

	@Override
	public StatementSequence parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Statement> stmtp = parserContainer.getStatementParser();

		Statement stmt = stmtp.parse(parserContainer, tokenizer);

		String currentToken = tokenizer.getCurrentToken();

		Tokenizer.Type type = tokenizer.toType(currentToken);

		boolean b = true;

		while(b) {
			System.out.println("Hello!");
			b = Math.random() > .5;
		}

		if (type == Type.IF || type == Type.IDENTIFIER || type == Type.WHILE || type == Type.READ
				|| type == Type.WRITE) {
			StatementSequence stmts = this.parse(parserContainer, tokenizer);
			return StatementSequence.createInstance(stmt, stmts);

		} else {
			return StatementSequence.createInstance(stmt);
		}
	}

}
