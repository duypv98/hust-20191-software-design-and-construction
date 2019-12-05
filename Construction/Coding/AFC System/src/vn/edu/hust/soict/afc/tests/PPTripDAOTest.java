package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.PPTripDAOImpl;
import vn.edu.hust.soict.afc.entities.PrepaidTrip;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PPTripDAOTest {

	private PPTripDAOImpl dao = new PPTripDAOImpl();
	private PrepaidTrip trip = new PrepaidTrip(1, "PC201910300000", 1, new Timestamp(new Date().getTime()), 
									   3, new Timestamp(new Date().getTime()), 2.7, true);
	
	@Test
	public void test01FindByCardIdAndOnTrip() {
		PrepaidTrip foundedTrip = dao.findByCardIdAndOnTrip(trip.getCardId(), trip.isOnTrip());
		assertEquals(foundedTrip.getId(), trip.getId());
	}
	
	@Test
	public void test02Save() {
		assertEquals(dao.save(trip), true);
	}
	
	@Test
	public void test03Update() {
		trip.setRealFare(3.0);
		assertEquals(dao.update(trip), true);
	}
	
}