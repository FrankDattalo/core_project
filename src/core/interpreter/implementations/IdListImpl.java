package core.interpreter.implementations;

import java.io.PrintStream;
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
		// TODO Auto-generated method stub
		return null;
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
