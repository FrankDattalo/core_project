package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Statement;
import core.parser.interfaces.Executable;
import core.parser.interfaces.Printable;

public class StatementImpl implements Statement {

	private Executable statement;

	public StatementImpl(Executable stmt) {
		this.statement = stmt;
	}

	@Override
	public void execute() {
		this.statement.execute();
	}

	@Override
	public void print(int indent, PrintStream printer) {
		Printable.printNSpaces(indent, printer);
		this.statement.print(indent, printer);
	}

}
