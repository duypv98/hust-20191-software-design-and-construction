package vn.edu.hust.soict.afc.utils;

import vn.edu.hust.soict.afc.entities.Station;

/**
 * 
 * @author duytruong
 *
 */
public class FareCalculatorByDistance implements FareCalculator {
	
	@Override
	public double caculate(Station incomeStation, Station outcomeStation) {
		double distance = Math.abs(outcomeStation.getDistance() - incomeStation.getDistance());
		if (distance < BASE_DISTANCE) {
			return BASE_FARE;
		}

		double numberOfAddedDistance = Math.ceil((distance - BASE_DISTANCE) / ADDED_DISTANCE);
		return NumberRound.roundOneDigitAfterAComma(BASE_FARE + ADDED_FARE * numberOfAddedDistance);
	}

}
