package core.interpreter.interfaces;

import core.interpreter.implementations.AssignImpl;
import core.parser.interfaces.Executable;

/**
 * Assign AST node.
 */
public interface Assign extends Executable {

	/**
	 * Factory method to create Assign node instances.
	 * 
	 * @param id
	 *            The id child node.
	 * @param expr
	 *            The expression child node.
	 * @return The assign node instance.
	 */
	static Assign createInstance(Id id, Expression expr) {
		return new AssignImpl(id, expr);
	}
}
