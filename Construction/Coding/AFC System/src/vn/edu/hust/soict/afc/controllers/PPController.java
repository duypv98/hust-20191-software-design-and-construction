package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.PPCardService;
import vn.edu.hust.soict.afc.services.PPCardServiceImpl;
import vn.edu.hust.soict.afc.utils.AFareCalculator;

public class PPController {
	private PPCardService pPCardService;
	
	public PPController(AFareCalculator fareCalculator) {
		pPCardService = new PPCardServiceImpl(fareCalculator);
	}

	public DataResponse process(AppState appState) {
		if (appState.isActCheckIn()) {
			return pPCardService.checkIn(appState.getItemBarcode(), appState.getSelectedStation());
		} else {
			return pPCardService.checkOut(appState.getItemBarcode(), appState.getSelectedStation());
		}
	}
}
