package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.Out;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class OutParserImpl implements Parser<Out> {

	@Override
	public Out parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<IdList> idlp = parserContainer.getIdListParser();

		Parser.expectAndConsume(tokenizer, "write");
		IdList idl = idlp.parse(parserContainer, tokenizer);
		Parser.expectAndConsume(tokenizer, ";");

		for (Id id : idl.evaluate()) {
			if (!id.isDeclared()) {
				throw new CoreException("Cannot reference undeclared variable " + id.getIdentifier());
			}
		}

		return Out.createInstance(idl);
	}

}
