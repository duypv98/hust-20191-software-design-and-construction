/**
 * 
 */
package vn.edu.hust.soict.afc.exception;

/**
 * @author iProfessor
 *
 */
@SuppressWarnings("serial")
public class WrongStationException extends RuntimeException {

	/**
	 * @param message
	 */
	public WrongStationException(String message) {
		super(message);
	}

}
