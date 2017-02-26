package core.parser.implementations;

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

		return In.createInstance(idl);
	}

}
