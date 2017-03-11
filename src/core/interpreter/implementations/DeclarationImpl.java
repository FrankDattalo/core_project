package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.IdList;
import core.parser.interfaces.Printable;

public class DeclarationImpl implements Declaration {

	private IdList idl;

	public DeclarationImpl(IdList idl) {
		this.idl = idl;
	}

	@Override
	public void execute() {
	}

	@Override
	public void print(int indent, PrintStream printer) {
		Printable.printNSpaces(indent, printer);
		printer.print("int ");
		this.idl.print(indent, printer);
		printer.print(";");
	}

}
