package core.parser.interfaces;

import java.io.PrintStream;

/**
 * Represents an AST node interface that defines the ability to print itself.
 */
public interface Printable {

	/**
	 * Prints this AST node to the PrintStream at a specified indent.
	 *
	 * @param indent
	 *            the indent to print at.
	 * @param printer
	 *            the printer to print to.
	 */
	void print(int indent, PrintStream printer);

	/**
	 * Utility method to print amount spaces to a given print stream.
	 *
	 * @param amount
	 *            the amount of spaces to print.
	 * @param p
	 *            the print stream to print to.
	 */
	static void printNSpaces(int amount, PrintStream p) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < amount; i++) {
			sb.append(' ');
		}

		p.print(sb.toString());
	}

	/**
	 * Utility method that returns the next indent given a previous indent.
	 * 
	 * @param i
	 *            the previous indent.
	 * @return the next indent.
	 */
	static int nextIndent(int i) {
		return i + 2;
	}
}
