package vn.edu.hust.soict.afc.DAO;

import java.util.List;

import vn.edu.hust.soict.afc.entities.Station;

public interface StationDAO {

	/**
	 * find Station by station's id
	 * @param id station's id
	 * @return station
	 */
	Station findById(int id);

	/**
	 * find all station
	 * @return list stations
	 */
	List<Station> findAll();

}