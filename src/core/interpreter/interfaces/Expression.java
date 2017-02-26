package core.interpreter.interfaces;

import core.interpreter.implementations.ExpressionImpl;
import core.parser.interfaces.Evaluatable;

/**
 * Expression AST node interface.
 */
public interface Expression extends Evaluatable<Integer> {

	/**
	 * Expression Type enum
	 */
	public static enum Type {
		ADD, SUB
	}

	/**
	 * Expression node factory method.
	 * 
	 * @param t
	 *            the term node child instance.
	 * @return An expression node.
	 */
	static Expression createInstance(Term t) {
		return new ExpressionImpl(t);
	}

	/**
	 * Expression node factory method.
	 * 
	 * @param t
	 *            The term node child instance.
	 * @param type
	 *            The expression type.
	 * @param expr
	 *            The expression node child instance.
	 * @return An expression node.
	 */
	static Expression createInstance(Term t, Type type, Expression expr) {
		return new ExpressionImpl(t, type, expr);
	}
}
