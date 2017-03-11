package core.interpreter.implementations;

import java.io.PrintStream;
import java.util.Scanner;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.In;
import core.tokenizer.interfaces.Tokenizer;

public class InImpl implements In {

	private IdList idl;

	private Scanner in = new Scanner(System.in);

	public InImpl(IdList idl) {
		this.idl = idl;
	}

	@Override
	public void execute() {
		for (Id id : this.idl.evaluate()) {
			System.out.print(id.getIdentifier() + " =? ");
			String input = this.in.nextLine();

			if (IntParser.isValidInt(input)) {
				id.assign(IntParser.parse(input));

			} else {
				throw new CoreException("Error input '" + input + "' failed to be parsed as an integer");
			}
		}
	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print("read ");
		this.idl.print(indent, printer);
		printer.print(";");
		printer.println();
	}

}
