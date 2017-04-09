package core.interpreter.interfaces;

import core.interpreter.implementations.IdImpl;
import core.parser.interfaces.Assignable;

/**
 * Id AST node interface.
 */
public interface Id extends Assignable<Integer> {

	/**
	 * Id node factory method.
	 *
	 * @param value
	 *            the value of the id.
	 * @return An id node.
	 */
	static Id createInstance(String value) {
		return IdImpl.instance(value);
	}

	/**
	 * Returns the identifier associated with this id.
	 */
	String getIdentifier();

	/**
	 * Reports whether this Id has a value.
	 */
	boolean hasValue();

	/**
	 * Reports whether this object is declared.
	 */
	boolean isDeclared();

	/**
	 * Declares this object.
	 */
	void setDeclared();
}
