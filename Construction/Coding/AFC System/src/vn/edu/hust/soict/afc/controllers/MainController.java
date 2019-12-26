/**
 * @author duypv
 * @date Dec 2, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;
import vn.edu.hust.soict.afc.services.TicketService;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;
import vn.edu.hust.soict.afc.utils.FareCalculator;

/**
 * Main controller of system
 * @author duypv
 *
 */
public class MainController {

	private TicketService ticketService = new TicketService();
	private ItemController itemController;
	private FareCalculator fareCalculator = new FareCalculatorByDistance();

	/**
	 * Constructor
	 */
	public MainController() {
	}

	/**
	 * 
	 * @param appState
	 * @return {DataResponse}
	 */
	public DataResponse commandEnter(AppState appState) {
		String barcode = appState.getItemBarcode();
		if (appState.isByTicket()) {
			String ticketType = ticketService.getTicketType(barcode);
			if (ticketType.equalsIgnoreCase("OW")) {
				itemController = new OWController(new OWTicketServiceImpl(fareCalculator));
			} else if (ticketType.equalsIgnoreCase("TF")) {
				itemController = new TFController(new TFTicketServiceImpl());
			}
		} else {
			itemController = new PPController(new PPCardServiceImpl(fareCalculator));

		}
		return itemController.process(appState);
	}
}