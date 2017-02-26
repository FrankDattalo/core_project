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
		return new IdImpl(value);
	}
}
