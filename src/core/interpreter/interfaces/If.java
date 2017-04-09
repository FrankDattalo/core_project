package core.interpreter.interfaces;

import core.interpreter.implementations.IfImpl;
import core.parser.interfaces.Executable;

/**
 * If AST node interface.
 */
public interface If extends Executable {

	/**
	 * If factory instance.
	 * 
	 * @param cond
	 *            the condition node child instance.
	 * @param stmsSeq
	 *            the smtSeq node child instance.
	 * @return An if node.
	 */
	static If createInstance(Condition cond, StatementSequence stmsSeq) {
		return new IfImpl(cond, stmsSeq);
	}

	/**
	 * If factory instance.
	 * 
	 * @param cond
	 *            the condition node child instance.
	 * @param stmsSeqTrue
	 *            the statement sequence upon true child instance.
	 * @param stmsSeqFalse
	 *            the statement sequence upon false child instance.
	 * @return An if node.
	 */
	static If createInstance(Condition cond, StatementSequence stmsSeqTrue, StatementSequence stmsSeqFalse) {
		return new IfImpl(cond, stmsSeqTrue, stmsSeqFalse);
	}
}
