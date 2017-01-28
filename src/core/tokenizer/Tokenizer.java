package core.tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

import core.exceptions.CoreException;

/**
 * Public interface for the Core Tokenizer.
 * 
 * @author Frank Dattalo
 */
public interface Tokenizer {
	
	/**
	 * The Token that signifies the end of the file.
	 */
	public static final String 	EOF 			= "###END_OF_FILE_TOKEN###";
	
	/**
	 * Patterns to be used throughout the Tokenization process.
	 */
	public static final Pattern RESERVED_WORD 	= Pattern.compile("^(program|begin|end|int|if|then|else|while|loop|read|write|and|or)$");
	public static final Pattern INTEGER 		= Pattern.compile("^([0-9]{1,8})$");
	public static final Pattern TL_SPECIAL 		= Pattern.compile("^(!=|==|>=|<=)$");
	public static final Pattern SPECIAL 		= Pattern.compile("^(;|,|=|!|\\[|\\]|\\(|\\)|\\+|-|\\*|>|<)$");
	public static final Pattern IDENTIFIER 		= Pattern.compile("^([A-Z][0-9]{0,7}|[A-Z]{2}[0-9]{0,6}|[A-Z]{3}[0-9]{0,5}|[A-Z]{4}[0-9]{0,4}|[A-Z]{5}[0-9]{0,3}|[A-Z]{6}[0-9]{0,2}|[A-Z]{7}[0-9]{0,1}|[A-Z]{8})$");
	public static final Pattern UPPERCASE 		= Pattern.compile("^([A-Z])$");
	public static final Pattern LOWERCASE 		= Pattern.compile("^([a-z])$");
	public static final Pattern DIGIT 			= Pattern.compile("^([0-9])$");
	public static final Pattern WHITESPACE 		= Pattern.compile("^(\n|\t|\r| )+$");
	
	/**
	 * Returns the current token from the input program.
	 */
	String currentToken();
	
	/**
	 * Returns the next token from the input program.
	 */
	String nextToken();

	void setScanner(Scanner in);

	/**
	 * Function that maps symbols to their respective Token numbers.
	 */
	public static int toIntCode(String x) {

		if(INTEGER.matcher(x).matches()) {
			return 31;
		}
		
		switch(x) {
		case "program": return 1;
		case "begin": 	return 2;
		case "end": 	return 3;
		case "int": 	return 4;
		case "if": 		return 5;
		case "then": 	return 6;
		case "else": 	return 7;
		case "while": 	return 8;
		case "loop": 	return 9;
		case "read": 	return 10;
		case "write": 	return 11;
		case "and": 	return 12;
		case "or": 		return 13;
		case ";": 		return 14;
		case ",":		return 15;
		case "=":		return 16;
		case "!": 		return 17;
		case "[":		return 18;
		case "]":		return 19;
		case "(":		return 20;
		case ")":		return 21;
		case "+":		return 22;
		case "-":		return 23;
		case "*":		return 24;
		case "!=":		return 25;
		case "==":		return 26;
		case ">=":		return 27;
		case "<=":		return 28;
		case ">":		return 29;
		case "<":		return 30;
		case EOF: 		return 33;
		default: 		return 32;
		}
	}
}
