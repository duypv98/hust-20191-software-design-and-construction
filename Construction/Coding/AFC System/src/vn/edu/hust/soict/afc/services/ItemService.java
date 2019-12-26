package vn.edu.hust.soict.afc.services;

import hust.soict.se.recognizer.TicketRecognizer;
import hust.soict.se.scanner.CardScanner;
import vn.edu.hust.soict.afc.DAO.OWTicketDAO;
import vn.edu.hust.soict.afc.DAO.OWTripDAO;
import vn.edu.hust.soict.afc.DAO.PPCardDAO;
import vn.edu.hust.soict.afc.DAO.PPTripDAO;
import vn.edu.hust.soict.afc.DAO.StationDAO;
import vn.edu.hust.soict.afc.DAO.TFTicketDAO;
import vn.edu.hust.soict.afc.DAO.TFTripDAO;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.utils.FareCalculator;

/**
 *
 * @author duytruong
 *
 */
public abstract class ItemService {

	protected StationDAO stationDAO;

	protected OWTicketDAO oWTicketDAO;
	protected TFTicketDAO tFTicketDAO;
	protected PPCardDAO pPCardDAO;

	protected OWTripDAO oWTripDAO;
	protected TFTripDAO tFTripDAO;
	protected PPTripDAO pPTripDAO;

	protected FareCalculator fareCalculator;

	protected TicketRecognizer ticketRecognizer;
	protected CardScanner cardScanner;

	public ItemService(StationDAO stationDAO, OWTicketDAO oWTicketDAO, OWTripDAO oWTripDAO,
			FareCalculator fareCalculator, TicketRecognizer ticketRecognizer) {
		super();
		this.stationDAO = stationDAO;
		this.oWTicketDAO = oWTicketDAO;
		this.oWTripDAO = oWTripDAO;
		this.fareCalculator = fareCalculator;
		this.ticketRecognizer = ticketRecognizer;
	}

	public ItemService(TFTicketDAO tFTicketDAO, TFTripDAO tFTripDAO, TicketRecognizer ticketRecognizer) {
		super();
		this.tFTicketDAO = tFTicketDAO;
		this.tFTripDAO = tFTripDAO;
		this.ticketRecognizer = ticketRecognizer;
	}

	public ItemService(StationDAO stationDAO, PPCardDAO pPCardDAO, PPTripDAO pPTripDAO, FareCalculator fareCalculator,
			CardScanner cardScanner) {
		super();
		this.stationDAO = stationDAO;
		this.pPCardDAO = pPCardDAO;
		this.pPTripDAO = pPTripDAO;
		this.fareCalculator = fareCalculator;
		this.cardScanner = cardScanner;
	}

	/**
	 *
	 * @param barCode
	 * @param station
	 * @return
	 */
	public abstract DataResponse checkIn(String barCode, Station station);

	/**
	 *
	 * @param barCode
	 * @param station
	 * @return
	 */
	public abstract DataResponse checkOut(String barCode, Station station);
}