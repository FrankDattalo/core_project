package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Factor;
import core.interpreter.interfaces.Id;

public class FactorImpl implements Factor {

	private int alternative;
	private Object altObj;

	public FactorImpl(String integerString) {
		this.altObj = integerString;
		this.alternative = 1;
	}

	public FactorImpl(Id id) {
		this.altObj = id;
		this.alternative = 2;
	}

	public FactorImpl(Expression expr) {
		this.altObj = expr;
		this.alternative = 3;
	}

	@Override
	public Integer evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		switch (this.alternative) {
		case 1:
			String str = (String) this.altObj;
			printer.print(str);
			break;
		case 2:
			Id id = (Id) this.altObj;
			id.print(indent, printer);
			break;
		case 3:
			printer.print("(");
			Expression expr = (Expression) this.altObj;
			expr.print(indent, printer);
			printer.print(")");
			break;
		default:
			throw new RuntimeException("Invalid alternative");
		}
	}

}
