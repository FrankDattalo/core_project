package core.main;

import core.exceptions.CoreException;
import core.tokenizer.SingleLineTokenizerImplementation;
import core.tokenizer.Tokenizer;
import java.util.regex.Pattern;
import java.io.File;
import java.util.Scanner;

/**
 * Entry class to the Core Tokenizer.
 * 
 * @author Frank Dattalo
 */
public class Main {

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		try {
			// input validation
			if(args.length < 1) {
				throw new CoreException("Expected filename as first argument");
			}

			String fileName = args[0];

			// input validation
			if(!Pattern.compile(".*\\.core$").matcher(fileName).matches()) {	
				throw new CoreException("'" + fileName + "' is an invalid file format. Expected file extension: '.core'");
			}
			
			// open file 
			File inputFile = new File(fileName);
			
			// input validation
			if(!inputFile.exists()) { 
				throw new CoreException(fileName + " does not exist.");
			}
			
			// instantiate tokenizer
			Tokenizer tokenizer = new SingleLineTokenizerImplementation();

			// setup tokenizer
			tokenizer.setScanner(new Scanner(inputFile));

			String current = Tokenizer.EOF;
			do {
				tokenizer.nextToken();

				current = tokenizer.currentToken();

				System.out.println(Tokenizer.toIntCode(current));

			} while(current != Tokenizer.EOF);
			
		} catch (CoreException e) { // user runtime error
			System.err.println("[ERROR]: " + e.getMessage());

		} catch (Exception e) { // programming error
			e.printStackTrace();
		}
	}
}
