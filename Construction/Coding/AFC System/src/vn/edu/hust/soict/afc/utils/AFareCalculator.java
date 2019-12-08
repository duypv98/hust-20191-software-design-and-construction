package vn.edu.hust.soict.afc.utils;

import vn.edu.hust.soict.afc.entities.Station;

public abstract class AFareCalculator {
	protected final double BASE_DISTANCE = 5.0;
	public final double BASE_FARE = 1.9;
	protected final double ADDED_DISTANCE = 2.0;
	protected final double ADDED_FARE = 0.4;

	public abstract double caculate(Station incomeStation, Station outcomeStation);
}