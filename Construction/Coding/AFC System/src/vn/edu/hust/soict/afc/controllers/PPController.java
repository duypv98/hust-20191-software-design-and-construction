package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.PrepaidTrip;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.PPCardService;
import vn.edu.hust.soict.afc.services.PPTripService;
import vn.edu.hust.soict.afc.services.StationService;

public class PPController {
	
	public final static double BASE_DISTANCE = 5.0;
	public final static double BASE_FARE = 1.9;
	public final static double ADDED_DISTANCE = 2.0;
	public final static double ADDED_FARE = 0.4;

	/**
	 * 
	 */
	public PPController() {
	}

	public static PrepaidCard getPrepaidCard(String cardId) {
		PrepaidCard prepaidCard = PPCardService.getPrepaidCardInfo(cardId);
		return prepaidCard;
	}

	public DataResponse process(String cardId, boolean isActCheckIn, Station selectedStation) {
		DataResponse res = new DataResponse();
		PrepaidCard ppc = getPrepaidCard(cardId);
		if (ppc != null) {
			if (ppc.getBalance() < BASE_FARE) {
				res.setMessage("INVALID CARD\nThe balance on the card is less than the base fare" + "\nCardID: "
						+ ppc.getId() + "\nBalance: " + ppc.getBalance() + " eur");
				res.setDisplayColor(Color.RED);
			} else {
				if (isActCheckIn) {
					res = checkIn(selectedStation, ppc);
				} else {
					res = checkOut(selectedStation, ppc);
				}
			}
		}
		return res;
	}

	public DataResponse checkIn(Station selectedStation, PrepaidCard prepaidCard) {
		DataResponse res = new DataResponse();
		if (prepaidCard.isCheckedIn()) {
			res.setMessage("INVALID CARD\nThis card just only for checkout");
			res.setDisplayColor(Color.RED);
		} else {
			if (prepaidCard.getBalance() < BASE_FARE) {
				res.setMessage("INVALID CARD\nNot enough balance\nYour Balance: " + prepaidCard.getBalance() + " eur "
						+ "\nIt must be equals or more than: " + BASE_FARE + " eur");
				res.setDisplayColor(Color.RED);
			} else {
				Timestamp incomeTime = new Timestamp(new Date().getTime());
				String message = "OPENING TICKET..." + "\nCardID: " + prepaidCard.getId() + "\nBalance: "
						+ prepaidCard.getBalance() + " eur";
				res.setMessage(message);
				res.setDisplayColor(Color.GREEN);
				res.setGateOpen(true);
				PPTripService.createTrip(prepaidCard.getId(), selectedStation.getId(), incomeTime, true);
				PPCardService.updateCard(prepaidCard.getId(), true, prepaidCard.getBalance());
			}
		}
		return res;
	}

	public DataResponse checkOut(Station selectedStation, PrepaidCard prepaidCard) {
		DataResponse res = new DataResponse();
		if (prepaidCard.isCheckedIn()) {
			PrepaidTrip currentTrip = PPTripService.getTripInfo(prepaidCard.getId());
			Station iStation = StationService.getStationInfo(currentTrip.getIncomeStationId());
			double realFare = getFare(iStation, selectedStation);
			Timestamp outcomeTime = new Timestamp(new Date().getTime());

			if (realFare > prepaidCard.getBalance()) {
				res.setMessage("INVALID CARD\nNot enough balance\nYour Balance: " + prepaidCard.getBalance() + " eur "
						+ "\nBut you have to pay: " + realFare + " eur");
				res.setDisplayColor(Color.RED);
			} else {
				String message = "OPENING CARD\n" + "ID: " + prepaidCard.getId() + ", balance: "
						+ prepaidCard.getBalance() + " eur\n" + "In reality: " + realFare + " eur";
				res.setMessage(message);
				res.setDisplayColor(Color.GREEN);
				double newBalance = prepaidCard.getBalance() - realFare;
				PPTripService.updateTrip(currentTrip.getId(), selectedStation.getId(), outcomeTime, realFare, false);
				PPCardService.updateCard(prepaidCard.getId(), false, newBalance);
			}
		} else {
			res.setMessage("INVALID TICKET\nThis card just only for checkin");
			res.setDisplayColor(Color.RED);
		}
		return res;
	}

	public double getFare(Station incomeStation, Station outcomeStation) {
		double distance = Math.abs(outcomeStation.getDistance() - incomeStation.getDistance());
		if (distance < BASE_DISTANCE) {
			return BASE_FARE;
		}

		double numberOfAddedDistance = Math.ceil((distance - BASE_DISTANCE) / ADDED_DISTANCE);
		return BASE_FARE + ADDED_FARE * numberOfAddedDistance;
	}
}
