/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.OWTicketService;

/**
 * @author Professor
 *
 */
public class OWController {

	/**
	 * @param ticketCode
	 * @throws SQLException
	 * @return OneWayTicket
	 */
	public OneWayTicket getTicketInfo(String ticketCode) throws SQLException {
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
	 * @return
	 */
	public String findTicket(OneWayTicket oneWayTicket, Station station) {
		String message = null;
		if (oneWayTicket == null) {
			message = "Can't find this ticket";
		} else {
			message = checkIn(oneWayTicket, station);
		}
		return message;

	}

	/**
	 * 
	 * @param embarkationId
	 * @param disembarkationId
	 * @param station
	 * @return
	 */
	public String checkIn(OneWayTicket oneWayTicket, Station station) {
		String message = null;
		if (oneWayTicket.isActivated() == true) {
			message = "This ticket is already used";
		} else {
			if (oneWayTicket.getEmbarkationId() >= station.getId()
					&& oneWayTicket.getDisembarkationId() <= station.getId()) {
				message = "OK";
			} else {
				message = "";
			}

		}
		return null;
	}
}
