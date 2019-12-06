package vn.edu.hust.soict.afc.services;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;

/**
 * prepaid card's service interface
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public interface PPCardService {

	/**
	 * check-in
	 * @param barCode bar code
	 * @param incomeStation income station
	 * @return data response
	 */
	DataResponse checkIn(String barCode, Station incomeStation);

	/**
	 * check out
	 * @param barCode bar code
	 * @param outcomeStation outcome station
	 * @return data response
	 */
	DataResponse checkOut(String barCode, Station outcomeStation);
}