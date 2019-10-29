package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.PrepaidCardService;

public class PrePaidCardController {

    public static PrepaidCard getPrepaidCardInfo(String cardCode) throws SQLException {
        PrepaidCard prePaidCard = PrepaidCardService.getPrepaidCardInfo(cardCode);
        return prePaidCard;
    }

    public static boolean checkIn(PrepaidCard prepaidCard, Station station) throws SQLException {
        if(PrepaidCardService.saveTransactionCheckIn(prepaidCard.getId(), station.getId())) {
            return true;
        }
        return false;
    }
}
