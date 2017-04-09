package core.interpreter.implementations;

import java.io.PrintStream;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.Out;

public class OutImpl implements Out {

	private IdList idl;

	public OutImpl(IdList idl) {
		this.idl = idl;
	}

	@Override
	public void execute() {
		for (Id id : this.idl.evaluate()) {
			if (!id.hasValue()) {
				throw new CoreException("Cannot write " + id.getIdentifier() + " before it has a value");
			}

			System.out.println(id.getIdentifier() + " = " + id.evaluate());
		}
	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print("write ");
		this.idl.print(indent, printer);
		printer.print(";");
		printer.println();
	}

}
