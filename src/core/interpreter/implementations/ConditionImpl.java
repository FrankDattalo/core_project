package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Composite;
import core.interpreter.interfaces.Condition;
import core.parser.interfaces.Evaluatable;

public class ConditionImpl implements Condition {

	private Type type;
	private Evaluatable<Boolean> obj1;
	private Evaluatable<Boolean> obj2;

	public ConditionImpl(Composite comp) {
		this.type = Condition.Type.REGULAR;
		this.obj1 = comp;
	}

	public ConditionImpl(Condition cond) {
		this.type = Condition.Type.NEGATED;
		this.obj1 = cond;
	}

	public ConditionImpl(Type type, Condition cond1, Condition cond2) {
		this.type = type;
		this.obj1 = cond1;
		this.obj2 = cond2;
	}

	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		switch (this.type) {
		case REGULAR:
			((Composite) this.obj1).print(indent, printer);
			break;
		case NEGATED:
			printer.print("!");
			((Condition) this.obj1).print(indent, printer);
			break;
		case AND:
		case OR:
			printer.print("[");
			((Condition) this.obj1).print(indent, printer);
			printer.print(this.type == Condition.Type.AND ? " and " : " or ");
			((Condition) this.obj2).print(indent, printer);
			printer.print("]");
			break;
		default:
			throw new RuntimeException("Invalid type");
		}
	}

}
