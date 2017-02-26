package core.parser.implementations;

import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class IdListParserImpl implements Parser<IdList> {

	@Override
	public IdList parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Id> idp = parserContainer.getIdParser();

		Id id = idp.parse(parserContainer, tokenizer);

		if (tokenizer.getCurrentToken().equals(",")) {
			tokenizer.nextToken();

			IdList idl = this.parse(parserContainer, tokenizer);

			return IdList.createInstance(id, idl);

		} else {
			return IdList.createInstance(id);
		}
	}
}
