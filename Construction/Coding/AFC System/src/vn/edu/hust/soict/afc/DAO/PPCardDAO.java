package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.PrepaidCard;
/**
 *
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description Prepaid Card DAO Interface
 */
public interface PPCardDAO {

	/**
	 * find prepaid card by card's id
	 * @param id card's id
	 * @return prepaid card
	 */
    PrepaidCard findById(String id);

    /**
     * update prepaid card
     * @param prepaidCard prepaid card
     * @return success or failed
     */
    boolean update(PrepaidCard prepaidCard);

    /**
     * find prepaid card by card code
     * @param cardCode card code
     * @return prepaid card
     */
    PrepaidCard findByCardCode(String cardCode);

}