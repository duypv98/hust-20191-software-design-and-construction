package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.OneWayTicket;

public interface OWTicketDAO {

	/**
	 * 
	 * @param id
	 * @return
	 */
	OneWayTicket findById(String id);

	/**
	 * 
	 * @param ticketId
	 * @param checkedIn
	 * @param activated
	 */
	boolean update(OneWayTicket oneWayTicket);

	/**
	 * 
	 * @param ticketCode
	 * @return
	 */
	OneWayTicket findByTicketCode(String ticketCode);

}