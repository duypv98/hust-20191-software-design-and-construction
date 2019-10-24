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
public class OneWayTicket {
	/**
	 * 
	 */
	private String ticketId;
	private String ticketCode;
	private Station embarkation;
	private Station disembarkation;
	private boolean checkedIn;
	private int fare;
	
	/**
	 * 
	 */
	public OneWayTicket() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * @return the ticketCode
	 */
	public String getTicketCode() {
		return ticketCode;
	}

	/**
	 * @param ticketCode the ticketCode to set
	 */
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	/**
	 * @return the embarkation
	 */
	public Station getEmbarkation() {
		return embarkation;
	}

	/**
	 * @param embarkation the embarkation to set
	 */
	public void setEmbarkation(Station embarkation) {
		this.embarkation = embarkation;
	}

	/**
	 * @return the disembarkation
	 */
	public Station getDisembarkation() {
		return disembarkation;
	}

	/**
	 * @param disembarkation the disembarkation to set
	 */
	public void setDisembarkation(Station disembarkation) {
		this.disembarkation = disembarkation;
	}

	/**
	 * @return the checkedIn
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * @param checkedIn the checkedIn to set
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * @return the fare
	 */
	public int getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(int fare) {
		this.fare = fare;
	}

}

