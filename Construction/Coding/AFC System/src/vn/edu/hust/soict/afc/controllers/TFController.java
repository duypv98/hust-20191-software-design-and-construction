/**
 * 
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TFTicketService;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;

/**
 * twenty-four hour ticket's controller
 * @author hainn
 * @date Dec 7, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public class TFController {
	private TFTicketService tFTicketService = new TFTicketServiceImpl();
	
	/**
	 * navigation twenty-four hour ticket's business process
	 * @param appState
	 * @return
	 */
	
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return tFTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return tFTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
