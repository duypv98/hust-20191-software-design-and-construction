/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.OWTicketService;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;

/**
 * @author iProfessor
 *
 */
public class OWController {
	private OWTicketService oWTicketService = new OWTicketServiceImpl();
	
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return oWTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return oWTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
