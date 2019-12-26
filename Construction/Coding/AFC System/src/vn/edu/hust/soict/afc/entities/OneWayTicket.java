/**
 *
 */
package vn.edu.hust.soict.afc.entities;

/**
 * @author Professor
 *
 */
public class OneWayTicket extends Item {

	private String ticketCode;
	private int embarkationId;
	private int disembarkationId;
	private double fare;
	private boolean activated;

	/**
	 *
	 */
	public OneWayTicket() {

	}

	/**
	 *
	 * @param id
	 * @param ticketCode
	 * @param embarkationId
	 * @param disembarkationId
	 * @param fare
	 * @param checkedIn
	 * @param activated
	 */
	public OneWayTicket(String id, String ticketCode, int embarkationId, int disembarkationId, double fare,
			boolean checkedIn, boolean activated) {
		this.id = id;
		this.ticketCode = ticketCode;
		this.embarkationId = embarkationId;
		this.disembarkationId = disembarkationId;
		this.fare = fare;
		this.checkedIn = checkedIn;
		this.activated = activated;
	}

	public boolean isComparedWith(OneWayTicket ticket) {
		return this.getId() == ticket.getId() &&
			   this.getTicketCode() == ticket.getTicketCode() &&
			   this.getEmbarkationId() == ticket.getEmbarkationId() &&
			   this.getDisembarkationId() == ticket.getDisembarkationId() &&
			   this.getFare() == ticket.getFare() &&
			   this.isCheckedIn() == ticket.isCheckedIn() &&
			   this.isActivated() == ticket.isActivated();
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
