/**
 * @author duycv
 * @date Nov 14, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.OneWayTrip;

public interface OWTripDAO {

	/**
	 * Find one-way trip by ticketID
	 * @param ticketId
	 * @return {OneWayTrip} from ticketId
	 */
	OneWayTrip findByTicketId(String ticketId);
	/**
	 * Save new one-way trip
	 * @param oneWayTrip
	 */
	boolean save(OneWayTrip oneWayTrip);

	/**
	 * Update one-way trip state
	 * @param id
	 * @param outcomeStationId
	 * @param outcomeTime
	 * @param realFare
	 * @param onTrip
	 */
	boolean update(OneWayTrip oneWayTrip);

}