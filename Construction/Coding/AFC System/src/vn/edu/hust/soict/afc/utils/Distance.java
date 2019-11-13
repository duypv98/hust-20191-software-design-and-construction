/**
 * 
 */
package vn.edu.hust.soict.afc.utils;

import vn.edu.hust.soict.afc.entities.Station;

/**
 * @author iProfessor
 *
 */
public class Distance {
	public static double calculate(Station s1, Station s2) {
		double result = Math.round((s1.getDistance() - s2.getDistance())*10 / 10.0);
		return result;
	}
	
	public static boolean isCorrectPosition(Station s1, Station s2, Station cur) {
		if (s1.getDistance() <= cur.getDistance() && cur.getDistance() <= s2.getDistance()) {
			return true;
		}
		return false;
	}
}
