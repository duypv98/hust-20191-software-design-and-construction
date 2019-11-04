/**
 * 
 */
package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

/**
 * @author Professor
 *
 */
public class PrepaidTrip {
	
	private int id;
	private String cardId;
	private int incomeStationId;
	private Timestamp incomeTime;
	private int outcomeStationId;
	private Timestamp outcomeTime;
	private Double realFare;
	private boolean onTrip;
	
	/**
	 * 
	 */
	public PrepaidTrip() {
		this.outcomeStationId = 0;
		this.outcomeTime = null;
		this.realFare = 0.0;
		this.onTrip = true;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cardId
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the incomeStationId
	 */
	public int getIncomeStationId() {
		return incomeStationId;
	}

	/**
	 * @param incomeStationId the incomeStationId to set
	 */
	public void setIncomeStationId(int incomeStationId) {
		this.incomeStationId = incomeStationId;
	}

	/**
	 * @return the incomeTime
	 */
	public Timestamp getIncomeTime() {
		return incomeTime;
	}

	/**
	 * @param incomeTime the incomeTime to set
	 */
	public void setIncomeTime(Timestamp incomeTime) {
		this.incomeTime = incomeTime;
	}

	/**
	 * @return the outcomeStationId
	 */
	public int getOutcomeStationId() {
		return outcomeStationId;
	}

	/**
	 * @param outcomeStationId the outcomeStationId to set
	 */
	public void setOutcomeStationId(int outcomeStationId) {
		this.outcomeStationId = outcomeStationId;
	}

	/**
	 * @return the outcomeTime
	 */
	public Timestamp getOutcomeTime() {
		return outcomeTime;
	}

	/**
	 * @param outcomeTime the outcomeTime to set
	 */
	public void setOutcomeTime(Timestamp outcomeTime) {
		this.outcomeTime = outcomeTime;
	}

	/**
	 * @return the realFare
	 */
	public Double getRealFare() {
		return realFare;
	}

	/**
	 * @param realFare the realFare to set
	 */
	public void setRealFare(Double realFare) {
		this.realFare = realFare;
	}

	/**
	 * @return the onTrip
	 */
	public boolean isOnTrip() {
		return onTrip;
	}

	/**
	 * @param onTrip the onTrip to set
	 */
	public void setOnTrip(boolean onTrip) {
		this.onTrip = onTrip;
	}
}
