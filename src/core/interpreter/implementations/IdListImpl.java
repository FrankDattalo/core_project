package core.interpreter.implementations;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;

public class IdListImpl implements IdList {

	private Id id;
	private IdList idl;

	public IdListImpl(Id id) {
		this.id = id;
	}

	public IdListImpl(Id id, IdList idl) {
		this.id = id;
		this.idl = idl;
	}

	@Override
	public List<Id> evaluate() {
		List<Id> l = new LinkedList<>();
		l.add(this.id);

		if (this.idl != null) {
			l.addAll(this.idl.evaluate());
		}

		return l;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.id.print(indent, printer);

		if (this.idl != null) {
			printer.print(", ");

			this.idl.print(indent, printer);
		}
	}

}
