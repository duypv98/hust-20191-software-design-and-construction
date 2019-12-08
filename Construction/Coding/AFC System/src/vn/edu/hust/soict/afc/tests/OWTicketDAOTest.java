package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.OWTicketDAOImpl;
import vn.edu.hust.soict.afc.entities.OneWayTicket;

/**
 * 
 * @author duytruong
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OWTicketDAOTest {

	private OWTicketDAOImpl dao = new OWTicketDAOImpl();
	private OneWayTicket ticket = new OneWayTicket("OW201910300000", "14ffab8aebbc5204", 1, 3, 2.7, false, false);

	@Test
	public void test01FindById() {
		OneWayTicket foundedTicket = dao.findById(ticket.getId());
		assertEquals(foundedTicket.isComparedWith(ticket), true);
	}
	
	@Test
	public void test02FindByTicketCode() {
		OneWayTicket foundedTicket = dao.findByTicketCode(ticket.getTicketCode());
		assertEquals(foundedTicket.isComparedWith(ticket), true);
	}
	
	@Test
	public void test03Update() {
		OneWayTicket newTicket = new OneWayTicket(ticket.getId(), ticket.getTicketCode(), 2, 4, 2.3, true, false);
		assertEquals(dao.update(newTicket), true);
		assertEquals(dao.findById(ticket.getId()).isComparedWith(newTicket), true);
	}
	
}