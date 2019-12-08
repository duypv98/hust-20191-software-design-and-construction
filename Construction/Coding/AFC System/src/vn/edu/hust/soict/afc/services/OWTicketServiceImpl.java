/**
 *
 */
package vn.edu.hust.soict.afc.services;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.DAO.OWTicketDAO;
import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.DAO.OWTripDAO;
import vn.edu.hust.soict.afc.DAO.OWTripDAOImpl;
import vn.edu.hust.soict.afc.DAO.StationDAO;
import vn.edu.hust.soict.afc.DAO.StationDAOImpl;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.OneWayTrip;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.exception.CantFindTicketException;
import vn.edu.hust.soict.afc.exception.CantReadBarCodeException;
import vn.edu.hust.soict.afc.exception.ExpectedFareException;
import vn.edu.hust.soict.afc.exception.FailedTransactionException;
import vn.edu.hust.soict.afc.exception.NoLongerValidTicketException;
import vn.edu.hust.soict.afc.exception.TicketOnlyCheckInException;
import vn.edu.hust.soict.afc.exception.TicketOnlyCheckOutException;
import vn.edu.hust.soict.afc.exception.WrongStationException;
import vn.edu.hust.soict.afc.utils.AFareCalculator;
import vn.edu.hust.soict.afc.utils.Distance;

/**
 * @author Professor
 *
 */
public class OWTicketServiceImpl implements OWTicketService {

	private OWTicketDAO oWTicketDAO = new OWTicketDAOImpl();
	private OWTripDAO oWTripDAO = new OWTripDAOImpl();
	private TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
	private AFareCalculator fareCalculator;
	private StationDAO stationDAO = new StationDAOImpl();

	/**
	 *
	 */
	public OWTicketServiceImpl(AFareCalculator fareCalculator) {
		this.fareCalculator = fareCalculator;
	}

	/**
	 *
	 * @param barCode
	 * @param station
	 * @return
	 */
	@Override
	public DataResponse checkIn(String barCode, Station station) {
		String ticketCode;
		try {
			ticketCode = ticketRecognizer.process(barCode);
		} catch (InvalidIDException e) {
			throw new CantReadBarCodeException("INVALID TICKET\nCan't read barcode");
		}

		OneWayTicket oneWayTicket = oWTicketDAO.findByTicketCode(ticketCode);

		if (oneWayTicket == null) {
			throw new CantFindTicketException("INVALID TICKET\nCan't find this ticket");
		}

		if (oneWayTicket.isActivated()) {
			throw new NoLongerValidTicketException("INVALID TICKET\nThis ticket is no longer valid");
		}

		if (oneWayTicket.isCheckedIn()) {
			throw new TicketOnlyCheckOutException("INVALID TICKET\nThis ticket just only for checkout");
		}

		Station embarkation = stationDAO.findById(oneWayTicket.getEmbarkationId());
		Station disembarkation = stationDAO.findById(oneWayTicket.getDisembarkationId());

		if (!Distance.isCorrectPosition(embarkation, disembarkation, station)) {
			throw new WrongStationException("INVALID TICKET\nWrong station to go");
		}

		Timestamp timestamp = new Timestamp(new Date().getTime());
		OneWayTrip oneWayTrip = new OneWayTrip();
		oneWayTrip.setTicketId(oneWayTicket.getId());
		oneWayTrip.setIncomeStationId(station.getId());
		oneWayTrip.setIncomeTime(timestamp);
		oneWayTrip.setOnTrip(true);

		oneWayTicket.setCheckedIn(true);
		oneWayTicket.setActivated(false);

		if (saveTransactionForCheckIn(oneWayTicket, oneWayTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING TICKET..." + "\nTicketID: " + oneWayTicket.getId() + "\nDistance: " + Distance.calculate(embarkation, disembarkation)
					+ " km" + "\nFare: " + oneWayTicket.getFare() + " eur";

			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);

			return res;
		} else {
			throw new FailedTransactionException("Save Transaction Failed");
		}

	}
	/**
	 *
	 * @param oneWayTicket
	 * @param oneWayTrip
	 * @return
	 */
	private boolean saveTransactionForCheckIn(OneWayTicket oneWayTicket, OneWayTrip oneWayTrip) {
		return oWTicketDAO.update(oneWayTicket) && oWTripDAO.save(oneWayTrip);
	}

	/**
	 *
	 * @param barCode
	 * @param station
	 * @return
	 */
	@Override
	public DataResponse checkOut(String barCode, Station station) {
		String ticketCode;
		try {
			ticketCode = ticketRecognizer.process(barCode);
		} catch (InvalidIDException e) {
			throw new CantReadBarCodeException("INVALID CARD\nCan't read barcode");
		}
		OneWayTicket oneWayTicket = oWTicketDAO.findByTicketCode(ticketCode);

		if (oneWayTicket == null) {
			throw new CantFindTicketException("INVALID TICKET\nCan't find this ticket");
		}

		if (oneWayTicket.isActivated()) {
			throw new NoLongerValidTicketException("INVALID TICKET\nThis ticket is no longer valid");
		}

		if (!oneWayTicket.isCheckedIn()) {
			throw new TicketOnlyCheckInException("INVALID TICKET\nThis ticket just only for checkin");
		}

		Timestamp timestamp = new Timestamp(new Date().getTime());
		OneWayTrip oneWayTrip = oWTripDAO.findByTicketId(oneWayTicket.getId());

		Station incomeStation = stationDAO.findById(oneWayTrip.getIncomeStationId());
		double realFare = fareCalculator.caculate(incomeStation, station);
		double realDistance = Distance.calculate(incomeStation, station);

		if (realFare > oneWayTicket.getFare()) {
			throw new ExpectedFareException("INVALID TICKET\n(Warnings) Expected Fare: " + realFare + " eur, your ticket's fare: " + oneWayTicket.getFare() + " eur");
		}

		oneWayTicket.setActivated(true);
		oneWayTicket.setCheckedIn(false);
		oneWayTrip.setOnTrip(false);
		oneWayTrip.setOutcomeStationId(station.getId());
		oneWayTrip.setOutcomeTime(timestamp);

		if (saveTransactionForCheckOut(oneWayTicket, oneWayTrip)) {
			DataResponse res = new DataResponse();
			String message = "OPENING TICKET..." + "\nTicketID: " + oneWayTicket.getId() + "\nDistance: " + realDistance
					+ " km" + "\nFare: " + oneWayTicket.getFare() + " eur";

			res.setMessage(message);
			res.setDisplayColor(Color.GREEN);
			res.setGateOpen(true);

			return res;
		} else {
			throw new FailedTransactionException("Save Transaction Failed");
		}
	}

	/**
	 *
	 * @param oneWayTicket
	 * @param oneWayTrip
	 * @return
	 */
	private boolean saveTransactionForCheckOut(OneWayTicket oneWayTicket, OneWayTrip oneWayTrip) {
		return oWTicketDAO.update(oneWayTicket) && oWTripDAO.update(oneWayTrip);
	}
}
