package core.tokenizer.interfaces;

import core.tokenizer.implementations.TokenizerImpl;

import java.io.File;

/**
 * Public interface for the Core Tokenizer.
 *
 * @author Frank Dattalo
 */
public interface Tokenizer {

	enum Type {
		PROGRAM, BEGIN, END, INT, IF, THEN, ELSE, WHILE, LOOP, READ, WRITE, AND, OR, SEMICOLON, ASSIGN, NOT,
        LEFT_BRACKET, RIGHT_BRACKET, LEFT_PAREN, RIGHT_PAREN, PLUS, MINUS, TIMES, NOT_EQUAL, EQUAL,
        GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL, GREATER_THAN, LESS_THAN, INTEGER, IDENTIFIER
	}

	/**
	 * Factory method for instantiating tokenizers.
	 *
	 * @param in
	 *            the core input file.
	 * @return A core tokenizer instance.
	 */
	static Tokenizer createInstance(File in) {
		return new TokenizerImpl(in);
	}

	/**
	 * The Token that signifies the end of the file.
	 */
	static final String EOF = "###END_OF_FILE_TOKEN###";

	/**
	 * Returns the current token from the input program.
	 */
	String getCurrentToken();

	/**
	 * Advances the cursor to the next token from the input program.
	 */
	void nextToken();

	/**
	 * Returns the current line number of the tokenizer.
	 */
	int getCurrentLineNumber();

	/**
	 * @param t
	 *            - The Token to conver to a tokenizer enum
	 * @return - the Enum tokenizer type
	 */
	Tokenizer.Type toType(String t);
}
