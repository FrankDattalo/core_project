package core.interpreter.interfaces;

import core.interpreter.implementations.FactorImpl;
import core.parser.interfaces.Evaluatable;

/**
 * Factor AST node.
 */
public interface Factor extends Evaluatable<Integer> {

	/**
	 * Factor node factory method.
	 * 
	 * @param integerString
	 *            The integer literal child node.
	 * @return A factor node.
	 */
	static Factor createInstance(String integerString) {
		return new FactorImpl(integerString);
	}

	/**
	 * Factor node factory method.
	 * 
	 * @param id
	 *            the id node child instance.
	 * @return A factor node.
	 */
	static Factor createInstance(Id id) {
		return new FactorImpl(id);
	}

	/**
	 * Factor node factory method.
	 * 
	 * @param expr
	 *            the expression node child instance.
	 * @return A factor node.
	 */
	static Factor createInstance(Expression expr) {
		return new FactorImpl(expr);
	}
}
