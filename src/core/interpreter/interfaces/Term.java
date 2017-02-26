package core.interpreter.interfaces;

import core.interpreter.implementations.TermImpl;
import core.parser.interfaces.Evaluatable;

/**
 * Term AST node interface.
 */
public interface Term extends Evaluatable<Integer> {

	/**
	 * Term factory method.
	 * 
	 * @param f
	 *            Factor node child instance.
	 * @return A factor node.
	 */
	static Term createInstance(Factor f) {
		return new TermImpl(f);
	}

	/**
	 * Term factory method.
	 * 
	 * @param f
	 *            Factor node child instance.
	 * @param t
	 *            Term node child instance.
	 * @return A factor node.
	 */
	static Term createInstance(Factor f, Term t) {
		return new TermImpl(f, t);
	}
}
