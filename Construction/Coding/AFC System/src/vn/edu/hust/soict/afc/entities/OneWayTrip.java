package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public class OneWayTrip extends ItemTrip {
	private String ticketId;
	private double realFare;

	public OneWayTrip() {

	}

	public OneWayTrip(int id, String ticketId, int incomeStationId, Timestamp incomeTime, int outcomeStationId,
			Timestamp outcomeTime, double realFare, boolean onTrip) {
		super();
		this.id = id;
		this.ticketId = ticketId;
		this.incomeStationId = incomeStationId;
		this.incomeTime = incomeTime;
		this.outcomeStationId = outcomeStationId;
		this.outcomeTime = outcomeTime;
		this.realFare = realFare;
		this.onTrip = onTrip;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public double getRealFare() {
		return realFare;
	}

	public void setRealFare(double realFare) {
		this.realFare = realFare;
	}

}
