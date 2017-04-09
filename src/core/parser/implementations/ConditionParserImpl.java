package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Composite;
import core.interpreter.interfaces.Condition;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class ConditionParserImpl implements Parser<Condition> {

	@Override
	public Condition parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Composite> compp = parserContainer.getCompositeParser();

		String currentToken = tokenizer.getCurrentToken();
		Tokenizer.Type type = tokenizer.toType(currentToken);

		switch (type) {
		case NOT:
			tokenizer.nextToken();
			return Condition.createInstance(this.parse(parserContainer, tokenizer));

		case LEFT_BRACKET:
			tokenizer.nextToken();
			Condition left = this.parse(parserContainer, tokenizer);

			String op = tokenizer.getCurrentToken();
			Condition.Type opType = null;

			switch (tokenizer.toType(op)) {
			case AND:
				opType = Condition.Type.AND;
				break;

			case OR:
				opType = Condition.Type.OR;
				break;

			default:
				throw new CoreException("Expected boolean operator at immediately before line "
						+ tokenizer.getCurrentLineNumber() + " but found '" + currentToken + "'");
			}

			tokenizer.nextToken();

			Condition right = this.parse(parserContainer, tokenizer);
			Parser.expectAndConsume(tokenizer, "]");

			return Condition.createInstance(opType, left, right);

		case LEFT_PAREN:

			return Condition.createInstance(compp.parse(parserContainer, tokenizer));

		default:
			throw new CoreException("Expected condition or compound condition at or immediately before line "
					+ tokenizer.getCurrentLineNumber() + " but found '" + currentToken + "'");
		}
	}

}
