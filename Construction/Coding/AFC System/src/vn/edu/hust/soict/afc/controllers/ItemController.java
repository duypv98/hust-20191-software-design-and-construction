/**
 * @author duytruong
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

/**
 * Interface for items' controller
 * @author duytruong
 *
 */
public interface ItemController {
	
	/**
	 * 
	 * @param appState
	 * @return {DataResponse} Response
	 */
	DataResponse process(AppState appState);

}