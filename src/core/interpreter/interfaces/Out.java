package core.interpreter.interfaces;

import core.interpreter.implementations.OutImpl;
import core.parser.interfaces.Executable;

/**
 * Out AST node interface.
 */
public interface Out extends Executable {

	/**
	 * Out factory method.
	 * 
	 * @param idl
	 *            the id list child instance.
	 * @return An out node instance.
	 */
	static Out createInstance(IdList idl) {
		return new OutImpl(idl);
	}
}
