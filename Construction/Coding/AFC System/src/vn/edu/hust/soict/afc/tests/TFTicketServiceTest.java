package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TFTicketServiceTest {
	
//	TwentyFourTicket ticket = new TwentyFourTicket("TF201910300001", "e8dc4081b13434b4", "2019-12-06 07:51:30", false);
	private String barCode = "abcdefgh";
	private TFTicketServiceImpl service = new TFTicketServiceImpl();
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