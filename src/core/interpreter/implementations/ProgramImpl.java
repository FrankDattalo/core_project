package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.DeclarationSequence;
import core.interpreter.interfaces.Program;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Printable;

public class ProgramImpl implements Program {

	private DeclarationSequence ds;
	private StatementSequence ss;

	public ProgramImpl(DeclarationSequence ds, StatementSequence ss) {
		this.ds = ds;
		this.ss = ss;
	}

	@Override
	public void execute() {
		this.ds.execute();
		this.ss.execute();
	}

	@Override
	public void print(int indent, PrintStream printer) {
		Printable.printNSpaces(indent, printer);
		printer.println("program");

		int next = Printable.nextIndent(indent);

		this.ds.print(next, printer);

		Printable.printNSpaces(next, printer);
		printer.println("begin");

		this.ss.print(Printable.nextIndent(next), printer);

		Printable.printNSpaces(next, printer);
		printer.println("end");
	}

}
