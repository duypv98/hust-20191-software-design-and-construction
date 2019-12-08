/**
 *
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TicketService;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;
import vn.edu.hust.soict.afc.utils.IFareCalculator;

/**
 * @author Professor
 *
 */
public class MainController {

	private TicketService ticketService = new TicketService();
	private ItemController itemController;
	private IFareCalculator fareCalculator = new FareCalculatorByDistance();

	/**
	 *
	 */
	public MainController() {
	}

	public DataResponse commandEnter(AppState appState) {
		String barcode = appState.getItemBarcode();
		if (appState.isByTicket()) {
			String ticketType = ticketService.getTicketType(barcode);
			if (ticketType.equalsIgnoreCase("OW")) {
				itemController = new OWController(fareCalculator);
			} else if (ticketType.equalsIgnoreCase("TF")) {
				itemController = new TFController();
			}
		} else {
			itemController = new PPController(fareCalculator);

		}
		return itemController.process(appState);
	}
}