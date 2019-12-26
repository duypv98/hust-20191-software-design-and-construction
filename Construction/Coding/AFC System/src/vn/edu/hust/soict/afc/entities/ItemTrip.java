package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

public abstract class ItemTrip {

	protected int id;
	protected int incomeStationId;
	protected Timestamp incomeTime;
	protected int outcomeStationId;
	protected Timestamp outcomeTime;
	protected boolean onTrip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
