package core.parser.interfaces;

import core.exceptions.CoreException;
import core.tokenizer.interfaces.Tokenizer;

/**
 * The parser interface defines an object or functional interface which recieves
 * a parser container and tokenizer and returns a AST node of type T.
 *
 * @param <T>
 *            The that this parser will return from parse().
 */
public interface Parser<T extends Printable> {

	/**
	 * Utility method which throws an exception unless the type of the current
	 * token is of Tokenizer.Type type.
	 *
	 * @param t
	 *            the tokenizer.
	 * @param type
	 *            the type to expect of the current token.
	 */
	static void expect(Tokenizer t, Tokenizer.Type type) {
		String actual = t.getCurrentToken();

		if (t.toType(actual) != type) {
			throw new CoreException("Error on or immediately before line number " + t.getCurrentLineNumber()
					+ " expected '" + type + "' but was '" + actual + '\'');
		}
	}

	/**
	 * Utility method which throws an exception unless the value of the current
	 * token equals() expect.
	 *
	 * @param t
	 *            The tokenizer.
	 * @param expect
	 *            The expected token.
	 */
	static void expect(Tokenizer t, String expect) {
		String actual = t.getCurrentToken();

		if (!expect.equals(actual)) {
			throw new CoreException("Error on or immediately before line number " + t.getCurrentLineNumber()
					+ " expected '" + expect + "' but was '" + actual + '\'');
		}
	}

	/**
	 * Utility method which will expect the current token from tokenizer t to be
	 * expect and then consume that token.
	 *
	 * @param t
	 *            The tokenizer.
	 * @param expect
	 *            The expected token.
	 */
	static void expectAndConsume(Tokenizer t, String expect) {
		expect(t, expect);
		t.nextToken();
	}

	/**
	 * Given a parserContainer and tokenizer, this method will parse the AST
	 * node T or throw an exception if it fails to parse.
	 *
	 * @param parserContainer
	 *            the parser container.
	 * @param tokenizer
	 *            the tokenizer.
	 * @return An AST node T.
	 */
	T parse(ParserContainer parserContainer, Tokenizer tokenizer);
}
