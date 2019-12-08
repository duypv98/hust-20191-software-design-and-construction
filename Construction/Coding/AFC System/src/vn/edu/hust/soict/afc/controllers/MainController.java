/**
 *
 */
package vn.edu.hust.soict.afc.controllers;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TicketService;
import vn.edu.hust.soict.afc.utils.IFareCalculator;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;

/**
 * @author Professor
 *
 */
public class MainController {

	public static TicketRecognizer ticketRecognizer;
	private TicketService ticketService = new TicketService();
	private OWController owController;
	private TFController tfController;
	private PPController pPController;
	private IFareCalculator fareCalculator = new FareCalculatorByDistance();

	/**
	 *
	 */
	public MainController() {
		ticketRecognizer = TicketRecognizer.getInstance();
		owController = new OWController(fareCalculator);
		tfController = new TFController();
		pPController = new PPController(fareCalculator);
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