package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Id;

public class IdImpl implements Id {

	private String value;

	public IdImpl(String value) {
		this.value = value;
	}

	@Override
	public void assign(Integer value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print(this.value);
	}
}
