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
}
