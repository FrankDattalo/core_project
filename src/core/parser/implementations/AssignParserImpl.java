package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Assign;
import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Id;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class AssignParserImpl implements Parser<Assign> {

	@Override
	public Assign parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Id> idp = parserContainer.getIdParser();
		Parser<Expression> exp = parserContainer.getExpressionParser();

		Id id = idp.parse(parserContainer, tokenizer);

		if (!id.isDeclared()) {
			throw new CoreException(id.getIdentifier() + " must be declared before use");
		}

		Parser.expectAndConsume(tokenizer, "=");

		Expression expr = exp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, ";");

		return Assign.createInstance(id, expr);
	}

}
