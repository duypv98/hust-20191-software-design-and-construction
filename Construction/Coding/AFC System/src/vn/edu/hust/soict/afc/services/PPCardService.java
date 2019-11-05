package vn.edu.hust.soict.afc.services;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.Station;

public interface PPCardService {

    double BASE_DISTANCE = 5.0;
    double BASE_FARE = 1.9;
    double ADDED_DISTANCE = 2.0;
    double ADDED_FARE = 0.4;

    DataResponse process(String cardId, boolean isActCheckIn, Station selectedStation);

    DataResponse checkIn(Station selectedStation, PrepaidCard prepaidCard);

    DataResponse checkOut(Station selectedStation, PrepaidCard prepaidCard);

    double getFare(Station incomeStation, Station outcomeStation);

}