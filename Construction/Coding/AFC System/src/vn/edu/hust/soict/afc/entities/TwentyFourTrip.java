package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public class TwentyFourTrip {
	private int id;
	private String ticketId;
	private int incomeStationId;
	private Timestamp incomeTime;
	private int outcomeStationId;
	private Timestamp outcomeTime;
	private boolean onTrip;
	
public TwentyFourTrip() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public int getIncomeStationId() {
		return incomeStationId;
	}

	public void setIncomeStationId(int incomeStationId) {
		this.incomeStationId = incomeStationId;
	}

	public Timestamp getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Timestamp incomeTime) {
		this.incomeTime = incomeTime;
	}
	
	public int getOutcomeStationId() {
		return outcomeStationId;
	}

	public void setOutcomeStationId(int outcomeStationId) {
		this.outcomeStationId = outcomeStationId;
	}

	public Timestamp getOutcomeTime() {
		return outcomeTime;
	}

	public void setOutcomeTime(Timestamp outcomeTime) {
		this.outcomeTime = outcomeTime;
	}

	public boolean isOnTrip() {
		return onTrip;
	}

	public void setOnTrip(boolean onTrip) {
		this.onTrip = onTrip;
	}
	
}

