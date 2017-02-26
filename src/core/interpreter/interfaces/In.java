package core.interpreter.interfaces;

import core.interpreter.implementations.InImpl;
import core.parser.interfaces.Executable;

/**
 * In AST node interface.
 */
public interface In extends Executable {

	/**
	 * In factory method.
	 * 
	 * @param idl
	 *            the id list child instance.
	 * @return An in instance.
	 */
	static In createInstance(IdList idl) {
		return new InImpl(idl);
	}
}
