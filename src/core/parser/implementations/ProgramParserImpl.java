package core.parser.implementations;

import core.exceptions.CoreException;
import core.interpreter.interfaces.DeclarationSequence;
import core.interpreter.interfaces.Program;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class ProgramParserImpl implements Parser<Program> {

	@Override
	public Program parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<DeclarationSequence> dsp = parserContainer.getDeclarationSequenceParser();
		Parser<StatementSequence> ssp = parserContainer.getStatementSequenceParser();

		Parser.expectAndConsume(tokenizer, "program");

		DeclarationSequence ds = dsp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, "begin");

		StatementSequence ss = ssp.parse(parserContainer, tokenizer);

		Parser.expectAndConsume(tokenizer, "end");

		if (tokenizer.getCurrentToken() != Tokenizer.EOF) {
			throw new CoreException("Invalid symbol after end symbol at end of file on line "
					+ tokenizer.getCurrentLineNumber() + ": " + tokenizer.getCurrentToken());
		}

		return Program.createInstance(ds, ss);
	}

}
