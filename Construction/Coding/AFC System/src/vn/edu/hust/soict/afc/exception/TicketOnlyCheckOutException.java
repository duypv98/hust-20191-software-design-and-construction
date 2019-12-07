/**
 * 
 */
package vn.edu.hust.soict.afc.exception;

/**
 * ticket only check out exception
 * @author hainn
 * @date Dec 7, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */

@SuppressWarnings("serial")
public class TicketOnlyCheckOutException extends RuntimeException {

	public TicketOnlyCheckOutException(String message) {
		super(message);
	}

}
