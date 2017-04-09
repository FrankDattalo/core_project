package core.interpreter.implementations;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Id;

public class IdImpl implements Id {

	private static final Map<String, Id> idMap = new HashMap<>();

	public static Id instance(String idValue) {
		if (idMap.containsKey(idValue)) {
			return idMap.get(idValue);

		} else {
			Id id = new IdImpl(idValue);

			idMap.put(idValue, id);

			return id;
		}
	}

	private String identifier = null;
	private boolean declared = false;
	private Integer value = null;

	private IdImpl(String value) {
		this.identifier = value;
	}

	@Override
	public void assign(Integer value) {
		this.value = value;
	}

	@Override
	public Integer evaluate() {
		if(this.value == null) { throw new CoreException("Tried to use " + this.getIdentifier() + " without a value"); }

		return this.value;
	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print(this.identifier);
	}

	@Override
	public boolean hasValue() {
		return this.value != null;
	}

	@Override
	public boolean isDeclared() {
		return this.declared;
	}

	@Override
	public void setDeclared() {
		this.declared = true;
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}
}
