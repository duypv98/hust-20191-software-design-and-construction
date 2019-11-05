package vn.edu.hust.soict.afc.DAO;

import vn.edu.hust.soict.afc.entities.PrepaidTrip;

public interface PPTripDAO {

    PrepaidTrip findByCardIdAndOnTrip(String cardId, boolean onTrip);

    boolean save(PrepaidTrip prepaidTrip);

    boolean update(PrepaidTrip prepaidTrip);

}