package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.DAO.OWTripDAOImpl;
import vn.edu.hust.soict.afc.DAO.StationDAOImpl;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;

/**
 *
 * @author duytruong
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OWTicketServiceTest {

//	OneWayTicket ticket = new OneWayTicket("OW201910300001", "e8dc4081b13434b4", 1, 3, 2.7, false, false);
	private String barCode = "abcdefgh";
	private OWTicketServiceImpl service = new OWTicketServiceImpl(new StationDAOImpl(), new OWTicketDAOImpl(), new OWTripDAOImpl(), new FareCalculatorByDistance(), TicketRecognizer.getInstance());
	private Station incomeStation = new Station(1, "Saint-Lazare", 0);
	private Station outcomeStation = new Station(3, "Pyramides", 8.5);

	@Test
	public void test01CheckIn() {
		DataResponse response = service.checkIn(barCode, incomeStation);
		assertEquals(response.getDisplayColor(), Color.GREEN);
	}

	@Test
	public void test02CheckOut() {
		DataResponse response = service.checkOut(barCode, outcomeStation);
		assertEquals(response.getDisplayColor(), Color.GREEN);
	}

}