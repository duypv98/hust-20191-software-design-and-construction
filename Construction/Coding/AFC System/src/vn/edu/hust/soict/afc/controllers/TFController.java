/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.entities.TwentyFourTicket;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;
import vn.edu.hust.soict.afc.services.StationService;
import vn.edu.hust.soict.afc.services.TFTicketService;
import vn.edu.hust.soict.afc.services.TwentyFourTripService;

/**
 * @author Professor
 *
 */
public class TFController {
	/**
	 * 
	 */
	public TFController() {

	}

	/**
	 * 
	 * @param ticketId
	 * @return a twentyfour ticket from id
	 */
	public TwentyFourTicket getTicket(String ticketId) {
		TwentyFourTicket tft = new TwentyFourTicket();
		tft = TFTicketService.getTicketInfo(ticketId);
		return tft;
	}
	
	public DataResponse process(String ticketId, boolean isCheckedIn) {
		DataResponse res = new DataResponse();
		TwentyFourTicket tft = getTicket(ticketId);
		if (tft == null) {
			res.setMessage("INVALID TICKET\nCan't find this ticket");
			res.setDisplayColor(Color.RED);
		} else {
			if (tft.isCheckedIn()) {
				res.setMessage("INVALID TICKET\n This ticket just only for checkout");
				res.setDisplayColor(Color.RED);
			}
		}
		return res;
	}
	
	public DataResponse checkIn (TwentyFourTicket twentyFourTicket) throws SQLException {
		DataResponse res = new DataResponse();
		
		if (twentyFourTicket.getValidTime().equals(0)) {
			res.setMessage("INVALID TICKET\nThis ticket is expired");
			res.setDisplayColor(Color.RED);
		} else {
			String message = "OPENING TICKET..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
		}
		return res;
	}
	
	public DataResponse checkOut (Station selectedStation, TwentyFourTicket twentyFourTicket) throws SQLException {
		DataResponse res = new DataResponse();
		
		TwentyFourTrip twentyFourTrip = TwentyFourTripService.getTripInfo(twentyFourTicket.getId());
		Station incomeStation = StationService.getStationInfo(twentyFourTrip.getIncomeStationId());
		Station outcomeStation = selectedStation;
		
		if (!twentyFourTicket.isCheckedIn()) {
			res.setMessage("INVALID TICKET\n This ticket just only for checkin ");
			res.setDisplayColor(Color.RED);
		} else {
			TwentyFourTripService.updateTrip(twentyFourTrip.getId(), outcomeStation.getId(), new Timestamp(new Date().getTime()), false);
			String message = "OPENING GATE..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
		}
			return res;
		} 
	
	}
