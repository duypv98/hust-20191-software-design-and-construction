package vn.edu.hust.soict.afc.exception;

import java.awt.Color;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.controllers.MainController;

/**
 * exception handler
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public class ExceptionHandler {

	private MainController mainController = new MainController();

	/**
	 * catch and handler exception
	 * @param appState app's state
	 * @return data response
	 */
	public DataResponse catchException(AppState appState) {
		try {
			return mainController.commandEnter(appState);
		} catch (RuntimeException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		}
	}
}
