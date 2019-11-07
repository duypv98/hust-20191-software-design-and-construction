package vn.edu.hust.soict.afc.services;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.scanner.CardScanner;
import vn.edu.hust.soict.afc.DAO.PPCardDAO;
import vn.edu.hust.soict.afc.DAO.PPCardDAOImpl;
import vn.edu.hust.soict.afc.DAO.PPTripDAO;
import vn.edu.hust.soict.afc.DAO.PPTripDAOImpl;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.PrepaidTrip;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.utils.Fare;

public class PPCardServiceImpl implements PPCardService {

	private PPCardDAO pPCardDAO = new PPCardDAOImpl();
	private PPTripDAO pPTripDAO = new PPTripDAOImpl();
	private CardScanner cardScanner = CardScanner.getInstance();

	/**
	 *
	 */
	public PPCardServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.services.PPCardService#checkIn(vn.edu.hust.soict.afc.
	 * entities.Station, vn.edu.hust.soict.afc.entities.PrepaidCard)
	 */
	@Override
	public DataResponse checkIn(String barCode, Station incomeStation) {
		String cardCode;
		try {
			cardCode = cardScanner.process(barCode);
		} catch (InvalidIDException e) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nCan't read barcode");
			res.setDisplayColor(Color.RED);
			return res;
		}

		PrepaidCard prepaidCard = pPCardDAO.findByCardCode(cardCode);

		if (prepaidCard == null) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nCan't find this card");
			res.setDisplayColor(Color.RED);
			return res;
		}

		if (prepaidCard.getBalance() < Fare.BASE_FARE) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nThe balance on the card is less than the base fare" + "\nCardID: "
					+ prepaidCard.getId() + "\nBalance: " + prepaidCard.getBalance() + " eur");
			res.setDisplayColor(Color.RED);
			return res;
		}

		if (prepaidCard.isCheckedIn()) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nThis card just only for checkout");
			res.setDisplayColor(Color.RED);
			return res;
		}

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		PrepaidTrip prepaidTrip = new PrepaidTrip();
		prepaidTrip.setCardId(prepaidCard.getId());
		prepaidTrip.setIncomeStationId(incomeStation.getId());
		prepaidTrip.setIncomeTime(timestamp);
		prepaidTrip.setOnTrip(true);

		prepaidCard.setCheckedIn(true);

		if (saveTransactionForCheckIn(prepaidCard, prepaidTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING TICKET..." + "\nCardID: " + prepaidCard.getId() + "\nBalance: "
					+ prepaidCard.getBalance() + " eur";
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);
			return res;
		} else {
			DataResponse res = new DataResponse();
			res.setMessage("Save Transaction Failed");
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		}
	}

	private boolean saveTransactionForCheckIn(PrepaidCard prepaidCard, PrepaidTrip prepaidTrip) {
		return pPCardDAO.update(prepaidCard) && pPTripDAO.save(prepaidTrip);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.services.PPCardService#checkOut(vn.edu.hust.soict.afc.
	 * entities.Station, vn.edu.hust.soict.afc.entities.PrepaidCard)
	 */
	@Override
	public DataResponse checkOut(String barCode, Station outcomeStation) {
		String cardCode;
		try {
			cardCode = cardScanner.process(barCode);
		} catch (InvalidIDException e) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nCan't read barcode");
			res.setDisplayColor(Color.RED);
			return res;
		}

		PrepaidCard prepaidCard = pPCardDAO.findByCardCode(cardCode);

		if (prepaidCard == null) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nCan't find this card");
			res.setDisplayColor(Color.RED);
			return res;
		}

		if (!prepaidCard.isCheckedIn()) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID TICKET\nThis card just only for checkin");
			res.setDisplayColor(Color.RED);
			return res;
		}

		PrepaidTrip prepaidTrip = pPTripDAO.findByCardIdAndOnTrip(prepaidCard.getId(), true);
		Station incomeStation = StationService.getStationInfo(prepaidTrip.getIncomeStationId());
		double realFare = Fare.caculate(incomeStation, outcomeStation);

		if (realFare > prepaidCard.getBalance()) {
			DataResponse res = new DataResponse();
			res.setMessage("INVALID CARD\nNot enough balance\nYour Balance: " + prepaidCard.getBalance() + " eur "
					+ "\nBut you have to pay: " + realFare + " eur");
			res.setDisplayColor(Color.RED);
			return res;
		}

		double newBalance = prepaidCard.getBalance() - realFare;

		prepaidCard.setBalance(newBalance);
		prepaidCard.setCheckedIn(false);

		Timestamp timestamp = new Timestamp(new Date().getTime());
		prepaidTrip.setOutcomeStationId(outcomeStation.getId());
		prepaidTrip.setOutcomeTime(timestamp);
		prepaidTrip.setRealFare(realFare);
		prepaidTrip.setOnTrip(false);

		if (saveTransactionForCheckOut(prepaidCard, prepaidTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING CARD\n" + "ID: " + prepaidCard.getId() + ", balance: " + prepaidCard.getBalance()
					+ " eur\n" + "In reality: " + realFare + " eur";
			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);
			return res;
		} else {
			DataResponse res = new DataResponse();
			res.setMessage("Save Transaction Failed");
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		}
	}

	private boolean saveTransactionForCheckOut(PrepaidCard prepaidCard, PrepaidTrip prepaidTrip) {
		return pPCardDAO.update(prepaidCard) && pPTripDAO.update(prepaidTrip);
	}
}
