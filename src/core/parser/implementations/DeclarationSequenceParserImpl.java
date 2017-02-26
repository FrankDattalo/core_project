package core.parser.implementations;

import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.DeclarationSequence;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class DeclarationSequenceParserImpl implements Parser<DeclarationSequence> {

	@Override
	public DeclarationSequence parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Declaration> decp = parserContainer.getDeclarationParser();

		Declaration d = decp.parse(parserContainer, tokenizer);

		if (tokenizer.getCurrentToken().equals("int")) {
			DeclarationSequence ds = this.parse(parserContainer, tokenizer);
			return DeclarationSequence.createInstance(d, ds);

		} else {
			return DeclarationSequence.createInstance(d);
		}
	}

}
