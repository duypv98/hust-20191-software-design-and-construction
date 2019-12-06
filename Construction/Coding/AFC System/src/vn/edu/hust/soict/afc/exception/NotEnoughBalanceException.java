package vn.edu.hust.soict.afc.exception;

/**
 * not enought balance exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class NotEnoughBalanceException extends RuntimeException {

	public NotEnoughBalanceException(String message) {
		super(message);
	}

}
