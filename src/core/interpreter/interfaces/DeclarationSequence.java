package core.interpreter.interfaces;

import core.interpreter.implementations.DeclarationSequenceImpl;
import core.parser.interfaces.Executable;

/**
 * Declaration sequence AST node interface.
 */
public interface DeclarationSequence extends Executable {

	/**
	 * Declaration Sequence factory method.
	 * 
	 * @param dec
	 *            the declaration node child instance.
	 * @return A declaration sequence node.
	 */
	static DeclarationSequence createInstance(Declaration dec) {
		return new DeclarationSequenceImpl(dec);
	}

	/**
	 * Declaration sequence factory method.
	 * 
	 * @param dec
	 *            the declaration node child instance.
	 * @param decs
	 *            the declaration sequence child instance.
	 * @return A declaration sequence node.
	 */
	static DeclarationSequence createInstance(Declaration dec, DeclarationSequence decs) {
		return new DeclarationSequenceImpl(dec, decs);
	}
}
