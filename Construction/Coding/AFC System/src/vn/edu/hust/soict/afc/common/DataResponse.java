/**
 * 
 */
package vn.edu.hust.soict.afc.common;

import java.awt.Color;

/**
 * @author Professor
 *
 */
public class DataResponse {

	private String message;
	private Color displayColor;
	private boolean gateOpen;

	/**
	 * 
	 */
	public DataResponse() {
		this.message = "";
		this.displayColor = Color.GREEN;
		this.gateOpen = false;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the displayColor
	 */
	public Color getDisplayColor() {
		return displayColor;
	}

	/**
	 * @param displayColor the displayColor to set
	 */
	public void setDisplayColor(Color displayColor) {
		this.displayColor = displayColor;
	}

	/**
	 * @return the gateOpen
	 */
	public boolean isGateOpen() {
		return gateOpen;
	}

	/**
	 * @param gateOpen the gateOpen to set
	 */
	public void setGateOpen(boolean gateOpen) {
		this.gateOpen = gateOpen;
	}

}
