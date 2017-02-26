package core.parser.implementations;

import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.IdList;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class DeclarationParserImpl implements Parser<Declaration> {

	@Override
	public Declaration parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<IdList> idlp = parserContainer.getIdListParser();

		Parser.expectAndConsume(tokenizer, "int");

		IdList idl = idlp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, ";");

		return Declaration.createInstance(idl);
	}

}
