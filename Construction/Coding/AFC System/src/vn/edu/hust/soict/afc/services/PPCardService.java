package vn.edu.hust.soict.afc.services;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;

public interface PPCardService {

	DataResponse checkIn(String barCode, Station incomeStation);

	DataResponse checkOut(String barCode, Station outcomeStation);
}