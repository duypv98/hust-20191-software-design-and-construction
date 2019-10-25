/**
* @author Professor
* @created_at 24 Oct 2019
* @project_name AFC_System
* @lecturer Nguyen Thi Thu Trang
* @class_id 111589
*
* @description Java Project for Automated Fare Collection Simulation
*/
package vn.edu.hust.soict.afc.entities;

/**
 * @author Professor
 *
 */
public class Station {
	/**
	 * 
	 */
	private int stationId;
	private String name;
	private double distance;
	
	/**
	 * 
	 */
	public Station() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the stationId
	 */
	public int getStationId() {
		return stationId;
	}

	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

