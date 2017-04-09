package core.interpreter.interfaces;

import core.interpreter.implementations.ConditionImpl;
import core.parser.interfaces.Evaluatable;

/**
 * Condition AST node.
 */
public interface Condition extends Evaluatable<Boolean> {

	/**
	 * Condition node type enum.
	 */
	public static enum Type {
		REGULAR, NEGATED, AND, OR
	}

	/**
	 * Condition node factory method.
	 *
	 * @param comp
	 *            the composite node child instance.
	 * @return A condition node.
	 */
	static Condition createInstance(Composite comp) {
		return new ConditionImpl(comp);
	}

	/**
	 * Condition node factory method.
	 * 
	 * @param cond
	 *            the condition node child instance.
	 * @return A condition node.
	 */
	static Condition createInstance(Condition cond) {
		return new ConditionImpl(cond);
	}

	/**
	 * Condition node factory method.
	 * 
	 * @param type
	 *            The type of this condition.
	 * @param cond1
	 *            the condition 1 node child instance.
	 * @param cond2
	 *            the condition 2 node child instance.
	 * @return A condition node.
	 */
	static Condition createInstance(Type type, Condition cond1, Condition cond2) {
		return new ConditionImpl(type, cond1, cond2);
	}
}
