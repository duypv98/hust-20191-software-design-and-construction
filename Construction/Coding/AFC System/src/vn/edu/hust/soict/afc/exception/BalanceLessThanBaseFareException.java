package vn.edu.hust.soict.afc.exception;

@SuppressWarnings("serial")
public class BalanceLessThanBaseFareException extends RuntimeException {

	public BalanceLessThanBaseFareException(String message) {
		super(message);
	}

}
