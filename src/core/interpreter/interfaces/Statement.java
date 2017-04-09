package core.interpreter.interfaces;

import core.interpreter.implementations.StatementImpl;
import core.parser.interfaces.Executable;

/**
 * Statement AST node.
 */
public interface Statement extends Executable {

	/**
	 * Statement factory method.
	 * 
	 * @param assign
	 *            the assign node child instance.
	 * @return A statement node.
	 */
	static Statement createInstance(Assign assign) {
		return new StatementImpl(assign);
	}

	/**
	 * Statement factory method.
	 * 
	 * @param ifStmt
	 *            the if statement node child instance.
	 * @return A statement node.
	 */
	static Statement createInstance(If ifStmt) {
		return new StatementImpl(ifStmt);
	}

	/**
	 * Statement factory method.
	 * 
	 * @param loop
	 *            the loop statement node child instance.
	 * @return A statement node.
	 */
	static Statement createInstance(Loop loop) {
		return new StatementImpl(loop);
	}

	/**
	 * Statement factory method.
	 * 
	 * @param in
	 *            the in statement node child instance.
	 * @return A statement node.
	 */
	static Statement createInstance(In in) {
		return new StatementImpl(in);
	}

	/**
	 * Statement factory method.
	 * 
	 * @param out
	 *            the out statement node child instance.
	 * @return A statement node.
	 */
	static Statement createInstance(Out out) {
		return new StatementImpl(out);
	}
}
