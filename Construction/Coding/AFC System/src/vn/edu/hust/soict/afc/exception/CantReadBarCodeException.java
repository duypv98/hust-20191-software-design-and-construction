package vn.edu.hust.soict.afc.exception;

/**
 * can't read bar code exception
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
@SuppressWarnings("serial")
public class CantReadBarCodeException extends RuntimeException {

	public CantReadBarCodeException(String message) {
		super(message);
	}

}
