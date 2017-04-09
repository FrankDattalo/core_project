package core.parser.implementations;

import java.util.List;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.Id;
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

		List<Id> ids = idl.evaluate();

		for (Id id : ids) {
			if (id.isDeclared()) {
				throw new CoreException("Duplicate declaration of " + id.getIdentifier());
			}

			id.setDeclared();
		}

		Parser.expectAndConsume(tokenizer, ";");

		return Declaration.createInstance(idl);
	}

}
