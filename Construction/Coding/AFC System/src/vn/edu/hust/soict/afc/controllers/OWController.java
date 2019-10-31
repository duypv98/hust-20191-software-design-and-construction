/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
	 * @param ticketCode
	 * @throws SQLException
	 * @return OneWayTicket
	 */
	public static OneWayTicket getTicketInfo(String ticketCode) throws SQLException {
		OneWayTicket owt = null;
		try {
			owt = new OneWayTicket();
			owt = OWTicketService.getTicketInfo(ticketCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return owt;
	}

	/**
	 * 
	 * @param oneWayTicket
	 * @param station
	 * @return
	 * @throws SQLException
	 */
	public static boolean checkIn(OneWayTicket oneWayTicket, Station station) throws SQLException {
		Station embarkation = StationService.getStationInfo(oneWayTicket.getEmbarkationId());
		Station disembarkation = StationService.getStationInfo(oneWayTicket.getDisembarkationId());
		double currentDistance = station.getDistance();
		boolean pass = false;
		if (embarkation.getDistance() <= currentDistance && disembarkation.getDistance() >= currentDistance) {
			pass = true;
		}
		return pass;
	}
	
	public static boolean checkOut(OneWayTicket oneWayTicket, Station station) throws SQLException {
		if (oneWayTicket.isActivated() || !oneWayTicket.isCheckedIn()) {
			return false;
		}
		OneWayTrip oneWayTrip = OneWayTripService.getTripInfo(oneWayTicket.getId());
		Station embarkation = StationService.getStationInfo(oneWayTicket.getEmbarkationId());
		Station disembarkation = StationService.getStationInfo(oneWayTicket.getDisembarkationId());
		Station incomeStation = StationService.getStationInfo(oneWayTrip.getIncomeStationId());
		Station outcomeStation = station;
		Double realFare = getFare(incomeStation, outcomeStation);
		
		if (realFare <= getFare(embarkation, disembarkation)) {
			OneWayTripService.updateTrip(oneWayTrip.getId(), station.getId(), new Timestamp(new Date().getTime()), realFare, false);
//			OWTicketService.update status
			return true;
		}
		return false;
	}
	
	public static double getFare(Station startStation, Station endStation) {
		// TO DO
		return 3.0;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(checkOut(OWTicketService.getTicketInfo("b8094c1ccdff1df9"), StationService.getStationInfo(5)));
	}
}
