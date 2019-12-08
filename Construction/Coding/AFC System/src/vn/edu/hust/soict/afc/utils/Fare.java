package vn.edu.hust.soict.afc.utils;

import vn.edu.hust.soict.afc.entities.Station;

/**
 * fare
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public class Fare {
	public final static double BASE_DISTANCE = 5.0;
	public final static double BASE_FARE = 1.9;
	public final static double ADDED_DISTANCE = 2.0;
	public final static double ADDED_FARE = 0.4;

	/**
	 * caculate fare
	 * @param incomeStation income station
	 * @param outcomeStation outcome station
	 * @return fare
	 */
	public static double caculate(Station incomeStation, Station outcomeStation) {
		double distance = Math.abs(outcomeStation.getDistance() - incomeStation.getDistance());
		if (distance < BASE_DISTANCE) {
			return BASE_FARE;
		}

		double numberOfAddedDistance = Math.ceil((distance - BASE_DISTANCE) / ADDED_DISTANCE);
		return roundedOneDigitAfterAComma(BASE_FARE + ADDED_FARE * numberOfAddedDistance);
	}

	/**
	 * rounded one digit after a comma
	 * @param number number
	 * @return rounded number
	 */
	public static double roundedOneDigitAfterAComma(double number) {
		return Math.round(number * 10) / 10.0;
	}
}
