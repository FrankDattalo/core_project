package core.parser.interfaces;

/**
 * Represents an AST node interface with the ability to assign a value.
 *
 * @param <T>
 *            The type of the value that can be assigned to this node.
 */
public interface Assignable<T> extends Evaluatable<T> {

	/**
	 * Assigns value to this node.
	 * 
	 * @param value
	 *            the value to assign.
	 */
	void assign(T value);
}
