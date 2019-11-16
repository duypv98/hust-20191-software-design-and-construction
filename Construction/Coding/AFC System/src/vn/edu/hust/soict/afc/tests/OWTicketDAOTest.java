package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.entities.OneWayTicket;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OWTicketDAOTest {

	OWTicketDAOImpl dao = new OWTicketDAOImpl();
	OneWayTicket ticket = new OneWayTicket("1", "OW201910300000", 1, 3, 2.7, false, false);

	@Test
	public void test01FindById() {
		assertEquals(dao.findById(ticket.getId()), ticket);
	}
	
	@Test
	public void test02Update() {
		OneWayTicket newTicket = new OneWayTicket(ticket.getId(), ticket.getTicketCode(), 2, 4, 2.3, true, false);
		assertEquals(dao.update(newTicket), true);
		assertEquals(dao.findById(ticket.getId()), newTicket);
		ticket = newTicket;
	}
	
	@Test
	public void test03FindByTicketCode() {
		assertEquals(dao.findByTicketCode(ticket.getTicketCode()), ticket);
	}

}
