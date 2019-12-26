package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public class TwentyFourTicket extends Item {

	private String ticketCode;
	private Timestamp validTime;

	/**
	 *
	 */

	public TwentyFourTicket() {

	}

	public TwentyFourTicket(String id, String ticketCode, Timestamp validTime, boolean checkedIn) {
		this.id = id;
		this.ticketCode = ticketCode;
		this.validTime = validTime;
		this.checkedIn = checkedIn;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode (String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public Timestamp getValidTime() {
		return validTime;
	}

	public void setValidTime (Timestamp validTime) {
		this.validTime = validTime;
	}
}
