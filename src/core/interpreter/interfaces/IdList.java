package core.interpreter.interfaces;

import java.util.List;

import core.interpreter.implementations.IdListImpl;
import core.parser.interfaces.Evaluatable;

/**
 * IdList AST node.
 */
public interface IdList extends Evaluatable<List<Id>> {

	/**
	 * IdList factory method.
	 * 
	 * @param id
	 *            the id child node.
	 * @return An IdList instance.
	 */
	static IdList createInstance(Id id) {
		return new IdListImpl(id);
	}

	/**
	 * IdList factory method.
	 * 
	 * @param id
	 *            the id child node.
	 * @param idl
	 *            the idlist child node.
	 * @return An idlist instance.
	 */
	static IdList createInstance(Id id, IdList idl) {
		return new IdListImpl(id, idl);
	}
}
