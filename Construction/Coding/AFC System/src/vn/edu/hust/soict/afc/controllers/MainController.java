package vn.edu.hust.soict.afc.controllers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
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
	private TFController tfController;

	/**
	 * 
	 */
	public MainController() {
		res = new DataResponse();
		ticketRecognizer = TicketRecognizer.getInstance();
		setOwController(new OWController());
		setTfController(new TFController());
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
			/* Ignore */
		}
		return ticketCode;
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
					if (ticketType.equalsIgnoreCase("TF")) {
						
					res = tfController.process(ticketId, mainFrame.getAppState().isActCheckIn());
					}
				}
			}
		} else {
			// TODO Handle check by Prepaid Card
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
