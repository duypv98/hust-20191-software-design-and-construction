/**
 * 
 */
package vn.edu.hust.soict.afc.entities;

/**
 * @author Professor
 *
 */
public class Station {

	private int id;
	private String stationName;
	private double distance;

	/**
	 * 
	 */
	public Station() {
	}

	/**
	 * 
	 * @param id
	 * @param stationName
	 * @param distance
	 */
	public Station(int id, String stationName, double distance) {
		this.id = id;
		this.stationName = stationName;
		this.distance = distance;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

}
