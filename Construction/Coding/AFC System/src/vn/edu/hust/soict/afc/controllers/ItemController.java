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
import vn.edu.hust.soict.afc.services.ItemService;

/**
 * Interface for items' controller
 * @author duytruong
 *
 */
public abstract class ItemController {
	
	protected ItemService service;
	
	/**
	 * 
	 * @param service
	 * @param fareCalculator
	 */
	public ItemController(ItemService service) {
		this.service = service;
	}
	/**
	 * 
	 * @param appState
	 * @return {DataResponse} Response
	 */
	abstract DataResponse process(AppState appState);

}