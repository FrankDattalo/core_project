package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Assign;
import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Id;

public class AssignImpl implements Assign {

	private Id id;
	private Expression expr;

	public AssignImpl(Id id, Expression expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.id.print(indent, printer);

		printer.print(" = ");

		this.expr.print(indent, printer);
		printer.print(";");
		printer.println();

	}

}
