package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Statement;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class StatementParserImpl implements Parser<Statement> {

	@Override
	public Statement parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		String currentToken = tokenizer.getCurrentToken();
		Tokenizer.Type type = tokenizer.toType(currentToken);

		switch (type) {
		case IDENTIFIER:
			return Statement.createInstance(parserContainer.getAssignParser().parse(parserContainer, tokenizer));
		case IF:
			return Statement.createInstance(parserContainer.getIfParser().parse(parserContainer, tokenizer));
		case WHILE:
			return Statement.createInstance(parserContainer.getLoopParser().parse(parserContainer, tokenizer));
		case READ:
			return Statement.createInstance(parserContainer.getInParser().parse(parserContainer, tokenizer));
		case WRITE:
			return Statement.createInstance(parserContainer.getOutParser().parse(parserContainer, tokenizer));
		default:
			throw new CoreException("Expected id, if, while, read, or write statement on or immediately before line "
					+ tokenizer.getCurrentLineNumber() + " but found: " + currentToken);
		}
	}

}
