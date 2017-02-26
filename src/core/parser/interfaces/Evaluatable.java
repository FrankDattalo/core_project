package core.parser.interfaces;

/**
 * Represents an AST node with the ability to evaluate and return a value.
 *
 * @param <T>
 *            The type that this node will return after evaluation.
 */
public interface Evaluatable<T> extends Printable {

	/**
	 * Evaluates this node.
	 * 
	 * @return The result of the evaluation.
	 */
	T evaluate();
}
