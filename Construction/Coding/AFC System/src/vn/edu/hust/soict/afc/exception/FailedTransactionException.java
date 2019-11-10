package vn.edu.hust.soict.afc.exception;

@SuppressWarnings("serial")
public class FailedTransactionException extends RuntimeException {

	public FailedTransactionException(String message) {
		super(message);
	}

}
