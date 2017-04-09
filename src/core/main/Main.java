package core.main;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import core.exceptions.CoreException;
import core.interpreter.implementations.IdImpl;
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

    private static void runProgram(String[] args) {
        try {

            if(args.length < 1) {
                throw new CoreException("Expected path to core file as first argument");
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

            program.execute();

        } catch (CoreException e) { // user runtime error
            System.err.println("[ERROR]: " + e.getMessage());
        }
    }

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
        runProgram(args);
	}
}
