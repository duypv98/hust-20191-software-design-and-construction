package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.TFTripDAOImpl;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TFTripDAOTest {

	private TFTripDAOImpl dao = new TFTripDAOImpl();
	private TwentyFourTrip trip = new TwentyFourTrip(1, "TF201910300000", 1, new Timestamp(new Date().getTime()), 
											 3, new Timestamp(new Date().getTime()), true);
	
	@Test
	public void test01FindByTicketIdAndOnTrip() {
		TwentyFourTrip foundedTrip = dao.findByTicketIdAndOnTrip(trip.getTicketId(), trip.isOnTrip());
		assertEquals(foundedTrip.getId(), trip.getId());
	}
	
	@Test
	public void test02Save() {
		assertEquals(dao.save(trip), true);
	}
	
	@Test
	public void test03Update() {
		trip.setOutcomeStationId(4);
		assertEquals(dao.update(trip), true);
	}
	
}