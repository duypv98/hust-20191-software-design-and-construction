package vn.edu.hust.soict.afc.services;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.DAO.PPCardDAO;
import vn.edu.hust.soict.afc.DAO.PPCardDAOImpl;
import vn.edu.hust.soict.afc.DAO.PPTripDAO;
import vn.edu.hust.soict.afc.DAO.PPTripDAOImpl;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.PrepaidTrip;
import vn.edu.hust.soict.afc.entities.Station;

public class PPCardServiceImpl implements PPCardService {

	private PPCardDAO pPCardDAO = new PPCardDAOImpl();
	private PPTripDAO pPTripDAO = new PPTripDAOImpl();

	/**
	 *
	 */
	public PPCardServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see vn.edu.hust.soict.afc.services.PPCardService#process(java.lang.String,
	 * boolean, vn.edu.hust.soict.afc.entities.Station)
	 */
	@Override
	public DataResponse process(String cardCode, boolean isActCheckIn, Station selectedStation) {
		DataResponse res = new DataResponse();
		PrepaidCard prepaidCard = pPCardDAO.findByCardCode(cardCode);
		if (prepaidCard == null) {
			res.setMessage("INVALID CARD\nCan't find this card");
			res.setDisplayColor(Color.RED);
		} else {
			if (isActCheckIn) {
				res = checkIn(selectedStation, prepaidCard);
			} else {
				res = checkOut(selectedStation, prepaidCard);
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.services.PPCardService#checkIn(vn.edu.hust.soict.afc.
	 * entities.Station, vn.edu.hust.soict.afc.entities.PrepaidCard)
	 */
	@Override
	public DataResponse checkIn(Station selectedStation, PrepaidCard prepaidCard) {
		DataResponse res = new DataResponse();
		if (prepaidCard.getBalance() < BASE_FARE) {
			res.setMessage("INVALID CARD\nThe balance on the card is less than the base fare" + "\nCardID: "
					+ prepaidCard.getId() + "\nBalance: " + prepaidCard.getBalance() + " eur");
			res.setDisplayColor(Color.RED);
		} else {
			if (prepaidCard.isCheckedIn()) {
				res.setMessage("INVALID CARD\nThis card just only for checkout");
				res.setDisplayColor(Color.RED);
			} else {
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());

				PrepaidTrip prepaidTrip = new PrepaidTrip();
				prepaidTrip.setCardId(prepaidCard.getId());
				prepaidTrip.setIncomeStationId(selectedStation.getId());
				prepaidTrip.setIncomeTime(timestamp);
				prepaidTrip.setOnTrip(true);

				prepaidCard.setCheckedIn(true);

				if (pPTripDAO.save(prepaidTrip) && pPCardDAO.update(prepaidCard)) {
					String message = "OPENING TICKET..." + "\nCardID: " + prepaidCard.getId() + "\nBalance: "
							+ prepaidCard.getBalance() + " eur";
					res.setMessage(message);
					res.setDisplayColor(Color.GREEN);
					res.setGateOpen(true);
				} else {
					res.setMessage("CHECKIN FAILED");
					res.setDisplayColor(Color.RED);
					res.setGateOpen(false);
				}
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.services.PPCardService#checkOut(vn.edu.hust.soict.afc.
	 * entities.Station, vn.edu.hust.soict.afc.entities.PrepaidCard)
	 */
	@Override
	public DataResponse checkOut(Station selectedStation, PrepaidCard prepaidCard) {
		DataResponse res = new DataResponse();
		if (prepaidCard.isCheckedIn()) {
			PrepaidTrip prepaidTrip = pPTripDAO.findByCardIdAndOnTrip(prepaidCard.getId(), true);
			Station iStation = StationService.getStationInfo(prepaidTrip.getIncomeStationId());
			double realFare = getFare(iStation, selectedStation);

			if (realFare > prepaidCard.getBalance()) {
				res.setMessage("INVALID CARD\nNot enough balance\nYour Balance: " + prepaidCard.getBalance() + " eur "
						+ "\nBut you have to pay: " + realFare + " eur");
				res.setDisplayColor(Color.RED);
			} else {
				double newBalance = prepaidCard.getBalance() - realFare;

				prepaidCard.setBalance(newBalance);
				prepaidCard.setCheckedIn(false);

				Timestamp timestamp = new Timestamp(new Date().getTime());
				prepaidTrip.setOutcomeStationId(selectedStation.getId());
				prepaidTrip.setOutcomeTime(timestamp);
				prepaidTrip.setRealFare(realFare);
				prepaidTrip.setOnTrip(false);

				if (pPCardDAO.update(prepaidCard) && pPTripDAO.update(prepaidTrip)) {
					String message = "OPENING CARD\n" + "ID: " + prepaidCard.getId() + ", balance: "
							+ prepaidCard.getBalance() + " eur\n" + "In reality: " + realFare + " eur";
					res.setMessage(message);
					res.setDisplayColor(Color.GREEN);
					res.setGateOpen(true);
				} else {
					res.setMessage("CHECKOUT FAILED");
					res.setDisplayColor(Color.RED);
					res.setGateOpen(false);
				}
			}
		} else {
			res.setMessage("INVALID TICKET\nThis card just only for checkin");
			res.setDisplayColor(Color.RED);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.services.PPCardService#getFare(vn.edu.hust.soict.afc.
	 * entities.Station, vn.edu.hust.soict.afc.entities.Station)
	 */
	@Override
	public double getFare(Station incomeStation, Station outcomeStation) {
		double distance = Math.abs(outcomeStation.getDistance() - incomeStation.getDistance());
		if (distance < BASE_DISTANCE) {
			return BASE_FARE;
		}

		double numberOfAddedDistance = Math.ceil((distance - BASE_DISTANCE) / ADDED_DISTANCE);
		return BASE_FARE + ADDED_FARE * numberOfAddedDistance;
	}
}
