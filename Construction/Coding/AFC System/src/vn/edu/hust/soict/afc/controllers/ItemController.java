package vn.edu.hust.soict.afc.controllers;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;

/**
 * 
 * @author duytruong
 *
 */
public interface ItemController {
	
	/**
	 * 
	 * @param appState
	 * @return
	 */
	DataResponse process(AppState appState);

}