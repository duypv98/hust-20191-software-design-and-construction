/**
 * 
 */
package vn.edu.hust.soict.afc.exception;

/**
 * @author iProfessor
 *
 */
@SuppressWarnings("serial")
public class TicketOnlyCheckOutException extends RuntimeException {

	/**
	 * 
	 * @param message
	 */
	public TicketOnlyCheckOutException(String message) {
		super(message);
	}

}
