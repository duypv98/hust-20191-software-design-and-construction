package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.IItemService;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.utils.AFareCalculator;

/**
 * prepaid card's controller
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public class PPController {
	private IItemService pPCardService;
	
	public PPController(AFareCalculator fareCalculator) {
		pPCardService = new PPCardServiceImpl(fareCalculator);
	}

	/**
	 * navigation prepaid card's business process
	 * @param appState
	 * @return
	 */
	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return pPCardService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return pPCardService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
