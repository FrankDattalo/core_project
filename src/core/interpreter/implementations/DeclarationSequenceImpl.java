package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.DeclarationSequence;

public class DeclarationSequenceImpl implements DeclarationSequence {

	private Declaration dec;
	private DeclarationSequence decs;

	public DeclarationSequenceImpl(Declaration dec) {
		this.dec = dec;
	}

	public DeclarationSequenceImpl(Declaration dec, DeclarationSequence decs) {
		this.dec = dec;
		this.decs = decs;
	}

	@Override
	public void execute() {
		this.dec.execute();

		if (this.decs != null) {
			this.decs.execute();
		}
	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.dec.print(indent, printer);
		printer.println();

		if (this.decs != null) {
			this.decs.print(indent, printer);
		}
	}

}
