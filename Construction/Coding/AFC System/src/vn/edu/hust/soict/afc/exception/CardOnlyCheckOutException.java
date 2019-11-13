package vn.edu.hust.soict.afc.exception;

@SuppressWarnings("serial")
public class CardOnlyCheckOutException extends RuntimeException {

	public CardOnlyCheckOutException(String message) {
		super(message);
	}

}
