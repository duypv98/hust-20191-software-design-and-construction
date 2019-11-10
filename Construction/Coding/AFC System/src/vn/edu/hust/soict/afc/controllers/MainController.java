/**
 *
 */
package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TicketService;

/**
 * @author Professor
 *
 */
public class MainController {

	public static TicketRecognizer ticketRecognizer;
	private OWController owController;
	private TFController tfController;
	private PPController pPController = new PPController();

	/**
	 *
	 */
	public MainController() {
		ticketRecognizer = TicketRecognizer.getInstance();
		setOwController(new OWController());
		setTfController(new TFController());
	}

	/**
	 * @return the owController
	 */
	public OWController getOwController() {
		return owController;
	}

	/**
	 * @return the tfController
	 */
	public TFController getTfController() {
		return tfController;
	}

	/**
	 * @param owController the owController to set
	 */
	public void setOwController(OWController owController) {
		this.owController = owController;
	}

	/**
	 * @param tfController
	 */
	public void setTfController(TFController tfController) {
		this.tfController = tfController;
	}

	public String getTicketCode(String barcode) {
		String ticketCode = null;
		try {
			ticketCode = ticketRecognizer.process(barcode);
		} catch (InvalidIDException e) {

		}
		return ticketCode;
	}

	public DataResponse commandEnter(AppState appState) {
		String barcode = appState.getItemBarcode();
		if (appState.isByTicket()) {
			String ticketCode = getTicketCode(barcode);
			if (ticketCode != null) {
				String ticketId = TicketService.getTicketId(ticketCode);
				if (ticketId == null) {
					DataResponse res = new DataResponse();
					res.setMessage("INVALID TICKET\nCan't find this ticket");
					res.setDisplayColor(Color.RED);
					return res;
				} else {

					String ticketType = ticketId.substring(0, 2);
					if (ticketType.equalsIgnoreCase("OW")) {
						return owController.process(ticketId, appState.isActCheckIn(), appState.getSelectedStation());

					} else if (ticketType.equalsIgnoreCase("TF")) {
						return tfController.process(ticketId, appState.isActCheckIn(), appState.getSelectedStation());
					}
				}
			} else {
				DataResponse res = new DataResponse();
				res.setMessage("INVALID TICKET\nCan't read barcode");
				res.setDisplayColor(Color.RED);
				return res;
			}
		} else {
			return pPController.process(appState);
		}
		return null;
	}
}