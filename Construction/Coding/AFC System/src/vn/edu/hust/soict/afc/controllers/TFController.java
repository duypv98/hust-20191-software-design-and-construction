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
import vn.edu.hust.soict.afc.services.ItemService;

/**
 * Twenty-four-hour ticket controller
 * @author duypv, duycv
 *
 */
public class TFController extends ItemController {
	
	public TFController(ItemService service) {
		super(service);
	}

	@Override
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return service.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return service.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
