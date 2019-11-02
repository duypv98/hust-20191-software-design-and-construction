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
		if (isActCheckedIn) {
			res = checkIn(selectedStation, tft);
		} else {
			res = checkOut(selectedStation, tft);
		}
		return res;
	}
	
	public DataResponse checkIn (Station selectedStation, TwentyFourTicket twentyFourTicket) {
		DataResponse res = new DataResponse();
		boolean isFirstTime = twentyFourTicket.getValidTime().equals(null);
		Timestamp incomeTime = new Timestamp(new Date().getTime());
		Timestamp validTime = new Timestamp(incomeTime.getTime() + 24*60*60*1000);
		if (twentyFourTicket.isCheckedIn()) {
			res.setMessage("INVALID TICKET\nThis ticket just only for checkout");
		} else {
			if (isFirstTime || incomeTime.before(twentyFourTicket.getValidTime())) {
				String message = "OPENING TICKET..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
				res.setMessage(message);
				res.setDisplayColor(Color.GREEN);
				if (isFirstTime) {
					TFTicketService.updateFirstTime(twentyFourTicket.getId(), validTime, true);;
				} else {
					TFTicketService.updateTicket(twentyFourTicket.getId(), true);;
				}
				TFTripService.createTrip(twentyFourTicket.getId(), selectedStation.getId(), incomeTime, true);
			} else if (incomeTime.after(twentyFourTicket.getValidTime())) {
				res.setMessage("INVALID TICKET\nThis ticket is no longer valid");
				res.setDisplayColor(Color.RED);
			}
		}
		return res;
	}
	
	public DataResponse checkOut (Station selectedStation, TwentyFourTicket twentyFourTicket) {
		DataResponse res = new DataResponse();
		Timestamp outcomeTime = new Timestamp(new Date().getTime());
		
		if (!twentyFourTicket.isCheckedIn()) {
			res.setMessage("INVALID TICKET\n This ticket just only for checkin");
			res.setDisplayColor(Color.RED);
		} else {
			
			String message = "OPENING GATE..." + "\nTicketID: " + twentyFourTicket.getId() + "\nValid Time: " + twentyFourTicket.getValidTime();
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			TFTripService.updateTrip(twentyFourTicket.getId(), selectedStation.getId(), outcomeTime, false);
			TFTicketService.updateTicket(twentyFourTicket.getId(), false);
		}
			return res;
		} 
	
	}
