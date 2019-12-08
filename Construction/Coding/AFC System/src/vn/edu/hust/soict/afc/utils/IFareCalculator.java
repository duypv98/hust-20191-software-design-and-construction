package vn.edu.hust.soict.afc.utils;

import vn.edu.hust.soict.afc.entities.Station;

public interface IFareCalculator {
	public static final double BASE_DISTANCE = 5.0;
	public static final double BASE_FARE = 1.9;
	public static final double ADDED_DISTANCE = 2.0;
	public static final double ADDED_FARE = 0.4;

	public double caculate(Station incomeStation, Station outcomeStation);
}