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

	private TFTicketDAOImpl dao = new TFTicketDAOImpl();
	private TwentyFourTicket ticket = new TwentyFourTicket("1", "TF201910300000", new Timestamp(new Date().getTime()), false);
	
	@Test
	public void test01FindById() {
		TwentyFourTicket foundedTicket = dao.findById(ticket.getId());
		assertEquals(foundedTicket.getTicketCode(), ticket.getTicketCode());
		assertEquals(foundedTicket.isCheckedIn(), ticket.isCheckedIn());
	}
	
	@Test
	public void test02FindByTicketCode() {
		TwentyFourTicket foundedTicket = dao.findByTicketCode(ticket.getTicketCode());
		assertEquals(foundedTicket.getId(), foundedTicket.getId());
		assertEquals(foundedTicket.isCheckedIn(), ticket.isCheckedIn());
	}
	
	@Test
	public void test03Update() {
		TwentyFourTicket newTicket = ticket;
		ticket.setCheckedIn(true);
		assertEquals(dao.update(newTicket), true);	
	}

}
