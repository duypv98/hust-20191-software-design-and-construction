/**
 *
 */
package vn.edu.hust.soict.afc.entities;

import java.sql.Timestamp;

/**
 * @author Professor
 *
 */
public class PrepaidTrip extends ItemTrip {

	private String cardId;
	private double realFare;

	/**
	 *
	 */
	public PrepaidTrip() {

	}

	public PrepaidTrip(int id, String cardId, int incomeStationId, Timestamp incomeTime, int outcomeStationId,
			Timestamp outcomeTime, double realFare, boolean onTrip) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.incomeStationId = incomeStationId;
		this.incomeTime = incomeTime;
		this.outcomeStationId = outcomeStationId;
		this.outcomeTime = outcomeTime;
		this.realFare = realFare;
		this.onTrip = onTrip;
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
	 * @return the realFare
	 */
	public double getRealFare() {
		return realFare;
	}

	/**
	 * @param realFare the realFare to set
	 */
	public void setRealFare(double realFare) {
		this.realFare = realFare;
	}
}
