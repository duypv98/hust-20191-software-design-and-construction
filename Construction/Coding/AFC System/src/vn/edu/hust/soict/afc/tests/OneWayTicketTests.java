package vn.edu.hust.soict.afc.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import vn.edu.hust.soict.afc.controllers.OWController;
import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.Station;

public class OneWayTicketTests {
	
	OWController owController = new OWController();

	@Test
	public void testGetFare() {
		Station startStation = new Station(3, "Station 1", 3.0);
		Station endStation = new Station(5, "Station 2", 4.1);
		assertEquals(1.9, OWController.getFare(startStation, endStation), 0.0);
		endStation.setDistance(8.0);
		assertEquals(1.9, OWController.getFare(startStation, endStation), 0.0);
		endStation.setDistance(8.1);
		assertEquals(2.3, OWController.getFare(startStation, endStation), 0.0);
		endStation.setDistance(10.0);
		assertEquals(2.3, OWController.getFare(startStation, endStation), 0.0);
		endStation.setDistance(10.1);
		assertEquals(2.7, OWController.getFare(startStation, endStation), 0.0);
	}
	
	@Test
	public void testGetTicket() {
		String ticketId = "OW201910300000";
		OneWayTicket oneWayTicket = new OneWayTicket("OW201910300000", "14ffab8aebbc5204", 0, 1, 1.9, false, false);
		assertEquals(oneWayTicket, owController.getTicket(ticketId));
	}

	@Test
	public void testCheckIn() {
		OneWayTicket oneWayTicket = new OneWayTicket("OW201910300001", "b8094c1ccdff1df9", 1, 3, 2.3, false, false);
		Station selectedStation = new Station(0, "Saint Lazare", 0.0);
		
		assertEquals(Color.RED, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
		selectedStation = new Station(4, "Gare de Lyon", 15.8);
		assertEquals(Color.RED, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
		selectedStation = new Station(2, "Pyramides", 8.5);
		assertEquals(Color.GREEN, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
		
		oneWayTicket.setCheckedIn(true);
		assertEquals(Color.RED, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
		oneWayTicket.setCheckedIn(false);
		oneWayTicket.setActivated(true);
		assertEquals(Color.RED, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
	}
	
	@Test
	public void testCheckOut() {
		OneWayTicket oneWayTicket = new OneWayTicket("OW201910300002", "4435a2c99ae25d9de", 1, 3, 2.3, true, false);
		Station selectedStation = new Station(4, "Gare de Lyon", 15.8);
		
		// incomeStation id = 2
		assertEquals(Color.RED, owController.checkOut(selectedStation, oneWayTicket).getDisplayColor());
		selectedStation = new Station(2, "Madeleine", 5.0);
		assertEquals(Color.GREEN, owController.checkIn(selectedStation, oneWayTicket).getDisplayColor());
		
		oneWayTicket.setCheckedIn(false);
		assertEquals(Color.RED, owController.checkOut(selectedStation, oneWayTicket).getDisplayColor());
		oneWayTicket.setCheckedIn(true);
		oneWayTicket.setActivated(false);
		assertEquals(Color.RED, owController.checkOut(selectedStation, oneWayTicket).getDisplayColor());
	}
	
	@Test
	public void testProcess() {
		String ticketId = "OW222222";
		Station selectedStation = new Station(4, "Gare de Lyon", 15.8);
		assertEquals(Color.RED, owController.process(ticketId, false, selectedStation).getDisplayColor());
	}
}
