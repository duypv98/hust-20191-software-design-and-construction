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

import hust.soict.se.recognizer.TicketRecognizer;
import hust.soict.se.scanner.CardScanner;
import vn.edu.hust.soict.afc.DAO.OWTicketDAO;
import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.DAO.OWTripDAO;
import vn.edu.hust.soict.afc.DAO.OWTripDAOImpl;
import vn.edu.hust.soict.afc.DAO.PPCardDAO;
import vn.edu.hust.soict.afc.DAO.PPCardDAOImpl;
import vn.edu.hust.soict.afc.DAO.PPTripDAO;
import vn.edu.hust.soict.afc.DAO.PPTripDAOImpl;
import vn.edu.hust.soict.afc.DAO.StationDAO;
import vn.edu.hust.soict.afc.DAO.StationDAOImpl;
import vn.edu.hust.soict.afc.DAO.TFTicketDAO;
import vn.edu.hust.soict.afc.DAO.TFTicketDAOImpl;
import vn.edu.hust.soict.afc.DAO.TFTripDAO;
import vn.edu.hust.soict.afc.DAO.TFTripDAOImpl;
import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;
import vn.edu.hust.soict.afc.services.TicketService;
import vn.edu.hust.soict.afc.utils.FareCalculator;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;

/**
 * Main controller of system
 * @author duypv
 *
 */
public class MainController {

	private TicketService ticketService = new TicketService();
	private ItemController itemController;
	private FareCalculator fareCalculator = new FareCalculatorByDistance();

	private StationDAO stationDAO = new StationDAOImpl();
	private OWTicketDAO oWTicketDAO = new OWTicketDAOImpl();
	private TFTicketDAO tFTicketDAO = new TFTicketDAOImpl();
	private PPCardDAO pPCardDAO = new PPCardDAOImpl();

	private OWTripDAO oWTripDAO = new OWTripDAOImpl();
	private TFTripDAO tFTripDAO = new TFTripDAOImpl();
	private PPTripDAO pPTripDAO = new PPTripDAOImpl();

	private TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
	private CardScanner cardScanner = CardScanner.getInstance();

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
				itemController = new OWController(new OWTicketServiceImpl(stationDAO, oWTicketDAO, oWTripDAO, fareCalculator, ticketRecognizer));
			} else if (ticketType.equalsIgnoreCase("TF")) {
				itemController = new TFController(new TFTicketServiceImpl(tFTicketDAO, tFTripDAO, ticketRecognizer));
			}
		} else {
			itemController = new PPController(new PPCardServiceImpl(stationDAO, pPCardDAO, pPTripDAO, fareCalculator, cardScanner));

		}
		return itemController.process(appState);
	}
}