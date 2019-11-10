package vn.edu.hust.soict.afc.exception;

@SuppressWarnings("serial")
public class NotEnoughBalanceException extends RuntimeException {

	public NotEnoughBalanceException(String message) {
		super(message);
	}

}
