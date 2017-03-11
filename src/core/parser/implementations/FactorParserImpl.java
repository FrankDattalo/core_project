package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Factor;
import core.interpreter.interfaces.Id;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class FactorParserImpl implements Parser<Factor> {

	@Override
	public Factor parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Expression> exprp = parserContainer.getExpressionParser();

		String currentToken = tokenizer.getCurrentToken();
		Tokenizer.Type type = tokenizer.toType(currentToken);

		tokenizer.nextToken();

		switch (type) {
		case INTEGER:
			return Factor.createInstance(currentToken);

		case IDENTIFIER:

			Id id = Id.createInstance(currentToken);

			if (!id.isDeclared()) {
				throw new CoreException(id.getIdentifier()
						+ " must be declared and assigned a value before being used in an expression.");
			}

			return Factor.createInstance(id);

		case LEFT_PAREN:

			Expression e = exprp.parse(parserContainer, tokenizer);

			Parser.expectAndConsume(tokenizer, ")");

			return Factor.createInstance(e);

		default:
			throw new CoreException("Expect integer, id, or expression on or immediately line "
					+ tokenizer.getCurrentLineNumber() + " but was '" + currentToken + "'");
		}
	}

}
