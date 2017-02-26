package core.interpreter.interfaces;

import core.interpreter.implementations.ProgramImpl;
import core.parser.interfaces.Executable;

/**
 * Program AST node interface.
 */
public interface Program extends Executable {

	/**
	 * Program factory method.
	 * 
	 * @param ds
	 *            the declaration sequence child node instance.
	 * @param ss
	 *            the statement sequence child node instance.
	 * @return A program node.
	 */
	static Program createInstance(DeclarationSequence ds, StatementSequence ss) {
		return new ProgramImpl(ds, ss);
	}
}
