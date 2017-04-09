package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Composite;
import core.interpreter.interfaces.Factor;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class CompositeParserImpl implements Parser<Composite> {

	@Override
	public Composite parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Factor> fp = parserContainer.getFactorParser();

		Parser.expectAndConsume(tokenizer, "(");
		Factor f1 = fp.parse(parserContainer, tokenizer);

		String currentToken = tokenizer.getCurrentToken();
		Tokenizer.Type type = tokenizer.toType(currentToken);

		Composite.Type cType = null;

		switch (type) {
		case NOT_EQUAL:
			cType = Composite.Type.NOT_EQUAL;
			break;
		case EQUAL:
			cType = Composite.Type.EQUAL;
			break;
		case LESS_THAN:
			cType = Composite.Type.LESS;
			break;
		case GREATER_THAN:
			cType = Composite.Type.GREATER;
			break;
		case GREATER_THAN_OR_EQUAL:
			cType = Composite.Type.GREATER_OR_EQUAL;
			break;
		case LESS_THAN_OR_EQUAL:
			cType = Composite.Type.LESS_OR_EQUAL;
			break;
		default:
			throw new CoreException("Error on or immediately before line " + tokenizer.getCurrentLineNumber()
					+ " expected compound boolean operator but found '" + currentToken + "'");
		}

		tokenizer.nextToken();

		Factor f2 = fp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, ")");

		return Composite.createInstance(f1, cType, f2);
	}

}
