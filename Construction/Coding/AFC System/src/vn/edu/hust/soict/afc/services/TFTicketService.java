package vn.edu.hust.soict.afc.services;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;

/**
 * twenty-four hour ticket's service interface
 * @author hainn
 * @date Dec 7, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */

public interface TFTicketService {

	/**
	 * check-in
	 * @param barCode bar code
	 * @param incomeStation income station
	 * @return data response
	 */
	
	DataResponse checkIn(String barCode, Station station);

	/**
	 * check out
	 * @param barCode bar code
	 * @param outcomeStation outcome station
	 * @return data response
	 */
	
	DataResponse checkOut(String barCode, Station station);

}