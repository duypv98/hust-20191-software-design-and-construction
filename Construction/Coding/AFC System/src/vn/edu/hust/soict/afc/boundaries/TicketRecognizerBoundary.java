package vn.edu.hust.soict.afc.boundaries;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;

public class TicketRecognizerBoundary {
	private static TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();

	/**
	 * @throws InvalidIDException
	 * 
	 */

	public static String getCode(String barcode) throws InvalidIDException {
		String ticketCode = null;
		try {
			ticketCode = ticketRecognizer.process(barcode);
		} catch (InvalidIDException e) {
			e.printStackTrace();
			throw e;
		}
		return ticketCode;
	}

}
