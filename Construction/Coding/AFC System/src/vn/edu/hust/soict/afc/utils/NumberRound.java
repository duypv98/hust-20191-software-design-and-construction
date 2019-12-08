package vn.edu.hust.soict.afc.utils;

/**
 * 
 * @author duytruong
 *
 */
public class NumberRound {

	/**
	 * 
	 * @param number
	 * @return
	 */
	public final static double roundOneDigitAfterAComma(double number) {
		return Math.round(number * 10) / 10.0;
	}
}
