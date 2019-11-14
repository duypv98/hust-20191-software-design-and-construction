/**
 *
 */
package vn.edu.hust.soict.afc.controllers;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TicketService;

/**
 * @author Professor
 *
 */
public class MainController {

	public static TicketRecognizer ticketRecognizer;
	private TicketService ticketService = new TicketService();
	private OWController owController = new OWController();
	private TFController tfController = new TFController();
	private PPController pPController = new PPController();

	/**
	 *
	 */
	public MainController() {
		ticketRecognizer = TicketRecognizer.getInstance();
	}

	public String getTicketCode(String barcode) {
		String ticketCode = null;
		try {
			ticketCode = ticketRecognizer.process(barcode);
		} catch (InvalidIDException e) {

		}
		return ticketCode;
	}

	public DataResponse commandEnter(AppState appState) {
		String barcode = appState.getItemBarcode();
		if (appState.isByTicket()) {
			String ticketType = ticketService.getTicketType(barcode);
			if (ticketType.equalsIgnoreCase("OW")) {
				return owController.process(appState);
			} else if (ticketType.equalsIgnoreCase("TF")) {
				return tfController.process(appState);
			}
		} else {
			return pPController.process(appState);
		}
		return null;
	}
}