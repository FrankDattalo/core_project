package core.interpreter.interfaces;

import core.interpreter.implementations.StatementSequenceImpl;
import core.parser.interfaces.Executable;

/**
 * Statement Sequence AST node.
 */
public interface StatementSequence extends Executable {

	/**
	 * Statement Sequence factory method.
	 * 
	 * @param stmt
	 *            The statement child node instance.
	 * @return A statement sequence node.
	 */
	static StatementSequence createInstance(Statement stmt) {
		return new StatementSequenceImpl(stmt);
	}

	/**
	 * Statement sequence factory method.
	 * 
	 * @param stmt
	 *            the statement child node instance.
	 * @param stmtSeq
	 *            the statement sequence child node instance.
	 * @return A statement sequence node.
	 */
	static StatementSequence createInstance(Statement stmt, StatementSequence stmtSeq) {
		return new StatementSequenceImpl(stmt, stmtSeq);
	}
}
