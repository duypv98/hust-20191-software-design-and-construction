package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.PrepaidCard;

public interface PPCardDAO {

    PrepaidCard findById(String id);

    boolean update(PrepaidCard prepaidCard);

    PrepaidCard findByCardCode(String cardCode);

}