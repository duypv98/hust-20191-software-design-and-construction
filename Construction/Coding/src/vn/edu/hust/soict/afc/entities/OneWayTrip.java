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

import java.util.Date;

/**
 * @author Professor
 *
 */
public class OneWayTrip {
	/**
	 * 
	 */
	private int trip_id;
	private OneWayTicket ticket;
	private Station incomeStation;
	private Station outcomeStation;
	private Date incomeTime;
	private Date outcomeTime;
	private int realFare;
	private boolean activated;
	
	/**
	 * 
	 */
	public OneWayTrip() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the trip_id
	 */
	public int getTrip_id() {
		return trip_id;
	}

	/**
	 * @param trip_id the trip_id to set
	 */
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * @return the ticket
	 */
	public OneWayTicket getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(OneWayTicket ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the incomeStation
	 */
	public Station getIncomeStation() {
		return incomeStation;
	}

	/**
	 * @param incomeStation the incomeStation to set
	 */
	public void setIncomeStation(Station incomeStation) {
		this.incomeStation = incomeStation;
	}

	/**
	 * @return the outcomeStation
	 */
	public Station getOutcomeStation() {
		return outcomeStation;
	}

	/**
	 * @param outcomeStation the outcomeStation to set
	 */
	public void setOutcomeStation(Station outcomeStation) {
		this.outcomeStation = outcomeStation;
	}

	/**
	 * @return the incomeTime
	 */
	public Date getIncomeTime() {
		return incomeTime;
	}

	/**
	 * @param incomeTime the incomeTime to set
	 */
	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	/**
	 * @return the outcomeTime
	 */
	public Date getOutcomeTime() {
		return outcomeTime;
	}

	/**
	 * @param outcomeTime the outcomeTime to set
	 */
	public void setOutcomeTime(Date outcomeTime) {
		this.outcomeTime = outcomeTime;
	}

	/**
	 * @return the realFare
	 */
	public int getRealFare() {
		return realFare;
	}

	/**
	 * @param realFare the realFare to set
	 */
	public void setRealFare(int realFare) {
		this.realFare = realFare;
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}

