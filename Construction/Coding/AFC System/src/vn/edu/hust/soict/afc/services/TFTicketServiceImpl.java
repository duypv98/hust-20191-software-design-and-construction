/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.DAO.TFTicketDAO;
import vn.edu.hust.soict.afc.DAO.TFTicketDAOImpl;
import vn.edu.hust.soict.afc.DAO.TFTripDAO;
import vn.edu.hust.soict.afc.DAO.TFTripDAOImpl;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.entities.TwentyFourTicket;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;
import vn.edu.hust.soict.afc.exception.CantFindTicketException;
import vn.edu.hust.soict.afc.exception.CantReadBarCodeException;
import vn.edu.hust.soict.afc.exception.FailedTransactionException;
import vn.edu.hust.soict.afc.exception.NoLongerValidTicketException;
import vn.edu.hust.soict.afc.exception.TicketOnlyCheckInException;
import vn.edu.hust.soict.afc.exception.TicketOnlyCheckOutException;

/**
 * twenty-four hour ticket's service implement
 * @author hainn
 * @date Dec 7, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */

public class TFTicketServiceImpl implements TFTicketService {

	private TFTicketDAO tFTicketDAO = new TFTicketDAOImpl();
	private TFTripDAO tFTripDAO = new TFTripDAOImpl();
	private TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();

	public TFTicketServiceImpl() {
	}

	@Override
	public DataResponse checkIn(String barCode, Station station) {
		String ticketCode;
		try {
			ticketCode = ticketRecognizer.process(barCode);
		} catch (InvalidIDException e) {
			throw new CantReadBarCodeException("INVALID TICKET\nCan't read barcode");
		}

		TwentyFourTicket twentyFourTicket = tFTicketDAO.findByTicketCode(ticketCode);

		if (twentyFourTicket == null) {
			throw new CantFindTicketException("INVALID TICKET\nCan't find this ticket");
		}

		boolean isFirstTime = twentyFourTicket.getValidTime().equals(null);

		Timestamp timestamp = new Timestamp(new Date().getTime());
		long newValueOfDay = timestamp.getTime() + 24 * 60 * 60 * 1000;

		if (!isFirstTime && twentyFourTicket.getValidTime().after(timestamp)) {
			throw new NoLongerValidTicketException("INVALID TICKET\nThis ticket is no longer valid");
		}
		
		if (twentyFourTicket.isCheckedIn()) {
			throw new TicketOnlyCheckOutException("INVALID TICKET\nThis ticket just only for checkout"); 
		}

		TwentyFourTrip twentyFourTrip = new TwentyFourTrip();

		twentyFourTrip.setTicketId(twentyFourTicket.getId());
		twentyFourTrip.setIncomeStationId(station.getId());
		twentyFourTrip.setIncomeTime(timestamp);
		twentyFourTrip.setOnTrip(true);

		twentyFourTicket.setCheckedIn(true);
		
		if (isFirstTime) {
			Timestamp newValidTime = new Timestamp(newValueOfDay);
			twentyFourTicket.setValidTime(newValidTime);
		}
		
		if (saveTransactionForCheckIn(twentyFourTicket, twentyFourTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING TICKET...\n"
					+ "TicketID: " + twentyFourTicket.getId() + "\n"
					+ "ValidTime:" + twentyFourTicket.getValidTime();
			
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);
			
			return res;
		} else {
			throw new FailedTransactionException("Save Transaction Failed");
		}
	}
	
	/**
	 * 
	 * @param twentyFourTicket
	 * @param twentyFourTrip
	 * @return
	 */
	private boolean saveTransactionForCheckIn(TwentyFourTicket twentyFourTicket, TwentyFourTrip twentyFourTrip) {
		return tFTicketDAO.update(twentyFourTicket) && tFTripDAO.save(twentyFourTrip);
	}
	
	/**
	 * 
	 * @param barCode
	 * @param station
	 * @return
	 */
	@Override
	public DataResponse checkOut(String barCode, Station station) {
		String ticketCode;
		try {
			ticketCode = ticketRecognizer.process(barCode);
		} catch (InvalidIDException e) {
			throw new CantReadBarCodeException("INVALID TICKET\nCan't read barcode");
		}

		TwentyFourTicket twentyFourTicket = tFTicketDAO.findByTicketCode(ticketCode);

		if (twentyFourTicket == null) {
			throw new CantFindTicketException("INVALID TICKET\nCan't find this ticket");
		}
		
		if (!twentyFourTicket.isCheckedIn()) {
			throw new TicketOnlyCheckInException("INVALID TICKET\nThis ticket just only for checkin");
		}
		
		Timestamp timestamp = new Timestamp(new Date().getTime());
		TwentyFourTrip twentyFourTrip = tFTripDAO.findByTicketIdAndOnTrip(twentyFourTicket.getId(), true);
		
		twentyFourTrip.setOnTrip(false);
		twentyFourTrip.setOutcomeStationId(station.getId());
		twentyFourTrip.setOutcomeTime(timestamp);
		
		twentyFourTicket.setCheckedIn(false);
		
		if (saveTransactionForCheckOut(twentyFourTicket, twentyFourTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING TICKET...\n"
					+ "TicketID: " + twentyFourTicket.getId() + "\n"
					+ "ValidTime:" + twentyFourTicket.getValidTime();
			
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);
			
			return res;
		} else {
			throw new FailedTransactionException("Save Transaction Failed");
		}
	}
	
	/**
	 * save checkout's transaction
	 * @param TwentyFourTicket twenty-four hour ticket
	 * @param TwentyFourTrip twenty-four hour ticket's trip
	 * @return success or failed
	 */
	
	private boolean saveTransactionForCheckOut(TwentyFourTicket twentyFourTicket, TwentyFourTrip twentyFourTrip) {
		return tFTicketDAO.update(twentyFourTicket) && tFTripDAO.update(twentyFourTrip);
	}
}
