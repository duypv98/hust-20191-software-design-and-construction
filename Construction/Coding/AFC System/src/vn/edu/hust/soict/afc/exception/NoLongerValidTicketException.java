/**
 * 
 */
package vn.edu.hust.soict.afc.exception;

/**
 * no longer valid ticket exception
 * @author hainn
 * @date Dec 7, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
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
