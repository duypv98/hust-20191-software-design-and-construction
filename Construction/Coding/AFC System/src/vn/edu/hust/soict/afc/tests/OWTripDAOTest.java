package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.OWTripDAOImpl;
import vn.edu.hust.soict.afc.entities.OneWayTrip;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OWTripDAOTest {

	private OWTripDAOImpl dao = new OWTripDAOImpl();
	private OneWayTrip trip = new OneWayTrip(1, "OW201910300000", 1, new Timestamp(new Date().getTime()), 3, new Timestamp(new Date().getTime()), 2.7, false);
	
	@Test
	public void test01FindByTicketId() {
		OneWayTrip foundedTrip = dao.findByTicketId(trip.getTicketId());
		assertEquals(foundedTrip.getId(), trip.getId());
	}
	
	@Test
	public void test02Save() {
		assertEquals(dao.save(trip), true);
	}
	
	@Test
	public void test03Update() {
		trip.setOnTrip(true);
		assertEquals(dao.update(trip), true);
	}
	
}