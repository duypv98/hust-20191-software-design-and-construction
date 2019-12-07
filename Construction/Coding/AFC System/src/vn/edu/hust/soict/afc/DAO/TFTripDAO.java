package vn.edu.hust.soict.afc.DAO;

import java.sql.SQLException;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;

/**
*
* @author hainn
* @date Dec 7, 2019
* @project AFC System
* @lecturer Nguyen Thi Thu Trang
* @class 111589
*
* @description Twenty-Four Hour ticket's trip DAO Interface
*/

public interface TFTripDAO {

	/**
	 * find Twenty-Four Hour ticket's trip by ticket's id and on trip
	 * @param ticketId ticket's id
	 * @param onTrip on trip
	 * @return trip of twenty-four hour ticket
	 */
	
	TwentyFourTrip findByTicketIdAndOnTrip(String ticketId, boolean onTrip);

	/**
     * save twenty-four hour ticket's trip
     * @param TwentyFourTrip twenty-four hour ticket's trip
     * @return success or failed
     */
	
	boolean save(TwentyFourTrip twentyFourTrip);

	/**
     * update twenty-four hour ticket's trip
     * @param TwentyFourTrip twenty-four hour ticket's trip
     * @return success or failed
     */
	
	boolean update(TwentyFourTrip twentyFourTrip);

}