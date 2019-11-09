package vn.edu.hust.soict.afc.exception;

@SuppressWarnings("serial")
public class CantReadBarCodeException extends RuntimeException {

	public CantReadBarCodeException(String message) {
		super(message);
	}

}
