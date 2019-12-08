package vn.edu.hust.soict.afc.exception;

/**
 * card only check-out exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class CardOnlyCheckOutException extends RuntimeException {

	public CardOnlyCheckOutException(String message) {
		super(message);
	}

}
