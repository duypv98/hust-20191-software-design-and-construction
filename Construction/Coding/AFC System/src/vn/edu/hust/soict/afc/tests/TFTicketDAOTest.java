package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.TFTicketDAOImpl;
import vn.edu.hust.soict.afc.entities.TwentyFourTicket;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TFTicketDAOTest {

	TFTicketDAOImpl dao = new TFTicketDAOImpl();
	TwentyFourTicket ticket = new TwentyFourTicket("1", "TF201910300000", new Timestamp(new Date().getTime()), false);

	@Test
	public void test01FindById() {
		assertEquals(dao.findById(ticket.getId()), ticket);
	}
	
	@Test
	public void test02Update() {
		TwentyFourTicket newTicket= new TwentyFourTicket(ticket.getId(), ticket.getTicketCode(), new Timestamp(new Date().getTime() + 3600 * 1000), true);
		assertEquals(dao.update(newTicket), true);
		assertEquals(dao.findById(ticket.getId()), newTicket);
		ticket = newTicket;
	}
	
	@Test
	public void test03FindByTicketCode() {
		assertEquals(dao.findByTicketCode(ticket.getTicketCode()), ticket);
	}

}
