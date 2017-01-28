package core.exceptions;

/**
 * Common exception class for the Core Interpreter. User related errors will be
 * thrown using this exception.
 * 
 * @author Frank Dattalo
 */
public class CoreException extends RuntimeException {

	private static final long serialVersionUID = -1894033025984827750L;

	public CoreException(String s) {
		super(s);
	}
}
