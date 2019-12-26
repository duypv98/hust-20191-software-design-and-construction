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
import vn.edu.hust.soict.afc.services.IItemService;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.utils.IFareCalculator;

/**
 * Prepaid card controller
 * @author duycv
 * 
 */
public class PPController implements ItemController {
	private IItemService pPCardService;

	public PPController(IFareCalculator fareCalculator) {
		pPCardService = new PPCardServiceImpl(fareCalculator);
	}

	/**
	 * navigation prepaid card's business process
	 * @param appState
	 * @return {DataResponse}
	 */
	@Override
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return pPCardService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return pPCardService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
