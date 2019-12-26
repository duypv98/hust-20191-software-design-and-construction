/**
 * @author duypv
 * @date Oct 23, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.common;

import vn.edu.hust.soict.afc.entities.Station;

/**
 * Define Application's state
 * @author duypv
 *
 */
public class AppState {

	private Station selectedStation;
	private boolean actCheckIn;
	private boolean byTicket;
	private String itemBarcode;

	/**
	 * Constructor
	 */
	public AppState() {
		this.selectedStation = new Station();
		this.actCheckIn = true;
		this.byTicket = true;
		this.itemBarcode = null;
	}

	/**
	 * Get selected station
	 * @return the selectedStation
	 */
	public Station getSelectedStation() {
		return selectedStation;
	}

	/**
	 * Set selected station
	 * @param selectedStation the selectedStation to set
	 */
	public void setSelectedStation(Station selectedStation) {
		this.selectedStation = selectedStation;
	}

	/**
	 * Get action: CheckIn or not
	 * @return the actCheckIn
	 */
	public boolean isActCheckIn() {
		return actCheckIn;
	}

	/**
	 * Set action
	 * @param actCheckIn the actCheckIn to set
	 */
	public void setActCheckIn(boolean actCheckIn) {
		this.actCheckIn = actCheckIn;
	}

	/**
	 * Get item: Ticket or Card
	 * @return the byTicket
	 */
	public boolean isByTicket() {
		return byTicket;
	}

	/**
	 * Set item
	 * @param byTicket the byTicket to set
	 */
	public void setByTicket(boolean byTicket) {
		this.byTicket = byTicket;
	}

	/**
	 * Get pseudoBarcode
	 * @return the itemBarcode
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * Set pseudoBarcode
	 * @param itemBarcode the itemBarcode to set
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}
}
