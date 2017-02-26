package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.Out;

public class OutImpl implements Out {

	private IdList idl;

	public OutImpl(IdList idl) {
		this.idl = idl;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print("write ");
		this.idl.print(indent, printer);
		printer.print(";");
		printer.println();
	}

}
