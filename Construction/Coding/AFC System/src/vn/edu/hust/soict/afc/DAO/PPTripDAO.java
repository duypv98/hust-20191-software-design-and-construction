package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.PrepaidTrip;

/**
 *
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description Prepaid card's trip DAO Interface
 */
public interface PPTripDAO {

	/**
	 * find prepaid card's trip by card's id and on trip
	 * @param cardId card's id
	 * @param onTrip on trip
	 * @return trip of prepaid card
	 */
    PrepaidTrip findByCardIdAndOnTrip(String cardId, boolean onTrip);

    /**
     * save prepaid card's trip
     * @param prepaidTrip prepaid card's trip
     * @return success or failed
     */
    boolean save(PrepaidTrip prepaidTrip);

    /**
     * update prepaid card's trip
     * @param prepaidTrip prepaid card's trip
     * @return success or failed
     */
    boolean update(PrepaidTrip prepaidTrip);

}