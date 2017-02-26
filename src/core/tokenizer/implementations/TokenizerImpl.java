package core.tokenizer.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.exceptions.CoreException;
import core.tokenizer.interfaces.Tokenizer;

/**
 * Implementation of Tokenizer which reads in one line at a time during
 * tokenization.
 *
 * @author Frank Dattalo
 */
public class TokenizerImpl extends AbstractTokenizer {

	private Scanner input;
	private String currentLine;
	private int lineNumber = 1;
	private int tokenBegin = 0;
	private int tokenEnd = 0;

	private static final String JUST_INSTANTIATED = "##JI##";

	public TokenizerImpl(File in) {
		this.currentLine = JUST_INSTANTIATED;
		try {
			this.input = new Scanner(in);
		} catch (FileNotFoundException f) {
			throw new CoreException("Could not locate file " + in.getName());
		}
	}

	/**
	 * Returns the current line of the tokenizer.
	 */
	@Override
	public int getCurrentLineNumber() {
		return this.lineNumber;
	}

	/**
	 * Returns the next token in the stream or the EOF token if no token exists.
	 */
	@Override
	public void nextToken() {
		if (this.noTokensAvailable()) {
			return;
		}

		if (this.currentLine == JUST_INSTANTIATED) {
			this.currentLine = null;
		}

		if (this.currentLine == null) {
			if (this.input.hasNextLine()) {
				this.currentLine = this.input.nextLine();
			} else {
				return;
			}
		}

		this.advanceToNextNonWhitespace();

		if (this.noTokensAvailable()) {
			return;
		}

		String currentChar = this.currentLine.substring(this.tokenBegin, this.tokenEnd);

		// if the character we are currently looking at is invalid then alert
		// user
		if (!validCharacter(currentChar)) {
			this.error("Invalid character '" + currentChar + "'");
		}

		// if the current character is uppercase then parse identifier
		if (UPPERCASE.matcher(currentChar).matches()) {
			// parse until whitespace or special
			this.parseUntilWhitespaceOrSpecial();

			// get fully parsed token
			String token = this.getCurrentToken();

			// if the token is not a valid identifier then alert user
			if (!IDENTIFIER.matcher(token).matches()) {
				this.error("Invalid identifier '" + token + "'");
			}

			// if the current character is lowercase then parse reserved word
		} else if (LOWERCASE.matcher(currentChar).matches()) {
			// parse until whitespace or special
			this.parseUntilWhitespaceOrSpecial();

			// get fully parsed token
			String token = this.getCurrentToken();

			// if the token is not a valid reserved word then alert user
			if (!RESERVED_WORD.matcher(token).matches()) {
				this.error("Invalid keyword '" + token + "'");
			}

			// if the current character is a special character then parse as
			// either single or double special
		} else if (SPECIAL.matcher(currentChar).matches()) {

			// try to parse as double special
			if (containsAtLeastNMore(this.currentLine, this.tokenEnd, 1)) {

				// check the next character is valid
				String nextChar = this.currentLine.substring(this.tokenEnd, this.tokenEnd + 1);

				if (!validCharacter(nextChar)) {
					this.error("Unexpected character '" + nextChar + "'");
				}

				// if the concatenation of our current character and next
				// character is a two letter
				// special then increase length of current token
				if (TL_SPECIAL.matcher(currentChar + nextChar).matches()) {
					this.tokenEnd++;
				}
			}

			// if the current character is a digit then parse as an integer
		} else if (DIGIT.matcher(currentChar).matches()) {
			// parse until whitespace or special
			this.parseUntilWhitespaceOrSpecial();

			// get the fully parsed token
			String token = this.getCurrentToken();

			// if the token is not a valid integer then alert user
			if (!INTEGER.matcher(token).matches()) {
				this.error("Invalid integer literal '" + token + "'");
			}

			// if we were unable to parse as integer, reserved word, identifier,
			// or special then alert
			// the user of invalid character
		} else {
			this.error("Unexpected character '" + currentChar + "'");

		}
	}

	/**
	 * Returns the current token in the stream. Repeated calls to this will
	 * return the same token.
	 */
	@Override
	public String getCurrentToken() {
		if (this.currentLine == JUST_INSTANTIATED) {
			this.nextToken();
		}

		if (this.noTokensAvailable()) {
			return EOF;
		}

		return this.currentLine.substring(this.tokenBegin, this.tokenEnd);
	}

	/**
	 * Utility method to determine if there is a parsable token in the stream
	 */
	private boolean noTokensAvailable() {
		return (!this.input.hasNextLine() && this.currentLine != null && this.tokenBegin >= this.currentLine.length())
				|| (!this.input.hasNextLine() && this.currentLine == JUST_INSTANTIATED);
	}

	/**
	 * Advances the cursor until it hits a whitespace or special character
	 */
	private void parseUntilWhitespaceOrSpecial() {

		// while the current line contains at least 1 more parseable character
		while (containsAtLeastNMore(this.currentLine, this.tokenEnd, 1)) {

			// get that character
			String nextChar = this.currentLine.substring(this.tokenEnd, this.tokenEnd + 1);

			// verify that it is valid
			if (!validCharacter(nextChar)) {
				this.error("Unexpected character '" + nextChar + "'");
			}

			// if that character is a whitespace or special break
			if (WHITESPACE.matcher(nextChar).matches() || SPECIAL.matcher(nextChar).matches()) {
				break;
			}

			// otherwise move to next character
			this.tokenEnd++;
		}
	}

	/**
	 * Utility method for creating Core Tokenizer Exceptions.
	 */
	private void error(String str) {
		throw new CoreException("An error occurred on line " + this.lineNumber + ": " + str);
	}

	/**
	 * Utility method to advance to the next non whitespace character.
	 */
	private void advanceToNextNonWhitespace() {

		// move token beginning
		this.tokenBegin = this.tokenEnd;
		this.tokenEnd++;

		// if we are at the end of the the line then go to the next line
		if (this.currentLine.length() <= this.tokenBegin) {

			this.advanceToNextNonEmptyLine();

			// if there are no tokens available then return
			if (this.noTokensAvailable()) {
				return;
			}

			// else advance to the next non whitespace token
			this.advanceToNextNonWhitespace();
		}

		// if our current character is a whitespace then advance
		while (this.tokenBegin <= this.currentLine.length() && this.tokenEnd <= this.currentLine.length()
				&& WHITESPACE.matcher(this.currentLine.substring(this.tokenBegin, this.tokenEnd)).matches()) {

			this.tokenBegin = this.tokenEnd;
			this.tokenEnd++;
		}

		// if we are at the end of the line then we did not hit a whitespace
		if (this.currentLine.length() <= this.tokenBegin) {

			// so go to the next line
			this.advanceToNextNonEmptyLine();

			// if there are no tokens available then return
			if (this.noTokensAvailable()) {
				return;
			}

			// else advance to the next non whitespace token
			this.advanceToNextNonWhitespace();
		}
	}

	/**
	 * Utility method to advance the cursor to the next non empty line
	 */
	private void advanceToNextNonEmptyLine() {
		do {
			if (!this.input.hasNextLine()) {
				return;
			}

			this.lineNumber++;
			this.tokenBegin = 0;
			this.tokenEnd = 0;

			this.currentLine = this.input.nextLine();

		} while (this.currentLine.length() == 0);
	}

	/**
	 * Utility method that reports whether a given String str containing one
	 * character is an acceptable character within the core language.
	 */
	private static boolean validCharacter(String str) {
		return DIGIT.matcher(str).matches() || UPPERCASE.matcher(str).matches() || LOWERCASE.matcher(str).matches()
				|| SPECIAL.matcher(str).matches() || WHITESPACE.matcher(str).matches();
	}

	/**
	 * Utility method that reports whether the string str contains at least n
	 * more characters starting at index start.
	 */
	private static boolean containsAtLeastNMore(String str, int start, int n) {
		return start + n <= str.length();
	}

	@Override
	public Type toType(String t) {

		if (IDENTIFIER.matcher(t).matches()) {
			return Tokenizer.Type.IDENTIFIER;
		}

		if (INTEGER.matcher(t).matches()) {
			return Tokenizer.Type.INTEGER;
		}

		switch (t) {
		case "program":
			return Tokenizer.Type.PROGRAM;
		case "begin":
			return Tokenizer.Type.BEGIN;
		case "end":
			return Tokenizer.Type.END;
		case "int":
			return Tokenizer.Type.INT;
		case "if":
			return Tokenizer.Type.IF;
		case "then":
			return Tokenizer.Type.THEN;
		case "else":
			return Tokenizer.Type.ELSE;
		case "while":
			return Tokenizer.Type.WHILE;
		case "loop":
			return Tokenizer.Type.LOOP;
		case "read":
			return Tokenizer.Type.READ;
		case "write":
			return Tokenizer.Type.WRITE;
		case "and":
			return Tokenizer.Type.AND;
		case "or":
			return Tokenizer.Type.OR;
		case ":":
			return Tokenizer.Type.SEMICOLON;
		case "=":
			return Tokenizer.Type.ASSIGN;
		case "!":
			return Tokenizer.Type.NOT;
		case "[":
			return Tokenizer.Type.LEFT_BRACKET;
		case "]":
			return Tokenizer.Type.RIGHT_BRACKET;
		case "(":
			return Tokenizer.Type.LEFT_PAREN;
		case ")":
			return Tokenizer.Type.RIGHT_BRACKET;
		case "+":
			return Tokenizer.Type.PLUS;
		case "-":
			return Tokenizer.Type.MINUS;
		case "*":
			return Tokenizer.Type.TIMES;
		case "!=":
			return Tokenizer.Type.NOT_EQUAL;
		case "==":
			return Tokenizer.Type.EQUAL;
		case ">=":
			return Tokenizer.Type.GREATER_THAN_OR_EQUAL;
		case "<=":
			return Tokenizer.Type.LESS_THAN_OR_EQUAL;
		case ">":
			return Tokenizer.Type.GREATER_THAN;
		case "<":
			return Tokenizer.Type.LESS_THAN;
		case ";":
			return Tokenizer.Type.SEMICOLON;

		default:
			throw new RuntimeException("Invalid or missing entry for " + t + " in TokenizerImpl.toType");
		}
	}
}