package core.interpreter.interfaces;

import core.interpreter.implementations.DeclarationImpl;
import core.parser.interfaces.Executable;

/**
 * Declaration AST node interface.
 */
public interface Declaration extends Executable {

	/**
	 * Declaration node factory method.
	 *
	 * @param idl
	 *            the id list node child instance.
	 * @return A declaration node.
	 */
	static Declaration createInstance(IdList idl) {
		return new DeclarationImpl(idl);
	}
}
