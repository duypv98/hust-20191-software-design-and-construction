package vn.edu.hust.soict.afc.exception;

/**
 * balance less than base fare exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class BalanceLessThanBaseFareException extends RuntimeException {

	public BalanceLessThanBaseFareException(String message) {
		super(message);
	}

}
