/**
 * 
 */
package vn.edu.hust.soict.afc.exception;

/**
 * @author iProfessor
 *
 */
@SuppressWarnings("serial")
public class NoLongerValidTicketException extends RuntimeException {
	/**
	 * @param message
	 */
	public NoLongerValidTicketException(String message) {
		super(message);
	}
}
