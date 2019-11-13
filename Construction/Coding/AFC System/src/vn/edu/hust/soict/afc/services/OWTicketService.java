package vn.edu.hust.soict.afc.services;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;

public interface OWTicketService {

	/**
	 * 
	 * @param barCode
	 * @param station
	 * @return
	 */
	DataResponse checkIn(String barCode, Station station);

	/**
	 * 
	 * @param barCode
	 * @param station
	 * @return
	 */
	DataResponse checkOut(String barCode, Station station);

}