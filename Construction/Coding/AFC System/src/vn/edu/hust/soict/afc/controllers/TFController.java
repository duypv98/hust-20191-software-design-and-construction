/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TFTicketService;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;

/**
 * @author iProfessor
 *
 */
public class TFController {
	private TFTicketService tFTicketService = new TFTicketServiceImpl();
	
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return tFTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return tFTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
