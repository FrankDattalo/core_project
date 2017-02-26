package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Term;

public class ExpressionImpl implements Expression {

	private Term term;
	private Type type;
	private Expression expr;

	public ExpressionImpl(Term t) {
		this.term = t;
	}

	public ExpressionImpl(Term t, Type type, Expression expr) {
		this.term = t;
		this.type = type;
		this.expr = expr;
	}

	@Override
	public Integer evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.term.print(indent, printer);

		if (this.type != null) {
			switch (this.type) {
			case ADD:
				printer.print(" + ");
				break;
			case SUB:
				printer.print(" - ");
				break;
			default:
				throw new RuntimeException("Invalid type for expression");
			}

			this.expr.print(indent, printer);
		}
	}

}
