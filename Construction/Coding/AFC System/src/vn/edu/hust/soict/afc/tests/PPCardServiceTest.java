package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.utils.FareCalculatorByDistance;

/**
 * 
 * @author duytruong
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PPCardServiceTest {
	
//	PrepaidCard card = new PrepaidCard("PC201910300001", "9ac2197d9258257b", 5.0, false);
	private String barCode = "ABCDEFGH";
	private PPCardServiceImpl service = new PPCardServiceImpl(new FareCalculatorByDistance());
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