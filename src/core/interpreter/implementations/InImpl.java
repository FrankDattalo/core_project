package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.In;

public class InImpl implements In {

	private IdList idl;

	public InImpl(IdList idl) {
		this.idl = idl;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print("read ");
		this.idl.print(indent, printer);
		printer.print(";");
		printer.println();
	}

}
