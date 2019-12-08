/**
 * @author duypv
 * @date Nov 14, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.OneWayTicket;

/**
 * One-way ticket data access object
 * @author duypv
 *
 */
public interface OWTicketDAO {

	/**
	 * Get one-way ticket by ID
	 * @param id
	 * @return {OneWayTicket}
	 */
	OneWayTicket findById(String id);

	/**
	 * Update One-way ticket state
	 * @param ticketId
	 * @param checkedIn
	 * @param activated
	 */
	boolean update(OneWayTicket oneWayTicket);

	/**
	 * Get one-way ticket by Code
	 * @param ticketCode
	 * @return {OneWayTicket}
	 */
	OneWayTicket findByTicketCode(String ticketCode);

}