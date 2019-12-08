/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.IItemService;
import vn.edu.hust.soict.afc.services.OWTicketServiceImpl;
import vn.edu.hust.soict.afc.utils.IFareCalculator;

/**
 * @author iProfessor
 *
 */
public class OWController {
	private IItemService oWTicketService;
	
	public OWController(IFareCalculator fareCalculator) {
		oWTicketService = new OWTicketServiceImpl(fareCalculator);
	}
	
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return oWTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return oWTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
