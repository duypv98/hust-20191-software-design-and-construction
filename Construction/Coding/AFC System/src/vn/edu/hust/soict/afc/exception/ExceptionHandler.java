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
		} catch (CantReadBarCodeException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (CantFindCardException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (BalanceLessThanBaseFareException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (CardOnlyCheckOutException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (CardOnlyCheckInException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (NotEnoughBalanceException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			return res;
		} catch (FailedTransactionException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (CantFindTicketException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (NoLongerValidTicketException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (TicketOnlyCheckOutException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (WrongStationException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (TicketOnlyCheckInException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		} catch (ExpectedFareException e) {
			DataResponse res = new DataResponse();
			res.setMessage(e.getMessage());
			res.setDisplayColor(Color.RED);
			res.setGateOpen(false);
			return res;
		}
	}
}
