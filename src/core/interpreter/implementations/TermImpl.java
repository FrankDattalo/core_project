package core.interpreter.implementations;

import java.io.PrintStream;

import core.exceptions.CoreException;
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
		int ret = this.f.evaluate();

		if (this.t != null) {
			ret = ret * this.t.evaluate();

			if(!IntParser.isValidInt(ret)) {
				throw new CoreException("Integer overflow from mathematical expression");
			}
		}

		return ret;
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
