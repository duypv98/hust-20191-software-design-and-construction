package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import vn.edu.hust.soict.afc.DAO.PPCardDAOImpl;
import vn.edu.hust.soict.afc.entities.PrepaidCard;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PPCardDAOTest {

	private PPCardDAOImpl dao = new PPCardDAOImpl();
	private PrepaidCard card = new PrepaidCard("1", "PC201910300000", 5.1, false);

	@Test
	public void test01FindById() {
		PrepaidCard foundedCard = dao.findById(card.getId());
		assertEquals(foundedCard.isComparedWith(card), true);
	}
	
	@Test
	public void test02FindByCardCode() {
		PrepaidCard foundedCard = dao.findByCardCode(card.getCardCode());
		assertEquals(foundedCard.isComparedWith(card), true);
	}
	
	@Test
	public void test03Update() {
		PrepaidCard newCard= new PrepaidCard(card.getId(), card.getCardCode(), 4.9, true);
		assertEquals(dao.update(newCard), true);
		assertEquals(dao.findById(card.getId()).isComparedWith(newCard), true);
	}

}
