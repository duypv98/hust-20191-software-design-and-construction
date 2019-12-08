/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.OWTicketService;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;
import vn.edu.hust.soict.afc.utils.AFareCalculator;

/**
 * @author iProfessor
 *
 */
public class OWController implements ItemController {
	private OWTicketService oWTicketService;
	
	public OWController(AFareCalculator fareCalculator) {
		oWTicketService = new OWTicketServiceImpl(fareCalculator);
	}
	
	@Override
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return oWTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return oWTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
