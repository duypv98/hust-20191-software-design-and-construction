/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.OWTicketService;
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
}
