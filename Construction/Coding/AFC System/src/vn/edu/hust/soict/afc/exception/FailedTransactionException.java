package vn.edu.hust.soict.afc.exception;

/**
 * failed transaction exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class FailedTransactionException extends RuntimeException {

	public FailedTransactionException(String message) {
		super(message);
	}

}
