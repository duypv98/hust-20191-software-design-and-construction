/**
 *
 */
package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import hust.soict.se.scanner.CardScanner;
import vn.edu.hust.soict.afc.boundaries.MainGUI;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.services.TicketService;

/**
 * @author Professor
 *
 */
public class MainController {

	public static DataResponse res;
	public static ImageIcon closeGate = new ImageIcon(MainGUI.class.getResource("/closegate.jpg"));
	public static ImageIcon openGate = new ImageIcon(MainGUI.class.getResource("/opengate.jpg"));
	public MainGUI mainFrame;
	public static TicketRecognizer ticketRecognizer;
	private OWController owController;
	public static CardScanner cardScanner;
	private PrepaidCardController prepaidCardController = new PrepaidCardController();

	/**
	 *
	 */
	public MainController() {
		res = new DataResponse();
		ticketRecognizer = TicketRecognizer.getInstance();
		cardScanner = CardScanner.getInstance();
		setOwController(new OWController());
		mainFrame = new MainGUI();
		mainFrame.getBtnEnter().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				commandEnter();
			}
		});
	}

	/**
	 * @return the owController
	 */
	public OWController getOwController() {
		return owController;
	}

	/**
	 * @param owController the owController to set
	 */
	public void setOwController(OWController owController) {
		this.owController = owController;
	}

	public String getTicketCode(String barcode) {
		String ticketCode = null;
		try {
			ticketCode = ticketRecognizer.process(barcode);
		} catch (InvalidIDException e) {
			/* Ignore */
		}
		return ticketCode;
	}

	public String getCardCode(String barcode) {
		String cardCode = null;
		try {
			cardCode = cardScanner.process(barcode);
		} catch (InvalidIDException e) {
			/* Ignore */
		}
		return cardCode;
	}

	public void commandEnter() {
		String barcode = mainFrame.getBarcodeInputField().getText();
		if (mainFrame.getAppState().isByTicket()) {
			String ticketCode = getTicketCode(barcode);
			if (ticketCode == null) {
				res.setMessage("INVALID TICKET\nCan't read barcode");
				res.setDisplayColor(Color.RED);

			} else {
				String ticketId = TicketService.getTicketId(ticketCode);
				String ticketType = ticketId.substring(0, 2);
				if (ticketType.equalsIgnoreCase("OW")) {

					res = owController.process(ticketId, mainFrame.getAppState().isActCheckIn(),
							mainFrame.getAppState().getSelectedStation());

				} else {
					// TODO Handle check by 24h Ticket
				}
			}
		} else {
			// TODO Handle check by Prepaid Card
            String cardCode = getCardCode(barcode);
            if (cardCode == null) {
                res.setMessage("INVALID CARD\nCan't read barcode");
                res.setDisplayColor(Color.RED);
            } else {
                if (mainFrame.getAppState().isActCheckIn()) {
                    res = prepaidCardController.process(cardCode, mainFrame.getAppState());
                } else {

                }
            }
		}
		mainFrame.getInfoFrame().setText(res.getMessage());
		mainFrame.getInfoFrame().setForeground(res.getDisplayColor());
	}

	public static void main(String[] args) {
		final MainController mainController = new MainController();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainController.mainFrame.setVisible(true);
			}
		});
	}
}
