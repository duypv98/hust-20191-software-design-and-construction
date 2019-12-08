package vn.edu.hust.soict.afc.utils;

public class NumberRound {

	public final static double roundOneDigitAfterAComma(double number) {
		return Math.round(number * 10) / 10.0;
	}
}
