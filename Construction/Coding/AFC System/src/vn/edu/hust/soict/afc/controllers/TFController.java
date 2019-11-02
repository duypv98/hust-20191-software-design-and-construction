/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.entities.TwentyFourTicket;
import vn.edu.hust.soict.afc.services.TFTicketService;
import vn.edu.hust.soict.afc.services.TFTripService;

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
	 * @return a twenty four-hour ticket from id
	 */
	public TwentyFourTicket getTicket(String ticketId) {
		TwentyFourTicket tft = new TwentyFourTicket();
		tft = TFTicketService.getTicketInfo(ticketId);
		return tft;
	}
	
	public DataResponse process(String ticketId, boolean isActCheckedIn, Station selectedStation) {
		DataResponse res = new DataResponse();
		TwentyFourTicket tft = getTicket(ticketId);
		if (tft == null) {
			res.setMessage("INVALID TICKET\nCan't find this ticket");
			res.setDisplayColor(Color.RED);
		} else {
			if (isActCheckedIn) {
				res = checkIn(selectedStation, tft);
			} else {
				res = checkOut(selectedStation, tft);
			}
		}
		return res;
	}
	
	public DataResponse checkIn (Station selectedStation, TwentyFourTicket twentyFourTicket) {
		DataResponse res = new DataResponse();
		Timestamp incomeTime = new Timestamp(new Date().getTime());
		if (twentyFourTicket.isCheckedIn()) {
			if (twentyFourTicket.getValidTime().equals(null) || (twentyFourTicket.getValidTime() != null && incomeTime.before(twentyFourTicket.getValidTime()))) {
				String message = "OPENING TICKET..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
				res.setMessage(message);
				res.setDisplayColor(Color.GREEN);
				res.setGateOpen(true);
				if (twentyFourTicket.getValidTime().equals(null)) {
					TFTicketService.updateTicketFirstTime(twentyFourTicket.getId(), incomeTime, true);
				} else {
					TFTicketService.updateTicket(twentyFourTicket.getId(), true);
				}
				TFTripService.createTrip(twentyFourTicket.getId(), selectedStation.getId(), incomeTime, true);
			}
			if (incomeTime.after(twentyFourTicket.getValidTime())) {
				res.setMessage("INVALID TICKET\nThis ticket is no longer valid");
				res.setDisplayColor(Color.RED);
			}
		} else {
			res.setMessage("INVALID TICKET\nThis ticked just only for checkout");
			res.setDisplayColor(Color.RED);
		}
		return res;
	}
	
	public DataResponse checkOut (Station selectedStation, TwentyFourTicket twentyFourTicket) {
		DataResponse res = new DataResponse();
		
		if (twentyFourTicket.isCheckedIn()) {
			res.setMessage("INVALID TICKET\n This ticket just only for checkin ");
			res.setDisplayColor(Color.RED);
		} else {
			Timestamp outcomeTime = new Timestamp(new Date().getTime());
			TFTripService.updateTrip(twentyFourTicket.getId(), selectedStation.getId(), outcomeTime);
			TFTicketService.updateTicket(twentyFourTicket.getId(), false);
			String message = "OPENING GATE..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
		}
			return res;
		} 
	
	}
