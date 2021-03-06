/**
 * @author duypv
 * @date Oct 23, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.boundaries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import vn.edu.hust.soict.afc.DAO.StationDAO;
import vn.edu.hust.soict.afc.DAO.StationDAOImpl;
import vn.edu.hust.soict.afc.common.AppState;
import vn.edu.hust.soict.afc.common.DataResponse;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.exception.ExceptionHandler;

public class MainGUI extends JFrame {
	/**
	 * Define UI states
	 */
	private AppState appState;
	private Map<String, Station> listStations;
	private DefaultListModel<String> listBarcode;
	private List<String> stationKeys;
	private GateBoundary gatePanel;

	/**
	 * Auto-generated JWT Config
	 */
	private static final long serialVersionUID = -3291303651386410923L;
	private JPanel contentPanel;
	private JTextField barcodeInputField;
	private JButton btnEnter;
	private JTextPane infoFrame;

	private ExceptionHandler exceptionHandler = new ExceptionHandler();
	public static ImageIcon closeGate = new ImageIcon(MainGUI.class.getResource("/closegate.jpg"));
	public static ImageIcon openGate = new ImageIcon(MainGUI.class.getResource("/opengate.jpg"));
	private StationDAO stationDAO = new StationDAOImpl();

	/**
	 * Get lists station for UI
	 * @return the listStations
	 */
	public Map<String, Station> getListStations() {
		return listStations;
	}

	/**
	 * Set list stations for UI
	 * @param listStations the listStations to set
	 */
	public void setListStations(Map<String, Station> listStations) {
		this.listStations = listStations;
	}

	/**
	 * Get list pseudo-barcodes for UI
	 * @return the listBarcode
	 */
	public DefaultListModel<String> getListBarcode() {
		return listBarcode;
	}

	/**
	 * Set list pseudo-barcodes for UI
	 * @param listBarcode the listBarcode to set
	 */
	public void setListBarcode(DefaultListModel<String> listBarcode) {
		this.listBarcode = listBarcode;
	}

	/**
	 * Get list stations' key for passing to application's state
	 * @return the stationKeys
	 */
	public List<String> getStationKeys() {
		return stationKeys;
	}

	/**
	 * Set list stations' key
	 * @param stationKeys the stationKeys to set
	 */
	public void setStationKeys(List<String> stationKeys) {
		this.stationKeys = stationKeys;
	}

	/**
	 * Get application's state
	 * @return the appState
	 */
	public AppState getAppState() {
		return appState;
	}

	/**
	 * Get the barcode input field
	 * @return the barcodeInputField
	 */
	public JTextField getBarcodeInputField() {
		return barcodeInputField;
	}

	/**
	 * Get the enter button
	 * @return the btnEnter
	 */
	public JButton getBtnEnter() {
		return btnEnter;
	}

	/**
	 * Get info frame, which displays infomation when passengers use the system
	 * @return the infoFrame
	 */
	public JTextPane getInfoFrame() {
		return infoFrame;
	}

	/**
	 * Get Gate panel for controlling
	 * @return the gatePanel
	 */
	public GateBoundary getGatePanel() {
		return gatePanel;
	}

	/**
	 * Init state of GUI
	 */
	public void init() {
		this.appState = new AppState();
		this.listStations = new HashMap<String, Station>();
		this.stationKeys = new ArrayList<String>();
		this.listBarcode = new DefaultListModel<String>();
		List<Station> allStations = new ArrayList<>();
		allStations = stationDAO.findAll();
		for (int i = 0; i < allStations.size(); i++) {
			listStations.put(allStations.get(i).getStationName(), allStations.get(i));
			stationKeys.add(allStations.get(i).getStationName());
		}
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setResizable(false);
		/* init data */
		init();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 634);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.CYAN);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		/* Title Component */
		JTextPane panelTitle = new JTextPane();
		panelTitle.setEditable(false);
		panelTitle.setBackground(Color.CYAN);
		panelTitle.setFont(new Font("HL-Comic1unicode", Font.BOLD | Font.ITALIC, 36));
		panelTitle.setText("Subway Station");
		panelTitle.setBounds(261, 13, 353, 59);
		contentPanel.add(panelTitle);

		/* Seperator between header and body */
		JSeparator separatorHeader = new JSeparator();
		separatorHeader.setBounds(0, 85, 926, 7);
		contentPanel.add(separatorHeader);

		/* Label for Station */
		JLabel lblSelectStation = new JLabel("Station");
		lblSelectStation.setToolTipText("Select station");
		lblSelectStation.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSelectStation.setBounds(35, 91, 97, 41);
		contentPanel.add(lblSelectStation);

		/* List of all stations goes here */
		JComboBox<String> comboStation = new JComboBox<>();
		comboStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedKey = (String) combo.getSelectedItem();
				Station selectedStation = listStations.get(selectedKey);
				appState.setSelectedStation(selectedStation);
				System.out.println("Station to go: " + selectedStation.getStationName());
			}
		});
		comboStation.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		stationKeys.forEach(key -> comboStation.addItem(key));
		lblSelectStation.setLabelFor(comboStation);
		comboStation.setBounds(153, 98, 699, 30);
		contentPanel.add(comboStation);

		/* Buttons to select action: Check In or Check Out */
		JRadioButton btnCheckIn = new JRadioButton("Check In");

		btnCheckIn.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					appState.setActCheckIn(true);
				} else if (state == ItemEvent.DESELECTED) {
					appState.setActCheckIn(false);
				}
				System.out.println("Action: " + appState.isActCheckIn());
			}
		});
		btnCheckIn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCheckIn.setBackground(Color.CYAN);
		btnCheckIn.setSelected(true);
		btnCheckIn.setBounds(153, 150, 88, 25);
		contentPanel.add(btnCheckIn);

		JRadioButton btnCheckOut = new JRadioButton("Check Out");
		btnCheckOut.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCheckOut.setBackground(Color.CYAN);
		btnCheckOut.setBounds(261, 150, 109, 25);
		contentPanel.add(btnCheckOut);
		/* create Group action */
		ButtonGroup actionGroup = new ButtonGroup();
		actionGroup.add(btnCheckIn);
		actionGroup.add(btnCheckOut);

		/* Label for actions */
		JLabel lblSelectAction = new JLabel("Action");
		lblSelectAction.setToolTipText("Select action");
		lblSelectAction.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSelectAction.setBounds(35, 134, 97, 53);
		contentPanel.add(lblSelectAction);

		/* Label for check item */
		JLabel goByLabel = new JLabel("Go by");
		goByLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		goByLabel.setBounds(35, 184, 97, 47);
		contentPanel.add(goByLabel);

		/* Choose check by ticket */
		JRadioButton btnTicket = new JRadioButton("Ticket");

		btnTicket.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					appState.setByTicket(true);
					listBarcode.clear();
					listBarcode.addElement("abcz: Raise InvalidIDException");
					listBarcode.addElement("fakeauth: A Fake Ticket");
					listBarcode.addElement("notchout: A OneWayTicket, checked in but not checked out yet");
					listBarcode.addElement("outodate: A OneWayTicket, has been used");
					listBarcode.addElement("vlticket: A valid OneWayTicket");
					listBarcode.addElement("notouttf: A 24hTicket, checked in but not checked out yet");
					listBarcode.addElement("outotime: A 24hTicket, out of valid time");
					listBarcode.addElement("tfoktket: A valid 24hTicket");
				} else if (state == ItemEvent.DESELECTED) {
					appState.setByTicket(false);
					listBarcode.clear();
					listBarcode.addElement("AODC: Raise InvalidIDException");
					listBarcode.addElement("FAKECARD: A Fake Card");
					listBarcode.addElement("NOTCHOUT: A Prepaid Card, checked in but not checked out yet");
					listBarcode.addElement("NOTENBAL: A Prepaid Card, not enough balance to check in");
					listBarcode.addElement("VLPRCARD: A valid Prepaid card");
				}
				System.out.println("Check by ticket: " + appState.isByTicket());
			}
		});
		btnTicket.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnTicket.setSelected(true);
		btnTicket.setBackground(Color.CYAN);
		btnTicket.setBounds(153, 197, 88, 25);
		contentPanel.add(btnTicket);

		/* Choose check by prepaid card */
		JRadioButton btnPrepaidCard = new JRadioButton("Prepaid Card");
		btnPrepaidCard.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnPrepaidCard.setBackground(Color.CYAN);
		btnPrepaidCard.setBounds(261, 197, 125, 25);
		contentPanel.add(btnPrepaidCard);

		/* create Group: Check items */
		ButtonGroup goByGroup = new ButtonGroup();
		goByGroup.add(btnTicket);
		goByGroup.add(btnPrepaidCard);

		/* Text panel describe all list of tickets or cards below it */
		JTextPane panelListOfAvailable = new JTextPane();
		panelListOfAvailable.setEditable(false);
		panelListOfAvailable.setBackground(Color.CYAN);
		panelListOfAvailable.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		panelListOfAvailable.setText("List of available tickets/cards (for testing)");
		panelListOfAvailable.setBounds(35, 231, 344, 25);
		contentPanel.add(panelListOfAvailable);

		/* List of all tickets/cards that available */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 281, 344, 159);
		contentPanel.add(scrollPane);
		JList<String> listAvailable = new JList<String>(listBarcode);
		scrollPane.setViewportView(listAvailable);
		listAvailable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		listAvailable.setSelectionMode(0);

		/* Label: Text Input field for inputting manually */
		JLabel inputBarcodeLabel = new JLabel("You can input your ticket's or card's pseudoBarcode here");
		inputBarcodeLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		inputBarcodeLabel.setBounds(35, 440, 353, 25);
		contentPanel.add(inputBarcodeLabel);

		/* Input area */
		barcodeInputField = new JTextField();
		inputBarcodeLabel.setLabelFor(barcodeInputField);
		barcodeInputField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		barcodeInputField.setBounds(35, 468, 344, 25);
		barcodeInputField.setColumns(10);
		contentPanel.add(barcodeInputField);

		btnEnter = new JButton("Enter");
		btnEnter.setBounds(144, 496, 97, 41);
		contentPanel.add(btnEnter);

		infoFrame = new JTextPane();
		infoFrame.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		infoFrame.setEditable(false);
		infoFrame.setBounds(449, 440, 401, 97);
		contentPanel.add(infoFrame);

		/* Footer seperator */
		JSeparator separatorFooter = new JSeparator();
		separatorFooter.setBounds(0, 550, 905, 7);
		contentPanel.add(separatorFooter);

		/* License */
		JTextPane license = new JTextPane();
		license.setBackground(Color.CYAN);
		license.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		license.setText("\u00A9 2019 DuyPV, Automated Fare Collection System");
		license.setBounds(563, 562, 287, 25);
		license.setEditable(false);
		contentPanel.add(license);

		gatePanel = new GateBoundary();
		contentPanel.add(gatePanel);

		btnEnter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				appState.setItemBarcode(barcodeInputField.getText());
				DataResponse res = exceptionHandler.catchException(appState);
				infoFrame.setText(res.getMessage());
				infoFrame.setForeground(res.getDisplayColor());
				if (res.isGateOpen()) {
					gatePanel.open();
				}
			}
		});
	}
}
