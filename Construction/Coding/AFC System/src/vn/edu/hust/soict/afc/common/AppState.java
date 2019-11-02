/**
 * 
 */
package vn.edu.hust.soict.afc.common;

import vn.edu.hust.soict.afc.entities.Station;

/**
 * @author Professor
 *
 */
public class AppState {

	private Station selectedStation;
	private boolean actCheckIn;
	private boolean byTicket;
	private String itemBarcode;

	/**
	 * 
	 */
	public AppState() {
		this.selectedStation = new Station();
		this.actCheckIn = true;
		this.byTicket = true;
		this.itemBarcode = null;
	}

	/**
	 * @return the selectedStation
	 */
	public Station getSelectedStation() {
		return selectedStation;
	}

	/**
	 * @param selectedStation the selectedStation to set
	 */
	public void setSelectedStation(Station selectedStation) {
		this.selectedStation = selectedStation;
	}

	/**
	 * @return the actCheckIn
	 */
	public boolean isActCheckIn() {
		return actCheckIn;
	}

	/**
	 * @param actCheckIn the actCheckIn to set
	 */
	public void setActCheckIn(boolean actCheckIn) {
		this.actCheckIn = actCheckIn;
	}

	/**
	 * @return the byTicket
	 */
	public boolean isByTicket() {
		return byTicket;
	}

	/**
	 * @param byTicket the byTicket to set
	 */
	public void setByTicket(boolean byTicket) {
		this.byTicket = byTicket;
	}

	/**
	 * @return the itemBarcode
	 */
	public String getItemBarcode() {
		return itemBarcode;
	}

	/**
	 * @param itemBarcode the itemBarcode to set
	 */
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}
}
