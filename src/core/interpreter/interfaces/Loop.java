package core.interpreter.interfaces;

import core.interpreter.implementations.LoopImpl;
import core.parser.interfaces.Executable;

/**
 * Loop AST node interface.
 */
public interface Loop extends Executable {

	/**
	 * Loop node factory method.
	 * 
	 * @param cond
	 *            the condition node child instance.
	 * @param stmtSeq
	 *            the stmtSeq node child instance.
	 * @return A loop node.
	 */
	static Loop createInstance(Condition cond, StatementSequence stmtSeq) {
		return new LoopImpl(cond, stmtSeq);
	}
}
