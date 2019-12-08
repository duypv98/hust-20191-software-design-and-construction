/**
 * @author hainn
 * @date Oct 27, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.IItemService;
import vn.edu.hust.soict.afc.services.TFTicketServiceImpl;

/**
 * Twenty-four-hour ticket controller
 * @author duypv, duycv
 *
 */
public class TFController implements ItemController {
	private IItemService tFTicketService = new TFTicketServiceImpl();

	@Override
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return tFTicketService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return tFTicketService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
