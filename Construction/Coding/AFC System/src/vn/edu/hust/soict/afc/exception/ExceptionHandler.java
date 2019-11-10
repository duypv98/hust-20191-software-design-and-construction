package vn.edu.hust.soict.afc.exception;

import java.awt.Color;

import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.controllers.MainController;

public class ExceptionHandler {

	private MainController mainController = new MainController();

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
		}
	}
}
