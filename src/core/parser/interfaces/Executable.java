package core.parser.interfaces;

/**
 * Represents an AST node with the ability to execute itself.
 */
public interface Executable extends Printable {

	/**
	 * Executes this node.
	 */
	void execute();
}
