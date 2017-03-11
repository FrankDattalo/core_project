package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.In;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class InParserImpl implements Parser<In> {

	@Override
	public In parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<IdList> idlp = parserContainer.getIdListParser();

		Parser.expectAndConsume(tokenizer, "read");
		IdList idl = idlp.parse(parserContainer, tokenizer);
		Parser.expectAndConsume(tokenizer, ";");

		for (Id id : idl.evaluate()) {
			if (!id.isDeclared()) {
				throw new CoreException("Cannot read " + id.getIdentifier() + " because it is not defined");
			}
		}

		return In.createInstance(idl);
	}

}
