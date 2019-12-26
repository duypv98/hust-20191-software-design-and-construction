package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public class TwentyFourTrip extends ItemTrip {
	private String ticketId;

	public TwentyFourTrip() {

	}

	public TwentyFourTrip(int id, String ticketId, int incomeStationId, Timestamp incomeTime, int outcomeStationId,
			Timestamp outcomeTime, boolean onTrip) {
		super();
		this.id = id;
		this.ticketId = ticketId;
		this.incomeStationId = incomeStationId;
		this.incomeTime = incomeTime;
		this.outcomeStationId = outcomeStationId;
		this.outcomeTime = outcomeTime;
		this.onTrip = onTrip;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
}

