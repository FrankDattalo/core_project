package core.interpreter.implementations;

import java.io.PrintStream;

import core.exceptions.CoreException;
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
		if (this.type == null) {
			return this.term.evaluate();
		}

		int i;

		switch (this.type) {
		case ADD:
			i = this.term.evaluate() + this.expr.evaluate();
		    break;
        case SUB:
			i = this.term.evaluate() - this.expr.evaluate();
			break;
		default:
			throw new RuntimeException("Invalid type for expression");
		}

		if(!IntParser.isValidInt(i)) {
			throw new CoreException("Integer overflow from mathematical expression");
		}

		return i;
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
