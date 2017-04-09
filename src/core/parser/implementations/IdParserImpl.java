package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class IdParserImpl implements Parser<Id> {

	@Override
	public Id parse(ParserContainer parserContainer, Tokenizer tokenizer) {

		String current = tokenizer.getCurrentToken();

		if (tokenizer.toType(current) != Tokenizer.Type.IDENTIFIER) {
			throw new CoreException("Expected id at or immediately before line " + tokenizer.getCurrentLineNumber()
					+ " but found " + current);
		}

		tokenizer.nextToken();

		return Id.createInstance(current);
	}

}
