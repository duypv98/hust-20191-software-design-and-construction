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
import vn.edu.hust.soict.afc.exception.BalanceLessThanBaseFareException;
import vn.edu.hust.soict.afc.exception.CantFindCardException;
import vn.edu.hust.soict.afc.exception.CantReadBarCodeException;
import vn.edu.hust.soict.afc.exception.CardOnlyCheckInException;
import vn.edu.hust.soict.afc.exception.CardOnlyCheckOutException;
import vn.edu.hust.soict.afc.exception.FailedTransactionException;
import vn.edu.hust.soict.afc.exception.NotEnoughBalanceException;
import vn.edu.hust.soict.afc.utils.AFareCalculator;
import vn.edu.hust.soict.afc.utils.NumberRound;

public class PPCardServiceImpl implements PPCardService {

	private PPCardDAO pPCardDAO = new PPCardDAOImpl();
	private PPTripDAO pPTripDAO = new PPTripDAOImpl();
	private CardScanner cardScanner = CardScanner.getInstance();
	private AFareCalculator fareCalculator;
	/**
	 *
	 */
	public PPCardServiceImpl(AFareCalculator fareCalculator) {
		this.fareCalculator = fareCalculator;
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
			throw new CantReadBarCodeException("INVALID CARD\nCan't read barcode");
		}

		PrepaidCard prepaidCard = pPCardDAO.findByCardCode(cardCode);

		if (prepaidCard == null) {
			throw new CantFindCardException("INVALID CARD\nCan't find this card");
		}

		if (prepaidCard.getBalance() < fareCalculator.BASE_FARE) {
			String message = "INVALID CARD\nThe balance on the card is less than the base fare" + "\nCardID: "
					+ prepaidCard.getId() + "\nBalance: " + prepaidCard.getBalance() + " eur";
			throw new BalanceLessThanBaseFareException(message);
		}

		if (prepaidCard.isCheckedIn()) {
			throw new CardOnlyCheckOutException("INVALID CARD\nThis card just only for checkout");
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
			throw new FailedTransactionException("Save Transaction Failed");
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
			throw new CantReadBarCodeException("INVALID CARD\nCan't read barcode");
		}

		PrepaidCard prepaidCard = pPCardDAO.findByCardCode(cardCode);

		if (prepaidCard == null) {
			throw new CantFindCardException("INVALID CARD\nCan't find this card");
		}

		if (!prepaidCard.isCheckedIn()) {
			throw new CardOnlyCheckInException("INVALID TICKET\nThis card just only for checkin");
		}

		PrepaidTrip prepaidTrip = pPTripDAO.findByCardIdAndOnTrip(prepaidCard.getId(), true);
		Station incomeStation = StationService.getStationInfo(prepaidTrip.getIncomeStationId());
		double realFare = fareCalculator.caculate(incomeStation, outcomeStation);

		if (realFare > prepaidCard.getBalance()) {
			String message = "INVALID CARD\nNot enough balance\nYour Balance: " + prepaidCard.getBalance() + " eur "
					+ "\nBut you have to pay: " + realFare + " eur";
			throw new NotEnoughBalanceException(message);
		}

		double newBalance = NumberRound.roundOneDigitAfterAComma(prepaidCard.getBalance() - realFare);

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
			throw new FailedTransactionException("Save Transaction Failed");
		}
	}

	private boolean saveTransactionForCheckOut(PrepaidCard prepaidCard, PrepaidTrip prepaidTrip) {
		return pPCardDAO.update(prepaidCard) && pPTripDAO.update(prepaidTrip);
	}
	
	public static void main(String[] args) throws InvalidIDException {
		String barcode = "ABCDEFGH";
		System.out.println(CardScanner.getInstance().process(barcode));
	}
}
