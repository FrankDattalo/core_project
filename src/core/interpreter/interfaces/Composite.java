package core.interpreter.interfaces;

import core.interpreter.implementations.CompositeImpl;
import core.parser.interfaces.Evaluatable;

/**
 * Composite AST node.
 */
public interface Composite extends Evaluatable<Boolean> {

	/**
	 * Composite node Type enum.
	 */
	public static enum Type {
		NOT_EQUAL, EQUAL, GREATER, LESS, GREATER_OR_EQUAL, LESS_OR_EQUAL
	}

	/**
	 * Factory method to create composite node instances.
	 *
	 * @param f1
	 *            factor 1 child.
	 * @param t
	 *            composite type child.
	 * @param f2
	 *            factor 2 child.
	 * @return A composite node instance.
	 */
	static Composite createInstance(Factor f1, Type t, Factor f2) {
		return new CompositeImpl(f1, t, f2);
	}
}
