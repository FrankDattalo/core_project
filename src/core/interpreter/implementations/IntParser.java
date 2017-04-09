package core.interpreter.implementations;

import core.exceptions.CoreException;

/**
 * Utility class to parse Core Ints
 */
public class IntParser {

    /**
     * Reports whether i is a valid core integer.
     * @param i The int to test
     * @return Whether i is a valid core integer.
     */
	public static boolean isValidInt(int i) {
		return -99999999 <= i && i <= 99999999;
	}

    /**
     * Reports whether the string is a valid integer.
     * @param str the string to test.
     * @return whether the string is a valid integer.
     */
	public static boolean isValidInt(String str) {
		try {
			return isValidInt(parse(str));
		} catch (CoreException e) {
			return false;
		}
	}

    /**
     * Parses str as a core int.
     * @param str the string to parse.
     * @return the parsed int.
     */
	public static int parse(String str) {

		if(str == null || str.isEmpty()) {
			throw new CoreException("Invalid integer " + str);
		}

		boolean isNegative = str.charAt(0) == '-';

		if(isNegative) {
			str = str.substring(1);
		}

		if(str == null || str.isEmpty()) {
			throw new CoreException("Invalid integer " + str);
		}

		int ret = 0;

		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i) - '0';

			if(c < 0 || c > 9) {
				throw new CoreException("Invalid integer " + str);
			}

			ret += c;

			if (i < str.length() - 1) {
				ret *= 10;
			}
		}

		if(isNegative) {
		    ret *= -1;
        }

		return ret;
	}
}
