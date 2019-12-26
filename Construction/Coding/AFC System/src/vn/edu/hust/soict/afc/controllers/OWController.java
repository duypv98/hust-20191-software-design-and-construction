/**
 * @author duypv
 * @date Oct 23, 2019
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
 * One-way ticket controller
 * @author duypv
 *
 */
public class OWController extends ItemController {
	/**
	 * 
	 * @param service
	 * @param fareCalculator
	 */
	public OWController(ItemService service) {
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
