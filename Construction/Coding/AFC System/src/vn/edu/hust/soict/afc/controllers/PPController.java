/**
 * @author duycv
 * @date Dec 6, 2019
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
 * Prepaid card controller
 * @author duycv
 * 
 */
public class PPController extends ItemController {
	/**
	 * 
	 * @param service
	 * @param fareCalculator
	 */
	public PPController(ItemService service) {
		super(service);
	}

	/**
	 * navigation prepaid card's business process
	 * @param appState
	 * @return {DataResponse}
	 */
	@Override
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return service.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return service.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
