package core.parser.implementations;

import core.interpreter.interfaces.Factor;
import core.interpreter.interfaces.Term;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class TermParserImpl implements Parser<Term> {

	@Override
	public Term parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Factor> facp = parserContainer.getFactorParser();

		Factor f = facp.parse(parserContainer, tokenizer);

		Tokenizer.Type type = tokenizer.toType(tokenizer.getCurrentToken());

		if (type == Tokenizer.Type.TIMES) {
			tokenizer.nextToken();

			Term term = this.parse(parserContainer, tokenizer);

			return Term.createInstance(f, term);

		} else {
			return Term.createInstance(f);
		}
	}

}
