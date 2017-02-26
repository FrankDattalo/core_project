package core.tokenizer.implementations;

import java.util.regex.Pattern;

import core.tokenizer.interfaces.Tokenizer;

public abstract class AbstractTokenizer implements Tokenizer {

	/**
	 * Patterns to be used throughout the Tokenization process.
	 */
	protected static final Pattern RESERVED_WORD = Pattern
			.compile("^(program|begin|end|int|if|then|else|while|loop|read|write|and|or)$");
	protected static final Pattern INTEGER = Pattern.compile("^([0-9]{1,8})$");
	protected static final Pattern TL_SPECIAL = Pattern.compile("^(!=|==|>=|<=)$");
	protected static final Pattern SPECIAL = Pattern.compile("^(;|,|=|!|\\[|\\]|\\(|\\)|\\+|-|\\*|>|<)$");
	protected static final Pattern IDENTIFIER = Pattern.compile(
			"^([A-Z][0-9]{0,7}|[A-Z]{2}[0-9]{0,6}|[A-Z]{3}[0-9]{0,5}|[A-Z]{4}[0-9]{0,4}|[A-Z]{5}[0-9]{0,3}|[A-Z]{6}[0-9]{0,2}|[A-Z]{7}[0-9]{0,1}|[A-Z]{8})$");
	protected static final Pattern UPPERCASE = Pattern.compile("^([A-Z])$");
	protected static final Pattern LOWERCASE = Pattern.compile("^([a-z])$");
	protected static final Pattern DIGIT = Pattern.compile("^([0-9])$");
	protected static final Pattern WHITESPACE = Pattern.compile("^(\n|\t|\r| )+$");

	/**
	 * Function that maps symbols to their respective Token numbers.
	 */
	public int toIntCode(String x) {

		if (INTEGER.matcher(x).matches()) {
			return 31;
		}

		switch (x) {
		case "program":
			return 1;
		case "begin":
			return 2;
		case "end":
			return 3;
		case "int":
			return 4;
		case "if":
			return 5;
		case "then":
			return 6;
		case "else":
			return 7;
		case "while":
			return 8;
		case "loop":
			return 9;
		case "read":
			return 10;
		case "write":
			return 11;
		case "and":
			return 12;
		case "or":
			return 13;
		case ";":
			return 14;
		case ",":
			return 15;
		case "=":
			return 16;
		case "!":
			return 17;
		case "[":
			return 18;
		case "]":
			return 19;
		case "(":
			return 20;
		case ")":
			return 21;
		case "+":
			return 22;
		case "-":
			return 23;
		case "*":
			return 24;
		case "!=":
			return 25;
		case "==":
			return 26;
		case ">=":
			return 27;
		case "<=":
			return 28;
		case ">":
			return 29;
		case "<":
			return 30;
		case EOF:
			return 33;
		default:
			return 32;
		}
	}
}
