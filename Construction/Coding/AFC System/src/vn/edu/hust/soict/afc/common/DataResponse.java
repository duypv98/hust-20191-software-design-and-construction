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

import java.awt.Color;

/**
 * Define Response of system when processing data
 * @author duypv
 *
 */
public class DataResponse {

	private String message;
	private Color displayColor;
	private boolean gateOpen;

	/**
	 * Constructor
	 */
	public DataResponse() {
		this.message = "";
		this.displayColor = Color.GREEN;
		this.gateOpen = false;
	}

	/**
	 * Get the message to display
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get the display color of text
	 * @return the displayColor
	 */
	public Color getDisplayColor() {
		return displayColor;
	}

	/**
	 * Set display color
	 * @param displayColor the displayColor to set
	 */
	public void setDisplayColor(Color displayColor) {
		this.displayColor = displayColor;
	}

	/**
	 * Get the state of gate
	 * @return the gateOpen
	 */
	public boolean isGateOpen() {
		return gateOpen;
	}

	/**
	 * Set the state of gate
	 * @param gateOpen the gateOpen to set
	 */
	public void setGateOpen(boolean gateOpen) {
		this.gateOpen = gateOpen;
	}

}
