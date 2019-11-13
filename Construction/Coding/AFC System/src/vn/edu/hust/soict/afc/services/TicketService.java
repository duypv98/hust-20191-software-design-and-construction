/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.DAO.OWTicketDAO;
import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.exception.CantReadBarCodeException;

/**
 * @author Professor
 *
 */
public class TicketService {
	private TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
	private OWTicketDAO oWTicketDAO = new OWTicketDAOImpl();
	//private TFTicketDAO tFTicketDAO = new TFTicketDAOImpl();
	
	public String getTicketType(String barCode) {
		String ticketCode;
		try {
			ticketCode = ticketRecognizer.process(barCode);
		} catch (InvalidIDException e) {
			throw new CantReadBarCodeException("INVALID TICKET\nCan't read barcode");
		}

		OneWayTicket oneWayTicket = oWTicketDAO.findByTicketCode(ticketCode);
//		TwentyFourTicket twentyFourTicket = tFTicketDAO.findByTicketCode(ticketCode);
		
		if (oneWayTicket != null) {
			return "OW";
		} else {
			return "TF";
		}
	}
}
