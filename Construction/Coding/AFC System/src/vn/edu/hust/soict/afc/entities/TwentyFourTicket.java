package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public class TwentyFourTicket {
	
	private String id;
	private String ticketCode;
	private Timestamp validTime;
	private boolean checkedIn;
	
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

	public String getId() {
		return id;
	}
	
	public void setId (String id) {
		this.id = id;
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
	
	public boolean isCheckedIn() {
		return checkedIn;
	}
	
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
}
