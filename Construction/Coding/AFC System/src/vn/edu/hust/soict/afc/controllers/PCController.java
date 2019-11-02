package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.PCService;

public class PCController {

	private static final double BASE_FARE = 1.9;

	/**
	 * 
	 */
	public PCController() {

	}

	public PrepaidCard getPrepaidCardInfo(String cardCode) throws SQLException {
		PrepaidCard prePaidCard = PCService.getPrepaidCardInfo(cardCode);
		return prePaidCard;
	}

	public boolean checkIn(PrepaidCard prepaidCard, Station station) throws SQLException {
		if (PCService.saveTransactionCheckIn(prepaidCard.getId(), station.getId())) {
			return true;
		}
		return false;
	}

	public DataResponse process(String cardCode, AppState appState) {
		DataResponse res = new DataResponse();
		PrepaidCard prepaidCard = null;
		try {
			prepaidCard = PCService.getPrepaidCardInfo(cardCode);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		if (prepaidCard == null) {
			res.setMessage("INVALID CARD\nCan't find this ticket");
			res.setDisplayColor(Color.RED);
		} else {
			if (prepaidCard.getBalance() < BASE_FARE) {
				res.setMessage("INVALID CARD\nThe balance on the card is less than the base fare" + "\nCardID: "
						+ prepaidCard.getId() + "\nBalance: " + prepaidCard.getBalance() + " eur");
				res.setDisplayColor(Color.RED);
			} else {
				if (appState.isActCheckIn()) {
					try {
						if (prepaidCard.isCheckedIn()) {
							res.setMessage("INVALID TICKET\nThis card just only for checkout");
							res.setDisplayColor(Color.RED);
						} else {
							if (checkIn(prepaidCard, appState.getSelectedStation())) {
								String message = "OPENING TICKET..." + "\nCardID: " + prepaidCard.getId()
										+ "\nBalance: " + prepaidCard.getBalance() + " eur";
								res.setMessage(message);
								res.setDisplayColor(Color.GREEN);
							} else {
								res.setMessage("CHECKIN FAILED");
								res.setDisplayColor(Color.RED);
							}
						}
					} catch (SQLException e) {
						/* Ignore */
					}
				}
			}
		}
		return res;
	}
}
