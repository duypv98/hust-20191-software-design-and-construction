/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;

import hust.soict.se.customexception.InvalidIDException;
import vn.edu.hust.soict.afc.boundaries.TicketRecognizerBoundary;
import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.StationService;

/**
 * @author Professor
 *
 */
public class MainApp {

	/**
	 * 
	 */
	public MainApp() {
		// TODO Auto-generated constructor stub
	}

	public void start() {
		String pseudoBarcode = "outodate";
		String ticketCode = null;
		try {
			ticketCode = TicketRecognizerBoundary.getCode(pseudoBarcode);
		} catch (InvalidIDException e) {
			System.out.println("Can't recognize");
			e.printStackTrace();
		}
		Station station = new Station();
		try {
			station = StationService.getStationInfo(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OneWayTicket oneWayTicket = new OneWayTicket();
		try {
			oneWayTicket = OWController.getTicketInfo(ticketCode);
		} catch (SQLException e) {
			System.out.println("Can't find your ticket, this may be a wrong ticket");
			e.printStackTrace();
		}

		if (oneWayTicket.isActivated()) {
			System.out.println("This ticket is out of date");
			return;
		}
		if (oneWayTicket.isCheckedIn()) {
			System.out.println("This ticket is now only for checking out");
			return;
		} else {
			try {
				if (OWController.checkIn(oneWayTicket, station)) {
					System.out.println("Opening Gate...");
					System.out.println("Ticket: " + oneWayTicket.getId());
					System.out.println("Fare: " + oneWayTicket.getFare());
				} else {
					System.out.println("Wrong station to go");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.start();
	}

}
