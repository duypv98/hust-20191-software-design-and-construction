/**
 * @author duypv
 * @date Dec 6, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.PrepaidCard;
/**
 * Prepaid Card DAO Interface
 * @author duycv
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