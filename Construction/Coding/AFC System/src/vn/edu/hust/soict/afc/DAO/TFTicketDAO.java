package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.TwentyFourTicket;

/**
*
* @author hainn
* @date Dec 7, 2019
* @project AFC System
* @lecturer Nguyen Thi Thu Trang
* @class 111589
*
* @description Twenty-Four Hour Ticket DAO Interface
*/

public interface TFTicketDAO {
	
	/**
	 * find twenty-four hour ticket by ticket's id
	 * @param id ticket's id
	 * @return twenty-four hour ticket
	 */

	TwentyFourTicket findById(String id);
	
	/**
     * update twenty-four hour ticket
     * @param TwentyFourTicket ticket code
     * @return success or failed
     */

	boolean update(TwentyFourTicket twentyFourTicket);
	
	/**
     * find twenty-four hour ticket by ticket code
     * @param ticketCode ticket code
     * @return twenty-four hour ticket
     */

	TwentyFourTicket findByTicketCode(String ticketCode);

}