/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.OneWayTrip;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.OWTicketService;
import vn.edu.hust.soict.afc.services.OneWayTripService;
import vn.edu.hust.soict.afc.services.StationService;

/**
 * @author Professor
 *
 */
public class OWController {
	/**
	 * 
	 */
	public OWController() {

	}

	/**
	 * 
	 * @param ticketId
	 * @return a oneway ticket from id
	 */
	public OneWayTicket getTicket(String ticketId) {
		OneWayTicket owt = new OneWayTicket();
		owt = OWTicketService.getTicketInfo(ticketId);
		return owt;
	}

	/**
	 * 
	 * @param selectedStation
	 * @param oneWayTicket
	 * @return response of checkin operation
	 * @throws SQLException
	 */
	public DataResponse checkIn(Station selectedStation, OneWayTicket oneWayTicket) throws SQLException {
		DataResponse res = new DataResponse();
		double currentPos = selectedStation.getDistance();
		if (oneWayTicket.isCheckedIn()) {
			res.setMessage("INVALID TICKET\n This ticket just only for checkout");
			res.setDisplayColor(Color.RED);
		} else {
			Station embarkation = StationService.getStationInfo(oneWayTicket.getEmbarkationId());
			Station disembarkation = StationService.getStationInfo(oneWayTicket.getDisembarkationId());
			double distance = disembarkation.getDistance() - embarkation.getDistance();
			if (embarkation.getDistance() <= currentPos && currentPos <= disembarkation.getDistance()) {
				String message = "OPENING TICKET..." + "\nTicketID: " + oneWayTicket.getId() + "\nDistance: " + distance
						+ " km" + "\nFare: " + oneWayTicket.getFare() + " eur";
				res.setMessage(message);
				res.setDisplayColor(Color.GREEN);
			} else {
				res.setMessage("INVALID TICKET\nWRONG STATION TO GO !");
				res.setDisplayColor(Color.RED);
			}
		}
		return res;
	}

	/**
	 * 
	 * @param ticketId
	 * @param isActCheckIn
	 * @param selectedStation
	 * @return response for process on ticket
	 */
	public DataResponse process(String ticketId, boolean isActCheckIn, Station selectedStation) {
		DataResponse res = new DataResponse();
		OneWayTicket owt = getTicket(ticketId);
		if (owt == null) {
			res.setMessage("INVALID TICKET\nCan't find this ticket");
			res.setDisplayColor(Color.RED);
		} else {
			if (owt.isActivated()) {
				res.setMessage("INVALID TICKET\nThis ticket is no longer valid");
				res.setDisplayColor(Color.RED);
			} else {
				if (isActCheckIn) {
					try {
						res = checkIn(selectedStation, owt);
					} catch (SQLException e) {
						/* Ignore */
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @param selectedStation
	 * @param oneWayTicket
	 * @return response of checkout operation
	 * @throws SQLException
	 */
	public DataResponse checkOut(Station selectedStation, OneWayTicket oneWayTicket) throws SQLException {
		DataResponse res = new DataResponse();
		if (!oneWayTicket.isCheckedIn()) {
			res.setMessage("INVALID ONE WAY TICKET\n "
					+ "ID: " + oneWayTicket.getId() + "\n"
					+ "This ticket just only for checkin");
			res.setDisplayColor(Color.RED);
			return res;
		} 
		
		OneWayTrip oneWayTrip = OneWayTripService.getTripInfo(oneWayTicket.getId());
		Station incomeStation = StationService.getStationInfo(oneWayTrip.getIncomeStationId());
		Station outcomeStation = selectedStation;
		Double realFare = getFare(incomeStation, outcomeStation);
		Double fareOnTicket = oneWayTicket.getFare();
		
		String message = "";
		if (realFare > fareOnTicket) {
			message = "INVALID ONE WAY TICKET\n"
					+ "ID: " + oneWayTicket.getId() + ", balance: " + fareOnTicket + " eur\n"
					+ "Not enough balance, expected: " + realFare + " eur";
			res.setMessage(message);
			res.setDisplayColor(Color.RED);
			return res;
		} 
		
		OneWayTripService.updateTrip(oneWayTrip.getId(), outcomeStation.getId(), new Timestamp(new Date().getTime()), realFare, false);
		message = "OPENING GATE BY ONE WAY TICKET...\n"
				+ "ID: " + oneWayTicket.getId() + ", balance: " + fareOnTicket + " eur\n"
				+ "In reality: " + realFare + " eur";
//		OWTicketService.update status
		res.setMessage(message);
		res.setDisplayColor(Color.GREEN);
		return res;
	}
	
	public static double getFare(Station startStation, Station endStation) {
		// TO DO
		return 3.0;
	}
}
