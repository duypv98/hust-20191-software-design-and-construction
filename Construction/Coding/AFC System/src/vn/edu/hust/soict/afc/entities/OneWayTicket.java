/**
 * 
 */
package vn.edu.hust.soict.afc.entities;

/**
 * @author Professor
 *
 */
public class OneWayTicket {

	private String id;
	private String ticketCode;
	private int embarkationId;
	private int disembarkationId;
	private double fare;
	private boolean checkedIn;
	private boolean activated;

	/**
	 * 
	 */
	public OneWayTicket() {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the embarkationId
	 */
	public int getEmbarkationId() {
		return embarkationId;
	}

	/**
	 * @param embarkationId the embarkationId to set
	 */
	public void setEmbarkationId(int embarkationId) {
		this.embarkationId = embarkationId;
	}

	/**
	 * @return the disembarkationId
	 */
	public int getDisembarkationId() {
		return disembarkationId;
	}

	/**
	 * @param disembarkationId the disembarkationId to set
	 */
	public void setDisembarkationId(int disembarkationId) {
		this.disembarkationId = disembarkationId;
	}

	/**
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
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
