package core.interpreter.implementations;

import java.io.PrintStream;

import core.exceptions.CoreException;
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
		switch (this.alternative) {
		case 1:
			return IntParser.parse((String) this.altObj);
		case 2:
			Id id = (Id) this.altObj;

			if(!id.hasValue()) {
				throw new CoreException("Tried to use " + id.getIdentifier() + " before it had a value");
			}

			return id.evaluate();
		case 3:
			int i = ((Expression) this.altObj).evaluate();

			if(!IntParser.isValidInt(i)) {
				throw new CoreException("Integer overflow from mathematical expression");
			}

			return i;
		default:
			throw new RuntimeException("Invalid alternative");
		}
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
