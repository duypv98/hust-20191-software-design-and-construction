package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.TwentyFourTicket;

public interface TFTicketDAO {

	/**
	 * 
	 * @param id
	 * @return
	 */
	TwentyFourTicket findById(String id);

	boolean update(TwentyFourTicket twentyFourTicket);

	/**
	 * 
	 * @param ticketCode
	 * @return
	 */
	TwentyFourTicket findByTicketCode(String ticketCode);

}