package vn.edu.hust.soict.afc.exception;

/**
 * can't find card exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class CantFindCardException extends RuntimeException {

	public CantFindCardException(String message) {
		super(message);
	}

}
