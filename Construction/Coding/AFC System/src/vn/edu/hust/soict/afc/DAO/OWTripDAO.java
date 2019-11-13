package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.OneWayTrip;

public interface OWTripDAO {

	/**
	 * 
	 * @param ticketId
	 * @return oneway trip from ticketId
	 */
	OneWayTrip findByTicketId(String ticketId);
	/**
	 * 
	 * @param oneWayTrip
	 * @return
	 */
	boolean save(OneWayTrip oneWayTrip);

	/**
	 * 
	 * @param id
	 * @param outcomeStationId
	 * @param outcomeTime
	 * @param realFare
	 * @param onTrip
	 */
	boolean update(OneWayTrip oneWayTrip);

}