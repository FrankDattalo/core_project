package core.tokenizer;

import java.util.Scanner;

import core.exceptions.CoreException;
import core.tokenizer.Tokenizer;

/**
 * Implementation of Tokenizer which reads in one line at a time during tokenization.
 * 
 * @author Frank Dattalo
 */
public class SingleLineTokenizerImplementation implements Tokenizer {

    private Scanner input;
    private String currentLine;
    private int lineNumber = 1;
    private int tokenBegin = 0;
    private int tokenEnd = 0;

    public SingleLineTokenizerImplementation() {
    }

    public void setScanner(Scanner in) {
        input = in;
    }

	/**
	 * Returns the next token in the stream or the EOF token if no token exists.
	 */
    public void nextToken() {
        if(noTokensAvailable()) return;

        if(currentLine == null) currentLine = input.nextLine();
        
        advanceToNextNonWhitespace();
               
        if(noTokensAvailable()) return;

        String currentChar = currentLine.substring(tokenBegin, tokenEnd);

        // if the character we are currently looking at is invalid then alert user
		if(!validCharacter(currentChar)) {
			error("Invalid character '" + currentChar + "'");
		}

        // if the current character is uppercase then parse identifier
		if(UPPERCASE.matcher(currentChar).matches()) {
			// parse until whitespace or special
			parseUntilWhitespaceOrSpecial();
			
			// get fully parsed token
			String token = currentToken();
			
			// if the token is not a valid identifier then alert user
			if(!IDENTIFIER.matcher(token).matches()) {
				error("Invalid identifier '" + token + "'");
			}
		
		// if the current character is lowercase then parse reserved word
		} else if (LOWERCASE.matcher(currentChar).matches()) {
			// parse until whitespace or special
			parseUntilWhitespaceOrSpecial();
			
			// get fully parsed token
			String token = currentToken();
			
			// if the token is not a valid reserved word then alert user
			if(!RESERVED_WORD.matcher(token).matches()) {
				error("Invalid keyword '" + token + "'");
			}
			
		// if the current character is a special character then parse as either single or double special
		} else if (SPECIAL.matcher(currentChar).matches()) {

			// try to parse as double special
			if(containsAtLeastNMore(currentLine, tokenEnd, 1)) {
				
				// check the next character is valid
				String nextChar = currentLine.substring(tokenEnd, tokenEnd + 1);
				
				if(!validCharacter(nextChar)) {
					error("Unexpected character '" + nextChar + "'");
				}
				
				// if the concatenation of our current character and next character is a two letter
				// special then increase length of current token
				if(TL_SPECIAL.matcher(currentChar + nextChar).matches()) {
					tokenEnd++;
				}
			}
				
		// if the current character is a digit then parse as an integer
		} else if(DIGIT.matcher(currentChar).matches()) {
			// parse until whitespace or special
			parseUntilWhitespaceOrSpecial();

			// get the fully parsed token
			String token = currentToken();
			
			// if the token is not a valid integer then alert user
			if(!INTEGER.matcher(token).matches()) {
				error("Invalid integer literal '" + token + "'");
			}
			
		// if we were unable to parse as integer, reserved word, identifier, or special then alert 
		// the user of invalid character
		} else {
			error("Unexpected character '" + currentChar + "'");
		
		}
    }

	/**
	 * Returns the current token in the stream.  Repeated calls to this will return the same token.
	 * Calling this method before calling nextToken when a stream is originally opened is undefined behavior.
	 */
    public String currentToken() {
        if(noTokensAvailable()) return EOF;
        
        return currentLine.substring(tokenBegin, tokenEnd);
    }

	/**
	 * Utility method to determine if there is a parsable token in the stream
	 */
    private boolean noTokensAvailable() {
        return !input.hasNextLine() && currentLine != null && tokenBegin >= currentLine.length();
    }

	/**
	 * Advances the cursor until it hits a whitespace or special character
	 */
    private void parseUntilWhitespaceOrSpecial() {

        // while the current line contains at least 1 more parseable character
		while(containsAtLeastNMore(currentLine, tokenEnd, 1)) {
			
			// get that character
			String nextChar = currentLine.substring(tokenEnd, tokenEnd + 1);
			
			// verify that it is valid
			if(!validCharacter(nextChar)) {
				error("Unexpected character '" + nextChar + "'");
			}
			
			// if that character is a whitespace or special break
			if(WHITESPACE.matcher(nextChar).matches() 
					|| SPECIAL.matcher(nextChar).matches()) {
				break;
			}
			
			// otherwise move to next character
			tokenEnd++;
		}
    }

    /**
	 * Utility method for creating Core Tokenizer Exceptions.
	 */
	private void error(String str) {
		throw new CoreException("LINE: [" + lineNumber + "]: " + str);
	}

	/**
	 * Utility method to advance to the next non whitespace character.
	 */
    private void advanceToNextNonWhitespace() {
    	
		// move token beginning
        tokenBegin = tokenEnd;
        tokenEnd++;

		// if we are at the end of the the line then go to the next line
        if(currentLine.length() <= tokenBegin) {

            advanceToNextNonEmptyLine();

			// if there are no tokens available then return
            if(noTokensAvailable()) return;
                        
			// else advance to the next non whitespace token
            advanceToNextNonWhitespace();
        }

		// if our current character is a whitespace then advance
        while(tokenBegin <= currentLine.length()
				&& tokenEnd <= currentLine.length()
                && WHITESPACE.matcher(currentLine.substring(tokenBegin, tokenEnd)).matches()) {
        
            tokenBegin = tokenEnd;
            tokenEnd++;
        }

		// if we are at the end of the line then we did not hit a whitespace
        if(currentLine.length() <= tokenBegin) {

			// so go to the next line
            advanceToNextNonEmptyLine();
            
			// if there are no tokens available then return
            if(noTokensAvailable()) return;
            
			// else advance to the next non whitespace token
            advanceToNextNonWhitespace();
        }
    }

	/**
	 * Utility method to advance the cursor to the next non empty line
	 */
    private void advanceToNextNonEmptyLine() {
        do {
            if(!input.hasNextLine()) return;

            lineNumber++;
            tokenBegin = 0;
            tokenEnd = 0;

            currentLine = input.nextLine();

        } while(currentLine.length() == 0);
    }

    /**
	 * Utility method that reports whether a given String str containing one character
	 * is an acceptable character within the core language.
	 */
	private static boolean validCharacter(String str) {
		return DIGIT.matcher(str).matches() ||
				UPPERCASE.matcher(str).matches() ||
				LOWERCASE.matcher(str).matches() ||
				SPECIAL.matcher(str).matches() ||
				WHITESPACE.matcher(str).matches();
	}

    /**
	 * Utility method that reports whether the string str contains at least n more 
	 * characters starting at index start.
	 */	
	private static boolean containsAtLeastNMore(String str, int start, int n) {
		return start + n <= str.length();
	}
}