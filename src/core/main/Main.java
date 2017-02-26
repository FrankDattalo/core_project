package core.main;

import java.io.File;

import core.exceptions.CoreException;
import core.interpreter.interfaces.Program;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

/**
 * Entry class to the Core Interpreter.
 *
 * @author Frank Dattalo
 */
public class Main {

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		try {

			if (args.length < 1) {
				throw new CoreException("Expected filename as first argument");
			}

			String fileName = args[0];

			// open file
			File inputFile = new File(fileName);

			// input validation
			if (!inputFile.exists()) {
				throw new CoreException(fileName + " does not exist.");
			}

			// instantiate tokenizer
			Tokenizer tokenizer = Tokenizer.createInstance(inputFile);

			// instantiate a new parser container
			ParserContainer container = ParserContainer.newInstance();

			// get the program parser
			Parser<Program> parser = container.getProgramParser();

			// parse the program
			Program program = parser.parse(container, tokenizer);

			// print the program
			program.print(0, System.out);

		} catch (CoreException e) { // user runtime error
			System.err.println("[ERROR]: " + e.getMessage());

		} catch (Exception e) { // programming error
			e.printStackTrace();
		}
	}
}
