package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Factor;
import core.interpreter.interfaces.Term;

public class TermImpl implements Term {

	private Factor f;
	private Term t;

	public TermImpl(Factor f) {
		this.f = f;
	}

	public TermImpl(Factor f, Term t) {
		this.f = f;
		this.t = t;
	}

	@Override
	public Integer evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.f.print(indent, printer);

		if (this.t != null) {
			printer.print(" * ");
			this.t.print(indent, printer);
		}

	}

}
